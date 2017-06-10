package com.ucap.cloud_web.timer;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.publics.util.utils.DateUtils;
import com.publics.util.utils.PropertiesUtil;
import com.publics.util.utils.StringUtils;
import com.ucap.cloud_web.constant.AccountBindStatus;
import com.ucap.cloud_web.constant.EarlyMailType;
import com.ucap.cloud_web.constant.EarlyType;
import com.ucap.cloud_web.constant.QuestionType;
import com.ucap.cloud_web.constant.QueueType;
import com.ucap.cloud_web.constant.ReceiveType;
import com.ucap.cloud_web.dto.AccountBindInfoRequest;
import com.ucap.cloud_web.dto.ConfigEarlyRequest;
import com.ucap.cloud_web.dto.ContractInfoRequest;
import com.ucap.cloud_web.dto.DatabaseInfoRequest;
import com.ucap.cloud_web.dto.DatabaseTreeInfoRequest;
import com.ucap.cloud_web.dto.DicItemRequest;
import com.ucap.cloud_web.dto.EarlyDetailRequest;
import com.ucap.cloud_web.dto.EarlyDetailTempRequest;
import com.ucap.cloud_web.dto.SecurityResponseRequest;
import com.ucap.cloud_web.entity.AccountBindInfo;
import com.ucap.cloud_web.entity.ConfigEarly;
import com.ucap.cloud_web.entity.ContractInfo;
import com.ucap.cloud_web.entity.DatabaseInfo;
import com.ucap.cloud_web.entity.DatabaseTreeInfo;
import com.ucap.cloud_web.entity.DicItem;
import com.ucap.cloud_web.entity.EarlyDetail;
import com.ucap.cloud_web.entity.EarlyDetailTemp;
import com.ucap.cloud_web.entity.EarlyLog;
import com.ucap.cloud_web.entity.SecurityResponse;
import com.ucap.cloud_web.entity.ShortLink;
import com.ucap.cloud_web.service.IAccountBindInfoService;
import com.ucap.cloud_web.service.IConfigEarlyService;
import com.ucap.cloud_web.service.IContractInfoService;
import com.ucap.cloud_web.service.IDatabaseInfoService;
import com.ucap.cloud_web.service.IDatabaseTreeInfoService;
import com.ucap.cloud_web.service.IDicItemService;
import com.ucap.cloud_web.service.IEarlyDetailService;
import com.ucap.cloud_web.service.IEarlyDetailTempService;
import com.ucap.cloud_web.service.IEarlyLogService;
import com.ucap.cloud_web.service.ISecurityResponseService;
import com.ucap.cloud_web.service.IShortLinkService;
import com.ucap.cloud_web.servlet.util.CommonUtils;
import com.ucap.cloud_web.util.GenerateShortUrlUtil;
import com.ucap.cloud_web.util.SendEmail;
import com.ucap.cloud_web.util.YunpianSmsUtil;

import net.sf.json.JSONObject;



/**
 * 描述： 预警发送
 * 包：com.ucap.cloud_web.timer
 * 文件名称：TaskSendEarly
 * 公司名称：开普互联
 * 作者：liujc@ucap.com.cn
 * 时间：2016-7-29上午10:24:31 
 * @version V1.0
 */
@Service("taskSendEarly")
public class TaskSendEarly {
	@Autowired
	private IEarlyDetailService earlyDetailServiceImpl;
	@Autowired
	private IConfigEarlyService configEarlyServiceImpl;
	@Autowired
	private IDatabaseInfoService databaseInfoServiceImpl;
	@Autowired
	private IContractInfoService contractInfoServiceImpl;
	@Autowired
	private IEarlyLogService earlyLogServiceImpl;
	@Autowired
	private IDatabaseTreeInfoService databaseTreeInfoServiceImpl;
	@Autowired
	private IEarlyDetailTempService earlyDetailTempServiceImpl;
	@Autowired
	private IAccountBindInfoService accountBindInfoServiceImpl;
	@Autowired
	private TaskSendWXEarly taskSendWXEarly;
	@Autowired
	private ISecurityResponseService securityResponseServiceImpl;
	@Autowired
	private IDicItemService dicItemServiceImpl;
	@Autowired
	private IShortLinkService shortLinkServiceImpl;
	
	private static Properties prop = PropertiesUtil.getProperties("app.properties");
	private static Logger logger = Logger.getLogger(TaskSendEarly.class);//日志信息
	private String localWeiXinUrl=prop.getProperty("localWeiXinUrl");
	//微信模板消息   点击详情跳转
	private String connHomeUrl=localWeiXinUrl+"/freeToken_connHomeTbIndex.action";//首页不连通实时
	private String securityHomeTbUrl=localWeiXinUrl+"/freeToken_securityHomeTbIndex.action";//首页更新不及时
	
	private String securityChannelTbUrl=localWeiXinUrl+"/costToken_securityChannelTbIndex.action";//栏目更新不及时
	private String contCorrectTbUrl=localWeiXinUrl+"/costToken_contCorrectTbIndex.action";//内容正确性
	
