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
import com.ucap.cloud_web.constant.SecurityServiceType;
import com.ucap.cloud_web.constant.ServiceUnuserfulType;
import com.ucap.cloud_web.dto.ReportManageLogRequest;
import com.ucap.cloud_web.dto.SecurityServcieRequest;
import com.ucap.cloud_web.dto.ServicePeriodRequest;
import com.ucap.cloud_web.entity.ContractInfo;
import com.ucap.cloud_web.entity.ReportManageLog;
import com.ucap.cloud_web.entity.SecurityServcie;
import com.ucap.cloud_web.entity.ServicePeriod;
import com.ucap.cloud_web.service.IReportManageLogService;
import com.ucap.cloud_web.service.ISecurityServcieService;
import com.ucap.cloud_web.service.IServicePeriodService;
import com.ucap.cloud_web.shiro.Constants;
import com.ucap.cloud_web.util.CommonUtils;
import com.ucap.cloud_web.util.ExportExcel;

import net.sf.json.JSONObject;

/**
 * <p>
 * Description:
 * </p>
 * 内容保障问题--服务不实详情表
 * <p>
 * 
 * @Package：com.ucap.cloud_web.action </p>
 *                                    <p>
 *                                    Title: SecurityServcieAction
 *                                    </p>
 *                                    <p>
 *                                    Company: 开普互联
 *                                    </p>
 *                                    <p>
 * @author：cuichx </p>
 *                <p>
 * @date：2015-11-19下午4:40:57 </p>
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class SecurityServcieAction extends BaseAction {

	@Autowired
	private ISecurityServcieService securityServcieServiceImpl;

	@Autowired
	private IServicePeriodService servicePeriodServiceImpl;

	@Autowired
	private IReportManageLogService reportManageLogServiceImpl;

	@Autowired
	private DatabaseBizService databaseBizServiceImpl;
	// @Autowired
	// private IDicServiceUnuserfulService dicServiceUnuserfulServiceImpl;

	@Autowired
	private UrlAdapterVar urlAdapterVar;

	// log日志信息
	private static Log logger = LogFactory.getLog(SecurityServcieAction.class);

	private String menuType;

	/**
	 * @Description: 服务不实用页面初始化
	 * @author cuichx --- 2015-11-19下午4:42:25
	 * @return
	 */
	public String index() {
		// siteCode处理由组织单位跳转到该页面时，session的修改
		String siteCode = request.getParameter("siteCode");
		String servicePeriodIdZZ = request.getParameter("servicePeriodId");
		request.setAttribute("servicePeriodIdZZ", servicePeriodIdZZ);
		if (StringUtils.isNotEmpty(siteCode)) {
			setCurrentShiroUser(siteCode);
		}
		return "success";
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
	 * @Description: 本期检测进度--饼状图 处理思路： 1.获取当前期号、本期的服务开始时间、服务结束时间
	 *               2.根据本期服务开始时间、结束时间和当前时间，算出进度
	 *               注意：如果当前时间大于本期服务结束时间，但是未生成报告，完成进度为99%，未完成1%，其他按正常逻辑处理
	 * @author cuichx --- 2015-11-17下午5:28:30
	 */
	public void servicePie() {

		Map<String, Object> pieResultMap = new HashMap<String, Object>();
		try {
			// 从session中获取10位填报单位网站标识码
			String siteCode = getCurrentUserInfo().getChildSiteCode();
			if (StringUtils.isEmpty(siteCode)) {
				siteCode = getCurrentUserInfo().getSiteCode();
			}
			// 获取当前周期
			/**老合同信息**/
			ServicePeriod currPeriod = getCurrentPeriod();
			/**新产品信息**/
			// Integer[] productTypeArr = { CrmProductsType.DETECTION.getCode()
			// };
			// ServicePeriod currPeriod =
			// getCurrentServicePeriod(productTypeArr);
			if (currPeriod != null) {
				Date startTime = currPeriod.getStartDate();// 本期服务开始时间
				Date endTime = currPeriod.getEndDate();// 本期服务结束时间
				// 本期总天数
				int daysTotal = DateUtils.getDaysBetween2Days(startTime,
						endTime);
				// 本期已经过去的天数
				int outTotal = DateUtils.getDaysBetween2Days(startTime,
						new Date());
				// 判断当前时间是否在此周期内
				int compareDay = DateUtils.getDaysBetween2Days(endTime,
						new Date());

				String comPercent = "";// 完成进度
				String uncomPercent = "";// 未完成进度

				logger.info("compareDay=" + compareDay);
				// 如果到时间点未生成报告，完成进度为99%，未完成1%
				if (compareDay >= 0) {
					// 根据服务周期表id和网站表示码查询报告管理页面，如果数据不存在，说明报告还未生成，反而，报告已经生成
					ReportManageLogRequest reportManageLogRequest = new ReportManageLogRequest();
					reportManageLogRequest.setSiteCode(siteCode);
					reportManageLogRequest.setServicePeriodId(currPeriod
							.getId());

					List<ReportManageLog> reportList = new ArrayList<ReportManageLog>();
					reportList = reportManageLogServiceImpl
							.queryList(reportManageLogRequest);

					// 如果数据不存在，说明报告还未生成 完成进度为99%，未完成1%
					if (reportList == null || reportList.size() == 0) {
						comPercent = "99";
						uncomPercent = "1";
					}
				} else {
					comPercent = StringUtils.getPrettyNumber(StringUtils
							.formatDouble(2, 100.0 * outTotal / daysTotal));// 已完成
					uncomPercent = StringUtils.getPrettyNumber(StringUtils
							.formatDouble(2, 100.0 - 100.0 * outTotal
									/ daysTotal));// 未完成
				}
				pieResultMap.put("comPercent", comPercent);
				pieResultMap.put("uncomPercent", uncomPercent);
			} else {
				pieResultMap.put("errorMsg", "不存在当前周期对象！");
			}

			// 返回给给页面的数据
			logger.info("pieResultMap=" + pieResultMap);
			writerPrint(JSONObject.fromObject(pieResultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
			pieResultMap.put("errorMsg", "本期检测进度初始化异常!");
			writerPrint(JSONObject.fromObject(pieResultMap).toString());
		}
	}

	/**
	 * @Description: 服务不实用--问题类型统计
	 * @author cuichx --- 2015-11-19下午5:39:48
	 */
	public void serviceProblemType() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			// 从session中获取10位填报单位网站标识码
			String siteCode = getCurrentUserInfo().getChildSiteCode();
			if (StringUtils.isEmpty(siteCode)) {
				siteCode = getCurrentUserInfo().getSiteCode();
			}
			String servicePdId = request.getParameter("servicePdId");// 获取期号
			logger.info("servicePdId=" + servicePdId);

			/**老合同信息**/
			ServicePeriod servicePd = getCurrentPeriod();
			/**新产品信息**/
			// Integer[] productTypeArr = { CrmProductsType.DETECTION.getCode()
			// };
			// ServicePeriod servicePd =
			// getCurrentServicePeriod(productTypeArr);
			if (StringUtils.isEmpty(servicePdId)) {
				// 如果服务周期id为空，获取当前周期id
				if (servicePd != null) {
					servicePdId = servicePd.getId() + "";
				}
			}

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("siteCode", siteCode);
			paramMap.put("servicePeriodId", servicePdId);

			int problemSum = 0;// 问题总数
			List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();// 统计每类问题的
																						// 个数信息

			// 根据网站标识码、期号，查询服务不实用详情表（已问题类型id分组）
			List<SecurityServcieRequest> serviceList = securityServcieServiceImpl
					.queryByMap(paramMap);
			if (serviceList != null && serviceList.size() > 0) {
				for (int i = 0; i < serviceList.size(); i++) {
					Map<String, Object> serviceMap = new HashMap<String, Object>();
					// 获取问题类型id，查询字典表获取类型名称
					int problemTypeId = serviceList.get(i).getProblemTypeId();
					int serviceSum = serviceList.get(i).getServiceSum();// 每类问题的个数
					problemSum += serviceSum;
					serviceMap.put("serviceSum", serviceSum);

					// 办事指南要素缺失
					if (problemTypeId == ServiceUnuserfulType.ONE_PROBLEM_TYPE
							.getCode()) {
						serviceMap
								.put("problemType",
										ServiceUnuserfulType.ONE_PROBLEM_TYPE
												.getName());
					} else if (problemTypeId == ServiceUnuserfulType.TWO_PROBLEM_TYPE
							.getCode()) {
						serviceMap
								.put("problemType",
										ServiceUnuserfulType.TWO_PROBLEM_TYPE
												.getName());
					} else if (problemTypeId == ServiceUnuserfulType.THREE_PROBLEM_TYPE
							.getCode()) {
						serviceMap.put("problemType",
								ServiceUnuserfulType.THREE_PROBLEM_TYPE
										.getName());
					} else if (problemTypeId == ServiceUnuserfulType.FOUR_PROBLEM_TYPE
							.getCode()) {
						serviceMap.put("problemType",
								ServiceUnuserfulType.FOUR_PROBLEM_TYPE
										.getName());
					}
					returnList.add(serviceMap);

				}
			}
			resultMap.put("returnList", returnList);
			resultMap.put("problemSum", problemSum);
			logger.info("resultMap=" + resultMap);
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("errorMsg", "获取服务不实用统计数据异常");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		}
	}

	/**
	 * @Description: 获取监测周期
	 * @author Liukl --- 2016年12月13日17:56:45
	 */
	public void monitoringPeriod() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			// 从session中获取10位填报单位网站标识码
			String siteCode = getCurrentUserInfo().getSiteCode();
			if (StringUtils.isEmpty(siteCode)) {
				siteCode = getCurrentUserInfo().getChildSiteCode();
			}
			String siteCodeTB = getCurrentUserInfo().getChildSiteCode();
			if (siteCode.length() == 6) {
				resultMap = databaseBizServiceImpl.characterCycle(siteCode,
						siteCodeTB);
			} else {
				resultMap = databaseBizServiceImpl.characterCycle(siteCode,
						null);
			}
			resultMap.put("errorMsg", "更新成功！");
			resultMap.put("success", "true");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			resultMap.put("success", "false");
			resultMap.put("errorMsg", "更新失败！");
			writerPrint(JSONObject.fromObject(resultMap).toString());
			e.printStackTrace();
		}

	}

	/**
	 * @Description: 服务不实用检测结果列表查询
	 * @author cuichx --- 2015-11-19下午8:03:10
	 */
	public void serviceProblemPage() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			// 从session中获取10位填报单位网站标识码
			String siteCode = getCurrentUserInfo().getChildSiteCode();
			if (StringUtils.isEmpty(siteCode)) {
				siteCode = getCurrentUserInfo().getSiteCode();
			}
			
			/*String contCode = getCurrentUserInfo().getSiteCode();
			List<ContractInfo> conList = getContractInfoList(contCode, DateUtils.formatStandardDate(new Date()));*/
			if(getCurrentUserInfo().getIscost() ==0 ){
				// 没有查询到此siteCode的合同,直接返回页面，提示相关信息
				resultMap.put("success", "other");
				writerPrint(JSONObject.fromObject(resultMap).toString());
				return;
			}
			
			String servicePdId = request.getParameter("servicePdId");
			String terms = request.getParameter("terms");

			List<Map<String, Object>> pageList = new ArrayList<Map<String, Object>>();
			SecurityServcieRequest securityServcieRequest = new SecurityServcieRequest();
			ServicePeriod servicePeriod = null;
			if (StringUtils.isNotEmpty(servicePdId)
					&& !servicePdId.equals("暂无周期！")) {
				securityServcieRequest.setServicePeriodId(Integer
						.valueOf(servicePdId));
				// 使用周期id 去service_period表中获取status
				// 服务报告周期状态（-1：删除，0：未开始服务，1：服务中，2已完成服务
				servicePeriod = servicePeriodServiceImpl.get(Integer
						.valueOf(servicePdId));
			}
			if (StringUtils.isNotEmpty(terms)) {
				securityServcieRequest.setServiceItem(terms);
			}
			securityServcieRequest.setSiteCode(siteCode);
			// securityServcieRequest.setServicePeriodId(Integer.valueOf(servicePdId));
			List<QueryOrder> queryBlankList = new ArrayList<QueryOrder>();
			// 前台去掉周期，发现时间倒排序
			QueryOrder siteQueryOrder = new QueryOrder("scan_date",
					QueryOrderType.DESC);
			queryBlankList.add(siteQueryOrder);
			securityServcieRequest.setQueryOrderList(queryBlankList);
			securityServcieRequest.setPageSize(Integer.MAX_VALUE);

			// 查询今天之前的数据
			String yesDate = DateUtils.getYesterdayStr();
			securityServcieRequest.setScanDate(yesDate);

			// 根据网站标识码、期号id，查询服务不实用详情表
			List<SecurityServcie> serviceList = securityServcieServiceImpl
					.queryList(securityServcieRequest);
			if (serviceList != null && serviceList.size() > 0) {
				for (int i = 0; i < serviceList.size(); i++) {
					Map<String, Object> serviceMap = new HashMap<String, Object>();

					serviceMap.put("serviceItem", serviceList.get(i)
							.getServiceItem());// 办事事项
					// 问题类型名称
					int problemTypeId = serviceList.get(i).getProblemTypeId();
					String problemTypeName = getSecurityServiceType(problemTypeId);
					if (StringUtils.isNotEmpty(problemTypeName)) {
						serviceMap.put("problemTypeName", problemTypeName);// 问题类型名称
					} else {
						serviceMap.put("problemTypeName", "");
					}
					serviceMap.put("problemDesc", serviceList.get(i)
							.getProblemDesc());// 问题描述
					serviceMap
							.put("scanDate", serviceList.get(i).getScanDate());// 发现时间
					serviceMap.put("url", serviceList.get(i).getUrl() == null?"":serviceList.get(i).getUrl());// 网页url
					serviceMap.put("parentUrl", serviceList.get(i).getParentUrl() == null?"":serviceList.get(i).getParentUrl()); // 父页面url

					// 判断是截图还是快照
					String imageStr2 = serviceList.get(i).getImgUrl();
					if(StringUtils.isNotEmpty(imageStr2)){
						if (imageStr2.contains("htm")) {// 说明是快照
							serviceMap.put("imgUrl", urlAdapterVar.getImgUrl()
									+ imageStr2);// 快照
						} else {
							serviceMap.put("imgUrl", imageStr2);// 截图
						}
					}else{
						serviceMap.put("imgUrl", "");
					}
					
					pageList.add(serviceMap);
				}
			}
			resultMap.put("litImgUrl", urlAdapterVar.getLinkUrl());
			resultMap.put("pageList", pageList);
			resultMap.put("body", pageList);
			resultMap.put("listSize", pageList.size());
			if (servicePeriod != null) {
				resultMap.put("servicePeriodStatus", servicePeriod.getStatus());
			}
			resultMap.put("success", "true");
			
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", "false");
			resultMap.put("errorMsg", "服务不实用列表查询数据异常");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		}
	}

	/**
	 * @Description: 服务不实用柱状图--查询近三期的数据 处理思路：
	 *               获取上一期的期数n，如果n小于3，查询期数<=n的所有数据；如果n大于等于3，获取n,n-1,n-2三期数据
	 * @author cuichx --- 2015-11-20上午9:26:07
	 */
	public void serviceProblemBar() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			// 从session中获取10位填报单位网站标识码
			String siteCode = getCurrentUserInfo().getChildSiteCode();
			if (StringUtils.isEmpty(siteCode)) {
				siteCode = getCurrentUserInfo().getSiteCode();
			}
			// 获取当前周期
			/**老合同信息**/
			ServicePeriod currentPeriod = getCurrentPeriod();
			/**新产品信息**/
			// Integer[] productTypeArr = { CrmProductsType.DETECTION.getCode()
			// };
			// ServicePeriod currentPeriod =
			// getCurrentServicePeriod(productTypeArr);
			if (currentPeriod != null) {
				int currentPeriodNum = 1;// 获取当前期数
				int upPeriodNum = currentPeriodNum - 1;// 获取上一期期数
				// int websiteInfoId=1;//站点信息id

				ServicePeriodRequest servicePeriodRequest = new ServicePeriodRequest();
				if (upPeriodNum > 0) {
					// 根据站点信息id、网站标识码、期数查询服务周期表，获取所有的服务周期表id
					// 查询结果以期号正序排列 便于封装返回页面数据（期号与对应的数据一致）
					List<QueryOrder> querySiteList = new ArrayList<QueryOrder>();
					QueryOrder siteQueryOrder = new QueryOrder("period_num",
							QueryOrderType.ASC);
					querySiteList.add(siteQueryOrder);
					servicePeriodRequest.setQueryOrderList(querySiteList);

					List<Object> typeOne = new ArrayList<Object>();// 问题类型1
					List<Object> typeTwo = new ArrayList<Object>();// 问题类型2
					List<Object> typeThree = new ArrayList<Object>();// 问题类型3
					List<Object> typeFour = new ArrayList<Object>();// 问题类型4
					List<Object> periodList = new ArrayList<Object>();// 周期期数

					List<ServicePeriod> servicePeriodList = servicePeriodServiceImpl
							.queryList(servicePeriodRequest);
					if (servicePeriodList != null
							&& servicePeriodList.size() > 0) {
						for (int i = 0; i < servicePeriodList.size(); i++) {
							int servicePeriodId = servicePeriodList.get(i)
									.getId();
							periodList.add(1);
							Map<String, Object> paramMap = new HashMap<String, Object>();
							paramMap.put("siteCode", siteCode);
							paramMap.put("servicePeriodId", servicePeriodId);

							// 根据网站标识码和服务周期id，查询服务不实用详情表
							List<SecurityServcieRequest> securityServcieList = securityServcieServiceImpl
									.queryByMap(paramMap);
							if (securityServcieList != null
									&& securityServcieList.size() > 0) {
								int serviceTypeOneSum = 0;
								int serviceTypeTwoSum = 0;
								int serviceTypeThreeSum = 0;
								int serviceTypeFourSum = 0;
								for (int j = 0; j < securityServcieList.size(); j++) {
									SecurityServcieRequest returnRequest = securityServcieList
											.get(j);
									int problemTypeId = securityServcieList
											.get(j).getProblemTypeId();// 问题类型id

									if (problemTypeId == ServiceUnuserfulType.ONE_PROBLEM_TYPE
											.getCode()) {
										serviceTypeOneSum += returnRequest
												.getServiceSum();
										typeOne.add(serviceTypeOneSum);
									} else if (problemTypeId == ServiceUnuserfulType.TWO_PROBLEM_TYPE
											.getCode()) {
										serviceTypeTwoSum += returnRequest
												.getServiceSum();
										typeTwo.add(serviceTypeTwoSum);
									} else if (problemTypeId == ServiceUnuserfulType.THREE_PROBLEM_TYPE
											.getCode()) {
										serviceTypeThreeSum += returnRequest
												.getServiceSum();
										typeThree.add(serviceTypeThreeSum);
									} else if (problemTypeId == ServiceUnuserfulType.FOUR_PROBLEM_TYPE
											.getCode()) {
										serviceTypeFourSum += returnRequest
												.getServiceSum();
										typeFour.add(serviceTypeFourSum);
									}
								}
							}
						}
					}
					resultMap.put("typeOne", typeOne);
					resultMap.put("typeTwo", typeTwo);
					resultMap.put("typeThree", typeThree);
					resultMap.put("typeFour", typeFour);
					resultMap.put("periodList", periodList);
				}
			} else {
				logger.info("暂无数据！");
			}
			logger.info("resultMap=" + resultMap);
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Description: 服务不实用excel导出
	 * @author cuichx --- 2015-12-7下午3:04:20
	 */
	@SuppressWarnings("unused")
	public void serviceExcel() {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			// 从session中获取10位填报单位网站标识码
			String siteCode = getCurrentUserInfo().getChildSiteCode();
			if (StringUtils.isEmpty(siteCode)) {
				siteCode = getCurrentUserInfo().getSiteCode();
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String title = "互动回应差监测结果";
			ServicePeriod sp = null;

			String servicePdId = request.getParameter("servicePdId");
			String key = request.getParameter("key");

			// 封装数据中查询的结果
			ArrayList<Object[]> list = new ArrayList<Object[]>();
			// excel标题
			Object[] obj1 = new Object[] { "序号", "办事事项", "网页URL", "问题类型",
					"问题描述", "父页面URL", "问题发现时间", "截图" };
			list.add(obj1);

			String fileName = "";
			String userName = Constants.getCurrendUser().getUserName().trim();//获得当前登录站点名称
			if (StringUtils.isNotEmpty(servicePdId)) {
				sp = servicePeriodServiceImpl
						.get(Integer.parseInt(servicePdId));
				title = "互动回应差监测结果(" + sdf.format(sp.getStartDate()) + "至"
						+ sdf.format(sp.getEndDate()) + ")";
				fileName =userName+"-内容保障问题-服务不实用监测结果("
						+ sdf.format(sp.getStartDate()) + "至"+ sdf.format(sp.getEndDate()) + ").xls";
			}else{
				fileName =userName+"-内容保障问题-服务不实用监测结果）("
						+ DateUtils.formatStandardDate(new Date()) + ").xls";
			}

			// 获取当前周期
			/*
			 * ServicePeriod currentPeriod=getCurrentPeriod();
			 * if(StringUtils.isEmpty(servicePdId)){ if(currentPeriod!=null){
			 * servicePdId=currentPeriod.getId()+""; } }
			 */

			SecurityServcieRequest securityServcieRequest = new SecurityServcieRequest();
			securityServcieRequest.setSiteCode(siteCode);
			if (StringUtils.isNotEmpty(servicePdId)) {
				securityServcieRequest.setServicePeriodId(Integer
						.valueOf(servicePdId));
			}
			securityServcieRequest.setPageSize(Integer.MAX_VALUE);
			if (StringUtils.isNotEmpty(key)) {
				securityServcieRequest.setServiceItem(key);
			}
			List<QueryOrder> queryBlankList = new ArrayList<QueryOrder>();
			// 前台去掉周期，发现时间倒排序
			QueryOrder siteQueryOrder = new QueryOrder("scan_date",
					QueryOrderType.DESC);
			queryBlankList.add(siteQueryOrder);
			securityServcieRequest.setQueryOrderList(queryBlankList);
			// 查询今天之前的数据
			String yesDate = DateUtils.getYesterdayStr();
			securityServcieRequest.setScanDate(yesDate);

			// 根据网站标识码、期号id，查询服务不实用详情表
			List<SecurityServcie> serviceList = securityServcieServiceImpl
					.queryList(securityServcieRequest);
			if (serviceList != null && serviceList.size() > 0) {
				for (int i = 0; i < serviceList.size(); i++) {
					Object[] obj = new Object[8];

					String serviceItem = serviceList.get(i).getServiceItem();// 办事事项
					// 问题类型名称
					int problemTypeId = serviceList.get(i).getProblemTypeId();
					String problemTypeName = getSecurityServiceType(problemTypeId);
					if (StringUtils.isEmpty(problemTypeName)) {
						problemTypeName = "";
					}

					String problemDesc = serviceList.get(i).getProblemDesc();// 问题描述
					if (StringUtils.isEmpty(problemDesc)) {
						problemDesc = "";
					}
					String url = CommonUtils.setHttpUrl(serviceList.get(i)
							.getUrl()); // 判断网址前缀 http
					String parentUrl = CommonUtils.setHttpUrl(serviceList
							.get(i).getParentUrl()); // 父页面url
					String scanDate = serviceList.get(i).getScanDate(); // 扫描时间

					String imgUrl =serviceList.get(i)
							.getImgUrl(); // 判断网址前缀 http 截图
					String[] imgUrlStr = imgUrl.split("\\|");
					if(StringUtils.isNotEmpty(imgUrl)){
						if (imgUrl.contains("htm")) {// 说明是快照
							for (int j = 0; j < imgUrlStr.length; j++) {
								if (imgUrlStr[j] != "") {
									imgUrlStr[j] = urlAdapterVar.getImgUrl()
											+  imgUrlStr[j];
								}

							}
							obj[7] = imgUrlStr;
						} else {
							String path = getBasePath();
							for (int j = 0; j < imgUrlStr.length; j++) {
								if (StringUtils.isNotEmpty(imgUrlStr[j])) {
									imgUrlStr[j] = urlAdapterVar.getLinkUrl()
											+ imgUrlStr[j];
								}

							}
							obj[7] = imgUrlStr;
						}
					}else{
						obj[7] = imgUrlStr;
					}
			

					obj[0] = i + 1;
					obj[1] = serviceItem;
					obj[2] = url;
					obj[3] = problemTypeName;
					obj[4] = problemDesc;
					obj[5] = parentUrl;
					obj[6] = scanDate;

					list.add(obj);
				}
			}

			ExportExcel.serviceUnuseExcel(fileName, title, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Description:根据服务不实用类型编码获取该类型对应的名称
	 * @author cuichx --- 2016-2-2下午1:25:51
	 * @return
	 */
	public String getSecurityServiceType(int code) {
		if (SecurityServiceType.GUIDE_LOSE.getCode() == code) {
			return SecurityServiceType.GUIDE_LOSE.getName();
		} else if (SecurityServiceType.GUIDE_NOT_CORRECT.getCode() == code) {
			return SecurityServiceType.GUIDE_NOT_CORRECT.getName();
		} else if (SecurityServiceType.ACCESSORY_NOT_UPLOAD.getCode() == code) {
			return SecurityServiceType.ACCESSORY_NOT_UPLOAD.getName();
		} else if (SecurityServiceType.ACCESSORY_UPLOAD_FAILURE.getCode() == code) {
			return SecurityServiceType.ACCESSORY_UPLOAD_FAILURE.getName();
		} else if (SecurityServiceType.OTHERS.getCode() == code) {
			return SecurityServiceType.OTHERS.getName();
		} else {
			return "";
		}
	}

	public String getMenuType() {
		return menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}
	
}
