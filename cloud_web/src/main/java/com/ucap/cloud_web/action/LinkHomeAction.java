package com.ucap.cloud_web.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;

import com.publics.util.page.QueryOrder;
import com.publics.util.page.QueryOrderType;
import com.publics.util.utils.DateUtils;
import com.publics.util.utils.StringUtils;
import com.ucap.cloud_web.UrlAdapterVar;
import com.ucap.cloud_web.bizService.DatabaseBizService;
import com.ucap.cloud_web.common.DicUtils;
import com.ucap.cloud_web.constant.CacheType;
import com.ucap.cloud_web.constant.DatabaseInfoType;
import com.ucap.cloud_web.constant.DatabaseLinkType;
import com.ucap.cloud_web.constant.DatabaseTreeInfoType;
import com.ucap.cloud_web.constant.QuestionType;
import com.ucap.cloud_web.constant.ResourceType;
import com.ucap.cloud_web.constant.ScopeType;
import com.ucap.cloud_web.dto.DetectionOrgCountRequest;
import com.ucap.cloud_web.dto.LinkAllInfoRequest;
import com.ucap.cloud_web.dto.LinkHomeAvailableRequest;
import com.ucap.cloud_web.dto.LinkHomeTrendRequest;
import com.ucap.cloud_web.entity.DetectionOrgCount;
import com.ucap.cloud_web.entity.DicConfig;
import com.ucap.cloud_web.entity.DicItem;
import com.ucap.cloud_web.entity.LinkAllInfo;
import com.ucap.cloud_web.entity.LinkHomeAvailable;
import com.ucap.cloud_web.entity.LinkHomeTrend;
import com.ucap.cloud_web.entity.ServicePeriod;
import com.ucap.cloud_web.service.IConfigLinkExceptService;
import com.ucap.cloud_web.service.IDetectionOrgCountService;
import com.ucap.cloud_web.service.IDicConfigService;
import com.ucap.cloud_web.service.ILinkAllInfoService;
import com.ucap.cloud_web.service.ILinkHomeAvailableService;
import com.ucap.cloud_web.service.ILinkHomeTrendService;
import com.ucap.cloud_web.util.ExportExcel;
import com.ucap.cloud_web.util.MonitoringCacheUtils;

