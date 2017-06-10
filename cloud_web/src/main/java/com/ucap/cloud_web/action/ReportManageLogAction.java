package com.ucap.cloud_web.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.publics.util.utils.DateUtils;
import com.publics.util.utils.StringUtils;
import com.ucap.cloud_web.UrlAdapterVar;
import com.ucap.cloud_web.constant.SendStateType;
import com.ucap.cloud_web.dto.DatabaseInfoRequest;
import com.ucap.cloud_web.dto.ReportManageLogRequest;
import com.ucap.cloud_web.entity.DatabaseInfo;
import com.ucap.cloud_web.entity.ReportWordResult;
import com.ucap.cloud_web.service.IDatabaseInfoService;
import com.ucap.cloud_web.service.IDicSiteService;
import com.ucap.cloud_web.service.IReportManageLogService;
import com.ucap.cloud_web.service.IReportWordResultService;
import com.ucap.cloud_web.util.DownFiles;
import com.ucap.cloud_web.util.ExportExcel;

/**
 * <p>Description: </p>报告管理页面
 * <p>@Package：com.ucap.cloud_web.action </p>
 * <p>Title: ReportManageLog </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：cuichx </p>
 * <p>@date：2015-11-25上午9:52:16 </p>
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class ReportManageLogAction extends BaseAction {
	/**
	 * log日志加载
	 */
	private static Log logger = LogFactory.getLog(ReportManageLogAction.class);
	@Autowired
	private IReportManageLogService reportManageLogServiceImpl;
	@Autowired
	private IReportWordResultService reportWordResultServiceImpl;

	@Autowired
	private IDicSiteService dicSiteServiceImpl;
	
	@Autowired
	private IDatabaseInfoService databaseInfoServiceImpl;
	@Autowired
	private UrlAdapterVar urlAdapterVar;
	Map<String, Object> result_map=new HashMap<String, Object>();
	/**
	 * @Description: 报告管理页面初始化--组织单位单位--所有
	 * @author cuichx --- 2015-11-25上午10:19:17     
	 * @return
	 */
	public String indexOrg(){
		return "success";
	}
	/**
	 * @Description: 报告管理页面初始化--组织单位单位--门户
	 * @author cuichx --- 2015-11-25上午10:19:17     
	 * @return
	 */
	public String indexOrgOwn(){
		DatabaseInfoRequest databaseInfoRequest = new DatabaseInfoRequest();
		databaseInfoRequest.setSiteCodeLike(getCurrentSiteCode());
		List<DatabaseInfo> queryList = databaseInfoServiceImpl.queryList(databaseInfoRequest);
		if(queryList.size()>0){
			DatabaseInfo databaseInfo = queryList.get(0);
			if(databaseInfo.getLevel().equals("1")){//省登录---省门户
				result_map.put("menuType", "5");
			}else if(databaseInfo.getLevel().equals("2")){//市登录--市门户
				result_map.put("menuType", "6");
			}else if(databaseInfo.getLevel().equals("3")){//县登录--县门户
				result_map.put("menuType", "8");
			}
		}
		return "success";
	}
	/**
	 * @Description: 报告管理页面初始化--组织单位单位--省部门页面
	 * @author cuichx --- 2015-11-25上午10:19:17     
	 * @return
	 */
	public String indexOrgSheng(){
		DatabaseInfoRequest databaseInfoRequest = new DatabaseInfoRequest();
		databaseInfoRequest.setSiteCodeLike(getCurrentSiteCode());
		List<DatabaseInfo> queryList = databaseInfoServiceImpl.queryList(databaseInfoRequest);
		if(queryList.size()>0){
			DatabaseInfo databaseInfo = queryList.get(0);
			if(databaseInfo.getLevel().equals("1")){//省登录---省部门
				result_map.put("menuType", "1");
			}
		}
		return "success";
	}
	/**
	 * @Description: 报告管理页面初始化--组织单位单位--市级页面
	 * @author cuichx --- 2015-11-25上午10:19:17     
	 * @return
	 */
	public String indexOrgShi(){
		DatabaseInfoRequest databaseInfoRequest = new DatabaseInfoRequest();
		databaseInfoRequest.setSiteCodeLike(getCurrentSiteCode());
		List<DatabaseInfo> queryList = databaseInfoServiceImpl.queryList(databaseInfoRequest);
		if(queryList.size()>0){
			DatabaseInfo databaseInfo = queryList.get(0);
			if(databaseInfo.getLevel().equals("1")){//省登录---市政府
				result_map.put("menuType", "2");
			}else{
				result_map.put("menuType", "4");//市登录，市部门
			}
		}
		return "success";
	}
	/**
	 * @Description: 报告管理页面初始化--组织单位单位--县
	 * @author cuichx --- 2015-11-25上午10:19:17     
	 * @return
	 */
	public String indexOrgXian(){
		DatabaseInfoRequest databaseInfoRequest = new DatabaseInfoRequest();
		databaseInfoRequest.setSiteCodeLike(getCurrentSiteCode());
		List<DatabaseInfo> queryList = databaseInfoServiceImpl.queryList(databaseInfoRequest);
		if(queryList.size()>0){
			DatabaseInfo databaseInfo = queryList.get(0);
			if(databaseInfo.getLevel().equals("1")){//省登录---县门户和县部门
				result_map.put("menuType", "3");
			}else if(databaseInfo.getLevel().equals("2")){
				result_map.put("menuType", "7");//市登录--县门户和县部门
			}else{
				result_map.put("menuType", "9");//县登录---县部门
			}
		}
		return "success";
	}
	/** @Description: 报告管理页面周期控件初始化--组织单位单位
	 * @author sunjiaqi --- 2015-12-3上午11:31:53                
	*/
	public void initPeriod(){
		
//		String menuType=request.getParameter("menuType");
		String comboType=request.getParameter("comboType");//套餐类型
		String currentSiteCode = getCurrentSiteCode();
		try {
//			List<DatabaseInfo> currentNextSiteCode = new ArrayList<DatabaseInfo>();
//			currentNextSiteCode=queryDatebaseInfoListByType(menuType, currentSiteCode);
			List<Map<String,Object>> returnList=new ArrayList<Map<String,Object>>();
			List<String> siteCodeList=new ArrayList<String>();
			siteCodeList.add(currentSiteCode);
			/*//查询同一订单下的siteCode,将其封装到一个集合中
			if(currentNextSiteCode!=null && currentNextSiteCode.size()>0){
				for (DatabaseInfo databaseInfo : currentNextSiteCode) {
					siteCodeList.add(databaseInfo.getSiteCode());
				}
			}*/
			returnList=getTimeTooLList(siteCodeList,comboType);
			logger.info("组织单位报告管理_所有_周期控件:"+returnList);
			writerPrint(JSONArray.fromObject(returnList).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * @Description: 列表数据查询
	 * @author cuichx --- 2015-12-10上午11:39:09
	 */
	public void getMessageList(){
		List<Map<String,Object>>  resultList=new ArrayList<Map<String,Object>>();
		try {
			//页面传递过来的周期期数
			String pagePeriodNum = request.getParameter("periodNum");
			//套餐类型
			String comboType=request.getParameter("comboType");
			int periodNum=0;
			
			//封装查询条件
			Map<String, Object> paramMap=new HashMap<String, Object>();
			if(StringUtils.isNotEmpty(comboType)){
				paramMap.put("comboType", comboType);
			}else{
				paramMap.put("comboType",3);//默认为普通套餐
			}
			//如果该字段为空，说明是页面初始化时，调用该方法，否则为点击周期控件调用该方法
			periodNum=Integer.valueOf(pagePeriodNum);
			if(periodNum>0){
				paramMap.put("periodNum", periodNum);
				String menuType=request.getParameter("menuType");

				String siteCode = getCurrentSiteCode();
				List<DatabaseInfo> currentNextSiteCode = new ArrayList<DatabaseInfo>();
				
				currentNextSiteCode = queryDatebaseInfoListByType(menuType, siteCode);
				
				paramMap.put("currentNextSiteCode", currentNextSiteCode);
				//paramMap.put("comboType", comboType);
				
				//根据网站标识码集合和服务周期期数,查询报告管理表和站点信息表
				List<ReportManageLogRequest> reportList=reportManageLogServiceImpl.queryByMap(paramMap);
				if(reportList!=null && reportList.size()>0){
					for (ReportManageLogRequest reportManageLogRequest : reportList) {
						Map<String, Object> map=new HashMap<String, Object>();
						map.put("siteCode", reportManageLogRequest.getSiteCode());//网站标识码
						map.put("siteName", reportManageLogRequest.getSiteName());//网站名称
						int siteTypeId=reportManageLogRequest.getDicSiteId();
						map.put("remark", dicSiteServiceImpl.get(siteTypeId).getRemark());//网站类型
						String sendTime = DateUtils.formatStandardDateTime(DateUtils.parseStandardDateTime(reportManageLogRequest.getSendTime()));
						map.put("sendTime", sendTime);//报告上次发送时间	
						map.put("sendState", reportManageLogRequest.getSendState());//报告上次发送状态
						map.put("pdfUrl", reportManageLogRequest.getPdfUrl());
						map.put("dicComboName",reportManageLogRequest.getDicComboId());//套餐id
						resultList.add(map);
					}
					logger.info("getMessageList_所有："+resultList);
				}
			}
			writerPrint(JSONArray.fromObject(resultList).toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * @Description: 通过网站标识码集合获取页面周期控件中的数据
	 * @author cuichx --- 2015-12-9下午5:46:05     
	 * @param siteCodeList
	 * @return
	 */
	public List<Map<String,Object>> getTimeTooLList(List<String> siteCodeList,String comboType){
		
		List<Map<String,Object>> timeToolList=new ArrayList<Map<String,Object>>();
/*		//根据网站标识码集合和当前时间查询站点信息表，获取套餐种类信息
		Map<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("idLists", siteCodeList);
		paramMap.put("comboId", comboType);
		paramMap.put("nowTime", DateUtils.formatStandardDateTime(new Date()));
		paramMap.put("status", 2);
		
		getServicePeriodList();
		List<DatabaseInfo> siteInfoList=databaseInfoServiceImpl.queryByMap(param);*/
		if(siteCodeList !=null && siteCodeList.size()>0){
			for(int i=0;i<siteCodeList.size();i++){
				Map<String,Object> map=new HashMap<String, Object>();
				map=timeTool(siteCodeList.get(i));
				//map.put("dicComboName",dicComboId);//套餐id
				timeToolList.add(map);
			}
		}
		return timeToolList;
	}
	
	/** @Description: 判断是否包含下载文件
	 * @author sunjiaqi --- 2015-12-16下午06:20:29                
	*/
	public void hasReport(){
		try {
			//周期期数
			String pagePeriodNum = request.getParameter("reportPeriodNum");
			//需要下载的siteCode集合
			String reportSitecode = request.getParameter("reportSitecode");
			int periodNum=0;
			Map<String, Object> paramMap=new HashMap<String, Object>();
			periodNum=Integer.valueOf(pagePeriodNum);
			paramMap.put("periodNum", periodNum);
			
			//将网站标识码字符串截取到数组内
			String[] siteCodeList=null;
			if(StringUtils.isNotEmpty(reportSitecode)){
				siteCodeList= reportSitecode.split(",");
				paramMap.put("siteCodeList", siteCodeList);
			}
			/**
			 * 防止站点信息表中出现siteCode重复的情况
			 * 		判断当前时间是否在站点的服务开始时间和结束时间之内
			 */
			paramMap.put("nowTime", DateUtils.formatStandardDateTime(new Date()));
			
			List<ReportWordResult> rwrList = reportWordResultServiceImpl.queryByMap(paramMap);
			if(rwrList !=null && rwrList.size()>0){
				writerPrint("true");
			}else{
				writerPrint("false");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** @Description: 批量导出报告
	 * @author sunjiaqi --- 2016-3-8下午04:39:48                
	*/
	public void batchDownloadReport(){
		try {
			//周期期数
			String pagePeriodNum = request.getParameter("reportPeriodNum");
			//需要下载的siteCode集合
			String reportSitecode = request.getParameter("reportSitecode");
			int periodNum=0;
			Map<String, Object> paramMap=new HashMap<String, Object>();
			periodNum=Integer.valueOf(pagePeriodNum);
			paramMap.put("periodNum", periodNum);
			
			//将网站标识码字符串截取到数组内
			String[] siteCodeList=null;
			if(StringUtils.isNotEmpty(reportSitecode)){
				siteCodeList= reportSitecode.split(",");
				paramMap.put("siteCodeList", siteCodeList);
			}
			/**
			 * 防止站点信息表中出现siteCode重复的情况
			 * 		判断当前时间是否在站点的服务开始时间和结束时间之内
			 */
			paramMap.put("nowTime", DateUtils.formatStandardDateTime(new Date()));
			
			List<String> fileNames = new ArrayList<String>();
			List<ReportWordResult> rwrList = reportWordResultServiceImpl.queryByMap(paramMap);
			if(rwrList !=null && rwrList.size()>0){
				for(int i=0;i<rwrList.size();i++){
					ReportWordResult rwr = rwrList.get(i);
					String path=ServletActionContext.getServletContext().getRealPath("/");
					String wordUrl=path+rwr.getWordUrl();
					String pdfUrl=path+rwr.getPdfUrl();
					String excelUrl=path+rwr.getExcelUrl();

					if(StringUtils.isNotEmpty(wordUrl)){
						fileNames.add(wordUrl);
					}
					if(StringUtils.isNotEmpty(pdfUrl)){
						fileNames.add(pdfUrl);
					}
					if(StringUtils.isNotEmpty(excelUrl)){
						fileNames.add(excelUrl);
					}
				}
			}
			if(fileNames.size()>0){
				DownFiles df = new DownFiles();
				Map<String, String> fileMap = df.getNamemap(fileNames);
				outPutFile(fileMap.get("filePath"),"报告导出.zip");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * @Description: excel导出
	 * @author sunjiang --- 2015年11月18日下午6:13:53
	 */
	public void batchDownloadExcel(){
		try {
				//周期期数
				String pagePeriodNum = request.getParameter("servicePeriodNumEx");
				//需要下载的siteCode集合
				String reportSitecode = request.getParameter("reportSitecodeEx");
				int periodNum=0;
				Map<String, Object> paramMap=new HashMap<String, Object>();
				periodNum=Integer.valueOf(pagePeriodNum);
				paramMap.put("periodNum", periodNum);
				String[] siteCodeList = reportSitecode.split(",");
				paramMap.put("siteCodeList", siteCodeList);
				/**
				 * 防止站点信息表中出现siteCode重复的情况
				 * 		判断当前时间是否在站点的服务开始时间和结束时间之内
				 */
				paramMap.put("nowTime", DateUtils.formatStandardDateTime(new Date()));
				//封装数据中查询的结果
				ArrayList<Object[]> list = new ArrayList<Object[]>();
				//excel标题
				Object[] obj1 = new Object[]{"序号","网站标识码","网站名称","网站类型","报告上次发送时间","报告上次发送状态"};
				list.add(obj1);

				String fileName = "报告管理-组织单位报告管理）("+DateUtils.formatStandardDate(new Date())+").xls";
				String title = "组织单位报告管理结果"; 
				
				//根据网站标识码集合和服务周期id,查询报告管理表和站点信息表
				List<ReportManageLogRequest> reportList=reportManageLogServiceImpl.queryByMap(paramMap);
				if(reportList!=null && reportList.size()>0){
					for (int i=0;i<reportList.size();i++) {
						ReportManageLogRequest reportManageLogRequest=reportList.get(i);
						String siteCode=reportManageLogRequest.getSiteCode();//网站标识码
						String siteName=reportManageLogRequest.getSiteName();//网站名称
						int siteTypeId=reportManageLogRequest.getDicSiteId();
						String remark=dicSiteServiceImpl.get(siteTypeId).getRemark();//网站类型
						String sendTime=reportManageLogRequest.getSendTime();//报告上次发送时间	
						Integer sendState=reportManageLogRequest.getSendState();//报告上次发送状态
						String SendStateName=(sendState==0?SendStateType.FAILURE.getName():SendStateType.SUCCESS.getName());
						
						Object[] obj = new Object[6];
						obj[0]=i+1;
						obj[1]=siteCode;
						obj[2]=siteName;
						obj[3]=remark;
						obj[4]=sendTime;
						obj[5]=SendStateName;
						
						list.add(obj);
					}
					ExportExcel.batchDownloadExcel(fileName, title, list);
				}else{
					writerPrint("");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		
	}
	
	/** @Description: 批量邮件督办
	 * @author sunjiaqi --- 2015-12-7下午02:52:34                
	 * @throws Exception 
	*/
//	public void sendEmail(){
//		//依据期号和选择的sitecode在reportWordResult表中获取对应的数据,
//		//并在websiteinfo里面查找对应的email地址发送邮件
//		try {
//			String parameters = request.getParameter("parameters");//获取选择的sitecode数据
//			Integer periodNum = Integer.parseInt(request.getParameter("periodNum"));//获取选择的周期number
//			Object[] idLists=null;
//			if(StringUtils.isNotEmpty(parameters)){
//				idLists=parameters.split(",");
//			}
//			//查询siteCode对应的网站信息的联系人
//			DatabaseInfoRequest databaseInfoRequest = new DatabaseInfoRequest();
//			List<DatabaseInfo> wiLsit = databaseInfoServiceImpl.queryList(databaseInfoRequest);
//			if(null != wiLsit && wiLsit.size()>0){
//				for (DatabaseInfo websiteInfo : wiLsit) {
//					String email = websiteInfo.getEmail2();//获取email地址
//					String siteName = websiteInfo.getName();//获取网站名称
//					String siteCode = websiteInfo.getSiteCode();
//					//report_word_result报告记录表里面获取对应的报告
//					//根据网站标识码集合和服务周期期数,查询报告管理表和站点信息表
//					Map<String, Object> map = new HashMap<String, Object>();
//					map.put("siteName", siteName);
//					ServicePeriodRequest servicePeriodRequest = new ServicePeriodRequest();
//					List<ServicePeriod> spLsit = servicePeriodServiceImpl.queryList(servicePeriodRequest);
//					ServicePeriod servicePeriod = spLsit.get(0);
//					map.put("perNumer", periodNum);
//					map.put("perStart", DateUtils.formatStandardMMDD_HHMMDateTime(servicePeriod.getStartDate()));
//					map.put("perEnd", DateUtils.formatStandardMMDD_HHMMDateTime(servicePeriod.getEndDate()));
//					ReportWordResultRequest reportWordResultRequest = new ReportWordResultRequest();
//					reportWordResultRequest.setSiteCode(websiteInfo.getSiteCode());
//					reportWordResultRequest.setServicePeriodId(servicePeriod.getId());
//					List<ReportWordResult> rwrList = reportWordResultServiceImpl.queryList(reportWordResultRequest);
//					if(rwrList!=null && rwrList.size()>0){
//						String path=ServletActionContext.getServletContext().getRealPath("/");
//						String wordUrl ="";
//						ReportWordResult rwr = rwrList.get(0);
//						wordUrl=path+rwr.getWordUrl();
//						// 发送邮件
//						List<String> fileNames = new ArrayList<String>();
//						if(wordUrl!=null && !wordUrl.equals("")){
//							fileNames.add(wordUrl);
//						}
////						boolean flag = SendEmail.sendEmail("网站云监测报告发送通知", "mail.ftl", map, email,fileNames);
//						ReportManageLogRequest reportManageLogRequest = new ReportManageLogRequest();
//						reportManageLogRequest.setSiteCode(websiteInfo.getSiteCode());
//						reportManageLogRequest.setServicePeriodId(servicePeriod.getId());
//						List<ReportManageLog>  rmList = reportManageLogServiceImpl.queryList(reportManageLogRequest);
//						ReportManageLog reportManageLog = rmList.get(0);
//						if(flag){
//							reportManageLog.setSendState(1);
//						}else{
//							reportManageLog.setSendState(0);
//						}
//					}
//				}
//			}
//			writerPrint("true");
//		} catch (Exception e) {
//			writerPrint("false");
//			e.printStackTrace();
//		}
//	}
	
	/** @Description: 填报单位登陆下载审核通过的word文件
	 * @author sunjiaqi --- 2016-5-13下午4:29:22                
	*/
	public void getCanSeeWordFile(){
		try {
			Integer fid = Integer.parseInt(request.getParameter("fid"));
			ReportWordResult reportWordResult = reportWordResultServiceImpl.get(fid);
			if(null!=reportWordResult){
				String wordUrl = reportWordResult.getWordUrl();
				String aliasName = reportWordResult.getAliasName();
				if(StringUtils.isNotEmpty(wordUrl)){
					wordUrl=urlAdapterVar.getJiXiaoWord()+wordUrl;
				}
				File file=new File(wordUrl);
				if(file.exists()){
					outPutFile(wordUrl,aliasName);
				}else{
					writerPrint("文件不存在");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	public void getWordFile(){
		String fileName = StringUtils.decode(request.getParameter("fileName"));
		String filePath = StringUtils.decode(request.getParameter("filePath"));
		
		outPutFile(filePath,fileName);
	}
	
	
	public void outPutFile(String filePath, String fileName) {
		InputStream ins = null;
		try {
			ins = new FileInputStream(new File(filePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			fileName = new String(fileName.getBytes("gbk"), "iso8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		BufferedInputStream bins = new BufferedInputStream(ins);// 放到缓冲流里面
		OutputStream outs = null;
		try {
			outs = response.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}// 获取文件输出IO流
		BufferedOutputStream bouts = new BufferedOutputStream(outs);
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/x-download");// 设置response内容的类型
		response.setHeader("Content-disposition", "attachment;filename="
				+ fileName); // 设置头部信息
		int bytesRead = 0;
		byte[] buffer = new byte[8192];
		// 开始向网络传输文件流
		try {
			while ((bytesRead = bins.read(buffer, 0, 8192)) != -1) {
				bouts.write(buffer, 0, bytesRead);
			}
			bouts.flush();
			ins.close();
			bins.close();
			outs.close();
			bouts.close();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
//			try {
//				File f = new File(filePath);
//				f.delete();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
		}
	}
	
	
	
	
	public Map<String, Object> getResult_map() {
		return result_map;
	}
	public void setResult_map(Map<String, Object> result_map) {
		this.result_map = result_map;
	}

	/**
	 * @Description: 报告管理pdf--组织单位单位--门户
	 * @author Nora --- 2015-11-25上午10:19:17     
	 * @return
	 */
	public String pdfIndex(){
		return "success";
	}

}
