package com.ucap.cloud_web.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.publics.util.page.QueryOrder;
import com.publics.util.page.QueryOrderType;
import com.publics.util.utils.DateUtils;
import com.publics.util.utils.StringUtils;
import com.ucap.cloud_web.constant.CrmProductsType;
import com.ucap.cloud_web.dto.ContractInfoRequest;
import com.ucap.cloud_web.dto.MonitorDarkChainRequest;
import com.ucap.cloud_web.dto.MonitorFragilityRequest;
import com.ucap.cloud_web.dto.MonitorLeakedRequest;
import com.ucap.cloud_web.dto.MonitorSilentResultRequest;
import com.ucap.cloud_web.dto.MonitorTamperRequest;
import com.ucap.cloud_web.dto.MonitorTaskSilentRequest;
import com.ucap.cloud_web.dto.MonitorTrojanRequest;
import com.ucap.cloud_web.entity.ContractInfo;
import com.ucap.cloud_web.entity.MonitorDarkChain;
import com.ucap.cloud_web.entity.MonitorFragility;
import com.ucap.cloud_web.entity.MonitorLeaked;
import com.ucap.cloud_web.entity.MonitorSilentResult;
import com.ucap.cloud_web.entity.MonitorTamper;
import com.ucap.cloud_web.entity.MonitorTaskSilent;
import com.ucap.cloud_web.entity.MonitorTrojan;
import com.ucap.cloud_web.service.IContractInfoService;
import com.ucap.cloud_web.service.IMonitorDarkChainService;
import com.ucap.cloud_web.service.IMonitorFragilityService;
import com.ucap.cloud_web.service.IMonitorLeakedService;
import com.ucap.cloud_web.service.IMonitorSilentResultService;
import com.ucap.cloud_web.service.IMonitorTamperService;
import com.ucap.cloud_web.service.IMonitorTaskSilentService;
import com.ucap.cloud_web.service.IMonitorTrojanService;
import com.ucap.cloud_web.util.ExportExcel;

import net.sf.json.JSONObject;

