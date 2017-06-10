package com.ucap.cloud_web.timer;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.publics.util.utils.DateUtils;
import com.publics.util.utils.PropertiesUtil;
import com.ucap.cloud_web.constant.AccountBindStatus;
import com.ucap.cloud_web.constant.ReceiveType;
import com.ucap.cloud_web.constant.TrueOrFalseType;
import com.ucap.cloud_web.dto.AccountBindInfoRequest;
import com.ucap.cloud_web.dto.ReportManageLogRequest;
import com.ucap.cloud_web.entity.ReportManageLog;
import com.ucap.cloud_web.service.IAccountBindInfoService;
import com.ucap.cloud_web.service.IReportManageLogService;
import com.ucap.cloud_web.servlet.message.WxSendData;
import com.ucap.cloud_web.servlet.message.WxSendModel;
import com.ucap.cloud_web.servlet.util.CommonUtils;

@Service("taskSendWXReport")
public class TaskSendWXReport {
	private static Logger logger = Logger.getLogger(TaskSendWXReport.class);

	@Autowired
	private IAccountBindInfoService accountBindInfoServiceImpl;
	@Autowired
	private IReportManageLogService reportManageLogServiceImpl;
	
	private static Properties prop = PropertiesUtil.getProperties("app.properties");
	
	//private String access_token="ge7Zn_VjpBwBvSH9byH70-SKwZ3roOHz0dp13K4jGg9CZBkAuQlQDbfe8cFIi3OLz9reBIZi42SBu1L-yUE5L1sH8hD6t6-4H-4Lk0M1XtsPDKfAHAIWD";
//	private  String access_token="";
//	//发送模板消息的接口
//	private String  requestUrl="https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+access_token;
	
