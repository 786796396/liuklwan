package com.ucap.cloud_web.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.publics.util.page.QueryOrder;
import com.publics.util.page.QueryOrderType;
import com.publics.util.utils.DateUtils;
import com.publics.util.utils.StringUtils;
import com.ucap.cloud_web.UrlAdapterVar;
import com.ucap.cloud_web.bizService.DatabaseBizService;
import com.ucap.cloud_web.constant.QuestionType;
import com.ucap.cloud_web.constant.ResourceType;
import com.ucap.cloud_web.dto.ConfigLinkExceptRequest;
import com.ucap.cloud_web.dto.DatabaseInfoRequest;
import com.ucap.cloud_web.dto.LinkAllDetailRequest;
import com.ucap.cloud_web.dto.ReportManageLogRequest;
import com.ucap.cloud_web.entity.ConfigLinkExcept;
import com.ucap.cloud_web.entity.DatabaseInfo;
import com.ucap.cloud_web.entity.DicConfig;
import com.ucap.cloud_web.entity.LinkAllDetail;
import com.ucap.cloud_web.entity.LinkAllInfo;
import com.ucap.cloud_web.entity.ReportManageLog;
import com.ucap.cloud_web.entity.ServicePeriod;
import com.ucap.cloud_web.service.IConfigLinkExceptService;
import com.ucap.cloud_web.service.IDatabaseInfoService;
import com.ucap.cloud_web.service.IDicConfigService;
import com.ucap.cloud_web.service.ILinkAllDetailService;
import com.ucap.cloud_web.service.ILinkAllInfoService;
import com.ucap.cloud_web.service.IReportManageLogService;
import com.ucap.cloud_web.service.IServicePeriodService;
import com.ucap.cloud_web.util.CommonUtils;
import com.ucap.cloud_web.util.ExportExcel;

import net.sf.json.JSONObject;