/**
 * 描述： 安全问题
 * 包：com.ucap.cloud_web.action
 * 文件名称：SecurityQuestion
 * 公司名称：开普互联
 * 作者：liujc@ucap.com.cn
 * 时间：2016-9-2上午10:03:41 
 * @version V1.0
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class MonitorSilentResultAction extends BaseAction{
	@Autowired
	private IMonitorSilentResultService monitorSilentResultServiceImpl;
	@Autowired
	private IMonitorFragilityService monitorFragilityServiceImpl;
	@Autowired
	private IMonitorTrojanService monitorTrojanServiceImpl;
	@Autowired
	private IMonitorDarkChainService monitorDarkChainServiceImpl;
	@Autowired
	private IMonitorTaskSilentService monitorTaskSilentServiceImpl;
	@Autowired
	private IMonitorLeakedService monitorLeakedServiceImpl;
	@Autowired
	private IMonitorTamperService monitorTamperServiceImpl;
	@Autowired
	private IContractInfoService contractInfoServiceImpl;
	private static Log logger = LogFactory.getLog(MonitorSilentResultAction.class);
	
	private Integer[] productTypeArr = { CrmProductsType.SECURITY.getCode() }; // 1云监管订单/2云监测订单/3云安全订单/4云搜索订单/5本地部署订单

	// 比较  日期  谁大   返回谁
	public  String compareDate(String DATE1, String DATE2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                return DATE1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return DATE2;
            } else {
                return DATE2;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return DATE2;
    }
	/**
	 * @Title: 安全问题 index页面
	 * @Description:
	 * @author linhb@ucap.com.cn	2016-11-4上午10:19:44
	 * @return
	 */
	public String indexTB(){
		try {
			//siteCode处理由组织单位跳转到该页面时，session的修改
			String siteCode = request.getParameter("siteCode");
			if(StringUtils.isNotEmpty(siteCode)){
				setCurrentShiroUser(siteCode);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "success";
	}
	
	// 概览页面  获取  安全问题总数数据 linhb@ucap.com.cn	
	public void getLastDay(){
		//封装返回给页面的参数
		Map<String,Object>  resultMap=new HashMap<String, Object>();
		try {
			String siteCode = getCurrentUserInfo().getChildSiteCode();//组织单位跳转
			if (StringUtils.isEmpty(siteCode)) {
				siteCode = getCurrentUserInfo().getSiteCode();
			}
			//查询安全问题
			MonitorSilentResultRequest monitorSilentResult=new MonitorSilentResultRequest();
			monitorSilentResult.setScanDate(DateUtils.getNextDay(new Date(), -1));
			monitorSilentResult.setSiteCode(siteCode);
			monitorSilentResult.setPageSize(1);
			String flag = request.getParameter("flag");

			//  1  付费的组织单位跳转过来  
			//  2  付费的填报单位  登录  //展示最后一个任务的数据
			if("1".equals(flag)){
				MonitorTaskSilent mTask = getMonitorTaskDatas( siteCode, 0);//存在正在运行的任务
				if(mTask.getDatabaseInfoId() == -55 ){
					monitorSilentResult.setEndDate(DateUtils.getNextDay(new Date(), -2));
					monitorSilentResult.setStartDate(DateUtils.getNextDay(new Date(), -1));
				}
				if(mTask.getDatabaseInfoId() == -11){ //存在当前  安全任务
					monitorSilentResult.setScanDate(DateUtils.getNextDay(new Date(), -1));
				}
				
			}else if("2".equals(flag)){
				MonitorTaskSilent mTask = getMonitorTaskDatas( siteCode, 1);//存在正在运行的任务
				if(mTask.getDatabaseInfoId() == -55 ){
					monitorSilentResult.setEndDate(DateUtils.getNextDay(new Date(), -2));
					monitorSilentResult.setStartDate(DateUtils.getNextDay(new Date(), -1));
				}
				if(mTask.getDatabaseInfoId() == -11){ //存在当前  安全任务
					monitorSilentResult.setScanDate(DateUtils.getNextDay(new Date(), -1));
				}
				
			}else if("3".equals(flag)){// 已过期的 填报单位登录  
				MonitorTaskSilent mTask = getMonitorTaskDatas( siteCode, 2);
				if(mTask.getDatabaseInfoId() == -55 ){
					monitorSilentResult.setEndDate(DateUtils.getNextDay(new Date(), -2));
					monitorSilentResult.setStartDate(DateUtils.getNextDay(new Date(), -1));
				}
				if(mTask.getDatabaseInfoId() == -12){ // 存在历史数据
					monitorSilentResult.setScanDate(DateUtils.formatStandardDate(mTask.getEndDate()));
				}
			}
			
			List<MonitorSilentResult>  mList =monitorSilentResultServiceImpl.queryList(monitorSilentResult);
			
			if(mList.size()>0){
				resultMap.put("securityQuestionNum", mList.get(mList.size()-1).getSilentNum());//安全问题总数
				resultMap.put("riskNum", mList.get(mList.size()-1).getRiskNum());//风险值
			}else{
				resultMap.put("securityQuestionNum", 0);//安全问题总数
				resultMap.put("riskNum", 0);//安全问题总数
			}
			resultMap.put("success", "true");//安全问题总数
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			resultMap.put("errorMsg", "查询getLastDay数据异常");
			resultMap.put("securityQuestionNum", 0);//安全问题总数
			writerPrint(JSONObject.fromObject(resultMap).toString());
			e.printStackTrace();
		}
	}

	/**
	 * @Title: 获取 7 天数据  填报单位
	 * @Description:
	 * @author linhb@ucap.com.cn	2016-11-4上午10:19:49
	 */
	public void getSevenDatas(){
		Map<String,Object> outMap=new HashMap<String, Object>();
		try {
			String siteCode = getCurrentUserInfo().getChildSiteCode();//组织单位跳转
			if (StringUtils.isEmpty(siteCode)) {
				siteCode = getCurrentUserInfo().getSiteCode();
			}
			//查询安全问题
			MonitorSilentResultRequest monitorSilentResult=new MonitorSilentResultRequest();
			monitorSilentResult.setEndDate(DateUtils.getNextDay(new Date(), -1));
			monitorSilentResult.setStartDate(DateUtils.getNextDay(new Date(), -7));
			monitorSilentResult.setSiteCode(siteCode);
			//monitorSilentResult.setPageSize(Integer.MAX_VALUE);
			String flag = request.getParameter("flag");

			//  1  付费的组织单位跳转过来  
			//  2  付费的填报单位  登录  //展示最后一个任务的数据
			if("1".equals(flag)){
				MonitorTaskSilent mTask = getMonitorTaskDatas( siteCode, 0);//存在正在运行的任务
				if(mTask.getDatabaseInfoId() == -55 ){
					monitorSilentResult.setEndDate(DateUtils.getNextDay(new Date(), -2));
					monitorSilentResult.setStartDate(DateUtils.getNextDay(new Date(), -1));
				}
				if(mTask.getDatabaseInfoId() == -11){ //存在当前  安全任务
					monitorSilentResult.setEndDate(DateUtils.getNextDay(new Date(), -1));
					monitorSilentResult.setStartDate(compareDate(DateUtils.getNextDay(new Date(), -7),DateUtils.formatStandardDate(mTask.getStartDate())));
				}
				
			}else if("2".equals(flag)){
				MonitorTaskSilent mTask = getMonitorTaskDatas( siteCode, 1);//存在正在运行的任务
				if(mTask.getDatabaseInfoId() == -55 ){
					monitorSilentResult.setEndDate(DateUtils.getNextDay(new Date(), -2));
					monitorSilentResult.setStartDate(DateUtils.getNextDay(new Date(), -1));
				}
				if(mTask.getDatabaseInfoId() == -11){ //存在当前  安全任务
					monitorSilentResult.setEndDate(DateUtils.getNextDay(new Date(), -1));
					monitorSilentResult.setStartDate(compareDate(DateUtils.getNextDay(new Date(), -7),DateUtils.formatStandardDate(mTask.getStartDate())));
				}
				
			}else if("3".equals(flag)){// 已过期的 填报单位登录  
				MonitorTaskSilent mTask = getMonitorTaskDatas( siteCode, 2);
				if(mTask.getDatabaseInfoId() == -55 ){
					monitorSilentResult.setEndDate(DateUtils.getNextDay(new Date(), -2));
					monitorSilentResult.setStartDate(DateUtils.getNextDay(new Date(), -1));
				}
				if(mTask.getDatabaseInfoId() == -12){ // 存在历史数据
					monitorSilentResult.setEndDate(DateUtils.formatStandardDate(mTask.getEndDate()));
					monitorSilentResult.setStartDate(compareDate(DateUtils.getNextDay(mTask.getEndDate(), -7),DateUtils.formatStandardDate(mTask.getStartDate())));
				}
			}
			
			
			
			
			
			List<QueryOrder> queryBlankList = new ArrayList<QueryOrder>();
			//时间排序
			QueryOrder siteQueryOrder=new QueryOrder("scan_date",QueryOrderType.ASC);
			queryBlankList.add(siteQueryOrder);
			monitorSilentResult.setQueryOrderList(queryBlankList);
			List<MonitorSilentResult>  mList =monitorSilentResultServiceImpl.queryList(monitorSilentResult);
			List<Integer> weaklist = new ArrayList<Integer>();
			List<Integer> horselist = new ArrayList<Integer>();
			List<Integer> updatelist = new ArrayList<Integer>();
			List<Integer> darklist = new ArrayList<Integer>();
			List<Integer> outlist = new ArrayList<Integer>();
			List<Integer> risklist = new ArrayList<Integer>();
			List<String> scanTimelist = new ArrayList<String>();
			for (MonitorSilentResult monitorSilentResult2 : mList) {
				
				//风险值
				risklist.add(monitorSilentResult2.getRiskNum());
				//脆弱性问题数
				weaklist.add(monitorSilentResult2.getFragilityNum());
				//挂马问题数
				horselist.add(monitorSilentResult2.getTrojanNum());
				//变更/篡改问题数
				updatelist.add(monitorSilentResult2.getTamperNum());
				//网站暗链数
				darklist.add(monitorSilentResult2.getDarkChainNum());
				//内容泄漏数
				outlist.add(monitorSilentResult2.getLeakedNum());
				scanTimelist.add(monitorSilentResult2.getScanDate().replaceAll("-", "/"));
			}

			outMap.put("weaklist", weaklist);
			outMap.put("horselist",horselist);
			outMap.put("updatelist",updatelist);
			outMap.put("darklist",darklist);
			outMap.put("outlist",outlist);
			outMap.put("scanTimelist",scanTimelist);
			logger.info("map_list="+outMap);
			outMap.put("success", "true");
			writerPrint(JSONObject.fromObject(outMap).toString());	
		} catch (Exception e) {
			e.printStackTrace();
			outMap.put("success", "false");
			outMap.put("errorMsg", "查询安全问题详情数据异常！");
			writerPrint(JSONObject.fromObject(outMap).toString());
		}
	}

	
	/**
	 * @Title: 获取 30 天数据
	 * @Description:
	 * @author linhb@ucap.com.cn	2016-11-4上午10:19:49
	 */
	public void getMonthDatas(){
		Map<String,Object> outMap=new HashMap<String, Object>();
		try {
			String siteCode = getCurrentUserInfo().getChildSiteCode();//组织单位
			if (StringUtils.isEmpty(siteCode)) {
				siteCode = getCurrentUserInfo().getSiteCode();
			}
			String flag = request.getParameter("flag");
			
			//查询安全问题
			MonitorSilentResultRequest monitorSilentResult=new MonitorSilentResultRequest();
			monitorSilentResult.setSiteCode(siteCode);
			monitorSilentResult.setEndDate(DateUtils.getNextDay(new Date(), -1));
			monitorSilentResult.setStartDate(DateUtils.getNextDay(new Date(), -30));
			monitorSilentResult.setPageSize(30);
			//  1  付费的组织单位跳转过来  
			//  2  付费的填报单位  登录  //展示最后一个任务的数据
			if("1".equals(flag)){
				MonitorTaskSilent mTask = getMonitorTaskDatas( siteCode, 0);//存在正在运行的任务
				if(mTask.getDatabaseInfoId() == -55 ){
					monitorSilentResult.setEndDate(DateUtils.getNextDay(new Date(), -2));
					monitorSilentResult.setStartDate(DateUtils.getNextDay(new Date(), -1));
				}
				if(mTask.getDatabaseInfoId() == -11){ //存在当前  安全任务
					monitorSilentResult.setEndDate(DateUtils.getNextDay(new Date(), -1));
					monitorSilentResult.setStartDate(compareDate(DateUtils.getNextDay(new Date(), -30),DateUtils.formatStandardDate(mTask.getStartDate())));
				}
				
			}else if("2".equals(flag)){
				MonitorTaskSilent mTask = getMonitorTaskDatas( siteCode, 1);//存在正在运行的任务
				if(mTask.getDatabaseInfoId() == -55 ){
					monitorSilentResult.setEndDate(DateUtils.getNextDay(new Date(), -1));
					monitorSilentResult.setStartDate(DateUtils.getNextDay(new Date(), -2));
				}
				if(mTask.getDatabaseInfoId() == -11){ //存在当前  安全任务
					monitorSilentResult.setEndDate(DateUtils.getNextDay(new Date(), -1));
					monitorSilentResult.setStartDate(compareDate(DateUtils.getNextDay(new Date(), -30),DateUtils.formatStandardDate(mTask.getStartDate())));
				}
				
			}else if("3".equals(flag)){// 已过期的 填报单位登录  
				MonitorTaskSilent mTask = getMonitorTaskDatas( siteCode, 2);
				if(mTask.getDatabaseInfoId() == -55 ){
					monitorSilentResult.setEndDate(DateUtils.getNextDay(new Date(), -2));
					monitorSilentResult.setStartDate(DateUtils.getNextDay(new Date(), -1));
				}
				if(mTask.getDatabaseInfoId() == -12){ // 存在历史数据
					monitorSilentResult.setEndDate(DateUtils.formatStandardDate(mTask.getEndDate()));
					monitorSilentResult.setStartDate(compareDate(DateUtils.getNextDay(mTask.getEndDate(), -30),DateUtils.formatStandardDate(mTask.getStartDate())));
				}
			}
			List<QueryOrder> queryBlankList = new ArrayList<QueryOrder>();
			//时间排序
			QueryOrder siteQueryOrder=new QueryOrder("scan_date",QueryOrderType.ASC);
			queryBlankList.add(siteQueryOrder);
			monitorSilentResult.setQueryOrderList(queryBlankList);
			List<MonitorSilentResult>  mList =monitorSilentResultServiceImpl.queryList(monitorSilentResult);
			List<Integer> weaklist = new ArrayList<Integer>();
			List<Integer> horselist = new ArrayList<Integer>();
			List<Integer> updatelist = new ArrayList<Integer>();
			List<Integer> darklist = new ArrayList<Integer>();
			List<Integer> outlist = new ArrayList<Integer>();
			List<Integer> risklist = new ArrayList<Integer>();
			List<String> scanTimelist = new ArrayList<String>();
			for (MonitorSilentResult monitorSilentResult2 : mList) {
				
				//风险值
				risklist.add(monitorSilentResult2.getRiskNum());
				//脆弱性问题数
				weaklist.add(monitorSilentResult2.getFragilityNum());
				//挂马问题数
				horselist.add(monitorSilentResult2.getTrojanNum());
				//变更/篡改问题数
				updatelist.add(monitorSilentResult2.getTamperNum());
				//网站暗链数
				darklist.add(monitorSilentResult2.getDarkChainNum());
				//内容泄漏数
				outlist.add(monitorSilentResult2.getLeakedNum());
				scanTimelist.add(monitorSilentResult2.getScanDate().replaceAll("-", "/"));
			}
			MonitorSilentResult mSilentResult = new MonitorSilentResult();
			if(mList.size() > 0){
				mSilentResult = mList.get(mList.size()-1);
				outMap.put("risk", mSilentResult.getRiskNum());
				outMap.put("weak", mSilentResult.getFragilityNum());
				outMap.put("horse", mSilentResult.getTrojanNum());
				outMap.put("update", mSilentResult.getTamperNum());
				outMap.put("dark", mSilentResult.getDarkChainNum());
				outMap.put("out", mSilentResult.getLeakedNum());
			}else{
				outMap.put("risk", 0);
				outMap.put("weak", 0);
				outMap.put("horse", 0);
				outMap.put("update", 0);
				outMap.put("dark",0);
				outMap.put("out",0);
			}
			
			outMap.put("scanDate",mSilentResult.getScanDate());
			outMap.put("risklist", risklist);
			outMap.put("weaklist", weaklist);
			outMap.put("horselist",horselist);
			outMap.put("updatelist",updatelist);
			outMap.put("darklist",darklist);
			outMap.put("outlist",outlist);
			outMap.put("scanTimelist",scanTimelist);
			logger.info("map_list="+outMap);
			outMap.put("success", "true");
			writerPrint(JSONObject.fromObject(outMap).toString());	
		} catch (Exception e) {
			e.printStackTrace();
			outMap.put("success", "false");
			outMap.put("errorMsg", "查询安全问题详情数据异常！");
			writerPrint(JSONObject.fromObject(outMap).toString());
		}
	}
	
	/**老合同信息**/
	/**
	 * linhb@ucap.com.cn	
	 * @param siteCode
	 * @param stu  1 合同执行中  2 已经完成
	 * @return
	 */
	public MonitorTaskSilent getMonitorTaskDatas(String siteCode,int stu){
		// stu 1 有正在运行的  安全任务  3 有过期的任务数据
		
		MonitorTaskSilent mTask = new MonitorTaskSilent();
		if(0 == stu){// 组织单位  跳转过来的  // 组织单位  状态 安全业务 执行中 
			MonitorTaskSilentRequest rTask = new MonitorTaskSilentRequest();
			rTask.setSiteCode(siteCode);
			rTask.setNowTime(DateUtils.formatStandardDateTime(new Date()));
			List<QueryOrder> queryBlankList = new ArrayList<QueryOrder>();
			//时间排序
			QueryOrder siteQueryOrder=new QueryOrder("start_date",QueryOrderType.ASC);
			queryBlankList.add(siteQueryOrder);
			rTask.setQueryOrderList(queryBlankList);
			List<MonitorTaskSilent> list =  monitorTaskSilentServiceImpl.queryList(rTask);
			if(list.size()>0){
				mTask = list.get(0);
				mTask.setDatabaseInfoId(-11);//使用  databaseInfoId 做标识  -11 代表 当前时间处在  任务时间内
			}else{
				mTask.setDatabaseInfoId(-55);
			}
			return mTask;
		}
		
		List<Integer> ids = new ArrayList<Integer>();
		//填报单位获取  合同
		ContractInfoRequest conRequest = new ContractInfoRequest();
		conRequest.setSiteCode(siteCode);//
		conRequest.setTypeFlag(1);//非抽查合同
		if(stu == 1){
			conRequest.setExecuteStatus(1);
		}else{
			Integer[] coStatues = { 1, 2 };
			conRequest.setExecuteStatusArr(coStatues);//合同状态为0-初始化 1-服务中
		}
		List<ContractInfo> conList = contractInfoServiceImpl.queryList(conRequest);
		for (ContractInfo contractInfo : conList) {
			ids.add(contractInfo.getId());
		}
		// 组织单位的合同   下属可以看得  
		ContractInfoRequest cRequest = new ContractInfoRequest();
		cRequest.setSiteCode(siteCode);
		cRequest.setTypeFlag(1);//非抽查合同
		if(stu == 1){
			cRequest.setExecuteStatus(1);
		}else{
			Integer[] coStatues = { 1, 2 };
			cRequest.setExecuteStatusArr(coStatues);//合同状态为0-初始化 1-服务中
		}
		cRequest.setIsSearchTb(1);//组织单位购买的安全业务  填报单位可以查看
		List<ContractInfo> cList  = contractInfoServiceImpl.queryByParentCon(conRequest);
		for (ContractInfo contractInfo : cList) {
			ids.add(contractInfo.getId());
		}
		if(ids.size()<1){
			mTask.setDatabaseInfoId(-55);
			return mTask;
		}
		
		if(1 == stu){//任务正在运行中
			MonitorTaskSilentRequest rTask = new MonitorTaskSilentRequest();
			rTask.setSiteCode(siteCode);
			rTask.setNowTime(DateUtils.formatStandardDateTime(new Date()));
			
			rTask.setContractIds(ids);
			List<QueryOrder> queryBlankList = new ArrayList<QueryOrder>();
			//时间排序
			QueryOrder siteQueryOrder=new QueryOrder("start_date",QueryOrderType.ASC);
			queryBlankList.add(siteQueryOrder);
			rTask.setQueryOrderList(queryBlankList);
			List<MonitorTaskSilent> list =  monitorTaskSilentServiceImpl.queryList(rTask);
			if(list.size()>0){
				mTask = list.get(0);
				mTask.setDatabaseInfoId(-11);//使用  databaseInfoId 做标识  -11 代表 当前时间处在  任务时间内
			}else{
				mTask.setDatabaseInfoId(-55);
			}
		}else{//获取最近的历史任务
			MonitorTaskSilentRequest rTaskk = new MonitorTaskSilentRequest();
			rTaskk.setSiteCode(siteCode);
			rTaskk.setContractIds(ids);
			rTaskk.setEndNowTime(DateUtils.formatStandardDateTime(new Date()));
			//时间排序
			List<QueryOrder> qList = new ArrayList<QueryOrder>();
			QueryOrder queryOrder=new QueryOrder("end_date",QueryOrderType.DESC);
			qList.add(queryOrder);
			rTaskk.setQueryOrderList(qList);
			List<MonitorTaskSilent> listt =  monitorTaskSilentServiceImpl.queryList(rTaskk);
			if(listt.size()>0){
				mTask = listt.get(0);
				//使用  databaseInfoId 做标识  
				//-12 代表 当前时间处在  执行合同中的  开始结束时间内 , 但是当前时间之前 合同时间之内 有安全任务执行
				mTask.setDatabaseInfoId(-12);
			}else{
				mTask.setDatabaseInfoId(-55);
			}
		}

		return mTask;
	}
	
	/**
	 * linhb@ucap.com.cn	
	 * 查询时   过期不过期的数据  都显示
	 * @param siteCode
	 * @param stu 
	 * @return
	 */
	public List<MonitorTaskSilent> getDateDatas(String siteCode,String endNow){
		List<MonitorTaskSilent> lSilent = new ArrayList<MonitorTaskSilent>();
		List<Integer> ids = new ArrayList<Integer>();
		//填报单位获取  合同
		ContractInfoRequest conRequest = new ContractInfoRequest();
		conRequest.setSiteCode(siteCode);//
		conRequest.setTypeFlag(1);//非抽查合同
		Integer[] conStatues = { 1, 2 };
		conRequest.setExecuteStatusArr(conStatues);//合同状态为 1-服务中 2 已完成
		List<ContractInfo> conList = contractInfoServiceImpl.queryList(conRequest);
		for (ContractInfo contractInfo : conList) {
			ids.add(contractInfo.getId());
		}
		
		// 组织单位的合同   下属可以看得  
		ContractInfoRequest cRequest = new ContractInfoRequest();
		cRequest.setSiteCode(siteCode);
		cRequest.setTypeFlag(1);//非抽查合同
		cRequest.setExecuteStatusArr(conStatues);//合同状态为 1-服务中 2 已完成
		cRequest.setIsSearchTb(1);//组织单位购买的安全业务  填报单位可以查看
		List<ContractInfo> cList  = contractInfoServiceImpl.queryByParentCon(conRequest);
		for (ContractInfo contractInfo : cList) {
			ids.add(contractInfo.getId());
		}
		MonitorTaskSilentRequest rTask = new MonitorTaskSilentRequest();
		if(ids.size()>0){
			rTask.setContractIds(ids);
		}else{
			return lSilent;
		}
		rTask.setSiteCode(siteCode);
		
		List<MonitorTaskSilent> list =  monitorTaskSilentServiceImpl.queryList(rTask);
		
		for (MonitorTaskSilent monitorTaskSilent : list) {
			MonitorTaskSilent mSilent = new MonitorTaskSilent();
			mSilent.setStartD(DateUtils.formatStandardDate(monitorTaskSilent.getStartDate()));
			mSilent.setEndD(DateUtils.formatStandardDate(monitorTaskSilent.getEndDate()));
			lSilent.add(mSilent);
		}
		return lSilent;
	}
	
//	/**新产品信息**/
//	/**
//	 * linhb@ucap.com.cn
//	 * 
//	 * @param siteCode
//	 * @param stu
//	 *            1 合同执行中 2 已经完成
//	 * @return
//	 */
//	public MonitorTaskSilent getMonitorTaskDatas(String siteCode, int stu) {
//		// stu 1 有正在运行的 安全任务 3 有过期的任务数据
//
//		MonitorTaskSilent mTask = new MonitorTaskSilent();
//		if (0 == stu) {// 组织单位 跳转过来的 // 组织单位 状态 安全业务 执行中
//			MonitorTaskSilentRequest rTask = new MonitorTaskSilentRequest();
//			rTask.setSiteCode(siteCode);
//			rTask.setNowTime(DateUtils.formatStandardDateTime(new Date()));
//			List<QueryOrder> queryBlankList = new ArrayList<QueryOrder>();
//			// 时间排序
//			QueryOrder siteQueryOrder = new QueryOrder("start_date", QueryOrderType.ASC);
//			queryBlankList.add(siteQueryOrder);
//			rTask.setQueryOrderList(queryBlankList);
//			List<MonitorTaskSilent> list = monitorTaskSilentServiceImpl.queryList(rTask);
//			if (list.size() > 0) {
//				mTask = list.get(0);
//				mTask.setDatabaseInfoId(-11);// 使用 databaseInfoId 做标识 -11 代表
//												// 当前时间处在 任务时间内
//			} else {
//				mTask.setDatabaseInfoId(-55);
//			}
//			return mTask;
//		}
//
//		List<Integer> ids = new ArrayList<Integer>();
//		// 填报单位获取 产品
//		CrmProductsRequest crmReq = new CrmProductsRequest();
//		crmReq.setSiteCode(siteCode);
//		crmReq.setProductTypeArr(productTypeArr);
//		if (stu == 1) {
//			crmReq.setExecuteStatus(1);
//		} else {
//			Integer[] coStatues = { 1, 2 };
//			crmReq.setExecuteStatusArr(coStatues);// 合同状态为0-初始化 1-服务中
//		}
//		List<CrmProducts> crmlist = crmProductsServiceImpl.queryList(crmReq);
//
//		// 查询历史合同
//		crmReq.setProductTypeArr(null);
//		crmReq.setProtoContractCode(1); // 不为空的是历史合同 1
//		List<CrmProductsResponse> historyList = crmProductsServiceImpl.historyCrmProductsList(crmReq);
//
//		for (CrmProducts crm : crmlist) {
//			ids.add(crm.getId());
//		}
//		for (CrmProductsResponse crm : historyList) {
//			ids.add(crm.getId());
//		}
//
//		// 组织单位的合同 下属可以看得
//		CrmProductsRequest crmSeReq = new CrmProductsRequest();
//		crmSeReq.setSiteCode(siteCode);
//		if (stu == 1) {
//			crmSeReq.setExecuteStatus(1);
//		} else {
//			Integer[] coStatues = { 1, 2 };
//			crmSeReq.setExecuteStatusArr(coStatues);// 合同状态为0-初始化 1-服务中
//		}
//		crmSeReq.setProductTypeArr(productTypeArr);
//		crmSeReq.setIsSearchTb(1);// 组织单位购买的安全业务 填报单位可以查看
//		List<CrmProductsResponse> crmSelist = crmProductsServiceImpl.getSecurityProducts(crmSeReq);
//
//		// 查询历史合同
//		crmSeReq.setProductTypeArr(null);
//		crmSeReq.setProtoContractCode(1); // 不为空的是历史合同 1
//		List<CrmProductsResponse> historySeList = crmProductsServiceImpl.historyCrmProductsList(crmSeReq);
//
//		crmSelist.addAll(historySeList);
//		for (CrmProductsResponse crmSe : crmSelist) {
//			ids.add(crmSe.getId());
//		}
//		if (ids.size() < 1) {
//			mTask.setDatabaseInfoId(-55);
//			return mTask;
//		}
//
//		if (1 == stu) {// 任务正在运行中
//			MonitorTaskSilentRequest rTask = new MonitorTaskSilentRequest();
//			rTask.setSiteCode(siteCode);
//			rTask.setNowTime(DateUtils.formatStandardDateTime(new Date()));
//
//			rTask.setContractIds(ids);
//			List<QueryOrder> queryBlankList = new ArrayList<QueryOrder>();
//			// 时间排序
//			QueryOrder siteQueryOrder = new QueryOrder("start_date", QueryOrderType.ASC);
//			queryBlankList.add(siteQueryOrder);
//			rTask.setQueryOrderList(queryBlankList);
//			List<MonitorTaskSilent> list = monitorTaskSilentServiceImpl.queryList(rTask);
//			if (list.size() > 0) {
//				mTask = list.get(0);
//				mTask.setDatabaseInfoId(-11);// 使用 databaseInfoId 做标识 -11 代表
//												// 当前时间处在 任务时间内
//			} else {
//				mTask.setDatabaseInfoId(-55);
//			}
//		} else {// 获取最近的历史任务
//			MonitorTaskSilentRequest rTaskk = new MonitorTaskSilentRequest();
//			rTaskk.setSiteCode(siteCode);
//			rTaskk.setContractIds(ids);
//			rTaskk.setEndNowTime(DateUtils.formatStandardDateTime(new Date()));
//			// 时间排序
//			List<QueryOrder> qList = new ArrayList<QueryOrder>();
//			QueryOrder queryOrder = new QueryOrder("end_date", QueryOrderType.DESC);
//			qList.add(queryOrder);
//			rTaskk.setQueryOrderList(qList);
//			List<MonitorTaskSilent> listt = monitorTaskSilentServiceImpl.queryList(rTaskk);
//			if (listt.size() > 0) {
//				mTask = listt.get(0);
//				// 使用 databaseInfoId 做标识
//				// -12 代表 当前时间处在 执行合同中的 开始结束时间内 , 但是当前时间之前 合同时间之内 有安全任务执行
//				mTask.setDatabaseInfoId(-12);
//			} else {
//				mTask.setDatabaseInfoId(-55);
//			}
//		}
//
//		return mTask;
//	}
//
//	/**
//	 * linhb@ucap.com.cn 查询时 过期不过期的数据 都显示
//	 * 
//	 * @param siteCode
//	 * @param stu
//	 * @return
//	 */
//	public List<MonitorTaskSilent> getDateDatas(String siteCode, String endNow) {
//		List<MonitorTaskSilent> lSilent = new ArrayList<MonitorTaskSilent>();
//		List<Integer> ids = new ArrayList<Integer>();
//		// 填报单位获取 合同
//		CrmProductsRequest crmReq = new CrmProductsRequest();
//		crmReq.setSiteCode(siteCode);
//		crmReq.setProductTypeArr(productTypeArr);
//		Integer[] conStatues = { 1, 2 };
//		crmReq.setExecuteStatusArr(conStatues);// 合同状态为0-初始化 1-服务中
//		List<CrmProducts> crmlist = crmProductsServiceImpl.queryList(crmReq);
//
//		// 查询历史合同
//		crmReq.setProductTypeArr(null);
//		crmReq.setProtoContractCode(1); // 不为空的是历史合同 1
//		List<CrmProductsResponse> historyList = crmProductsServiceImpl.historyCrmProductsList(crmReq);
//
//		for (CrmProducts crm : crmlist) {
//			ids.add(crm.getId());
//		}
//		for (CrmProductsResponse crm : historyList) {
//			ids.add(crm.getId());
//		}
//
//		// 组织单位的合同 下属可以看得
//		CrmProductsRequest crmSeReq = new CrmProductsRequest();
//		crmSeReq.setSiteCode(siteCode);
//		crmSeReq.setExecuteStatusArr(conStatues);// 合同状态为0-初始化 1-服务中
//		crmSeReq.setIsSearchTb(1);// 组织单位购买的安全业务 填报单位可以查看
//		crmSeReq.setProductTypeArr(productTypeArr);
//		List<CrmProductsResponse> crmSelist = crmProductsServiceImpl.getSecurityProducts(crmSeReq);
//		// 查询历史合同
//		crmSeReq.setProductTypeArr(null);
//		crmSeReq.setProtoContractCode(1); // 不为空的是历史合同 1
//		List<CrmProductsResponse> historySeList = crmProductsServiceImpl.historyCrmProductsList(crmSeReq);
//
//		crmSelist.addAll(historySeList);
//		for (CrmProductsResponse crmSe : crmSelist) {
//			ids.add(crmSe.getId());
//		}
//
//		MonitorTaskSilentRequest rTask = new MonitorTaskSilentRequest();
//		if (ids.size() > 0) {
//			rTask.setContractIds(ids);
//		} else {
//			return lSilent;
//		}
//		rTask.setSiteCode(siteCode);
//
//		List<MonitorTaskSilent> list = monitorTaskSilentServiceImpl.queryList(rTask);
//
//		for (MonitorTaskSilent monitorTaskSilent : list) {
//			MonitorTaskSilent mSilent = new MonitorTaskSilent();
//			mSilent.setStartD(DateUtils.formatStandardDate(monitorTaskSilent.getStartDate()));
//			mSilent.setEndD(DateUtils.formatStandardDate(monitorTaskSilent.getEndDate()));
//			lSilent.add(mSilent);
//		}
//		return lSilent;
//	}
//	

	
	/**
	 * @Title: 获取问题总数
	 * @Description:
	 * @author linhb@ucap.com.cn	2016-11-4上午10:19:49
	 */
	public void getWeaks(){
		Map<String,Object> outMap=new HashMap<String, Object>();
		try {
			String siteCode = getCurrentUserInfo().getChildSiteCode();//组织单位跳转
			if (StringUtils.isEmpty(siteCode)) {
				siteCode = getCurrentUserInfo().getSiteCode();
			}
			String type = request.getParameter("type");
			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");
			String flag = request.getParameter("flag");
			//查询monitor_fragility 网站脆弱性  脆弱性类型（0:全部、1：sql注入、2：XSS脚本、3：CGI、4：CSRF、5：应用漏洞、6：表单破解）
			MonitorFragilityRequest monitorFragility=new MonitorFragilityRequest();
			monitorFragility.setSiteCode(siteCode);
			monitorFragility.setType(Integer.valueOf(type));
			monitorFragility.setPageSize(Integer.MAX_VALUE);
			if(!"1".equals(flag)){
				List<MonitorTaskSilent> list = getDateDatas(siteCode,"");
				if(list.size()>0){
					monitorFragility.setStartEndDates(list);
				}
			}
			//时间排序
			if(StringUtils.isNotEmpty(startDate)){
				monitorFragility.setStartDate(startDate);
			}
			if(StringUtils.isNotEmpty(endDate)){
				monitorFragility.setEndDate(endDate);
			}
			if(StringUtils.isEmpty(startDate) && StringUtils.isEmpty(endDate)){
				monitorFragility.setYesFlag(DateUtils.getYesterdayStr());
			}
			List<MonitorFragility>  mList =monitorFragilityServiceImpl.queryList(monitorFragility);
			
			outMap.put("datalist", mList);
			outMap.put("success", "true");
			writerPrint(JSONObject.fromObject(outMap).toString());	
		} catch (Exception e) {
			e.printStackTrace();
			outMap.put("success", "false");
			outMap.put("errorMsg", "查询数据异常！");
			writerPrint(JSONObject.fromObject(outMap).toString());
		}
	}
	/**
	 * @Title: 获取网站挂马
	 * @Description:
	 * @author linhb@ucap.com.cn	2016-11-4上午10:19:49
	 */
	public void getHorses(){
		Map<String,Object> outMap=new HashMap<String, Object>();
		try {
			String siteCode = getCurrentUserInfo().getChildSiteCode();//组织单位跳转
			if (StringUtils.isEmpty(siteCode)) {
				siteCode = getCurrentUserInfo().getSiteCode();
			}
			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");
			String flag = request.getParameter("flag");
			MonitorTrojanRequest monitorTrojan=new MonitorTrojanRequest();
			monitorTrojan.setSiteCode(siteCode);
			monitorTrojan.setPageSize(Integer.MAX_VALUE);
			if(!"1".equals(flag)){
				List<MonitorTaskSilent> list = getDateDatas(siteCode,"");
				if(list.size()>0){
					monitorTrojan.setStartEndDates(list);
				}
			}
			//时间排序
			if(StringUtils.isNotEmpty(startDate)){
				monitorTrojan.setStartDate(startDate);
			}
			if(StringUtils.isNotEmpty(endDate)){
				monitorTrojan.setEndDate(endDate);
			}
			if(StringUtils.isEmpty(startDate) && StringUtils.isEmpty(endDate)){
				monitorTrojan.setYesFlag(DateUtils.getYesterdayStr());
			}
			List<MonitorTrojan>  mList =monitorTrojanServiceImpl.queryList(monitorTrojan);
			
			outMap.put("datalist", mList);
			outMap.put("success", "true");
			writerPrint(JSONObject.fromObject(outMap).toString());	
		} catch (Exception e) {
			e.printStackTrace();
			outMap.put("success", "false");
			outMap.put("errorMsg", "查询数据异常！");
			writerPrint(JSONObject.fromObject(outMap).toString());
		}
	}
	
	/**
	 * @Title: 获取  变更/篡改监测
	 * @Description:
	 * @author linhb@ucap.com.cn	2016-11-4上午10:19:49
	 */
	public void getUpdateDatas(){
		Map<String,Object> outMap=new HashMap<String, Object>();
		try {
			String siteCode = getCurrentUserInfo().getChildSiteCode();//组织单位跳转
			if (StringUtils.isEmpty(siteCode)) {
				siteCode = getCurrentUserInfo().getSiteCode();
			}
			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");
			String flag = request.getParameter("flag");
			MonitorTamperRequest monitorTamper=new MonitorTamperRequest();
			monitorTamper.setSiteCode(siteCode);
			monitorTamper.setPageSize(Integer.MAX_VALUE);
			if(!"1".equals(flag)){
				List<MonitorTaskSilent> list = getDateDatas(siteCode,"");
				if(list.size()>0){
					monitorTamper.setStartEndDates(list);
				}
			}
			//时间排序
			if(StringUtils.isNotEmpty(startDate)){
				monitorTamper.setStartDate(startDate);
			}
			if(StringUtils.isNotEmpty(endDate)){
				monitorTamper.setEndDate(endDate);
			}
			if(StringUtils.isEmpty(startDate) && StringUtils.isEmpty(endDate)){
				monitorTamper.setYesFlag(DateUtils.getYesterdayStr());
			}
			List<MonitorTamper> mList =monitorTamperServiceImpl.queryList(monitorTamper);
			
			outMap.put("datalist", mList);
			outMap.put("success", "true");
			writerPrint(JSONObject.fromObject(outMap).toString());	
		} catch (Exception e) {
			e.printStackTrace();
			outMap.put("success", "false");
			outMap.put("errorMsg", "查询数据异常！");
			writerPrint(JSONObject.fromObject(outMap).toString());
		}
	}
	
	/**
	 * @Title: 获取暗链
	 * @Description:
	 * @author linhb@ucap.com.cn	2016-11-4上午10:19:49
	 */
	public void getDarkDatas(){
		Map<String,Object> outMap=new HashMap<String, Object>();
		try {
			String siteCode = getCurrentUserInfo().getChildSiteCode();//组织单位跳转
			if (StringUtils.isEmpty(siteCode)) {
				siteCode = getCurrentUserInfo().getSiteCode();
			}
			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");
			String flag = request.getParameter("flag");
			MonitorDarkChainRequest monitorDarkChain=new MonitorDarkChainRequest();
			monitorDarkChain.setSiteCode(siteCode);
			monitorDarkChain.setPageSize(Integer.MAX_VALUE);
			if(!"1".equals(flag)){
				List<MonitorTaskSilent> list = getDateDatas(siteCode,"");
				if(list.size()>0){
					monitorDarkChain.setStartEndDates(list);
				}
			}
			//时间排序
			if(StringUtils.isNotEmpty(startDate)){
				monitorDarkChain.setStartDate(startDate);
			}
			if(StringUtils.isNotEmpty(endDate)){
				monitorDarkChain.setEndDate(endDate);
			}
			if(StringUtils.isEmpty(startDate) && StringUtils.isEmpty(endDate)){
				monitorDarkChain.setYesFlag(DateUtils.getYesterdayStr());
			}
			List<MonitorDarkChain> mList =monitorDarkChainServiceImpl.queryList(monitorDarkChain);
			
			outMap.put("datalist", mList);
			outMap.put("success", "true");
			writerPrint(JSONObject.fromObject(outMap).toString());	
		} catch (Exception e) {
			e.printStackTrace();
			outMap.put("success", "false");
			outMap.put("errorMsg", "查询数据异常！");
			writerPrint(JSONObject.fromObject(outMap).toString());
		}
	}
	
	/**
	 * @Title: 获取网站泄露
	 * @Description:
	 * @author linhb@ucap.com.cn	2016-11-4上午10:19:49
	 */
	public void getLeakDatas(){
		Map<String,Object> outMap=new HashMap<String, Object>();
		try {
			String siteCode = getCurrentUserInfo().getChildSiteCode();//组织单位跳转
			if (StringUtils.isEmpty(siteCode)) {
				siteCode = getCurrentUserInfo().getSiteCode();
			}
			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");
			String type = request.getParameter("type");
			String flag = request.getParameter("flag");
			MonitorLeakedRequest monitorLeaked=new MonitorLeakedRequest();
			monitorLeaked.setSiteCode(siteCode);
			monitorLeaked.setPageSize(Integer.MAX_VALUE);
			if(!"1".equals(flag)){
				List<MonitorTaskSilent> list = getDateDatas(siteCode,"");
				if(list.size()>0){
					monitorLeaked.setStartEndDates(list);
				}
			}
			//时间排序
			if(StringUtils.isNotEmpty(startDate)){
				monitorLeaked.setStartDate(startDate);
			}
			if(StringUtils.isNotEmpty(endDate)){
				monitorLeaked.setEndDate(endDate);
			}
			if(StringUtils.isNotEmpty(type)){
				monitorLeaked.setType(type);
			}
			if(StringUtils.isEmpty(startDate) && StringUtils.isEmpty(endDate)){
				monitorLeaked.setYesFlag(DateUtils.getYesterdayStr());
			}
			List<MonitorLeaked> mList =monitorLeakedServiceImpl.queryList(monitorLeaked);
			
			outMap.put("datalist", mList);
			outMap.put("success", "true");
			writerPrint(JSONObject.fromObject(outMap).toString());	
		} catch (Exception e) {
			e.printStackTrace();
			outMap.put("success", "false");
			outMap.put("errorMsg", "查询数据异常！");
			writerPrint(JSONObject.fromObject(outMap).toString());
		}
	}
	
	public String getName(String startDate,String endDate,String level1,String level2){
		String name ="";
		name+=level1;
		if(StringUtils.isNotEmpty(level2)){
			name+="-"+level2;
		}
		if(StringUtils.isEmpty(startDate) && StringUtils.isEmpty(endDate)){
			name+="(昨天).xls";
		}else{
			name+="("+startDate.replaceAll("-","")+"-"+endDate.replaceAll("-","")+").xls";
		}
		return name;
	}
	public String getTitle(String startDate,String endDate,String level1,String level2){
		String name ="";
		name+=level1;
		if(StringUtils.isNotEmpty(level2)){
			name+="-"+level2;
		}
		if(StringUtils.isEmpty(startDate) && StringUtils.isEmpty(endDate)){
			name+="(昨天)";
		}else{
			name+="("+startDate.replaceAll("-","")+"-"+endDate.replaceAll("-","")+")";
		}
		return name;
	}
	
	
	/**
	 * @Description: 安全问题  excel导出
	 * @author linhb --- 2016-11-8上午11:42:02
	 */
	public void getExcel(){
		try {
			//获取页面参数
			String titleOne =request.getParameter("titleOne");//
			String startDate =request.getParameter("startDate");//
			String endDate   =request.getParameter("endDate");//
			String flag = request.getParameter("flag");
			//从session中获取10位填报单位网站标识码
			String siteCode = getCurrentUserInfo().getChildSiteCode();//组织单位跳转
			if (StringUtils.isEmpty(siteCode)) {
				siteCode = getCurrentUserInfo().getSiteCode();
			}
			if(StringUtils.isEmpty(siteCode)){
				siteCode=getCurrentUserInfo().getSiteCode();
			}
			String fileName = "";
			String title = ""; 
			
			//封装数据中查询的结果
			ArrayList<Object[]> list = new ArrayList<Object[]>();
			if("1".equals(titleOne)){//网站脆弱性
				//excel标题  // 网站脆弱性 1：sql注入、2：XSS脚本、3：CGI、4：CSRF、5：应用漏洞、6：表单破解）
				String type =request.getParameter("type");//
				String isShow   =request.getParameter("isShow");//
				if(type.equals("1")){
					fileName=getName(startDate,endDate,"网站脆弱性","SQL注入");
					title = getTitle(startDate,endDate,"网站脆弱性","SQL注入");
				}else if(type.equals("2")){
					fileName=getName(startDate,endDate,"网站脆弱性","XSS脚本");
					title = getTitle(startDate,endDate,"网站脆弱性","XSS脚本");
				}else if(type.equals("3")){
					fileName=getName(startDate,endDate,"网站脆弱性","CGI");
					title = getTitle(startDate,endDate,"网站脆弱性","CGI");
				}else if(type.equals("4")){
					fileName=getName(startDate,endDate,"网站脆弱性","CSRF");
					title = getTitle(startDate,endDate,"网站脆弱性","CSRF");
				}else if(type.equals("5")){
					fileName=getName(startDate,endDate,"网站脆弱性","应用漏洞");
					title = getTitle(startDate,endDate,"网站脆弱性","应用漏洞");
				}else if(type.equals("6")){
					fileName=getName(startDate,endDate,"网站脆弱性","表单破解");
					title = getTitle(startDate,endDate,"网站脆弱性","表单破解");
				}
				MonitorFragilityRequest monitorFragility=new MonitorFragilityRequest();
				monitorFragility.setSiteCode(siteCode);
				monitorFragility.setType(Integer.valueOf(type));
				monitorFragility.setPageSize(Integer.MAX_VALUE);
				if(!"1".equals(flag)){
					List<MonitorTaskSilent> lists = getDateDatas(siteCode,"");
					if(lists.size()>0){
						monitorFragility.setStartEndDates(lists);
					}
				}
				//时间排序
				if(StringUtils.isNotEmpty(startDate)){
					monitorFragility.setStartDate(startDate);
				}
				if(StringUtils.isNotEmpty(endDate)){
					monitorFragility.setEndDate(endDate);
				}
				if(StringUtils.isEmpty(startDate) && StringUtils.isEmpty(endDate)){
					monitorFragility.setYesFlag(DateUtils.getYesterdayStr());
				}
				List<MonitorFragility>  mList =monitorFragilityServiceImpl.queryList(monitorFragility);
				if("1".equals(isShow)){
					Object[] weakList2 = new Object[]{"序号","级别","具体类型","问题URL","方法","参数","扫描时间"};
					list.add(weakList2);
					
					if(mList!=null && mList.size()>0){
						for(int i=0;i<mList.size();i++){
							MonitorFragility mFragility=mList.get(i);
							Object[] obj = new Object[7];
							obj[0]=i+1;
							
							int  level=mFragility.getLevel();//
							if(level == 1){
								obj[1]="低级";
							}else if(level == 2){
								obj[1]="高级";
							}else{
								obj[1]="";
							}
							
							// 具体类型（1.配置风险，2.信息泄露）
							if(1 == mFragility.getLoopholeType()){
								obj[2]="配置风险";
							}else if(2 == mFragility.getLoopholeType()){
								obj[2]="信息泄露";
							}else{
								obj[2]="";
							}
							if(StringUtils.isNotEmpty(mFragility.getPurl())){
								obj[3]=mFragility.getPurl();//问题url
							}else{
								obj[3]="";//
							}
							
							if(StringUtils.isNotEmpty(mFragility.getMethod())){
								obj[4]=mFragility.getMethod();//
							}else{
								obj[4]="";//
							}
							
							
							if(StringUtils.isNotEmpty(mFragility.getParam())){
								obj[5]=mFragility.getParam();
							}else{
								obj[5]="";//
							}
							if(StringUtils.isNotEmpty(mFragility.getStime())){
								if(mFragility.getStime().length()>19){
									obj[6]=mFragility.getStime().substring(0, 19);
								}
							}else{
								obj[6]="";//
							}
							list.add(obj);
						}
					}
					
				}else{
					Object[] weakList1 = new Object[]{"序号","级别","具体类型","问题URL","方法","扫描时间"};
					list.add(weakList1);
					
					if(mList!=null && mList.size()>0){
						for(int i=0;i<mList.size();i++){
							MonitorFragility mFragility=mList.get(i);
							Object[] obj = new Object[6];
							obj[0]=i+1;
							
							int  level=mFragility.getLevel();//
							if(level == 1){
								obj[1]="低级";
							}else if(level == 2){
								obj[1]="高级";
							}else{
								obj[1]="";
							}
							
							// 具体类型（1.配置风险，2.信息泄露）
							if(1 == mFragility.getLoopholeType()){
								obj[2]="配置风险";
							}else if(2 == mFragility.getLoopholeType()){
								obj[2]="信息泄露";
							}else{
								obj[2]="";
							}
							if(StringUtils.isNotEmpty(mFragility.getPurl())){
								obj[3]=mFragility.getPurl();//问题url
							}else{
								obj[3]="";//
							}
							
							if(StringUtils.isNotEmpty(mFragility.getMethod())){
								obj[4]=mFragility.getMethod();//
							}else{
								obj[4]="";//
							}
							
							
							if(StringUtils.isNotEmpty(mFragility.getStime())){
								if(mFragility.getStime().length()>19){
									obj[5]=mFragility.getStime().substring(0, 19);
								}
							}else{
								obj[5]="";//
							}
							list.add(obj);
						}
					}
					
					
				}
				
			}else if("2".equals(titleOne)){
				//网站挂马
				fileName=getName(startDate,endDate,"网站挂马","");
				title = getTitle(startDate,endDate,"网站挂马","");
				MonitorTrojanRequest monitorTrojan=new MonitorTrojanRequest();
				monitorTrojan.setSiteCode(siteCode);
				monitorTrojan.setPageSize(Integer.MAX_VALUE);
				if(!"1".equals(flag)){
					List<MonitorTaskSilent> lists = getDateDatas(siteCode,"");
					if(lists.size()>0){
						monitorTrojan.setStartEndDates(lists);
					}
				}
				//时间排序
				if(StringUtils.isNotEmpty(startDate)){
					monitorTrojan.setStartDate(startDate);
				}
				if(StringUtils.isNotEmpty(endDate)){
					monitorTrojan.setEndDate(endDate);
				}
				if(StringUtils.isEmpty(startDate) && StringUtils.isEmpty(endDate)){
					monitorTrojan.setYesFlag(DateUtils.getYesterdayStr());
				}
				List<MonitorTrojan>  mList =monitorTrojanServiceImpl.queryList(monitorTrojan);
				Object[] weakList2 = new Object[]{"序号","级别","具体类型","问题URL","方法","参数","扫描时间"};
				list.add(weakList2);
				
				if(mList!=null && mList.size()>0){
					for(int i=0;i<mList.size();i++){
						MonitorTrojan mFragility=mList.get(i);
						Object[] obj = new Object[7];
						obj[0]=i+1;
						
						int  level=mFragility.getLevel();//
						if(level == 1){
							obj[1]="低级";
						}else if(level == 2){
							obj[1]="高级";
						}else{
							obj[1]="";
						}
						
						// 具体类型（1.配置风险，2.信息泄露）
						if(1 == mFragility.getLoopholeType()){
							obj[2]="配置风险";
						}else if(2 == mFragility.getLoopholeType()){
							obj[2]="信息泄露";
						}else{
							obj[2]="";
						}
						if(StringUtils.isNotEmpty(mFragility.getPurl())){
							obj[3]=mFragility.getPurl();//问题url
						}else{
							obj[3]="";//
						}
						
						if(StringUtils.isNotEmpty(mFragility.getMethod())){
							obj[4]=mFragility.getMethod();//
						}else{
							obj[4]="";//
						}
						if(StringUtils.isNotEmpty(mFragility.getParam())){
							obj[5]=mFragility.getParam();
						}else{
							obj[5]="";//
						}
						if(StringUtils.isNotEmpty(mFragility.getStime())){
							if(mFragility.getStime().length()>19){
								obj[6]=mFragility.getStime().substring(0, 19);
							}
						}else{
							obj[6]="";//
						}
						list.add(obj);
					}
				}
					
			}else if("3".equals(titleOne)){
				//变更/篡改
				fileName=getName(startDate,endDate,"变更-篡改","");
				title = getTitle(startDate,endDate,"变更-篡改","");
				MonitorTamperRequest monitorTamper=new MonitorTamperRequest();
				monitorTamper.setSiteCode(siteCode);
				monitorTamper.setPageSize(Integer.MAX_VALUE);
				if(!"1".equals(flag)){
					List<MonitorTaskSilent> lists = getDateDatas(siteCode,"");
					if(lists.size()>0){
						monitorTamper.setStartEndDates(lists);
					}
				}
				//时间排序
				if(StringUtils.isNotEmpty(startDate)){
					monitorTamper.setStartDate(startDate);
				}
				if(StringUtils.isNotEmpty(endDate)){
					monitorTamper.setEndDate(endDate);
				}
				if(StringUtils.isEmpty(startDate) && StringUtils.isEmpty(endDate)){
					monitorTamper.setYesFlag(DateUtils.getYesterdayStr());
				}
				List<MonitorTamper> mList =monitorTamperServiceImpl.queryList(monitorTamper);
				Object[] weakList2 = new Object[]{"序号","问题URL","扫描时间"};
				list.add(weakList2);
				
				if(mList!=null && mList.size()>0){
					for(int i=0;i<mList.size();i++){
						MonitorTamper mFragility=mList.get(i);
						Object[] obj = new Object[3];
						obj[0]=i+1;
						
						if(StringUtils.isNotEmpty(mFragility.getPurl())){
							obj[1]=mFragility.getPurl();//问题url
						}else{
							obj[1]="";//
						}
						
						if(StringUtils.isNotEmpty(mFragility.getStime())){
							if(mFragility.getStime().length()>19){
								obj[2]=mFragility.getStime().substring(0, 19);
							}
						}else{
							obj[2]="";//
						}
						list.add(obj);
					}
				}
			}else if("4".equals(titleOne)){
				//网站暗链
				fileName=getName(startDate,endDate,"网站暗链","");
				title = getTitle(startDate,endDate,"网站暗链","");
				MonitorDarkChainRequest monitorDarkChain=new MonitorDarkChainRequest();
				monitorDarkChain.setSiteCode(siteCode);
				monitorDarkChain.setPageSize(Integer.MAX_VALUE);
				if(!"1".equals(flag)){
					List<MonitorTaskSilent> lists = getDateDatas(siteCode,"");
					if(lists.size()>0){
						monitorDarkChain.setStartEndDates(lists);
					}
				}
				//时间排序
				if(StringUtils.isNotEmpty(startDate)){
					monitorDarkChain.setStartDate(startDate);
				}
				if(StringUtils.isNotEmpty(endDate)){
					monitorDarkChain.setEndDate(endDate);
				}
				if(StringUtils.isEmpty(startDate) && StringUtils.isEmpty(endDate)){
					monitorDarkChain.setYesFlag(DateUtils.getYesterdayStr());
				}
				List<MonitorDarkChain> mList =monitorDarkChainServiceImpl.queryList(monitorDarkChain);
				Object[] weakList2 = new Object[]{"序号","级别","问题URL","扫描时间"};
				list.add(weakList2);
				
				if(mList!=null && mList.size()>0){
					for(int i=0;i<mList.size();i++){
						MonitorDarkChain mFragility=mList.get(i);
						Object[] obj = new Object[4];
						obj[0]=i+1;
						
						int  level=mFragility.getLevel();//
						if(level == 1){
							obj[1]="低级";
						}else if(level == 2){
							obj[1]="高级";
						}else{
							obj[1]="";
						}
						if(StringUtils.isNotEmpty(mFragility.getPurl())){
							obj[2]=mFragility.getPurl();//问题url
						}else{
							obj[2]="";//
						}
						
						if(StringUtils.isNotEmpty(mFragility.getStime())){
							if(mFragility.getStime().length()>19){
								obj[3]=mFragility.getStime().substring(0, 19);
							}
						}else{
							obj[3]="";//
						}
						list.add(obj);
					}
				}
			}else if("5".equals(titleOne)){
				//内容泄露
				String ltype =request.getParameter("type");//
				
				if(ltype.equals("1")){
					fileName=getName(startDate,endDate,"网站泄露","文件泄露");
					title = getTitle(startDate,endDate,"网站泄露","文件泄露");
				}else if(ltype.equals("2")){
					fileName=getName(startDate,endDate,"网站泄露","内容泄露");
					title = getTitle(startDate,endDate,"网站泄露","内容泄露");
				}
				MonitorLeakedRequest monitorLeaked=new MonitorLeakedRequest();
				monitorLeaked.setSiteCode(siteCode);
				monitorLeaked.setType(ltype);
				monitorLeaked.setPageSize(Integer.MAX_VALUE);
				if(!"1".equals(flag)){
					List<MonitorTaskSilent> lists = getDateDatas(siteCode,"");
					if(lists.size()>0){
						monitorLeaked.setStartEndDates(lists);
					}
				}
				//时间排序
				if(StringUtils.isNotEmpty(startDate)){
					monitorLeaked.setStartDate(startDate);
				}
				if(StringUtils.isNotEmpty(endDate)){
					monitorLeaked.setEndDate(endDate);
				}
				if(StringUtils.isEmpty(startDate) && StringUtils.isEmpty(endDate)){
					monitorLeaked.setYesFlag(DateUtils.getYesterdayStr());
				}
				List<MonitorLeaked> mList =monitorLeakedServiceImpl.queryList(monitorLeaked);
				Object[] weakList2 = new Object[]{"序号","级别","问题URL","扫描时间"};
				list.add(weakList2);
				
				if(mList!=null && mList.size()>0){
					for(int i=0;i<mList.size();i++){
						MonitorLeaked mFragility=mList.get(i);
						Object[] obj = new Object[4];
						obj[0]=i+1;
						
						int  level=mFragility.getLevel();//
						if(level == 1){
							obj[1]="低级";
						}else if(level == 2){
							obj[1]="高级";
						}else{
							obj[1]="";
						}
						if(StringUtils.isNotEmpty(mFragility.getPurl())){
							obj[2]=mFragility.getPurl();//问题url
						}else{
							obj[2]="";//
						}
						
						if(StringUtils.isNotEmpty(mFragility.getStime())){
							if(mFragility.getStime().length()>19){
								
								obj[3]=mFragility.getStime().substring(0, 19);
							}
						}else{
							obj[3]="";//
						}
						list.add(obj);
					}
				}
					
			}
			
			ExportExcel.tempReportExcel(fileName, title, list);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
