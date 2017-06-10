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
import com.ucap.cloud_web.bizService.DatabaseBizService;
import com.ucap.cloud_web.common.ComparatorHashMap;
import com.ucap.cloud_web.common.DicUtils;
import com.ucap.cloud_web.constant.CacheType;
import com.ucap.cloud_web.constant.DatabaseLinkType;
import com.ucap.cloud_web.constant.DatabaseTreeInfoType;
import com.ucap.cloud_web.dto.ChannelPointRequest;
import com.ucap.cloud_web.dto.RelationsPeriodRequest;
import com.ucap.cloud_web.dto.SecurityHomeChannelRequest;
import com.ucap.cloud_web.dto.ServicePeriodRequest;
import com.ucap.cloud_web.dtoResponse.SecurityGuaranteeResponse;
import com.ucap.cloud_web.entity.ContractInfo;
import com.ucap.cloud_web.entity.DatabaseInfo;
import com.ucap.cloud_web.entity.DicItem;
import com.ucap.cloud_web.entity.RelationsPeriod;
import com.ucap.cloud_web.entity.SecurityHomeChannel;
import com.ucap.cloud_web.service.IChannelPointService;
import com.ucap.cloud_web.service.IDatabaseInfoService;
import com.ucap.cloud_web.service.IDetectionPeriodSiteService;
import com.ucap.cloud_web.service.IRelationsPeriodService;
import com.ucap.cloud_web.service.ISecurityBlankDetailService;
import com.ucap.cloud_web.service.ISecurityHomeChannelService;
import com.ucap.cloud_web.service.ISecurityResponseService;
import com.ucap.cloud_web.service.ISecurityServcieService;
import com.ucap.cloud_web.service.IServicePeriodService;
import com.ucap.cloud_web.shiro.Constants;
import com.ucap.cloud_web.util.ExportExcel;
import com.ucap.cloud_web.util.MonitoringCacheUtils;

import net.sf.json.JSONObject;

