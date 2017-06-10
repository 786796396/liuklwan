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
import org.springframework.util.CollectionUtils;

import com.publics.util.page.QueryOrder;
import com.publics.util.page.QueryOrderType;
import com.publics.util.utils.DateUtils;
import com.publics.util.utils.PropertiesUtil;
import com.publics.util.utils.StringUtils;
import com.ucap.cloud_web.constant.AccountBindStatus;
import com.ucap.cloud_web.constant.QuestionType;
import com.ucap.cloud_web.constant.ReceiveType;
import com.ucap.cloud_web.constant.TrueOrFalseType;
import com.ucap.cloud_web.dto.AccountBindInfoRequest;
import com.ucap.cloud_web.dto.ContractInfoRequest;
import com.ucap.cloud_web.dto.DatabaseInfoRequest;
import com.ucap.cloud_web.dto.DatabaseLinkRequest;
import com.ucap.cloud_web.dto.EarlyDetailRequest;
import com.ucap.cloud_web.dto.EarlyInfoRequest;
import com.ucap.cloud_web.dto.RelationsPeriodRequest;
import com.ucap.cloud_web.entity.AccountBindInfo;
import com.ucap.cloud_web.entity.ContractInfo;
import com.ucap.cloud_web.entity.DatabaseInfo;
import com.ucap.cloud_web.entity.DatabaseLink;
import com.ucap.cloud_web.entity.EarlyDetail;
import com.ucap.cloud_web.entity.EarlyInfo;
import com.ucap.cloud_web.service.IAccountBindInfoService;
import com.ucap.cloud_web.service.IContractInfoService;
import com.ucap.cloud_web.service.IDatabaseInfoService;
import com.ucap.cloud_web.service.IDatabaseLinkService;
import com.ucap.cloud_web.service.IDicChannelService;
import com.ucap.cloud_web.service.IEarlyDetailService;
import com.ucap.cloud_web.service.IEarlyInfoService;
import com.ucap.cloud_web.service.IRelationsPeriodService;
import com.ucap.cloud_web.servlet.message.WxSendData;
import com.ucap.cloud_web.servlet.message.WxSendModel;
import com.ucap.cloud_web.servlet.util.CommonUtils;
import com.ucap.cloud_web.util.YunpianSmsUtil;

@Service("taskSendWXEarly")
public class TaskSendWXEarly {
	private static Logger logger = Logger.getLogger(TaskSendWXEarly.class);
	@Autowired
	private IEarlyInfoService earlyInfoServiceImpl;
	@Autowired
	private IAccountBindInfoService accountBindInfoServiceImpl;
	@Autowired
	private IEarlyDetailService earlyDetailServiceImpl;
	@Autowired
	private IDatabaseInfoService databaseInfoServiceImpl;
	@Autowired
	private IDicChannelService dicChannelServiceImpl;
	@Autowired
	private IRelationsPeriodService relationsPeriodServiceImpl;
	@Autowired
	private IContractInfoService contractInfoServiceImpl;
	@Autowired
	private IDatabaseLinkService databaseLinkServiceImpl;

	
	private static Properties prop = PropertiesUtil.getProperties("app.properties");
	
	//private String access_token="EYdOFbeIg09sQVO5xFxKBGafdg-lsYIehyBFh5maTD4SOO4SvjUTxX-3DfqkI2TrJLZ3r-fkYRrwXYV6mGr75Oj74sPDyznlBKzDIXKpGHkBRVjAHAPFJ";
	
