package com.ucap.cloud_web.action;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.publics.util.page.QueryOrder;
import com.publics.util.page.QueryOrderType;
import com.publics.util.utils.DateUtils;
import com.ucap.cloud_web.UrlAdapterVar;
import com.ucap.cloud_web.dto.DatabaseInfoRequest;
import com.ucap.cloud_web.dto.DatabaseOrgInfoRequest;
import com.ucap.cloud_web.dto.DetectionPeroidCountRequest;
import com.ucap.cloud_web.dto.ReportCollectResultRequest;
import com.ucap.cloud_web.dto.ReportWordResultRequest;
import com.ucap.cloud_web.dto.ServicePeriodRequest;
import com.ucap.cloud_web.dto.SpotCheckInfoRequest;
import com.ucap.cloud_web.dto.SpotCheckNoticeRequest;
import com.ucap.cloud_web.dto.SpotCheckResultRequest;
import com.ucap.cloud_web.dto.SpotCheckScheduleRequest;
import com.ucap.cloud_web.entity.ContractInfo;
import com.ucap.cloud_web.entity.DatabaseInfo;
import com.ucap.cloud_web.entity.DatabaseOrgInfo;
import com.ucap.cloud_web.entity.DetectionPeroidCount;
import com.ucap.cloud_web.entity.ReportCollectResult;
import com.ucap.cloud_web.entity.ReportWordResult;
import com.ucap.cloud_web.entity.ServicePeriod;
import com.ucap.cloud_web.entity.SpotCheckInfo;
import com.ucap.cloud_web.entity.SpotCheckNotice;
import com.ucap.cloud_web.entity.SpotCheckResult;
import com.ucap.cloud_web.entity.SpotCheckSchedule;
import com.ucap.cloud_web.entity.SpotSecurityReport;
import com.ucap.cloud_web.service.IDatabaseInfoService;
import com.ucap.cloud_web.service.IDatabaseOrgInfoService;
import com.ucap.cloud_web.service.IDetectionPeroidCountService;
import com.ucap.cloud_web.service.IReportCollectResultService;
import com.ucap.cloud_web.service.IReportWordResultService;
import com.ucap.cloud_web.service.IServicePeriodService;
import com.ucap.cloud_web.service.ISpotCheckInfoService;
import com.ucap.cloud_web.service.ISpotCheckNoticeService;
import com.ucap.cloud_web.service.ISpotCheckResultService;
import com.ucap.cloud_web.service.ISpotCheckScheduleService;
import com.ucap.cloud_web.service.ISpotSecurityReportService;
import com.ucap.cloud_web.util.FileUtils;

import net.sf.json.JSONObject;

