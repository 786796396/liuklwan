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
import com.ucap.cloud_web.dto.ReportManageLogRequest;
import com.ucap.cloud_web.dto.SecurityResponseRequest;
import com.ucap.cloud_web.entity.ContractInfo;
import com.ucap.cloud_web.entity.DicChannel;
import com.ucap.cloud_web.entity.DicInteractProblem;
import com.ucap.cloud_web.entity.ReportManageLog;
import com.ucap.cloud_web.entity.SecurityResponse;
import com.ucap.cloud_web.entity.ServicePeriod;
import com.ucap.cloud_web.service.IDicChannelService;
import com.ucap.cloud_web.service.IDicInteractProblemService;
import com.ucap.cloud_web.service.IReportManageLogService;
import com.ucap.cloud_web.service.ISecurityResponseService;
import com.ucap.cloud_web.service.IServicePeriodService;
import com.ucap.cloud_web.shiro.Constants;
import com.ucap.cloud_web.shiro.ShiroUser;
import com.ucap.cloud_web.util.ExportExcel;

import net.sf.json.JSONObject;

/**
 * <p>
 * Description:
 * </p>
 * 互动回应差
 * <p>
 * 
 * @Package：com.ucap.cloud_web.action </p>
 *                                    <p>
 *                                    Title: SecurityResponseAction
 *                                    </p>
 *                                    <p>
 *                                    Company: 开普互联
 *                                    </p>
 *                                    <p>
 * @author：cuichx </p>
 *                <p>
 * @date：2015-11-18下午6:41:45 </p>
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class SecurityResponseAction extends BaseAction {
	@Autowired
	private UrlAdapterVar urlAdapterVar;
	@Autowired
	private ISecurityResponseService securityResponseServiceImpl;

	@Autowired
	private IReportManageLogService reportManageLogServiceImpl;

	@Autowired
	private IDicChannelService dicChannelServiceImpl;

	@Autowired
	private IDicInteractProblemService dicInteractProblemServiceImpl;

	@Autowired
	private DatabaseBizService databaseBizServiceImpl;

	@Autowired
	private IServicePeriodService servicePeriodServiceImpl;

	private String menuType;
	// log日志信息
	private static Log logger = LogFactory.getLog(SecurityResponseAction.class);

	/**
	 * @Description: 互动回应差页面
	 * @author cuichx --- 2015-11-18下午6:48:13
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
	public void responseChannelPie() {
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
					comPercent = StringUtils.formatDouble(2, 100.0 * outTotal
							/ daysTotal);// 已完成
					uncomPercent = StringUtils.formatDouble(2, 100.0 - 100.0
							* outTotal / daysTotal);// 未完成
				}
				pieResultMap.put("comPercent", comPercent);
				pieResultMap.put("uncomPercent", uncomPercent);
			}

			// 打印传给页面的数据
			logger.info("pieResultMap=" + pieResultMap);
			writerPrint(JSONObject.fromObject(pieResultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Description: 互动回应栏目开通情况统计
	 * @author cuichx --- 2015-11-18下午8:57:04
	 */
	public void responseChannelSum() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			// 从session中获取10位填报单位网站标识码
			String siteCode = getCurrentUserInfo().getChildSiteCode();
			if (StringUtils.isEmpty(siteCode)) {
				siteCode = getCurrentUserInfo().getSiteCode();
			}
			String serviceId = request.getParameter("serviceId");// 获取周期id

			logger.info("serviceId=" + serviceId);

			if (StringUtils.isEmpty(serviceId)) {
				resultMap.put("errorMsg", "不存在该服务周期对象！");
				writerPrint(JSONObject.fromObject(resultMap).toString());
			} else {

				int[] dicChannelIdArr = { 21, 22, 23, 24 };

				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("servicePeriodId", serviceId);
				paramMap.put("siteCode", siteCode);

				List<Object> resultList = new ArrayList<Object>();
				int total = 0;
				// 根据网站标识码和服务周期id查询互动回应差信息表
				List<SecurityResponseRequest> securityResponsesList = securityResponseServiceImpl
						.queryByMap(paramMap);
				if (securityResponsesList != null
						&& securityResponsesList.size() > 0) {
					for (int i = 0; i < dicChannelIdArr.length; i++) {
						Map<String, Object> map = new HashMap<String, Object>();

						for (SecurityResponseRequest securityResponseRequest : securityResponsesList) {
							String channelId = securityResponseRequest
									.getDicChannelId();
							if (Integer.valueOf(channelId) == dicChannelIdArr[i]) {
								DicChannel dicChannel = dicChannelServiceImpl
										.get(Integer
												.valueOf(securityResponseRequest
														.getDicChannelId()));
								map.put("dicChannel", dicChannel);
								map.put("channelName",
										dicChannel.getChannelName());// 栏目类型
								map.put("channelSum",
										securityResponseRequest.getChannelSum());// 栏目数量

								total += securityResponseRequest
										.getChannelSum();
							}
						}
						if (map.size() == 0) {
							DicChannel dicChannel = dicChannelServiceImpl
									.get(dicChannelIdArr[i]);
							map.put("dicChannel", dicChannel);
							map.put("channelName", dicChannel.getChannelName());// 栏目类型
							map.put("channelSum", "0");// 栏目数量
						}
						resultList.add(map);
					}
				} else {
					for (int i = 0; i < dicChannelIdArr.length; i++) {
						DicChannel dicChannel = dicChannelServiceImpl
								.get(dicChannelIdArr[i]);
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("dicChannel", dicChannel);
						map.put("channelName", dicChannel.getChannelName());// 栏目类型
						map.put("channelSum", "0");// 栏目数量

						resultList.add(map);
					}
				}
				resultMap.put("resultList", resultList);
				resultMap.put("total", total);
				logger.info(resultMap);
				writerPrint(JSONObject.fromObject(resultMap).toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("errorMsg", "栏目统计数据获取异常！");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		}
	}

	/**
	 * @Description: 获取监测周期
	 * @author Liukl --- 2016年12月13日17:56:45
	 */
	public void responseScanningPeriod() {
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
	 * @Description: 互动回应差检测结果--列表查询
	 * @author cuichx --- 2015-11-19下午2:30:39
	 */
	public void responseChannelList() {
		Map<String, Object> map_list = new HashMap<String, Object>();
		List<Object> returnList = new ArrayList<Object>();
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
				map_list.put("success", "other");
				writerPrint(JSONObject.fromObject(map_list).toString());
				return;
			}
			
			String servicePdId = request.getParameter("servicePdId");// 周期id
			String terms = request.getParameter("terms");
			// 封装页面参数
			SecurityResponseRequest responseRequest = new SecurityResponseRequest();
			/**
			 * 通过网站表示码、服务周期id 查询空白栏目详情表
			 */
			responseRequest.setSiteCode(siteCode);
			ServicePeriod servicePeriod = null;
			if (StringUtils.isNotEmpty(servicePdId)
					&& !servicePdId.equals("暂无周期！")) {
				responseRequest
						.setServicePeriodId(Integer.valueOf(servicePdId));
				// 使用周期id 去service_period表中获取status
				// 服务报告周期状态（-1：删除，0：未开始服务，1：服务中，2已完成服务
				servicePeriod = servicePeriodServiceImpl.get(Integer
						.valueOf(servicePdId));
			}
			if (StringUtils.isNotEmpty(terms)) {
				responseRequest.setChannelName(terms);
			}
			responseRequest.setPageSize(Integer.MAX_VALUE);
			List<QueryOrder> queryBlankList = new ArrayList<QueryOrder>();
			// 前台去掉周期，发现时间倒排序
			QueryOrder siteQueryOrder = new QueryOrder("scan_date",
					QueryOrderType.DESC);
			queryBlankList.add(siteQueryOrder);
			responseRequest.setQueryOrderList(queryBlankList);

			// 查询今天之前的数据
			String yesDate = DateUtils.getYesterdayStr();
			responseRequest.setScanDate(yesDate);

			int total = 0;
			List<SecurityResponse> securityResponseList = securityResponseServiceImpl
					.queryList(responseRequest);
			if (securityResponseList != null && securityResponseList.size() > 0) {
				for (int i = 0; i < securityResponseList.size(); i++) {
					SecurityResponse securityResponse = securityResponseList
							.get(i);
					Map<String, Object> map = new HashMap<String, Object>();

					// 栏目类型
					if (StringUtils.isNotEmpty(securityResponse
							.getDicChannelId() + "")) {
						DicChannel dicChannel = dicChannelServiceImpl
								.get(securityResponse.getDicChannelId());
						if (dicChannel != null) {
							map.put("dicChannelId", dicChannel.getChannelName());
						} else {
							map.put("dicChannelId", "");
						}
					} else {
						map.put("dicChannelId", "");
					}
					if (StringUtils.isNotEmpty(securityResponse
							.getChannelName())) {
						map.put("channelName",
								securityResponse.getChannelName());// 栏目名称
					}

					map.put("channelUrl", securityResponse.getChannelUrl());// 栏目url

					// 问题类型
					logger.info(">>>>>>>>>>>>>>>>>"
							+ securityResponse.getProblemTypeId()
							+ "<<<<<<<<<<<<<<<<<<<<<<");
					if (StringUtils.isNotEmpty(""
							+ securityResponse.getProblemTypeId())) {
						DicInteractProblem dic = dicInteractProblemServiceImpl
								.get(securityResponse.getProblemTypeId());
						if (dic != null) {
							map.put("problemTypeId", dic.getRemark());
						} else {
							map.put("problemTypeId", "");
						}
					} else {
						map.put("problemTypeId", "");
					}
					/*
					 * String
					 * problemTypeName=getSecurityResponseTypeName(securityResponse
					 * .getProblemTypeId());
					 * if(StringUtils.isNotEmpty(problemTypeName)){
					 * map.put("problemTypeId",problemTypeName ); }else{
					 * map.put("problemTypeId",""); }
					 */
					if (StringUtils.isNotEmpty(securityResponse
							.getProblemDesc())) {
						map.put("problemDesc",
								securityResponse.getProblemDesc());// 问题描述
					} else {
						map.put("problemDesc", "");// 问题描述
					}
					map.put("scanDate", securityResponse.getScanDate());
					String imgUrl = securityResponse.getImgUrl();
					if (StringUtils.isNotEmpty(imgUrl)) {
						if (imgUrl.startsWith("htm")) {
							map.put("imgUrl", urlAdapterVar.getImgUrl() + imgUrl);// 快照
						} else {
							map.put("imgUrl", imgUrl);// 问题截图
						}
					}else{
						map.put("imgUrl", "");
					}
					returnList.add(map);
				}
			}
			map_list.put("litImgUrl", urlAdapterVar.getLinkUrl());
			map_list.put("returnList", returnList);// 列表查询数据
			map_list.put("body", returnList);
			map_list.put("listSize", returnList.size());
			map_list.put("dataSize", returnList.size());
			map_list.put("total", total);// 空白栏目个数
			if (servicePeriod != null) {
				map_list.put("servicePeriodStatus", servicePeriod.getStatus());// 周期状态
			}
			map_list.put("success", "true");
			logger.info("map_list=" + map_list);
			writerPrint(JSONObject.fromObject(map_list).toString());
		} catch (Exception e) {
			e.printStackTrace();
			map_list.put("errorMsg", "获取互动回应差数据异常！");
			writerPrint(JSONObject.fromObject(map_list).toString());
		}

	}

	/**
	 * @Description: 互动回应差 excel导出
	 * @author cuichx --- 2015-11-25上午11:42:02
	 */
	public void responseChannelExcel() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String title = "互动回应差监测结果";
			ServicePeriod sp = null;
			// 获取页面参数
			String serviceId = request.getParameter("serviceId");// 服务周期id

			// 关键字
			String key = request.getParameter("key");
			// 从session中获取10位填报单位网站标识码
			String siteCode = getCurrentUserInfo().getChildSiteCode();
			if (StringUtils.isEmpty(siteCode)) {
				siteCode = getCurrentUserInfo().getSiteCode();
			}
			// 封装数据中查询的结果
			ArrayList<Object[]> list = new ArrayList<Object[]>();
			// excel标题
			Object[] obj1 = new Object[] { "序号", "栏目类型", "栏目名称", "问题类型",
					"问题描述", "问题发现时间", "截图" };
			list.add(obj1);
			// 内容保障问题-互动回应差监测结果(YYYY-MM-DD)
			String fileName ="";
			String userName = Constants.getCurrendUser().getUserName().trim();//获得当前登录站点名称
			if (StringUtils.isNotEmpty(serviceId)) {
				sp = servicePeriodServiceImpl.get(Integer.parseInt(serviceId));
				title = "互动回应差监测结果(" + sdf.format(sp.getStartDate()) + "至"
						+ sdf.format(sp.getEndDate()) + ")";
				fileName =	userName+"-内容保障问题-互动回应差监测结果("
						+ sdf.format(sp.getStartDate()) + "至"+ sdf.format(sp.getEndDate()) + ").xls";
			}else{
				fileName =	userName+"-内容保障问题-互动回应差监测结果("
						+ DateUtils.formatStandardDate(new Date()) + ").xls";
			}
			// dto对象封装页面参数
			SecurityResponseRequest scuResponseRequest = new SecurityResponseRequest();
			scuResponseRequest.setSiteCode(siteCode);
			scuResponseRequest.setPageSize(Integer.MAX_VALUE);
			if (StringUtils.isNotEmpty(serviceId)) {
				scuResponseRequest.setServicePeriodId(Integer
						.valueOf(serviceId));
			}
			if (StringUtils.isNotEmpty(key)) {
				scuResponseRequest.setChannelName(key);
			}

			// 查询今天之前的数据
			String yesDate = DateUtils.getYesterdayStr();
			scuResponseRequest.setScanDate(yesDate);

			List<SecurityResponse> scuResponseList = securityResponseServiceImpl
					.queryList(scuResponseRequest);
			if (scuResponseList != null && scuResponseList.size() > 0) {
				for (int i = 0; i < scuResponseList.size(); i++) {
					SecurityResponse scuResponse = scuResponseList.get(i);
					Object[] obj = new Object[7];
					String dicChannelId = dicChannelServiceImpl.get(
							scuResponse.getDicChannelId()).getChannelName();// 栏目类型
					String channelName = scuResponse.getChannelName();// 栏目名称
					if (StringUtils.isEmpty(channelName)) {
						channelName = "";
					}
					String problemTypeId = "";
					if (StringUtils.isNotEmpty(""
							+ scuResponse.getProblemTypeId())) {
						DicInteractProblem dic = dicInteractProblemServiceImpl
								.get(scuResponse.getProblemTypeId());
						if (dic != null) {
							problemTypeId = dic.getRemark();
						}
					}
					String problemDesc = scuResponse.getProblemDesc();// 问题描述
					String scanDate = scuResponse.getScanDate();// 问题发现时间
					String imgUrl = scuResponse
							.getImgUrl(); // 判断是否有http头

					obj[0] = i + 1;
					obj[1] = dicChannelId;
					obj[2] = channelName;
					obj[3] = problemTypeId;
					obj[4] = problemDesc;
					obj[5] = scanDate;
					String[] imgUrlStr = imgUrl.split("\\|");
					if (imgUrl.startsWith("htm")) {
						for (int j = 0; j < imgUrlStr.length; j++) {
							if (StringUtils.isNotEmpty(imgUrlStr[j])) {
								imgUrlStr[j] = urlAdapterVar.getImgUrl()
										+ imgUrlStr[j];
							}

						}
						obj[6] = imgUrlStr;
					} else {
						for (int j = 0; j < imgUrlStr.length; j++) {
							if (StringUtils.isNotEmpty(imgUrlStr[j])) {
								imgUrlStr[j] = urlAdapterVar.getLinkUrl()
										+ imgUrlStr[j];
							}

						}
						obj[6] = imgUrlStr;
					}
					list.add(obj);
				}
			}
			ExportExcel.responseChannelExcel(fileName, title, list);
			// }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getMenuType() {
		return menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

}
