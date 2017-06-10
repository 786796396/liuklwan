package com.ucap.cloud_web.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

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
import com.ucap.cloud_web.common.DicUtils;
import com.ucap.cloud_web.dto.DatabaseInfoRequest;
import com.ucap.cloud_web.dto.DatabaseTreeInfoRequest;
import com.ucap.cloud_web.dto.DetectionPeroidCountRequest;
import com.ucap.cloud_web.dto.RelationsPeriodRequest;
import com.ucap.cloud_web.dto.ReportCollectResultRequest;
import com.ucap.cloud_web.dto.ReportWordResultRequest;
import com.ucap.cloud_web.dto.ServicePeriodRequest;
import com.ucap.cloud_web.dto.SpotCheckNoticeRequest;
import com.ucap.cloud_web.dto.SpotCheckScheduleRequest;
import com.ucap.cloud_web.entity.ContractInfo;
import com.ucap.cloud_web.entity.DatabaseInfo;
import com.ucap.cloud_web.entity.DatabaseTreeInfo;
import com.ucap.cloud_web.entity.DetectionPeroidCount;
import com.ucap.cloud_web.entity.DicItem;
import com.ucap.cloud_web.entity.RelationsPeriod;
import com.ucap.cloud_web.entity.ReportCollectResult;
import com.ucap.cloud_web.entity.ReportWordResult;
import com.ucap.cloud_web.entity.ServicePeriod;
import com.ucap.cloud_web.entity.SpotCheckNotice;
import com.ucap.cloud_web.entity.SpotCheckSchedule;
import com.ucap.cloud_web.service.IDatabaseInfoService;
import com.ucap.cloud_web.service.IDatabaseTreeInfoService;
import com.ucap.cloud_web.service.IDetectionPeroidCountService;
import com.ucap.cloud_web.service.IRelationsPeriodService;
import com.ucap.cloud_web.service.IReportCollectResultService;
import com.ucap.cloud_web.service.IReportWordResultService;
import com.ucap.cloud_web.service.IServicePeriodService;
import com.ucap.cloud_web.service.ISpotCheckNoticeService;
import com.ucap.cloud_web.service.ISpotCheckScheduleService;
import com.ucap.cloud_web.util.CommonUtils;
import com.ucap.cloud_web.util.ExportExcel;