/**
 * <p>Description: </p>
 * <p>@Package：com.ucap.cloud_web.action </p>
 * <p>Title: SpotCheckScheduleAction </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：Liukl </p>
 * <p>@date：2016年12月7日13:02:47 </p>
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class SpotCheckScheduleAction extends BaseAction{
	@Autowired
	private IServicePeriodService servicePeriodServiceImpl;
	@Autowired
	private ISpotCheckScheduleService spotCheckScheduleServiceImpl;
	@Autowired
	private IReportCollectResultService reportCollectResultServiceImpl;
	@Autowired
	private UrlAdapterVar urlAdapterVar;
	@Autowired
	private ISpotCheckInfoService spotCheckInfoServiceImpl;
	@Autowired
	private IReportWordResultService reportWordResultServiceImpl;
	@Autowired
	private ISpotCheckNoticeService spotCheckNoticeServiceImpl;
	@Autowired
	private ISpotCheckResultService spotCheckResultServiceImpl;
	@Autowired
	private IDatabaseInfoService databaseInfoServiceImpl;
	@Autowired
	private IDetectionPeroidCountService detectionPeroidCountServiceImpl;
	@Autowired
	private IDatabaseOrgInfoService databaseOrgInfoServiceImpl;
//	@Autowired
//	private ICrmProductsService crmProductsServiceImpl;
	@Autowired
	private ISpotSecurityReportService spotSecurityReportServiceImpl;
	
	private String type;//1从抽查跳转    2全面检测
	private Integer checkSiteType;//网站类型 0全部  1本级部门  2下属单位
	private Integer checkResult;// 监测结果0全部  1合格  2不合格
	private Integer checkReport;//报告状态是否选中 0全部 1已完成   2未完成
	private Integer checkNotice;//通知整改是否选中 0全部 1已通知 2未通知
	private Integer checkRead;//是否已读  0全部  1已读未反馈  2未读未反馈
	private Integer dataType;//1站点数量    2完成报告数量    3反馈整改数量
	
	// 类型为抽查产品
//	private Integer[] productTypeArr = { CrmProductsType.CHECK.getCode() };

	private static Log logger = LogFactory.getLog(SpotCheckScheduleAction.class);
		
	/**
	 * @Description: 获取服务周期数据
	 * @author: liukl --- 2016-5-26下午5:14:31
	 * @return
	 * @throws Exception
	 */
	public String spotCheckDetails(){
		try {
			// 0 抽查
			if("0".equals(type)){
				 String batchNum=request.getParameter("batchNum");//批次
				 String groupNum=request.getParameter("groupNum");//组次
				 String flagAll = request.getParameter("flagAll");//抽查进度id
				 request.setAttribute("scheduleCheckId", flagAll);//抽查进度id放到详情页面
				 request.setAttribute("scheduleId", flagAll);
				//获取当前登录人的组织机构编码
//				String site_code_session = getCurrentSiteCode();
//				String siteCode=getCurrentSiteCode();
					String siteCode = request.getParameter("siteCode");
					String contractId = "";
					if(flagAll != null && !"".equals(flagAll)){
						if("".equals(contractId)){
							SpotCheckSchedule spotCheckSchedule = spotCheckScheduleServiceImpl.get(Integer.parseInt(flagAll));
							contractId = spotCheckSchedule.getContractInfoId();
						}
					}else{
						/**老合同信息**/
					List<ContractInfo> conList = getContractInfoList(siteCode,
							DateUtils.formatStandardDate(new Date()));
					if (conList != null && conList.size() > 0) {
						contractId = conList.get(0).getId() + "";
					}
					/** 新产品信息 **/
					// List<CrmProductsResponse> crmlist =
					// getCrmProductsList(siteCode, productTypeArr,
					// DateUtils.formatStandardDate(new Date()), null, null);
					// if (CollectionUtils.isNotEmpty(crmlist)) {
					// contractId = crmlist.get(0).getId() + "";
					// }
					}
					
					//if(conList!=null && conList.size()>0){
					if(!"".equals(contractId)){//
						//根据组织机构编码、合同id、批次、组次,查询抽查进度表
						SpotCheckScheduleRequest spotRequest=new SpotCheckScheduleRequest();
						spotRequest.setSiteCode(siteCode);
						//spotRequest.setContractInfoId(conList.get(0).getId()+"");
						spotRequest.setContractInfoId(contractId);
						spotRequest.setBatchNum(Integer.valueOf(batchNum));
						spotRequest.setGroupNum(Integer.valueOf(groupNum));
						
						
						
						List<SpotCheckSchedule> spotScheduleList=spotCheckScheduleServiceImpl.queryList(spotRequest);
						if(spotScheduleList!=null && spotScheduleList.size()>0){
							SpotCheckSchedule schedule=spotScheduleList.get(0);
							request.setAttribute("startDate", schedule.getStartDate());//监测开始时间（yyyy-mm-dd）
							request.setAttribute("endDate", schedule.getEndDate());//监测结束时间(yyyy-mm-dd)
							request.setAttribute("webCount", schedule.getSpotWebsiteNum());//站点总数
							request.setAttribute("groupNum", groupNum);
							request.setAttribute("batchNum", batchNum);
							request.setAttribute("state", schedule.getStatus());//状态（0：未启动，1：检查中，2：检查完成）
							request.setAttribute("taskName", schedule.getTaskName());//任务名称
							request.setAttribute("scheduleId", schedule.getId());//抽查进度表id
							if(schedule.getStatus()==0){
								request.setAttribute("status", "未启动");
							}else if(schedule.getStatus()==1){
								request.setAttribute("status", "检查中");
							}else{
								request.setAttribute("status", "检查完成");
							}
							
							//查询服务周期id
							ServicePeriodRequest servicePeriodRequest=new ServicePeriodRequest();
							servicePeriodRequest.setSpotCheckScheduleId( schedule.getId());
							servicePeriodRequest.setSiteCode(siteCode);
							List<ServicePeriod> servicePeriodList=servicePeriodServiceImpl.queryList(servicePeriodRequest);
							if(servicePeriodList.size()>0){
								ServicePeriod servicePeriod=servicePeriodList.get(0);
								Integer servicePeriodId=servicePeriod.getId();
								String servicePeriodNum=servicePeriod.getServicePeriodNum();
								request.setAttribute("servicePeriodNum", servicePeriodNum);
								request.setAttribute("servicePeriodId", servicePeriodId);
							}else{
								request.setAttribute("servicePeriodNum", "");
								request.setAttribute("servicePeriodId", 0);
							}
							
						}
					}
			}else{
				String servicePeriodId = request.getParameter("servicePeriodId");
				//获取服务周期信息
				ServicePeriod servicePeriod = servicePeriodServiceImpl.get(Integer.parseInt(servicePeriodId));
				String groupNum = request.getParameter("groupNum");
				String batchNum = request.getParameter("batchNum");
				String webCount = request.getParameter("webCount");
				request.setAttribute("startDate", DateUtils.formatStandardDate(servicePeriod.getStartDate()));
				request.setAttribute("endDate", DateUtils.formatStandardDate(servicePeriod.getEndDate()));
				request.setAttribute("webCount", webCount);
				request.setAttribute("servicePeriodNum", servicePeriod.getServicePeriodNum());
				request.setAttribute("servicePeriodId", servicePeriodId);
				request.setAttribute("groupNum", groupNum);
				request.setAttribute("batchNum", batchNum);
				request.setAttribute("state", servicePeriod.getStatus());
				request.setAttribute("xingqingId", servicePeriod.getStatus());
				
				if(servicePeriod.getStatus()==0){
					request.setAttribute("status", "未开始服务");
				}else if(servicePeriod.getStatus()==1){
					request.setAttribute("status", "服务中");
				}else{
					request.setAttribute("status", "已完成服务");
				}
				ReportCollectResultRequest reportCollectrequest=new ReportCollectResultRequest();
				String siteCode=getCurrentSiteCode();
				reportCollectrequest.setSiteCode(siteCode);
				reportCollectrequest.setServicePeriodId(servicePeriodId);
				List<ReportCollectResult> reportCollectResultList=reportCollectResultServiceImpl.queryList(reportCollectrequest);
				request.setAttribute("reportCollectResultSize", reportCollectResultList.size());
			}
			request.setAttribute("type", type);
			if(null == dataType){
				request.setAttribute("checkReport", 0);
				request.setAttribute("checkNotice", 0);
				request.setAttribute("checkRead", 0);
			}else{
				if(dataType==1){
					request.setAttribute("checkReport", 0);
					request.setAttribute("checkNotice", 0);
					request.setAttribute("checkRead", 0);
				}else if(dataType==2){
					request.setAttribute("checkReport", 1);
					request.setAttribute("checkNotice", 0);
					request.setAttribute("checkRead", 0);
				}else if(dataType==3){
					request.setAttribute("checkReport", 0);
					request.setAttribute("checkNotice", 1);
					request.setAttribute("checkRead", 0);
				}else if(dataType==4){
					request.setAttribute("checkReport", 0);
					request.setAttribute("checkNotice", 0);
					request.setAttribute("checkRead", 1);
				}
			}
			
		} catch (Exception e) {
		}
		
		
		return "success";
	}

	/**老合同信息**/

	/**
	 * @Description: 获取抽查批次 通过report_org_code
	 * @author liukl --- 2016年12月7日19:10:23
	 */
	
	public void getScheduleList(){
		String loginOrgCode = getCurrentUserInfo().getSiteCode();//登录组织单位编码
		//封装返回数据
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			String key=request.getParameter("key");
			logger.info("getSchedule key:" + key);
			String siteCode = getCurrentSiteCode();// 获取当前登陆组织单位
			int spotSum = 0;//抽查总数
			int spotNum = 0;//已抽查次数
			int remainNum = 0;//剩余可抽查次数
			List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
			//封装批次集合  主要用于前台区分批次和组的关系
			List<Integer> batchNumList=new ArrayList<Integer>();
			List<ContractInfo> conList = getContractInfoList(siteCode, DateUtils.formatStandardDate(new Date()));
			List<ContractInfo> conLists = getContractInfoList(siteCode, null);
			logger.info("conList size:" + conList.size() + "  siteCode:" + siteCode + "new Date()"
					+ DateUtils.formatStandardDateTime(new Date()));
			if (conLists != null && conLists.size() > 0) {
				if (conList != null && conList.size() > 0) {
					
					ContractInfo contractInfo = conList.get(0);
					if (StringUtils.isNotEmpty(contractInfo.getContractCode())) {
						//当前服务中的合同号
						resultMap.put("curredContractCode", contractInfo.getContractCode());
					} else {
						// 当前服务中的临时合同号
						resultMap.put("curredContractCode", contractInfo.getContractCodeTemp());
					}
				
					SpotCheckInfoRequest spotCheckInfoRequest = new SpotCheckInfoRequest();
					spotCheckInfoRequest.setSiteCode(siteCode);
					spotCheckInfoRequest.setContractInfoId(conList.get(0).getId());
					logger.info("conList id:" + conList.get(0).getId());
					// 抽查服务统计表
					List<SpotCheckInfo> queryInfo = spotCheckInfoServiceImpl.queryList(spotCheckInfoRequest);
					for (SpotCheckInfo spotCheckInfo : queryInfo) {
						spotSum = spotCheckInfo.getSpotSum();
						spotNum= spotCheckInfo.getSpotNum();
					}
				}else{
					spotSum = 0;
					spotNum= 0;
				}
				//查询抽查进度表
				SpotCheckScheduleRequest spotCheckScheduleRequest = new SpotCheckScheduleRequest();
				spotCheckScheduleRequest.setPageSize(Integer.MAX_VALUE);
				// =============
				spotCheckScheduleRequest.setReportOrgCode(loginOrgCode);	
				
				if(StringUtils.isNotEmpty(key)){
					spotCheckScheduleRequest.setTaskName(key);
				}
				List<SpotCheckSchedule> spotCheckList = spotCheckScheduleServiceImpl
						.spotCheckReportUpList(spotCheckScheduleRequest);
				DateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
				if(spotCheckList!=null && spotCheckList.size()>0){

					for (int i = 0; i < spotCheckList.size(); i++) {
						SpotCheckSchedule spotCheckSchedule = spotCheckList.get(i);
						HashMap<String, Object> map = new HashMap<String, Object>();
						//获取汇报单位名称
						DatabaseOrgInfoRequest orgInfoRequest = new DatabaseOrgInfoRequest();
						if(StringUtils.isNotEmpty(spotCheckSchedule.getSiteCode())){
							orgInfoRequest.setStieCode(spotCheckSchedule.getSiteCode());
						}
						List<DatabaseOrgInfo> orgInfoList = databaseOrgInfoServiceImpl.queryList(orgInfoRequest);
						if(orgInfoList.size()>0){
							map.put("reportName", orgInfoList.get(0).getName());//汇报单位名称
							map.put("reportCode", orgInfoList.get(0).getSiteCode());//汇报单位编码
						}
						
						map.put("scheduleId", spotCheckSchedule.getId());//抽查进度表id
						map.put("siteCode", spotCheckSchedule.getSiteCode());//网站标识码
						//任务名称
						if(StringUtils.isNotBlank(spotCheckSchedule.getTaskName())){
							map.put("taskName", spotCheckSchedule.getTaskName());
						}else{
							map.put("taskName", "");
						}
						map.put("modifyTime", format.format(spotCheckSchedule.getModifyTime()));// 修改时间
						map.put("affixUrl", spotCheckSchedule.getAffixUrl());// 附件url
						map.put("affixName", spotCheckSchedule.getAffixName());// 附件名
						map.put("upId", spotCheckSchedule.getUpId());// 附件id

						map.put("type", spotCheckSchedule.getType());// 有无抽查进度状态
						// 监测开始日期
						if (StringUtils.isNotEmpty(spotCheckSchedule.getStartDate())) {
							map.put("dateStart", DateUtils
									.formatDate(DateUtils.parseStandardDate(spotCheckSchedule.getStartDate())));
						} else {
							map.put("dateStart", "--");
						}
						//监测结束日期
						if (StringUtils.isNotEmpty(spotCheckSchedule.getEndDate())) {
							map.put("endStart", DateUtils.formatDate(DateUtils.parseStandardDate(spotCheckSchedule.getEndDate())));
						} else {
							map.put("endStart", "--");
						}
						//监测状态
						map.put("state", spotCheckSchedule.getStatus());
						map.put("batchNum", spotCheckSchedule.getBatchNum());
						map.put("groupNum", spotCheckSchedule.getGroupNum());
						map.put("webSum", spotCheckSchedule.getSpotWebsiteNum());
						//查询服务周期id
						//循环遍历批次集合，如果批次集合中存在该批次，不添加；否则将批次添加到集合里面
						if(batchNumList!=null && batchNumList.size()>0){
							//获取批次
							int batchNum=spotCheckSchedule.getBatchNum();
							boolean existFlag=false;
							for(int j=0;j<batchNumList.size();j++){
								if(batchNum==batchNumList.get(j)){
									existFlag=true;
								}
							}
							//批次集合中不存在该数据，将该批次添加到批次集合中
							if(!existFlag){
								batchNumList.add(spotCheckSchedule.getBatchNum());
							}
						}else{
							batchNumList.add(spotCheckSchedule.getBatchNum());
						}
						returnList.add(map);
					}
				}
			}
			
			//add by Na.Y 20160914,剩余可抽查次数
			remainNum = spotSum-spotNum;
			if(remainNum<0){
				remainNum=0;
			}
				
				resultMap.put("spotSum", spotSum);
				resultMap.put("spotNum", spotNum);
				resultMap.put("remainNum", remainNum);
				resultMap.put("returnList", returnList);
				resultMap.put("batchNumList", batchNumList);
				writerPrint(JSONObject.fromObject(resultMap).toString());
			
		} catch (Exception e) {
			e.printStackTrace();
				resultMap.put("errorMsg", "获取抽查数据异常");
		}
		
	}

	/**
	 * @Description: 抽查汇报附件下载
	 * @author liukl --- 2017年3月22日19:10:23
	 */
	public void downLoadReportFile() {
		try {
			String reportUpId = request.getParameter("reportUpId");
			if (StringUtils.isEmpty(reportUpId)) {
				writerPrint("文件不存在");
				return;
			}
			SpotSecurityReport spotSecurityReport = spotSecurityReportServiceImpl.get(Integer.parseInt(reportUpId));
			if (spotSecurityReport != null && StringUtils.isNotEmpty(spotSecurityReport.getAffixUrl())) {
				String upUrl = urlAdapterVar.getWebPaths() + spotSecurityReport.getAffixUrl();
				File file = new File(upUrl);
				if (file.exists()) {
					String aliasName = spotSecurityReport.getAffixName();
					aliasName = FileUtils.getAliasName(aliasName, upUrl);
					FileUtils.outPutFile(response, upUrl, aliasName);
				} else {
					writerPrint("文件不存在");
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			writerPrint("文件下载异常");
			e.printStackTrace();
		}
	}

	/** 新产品信息 **/
	/**
	 * @Description: 获取抽查批次 通过report_org_code
	 * @author liukl --- 2016年12月7日19:10:23
	 */

	// public void getScheduleList(){
	// String loginOrgCode = getCurrentUserInfo().getSiteCode();//登录组织单位编码
	// //封装返回数据
	// Map<String, Object> resultMap = new HashMap<String, Object>();
	// try {
	// String key=request.getParameter("key");
	// String datePd = request.getParameter("datePd");
	// logger.info("getSchedule key:" + key + " datePd:" + datePd);
	// String siteCode = getCurrentSiteCode();// 获取当前登陆组织单位
	// int spotSum = 0;//抽查总数
	// int spotNum = 0;//已抽查次数
	// int remainNum = 0;//剩余可抽查次数
	// List<Map<String, Object>> returnList = new ArrayList<Map<String,
	// Object>>();
	// //封装批次集合 主要用于前台区分批次和组的关系
	// List<Integer> batchNumList=new ArrayList<Integer>();
	// List<CrmProductsResponse> currentCrmlist = getCrmProductsList(siteCode,
	// productTypeArr,
	// DateUtils.formatStandardDate(new Date()), null, null);
	// List<CrmProductsResponse> crmlist = getCrmProductsList(siteCode,
	// productTypeArr, null, null, null);
	// logger.info("conList size:" + currentCrmlist.size() + " siteCode:" +
	// siteCode + "new Date()"
	// + DateUtils.formatStandardDateTime(new Date()));
	// if (CollectionUtils.isNotEmpty(crmlist)) {
	// if (CollectionUtils.isNotEmpty(currentCrmlist)) {
	//
	// CrmProductsResponse crm = currentCrmlist.get(0);
	// if (StringUtils.isNotEmpty(crm.getProductCode())) {
	// //当前服务中的合同号
	// resultMap.put("curredContractCode", crm.getProductCode());
	// }
	//
	// SpotCheckInfoRequest spotCheckInfoRequest = new SpotCheckInfoRequest();
	// spotCheckInfoRequest.setSiteCode(siteCode);
	// spotCheckInfoRequest.setContractInfoId(currentCrmlist.get(0).getId());
	// logger.info("conList id:" + currentCrmlist.get(0).getId());
	// // 抽查服务统计表
	// List<SpotCheckInfo> queryInfo =
	// spotCheckInfoServiceImpl.queryList(spotCheckInfoRequest);
	// for (SpotCheckInfo spotCheckInfo : queryInfo) {
	// spotSum = spotCheckInfo.getSpotSum();
	// spotNum= spotCheckInfo.getSpotNum();
	// }
	// }else{
	// spotSum = 0;
	// spotNum= 0;
	// }
	// //查询抽查进度表
	// SpotCheckScheduleRequest spotCheckScheduleRequest = new
	// SpotCheckScheduleRequest();
	// spotCheckScheduleRequest.setPageSize(Integer.MAX_VALUE);
	// spotCheckScheduleRequest.setReportOrgCode(loginOrgCode);
	//
	// if(StringUtils.isNotEmpty(key)){
	// spotCheckScheduleRequest.setTaskName(key);
	// }
	// if (StringUtils.isNotEmpty(datePd)) {
	// spotCheckScheduleRequest.setEndTime(DateUtils.formatStandardDateTime(new
	// Date()));
	// spotCheckScheduleRequest.setBeginTime(DateUtils.getNextDay(new Date(),
	// -Integer.valueOf(datePd)));
	// }
	// // 设置排序 先按批次排序 再按组次排序
	// List<QueryOrder> queryOrderList = new ArrayList<QueryOrder>();
	// QueryOrder siteQueryOrderBatch = new QueryOrder("batch_num",
	// QueryOrderType.DESC);
	// QueryOrder siteQueryOrderGroup = new QueryOrder("group_num",
	// QueryOrderType.DESC);
	// queryOrderList.add(siteQueryOrderBatch);
	// queryOrderList.add(siteQueryOrderGroup);
	// spotCheckScheduleRequest.setQueryOrderList(queryOrderList);
	//
	// List<SpotCheckSchedule> spotCheckList = spotCheckScheduleServiceImpl
	// .queryList(spotCheckScheduleRequest);
	// if(spotCheckList!=null && spotCheckList.size()>0){
	//
	// for (int i = 0; i < spotCheckList.size(); i++) {
	// SpotCheckSchedule spotCheckSchedule = spotCheckList.get(i);
	// HashMap<String, Object> map = new HashMap<String, Object>();
	// //获取汇报单位名称
	// DatabaseOrgInfoRequest orgInfoRequest = new DatabaseOrgInfoRequest();
	// if(StringUtils.isNotEmpty(spotCheckSchedule.getSiteCode())){
	// orgInfoRequest.setStieCode(spotCheckSchedule.getSiteCode());
	// }
	// List<DatabaseOrgInfo> orgInfoList =
	// databaseOrgInfoServiceImpl.queryList(orgInfoRequest);
	// if(orgInfoList.size()>0){
	// map.put("reportName", orgInfoList.get(0).getName());//汇报单位名称
	// map.put("reportCode", orgInfoList.get(0).getSiteCode());//汇报单位编码
	// }
	//
	// map.put("scheduleId", spotCheckSchedule.getId());//抽查进度表id
	// map.put("siteCode", spotCheckSchedule.getSiteCode());//网站标识码
	// //任务名称
	// if(StringUtils.isNotBlank(spotCheckSchedule.getTaskName())){
	// map.put("taskName", spotCheckSchedule.getTaskName());
	// }else{
	// map.put("taskName", "");
	// }
	//
	// if (StringUtils.isNotEmpty(spotCheckSchedule.getContractInfoId())) {
	// CrmProducts crm = crmProductsServiceImpl
	// .get(Integer.valueOf(spotCheckSchedule.getContractInfoId()));
	// if (StringUtils.isNotEmpty(crm.getProductCode())) {
	// // 正式合同号
	// map.put("contractCode", crm.getProductCode());
	// }
	// }
	// //监测开始日期
	// map.put("dateStart",
	// DateUtils.formatDate(DateUtils.parseStandardDate(spotCheckSchedule.getStartDate())));
	// //监测结束日期
	// map.put("endStart",
	// DateUtils.formatDate(DateUtils.parseStandardDate(spotCheckSchedule.getEndDate())));
	// //监测状态
	// map.put("state", spotCheckSchedule.getStatus());
	// map.put("batchNum", spotCheckSchedule.getBatchNum());
	// map.put("groupNum", spotCheckSchedule.getGroupNum());
	// map.put("webSum", spotCheckSchedule.getSpotWebsiteNum());
	// //查询服务周期id
	// ServicePeriodRequest servicePeriodRequest = new ServicePeriodRequest();
	// servicePeriodRequest.setSpotCheckScheduleId(spotCheckSchedule.getId());
	// servicePeriodRequest.setSiteCode(siteCode);
	// List<ServicePeriod> servicePeriodList = servicePeriodServiceImpl
	// .queryList(servicePeriodRequest);
	// if (servicePeriodList.size() > 0) {
	// Integer servicePeriodId = servicePeriodList.get(0).getId();
	//
	// // 完成报告数量
	// ReportWordResultRequest reportWordResultRequest = new
	// ReportWordResultRequest();
	// reportWordResultRequest.setServicePeriodId(servicePeriodId);
	// reportWordResultRequest.setPageNo(0);
	// reportWordResultRequest.setPageSize(Integer.MAX_VALUE);
	// reportWordResultRequest.setGroupBy("site_code");
	//
	// List<ReportWordResult> wordList = reportWordResultServiceImpl
	// .queryList(reportWordResultRequest);
	// Integer successReportWordNum = wordList.size();
	// map.put("successReportWordNum", successReportWordNum);
	//
	// // 反馈整改数量
	// SpotCheckNoticeRequest spotCheckNoticeRequest = new
	// SpotCheckNoticeRequest();
	// spotCheckNoticeRequest.setServicePeriodId(servicePeriodId);
	// spotCheckNoticeRequest.setCheckReportResults(0);
	// spotCheckNoticeRequest.setPageNo(0);
	// spotCheckNoticeRequest.setPageSize(Integer.MAX_VALUE);
	// spotCheckNoticeRequest.setGroupBy("site_code");
	// List<SpotCheckNotice> SpotCheckNoticeList = spotCheckNoticeServiceImpl
	// .queryList(spotCheckNoticeRequest);
	// map.put("checkReportResultNum", SpotCheckNoticeList.size());
	// spotCheckNoticeRequest.setCheckReportResults(null);
	// spotCheckNoticeRequest.setIsRead(1);
	// List<SpotCheckNotice> IsReadNoticeList = spotCheckNoticeServiceImpl
	// .queryList(spotCheckNoticeRequest);
	// map.put("IsReadNoticeNum", IsReadNoticeList.size());
	// } else {
	// map.put("successReportWordNum", 0);
	// map.put("checkReportResultNum", 0);
	// map.put("IsReadNoticeNum", 0);
	// }
	//
	// // map.put("contractInfoId",conList.get(0).getId());//未使用，注释
	// //循环遍历批次集合，如果批次集合中存在该批次，不添加；否则将批次添加到集合里面
	// if(batchNumList!=null && batchNumList.size()>0){
	// //获取批次
	// int batchNum=spotCheckSchedule.getBatchNum();
	// boolean existFlag=false;
	// for(int j=0;j<batchNumList.size();j++){
	// if(batchNum==batchNumList.get(j)){
	// existFlag=true;
	// }
	// }
	// //批次集合中不存在该数据，将该批次添加到批次集合中
	// if(!existFlag){
	// batchNumList.add(spotCheckSchedule.getBatchNum());
	// }
	// }else{
	// batchNumList.add(spotCheckSchedule.getBatchNum());
	// }
	// returnList.add(map);
	// }
	// }
	// }
	//
	// //add by Na.Y 20160914,剩余可抽查次数
	// remainNum = spotSum-spotNum;
	// if(remainNum<0){
	// remainNum=0;
	// }
	//
	// resultMap.put("spotSum", spotSum);
	// resultMap.put("spotNum", spotNum);
	// resultMap.put("remainNum", remainNum);
	// resultMap.put("returnList", returnList);
	// resultMap.put("batchNumList", batchNumList);
	// writerPrint(JSONObject.fromObject(resultMap).toString());
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	// resultMap.put("errorMsg", "获取抽查数据异常");
	// }
	//
	//
	// }
	
	
	/**批次详情数据请求
	 * @Description: 抽查站点详情表--获取抽查结果数据
	 * @author liukl --- 2016-12-9下午4:33:26
	 */
	
	public void getSpotResult(){
		//封装返回结果
		Map<String, Object> resultMap=new HashMap<String, Object>();
		String scheduleId=request.getParameter("scheduleId");//抽查进度表id
		String servicePeriodId = request.getParameter("servicePeriodId");
//		String orgSiteCode=getCurrentSiteCode();//当前登录组织机构编码
		try {
			int index=0;
			if(StringUtils.isNotEmpty(scheduleId)){
				//根据组织机构编码和抽查进度表id,查询抽查结果表
				SpotCheckResultRequest resultRequest=new SpotCheckResultRequest();
//				resultRequest.setOrgSiteCode(orgSiteCode);
				resultRequest.setSpotCheckSchedule(Integer.valueOf(scheduleId));
				resultRequest.setPageSize(Integer.MAX_VALUE);
				//默认 siteCode 正序
				List<QueryOrder> queryList=new ArrayList<QueryOrder>();
				QueryOrder queryOrder=new QueryOrder("site_code",QueryOrderType.ASC);
				queryList.add(queryOrder);
				resultRequest.setQueryOrderList(queryList);
				
				ServicePeriodRequest serviceRequest = new ServicePeriodRequest();
				serviceRequest.setSpotCheckScheduleId(Integer.parseInt(scheduleId));
//				serviceRequest.setSiteCode(orgSiteCode);
				List<ServicePeriod> sPeriodList = servicePeriodServiceImpl.queryList(serviceRequest);
				
				List<SpotCheckResult> resultList=spotCheckResultServiceImpl.queryList(resultRequest);
				
				if(resultList!=null && resultList.size()>0){
					List<Map<String, Object>> returnList=new ArrayList<Map<String,Object>>();
					for (int i = 0; i < resultList.size(); i++) {
						Map<String, Object> map =new HashMap<String, Object>();
						SpotCheckResult result=resultList.get(i);
						
						String siteCode="";//网站标识码
						if(StringUtils.isNotEmpty(result.getSiteCode())){
							siteCode=result.getSiteCode();
						}
						map.put("siteCode",siteCode);
						
						String siteName="";//网站名称
						if(StringUtils.isNotEmpty(result.getSiteName())){
							siteName=result.getSiteName();
						}
						map.put("siteName", siteName);
						
						//是否门户（0：否，1：是）
						int isorganizational=result.getIsorganizational();
						map.put("isorganizational", isorganizational);
						
						String url="";//首页url
						if(StringUtils.isNotEmpty(result.getUrl())){
							url=result.getUrl();
						}
						map.put("url", url);
						
						String province="";//省/部
						if(StringUtils.isNotEmpty(result.getProvince())){
							province=result.getProvince();
						}
						map.put("province", province);
						
						String city="";//市
						if(StringUtils.isNotEmpty(result.getCity())){
							city=result.getCity();
						}
						map.put("city", city);
						
						String county="";//县
						if(StringUtils.isNotEmpty(result.getCounty())){
							county=result.getCounty();
						}
						map.put("county", county);
						
						//抽查状态（-1:未提交，0：未启动，1:检查中，2：扫描完成 ，3：报告完成）
						map.put("resultState", result.getState());
						
						//检查结果（1：合格，0：单项否决,2:未检查）
						map.put("checkResult", result.getCheckResult());
						
						//检查结果（0：待审核，1：通过,2:不通过）
						map.put("checkReportResult", result.getCheckReportResult());
						//判断是否生成报告
						if(sPeriodList.size() >0){
							ReportWordResultRequest req = new ReportWordResultRequest();
							req.setSiteCode(siteCode);
							req.setServicePeriodId(sPeriodList.get(0).getId());
							List<ReportWordResult> count = reportWordResultServiceImpl.queryList(req);
							if(count.size() >0){
								map.put("isDown", 1);
							}else{
								map.put("isDown", 0);
							}
						}else{
							map.put("isDown", 0);
						}
						
						if(null ==checkReport){
							checkReport=0;
						}
						if(null == checkNotice){
							checkNotice=0;
						}
						if(null == checkRead){
							checkRead=0;
						}
						//获取整改通知状态
						SpotCheckNoticeRequest spotCheckNoticeRequest = new SpotCheckNoticeRequest();
						spotCheckNoticeRequest.setSiteCode(siteCode);
						spotCheckNoticeRequest.setServicePeriodId(Integer.parseInt(servicePeriodId));
						List<SpotCheckNotice> spotCheckNoticeList = spotCheckNoticeServiceImpl.queryList(spotCheckNoticeRequest);
						Integer isRead=0;
						if(spotCheckNoticeList != null && spotCheckNoticeList.size()>0){
							SpotCheckNotice spotCheckNotice=spotCheckNoticeList.get(0);
							isRead=spotCheckNotice.getIsRead();
							if(null == isRead){
								isRead=1;
							}
						}else{
							isRead=0;
						}
						if(checkReport==1 && checkNotice==1 && checkRead==1){
							//两个都选中
							
							if(sPeriodList.size() >0){
								ReportWordResultRequest req = new ReportWordResultRequest();
								req.setSiteCode(siteCode);
								req.setServicePeriodId(sPeriodList.get(0).getId());
								List<ReportWordResult> count = reportWordResultServiceImpl.queryList(req);
								if(count.size() >0 && result.getCheckReportResult()!=0 && isRead==1){
									index++;
									map.put("isHide", 0);
								}else{
									map.put("isHide", 1);
								}
							}else{
								map.put("isHide", 1);
							}
							
							
							
						}else if(checkReport==0 && checkNotice==0 && checkRead==0){
								//如果页面没有选中复选框  直接显示全部
								index++;
								map.put("isHide", 0);
						}else{
						 if(checkReport==1 ){
								//已完成选中  已通知整改未选中
								if(sPeriodList.size() >0){
									ReportWordResultRequest req = new ReportWordResultRequest();
									req.setSiteCode(siteCode);
									req.setServicePeriodId(sPeriodList.get(0).getId());
									List<ReportWordResult> count = reportWordResultServiceImpl.queryList(req);
									if(count.size() >0 ){
										index++;
										map.put("isHide", 0);
									}else{
										map.put("isHide", 1);
									}
								}else{
									map.put("isHide", 1);
								}
							}
						 
						 if(checkNotice==1){
								//已完成没选中  已通知整改选中
								if(result.getCheckReportResult()!=0){
									index++;
									map.put("isHide", 0);
								}else{
									map.put("isHide", 1);
								}
								
							}
						 if(checkRead==1){
							 if(isRead==1){
									//已读显示
								 index++;
									map.put("isHide", 0);
							}else{
									map.put("isHide", 1);
						   }
						 }
						 
						}
						
						/*2016/06/23 网站考评->站点数明细 网站名称添加鼠标悬浮显示站点基本信息；展现形式参照本级部门--START-- sunjy*/
						DatabaseInfoRequest databaseInfoRequest = new DatabaseInfoRequest();
						databaseInfoRequest.setSiteCode(result.getSiteCode());
						List<DatabaseInfo> datalist = databaseInfoServiceImpl.queryList(databaseInfoRequest);
						if (!CollectionUtils.isEmpty(datalist)) {
							DatabaseInfo databaseInfo = datalist.get(0);
							String director = databaseInfo.getDirector();
							map.put("siteManageUnit", director);
							String address = databaseInfo.getAddress();
							map.put("officeAddress", address);
							String principalName = databaseInfo.getPrincipalName();
							map.put("relationName", principalName);
							String telephone = databaseInfo.getTelephone();
							map.put("relationCellphone", telephone);
							String mobile = databaseInfo.getMobile();
							map.put("relationPhone", mobile);
							String email = databaseInfo.getEmail();
							map.put("relationEmail", email);

							String linkmanName = databaseInfo.getLinkmanName();
							map.put("linkman", linkmanName);
							String telephone2 = databaseInfo.getTelephone2();
							map.put("linkmanCellphone", telephone2);

							String mobile2 = databaseInfo.getMobile2();
							map.put("linkmanPhone", mobile2);
							String email2 = databaseInfo.getEmail2();
							map.put("linkmanEmail", email2);
						}
						//查询详情页各项指标数据
						DetectionPeroidCountRequest detectionPeroidCountRequest=new DetectionPeroidCountRequest();
						if(StringUtils.isNotEmpty(servicePeriodId) ){
							detectionPeroidCountRequest.setServicePeroidId(Integer.valueOf(servicePeriodId));
						}else{
							detectionPeroidCountRequest.setServicePeroidId(-1);
						}
						detectionPeroidCountRequest.setSiteCode(siteCode);
						List<DetectionPeroidCount> detectionPeroidList=detectionPeroidCountServiceImpl.queryList(detectionPeroidCountRequest);
						if(detectionPeroidList.size()>0){
							for(DetectionPeroidCount detectionPeroidCount :detectionPeroidList){
								detectionPeroidCount.setIsRead(isRead);
								map.put("detectionPeroidCount", detectionPeroidCount);
								}
							returnList.add(map);
						}
//						else{
//							map.put("detectionPeroidCount",new DetectionPeroidCount());
//						}
						
						
					}
					resultMap.put("returnList", returnList);
					if(returnList.size()==0){
						resultMap.put("listSize", 0);
					}else{
						resultMap.put("listSize", index);
					}
					
					writerPrint(JSONObject.fromObject(resultMap).toString());
				}else{
//					if(site_code_exp.indexOf(site_code_session)>=0){
//						resultMap.put("errorMsg", "不存在考评结果数据");
//					}else{
//						resultMap.put("errorMsg", "不存在抽查结果数据");
//					}
					resultMap.put("listSize", 0);
					writerPrint(JSONObject.fromObject(resultMap).toString());
				}
			}else{
//				if(site_code_exp.indexOf(site_code_session)>=0){
//					resultMap.put("errorMsg", "考评进度表参数不能为空");
//				}else{
//					resultMap.put("errorMsg", "抽查进度表参数不能为空");
//				}
				resultMap.put("listSize", 0);
				writerPrint(JSONObject.fromObject(resultMap).toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
//			if(site_code_exp.indexOf(site_code_session)>=0){
//				resultMap.put("errorMsg", "获取考评结果数据异常");
//			}else{
//				resultMap.put("errorMsg", "获取抽查结果数据异常");
//			}
			resultMap.put("listSize", 0);
			writerPrint(JSONObject.fromObject(resultMap).toString());
		}
	}
	
	
	


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public Integer getCheckSiteType() {
		return checkSiteType;
	}


	public void setCheckSiteType(Integer checkSiteType) {
		this.checkSiteType = checkSiteType;
	}


	public Integer getCheckResult() {
		return checkResult;
	}


	public void setCheckResult(Integer checkResult) {
		this.checkResult = checkResult;
	}


	public Integer getCheckReport() {
		return checkReport;
	}


	public void setCheckReport(Integer checkReport) {
		this.checkReport = checkReport;
	}


	public Integer getCheckNotice() {
		return checkNotice;
	}


	public void setCheckNotice(Integer checkNotice) {
		this.checkNotice = checkNotice;
	}


	public Integer getCheckRead() {
		return checkRead;
	}


	public void setCheckRead(Integer checkRead) {
		this.checkRead = checkRead;
	}


	public Integer getDataType() {
		return dataType;
	}


	public void setDataType(Integer dataType) {
		this.dataType = dataType;
	}
	
	
}