	public  void getTaskSendWXReport(){
		logger.info("getTaskSendWXReport    start-----");
		/**
		 * <1>每隔30分钟，查询报告管理表，查询创建时间为当天日期时间的所有的siteCode,将其放到集合A中
		 * <2>遍历A集合，获取每个siteCode，先通过siteCode查询微信绑定账户表和客户信息表，
		 * 		如果存在记录，说明该填报单位是一个客户，直接发送报告管理模板消息，
		 * 		如果不存在，通过站点信息表+订单表+客户表+微信绑定账户信息表，获取对应的客户，将该客户去重复放到集合B中（里面是所有的组织单位客户）
		 * <3>遍历集合b,获取模板需要的统计数据，并发送模板信息
		 */
		//查询报告管理表，当天且未发送微信通知的的记录
		ReportManageLogRequest pmlr=new ReportManageLogRequest();
		pmlr.setSendWxState(TrueOrFalseType.FALSE.getCode());//未发送通知的
		pmlr.setCreateTime(DateUtils.formatStandardDate(new Date()));//当天时间（只查询报告管理中当天的数据）
		pmlr.setPageSize(Integer.MAX_VALUE);
		List<String> siteCodeList=new ArrayList<String>();//封装组织单位编码
		
		String access_token=CommonUtils.getTokenFromServlet();
		//发送模板消息的接口
		String  requestUrl="https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+access_token;
		
		List<ReportManageLog>  pmlrList=reportManageLogServiceImpl.queryList(pmlr);
		if(pmlrList!=null && pmlrList.size()>0){
			for(int i=0;i<pmlrList.size();i++){
				ReportManageLog pml=pmlrList.get(i);
				int sendState=pml.getSendState();//报告发送状态
//				int servicePId=pml.getServicePeriodId();//服务周期id
				
				//通过网站标识码查询微信绑定账户表和客户信息表
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("siteCode", pml.getSiteCode());
				map.put("status", AccountBindStatus.ACCOUNT_BIND.getCode());//状态为绑定
				map.put("localhostReportStatus", ReceiveType.IS_RECEIVE.getCode());//接收报告通知
				
				AccountBindInfoRequest  accBindInfoRequest=accountBindInfoServiceImpl.queryByMap(map);
				if(accBindInfoRequest!=null){//说明该填报单位为客户且接收报告通知，发送微信模板信息
					String name=accBindInfoRequest.getCompanyName();//网站名称
					String code=accBindInfoRequest.getSiteCode();//标识码
					

					
//					//根据周期id查询，服务周期表
//					ServicePeriod servicePeriod=servicePeriodServiceImpl.get(servicePId);
					int peroidNum=1;
					String peroidStr="第"+peroidNum+"期";
					
					Map<String, Object> paramMap=new HashMap<String, Object>();
					paramMap.put("openId", accBindInfoRequest.getOpenId());//客户微信号唯一标识
					paramMap.put("siteCode", code);//标识码
					paramMap.put("name", name);//客户名称
					paramMap.put("peroidStr",peroidStr);//期数
					paramMap.put("sendState", sendState);//报告发送状态
					
					String jsonStr=getSendModelTB(paramMap);
					//发送https请求
					CommonUtils.httpRequest(requestUrl, "POST", jsonStr);
					
					//更新报告管理日志信息表中的是否已经发送微信通知（0-否 1-是）更新为1-是
					pml.setSendWxState(TrueOrFalseType.TRUE.getCode());
					reportManageLogServiceImpl.update(pml);
					
				}else{
					//通过网站标识码，查询报告管理+站点信息表+订单表+客户表+微信绑定账户信息表，获取对应的客户
					map.put("newTime", DateUtils.formatStandardDateTime(new Date()));//在站点信息表中去重复
					map.put("sendWxState", TrueOrFalseType.FALSE.getCode());//未发送微信报告通知
					map.put("createTime", DateUtils.formatStandardDate(new Date()));//仅查询报告管理当天的报告
					//暂时注掉
//					List<AccountBindInfoRequest> accBindInfoList=accountBindInfoServiceImpl.queryBySiteCodeRP(map);
// 					if(accBindInfoList!=null && accBindInfoList.size()>0){
//						//判断客户集合中是否已经存在该客户了，如果不存在则添加到该集合中，如果存在则不添加
//						if(siteCodeList.size()==0){
//							siteCodeList.add(accBindInfoList.get(0).getSiteCode());
//						}else{
//							for(int j=0;j<siteCodeList.size();j++){
//								if(!siteCodeList.get(j).equals(accBindInfoList.get(0).getSiteCode())){
//									siteCodeList.add(accBindInfoList.get(0).getSiteCode());
//								}
//							}
//						}
// 					}
				}
			}
		}
		if(siteCodeList!=null && siteCodeList.size()>0){
			Map<String, Object> reportMap=new HashMap<String, Object>();
			reportMap.put("siteCodeList", siteCodeList);
			reportMap.put("status", AccountBindStatus.ACCOUNT_BIND.getCode());//状态为绑定
			reportMap.put("newTime", DateUtils.formatStandardDateTime(new Date()));//在站点信息表中去重复
			reportMap.put("sendWxState", TrueOrFalseType.FALSE.getCode());//未发送微信报告通知
			reportMap.put("createTime", DateUtils.formatStandardDate(new Date()));//仅查询报告管理当天的报告
//			List<AccountBindInfoRequest> bindList=accountBindInfoServiceImpl.queryReprotMsgLog(reportMap);
//			for(int j=0;j<bindList.size();j++){
//				AccountBindInfoRequest accBind=bindList.get(j);
//				String openId=accBind.getOpenId();//客户微信号唯一标识
//				String name=accBind.getCompanyName();//客户名称
//				//String code=bindList.get(j).getSiteCode();//客户编码
//				int siteNum=accBind.getSiteNum();//网站数
//				int reportNum=accBind.getReportNum();//报告数
//				int errorNum=accBind.getErrorNum();//报告发送发送失败数
//				int successNum=accBind.getSuccessNum();//报告发送成功个数
//				Map<String, Object> paramMap=new HashMap<String, Object>();
//				paramMap.put("openId", openId);
//				paramMap.put("name", name);
//				paramMap.put("siteNum", siteNum);
//				paramMap.put("reportNum", reportNum);
//				paramMap.put("errorNum", errorNum);
//				paramMap.put("successNum", successNum);
//				
//				String jsonStr=getSendModelORG(paramMap);
//				//发送https请求
//				CommonUtils.httpRequest(requestUrl, "POST", jsonStr);
//				
//				
//				//查询该客户下所有的未发送报告管理日志信息
//				ReportManageLogRequest reportRequest=new ReportManageLogRequest();
//				reportRequest.setSendWxState(TrueOrFalseType.FALSE.getCode());//未发送微信报告通知
//				
//				Map<String, Object> map=new HashMap<String, Object>();
//				map.put("siteCode", accBind.getSiteCode());
//				List<DatabaseInfo> currentNextSiteCode = databaseInfoServiceImpl.queryListDatabaseInfo(map);
//				reportRequest.setCurrentNextSiteCode(currentNextSiteCode);
//				
//				//更新报告日志表中的记录  将是否发送微信报告改为1-发送
//				 List<ReportManageLog> reportList=reportManageLogServiceImpl.queryList(reportRequest);
//				 if(reportList!=null && reportList.size()>0){
//					 for (ReportManageLog reportManageLog : reportList) {
//						 reportManageLog.setSendWxState(TrueOrFalseType.TRUE.getCode());
//						 reportManageLogServiceImpl.update(reportManageLog);
//					}
//				 }
//			}
		}
		logger.info("getTaskSendWXReport    end-----");
	}
	