/**
 * <p>Description: </p>首页链接可用性处理逻辑--填报单位
 * <p>@Package：com.ucap.cloud_web.action </p>
 * <p>Title: LinkHomeAction </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：cuichx </p>
 * <p>@date：2015-11-6下午3:42:59 </p>
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class LinkHomeAction extends BaseAction {
	/**
	 * log日志加载
	 */
	private static Log logger = LogFactory.getLog(LinkAllAction.class);

	@Autowired
	private ILinkHomeAvailableService linkHomeAvailableServiceImpl;
	@Autowired
	private ILinkHomeTrendService linkHomeTrendServiceImpl;
	@Autowired
	private ILinkAllInfoService linkAllInfoServiceImpl;
	@Autowired
	private UrlAdapterVar urlAdapterVar;
	@Autowired
	private IDetectionOrgCountService detectionOrgCountServiceImpl;
	@Autowired
	private IConfigLinkExceptService configLinkExceptServiceImpl;
	@Autowired
	private DicUtils dicUtils;
	@Autowired
	private IDicConfigService dicConfigServiceImpl;
	@Autowired
	private DatabaseBizService databaseBizServiceImpl;
	List<LinkHomeAvailable> linkHomeAvailableList = null;
	List<LinkHomeAvailable> longNoAvailableList = null;
	String siteBeginServiceDate = "";//页面时间控件的起始时间
	//控制长期不可用链接的excel标签的显示和隐藏
	String controlExcel = "";

	/************************************* 日常监测-首页链接可用性 开始********************************************/

	/**
	 * 
	 * @描述:跳转首页链接可用性页面--组织单位
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2016年12月20日下午2:30:16
	 * @return
	 */
	public String linkHomeIndexOrg() {
		List<DicItem> dicList = dicUtils.getDictList("siteType");
		request.setAttribute("dicList", dicList); // 网站类别
		request.setAttribute("yesterday", DateUtils.getYesterdayStr()); // 昨天
		return "success";
	}

	/**
	 * 
	 * @描述:转换时间格式为20160115
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2017年2月28日上午10:12:16
	 * @param da
	 * @return
	 */
	public Integer conversion(String da) {
		Date dt = null;
		dt = DateUtils.parseStandardDate(da);
		String st = DateUtils.formatShortDate(dt);
		Integer inte = Integer.valueOf(st);
		return inte;
	}

	/**
	 * 
	 * @描述:首页链接可用性table
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2016年12月20日下午3:16:52
	 */
	@SuppressWarnings("unchecked")
	public void linkHomeIndexOrgTable() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
		String siteType = request.getParameter("siteType"); // 网站类别
		String startDate = request.getParameter("startDate"); // 开始时间
		String endDate = request.getParameter("endDate"); // 结束时间

		try {
			// 获取当前组织机构编码
			String siteCode = getCurrentSiteCode();

			float linkNum = 0;
			float websiteSum = 0;
			String percentage = "";
			Integer homeUrlNum = 0; // 首页url总数
			float linkUrlNum = 0; // 扫描url总数
			float websiteUrlSum = 0; // 不可链接url总数
			String percentageThan = ""; // 总占比
			Integer bigStartDate = conversion(startDate);
			Integer bigEndDate = conversion(endDate);
			HashMap<String, Object> hashMap = new HashMap<String, Object>();
			hashMap.put("orgSiteCode", siteCode);
			hashMap.put("startDate", startDate);
			hashMap.put("endDate", endDate);
			hashMap.put("bigStartDate", bigStartDate);
			hashMap.put("bigEndDate", bigEndDate);
			hashMap.put("isLink", DatabaseTreeInfoType.ISLINK.getCode());
			hashMap.put("isExp", DatabaseInfoType.NORMAL.getCode());
			if (StringUtils.isNotEmpty(siteType) && !siteType.equals("0")) {
				hashMap.put("siteType", siteType);
			}

			String cacheLinkHomeTrend = CacheType.MONITORING_LINKHOMETREND.getName();
			String conkey = cacheLinkHomeTrend + siteCode + startDate + endDate + siteType; // 缓存名
			List<LinkHomeTrendRequest> queryList = (List<LinkHomeTrendRequest>) MonitoringCacheUtils.get(conkey); // 查询缓存中是否存在
			if (queryList == null) {
				queryList = linkHomeTrendServiceImpl.getHomeOrgList(hashMap);
				MonitoringCacheUtils.put(conkey, queryList); // 将数据存到缓存中
			}

			if (queryList != null && queryList.size() > 0) {
				for (int i = 0; i < queryList.size(); i++) {
					LinkHomeTrendRequest linkHome = queryList.get(i);
					Map<String, Object> item = new HashMap<String, Object>();
					item.put("siteCode", linkHome.getSiteCode());
					item.put("siteName", linkHome.getSiteName());
					if (StringUtils.isNotEmpty(linkHome.getJumpPageUrl())) {
						item.put("url", linkHome.getJumpPageUrl());// 跳转url地址
					} else {
						item.put("url", linkHome.getHomePageUrl());// 首页url地址
					}
					item.put("indexlinknum", linkHome.getIndexlinknum()); // 链接总数
					item.put("websiteSum", linkHome.getWebsiteSum()); // 不连通链接数
					linkNum = linkHome.getIndexlinknum();
					websiteSum = linkHome.getWebsiteSum();
					if (websiteSum == 0) { // 被除数为0的情况下
						percentage = "0";
					} else if (linkNum == 0) { // 除数为0的情况下
						percentage = "0";
					} else {
						percentage = totalPercentage(websiteSum, linkNum);
						if (percentage.equals("0")) { // 基数太小得到的数据为0.00默认改为0.01
							percentage = "0.01";
						}
					}
					item.put("percentage", percentage);
					items.add(item);

					homeUrlNum++;
					linkUrlNum += linkHome.getIndexlinknum();
					websiteUrlSum += linkHome.getWebsiteSum();
				}
				if (websiteUrlSum == 0) {
					percentageThan = "0";
				} else if (linkUrlNum == 0) {
					percentageThan = "100";
				} else {
					percentageThan = totalPercentage(websiteUrlSum, linkUrlNum);
					if (percentageThan.equals("0")) { // 基数太小得到的数据为0.00默认改为0.01
						percentageThan = "0.01";
					}
				}
			}
			resultMap.put("success", "true");
			resultMap.put("body", items);
			resultMap.put("homeUrlNum", homeUrlNum);
			resultMap.put("linkUrlNum", linkUrlNum);
			resultMap.put("websiteUrlSum", websiteUrlSum);
			resultMap.put("percentageThan", percentageThan + "%");
			resultMap.put("size", queryList.size()); // 总条数
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", "false");
			resultMap.put("errorMsg", "查询日常监测-首页链接可用性数据异常！");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		}
	}

	/**
	 * 
	 * @描述:首页链接可用性excel导出
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2016年12月21日下午5:07:59
	 */
	@SuppressWarnings("unchecked")
	public void linkHomeIndexOrgTableExcel() {
		ArrayList<Object[]> list = new ArrayList<Object[]>();
		String siteType = request.getParameter("siteType"); // 网站类别
		String startDate = request.getParameter("startDate"); // 开始时间
		String endDate = request.getParameter("endDate"); // 结束时间
		try {
			// 获取当前组织机构编码
			String siteCode = getCurrentSiteCode();

			Object[] obj1 = new Object[] { "序号", "网站标识码", "网站名称", "首页url", "扫描URL总数", "首页不可用链接个数", "占比" };
			list.add(obj1);
			String fileName = "日常监测-首页链接可用性(" + DateUtils.formatStandardDate(new Date()) + ").xls";
			String title = "首页链接可用性";
			Integer dataNumber = 0;
			float linkNum = 0;
			float websiteSum = 0;
			String percentage = "";
			Integer bigStartDate = conversion(startDate);
			Integer bigEndDate = conversion(endDate);
			HashMap<String, Object> hashMap = new HashMap<String, Object>();
			hashMap.put("orgSiteCode", siteCode);
			hashMap.put("startDate", startDate);
			hashMap.put("endDate", endDate);
			hashMap.put("bigStartDate", bigStartDate);
			hashMap.put("bigEndDate", bigEndDate);
			hashMap.put("isLink", DatabaseTreeInfoType.ISLINK.getCode());
			hashMap.put("isExp", DatabaseInfoType.NORMAL.getCode());
			if (StringUtils.isNotEmpty(siteType) && !siteType.equals("0")) {
				hashMap.put("siteType", siteType);
			}

			String cacheLinkHomeTrendExcel = CacheType.MONITORING_LINKHOMETRENDEXCEL.getName();
			String conkey = cacheLinkHomeTrendExcel + siteCode + startDate + endDate + siteType; // 缓存名
			List<LinkHomeTrendRequest> queryList = (List<LinkHomeTrendRequest>) MonitoringCacheUtils.get(conkey); // 查询缓存中是否存在
			if (queryList == null) {
				queryList = linkHomeTrendServiceImpl.getHomeOrgList(hashMap);
				MonitoringCacheUtils.put(conkey, queryList); // 将数据存到缓存中
			}

			if (queryList != null && queryList.size() > 0) {
				for (int i = 0; i < queryList.size(); i++) {
					LinkHomeTrendRequest linkHome = queryList.get(i);
					Object[] obj = new Object[7];
					dataNumber = dataNumber + 1;
					obj[0] = dataNumber;
					obj[1] = linkHome.getSiteCode();
					obj[2] = linkHome.getSiteName();

					// url先取跳转url,再取首页url
					if (StringUtils.isNotEmpty(linkHome.getJumpPageUrl())) {
						obj[3] = linkHome.getJumpPageUrl();
					} else {
						if (StringUtils.isNotEmpty(linkHome.getHomePageUrl())) {
							obj[3] = linkHome.getHomePageUrl();
						} else {
							obj[3] = "";
						}
					}
					obj[4] = linkHome.getIndexlinknum(); // 链接总数
					obj[5] = linkHome.getWebsiteSum(); // 不连通链接数
					linkNum = linkHome.getIndexlinknum();
					websiteSum = linkHome.getWebsiteSum();
					if (websiteSum == 0) { // 被除数为0的情况下
						percentage = "0";
					} else if (linkNum == 0) { // 除数为0的情况下
						percentage = "100";
					} else {
						percentage = totalPercentage(websiteSum, linkNum);
						if (percentage.equals("0")) { // 基数太小得到的数据为0.00默认改为0.01
							percentage = "0.01";
						}
					}
					obj[6] = percentage + "%";
					list.add(obj);
				}
			}
			ExportExcel.securityQuestionExcel(fileName, title, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/************************************* 日常监测-首页链接可用性 结束********************************************/

	/**
	 * @Description: 首页链接可用性检测结果
	 * @author cuichx --- 2015-11-6下午3:53:43
	 * @return
	 */
	public String linkHomeIndex() {
		try {
			//siteCode处理由组织单位跳转到该页面时，session的修改
			String siteCode = request.getParameter("siteCode");

			if (StringUtils.isNotEmpty(siteCode)) {
				setCurrentShiroUser(siteCode);
			} else {
				//从session中获取10位填报单位网站标识码
				siteCode = getCurrentUserInfo().getChildSiteCode();
				if (StringUtils.isEmpty(siteCode)) {
					siteCode = getCurrentUserInfo().getSiteCode();
				}
			}

			LinkHomeAvailableRequest linkHomeAvailableRequest = new LinkHomeAvailableRequest();
			linkHomeAvailableRequest.setSiteCode(siteCode);
			linkHomeAvailableRequest.setScanDate(DateUtils.getYesterdayStr());
			linkHomeAvailableList = new ArrayList<LinkHomeAvailable>();
			//根据页面传递的时间，查询
			linkHomeAvailableList = linkHomeAvailableServiceImpl.queryList(linkHomeAvailableRequest);
			//封装长期不可用链接数据
			longNoAvailableList = new ArrayList<LinkHomeAvailable>();
			if (linkHomeAvailableList != null && linkHomeAvailableList.size() > 0) {
				controlExcel = "1";
				for (LinkHomeAvailable linkHomeAvailable : linkHomeAvailableList) {
					if (linkHomeAvailable.getLinkNotavailableDay() >= 30) {
						longNoAvailableList.add(linkHomeAvailable);
					}
				}
			} else {
				controlExcel = "0";
			}
			logger.info("longNoAvailableList=" + longNoAvailableList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	/** @Description: 首页链接可用性监测结果
	 * @author zhurk --- 2015-11-19下午2:40:58                
	*/
	public void linkHomeAvaliable() {
		//从session中获取10位填报单位网站标识码
		String siteCode = getCurrentUserInfo().getChildSiteCode();
		if (StringUtils.isEmpty(siteCode)) {
			siteCode = getCurrentUserInfo().getSiteCode();
		}
		String scanDate = request.getParameter("date");
		String homeUnavailableSelectId = request.getParameter("homeUnavailableSelectId");//确认死链
		
		LinkHomeAvailableRequest linkHomeAvailableRequest = new LinkHomeAvailableRequest();
		HashMap<String, Object> map_list = new HashMap<String, Object>();
		linkHomeAvailableRequest.setSiteCode(siteCode);
		linkHomeAvailableRequest.setPageSize(Integer.MAX_VALUE);
		if(StringUtils.isNotEmpty(homeUnavailableSelectId) && !homeUnavailableSelectId.equals("0")){
			if(homeUnavailableSelectId.equals("1")){
				DicConfig dicConfig = dicConfigServiceImpl.get(20);//取出确认死链配置
				String[] strArray = dicConfig.getValue().split(",");
				linkHomeAvailableRequest.setQuestionCodeArr(strArray);
			}else if(homeUnavailableSelectId.equals("2")){
				DicConfig dicConfig = dicConfigServiceImpl.get(21);//疑似死链配置
				String[] strArray = dicConfig.getValue().split(",");
				linkHomeAvailableRequest.setQuestionCodeArr(strArray);
			}
		}
		String yesterdayStr = DateUtils.getYesterdayStr();
		if (StringUtils.isNotEmpty(scanDate)) {
			linkHomeAvailableRequest.setScanDate(scanDate);
			map_list.put("yesterdayStr", DateUtils.formatDate(DateUtils.parseStandardDate(scanDate)));
			map_list.put("date", scanDate);
		} else {
			linkHomeAvailableRequest.setScanDate(yesterdayStr);
			map_list.put("yesterdayStr", DateUtils.formatDate(DateUtils.parseStandardDate(yesterdayStr)));
			// map_list.put("date", yesterdayStr);
		}
		try {
			List<LinkHomeAvailable> linkHomeAvailableList = linkHomeAvailableServiceImpl.queryList(linkHomeAvailableRequest);
//			
			// 如果configLinkExcept表中的url存在 则从 linkHomeAvailableList 集合中删除
//			ConfigLinkExceptRequest cRequest = new ConfigLinkExceptRequest();
//			cRequest.setSiteCode(linkHomeAvailableRequest.getSiteCode());
//			cRequest.setStatus(0);
//			cRequest.setPageSize(Integer.MAX_VALUE);
//			List<ConfigLinkExcept> configList = configLinkExceptServiceImpl.queryList(cRequest);
//			
//			List<LinkHomeAvailable> listUrl = new ArrayList<LinkHomeAvailable>();
//			for (LinkHomeAvailable linkHomeAvailable : linkHomeAvailableList) {
//				for (ConfigLinkExcept configLinkExcept : configList) {
//					if(linkHomeAvailable.getUrl().startsWith(configLinkExcept.getUrl())){
//						listUrl.add(linkHomeAvailable);
//					}
//				}
//			}
//			linkHomeAvailableList.removeAll(listUrl);
			

			List<Object> linkHome_List = new ArrayList<Object>();
			for (LinkHomeAvailable linkHomeAvailable : linkHomeAvailableList) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				String NoAvailableurl = linkHomeAvailable.getUrl();
				map.put("NoAvailableurl", NoAvailableurl);

				String scanTime = linkHomeAvailable.getScanTime();
				if (StringUtils.isNotEmpty(scanTime)) {
					map.put("scanTime", scanTime);
				} else {
					map.put("scanTime", "");
				}

				String linkTitle = linkHomeAvailable.getLinkTitle();
				if (StringUtils.isNotEmpty(linkTitle)) {
					map.put("linkTitle", linkTitle);
				} else {
					map.put("linkTitle", "无");
				}

				//连续不可用链接天数
				int linkNotavailableDay = linkHomeAvailable.getLinkNotavailableDay();
				map.put("linkNotavailableDay", linkNotavailableDay);

				int urlType = linkHomeAvailable.getUrlType();
				String resourceDes =ResourceType.getNameByCode(databaseBizServiceImpl.getResourceType(urlType));
				map.put("resourceDes", resourceDes);
				String scope =ScopeType.getNameByCode(linkHomeAvailable.getScope());
				map.put("scope", scope);

				String questionCode = linkHomeAvailable.getQuestionCode();
				if (StringUtils.isNotEmpty(questionCode)) {
					String questionDescribe = QuestionType.getNameByCode(questionCode);
					if (StringUtils.isNotEmpty(questionDescribe)) {
						map.put("questionDescribe", questionCode + "　　" + questionDescribe);
					} else {
						map.put("questionDescribe", questionCode);
					}
				} else {
					map.put("questionDescribe", "");
				}

				String imgUrl = linkHomeAvailable.getImgUrl();

				if (StringUtils.isNotEmpty(imgUrl)) {
					imgUrl = urlAdapterVar.getXenuLinkUrl() + imgUrl;
					//map.put("imgUrl",imgUrl);
				} else {
					imgUrl = "";
					//imgUrl=urlAdapterVar.getXenuLinkUrl() + "";
					//map.put("imgUrl",imgUrl);
				}
				map.put("imgUrl", imgUrl);
				//父页面url
				String parentUrl = linkHomeAvailable.getParentUrl();

				if (StringUtils.isEmpty(parentUrl)) {
					//map.put("imgUrl",imgUrl);
					parentUrl = "";
				} 
				map.put("parentUrl", parentUrl);
				
				
				
				
				linkHome_List.add(map);
			}
			map_list.put("linkHome_List", linkHome_List);
			writerPrint(JSONObject.fromObject(map_list).toString());
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("首页链接可用性监测结果");
		}
	}

	/**
	 * @Description:填报单位 首页链接可用性趋势分析---折线图
	 * @author cuichx --- 2015-11-6下午4:53:33     
	 * @param siteCode
	 * @param scanDate
	 * @return
	 */
	public void linkHomeTrendLine() {
		ArrayList<Object> list = new ArrayList<Object>();
		try {

			//从session中获取10位填报单位网站标识码
			String siteCode = getCurrentUserInfo().getChildSiteCode();
			if (StringUtils.isEmpty(siteCode)) {
				siteCode = getCurrentUserInfo().getSiteCode();
			}
			//获取前90天的开始时间
			String nextDay = DateUtils.getNextDay(new Date(), -90);
			//获取昨天的日期
			String endDate = DateUtils.getNextDay(new Date(), -1);

			HashMap<String, Object> hashMap = new HashMap<String, Object>();
			hashMap.put("siteCode", siteCode);
			hashMap.put("begeinScanDate", nextDay);
			hashMap.put("endScanDate", endDate);
			List<LinkHomeTrend> queryList = linkHomeTrendServiceImpl.getHomeLine(hashMap);
			for (LinkHomeTrend linkHomeTrend : queryList) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				int errorNum = linkHomeTrend.getWebsiteSum();
				String scanDate = DateUtils.formatStandardDate(linkHomeTrend.getScanDate());
				map.put("errorNum", errorNum);
				map.put("scanDate", scanDate);
				list.add(map);
			}

			writerPrint(JSONArray.fromObject(list).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Description: 组织单位--首页链接可用性趋势分析---折线图
	 * @author sunjiang --- 2015年11月28日下午5:58:11
	 */
	public void linkHomeTrendLineOrg() {
		ArrayList<Object> list = new ArrayList<Object>();
		try {
			String currentSiteCode = getCurrentSiteCode();

			String endDate = DateUtils.getNextDay(queryHomePageDate(), "0");
			String nextDay = DateUtils.getNextDay(queryHomePageDate(), "-6");

			HashMap<String, Object> hashMap = new HashMap<String, Object>();
			hashMap.put("begeinScanDate", nextDay);
			hashMap.put("endScanDate", endDate);
			DetectionOrgCountRequest detectionOrgCountRequest = new DetectionOrgCountRequest();
			detectionOrgCountRequest.setSiteCode(currentSiteCode);
			detectionOrgCountRequest.setStartDate(nextDay);
			detectionOrgCountRequest.setEndDate(endDate);
			detectionOrgCountRequest.setType(DatabaseLinkType.ALL.getCode().toString());
			List<QueryOrder> queryOrderOrgList = new ArrayList<QueryOrder>();
			QueryOrder queryOrder = new QueryOrder("scan_date", QueryOrderType.ASC);
			queryOrderOrgList.add(queryOrder);
			detectionOrgCountRequest.setQueryOrderList(queryOrderOrgList);
			List<DetectionOrgCount> detectionOrgCountList = detectionOrgCountServiceImpl.queryList(detectionOrgCountRequest);
			for (DetectionOrgCount detectionOrgCount : detectionOrgCountList) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				int errorNum = detectionOrgCount.getLinkHome();
				String scanDate = DateUtils.formatStandardDate(detectionOrgCount.getScanDate());
				map.put("errorNum", errorNum);
				map.put("scanDate", scanDate);
				list.add(map);
			}

			writerPrint(JSONArray.fromObject(list).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Description:填报单位  全站不可用链接  柱状图
	 * @author sunjiang --- 2015年11月12日下午5:55:11
	 */
	public void linkAllLine() {
		ArrayList<Object> list = new ArrayList<Object>();
		try {
			//从session中获取10位填报单位网站标识码
			String siteCode = getCurrentUserInfo().getChildSiteCode();
			if (StringUtils.isEmpty(siteCode)) {
				siteCode = getCurrentUserInfo().getSiteCode();
			}
			logger.info("linkAllUnuse siteCode:" + siteCode);
			/**老合同信息**/
			List<ServicePeriod> serviceList = getServicePeriodList(0);
			/**新产品信息**/
			// Integer[] productTypeArr = { CrmProductsType.DETECTION.getCode()
			// };
			// List<ServicePeriod> serviceList = getAllServicePeriodList(0,
			// productTypeArr);
			if (serviceList != null) {
				LinkAllInfoRequest linkAllInfoRequest = new LinkAllInfoRequest();
				linkAllInfoRequest.setSiteCode(siteCode);
				linkAllInfoRequest.setServiceList(serviceList);

				//按创建时间正序排列
				List<QueryOrder> querySiteList = new ArrayList<QueryOrder>();
				QueryOrder siteQueryOrder = new QueryOrder("create_time", QueryOrderType.ASC);
				querySiteList.add(siteQueryOrder);
				linkAllInfoRequest.setQueryOrderList(querySiteList);

				List<LinkAllInfo> queryList = linkAllInfoServiceImpl.queryList(linkAllInfoRequest);
				if (queryList != null && queryList.size() > 0) {
					for (int i = 0; i < queryList.size(); i++) {
						LinkAllInfo linkAllInfo = queryList.get(i);
						HashMap<String, Object> map = new HashMap<String, Object>();
						map.put("websiteSum", linkAllInfo.getWebsiteSum());
						list.add(map);
					}
				}
			}
			writerPrint(JSONArray.fromObject(list).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Description: 组织单位  全站不可用链接  柱状图
	 * @author sunjiang --- 2015年11月28日下午5:55:29
	 */
	public void linkAllLineOrg() {
		ArrayList<Object> list = new ArrayList<Object>();
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		try {
			String currentSiteCode = getCurrentSiteCode();

			hashMap.put("orgSiteCode", currentSiteCode);
			hashMap.put("isLink", DatabaseTreeInfoType.ISLINK.getCode());
			hashMap.put("isExp", DatabaseInfoType.NORMAL.getCode());
			List<LinkAllInfo> queryList = linkAllInfoServiceImpl.getAlllineOrg(hashMap);
			if (!CollectionUtils.isEmpty(queryList)) {
				for (int i = 0; i < queryList.size(); i++) {
					LinkAllInfo linkAllInfo = queryList.get(i);
					HashMap<String, Object> map = new HashMap<String, Object>();
					int servicePeriodId = i + 1;
					int websiteSum = linkAllInfo.getWebsiteSum();
					map.put("periodNum", "第" + servicePeriodId + "期");
					map.put("websiteSum", websiteSum);
					list.add(map);
				}
			}
			writerPrint(JSONArray.fromObject(list).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Description: 首页链接可用性监测结果--导出Excel报表
	 * @author zhurk --- 2015-11-19下午3:11:45
	 *  
	 * @Description: 首页链接可用性导出excel时，问题描述为空
	 * @modify cuichx --- 2016-4-28下午1:28:16
	 */
	public void linkHomeAvaliableExcel() {

		//从session中获取10位填报单位网站标识码
		String siteCode = getCurrentUserInfo().getChildSiteCode();
		if (StringUtils.isEmpty(siteCode)) {
			siteCode = getCurrentUserInfo().getSiteCode();
		}
		LinkHomeAvailableRequest linkHomeAvailableRequest = new LinkHomeAvailableRequest();
		ArrayList<Object[]> list = new ArrayList<Object[]>();
		Object[] obj1 = new Object[] { "序号", "扫描时间", "不可用链接URL", "链接标题","父页面URL", "连接不可用天数", "资源描述", "问题描述", "快照" };
		list.add(obj1);
		String scanDate = request.getParameter("scanDate");
		if (StringUtils.isEmpty(scanDate)) {
			scanDate = DateUtils.getYesterdayStr();
		}

		String key = request.getParameter("key");
		if (StringUtils.isNotEmpty(key)) {
			linkHomeAvailableRequest.setTerm(key);
		}
		String homeUnavailableSelectId = request.getParameter("homeUnavailableSelectId");
		if(StringUtils.isNotEmpty(homeUnavailableSelectId) && !homeUnavailableSelectId.equals("0")){
			if(homeUnavailableSelectId.equals("1")){
				DicConfig dicConfig = dicConfigServiceImpl.get(20);//取出确认死链配置
				String[] strArray = dicConfig.getValue().split(",");
				linkHomeAvailableRequest.setQuestionCodeArr(strArray);
			}else if(homeUnavailableSelectId.equals("2")){
				DicConfig dicConfig = dicConfigServiceImpl.get(21);//疑似死链配置
				String[] strArray = dicConfig.getValue().split(",");
				linkHomeAvailableRequest.setQuestionCodeArr(strArray);
			}
		}
		String fileName = scanDate + "首页链接可用性监测结果.xls";
		String title = "首页链接可用性监测结果";
		linkHomeAvailableRequest.setScanDate(scanDate);
		linkHomeAvailableRequest.setSiteCode(siteCode);
		linkHomeAvailableRequest.setPageSize(Integer.MAX_VALUE);
		try {
			List<LinkHomeAvailable> queryList = linkHomeAvailableServiceImpl.queryList(linkHomeAvailableRequest);
//			// 如果configLinkExcept表中的url存在 则从 linkHomeAvailableList 集合中删除
//			ConfigLinkExceptRequest cRequest = new ConfigLinkExceptRequest();
//			cRequest.setSiteCode(linkHomeAvailableRequest.getSiteCode());
//			cRequest.setStatus(0);
//			cRequest.setPageSize(Integer.MAX_VALUE);
//			List<ConfigLinkExcept> configList = configLinkExceptServiceImpl.queryList(cRequest);
//			
//			List<LinkHomeAvailable> listUrl = new ArrayList<LinkHomeAvailable>();
//			for (LinkHomeAvailable linkHomeAvailable : queryList) {
//				for (ConfigLinkExcept configLinkExcept : configList) {
//					if(linkHomeAvailable.getUrl().startsWith(configLinkExcept.getUrl())){
//						listUrl.add(linkHomeAvailable);
//					}
//				}
//			}
//			queryList.removeAll(listUrl);
			

			if (queryList.size() > 0) {
				for (int i = 0; i < queryList.size(); i++) {
					LinkHomeAvailable linkHomeAvailable = queryList.get(i);
					Object[] obj = new Object[9];
					obj[0] = i + 1;
					String scanTime = linkHomeAvailable.getScanTime();
					if (StringUtils.isNotEmpty(scanTime)) {
						obj[1] = DateUtils.formatStandardDateTime(DateUtils.parseStandardDateTime(scanTime));
					} else {
						obj[1] = "";
					}
					String Url = linkHomeAvailable.getUrl();
					obj[2] = Url;
					String linkTitle = linkHomeAvailable.getLinkTitle();
					if (StringUtils.isNotEmpty(linkTitle)) {
						obj[3] = linkTitle;
					} else {
						obj[3] = "";
					}
					//父页面url
					String parentUrl = linkHomeAvailable.getParentUrl();
					if(StringUtils.isEmpty(parentUrl)){
						parentUrl="";
					}
					obj[4] = parentUrl;
					obj[5] = linkHomeAvailable.getLinkNotavailableDay();//链接可用性天数
					int urlType = linkHomeAvailable.getUrlType();
					String resourceDes =ResourceType.getNameByCode(databaseBizServiceImpl.getResourceType(urlType));
					String scope =ScopeType.getNameByCode(linkHomeAvailable.getScope());
					obj[6] = resourceDes + "(" + scope + ")";

					String questionCode = "";
					if (StringUtils.isNotEmpty(linkHomeAvailable.getQuestionCode())) {
						questionCode = linkHomeAvailable.getQuestionCode();
						String questionDescribe = QuestionType.getNameByCode(questionCode);
						obj[7] = questionCode + "    " + questionDescribe;
					} else {
						obj[7] = "";
					}

					String imgUrl = linkHomeAvailable.getImgUrl();
					if (StringUtils.isNotEmpty(imgUrl)) {
						obj[8] = urlAdapterVar.getXenuLinkUrl() + imgUrl;
					} else {
						obj[8] = "无";
					}
					list.add(obj);
				}
			}
			ExportExcel.HomeLinkHomeExcel(fileName, title, list);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("首页链接可用性监测结果 excel 导出" + "  siteCode :" + siteCode + " scanDate :" + scanDate);
		}
	}

	/** @Description:长期不可用链接
	 * @author zhurk --- 2015-11-19下午5:30:06                
	*/
	public void longNotAvailableExcel() {

		//从session中获取10位填报单位网站标识码
		String siteCode = getCurrentUserInfo().getChildSiteCode();
		if (StringUtils.isEmpty(siteCode)) {
			siteCode = getCurrentUserInfo().getSiteCode();
		}
		LinkHomeAvailableRequest linkHomeAvailableRequest = new LinkHomeAvailableRequest();
		ArrayList<Object[]> list = new ArrayList<Object[]>();
		Object[] obj1 = new Object[] { "序号", "不可用链接URL", "链接标题", "资源类型", "范围", "连续不可用天数" };
		list.add(obj1);
		String fileName = "长期不可用链接.xls";
		String title = "长期不可用链接";
		linkHomeAvailableRequest.setSiteCode(siteCode);
		linkHomeAvailableRequest.setPageSize(Integer.MAX_VALUE);
		try {
			List<LinkHomeAvailable> queryList = linkHomeAvailableServiceImpl.queryList(linkHomeAvailableRequest);
			if (queryList.size() > 0) {
				for (int i = 0; i < queryList.size(); i++) {
					LinkHomeAvailable linkHomeAvailable = queryList.get(i);
					if (linkHomeAvailable.getLinkNotavailableDay() >= 30) {
						Object[] obj = new Object[6];
						obj[0] = i + 1;
						String Url = linkHomeAvailable.getUrl();
						obj[1] = Url;
						String linkTitle = linkHomeAvailable.getLinkTitle();
						obj[2] = linkTitle;
						int urlType = linkHomeAvailable.getUrlType();
						String resourceDes =ResourceType.getNameByCode(databaseBizServiceImpl.getResourceType(urlType));
						String scope =ScopeType.getNameByCode(linkHomeAvailable.getScope());
						obj[3] = resourceDes;
						obj[4] = scope;
						int linkNotAvailableday = linkHomeAvailable.getLinkNotavailableDay();
						obj[5] = linkNotAvailableday;
						list.add(obj);
					}
				}
			}
			ExportExcel.HomeLinkLongExcel(fileName, title, list);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("长期不可用性 excel 导出" + "  siteCode :" + siteCode);
		}

	}

	public List<LinkHomeAvailable> getLinkHomeAvailableList() {
		return linkHomeAvailableList;
	}

	public void setLinkHomeAvailableList(List<LinkHomeAvailable> linkHomeAvailableList) {
		this.linkHomeAvailableList = linkHomeAvailableList;
	}

	public List<LinkHomeAvailable> getLongNoAvailableList() {
		return longNoAvailableList;
	}

	public void setLongNoAvailableList(List<LinkHomeAvailable> longNoAvailableList) {
		this.longNoAvailableList = longNoAvailableList;
	}

	public String getControlExcel() {
		return controlExcel;
	}

	public void setControlExcel(String controlExcel) {
		this.controlExcel = controlExcel;
	}

	public String getSiteBeginServiceDate() {
		return siteBeginServiceDate;
	}

	public void setSiteBeginServiceDate(String siteBeginServiceDate) {
		this.siteBeginServiceDate = siteBeginServiceDate;
	}

}