	/** @Description: 执行轮询从early_detail中查询数据，发送短信和微信预警
	 * @author sunjiaqi --- 2016-3-30下午8:51:47                
	*/
	@SuppressWarnings("unchecked")
	public void getTaskData(){
		try {
			/**
			 * 1.首页连通性的预警只发送今天8：00到下午18：00，
			 * 填报单位 ： 有一次 发一次				
			 * 组织单位 ：针对本站 ，有一次 发一次
			 * 			：针对站群 每天一次，并且是 8:00-18:00出现问题时 发 微信 短信
			 * 
			 **/
			EarlyDetailRequest earlyDetailRequest = new EarlyDetailRequest();
			earlyDetailRequest.setPageNo(0);
			earlyDetailRequest.setPageSize(Integer.MAX_VALUE);
			earlyDetailRequest.setType(1);//首页连通性预警
			earlyDetailRequest.setStatus("not null");
			earlyDetailRequest.setCheckType(1);//检查类型：正常合同
			earlyDetailRequest.setScanDate(DateUtils.formatStandardDate(new Date()));//只发送今天的，以前的不补发
			List<EarlyDetail> homeLinkList = earlyDetailServiceImpl.queryList(earlyDetailRequest);
			if(homeLinkList!=null && homeLinkList.size()>0){
				//验证微信凭证的access_token是否超时
				String access_token=CommonUtils.getTokenFromServlet();
				String  requestUrl="https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+access_token;
				
				for(EarlyDetail earlyDetail : homeLinkList){//循环处理
					String siteCode = earlyDetail.getSiteCode();//网站标识码
					String siteName = "";//网站名称
					//int sendSmsStatus = earlyDetail.getSendSmsStatus();//短信发送状态
					int sendWxStatus = earlyDetail.getSendWxStatus();//微信发送状态
					
					DatabaseInfoRequest databaseInfoRequest = new DatabaseInfoRequest();
					databaseInfoRequest.setSiteCode(siteCode);
					databaseInfoRequest.setIsexp(1);//只发送正常网站
					List<DatabaseInfo> queryList = databaseInfoServiceImpl.queryList(databaseInfoRequest);
					if(null != queryList && queryList.size()>0){
						siteName = queryList.get(0).getName();
						Map<String, Object> webInfoMap = getWebInfoMap(siteCode);//查询站点下面需要发送预警的信息
						//int level = (Integer) webInfoMap.get("level");//获取发送级别
						
						if(sendWxStatus == 0){//微信未发送状态
							EarlyDetailRequest edRequest = new EarlyDetailRequest();
							edRequest.setSiteCode(siteCode);
							edRequest.setPageNo(0);
							edRequest.setType(1);
							edRequest.setPageSize(Integer.MAX_VALUE);
							edRequest.setSendWxStatus(1);
							edRequest.setScanDate(DateUtils.formatStandardDate(new Date()));
							
							List<EarlyDetail> queryList2 = earlyDetailServiceImpl.queryList(edRequest);//查询一下这个siteCode8:00-18:00是否发送多一次短信了，没有则发，有则不发
							
							//首页连通性给个站点每天只发送一次   如果已经发送过且发送成功，就不再发送了;否则还需要发送
							if(queryList2==null || (queryList2!=null && queryList2.size()==0)){
								List<String> openIdList = (List<String>) webInfoMap.get("openId");//获取绑定该站点的所有微信openId
								sendWXMesg(openIdList,earlyDetail,siteName,siteCode,requestUrl);
							}
						}
						
						
						/*				if(level == 1){ //发送状态
						if(sendSmsStatus == 0){//短信未发送状态 
							sendSMSMesg(webInfoMap, siteCode, earlyDetail);
						}
						if(sendWxStatus == 0){//微信未发送状态
							List<String> openIdList =  (List<String>) webInfoMap.get("openId");//获取绑定该站点的所有微信openId
							sendWXMesg(openIdList,earlyDetail,siteName,siteCode);
						}
					}else{
						String now = DateUtils.getTodayStandardStr();
						EarlyDetailRequest edRequest = new EarlyDetailRequest();
						edRequest.setSiteCode(siteCode);
						edRequest.setPageNo(0);
						edRequest.setType(1);
						edRequest.setPageSize(Integer.MAX_VALUE);
						edRequest.setSendSmsStatus(1);
						edRequest.setBeginTime(now + " 08:00:00");
						edRequest.setEndTime(now + " 18:00:00");
						List<EarlyDetail> queryList2 = earlyDetailServiceImpl.queryList(edRequest);//查询一下这个siteCode8:00-18:00是否发送多一次短信了，没有则发，有则不发
						if(null!=queryList2 && queryList2.size()>0){
							
						}else{
							sendSMSMesg(webInfoMap, siteCode, earlyDetail);
							List<String> openIdList = (List<String>) webInfoMap.get("openId");//获取绑定该站点的所有微信openId
							sendWXMesg(openIdList,earlyDetail,siteName,siteCode);
						}
						
						
					}*/
						
					}


				}
			}

			/**
			 * 2.处理内容正确性的数据
			 */
			earlyDetailRequest.setType(6);
			List<EarlyDetail> correctContentList = earlyDetailServiceImpl.queryList(earlyDetailRequest);
			if(correctContentList!=null && correctContentList.size()>0){
				//验证微信凭证access_token是否超时
				String access_token=CommonUtils.getTokenFromServlet();
				String  requestUrl="https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+access_token;
				
				for(EarlyDetail earlyDetail : correctContentList){
					String siteCode = earlyDetail.getSiteCode();//网站标识码
					String siteName = "";//网站名称
					//int sendSmsStatus = earlyDetail.getSendSmsStatus();//短信发送状态
					int sendWxStatus = earlyDetail.getSendWxStatus();//微信发送状态
					DatabaseInfoRequest databaseInfoRequest = new DatabaseInfoRequest();
					databaseInfoRequest.setSiteCode(siteCode);
					databaseInfoRequest.setIsexp(1);//只发送正常网站
					
					List<DatabaseInfo> queryList = databaseInfoServiceImpl.queryList(databaseInfoRequest);
					if(null != queryList && queryList.size()>0){
						siteName = queryList.get(0).getName();
						Map<String, Object> webInfoMap = getWebInfoMap(siteCode);//查询站点下面需要发送预警的信息
		/*				if(sendSmsStatus == 0){//短信未发送状态
							sendSMSMesg(webInfoMap, siteCode, earlyDetail);
						}*/
						if(sendWxStatus == 0){//微信未发送状态
							List<String> openIdList = (List<String>) webInfoMap.get("openId");//获取绑定该站点的所有微信openId
							sendWXMesg(openIdList,earlyDetail,siteName,siteCode,requestUrl);
						}						
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description: 调用微信接口发送微信
	 * @author cuichx --- 2016-5-25上午10:17:31     
	 * @param openIdList
	 * @param earlyDetail
	 * @param siteName
	 * @param siteCode
	 * @param requestUrl
	 */
	public void sendWXMesg(List<String> openIdList,EarlyDetail earlyDetail,String siteName,String siteCode,String requestUrl){
		Map<String,Object> paramMap = new HashMap<String, Object>();
		if(openIdList!=null && openIdList.size()>0){
			for(String openId : openIdList){
				String scanTime = earlyDetail.getScanTime();//发现时间
				String pbUrl = earlyDetail.getUrl();///问题页面
				int type = earlyDetail.getType();//预警类型
				if(type == 1){//首页连通性预警
					String now = DateUtils.getTodayStandardStr();
					if(scanTime.startsWith(now)){//只发送今天的首页连通性预警
						String dicChannelId = "首页连通性";//问题类型
						String queDescribe = earlyDetail.getErrorCode() + " " + QuestionType.getNameByCode(earlyDetail.getErrorCode()+ "");//问题描述
						paramMap.put("dicChannelId", dicChannelId);
						paramMap.put("queDescribe", queDescribe);
						paramMap.put("openId", openId);
						paramMap.put("name", siteName);
						paramMap.put("code", siteCode);
						paramMap.put("scanTime", scanTime);
						paramMap.put("url", pbUrl);
						//封装模板消息
						String jsonStr=getSendModelTB(paramMap);
						//发送模板消息
						JSONObject httpRequest = CommonUtils.httpRequest(requestUrl, "POST", jsonStr);
						String errmsg = (String) httpRequest.get("errmsg");
						logger.info("errmsg:"+errmsg);
						if(errmsg !=null && errmsg.equals("ok")){
							earlyDetail.setSendWxStatus(1);//设置微信发送状态为1发送成功
						}else{
							earlyDetail.setSendWxStatus(2);//设置微信发送状态为2发送失败
						}
						earlyDetail.setSendWxTimes(earlyDetail.getSendWxTimes() + 1);//微信发送次数+1
						earlyDetail.setSendWxTime(DateUtils.getNow());//更新发送微信时间
						earlyDetailServiceImpl.update(earlyDetail);
					}
				}else if(type == 6){//错别字预警
					String dicChannelId = "内容正确性";//问题类型
					String queDescribe = earlyDetail.getWrongTerm() + "建议改为" + earlyDetail.getExpectTerms();//问题描述
					paramMap.put("dicChannelId", dicChannelId);
					paramMap.put("queDescribe", queDescribe);
					paramMap.put("openId", openId);
					paramMap.put("name", siteName);
					paramMap.put("code", siteCode);
					paramMap.put("openId", openId);
					Date parseDateTime = DateUtils.parseStandardDate(scanTime);
					scanTime= DateUtils.formatStandardDate(parseDateTime);//错别字预警时间去掉时分秒
					paramMap.put("scanTime", scanTime);
					paramMap.put("url", pbUrl);
					//封装模板消息
					String jsonStr=getSendModelTB(paramMap);
					//发送模板消息
					JSONObject httpRequest = CommonUtils.httpRequest(requestUrl, "POST", jsonStr);
					
					String errmsg = (String) httpRequest.get("errmsg");
					if(errmsg !=null && errmsg.equals("ok")){
						earlyDetail.setSendWxStatus(1);//设置微信发送状态为1发送成功
					}else{
						earlyDetail.setSendWxStatus(2);//设置微信发送状态为2发送失败
					}
					earlyDetail.setSendWxTimes(earlyDetail.getSendWxTimes() + 1);//微信发送次数+1
					earlyDetail.setSendWxTime(DateUtils.getNow());//更新发送微信时间
					earlyDetailServiceImpl.update(earlyDetail);
				}else if(type == 7){//错别字预警
					String dicChannelId = "升级改版或者临时关停";//问题类型
					String queDescribe = "升级改版或者临时关停";//问题描述
					paramMap.put("dicChannelId", dicChannelId);
					paramMap.put("queDescribe", queDescribe);
					paramMap.put("openId", openId);
					paramMap.put("name", siteName);
					paramMap.put("code", siteCode);
					paramMap.put("openId", openId);
					Date parseDateTime = DateUtils.parseStandardDate(scanTime);
					scanTime= DateUtils.formatStandardDate(parseDateTime);//
					paramMap.put("scanTime", scanTime);
					paramMap.put("url", pbUrl);
					//封装模板消息
					String jsonStr=getSendModelTB(paramMap);
					//发送模板消息
					JSONObject httpRequest = CommonUtils.httpRequest(requestUrl, "POST", jsonStr);
					
					String errmsg = (String) httpRequest.get("errmsg");
					if(errmsg !=null && errmsg.equals("ok")){
						earlyDetail.setSendWxStatus(1);//设置微信发送状态为1发送成功
					}else{
						earlyDetail.setSendWxStatus(2);//设置微信发送状态为2发送失败
					}
					earlyDetail.setSendWxTimes(earlyDetail.getSendWxTimes() + 1);//微信发送次数+1
					earlyDetail.setSendWxTime(DateUtils.getNow());//更新发送微信时间
					earlyDetailServiceImpl.update(earlyDetail);
				}
			}
		}
	}
	
	/** @Description: 调用接口发送短信
	 * @author sunjiaqi --- 2016-4-7下午7:18:54     
	 * @param webInfoMap
	 * @param siteCode
	 * @param earlyDetail           
	*/
	public void sendSMSMesg(Map<String, Object> webInfoMap,String siteCode,EarlyDetail earlyDetail){
		try {
			int type = earlyDetail.getType();//预警类型
			String mobile = "";//发送的手机号
			String  tpl_id = "";
			String tpl_value = "";
			String url = earlyDetail.getUrl();
			if(webInfoMap.get("telephone2") !=null && !webInfoMap.get("telephone2").equals("")){
				mobile = (String) webInfoMap.get("telephone2");//添加联系人手机号
			}
			if(webInfoMap.get("telephone") !=null && !webInfoMap.get("telephone").equals("")){
				if(StringUtils.isEmpty(mobile)){
					mobile = (String) webInfoMap.get("telephone");//添加负责人手机号
				}else{
					mobile =  mobile + ","+ webInfoMap.get("telephone");//添加负责人手机号
				}
			}
			if(StringUtils.isNotEmpty(mobile)){//判断接收预警的手机号是否为空,如果为空则不发送短信
				String scanTime = earlyDetail.getScanTime();//发现时间
				url = regString(url);
				if(type == 1){//首页连通性预警
					String now = DateUtils.getTodayStandardStr();
					if(scanTime.startsWith(now)){//只发送今天的首页连通性预警
						tpl_id = prop.getProperty("tplHomeLink");//首页连通性预警短信模板
						tpl_value = "#url#="+url + "&#datetime#=" + scanTime + "&#siteCode#=" + siteCode;
						String temp = YunpianSmsUtil.tplSendSms(tpl_id, tpl_value, mobile);
						JSONObject fromObject = JSONObject.fromObject(temp);
						if(fromObject.get("code")!=null && !fromObject.get("code").equals("")){
							Integer code = (Integer) fromObject.get("code");//获取短信发送返回状态
							if(code==0 || code.equals(0) || code.equals("0")){
								earlyDetail.setSendSmsStatus(1);//设置短信发送状态为1发送成功
							}else{
								earlyDetail.setSendSmsStatus(2);//设置短信发送状态为2发送失败
							}
							earlyDetail.setSendSmsTimes(earlyDetail.getSendSmsTimes() +1);//短信发送次数+1
							earlyDetail.setSendSmsTime(DateUtils.getNow());//更新发送短信时间
							earlyDetailServiceImpl.update(earlyDetail);
						}
					}
				}else if(type == 6){//错别字预警
					tpl_id = prop.getProperty("tplCorrectContent");//错别字预警短信模板
					Date parseDateTime = DateUtils.parseStandardDate(scanTime);
					scanTime= DateUtils.formatStandardDate(parseDateTime);//错别字预警时间去掉时分秒
					tpl_value = "#webUrl#="+url + "&#datetime#=" +scanTime + "&#siteCode#=" + siteCode;
					String temp = YunpianSmsUtil.tplSendSms(tpl_id, tpl_value, mobile);
					JSONObject fromObject = JSONObject.fromObject(temp);
					if(fromObject.get("code")!=null && !fromObject.get("code").equals("")){
						Integer code = (Integer) fromObject.get("code");//获取短信发送返回状态
						if(code==0 || code.equals(0) || code.equals("0")){
							earlyDetail.setSendSmsStatus(1);//设置短信发送状态为1发送成功
						}else{                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    
							earlyDetail.setSendSmsStatus(2);//设置短信发送状态为2发送失败
						}
						earlyDetail.setSendSmsTimes(earlyDetail.getSendSmsTimes() +1);//短信发送次数+1
						earlyDetail.setSendSmsTime(DateUtils.getNow());//更新发送短信时间
						earlyDetailServiceImpl.update(earlyDetail);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	/** @Description: 依据站点siteCode查询，预警需要发送的手机号，微信信息
	 * @author sunjiaqi --- 2016-3-31上午11:14:38     
	 * @param siteCode
	 * @return           
	*/
	public Map<String,Object> getWebInfoMap(String siteCode){
		
		int level = 0;//设置一个级别，0：代表不发送，1：表示
		Map<String,Object> map = new HashMap<String, Object>();//返回值，{'openid':微信号,'phone':手机号;email:邮箱}
		DatabaseInfoRequest databaseInfoRequest = new DatabaseInfoRequest();
		try {
			ContractInfoRequest cont = new ContractInfoRequest();
			cont.setNowTime(DateUtils.getTodayStandardStr());//当前日期
			cont.setSiteCode(siteCode);
			//依据站点siteCode和当前日期查询合同表中是否有本站点并且未过期的的合同
			List<ContractInfo> contractlist = contractInfoServiceImpl.queryList(cont);
			//判断集合为空，说明不是站点购买的合同.再去获取上级的组织单位看看是否是组织单位购买的合同
			if(CollectionUtils.isEmpty(contractlist)){
				DatabaseLinkRequest databaseLinkRequest = new DatabaseLinkRequest();
				databaseLinkRequest.setPageSize(Integer.MAX_VALUE);
				databaseLinkRequest.setSiteCode(siteCode);
				
				//按组织单位编号正序排列
/*				List<QueryOrder> querySiteList=new ArrayList<QueryOrder>();
				QueryOrder siteQueryOrder=new QueryOrder("org_site_code",QueryOrderType.ASC);
				querySiteList.add(siteQueryOrder);
				databaseLinkRequest.setQueryOrderList(querySiteList);*/
				List<DatabaseLink> queryList = databaseLinkServiceImpl.queryList(databaseLinkRequest);
				
				ContractInfoRequest contractInfoRequest = new ContractInfoRequest();
				contractInfoRequest.setIdsOrgSiteCode(queryList);
				contractInfoRequest.setNowTime(DateUtils.getYesterdayStr());
				
				List<ContractInfo> contractInfoList = contractInfoServiceImpl.queryList(contractInfoRequest);
				
				String xianSiteCode = "";
				String shiSiteCode = "";
				String shengSiteCode = "";
				if(!CollectionUtils.isEmpty(contractInfoList)){
					for (ContractInfo contractInfo : contractInfoList) {
						String orgSiteCode = contractInfo.getSiteCode();
						//优先找低级的   县级
						if(!orgSiteCode.substring(4, 6).equals("00")){
							xianSiteCode = orgSiteCode;
							break;
						//市级
						}else if((orgSiteCode.substring(4, 6).equals("00"))&&(!orgSiteCode.substring(2, 6).equals("0000"))){
							shiSiteCode = orgSiteCode;
							break;
						//省级
						}else{
							shengSiteCode = orgSiteCode;
							break;
						}
					}
				}
				int isorganizational = 0;//记录该站点是否是门户站点
				DatabaseInfoRequest request1 = new DatabaseInfoRequest();
				request1.setSiteCode(siteCode);
				List<DatabaseInfo> queryList2 = databaseInfoServiceImpl.queryList(request1);
				if(null!=queryList2 && queryList2.size()>0){
					isorganizational = queryList2.get(0).getIsorganizational();
				}
				
				if(StringUtils.isNotEmpty(xianSiteCode)){//县级
					if(siteCode.startsWith(xianSiteCode) && isorganizational ==1){//若果是组织单位本站
						level = 1;
					}else{
						level = 0;
					}
					siteCode = xianSiteCode;
				}else if(StringUtils.isNotEmpty(shiSiteCode)){//市级
					if(siteCode.startsWith(shiSiteCode) && isorganizational ==1){//若果是组织单位本站
						level = 1;
					}else{
						level = 0;
					}
					siteCode = shiSiteCode;
				}else{//省级
					if(siteCode.startsWith(shengSiteCode) && isorganizational ==1){//若果是组织单位本站
						level = 1;
					}else{
						level = 0;
					}
					siteCode = shengSiteCode;
				}
				databaseInfoRequest.setIsorganizational(1);//组织单位买单获取的是门户站点的信息
			}else{
				siteCode = contractlist.get(0).getSiteCode();
				level = 1;//如果是自己买单的，发送状态为1
			}
			if(StringUtils.isNotEmpty(siteCode)){//判断是否获取到了该站点的合同信息
				databaseInfoRequest.setSiteCodeLike(siteCode);
				if(siteCode.length()==6){//组织单位
					databaseInfoRequest.setIsorganizational(1);//门户
				}
				databaseInfoRequest.setIsexp(1);//只发送正常网站
				List<DatabaseInfo> databaseList = databaseInfoServiceImpl.queryList(databaseInfoRequest);
				if(!CollectionUtils.isEmpty(databaseList)){
					DatabaseInfo databaseInfo = databaseList.get(0);
					String telephone2 = databaseInfo.getTelephone2();//获取联系人手机号
					String telephone = databaseInfo.getTelephone();//获取负责人手机号
					int telReceive2 = databaseInfo.getTelReceive2();//联系人是否接收预警
					int telReceive = databaseInfo.getTelReceive();//负责人是否接收预警
					if(telReceive2 == 1 && StringUtils.isNotEmpty(telephone2)){
						map.put("telephone2", telephone2);//联系人手机号
					}
					if(telReceive == 1 && StringUtils.isNotEmpty(telephone)){
						map.put("telephone", telephone);//负责人手机号
					}
					
					//联系人 是否发送邮件
					int emailReceive2=databaseInfo.getEmailReceive2();
					String email2=databaseInfo.getEmail2();
					if(emailReceive2==1 && StringUtils.isNotEmpty(email2)){
						map.put("email2", email2);
					}
					
					
					AccountBindInfoRequest accountBindInfoRequest = new AccountBindInfoRequest();
					accountBindInfoRequest.setPageNo(0);
					accountBindInfoRequest.setPageSize(Integer.MAX_VALUE);
					accountBindInfoRequest.setSiteCode(siteCode);
					accountBindInfoRequest.setStatus(AccountBindStatus.ACCOUNT_BIND.getCode());//状态为绑定
					accountBindInfoRequest.setLocalhostEarlyStatus(ReceiveType.IS_RECEIVE.getCode());//接收预警信息
					List<AccountBindInfo> accountBindInfoList = accountBindInfoServiceImpl.queryList(accountBindInfoRequest);
					List<String> openIdList = new ArrayList<String>();
					if(accountBindInfoList!=null && accountBindInfoList.size()>0){//说明该填报单位为客户且接收预警信息，发送微信模板信息
						for(AccountBindInfo accountBindInfo : accountBindInfoList){
							String openId=accountBindInfo.getOpenId();//客户微信号唯一标识
							openIdList.add(openId);
						}
					}
					map.put("openId", openIdList);
				}
			}
			map.put("level", level);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	
	
	
	
	public  void getTaskSendWXEarly(){
		logger.info("getTaskSendWXEarly    start-----");
		/**
		 * 查询预警表，获取最新预警数>0的所有的siteCode（10位），封装到一个集合A中
		 * 遍历A集合，获取每个siteCode，先通过siteCode查询微信绑定账户表和客户信息表，
		 * 		如果存在记录，说明该填报单位是一个客户，直接发送模板消息，
		 * 		如果不存在，通过站点信息表+订单表+客户表+微信绑定账户信息表，获取对应的客户，将该客户去重复放到集合B中（里面是所有的组织单位客户）
		 * 处理集合B  遍历集合b，获取总的预警数，并发送模板信息
		 * 			
		 */
		String todayDate=DateUtils.formatStandardDate(new Date());//获取今天日期
		
		//查询预警表，获取最新预警数>0的所有的siteCode（10位）
		EarlyInfoRequest earlyRequest=new EarlyInfoRequest();
		earlyRequest.setNewEarlyNum(1);
		earlyRequest.setPageSize(Integer.MAX_VALUE);
		earlyRequest.setCheckType(1);//正常合同
		List<EarlyInfo> earlyInfoList=earlyInfoServiceImpl.queryList(earlyRequest);
		
		String access_token=CommonUtils.getTokenFromServlet();
		String  requestUrl="https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+access_token;
		
		List<String> siteCodeList=new ArrayList<String>();
		if(earlyInfoList!=null && earlyInfoList.size()>0){
			for(int i=0;i<earlyInfoList.size();i++){
				EarlyInfo elInfo=earlyInfoList.get(i);
				//int earlyCountTB=elInfo.getNewEarlyNum();//新预警总数
				//通过网站标识码查询微信绑定账户表和客户信息表
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("siteCode", elInfo.getSiteCode());
				map.put("status", AccountBindStatus.ACCOUNT_BIND.getCode());//状态为绑定
				map.put("localhostEarlyStatus", ReceiveType.IS_RECEIVE.getCode());//接收预警信息
				AccountBindInfoRequest  accBindInfoRequest=accountBindInfoServiceImpl.queryByMap(map);
				if(accBindInfoRequest!=null){//说明该填报单位为客户且接收预警信息，发送微信模板信息
					
					Map<String, Object> paramMap=new HashMap<String, Object>();
					
					
					String openId=accBindInfoRequest.getOpenId();//客户微信号唯一标识
					String name=accBindInfoRequest.getCompanyName();//客户名称
					String code=accBindInfoRequest.getSiteCode();//客户编码
					paramMap.put("openId", openId);
					paramMap.put("name", name);
					paramMap.put("code", code);
					
					//通过网站标识码，查询预警详情表
					EarlyDetailRequest earlyDetailRequest=new EarlyDetailRequest();
					earlyDetailRequest.setSiteCode(code);
					earlyDetailRequest.setPageSize(Integer.MAX_VALUE);
					earlyDetailRequest.setSendWxStatus(TrueOrFalseType.FALSE.getCode());//未发送微信预警通知
					earlyDetailRequest.setCheckType(1);//正常合同
					earlyDetailRequest.setScanTime(todayDate);
					
					List<QueryOrder> querySiteList=new ArrayList<QueryOrder>();
					QueryOrder siteQueryOrder=new QueryOrder("scan_time",QueryOrderType.DESC);
					querySiteList.add(siteQueryOrder);
					earlyDetailRequest.setQueryOrderList(querySiteList);
					
					List<EarlyDetail>  earlyList=earlyDetailServiceImpl.queryList(earlyDetailRequest);
					if(earlyList!=null && earlyList.size()>0){
						for (EarlyDetail earlyDetail : earlyList) {
							String dicChannelId=dicChannelServiceImpl.get(earlyDetail.getDicChannelId()).getChannelName();//问题类型
							paramMap.put("dicChannelId", dicChannelId);
							if(StringUtils.isNotEmpty(earlyDetail.getQueDescribe())){
								paramMap.put("queDescribe", earlyDetail.getQueDescribe());//问题描述
							}
							
							paramMap.put("scanTime", earlyDetail.getScanTime());//发现时间
							//通过网站标识码，获取问题页面的url
							DatabaseInfoRequest siteRequest=new DatabaseInfoRequest();
							siteRequest.setSiteCode(elInfo.getSiteCode());
							 List<DatabaseInfo> siteList=databaseInfoServiceImpl.queryList(siteRequest);
							 if(siteList!=null && siteList.size()>0){
								 DatabaseInfo dataInfo=siteList.get(0);
								 if(StringUtils.isNotEmpty(dataInfo.getJumpUrl())){
									 paramMap.put("url",dataInfo.getJumpUrl());//问题页面
								 }else{
									 paramMap.put("url",dataInfo.getUrl());//问题页面
								 }
							 }
							 
							 //将预警详情表中的记录状态修改为已发送微信预警通知
							 earlyDetail.setSendWxStatus(TrueOrFalseType.TRUE.getCode());
							 earlyDetailServiceImpl.update(earlyDetail);
							 
							String jsonStr=getSendModelTB(paramMap);
							//发送https请求
							logger.info("httpRequest begin----------");
							CommonUtils.httpRequest(requestUrl, "POST", jsonStr);
							logger.info("httpRequest end----------");
						}
					}
				}else{
					//通过网站标识码，查询预警详情表
					EarlyDetailRequest earlyDetailRequest=new EarlyDetailRequest();
					earlyDetailRequest.setSiteCode(elInfo.getSiteCode());
					earlyDetailRequest.setPageSize(Integer.MAX_VALUE);
					earlyDetailRequest.setSendWxStatus(TrueOrFalseType.FALSE.getCode());//未发送微信预警通知
					earlyDetailRequest.setCheckType(1);//正常合同
					earlyDetailRequest.setScanTime(todayDate);
					
					List<QueryOrder> querySiteList=new ArrayList<QueryOrder>();
					QueryOrder siteQueryOrder=new QueryOrder("scan_time",QueryOrderType.DESC);
					querySiteList.add(siteQueryOrder);
					earlyDetailRequest.setQueryOrderList(querySiteList);
					
					List<EarlyDetail>  earlyList=earlyDetailServiceImpl.queryList(earlyDetailRequest);
					if(earlyList!=null && earlyList.size()>0){
						for (EarlyDetail earlyDetail : earlyList) {
							//将预警详情表中的记录状态修改为已发送微信预警通知
							earlyDetail.setSendWxStatus(TrueOrFalseType.TRUE.getCode());
							earlyDetailServiceImpl.update(earlyDetail);
						}
						 

						/**
						 * 通过网站标识码，获取对应的客户
						 *		通过网站标识码和当前时间，查询服务周期中间表，获取服务周期id
						 *		通过获取的服务周期id,查询服务周期表，获取合同信息表id
						 *		通过合同信息表id,查询合同信息表，获取客户编号
						 */
						Map<String,Object> pMap = new HashMap<String, Object>();
						pMap.put("siteCode",elInfo.getSiteCode());
						pMap.put("newTime", DateUtils.formatStandardDateTime(new Date()));
						
						List<RelationsPeriodRequest> relationList=relationsPeriodServiceImpl.queryByMap(pMap);
						if(relationList!=null && relationList.size()>0){
							//判断客户集合中是否已经存在该客户了，如果不存在则添加到该集合中，如果存在则不添加
							if(siteCodeList.size()==0){//客户编码容器添加第一条数据---因为容器大小如果为0的话,无法进行循环遍历去重,只能单独处理
								siteCodeList.add(relationList.get(0).getSiteCode());
							}else{
								for(int j=0;j<siteCodeList.size();j++){//客户编码容器大小不为零,循环遍历容器去重
									if(!(siteCodeList.get(j).equals(relationList.get(0).getSiteCode()))){
										siteCodeList.add(relationList.get(0).getSiteCode());
									}
								}
							}
						}
					}
				}
			}
		}
		if(siteCodeList!=null && siteCodeList.size()>0){
			Map<String, Object> earlyMap=new HashMap<String, Object>();
			earlyMap.put("siteCodeList", siteCodeList);
			earlyMap.put("status", AccountBindStatus.ACCOUNT_BIND.getCode());//状态为绑定
			earlyMap.put("siteListEarlyStatus", ReceiveType.IS_RECEIVE.getCode());//接收站群预警通知
			logger.info(">>>>>>>>>>>earlyMap>>>>>>>>>>>>>"+earlyMap);
			
			List<AccountBindInfoRequest> bindList=accountBindInfoServiceImpl.queryEarlyInfo(earlyMap);
			for(int j=0;j<bindList.size();j++){
				String openId=bindList.get(j).getOpenId();//客户微信号唯一标识
				String name=bindList.get(j).getCompanyName();//客户名称
				String code=bindList.get(j).getSiteCode();//客户编码
				int count=bindList.get(j).getNewEarlyNum();//新预警总数
				
				String jsonStr=getSendModelORG(openId, name, code, count);
				//发送https请求
				logger.info(">>>>>>>>>>>>>>>httpRequest begin>>>>>>>>>>>");
				
				logger.info(">>>>>>requestUrl>>>>>>"+requestUrl);
				CommonUtils.httpRequest(requestUrl, "POST", jsonStr);
				logger.info(">>>>>>>>>>>>>>>httpRequest end>>>>>>>>>>>");
			}
		}
		logger.info("getTaskSendWXEarly    end-----");
	}
	
	/**
	 * @Description: 
	 * @author cuichx --- 2016-1-6下午6:17:33     
	 * @param openId
	 * @param name 组织单位名称
	 * @param code 组织单位账号
	 * @param count 预警个数
	 * @return
	 */
	public String getSendModelORG(String openId,String name,String code,int count){
		String requestUrl=prop.getProperty("requestUrl");
		String url=requestUrl+"/token_earlyDetailIndex.action?openId="+openId;
		String template_id=prop.getProperty("templateEarlyORG");
		//创建发送微信的模板对象
		WxSendModel wxSendModel=new WxSendModel(); 
		wxSendModel.setTouser(openId);//微信客户唯一标识
		wxSendModel.setTemplate_id(template_id);//模板编号
		wxSendModel.setUrl(url);//点击详情跳转url
		wxSendModel.setTopcolor("#000000");//颜色
		
		Map<String, WxSendData> map=new HashMap<String, WxSendData>();
		//标题
		WxSendData first=new WxSendData();
		first.setValue(name+"站群预警通知");
		first.setColor("#000000");
		map.put("first", first);
		//组织单位名称
		WxSendData keyword1=new WxSendData();
		keyword1.setValue(name);
		keyword1.setColor("#173177");
		map.put("keyword1", keyword1);
		//组织单位账号
		WxSendData keyword2=new WxSendData();
		keyword2.setValue(code);
		keyword2.setColor("#173177");
		map.put("keyword2", keyword2);
		//预警个数
		WxSendData keyword3=new WxSendData();
		keyword3.setValue(count+"");
		keyword3.setColor("#173177");
		map.put("keyword3", keyword3);
		
		//组织单位账号
		WxSendData remark=new WxSendData();
		remark.setValue("查看具体情况请点击详情");
		remark.setColor("#000000");
		map.put("remark", remark);
		
		wxSendModel.setData(map);
		
		return JSONObject.fromObject(wxSendModel).toString();
	}
	/**
	 * @Description: 单个网扎发送微信预警信息模板
	 * @author cuichx --- 2016-1-6下午6:20:29     
	 * @param map
	 * @return
	 */
	public String getSendModelTB(Map<String,Object> map){
		
		String openId=(String) map.get("openId");//客户微信号唯一标识
		String name=(String) map.get("name");//客户名称
		String code=(String) map.get("code");//客户编码
		
		String dicChannelId=(String) map.get("dicChannelId");//问题类型
		String queDescribe=(String) map.get("queDescribe");//问题描述
		String scanTime=(String) map.get("scanTime");//发现时间
		
		String url=(String)map.get("url");//点击详情url跳转
		
		//String requestUrl=prop.getProperty("requestUrl");
		//String url=requestUrl+"/token_earlyDetailIndex.action?openId="+openId;
		String template_id=prop.getProperty("templateEarlyTB");
		//创建发送微信的模板对象
		WxSendModel wxSendModel=new WxSendModel(); 
		wxSendModel.setTouser(openId);//微信客户唯一标识
		wxSendModel.setTemplate_id(template_id);//模板编号
		wxSendModel.setUrl(url);//点击详情跳转url
		wxSendModel.setTopcolor("#000000");//颜色
		
		Map<String, WxSendData> paramMap=new HashMap<String, WxSendData>();
		//标题
		WxSendData first=new WxSendData();
		first.setValue(name+"网站预警通知");
		first.setColor("#000000");
		paramMap.put("first", first);
		//网站名称
		WxSendData keyword1=new WxSendData();
		keyword1.setValue(name);
		keyword1.setColor("#173177");
		paramMap.put("keyword1", keyword1);
		
		//标识码
		WxSendData keyword2=new WxSendData();
		keyword2.setValue(code);
		keyword2.setColor("#173177");
		paramMap.put("keyword2", keyword2);
		
		//问题类型
		WxSendData keyword3=new WxSendData();
		keyword3.setValue(dicChannelId);
		keyword3.setColor("#173177");
		paramMap.put("keyword3", keyword3);
		
		
		//问题描述
		WxSendData keyword4=new WxSendData();
		keyword4.setValue(queDescribe);
		keyword4.setColor("#ff0000");
		paramMap.put("keyword4", keyword4);
		
		//发现时间
		WxSendData keyword5=new WxSendData();
		keyword5.setValue(scanTime);
		keyword5.setColor("#173177");
		paramMap.put("keyword5", keyword5);
		
		//问题页面--写到remark中
		WxSendData remark=new WxSendData();
		//remark.setValue("问题页面：<a target='_blank' href='"+pbUrl+"'>"+pbUrl+"</a>");
		if(map.get("type")!=null && !"".equals(map.get("type"))){
			if( "8".equals(map.get("type").toString()) || "1".equals(map.get("type").toString()) || "14".equals(map.get("type").toString()) ||"11".equals(map.get("type").toString()) ||"12".equals(map.get("type").toString())){
				String pbUrl=(String) map.get("websiteUrl");///网站地址
				remark.setValue("网站地址："+pbUrl+"\n");
			}/*else{
				String pbUrl=(String) map.get("url");///问题页面
				remark.setValue("问题页面："+pbUrl+"\n");
			}*/
		}else{
			/*String pbUrl=(String) map.get("url");///问题页面
			remark.setValue("问题页面："+pbUrl+"\n");*/
		}

		remark.setColor("#173177");
		paramMap.put("remark", remark);
		
		wxSendModel.setData(paramMap);
		return JSONObject.fromObject(wxSendModel).toString();
	}
	
	/**
	 * @Description: 获取发送的微信的内容
	 * @author luocheng --- 2017/1/4  
	 * @param map
	 * @return 微信发送的内容
	 */
	public String getWXSendMessage(Map<String,Object> map){
		StringBuffer wxMsg = new StringBuffer();
		
		String name=(String) map.get("name");//客户名称
		wxMsg.append("客户名称:"+name+";");
		String code=(String) map.get("code");//客户编码
		wxMsg.append("客户编码:"+code+";");
		String dicChannelId=(String) map.get("dicChannelId");//问题类型
		wxMsg.append("问题类型:"+dicChannelId+";");
		String queDescribe=(String) map.get("queDescribe");//问题描述
		wxMsg.append("问题描述:"+queDescribe+";");
		String scanTime=(String) map.get("scanTime");//发现时间
		wxMsg.append("发现时间:"+scanTime+";");
		
		if(map.get("type")!=null && !"".equals(map.get("type"))){
			if( "8".equals(map.get("type").toString()) || "1".equals(map.get("type").toString()) ||"11".equals(map.get("type").toString()) ||"12".equals(map.get("type").toString())){
				String pbUrl=(String) map.get("websiteUrl");///网站地址
				wxMsg.append("网站地址:"+pbUrl+";");
			}/*else{
				String pbUrl=(String) map.get("url");///问题页面
				wxMsg.append("问题页面:"+pbUrl+";");
			}*/
		}else{
			/*String pbUrl=(String) map.get("url");///问题页面
			wxMsg.append("问题页面:"+pbUrl+";");*/
		}

		
		return wxMsg.toString();
		
	}
	
	/** @Description: 
	 * @author sunjiaqi --- 2016-4-7下午1:50:48     
	 * @param ss
	 * @return           
	*/
	public String regString(String ss){
		
		ss  = ss.replaceAll("=", "%3D");
		
		ss  = ss.replaceAll("&", "%26");
		return ss;
	}
	
	
	public static void main(String[] args) {
		try {
			/*String mobile = "13810091463";
			String tpl_id = "1149127";//首页连通性预警模板
			String tpl_value = "#name#=开普测试&#url#=www.kaipuyun.com.cn&#datetime#=3月15日20:46";
			
			String temp = YunpianSmsUtil.tplSendSms(tpl_id, tpl_value, mobile);
			System.out.println("temp=" + temp);
			
			String tpl_id = "1301551";//错别字预警短信模板
			
			
			
			String tpl_value = "#webUrl#=http://www.baidu.com&#datetime#=2016-04-07&#siteCode#=1111";
			String temp = YunpianSmsUtil.tplSendSms(tpl_id, tpl_value, mobile);
			JSONObject fromObject = JSONObject.fromObject(temp);
			System.out.println(temp);*/
			System.out.println(DateUtils.getTodayStandardStr());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