/**
 * <p>Description: </p>
 * <p>@Package：com.ucap.cloud_web.action </p>
 * <p>Title: servicePeriodAction </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：yangshuai </p>
 * <p>@date：2016-5-26下午5:11:22 </p>
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class ServicePeriodAction extends BaseAction{

	@Autowired
	private IServicePeriodService servicePeriodServiceImpl;
	@Autowired
	private IDatabaseInfoService databaseInfoServiceImpl;
	@Autowired
	private IRelationsPeriodService relationsPeriodServiceImpl;
	
	@Autowired
	private IReportWordResultService reportWordResultServiceImpl;
	
	@Autowired
	private ISpotCheckNoticeService spotCheckNoticeServiceImpl;
	@Autowired
	private IDetectionPeroidCountService detectionPeroidCountServiceImpl;
	@Autowired
	private ISpotCheckScheduleService spotCheckScheduleServiceImpl;
	@Autowired
	private IReportCollectResultService reportCollectResultServiceImpl;
	@Autowired
	private DicUtils dicUtils;
	@Autowired
	private UrlAdapterVar urlAdapterVar;
	@Autowired
	private IDatabaseTreeInfoService databaseTreeInfoServiceImpl;

	private static Log logger = LogFactory.getLog(ServicePeriodAction.class);
	private String type;//1从抽查跳转    2全面检测
	private Integer checkSiteType;//网站类型 0全部  1本级门户  2本级部门 3下属单位
	private Integer checkResult;// 监测结果0全部  1合格  2不合格
	private Integer checkReport;//报告状态是否选中 0全部 1已完成   2未完成
	private Integer checkNotice;//通知整改是否选中 0全部 1已通知 2未通知
	private Integer checkRead;//是否已读  0全部  1已读未反馈  2未读未反馈
	private Integer dataType;//1站点数量    2完成报告数量    3反馈整改数量
	public String servicePeriod(){
		String type = request.getParameter("type"); // 区分组织与填报
		request.setAttribute("type", type); // 网站类别
		return "success";
	}
	
	/**
	 * @Description: 获取服务周期数据
	 * @author: yangshuai --- 2016-5-26下午5:14:31
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	public String getServicePeriod(){

		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			String siteCode = "";
			String type = "";
			String key="";
			String datePd="";
			JSONObject json = getJSONObject();

			if (json != null) {
				type = json.getString("type");// 区分 1 组织与 2填报
				key = json.getString("key");//关键字
				siteCode = json.getString("siteCode");// sitecode
				datePd = json.getString("datePd");//创建时间
			}

			/**老合同信息**/
			List<ContractInfo> crmlist = getContractInfoList(siteCode, null);
			if (CollectionUtils.isEmpty(crmlist)) {
				resultMap.put("NoContract", "no");
			}else{
			/**新产品信息**/
//			Integer[] productTypeArr = { CrmProductsType.DETECTION.getCode() };
//			List<CrmProductsResponse> crmlist = getCrmProductsList(siteCode, productTypeArr, null, null, null);
			resultMap.put("NoContract", "yes");
			if (CollectionUtils.isNotEmpty(crmlist)) {
				//根据合同id|查询条件查询周期任务
				Map<String, Object> paramMap = new HashMap<String, Object>();
				ServicePeriodRequest servicePeriodRequest = new ServicePeriodRequest();
				servicePeriodRequest.setPageSize(Integer.MAX_VALUE);
				List<QueryOrder> queryOrderList=new ArrayList<QueryOrder>();
				QueryOrder siteQueryOrder1=new QueryOrder("contract_info_id",QueryOrderType.DESC);
				queryOrderList.add(siteQueryOrder1);
				QueryOrder siteQueryOrder2=new QueryOrder("create_time",QueryOrderType.DESC);
				queryOrderList.add(siteQueryOrder2);
				servicePeriodRequest.setQueryOrderList(queryOrderList);
				servicePeriodRequest.setContractList(crmlist);
//				servicePeriodRequest.setCrmProductsList(crmlist);
				servicePeriodRequest.setPeriodStatus(-1);
				if(StringUtils.isNotEmpty(key)){
					servicePeriodRequest.setServicePeriodNum(key);
					paramMap.put("servicePeriodNum", key);
				}
				if(StringUtils.isNotEmpty(datePd)){
					servicePeriodRequest.setEndTime(DateUtils.formatStandardDateTime(new Date()));
					servicePeriodRequest.setBeginTime(DateUtils.getNextDay(new Date(), -Integer.valueOf(datePd)));
					paramMap.put("endTime", DateUtils.formatStandardDateTime(new Date()));
					paramMap.put("beginTime", DateUtils.getNextDay(new Date(), -Integer.valueOf(datePd)));
				}
				//add by　Na.Y 20160918 全面监测只展示高级版
				servicePeriodRequest.setComboI(4);
				List<ServicePeriod> servicePeriodLists = servicePeriodServiceImpl.queryList(servicePeriodRequest);
				List<Map<String, Object>> servicePeriodList = new ArrayList<Map<String, Object>>();
			
				paramMap.put("contractList", crmlist);
				paramMap.put("periodStatus", -1);
				//add by　Na.Y 20160918 全面监测只展示高级版
				paramMap.put("comboI", 4);
				List<ServicePeriodRequest> stList = servicePeriodServiceImpl.queryByGroup(paramMap);
				logger.info("任务周期批次"+stList.size());
				if(servicePeriodLists != null && servicePeriodLists.size()>0){
					for (ServicePeriod servicePeriod : servicePeriodLists) {
						HashMap<String, Object> map = new HashMap<String, Object>();
						//if(servicePeriod.getStatus()!=-1){
							for (ServicePeriodRequest spq : stList) {
								if(servicePeriod.getContractInfoId() == spq.getContractInfoId()){
									map.put("rowspanNum", spq.getPeriodNum());
								}
							}
							map.put("id", servicePeriod.getId());//周期id
							map.put("siteCode", servicePeriod.getSiteCode());//网站标识码
							map.put("startDate", DateUtils.formatStandardDate(servicePeriod.getStartDate()));
							map.put("endDate", DateUtils.formatStandardDate(servicePeriod.getEndDate()));
							map.put("createTime", DateUtils.formatStandardDate(servicePeriod.getCreateTime()));
							map.put("modifyTime", DateUtils.formatStandardDate(servicePeriod.getModifyTime()));
							map.put("comboId", servicePeriod.getComboId());
							map.put("contractInfoId", servicePeriod.getContractInfoId());
							RelationsPeriodRequest relationsPeriodRequest = new RelationsPeriodRequest();
							relationsPeriodRequest.setServicePeriodId(servicePeriod.getId());
							int webCount = relationsPeriodServiceImpl.queryCount(relationsPeriodRequest);
							map.put("webCount", webCount);
							map.put("status", servicePeriod.getStatus());
							map.put("spotCheckScheduleId", servicePeriod.getSpotCheckScheduleId());
							String servicePeriodNum=servicePeriod.getServicePeriodNum();
							map.put("servicePeriodNum", servicePeriodNum);
							
							
								//完成报告数量
								ReportWordResultRequest reportWordResultRequest=new ReportWordResultRequest();
								reportWordResultRequest.setServicePeriodId(servicePeriod.getId());
								reportWordResultRequest.setPageNo(0);
								reportWordResultRequest.setPageSize(Integer.MAX_VALUE);
								reportWordResultRequest.setGroupBy("site_code");
								List<ReportWordResult> wordList=reportWordResultServiceImpl.queryList(reportWordResultRequest);
								Integer successReportWordNum=wordList.size();
								map.put("successReportWordNum", successReportWordNum);
								
								
								//反馈整改数量
								SpotCheckNoticeRequest spotCheckNoticeRequest=new SpotCheckNoticeRequest();
								spotCheckNoticeRequest.setServicePeriodId(servicePeriod.getId());
								spotCheckNoticeRequest.setCheckReportResults(0);
								spotCheckNoticeRequest.setPageNo(0);
								spotCheckNoticeRequest.setPageSize(Integer.MAX_VALUE);
								spotCheckNoticeRequest.setGroupBy("site_code");
								List<SpotCheckNotice> SpotCheckNoticeList=spotCheckNoticeServiceImpl.queryList(spotCheckNoticeRequest);	
								map.put("checkReportResultNum", SpotCheckNoticeList.size());
								spotCheckNoticeRequest.setCheckReportResults(null);
								spotCheckNoticeRequest.setIsRead(1);
								List<SpotCheckNotice> IsReadNoticeList=spotCheckNoticeServiceImpl.queryList(spotCheckNoticeRequest);
								map.put("IsReadNoticeNum", IsReadNoticeList.size());
							servicePeriodList.add(map);
						//}
					}
				}
				resultMap.put("servicePeriodList", servicePeriodList);
				resultMap.put("stList", stList.size());
			}else{
				resultMap.put("errorMsg", "合同已过期或暂无合同数据");
			}
			}
		
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("errorMsg", "获取数据异常");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		}
		return null;
	}
	
	public String websiteList(){
		try {
			//加载网站类别
			List<DicItem> dicList = dicUtils.getDictList("siteType");
			request.setAttribute("dicList", dicList); // 网站类别
			// 0 抽查
			if("0".equals(type)){
				 String batchNum=request.getParameter("batchNum");//批次
				 String groupNum=request.getParameter("groupNum");//组次
				 String flagAll = request.getParameter("flagAll");//抽查进度id
//				 String servicePeriodId = request.getParameter("servicePeriodId");//周期id
				 request.setAttribute("scheduleCheckId", flagAll);//抽查进度id放到详情页面
//				 request.setAttribute("servicePeriodId", flagAll);//周期id放到详情页面
				//获取当前登录人的组织机构编码
				String siteCode=getCurrentSiteCode();
					
					String contractId = "";
					if(flagAll != null && !"".equals(flagAll)){
						if("".equals(contractId)){
							SpotCheckSchedule spotCheckSchedule = spotCheckScheduleServiceImpl.get(Integer.parseInt(flagAll));
							contractId = spotCheckSchedule.getContractInfoId();
						}
				} else { // 取当前抽查任务
					/** 老合同信息 **/
					List<ContractInfo> conList = getContractInfoList(siteCode,
							DateUtils.formatStandardDate(new Date()));
					if (conList != null && conList.size() > 0) {
						contractId = conList.get(0).getId() + "";
					}
					/** 新产品信息 **/
					// Integer[] productTypeArr = {
					// CrmProductsType.CHECK.getCode() };
					// List<CrmProductsResponse> crmlist =
					// getCrmProductsList(siteCode, productTypeArr,
					// DateUtils.formatStandardDate(new Date()), null, null);
					// if (crmlist != null && crmlist.size() > 0) {
					// contractId = crmlist.get(0).getId() + "";
					// }
					}
					
					if(!"".equals(contractId)){//
						//根据组织机构编码、合同id、批次、组次,查询抽查进度表
						SpotCheckScheduleRequest spotRequest=new SpotCheckScheduleRequest();
						spotRequest.setSiteCode(siteCode);
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
								request.setAttribute("endDate", DateUtils.formatStandardDate(servicePeriod.getEndDate()));//周期结束时间
								String date = dicUtils.getValue("spot_check_time");  //控制合格时间
								request.setAttribute("quDate",date);
								ReportCollectResultRequest reportCollectrequest=new ReportCollectResultRequest();
								reportCollectrequest.setSiteCode(siteCode);
								reportCollectrequest.setServicePeriodId(String.valueOf(servicePeriodId));
								List<ReportCollectResult> reportCollectResultList=reportCollectResultServiceImpl.queryList(reportCollectrequest);
								request.setAttribute("reportCollectResultSize", reportCollectResultList.size());
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
				String siteCode=getCurrentUserInfo().getSiteCode();
				reportCollectrequest.setSiteCode(siteCode);
				reportCollectrequest.setServicePeriodId(servicePeriodId);
				List<ReportCollectResult> reportCollectResultList=reportCollectResultServiceImpl.queryList(reportCollectrequest);
				request.setAttribute("reportCollectResultSize", reportCollectResultList.size());
				String date = dicUtils.getValue("qualified_state_time");  //控制合格时间
				request.setAttribute("quDate",date);
			}
			String finishRe = request.getParameter("finishRe");//网站抽查用来判断已读 已报告
			request.setAttribute("finishRe",finishRe);
			
			request.setAttribute("type", type);
			if(null == dataType){
				request.setAttribute("checkReport", 0);
				request.setAttribute("checkNotice", 0);
				request.setAttribute("checkRead", 0);
				request.setAttribute("reportType", 0);
				request.setAttribute("noticeType", 0);
				request.setAttribute("readType", 0);
			}else{
				if(dataType==1){
					request.setAttribute("checkReport", 0);
					request.setAttribute("checkNotice", 0);
					request.setAttribute("checkRead", 0);
					request.setAttribute("reportType", 0);
					request.setAttribute("noticeType", 0);
					request.setAttribute("readType", 0);
				}else if(dataType==2){
					request.setAttribute("checkReport", 1);
					request.setAttribute("checkNotice", 0);
					request.setAttribute("checkRead", 0);
					request.setAttribute("reportType", 1);
					request.setAttribute("noticeType", 0);
					request.setAttribute("readType", 0);
				}else if(dataType==3){
					request.setAttribute("checkReport", 0);
					request.setAttribute("checkNotice", 1);
					request.setAttribute("checkRead", 0);
					request.setAttribute("noticeType", 1);
					request.setAttribute("reportType", 0);
					request.setAttribute("readType", 0);
				}else if(dataType==4){
					request.setAttribute("checkReport", 0);
					request.setAttribute("checkNotice", 0);
					request.setAttribute("checkRead", 1);
					request.setAttribute("readType", 1);
					request.setAttribute("reportType", 0);
					request.setAttribute("noticeType", 0);
				}
			}
			
		} catch (Exception e) {
		}
		return "success";
	}
	
	/**
	 * @Description: 站点数据导出
	 * @author: yangshuai --- 2016-5-27下午8:49:57
	 * @return
	 */
	public void excelWebsiteList(){
		String servicePeriodId = request.getParameter("servicePeriodId");//抽查进度表id
		try {
			
			ArrayList<Object[]> list = new ArrayList<Object[]>();
			Object[] obj1 = new Object[] {"序号","网站标识码", "网站名称", "网站地址","省","市","县", "首页不连通率","网站死链个数","网站不更新","空白栏目个数",
					"互动回应问题","服务不实用问题","严重错误","其他问题","报告状态"};
			list.add(obj1);
			String fileName = "全面监测-站点明细(" + DateUtils.formatStandardDate(new Date()) + ").xls";
			String title = "监测问题统计";
			
			if("0".equals(type)){
				fileName = "抽查-结果明细(" + DateUtils.formatStandardDate(new Date()) + ").xls";
			}else{
				fileName = "全面监测-结果明细(" + DateUtils.formatStandardDate(new Date()) + ").xls";
			}
			
			//查询 详情数据表
			DetectionPeroidCountRequest detectionPeroidCountRequest=new DetectionPeroidCountRequest();
			if(StringUtils.isNotEmpty(servicePeriodId) ){
				detectionPeroidCountRequest.setServicePeroidId(Integer.valueOf(servicePeriodId));
			}else{
				detectionPeroidCountRequest.setServicePeroidId(-1);
			}
			detectionPeroidCountRequest.setPageSize(Integer.MAX_VALUE);
			List<DetectionPeroidCount> detectionPeroidList=detectionPeroidCountServiceImpl.queryList(detectionPeroidCountRequest);
			
			if(!CollectionUtils.isEmpty(detectionPeroidList)){
				//汇总
				Object[] objSum = new Object[16];
				//总站点数
				int sumNum =detectionPeroidList.size();
				//不连通率和
				double sumConnErrorProportion=0d;
				int sumLinkNum=0;
				int sumSecurityUpdate=0;
				int sumSecurityBlank=0;
				int sumSecurityResponse=0;
				int sumSerice=0;
				int sumSerious=0;
				int sumOthers=0;
				
				for (int i = 0; i < detectionPeroidList.size(); i++) {
					DetectionPeroidCount detectionPeroidCount = detectionPeroidList.get(i);
					DatabaseInfo databaseInfo = getDatabaseInfo(detectionPeroidCount.getSiteCode());
					if(null==databaseInfo){
						sumNum-=1;
						continue;
					}
					Object[] obj = new Object[16];
					obj[0] = i + 1;
					String siteCode=databaseInfo.getSiteCode();//网站标识码
					if(StringUtils.isNotEmpty(databaseInfo.getSiteCode())){
						siteCode=databaseInfo.getSiteCode();
					}
					obj[1]=siteCode;
					
					String siteName="";//网站名称
					if(StringUtils.isNotEmpty(databaseInfo.getName())){
						siteName=databaseInfo.getName();
					}
					obj[2]=siteName;
					
					String homeUrl="";//网站网址
					if(StringUtils.isNotEmpty(databaseInfo.getJumpUrl())){
						homeUrl=CommonUtils.setHttpUrl(databaseInfo.getJumpUrl());  // 判断网址前缀 http
					}else if(StringUtils.isNotEmpty(databaseInfo.getUrl())){
						homeUrl=CommonUtils.setHttpUrl(databaseInfo.getUrl());  // 判断网址前缀 http
					}
					obj[3]=homeUrl;
					if(StringUtils.isNotEmpty(databaseInfo.getProvince())){
						obj[4]=databaseInfo.getProvince();
					}else{
						obj[4]="";
					}
					if(StringUtils.isNotEmpty(databaseInfo.getCity())){
						obj[5]=databaseInfo.getCity();
					}else{
						obj[5]="";
					}
					if(StringUtils.isNotEmpty(databaseInfo.getCounty())){
						obj[6]=databaseInfo.getCounty();
					}else{
						obj[6]="";
					}
					String connErrorProportion="";//首页不连通率
					if(StringUtils.isNotEmpty(detectionPeroidCount.getConnErrorProportion())){
						connErrorProportion=detectionPeroidCount.getConnErrorProportion();
						sumConnErrorProportion+=Double.parseDouble(connErrorProportion);
					}
					obj[7]=connErrorProportion+"%";
					
					int linkNum=detectionPeroidCount.getLinkHome()+detectionPeroidCount.getLinkAll();
					
					obj[8]=linkNum;
					
					int securityUpdate =detectionPeroidCount.getSecurityChannel();
					if(detectionPeroidCount.getSecurityHome()==1){
						securityUpdate+=1;
					}
					obj[9]=securityUpdate;
					obj[10]=detectionPeroidCount.getSecurityBlank();
					obj[11]=detectionPeroidCount.getSecurityResponse();
					int seriveNum=detectionPeroidCount.getServiceGuide()+
							detectionPeroidCount.getServiceDownload()+
							detectionPeroidCount.getServiceConn();
					obj[12]=seriveNum;
					int seriousNum=detectionPeroidCount.getSeriousCorrect()+
							detectionPeroidCount.getSeriousUnreal()+
							detectionPeroidCount.getSeriousViolence()+
							detectionPeroidCount.getSeriousOthers();
					obj[13]=seriousNum;
					obj[14]=detectionPeroidCount.getOthers();
					//报告状态
					//报告按钮是否可以下载
					ReportWordResultRequest reportWordResultRequest = new ReportWordResultRequest();
					reportWordResultRequest.setSiteCode(detectionPeroidCount.getSiteCode());
					reportWordResultRequest.setServicePeriodId(Integer.valueOf(servicePeriodId));
					List<ReportWordResult> ls = reportWordResultServiceImpl.queryList(reportWordResultRequest);
					if(ls.size()>0){
						obj[15]="已完成";
					}else{
						obj[15]="未完成";
					}
					
					sumLinkNum+=linkNum;
					sumSecurityUpdate+=securityUpdate;
					sumSecurityBlank+=detectionPeroidCount.getSecurityBlank();
					sumSecurityResponse+=detectionPeroidCount.getSecurityResponse();
					sumSerice+=seriveNum;
					sumSerious+=seriousNum;
					sumOthers+=detectionPeroidCount.getOthers();
					list.add(obj);
				}
				objSum[0] = "汇总";
				objSum[1] = "";
				objSum[2] = "";
				objSum[3] = "";
				objSum[4] = "";
				objSum[5] = "";
				objSum[6] = "";
				objSum[7] = getAvgProportion(sumConnErrorProportion, sumNum)+"%";
				objSum[8] = sumLinkNum;
				objSum[9] = sumSecurityUpdate;
				objSum[10] = sumSecurityBlank;
				objSum[11] = sumSecurityResponse;
				objSum[12] = sumSerice;
				objSum[13] = sumSerious;
				objSum[14] = sumOthers;
				objSum[15] = "";
				list.add(objSum);
			}
		
			
				
				//detection_peroid_count
			ExportExcel.detectionPeroidExcel(fileName, title, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * @Description: 汇总报告excel下载
	 * @author: liujc --- 2016-5-27下午5:10:55
	 * @return
	 */
	public void excelOrg(){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String servicePeriodId = request.getParameter("servicePeriodId");//周期服务id
		String siteCode = request.getParameter("siteCode");//网站标识码
		try {	
			ReportCollectResultRequest reportCollectrequest=new ReportCollectResultRequest();
			reportCollectrequest.setSiteCode(siteCode);
			reportCollectrequest.setServicePeriodId(servicePeriodId);
			List<ReportCollectResult> reportCollectResultList=reportCollectResultServiceImpl.queryList(reportCollectrequest);
			if(reportCollectResultList!=null && reportCollectResultList.size()>0){
				ReportCollectResult wordResult=reportCollectResultList.get(0);
//				resultMap.put("filePath", wordResult.getWordUrl());
				String wordUrl=wordResult.getPath();
				if(StringUtils.isNotEmpty(wordUrl)){
					wordUrl=urlAdapterVar.getJiXiaoWord()+wordUrl;
				}
				resultMap.put("filePath", wordUrl);
				resultMap.put("fileName", wordResult.getAliasName());
				File file=new File(wordUrl);
				if(!file.exists()){
					resultMap.put("errorMsg", "未找到下载文件");
				}
			}else{
				resultMap.put("errorMsg", "未找到下载文件");
			}
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("errorMsg", "获取下载报告路径异常");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		}
	}
	/**
	 * 获取不连通比例（没有%号），保留2位小数点（平均值）
	 * 
	 * @param num
	 *            数量
	 * @param totalNum
	 *            总数
	 * @return
	 */
	private String getAvgProportion(double sumPoportion, int totalNum) {

		if (sumPoportion == 0 || totalNum == 0) {
			return "0";
		}

		double proportion = sumPoportion / totalNum;

		if (proportion <= 0) {
			return "0";
		}

		if (proportion >= 100) {
			return "100";
		}

		return StringUtils.formatDouble(2, proportion);
	}
	/**
	 * @Description: 获取服务周期
	 * @author: yangshuai --- 2016-6-7下午4:45:18
	 * @return
	 */
	public String getServicePeriodById(){
		Map<String, Object> resultMap=new HashMap<String, Object>();
		try {
			String servicePeriodId = request.getParameter("servicePeriodId");
			//获取服务周期信息
			ServicePeriod servicePeriod = servicePeriodServiceImpl.get(Integer.parseInt(servicePeriodId));
			resultMap.put("startDate", DateUtils.formatStandardDate(servicePeriod.getStartDate()));
			resultMap.put("endDate", DateUtils.formatStandardDate(servicePeriod.getEndDate()));
			resultMap.put("servicePeriodId", servicePeriod);
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			resultMap.put("errorMsg", "获取数据异常");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		}
		
		return null;
	}
	/**
	 * @Title: 获取站点数据 新方法
	 * @Description:
	 * @author liujc@ucap.com.cn	2016-9-14下午2:48:28
	 */
	public void getwebsiteList(){
		Map<String, Object> resultMap=new HashMap<String, Object>();
		try {
			String servicePeriodId = request.getParameter("servicePeriodId");
			//查询 详情数据表
			DetectionPeroidCountRequest detectionPeroidCountRequest=new DetectionPeroidCountRequest();
			if(StringUtils.isNotEmpty(servicePeriodId) ){
				detectionPeroidCountRequest.setServicePeroidId(Integer.valueOf(servicePeriodId));
			}else{
				detectionPeroidCountRequest.setServicePeroidId(-1);
			}
			//默认 siteCode 正序
			List<QueryOrder> queryList=new ArrayList<QueryOrder>();
			QueryOrder queryOrder=new QueryOrder("site_code",QueryOrderType.ASC);
			queryList.add(queryOrder);
			detectionPeroidCountRequest.setQueryOrderList(queryList);
			detectionPeroidCountRequest.setPageNo(0);
			detectionPeroidCountRequest.setPageSize(Integer.MAX_VALUE);
			List<DetectionPeroidCount> detectionPeroidList=detectionPeroidCountServiceImpl.queryList(detectionPeroidCountRequest);
			int i=0;
			if(detectionPeroidList.size()>0){
				for(DetectionPeroidCount detectionPeroidCount :detectionPeroidList){
					//报告按钮是否可以下载
					ReportWordResultRequest reportWordResultRequest = new ReportWordResultRequest();
					reportWordResultRequest.setSiteCode(detectionPeroidCount.getSiteCode());
					reportWordResultRequest.setServicePeriodId(Integer.valueOf(servicePeriodId));
					List<ReportWordResult> ls = reportWordResultServiceImpl.queryList(reportWordResultRequest);
					if(ls.size()>0){
						//已完成报告显示
						detectionPeroidCount.setIsDown(1);
						detectionPeroidCount.setIsHide("0");
					}else{
						detectionPeroidCount.setIsDown(0);
						detectionPeroidCount.setIsHide("1");
						
					}
					
					
					//获取整改通知状态
					SpotCheckNoticeRequest spotCheckNoticeRequest = new SpotCheckNoticeRequest();
					spotCheckNoticeRequest.setSiteCode(detectionPeroidCount.getSiteCode());
					spotCheckNoticeRequest.setServicePeriodId(Integer.parseInt(servicePeriodId));
					List<SpotCheckNotice> spotCheckNoticeList = spotCheckNoticeServiceImpl.queryList(spotCheckNoticeRequest);
					if(spotCheckNoticeList != null && spotCheckNoticeList.size()>0){
						SpotCheckNotice spotCheckNotice=spotCheckNoticeList.get(0);
						detectionPeroidCount.setCheckReportResult(spotCheckNotice.getCheckReportResult());
						detectionPeroidCount.setType(spotCheckNotice.getType());
						detectionPeroidCount.setIsRead(spotCheckNotice.getIsRead());
						
					}else{
						detectionPeroidCount.setCheckReportResult(0);
						detectionPeroidCount.setIsRead(0);//未读
					}
					
					DetectionPeroidCountRequest peroidCountRequest=new DetectionPeroidCountRequest();
					peroidCountRequest.setSiteCode(detectionPeroidCount.getSiteCode());
					peroidCountRequest.setServicePeroidId(Integer.parseInt(servicePeriodId));
					List<DetectionPeroidCount> peroidCountlist=detectionPeroidCountServiceImpl.queryList(peroidCountRequest);
					if(peroidCountlist.size()>0){
						Integer siteCheckResult=peroidCountlist.get(0).getSiteCheckResult();
						String staDate = peroidCountlist.get(0).getStartDate();
						String date = dicUtils.getValue("qualified_state_time");  
						Date d = DateUtils.parseStandardDate(date);       //控制合格时间
						
						if(siteCheckResult !=null){
							if(StringUtils.isNotEmpty(staDate)){
								Date da = DateUtils.parseStandardDate(staDate);     // 实际开始时间
								if(d.getTime() > da.getTime()){
										detectionPeroidCount.setSiteCheckResultName("--");
								}else{
									if(siteCheckResult==1){
										//合格
										detectionPeroidCount.setSiteCheckResultName("合格");
									}else if(siteCheckResult==2){
										//不合格
										detectionPeroidCount.setSiteCheckResultName("不合格");
									}
								}
							}
						}else{
							//空数据的时候
							detectionPeroidCount.setSiteCheckResultName("--");
						}
						
					}
					Integer isRead=detectionPeroidCount.getIsRead();
					if(null ==isRead){
						isRead=1;
					}
					if(null == checkRead){
						checkRead=0;
					}
					if(null == checkSiteType){
						checkSiteType=0;
					}
					if(null == checkResult){
						checkResult=0;
					}
					if(null == checkReport){
						checkReport=0;
					}
					if(null == checkNotice){
						checkNotice=0;
					}
					if(checkSiteType==0 && checkResult==0 && checkReport==0 && checkNotice==0 && checkRead==0){
						//如果页面没有选中复选框  直接显示全部
						i++;
						detectionPeroidCount.setIsHide("0");
					}else{
					detectionPeroidCount.setIsHide("0");
					String siteCode=detectionPeroidCount.getSiteCode();
					//获得当前登录用户的网站标识码
					String orgSiteCode = getCurrentUserInfo().getSiteCode();
					if(checkSiteType==1){
						DatabaseTreeInfoRequest treeInfoRequest = new DatabaseTreeInfoRequest();
						treeInfoRequest.setSiteCode(siteCode);
						treeInfoRequest.setOrgSiteCode(orgSiteCode);
						treeInfoRequest.setLayerType(1);//本级门户
						treeInfoRequest.setIsLink(1);//是否link使用
						List<DatabaseTreeInfo> treeList=databaseTreeInfoServiceImpl.queryList(treeInfoRequest);
						if(treeList.size()>0){
							i++;
							detectionPeroidCount.setIsHide("0");
						}else{
							detectionPeroidCount.setIsHide("1");
							continue;
						}
					}else if(checkSiteType==2){
						DatabaseTreeInfoRequest treeInfoRequest = new DatabaseTreeInfoRequest();
						treeInfoRequest.setSiteCode(siteCode);
						treeInfoRequest.setOrgSiteCode(orgSiteCode);
						treeInfoRequest.setLayerType(2);//本级部门
						treeInfoRequest.setIsLink(1);//是否link使用
						List<DatabaseTreeInfo> treeList=databaseTreeInfoServiceImpl.queryList(treeInfoRequest);
						if(treeList.size()>0){
							i++;
							detectionPeroidCount.setIsHide("0");
						}else{
							detectionPeroidCount.setIsHide("1");
							continue;
						}
					}else if(checkSiteType ==3 ){
						DatabaseTreeInfoRequest treeInfoRequest = new DatabaseTreeInfoRequest();
						treeInfoRequest.setSiteCode(siteCode);
						treeInfoRequest.setOrgSiteCode(orgSiteCode);
						treeInfoRequest.setLayerType(3);//下属单位
						treeInfoRequest.setIsLink(1);//是否link使用
						List<DatabaseTreeInfo> treeList=databaseTreeInfoServiceImpl.queryList(treeInfoRequest);
						if(treeList.size()>0){
							i++;
							detectionPeroidCount.setIsHide("0");
						}else{
							detectionPeroidCount.setIsHide("1");
							continue;
						}
					}
					if(checkResult==1){
						DetectionPeroidCountRequest request=new DetectionPeroidCountRequest();
						request.setSiteCode(siteCode);
						request.setSiteCheckResult(1);//合格
						List<DetectionPeroidCount> list=detectionPeroidCountServiceImpl.queryList(request);
						if(list.size()>0){
							i++;
							detectionPeroidCount.setIsHide("0");
						}else{
							detectionPeroidCount.setIsHide("1");
							continue;
						}
					}else if(checkResult==2){
						DetectionPeroidCountRequest request=new DetectionPeroidCountRequest();
						request.setSiteCode(siteCode);
						request.setSiteCheckResult(2);//不合格
						List<DetectionPeroidCount> list=detectionPeroidCountServiceImpl.queryList(request);
						if(list.size()>0){
							i++;
							detectionPeroidCount.setIsHide("0");
						}else{
							detectionPeroidCount.setIsHide("1");
							continue;
						}
					}
						 if(checkReport==1 ){
								//查询报告已完成  
								if( ls.size()>0){
									//已完成
									i++;
									detectionPeroidCount.setIsHide("0");
								}else{
									detectionPeroidCount.setIsHide("1");
									continue;
								}
							}else if(checkReport==2){
								//查询未完成
								if( ls.size()>0){
									//已完成
									detectionPeroidCount.setIsHide("1");
									continue;
								}else{
									i++;
									detectionPeroidCount.setIsHide("0");
								}
							}
						 
						 if(checkNotice==1){
							//查询  已通知整改
								if(spotCheckNoticeList != null && spotCheckNoticeList.size()>0){
									
									if(detectionPeroidCount.getCheckReportResult()!=0  ){
										//已通知整改显示
										i++;
										detectionPeroidCount.setIsHide("0");
									}else{
										detectionPeroidCount.setIsHide("1");
										continue;
									}
								}else{
									detectionPeroidCount.setIsHide("1");
									continue;
								}
								
							}else if(checkNotice==2){

								//查询  未通知整改
								if(spotCheckNoticeList != null && spotCheckNoticeList.size()>0){
									
									if(detectionPeroidCount.getCheckReportResult()!=0  ){
										//已通知整改显示
										
										detectionPeroidCount.setIsHide("1");
										continue;
									}else{
										i++;
										detectionPeroidCount.setIsHide("0");
									}
								}else{
									i++;
									detectionPeroidCount.setIsHide("0");
								}
							}else if(checkNotice == 3){
								if(spotCheckNoticeList != null && spotCheckNoticeList.size()>0){
									if(detectionPeroidCount.getCheckReportResult() == 2){
										detectionPeroidCount.setIsHide("0");
										continue;
									}else{
										i++;
										detectionPeroidCount.setIsHide("1");
									}
								}else{
									i++;
									detectionPeroidCount.setIsHide("1");
								}
							}else if(checkNotice == 4){
								if(spotCheckNoticeList != null && spotCheckNoticeList.size()>0){
									if(detectionPeroidCount.getCheckReportResult() == 1){
										detectionPeroidCount.setIsHide("0");
										continue;
									}else{
										i++;
										detectionPeroidCount.setIsHide("1");
									}
								}else{
									i++;
									detectionPeroidCount.setIsHide("1");
								}
							}
						 if(checkRead==1){
							 if(isRead==1){
								//已通读显示
									i++;
									detectionPeroidCount.setIsHide("0");
							 }else{
								 detectionPeroidCount.setIsHide("1");
								 continue;
							 }
						 }else if(checkRead ==2){
							 //显示未读
							 if(isRead==1){
									detectionPeroidCount.setIsHide("1");
									continue;
							 }else{
								 //未读显示
								 i++;
								 detectionPeroidCount.setIsHide("0");
							 }
						 
						 }
						
					}
					
					
					
					
					
					//查询siteName  Url
					DatabaseInfoRequest databaseInfoRequest=new DatabaseInfoRequest();
					databaseInfoRequest.setSiteCode(detectionPeroidCount.getSiteCode());
					List<DatabaseInfo> databaseInfoList=databaseInfoServiceImpl.queryList(databaseInfoRequest);
					if(databaseInfoList.size()>0){
						DatabaseInfo databaseInfo=databaseInfoList.get(0);
						String siteName=databaseInfo.getName();
						String siteUrl=databaseInfo.getUrl();
						detectionPeroidCount.setSiteName(siteName);
						detectionPeroidCount.setUrl(siteUrl);
						
					}
				}
				resultMap.put("detectionPeroidList", detectionPeroidList);
				
			}
			resultMap.put("listSize", i);
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description: 获取站点数据 旧方法
	 * @author: yangshuai --- 2016-5-27下午12:02:15
	 * @return
	 * @throws Exception
	 */
	public String getwebsiteListold(){
		Map<String, Object> resultMap=new HashMap<String, Object>();
		try {
			String servicePeriodId = request.getParameter("servicePeriodId");
//			String siteCode = getCurrentSiteCode();
			//获取周期关联表站点数据
			RelationsPeriodRequest relationsPeriodRequest = new RelationsPeriodRequest();
			relationsPeriodRequest.setServicePeriodId(Integer.parseInt(servicePeriodId));
			relationsPeriodRequest.setPageSize(Integer.MAX_VALUE);
			List<RelationsPeriod> relationsPeriodList = relationsPeriodServiceImpl.queryList(relationsPeriodRequest);
			if(relationsPeriodList != null && relationsPeriodList.size()>0){
				//根据站点集合查询站点信息集
				DatabaseInfoRequest databaseInfoRequest = new DatabaseInfoRequest();
				databaseInfoRequest.setPageSize(Integer.MAX_VALUE);
				databaseInfoRequest.setRelationsPeriodList(relationsPeriodList);
				List<DatabaseInfo> databaseInfoList = new ArrayList<DatabaseInfo>();
				List<DatabaseInfo> databaseInfoLists = databaseInfoServiceImpl.queryList(databaseInfoRequest);
				if(databaseInfoLists != null && databaseInfoLists.size()>0){
					for (DatabaseInfo databaseInfo : databaseInfoLists) {
						ReportWordResultRequest reportWordResultRequest = new ReportWordResultRequest();
						reportWordResultRequest.setSiteCode(databaseInfo.getSiteCode());
						reportWordResultRequest.setServicePeriodId(Integer.parseInt(servicePeriodId));
						List<ReportWordResult> ls = reportWordResultServiceImpl.queryList(reportWordResultRequest);
						if(ls.size()>0){
							databaseInfo.setIsDown(1);
						}else{
							databaseInfo.setIsDown(0);
						}
						//获取整改通知状态
						SpotCheckNoticeRequest spotCheckNoticeRequest = new SpotCheckNoticeRequest();
						spotCheckNoticeRequest.setSiteCode(databaseInfo.getSiteCode());
						spotCheckNoticeRequest.setServicePeriodId(Integer.parseInt(servicePeriodId));
						List<SpotCheckNotice> spotCheckNoticeList = spotCheckNoticeServiceImpl.queryList(spotCheckNoticeRequest);
						if(spotCheckNoticeList != null && spotCheckNoticeList.size()>0){
							databaseInfo.setCheckReportResult(spotCheckNoticeList.get(0).getCheckReportResult());
						}else{
							databaseInfo.setCheckReportResult(0);
						}
						databaseInfoList.add(databaseInfo);
					}
					resultMap.put("databaseInfoList", databaseInfoList);
				}else{
					resultMap.put("errorMsg", "暂无站点数据");
				}
			}else{
				resultMap.put("errorMsg", "暂无站点数据");
			}
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("errorMsg", "获取数据异常");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		}
		return null;
	}

	/**
	 * @Description: 站点数据导出
	 * @author: yangshuai --- 2016-5-27下午8:49:57
	 * @return
	 */
	public void excelDatabaseInfo(){
		String servicePeriodId = request.getParameter("servicePeriodId");//抽查进度表id
		String keyWord = request.getParameter("keyWord");//查询关键字
		String servicePeriodStatus = request.getParameter("servicePeriodStatus");
//		String orgSiteCode = getCurrentSiteCode();//当前组织机构编码
		try {
			ArrayList<Object[]> list = new ArrayList<Object[]>();
			Object[] obj1 = new Object[] {"序号","网站标识码", "网站名称", "省/部", "市","县","周期任务状态"};
			list.add(obj1);
			String fileName = "全面监测-站点明细(" + DateUtils.formatStandardDate(new Date()) + ").xls";
			String title = "站点明细";
			
			if(StringUtils.isNotEmpty(servicePeriodId)){
				//根据组织机构编码和抽查进度表id,查询抽查结果表
				RelationsPeriodRequest relationsPeriodRequest = new RelationsPeriodRequest();
				relationsPeriodRequest.setServicePeriodId(Integer.parseInt(servicePeriodId));
				relationsPeriodRequest.setPageSize(Integer.MAX_VALUE);
				List<RelationsPeriod> relationsPeriodList = relationsPeriodServiceImpl.queryList(relationsPeriodRequest);
				if(relationsPeriodList != null && relationsPeriodList.size()>0){
					DatabaseInfoRequest databaseInfoRequest = new DatabaseInfoRequest();
					databaseInfoRequest.setPageSize(Integer.MAX_VALUE);
					databaseInfoRequest.setRelationsPeriodList(relationsPeriodList);
					if(StringUtils.isNotEmpty(keyWord)){
						databaseInfoRequest.setKeyWord(keyWord);
					}
					List<DatabaseInfo> databaseInfoList = databaseInfoServiceImpl.queryList(databaseInfoRequest);
					if(databaseInfoList!=null && databaseInfoList.size()>0){
	
						for (int i = 0; i < databaseInfoList.size(); i++) {
							DatabaseInfo result = databaseInfoList.get(i);
							Object[] obj = new Object[7];
							obj[0] = i + 1;
							String siteCode="";//网站标识码
							if(StringUtils.isNotEmpty(result.getSiteCode())){
								siteCode=result.getSiteCode();
							}
							obj[1]=siteCode;
							
							String siteName="";//网站名称
							if(StringUtils.isNotEmpty(result.getName())){
								siteName=result.getName();
							}
							obj[2]=siteName;
							
							String province="";//省/部
							if(StringUtils.isNotEmpty(result.getProvince())){
								province=result.getProvince();
							}
							obj[3]=province;
							
							String city="";//市
							if(StringUtils.isNotEmpty(result.getCity())){
								city=result.getCity();
							}
							obj[4]=city;
							
							String county="";//县
							if(StringUtils.isNotEmpty(result.getCounty())){
								county=result.getCounty();
							}
							obj[5]=county;
							
							//周期任务状态（-1:删除，0：未开始服务，1:服务中，2：已完成服务）
							obj[6]=servicePeriodStatus;
							
							list.add(obj);
						}
					}
				}
			}
			ExportExcel.spotResultExcel(fileName, title, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public Integer getDataType() {
		return dataType;
	}

	public void setDataType(Integer dataType) {
		this.dataType = dataType;
	}

	public Integer getCheckRead() {
		return checkRead;
	}

	public void setCheckRead(Integer checkRead) {
		this.checkRead = checkRead;
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

	

}