	private String securityBlankTbUrl=localWeiXinUrl+"/advanceToken_securityBlankTbIndex.action";//空白栏目
	private String securityResponseTbUrl=localWeiXinUrl+"/advanceToken_securityResponseTbIndex.action";//互动回应差
	private String securityFatalErrorTbUrl=localWeiXinUrl+"/advanceToken_securityFatalErrorTbIndex.action";//严重问题
	List<Map<Object, Object>> orgSingleList=null;
	
	

	
	/**
	 * @Title: 发送日报预警信息---没有时间端限制
	 * @Description:
	 * @author liujc@ucap.com.cn	2016-7-29上午11:55:51
	 */
	@SuppressWarnings("unused")
	public void sendDailyEarlyInfo(){
		try {
			logger.info("======sendDailyEarlyInfo========start");
			//获取所有组织单位合同
			List<ContractInfo> conList=getContractInfo(1);
			//遍历组织单位合同
			for (ContractInfo contractInfo : conList) {
				
				String orgSiteCode=contractInfo.getSiteCode();
				//if("A00011".equals(orgSiteCode) || "44".equals(orgSiteCode.substring(0, 2))){//测试控制 ---正式删除
					Map<Object, Object> map = new HashMap<Object, Object>();
					EarlyDetailRequest earlyDetailRequest =new EarlyDetailRequest();
					earlyDetailRequest.setSiteCodeLength(6);
					earlyDetailRequest.setEarlyType(1);
					earlyDetailRequest.setIsDailyReceive(1);
					earlyDetailRequest.setOrgSiteCode(orgSiteCode);
					earlyDetailRequest.setBeginTime(DateUtils.getNextDay(new Date(), -1));
					earlyDetailRequest.setEndTime(DateUtils.getNextDay(new Date(), 0));
					earlyDetailRequest.setOrgSendStatus(2);//1-表示发送过日报  2-表示没有发送过日报
					List<EarlyDetail> earlyDetailList=earlyDetailServiceImpl.queryDailyInfo(earlyDetailRequest);
					if(earlyDetailList.size()>0){
						for(EarlyDetail earlyDetail:earlyDetailList){
							String siteCodeName=earlyDetail.getSiteCodeName();//组织单位名称
							String scanTime=DateUtils.getNextDay(DateUtils.getNow(), -1);
							
							Integer scanNum=earlyDetail.getScanNum();//有效监测站点个数
							Integer notScanNum=earlyDetail.getBlankNum();//异常站点个数
							Integer totalNum=scanNum+notScanNum;//涵盖站点数
							Integer notConnectionNum=earlyDetail.getNotConnectionNum();//首页连不通（站点数）
							Integer notConnectionSum=earlyDetail.getNotConnectionSum();////首页连不通（次数）
							String notConnectionPer7=earlyDetail.getNotConnectionPer7();////首页7日连不通率超3%站点数
							String notConnectionPer=earlyDetail.getNotConnectionPer();//7日总连不通率
							String linkHomeNum=earlyDetail.getLinkHomeNum();//首页链接不可用（站点数）
							Integer linkHomeSum=earlyDetail.getLinkHomeSum();//首页链接不可用链接（条数）
							Integer notUpdateHome=earlyDetail.getNotUpdateHome();//首页更新不及时站点数（日报使用）
							Integer updateNum=earlyDetail.getUpdateNum();//内容更新量（站点数）
							String updateAvgNum=earlyDetail.getUpdateAvgNum();//内容更新量（条数）
							Integer noUpdateChannelNum=earlyDetail.getNoUpdateChannelNum();//栏目更新不及时（站点数）
							Integer noUpdateChannelSum=earlyDetail.getNoUpdateChannelSum();//栏目更新不及时（栏目数）
							Integer servicePeriodId = earlyDetail.getServicePeriodId();
							

							String startHour=earlyDetail.getStartHour();//设置发送的  开始时间
							String endHour=earlyDetail.getEndHour();//设置发送的  结束时间
							
							map.put("scanTime", scanTime);
							map.put("siteCode", orgSiteCode);
							map.put("siteCodeName", siteCodeName);
							logger.info("=========siteCode======"+orgSiteCode+"=======siteCodeName========"+siteCodeName);
							
							map.put("totalNum", totalNum);//涵盖站点站点个数
							map.put("scanNum", scanNum);//有效监测站点个数
							map.put("notScanNum", notScanNum);//异常监测站点
							map.put("notConnectionNum", notConnectionNum);
							map.put("notConnectionSum", notConnectionSum);
							map.put("notConnectionPer7", notConnectionPer7);
							map.put("notConnectionPer", notConnectionPer);
							map.put("linkHomeNum", linkHomeNum);
							map.put("linkHomeSum", linkHomeSum);
							
							map.put("notUpdateHome", notUpdateHome);
							map.put("updateNum", updateNum);
							map.put("updateAvgNum", updateAvgNum);
							map.put("noUpdateChannelNum", noUpdateChannelNum);
							map.put("noUpdateChannelSum", noUpdateChannelSum);
							
							if(isInHour(startHour, endHour)){
								if(earlyDetail.getIsEmailReceive()==1){
									String principalEmail=earlyDetail.getPrincipalEmail();
									String linkmanEmail=earlyDetail.getLinkmanEmail();
									String linkmanEmailTwo=earlyDetail.getLinkmanEmailTwo();
									String linkmanEmailThree=earlyDetail.getLinkmanEmailThree();
									
									Map<String, String> lxyxmap = Maps.newHashMap();
									lxyxmap.put("principal", principalEmail);
									lxyxmap.put("linkman", linkmanEmail);
									lxyxmap.put("linkmanTwo", linkmanEmailTwo);
									lxyxmap.put("linkmanThree", linkmanEmailThree);
									
									Map<String, Integer> yjsfmap = Maps.newHashMap();
									yjsfmap.put("principal", 0);
									yjsfmap.put("linkman", 0);
									yjsfmap.put("linkmanTwo", 0);
									yjsfmap.put("linkmanThree", 0);
									
									
									/*
									 * 负责人发送邮件
									 */
									if(earlyDetail.getIsOrgPrincipal() ==1){
										if(isEmail(principalEmail)){
											boolean resultSend = SendEmail.sendEmail("开普云网站监测数据日报"+DateUtils.formatShortDate(DateUtils.getYesterdaytime()),
													EarlyMailType.DAY_REPORT.getName(), map, principalEmail);
											yjsfmap.put("principal", 1);
											if(resultSend){
												logger.info("======="+orgSiteCode+"=====日报=====发送邮件成功");
											}else{
												logger.info("======="+orgSiteCode+"======日报====发送邮件失败");
											}
											//插入预警发送日志
											String eMsg = getSendEmailMessage(EarlyMailType.DAY_REPORT.getName(), map,true,false); //发送的邮件内容
											addEarlyLog(orgSiteCode, servicePeriodId, 0, 1, principalEmail, eMsg,2);
										}
									}
									/*
									 * 联系人发送邮件
									 */
									if(earlyDetail.getIsOrgLinkman()!=null && earlyDetail.getIsOrgLinkman() ==1){
										if(isEmail(linkmanEmail) && ISend(lxyxmap, yjsfmap).get("linkman") == 1){
											boolean resultSendlink = SendEmail.sendEmail("开普云网站监测数据日报"+DateUtils.formatShortDate(DateUtils.getYesterdaytime()),
													EarlyMailType.DAY_REPORT.getName(), map, linkmanEmail);
											yjsfmap.put("linkman", 1);
											if(resultSendlink){
												logger.info("======="+orgSiteCode+"=====日报=====发送邮件成功");
											}else{
												logger.info("======="+orgSiteCode+"======日报====发送邮件失败");
											}
											//插入预警发送日志
											String eMsg = getSendEmailMessage(EarlyMailType.DAY_REPORT.getName(), map,true,false); //发送的邮件内容
											addEarlyLog(orgSiteCode, servicePeriodId, 0, 1, linkmanEmail, eMsg,2);
										}
									}
									if(earlyDetail.getOrgLinkmanTwo()!=null && earlyDetail.getOrgLinkmanTwo() ==1){
										if(isEmail(linkmanEmailTwo) && ISend(lxyxmap, yjsfmap).get("linkmanTwo") == 1){
											boolean resultSendlink = SendEmail.sendEmail("开普云网站监测数据日报"+DateUtils.formatShortDate(DateUtils.getYesterdaytime()),
													EarlyMailType.DAY_REPORT.getName(), map, linkmanEmailTwo);
											yjsfmap.put("linkmanTwo", 1);
											if(resultSendlink){
												logger.info("======="+orgSiteCode+"=====日报OrgLinkmanTwo=====发送邮件成功");
											}else{
												logger.info("======="+orgSiteCode+"======日报OrgLinkmanTwo====发送邮件失败");
											}
											//插入预警发送日志
											String eMsg = getSendEmailMessage(EarlyMailType.DAY_REPORT.getName(), map,true,false); //发送的邮件内容
											addEarlyLog(orgSiteCode, servicePeriodId, 0, 1, linkmanEmailTwo, eMsg,2);
										}
									}
									if(earlyDetail.getOrgLinkmanThree()!=null && earlyDetail.getOrgLinkmanThree() ==1){
										if(isEmail(linkmanEmailThree) && ISend(lxyxmap, yjsfmap).get("linkmanThree") == 1){
											boolean resultSendlink = SendEmail.sendEmail("开普云网站监测数据日报"+DateUtils.formatShortDate(DateUtils.getYesterdaytime()),
													EarlyMailType.DAY_REPORT.getName(), map, linkmanEmailThree);
											yjsfmap.put("linkmanThree", 1);
											if(resultSendlink){
												logger.info("======="+orgSiteCode+"=====日报OrgLinkmanThree=====发送邮件成功");
											}else{
												logger.info("======="+orgSiteCode+"======日报OrgLinkmanThree====发送邮件失败");
											}
											//插入预警发送日志
											String eMsg = getSendEmailMessage(EarlyMailType.DAY_REPORT.getName(), map,true,false); //发送的邮件内容
											addEarlyLog(orgSiteCode, servicePeriodId, 0, 1, linkmanEmailThree, eMsg,2);
										}
									}
								}
								if(earlyDetail.getIsTelReceive()==1){
									String tpl_id=prop.getProperty("tplDailyEarly");
									String principalPhone=earlyDetail.getPrincipalPhone();
									String linkmanPhone=earlyDetail.getLinkmanPhone();
									String linkmanPhoneTwo=earlyDetail.getLinkmanPhoneTwo();
									String linkmanPhoneThree=earlyDetail.getLinkmanPhoneThree();
									
									Map<String, String> lxdxmap = Maps.newHashMap();
									lxdxmap.put("principal", principalPhone);
									lxdxmap.put("linkman", linkmanPhone);
									lxdxmap.put("linkmanTwo", linkmanPhoneTwo);
									lxdxmap.put("linkmanThree", linkmanPhoneThree);
									
									Map<String, Integer> dxsfmap = Maps.newHashMap();
									dxsfmap.put("principal", 0);
									dxsfmap.put("linkman", 0);
									dxsfmap.put("linkmanTwo", 0);
									dxsfmap.put("linkmanThree", 0);
									
									
 									String tpl_value =getEncodeStr("#scanTime#")+"="+getEncodeStr(scanTime)
											+"&"+getEncodeStr("#siteCode#")+"="+getEncodeStr(orgSiteCode+"")
											+"&"+getEncodeStr("#totalNum#")+"="+getEncodeStr(totalNum+"")
											+"&"+getEncodeStr("#scanNum#")+"="+getEncodeStr(scanNum+"")
											+"&"+getEncodeStr("#notScanNum#")+"="+getEncodeStr(notScanNum+"")
											+"&"+getEncodeStr("#notConnectionNum#")+"="+getEncodeStr(notConnectionNum+"")
											+"&"+getEncodeStr("#notConnectionSum#")+"="+getEncodeStr(notConnectionSum+"")
											+"&"+getEncodeStr("#notConnectionPer7#")+"="+getEncodeStr(notConnectionPer7+"")
											+"&"+getEncodeStr("#notConnectionPer#")+"="+getEncodeStr(notConnectionPer+"")
											+"&"+getEncodeStr("#linkHomeNum#")+"="+getEncodeStr(linkHomeNum+"")
											+"&"+getEncodeStr("#linkHomeSum#")+"="+getEncodeStr(linkHomeSum+"")
											+"&"+getEncodeStr("#updateNum#")+"="+getEncodeStr(updateNum+"")
											+"&"+getEncodeStr("#updateAvgNum#")+"="+getEncodeStr(updateAvgNum+"")
											+"&"+getEncodeStr("#notUpdateHome#")+"="+getEncodeStr(notUpdateHome+"")
											+"&"+getEncodeStr("#noUpdateChannelNum#")+"="+getEncodeStr(noUpdateChannelNum+"")
											+"&"+getEncodeStr("#noUpdateChannelSum#")+"="+getEncodeStr(noUpdateChannelSum+"");
 									
									//封装短信数据的map
									Map<Object,Object>tplValueMap = new HashMap<Object,Object>();
									tplValueMap.put("scanTime", scanTime);
									tplValueMap.put("sitecode", orgSiteCode);
									tplValueMap.put("totalNum", totalNum);
									tplValueMap.put("scanNum", scanNum);
									tplValueMap.put("notScanNum", notScanNum);
									tplValueMap.put("notConnectionNum", notConnectionNum);
									tplValueMap.put("notConnectionSum", notConnectionSum);
									tplValueMap.put("notConnectionPer7", notConnectionPer7);
									tplValueMap.put("notConnectionPer", notConnectionPer);
									tplValueMap.put("linkHomeNum", linkHomeNum);
									tplValueMap.put("linkHomeSum", linkHomeSum);
									tplValueMap.put("notUpdateHome", notUpdateHome);
									tplValueMap.put("updateNum", updateNum);
									tplValueMap.put("updateAvgNum", updateAvgNum);
									tplValueMap.put("noUpdateChannelNum", noUpdateChannelNum);
									tplValueMap.put("noUpdateChannelSum", noUpdateChannelSum);
									
									if(earlyDetail.getIsOrgPrincipal() ==1){
										if(StringUtils.isEmpty(principalPhone) ||
												StringUtils.isEmpty(scanTime) ||
												StringUtils.isEmpty(orgSiteCode) ||
												StringUtils.isEmpty(totalNum+"") ||
												StringUtils.isEmpty(scanNum+"") ||
												StringUtils.isEmpty(notScanNum+"") ||
												StringUtils.isEmpty(notConnectionPer) ||
												StringUtils.isEmpty(linkHomeNum) ||
												StringUtils.isEmpty(notUpdateHome+"") ||
												StringUtils.isEmpty(updateNum +"") ||
												StringUtils.isEmpty(updateAvgNum)){
											logger.info("=====日报  负责人发送短信======存在参数为空");
										}else{
											if(isMobile(principalPhone)){
												String returnRB=YunpianSmsUtil.tplSendSms(tpl_id, tpl_value, principalPhone);
												dxsfmap.put("principal", 1);
												logger.info("=====日报  负责人发送短信======code:"+JSONObject.fromObject(returnRB).get("code"));
												//插入预警发送日志
												//获得短信发送的内容
												String sMsg = getSendShortMessage(tpl_id, tplValueMap);
												addEarlyLog(orgSiteCode, servicePeriodId, 0, 2, principalPhone, sMsg,2);
											}else{
												logger.info("=====日报  负责人发送短信======负责人手机号码格式不正确");
											}
										}
									}
									if(earlyDetail.getIsOrgLinkman()!=null && earlyDetail.getIsOrgLinkman() ==1){
										if(StringUtils.isEmpty(principalPhone) ||
												StringUtils.isEmpty(scanTime) ||
												StringUtils.isEmpty(orgSiteCode) ||
												StringUtils.isEmpty(scanNum+"") ||
												StringUtils.isEmpty(notConnectionPer) ||
												StringUtils.isEmpty(linkHomeNum) ||
												StringUtils.isEmpty(notUpdateHome+"") ||
												StringUtils.isEmpty(updateNum +"") ||
												StringUtils.isEmpty(updateAvgNum)){
											logger.info("=====日报   联系人发送短信======存在参数为空");
										}else{
											if(isMobile(linkmanPhone) && ISend(lxdxmap, dxsfmap).get("linkman") == 1){
												String returnRBL=YunpianSmsUtil.tplSendSms(tpl_id, tpl_value, linkmanPhone);
												dxsfmap.put("linkman", 1);
												logger.info("=====日报  联系人发送短信======code:"+JSONObject.fromObject(returnRBL).get("code"));
												//插入预警发送日志
												//获得短信发送的内容
												String sMsg = getSendShortMessage(tpl_id, tplValueMap);
												addEarlyLog(orgSiteCode, servicePeriodId, 0, 2, linkmanPhone, sMsg,2);
											}else{
												logger.info("=====日报  联系人发送短信======联系人手机号码格式不正确");
											}
											
										}
									}
									if(earlyDetail.getOrgLinkmanTwo()!=null &&earlyDetail.getOrgLinkmanTwo() ==1){
										if(isMobile(linkmanPhoneTwo) && ISend(lxdxmap, dxsfmap).get("linkmanTwo") == 1){
											String returnRBL=YunpianSmsUtil.tplSendSms(tpl_id, tpl_value, linkmanPhoneTwo);
											dxsfmap.put("linkmanTwo", 1);
											//插入预警发送日志
											//获得短信发送的内容
											String sMsg = getSendShortMessage(tpl_id, tplValueMap);
											addEarlyLog(orgSiteCode, servicePeriodId, 0, 2, linkmanPhoneTwo, sMsg,2);
										}
									}
									if(earlyDetail.getOrgLinkmanThree()!=null &&earlyDetail.getOrgLinkmanThree() ==1){
										if(isMobile(linkmanPhoneThree) && ISend(lxdxmap, dxsfmap).get("linkmanThree") == 1){
											String returnRBL=YunpianSmsUtil.tplSendSms(tpl_id, tpl_value, linkmanPhoneThree);
											dxsfmap.put("linkmanThree", 1);
											//插入预警发送日志
											//获得短信发送的内容
											String sMsg = getSendShortMessage(tpl_id, tplValueMap);
											addEarlyLog(orgSiteCode, servicePeriodId, 0, 2, linkmanPhoneThree, sMsg,2);
										}
									}
								}
								earlyDetail.setOrgSendStatus(1);//修改状态为发送
								earlyDetailServiceImpl.update(earlyDetail);
							}
							
						}
					}
			//}
			}
			logger.info("======sendDailyEarlyInfo========end");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Title: 组织单位发送预警定时任务调用
	 * @Description:
	 * @author liujc@ucap.com.cn	2016-10-18上午10:44:09
	 */
	public  void sendOrgEarlyTimer(){
		try {
			logger.info("sendOrgEarlyTimer=========================start");
			/**
			 * 分两种类型处理 
				 * 非人工   8 10  getContractInfo
				 * 人工     11 12 13  getContractListAndSPId
			 */
			//非人工
			List<ContractInfo> notPersonList=getContractInfo(1);
			if(notPersonList!=null && notPersonList.size()>0){
				for (ContractInfo contractInfo : notPersonList) {
					String siteCode=contractInfo.getSiteCode();
					//if("A00011".equals(siteCode)){
						ConfigEarlyRequest configRequest=new ConfigEarlyRequest();
						configRequest.setSiteCode(siteCode);
						configRequest.setEarlyType(2);
						List<ConfigEarly>  configList=configEarlyServiceImpl.queryList(configRequest);
						if(configList!=null && configList.size()>0){
							ConfigEarly configEarly=configList.get(0);
							//首页连不通比例超过3%
							if(configEarly.getHomeConnectionPer()!=null && configEarly.getHomeConnectionPer()==1){
								sendOrgSingleEarlyInfo(8,siteCode,null,null);
							}
						}
					//}
				}
			}
			
			//人工问题
			List<ContractInfoRequest> personList=getContractListAndSPId(1);
			if(personList!=null && personList.size()>0){
				for (ContractInfoRequest contractInfoRequest : personList) {
					String siteCode=contractInfoRequest.getSiteCode();
					//if("A00011".equals(siteCode)){
						Integer servicePeriodId=contractInfoRequest.getServicePeriodId();//服务周期id
						//获取所有收费组织单位
						ConfigEarlyRequest configRequest=new ConfigEarlyRequest();
						configRequest.setSiteCode(siteCode);
						configRequest.setEarlyType(2);
						List<ConfigEarly>  configList=configEarlyServiceImpl.queryList(configRequest);
						if(configList!=null && configList.size()>0){
							ConfigEarly configEarly=configList.get(0);
							//是否接收空白栏目超过2个预警 1接收 2不接收
							if(configEarly.getBlankChannel()!=null && configEarly.getBlankChannel()==1){
								sendOrgSingleEarlyInfo(11,siteCode,servicePeriodId,null);
							}
							//是否接收超过6个栏目不更新预警 1接收 2不接收
							if(configEarly.getNotUpdateChannel()!=null && configEarly.getNotUpdateChannel()==1){
								sendOrgSingleEarlyInfo(12,siteCode,servicePeriodId,null);
							}
							//是否接收互动回应差栏目超过三个月未回应预警 1接收 2不接收
							if(configEarly.getSecurityResponse()!=null && configEarly.getSecurityResponse()==1){
								sendOrgSingleEarlyInfo(13,siteCode,servicePeriodId,null);
							}
						}
					//}
				}
			}
			logger.info("sendOrgEarlyTimer=========================end");
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	/**
	 * @Title: 填报单位发送预警定时任务调用
	 * @Description:
	 * @author liujc@ucap.com.cn	2016-10-18上午10:43:41
	 */
	public void sendEarlyTimer(){
		try {
			logger.info("sendEarlyTimer=========================start");
			
			/**
			 * 分两种类型处理 
				 * 非人工   8 10  getContractInfo
				 * 人工     11 12 13  getContractListAndSPId
			 */
			
			//非人工
			List<ContractInfo> notPersonList=getContractInfo(2);
			if(notPersonList !=null && notPersonList.size()>0){
				for (ContractInfo contractInfo : notPersonList) {
					String siteCode=contractInfo.getSiteCode();
					//if("N000000546".equals(siteCode) || "N000000547".equals(siteCode) || "N000000548".equals(siteCode)){
						ConfigEarlyRequest configRequest=new ConfigEarlyRequest();
						configRequest.setSiteCode(siteCode);
						List<ConfigEarly>  configList=configEarlyServiceImpl.queryList(configRequest);
						if(configList!=null && configList.size()>0){
							ConfigEarly configEarly=configList.get(0);
							//首页不连通率超过3%
							if(configEarly.getHomeConnectionPer()!=null && configEarly.getHomeConnectionPer()==1){
								sendSingleEarlyInfo(8,siteCode,null,null);
							}
						}
						
					//}
				}
			}
			//人工
			List<ContractInfoRequest>  personList=getContractListAndSPId(2);
			if(personList!=null && personList.size()>0){
				for (ContractInfoRequest contractInfoRequest : personList) {
					String siteCode=contractInfoRequest.getSiteCode();
					//if("N000000546".equals(siteCode) || "N000000547".equals(siteCode) || "N000000548".equals(siteCode)){
						Integer servicePeriodId=contractInfoRequest.getServicePeriodId();
						ConfigEarlyRequest configRequest=new ConfigEarlyRequest();
							configRequest.setSiteCode(siteCode);
							List<ConfigEarly>  configList=configEarlyServiceImpl.queryList(configRequest);
							if(configList!=null && configList.size()>0){
								ConfigEarly configEarly=configList.get(0);
								
								//是否接收空白栏目超过5个预警 1接收 2不接收
								if(configEarly.getBlankChannel()!=null && configEarly.getBlankChannel()==1){
									sendSingleEarlyInfo(11,siteCode,servicePeriodId,null);
								}
								
								//是否接收超过8个栏目不更新预警 1接收 2不接收
								if(configEarly.getNotUpdateChannel()!=null && configEarly.getNotUpdateChannel()==1){
									sendSingleEarlyInfo(12,siteCode,servicePeriodId,null);
								}
								//是否接收互动回应差栏目超过三个月未回应预警 1接收 2不接收
								if(configEarly.getSecurityResponse()!=null && configEarly.getSecurityResponse()==1){
									sendSingleEarlyInfo(13,siteCode,servicePeriodId,null);
								}
						}
					//}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description: 连通性  每个5分钟扫描一次
	 * @author cuichx --- 2016-10-27下午2:31:09
	 */
	public void sendConnEarlySS(){
		try {
			logger.info("sendConnEarlySS====================start");
			//组织单位
			List<ContractInfo> conOrgList=getContractInfo(1);
			if(conOrgList!=null && conOrgList.size()>0){
				//遍历所有的组织单位合同
				for (ContractInfo contractInfo : conOrgList) {
					String siteCode=contractInfo.getSiteCode();
					//if("A00011".equals(siteCode) || "44".equals(siteCode.substring(0, 2))){
						logger.info("==============siteCode:"+siteCode);
						logger.info("=========组织单位实时预警==============");
						sendOrgSingleEarlyInfo(1,siteCode,null,contractInfo.getId());
					//}
				}
			}
			
			//填报单位
			List<ContractInfo> conTBList=getContractInfo(2);
			if(conTBList!=null  && conTBList.size()>0){
				//遍历所有的填报单位合同
				for (ContractInfo contractInfo : conTBList) {

					String siteCode=contractInfo.getSiteCode();
					//if("N000000546".equals(siteCode) || "N000000547".equals(siteCode) || "N000000548".equals(siteCode) || "44".equals(siteCode.substring(0, 2))){
						logger.info("=========填报单位实时预警==============");
						ConfigEarlyRequest configRequest=new ConfigEarlyRequest();
						configRequest.setSiteCode(siteCode);
						List<ConfigEarly>  configList=configEarlyServiceImpl.queryList(configRequest);
						if(configList!=null && configList.size()>0){
							ConfigEarly configEarly=configList.get(0);
							if(configEarly.getHomeConnection()!=null && configEarly.getHomeConnection()==1){
								sendSingleEarlyInfo(1,siteCode,null,contractInfo.getId());
							}
						}
					//}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * @Description: 后台调用预警发送
	 * @author cuichx --- 2016-11-14上午11:14:10     
	 * @param list
	 * @param type
	 */
	public void sendSingleEarlyInfoHT(List<String> list,int type){
		try {
			logger.info("=========sendSingleEarlyInfoHT=======begin");
			if(list !=null && list.size()>0){
				for(String earlyId:list){
					EarlyDetail earlyDetail =earlyDetailServiceImpl.get(Integer.valueOf(earlyId));
					if(earlyDetail!=null){
						/**
						 * 1  查询填报单位的配置   是否要给填报单位发送 
						 * 2 查询组织单位预警配置  判断组织单位是否发送   同时还要判断是否通知填报单位
						 */
						Integer servicePeroidId=earlyDetail.getServicePeriodId();
						String siteCode=earlyDetail.getSiteCode();
						String url=earlyDetail.getUrl();
						String errorProportion=earlyDetail.getErrorProportion();
						String earlyType=EarlyType.getNameByCode(earlyDetail.getType());
						String scanDate=earlyDetail.getScanDate();
						String queDescribe=earlyDetail.getQueDescribe();
						//推荐词汇
						String expectTerms= earlyDetail.getExpectTerms()==null?"":earlyDetail.getExpectTerms();
						//疑似错误
						String wrongTerm= earlyDetail.getWrongTerm()==null?"":earlyDetail.getWrongTerm();
					
						//封装邮件发送模版消息
						Map<Object, Object> mailMap=new HashMap<Object, Object>();
						mailMap.put("scanTime", earlyDetail.getScanTime());
						mailMap.put("scanDate", earlyDetail.getScanDate());
						mailMap.put("noUpdateDay", earlyDetail.getNoUpdateDay());
						mailMap.put("lastUpdateDate", earlyDetail.getLastUpdateDate());
						mailMap.put("newEarlNum", 1);
						
						//封装预警消息
						Map<String, Object> map = new HashMap<String, Object>();
						
						map.put("earlyId", earlyId);
						map.put("scanTime", earlyDetail.getScanTime());
						map.put("scanDate", earlyDetail.getScanDate());
						map.put("noUpdateDay", earlyDetail.getNoUpdateDay());
						map.put("lastUpdateDate", earlyDetail.getLastUpdateDate());
						map.put("newEarlNum", 1);
						if(servicePeroidId!=null){
							map.put("servicePeroidId", servicePeroidId);
						}else{
							map.put("servicePeroidId", "");
						}
						logger.info("========servicePeroidId======="+servicePeroidId);
						if(StringUtils.isNotEmpty(url)){
							map.put("url", url);
						}else{
							map.put("url", "");
						}
						if(StringUtils.isNotEmpty(errorProportion)){
							map.put("errorProportion", errorProportion);//首页不连通比例
						}else{
							map.put("errorProportion", "");//首页不连通比例
						}
						
						map.put("earlyType", earlyType);//问题类型
						
						if(StringUtils.isNotEmpty(queDescribe)){
							map.put("queDescribe", queDescribe);//问题描述
						}else{
							map.put("queDescribe", "");//问题描述
						}
						map.put("scanDate", scanDate);//监测时间
						map.put("loginUrl", "https://jg.kaipuyun.cn/");//点击查看详情url
						
						map.put("expectTerms", expectTerms);
						map.put("wrongTerm", wrongTerm);
						map.put("siteCode", siteCode);
						
						String title="";
						if(type==6){//内容正确性，4 当天
							title="开普云网站监测 - 疑似错别字";
							map.put("ftl", "earlyMail7_cbz.ftl");
							map.put("tplId", "tplSeriousMistake");
							
							map.put("title", title);
							map.put("type", type);
							map.put("code", "");

							//填报单位是否发送预警
							sendEarlyTB1(map, mailMap);
							//组织单位是否预警
							sendEarlyOrg1(map,mailMap);
						}else if(type==9){
							//网站疑似被挂码或被篡改，5 当天
							title="开普云网站监测 - 严重问题";
							map.put("tips", "根据十五号文规定，网站出现严重错误的，即单项否决。");
							map.put("ftl", "earlyMail7.ftl");
							map.put("tplId", "tplSeriousProblem");
							
							map.put("title", title);
							map.put("type", type);
							
							/**
							 * 严重问题需要根据服务周期，获取对应的填报单位编码或者组织单位编码
							 */
							if(servicePeroidId !=null){
								Map<String, Object> paramMap=new HashMap<String, Object>();
								paramMap.put("servicePdId", servicePeroidId);
								List<ContractInfoRequest>  conList=contractInfoServiceImpl.queryListByMap(paramMap);
								if(conList!=null && conList.size()>0){
									String code=conList.get(0).getSiteCode();
									if(StringUtils.isNotEmpty(code)){
										
										if(code.length()==6){//组织单位
											map.put("code", code);
											//组织单位是否预警
											sendEarlyOrg1(map,mailMap);
										}else{
											//填报单位是否发送预警
											sendEarlyTB1(map, mailMap);
										}
									}
								}
							}else{
								logger.info("===========手动发送严重问题    服务周期id为空===========");
							}
						}else if(type == 10){//首页未更新   Add by renpb
							title="开普云网站监测 - 首页更新不及时";
							map.put("ftl", "earlyMail7_sybgx.ftl");
							map.put("tplId", "tplNoUpdateDay");
							
							map.put("title", title);
							map.put("type", type);
							map.put("code", "");
							map.put("earlyType","首页更新不及时");
							//填报单位是否发送预警
							sendEarlyTB1(map, mailMap);
							//组织单位是否预警
							sendEarlyOrg1(map,mailMap);
						}
						//更新预警发送状态
						earlyDetail.setSendSmsStatus(1);//短信发送状态  1-发送成功
						earlyDetail.setSendEmailState(1);//邮件发送状态 1-发送成功
						earlyDetail.setSendWxStatus(1);//微信发送状态 1-发送成功
						earlyDetailServiceImpl.update(earlyDetail);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * @Title: 组织单位单站预警信息
	 * @Description: TODO
	 * @author liujc@ucap.com.cn	2016-8-3上午11:15:57
	 * @param type
	 */
	public void sendOrgSingleEarlyInfo(int type,String siteCodeStr,Integer servicePeriodId,Integer contractInfoId){
		logger.info("sendOrgSingleEarlyInfo start------========================组织单位单站预警信息   开始==type="+type);
		try {
			
			orgSingleList = new ArrayList<Map<Object, Object>>();
			
			String title="";
			String ftl="";
			ConfigEarlyRequest configEarlyRequest =new ConfigEarlyRequest();
			configEarlyRequest.setSiteCodeLength(6);
			configEarlyRequest.setEarlyType(2);
			configEarlyRequest.setSiteCode(siteCodeStr);
			//查询组织单位预警配置信息
			List<ConfigEarly>  configEarlyList=configEarlyServiceImpl.queryOrgSingleSiteCode(configEarlyRequest);
			logger.info("sendOrgSingleEarlyInfo configEarlyList.size------========================"+configEarlyList.size());
			if(configEarlyList.size()>0  ){
				//循环预警配置表  6位siteCode
				for(ConfigEarly configEarly:configEarlyList){
					Integer typeFlag=typeFlag(type, configEarly);//8项指标是否接受 1接受  2不接受
					logger.info("sendOrgSingleEarlyInfo typeFlag------========================"+typeFlag);
					
					if(typeFlag==1){
						Map<Object, Object> orgMap = new HashMap<Object, Object>();
						String orgSiteCode=configEarly.getSiteCode();//组织单位sitecode
						Integer isOrgPrincipalReceive=configEarly.getIsOrgPrincipal();//组织单位 负责人是否接受
						Integer isOrgLinkmanReceive=configEarly.getIsOrgLinkman();//组织单位 联系人是否接受
						
						Integer orgLinkmanTwo=configEarly.getOrgLinkmanTwo();
						Integer orgLinkmanThree=configEarly.getOrgLinkmanThree();
				
						Map<String, Object> treeMap=new HashMap<String, Object>();
						treeMap.put("orgSiteCode", orgSiteCode);
						//获得当先循环的 组织单位siteCode 下的填报siteCode	
						List<DatabaseTreeInfo> dataBaseList=databaseTreeInfoServiceImpl.queryDownSiteInfo(treeMap);
						if(dataBaseList.size()>0){
							//循环 填报单位 siteCode 10位
							for(DatabaseTreeInfo databaseLink: dataBaseList){
								configEarly.setDataBaseLinkType(databaseLink.getLayerType());
								List<Map<Object, Object>> oneSitelist = new ArrayList<Map<Object, Object>>();
								String siteCode=databaseLink.getSiteCode();
								
								EarlyDetailRequest earlyDetailRequest=new EarlyDetailRequest();
								earlyDetailRequest.setSiteCode(siteCode);
								earlyDetailRequest.setType(type);
								//type 是预警类型
								if(type==1){
									//首页连通性 2 当天数据
									title="开普云网站监测 - 首页连不通";
									//查询预警临时表
									EarlyDetailTempRequest tempRequest2=new EarlyDetailTempRequest();
									tempRequest2.setType(1);
									tempRequest2.setSiteCode(siteCode);
									tempRequest2.setSendStatus(2);
									tempRequest2.setContractInfoId(contractInfoId);
									List<EarlyDetailTemp> earlyDetailList=earlyDetailTempServiceImpl.querySiteEarlyInfo(tempRequest2);
									if(earlyDetailList!=null && earlyDetailList.size()>0){
										logger.info("=====组织单位---首页不连通预警，需要发送======");
										forDataConn(title,  ftl, earlyDetailList, configEarly, oneSitelist, orgSingleList);
									}else{
										logger.info("=====组织单位---首页不连通预警，不需要再发送======");
									}
								}else if(type==8){
									//首页连不连通率超过3%，3 昨天数据
									title="开普云网站监测 - 首页连不连通率累计超过3%";
									ftl="earlyMail6.ftl";
									configEarly.setTplId("tplHomeNotConnectedRatio");
									earlyDetailRequest.setScanTime(DateUtils.getYesterdayStr());
									earlyDetailRequest.setOrgSendStatus(2);//否为组织单位发送预警 1-是；2否  
									List<EarlyDetail> earlyDetailList=earlyDetailServiceImpl.querySiteEarlyInfo(earlyDetailRequest);
									forData( title,  "earlyMail6.ftl", earlyDetailList, configEarly,  oneSitelist, orgSingleList);
								}else if(type==11){
									//空白栏目超过5个，7 昨天的数据
									title="开普云网站监测 - 空白栏目超过2个";
									ftl="earlyMail7.ftl";
									configEarly.setTplId("tplblankChannel");
									earlyDetailRequest.setScanTime(DateUtils.getYesterdayStr());
									earlyDetailRequest.setServicePeriodId(servicePeriodId);
									earlyDetailRequest.setOrgSendStatus(2);//否为组织单位发送预警 1-是；2否  
									List<EarlyDetail> earlyDetailList=earlyDetailServiceImpl.querySiteEarlyInfo(earlyDetailRequest);
									forData( title,  "earlyMail7.ftl", earlyDetailList, configEarly,  oneSitelist, orgSingleList);
								}else if(type==12){
									//栏目不更新超过8个， 8 昨天的数据
									title="开普云网站监测 - 基本信息不更新的栏目超过6个";
									ftl="earlyMail7.ftl";
									configEarly.setTplId("tplNoUpdateChannel");
									earlyDetailRequest.setScanTime(DateUtils.getYesterdayStr());
									earlyDetailRequest.setServicePeriodId(servicePeriodId);
									earlyDetailRequest.setOrgSendStatus(2);//否为组织单位发送预警 1-是；2否  
									List<EarlyDetail> earlyDetailList=earlyDetailServiceImpl.querySiteEarlyInfo(earlyDetailRequest);
									forData( title,  "earlyMail7.ftl", earlyDetailList, configEarly, oneSitelist, orgSingleList);
								}else if(type==13){
									//互动回应差栏目超过3个月未回应 9 昨天的数据
									title="开普云网站监测 - 互动回应栏目长期未回应";
									ftl="earlyMail8.ftl";
									configEarly.setTplId("tplInteractiveResponse");
									earlyDetailRequest.setScanTime(DateUtils.getYesterdayStr());
									earlyDetailRequest.setServicePeriodId(servicePeriodId);
									earlyDetailRequest.setOrgSendStatus(2);//否为组织单位发送预警 1-是；2否  
									List<EarlyDetail> earlyDetailList=earlyDetailServiceImpl.querySiteEarlyInfo(earlyDetailRequest);
									forData( title,  "earlyMail7.ftl", earlyDetailList, configEarly,  oneSitelist, orgSingleList);
								}
							}
							/******************组织单位接受邮件****************/
							if(configEarly.getIsEmailReceive()==1){
								String principalEmail=configEarly.getPrincipalEmail();
								String linkmanEmail=configEarly.getLinkmanEmail();
								String linkmanEmailTwo=configEarly.getLinkmanEmailTwo();
								String linkmanEmailThree=configEarly.getLinkmanEmailThree();
								

								String startHour=configEarly.getStartHour();//设置发送的  开始时间
								String endHour=configEarly.getEndHour();//设置发送的  结束时间
								
								Map<String, String> lxyxmap = Maps.newHashMap();
								lxyxmap.put("principal", principalEmail);
								lxyxmap.put("linkman", linkmanEmail);
								lxyxmap.put("linkmanTwo", linkmanEmailTwo);
								lxyxmap.put("linkmanThree", linkmanEmailThree);
								
								Map<String, Integer> yjsfmap = Maps.newHashMap();
								yjsfmap.put("principal", 0);
								yjsfmap.put("linkman", 0);
								yjsfmap.put("linkmanTwo", 0);
								yjsfmap.put("linkmanThree", 0);
								
								orgMap.put("scanTime", DateUtils.getYesterdayStr());
								
								if((type==8 || type==11 || type==12 ||type==13) && isInHour(startHour, endHour)){
									//如果组织单位下的填报单位 没有预警 不需要发送了
									if(orgSingleList.size()>0){
										//if(type!=1){//首页不连通预警--需求要求有一封发一封   不用汇总发了
											orgMap.put("newEarlNum", orgSingleList.size());
											orgMap.put("dataList", orgSingleList);//给组织单位发放的邮件
											//负责人发送邮件
											if(isOrgPrincipalReceive ==1){
												if(isEmail(principalEmail)){
													SendEmail.sendEmail(title, ftl, orgMap, principalEmail);
													yjsfmap.put("principal", 1);
													//如果发送成功 向预警日志表中加入数据
													//获得邮件发送的内容
													@SuppressWarnings("unchecked")
													List<Map<Object, Object>> datalist = (List<Map<Object, Object>>) orgMap.get("dataList");
													StringBuffer eMgs1 = new StringBuffer();
													eMgs1.append("  网站告警信息:");
													eMgs1.append("  尊敬的客户，您好：");
													eMgs1.append( " 截止到"+DateUtils.getYesterdayStr()+"，开普云监管平台监测到"+ orgSingleList.size()+"个预警信息，请及时处理。详情如下：");

													int count = 0;
													for (Map<Object, Object> map : datalist) {
														count ++;
														eMgs1.append(count + "," +getSendEmailMessage(ftl, map,false,true));
													}
													addEarlyLog(siteCodeStr,servicePeriodId,type,1,principalEmail,eMgs1.toString(),2);
												}
											}
											//联系人发送邮件
											if(isOrgLinkmanReceive ==1){
												if(isEmail(linkmanEmail) && ISend(lxyxmap, yjsfmap).get("linkman") == 1){
													SendEmail.sendEmail(title, ftl, orgMap, linkmanEmail);
													yjsfmap.put("linkman", 1);
													//如果发送成功 向预警日志表中加入数据
													//获得邮件发送的内容
													@SuppressWarnings("unchecked")
													List<Map<Object, Object>> datalist = (List<Map<Object, Object>>) orgMap.get("dataList");
													StringBuffer eMgs1 = new StringBuffer();
													eMgs1.append("  网站告警信息:");
													eMgs1.append("  尊敬的客户，您好：");
													eMgs1.append( " 截止到"+DateUtils.getYesterdayStr()+"，开普云监管平台监测到"+ orgSingleList.size()+"个预警信息，请及时处理。详情如下：");
													int count = 0;
													for (Map<Object, Object> map : datalist) {
														count ++;
														eMgs1.append(count + "," + getSendEmailMessage(ftl, map,false,true));
													}
													addEarlyLog(siteCodeStr,servicePeriodId,type,1,linkmanEmail,eMgs1.toString(),2);
												}
											}
											
											if(orgLinkmanTwo != null && orgLinkmanTwo == 1 ){
												if(isEmail(linkmanEmailTwo) && ISend(lxyxmap, yjsfmap).get("linkmanTwo") == 1){
													SendEmail.sendEmail(title, ftl, orgMap, linkmanEmailTwo);
													yjsfmap.put("linkmanTwo", 1);
													//如果发送成功 向预警日志表中加入数据
													//获得邮件发送的内容
													@SuppressWarnings("unchecked")
													List<Map<Object, Object>> datalist = (List<Map<Object, Object>>) orgMap.get("dataList");
													StringBuffer eMgs1 = new StringBuffer();
													eMgs1.append("  网站告警信息:");
													eMgs1.append("  尊敬的客户，您好：");
													eMgs1.append( " 截止到"+DateUtils.getYesterdayStr()+"，开普云监管平台监测到"+ orgSingleList.size()+"个预警信息，请及时处理。详情如下：");
													int count = 0;
													for (Map<Object, Object> map : datalist) {
														count ++;
														eMgs1.append(count + "," + getSendEmailMessage(ftl, map,false,true));
													}
													addEarlyLog(siteCodeStr,servicePeriodId,type,1,linkmanEmailTwo,eMgs1.toString(),2);
												}
											}
											
											if(orgLinkmanThree != null && orgLinkmanThree ==1){
												if(isEmail(linkmanEmailThree) && ISend(lxyxmap, yjsfmap).get("linkmanThree") == 1){
													SendEmail.sendEmail(title, ftl, orgMap, linkmanEmailThree);
													yjsfmap.put("linkmanThree", 1);
													//如果发送成功 向预警日志表中加入数据
													//获得邮件发送的内容
													@SuppressWarnings("unchecked")
													List<Map<Object, Object>> datalist = (List<Map<Object, Object>>) orgMap.get("dataList");
													StringBuffer eMgs1 = new StringBuffer();
													eMgs1.append("  网站告警信息:");
													eMgs1.append("  尊敬的客户，您好：");
													eMgs1.append( " 截止到"+DateUtils.getYesterdayStr()+"，开普云监管平台监测到"+ orgSingleList.size()+"个预警信息，请及时处理。详情如下：");
													int count = 0;
													for (Map<Object, Object> map : datalist) {
														count ++;
														eMgs1.append(count + "," + getSendEmailMessage(ftl, map,false,true));
													}
													addEarlyLog(siteCodeStr,servicePeriodId,type,1,linkmanEmailThree,eMgs1.toString(),2);
												}
											}
										//}
									}
								}
							}
						}
					}
				}
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * @Title: 发放预警邮件 封装 数据map 调用模板----填报单位
	 * @Description:填报实时预警   TODO
	 * @author liujc@ucap.com.cn	2016-8-3上午11:53:54
	 * @param title 邮件标题
	 * @param ftl 邮件模板名称
	 * @param earlyDetailList
	 * @param configEarly
	 * @param map
	 * @param oneSitelist
	 * @param orglist
	 */
	@SuppressWarnings("unused")
	public void forDataTBConn(String title, String ftl,List<EarlyDetailTemp> earlyDetailList,ConfigEarly configEarly){
		String tpl_id="";
		String tpl_value="";
		try {
			//填报单位配置信息    负责人是否接受
			Integer isPrincipalReceive=configEarly.getIsPrincipalReceive();
			//填报单位配置信息  联系人是否接受
			Integer isLinkmanReceive=configEarly.getIsLinkmanReceive();
			Integer isLinkmanReceiveTwo=configEarly.getIsLinkmanTwo();
			Integer isLinkmanReceiveThree=configEarly.getIsLinkmanThree();
			String startHour=configEarly.getStartHour();//设置发送的  开始时间
			String endHour=configEarly.getEndHour();//设置发送的  结束时间
			
			if(earlyDetailList !=null && earlyDetailList.size()>0){
				for(int i=0;i<earlyDetailList.size();i++){
					Map<Object, Object> map = new HashMap<Object, Object>();
					
					EarlyDetailTemp earlyDetail=earlyDetailList.get(i);
					String siteCode=earlyDetail.getSiteCode();
					String siteCodeName=earlyDetail.getSiteCodeName();
					
					String principalEmail =earlyDetail.getPrincipalEmail();//10位 负责人邮箱地址
					String linkmanEmail =earlyDetail.getLinkmanEmail();//10位 联系人邮箱地址
					String linkmanEmailTwo =earlyDetail.getLinkmanEmailTwo();//10位 联系人邮箱地址
					String linkmanEmailThree =earlyDetail.getLinkmanEmailThree();//10位 联系人邮箱地址
					String principalPhone=earlyDetail.getPrincipalPhone();//10位 短信
					String linkmanPhone=earlyDetail.getLinkmanPhone();//10位 短信
					String linkmanPhoneTwo=earlyDetail.getLinkmanPhoneTwo();//10位 短信
					String linkmanPhoneThree=earlyDetail.getLinkmanPhoneThree();//10位 短信
					
					Map<String, String> lxyxmap = Maps.newHashMap();//联系方式
					lxyxmap.put("principal", principalEmail);
					lxyxmap.put("linkman", linkmanEmail);
					lxyxmap.put("linkmanTwo", linkmanEmailTwo);
					lxyxmap.put("linkmanThree", linkmanEmailThree);
					Map<String, Integer> yjsfmap = Maps.newHashMap();//是否发送过 0没有1发送过
					yjsfmap.put("principal", 0);
					yjsfmap.put("linkman", 0);
					yjsfmap.put("linkmanTwo", 0);
					yjsfmap.put("linkmanThree", 0);
					
					Map<String, String> lxdxmap = Maps.newHashMap();//联系方式
					lxdxmap.put("principal", principalPhone);
					lxdxmap.put("linkman", linkmanPhone);
					lxdxmap.put("linkmanTwo", linkmanPhoneTwo);
					lxdxmap.put("linkmanThree", linkmanPhoneThree);
					Map<String, Integer> dxsfmap = Maps.newHashMap();//是否发送过 0没有1发送过
					dxsfmap.put("principal", 0);
					dxsfmap.put("linkman", 0);
					dxsfmap.put("linkmanTwo", 0);
					dxsfmap.put("linkmanThree", 0);
					
					String url=earlyDetail.getUrl();
					String scanDate=earlyDetail.getScanTime();
					String queDescribe=earlyDetail.getErrorDescribe();
					String errorCode=earlyDetail.getErrorCode();
					Integer type=earlyDetail.getType();//预警类型
					Integer tbSendStatus=earlyDetail.getSendStatus();//是否为填报单位配置发送的预警1-是；2-否
					String jumpUrl=earlyDetail.getJumpUrl();
					String homeUrl=earlyDetail.getHomeUrl();
					String continuedTime=DateUtils.formatStandardDateTime(earlyDetail.getContinuedTime());
					String period=QueueType.getNameByCode(earlyDetail.getQueue());
					String periodTime=getHourAndMin(scanDate, continuedTime);
					int sendstatus = earlyDetail.getSendStatus();//是否发送预警
					int orgTbStatus = earlyDetail.getOrgTbStatus();//组织单位发送预警类型（1：本组织，2：下级填报单位），0：填报单位本身
					
					map.put("scanTime", earlyDetail.getScanTime());
					map.put("newEarlNum", earlyDetailList.size());
					map.put("siteCodeName", siteCodeName);
					map.put("siteCode", siteCode);

					map.put("websiteUrl", url);
					if(StringUtils.isNotEmpty(url)){
						map.put("url", url);//网站地址
					}else{
						map.put("url", "");//网站地址
					}
					map.put("queDescribe", queDescribe);//问题描述
					map.put("scanDate", scanDate);//监测时间
					map.put("loginUrl", "https://jg.kaipuyun.cn/");//点击查看详情url
					
					tpl_id = prop.getProperty(configEarly.getTplId());
					//封装短信传送数据的Map
					Map<Object,Object>tplValueMap = new HashMap<Object,Object>();
					tplValueMap.put("siteCodeName", siteCodeName);
					tplValueMap.put("scanTime", scanDate);
					tplValueMap.put("preTime", continuedTime);
					tplValueMap.put("periodTime", periodTime);
					tplValueMap.put("queDescribe", errorCode+queDescribe);
					tplValueMap.put("period", period);
					tplValueMap.put("url", url);
					tplValueMap.put("websiteUrl", url);
					tplValueMap.put("loginUrl", "https://jg.kaipuyun.cn/");
					//首页不连通
					if("200".equals(errorCode)){//使用连通模版
						title="开普云网站监测-首页恢复连通";
						tpl_value=getEncodeStr("#siteCodeName#")+"="+getEncodeStr(siteCodeName)
								+"&"+getEncodeStr("#scanTime#")+"="+getEncodeStr(scanDate)
								+"&"+getEncodeStr("#periodTime#")+"="+getEncodeStr(periodTime);
						tpl_id=prop.getProperty("tplHomeConn");
						ftl="earlyMail5_lt.ftl";
					}else{//使用不连通模版
						title="开普云网站监测 - 首页连不通";
						tpl_value=getEncodeStr("#siteCodeName#")+"="+getEncodeStr(siteCodeName)
								+"&"+getEncodeStr("#scanTime#")+"="+getEncodeStr(scanDate)
								+"&"+getEncodeStr("#queDescribe#")+"="+getEncodeStr(errorCode+queDescribe)
								+"&"+getEncodeStr("#period#")+"="+getEncodeStr(period);
						tpl_id=prop.getProperty("tplHomeNotConn");
						ftl="earlyMail5.ftl";
					}

					if(orgTbStatus == 0){
						//是否给填报单位发预警
						if(configEarly.getIsSiteReceive()==1){
							/***填报单位是否接受短信***********/
							if(configEarly.getIsTelReceive() ==1 ){
								if(isInHour(startHour, endHour)){
									if(isPrincipalReceive ==1){
										if(isMobile(principalPhone)){
											dxsfmap.put("principal", 1);
											//填报单位负责人发送短信
											String returnTP=YunpianSmsUtil.tplSendSms(tpl_id, tpl_value, principalPhone);
											logger.info("====填报单位负责人 发送短信====code:"+JSONObject.fromObject(returnTP).get("code"));
											  //向预警日志表中加入数据
											 //获得发送的短信的内容
											String sMsg = getSendShortMessage(tpl_id, tplValueMap);
											addEarlyLog(siteCode,null,type,2,principalPhone,sMsg,1);
										}else{
											logger.info("====填报单位负责人 发送短信==手机号码为空或者格式不正确");
										}
										
									}
									if(isLinkmanReceive ==1){
										if(isMobile(linkmanPhone) && ISend(lxdxmap, dxsfmap).get("linkman") == 1){
											//填报联系人发送短信
											String returnTL=YunpianSmsUtil.tplSendSms(tpl_id, tpl_value, linkmanPhone);
											dxsfmap.put("linkman", 1);
											logger.info("====填报单位联系人  发送短信====code:"+JSONObject.fromObject(returnTL).get("code"));
											  //向预警日志表中加入数据
											 //获得发送的短信的内容
											String sMsg = getSendShortMessage(tpl_id, tplValueMap);
											addEarlyLog(siteCode,null,type,2,linkmanPhone,sMsg,1);
										}else{
											logger.info("====填报单位联系人  发送短信==手机号码为空或者格式不正确");
										}
										
									}
									if(isLinkmanReceiveTwo ==1){
										if(isMobile(linkmanPhoneTwo) && ISend(lxdxmap, dxsfmap).get("linkmanTwo") == 1){
											//填报联系人发送短信
											String returnTL=YunpianSmsUtil.tplSendSms(tpl_id, tpl_value, linkmanPhoneTwo);
											dxsfmap.put("linkmanTwo", 1);
											logger.info("====填报单位联系人  发送短信====code:"+JSONObject.fromObject(returnTL).get("code"));
											
											  //向预警日志表中加入数据 获得发送的短信的内容
											String sMsg = getSendShortMessage(tpl_id, tplValueMap);
											addEarlyLog(siteCode,null,type,2,linkmanPhoneTwo,sMsg,1);
										}
									}
									if(isLinkmanReceiveThree ==1){
										if(isMobile(linkmanPhoneThree) && ISend(lxdxmap, dxsfmap).get("linkmanThree") == 1){
											//填报联系人发送短信
											String returnTL=YunpianSmsUtil.tplSendSms(tpl_id, tpl_value, linkmanPhoneThree);
											dxsfmap.put("linkmanThree", 1);
											logger.info("====填报单位联系人  发送短信====code:"+JSONObject.fromObject(returnTL).get("code"));
											  //向预警日志表中加入数据 获得发送的短信的内容
											String sMsg = getSendShortMessage(tpl_id, tplValueMap);
											addEarlyLog(siteCode,null,type,2,linkmanPhoneThree,sMsg,1);
										}
									}
								}
							}
							/*****是否接受邮件******/	
							if(configEarly.getIsEmailReceive()==1){
								
								if(isPrincipalReceive ==1){
									if(isEmail(principalEmail)){
										//负责人发送邮件
										SendEmail.sendEmail(title, ftl, tplValueMap, principalEmail);
										yjsfmap.put("principal", 1);
										//如果发送成功 向预警日志表中加入数据
										//获得邮件发送内容
										String eMsg = getSendEmailMessage(ftl, tplValueMap,true,false);
										addEarlyLog(siteCode,null,type,1,principalEmail,eMsg,1);
									}
								}
								if(isLinkmanReceive ==1){
									if(isEmail(linkmanEmail) && ISend(lxyxmap, yjsfmap).get("linkman") == 1){
										//联系人发送邮件
										SendEmail.sendEmail(title, ftl, tplValueMap, linkmanEmail);
										yjsfmap.put("linkman", 1);
										//如果发送成功 向预警日志表中加入数据
										//获得邮件发送内容
										String eMsg = getSendEmailMessage(ftl, tplValueMap,true,false);
										addEarlyLog(siteCode,null,type,1,linkmanEmail,eMsg,1);
									}
								}
								
								if(isLinkmanReceiveTwo ==1){
									if(isEmail(linkmanEmailTwo) && ISend(lxyxmap, yjsfmap).get("linkmanTwo") == 1){
										//联系人发送邮件
										SendEmail.sendEmail(title, ftl, tplValueMap, linkmanEmailTwo);
										yjsfmap.put("linkmanTwo", 1);
										//如果发送成功 向预警日志表中加入数据 获得邮件发送内容
										String eMsg = getSendEmailMessage(ftl, tplValueMap,true,false);
										addEarlyLog(siteCode,null,type,1,linkmanEmailTwo,eMsg,1);
									}
								}
								
								if(isLinkmanReceiveThree ==1){
									if(isEmail(linkmanEmailThree) && ISend(lxyxmap, yjsfmap).get("linkmanThree") == 1){
										//联系人发送邮件
										SendEmail.sendEmail(title, ftl, tplValueMap, linkmanEmailThree);
										yjsfmap.put("linkmanThree", 1);
										//如果发送成功 向预警日志表中加入数据 //获得邮件发送内容
										String eMsg = getSendEmailMessage(ftl, tplValueMap,true,false);
										addEarlyLog(siteCode,null,type,1,linkmanEmailThree,eMsg,1);
									}
								}
								
							}
							/***填报单位是否接受微信***********/
							if(configEarly.getIsWxReceive() ==1 ){
								Map<String,Object> pMap = new HashMap<String, Object>();
								if(errorCode.equals("200")){//恢复正常
									type=14;//首页访问正常
									pMap.put("queDescribe", "首页连接恢复正常");
								}else{//连不通
									pMap.put("queDescribe", queDescribe);
								}
								pMap.put("name", siteCodeName);
								pMap.put("code", siteCode);
								pMap.put("scanTime",scanDate);
								pMap.put("url", connHomeUrl+"?siteCode="+siteCode);
								pMap.put("websiteUrl", url);
								//封装模板消息
								// 微信发送  返回1 成功  2 失败；
								sendCommWX(pMap, String.valueOf(type),null,1,null);			
							}
						}
						//更新状态
						earlyDetail.setSendStatus(1);
						earlyDetailTempServiceImpl.update(earlyDetail);
					}
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * @Title: 发放预警邮件 封装 数据map 调用模板----填报单位
	 * @Description:
	 * @author liujc@ucap.com.cn	2016-8-3上午11:53:54
	 * @param title 邮件标题
	 * @param ftl 邮件模板名称
	 * @param earlyDetailList
	 * @param configEarly
	 * @param map
	 * @param oneSitelist
	 * @param orglist
	 */
	public void forDataTB(String title, String ftl,List<EarlyDetail> earlyDetailList,
			ConfigEarly configEarly){
		String tpl_id="";
		String tpl_value="";
		try {
			//邮件内容集合
			List<Map<Object, Object>> oneSitelist = new ArrayList<Map<Object, Object>>();
			
			//填报单位配置信息    负责人是否接受
			Integer isPrincipalReceive=configEarly.getIsPrincipalReceive();
			//填报单位配置信息  联系人是否接受
			Integer isLinkmanReceive=configEarly.getIsLinkmanReceive();
			
			Integer isLinkmanReceiveTwo=configEarly.getIsLinkmanTwo();
			Integer isLinkmanReceiveThree=configEarly.getIsLinkmanThree();
			

			String startHour=configEarly.getStartHour();//设置发送的  开始时间
			String endHour=configEarly.getEndHour();//设置发送的  结束时间
			
			if(earlyDetailList !=null && earlyDetailList.size()>0){
				//封装填报单位发送邮件内容
				Map<Object, Object> mailMap=new HashMap<Object, Object>();
				for(int i=0;i<earlyDetailList.size();i++){
					Map<Object, Object> map = new HashMap<Object, Object>();
					
					EarlyDetail earlyDetail=earlyDetailList.get(i);
					String siteCode=earlyDetail.getSiteCode();
					String siteCodeName=earlyDetail.getSiteCodeName();
					String principalEmail =earlyDetail.getPrincipalEmail();//10位 负责人邮箱地址
					String linkmanEmail =earlyDetail.getLinkmanEmail();//10位 联系人邮箱地址
					String linkmanEmailTwo =earlyDetail.getLinkmanEmailTwo();//10位 联系人邮箱地址
					String linkmanEmailThree =earlyDetail.getLinkmanEmailThree();//10位 联系人邮箱地址
					String principalPhone=earlyDetail.getPrincipalPhone();//10位 短信
					String linkmanPhone=earlyDetail.getLinkmanPhone();//10位 短信
					String linkmanPhoneTwo=earlyDetail.getLinkmanPhoneTwo();//10位 短信
					String linkmanPhoneThree=earlyDetail.getLinkmanPhoneThree();//10位 短信
					
					Map<String, String> lxyxmap = Maps.newHashMap();//联系人方式
					lxyxmap.put("principal", principalEmail);
					lxyxmap.put("linkman", linkmanEmail);
					lxyxmap.put("linkmanTwo", linkmanEmailTwo);
					lxyxmap.put("linkmanThree", linkmanEmailThree);
					Map<String, Integer> yjsfmap = Maps.newHashMap();//是否发送过 默认否
					yjsfmap.put("principal", 0);
					yjsfmap.put("linkman", 0);
					yjsfmap.put("linkmanTwo", 0);
					yjsfmap.put("linkmanThree", 0);
					
					Map<String, String> lxdxmap = Maps.newHashMap();//联系人方式
					lxdxmap.put("principal", principalPhone);
					lxdxmap.put("linkman", linkmanPhone);
					lxdxmap.put("linkmanTwo", linkmanPhoneTwo);
					lxdxmap.put("linkmanThree", linkmanPhoneThree);
					Map<String, Integer> dxsfmap = Maps.newHashMap();//是否发送过 默认否
					dxsfmap.put("principal", 0);
					dxsfmap.put("linkman", 0);
					dxsfmap.put("linkmanTwo", 0);
					dxsfmap.put("linkmanThree", 0);
					
					String url=earlyDetail.getUrl();
					String errorProportion=earlyDetail.getErrorProportion();
					String earlyType=EarlyType.getNameByCode(earlyDetail.getType());
					String scanDate=earlyDetail.getScanTime();
					String queDescribe=earlyDetail.getQueDescribe();
				//	Integer noUpdateDay=earlyDetail.getNoUpdateDay()==null?0:earlyDetail.getNoUpdateDay();//首页未更新天数
					Integer noUpdateChannelNum=earlyDetail.getNoUpdateChannelNum()==null?0:earlyDetail.getNoUpdateChannelNum();//未更新栏目个数
					Integer blankNum=earlyDetail.getBlankNum()==null?0:earlyDetail.getBlankNum();//空白栏目个数
					Integer type=earlyDetail.getType();//预警类型
					Integer tbSendStatus=earlyDetail.getTbSendStatus();//是否为填报单位配置发送的预警1-是；2-否
					String errorCode=earlyDetail.getErrorCode();//首页不连通错误码
					String jumpUrl=earlyDetail.getJumpUrl();
					String homeUrl=earlyDetail.getHomeUrl();
					Integer servicePeriodId = earlyDetail.getServicePeriodId();
					String weiXinUrl="";//点击详情
					
					//推荐词汇
					String expectTerms= earlyDetail.getExpectTerms()==null?"":earlyDetail.getExpectTerms();
					//疑似错误
					String wrongTerm= earlyDetail.getWrongTerm()==null?"":earlyDetail.getWrongTerm();
					
					String channelName="";
					if(type==13){// 互动回应差  需要有栏目名称
						SecurityResponseRequest responseRequest=new SecurityResponseRequest();
						responseRequest.setSiteCode(siteCode);
						responseRequest.setServicePeriodId(servicePeriodId);
						responseRequest.setChannelUrl(url);
						List<SecurityResponse>  responseList=securityResponseServiceImpl.queryList(responseRequest);
						if(responseList!=null && responseList.size()>0){
							channelName=responseList.get(0).getChannelName();
						}
						
					}
					map.put("expectTerms", expectTerms);
					map.put("wrongTerm", wrongTerm);
					
					mailMap.put("scanTime", earlyDetail.getScanTime());
					mailMap.put("newEarlNum", earlyDetailList.size());
					
					map.put("scanTime", earlyDetail.getScanTime());
					map.put("newEarlNum", earlyDetailList.size());
					map.put("siteCodeName", siteCodeName);
					map.put("siteCode", siteCode);
					
					//网站网址
					String websiteUrl="";
					if(StringUtils.isNotEmpty(jumpUrl)){
						websiteUrl=jumpUrl;
					}else{
						websiteUrl=homeUrl;
					}
					map.put("websiteUrl", websiteUrl);
					
					if(StringUtils.isNotEmpty(url)){
						map.put("url", url);//网站地址
					}else{
						map.put("url", "");//网站地址
					}
					if(type==11 || type==12 || type==8){
						if(StringUtils.isNotEmpty(jumpUrl)){
							map.put("url", jumpUrl);
						}else{
							map.put("url", homeUrl);
						}
					}else{
						if(StringUtils.isNotEmpty(url)){
							map.put("url", url);//网站地址
						}else{
							map.put("url", "");//网站地址
						}
					}

					
					if(StringUtils.isNotEmpty(errorProportion)){
						map.put("errorProportion", errorProportion);//首页不连通比例
					}else{
						map.put("errorProportion", "");//首页不连通比例
					}
					
					map.put("earlyType", earlyType);//问题类型
					
					//错误描述处理
					if(type==1){//首页不连通要特殊处理
						if(errorCode!=null){
							queDescribe=errorCode+" "+QuestionType.getNameByCode(errorCode);
						}else{
							queDescribe="";
						}
						map.put("queDescribe", queDescribe);//问题描述
					}else{
						if(StringUtils.isNotEmpty(queDescribe)){
							map.put("queDescribe", queDescribe);//问题描述
						}else{
							map.put("queDescribe", "");//问题描述
						}
					}
					
					map.put("scanDate", scanDate);//监测时间
					map.put("loginUrl", "https://jg.kaipuyun.cn/");//点击查看详情url
					
					//邮件中 提示信息
					if(earlyDetail.getType()==8){
						map.put("tips", "根据十五号文规定，首页不连通比例达到5%即单项否决");
						weiXinUrl=connHomeUrl+"?siteCode="+siteCode;
					}else if(earlyDetail.getType()==10){//首页不更新超过10天
						map.put("tips", "根据十五号文规定，首页超过两周未更新的即单项否决");
						weiXinUrl=securityHomeTbUrl+"?siteCode="+siteCode+"&siteName="+siteCodeName;
					}else if(earlyDetail.getType()==11){
						map.put("tips", "根据十五号文规定，空白栏目数量超过（含）5个即单项否决");
						weiXinUrl=securityBlankTbUrl+"?siteCode="+siteCode+"&siteName="+siteCodeName+"&servicePeroidId="+servicePeriodId;
					}else if(earlyDetail.getType()==12){
						map.put("tips", "根据十五号文规定，基本信息不更新的栏目数量超过10个即单项否决");
						weiXinUrl=securityChannelTbUrl+"?siteCode="+siteCode+"&siteName="+siteCodeName;
					}else if(earlyDetail.getType()==13){
						map.put("tips", "根据十五号文规定，互动回应栏目超过3个月未回应即单项否决");
						weiXinUrl=securityResponseTbUrl+"?siteCode="+siteCode+"&siteName="+siteCodeName+"&servicePeroidId="+servicePeriodId;
					}
					oneSitelist.add(map);
					
					tpl_id = prop.getProperty(configEarly.getTplId());
					//封装短信传送数据的Map
					Map<Object,Object>tplValueMap = new HashMap<Object,Object>();
					tplValueMap.put("siteCodeName", siteCodeName);
					if("tplHomeNotConnectedRatio".equals(configEarly.getTplId())){
						//首页不连通占比
						tpl_value=getEncodeStr("#siteCodeName#")+"="+getEncodeStr(siteCodeName)
								+"&"+getEncodeStr("#errorProportion#")+"="+getEncodeStr(errorProportion);
						
						tplValueMap.put("errorProportion", errorProportion);
						tplValueMap.put("url", websiteUrl);
					}else if("tplblankChannel".equals(configEarly.getTplId())){
						//空白栏目超过2个
						tpl_value=getEncodeStr("#siteCodeName#")+"="+getEncodeStr(siteCodeName)
								+"&"+getEncodeStr("#blankNum#")+"="+getEncodeStr(blankNum+"");
						
						tplValueMap.put("blankNum", blankNum);
					}else if("tplNoUpdateChannel".equals(configEarly.getTplId())){
						//栏目不更新
						tpl_value=getEncodeStr("#siteCodeName#")+"="+getEncodeStr(siteCodeName)
								+"&"+getEncodeStr("#noUpdateChannelNum#")+"="+getEncodeStr(noUpdateChannelNum+"");
						
						tplValueMap.put("noUpdateChannelNum", noUpdateChannelNum);
					}else if("tplInteractiveResponse".equals(configEarly.getTplId())){
						//互动回应
						tpl_value=getEncodeStr("#siteCodeName#")+"="+getEncodeStr(siteCodeName)
								+"&"+getEncodeStr("#channelName#")+"="+getEncodeStr(channelName)
								+"&"+getEncodeStr("#queDescribe#")+"="+getEncodeStr(queDescribe);
						
						tplValueMap.put("channelName", channelName);
						tplValueMap.put("queDescribe", queDescribe);
					}
					
					
					if(isInHour(startHour, endHour)){
						/**
						 * 首页不连通时时预警要特别处理  其他类型不用  且控制只发送最新一条 
						 */
						if((type==1 && tbSendStatus!=1 && i==0) || type!=1 ){
							//是否给填报单位发预警
							if(configEarly.getIsSiteReceive()==1){
								/*****是否接受邮件******/	
								if(configEarly.getIsEmailReceive()==1){
									//多个问题信息  一次性发送给 填报单位
									if(i==earlyDetailList.size()-1){
										mailMap.put("dataList", oneSitelist);//给填报单位发放的邮件
										if(isPrincipalReceive ==1){
											//负责人发送邮件
											if(isEmail(principalEmail)){
												SendEmail.sendEmail(title, ftl, mailMap, principalEmail);
												yjsfmap.put("principal", 1);
												if(type==1 && tbSendStatus!=1 && i==0){
													//更新发送状态
													earlyDetail.setSendEmailState(1);
													earlyDetail.setTbSendStatus(1);
												}
												//如果发送成功 向预警日志表中加入数据
												//获得邮件发送内容
												String eMsg = getSendEmailMessage(ftl, oneSitelist.get(0),true,false);
												addEarlyLog(siteCode,servicePeriodId,type,1,principalEmail,eMsg,1);
												
												earlyDetail.setTbSendStatus(1);
											}
										}
										
										if(isLinkmanReceive ==1){
											if(isEmail(linkmanEmail) && ISend(lxyxmap, yjsfmap).get("linkman") == 1){
												//联系人发送邮件
												SendEmail.sendEmail(title, ftl, mailMap, linkmanEmail);
												yjsfmap.put("linkman", 1);
												if(type==1 && tbSendStatus!=1 && i==0){
													//更新发送状态
													earlyDetail.setSendEmailState(1);
													earlyDetail.setTbSendStatus(1);
												}
												//如果发送成功 向预警日志表中加入数据
												//获得邮件发送内容
												String eMsg = getSendEmailMessage(ftl, oneSitelist.get(0),true,false);
												addEarlyLog(siteCode,servicePeriodId,type,1,linkmanEmail,eMsg,1);
												earlyDetail.setTbSendStatus(1);
											}
										}
										
										if(isLinkmanReceiveTwo ==1){
											if(isEmail(linkmanEmailTwo) && ISend(lxyxmap, yjsfmap).get("linkmanTwo") == 1){
												//联系人发送邮件
												SendEmail.sendEmail(title, ftl, mailMap, linkmanEmailTwo);
												yjsfmap.put("linkmanTwo", 1);
												if(type==1 && tbSendStatus!=1 && i==0){
													//更新发送状态
													earlyDetail.setSendEmailState(1);
													earlyDetail.setTbSendStatus(1);
												}
												//如果发送成功 向预警日志表中加入数据 获得邮件发送内容
												String eMsg = getSendEmailMessage(ftl, oneSitelist.get(0),true,false);
												addEarlyLog(siteCode,servicePeriodId,type,1,linkmanEmailTwo,eMsg,1);
												earlyDetail.setTbSendStatus(1);
											}
										}
										
										if(isLinkmanReceiveThree ==1){
											if(isEmail(linkmanEmailThree) && ISend(lxyxmap, yjsfmap).get("linkmanThree") == 1){
												//联系人发送邮件
												SendEmail.sendEmail(title, ftl, mailMap, linkmanEmailThree);
												yjsfmap.put("linkmanThree",1);
												if(type==1 && tbSendStatus!=1 && i==0){
													//更新发送状态
													earlyDetail.setSendEmailState(1);
													earlyDetail.setTbSendStatus(1);
												}
												//如果发送成功 向预警日志表中加入数据 //获得邮件发送内容
												String eMsg = getSendEmailMessage(ftl, oneSitelist.get(0),true,false);
												addEarlyLog(siteCode,servicePeriodId,type,1,linkmanEmailThree,eMsg,1);
												earlyDetail.setTbSendStatus(1);
											}
										}
									}//多个问题信息  一次性发送给 填报单位 end
								}//是否接受邮件end
							
								/***填报单位是否接受短信***********/
								if(configEarly.getIsTelReceive() ==1 ){
									if(isPrincipalReceive ==1){
										if(StringUtils.isNotEmpty(principalPhone) && isMobile(principalPhone)){
											//填报单位负责人发送短信
											String returnTP=YunpianSmsUtil.tplSendSms(tpl_id, tpl_value, principalPhone);
											dxsfmap.put("principal", 1);
											logger.info("====填报单位负责人 发送短信====code:"+JSONObject.fromObject(returnTP).get("code"));
											if(type==1 && tbSendStatus!=1 && i==0){
												//更新发送状态
												earlyDetail.setSendEmailState(1);
												earlyDetail.setTbSendStatus(1);
											}
											  //向预警日志表中加入数据
											 //获得发送的短信的内容
											String sMsg = getSendShortMessage(tpl_id, tplValueMap);
											addEarlyLog(siteCode,servicePeriodId,type,2,principalPhone,sMsg,1);
											
											earlyDetail.setTbSendStatus(1);
										}else{
											logger.info("====填报单位负责人 发送短信==手机号码为空或者格式不正确");
										}
										
									}
									if(isLinkmanReceive ==1){
										if(isMobile(linkmanPhone) && ISend(lxdxmap, dxsfmap).get("linkman") == 1){
											//填报联系人发送短信
											String returnTL=YunpianSmsUtil.tplSendSms(tpl_id, tpl_value, linkmanPhone);
											dxsfmap.put("linkman", 1);
											logger.info("====填报单位联系人  发送短信====code:"+JSONObject.fromObject(returnTL).get("code"));
											if(type==1 && tbSendStatus!=1 && i==0){
												//更新发送状态
												earlyDetail.setSendEmailState(1);
												earlyDetail.setTbSendStatus(1);
											}
											  //向预警日志表中加入数据
											 //获得发送的短信的内容
											String sMsg = getSendShortMessage(tpl_id, tplValueMap);
											addEarlyLog(siteCode,servicePeriodId,type,2,linkmanPhone,sMsg,1);
											
											earlyDetail.setTbSendStatus(1);
										}else{
											logger.info("====填报单位联系人  发送短信==手机号码为空或者格式不正确");
										}
										
									}
									if(isLinkmanReceiveTwo ==1){
										if(isMobile(linkmanPhoneTwo) && ISend(lxdxmap, dxsfmap).get("linkmanTwo") == 1){
											//填报联系人发送短信
											String returnTL=YunpianSmsUtil.tplSendSms(tpl_id, tpl_value, linkmanPhoneTwo);
											dxsfmap.put("linkmanTwo", 1);
											logger.info("====填报单位联系人  发送短信====code:"+JSONObject.fromObject(returnTL).get("code"));
											if(type==1 && tbSendStatus!=1 && i==0){
												//更新发送状态
												earlyDetail.setSendEmailState(1);
												earlyDetail.setTbSendStatus(1);
											}
											  //向预警日志表中加入数据 获得发送的短信的内容
											String sMsg = getSendShortMessage(tpl_id, tplValueMap);
											addEarlyLog(siteCode,servicePeriodId,type,2,linkmanPhoneTwo,sMsg,1);
											
											earlyDetail.setTbSendStatus(1);
										}
									}
									if(isLinkmanReceiveThree ==1){
										if(isMobile(linkmanPhoneThree) && ISend(lxdxmap, dxsfmap).get("linkmanThree") == 1){
											//填报联系人发送短信
											String returnTL=YunpianSmsUtil.tplSendSms(tpl_id, tpl_value, linkmanPhoneThree);
											dxsfmap.put("linkmanThree", 1);
											logger.info("====填报单位联系人  发送短信====code:"+JSONObject.fromObject(returnTL).get("code"));
											if(type==1 && tbSendStatus!=1 && i==0){
												//更新发送状态
												earlyDetail.setSendEmailState(1);
												earlyDetail.setTbSendStatus(1);
											}
											  //向预警日志表中加入数据 获得发送的短信的内容
											String sMsg = getSendShortMessage(tpl_id, tplValueMap);
											addEarlyLog(siteCode,servicePeriodId,type,2,linkmanPhoneThree,sMsg,1);
											
											earlyDetail.setTbSendStatus(1);
										}
									}
								}
								/***填报单位是否接受微信***********/
								if(configEarly.getIsWxReceive() ==1 ){
									Map<String,Object> pMap = new HashMap<String, Object>();
									pMap.put("queDescribe", queDescribe);
									pMap.put("name", siteCodeName);
									pMap.put("code", siteCode);
									pMap.put("scanTime",mailMap.get("scanTime"));
									pMap.put("url", weiXinUrl);
									pMap.put("websiteUrl", websiteUrl);
									//封装模板消息
									// 微信发送  返回1 成功  2 失败；
									sendCommWX(pMap, String.valueOf(type),null,1,servicePeriodId);	
									earlyDetail.setTbSendStatus(1);
								}
							}
						}
						earlyDetail.setSendEmailTime(DateUtils.getNow());
						earlyDetailServiceImpl.update(earlyDetail);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Title: 发放预警邮件 封装 数据map 调用模板----组织单位
	 * @Description:
	 * @author liujc@ucap.com.cn	2016-8-3上午11:53:54
	 * @param title 邮件标题
	 * @param ftl 邮件模板名称
	 * @param earlyDetailList
	 * @param configEarly
	 * @param map
	 * @param oneSitelist
	 * @param orglist
	 */
	@SuppressWarnings("unused")
	public void forData(String title, String ftl,List<EarlyDetail> earlyDetailList,
			ConfigEarly configEarly,List<Map<Object, Object>> oneSitelist,
			List<Map<Object, Object>> orglist){
		String tpl_id="";
		String tpl_value="";
		try {
			
			//组织单位的配置信息      组织负责人是否接受
			Integer isOrgPrincipalReceive=configEarly.getIsOrgPrincipal();
			//组织单位的配置信息   组织联系人是否接受
			Integer isOrgLinkmanReceive=configEarly.getIsOrgLinkman();
			Integer orgLinkmanTwo=configEarly.getOrgLinkmanTwo();
			Integer orgLinkmanThree=configEarly.getOrgLinkmanThree();
			
			//组织单位的配置信息      下属负责人是否接受
			Integer isPrincipalReceive=configEarly.getIsPrincipalReceive();
			//组织单位的配置信息   下属联系人是否接受
			Integer isLinkmanReceive=configEarly.getIsLinkmanReceive();
			Integer isLinkmanTwo=configEarly.getIsLinkmanTwo();
			Integer isLinkmanThree=configEarly.getIsLinkmanThree();
			
			String orgPrincipalPhone=configEarly.getPrincipalPhone();//组织单位负责人电话 短信
			String orgLinkmanPhone=configEarly.getLinkmanPhone();//组织单位联系人电话  短信
			String orgLinkmanPhoneTwo=configEarly.getLinkmanPhoneTwo();//组织单位联系人电话  短信
			String orgLinkmanPhoneThree=configEarly.getLinkmanPhoneThree();//组织单位联系人电话  短信
			
			String startHour=configEarly.getStartHour();//设置发送的  开始时间
			String endHour=configEarly.getEndHour();//设置发送的  结束时间
			
			String orgSiteCode = configEarly.getSiteCode(); //组织单位的siteCode
			
			if(earlyDetailList !=null && earlyDetailList.size()>0){
				//封装组织单位发送邮件
				Map<Object, Object> mailMap=new HashMap<Object, Object>();
				for(int i=0;i<earlyDetailList.size();i++){
					Map<Object, Object> map = new HashMap<Object, Object>();
					
					EarlyDetail earlyDetail=earlyDetailList.get(i);
					String siteCode=earlyDetail.getSiteCode();
					String siteCodeName=earlyDetail.getSiteCodeName();
					String principalEmail =earlyDetail.getPrincipalEmail();//10位 负责人邮箱地址 
					String linkmanEmail =earlyDetail.getLinkmanEmail();//10位 联系人邮箱地址
					String linkmanEmailTwo =earlyDetail.getLinkmanEmailTwo();//10位 联系人邮箱地址
					String linkmanEmailThree =earlyDetail.getLinkmanEmailThree();//10位 联系人邮箱地址
					String principalPhone=earlyDetail.getPrincipalPhone();//10位 短信
					String linkmanPhone=earlyDetail.getLinkmanPhone();//10位 短信
					String linkmanPhoneTwo=earlyDetail.getLinkmanPhoneTwo();//10位 短信
					String linkmanPhoneThree=earlyDetail.getLinkmanPhoneThree();//10位 短信
					
					//填报单位
					Map<String, String> lxyxmap = Maps.newHashMap();//联系人方式
					lxyxmap.put("principal", principalEmail);
					lxyxmap.put("linkman", linkmanEmail);
					lxyxmap.put("linkmanTwo", linkmanEmailTwo);
					lxyxmap.put("linkmanThree", linkmanEmailThree);
					Map<String, Integer> yjsfmap = Maps.newHashMap();//是否发送过 默认否
					yjsfmap.put("principal", 0);
					yjsfmap.put("linkman", 0);
					yjsfmap.put("linkmanTwo", 0);
					yjsfmap.put("linkmanThree", 0);
					
					Map<String, String> lxdxmap = Maps.newHashMap();//联系人方式
					lxdxmap.put("principal", principalPhone);
					lxdxmap.put("linkman", linkmanPhone);
					lxdxmap.put("linkmanTwo", linkmanPhoneTwo);
					lxdxmap.put("linkmanThree", linkmanPhoneThree);
					Map<String, Integer> dxsfmap = Maps.newHashMap();//是否发送过 默认否
					dxsfmap.put("principal", 0);
					dxsfmap.put("linkman", 0);
					dxsfmap.put("linkmanTwo", 0);
					dxsfmap.put("linkmanThree", 0);
					
					//组织单位-短信
					Map<String, String> zlxdxmap = Maps.newHashMap();//联系人方式
					zlxdxmap.put("principal", orgPrincipalPhone);
					zlxdxmap.put("linkman", orgLinkmanPhone);
					zlxdxmap.put("linkmanTwo", orgLinkmanPhoneTwo);
					zlxdxmap.put("linkmanThree", orgLinkmanPhoneThree);
					Map<String, Integer> zdxsfmap = Maps.newHashMap();//是否发送过 默认否
					zdxsfmap.put("principal", 0);
					zdxsfmap.put("linkman", 0);
					zdxsfmap.put("linkmanTwo", 0);
					zdxsfmap.put("linkmanThree", 0);
					
					String url=earlyDetail.getUrl();
					String weiXinUrl="";//点击详情跳转url
					String errorProportion=earlyDetail.getErrorProportion();
					String earlyType=EarlyType.getNameByCode(earlyDetail.getType());
					String scanDate=earlyDetail.getScanTime();
					String queDescribe=earlyDetail.getQueDescribe();
					Integer noUpdateDay=earlyDetail.getNoUpdateDay()==null?0:earlyDetail.getNoUpdateDay();//首页未更新天数
					Integer noUpdateChannelNum=earlyDetail.getNoUpdateChannelNum()==null?0:earlyDetail.getNoUpdateChannelNum();//未更新栏目个数
					Integer blankNum=earlyDetail.getBlankNum()==null?0:earlyDetail.getBlankNum();//空白栏目个数
					Integer servicePeriodId = earlyDetail.getServicePeriodId(); //服务周期编号

					//预警类型
					Integer type=earlyDetail.getType();
					//是否为组织单位发送预警 1-是；2否
					Integer orgSendStatus=earlyDetail.getOrgSendStatus();
					//是否为组织单位配置的填报单位发送预警 1-是；2否
					Integer orgTbSendStatus=earlyDetail.getOrgTbSendStatus();
					
					String channelName="";
					if(type==13){// 互动回应差  需要有栏目名称
						SecurityResponseRequest responseRequest=new SecurityResponseRequest();
						responseRequest.setSiteCode(siteCode);
						responseRequest.setServicePeriodId(servicePeriodId);
						responseRequest.setChannelUrl(url);
						List<SecurityResponse>  responseList=securityResponseServiceImpl.queryList(responseRequest);
						if(responseList!=null && responseList.size()>0){
							channelName=responseList.get(0).getChannelName();
						}
						
					}
					
					String jumpUrl=earlyDetail.getJumpUrl();//跳转url
					String homeUrl=earlyDetail.getHomeUrl();//首页url
					//互动回应差  需要一个网站urL
					String websiteUrl="";
					if(StringUtils.isNotEmpty(jumpUrl)){
						websiteUrl=jumpUrl;
					}else{
						websiteUrl=homeUrl;
					}
					map.put("websiteUrl", websiteUrl);
					if(StringUtils.isNotEmpty(url)){
						map.put("url", url);//网站地址
					}else{
						map.put("url", "");//网站地址
					}
					if(type==11 || type==12 || type==8){
						if(StringUtils.isNotEmpty(jumpUrl)){
							map.put("url", jumpUrl);
						}else{
							map.put("url", homeUrl);
						}
					}else{
						if(StringUtils.isNotEmpty(url)){
							map.put("url", url);//网站地址
						}else{
							map.put("url", "");//网站地址
						}
					}
					
					//首页最后更新时间
					String lastUpdateDate=earlyDetail.getLastUpdateDate()==null?"":earlyDetail.getLastUpdateDate();
					map.put("lastUpdateDate", lastUpdateDate);
					
					//推荐词汇
					String expectTerms= earlyDetail.getExpectTerms()==null?"":earlyDetail.getExpectTerms();
					//疑似错误
					String wrongTerm= earlyDetail.getWrongTerm()==null?"":earlyDetail.getWrongTerm();
					
					//首页不连通编码
					String errorCode=earlyDetail.getErrorCode();
					
					map.put("expectTerms", expectTerms);
					map.put("wrongTerm", wrongTerm);
					
					mailMap.put("scanTime", earlyDetail.getScanTime());
					mailMap.put("newEarlNum", earlyDetailList.size());
					
					map.put("scanTime", earlyDetail.getScanTime());
					map.put("newEarlNum", earlyDetailList.size());
					map.put("siteCodeName", siteCodeName);
					map.put("siteCode", siteCode);
					
					if(StringUtils.isNotEmpty(errorProportion)){
						map.put("errorProportion", errorProportion);//首页不连通比例
					}else{
						map.put("errorProportion", "");//首页不连通比例
					}
					
					map.put("earlyType", earlyType);//问题类型
					

					if(StringUtils.isNotEmpty(queDescribe)){
						map.put("queDescribe", queDescribe);//问题描述
					}else{
						map.put("queDescribe", "");//问题描述
					}
					
					map.put("scanDate", scanDate);//监测时间
					map.put("loginUrl", "https://jg.kaipuyun.cn/");//点击查看详情url
					
					//邮件中 提示信息
					if(earlyDetail.getType()==8){
						map.put("tips", "根据十五号文规定，首页不连通比例达到5%即单项否决");
						weiXinUrl=connHomeUrl+"?siteCode="+siteCode;
					}else if(earlyDetail.getType()==10){//首页不更新超过10天
						map.put("tips", "根据十五号文规定，首页超过两周未更新的即单项否决");
						weiXinUrl=securityHomeTbUrl+"?siteCode="+siteCode+"&siteName="+siteCodeName;
					}else if(earlyDetail.getType()==11){
						map.put("tips", "根据十五号文规定，空白栏目数量超过（含）5个即单项否决");
						weiXinUrl=securityBlankTbUrl+"?siteCode="+siteCode+"&siteName="+siteCodeName+"&servicePeroidId="+servicePeriodId;
					}else if(earlyDetail.getType()==12){
						map.put("tips", "根据十五号文规定，基本信息不更新的栏目数量超过10个即单项否决");
						weiXinUrl=securityChannelTbUrl+"?siteCode="+siteCode+"&siteName="+siteCodeName;
					}else if(earlyDetail.getType()==13){
						map.put("tips", "根据十五号文规定，互动回应栏目超过3个月未回应即单项否决");
						weiXinUrl=securityResponseTbUrl+"?siteCode="+siteCode+"&siteName="+siteCodeName+"&servicePeroidId="+servicePeriodId;
					}
					
					oneSitelist.add(map);

					//判断组织单位接受什么类型的预警信息   1接收所有 2所有不接收 3只接收本级门户预警
					if(configEarly.getIsSiteReceive() !=null){
						if(configEarly.getIsSiteReceive()==1 ||
								(configEarly.getIsSiteReceive()==3 && configEarly.getDataBaseLinkType()==1)){
							//只接收本级门户
							orglist.add(map);
						}
					}
					tpl_id = prop.getProperty(configEarly.getTplId());
					Map<Object,Object>tplValueMap=  new HashMap<Object,Object>();
					tplValueMap.put("siteCodeName", siteCodeName);
					if("tplHomeNotConnectedRatio".equals(configEarly.getTplId())){
						//首页不连通占比
						tpl_value=getEncodeStr("#siteCodeName#")+"="+getEncodeStr(siteCodeName)
								+"&"+getEncodeStr("#errorProportion#")+"="+getEncodeStr(errorProportion);
						
						tplValueMap.put("errorProportion", errorProportion);
						tplValueMap.put("url", websiteUrl);
					}else if("tplblankChannel".equals(configEarly.getTplId())){
						//空白栏目超过2个
						tpl_value=getEncodeStr("#siteCodeName#")+"="+getEncodeStr(siteCodeName)
								+"&"+getEncodeStr("#blankNum#")+"="+getEncodeStr(blankNum+"");
						
						tplValueMap.put("blankNum", blankNum);
					}else if("tplNoUpdateChannel".equals(configEarly.getTplId())){
						//栏目不更新
						tpl_value=getEncodeStr("#siteCodeName#")+"="+getEncodeStr(siteCodeName)
								+"&"+getEncodeStr("#noUpdateChannelNum#")+"="+getEncodeStr(noUpdateChannelNum+"");
						
						tplValueMap.put("noUpdateChannelNum", noUpdateChannelNum);
					}else if("tplInteractiveResponse".equals(configEarly.getTplId())){
						//互动回应
						tpl_value=getEncodeStr("#siteCodeName#")+"="+getEncodeStr(siteCodeName)
								+"&"+getEncodeStr("#channelName#")+"="+getEncodeStr(channelName)
								+"&"+getEncodeStr("#queDescribe#")+"="+getEncodeStr(queDescribe);
						
						tplValueMap.put("channelName", channelName);
						tplValueMap.put("queDescribe", queDescribe);
					}
					
					//时间段判断
					if((type==8 || type==11 || type==12 ||type==13) && !isInHour(startHour, endHour)){
						continue;
					}else{
						//是否给填报单位发预警
						if(configEarly.getIsNextAllSite()==1){
							/*****是否接受邮件******/	
							if(configEarly.getIsEmailReceive()==1){
								//多个问题信息  一次性发送给 填报单位
								if(i==earlyDetailList.size()-1){
									mailMap.put("dataList", oneSitelist);//给填报单位发放的邮件
									if(isPrincipalReceive ==1){
										if(isEmail(principalEmail)){
											//负责人发送邮件
											SendEmail.sendEmail(title, ftl, mailMap, principalEmail);
											yjsfmap.put("principal", 1);
											if(type==1 && orgSendStatus!=1 && i==0){
												earlyDetail.setOrgTbSendStatus(1);
											}
											//获得邮件发送的内容
											String eMsg = getSendEmailMessage(ftl, oneSitelist.get(0),true,false);
											addEarlyLog(siteCode,servicePeriodId,type,1,principalEmail,eMsg,1);
											
											earlyDetail.setOrgSendStatus(1);//设置为已经发送
										}
										
									}
									//下属联系人是否接收
									if(isLinkmanReceive ==1){
										if(isEmail(linkmanEmail) && ISend(lxyxmap, yjsfmap).get("linkman") == 1 ){
											//联系人发送邮件
											SendEmail.sendEmail(title, ftl, mailMap, linkmanEmail);
											yjsfmap.put("linkman", 1);
											if(type==1 && orgSendStatus!=1 && i==0){
												earlyDetail.setOrgTbSendStatus(1);
											}
											//获得邮件发送的内容
											String eMsg = getSendEmailMessage(ftl, oneSitelist.get(0),true,false);
											addEarlyLog(siteCode,servicePeriodId,type,1,linkmanEmail,eMsg,1);
											
											earlyDetail.setOrgSendStatus(1);//设置为已经发送
										}
									
										if(isEmail(linkmanEmailTwo) && ISend(lxyxmap, yjsfmap).get("linkmanTwo") == 1 ){
											SendEmail.sendEmail(title, ftl, mailMap, linkmanEmailTwo);
											yjsfmap.put("linkmanTwo", 1);
											if(type==1 && orgSendStatus!=1 && i==0){
												earlyDetail.setOrgTbSendStatus(1);
											}
											//获得邮件发送的内容
											String twoMsg = getSendEmailMessage(ftl, oneSitelist.get(0),true,false);
											addEarlyLog(siteCode,servicePeriodId,type,1,linkmanEmailTwo,twoMsg,1);
											
											earlyDetail.setOrgSendStatus(1);//设置为已经发送
										}
									
										if(isEmail(linkmanEmailThree) && ISend(lxyxmap, yjsfmap).get("linkmanThree") == 1 ){
											SendEmail.sendEmail(title, ftl, mailMap, linkmanEmailThree);
											yjsfmap.put("linkmanThree", 1);
											if(type==1 && orgSendStatus!=1 && i==0){
												earlyDetail.setOrgTbSendStatus(1);
											}
											//获得邮件发送的内容
											String threeMsg = getSendEmailMessage(ftl, oneSitelist.get(0),true,false);
											addEarlyLog(siteCode,servicePeriodId,type,1,linkmanEmailThree,threeMsg,1);
											
											earlyDetail.setOrgSendStatus(1);//设置为已经发送
										}
									}
									
								}//多个问题信息  一次性发送给 填报单位 end
							}//是否接受邮件end
						
							/***填报单位是否接受短信***********/
							if(configEarly.getIsTelReceive() ==1 ){
								if(isPrincipalReceive ==1){
									if(isMobile(principalPhone)){
										//填报单位负责人发送短信
										String returnOTP=YunpianSmsUtil.tplSendSms(tpl_id, tpl_value, principalPhone);
										dxsfmap.put("principal", 1);
										logger.info("======组织单位    填报单位  负责人 发送短信=====code:"
												+JSONObject.fromObject(returnOTP).get("code"));
										if(type==1 && orgSendStatus!=1 && i==0){
											earlyDetail.setOrgTbSendStatus(1);
										}
										   //向预警日志表中加入数据
										   //获得短信发送的内容
										String sMsg = getSendShortMessage(tpl_id, tplValueMap);
										addEarlyLog(siteCode,servicePeriodId,type,2,principalPhone,sMsg,1);
										
										earlyDetail.setOrgSendStatus(1);//设置为已经发送
									}else{
										logger.info("====组织单位    填报单位  负责人 发送短信==手机号码为空或者格式不正确");
									}
									
								}
								//下属联系人是否接收
								if(isLinkmanReceive ==1){
									if(isMobile(linkmanPhone) && ISend(lxdxmap, dxsfmap).get("linkman") == 1){
										//填报联系人发送短信
										String returnOTL=YunpianSmsUtil.tplSendSms(tpl_id, tpl_value, linkmanPhone);
										dxsfmap.put("linkman", 1);
										logger.info("======组织单位    填报单位  联系人 发送短信=====code:"
												+JSONObject.fromObject(returnOTL).get("code"));
										if(type==1 && orgSendStatus!=1 && i==0){
											earlyDetail.setOrgTbSendStatus(1);
										}
										//向预警日志表中加入数据
										  //获得短信发送的内容
										String sMsg = getSendShortMessage(tpl_id, tplValueMap);
										addEarlyLog(siteCode,servicePeriodId,type,2,linkmanPhone,sMsg,1);
										
										earlyDetail.setOrgSendStatus(1);//设置为已经发送
									}else{
										logger.info("====组织单位    填报单位  联系人 发送短信==手机号码为空或者格式不正确");
									}
									
									if(isMobile(linkmanPhoneTwo) && ISend(lxdxmap, dxsfmap).get("linkmanTwo") == 1){
										//填报联系人发送短信
										String returnOTL=YunpianSmsUtil.tplSendSms(tpl_id, tpl_value, linkmanPhoneTwo);
										dxsfmap.put("linkmanTwo", 1);
										if(type==1 && orgSendStatus!=1 && i==0){
											earlyDetail.setOrgTbSendStatus(1);
										}
										//向预警日志表中加入数据
										  //获得短信发送的内容
										String sMsg = getSendShortMessage(tpl_id, tplValueMap);
										addEarlyLog(siteCode,servicePeriodId,type,2,linkmanPhoneTwo,sMsg,1);
										
										earlyDetail.setOrgSendStatus(1);//设置为已经发送
									}
								
									if(isMobile(linkmanPhoneThree) && ISend(lxdxmap, dxsfmap).get("linkmanThree") == 1){
										//填报联系人发送短信
										String returnOTL=YunpianSmsUtil.tplSendSms(tpl_id, tpl_value, linkmanPhoneThree);
										dxsfmap.put("linkmanThree", 1);
										if(type==1 && orgSendStatus!=1 && i==0){
											earlyDetail.setOrgTbSendStatus(1);
										}
										//向预警日志表中加入数据
										  //获得短信发送的内容
										String sMsg = getSendShortMessage(tpl_id, tplValueMap);
										addEarlyLog(siteCode,servicePeriodId,type,2,linkmanPhoneThree,sMsg,1);
										
										earlyDetail.setOrgSendStatus(1);//设置为已经发送
									}
								}
							}
							if(configEarly.getIsEmailReceive()==1){
								earlyDetail.setOrgSendStatus(1);//设置为已经发送
							}
							/***填报单位是否接受微信***********/
							if(configEarly.getIsWxReceive() ==1 ){
								Map<String,Object> pMap = new HashMap<String, Object>();
								pMap.put("queDescribe", queDescribe);
								pMap.put("name", siteCodeName);
								pMap.put("code", siteCode);
								pMap.put("scanTime",mailMap.get("scanTime"));
								pMap.put("url", weiXinUrl);
								pMap.put("websiteUrl", websiteUrl);
								//封装模板消息
								// 微信发送  返回1 成功  2 失败；
								sendCommWX(pMap, String.valueOf(type),null,1,servicePeriodId);
								
								earlyDetail.setOrgSendStatus(1);//设置为已经发送
							}
						}
						
						if(configEarly.getIsSiteReceive() !=null){
							//本级门户  或者 所有时给组织单位发送短信
							if(configEarly.getIsSiteReceive()==1 ||
								(configEarly.getIsSiteReceive()==3 
									&& configEarly.getDataBaseLinkType()==1)){
								/***组织单位是否接受短信***********/
								if(configEarly.getIsTelReceive()==1){
									if(isOrgPrincipalReceive == 1){
										if(StringUtils.isNotEmpty(orgPrincipalPhone) && isMobile(orgPrincipalPhone)){
											//组织单位 负责人发送短信
											String returnOP=YunpianSmsUtil.tplSendSms(tpl_id, tpl_value, orgPrincipalPhone);
											zdxsfmap.put("principal", 1);
											logger.info("======组织单位  负责人  发送短信=====code:"
													+JSONObject.fromObject(returnOP).get("code"));
											if(type==1 && orgSendStatus!=1 && i==0){
												earlyDetail.setOrgSendStatus(1);
											}
											//向预警日志表中加入数据
											  //获得短信发送的内容
											String sMsg = getSendShortMessage(tpl_id, tplValueMap);
											addEarlyLog(orgSiteCode,null,type,2,orgPrincipalPhone,sMsg,2);
											
											earlyDetail.setOrgSendStatus(1);//设置为已经发送
										}else{
											logger.info("====组织单位  负责人  发送短信==手机号码为空或者格式不正确");
										}
									}
									if(isOrgLinkmanReceive == 1 ){
										if(isMobile(orgLinkmanPhone) && ISend(zlxdxmap, zdxsfmap).get("linkman") == 1){
											//组织单位联系人发送短信
											String returnOL=YunpianSmsUtil.tplSendSms(tpl_id, tpl_value, orgLinkmanPhone);
											zdxsfmap.put("linkman", 1);
											logger.info("======组织单位    联系人 发送短信=====code:"
													+JSONObject.fromObject(returnOL).get("code"));
											if(type==1 && orgSendStatus!=1 && i==0){
												earlyDetail.setOrgSendStatus(1);
											}
											//向预警日志表中加入数据
											  //获得短信发送的内容
											String sMsg = getSendShortMessage(tpl_id, tplValueMap);
											addEarlyLog(orgSiteCode,null,type,2,orgLinkmanPhone,sMsg,2);
											
											earlyDetail.setOrgSendStatus(1);//设置为已经发送
											
										}else{
											logger.info("====组织单位  负责人  发送短信==手机号码为空或者格式不正确");
										}
										
									}
									
									if(orgLinkmanTwo != null && orgLinkmanTwo == 1 ){
										if(isMobile(orgLinkmanPhoneTwo) && ISend(zlxdxmap, zdxsfmap).get("linkmanTwo") == 1){
											//组织单位联系人发送短信
											String returnOL=YunpianSmsUtil.tplSendSms(tpl_id, tpl_value, orgLinkmanPhoneTwo);
											zdxsfmap.put("linkmanTwo", 1);
											if(type==1 && orgSendStatus!=1 && i==0){
												earlyDetail.setOrgSendStatus(1);
											}
											//向预警日志表中加入数据
											  //获得短信发送的内容
											String sMsg = getSendShortMessage(tpl_id, tplValueMap);
											addEarlyLog(orgSiteCode,null,type,2,orgLinkmanPhoneTwo,sMsg,2);
											
											earlyDetail.setOrgSendStatus(1);//设置为已经发送
										}
										
									}
									
									if(orgLinkmanThree == 1 ){
										if(isMobile(orgLinkmanPhoneThree) && ISend(zlxdxmap, zdxsfmap).get("linkmanThree") == 1){
											//组织单位联系人发送短信
											String returnOL=YunpianSmsUtil.tplSendSms(tpl_id, tpl_value, orgLinkmanPhoneThree);
											zdxsfmap.put("linkmanThree", 1);
											if(type==1 && orgSendStatus!=1 && i==0){
												earlyDetail.setOrgSendStatus(1);
											}
											//向预警日志表中加入数据
											  //获得短信发送的内容
											String sMsg = getSendShortMessage(tpl_id, tplValueMap);
											addEarlyLog(orgSiteCode,null,type,2,orgLinkmanPhoneThree,sMsg,2);
											
											earlyDetail.setOrgSendStatus(1);//设置为已经发送
										}
										
									}
									
								}
								if(configEarly.getIsEmailReceive()==1){
									earlyDetail.setOrgSendStatus(1);//设置为已经发送
								}
								/***组织单位是否接受微信***********/
								if(configEarly.getIsWxReceive() ==1 ){
									Map<String,Object> pMap = new HashMap<String, Object>();
									pMap.put("queDescribe", queDescribe);
									pMap.put("name", siteCodeName);
									pMap.put("code", siteCode);
									pMap.put("scanTime",mailMap.get("scanTime"));
									pMap.put("url", weiXinUrl);
									pMap.put("websiteUrl", websiteUrl);
									//封装模板消息
									sendCommWX(pMap, String.valueOf(type),configEarly.getSiteCode(),2,null);	
									earlyDetail.setOrgSendStatus(1);//设置为已经发送
								}
							}
						}
						//更新预警信息
						earlyDetail.setSendEmailTime(DateUtils.getNow());
						earlyDetailServiceImpl.update(earlyDetail);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * @Description:组织实时预警    TODO 
	 * @author: renpb --- 2017年4月13日上午9:36:43
	 * @return
	 */
	@SuppressWarnings("unused")
	public void forDataConn(String title, String ftl,List<EarlyDetailTemp> earlyDetailList,
			ConfigEarly configEarly,List<Map<Object, Object>> oneSitelist,
			List<Map<Object, Object>> orglist){
		String tpl_id="";
		String tpl_value="";
		try {
			
			//组织单位的配置信息      组织负责人是否接受
			Integer isOrgPrincipalReceive=configEarly.getIsOrgPrincipal();
			//组织单位的配置信息   组织联系人是否接受
			Integer isOrgLinkmanReceive=configEarly.getIsOrgLinkman();
			Integer orgLinkmanTwo=configEarly.getOrgLinkmanTwo();
			Integer orgLinkmanThree=configEarly.getOrgLinkmanThree();
			
			//组织单位的配置信息      下属负责人是否接受
			Integer isPrincipalReceive=configEarly.getIsPrincipalReceive();
			//组织单位的配置信息   下属联系人是否接受
			Integer isLinkmanReceive=configEarly.getIsLinkmanReceive();
			Integer isLinkmanTwo=configEarly.getIsLinkmanTwo();
			Integer isLinkmanThree=configEarly.getIsLinkmanThree();
			
			String orgPrincipalPhone=configEarly.getPrincipalPhone();//组织单位负责人电话 短信 
			String orgLinkmanPhone=configEarly.getLinkmanPhone();//组织单位联系人电话  短信
			String orgLinkmanPhoneTwo=configEarly.getLinkmanPhoneTwo();//组织单位联系人电话  短信
			String orgLinkmanPhoneThree=configEarly.getLinkmanPhoneThree();//组织单位联系人电话  短信
			String orgPrincipalEmail=configEarly.getPrincipalEmail();
			String orgLinkmanEmail=configEarly.getLinkmanEmail();
			String orgLinkmanEmailTwo=configEarly.getLinkmanEmailTwo();
			String orgLinkmanEmailThree=configEarly.getLinkmanEmailThree();
			String orgSiteCode = configEarly.getSiteCode(); //组织单位的siteCode
			
			String startHour=configEarly.getStartHour();//设置发送的  开始时间
			String endHour=configEarly.getEndHour();//设置发送的  结束时间
			
			if(earlyDetailList !=null && earlyDetailList.size()>0){
				for(int i=0;i<earlyDetailList.size();i++){
					//组织单位排重
					Map<String, String> lxyxmap = Maps.newHashMap();//联系人方式
					lxyxmap.put("principal", orgPrincipalEmail);
					lxyxmap.put("linkman", orgLinkmanEmail);
					lxyxmap.put("linkmanTwo", orgLinkmanEmailTwo);
					lxyxmap.put("linkmanThree", orgLinkmanEmailThree);
					Map<String, Integer> yjsfmap = Maps.newHashMap();//是否发送过 默认否
					yjsfmap.put("principal", 0);
					yjsfmap.put("linkman", 0);
					yjsfmap.put("linkmanTwo", 0);
					yjsfmap.put("linkmanThree", 0);
					
					Map<String, String> lxdxmap = Maps.newHashMap();//联系人方式
					lxdxmap.put("principal", orgPrincipalPhone);
					lxdxmap.put("linkman", orgLinkmanPhone);
					lxdxmap.put("linkmanTwo", orgLinkmanPhoneTwo);
					lxdxmap.put("linkmanThree", orgLinkmanPhoneThree);
					Map<String, Integer> dxsfmap = Maps.newHashMap();//是否发送过 默认否
					dxsfmap.put("principal", 0);
					dxsfmap.put("linkman", 0);
					dxsfmap.put("linkmanTwo", 0);
					dxsfmap.put("linkmanThree", 0);
					
					
					EarlyDetailTemp earlyDetail=earlyDetailList.get(i);
					//填报单位信息
					String siteCode=earlyDetail.getSiteCode();
					String siteCodeName=earlyDetail.getSiteCodeName();
					String principalEmail =earlyDetail.getPrincipalEmail();//10位 负责人邮箱地址
					String linkmanEmail =earlyDetail.getLinkmanEmail();//10位 联系人邮箱地址
					String linkmanEmailTwo =earlyDetail.getLinkmanEmailTwo();//10位 联系人邮箱地址
					String linkmanEmailThree =earlyDetail.getLinkmanEmailThree();//10位 联系人邮箱地址
					String principalPhone=earlyDetail.getPrincipalPhone();//10位 短信
					String linkmanPhone=earlyDetail.getLinkmanPhone();//10位 短信
					String linkmanPhoneTwo=earlyDetail.getLinkmanPhoneTwo();//10位 短信
					String linkmanPhoneThree=earlyDetail.getLinkmanPhoneThree();//10位 短信
					
					String websiteUrl=earlyDetail.getUrl();
					String scanDate=earlyDetail.getScanTime();
					String continuedTime=DateUtils.formatStandardDateTime(earlyDetail.getContinuedTime());//持续时间
					String queDescribe=earlyDetail.getErrorDescribe();
					String errorCode=earlyDetail.getErrorCode();//选择邮件模版时用   200-连通  其他连不通
					String period=QueueType.getNameByCode(earlyDetail.getQueue());//队列类型
					
					
					 int sendstatus = earlyDetail.getSendStatus();//是否发送预警
					 int orgTbStatus = earlyDetail.getOrgTbStatus();//组织单位发送预警类型（1：本组织，2：下级填报单位），0：填报单位本身
					 
					 String periodTime=getHourAndMin(scanDate,continuedTime);
					//预警类型
					Integer type=earlyDetail.getType();
					
					Map<String, String> lxyxmapTB = Maps.newHashMap();//联系方式
					lxyxmapTB.put("principal", principalEmail);
					lxyxmapTB.put("linkman", linkmanEmail);
					lxyxmapTB.put("linkmanTwo", linkmanEmailTwo);
					lxyxmapTB.put("linkmanThree", linkmanEmailThree);
					Map<String, Integer> yjsfmapTB = Maps.newHashMap();//是否发送过 0没有1发送过
					yjsfmapTB.put("principal", 0);
					yjsfmapTB.put("linkman", 0);
					yjsfmapTB.put("linkmanTwo", 0);
					yjsfmapTB.put("linkmanThree", 0);
					
					Map<String, String> lxdxmapTB = Maps.newHashMap();//联系方式
					lxdxmapTB.put("principal", principalPhone);
					lxdxmapTB.put("linkman", linkmanPhone);
					lxdxmapTB.put("linkmanTwo", linkmanPhoneTwo);
					lxdxmapTB.put("linkmanThree", linkmanPhoneThree);
					Map<String, Integer> dxsfmapTB = Maps.newHashMap();//是否发送过 0没有1发送过
					dxsfmapTB.put("principal", 0);
					dxsfmapTB.put("linkman", 0);
					dxsfmapTB.put("linkmanTwo", 0);
					dxsfmapTB.put("linkmanThree", 0);
					
					Map<Object,Object>tplValueMap=  new HashMap<Object,Object>();
					tplValueMap.put("siteCodeName", siteCodeName);
					tplValueMap.put("scanTime", scanDate);
					tplValueMap.put("preTime", continuedTime);
					tplValueMap.put("periodTime", periodTime);
					tplValueMap.put("queDescribe", errorCode+queDescribe);
					tplValueMap.put("period", period);
					tplValueMap.put("url", websiteUrl);
					tplValueMap.put("websiteUrl", websiteUrl);
					tplValueMap.put("loginUrl", "https://jg.kaipuyun.cn/");
					
					//首页不连通
					if("200".equals(errorCode)){//使用连通模版
						title="开普云网站监测-首页恢复连通";
						tpl_value=getEncodeStr("#siteCodeName#")+"="+getEncodeStr(siteCodeName)
								+"&"+getEncodeStr("#scanTime#")+"="+getEncodeStr(scanDate)
								+"&"+getEncodeStr("#periodTime#")+"="+getEncodeStr(periodTime);
						tpl_id=prop.getProperty("tplHomeConn");
						ftl="earlyMail5_lt.ftl";
					}else{//使用不连通模版
						title="开普云网站监测 - 首页连不通";
						tpl_value=getEncodeStr("#siteCodeName#")+"="+getEncodeStr(siteCodeName)
								+"&"+getEncodeStr("#scanTime#")+"="+getEncodeStr(scanDate)
								+"&"+getEncodeStr("#queDescribe#")+"="+getEncodeStr(errorCode)+getEncodeStr(queDescribe)
								+"&"+getEncodeStr("#period#")+"="+getEncodeStr(period);
						tpl_id=prop.getProperty("tplHomeNotConn");
						ftl="earlyMail5.ftl";
					}

					if(orgTbStatus == 1){//组织单位
						//是否给组织单位发送预警
						if(configEarly.getIsSiteReceive() !=null){
							if(configEarly.getIsSiteReceive()==1 ||	(configEarly.getIsSiteReceive()==3 	&& configEarly.getDataBaseLinkType()==1)){
								/***组织单位是否接受短信***********/
								if(configEarly.getIsTelReceive()==1){
									if(isInHour(startHour, endHour)){

										if(isOrgPrincipalReceive == 1){
											if(isMobile(orgPrincipalPhone)){
												//组织单位 负责人发送短信
												String returnOP=YunpianSmsUtil.tplSendSms(tpl_id, tpl_value, orgPrincipalPhone);
												dxsfmap.put("principal", 1);
												logger.info("======组织单位  负责人  发送短信=====code:"+JSONObject.fromObject(returnOP).get("code"));
												//向预警日志表中加入数据
												  //获得短信发送的内容
												String sMsg = getSendShortMessage(tpl_id, tplValueMap);
												addEarlyLog(orgSiteCode,null,type,2,orgPrincipalPhone,sMsg,2);
											}else{
												logger.info("====组织单位  负责人  发送短信==手机号码为空或者格式不正确");
											}
										}
										if(isOrgLinkmanReceive == 1 ){
											if(isMobile(orgLinkmanPhone) && ISend(lxdxmap, dxsfmap).get("linkman") == 1){
												//组织单位联系人发送短信
												String returnOL=YunpianSmsUtil.tplSendSms(tpl_id, tpl_value, orgLinkmanPhone);
												dxsfmap.put("linkman", 1);
												logger.info("======组织单位    联系人 发送短信=====code:"
														+JSONObject.fromObject(returnOL).get("code"));
												//向预警日志表中加入数据
												  //获得短信发送的内容
												String sMsg = getSendShortMessage(tpl_id, tplValueMap);
												addEarlyLog(orgSiteCode,null,type,2,orgLinkmanPhone,sMsg,2);
												
											}else{
												logger.info("====组织单位  负责人  发送短信==手机号码为空或者格式不正确");
											}
											
										}
										
										if(orgLinkmanTwo!=null && orgLinkmanTwo == 1 ){
											if(isMobile(orgLinkmanPhoneTwo) && ISend(lxdxmap, dxsfmap).get("linkmanTwo") == 1){
												//组织单位联系人发送短信
												String returnOL=YunpianSmsUtil.tplSendSms(tpl_id, tpl_value, orgLinkmanPhoneTwo);
												dxsfmap.put("linkmanTwo", 1);
												//向预警日志表中加入数据
												  //获得短信发送的内容
												String sMsg = getSendShortMessage(tpl_id, tplValueMap);
												addEarlyLog(orgSiteCode,null,type,2,orgLinkmanPhoneTwo,sMsg,2);
											}
											
										}
										
										if(orgLinkmanThree!=null && orgLinkmanThree == 1 ){
											if(isMobile(orgLinkmanPhoneThree) && ISend(lxdxmap, dxsfmap).get("linkmanThree") == 1){
												//组织单位联系人发送短信
												String returnOL=YunpianSmsUtil.tplSendSms(tpl_id, tpl_value, orgLinkmanPhoneThree);
												dxsfmap.put("linkmanThree", 1);
												//向预警日志表中加入数据
												  //获得短信发送的内容
												String sMsg = getSendShortMessage(tpl_id, tplValueMap);
												addEarlyLog(orgSiteCode,null,type,2,orgLinkmanPhoneThree,sMsg,2);
											}
											
										}
									}
								}
								/***组织单位是否接受邮件***********/
								if(configEarly.getIsEmailReceive()==1){
									if(isOrgPrincipalReceive ==1){
										if(isEmail(orgPrincipalEmail)){
											//负责人发送邮件
											SendEmail.sendEmail(title, ftl, tplValueMap, orgPrincipalEmail);
											yjsfmap.put("principal", 1);
											//获得邮件发送的内容
											String eMsg = getSendEmailMessage(ftl, tplValueMap,true,false);
											addEarlyLog(orgSiteCode,null,type,1,orgPrincipalEmail,eMsg,1);
										}
									}

									if(isOrgLinkmanReceive ==1){
										if(isEmail(orgLinkmanEmail) && ISend(lxyxmap, yjsfmap).get("linkman") == 1){
											//联系人发送邮件
											SendEmail.sendEmail(title, ftl, tplValueMap, orgLinkmanEmail);
											yjsfmap.put("linkman", 1);
											//获得邮件发送的内容
											String eMsg = getSendEmailMessage(ftl, tplValueMap,true,false);
											addEarlyLog(orgSiteCode,null,type,1,orgLinkmanEmail,eMsg,1);
										}
									}

									if(orgLinkmanTwo != null && orgLinkmanTwo == 1 ){
										if(isEmail(orgLinkmanEmailTwo) && ISend(lxyxmap, yjsfmap).get("linkmanTwo") == 1){
											//联系人发送邮件
											SendEmail.sendEmail(title, ftl, tplValueMap, orgLinkmanEmailTwo);
											yjsfmap.put("linkmanTwo", 1);
											//获得邮件发送的内容
											String eMsg = getSendEmailMessage(ftl, tplValueMap,true,false);
											addEarlyLog(orgSiteCode,null,type,1,orgLinkmanEmailTwo,eMsg,1);
										}
									}

									if(orgLinkmanThree != null && orgLinkmanThree ==1){
										if(isEmail(orgLinkmanEmailThree) && ISend(lxyxmap, yjsfmap).get("linkmanThree") == 1){
											//联系人发送邮件
											SendEmail.sendEmail(title, ftl, tplValueMap, orgLinkmanEmailThree);
											yjsfmap.put("linkmanThree", 1);
											//获得邮件发送的内容
											String eMsg = getSendEmailMessage(ftl,tplValueMap,true,false);
											addEarlyLog(orgSiteCode,null,type,1,orgLinkmanEmailThree,eMsg,1);
										}
									}
								}
								
								/***组织单位是否接受微信***********/
								if(configEarly.getIsWxReceive() ==1 ){
									Map<String,Object> pMap = new HashMap<String, Object>();
									if(errorCode.equals("200")){//恢复正常
										type=14;//首页访问正常
										pMap.put("queDescribe", "首页连接恢复正常");
									}else{//连不通
										pMap.put("queDescribe", queDescribe);
									}
									pMap.put("name", siteCodeName);
									pMap.put("code", siteCode);
									pMap.put("scanTime",scanDate);
									pMap.put("url", connHomeUrl+"?siteCode="+siteCode);
									pMap.put("websiteUrl", websiteUrl);
									//封装模板消息
									// 微信发送  返回1 成功  2 失败；
									sendCommWX(pMap, String.valueOf(type),orgSiteCode,2,null);		
								}
							}
							//只要设置为接受状态就更改状态，不是发送成功才改状态  update by renpb 2017-04-14
							earlyDetail.setSendStatus(1);
							//更新预警信息
							earlyDetailTempServiceImpl.update(earlyDetail);
						}
					}
					if(orgTbStatus == 2){//填报单位
						//是否给填报单位发预警
						if(configEarly.getIsNextAllSite()!=null && configEarly.getIsNextAllSite()==1){
							/***填报单位是否接受短信***********/
							if(configEarly.getIsTelReceive() ==1 ){
								if(isInHour(startHour, endHour)){
									if(isPrincipalReceive ==1){
										if(StringUtils.isNotEmpty(principalPhone) && isMobile(principalPhone)){
											//填报单位负责人发送短信
											String returnOTP=YunpianSmsUtil.tplSendSms(tpl_id, tpl_value, principalPhone);
											dxsfmapTB.put("principal", 1);
											logger.info("======组织单位    填报单位  负责人 发送短信=====code:"+JSONObject.fromObject(returnOTP).get("code"));
											//向预警日志表中加入数据
											//获得短信发送的内容
											String sMsg = getSendShortMessage(tpl_id, tplValueMap);
											addEarlyLog(siteCode,null,type,2,principalPhone,sMsg,1);
										}else{
											logger.info("====组织单位    填报单位  负责人 发送短信==手机号码为空或者格式不正确");
										}
										
									}
									if(isLinkmanReceive ==1){
										if(StringUtils.isNotEmpty(linkmanPhone) && isMobile(linkmanPhone) && ISend(lxdxmapTB, dxsfmapTB).get("linkman") == 1 ){
											//填报联系人发送短信
											String returnOTL=YunpianSmsUtil.tplSendSms(tpl_id, tpl_value, linkmanPhone);
											dxsfmapTB.put("linkman", 1);
											logger.info("======组织单位    填报单位  联系人 发送短信=====code:"+JSONObject.fromObject(returnOTL).get("code"));
											//向预警日志表中加入数据
											  //获得短信发送的内容
											String sMsg = getSendShortMessage(tpl_id, tplValueMap);
											addEarlyLog(siteCode,null,type,2,linkmanPhone,sMsg,1);
										}else{
											logger.info("====组织单位    填报单位  联系人 发送短信==手机号码为空或者格式不正确");
										}
										
										if(StringUtils.isNotEmpty(linkmanPhoneTwo) && isMobile(linkmanPhoneTwo) && ISend(lxdxmapTB, dxsfmapTB).get("linkmanTwo") == 1){
											//填报联系人发送短信
											String returnOTL=YunpianSmsUtil.tplSendSms(tpl_id, tpl_value, linkmanPhoneTwo);
											dxsfmapTB.put("linkmanTwo", 1);
											//向预警日志表中加入数据
											  //获得短信发送的内容
											String sMsg = getSendShortMessage(tpl_id, tplValueMap);
											addEarlyLog(siteCode,null,type,2,linkmanPhoneTwo,sMsg,1);
										}
										
										if(StringUtils.isNotEmpty(linkmanPhoneThree) && isMobile(linkmanPhoneThree) && ISend(lxdxmapTB, dxsfmapTB).get("linkmanThree") == 1){
											//填报联系人发送短信
											String returnOTL=YunpianSmsUtil.tplSendSms(tpl_id, tpl_value, linkmanPhoneThree);
											dxsfmapTB.put("linkmanThree", 1);
											//向预警日志表中加入数据
											  //获得短信发送的内容
											String sMsg = getSendShortMessage(tpl_id, tplValueMap);
											addEarlyLog(siteCode,null,type,2,linkmanPhoneThree,sMsg,1);
										}
									}
								}
								
							}
							/*****是否接受邮件******/	
							if(configEarly.getIsEmailReceive()==1){
								if(isPrincipalReceive ==1){
									if(isEmail(principalEmail)){
										//负责人发送邮件
										SendEmail.sendEmail(title, ftl, tplValueMap, principalEmail);
										yjsfmapTB.put("principal", 1);
										//获得邮件发送的内容
										String eMsg = getSendEmailMessage(ftl, tplValueMap,true,false);
										addEarlyLog(siteCode,null,type,1,principalEmail,eMsg,1);
									}
								}
								if(isLinkmanReceive ==1){
									if(isEmail(linkmanEmail) && ISend(lxyxmapTB, yjsfmapTB).get("linkman") == 1){
										//联系人发送邮件
										SendEmail.sendEmail(title, ftl, tplValueMap, linkmanEmail);
										yjsfmapTB.put("linkman", 1);
										//获得邮件发送的内容
										String eMsg = getSendEmailMessage(ftl, tplValueMap,true,false);
										addEarlyLog(siteCode,null,type,1,linkmanEmail,eMsg,1);
									}
									if(isEmail(linkmanEmailTwo) && ISend(lxyxmapTB, yjsfmapTB).get("linkmanTwo") == 1){
										//联系人发送邮件
										SendEmail.sendEmail(title, ftl, tplValueMap, linkmanEmailTwo);
										yjsfmapTB.put("linkmanTwo", 1);
										//获得邮件发送的内容
										String twoMsg = getSendEmailMessage(ftl, tplValueMap,true,false);
										addEarlyLog(siteCode,null,type,1,linkmanEmailTwo,twoMsg,1);
									}
									if(isEmail(linkmanEmailThree) && ISend(lxyxmapTB, yjsfmapTB).get("linkmanThree") == 1){
										//联系人发送邮件
										SendEmail.sendEmail(title, ftl, tplValueMap, linkmanEmailThree);
										yjsfmapTB.put("linkmanThree", 1);
										//获得邮件发送的内容
										String threeMsg = getSendEmailMessage(ftl,tplValueMap,true,false);
										addEarlyLog(siteCode,null,type,1,linkmanEmailThree,threeMsg,1);
									}
								}
								
							}
						
							
							/***填报单位是否接受微信***********/
							if(configEarly.getIsWxReceive() ==1 ){
								Map<String,Object> pMap = new HashMap<String, Object>();
								if(errorCode.equals("200")){//恢复正常
									type=14;//首页访问正常
									pMap.put("queDescribe", "首页连接恢复正常");
								}else{//连不通
									pMap.put("queDescribe", queDescribe);
								}
								pMap.put("name", siteCodeName);
								pMap.put("code", siteCode);
								pMap.put("scanTime",scanDate);
								pMap.put("url", connHomeUrl+"?siteCode="+siteCode);
								pMap.put("websiteUrl", websiteUrl);
								//封装模板消息
								// 微信发送  返回1 成功  2 失败；
								sendCommWX(pMap, String.valueOf(type),null,1,null);	
							}
							//只要设置为接受状态就更改状态，不是发送成功才改状态  update by renpb 2017-04-14
							earlyDetail.setSendStatus(1);
							//更新预警信息
							earlyDetailTempServiceImpl.update(earlyDetail);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * @Description: 组织单位是否预警
	 * @author cuichx --- 2016-11-2下午4:50:33     
	 * @param map
	 * @param mailMap
	 */
	@SuppressWarnings("unused")
	private void sendEarlyOrg1(Map<String, Object> map,Map<Object, Object> mailMap) {
		try {
			logger.info("=========sendEarlyOrg1=======begin");
			String siteCode=String.valueOf(map.get("siteCode"));
			String url=String.valueOf(map.get("url"));
			String queDescribe=String.valueOf(map.get("queDescribe"));
			String title=String.valueOf(map.get("title"));
			String ftl=String.valueOf(map.get("ftl"));
			String tplId=String.valueOf(map.get("tplId"));
			String type=String.valueOf(map.get("type")); //这里是预警日志时插入的  预警类型
			String expectTerms=String.valueOf(map.get("expectTerms"));
			String wrongTerm=String.valueOf(map.get("wrongTerm"));
			String servicePeroidId=String.valueOf(map.get("servicePeroidId"));
			String code=String.valueOf(map.get("code"));
			String scanDate=String.valueOf(map.get("scanDate"));
			String noUpdateDay=String.valueOf(map.get("noUpdateDay"));
			
			List<Map<String, Object>> orglist=null;
			/**
			 * 根据网站标识码获取其组织机构
			 */
			DatabaseTreeInfoRequest infoRequest = new DatabaseTreeInfoRequest();
			infoRequest.setSiteCode(siteCode);
			//如果组织单位  收费  ，执行组织单位是否接受填报单位的预警，如果免费状态 则不给预警
			List<DatabaseTreeInfo> orgSiteCodeList=null;
			logger.info("==============siteCode"+siteCode);
			if(StringUtils.isNotEmpty(servicePeroidId) && StringUtils.isNotEmpty(code)){//严重问题
				logger.info("==========code========="+code);
				infoRequest.setOrgSiteCode(code);
				orgSiteCodeList=databaseTreeInfoServiceImpl.queryJoinContract(infoRequest);
			}else{//
				orgSiteCodeList=databaseTreeInfoServiceImpl.queryList(infoRequest);
			}
			if(orgSiteCodeList !=null && orgSiteCodeList.size()>0){
				for (DatabaseTreeInfo info : orgSiteCodeList) {
					orglist=new ArrayList<Map<String,Object>>();
					Integer typeInteger=info.getLayerType();//判断是否为本机门户
					String orgSiteCode=info.getOrgSiteCode();
					//判断组织机构是否发送
					ConfigEarlyRequest configEarlyRequest =new ConfigEarlyRequest();
					configEarlyRequest.setSiteCode(orgSiteCode);
					configEarlyRequest.setEarlyType(2); //这里的2是单位预警设置  不是预警发送的类型
					List<ConfigEarly>  configEarlyList=configEarlyServiceImpl.queryOrgSingleSiteCode(configEarlyRequest);
					if(configEarlyList!=null && configEarlyList.size()>0){
						ConfigEarly configEarly=configEarlyList.get(0);
						/**
						 *组织单位基本信息
						 */
						//组织机构负责人信息 
						String orgPrincipalEmail= configEarly.getPrincipalEmail();
						String orgPrincipalPhone=configEarly.getPrincipalPhone();
						String orgLinkmanEmail= configEarly.getLinkmanEmail();
						String orgLinkmanPhone= configEarly.getLinkmanPhone();
						String orgLinkmanEmailTwo= configEarly.getLinkmanEmailTwo();
						String orgLinkmanPhoneTwo= configEarly.getLinkmanPhoneTwo();
						String orgLinkmanEmailThree= configEarly.getLinkmanEmailThree();
						String orgLinkmanPhoneThree= configEarly.getLinkmanPhoneThree();
						
						//组织单位
						Map<String, String> lxyxmap = Maps.newHashMap();//联系人方式
						lxyxmap.put("principal", orgPrincipalEmail);
						lxyxmap.put("linkman", orgLinkmanEmail);
						lxyxmap.put("linkmanTwo", orgLinkmanEmailTwo);
						lxyxmap.put("linkmanThree", orgLinkmanEmailThree);
						Map<String, Integer> yjsfmap = Maps.newHashMap();//是否发送过 默认否
						yjsfmap.put("principal", 0);
						yjsfmap.put("linkman", 0);
						yjsfmap.put("linkmanTwo", 0);
						yjsfmap.put("linkmanThree", 0);
						
						Map<String, String> lxdxmap = Maps.newHashMap();//联系人方式
						lxdxmap.put("principal", orgPrincipalPhone);
						lxdxmap.put("linkman", orgLinkmanPhone);
						lxdxmap.put("linkmanTwo", orgLinkmanPhoneTwo);
						lxdxmap.put("linkmanThree", orgLinkmanPhoneThree);
						Map<String, Integer> dxsfmap = Maps.newHashMap();//是否发送过 默认否
						dxsfmap.put("principal", 0);
						dxsfmap.put("linkman", 0);
						dxsfmap.put("linkmanTwo", 0);
						dxsfmap.put("linkmanThree", 0);
						
						//组织单位负责人是否接收预警 1接收 2不接收
						Integer isOrgPrincipal=configEarly.getIsOrgPrincipal();
						//组织单位联系人是否接收预警 1接收 2不接收
						Integer isOrgLinkman=configEarly.getIsOrgLinkman();
						Integer isOrgLinkmanTwo=configEarly.getOrgLinkmanTwo();
						Integer isOrgLinkmanThree=configEarly.getOrgLinkmanThree();
						
						/**
						 * 填报单位基本信息
						 */
						//填报单位基本信息
						String siteCodeName="";//网站名称
						String principalEmail="";//填报单位负责人邮箱
						String principalPhone="";//填报单位负责人电话
						String linkmanEmail="";//填报单位联系人邮箱
						String linkmanPhone="";//填报单位联系人电话
						String linkmanEmailTwo="";
						String linkmanPhoneTwo="";
						String linkmanEmailThree="";
						String linkmanPhoneThree="";
						String websiteUrl="";//网站url
						Integer isPrincipalReceive=configEarly.getIsPrincipalReceive();//填报负责人是否接受
						Integer isLinkmanReceive=configEarly.getIsLinkmanReceive();//填报联系人是否接受
						Integer isLinkmanTwo=configEarly.getIsLinkmanTwo();//填报联系人是否接受
						Integer isLinkmanThree=configEarly.getIsLinkmanThree();//填报联系人是否接受
					
						
						DatabaseInfoRequest dataRequest=new DatabaseInfoRequest();
						dataRequest.setSiteCode(info.getSiteCode());
						List<DatabaseInfo> dataList=databaseInfoServiceImpl.queryList(dataRequest);
						if(dataList!=null && dataList.size()>0){
							siteCodeName=dataList.get(0).getName();
							
							principalEmail=dataList.get(0).getEmail();
							principalPhone=dataList.get(0).getTelephone();
							
							linkmanEmail=dataList.get(0).getEmail2();
							linkmanPhone=dataList.get(0).getTelephone2();
							
							linkmanEmailTwo=dataList.get(0).getLinkmanEmailTwo();
							linkmanPhoneTwo=dataList.get(0).getLinkmanPhoneTwo();
							
							linkmanEmailThree=dataList.get(0).getLinkmanEmailThree();
							linkmanPhoneThree=dataList.get(0).getLinkmanPhoneThree();
							
							/**
							 * 先取跳转url,为空再取首页url
							 */
							if(StringUtils.isNotEmpty(dataList.get(0).getJumpUrl())){
								websiteUrl=dataList.get(0).getJumpUrl();
							}else{
								websiteUrl=dataList.get(0).getUrl();
							}
						}
						
						//填报单位
						Map<String, String> lxyxmapTB = Maps.newHashMap();//邮箱
						lxyxmapTB.put("principal", principalEmail);
						lxyxmapTB.put("linkman", linkmanEmail);
						lxyxmapTB.put("linkmanTwo", linkmanEmailTwo);
						lxyxmapTB.put("linkmanThree", linkmanEmailThree);
						Map<String, Integer> yjsfmapTB = Maps.newHashMap();//是否发送过 默认否
						yjsfmapTB.put("principal", 0);
						yjsfmapTB.put("linkman", 0);
						yjsfmapTB.put("linkmanTwo", 0);
						yjsfmapTB.put("linkmanThree", 0);
						
						Map<String, String> lxdxmapTB = Maps.newHashMap();//联系人方式
						lxdxmapTB.put("principal", principalPhone);
						lxdxmapTB.put("linkman", linkmanPhone);
						lxdxmapTB.put("linkmanTwo", linkmanPhoneTwo);
						lxdxmapTB.put("linkmanThree", linkmanPhoneThree);
						Map<String, Integer> dxsfmapTB = Maps.newHashMap();//是否发送过 默认否
						dxsfmapTB.put("principal", 0);
						dxsfmapTB.put("linkman", 0);
						dxsfmapTB.put("linkmanTwo", 0);
						dxsfmapTB.put("linkmanThree", 0);
						
						
						map.put("websiteUrl", websiteUrl);
						map.put("siteCode", info.getSiteCode());
						map.put("siteCodeName", siteCodeName);

						//短信模板id
						String tpl_id = prop.getProperty(tplId);
						//短信参数
						String tpl_value="";
						
						//获取短连接域名
						String yuMingShort="";
						DicItemRequest dicItemRequest=new DicItemRequest();
						dicItemRequest.setEnName("yuMingShort");
						
						List<DicItem>  itemList=dicItemServiceImpl.queryList(dicItemRequest);
						if(itemList!=null && itemList.size()>0){
							yuMingShort=itemList.get(0).getValue();
						}
						String shortUrl="";
						//存放模板数据的map
						Map<Object,Object> tplValueMap = new HashMap<Object,Object>();
						if("tplSeriousMistake".equals(tplId)){
							
							/**
							 * 短连接表添加数据
							 */
							ShortLink shortLink=new ShortLink();
							shortLink.setUrl(url);
							shortLink.setShortUrl(GenerateShortUrlUtil.getShortUrl(url, ""));
							
							shortLinkServiceImpl.add(shortLink);
							//需要将长连接修改短连接
							shortUrl=yuMingShort+"/"+GenerateShortUrlUtil.getShortUrl(url, "");
							map.put("url",shortUrl);
							//疑似错别字
							tpl_value=getEncodeStr("#siteCodeName#")+"="+getEncodeStr(siteCodeName)
									+"&"+getEncodeStr("#wrongTerm#")+"="+getEncodeStr(wrongTerm)
									+"&"+getEncodeStr("#expectTerms#")+"="+getEncodeStr(expectTerms)
									+"&"+getEncodeStr("#pUrl#")+"="+getEncodeStr(shortUrl);
							tplValueMap.put("siteCodeName", siteCodeName);
							tplValueMap.put("wrongTerm", wrongTerm);
							tplValueMap.put("expectTerms", expectTerms);
							tplValueMap.put("pUrl", url);
							
						}else if("tplSeriousProblem".equals(tplId)){
							/**
							 * 短连接表添加数据
							 */
							ShortLink shortLink=new ShortLink();
							shortLink.setUrl(url);
							shortLink.setShortUrl(GenerateShortUrlUtil.getShortUrl(url, ""));
							
							shortLinkServiceImpl.add(shortLink);
							//需要将长连接修改短连接
							shortUrl=yuMingShort+"/"+GenerateShortUrlUtil.getShortUrl(url, "");
							map.put("url",shortUrl);
							//严重问题
							tpl_value=getEncodeStr("#siteCodeName#")+"="+getEncodeStr(siteCodeName)
									+"&"+getEncodeStr("#queDescribe#")+"="+getEncodeStr(queDescribe)
									+"&"+getEncodeStr("#pUrl#")+"="+getEncodeStr(shortUrl);
							
							tplValueMap.put("siteCodeName", siteCodeName);
							tplValueMap.put("queDescribe", queDescribe);
							tplValueMap.put("pUrl", url);
						}else if("tplNoUpdateDay".equals(tplId)){
							//首页更新不及时
							tpl_value = getEncodeStr("#siteCodeName#")+"="+getEncodeStr(siteCodeName)
							+"&"+getEncodeStr("#scanDate#")+"="+getEncodeStr(scanDate)
							+"&"+getEncodeStr("#noUpdateDay#")+"="+getEncodeStr(noUpdateDay);
							tplValueMap.put("siteCodeName", siteCodeName);
							tplValueMap.put("scanDate", scanDate);
							tplValueMap.put("noUpdateDay", noUpdateDay);//
						}
						
						orglist.add(map);
						//发送邮件封装数据
						mailMap.put("dataList", orglist);
						
						String weiXinUrl="";
						logger.info("=======orglist=========="+orglist.size());
						
						if(("6".equals(type) && configEarly.getCorrectContent()==1) 
								||("9".equals(type) && configEarly.getModifySite()==1)
							    ||("10".equals(type) && configEarly.getNotUpdateHome()==1)){
							
							if("6".equals(type)){//内容正确性
								weiXinUrl=contCorrectTbUrl+"?siteCode="+siteCode+"&siteName="+siteCodeName;
							}else if("9".equals(type)){//严重问题
								weiXinUrl=securityFatalErrorTbUrl+"?siteCode="+siteCode+"&siteName="+siteCodeName+"&servicePeroidId="+servicePeroidId;
							}else if("10".equals(type)){//首页更新不及时
								weiXinUrl=securityHomeTbUrl+"?siteCode="+siteCode+"&siteName="+siteCodeName;
							}
							
							/**
							 * 判断是否要给组织单位发信息
							 */
							//是否通知组织单位 1接收 2不接收 3直接收本级门户预警
							Integer isSiteReceive=configEarly.getIsSiteReceive();
							
							if(isSiteReceive==1 || (isSiteReceive==3 && typeInteger==1)){
								//接收所有  或者接受本机门户
								
								//发送短信预警
								if(configEarly.getIsTelReceive() ==1 ){
									if(isOrgPrincipal == 1){
										if(isMobile(orgPrincipalPhone)){
											//组织单位 负责人发送短信
											String returnOP=YunpianSmsUtil.tplSendSms(tpl_id, tpl_value, orgPrincipalPhone);
											dxsfmap.put("principal", 1);
											logger.info("===组织单位  负责人接受短信===="+returnOP);
											logger.info("===组织单位  负责人接受短信====code:"+JSONObject.fromObject(returnOP).get("code"));
											//向预警日志表中加入数据
										    //获得短信发送的内容
											String sMsg = getSendShortMessage(tpl_id, tplValueMap);
											addEarlyLog(orgSiteCode,servicePeroidId,Integer.parseInt(type),2,orgPrincipalPhone,sMsg,2);
										}else{
											logger.info("====组织单位  负责人接受短信==手机号码为空或者格式不正确");
										}
										
									}
									if(isOrgLinkman != null && isOrgLinkman == 1 ){
										if(isMobile(orgLinkmanPhone) && ISend(lxdxmap, dxsfmap).get("linkman") == 1){
											//组织单位联系人发送短信
											String returnOL=YunpianSmsUtil.tplSendSms(tpl_id, tpl_value, orgLinkmanPhone);
											dxsfmap.put("linkman", 1);
											logger.info("===组织单位   联系人接受短信===="+returnOL);
											logger.info("===组织单位  联系人接受短信====code:"+JSONObject.fromObject(returnOL).get("code"));
											//向预警日志表中加入数据
										    //获得短信发送的内容
											String sMsg = getSendShortMessage(tpl_id, tplValueMap);
											addEarlyLog(orgSiteCode,servicePeroidId,Integer.parseInt(type),2,orgLinkmanPhone,sMsg,2);
										}else{
											logger.info("====组织单位  联系人接受短信==手机号码为空或者格式不正确");
										}
									}
									
									if(isOrgLinkmanTwo != null && isOrgLinkmanTwo == 1 ){
										if(isMobile(orgLinkmanPhoneTwo) && ISend(lxdxmap, dxsfmap).get("linkmanTwo") == 1){
											//组织单位联系人发送短信
											String returnOL=YunpianSmsUtil.tplSendSms(tpl_id, tpl_value, orgLinkmanPhoneTwo);
											dxsfmap.put("linkmanTwo",1);
											//向预警日志表中加入数据 获得短信发送的内容
											String sMsg = getSendShortMessage(tpl_id, tplValueMap);
											addEarlyLog(orgSiteCode,servicePeroidId,Integer.parseInt(type),2,orgLinkmanPhoneTwo,sMsg,2);
										}
									}
									if(isOrgLinkmanThree != null && isOrgLinkmanThree == 1 ){
										if(isMobile(orgLinkmanPhoneThree) && ISend(lxdxmap, dxsfmap).get("linkmanThree") == 1){
											//组织单位联系人发送短信
											String returnOL=YunpianSmsUtil.tplSendSms(tpl_id, tpl_value, orgLinkmanPhoneThree);
											dxsfmap.put("linkmanThree", 1);
											//向预警日志表中加入数据 获得短信发送的内容
											String sMsg = getSendShortMessage(tpl_id, tplValueMap);
											addEarlyLog(orgSiteCode,servicePeroidId,Integer.parseInt(type),2,orgLinkmanPhoneThree,sMsg,2);
										}
									}
								}
								//发送邮件预警
								if(configEarly.getIsEmailReceive()==1){
									//负责人发送邮件
									if(isOrgPrincipal ==1){
										if(isEmail(orgPrincipalEmail)){
											logger.info("=============564========="+orgPrincipalEmail+"============645===========");
											SendEmail.sendEmail(title, ftl, mailMap, orgPrincipalEmail);
											yjsfmap.put("principal", 1);
											//预警日志插入
											//获得邮件发送的内容
											String eMsg = getSendEmailMessageStr(ftl, orglist.get(0),true,false);
											addEarlyLog(orgSiteCode,servicePeroidId,Integer.parseInt(type),1,orgPrincipalEmail,eMsg,2);
										}
									}
									//联系人发送邮件
									if(isOrgLinkman ==1){
										if(isEmail(orgLinkmanEmail) && ISend(lxyxmap, yjsfmap).get("linkman") == 1){
											SendEmail.sendEmail(title, ftl, mailMap, orgLinkmanEmail);
											yjsfmap.put("linkman", 1);
											//预警日志插入
											//获得邮件发送的内容
											String eMsg = getSendEmailMessageStr(ftl, orglist.get(0),true,false);
											addEarlyLog(orgSiteCode,servicePeroidId,Integer.parseInt(type),1,orgLinkmanEmail,eMsg,2);
										}
									}
									
									if(isOrgLinkmanTwo != null && isOrgLinkmanTwo == 1 ){
										if(isEmail(orgLinkmanEmailTwo) && ISend(lxyxmap, yjsfmap).get("linkmanTwo") == 1){
											SendEmail.sendEmail(title, ftl, mailMap, orgLinkmanEmailTwo);
											yjsfmap.put("linkmanTwo", 1);
											//预警日志插入
											//获得邮件发送的内容
											String eMsg = getSendEmailMessageStr(ftl, orglist.get(0),true,false);
											addEarlyLog(orgSiteCode,servicePeroidId,Integer.parseInt(type),1,orgLinkmanEmailTwo,eMsg,2);
										}
									}
									if(isOrgLinkmanThree != null && isOrgLinkmanThree == 1 ){
										if(isEmail(orgLinkmanEmailThree) && ISend(lxyxmap, yjsfmap).get("linkmanThree") == 1){
											SendEmail.sendEmail(title, ftl, mailMap, orgLinkmanEmailThree);
											yjsfmap.put("linkmanThree", 1);
											//预警日志插入
											//获得邮件发送的内容
											String eMsg = getSendEmailMessageStr(ftl, orglist.get(0),true,false);
											addEarlyLog(orgSiteCode,servicePeroidId,Integer.parseInt(type),1,orgLinkmanEmailThree,eMsg,2);
										}
									}
								}
								/***组织单位是否接受微信***********/
								if(configEarly.getIsWxReceive() ==1 ){
									Map<String,Object> pMap = new HashMap<String, Object>();
									pMap.put("queDescribe", queDescribe);
									pMap.put("name", siteCodeName);
									pMap.put("code", siteCode);
									pMap.put("scanTime",mailMap.get("scanTime"));
									pMap.put("url", weiXinUrl);
									pMap.put("websiteUrl", websiteUrl);
									//封装模板消息
									// 微信发送  返回1 成功  2 失败；
									sendCommWX(pMap, type,configEarly.getSiteCode(),2,servicePeroidId);									
								}
							}
							
							/**
							 *是否给填报单位发预警(组织单位是否要求 )
							 */
							//是否组织单位下的所有填报单位 1通知 2不通知
							Integer isNextAllSite=configEarly.getIsNextAllSite();
							if(isNextAllSite==1){
								if(configEarly.getIsEmailReceive()==1){
									//多个问题信息  一次性发送给 填报单位
									//mailMap.put("dataList", orglist);//给填报单位发放的邮件
									if(isPrincipalReceive ==1){
										if(isEmail(principalEmail)){
											//负责人发送邮件
											boolean resultSend = SendEmail.sendEmail(title, ftl, mailMap, principalEmail);
											yjsfmapTB.put("principal", 1);
											logger.info("======负责人邮件发送======="+resultSend);
											//预警日志插入
											//获得邮件发送的内容
											String eMsg = getSendEmailMessageStr(ftl, orglist.get(0),true,false);
											addEarlyLog(info.getSiteCode(),servicePeroidId,Integer.parseInt(type),1,principalEmail,eMsg,1);
										}
									}
									
									if(isLinkmanReceive ==1){
										if(isEmail(linkmanEmail) && ISend(lxyxmapTB, yjsfmapTB).get("linkman") == 1){
											//联系人发送邮件
											boolean resultSendlink = SendEmail.sendEmail(title, ftl, mailMap, linkmanEmail);
											yjsfmapTB.put("linkman", 1);
											logger.info("======联系人邮件发送======="+resultSendlink);
											//预警日志插入
											//获得邮件发送的内容
											String eMsg = getSendEmailMessageStr(ftl, orglist.get(0),true,false);
											addEarlyLog(info.getSiteCode(),servicePeroidId,Integer.parseInt(type),1,linkmanEmail,eMsg,1);
										}
										
										if(isEmail(linkmanEmailTwo) && ISend(lxyxmapTB, yjsfmapTB).get("linkmanTwo") == 1){
											//联系人发送邮件
											boolean resultSendlinkTwo = SendEmail.sendEmail(title, ftl, mailMap, linkmanEmailTwo);
											yjsfmapTB.put("linkmanTwo", 1);
											//预警日志插入  获得邮件发送的内容
											String twoMsg = getSendEmailMessageStr(ftl, orglist.get(0),true,false);
											addEarlyLog(info.getSiteCode(),servicePeroidId,Integer.parseInt(type),1,linkmanEmailTwo,twoMsg,1);
										}
										
										if(isEmail(linkmanEmailThree) && ISend(lxyxmapTB, yjsfmapTB).get("linkmanThree") == 1){
											//联系人发送邮件
											boolean resultSendlinkThree = SendEmail.sendEmail(title, ftl, mailMap, linkmanEmailThree);
											yjsfmapTB.put("linkmanThree", 1);
											//预警日志插入 获得邮件发送的内容
											String threeMsg = getSendEmailMessageStr(ftl, orglist.get(0),true,false);
											addEarlyLog(info.getSiteCode(),servicePeroidId,Integer.parseInt(type),1,linkmanEmailThree,threeMsg,1);
										}
									}
								}
							
								/***填报单位是否接受短信***********/
								if(configEarly.getIsTelReceive() ==1 ){
									if(isPrincipalReceive ==1){//负责人
										if(isMobile(principalPhone)){
											//填报单位负责人发送短信
											String returnOTP=YunpianSmsUtil.tplSendSms(tpl_id, tpl_value, principalPhone);
											dxsfmapTB.put("principal", 1);
											logger.info("===组织单位   填报单位   联系人接受短信====code:"+JSONObject.fromObject(returnOTP).get("code"));
											
											//向预警日志表中加入数据
										    //获得短信发送的内容
											String sMsg = getSendShortMessage(tpl_id, tplValueMap);
											addEarlyLog(info.getSiteCode(),servicePeroidId,Integer.parseInt(type),2,principalPhone,sMsg,1);
										}else{
											logger.info("====组织单位   填报单位   联系人接受短信==手机号码为空或者格式不正确");
										}
									}
									
									if(isLinkmanReceive ==1){//联系人
										if(isMobile(linkmanPhone) && ISend(lxdxmapTB, dxsfmapTB).get("linkman") == 1){//联系人
											//填报联系人发送短信
											String returnOTLTwo=YunpianSmsUtil.tplSendSms(tpl_id, tpl_value, linkmanPhone);
											dxsfmapTB.put("linkman", 1);
											logger.info("===组织单位   填报单位   联系人接受短信====code:"+JSONObject.fromObject(returnOTLTwo).get("code"));
											
											//向预警日志表中加入数据
										    //获得短信发送的内容
											String sMsg = getSendShortMessage(tpl_id, tplValueMap);
											addEarlyLog(info.getSiteCode(),servicePeroidId,Integer.parseInt(type),2,linkmanPhone,sMsg,1);
										}else{
											logger.info("====组织单位   填报单位   联系人接受短信==手机号码为空或者格式不正确");
										}
										
										if(isMobile(linkmanPhoneTwo) && ISend(lxdxmapTB, dxsfmapTB).get("linkmanTwo") == 1){//联系人2
											//填报联系人发送短信
											String returnOTL=YunpianSmsUtil.tplSendSms(tpl_id, tpl_value, linkmanPhoneTwo);
											dxsfmapTB.put("linkmanTwo", 1);
											//向预警日志表中加入数据 获得短信发送的内容
											String twoMsg = getSendShortMessage(tpl_id, tplValueMap);
											addEarlyLog(info.getSiteCode(),servicePeroidId,Integer.parseInt(type),2,linkmanPhoneTwo,twoMsg,1);
										}
										if(isMobile(linkmanPhoneThree) && ISend(lxdxmapTB, dxsfmapTB).get("linkmanThree") == 1){//联系人3
											//填报联系人发送短信
											String returnOTLThree=YunpianSmsUtil.tplSendSms(tpl_id, tpl_value, linkmanPhoneThree);
											dxsfmapTB.put("linkmanThree",1);
											//向预警日志表中加入数据 获得短信发送的内容
											String threeMsg = getSendShortMessage(tpl_id, tplValueMap);
											addEarlyLog(info.getSiteCode(),servicePeroidId,Integer.parseInt(type),2,linkmanPhoneThree,threeMsg,1);
										}
									}
								}	
								/***填报单位是否接受微信***********/
								if(configEarly.getIsWxReceive() ==1 ){
									Map<String,Object> pMap = new HashMap<String, Object>();
									pMap.put("queDescribe", queDescribe);
									pMap.put("name", siteCodeName);
									pMap.put("code", siteCode);
									pMap.put("scanTime",mailMap.get("scanTime"));
									pMap.put("url", weiXinUrl);
									pMap.put("websiteUrl", websiteUrl);
									// 微信发送  返回1 成功  2 失败；
									sendCommWX(pMap, type,null,1,servicePeroidId);									
								}
								
							}
							
						}
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 
	 * @描述:填报单位  数据微信发送
	 * @作者:linhb@ucap.com.cn
	 * @时间:2016年11月22日下午1:49:50 
	 * @param pMap 
	 * @param type
	 * @param sendSquare 给谁发送微信? 1 填报单位 2组织单位
	 * @param servicePeriodId 服务周期id
	 */
	public int sendCommWX(Map<String, Object> pMap,String type,String orgSiteCode,Integer sendSquare,Object servicePeriodId) throws Exception{
		int typeStatus = 0;
		if(StringUtils.isNotEmpty(type)){
			typeStatus = Integer.parseInt(type);
		}
		pMap.put("type", typeStatus);
		int status = 0;
		//封装模板消息
		String access_token=CommonUtils.getTokenFromServlet();
		String  requestUrl="https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+access_token;
		
		String siteCode = "";
		if(StringUtils.isNotEmpty(orgSiteCode) ){
			siteCode = orgSiteCode;
		}else{
			siteCode = (String) pMap.get("code");
		}
		AccountBindInfoRequest accountBindInfoRequest = new AccountBindInfoRequest();
		accountBindInfoRequest.setPageNo(0);
		accountBindInfoRequest.setPageSize(Integer.MAX_VALUE);
		accountBindInfoRequest.setSiteCode(siteCode);
		accountBindInfoRequest.setStatus(AccountBindStatus.ACCOUNT_BIND.getCode());//状态为绑定
		accountBindInfoRequest.setSiteListEarlyStatus(ReceiveType.IS_RECEIVE.getCode());//接收预警
		List<AccountBindInfo> accountBindInfoList = accountBindInfoServiceImpl.queryList(accountBindInfoRequest);
		for (AccountBindInfo accountBindInfo : accountBindInfoList) {
			pMap.put("openId", accountBindInfo.getOpenId());
			
			pMap.put("dicChannelId", EarlyType.getNameByCode(typeStatus));
			
			logger.info("点击详情====url"+String.valueOf(pMap.get("url")));
			String jsonStr=taskSendWXEarly.getSendModelTB(pMap);
			//发送模板消息
			JSONObject httpRequest = CommonUtils.httpRequest(requestUrl, "POST", jsonStr);
			String errmsg = (String) httpRequest.get("errmsg");
			
			//插入预警发送日志
			String wxSendMessage = taskSendWXEarly.getWXSendMessage(pMap); //微信发送的内容
			addEarlyLog(siteCode,servicePeriodId,typeStatus,3,accountBindInfo.getOpenId(),wxSendMessage,sendSquare);
			if(errmsg !=null && errmsg.equals("ok")){
				status = 1;
				//earlyDetail.setSendWxStatus(1);//设置微信发送状态为1发送成功
			}else{
				status = 2;
				//earlyDetail.setSendWxStatus(2);//设置微信发送状态为2发送失败
			}
		}
		return status;
	}
	/**
	 * @Description: 填报单位是否发送预警
	 * @author cuichx --- 2016-11-2下午4:49:39     
	 * @param map
	 * @param mailMap
	 */
	@SuppressWarnings("unused")
	public void sendEarlyTB1(Map<String, Object> map,Map<Object, Object> mailMap){
		
		try {
			logger.info("=========sendEarlyTB1=======begin");
			String siteCode=String.valueOf(map.get("siteCode"));
			String url=String.valueOf(map.get("url"));
			String queDescribe=String.valueOf(map.get("queDescribe"));
			String title=String.valueOf(map.get("title"));
			String ftl=String.valueOf(map.get("ftl"));
			String type=String.valueOf(map.get("type"));
			String newEarlNum=String.valueOf(map.get("newEarlNum"));
			
			String tplId=String.valueOf(map.get("tplId"));
			String expectTerms=String.valueOf(map.get("expectTerms"));
			String wrongTerm=String.valueOf(map.get("wrongTerm"));
			String servicePeroidId=String.valueOf(map.get("servicePeroidId"));
			String scanDate=String.valueOf(map.get("scanDate"));
			String noUpdateDay=String.valueOf(map.get("noUpdateDay"));
			
			List<Map<String, Object>> oneSitelist=new ArrayList<Map<String,Object>>();
			//查询填报单位预警表配置
			Map<String, Object> paramMap=new HashMap<String, Object>();
			paramMap.put("siteCode", siteCode);
			List<ConfigEarly>  configEarlyList=configEarlyServiceImpl.findByMap(paramMap);
			if(configEarlyList!=null && configEarlyList.size()>0){
				//循环填报单位配置表
				for(ConfigEarly configEarly:configEarlyList){
					
					String siteCodeName=configEarly.getName();
					String homeUrl=configEarly.getSiteUrl();//首页url
					String jumpUrl=configEarly.getJumpUrl();//跳转url
					if(StringUtils.isNotEmpty(jumpUrl)){
						map.put("websiteUrl", jumpUrl);
					}else{
						map.put("websiteUrl", homeUrl);
					}
					
					Integer isPrincipalReceive=configEarly.getIsPrincipalReceive();//负责人是否接受
					Integer isLinkmanReceive=configEarly.getIsLinkmanReceive();//联系人是否接受
					Integer isLinkmanTwo=configEarly.getIsLinkmanTwo();//联系人是否接受
					Integer isLinkmanThree=configEarly.getIsLinkmanThree();//联系人是否接受
					
					String principalEmail =configEarly.getEmail();//10位 负责人邮箱地址 
					String linkmanEmail =configEarly.getEmail2();//10位 联系人邮箱地址
					String linkmanEmailTwo =configEarly.getLinkmanEmailTwo();//10位 联系人邮箱地址
					String linkmanEmailThree =configEarly.getLinkmanEmailThree();//10位 联系人邮箱地址
					String principalPhone=configEarly.getTelephone();//10位 短信
					String linkmanPhone=configEarly.getTelephone2();//10位 短信
					String linkmanPhoneTwo=configEarly.getLinkmanPhoneTwo();//10位 短信
					String linkmanPhoneThree=configEarly.getLinkmanPhoneThree();//10位 短信
					
					Map<String, String> lxyxmap = Maps.newHashMap();//联系人方式
					lxyxmap.put("principal", principalEmail);
					lxyxmap.put("linkman", linkmanEmail);
					lxyxmap.put("linkmanTwo", linkmanEmailTwo);
					lxyxmap.put("linkmanThree", linkmanEmailThree);
					Map<String, Integer> yjsfmap = Maps.newHashMap();//是否发送过 默认否
					yjsfmap.put("principal", 0);
					yjsfmap.put("linkman", 0);
					yjsfmap.put("linkmanTwo", 0);
					yjsfmap.put("linkmanThree", 0);
					
					Map<String, String> lxdxmap = Maps.newHashMap();//联系人方式
					lxdxmap.put("principal", principalPhone);
					lxdxmap.put("linkman", linkmanPhone);
					lxdxmap.put("linkmanTwo", linkmanPhoneTwo);
					lxdxmap.put("linkmanThree", linkmanPhoneThree);
					Map<String, Integer> dxsfmap = Maps.newHashMap();//是否发送过 默认否
					dxsfmap.put("principal", 0);
					dxsfmap.put("linkman", 0);
					dxsfmap.put("linkmanTwo", 0);
					dxsfmap.put("linkmanThree", 0);
					
					map.put("siteCodeName", siteCodeName);
					
					//短信模板id
					String tpl_id = prop.getProperty(tplId);
					//短信参数
					String tpl_value="";
					String shortUrl="";
					//获取短连接域名
					String yuMingShort="";
					DicItemRequest dicItemRequest=new DicItemRequest();
					dicItemRequest.setEnName("yuMingShort");
					
					List<DicItem>  itemList=dicItemServiceImpl.queryList(dicItemRequest);
					if(itemList!=null && itemList.size()>0){
						yuMingShort=itemList.get(0).getValue();
					}
					
					Map<Object,Object> tplValueMap = new HashMap<Object,Object>();
					if("tplSeriousMistake".equals(tplId)){
						/**
						 * 短连接表添加数据
						 */
						ShortLink shortLink=new ShortLink();
						shortLink.setUrl(url);
						shortLink.setShortUrl(GenerateShortUrlUtil.getShortUrl(url, ""));
						
						shortLinkServiceImpl.add(shortLink);
						
						//需要将长连接修改短连接
						shortUrl=yuMingShort+"/"+GenerateShortUrlUtil.getShortUrl(url, "");
						map.put("url", shortUrl);
						//疑似错别字
						tpl_value=getEncodeStr("#siteCodeName#")+"="+getEncodeStr(siteCodeName)
								+"&"+getEncodeStr("#wrongTerm#")+"="+getEncodeStr(wrongTerm)
								+"&"+getEncodeStr("#expectTerms#")+"="+getEncodeStr(expectTerms)
								+"&"+getEncodeStr("#pUrl#")+"="+getEncodeStr(shortUrl);
						tplValueMap.put("siteCodeName", siteCodeName);
						tplValueMap.put("wrongTerm", wrongTerm);
						tplValueMap.put("expectTerms", expectTerms);
						tplValueMap.put("pUrl", shortUrl);
						
					}else if("tplSeriousProblem".equals(tplId)){
						/**
						 * 短连接表添加数据
						 */
						ShortLink shortLink=new ShortLink();
						shortLink.setUrl(url);
						shortLink.setShortUrl(GenerateShortUrlUtil.getShortUrl(url, ""));
						
						shortLinkServiceImpl.add(shortLink);
						//严重问题
						shortUrl=yuMingShort+"/"+GenerateShortUrlUtil.getShortUrl(url, "");
						map.put("url", shortUrl);
						tpl_value=getEncodeStr("#siteCodeName#")+"="+getEncodeStr(siteCodeName)
								+"&"+getEncodeStr("#queDescribe#")+"="+getEncodeStr(queDescribe)
								+"&"+getEncodeStr("#pUrl#")+"="+getEncodeStr(shortUrl);
						tplValueMap.put("siteCodeName", siteCodeName);
						tplValueMap.put("queDescribe", queDescribe);
						tplValueMap.put("pUrl", shortUrl);
					}else if("tplNoUpdateDay".equals(tplId)){
						//首页更新不及时
						tpl_value = getEncodeStr("#siteCodeName#")+"="+getEncodeStr(siteCodeName)
						+"&"+getEncodeStr("#scanDate#")+"="+getEncodeStr(scanDate)
						+"&"+getEncodeStr("#noUpdateDay#")+"="+getEncodeStr(url);
						
						tplValueMap.put("siteCodeName", siteCodeName);
						tplValueMap.put("scanDate", scanDate);
						tplValueMap.put("noUpdateDay", noUpdateDay);//
					}
					logger.info("==========tpl_id:"+tpl_id+"=========tpl_value:"+tpl_value);
					oneSitelist.add(map);
					mailMap.put("dataList", oneSitelist);
					String weiXinUrl="";
					//判断  错别字和严重问题是否勾选
					if(("6".equals(type) && configEarly.getCorrectContent()==1) 
							||("9".equals(type) && configEarly.getModifySite()==1)
						||("10".equals(type) && configEarly.getNotUpdateHome()==1)){
						
						if("6".equals(type)){//内容正确性
							weiXinUrl=contCorrectTbUrl+"?siteCode="+siteCode+"&siteName="+siteCodeName;
						}else if("9".equals(type)){//严重问题
							weiXinUrl=securityFatalErrorTbUrl+"?siteCode="+siteCode+"&siteName="+siteCodeName+"&servicePeroidId="+servicePeroidId;
						}else if("10".equals(type)){//首页更新不及时
							weiXinUrl=securityHomeTbUrl+"?siteCode="+siteCode+"&siteName="+siteCodeName;
						}
						//是否给填报单位发预警
						if(configEarly.getIsSiteReceive()==1){
							if(configEarly.getIsEmailReceive()==1){
								//多个问题信息  一次性发送给 填报单位
								if(isPrincipalReceive ==1){
									if(isEmail(principalEmail)){
										//负责人发送邮件
										SendEmail.sendEmail(title, ftl, mailMap, principalEmail);
										yjsfmap.put("principal", 1);
										//logger.info("======负责人邮件发送======="+resultSend);

										//预警日志插入
										//获得邮件发送的内容
										String eMsg = getSendEmailMessageStr(ftl, oneSitelist.get(0),true,false);
										addEarlyLog(siteCode,servicePeroidId,Integer.parseInt(type),1,principalEmail,eMsg,1);
									}
								}
								if(isLinkmanReceive ==1){
									if(isEmail(linkmanEmail) && ISend(lxyxmap, yjsfmap).get("linkman") == 1){
										//联系人发送邮件
										SendEmail.sendEmail(title, ftl, mailMap, linkmanEmail);
										yjsfmap.put("linkman", 1);
										//预警日志插入
										//获得邮件发送的内容
										String eMsg = getSendEmailMessageStr(ftl, oneSitelist.get(0),true,false);
										addEarlyLog(siteCode,servicePeroidId,Integer.parseInt(type),1,linkmanEmail,eMsg,1);
									}
								}
								
								if(isLinkmanTwo ==1){
									if(isEmail(linkmanEmailTwo) && ISend(lxyxmap, yjsfmap).get("linkmanTwo") == 1){
										//联系人发送邮件
										SendEmail.sendEmail(title, ftl, mailMap, linkmanEmailTwo);
										yjsfmap.put("linkmanTwo", 1);
										//预警日志插入
										//获得邮件发送的内容
										String eMsg = getSendEmailMessageStr(ftl, oneSitelist.get(0),true,false);
										addEarlyLog(siteCode,servicePeroidId,Integer.parseInt(type),1,linkmanEmailTwo,eMsg,1);
									}
								}
								if(isLinkmanThree ==1){
									if(isEmail(linkmanEmailThree) && ISend(lxyxmap, yjsfmap).get("linkmanThree") == 1){
										//联系人发送邮件
										SendEmail.sendEmail(title, ftl, mailMap, linkmanEmailThree);
										yjsfmap.put("linkmanThree", 1);
										//预警日志插入
										//获得邮件发送的内容
										String eMsg = getSendEmailMessageStr(ftl, oneSitelist.get(0),true,false);
										addEarlyLog(siteCode,servicePeroidId,Integer.parseInt(type),1,linkmanEmailThree,eMsg,1);
									}
								}
							}
						
							/***填报单位是否接受短信***********/
							if(configEarly.getIsTelReceive() ==1 ){
								if(isPrincipalReceive ==1){
									logger.info("=========fuzeren  duanxin=========tpl_id:"+tpl_id
											+"===tpl_value:"+tpl_value+"====principalPhone:"+principalPhone);
									if(isMobile(principalPhone)){
										//填报单位负责人发送短信
										String returnP=YunpianSmsUtil.tplSendSms(tpl_id, tpl_value, principalPhone);
										dxsfmap.put("principal", 1);
										logger.info("====错别字短信返回码===code:"+JSONObject.fromObject(returnP).get("code"));
										
										//向预警日志表中加入数据
									    //获得短信发送的内容
										String sMsg = getSendShortMessage(tpl_id, tplValueMap);
										addEarlyLog(siteCode,servicePeroidId,Integer.parseInt(type),2,principalPhone,sMsg,1);
									}else{
										logger.info("====填报 负责人 错别字短信 发送短信==手机号码为空或者格式不正确");
									}
									
								}
								if(isLinkmanReceive ==1){
									//填报联系人发送短信
									logger.info("=========fuzeren  duanxin=========tpl_id:"+tpl_id
											+"===tpl_value:"+tpl_value+"====principalPhone:"+linkmanPhone);
									if(isMobile(linkmanPhone) && ISend(lxdxmap, dxsfmap).get("linkman") == 1){
										String returnL=YunpianSmsUtil.tplSendSms(tpl_id, tpl_value, linkmanPhone);
										dxsfmap.put("linkman", 1);
										logger.info("====填报  联系人 错别字短信返回码===code:"+JSONObject.fromObject(returnL).get("code"));
										
										//向预警日志表中加入数据
									    //获得短信发送的内容
										String sMsg = getSendShortMessage(tpl_id, tplValueMap);
										addEarlyLog(siteCode,servicePeroidId,Integer.parseInt(type),2,linkmanPhone,sMsg,1);
									}else{
										logger.info("====填报  联系人  错别字短信 发送短信==手机号码为空或者格式不正确");
									}
									
								}
								
								if(isLinkmanTwo ==1){
									if(isMobile(linkmanPhoneTwo) && ISend(lxdxmap, dxsfmap).get("linkmanTwo") == 1){
										String returnL=YunpianSmsUtil.tplSendSms(tpl_id, tpl_value, linkmanPhoneTwo);
										dxsfmap.put("linkmanTwo", 1);
										//向预警日志表中加入数据  获得短信发送的内容
										String sMsg = getSendShortMessage(tpl_id, tplValueMap);
										addEarlyLog(siteCode,servicePeroidId,Integer.parseInt(type),2,linkmanPhoneTwo,sMsg,1);
									}
								}
								if(isLinkmanThree ==1){
									if(isMobile(linkmanPhoneThree) && ISend(lxdxmap, dxsfmap).get("linkmanThree") == 1){
										String returnL=YunpianSmsUtil.tplSendSms(tpl_id, tpl_value, linkmanPhoneThree);
										dxsfmap.put("linkmanThree", 1);
										//向预警日志表中加入数据  获得短信发送的内容
										String sMsg = getSendShortMessage(tpl_id, tplValueMap);
										addEarlyLog(siteCode,servicePeroidId,Integer.parseInt(type),2,linkmanPhoneThree,sMsg,1);
									}
								}
							}	
							/***填报单位是否接受微信***********/
							if(configEarly.getIsWxReceive() ==1 ){
								Map<String,Object> pMap = new HashMap<String, Object>();
								pMap.put("queDescribe", queDescribe);
								pMap.put("name", siteCodeName);
								pMap.put("code", siteCode);
								pMap.put("scanTime",mailMap.get("scanTime"));
								pMap.put("url",weiXinUrl);
								pMap.put("websiteUrl", map.get("websiteUrl"));
								//封装模板消息
								// 微信发送  返回1 成功  2 失败；
								sendCommWX(pMap, type,null,1,servicePeroidId);								
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @Title: 填报单位单站预警信息
	 * @Description: TODO
	 * @author liujc@ucap.com.cn	2016-8-3下午4:16:31
	 * @param type
	 */
	public void sendSingleEarlyInfo(int type,String siteCodeStr,Integer servicePeriodId,Integer contractInfoId){
		logger.info("填报单位 单站预警信息 ===============================开始   type====="+type);
		try {
			String title="";
			Map<String, Object> paramMap1=new HashMap<String, Object>();
			paramMap1.put("siteCode", siteCodeStr);
			List<ConfigEarly>  configEarlyList=configEarlyServiceImpl.findByMap(paramMap1);
			logger.info("填报单位 单站预警信息 ===============================开始  configEarlyList.size()==="+configEarlyList.size());
			if(configEarlyList.size()>0){
				//循环预警配置表  10位siteCode
				for(ConfigEarly configEarly:configEarlyList){
					String siteCode=configEarly.getSiteCode();
					EarlyDetailRequest earlyDetailRequest=new EarlyDetailRequest();
					earlyDetailRequest.setSiteCode(siteCode);
					earlyDetailRequest.setType(type);
					//earlyDetailRequest.setSendEmailStateNot(1);
					Integer typeFlag=typeFlag(type, configEarly);//8项指标是否接受 1接受  2不接受
					
					if(typeFlag == 1){
						if(type==1){
							//首页连通性 2 当天数据
							title="开普云网站监测 - 首页连不通";
							configEarly.setTplId("tplHomeNotConnected");
								EarlyDetailTempRequest tempRequest2=new EarlyDetailTempRequest();
								tempRequest2.setSiteCode(siteCode);
								tempRequest2.setType(1);
								tempRequest2.setSendStatus(2);
								tempRequest2.setContractInfoId(contractInfoId);//合同编号
								List<EarlyDetailTemp> earlyDetailList=earlyDetailTempServiceImpl.querySiteEarlyInfo(tempRequest2);
								if(CollectionUtils.isNotEmpty(earlyDetailList) && earlyDetailList.size()>0){
									logger.info("=====组织单位---首页不连通预警，需要再发送======");
									forDataTBConn( title, "earlyMail5.ftl", earlyDetailList, configEarly);
								}else{
									logger.info("=====组织单位---首页不连通预警，不需要发送======");
								}
						}else if(type==8){
							//首页连不连通率超过3%，3 昨天数据
							title="开普云网站监测 - 首页连不连通率累计超过3%";
							configEarly.setTplId("tplHomeNotConnectedRatio");
							earlyDetailRequest.setPageNo(0);
							earlyDetailRequest.setPageSize(Integer.MAX_VALUE);
							earlyDetailRequest.setScanTime(DateUtils.getYesterdayStr());
							earlyDetailRequest.setTbSendStatus(2);//2-未发送  1-已发送
							List<EarlyDetail> earlyDetailList=earlyDetailServiceImpl.querySiteEarlyInfo(earlyDetailRequest);
							forDataTB( title,  "earlyMail6.ftl", earlyDetailList, configEarly);
						}else if(type==11){
							//空白栏目超过5个，7 昨天的数据
							title="开普云网站监测 - 空白栏目超过2个";
							configEarly.setTplId("tplblankChannel");
							earlyDetailRequest.setPageNo(0);
							earlyDetailRequest.setPageSize(Integer.MAX_VALUE);
							earlyDetailRequest.setScanTime(DateUtils.getYesterdayStr());
							earlyDetailRequest.setServicePeriodId(servicePeriodId);
							earlyDetailRequest.setTbSendStatus(2);//2-未发送  1-已发送
							List<EarlyDetail> earlyDetailList=earlyDetailServiceImpl.querySiteEarlyInfo(earlyDetailRequest);
							forDataTB( title,  "earlyMail7.ftl", earlyDetailList, configEarly);
						}else if(type==12){
							//栏目不更新超过8个， 8 昨天的数据
							title="开普云网站监测 - 基本信息不更新的栏目超过6个";
							configEarly.setTplId("tplNoUpdateChannel");
							earlyDetailRequest.setPageNo(0);
							earlyDetailRequest.setPageSize(Integer.MAX_VALUE);
							earlyDetailRequest.setScanTime(DateUtils.getYesterdayStr());
							earlyDetailRequest.setServicePeriodId(servicePeriodId);
							earlyDetailRequest.setTbSendStatus(2);//2-未发送  1-已发送
							List<EarlyDetail> earlyDetailList=earlyDetailServiceImpl.querySiteEarlyInfo(earlyDetailRequest);
							forDataTB( title,  "earlyMail7.ftl", earlyDetailList, configEarly);
						}else if(type==13){
							//互动回应差栏目超过3个月未回应 9 昨天的数据
							title="开普云网站监测 - 互动回应栏目长期未回应";
							configEarly.setTplId("tplInteractiveResponse");
							earlyDetailRequest.setPageNo(0);
							earlyDetailRequest.setPageSize(Integer.MAX_VALUE);
							earlyDetailRequest.setScanTime(DateUtils.getYesterdayStr());
							earlyDetailRequest.setServicePeriodId(servicePeriodId);
							earlyDetailRequest.setTbSendStatus(2);//2-未发送  1-已发送
							List<EarlyDetail> earlyDetailList=earlyDetailServiceImpl.querySiteEarlyInfo(earlyDetailRequest);
							forDataTB( title,  "earlyMail8.ftl", earlyDetailList, configEarly);
						}
					}
				}
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @Title: 获得单站8项指标  是否接受
	 * @Description:
	 * @author liujc@ucap.com.cn	2016-9-8下午4:36:29
	 * @param type
	 * @param configEarlyList
	 * @return
	 */
	public Integer typeFlag(Integer type,ConfigEarly configEarly){
		Integer typeFlag=2;//8项指标是否接受 1接受  2不接受
			if(type==1 && (configEarly.getHomeConnection() !=null)){
//				首页连通性 2 当天数据
//				title="开普云网站监测 - 首页连不通";
				if(configEarly.getHomeConnection() !=null){
					typeFlag=configEarly.getHomeConnection();
				}
			}else if(type==6 && (configEarly.getCorrectContent() !=null)){
//				内容正确性，4 当天
//				title="开普云网站监测 - 疑似错别字";
				typeFlag=configEarly.getCorrectContent();
			}else if(type==8 && (configEarly.getHomeConnectionPer() !=null)){
//				首页连不连通率超过3%，3 昨天数据
//				title="开普云网站监测 - 首页连不连通率累计超过3%";
				typeFlag=configEarly.getHomeConnectionPer();
			}else if(type==9 && (configEarly.getModifySite() !=null)){
//				网站疑似被挂码或被篡改，5 当天
//				title="开普云网站监测 - 严重问题";
//				modify_site
				typeFlag=configEarly.getModifySite();
			}else if(type==10 && (configEarly.getNotUpdateHome() !=null)){
//				首页不更新超过10天 ，6  自动  昨天数据
//				title="开普云网站监测 - 首页超过10天未更新";
				typeFlag=configEarly.getNotUpdateHome();
			}else if(type==11 && (configEarly.getBlankChannel() !=null)){
//				空白栏目超过5个，7 昨天的数据
//				title="开普云网站监测 - 空白栏目超过4个";
				typeFlag=configEarly.getBlankChannel();
			}else if(type==12 && (configEarly.getNotUpdateChannel() !=null)){
//				栏目不更新超过8个， 8 昨天的数据
//				title="开普云网站监测 - 基本信息不更新的栏目超过8个";
				typeFlag=configEarly.getNotUpdateChannel();
			}else if(type==13 && (configEarly.getSecurityResponse() !=null)){
//				互动回应差栏目超过3个月未回应 9 昨天的数据
//				title="开普云网站监测 - 互动回应栏目长期未回应";
				typeFlag=configEarly.getSecurityResponse();
			}
	
		return typeFlag;
		
	}
	/**
	 * 
	 * @描述: 获取正在运行中的合同 
	 * @作者:linhb@ucap.com.cn
	 * @时间:2016年11月22日下午6:31:59 
	 * @param type 1 组织单位 2 填报单位 3全部
	 * @return
	 */
	public	List<ContractInfo> getContractInfo(Integer type){
		ContractInfoRequest contractInfoRequest = new ContractInfoRequest();
		List<ContractInfo> list = new ArrayList<ContractInfo>();
		contractInfoRequest.setTypeFlag(1);//非抽查合同
		contractInfoRequest.setExecuteStatus(1);
		contractInfoRequest.setPageSize(Integer.MAX_VALUE);
		if(type == 1){
			contractInfoRequest.setOrgFlag(1);
		}else if(type == 2){
			contractInfoRequest.setTbFlag(1);
		}
		list = contractInfoServiceImpl.queryList(contractInfoRequest);
		return list;
	}
	
	/**
	 * @Description: 获取有效合同以及合同当前的服务周期
	 * @author cuichx --- 2016-11-30下午2:21:43     
	 * @param type 1 组织单位 2 填报单位 
	 * @return
	 */
	public List<ContractInfoRequest> getContractListAndSPId(Integer type){
		
		Map<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("typeFlag", 1);//非抽查合同
		paramMap.put("executeStatus", 1);//执行中的合同
		paramMap.put("scanDate", DateUtils.formatStandardDate(new Date()));//当前日期
		if(type==1){//组织单位合同
			paramMap.put("orgFlag", 1);
		}else{//填报单位合同
			paramMap.put("tbFlag", 1);
		}
		List<ContractInfoRequest> contractList=contractInfoServiceImpl.queryListByMap(paramMap);
		if(contractList!=null && contractList.size()>0){
			return contractList;
		}else{
			return null;
		}
	}

	/**
	 * @Description: 将字符串进行utf-8编码
	 * @author cuichx --- 2016-11-25上午10:31:25     
	 * @param str
	 * @return
	 */
	public static String getEncodeStr(String str){
		try {
			if(StringUtils.isNotEmpty(str)){
				return URLEncoder.encode(str, "utf-8");
			}else {
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	/**
	 * @Description: 手机号码格式验证
	 * @author cuichx --- 2016-12-14下午3:25:05     
	 * @param str
	 * @return
	 */
    public static boolean isMobile(String str) {   
    	if(StringUtils.isEmpty(str)){
    		return false;
    	}
        Pattern p = null;  
        Matcher m = null;  
        boolean b = false;   
        p = Pattern.compile("^[1][3,4,5,7，8][0-9]{9}$"); // 验证手机号  
        m = p.matcher(str);  
        b = m.matches();   
        return b;  
    }  
    /**
     * 验证邮箱
     * @Description: 
     * @author: renpb --- 2017年4月1日上午10:50:08
     * @return
     */
    public static boolean isEmail(String str){
//    	^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$
    	if(StringUtils.isEmpty(str)){
    		return false;
    	}
    	Pattern p = null;  
    	Matcher m = null;  
    	boolean b = false;   
    	p = Pattern.compile("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$"); // 验证手机号  
    	m = p.matcher(str);  
    	b = m.matches();   
    	return b;  
    }
    /**
     * @Description: 向预警日志表中添加数据
     * @author luocheng --- 2017/1/4
     * @param siteCode 网站标志码 
     * @param servicePeriodId 服务周期号
     * @param earlyType 预警类型
     * @param sendType 发送方式 1.邮件 2.短信 3.微信
     * @param ConAddress 联系地址
     * @param Content 内容
     * @param sendSquare 1填报单位 2组织单位
     */
    public void addEarlyLog(String siteCode,Object servicePeriodId,Integer earlyType,Integer sendType,String contactAddress,String content,Integer sendSquare){
    	try {
    		EarlyLog earlyLog = new EarlyLog();
        	earlyLog.setSiteCode(siteCode);
        	if(servicePeriodId != null && servicePeriodId != "" && !"".equals(servicePeriodId)){
        		earlyLog.setServicePeriodId(Integer.valueOf(servicePeriodId.toString()));
        	}
        	if(earlyType==14){//14是首页不连通  回复情况
        		earlyLog.setEarlyType(1);
        	}else{
        		earlyLog.setEarlyType(earlyType);
        	}
        	earlyLog.setSendType(sendType);
        	earlyLog.setContactAddress(contactAddress);
        	earlyLog.setContent(content);
        	earlyLog.setSendSquare(sendSquare);
        	earlyLogServiceImpl.add(earlyLog);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }
    
    /**
     * @Description: 根据短信模板和数据来获得短信发送的内容,接受参数可以是Map<String, Object> map
     * @author luocheng --- 2017/1/4
     * @param tpl_id 短信模板id
     * @param map 封装发送短信内容的map
     */
    public String getSendShortMessageStr(String tpl_id,Map<String, Object> map){
    	Map<Object,Object> map1 = new HashMap<Object,Object>();
    	 Set<String> keySet = map.keySet();
    	 for (String key : keySet) {
    		 map1.put(key, map.get(key));
		}
    	return getSendShortMessage(tpl_id,map1);
    }
    /**
     * @Description: 根据短信模板和数据来获得短信发送的内容
     * @author luocheng --- 2017/1/4
     * @param tpl_id 短信模板id
     * @param map 封装发送短信内容的map
     */
    public String getSendShortMessage(String tpl_id,Map<Object, Object> map){
    	StringBuffer sMsg = new StringBuffer();
    	if("1803740".equals(tpl_id)){
    		sMsg.append("【开普云】"+"网站监测数据日报:"+map.get("scanTime"));
    		sMsg.append("，"+map.get("sitecode"));
    		sMsg.append("涵盖站点:"+map.get("totalNum")+"个");
    		sMsg.append("全面监测站点:"+map.get("scanNum")+"个");
    		sMsg.append("监测异常站点:"+map.get("notScanNum")+"个");
    		sMsg.append(",首页连不通站点数:"+map.get("notConnectionNum")+"个站点"+map.get("notConnectionSum")+"次");
    		sMsg.append(",首页连不通率超3%站点:"+map.get("notConnectionPer7"));
    		sMsg.append(",7日总连不通率:"+map.get("notConnectionPer")+"%");
    		sMsg.append(",首页链接不可用:"+map.get("linkHomeNum")+"站点"+map.get("linkHomeSum")+"条");
    		sMsg.append(",内容更新量:"+map.get("updateNum")+"站点"+map.get("updateAvgNum")+"条");
    		sMsg.append(",首页更新不及时:"+map.get("notUpdateHome"));
    		sMsg.append(",栏目更新不及时:"+map.get("noUpdateChannelNum")+"个站点"+map.get("noUpdateChannelSum")+"次");
    	}else{
    		sMsg.append("【开普云】"+map.get("siteCodeName"));
        	if("1825422".equals(tpl_id)){//互动回应差
        		sMsg.append("互动回应栏目“"+map.get("channelName")+"”"+map.get("queDescribe"));
        	}else if("1803122".equals(tpl_id)){//严重问题
        		sMsg.append("出现了严重问题：："+map.get("queDescribe")+"。地址："+map.get("pUrl"));
        	}else if("1803198".equals(tpl_id)){//首页更新不及时
        		sMsg.append("首页截止"+map.get("scanDate")+",已超过"+map.get("noUpdateDay")+"天未更新。");
        	}else if("1803104".equals(tpl_id)){//首页连通率
        		sMsg.append("首页7日连不通率已达"+map.get("errorProportion")+"%，如超过5%则单项否决");
        	}else if("1803108".equals(tpl_id)){//错别字
        		sMsg.append("出现疑似错别字："+map.get("wrongTerm")+"，建议修改为:"+map.get("expectTerms")+"，快照："+map.get("pUrl")+"。");
        	}else if("1803130".equals(tpl_id)){//空白栏目
        		sMsg.append("发现空白栏目累计已达"+map.get("blankNum")+"个，如超过5个则单项否决。");
        	}else if("1803136".equals(tpl_id)){//栏目不更新
        		sMsg.append("发现基本信息未更新的栏目累计已达"+map.get("noUpdateChannelNum")+"个，如超过10个则单项否决。");
        	}else if("1803740".equals(tpl_id)){//日报

        	}else if("1803074".equals(tpl_id)){//首页不连通
        		sMsg.append("首页于"+map.get("scanTime")+"发生访问异常："
        				+map.get("queDescribe")+"。每隔"+map.get("period")+"为您检测一次。");
        	}else if("1803100".equals(tpl_id)){//首页连通
        		sMsg.append("首页于"+map.get("scanTime")+"恢复正常访问，本次异常持续"
        				+map.get("periodTime"));
        	}
    	}
    	return sMsg.toString();
    }
    
    /**
     * @Description: 根据邮件模板和数据的map来获得邮件发送的内容,接受参数可以是Map<String, Object> map
     * @author luocheng --- 2017/1/4
     * @param ftl 邮件模板
     * @param map 封装发送邮件内容的map
     * @param isMore是否是多封邮件 内容
     * @param useCommon 循环中使用false 非循环使用true
     */
    public String getSendEmailMessageStr(String ftl,Map<String, Object> map,boolean useCommon,boolean isMore){
    	Map<Object,Object> map1 = new HashMap<Object,Object>();
	   	 Set<String> keySet = map.keySet();
	   	 for (String key : keySet) {
	   		 map1.put(key, map.get(key));
			}
	   	 return getSendEmailMessage(ftl,map1,useCommon,isMore);
    	
    }
    /**
     * @Description: 根据邮件模板和数据的map来获得邮件发送的内容
     * @author luocheng --- 2017/1/4
     * @param ftl 邮件模板
     * @param map 封装发送邮件内容的map
     * @param isMore 是否邮件中有多个站点信息
     * @param useCommon 是否每次使用公共信息头，循环获取发送内容使用false 非循环使用true
     */
    public String getSendEmailMessage(String ftl,Map<Object, Object> map,boolean useCommon,boolean isMore){
    	StringBuffer eMsg = new StringBuffer();
    	if(useCommon){
    	eMsg = new StringBuffer();
    	eMsg.append("  网站告警信息:");
		eMsg.append("  尊敬的客户，您好：");
		eMsg.append("  截止到"+map.get("scanTime")+"，开普云监管平台监测到"+map.get("newEarlNum")+"个预警信息，请及时处理。详情如下：");
    	}
    	if(isMore){
    		eMsg = new StringBuffer();
    	}
    	if("earlyMail6.ftl".equals(ftl)){//首页连不连通率累计超过3%
    		eMsg.append("  网站名称:"+map.get("siteCodeName"));
    		eMsg.append(" ,网站标识码:"+map.get("siteCode"));
    		eMsg.append(",网站地址："+map.get("websiteUrl"));
    		eMsg.append(",首页不连通率："+map.get("errorProportion")+"%");
    		eMsg.append(",问题描述："+map.get("queDescribe"));
    		eMsg.append(",监测时间："+map.get("scanDate"));
    		eMsg.append(",温馨提示："+map.get("tips")+ ";  ");
    	}else if("earlyMail7.ftl".equals(ftl)){//严重问题  空白栏目超过2个 基本信息不更新的栏目超过6个 互动回应栏目长期未回应
    		eMsg.append("  网站名称:"+map.get("siteCodeName"));
    		eMsg.append(" ,网站标识码:"+map.get("siteCode"));
    		eMsg.append(",网站地址："+map.get("websiteUrl"));
    		eMsg.append(",问题类型："+map.get("earlyType"));
    		eMsg.append(",问题描述："+map.get("queDescribe"));
    		eMsg.append(",监测时间："+map.get("scanDate"));
    		eMsg.append(",温馨提示："+map.get("tips")+ ";  ");
    	}else if("earlyMail7_cbz.ftl".equals(ftl)){//疑似错别字
    		eMsg.append("  网站名称:"+map.get("siteCodeName"));
    		eMsg.append(" ,网站标识码:"+map.get("siteCode"));
    		eMsg.append(",网站地址："+map.get("websiteUrl"));
    		eMsg.append(",问题类型："+map.get("earlyType"));
    		eMsg.append(",疑似错误："+map.get("wrongTerm"));
    		eMsg.append(",建议修改："+map.get("expectTerms"));
    		eMsg.append(",快照地址："+map.get("url"));
    		eMsg.append(",监测时间："+map.get("scanDate")+ ";  ");
    	}else if("earlyMail8.ftl".equals(ftl)){//
    		eMsg.append(" 网站名称:"+map.get("siteCodeName"));
    		eMsg.append(",网站标识码:"+map.get("siteCode"));
    		eMsg.append(",网站地址："+map.get("websiteUrl"));
    		eMsg.append(",问题类型："+map.get("earlyType"));
    		eMsg.append(",问题描述："+map.get("queDescribe"));
    		eMsg.append(",栏目地址："+map.get("url"));
    		eMsg.append(",监测时间："+map.get("scanDate")+";  ");
    		//eMsg.append(",温馨提示："+map.get("tips") + ";  ");
    	}else if("dailyMail.ftl".equals(ftl)){//日报
    		eMsg = new StringBuffer("");
    		eMsg.append("网站告警信息");
    		eMsg.append("尊敬的客户，您好：");
    		eMsg.append(map.get("scanTime"));
    		eMsg.append("，"+map.get("siteCodeName")+"的网站监测结果概述如下：");
    		eMsg.append(" 单位名称:"+map.get("siteCodeName"));
    		eMsg.append(",组织单位编码:"+map.get("siteCode"));
    		eMsg.append(",涵盖站点:"+map.get("totalNum")+"个");
    		eMsg.append(",全面监测站点:"+map.get("scanNum")+"个");
    		eMsg.append(",监测异常站点（持续多日访问异常）:"+map.get("notScanNum")+"个");
    		eMsg.append(",网站连通性:"+map.get("notConnectionNum")+"个站点首页"+map.get("notConnectionSum")+"次连不通；"+map.get("notConnectionPer7")+"个站点首页7日连不通率超3%；7日总体连不通率为"+map.get("notConnectionPer"));
    		eMsg.append(",首页不可用链接:"+map.get("linkHomeNum")+"个站点首页"+map.get("linkHomeSum")+"条链接不可用");
    		eMsg.append(",内容更新量:"+map.get("updateNum")+"个站点总更新"+map.get("updateAvgNum")+"条");
    		eMsg.append(",内容保障问题:"+map.get("notUpdateHome")+"个站点首页更新不及时；  "+map.get("noUpdateChannelNum")+"个站点"+map.get("noUpdateChannelSum")+"个栏目更新不及时");
    	}else if("earlyMail7_sybgx.ftl".equals(ftl)){//首页更新不及时
    		eMsg.append(" 网站名称:"+map.get("siteCodeName"));
    		eMsg.append(",网站标识码:"+map.get("siteCode"));
    		eMsg.append(",网站地址："+map.get("url"));
    		eMsg.append(",问题类型："+map.get("earlyType"));
    		eMsg.append(",问题描述："+map.get("queDescribe"));
    		eMsg.append(",监测时间："+map.get("scanDate")+ ";  ");
    	}else if("earlyMail5_lt.ftl".equals(ftl)){//首页连通
    		eMsg = new StringBuffer("【开普云监管】");
    		eMsg.append("尊敬的客户，您好：");
    		eMsg.append(map.get("siteCodeName")+"首页于"+map.get("scanTime")+"检测，访问恢复正常，异常发现时间为"
    				+map.get("preTime")+"，本次异常持续"+map.get("periodTime")+"，网址："+map.get("url"));
    	}else if("earlyMail5.ftl".equals(ftl)){//首页不连通
    		eMsg = new StringBuffer("【开普云监管】");
    		eMsg.append("尊敬的客户，您好：");
    		eMsg.append(map.get("siteCodeName")+"首页于"+map.get("scanTime")+"无法正常访问："
    				+map.get("queDescribe")+"，网址："+map.get("url")
    				+"。每隔"+map.get("period")+"为您检测一次。");
    	}
    	return eMsg.toString();
    }
    
    /**
     * @Description: 根据发送短信返回的消息判断是否发送成功
     * @author luocheng --- 2017/1/4
     * @param returnMsg 发送短信返回的字符串
     * @return
     
   public boolean getSendShortMessageSuccess(String returnMsg){
	   @SuppressWarnings("unchecked")
	   Map<Object, Object> data = JSONObject.fromObject(returnMsg);
	   Integer code = (Integer) data.get("code");
	   if(code == 0){ //code为0说明短信发送成功了
		   return true;
	   }
	   return false;
   }
   */
    
	
	/**
	 * @Description:   根据负责人，联系人、联系方式,以及是否发送过,判断是否需要发送
	 * @author: renpb --- 2017年4月24日上午11:49:49
	 * @return 0不发送 1发送
	 */
	private Map<String, Integer> ISend(Map<String , String> map , Map<String , Integer> mao){


		String principal    = map.get("principal");//负责人  联系方式
		String linkman      = map.get("linkman");//联系人
		String linkmanTwo   = map.get("linkmanTwo");//联系人2
		String linkmanThree = map.get("linkmanThree");//联系人3

		Integer principalFbf    = mao.get("principal");//负责人是否发送过
		Integer linkmanFbf      = mao.get("linkman");//联系人是否发送过
		Integer linkmanTwoFbf   = mao.get("linkmanTwo");//联系人2
		//Integer linkmanThreeFbf = mao.get("linkmanThree");//联系人3

/*		List<String> list = new ArrayList<String>();
		list.add(principal);
		list.add(linkman);
		list.add(linkmanTwo);
		list.add(linkmanThree);*/

		Map<String, Integer> mm = Maps.newHashMap();
		if(StringUtils.isNotEmpty(principal) && StringUtils.isNotEmpty(linkman)	&& linkman.equals(principal) && principalFbf == 1){
			mm.put("linkman", 0);//  联系人不发送
			System.out.println("联系人不发送");
		}else{
			mm.put("linkman", 1);//  联系人发送
			System.out.println("联系人发送");
		}
		if((StringUtils.isNotEmpty(principal) && StringUtils.isNotEmpty(linkmanTwo) && linkmanTwo.equals(principal) && principalFbf == 1)
				|| (StringUtils.isNotEmpty(linkman) && StringUtils.isNotEmpty(linkmanTwo) && linkmanTwo.equals(linkman) && linkmanFbf == 1)){
			mm.put("linkmanTwo", 0);//  联系人2不发送
			System.out.println("联系人2不发送");
		}else{
			mm.put("linkmanTwo", 1);//  联系人2发送
			System.out.println("联系人2发送");
		}
		if((StringUtils.isNotEmpty(principal) && StringUtils.isNotEmpty(linkmanThree) && linkmanThree.equals(principal) && principalFbf == 1)
				|| (StringUtils.isNotEmpty(linkman) && StringUtils.isNotEmpty(linkmanThree) && linkmanThree.equals(linkman) && linkmanFbf == 1)
				|| (StringUtils.isNotEmpty(linkmanTwo) && StringUtils.isNotEmpty(linkmanThree) && linkmanThree.equals(linkmanTwo) && linkmanTwoFbf == 1)){
			mm.put("linkmanThree", 0);//  联系人3不发送
			System.out.println("联系人3不发送");
		}else{
			mm.put("linkmanThree", 1);//  联系人3发送
			System.out.println("联系人3发送");
		}

		return mm;
	}
	/**
	 * @Description:   判断当前时间是否在时间段内
	 * @author: cuichx --- 2017年4月24日上午11:49:49
	 * @param startHour  HH:mm:ss
	 * @param endHour    HH:mm:ss
	 * @return
	 */
	public boolean isInHour(String startHour,String endHour){
		
		boolean flag=false;
		//获取当前时间的  时分秒
		String nowHourStr=DateUtils.getNowTimeStandardStr();
		SimpleDateFormat sf = new SimpleDateFormat("HH:mm:ss");
		try {
			long nowHour = sf.parse(nowHourStr).getTime();
			long begin=sf.parse(startHour).getTime();
			long end=sf.parse(endHour).getTime();
            if (nowHour >= begin && nowHour < end) {
            	flag= true;
            } else {
            	flag= false;
            }
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return flag;
		
	}
	/**
	 * @Description:   计算两个时间的差值
	 * @author: cuichx --- 2017年4月24日上午11:49:49
	 * @param startHour  HH:mm:ss
	 * @param endHour    HH:mm:ss
	 * @return
	 */
	private String getHourAndMin(String scanDate, String continuedTime) {
		
		String returnStr="";
		
		Date begin=DateUtils.parseStandardDateTime(continuedTime);
		Date end=DateUtils.parseStandardDateTime(scanDate);
		
		Integer minNum=DateUtils.getMinutesBetween2Days(begin, end);
		if(minNum>=60){//转换成    xx小时xx分钟
			Integer hour=minNum/60;//取整数
			Integer min=minNum%60;//取余数
			if(hour>=24){
				Integer days=hour/24;//取整数
				Integer hour2=hour%24;//取余数
				returnStr=days+"天"+hour2+"小时"+min+"分钟";
			}else{
				returnStr=hour+"小时"+min+"分钟";
			}
		}else{//  xx分钟
			returnStr=minNum+"分钟";
		}
		
		return returnStr;
	}
	
	public static void main(String[] args) throws IOException {
		
		
		
		//互动回应
		String tpl_value=getEncodeStr("#siteCodeName#")+"="+getEncodeStr("网站名称")
				+"&"+getEncodeStr("#channelName#")+"="+getEncodeStr("栏目名称")
				+"&"+getEncodeStr("#queDescribe#")+"="+getEncodeStr("问题描述");
		
		
		String returnRB=YunpianSmsUtil.tplSendSms("1825422", tpl_value, "18510843355");
		System.out.println(returnRB);
		
		/*"44".equals(siteCode.substring(0, 2))*/
		
		
		System.out.println("110000".subSequence(0, 1));
		/**
		 * 模板再次优化
		 */
		
/*		String tpl_value=getEncodeStr("#siteCodeName#")+"="+getEncodeStr("123")
				+"&"+getEncodeStr("#wrongTerm#")+"="+getEncodeStr("222")
				+"&"+getEncodeStr("#expectTerms#")+"="+getEncodeStr("333")
				+"&"+getEncodeStr("#pUrl#")+"="+getEncodeStr("http://www.baidu.com");
		
		String returnRB=YunpianSmsUtil.tplSendSms("1790824", tpl_value, "18510843355");
		
		logger.info("=====日报  负责人发送短信======code:"+JSONObject.fromObject(returnRB).get("code"));*/
		//日报测试
/*			String tpl_value =getEncodeStr("#scanTime#")+"="+getEncodeStr("2017-05-05")
					+"&"+getEncodeStr("#siteCode#")+"="+getEncodeStr("A00011")
					+"&"+getEncodeStr("#totalNum#")+"="+getEncodeStr("100")
					+"&"+getEncodeStr("#scanNum#")+"="+getEncodeStr("98")
					+"&"+getEncodeStr("#notScanNum#")+"="+getEncodeStr("2")
					+"&"+getEncodeStr("#notConnectionNum#")+"="+getEncodeStr("12")
					+"&"+getEncodeStr("#notConnectionSum#")+"="+getEncodeStr("12")
					+"&"+getEncodeStr("#notConnectionPer7#")+"="+getEncodeStr("12")
					+"&"+getEncodeStr("#notConnectionPer#")+"="+getEncodeStr("12")
					+"&"+getEncodeStr("#linkHomeNum#")+"="+getEncodeStr("12")
					+"&"+getEncodeStr("#linkHomeSum#")+"="+getEncodeStr("12")
					+"&"+getEncodeStr("#updateNum#")+"="+getEncodeStr("12")
					+"&"+getEncodeStr("#updateAvgNum#")+"="+getEncodeStr("12")
					+"&"+getEncodeStr("#notUpdateHome#")+"="+getEncodeStr("12")
					+"&"+getEncodeStr("#noUpdateChannelNum#")+"="+getEncodeStr("12")
					+"&"+getEncodeStr("#noUpdateChannelSum#")+"="+getEncodeStr("12"); 
		
			
			String returnRB=YunpianSmsUtil.tplSendSms("1790836", tpl_value, "18510843355");
			
			logger.info("=====日报  负责人发送短信======code:"+JSONObject.fromObject(returnRB).get("code"));*/
		
		
		
		
		
		
/*		boolean flag=false;
		String a="08:26:00";
		String beginStr="07:00:00";
		String endStr="09:00:00";
		SimpleDateFormat sf = new SimpleDateFormat("HH:mm:ss");
		try {
			long aa = sf.parse(a).getTime();
			long begin=sf.parse(beginStr).getTime();
			long end=sf.parse(endStr).getTime();
			
			System.out.println("aa======="+aa);
			System.out.println("begin======="+begin);
			System.out.println("end======"+end);
		
            if (aa >= begin && aa < end) {
            	flag= true;
            } else {
            	flag= false;
            }
		        
			System.out.println(flag);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}*/
		/*		String tpl_value =getEncodeStr("#scanTime#")+"="+getEncodeStr("2017-03-15")
						+"&"+getEncodeStr("#siteCode#")+"="+getEncodeStr("A00011"+"")
						+"&"+getEncodeStr("#scanNum#")+"="+getEncodeStr(3+"")
						+"&"+getEncodeStr("#notConnectionNum#")+"="+getEncodeStr(0+"")
						+"&"+getEncodeStr("#notConnectionSum#")+"="+getEncodeStr(0+"")
						+"&"+getEncodeStr("#notConnectionPer7#")+"="+getEncodeStr(0+"")
						+"&"+getEncodeStr("#notConnectionPer#")+"="+getEncodeStr("0%"+"")
						+"&"+getEncodeStr("#linkHomeNum#")+"="+getEncodeStr(0+"")
						+"&"+getEncodeStr("#linkHomeSum#")+"="+getEncodeStr(0+"")
						+"&"+getEncodeStr("#updateNum#")+"="+getEncodeStr(0+"")
						+"&"+getEncodeStr("#updateAvgNum#")+"="+getEncodeStr(0+"")
						+"&"+getEncodeStr("#notUpdateHome#")+"="+getEncodeStr(0+"")
						+"&"+getEncodeStr("#noUpdateChannelNum#")+"="+getEncodeStr(0+"")
						+"&"+getEncodeStr("#noUpdateChannelSum#")+"="+getEncodeStr(0+"");
				
				String returnRB=YunpianSmsUtil.tplSendSms("1735560", tpl_value, "18510843355");
				
				logger.info("=====日报  负责人发送短信======code:"+JSONObject.fromObject(returnRB).get("code"));
				*/
				
				
				/*String tpl_value=getEncodeStr("#siteCodeName#")+"="+getEncodeStr("开普测试")
						+"&"+getEncodeStr("#scanTime#")+"="+getEncodeStr("2017-03-15 16:16:53")
						+"&"+getEncodeStr("#queDescribe#")+"="+getEncodeStr("请求的网页不存在")
						+"&"+getEncodeStr("#period#")+"="+getEncodeStr("15分钟")
						+"&"+getEncodeStr("#url#")+"="+getEncodeStr("http://www.boxpro.cn/");
				
				String returnRB=YunpianSmsUtil.tplSendSms("1735562", tpl_value, "");
				System.out.println(returnRB);*/
				
				
				
				//System.out.println(isMobile("18110843355"));
				
				//日报模版测试
		/*		String tpl_value =getEncodeStr("#scanTime#")+"="+getEncodeStr("2016-11-24")
				  +"&"+getEncodeStr("#sitecode#")+"="+getEncodeStr("A00011"+"")
						+"&"+getEncodeStr("#scanNum#")+"="+getEncodeStr(10+"")
						+"&"+getEncodeStr("#notConnectionPer#")+"="+getEncodeStr(30+"")
						+"&"+getEncodeStr("#linkHomeNum#")+"="+getEncodeStr(10+"")
						+"&"+getEncodeStr("#notUpdateHome#")+"="+getEncodeStr(10+"")
						+"&"+getEncodeStr("#updateNum#")+"="+getEncodeStr(12+"")
						+"&"+getEncodeStr("#updateAvgNum#")+"="+getEncodeStr(11+""); 
				String tpl_id=prop.getProperty("tplDailyEarly");
				YunpianSmsUtil.tplSendSms(tpl_id, tpl_value, "18510843355");*/
				
				//首页不连通测试
				/*for(int i=0;i<3;i++){
					String tpl_id=prop.getProperty("tplHomeNotConnected");
					//tpl_value = "#siteCodeName#="+siteCodeName + "&#url#=" + url;
					 String tpl_value=getEncodeStr("#siteCodeName#")+"="+getEncodeStr("崔传新首页不连通测试")
										+"&"+getEncodeStr("#queDescribe#")+"="+"404"
										+"&"+getEncodeStr("#url#")+"="+"www.baidu.com";
					String tpl_value=getEncodeStr("#siteCodeName#")+"="+getEncodeStr("崔传新首页不连通测试")
							+"&"+getEncodeStr("#url#")+"="+"www.baidu.com";
					
					YunpianSmsUtil.tplSendSms(tpl_id, tpl_value, "18510843355");
				}*/
				
				//首页不连通占比
		/*		for(int i=0;i<3;i++){
					String tpl_id=prop.getProperty("tplHomeNotConnectedRatio");
					//tpl_value = "#siteCodeName#="+siteCodeName + "&#url#=" + url;
					 String tpl_value=getEncodeStr("#siteCodeName#")+"="+getEncodeStr("崔传新首页不连通测试")
										+"&"+getEncodeStr("#queDescribe#")+"="+"404"
										+"&"+getEncodeStr("#url#")+"="+"www.baidu.com";
					String tpl_value=getEncodeStr("#siteCodeName#")+"="+getEncodeStr("崔传新首页不连通测试")
							+"&"+getEncodeStr("#errorProportion#")+"="+getEncodeStr("33.02")
							+"&"+getEncodeStr("#url#")+"="+getEncodeStr("www.baidu.com");
					
					YunpianSmsUtil.tplSendSms(tpl_id, tpl_value, "18510843355");
				}*/
				
				//疑似错别字
		/*		for(int i=0;i<3;i++){
					String tpl_id=prop.getProperty("tplSeriousMistake");
					//tpl_value = "#siteCodeName#="+siteCodeName + "&#url#=" + url;
					 String tpl_value=getEncodeStr("#siteCodeName#")+"="+getEncodeStr("崔传新测试")
										+"&"+getEncodeStr("#queDescribe#")+"="+"404"
										+"&"+getEncodeStr("#url#")+"="+"www.baidu.com";
					String 	tpl_value=getEncodeStr("#siteCodeName#")+"="+getEncodeStr("崔传新测试")
							+"&"+getEncodeStr("#wrongTerm#")+"="+getEncodeStr("123")
							+"&"+getEncodeStr("#expectTerms#")+"="+getEncodeStr("321")
							+"&"+getEncodeStr("#url#")+"="+getEncodeStr("www.baidu.com");
					
					YunpianSmsUtil.tplSendSms(tpl_id, tpl_value, "18510843355");
				}*/
				
				//严重问题
		/*		for(int i=0;i<3;i++){
					String tpl_id=prop.getProperty("tplSeriousProblem");
					//tpl_value = "#siteCodeName#="+siteCodeName + "&#url#=" + url;
					 String tpl_value=getEncodeStr("#siteCodeName#")+"="+getEncodeStr("崔传新测试")
										+"&"+getEncodeStr("#queDescribe#")+"="+"404"
										+"&"+getEncodeStr("#url#")+"="+"www.baidu.com";
					String 	tpl_value=getEncodeStr("#siteCodeName#")+"="+getEncodeStr("崔传新测试")
							+"&"+getEncodeStr("#queDescribe#")+"="+getEncodeStr("严重问题");
					
					YunpianSmsUtil.tplSendSms(tpl_id, tpl_value, "18510843355");
				}*/
				
				
				//空白栏目超过2个
			/*	for(int i=0;i<3;i++){
					String tpl_id=prop.getProperty("tplblankChannel");
					//tpl_value = "#siteCodeName#="+siteCodeName + "&#url#=" + url;
					 String tpl_value=getEncodeStr("#siteCodeName#")+"="+getEncodeStr("崔传新测试")
										+"&"+getEncodeStr("#queDescribe#")+"="+"404"
										+"&"+getEncodeStr("#url#")+"="+"www.baidu.com";
					String 	tpl_value=getEncodeStr("#siteCodeName#")+"="+getEncodeStr("崔传新测试")
							+"&"+getEncodeStr("#blankNum#")+"="+getEncodeStr(21+"");
					
					YunpianSmsUtil.tplSendSms(tpl_id, tpl_value, "18510843355");
				}*/
				
				//栏目不更新
		/*		for(int i=0;i<3;i++){
					String tpl_id=prop.getProperty("tplNoUpdateChannel");
					//tpl_value = "#siteCodeName#="+siteCodeName + "&#url#=" + url;
					 String tpl_value=getEncodeStr("#siteCodeName#")+"="+getEncodeStr("崔传新测试")
										+"&"+getEncodeStr("#queDescribe#")+"="+"404"
										+"&"+getEncodeStr("#url#")+"="+"www.baidu.com";
					String 	tpl_value=getEncodeStr("#siteCodeName#")+"="+getEncodeStr("崔传新测试")
							+"&"+getEncodeStr("#noUpdateChannelNum#")+"="+getEncodeStr(21+"");
					
					YunpianSmsUtil.tplSendSms(tpl_id, tpl_value, "18510843355");
				}*/
				
				//互动回应差
			/*	for(int i=0;i<3;i++){
					String tpl_id=prop.getProperty("tplInteractiveResponse");
					//tpl_value = "#siteCodeName#="+siteCodeName + "&#url#=" + url;
					String 	tpl_value=getEncodeStr("#siteCodeName#")+"="+getEncodeStr("崔传新测试")
							+"&"+getEncodeStr("#url#")+"="+getEncodeStr("www.baidu.com"+"");
					
					YunpianSmsUtil.tplSendSms(tpl_id, tpl_value, "18510843355");
				}*/
				

		/*		tpl_id = prop.getProperty(configEarly.getTplId());
				if("tplHomeNotConnected".equals(configEarly.getTplId())){
					//首页不连通
					tpl_value = "#siteCodeName#="+siteCodeName + "&#url#=" + url;
				}else if("tplHomeNotConnectedRatio".equals(configEarly.getTplId())){
					//首页不连通占比
					url=URLEncoder.encode(url,"utf-8");
					tpl_value = "#siteCodeName#="+siteCodeName +"&#errorProportion#="+errorProportion+ "&#url#=" + url;
				}else if("tplSeriousMistake".equals(configEarly.getTplId())){
					//疑似错别字
					tpl_value = "#siteCodeName#="+siteCodeName +"&#wrongTerm#="+wrongTerm+"&#expectTerms#="+expectTerms+ "&#url#=" + url;
				}else if("tplSeriousProblem".equals(configEarly.getTplId())){
					//严重问题
					tpl_value = "#siteCodeName#="+siteCodeName + "&#queDescribe#="+queDescribe;
				}else if("tplNoUpdateDay".equals(configEarly.getTplId())){
					//首页10天未更新 noUpdateDay
					tpl_value = "#siteCodeName#="+siteCodeName + "&#noUpdateDay#="+noUpdateDay;
				}else if("tplblankChannel".equals(configEarly.getTplId())){
					//空白栏目超过2个
					tpl_value = "#siteCodeName#="+siteCodeName + "&#blankNum#="+blankNum;
				}else if("tplNoUpdateChannel".equals(configEarly.getTplId())){
					//栏目不更新
					tpl_value = "#siteCodeName#="+siteCodeName + "&#noUpdateChannelNum#="+noUpdateChannelNum;
				}else if("tplInteractiveResponse".equals(configEarly.getTplId())){
					//互动回应
					tpl_value = "#siteCodeName#="+siteCodeName + "&#url#=" + url;
				}*/
			}

}
