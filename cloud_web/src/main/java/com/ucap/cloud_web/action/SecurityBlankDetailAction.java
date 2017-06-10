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
import com.ucap.cloud_web.dto.SecurityBlankDetailRequest;
import com.ucap.cloud_web.dto.SecurityBlankInfoRequest;
import com.ucap.cloud_web.entity.ContractInfo;
import com.ucap.cloud_web.entity.ReportManageLog;
import com.ucap.cloud_web.entity.SecurityBlankDetail;
import com.ucap.cloud_web.entity.ServicePeriod;
import com.ucap.cloud_web.service.IReportManageLogService;
import com.ucap.cloud_web.service.ISecurityBlankDetailService;
import com.ucap.cloud_web.service.ISecurityBlankInfoService;
import com.ucap.cloud_web.service.IServicePeriodService;
import com.ucap.cloud_web.shiro.Constants;
import com.ucap.cloud_web.util.CommonUtils;
import com.ucap.cloud_web.util.ExportExcel;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * <p>
 * Description:
 * </p>
 * 空白栏目页面逻辑处理
 * <p>
 * 
 * @Package：com.ucap.cloud_web.action </p>
 *                                    <p>
 *                                    Title: SecurityBlankDetailAction
 *                                    </p>
 *                                    <p>
 *                                    Company: 开普互联
 *                                    </p>
 *                                    <p>
 * @author：cuichx </p>
 *                <p>
 * @date：2015-11-17下午5:15:37 </p>
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class SecurityBlankDetailAction extends BaseAction {
	@Autowired
	private IReportManageLogService reportManageLogServiceImpl;

	@Autowired
	private ISecurityBlankDetailService securityBlankDetailServiceImpl;

	@Autowired
	private ISecurityBlankInfoService securityBlankInfoServiceImpl;

	@Autowired
	private UrlAdapterVar urlAdapterVar;

	@Autowired
	private IServicePeriodService servicePeriodServiceImpl;

	@Autowired
	private DatabaseBizService databaseBizServiceImpl;

	private static Log logger = LogFactory
			.getLog(SecurityBlankDetailAction.class);

	private String menuType;

	/**
	 * @Description:首页面
	 * @author cuichx --- 2015-11-17下午5:16:33
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
	 * @Description: 获取监测周期
	 * @author Liukl --- 2016年12月13日17:56:45
	 */
	public void blankDetailMonitoringPeriod() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
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
	 * @Description: 本期检测进度--饼状图
	 * @author cuichx --- 2015-11-17下午5:28:30
	 */
	public void blankDetailPie() {

		Map<String, Object> pieResultMap = new HashMap<String, Object>();
		try {
			String siteCode = getCurrentUserInfo().getChildSiteCode();
			if (StringUtils.isEmpty(siteCode)) {
				siteCode = getCurrentUserInfo().getSiteCode();
			}
			// 获取当前周期
			/**老合同信息**/
			ServicePeriod servicePeriod = getCurrentPeriod();
			/**新产品信息**/
			// Integer[] productTypeArr = { CrmProductsType.DETECTION.getCode()
			// };
			// ServicePeriod servicePeriod =
			// getCurrentServicePeriod(productTypeArr);
			if (servicePeriod != null && !"".equals(servicePeriod)) {
				String startTime = DateUtils.formatStandardDate(servicePeriod
						.getStartDate());// 本期服务开始时间
				String endTime = DateUtils.formatStandardDate(servicePeriod
						.getEndDate());// 本期服务结束时间
				String nowTime = DateUtils.formatStandardDate(new Date());// 当前时间

				// 本期总天数
				int daysTotal = DateUtils.getDaysBetween2Days(
						DateUtils.parseStandardDate(startTime),
						DateUtils.parseStandardDate(endTime));
				// 本期已经过去的天数
				int outTotal = DateUtils.getDaysBetween2Days(
						DateUtils.parseStandardDate(startTime),
						DateUtils.parseStandardDate(nowTime));

				int compareDay = DateUtils.getDaysBetween2Days(
						DateUtils.parseStandardDate(endTime),
						DateUtils.parseStandardDate(nowTime));

				String comPercent = "";
				String uncomPercent = "";

				logger.info("compareDay=" + compareDay);
				// 如果到时间点未生成报告，完成进度为99%，未完成1%
				if (compareDay >= 0) {
					// 根据服务周期表id和网站表示码查询报告管理页面，如果数据不存在，说明报告还未生成，反而，报告已经生成
					ReportManageLogRequest reportManageLogRequest = new ReportManageLogRequest();
					reportManageLogRequest.setSiteCode(siteCode);
					reportManageLogRequest.setServicePeriodId(servicePeriod
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

				// 打印传给页面的数据
				logger.info("pieResultMap=" + pieResultMap);
				writerPrint(JSONObject.fromObject(pieResultMap).toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Description:空白栏目趋势折线图
	 * @author cuichx --- 2015-11-17下午6:40:36
	 */
	public void blankDetailLine() {

		List<Object> list = new ArrayList<Object>();
		try {
			String siteCode = getCurrentUserInfo().getChildSiteCode();
			if (StringUtils.isEmpty(siteCode)) {
				siteCode = getCurrentUserInfo().getSiteCode();
			}

			logger.info("linkAllUnuse siteCode:" + siteCode);

			// 获取所有的周期id（已完成周期+当前周期）
			/**老合同信息**/
			List<ServicePeriod> serviceList = getServicePeriodList(0);
			/**新产品信息**/
			// Integer[] productTypeArr = { CrmProductsType.DETECTION.getCode()
			// };
			// List<ServicePeriod> serviceList = getAllServicePeriodList(0,
			// productTypeArr);

			if (serviceList != null && serviceList.size() > 0) {
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("siteCode", siteCode);
				paramMap.put("serviceList", serviceList);
				paramMap.put("group", "groupBy");

				List<SecurityBlankInfoRequest> securityBlankInfoList = securityBlankInfoServiceImpl
						.queryByMap(paramMap);
				if (securityBlankInfoList != null
						&& securityBlankInfoList.size() > 0) {
					for (int i = 0; i < securityBlankInfoList.size(); i++) {
						SecurityBlankInfoRequest securityBlankInfoRequest = securityBlankInfoList
								.get(i);
						Map<String, Object> map = new HashMap<String, Object>();
						int blankNum = securityBlankInfoRequest.getBlankNum();// 每个月的空白栏目个数

						map.put("retuenPeriodNum", i + 1);
						map.put("blankNum", blankNum);
						list.add(map);
					}
				} else {
					logger.info("当前周期为第一周期，不存在上期数据！");
				}
			}
			logger.info(list);
			writerPrint(JSONArray.fromObject(list).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Description: 分页列表查询
	 * @author cuichx --- 2015-11-17下午9:14:56
	 */
	public void blankDetailPage() {
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
			
			String terms = request.getParameter("terms");
			String servicePdId = request.getParameter("servicePdId"); // 周期id
			/**
			 * 通过网站标识码、服务周期id 查询空白栏目详情表
			 */
			ServicePeriod servicePeriod = null;
			SecurityBlankDetailRequest detailRequest = new SecurityBlankDetailRequest();
			if (StringUtils.isNotEmpty(servicePdId)
					&& !servicePdId.equals("暂无周期！")) {
				detailRequest.setServicePeriodId(Integer.valueOf(servicePdId));
				// 使用周期id 去service_period表中获取status
				// 服务报告周期状态（-1：删除，0：未开始服务，1：服务中，2已完成服务
				servicePeriod = servicePeriodServiceImpl.get(Integer
						.valueOf(servicePdId));
			}
			if (StringUtils.isNotEmpty(terms)) {
				detailRequest.setChannelName(terms);
			}
			detailRequest.setSiteCode(siteCode);
			detailRequest.setPageSize(Integer.MAX_VALUE);
			List<QueryOrder> queryBlankList = new ArrayList<QueryOrder>();
			// 前台去掉周期，发现时间倒排序
			QueryOrder siteQueryOrder = new QueryOrder("scan_date",
					QueryOrderType.DESC);
			queryBlankList.add(siteQueryOrder);
			detailRequest.setQueryOrderList(queryBlankList);

			// 查询今天之前的数据
//			String yesDate = DateUtils.getYesterdayStr();
//			detailRequest.setScanDate(yesDate);

			int total = 0;
			List<SecurityBlankDetail> blankList = new ArrayList<SecurityBlankDetail>();
			blankList = securityBlankDetailServiceImpl.queryList(detailRequest);
			if (blankList != null && blankList.size() > 0) {
				for (int i = 0; i < blankList.size(); i++) {
					total = total + 1;
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("dataNumber", i + 1);// 序号
					map.put("channelName", blankList.get(i).getChannelName());// 栏目名称
					map.put("url", blankList.get(i).getUrl());// 栏目url
					map.put("path", blankList.get(i).getPath());// 路径

					String imgStr = blankList.get(i).getImgUrl();
					if(StringUtils.isNotEmpty(imgStr)){
						if (imgStr.contains("htm")) {// 快照
							map.put("imgUrl", urlAdapterVar.getImgUrl() + imgStr);// 快照
						} else {
							map.put("imgUrl", imgStr);// 截图
						}
					}else{
						map.put("imgUrl", "");
					}
					
					map.put("problemDesc", blankList.get(i).getProblemDesc());
					map.put("scanDate", blankList.get(i).getScanDate());
					returnList.add(map);
				}
				map_list.put("returnList", returnList);// 列表查询数据
				map_list.put("body", returnList);// 列表查询数据
				map_list.put("total", total);// 空白栏目个数
			} else {
				map_list.put("returnList", "");// 列表查询数据
				map_list.put("total", 0);// 空白栏目个数
			}
			map_list.put("litImgUrl", urlAdapterVar.getLinkUrl());
			// 将服务周期报告扔到页面
			if (servicePeriod != null) {
				map_list.put("servicePeriodStatus", servicePeriod.getStatus());
			}
			logger.info("map_list=" + map_list);
			map_list.put("success", "true");
			map_list.put("listSize", total);
			writerPrint(JSONObject.fromObject(map_list).toString());
		} catch (Exception e) {
			e.printStackTrace();
			map_list.put("errorMsg", "查询空白栏目详情数据异常！");
			writerPrint(JSONObject.fromObject(map_list).toString());
		}
	}

	/**
	 * @Description: 空白栏目 excel导出
	 * @author cuichx --- 2015-11-25上午11:42:02
	 */
	public void blankDetailExcel() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			// 获取页面参数
			String serviceId = request.getParameter("serviceId");// 周期id
			String key = request.getParameter("key");// 关键字查询
			ServicePeriod sp = null;
			String title = "空白栏目监测结果";
			// 从session中获取10位填报单位网站标识码
			String siteCode = getCurrentUserInfo().getChildSiteCode();
			if (StringUtils.isEmpty(siteCode)) {
				siteCode = getCurrentUserInfo().getSiteCode();
			}

			// 封装数据中查询的结果
			ArrayList<Object[]> list = new ArrayList<Object[]>();
			// excel标题
			Object[] obj1 = new Object[] { "序号", "栏目名称", "栏目URL", "路径", "问题描述",
					"问题发现时间", "截图" };
			list.add(obj1);
			// 内容保障问题-空白栏目监测结果(YYYY-MM-DD)
			String fileName = "";
			String userName = Constants.getCurrendUser().getUserName().trim();//获得当前登录站点名称
			if (StringUtils.isNotEmpty(serviceId)) {
				sp = servicePeriodServiceImpl.get(Integer.parseInt(serviceId));
				title = "空白栏目监测结果(" + sdf.format(sp.getStartDate())
						+ "至" + sdf.format(sp.getEndDate()) + ")";
				fileName =userName+"-内容保障问题-空白栏目监测结果("
						+ sdf.format(sp.getStartDate())+ "至" + sdf.format(sp.getEndDate()) + ").xls";
			}else{
				fileName =userName+"-内容保障问题-空白栏目监测结果("
						+ DateUtils.formatStandardDate(new Date()) + ").xls";
			}

			// dto对象封装页面参数
			SecurityBlankDetailRequest scuDetailRequest = new SecurityBlankDetailRequest();
			if (StringUtils.isNotEmpty(serviceId)) {
				scuDetailRequest.setServicePeriodId(Integer.valueOf(serviceId));
			}
			// 用于关键字查询
			if (StringUtils.isNotEmpty(key)) {
				scuDetailRequest.setChannelName(key);
			}
			scuDetailRequest.setSiteCode(siteCode);
			scuDetailRequest.setPageSize(Integer.MAX_VALUE);
			// 查询今天之前的数据
			String yesDate = DateUtils.getYesterdayStr();
			scuDetailRequest.setScanDate(yesDate);

			// 根据服务周期id和网站标识码查询空白栏目详情表
			List<SecurityBlankDetail> scuDetailList = securityBlankDetailServiceImpl
					.queryList(scuDetailRequest);
			if (scuDetailList != null && scuDetailList.size() > 0) {
				for (int i = 0; i < scuDetailList.size(); i++) {
					SecurityBlankDetail scubBlankDetail = scuDetailList.get(i);
					Object[] obj = new Object[7];
					obj[0] = i + 1;

					String channelName = scubBlankDetail.getChannelName();// 栏目名称
					if (StringUtils.isNotEmpty(channelName)) {
						obj[1] = channelName;
					} else {
						obj[1] = "";
					}
					String url = CommonUtils.setHttpUrl(scubBlankDetail
							.getUrl()); // 判断是否有http头
					obj[2] = url;

					String path = scubBlankDetail.getPath();// 路径
					obj[3] = path;
					obj[4] = scubBlankDetail.getProblemDesc();// 问题描述
					obj[5] = scubBlankDetail.getScanDate();// 问题发现时间
					String imgUrl = scubBlankDetail.getImgUrl(); // 判断是否有http头
					String[] imgUrlStr = imgUrl.split("\\|");
					if(StringUtils.isNotEmpty(imgUrl)){
						if (imgUrl.contains("htm")) {// 快照
							// obj[5]= urlAdapterVar.getImgUrl()+imgUrl;//快照
							for (int j = 0; j < imgUrlStr.length; j++) {
								if (imgUrlStr[j] != "") {
									imgUrlStr[j] = urlAdapterVar.getImgUrl()
											+ imgUrlStr[j];
								}

							}
							obj[6] = imgUrlStr;
						} else {
							// obj[5]=getBasePath()+"/jsp/onlineReport/cutImgs.jsp?imgUrl="+imgUrl+
							// "&litImgUrl="+urlAdapterVar.getLinkUrl();//截图

							for (int j = 0; j < imgUrlStr.length; j++) {
								if (imgUrlStr[j] != "") {
									imgUrlStr[j] = urlAdapterVar.getLinkUrl()
											+ imgUrlStr[j];
								}

							}
							obj[6] = imgUrlStr;
						}
					}else{
						obj[6]=imgUrlStr;
					}
					
					list.add(obj);
				}
			}
			ExportExcel.blankDetailExcel(fileName, title, list);

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