/**
 * <p>Description: </p>全站链接可用性--填报单位
 * <p>@Package：com.ucap.cloud_web.action </p>
 * <p>Title: LinkAllAction </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：cuichx </p>
 * <p>@date：2015-11-8下午3:32:51 </p>
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class LinkAllAction extends BaseAction {
	private static Log logger = LogFactory.getLog(LinkAllAction.class);

	@Autowired
	private ILinkAllDetailService linkAllDetailServiceImpl;

	@Autowired
	private IReportManageLogService reportManageLogServiceImpl;
	@Autowired
	private ILinkAllInfoService linkAllInfoServiceImpl;

	@Autowired
	private IDatabaseInfoService databaseInfoServiceImpl;

	@Autowired
	private IServicePeriodService servicePeriodServiceImpl;
	@Autowired
	private IConfigLinkExceptService configLinkExceptServiceImpl;
	@Autowired
	private UrlAdapterVar urlAdapterVar;
	@Autowired
	private IDicConfigService dicConfigServiceImpl;
	@Autowired
	private DatabaseBizService databaseBizServiceImpl;
	private List<LinkAllDetail> returnList = null;
	private String comPercent = "";
	private String uncomPercent = "";
	private String menuType;

	/**
	 * @Description: 全站链接可用性列表页面初始化--同时加载饼状图需要的数据
	 * @author cuichx --- 2015-11-11上午10:09:35     
	 * @return
	 */
	public String linkAllDetailIndex() {
		//siteCode处理由组织单位跳转到该页面时，session的修改
		String siteCode = request.getParameter("siteCode");
		String servicePeriodIdZZ = request.getParameter("servicePeriodId");
		request.setAttribute("servicePeriodIdZZ", servicePeriodIdZZ);
		if (StringUtils.isNotEmpty(siteCode)) {	
			setCurrentShiroUser(siteCode);
		}
		return "success";

	}

	/**
	 * @Description: 全站链接可用性--本期检测进度
	 * @author cuichx --- 2015-11-23上午10:18:47
	 */
	public void linkAllInfoPie() {
		Map<String, Object> pieMap = new HashMap<String, Object>();
		try {
			//从session中获取10位填报单位网站标识码
			String siteCode = getCurrentUserInfo().getChildSiteCode();
			if (StringUtils.isEmpty(siteCode)) {
				siteCode = getCurrentUserInfo().getSiteCode();
			}

			//获取当前周期
			/**老合同信息**/
			ServicePeriod servicePeriod = getCurrentPeriod();
			/**新产品信息**/
			// Integer[] productTypeArr = { CrmProductsType.DETECTION.getCode()
			// };
			// ServicePeriod servicePeriod =
			// getCurrentServicePeriod(productTypeArr);
			if (servicePeriod != null && !"".equals(servicePeriod)) {
				String startTime = DateUtils.formatStandardDate(servicePeriod.getStartDate());//本期服务开始时间
				String endTime = DateUtils.formatStandardDate(servicePeriod.getEndDate());//本期服务结束时间
				String nowTime = DateUtils.formatStandardDate(new Date());//当前时间

				//本期总天数
				int daysTotal = DateUtils.getDaysBetween2Days(DateUtils.parseStandardDate(startTime), DateUtils.parseStandardDate(endTime));
				//本期已经过去的天数
				int outTotal = DateUtils.getDaysBetween2Days(DateUtils.parseStandardDate(startTime), DateUtils.parseStandardDate(nowTime));

				int compareDay = DateUtils.getDaysBetween2Days(DateUtils.parseStandardDate(endTime), DateUtils.parseStandardDate(nowTime));

				//如果到时间点未生成报告，完成进度为99%，未完成1%
				if (compareDay >= 0) {
					//根据服务周期表id和网站表示码查询报告管理页面，如果数据不存在，说明报告还未生成，反而，报告已经生成
					ReportManageLogRequest reportManageLogRequest = new ReportManageLogRequest();
					reportManageLogRequest.setSiteCode(siteCode);
					reportManageLogRequest.setServicePeriodId(servicePeriod.getId());

					List<ReportManageLog> reportList = new ArrayList<ReportManageLog>();
					reportList = reportManageLogServiceImpl.queryList(reportManageLogRequest);

					//如果数据不存在，说明报告还未生成   完成进度为99%，未完成1%
					if (reportList == null || reportList.size() == 0) {
						comPercent = "99";
						uncomPercent = "1";
					}
				} else {
					comPercent = StringUtils.getPrettyNumber(StringUtils.formatDouble(2, 100.0 * outTotal / daysTotal));
					uncomPercent = StringUtils.getPrettyNumber(StringUtils.formatDouble(2, 100.0 - 100.0 * outTotal / daysTotal));
				}
				logger.info("comPercent=" + comPercent);
				logger.info("uncomPercent=" + uncomPercent);

				pieMap.put("comPercent", comPercent);
				pieMap.put("uncomPercent", uncomPercent);

				logger.info("pieMap=" + pieMap);
				writerPrint(JSONObject.fromObject(pieMap).toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Description:页面周期时间控件初始化数据 
	 * @author cuichx --- 2015-11-18下午4:06:24
	 */
	public void getTimeTool() {
		try {
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap = timeTool("");
			logger.info(JSONObject.fromObject(resultMap).toString());
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Description: 全站链接可用性趋势--折线图
	 * @author cuichx --- 2015-11-11上午10:09:35     
	 * @return
	 */
	@SuppressWarnings("unused")
	public void linkAllInfoLine() {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			//从session中获取10位填报单位网站标识码
			String siteCode = getCurrentUserInfo().getChildSiteCode();
			if (StringUtils.isEmpty(siteCode)) {
				siteCode = getCurrentUserInfo().getSiteCode();
			}
			//获取当前周期
			/**老合同信息**/
			ServicePeriod servicePeriod = getCurrentPeriod();
			/**新产品信息**/
			// Integer[] productTypeArr = { CrmProductsType.DETECTION.getCode()
			// };
			// ServicePeriod servicePeriod =
			// getCurrentServicePeriod(productTypeArr);
			if (servicePeriod != null) {
				if (1 > 1) {
					//根据站点信息表id和网站标识码，查询站点信息表，获取订单服务的起始时间
					DatabaseInfoRequest siteRequest = new DatabaseInfoRequest();
					siteRequest.setSiteCode(siteCode);
					List<DatabaseInfo> siteList = databaseInfoServiceImpl.queryList(siteRequest);
					if (siteList != null && siteList.size() > 0) {
						//获取站点服务信息表中的服务开始时间
						//Date beginTime=siteList.get(0).getServiceBeginTime();
						Map<String, Object> paramMap = new HashMap<String, Object>();
						paramMap.put("siteCode", siteCode);
						//paramMap.put("beginTime", beginTime);
						//根据siteCode查询  订单服务开始时间到当前时间的全站链接可用性信息
						List<LinkAllInfo> allInfoList = linkAllInfoServiceImpl.getAllline(paramMap);
						List<Object> lineList = new ArrayList<Object>();
						if (allInfoList != null && allInfoList.size() > 0) {
							for (LinkAllInfo linkAllInfo : allInfoList) {
								Map<String, Object> map = new HashMap<String, Object>();
								int websiteSum = linkAllInfo.getWebsiteSum();
								int servicePId = linkAllInfo.getServicePeriodId();
								//根据服务周期表id获取期数
								ServicePeriod servicePeriod2 = servicePeriodServiceImpl.get(servicePId);
								int perNum = 1;//获取期数
								map.put("perNum", perNum);
								map.put("websiteSum", websiteSum);
								lineList.add(map);
							}
						}

						resultMap.put("lineList", lineList);
					}
				} else {
					logger.info("当前周期为第一周期，不存在数据！");
					//resultMap.put("errormsg", "当前周期为第一周期，不存在数据！");
				}
			} else {
				logger.info("服务周期表中不存在当前周期对象！");
				//resultMap.put("errormsg", "服务周期表中不存在当前周期对象！");
			}
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 死链过滤数据
	 * @author qinjy
	 * @return
	 */
	public List<ConfigLinkExcept> deathChainList (String siteCode) {
		List<ConfigLinkExcept> configLinkExceptlist = new ArrayList<ConfigLinkExcept>();
		try{
			// 死链不包含url配置表数据
			ConfigLinkExceptRequest configLinkExceptRequest = new ConfigLinkExceptRequest();
			configLinkExceptRequest.setSiteCode(siteCode);
			configLinkExceptRequest.setStatus(0);
			configLinkExceptRequest.setPageSize(Integer.MAX_VALUE);
			configLinkExceptlist = configLinkExceptServiceImpl.queryList(configLinkExceptRequest);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return configLinkExceptlist;
		
	}
	
	/**
	 * @Description: 全站链接可用性检测结果---分页列表查询
	 * @author cuichx --- 2015-11-11下午2:17:05
	 */
	public void linkAllPage() {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		try {
			//从session中获取10位填报单位网站标识码
			String siteCode = getCurrentUserInfo().getChildSiteCode();
			if (StringUtils.isEmpty(siteCode)) {
				siteCode = getCurrentUserInfo().getSiteCode();
			}

			//关键字
			String terms = request.getParameter("terms");

			String pos = request.getParameter("pos");
			String size = request.getParameter("size");
			String servicePdId = request.getParameter("servicePdId");  //周期id
			String stationInsOut = request.getParameter("stationInsOut");//1站内 2站外
			String chainSelectId = request.getParameter("chainSelectId");//1确认，2疑似全站死链
			
			Integer se = 0;
			if(StringUtils.isNotEmpty(servicePdId)){
				se = Integer.valueOf(servicePdId);
			}
			//使用周期id 去service_period表中获取status 服务报告周期状态（-1：删除，0：未开始服务，1：服务中，2已完成服务
			ServicePeriod servicePeriod = servicePeriodServiceImpl.get(se);
			
			//获取排序条件
			String sSortDir_0 = request.getParameter("sSortDir_0");//控制升序和降序
			String soraFiel = request.getParameter("mDataProp_" + request.getParameter("iSortCol_0"));//获取要排序的字段

			//封装查询条件
			LinkAllDetailRequest request = new LinkAllDetailRequest();
			
			if (StringUtils.isNotEmpty(stationInsOut) && !stationInsOut.equals("0")) {
				request.setWebType(Integer.valueOf(stationInsOut));
			}
			if (StringUtils.isNotEmpty(chainSelectId) && !chainSelectId.equals("0")) {
				if(chainSelectId.equals("1")){
					DicConfig dicConfig = dicConfigServiceImpl.get(20);//取出确认死链配置
					String[] strArray = dicConfig.getValue().split(",");
					request.setErrorCodeArr(strArray);
				}else if(chainSelectId.equals("2")){
					DicConfig dicConfig = dicConfigServiceImpl.get(21);//疑似死链配置
					String[] strArray = dicConfig.getValue().split(",");
					request.setErrorCodeArr(strArray);
				}
			}
			if (StringUtils.isNotEmpty(pos)) {
				request.setPageNo(Integer.parseInt(pos));
			}
			if (StringUtils.isNotEmpty(size)) {
				request.setPageSize(Integer.parseInt(size));
			}
			request.setPageSize(Integer.MAX_VALUE);
			if (StringUtils.isNotEmpty(terms)) {
				request.setTerms(terms);
			}
			if(StringUtils.isNotEmpty(servicePdId) && !servicePdId.equals("undefined") && !servicePdId.equals("暂无周期！")) {
				request.setServicePeriodId(Integer.valueOf(servicePdId));
			}
			request.setSiteCode(siteCode);
			
			List<Object> list = new ArrayList<Object>();
			Integer count = 0;
			if(StringUtils.isNotEmpty(servicePdId) && !servicePdId.equals("暂无周期！") &&  !servicePdId.equals("undefined")){
				//分页查询全站链接可用性表	
				ConfigLinkExceptRequest cRequest = new ConfigLinkExceptRequest();
				cRequest.setSiteCode(siteCode);
				cRequest.setStatus(0);
				int counts = configLinkExceptServiceImpl.queryCount(cRequest);
				List<LinkAllDetail> dataList = new ArrayList<LinkAllDetail>();
				if (soraFiel != null) {
				if("scanLeval".equals(soraFiel)){
					soraFiel="scan_level";
				}
				if("scanTime".equals(soraFiel)){
					soraFiel="scan_time";
				}
				}
				if(counts>0){
					//排序字段
					if (soraFiel != null) {
						List<QueryOrder> querySiteList = new ArrayList<QueryOrder>();
						if (!"".equals(sSortDir_0) && "asc".equals(sSortDir_0)) {
							QueryOrder siteQueryOrder = new QueryOrder("la."+soraFiel, QueryOrderType.ASC);
							querySiteList.add(siteQueryOrder);
						} else {
							QueryOrder siteQueryOrder = new QueryOrder("la."+soraFiel, QueryOrderType.DESC);
							querySiteList.add(siteQueryOrder);
						}
						request.setQueryOrderList(querySiteList);
					}
				}else{
					//排序字段
					if (soraFiel != null) {
						List<QueryOrder> querySiteList = new ArrayList<QueryOrder>();
						if (!"".equals(sSortDir_0) && "asc".equals(sSortDir_0)) {
							QueryOrder siteQueryOrder = new QueryOrder(soraFiel, QueryOrderType.ASC);
							querySiteList.add(siteQueryOrder);
						} else {
							QueryOrder siteQueryOrder = new QueryOrder(soraFiel, QueryOrderType.DESC);
							querySiteList.add(siteQueryOrder);
						}
						request.setQueryOrderList(querySiteList);
					}
				}
			 	count = linkAllDetailServiceImpl.queryCount(request);
				if(count >= 500){//如果大于等于500条数据只查500条
					request.setPageSize(500);
				}
				dataList  = linkAllDetailServiceImpl.queryList(request); 

				if (dataList != null && dataList.size() > 0) {
					for (int i = 0; i < dataList.size(); i++) {
						HashMap<String, Object> map = new HashMap<String, Object>();
						LinkAllDetail linkAllDetail = dataList.get(i);
						String scanTime = linkAllDetail.getScanTime();//扫描时间
						String url = linkAllDetail.getUrl();//不可用链接URL
						String parentUrl=linkAllDetail.getParentUrl();//父页面URl
						int scanLeval = linkAllDetail.getScanLevel();//监测层次
						
						if (StringUtils.isEmpty(parentUrl)) {
							parentUrl = "";
						}
						String linkTitle = linkAllDetail.getLinkTitle();//链接标题
						if (StringUtils.isEmpty(linkTitle)) {
							linkTitle = "无";
						}

						String parentTitle = linkAllDetail.getParentTitle();//父页面标题
						if (StringUtils.isEmpty(parentTitle)) {
							parentTitle = "无";
						}
						int urlType = Integer.valueOf(linkAllDetail.getUrlType());
						String resourceTypeName =ResourceType.getNameByCode(databaseBizServiceImpl.getResourceType(urlType));
						String webType = linkAllDetail.getWebType() == 1 ?"站内":"站外";
						String resourceDes = resourceTypeName + "(" + webType + ")";//资源类型

						//错误描述
//						String errorDes = "";
//						if (StringUtils.isNotEmpty(linkAllDetail.getErrorCode())) {
//							if (StringUtils.isNotEmpty(linkAllDetail.getErrorDescribe())) {
//								errorDes = linkAllDetail.getErrorCode() + " " + QuestionType.getNameByCode(linkAllDetail.getErrorCode());
//							} else {
//								errorDes = linkAllDetail.getErrorCode();
//							}
//						} else {
//							errorDes = "";
//						}
						//table弹框内容Bomb
						String errorCodeBomb = linkAllDetail.getErrorCode();//错误编码
						String errorDescribeBomb = QuestionType.getNameByCode(linkAllDetail.getErrorCode());//错误描述
						String imgUrl = linkAllDetail.getImgUrl();//快照
						if (StringUtils.isNotEmpty(imgUrl)) {
							imgUrl = urlAdapterVar.getXenuLinkUrl() + imgUrl;
						} else {
							imgUrl = "";//urlAdapterVar.getXenuLinkUrl()+ "";
						}

						map.put("scanTime", scanTime);
						map.put("url", url);
						map.put("parentUrl", parentUrl);
						map.put("scanLeval", scanLeval);
						map.put("linkTitle", linkTitle);
						map.put("parentTitle", parentTitle);
						map.put("resourceDes", resourceDes);
						map.put("errorCodeBomb", errorCodeBomb);//table弹框内容
						map.put("errorDescribeBomb", errorDescribeBomb);//table弹框内容
						map.put("imgUrl", imgUrl);
						
						if (deathChainList(siteCode).size() > 0) {
							int addflag = filteringUrl(linkAllDetail.getUrl(),deathChainList(siteCode));
							if (addflag == 0) {  // addflag==0 表示 死链不包含表里 没有 该url 可以添加
								list.add(map); 
							}
						}else {
							list.add(map);  // 死链不包含表没有改siteCode数据 直接添加
						}
						
					}
				}
			}
			
			returnMap.put("body", list);
			returnMap.put("size", count);
//			returnMap.put("count", count);
			//将服务周期报告扔到页面
			if(servicePeriod !=null){
				returnMap.put("servicePeriodStatus",servicePeriod.getStatus());
			}
			
//			returnMap.put("items", list);
//			returnMap.put("totalRecords", pageVo.getData().size());
//			returnMap.put("iTotalDisplayRecords", pageVo.getRecordSize());
//			returnMap.put("hasMoreItems", true);

			writerPrint(JSONObject.fromObject(returnMap).toString());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @Description: 周期时间
	 * @author qinjy
	 */
	public void linkAllScanCycle() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			//从session中获取10位填报单位网站标识码
			String siteCode = getCurrentUserInfo().getSiteCode();
			if(StringUtils.isEmpty(siteCode)){
				siteCode=getCurrentUserInfo().getChildSiteCode();
			}
			String siteCodeTB = getCurrentUserInfo().getChildSiteCode();
			if(siteCode.length()==6){
				resultMap = databaseBizServiceImpl.characterCycle(siteCode,siteCodeTB);
			}else{
				resultMap = databaseBizServiceImpl.characterCycle(siteCode,null);
			}
			resultMap.put("errorMsg", "更新成功！");
			resultMap.put("success", "true");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			resultMap.put("errorMsg", "更新失败！");
			writerPrint(JSONObject.fromObject(resultMap).toString());
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description: 全站链接可用性 excel 导出
	 * @author sunjiang --- 2015年11月18日下午6:13:53
	 */
	public void linkAllExcel() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			//从session中获取10位填报单位网站标识码
			String siteCode = getCurrentUserInfo().getChildSiteCode();
			if (StringUtils.isEmpty(siteCode)) {
				siteCode = getCurrentUserInfo().getSiteCode();
			}
			
			ServicePeriod sp = null;
			String title = "全站链接可用性监测结果";
			String servicePdId = request.getParameter("servicePdId");  //周期id
			String terms = request.getParameter("terms");	//关键字
			String stationInsOut = request.getParameter("stationInsOut");//站内站外
			String chainSelectId = request.getParameter("chainSelectId");//确认，疑似全站死链
			
			//dto对象封装页面参数
			LinkAllDetailRequest detailRequest = new LinkAllDetailRequest();

			//封装数据中查询的结果
			ArrayList<Object[]> list = new ArrayList<Object[]>();
			//excel标题
			Object[] obj1 = new Object[] { "序号", "时间", "不可用链接URL", "监测层次", "链接标题","父页面URL", "父页面标题", "资源描述", "错误描述", "快照" };
			list.add(obj1);

			String databaseName = "";
			DatabaseInfoRequest request = new DatabaseInfoRequest();
			request.setSiteCode(siteCode);
			List<DatabaseInfo> dataList = databaseInfoServiceImpl.queryList(request);
			if(CollectionUtils.isNotEmpty(dataList) && dataList.size() > 0){
				databaseName = dataList.get(0).getName().trim();//获得当前登录站点名称
			}
			if (StringUtils.isNotEmpty(servicePdId)) {
				sp = servicePeriodServiceImpl.get(Integer.parseInt(servicePdId));
				title = "全站链接可用性监测结果(" + sdf.format(sp.getStartDate())
						+ "至" + sdf.format(sp.getEndDate()) + ")";
			}
			String fileName = "";
			if (StringUtils.isNotEmpty(servicePdId)) {
				fileName=databaseName+"-链接可用性-全站链接可用性(" + sdf.format(sp.getStartDate())
						+ "至" + sdf.format(sp.getEndDate()) + ").xls";
			}else{
				fileName=databaseName+"-链接可用性-全站链接可用性(" + DateUtils.formatStandardDate(new Date()) + ").xls";
			}					

			/*if(StringUtils.isNotEmpty(servicePdId)){
				detailRequest.setServicePeriodId(Integer.valueOf(servicePdId));
			}else{
				ServicePeriod curServicePd=getCurrentPeriod();
				if(curServicePd!=null){
					detailRequest.setServicePeriodId(curServicePd.getId());
				}
			}*/
			if (StringUtils.isNotEmpty(stationInsOut) && !stationInsOut.equals("0")) {
				detailRequest.setWebType(Integer.valueOf(stationInsOut));
			}
			if (StringUtils.isNotEmpty(chainSelectId) && !chainSelectId.equals("0")) {
				if(chainSelectId.equals("1")){
					DicConfig dicConfig = dicConfigServiceImpl.get(20);//取出确认死链配置
					String[] strArray = dicConfig.getValue().split(",");
					detailRequest.setErrorCodeArr(strArray);
				}else if(chainSelectId.equals("2")){
					DicConfig dicConfig = dicConfigServiceImpl.get(21);//疑似死链配置
					String[] strArray = dicConfig.getValue().split(",");
					detailRequest.setErrorCodeArr(strArray);
				}
			}
			if(StringUtils.isNotEmpty(servicePdId) && !servicePdId.equals("undefined") && !servicePdId.equals("暂无周期！")){
				detailRequest.setServicePeriodId(Integer.valueOf(servicePdId));
			}
			if (StringUtils.isNotEmpty(terms)) {
				detailRequest.setTerms(terms);
			}

			if(!servicePdId.equals("暂无周期！") && StringUtils.isNotEmpty(servicePdId)){
				detailRequest.setSiteCode(siteCode);
				detailRequest.setPageSize(Integer.MAX_VALUE);
				List<LinkAllDetail> detailList = new ArrayList<LinkAllDetail>();
				detailList = linkAllDetailServiceImpl.queryList(detailRequest);//  代码处理

				if (detailList != null && detailList.size() > 0) {
					for (int i = 0; i < detailList.size(); i++) {
						LinkAllDetail linkAllDetail = detailList.get(i);
						Object[] obj = new Object[10];

						String scanTime = linkAllDetail.getScanTime();//扫描时间
						String url = linkAllDetail.getUrl();//不可用链接URL
						int scanLeval = linkAllDetail.getScanLevel();//监测层次
						String linkTitle = linkAllDetail.getLinkTitle();//链接标题
						String parentTitle = linkAllDetail.getParentTitle();//父页面标题
						if (StringUtils.isEmpty(linkTitle)) {
							linkTitle = "";
						}
						if (StringUtils.isEmpty(parentTitle)) {
							parentTitle = "";
						}
						int urlType = Integer.valueOf(linkAllDetail.getUrlType());
						String resourceTypeName =ResourceType.getNameByCode(databaseBizServiceImpl.getResourceType(urlType));
//						int resourceType = linkAllDetail.getResourceType();
//						String resourceTypeName = resourceType == 1 ? ResourceType.PICTURE.getName() : resourceType == 2 ? ResourceType.WEBPAGE.getName() : resourceType == 3 ? ResourceType.ATTACHMENT.getName() : "其他";
//						String scope = linkAllDetail.getScope() == 1 ? ScopeType.IN_AREA.getName() : ScopeType.OUT_AREA.getName();
						String webType = linkAllDetail.getWebType() == 1 ?"站内":"站外";
						String resourceDes = resourceTypeName + "(" + webType + ")";//资源类型
						
						//错误描述
						String errorDes = "";
						if (StringUtils.isNotEmpty(linkAllDetail.getErrorCode())) {
							if (StringUtils.isNotEmpty(linkAllDetail.getErrorDescribe())) {
								errorDes = linkAllDetail.getErrorCode() + " " + linkAllDetail.getErrorDescribe();
							} else {
								errorDes = linkAllDetail.getErrorCode() + " " + QuestionType.getNameByCode(linkAllDetail.getErrorCode());
							}
						}

						String imgUrl = linkAllDetail.getImgUrl();//快照
						if (StringUtils.isNotEmpty(imgUrl)) {
							imgUrl = urlAdapterVar.getXenuLinkUrl() + imgUrl;
						} else {
							imgUrl = "";//urlAdapterVar.getXenuLinkUrl()+ "";
						}
						String parentUrl=linkAllDetail.getParentUrl();//父页面URl
						if (StringUtils.isEmpty(parentUrl)) {
							parentUrl = "";
						}
						obj[0] = i + 1;
						obj[1] = scanTime;
						obj[2] = CommonUtils.setHttpUrl(url); //  判断是否有http头
						obj[3] = scanLeval;
						obj[4] = linkTitle;
						obj[5] = parentUrl;
						obj[6] = parentTitle;
						obj[7] = resourceDes;
						obj[8] = errorDes;
						obj[9] = CommonUtils.setHttpUrl(imgUrl); //  判断是否有http头
						
						if (deathChainList(siteCode).size() > 0) {
							int addflag = filteringUrl(linkAllDetail.getUrl(),deathChainList(siteCode));
							if (addflag == 0) {  // addflag==0 表示 死链不包含表里 没有 该url 可以添加
								list.add(obj); 
							}
						}else {
							list.add(obj);  // 死链不包含表没有改siteCode数据 直接添加
						}
						
						
					}
				}
			}
			
			ExportExcel.linkAllDetailExcel(fileName, title, list);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<LinkAllDetail> getReturnList() {
		return returnList;
	}

	public void setReturnList(List<LinkAllDetail> returnList) {
		this.returnList = returnList;
	}

	public String getComPercent() {
		return comPercent;
	}

	public void setComPercent(String comPercent) {
		this.comPercent = comPercent;
	}

	public String getUncomPercent() {
		return uncomPercent;
	}

	public void setUncomPercent(String uncomPercent) {
		this.uncomPercent = uncomPercent;
	}

	public String getMenuType() {
		return menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

}