/**
 * @描述： 内容保障问题
 * @包：com.ucap.cloud_web.action
 * @文件名称：ContentGuaranteeAction 深度监测-内容保障问题
 * @公司名称：开普互联
 * @author luocheng@ucap.com.cn
 * @时间：2017/1/9
 * @version V1.0
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class SecurityGuaranteeAction extends BaseAction {
	@Autowired
	private DicUtils dicUtils;
	@Autowired
	private IChannelPointService channelPointServiceImpl;
	@Autowired
	private ISecurityResponseService securityResponseServiceImpl;
	@Autowired
	private ISecurityServcieService securityServcieServiceImpl;
	@Autowired
	private ISecurityHomeChannelService securityHomeChannelServiceImpl;
	@Autowired
	private IServicePeriodService servicePeriodServiceImpl;
	@Autowired
	private IRelationsPeriodService relationsPeriodServiceImpl;
	@Autowired
	private IDatabaseInfoService databaseInfoServiceImpl;
	@Autowired
	private DatabaseBizService databaseBizServiceImpl;
	@Autowired
	private ISecurityBlankDetailService securityBlankDetailServiceImpl;
	@Autowired
	private IDetectionPeriodSiteService detectionPeriodSiteServiceImpl;
	

	private String siteCode;// 获取当前单位的组织编码

	/**
	 * @描述:[跳转] 内容保障主页面
	 * @作者:luocheng@ucap.com.cn
	 * @时间:2017/1/9
	 * @return
	 */
	public String securityGuaranteeMain() {
		String typeId = request.getParameter("typeId");
		request.setAttribute("typeIdJump", typeId); // 网站类别
		List<DicItem> dicList = dicUtils.getDictList("siteType");
		request.setAttribute("dicList", dicList); // 网站类别
		List<ServicePeriodRequest> servicePeriodRequest = getServicePeriodList();
		request.setAttribute("servicePeriodListResult", servicePeriodRequest); // 服务周期

		// 默认显示 最近的服务周期
		if (CollectionUtils.isNotEmpty(servicePeriodRequest)
				&& servicePeriodRequest.size() > 0) {
			request.setAttribute("nearServicePeriod",
					servicePeriodRequest.get(0).getStartDate() + "至"
							+ servicePeriodRequest.get(0).getEndDate());
			request.setAttribute("nearServicePeriodId",servicePeriodRequest.get(0).getId());
		} else {
			request.setAttribute("nearServicePeriod", "未查询到周期");
		}

		// 基本信息的服务周期
		List<ServicePeriodRequest> servicePeriodRequestBasic = getServicePeriodListBasic();
		request.setAttribute("servicePeriodRequestBasic",
				servicePeriodRequestBasic);

		// 默认显示 最近的基本信息的服务周期
		if (CollectionUtils.isNotEmpty(servicePeriodRequestBasic)
				&& servicePeriodRequestBasic.size() > 0) {
			request.setAttribute("nearServicePeriodBasic",
					servicePeriodRequestBasic.get(0).getStartDate() + "至"
							+ servicePeriodRequestBasic.get(0).getEndDate());
			request.setAttribute("nearServicePeriodBasicId",servicePeriodRequestBasic.get(0).getId());
		} else {
			request.setAttribute("nearServicePeriodBaisc", "未查询到周期");
		}
		return "success";
	}


	/**
	 * @描述:获取当前组织单位的 服务周期
	 * @作者:luocheng@ucap.com.cn
	 * @时间:2017/1/10
	 * @return
	 */
	public List<ServicePeriodRequest> getServicePeriodList() {
		siteCode = getCurrentSiteCode(); // 获得当前登录的组织单位siteCode
		try {
			// 获得当前组织单位的 服务周期
			ServicePeriodRequest spRequest = new ServicePeriodRequest();
			spRequest.setSiteCode(siteCode);
			spRequest.setComboI(4);
			List<ServicePeriodRequest> servicePeriodList = servicePeriodServiceImpl
					.queryByRelationPeriod(spRequest);
			return servicePeriodList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @描述:获取当前组织单位的基本信息的 服务周期
	 * @作者:luocheng@ucap.com.cn
	 * @时间:2017/2/8
	 * @return
	 */
	public List<ServicePeriodRequest> getServicePeriodListBasic() {
		siteCode = getCurrentSiteCode(); // 获得当前登录的组织单位siteCode

		try {
			// 获得当前组织单位的合同 判断是否存在合同
			List<ContractInfo> contractList = databaseBizServiceImpl.getContractInfoServurity(siteCode, DateUtils.formatStandardDate(new Date()));
			if (CollectionUtils.isNotEmpty(contractList)) {
				ServicePeriodRequest spRequest = new ServicePeriodRequest();
				spRequest.setSiteCode(siteCode);
				// 有合同 需要区分是否有高级版任务
				Integer currentInfoId = contractList.get(contractList.size()-1).getId(); // 当前合同的id
				String serviceBeginDate = DateUtils.formatStandardDate(contractList.get(contractList.size()-1).getContractBeginTime()); // 当前合同的开始时间

				spRequest.setComboI(4); // 查询高级服务周期
				spRequest.setContractInfoId(currentInfoId);
				spRequest.setStartDateTime(serviceBeginDate);

				List<ServicePeriodRequest> servicePeriodBasicList = databaseBizServiceImpl
						.queryByRelationPeriodBasic(spRequest);
				// 获取当前周期 是否为高级版 comboId = 4 既是高级版
				return servicePeriodBasicList;
			} else {
				// 不存在合同
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**新产品信息**/
	/**
	 * @描述:获取当前组织单位的基本信息的 服务周期
	 * @作者:luocheng@ucap.com.cn
	 * @时间:2017/2/8
	 * @return
	 */
	// public List<ServicePeriodRequest> getServicePeriodListBasic() {
	// siteCode = getCurrentSiteCode(); // 获得当前登录的组织单位siteCode
	//
	// try {
	// // 获得当前组织单位的合同 判断是否存在合同
	// Integer[] productTypeArr = { CrmProductsType.DETECTION.getCode() };
	// List<CrmProductsResponse> crmlist = getCrmProductsList(siteCode,
	// productTypeArr,
	// DateUtils.formatStandardDate(new Date()), null, null);
	// if (CollectionUtils.isNotEmpty(crmlist)) {
	// ServicePeriodRequest spRequest = new ServicePeriodRequest();
	// spRequest.setSiteCode(siteCode);
	// // 有合同 需要区分是否有高级版任务
	// Integer currentInfoId = crmlist.get(0).getId(); // 当前合同的id
	// String serviceBeginDate =
	// DateUtils.formatStandardDate(crmlist.get(0).getBeginTime()); // 当前合同的开始时间
	//
	// spRequest.setComboI(4); // 查询高级服务周期
	// spRequest.setContractInfoId(currentInfoId);
	// spRequest.setStartDateTime(serviceBeginDate);
	//
	// List<ServicePeriodRequest> servicePeriodBasicList =
	// databaseBizServiceImpl
	// .queryByRelationPeriodBasic(spRequest);
	// // 获取当前周期 是否为高级版 comboId = 4 既是高级版
	// return servicePeriodBasicList;
	// } else {
	// // 不存在合同
	// return null;
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// return null;
	// }

	/**
	 * @描述:[导出Excel] 内容保障问题数据
	 * @作者:luocheng@ucap.com.cn
	 * @时间:2017/1/9
	 * @return
	 */
	public void securityExcel() {
		String modeVal = request.getParameter("modeVal"); // 检测方式
		String siteType = request.getParameter("siteType"); // 网站类别
		String typeId = request.getParameter("typeId"); // 内容保障的模块类型
		String servicePeriod = request.getParameter("servicePeriod"); // 监测周期
		Integer servicePeriodId = null;
		String title = "";
		List<ServicePeriodRequest> listPeriod = null;
		if (StringUtils.isNotEmpty(request.getParameter("servicePeriodId"))) {
			servicePeriodId = Integer.parseInt(request
					.getParameter("servicePeriodId")); // 监测周期id
		}
		               //人工才有检测周期
		if(StringUtils.isEmpty(servicePeriod)){
				if(typeId.equals("0")){
					listPeriod = getServicePeriodListBasic();
					if(CollectionUtils.isNotEmpty(listPeriod) && listPeriod.size()>0){
						servicePeriod = "(" + listPeriod.get(0).getStartDate() + "至" + listPeriod.get(0).getEndDate() + ")";
					}
				}else{
					listPeriod = getServicePeriodList();
					if(CollectionUtils.isNotEmpty(listPeriod) && listPeriod.size()>0){
						servicePeriod = "(" + listPeriod.get(0).getStartDate() + "至" + listPeriod.get(0).getEndDate() + ")";
					}
				}
			}else{
					servicePeriod = "(" + servicePeriod + ")";
			}
		
		
		try {
			String siteName =  Constants.getCurrendUser().getUserName().trim();//获得当前登录站点名称
			Map<String, Object> resultMap = getSecurityMsgs(siteType, typeId,
					servicePeriod, servicePeriodId, modeVal);
			if (resultMap != null) {
				@SuppressWarnings("unchecked")
				List<Map<String, Object>> allDatabaseBasicList = (List<Map<String, Object>>) resultMap
						.get("body");
				ArrayList<Object[]> list = new ArrayList<Object[]>();
				String fileName = "";
				
				if(modeVal.equals("1")){
					fileName=siteName+"-深度检测-内容保障问题("
							+ servicePeriod + ").xls";
				}else{
					fileName=siteName+"-深度检测-内容保障问题("
							+ DateUtils.formatStandardDate(new Date()) + ").xls";
				}
				
				Object[] obj1 = new Object[] {};
				if (typeId.equals("0")) {
					if (modeVal.equals("1")) { // 人工监测
						obj1 = new Object[] { "序号", "标识码","网站名称", "逾期未更新" };
						list.add(obj1);
						title = "基本信息"+servicePeriod;
					} else { // 系统监测
						obj1 = new Object[] { "序号", "标识码","网站名称", "监测栏目","栏目数量","逾期未更新" };
						list.add(obj1);
						title = "基本信息";
					}
				} else if (typeId.equals("1")) {
					obj1 = new Object[] { "序号","标识码", "网站名称", "空白栏目个数" };
					list.add(obj1);
					title = "空白栏目"+servicePeriod;
				} else if (typeId.equals("2")) {
					obj1 = new Object[] { "序号", "标识码","网站名称","问题个数" };
					list.add(obj1);
					title = "互动回应差"+servicePeriod;
				} else if (typeId.equals("3")) {
					obj1 = new Object[] { "序号","标识码", "网站名称", "问题个数" };
					list.add(obj1);
					title = "服务不实用"+servicePeriod;
				}

				int msgNum = 0;
				for (Map<String, Object> map : allDatabaseBasicList) {
					msgNum++;
					
					if (typeId.equals("0")) {
						DatabaseInfo databaseInfo = (DatabaseInfo) map.get("databaseInfo");

						Object[] obj = null;
						if (modeVal.equals("1")) { // 人工
							obj = new Object[4];
							obj[0] = msgNum;
							obj[1] =  databaseInfo.getSiteCode();// 标识码
							obj[2] =  databaseInfo.getName();// 网站名称
							obj[3] = (Integer) map.get("size");// 逾期栏目个数
						} else { // 系统
							String modeStr = (String) map.get("modeStr");
							String[] modeStrs = modeStr.split("/");
							obj = new Object[6];
							obj[0] = msgNum;
							obj[1] = databaseInfo.getSiteCode(); // 标识码
							obj[2] = databaseInfo.getName();// 网站名称
							obj[3] = modeStrs[0];// 检测栏目
							obj[4] = modeStrs[1];//栏目数量
							obj[5] = (Integer) map.get("size");// 逾期栏目个数
						}
						list.add(obj);
						
					} else if(typeId.equals("4")){
						Object[] obj = new Object[5];
						obj[0] = msgNum;
						obj[1] = map.get("siteCode"); // 标识码
						obj[2] = map.get("name"); // 网站名称
						obj[3] = map.get("layerTypeValue");//网站类别
						obj[4] = (Integer) map.get("size");// 除了基本信息的其他三个 的问题个数
						list.add(obj);
					}
				}
				ExportExcel.organConnHomeExcel(fileName, title, list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @描述:[查询] 内容保障表格数据
	 * @作者:luocheng@ucap.com.cn
	 * @时间:2017/1/9
	 * @return
	 */
	public void getSercurityMsg() {
		String modeVal = request.getParameter("modeVal"); // 检测方式
		String siteType = request.getParameter("siteType"); // 网站类别
		String typeId = request.getParameter("typeId"); // 内容保障的模块类型
		String servicePeriod = request.getParameter("servicePeriod"); // 监测周期
		Integer servicePeriodId = null;

		if (StringUtils.isNotEmpty(request.getParameter("servicePeriodId"))) {
			servicePeriodId = Integer.parseInt(request
					.getParameter("servicePeriodId")); // 监测周期id
		}
			Map<String, Object> resultMap = getSecurityMsgs(siteType, typeId,
					servicePeriod, servicePeriodId, modeVal);
			if (resultMap != null) {
				writerPrint(JSONObject.fromObject(resultMap).toString());
			} else {
				resultMap = new HashMap<String, Object>();
				resultMap.put("success", "false");
				writerPrint(JSONObject.fromObject(resultMap).toString());
			}
		}
	/**
	 * @描述:根据条件查询出内容保障问题数据
	 * @作者:luocheng@ucap.com.cn
	 * @时间:2017/1/9
	 * @param siteType
	 *            网站类别
	 * @param typeId
	 *            /内容保障的模块类型
	 * @param servicePeriod监测周期
	 * @param servicePeriodId
	 *            监测周期Id
	 * @param modeVal检测方式
	 *            [人工为1 系统为2]
	 * @return 返回查询出的结果Map
	 */	
	@SuppressWarnings("unused")
	public Map<String, Object> getSecurityMsgs(String siteType, String typeId,
			String servicePeriod, Integer servicePeriodId, String modeVal) {

		Map<String, Object> resultMap = new HashMap<String, Object>(); // 向页面传递数据的map
		siteCode = getCurrentSiteCode();
 
		String cacheDataList = CacheType.MONITORING_SECURITYGUARANTEE_DATABASEALLLIST
				.getName(); // --获得缓存名字
		String keyDataList = cacheDataList + siteCode + siteType + typeId + servicePeriodId + modeVal + DateUtils.getYesterdayStr(); // --缓存名
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> allDatabaseBasicList = (List<Map<String, Object>>) MonitoringCacheUtils
				.get(keyDataList); // 查询缓存中是否存在

		// 基本信息页面 判断该组织单位是否有合同,来展示人工或者系统检测页面
		// 获得当前组织单位的合同 判断是否存在合同
		/** 老合同信息 **/
		List<ContractInfo> crmlist = getContractInfoList(siteCode, DateUtils.formatStandardDate(new Date()));
		if (CollectionUtils.isEmpty(crmlist)) {
			// 没有查询到此siteCode的合同,直接返回页面，提示相关信息
			Map<String, Object> otherResultMap = new HashMap<String, Object>();
			otherResultMap.put("success", "other");
			return otherResultMap;
		} else {
		if (typeId.equals("0")) { // 当查询为基本信息时 需要区别是 人工监测还是系统监测
			List<ServicePeriodRequest> servicePeriodBasicList = getServicePeriodListBasic();
			if (StringUtils.isEmpty(modeVal)) {
				// 获取当前周期 是否为高级版 comboid = 4 即是高级版
				if (CollectionUtils.isNotEmpty(servicePeriodBasicList)) { // 查询是否存在高级版的服务周期对象
																			// 有就说明存在高级任务
																			// 默认显示人工监测数据
					// 有高级版任务 人工监测
					modeVal = "1";
				} else {
					modeVal = "2"; // 系统监测
				}
			}
			
			resultMap.put("modeVal", modeVal); // 检测类型

			/**新产品信息**/
			// Integer[] productTypeArr = { CrmProductsType.DETECTION.getCode()
			// };
			// List<CrmProductsResponse> crmlist = getCrmProductsList(siteCode,
			// productTypeArr,
			// DateUtils.formatStandardDate(new Date()), null, null);

				if (modeVal.equals("2")) { // 基本信息 --系统监测
					if (allDatabaseBasicList == null) {
						allDatabaseBasicList = new ArrayList<Map<String, Object>>();
						HashMap<String, Object> hashMap = new HashMap<String, Object>();
						if (StringUtils.isNotEmpty(siteType)) {
							hashMap.put("siteType",
									Integer.parseInt(siteType) == 0 ? null : Integer.parseInt(siteType));// 类型
																											// 1本级门户
																											// ,2本级部门
																											// ,
																											// 3
																											// 下属单位
						}
						hashMap.put("orgSiteCode", siteCode);
						hashMap.put("isexp", 1);// 类型 1 正常 2例外 3 关停
						// 查询此组织单位下的 填报单位站点
						List<DatabaseInfo> databaseInfoSystemList = databaseInfoServiceImpl
								.queryListJoinDatabaseTree(hashMap);
						if (CollectionUtils.isNotEmpty(databaseInfoSystemList) && databaseInfoSystemList.size() != 0) {
							for (DatabaseInfo databaseInfo : databaseInfoSystemList) {
								Map<String, Object> resultgMap = new HashMap<String, Object>();
								// 无高级版任务时候，默认展示系统检测页面
								ChannelPointRequest channelRequest = new ChannelPointRequest();
								channelRequest.setSiteCode(databaseInfo.getSiteCode());
								channelRequest.setStatusFlag("notNull"); // 查询
																			// 除【标记删除】以外的所有栏目
								// 该siteCode所有的栏目数量
								Integer allSercurityCount = channelPointServiceImpl.queryCount(channelRequest);
								channelRequest.setStatus(1);
								// 该站点 [监测中] 的栏目的数量
								Integer sercurityCount = channelPointServiceImpl.queryCount(channelRequest);
								String modeStr = sercurityCount + "/" + allSercurityCount;
								resultgMap.put("modeStr", modeStr); // 检测栏目

								SecurityHomeChannelRequest homeChannelRequest = new SecurityHomeChannelRequest(); // 逾期未更新天数
								homeChannelRequest.setSiteCode(databaseInfo.getSiteCode());
								homeChannelRequest.setAutoInput("auto");// 系统检测
																		// 机器输入
								homeChannelRequest.setType(2); // 查询逾期 [栏目]
								homeChannelRequest.setSelectType("4");
								homeChannelRequest.setFlag(false); // 栏目类型
								// 查询出所有栏目的逾期
								homeChannelRequest.setPageSize(Integer.MAX_VALUE);
								homeChannelRequest.setScanDate(DateUtils.getYesterdayStr()); // 查询扫描时间在昨天的
								List<SecurityHomeChannel> securityHomeChannelList = securityHomeChannelServiceImpl
										.queryListTb(homeChannelRequest);

								resultgMap.put("databaseInfo", databaseInfo);
								if (securityHomeChannelList != null && securityHomeChannelList.size() > 0) {
									resultgMap.put("size", securityHomeChannelList.size()); // 逾期未更新栏目个数
								} else {
									resultgMap.put("size", 0);
								}
								allDatabaseBasicList.add(resultgMap);
							}
						 }
					 }
					ComparatorHashMap comparatorHashMapTotal = new ComparatorHashMap();
					Collections.sort(allDatabaseBasicList, comparatorHashMapTotal); // 按问题个数排序
					MonitoringCacheUtils.put(keyDataList, allDatabaseBasicList); // 将数据存到缓存中
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
					resultMap.put("dateStr", sdf.format(DateUtils.getYesterdaytime()));
					resultMap.put("body", allDatabaseBasicList);
					resultMap.put("success", "true");
					return resultMap;
				}
			 }
		}

		try {
			// 获得默认查询的周期数据
			if (StringUtils.isEmpty(servicePeriod) || servicePeriodId == null) { // 传递过来的周期为空
																					// 则默认查询最近的一个周期
				List<ServicePeriodRequest> servicePeriodResultListBasic = getServicePeriodListBasic();
				if (typeId.equals("0")) {
					if (CollectionUtils
							.isNotEmpty(servicePeriodResultListBasic)
							&& servicePeriodResultListBasic.size() > 0) {
							servicePeriodId = servicePeriodResultListBasic.get(0)
								.getId(); // 基本信息 默认查询的周期id
					} else {
						resultMap.put("noServicePeriod", -1); // -1表示
																// 基本信息没有查询到服务周期
						resultMap.put("success", "true");
						return resultMap;
					}
				} else {
					List<ServicePeriodRequest> servicePeriodResultList = getServicePeriodList();
					if (CollectionUtils.isNotEmpty(servicePeriodResultList)
							&& servicePeriodResultList.size() > 0) {
						servicePeriodId = servicePeriodResultList.get(0)
								.getId(); // 默认查询的周期id
					} else {
						// 没有查询到周期 则直接返回
						resultMap.put("noServicePeriod", -2); // -2表示其他三种内容保障没有查询到周期
						resultMap.put("success", "true");
						return resultMap;
					}
				}
			}

			// 根据组织机构编码和组织机构的级别，获取对应的网站标识码集合
			String cacheSecurityData = CacheType.MONITORING_SECURITYGUARANTEE_DATABASEINFO
					.getName(); // 获得缓存名字
			String keyData = cacheSecurityData + siteCode + siteType + typeId
			 + servicePeriodId + modeVal + DateUtils.getYesterdayStr(); // 缓存名
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
			if (allDatabaseBasicList == null) {
				allDatabaseBasicList = new ArrayList<Map<String, Object>>();
				Integer sercurityCount = null; // 查询出的栏目或者问题数量
	
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("orgSiteCode", siteCode);
				map.put("servicePeriodId", servicePeriodId); // 周期id
				map.put("isLink", DatabaseTreeInfoType.ISLINK.getCode());
				if (StringUtils.isNotEmpty(siteType) && !siteType.equals("0")) {
					map.put("siteType", siteType);
				}
				List<SecurityGuaranteeResponse> treeList = relationsPeriodServiceImpl.contentSecurityTree(map);
				List<Map<String, Object>> allMap = new ArrayList<Map<String, Object>>();
				
				if (typeId.equals("0")) {
					for (DatabaseInfo databaseInfo : siteList) {
						Map<String, Object> resultgMap = new HashMap<String, Object>();
						// 根据服务周期和siteCode查询对应的栏目问题数量
						HashMap<String, Object> hashMap = new HashMap<String, Object>();
						hashMap.put("servicePeriodId", servicePeriodId);
						hashMap.put("siteCode", databaseInfo.getSiteCode());
						 // [基本信息 --人工监测]
						SecurityHomeChannelRequest homeChannelRequest = new SecurityHomeChannelRequest(); // 逾期未更新天数
						List<SecurityHomeChannel> securityHomeChannelList = null;
						// 有高级版任务时候，默认展示人工检测页面
						homeChannelRequest.setSiteCode(databaseInfo
								.getSiteCode());
						homeChannelRequest.setNoAutoInput("noAuto"); //人工检测 非机器输入
						homeChannelRequest.setType(2); // 查询逾期 [栏目]
						homeChannelRequest.setSelectType("4");
						homeChannelRequest.setFlag(false); // 栏目类型 查询出所有栏目的逾期
						homeChannelRequest.setPageSize(Integer.MAX_VALUE);
						/*
						 * homeChannelRequest.setBeginScanDate(startDate); //
						 * 查询扫描时间在周期内的
						 * homeChannelRequest.setEndScanDate(endDate);
						 */
						homeChannelRequest.setServicePeriodId(servicePeriodId); // 查询属于此周期id的逾期未更新[人工查询]
						securityHomeChannelList = securityHomeChannelServiceImpl
								.queryListTb(homeChannelRequest);
						resultgMap.put("databaseInfo", databaseInfo);
						// resultgMap.put("total", sercurityCount); //监测栏目个数
						if (securityHomeChannelList != null
								&& securityHomeChannelList.size() > 0) {
							resultgMap.put("size",
									securityHomeChannelList.size()); // 逾期未更新栏目个数
						} else {
							resultgMap.put("size", 0);
						}
						allDatabaseBasicList.add(resultgMap);
					}
				}else if (typeId.equals("1")) { // [空白栏目]
					List<SecurityGuaranteeResponse> blankList = securityBlankDetailServiceImpl.getBlankNumber(map);
					allMap = ListMap(blankList, treeList);
					allDatabaseBasicList.addAll(allMap);
				} else if (typeId.equals("2")) { // [互动回应差]
					List<SecurityGuaranteeResponse> responseList = securityResponseServiceImpl.getResponseNumber(map);
					allMap = ListMap(responseList, treeList);
					allDatabaseBasicList.addAll(allMap);
				} else if (typeId.equals("3")) { // [服务不实用]
					List<SecurityGuaranteeResponse> serviceList = securityServcieServiceImpl.getServiceNumber(map);
					allMap = ListMap(serviceList, treeList);
					allDatabaseBasicList.addAll(allMap);
				}
			}

			ComparatorHashMap comparatorHashMapTotal = new ComparatorHashMap();
			Collections.sort(allDatabaseBasicList, comparatorHashMapTotal); // 按问题个数排序
			MonitoringCacheUtils.put(keyDataList, allDatabaseBasicList);// 将数据存到缓存中

			resultMap.put("body", allDatabaseBasicList);
			resultMap.put("success", "true");
			return resultMap;
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", "false");
			return resultMap;
		}
	}

	/**
	 * 
	 * @描述:栏目table数据
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2017年3月7日下午5:26:42
	 * @param list
	 * @param treeList
	 * @return
	 */
	public List<Map<String, Object>> ListMap(List<SecurityGuaranteeResponse> list, List<SecurityGuaranteeResponse> treeList){
		List<Map<String, Object>> allMap = new ArrayList<Map<String, Object>>();
		// 栏目有数据
		if (CollectionUtils.isNotEmpty(list) && CollectionUtils.isNotEmpty(treeList)) {
			for (SecurityGuaranteeResponse secTree : treeList) {
				int flag = 0;
				Map<String, Object> resMap = new HashMap<String, Object>();
				for (SecurityGuaranteeResponse sec : list) {
					if (secTree.getSiteCode().equals(sec.getSiteCode())) {
						flag = 1;
						resMap.put("siteCode", secTree.getSiteCode());
						resMap.put("name", secTree.getName());
						if (StringUtils.isNotEmpty(secTree.getJumpPageUrl())) {
							resMap.put("url", secTree.getJumpPageUrl());// 跳转url地址
						} else {
							resMap.put("url", secTree.getHomePageUrl());// 首页url地址
						}
						resMap.put("siteCode", secTree.getSiteCode());
						resMap.put("layerType", secTree.getIsorganizational()); // 0非门户，1门户

						resMap.put("size", sec.getColumnNum()); // 栏目个数
						allMap.add(resMap);
						break;
					}
				}
				if (flag == 0) {
					resMap.put("siteCode", secTree.getSiteCode());
					resMap.put("name", secTree.getName());
					if (StringUtils.isNotEmpty(secTree.getJumpPageUrl())) {
						resMap.put("url", secTree.getJumpPageUrl());// 跳转url地址
					} else {
						resMap.put("url", secTree.getHomePageUrl());// 首页url地址
					}
					resMap.put("siteCode", secTree.getSiteCode());
					resMap.put("layerType", secTree.getIsorganizational()); // 0非门户，1门户

					resMap.put("size", secTree.getColumnNum()); // 栏目个数

					allMap.add(resMap);
				}

			}
		}
		// 栏目无数据
		if (CollectionUtils.isEmpty(list)) {
			if (CollectionUtils.isNotEmpty(treeList)) {
				for (SecurityGuaranteeResponse sec : treeList) {
					Map<String, Object> resMap = new HashMap<String, Object>();
					resMap.put("siteCode", sec.getSiteCode());
					resMap.put("name", sec.getName());
					if (StringUtils.isNotEmpty(sec.getJumpPageUrl())) {
						resMap.put("url", sec.getJumpPageUrl());// 跳转url地址
					} else {
						resMap.put("url", sec.getHomePageUrl());// 首页url地址
					}
					resMap.put("siteCode", sec.getSiteCode());
					resMap.put("layerType", sec.getIsorganizational()); // 0非门户，1门户
					resMap.put("size", sec.getColumnNum()); // 栏目个数

					allMap.add(resMap);
				}
			}
		}
		return allMap;
	}
	
	/**
	 * @Description:组织单位首页 内容保障色块 栏目不更新数据查询
	 * @author luocheng--- 2017-03-24
	 */
	public void getSecurityAllSum() {
		Map<String, Object> resultMap = new HashMap<String, Object>(); // 向页面传递数据的map
		String yesStr = DateUtils.getYesterdayStr(); // 获取昨天的日期
		List<ServicePeriodRequest> otherList = getServicePeriodList(); // 基本信息的周期
		List<ServicePeriodRequest> basicList = getServicePeriodListBasic(); // 其他内容保障的周期
		int seChannelId = 0;
		int sBasic = 0;
		int sBlank = 0;
		int sResponse = 0;
		int sService = 0;
		
		Map<String, Object> systemBasicMap = getSecurityMsgs(null,"0",null,null,"2"); //系统检测的基本信息
		String ststus = (String) systemBasicMap.get("success");
		if("other".equals(ststus)){ //合同到期 返回未监测
			resultMap.put("allOtherPeriod", "notIn");
		}
		
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> systemBasicDatabaseBasicList = (List<Map<String, Object>>) systemBasicMap.get("body");
		if(systemBasicDatabaseBasicList != null && systemBasicDatabaseBasicList.size() > 0){
			for (Map<String, Object> map : systemBasicDatabaseBasicList) {
				sBasic += (Integer) map.get("size");// 统计所有站点的
			}

		}
		
		if (CollectionUtils.isNotEmpty(basicList) && basicList.size() != 0) {
			ServicePeriodRequest spRequest = otherList.get(0); // 取得最近的一个周期
			String strat = spRequest.getStartDate();
			String end = spRequest.getEndDate();
			
			if (yesStr.compareTo(strat) >= 0 && end.compareTo(yesStr) >= 0) { //昨天在周期内
				Map<String, Object> peopleBasicMap = getSecurityMsgs(null,"0",null,spRequest.getId(),"1"); //互动回应
				@SuppressWarnings("unchecked")
				List<Map<String, Object>> perpleBasicDatabaseBasicList = (List<Map<String, Object>>) peopleBasicMap.get("body");
				for (Map<String, Object> map : perpleBasicDatabaseBasicList) {
					sBasic += (Integer) map.get("size");// 统计所有站点的
				}
			}
		}
		if (CollectionUtils.isNotEmpty(otherList) && otherList.size() != 0) { 
			ServicePeriodRequest spRequest = otherList.get(0); // 取得最近的一个周期
			String strat = spRequest.getStartDate();
			String end = spRequest.getEndDate();
			if (yesStr.compareTo(strat) >= 0 && end.compareTo(yesStr) >= 0) { //昨天在周期内
				Map<String, Object> blankMap = getSecurityMsgs(null,"1",null,spRequest.getId(),null); //空白栏目
				@SuppressWarnings("unchecked")
				List<Map<String, Object>> blankDatabaseBasicList = (List<Map<String, Object>>) blankMap.get("body");
				if(blankDatabaseBasicList == null || blankDatabaseBasicList.size() == 0){
					sBlank += 0;
				}else{
					for (Map<String, Object> map : blankDatabaseBasicList) {
						sBlank += (Integer) map.get("size");// 统计所有站点的
																// 全站不可链接的总数
					}
				}
				
				Map<String, Object> responseMap = getSecurityMsgs(null,"2",null,spRequest.getId(),null); //互动回应
				@SuppressWarnings("unchecked")
				List<Map<String, Object>> responseDatabaseBasicList = (List<Map<String, Object>>) responseMap.get("body");
				if(responseDatabaseBasicList == null || responseDatabaseBasicList.size() == 0){
					sResponse += 0 ;
				}else{
					for (Map<String, Object> map : responseDatabaseBasicList) {
						sResponse += (Integer) map.get("size");// 统计所有站点的
					}
				}
				
				
				Map<String, Object> serviceMap = getSecurityMsgs(null,"3",null,spRequest.getId(),null); //服务不实用栏目
				@SuppressWarnings("unchecked")
				List<Map<String, Object>> serviceDatabaseBasicList = (List<Map<String, Object>>) serviceMap.get("body");
				if(serviceDatabaseBasicList == null || serviceDatabaseBasicList.size() == 0){
					sResponse += 0 ;
				}else{
					for (Map<String, Object> map : serviceDatabaseBasicList) {
						sService += (Integer) map.get("size");// 统计所有站点的
					}
				}
			}else{ //昨天没在周期内
				resultMap.put("otherPeriod", "notIn");
			}
		}
		resultMap.put("sBasic", sBasic);
		resultMap.put("sBlank", sBlank);
		resultMap.put("sResponse", sResponse);
		resultMap.put("sService", sService);
		seChannelId = sBasic + sBlank + sResponse + sService;
		resultMap.put("seChannelId", seChannelId);
		resultMap.put("success", "true");
		
		writerPrint(JSONObject.fromObject(resultMap).toString());
	}
	
	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

}
