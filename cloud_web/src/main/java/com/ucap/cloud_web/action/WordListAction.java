package com.ucap.cloud_web.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.publics.util.page.PageVo;
import com.publics.util.page.QueryOrder;
import com.publics.util.page.QueryOrderType;
import com.publics.util.utils.DateUtils;
import com.publics.util.utils.StringUtils;
import com.ucap.cloud_web.UrlAdapterVar;
import com.ucap.cloud_web.dto.ReportWordResultRequest;
import com.ucap.cloud_web.dto.ServicePeriodRequest;
import com.ucap.cloud_web.dto.SpotCheckResultRequest;
import com.ucap.cloud_web.entity.DatabaseTreeInfo;
import com.ucap.cloud_web.entity.ReportWordResult;
import com.ucap.cloud_web.entity.ServicePeriod;
import com.ucap.cloud_web.entity.SpotCheckNotice;
import com.ucap.cloud_web.entity.SpotCheckResult;
import com.ucap.cloud_web.service.IDatabaseTreeInfoService;
import com.ucap.cloud_web.service.IReportWordResultService;
import com.ucap.cloud_web.service.IServicePeriodService;
import com.ucap.cloud_web.service.ISpotCheckNoticeService;
import com.ucap.cloud_web.service.ISpotCheckResultService;
/**
 * <p>Description: word下载列表</p>
 * <p>@Package：com.ucap.cloud_web.action </p>
 * <p>Title: ConnBusinessDetailAction </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：sunjiang </p>
 * <p>@date：2016年05月16日上午11:35:35 </p>
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class WordListAction extends BaseAction{
	
	@Autowired
	private ISpotCheckResultService spotCheckResultServiceImpl;
	@Autowired
	private IServicePeriodService servicePeriodServiceImpl;
	@Autowired
	private IReportWordResultService reportWordResultServiceImpl;
	@Autowired
	private ISpotCheckNoticeService spotCheckNoticeServiceImpl;
	@Autowired
	private IDatabaseTreeInfoService databaseTreeInfoServiceImpl;
	@Autowired
	private UrlAdapterVar urlAdapterVar;
	
	/**
	 * log日志加载
	 */
	private static Log logger =  LogFactory.getLog(WordListAction.class);
	
	private String isScanFlag;
	public String getIsScanFlag() {
		return isScanFlag;
	}
	public void setIsScanFlag(String isScanFlag) {
		this.isScanFlag = isScanFlag;
	}


	public String index(){
		isScanFlag=request.getParameter("isScanFlag");
		logger.info("======WordListAction  index======isScanFlag:"+isScanFlag);
		if(StringUtils.isNotEmpty(isScanFlag) && "true".equals(isScanFlag)){
			isScanFlag="true";
		}else{
			isScanFlag="false";
		}
		request.setAttribute("onlineReportUrl", urlAdapterVar.getOnlineReportUrl());
		return SUCCESS;
	}
	/**
	 * @描述:获取在线预览 后台访问路径
	 * @作者:liujc@ucap.com.cn
	 * @时间:2017-4-10下午2:27:14
	 */
	public void queryOnlineReportUrl(){
		Map<String,Object> dataMap = new HashMap<String, Object>();
		dataMap.put("onlineReportUrl", urlAdapterVar.getOnlineReportUrl());
		writerPrint(JSONObject.fromObject(dataMap).toString());
	}
	//组织单位：下级整改通知
	public String indexOrg(){
		isScanFlag=request.getParameter("isScanFlag");
		logger.info("======WordListAction  index======isScanFlag:"+isScanFlag);
		if(StringUtils.isNotEmpty(isScanFlag) && "true".equals(isScanFlag)){
			isScanFlag="true";
		}else{
			isScanFlag="false";
		}
		request.setAttribute("onlineReportUrl", urlAdapterVar.getOnlineReportUrl());
		return SUCCESS;
	}
	
	/** @Description: 获取word列表数据  作废了
	 * @author sunjiaqi --- 2016-5-16下午5:54:23     
	 * yangshuai 修改   --- 2016-6-3下午3:01:22
	 * CheckReportResults != 0 网站抽查/网站考评
	*/
	public void getList(){
		
		try {
			Integer size = Integer.parseInt(request.getParameter("size"));
			Integer pageNo = Integer.parseInt(request.getParameter("pageNo"));
			String siteCode = getCurrentUserInfo().getChildSiteCode();//获取当前登录人siteCode
			
			SpotCheckResultRequest  spotCheckResultRequest =new SpotCheckResultRequest();
			spotCheckResultRequest.setSiteCode(siteCode);
			spotCheckResultRequest.setPageNo(pageNo);
			spotCheckResultRequest.setPageSize(size);
			spotCheckResultRequest.setCheckReportResults(0);//报告状态不为0
			List<QueryOrder> queryOrderList = new ArrayList<QueryOrder>();
			queryOrderList.add(new QueryOrder("create_time", QueryOrderType.DESC));//创建时间倒序
			spotCheckResultRequest.setQueryOrderList(queryOrderList);
			//查询审核通过的word报告数据
			List<SpotCheckResult> queryList = spotCheckResultServiceImpl.queryList(spotCheckResultRequest);
			
			List<Object> resultList = new ArrayList<Object>();
			Map<String,Object> dataMap = new HashMap<String, Object>();
			if(null!=queryList && queryList.size()>0){
				for(SpotCheckResult spotCheckResult : queryList){
					int spotCheckSchedule = spotCheckResult.getSpotCheckSchedule();//查找抽查结果表里面的抽查进度表ID
					ServicePeriodRequest  servicePeriodRequest = new ServicePeriodRequest();
					servicePeriodRequest.setSpotCheckScheduleId(spotCheckSchedule);
					//通过抽查进度表ID获取周期任务ID
					List<ServicePeriod> queryList2 = servicePeriodServiceImpl.queryList(servicePeriodRequest);
					if(null!=queryList2 && queryList2.size()>0){
						ServicePeriod servicePeriod = queryList2.get(0);
						Date startDate = servicePeriod.getStartDate();
						Date endDate = servicePeriod.getEndDate();
						ReportWordResultRequest  reportWordResultRequest = new ReportWordResultRequest();
						reportWordResultRequest.setSiteCode(siteCode);
						reportWordResultRequest.setServicePeriodId(servicePeriod.getId());
						//通过周期任务ID和siteCode获取报告结果数据
						List<ReportWordResult> queryList3 = reportWordResultServiceImpl.queryList(reportWordResultRequest);
						if(null!=queryList3 && queryList3.size()>0){
							Map<String,Object> map = new HashMap<String, Object>(); 
							for (ReportWordResult reportWordResult : queryList3) {
								String aliasName = reportWordResult.getAliasName();
								map.put("fileName", aliasName);
								map.put("taskTime", DateUtils.formatStandardDate(startDate) + "至" + DateUtils.formatStandardDate(endDate));
								map.put("fid", reportWordResult.getId());
								map.put("servicePeriodId", reportWordResult.getServicePeriodId());
								map.put("siteCode", reportWordResult.getSiteCode());
								resultList.add(map);
							}
						}
					}
					
				}
			}
			dataMap.put("body", resultList);
			dataMap.put("totalRecords", queryList.size());
			dataMap.put("iTotalDisplayRecords", queryList.size());
			dataMap.put("hasMoreItems",true);
			writerPrint(JSONObject.fromObject(dataMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description: 通过siteCode四表查询（service_period、spot_check_notice、relations_period）来获取数据
	 * 网站考评/网站抽查、全面检测使用
	 * @author: yangshuai --- 2016-6-7上午10:17:51
	 * @return
	 */
	public void getWordList(){
		
		try {
			Integer size = Integer.parseInt(request.getParameter("size"));
			Integer pageNo = Integer.parseInt(request.getParameter("pageNo"));
			String siteCode = getCurrentUserInfo().getChildSiteCode();//获取当前登录人siteCode
			
			List<Object> resultList = new ArrayList<Object>();
			Map<String,Object> dataMap = new HashMap<String, Object>();
			
			//siteCode获取报告结果数据
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("siteCode", siteCode);
			params.put("pageNo",pageNo);
			params.put("pageSize", size);
			List<ReportWordResult> reportWordResultList = reportWordResultServiceImpl.queryByMapWord(params);
			
			if(null!=reportWordResultList && reportWordResultList.size()>0){
				for (ReportWordResult reportWordResult : reportWordResultList) {
					Map<String,Object> map = new HashMap<String, Object>(); 
					
					String aliasName = reportWordResult.getAliasName();
					map.put("fileName", aliasName);
					map.put("taskTime", DateUtils.formatStandardDate(reportWordResult.getStartDate()) + "至" + DateUtils.formatStandardDate(reportWordResult.getEndDate()));
					map.put("fid", reportWordResult.getId());
					map.put("servicePeriodId", reportWordResult.getServicePeriodId());
					map.put("siteCode", reportWordResult.getSiteCode());
					map.put("checkReportResult", reportWordResult.getCheckReportResult());
					
					
					//通知整改表id
					map.put("scnId", reportWordResult.getScnId());
					
					//通知整改类型
					if(reportWordResult.getScnType()!=null){
						map.put("scnType", reportWordResult.getScnType()==1?"全面检测":"抽查");
					}else{
						map.put("scnType","");
					}
					
					
					//主办单位
					if(StringUtils.isNotEmpty(reportWordResult.getDirector())){
						map.put("director", reportWordResult.getDirector());
					}else{
						map.put("director", "");
					}
					
					//已读状态
					if(reportWordResult.getIsRead()!=null){
						map.put("isRead", reportWordResult.getIsRead()==1?"已读":"未读");
					}else{
						map.put("isRead", "");
					}
					
					//查询服务 开始 结束时间
					ServicePeriodRequest servicePeriodRequest=new ServicePeriodRequest();
					servicePeriodRequest.setId(reportWordResult.getServicePeriodId());
					List<ServicePeriod>  servicePeriodList= servicePeriodServiceImpl.queryList(servicePeriodRequest);
					if(servicePeriodList.size()>0){
						ServicePeriod servicePeriod =servicePeriodList.get(0);
		
						map.put("startDate", DateUtils.formatStandardDate(servicePeriod.getStartDate()));
						map.put("endDate", DateUtils.formatStandardDate(servicePeriod.getEndDate()));
					}
					
					if(StringUtils.isNotEmpty(reportWordResult.getWordUrl())){
							resultList.add(map);	
					}
					
					
				}
			}
			dataMap.put("body", resultList);
			dataMap.put("totalRecords", reportWordResultList.size());
			dataMap.put("iTotalDisplayRecords", reportWordResultList.size());
			dataMap.put("hasMoreItems",true);
			writerPrint(JSONObject.fromObject(dataMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 组织单位：获取通知列表（发送给填报单位的）
	 */
	public void getOrgWordList(){
		
		try {
			String siteCode = getCurrentUserInfo().getChildSiteCode();//获取当前登录人siteCode
			Map<String,Object> mapp = new HashMap<String,Object>();
			mapp.put("siteCode", siteCode);
			DatabaseTreeInfo info= databaseTreeInfoServiceImpl.queryUpOrgSiteCode(mapp);
			String[] strs = info.getSiteCode().split(",");
			String siteType =request.getParameter("siteTypeIdVal");//网站类别 
			String foldName =request.getParameter("foldName");//文件名称 
			String reportRead =request.getParameter("reportRead");//反馈状态
			
			List<Object> resultList = new ArrayList<Object>();
			Map<String,Object> dataMap = new HashMap<String, Object>();
			
			ReportWordResultRequest wordRequest = new ReportWordResultRequest();
			wordRequest.setCode(info.getCode());
			wordRequest.setLevel(info.getLevel()+1);
			if(strs.length>1){
				wordRequest.setList(Arrays.asList(strs));
			}else{
				//siteCode获取报告结果数据
				if(strs.length==1){
					//wordRequest.setList(Arrays.asList(strs));
					wordRequest.setOrgSiteCode(strs[0]);
				}
				
			}
			String pos = request.getParameter("pos");
			if(StringUtils.isNotEmpty(pos)){
				wordRequest.setPageNo(Integer.parseInt(pos));
			}
			String size = request.getParameter("size");
			if(StringUtils.isNotEmpty(size)){
				wordRequest.setPageSize(Integer.parseInt(size));
			}
			if(StringUtils.isNotEmpty(siteType) && !siteType.equals("0")){
				wordRequest.setLayerType(Integer.valueOf(siteType));
			}
			if(StringUtils.isNotEmpty(reportRead)){
				if(reportRead.equals("1")){
					wordRequest.setCheckReportResult(2);
					wordRequest.setIsRead(1);
				}else if(reportRead.equals("2")){
					wordRequest.setCheckReportResult(1);
					wordRequest.setIsRead(0);
				}else if(reportRead.equals("3")){
					wordRequest.setCheckReportResult(1);
					wordRequest.setIsRead(1);
				}
			}
			if(StringUtils.isNotEmpty(foldName)){
				wordRequest.setAliasName(foldName);
			}
			//按照创建时间倒排序
			List<QueryOrder> queryOrderList=new ArrayList<QueryOrder>();
			QueryOrder timeOrderCreat=new QueryOrder("create_time",QueryOrderType.DESC);
			queryOrderList.add(timeOrderCreat);
			wordRequest.setQueryOrderList(queryOrderList);
			
			
			PageVo<ReportWordResult> query = new PageVo<ReportWordResult>();
			if(strs.length==0){
			}else{
				query = reportWordResultServiceImpl.queryRectificationNotice(wordRequest); 
			}
			List<ReportWordResult> reportWordResultList = query.getData();
			if(null!=reportWordResultList && reportWordResultList.size()>0){
				for (ReportWordResult reportWordResult : reportWordResultList) {
					Map<String,Object> map = new HashMap<String, Object>(); 
					
					String aliasName = reportWordResult.getAliasName();
					map.put("fileName", aliasName);
					map.put("taskTime", DateUtils.formatStandardDate(reportWordResult.getStartDate()) + "至" + DateUtils.formatStandardDate(reportWordResult.getEndDate()));
					map.put("fid", reportWordResult.getId());
					map.put("servicePeriodId", reportWordResult.getServicePeriodId());
					map.put("siteCode", reportWordResult.getSiteCode());
					map.put("checkReportResult", reportWordResult.getCheckReportResult());
					
					//通知整改表id
					map.put("scnId", reportWordResult.getScnId());
					
					//通知整改类型
					if(reportWordResult.getScnType()!=null){
						map.put("scnType", reportWordResult.getScnType()==1?"全面检测":"抽查");
					}else{
						map.put("scnType","");
					}
					
					
					//主办单位
					if(StringUtils.isNotEmpty(reportWordResult.getDirector())){
						map.put("director", reportWordResult.getDirector());
					}else{
						map.put("director", "");
					}
					
					//已读状态
					if(reportWordResult.getIsRead()!=null){
						map.put("isRead", reportWordResult.getIsRead()==1?"已读":"未读");
					}else{
						map.put("isRead", "");
					}
					//查询服务 开始 结束时间
					ServicePeriodRequest servicePeriodRequest=new ServicePeriodRequest();
					servicePeriodRequest.setId(reportWordResult.getServicePeriodId());
					List<ServicePeriod>  servicePeriodList= servicePeriodServiceImpl.queryList(servicePeriodRequest);
					if(servicePeriodList.size()>0){
						ServicePeriod servicePeriod =servicePeriodList.get(0);
		
						map.put("startDate", DateUtils.formatStandardDate(servicePeriod.getStartDate()));
						map.put("endDate", DateUtils.formatStandardDate(servicePeriod.getEndDate()));
					}
					
					if(StringUtils.isNotEmpty(reportWordResult.getWordUrl())){
							resultList.add(map);	
					}
					
					
				}
			}
			dataMap.put("body", resultList);
			dataMap.put("totalRecords", query.getData().size());
			dataMap.put("iTotalDisplayRecords", query.getRecordSize());//总数
			dataMap.put("hasMoreItems",true);
			dataMap.put("success", "true");
			writerPrint(JSONObject.fromObject(dataMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * @Description: 更新整改通知表
	 * @author cuichx --- 2016-10-21下午6:58:30
	 */
	public void updateNotice() {
		Map<String, Object> resultMap=new HashMap<String, Object>();
		try {
			String id=request.getParameter("id");
			logger.info("=======updateNotice  id========="+id);
			if(!StringUtils.isNotEmpty(id)){
				resultMap.put("errmsg", "通知整改表id不能为空");
			}else{
				SpotCheckNotice spotCheck=spotCheckNoticeServiceImpl.get(Integer.valueOf(id));
				if(spotCheck!=null && !"".equals(spotCheck)){
					//更新状态为 1已读 
					spotCheck.setIsRead(1);
					spotCheckNoticeServiceImpl.update(spotCheck);
				}else{
					resultMap.put("errmsg", "不存在要更新的通知整改记录");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("errmsg", "更新通知整改数据异常");
		}
		writerPrint(JSONObject.fromObject(resultMap).toString());
	}
}
