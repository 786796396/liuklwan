package com.ucap.cloud_web.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.publics.util.utils.DateUtils;
import com.ucap.cloud_web.common.ComparatorHashMap;
import com.ucap.cloud_web.common.DicUtils;
import com.ucap.cloud_web.constant.CacheType;
import com.ucap.cloud_web.constant.DatabaseInfoType;
import com.ucap.cloud_web.constant.DatabaseTreeInfoType;
import com.ucap.cloud_web.dto.LinkAllDetailRequest;
import com.ucap.cloud_web.dto.RelationsPeriodRequest;
import com.ucap.cloud_web.dto.ServicePeriodRequest;
import com.ucap.cloud_web.entity.ContractInfo;
import com.ucap.cloud_web.entity.DatabaseInfo;
import com.ucap.cloud_web.entity.DicConfig;
import com.ucap.cloud_web.entity.DicItem;
import com.ucap.cloud_web.entity.LinkAllInfo;
import com.ucap.cloud_web.entity.RelationsPeriod;
import com.ucap.cloud_web.entity.ServicePeriod;
import com.ucap.cloud_web.service.IDatabaseInfoService;
import com.ucap.cloud_web.service.IDicConfigService;
import com.ucap.cloud_web.service.ILinkAllDetailService;
import com.ucap.cloud_web.service.ILinkAllInfoService;
import com.ucap.cloud_web.service.IRelationsPeriodService;
import com.ucap.cloud_web.service.IServicePeriodService;
import com.ucap.cloud_web.shiro.Constants;
import com.ucap.cloud_web.util.ExportExcel;
import com.ucap.cloud_web.util.MonitoringCacheUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @描述： 全站链接可用性
 * @包：com.ucap.cloud_web.action
 * @文件名称：DepthLinkAllAction 深度监测-全站链接可用性
 * @公司名称：开普互联
 * @author luocheng@ucap.com.cn
 * @时间：2017/2/21
 * @version V1.0
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class DepthLinkAllAction extends BaseAction {
	@Autowired
	private DicUtils dicUtils;
	@Autowired
	private IServicePeriodService servicePeriodServiceImpl;
	@Autowired
	private IRelationsPeriodService relationsPeriodServiceImpl;
	@Autowired
	private IDatabaseInfoService databaseInfoServiceImpl;
	@Autowired
	private ILinkAllDetailService linkAllDetailServiceImpl;
	@Autowired
	private IDicConfigService dicConfigServiceImpl;
	@Autowired
	private ILinkAllInfoService linkAllInfoServiceImpl;

	private String siteCode;// 获取当前单位的组织编码
	// 类型为全面检测产品
	//private Integer[] productTypeArr = { CrmProductsType.DETECTION.getCode() };

	/**
	 * @描述:[跳转] 全站链接可用性主页面
	 * @作者:luocheng@ucap.com.cn
	 * @时间:2017/2/21
	 * @return
	 */
	public String depthLinkAllMain() {
		List<DicItem> dicList = dicUtils.getDictList("siteType");
		request.setAttribute("dicList", dicList); // 网站类别

		// 全站链接可用性的服务周期
		List<ServicePeriodRequest> servicePeriodLinkAll = getServicePeriodLinkAll();
		request.setAttribute("servicePeriodLinkAll", servicePeriodLinkAll);

		// 默认显示 全站链接可用性的最近的服务周期
		if (CollectionUtils.isNotEmpty(servicePeriodLinkAll)
				&& servicePeriodLinkAll.size() > 0) {
			request.setAttribute("nearservicePeriodLinkAll",
					servicePeriodLinkAll.get(0).getStartDate() + "至"
							+ servicePeriodLinkAll.get(0).getEndDate());
		} else {
			request.setAttribute("nearservicePeriodLinkAll", "未查询到周期");
		}
		return "success";
	}

	/**
	 * @描述:获取当前组织单位的 服务周期
	 * @作者:luocheng@ucap.com.cn
	 * @时间:2017/2/21
	 * @return
	 */
	public List<ServicePeriodRequest> getServicePeriodLinkAll() {
		siteCode = getCurrentSiteCode(); // 获得当前登录的组织单位siteCode
		try {
			// 获得当前组织单位的 服务周期
			ServicePeriodRequest spRequest = new ServicePeriodRequest();
			spRequest.setComboI(4);
			spRequest.setSiteCode(siteCode);
			List<ServicePeriodRequest> servicePeriodList = servicePeriodServiceImpl
					.queryByRelationPeriod(spRequest);
			return servicePeriodList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @描述:[导出Excel] 全站链接可用性数据
	 * @作者:luocheng@ucap.com.cn
	 * @时间:2017/2/21
	 * @return
	 */
	public void depthLinkAllExcel() {
		String siteType = request.getParameter("siteType"); // 网站类别
		Integer servicePeriodId = null;
		ServicePeriodRequest spr = null;
		String title = null;
		ServicePeriod sp = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		if (StringUtils.isNotEmpty(request.getParameter("servicePeriodId"))) {
			servicePeriodId = Integer.parseInt(request
					.getParameter("servicePeriodId")); // 监测周期id
			 sp = servicePeriodServiceImpl.get(servicePeriodId);
			title = "全站链接可用性(" + sdf.format(sp.getStartDate()) + "至"
					+ sdf.format(sp.getEndDate()) + ")";
		} else {
			// 默认查询的周期
			List<ServicePeriodRequest> splist = getServicePeriodLinkAll();// 获取周期集合
			if (CollectionUtils.isNotEmpty(splist) && splist.size() != 0) {
				spr = splist.get(0); // 取得最近的一个周期
				title = "全站链接可用性(" + spr.getStartDate() + "至"
						+ spr.getEndDate() + ")";
			}
		}
		try {
			Map<String, Object> resultMap = getDepthLinkAllMsgs(siteType,
					servicePeriodId);
			if (resultMap != null) {
				@SuppressWarnings("unchecked")
				List<Map<String, Object>> allDatabaseBasicList = (List<Map<String, Object>>) resultMap
						.get("body");
				ArrayList<Object[]> list = new ArrayList<Object[]>();
				String databaseName = Constants.getCurrendUser().getUserName().trim();//获得当前登录站点名称
				String fileName = "";
				
				if (StringUtils.isNotEmpty(request.getParameter("servicePeriodId"))) {
					fileName=databaseName+"-深度检测-全站链接可用性("
							+ sdf.format(sp.getStartDate()) + "至"
							+ sdf.format(sp.getEndDate()) + ").xls";
					
				}else{
					fileName=databaseName+"-深度检测-全站链接可用性("+ spr.getStartDate() + "至"+ spr.getEndDate() + ").xls";
							
				}
				
				Object[] obj1 = new Object[] {};
				obj1 = new Object[] { "序号", "网站名称", "标识码", "全站不可用链接个数" };
				list.add(obj1);
				int msgNum = 0;
				for (Map<String, Object> map : allDatabaseBasicList) {
					msgNum++;
					DatabaseInfo databaseInfo = (DatabaseInfo) map
							.get("databaseInfo");

					Object[] obj = new Object[4];
					obj[0] = msgNum;
					obj[1] = databaseInfo.getName(); // 网站名称
					obj[2] = databaseInfo.getSiteCode(); // 标识码
					obj[3] = (Integer) map.get("size");// 全站不可用链接个数

					list.add(obj);
				}
				ExportExcel.organConnHomeExcel(fileName, title, list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @描述:[查询] 全站链接可用性表格数据
	 * @作者:luocheng@ucap.com.cn
	 * @时间:2017/2/21
	 * @return
	 */
	public void getDepthLinkAllMsg() {
		String siteType = request.getParameter("siteType"); // 网站类别
		Integer servicePeriodId = null;

		if (StringUtils.isNotEmpty(request.getParameter("servicePeriodId"))) {
			servicePeriodId = Integer.parseInt(request
					.getParameter("servicePeriodId")); // 监测周期id
		}
		Map<String, Object> resultMap = getDepthLinkAllMsgs(siteType,
				servicePeriodId);
		if (resultMap != null) {
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} else {
			resultMap = new HashMap<String, Object>();
			resultMap.put("success", "false");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		}

	}

	/**
	 * @描述:根据条件查询出 【全站链接可用性】数据
	 * @作者:luocheng@ucap.com.cn
	 * @时间:2017/2/21
	 * @param siteType
	 *            网站类别
	 * @param servicePeriodId
	 *            监测周期Id
	 * @return 返回查询出的结果Map
	 */
	@SuppressWarnings("unused")
	public Map<String, Object> getDepthLinkAllMsgs(String siteType,
			Integer servicePeriodId) {
		// 获取当前组织机构编码
		String siteCode = getCurrentSiteCode();
		/**老合同信息**/
		List<ContractInfo> crmlist = getContractInfoList(siteCode, DateUtils.formatStandardDate(new Date()));
		/**新产品信息**/
		// List<CrmProductsResponse> crmlist = getCrmProductsList(siteCode,
		// productTypeArr,
		// DateUtils.formatStandardDate(new Date()), null, null);
		if (CollectionUtils.isEmpty(crmlist)) {
			// 没有查询到此siteCode的合同,直接返回页面，提示相关信息
			Map<String, Object> otherResultMap = new HashMap<String, Object>();
			otherResultMap.put("success", "other");
			return otherResultMap;
		}else{
			Map<String, Object> resultMap = new HashMap<String, Object>(); // 向页面传递数据的map
			siteCode = getCurrentSiteCode();

			String cacheDataList = CacheType.DEPTHLINKALL_TABLELIST.getName(); // --获得缓存名字
			String keyDataList = cacheDataList + siteCode + siteType
					+ (servicePeriodId == null ? 0 : servicePeriodId) + DateUtils.getYesterdayStr(); // --缓存名
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> allDatabaseList = (List<Map<String, Object>>) MonitoringCacheUtils
					.get(keyDataList); // 查询缓存中是否存在

			try {
				// 获得默认查询的周期数据
				if (servicePeriodId == null) { // 传递过来的周期为空
												// 则默认查询最近的一个周期
					List<ServicePeriodRequest> servicePeriodLinkAll = getServicePeriodLinkAll();

					if (CollectionUtils.isNotEmpty(servicePeriodLinkAll)
							&& servicePeriodLinkAll.size() > 0) {
						servicePeriodId = servicePeriodLinkAll.get(0).getId(); // 默认查询的周期id
					} else {
						// 没有查询到周期 则直接返回
						resultMap.put("noServicePeriod", -1); // -1表示没有查询到周期
						resultMap.put("success", "true");
						return resultMap;
					}
				}

				// 根据组织机构编码和组织机构的级别，获取对应的网站标识码集合
				String cacheDeptLinkAllData = CacheType.DEPTHLINKALL_DATABASELIST
						.getName(); // 获得缓存名字
				String keyData = cacheDeptLinkAllData + siteCode + siteType
						+ (servicePeriodId == null ? 0 : servicePeriodId) + DateUtils.getYesterdayStr();
				; // 缓存名
				@SuppressWarnings("unchecked")
				List<DatabaseInfo> siteList = (List<DatabaseInfo>) MonitoringCacheUtils
						.get(keyData); // 查询缓存中是否存在
				if (siteList == null) {
					// 根据选择的servicePeriodId来查询有哪些站点在此 周期下
					RelationsPeriodRequest relationsPeriodRequest = new RelationsPeriodRequest();
					relationsPeriodRequest.setServicePeriodId(servicePeriodId);
					relationsPeriodRequest.setPageSize(Integer.MAX_VALUE);
					List<RelationsPeriod> relationsPeriodList = relationsPeriodServiceImpl
							.queryList(relationsPeriodRequest);

					HashMap<String, Object> hashMap = new HashMap<String, Object>();
					if (relationsPeriodList.size() != 0
							|| CollectionUtils.isNotEmpty(relationsPeriodList)) {
						hashMap.put("ids", relationsPeriodList);
					}
					if (StringUtils.isNotEmpty(siteType)) {
						hashMap.put(
								"siteType",
								Integer.parseInt(siteType) == 0 ? null : Integer
										.parseInt(siteType));// 类型 1本级门户 2本级部门 3
																// 下属单位
					}
					hashMap.put("orgSiteCode", siteCode);
					hashMap.put("isexp", 1);// 类型 1 正常 2例外 3 关停
					siteList = databaseInfoServiceImpl
							.queryListJoinDatabaseTree(hashMap);

					MonitoringCacheUtils.put(keyData, siteList); // 将数据存到缓存中
					if (siteList == null || siteList.size() < 1) { // 此周期内查询不到数据
						resultMap.put("success", "false");
						return resultMap;
					}
				}
				// 封装了 站点信息 以及对应的数据的List
				if (allDatabaseList == null) {
					allDatabaseList = new ArrayList<Map<String, Object>>();
					Integer sercurityCount = null; // 查询出的栏目或者问题数量

					for (DatabaseInfo databaseInfo : siteList) {
						Map<String, Object> resultgMap = new HashMap<String, Object>();
						// 根据服务周期和siteCode查询对应的栏目问题数量
						LinkAllDetailRequest detailRequest = new LinkAllDetailRequest();
						detailRequest.setServicePeriodId(Integer
								.valueOf(servicePeriodId));
						detailRequest.setSiteCode(databaseInfo.getSiteCode());
						detailRequest.setPageSize(Integer.MAX_VALUE);
						detailRequest.setWebType(1); // 站内
						
						DicConfig dicConfig = dicConfigServiceImpl.get(20); //取出确认死链配置 ,组织只查询 确认死链个数
						String[] strArray = dicConfig.getValue().split(",");
						detailRequest.setErrorCodeArr(strArray);

						Integer size = linkAllDetailServiceImpl
								.queryCount(detailRequest);
						resultgMap.put("databaseInfo", databaseInfo);
						resultgMap.put("size", size == null ? 0 : size); // 全站不可用链接个数
						allDatabaseList.add(resultgMap);

					}
					ComparatorHashMap comparatorHashMapTotal = new ComparatorHashMap();
					Collections.sort(allDatabaseList, comparatorHashMapTotal); // 按问题个数排序

					MonitoringCacheUtils.put(keyDataList, allDatabaseList); // 将数据存到缓存中
				}
				resultMap.put("body", allDatabaseList);
				resultMap.put("success", "true");
				return resultMap;
			} catch (Exception e) {
				e.printStackTrace();
				resultMap.put("success", "false");
				return resultMap;
			}
		}

	
	}

	/**
	 * 【组织单位首页】 获取当前组织单位下所有站点的 全站不可用链接的总数
	 * 
	 * @作者:luocheng@ucap.com.cn
	 * @时间:2017/2/21
	 * @return
	 */
	public void getDepthLinkAllSum() {
		Map<String, Object> resultMap = new HashMap<String, Object>(); // 向页面传递数据的map
		String yesStr = DateUtils.getYesterdayStr(); // 获取昨天的日期
		List<ServicePeriodRequest> splist = getServicePeriodLinkAll();// 获取周期集合
		if (CollectionUtils.isNotEmpty(splist) && splist.size() != 0) {
			ServicePeriodRequest spRequest = splist.get(0); // 取得最近的一个周期
			String strat = spRequest.getStartDate();
			String end = spRequest.getEndDate();
			if (yesStr.compareTo(strat) >= 0 && end.compareTo(yesStr) >= 0) {
				// 昨天在周期内

				// 查询所有类型 第一个周期的数据
				Map<String, Object> resultsMap = getDepthLinkAllMsgs(null, null);
				if (resultsMap != null && resultsMap.get("success").equals("true")) {
					@SuppressWarnings("unchecked")
					List<Map<String, Object>> allDatabaseBasicList = (List<Map<String, Object>>) resultsMap
							.get("body");
					int linkAllSum = 0;
					for (Map<String, Object> map : allDatabaseBasicList) {
						@SuppressWarnings("unused")
						DatabaseInfo databaseInfo = (DatabaseInfo) map
								.get("databaseInfo");
						linkAllSum += (Integer) map.get("size");// 统计所有站点的
																// 全站不可链接的总数
					}
					resultMap.put("linkAllSum", linkAllSum);
					resultMap.put("success", "true");
				} else {
					resultMap.put("success", "false");
				}

			} else {
				resultMap.put("success", "false");
			}
		} else {
			resultMap.put("success", "false");
		}

		writerPrint(JSONObject.fromObject(resultMap).toString());
	}
	
	
	/**
	 * @Description: 组织单位  全站不可用链接 折线图 [重构]
	 * @author luocheng --- 2017-03-16
	 */
	public void linkAllLineOrgRebuild() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		ArrayList<Object> list = new ArrayList<Object>();
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		try {
			String currentSiteCode = getCurrentSiteCode();
			// 根据合同Id来查询
			ServicePeriodRequest spRequest = new ServicePeriodRequest();
			spRequest.setComboI(4);
			spRequest.setSiteCode(currentSiteCode);
			List<ServicePeriodRequest> servicePeriodList = servicePeriodServiceImpl
					.queryByRelationPeriod(spRequest);

			hashMap.put("orgSiteCode", currentSiteCode);
			hashMap.put("isLink", DatabaseTreeInfoType.ISLINK.getCode());
			hashMap.put("isExp", DatabaseInfoType.NORMAL.getCode());
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date oldDate = dateFormat.parse(Constants.OLDDATETIME);
			int i = 0;
			Collections.reverse(servicePeriodList);
			for (ServicePeriodRequest spR : servicePeriodList) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				ServicePeriod sp = servicePeriodServiceImpl.get(spR.getId());
				if (sp.getStartDate().getTime() < oldDate.getTime()) {
					map.put("oldServicePeriod", 1); // 是老规则统计的 周期数据
				} else {
					map.put("oldServicePeriod", 2); // 是新规则统计的 周期数据
				}
				map.put("periodStr",
						sp == null ? "" : sdf.format(sp.getStartDate()) + "至"
								+ sdf.format(sp.getEndDate()));
				int servicePeriodId = ++i;
				map.put("periodNum", "第" + servicePeriodId + "期");
				hashMap.put("servicePeriodId", spR.getId());
				List<LinkAllInfo> queryList = linkAllInfoServiceImpl
						.getAlllineOrg(hashMap);
				int websiteSum = 0;
				int webSiteDetailSum = 0;
				if (!CollectionUtils.isEmpty(queryList) && queryList.size() > 0) {
					LinkAllInfo linkAllInfo = queryList.get(0);
					websiteSum = linkAllInfo.getWebsiteSum();
					if(websiteSum == 0){
						Map<String, Object> resultsMap = getDepthLinkAllMsgs(null,
								spR.getId());
						@SuppressWarnings("unchecked")
						List<Map<String, Object>> allDatabaseBasicList = (List<Map<String, Object>>) resultsMap
								.get("body");
						int linkAllSum = 0;
						if(allDatabaseBasicList != null && allDatabaseBasicList.size() > 0){
							for (Map<String, Object> map1 : allDatabaseBasicList) {
								linkAllSum += (Integer) map1.get("size");// 统计所有站点的
							}
						}
						webSiteDetailSum += linkAllSum;
						websiteSum = webSiteDetailSum;
						if(websiteSum == 0){
							if(webSiteDetailSum == 0){
								map.put("oldServicePeriod", 2); // 是新规则统计的 周期数据
							}
						}else if(websiteSum != 0){
								map.put("oldServicePeriod", 2); // 是新规则统计的 周期数据
						}
					}
					map.put("websiteSum", websiteSum);
				}else{
					map.put("websiteSum", 0);
					map.put("oldServicePeriod", 2);
				} 
				list.add(map);
			}
			writerPrint(JSONArray.fromObject(list).toString());
			Collections.reverse(servicePeriodList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

}