	/**
	 * @Description: 封装模板
	 * @author cuichx --- 2015-12-31上午9:38:38     
	 * @return
	 */
	public String getSendModelORG(Map<String, Object> paramMap){
	
		String openId=(String) paramMap.get("openId");//客户微信号唯一标识
		String name=(String) paramMap.get("name");//客户名称
		int siteNum=(Integer) paramMap.get("siteNum");//网站数
		int reportNum=(Integer) paramMap.get("reportNum");//报告数
		int errorNum=(Integer) paramMap.get("errorNum");//报告发送发送失败数
		int successNum=(Integer) paramMap.get("successNum");//报告发送成功个数
		
		String requestUrl=prop.getProperty("requestUrl");
		String url=requestUrl+"/token_reportManageLogIndex.action?openId="+openId;
		String template_id=prop.getProperty("templateReportTB");
		//创建发送微信的模板对象
		WxSendModel wxSendModel=new WxSendModel(); 
		wxSendModel.setTouser(openId);//微信客户唯一标识
		wxSendModel.setTemplate_id(template_id);//模板编号
		wxSendModel.setUrl(url);//点击详情跳转url
		wxSendModel.setTopcolor("#000000");//颜色
		
		Map<String, WxSendData> map=new HashMap<String, WxSendData>();
		//标题
		WxSendData first=new WxSendData();
		first.setValue(name+"站群报告通知");
		first.setColor("#000000");
		map.put("first", first);
		//组织单位名称
		WxSendData comName=new WxSendData();
		comName.setValue(name);
		comName.setColor("#173177");
		map.put("keyword1", comName);
		//网站数
		WxSendData keyword2=new WxSendData();
		keyword2.setValue(siteNum+"个");
		keyword2.setColor("#173177");
		map.put("keyword2", keyword2);
		//报告数
		WxSendData keyword3=new WxSendData();
		keyword3.setValue(reportNum+"个");
		keyword3.setColor("#173177");
		map.put("keyword3", keyword3);
		
		
		//发送成功个数
		WxSendData keyword4=new WxSendData();
		keyword4.setValue(successNum+"个");
		keyword4.setColor("#173177");
		map.put("keyword4", keyword4);
		
		
/*		WxSendData keyword5=new WxSendData();
		keyword5.setValue(errorNum+"个");
		keyword5.setColor("#173177");
		map.put("keyword5", keyword5);*/
		//发送失败个数
		WxSendData remark=new WxSendData();
		remark.setValue("发送失败："+errorNum+"个\n");
		remark.setColor("#173177");
		map.put("remark", remark);

		
		wxSendModel.setData(map);
		return JSONObject.fromObject(wxSendModel).toString();
	}
	
	/**
	 * @Description: 填报单位--微信发送报告通知模板封装
	 * @author cuichx --- 2015-12-31下午5:40:40     
	 * @return
	 */
	public String getSendModelTB(Map<String, Object> paramMap){
		
		String openId=(String) paramMap.get("openId");//客户微信号唯一标识
		String name=(String) paramMap.get("name");//客户名称
		String siteCode=(String) paramMap.get("siteCode");//标识码
		String peroidStr=(String) paramMap.get("peroidStr");//期数
		String sendState=(String) paramMap.get("sendState");//报告发送状态
		
		String requestUrl=prop.getProperty("requestUrl");
		String url=requestUrl+"/token_reportManageLogIndex.action?openId="+openId;
		String template_id=prop.getProperty("templateReportORG");
		
		//创建发送微信的模板对象
		WxSendModel wxSendModel=new WxSendModel(); 
		wxSendModel.setTouser(openId);//微信客户唯一标识
		wxSendModel.setTemplate_id(template_id);//模板编号
		wxSendModel.setUrl(url);//点击详情跳转url
		wxSendModel.setTopcolor("#000000");//颜色
		
		Map<String, WxSendData> map=new HashMap<String, WxSendData>();
		//标题
		WxSendData first=new WxSendData();
		first.setValue(name+"监测报告通知");
		first.setColor("#000000");
		map.put("first", first);
		
		//组织单位名称
		WxSendData keyword1=new WxSendData();
		keyword1.setValue(name);
		keyword1.setColor("#173177");
		map.put("keyword1", keyword1);
		
		//标识码
		WxSendData keyword2=new WxSendData();
		keyword2.setValue(siteCode);
		keyword2.setColor("#173177");
		map.put("keyword2", keyword2);
		
		//期数
		WxSendData keyword3=new WxSendData();
		keyword3.setValue(peroidStr);
		keyword3.setColor("#173177");
		map.put("keyword3", keyword3);
		
		//报告发送状态
		WxSendData keyword4=new WxSendData();
		keyword4.setValue(sendState);
		keyword4.setColor("#173177");
		map.put("keyword4", keyword4);
		
		//备注
		WxSendData remark=new WxSendData();
		remark.setValue("查看具体内容请点击详情");
		remark.setColor("#000000");
		map.put("remark", remark);
		
		wxSendModel.setData(map);
		
		return JSONObject.fromObject(wxSendModel).toString();
	}
}
