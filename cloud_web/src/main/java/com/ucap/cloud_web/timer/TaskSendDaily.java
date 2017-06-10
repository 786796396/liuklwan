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
import com.publics.util.utils.StringUtils;
import com.ucap.cloud_web.constant.AccountBindStatus;
import com.ucap.cloud_web.constant.ConnectionType;
import com.ucap.cloud_web.constant.DatabaseInfoType;
import com.ucap.cloud_web.constant.EarlyType;
import com.ucap.cloud_web.constant.EarlyUpdateGradeType;
import com.ucap.cloud_web.constant.TrueOrFalseType;
import com.ucap.cloud_web.dto.AccountBindInfoRequest;
import com.ucap.cloud_web.dto.ConnectionAllRequest;
import com.ucap.cloud_web.dto.DatabaseInfoRequest;
import com.ucap.cloud_web.dto.DatabaseLinkRequest;
import com.ucap.cloud_web.dto.DatabaseOrgInfoRequest;
import com.ucap.cloud_web.dto.EarlyDetailRequest;
import com.ucap.cloud_web.dto.SecurityHomeChannelRequest;
import com.ucap.cloud_web.entity.AccountBindInfo;
import com.ucap.cloud_web.entity.ConnectionAll;
import com.ucap.cloud_web.entity.ContractInfo;
import com.ucap.cloud_web.entity.DatabaseInfo;
import com.ucap.cloud_web.entity.DatabaseLink;
import com.ucap.cloud_web.entity.DatabaseOrgInfo;
import com.ucap.cloud_web.entity.EarlyDetail;
import com.ucap.cloud_web.entity.SecurityHomeChannel;
import com.ucap.cloud_web.service.IAccountBindInfoService;
import com.ucap.cloud_web.service.IConnectionAllService;
import com.ucap.cloud_web.service.IContractInfoService;
import com.ucap.cloud_web.service.IDatabaseInfoService;
import com.ucap.cloud_web.service.IDatabaseLinkService;
import com.ucap.cloud_web.service.IDatabaseOrgInfoService;
import com.ucap.cloud_web.service.IEarlyDetailService;
import com.ucap.cloud_web.service.ISecurityHomeChannelService;
import com.ucap.cloud_web.servlet.message.WxSendData;
import com.ucap.cloud_web.servlet.message.WxSendModel;
import com.ucap.cloud_web.servlet.util.CommonUtils;
import com.ucap.cloud_web.util.SendEmail;


/**
 * <p>Description: 免费用户   绑定微信后发送首页连不通日报--日报统计的是昨天的连通情况</p>
 * <p>@Package：com.ucap.cloud_web.timer </p>
 * <p>Title: TaskSendConnDaily </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：cuichx </p>
 * <p>@date：2016-5-25上午9:01:34 </p>
 */
@Service("taskSendDaily")
public class TaskSendDaily {
	
	private static final String String = null;
	private static Logger logger = Logger.getLogger(TaskSendDaily.class);//日志信息
	@Autowired
	private IAccountBindInfoService accountBindInfoServiceImpl;
	
	@Autowired
	private IConnectionAllService connectionAllServiceImpl;
	
	@Autowired
	private IDatabaseInfoService databaseInfoServiceImpl;
	
	@Autowired
	private IDatabaseLinkService databaseLinkServiceImpl;
	
	@Autowired
	private IDatabaseOrgInfoService databaseOrgInfoServiceImpl;
	
	@Autowired
	private IContractInfoService contractInfoServiceImpl;
	
	@Autowired
	private ISecurityHomeChannelService securityHomeChannelServiceImpl;
	
	@Autowired
	private IEarlyDetailService earlyDetailServiceImpl;
	
	
	private static Properties prop = PropertiesUtil.getProperties("app.properties");
	
	
	/**
	 * @Description: 升级改版或者临时关停类型  发送预警(日/次)
	 * @author cuichx --- 2016-6-21上午10:55:40
	 */
	public void getUpgradeData(){
		
		try {
			/**
			 * 升级改版或者临时关停类型  发送预警--每天发送一次
			 */
			//获取所有的收费用户
			Integer[] conStatues={0,1};
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("nowTime", DateUtils.formatStandardDateTime(new Date()));
			map.put("executeStatusArr", conStatues);//合同状态为0-初始化 1-服务中
			map.put("typeFlag",1);//非抽查合同
			
			List<ContractInfo>  conList=contractInfoServiceImpl.queryContractByMap(map);
			if(conList!=null && conList.size()>0){//收费用户
				//验证微信凭证的access_token是否超时
				String access_token=CommonUtils.getTokenFromServlet();
				String  requestUrl="https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+access_token;
				
				String scanDate=DateUtils.getYesterdayStr();//昨天日期
				
				for (int i = 0; i < conList.size(); i++) {
					String code=conList.get(i).getSiteCode();
					logger.info("homeNotUpdateData ContractInfo code:"+code);
					
					EarlyDetailRequest earlyDetailRequest = new EarlyDetailRequest();
					earlyDetailRequest.setPageNo(0);
					earlyDetailRequest.setPageSize(Integer.MAX_VALUE);
					earlyDetailRequest.setType(7);//升级改版或者临时关停类型
					earlyDetailRequest.setCheckType(1);//检查类型：正常合同
					earlyDetailRequest.setScanDate(scanDate);
					earlyDetailRequest.setCheckState(2);//人工审核状态（0未审核，1未通过，2通过）
					
					if(code.length()==10){//填报单位--只发送未更新天数为10天的，后面的就不再发送
						earlyDetailRequest.setSiteCode(code);
						
						List<EarlyDetail>  earlyList=earlyDetailServiceImpl.queryList(earlyDetailRequest);
						if(earlyList!=null && earlyList.size()>0){
							singleSiteUpGrade(code,earlyList, requestUrl,scanDate);
						}
					}else{//组织单位
						
						DatabaseLinkRequest linkRequest=new DatabaseLinkRequest();
						linkRequest.setOrgSiteCode(code);
						linkRequest.setPageSize(Integer.MAX_VALUE);
						
						List<DatabaseLink>  linkList=databaseLinkServiceImpl.queryList(linkRequest);
						if(linkList!=null && linkList.size()>0){
							earlyDetailRequest.setLinkList(linkList);
							List<EarlyDetail>  earlyList=earlyDetailServiceImpl.queryList(earlyDetailRequest);
							if(earlyList!=null && earlyList.size()>0){
								singleSiteUpGrade(code,earlyList, requestUrl,scanDate);
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
	 * @Description: 单个网站发送微信预警和邮件预警(升级改版)
	 * @author cuichx --- 2016-6-12下午6:27:46 
	 * @param code  发送预警的标识码  
	 * @param homeChannelList  首页不更新列表
	 * @param requestUrl 发送微信模板url
	 */
	public void singleSiteUpGrade(String code,List<EarlyDetail>  earlylList,String requestUrl,String scanDate){
		try {
			if(earlylList!=null && earlylList.size()>0){
				String scanTime=DateUtils.formatStandardDateTime(new Date());;
				
				//封装发送邮件的集合
				List<Map<String, Object>> eList=new ArrayList<Map<String,Object>>();
				
				for (EarlyDetail earlyDetail : earlylList) {

					String siteCode=earlyDetail.getSiteCode();
					String siteName="";//栏目名称/网站名称
					String url="";//网址
					
					DatabaseInfoRequest dataRequest=new DatabaseInfoRequest();
					dataRequest.setSiteCode(siteCode);
					List<DatabaseInfo> dataList=databaseInfoServiceImpl.queryList(dataRequest);
					if(dataList!=null && dataList.size()>0){
						if(dataList!=null && dataList.size()>0){
							siteName=dataList.get(0).getName();
							if(StringUtils.isNotEmpty(dataList.get(0).getJumpUrl())){
								url=dataList.get(0).getJumpUrl();
							}else{
								url=dataList.get(0).getUrl();
							}
						}
					}
					
					
					Map<String, Object> map=new HashMap<String, Object>();
					map.put("siteCode", earlyDetail.getSiteCode());
					map.put("siteName", siteName);
					map.put("url", url);
					
					/**
					 * 发送微信预警模板消息
					 */
					//根据网站标识码、绑定状态查询微信绑定账户信息表，获取要接受预警信息的 openId
					AccountBindInfoRequest accRequest=new AccountBindInfoRequest();
					accRequest.setSiteCode(code);
					accRequest.setStatus(AccountBindStatus.ACCOUNT_BIND.getCode());
					accRequest.setPageSize(Integer.MAX_VALUE);
					
					List<AccountBindInfo> accList=accountBindInfoServiceImpl.queryList(accRequest);
					if(accList!=null && accList.size()>0){
						List<String> openIdList=new ArrayList<String>();
						
						if(code.length()==6){//组织单位
							for (int j = 0; j < accList.size(); j++) {
								AccountBindInfo accInfo=accList.get(j);
								/**
								 * 接收分类处理
								 * 如果 门户和站群都接收--直接循环
								 * 如果 门户接收 站群不接收--判断网站是否为门户，只有门户才发送
								 * 如果 门户不接收，站群接收--判断网站非门户，就发送
								 */
								String menCode="";
								DatabaseLinkRequest linkRequest=new DatabaseLinkRequest();
								linkRequest.setOrgSiteCode(code);
								linkRequest.setType(1);
								List<DatabaseLink> linkList=databaseLinkServiceImpl.queryList(linkRequest);
								if(linkList!=null && linkList.size()>0){
									menCode=linkList.get(0).getSiteCode();
									
									if(accInfo.getLocalhostEarlyStatus()==1 && accInfo.getSiteListEarlyStatus()==0){
										if(menCode.equals(earlyDetail.getSiteCode())){
											openIdList.add(accList.get(j).getOpenId());
										}
									}else if(accInfo.getLocalhostEarlyStatus()==0 && accInfo.getSiteListEarlyStatus()==1){
										if(!menCode.equals(earlyDetail.getSiteCode())){
											openIdList.add(accList.get(j).getOpenId());
										}
									}else if(accInfo.getLocalhostEarlyStatus()==1 && accInfo.getSiteListEarlyStatus()==1){
										openIdList.add(accList.get(j).getOpenId());
									}
								}
								//openIdList.add(accList.get(j).getOpenId());
							}
						}else{//填报单位
							for (int j = 0; j < accList.size(); j++) {
								AccountBindInfo accInfo=accList.get(j);
								/**
								 * 只要门户接收 就发送  
								 */
								if(accInfo.getLocalhostEarlyStatus()==1){
									openIdList.add(accList.get(j).getOpenId());
								}
							}
						}

						//获取模板消息
						getSendModelHomeNotUpdate(map,requestUrl,openIdList);
					}
					
					map.put("type", EarlyType.getNameByCode(earlyDetail.getType()));
					map.put("updateGradeType",EarlyUpdateGradeType.getNameByCode(earlyDetail.getUpdateGradeType()));
					map.put("scanDate", scanDate);
					eList.add(map);
				}
				/**
				 * 发送邮件预警消息
				 */
				//根据网站标识码，查询基本信息表，获取联系人邮箱地址
				//获取需要发送的emil
				String email="";
				DatabaseInfoRequest dataRequest=new DatabaseInfoRequest();
				dataRequest.setIsexp(DatabaseInfoType.NORMAL.getCode());//1正常，2例外，3关停
				dataRequest.setEmailReceive2(TrueOrFalseType.TRUE.getCode());//联系人是否接收邮箱（0：否，1：是
				if(code.length()==6){//组织单位
					dataRequest.setSiteCodeLike(code);
					dataRequest.setIsorganizational(1);
				}else{
					dataRequest.setSiteCode(code);
				}
				List<DatabaseInfo>  dataList=databaseInfoServiceImpl.queryList(dataRequest);
				if(dataList!=null && dataList.size()>0){
					email=dataList.get(0).getEmail2();
				}
				if(StringUtils.isNotEmpty(email) && (eList!=null && eList.size()>0)){
					Map<String, Object> modelMap=new HashMap<String, Object>();
					modelMap.put("scanTime", scanTime);
					modelMap.put("editList", eList);
					modelMap.put("newEarlNum", eList.size());
					
					logger.info("homeNotUpdateData code:"+code+"  email:"+email);
					
					boolean resultSend = SendEmail.sendEmailHomeNotUpdate("监测到1次预警信息- 开普云政府网站云监管平台", "upGradeEarly.ftl", modelMap, email);
					if(resultSend){
						logger.info("预警定时发送邮箱成功");
					}else{
						logger.info("预警定时发送邮箱失败");
					}
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	/**--------------------------------------------首页不更新预警（日/次）-------------------------------------------------------*/
	/**
	 * @Description: 首页不更新预警（日/次）--首页连续10天未更新触发预警(收费用户)
	 * @author cuichx --- 2016-6-12上午9:40:11
	 */
	public void homeNotUpdateData(){
		
		try {
			/**
			 *   查询正式合同信息表，获取所有的收费用户（组织机构和填报单位两类）
			 *   	填报单位--直接查询数据库表，判断首页未更新天数是否超过10，如果超过10天，发送预警消息；
			 *   	组织单位--查询database_link表获取该组织单位下面所有的站点信息，
			 *   		         循环遍历每个站点，判断首页未更新天数是否超过10天，如果超过10天，发送预警信息；
			 */	
			//获取所有的收费用户
			Integer[] conStatues={0,1};
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("nowTime", DateUtils.formatStandardDateTime(new Date()));
			map.put("executeStatusArr", conStatues);//合同状态为0-初始化 1-服务中
			map.put("typeFlag",1);//非抽查合同
			map.put("siteCodeLike", "23");
			
			
			List<ContractInfo>  conList=contractInfoServiceImpl.queryContractByMap(map);
			if(conList!=null && conList.size()>0){//收费用户
				//验证微信凭证的access_token是否超时
				String access_token=CommonUtils.getTokenFromServlet();
				String  requestUrl="https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+access_token;
				
				String scanDate=DateUtils.getYesterdayStr();//昨天日期
				
				for (int i = 0; i < conList.size(); i++) {
					String code=conList.get(i).getSiteCode();
					logger.info("homeNotUpdateData ContractInfo code:"+code);
					if(code.length()==10){//填报单位--只发送未更新天数为10天的，后面的就不再发送
						
						SecurityHomeChannelRequest homeChannelRequest=new SecurityHomeChannelRequest();
						homeChannelRequest.setSiteCode(code);
						homeChannelRequest.setScanDate(scanDate);
						homeChannelRequest.setType(1);//类型（1首页，2栏目）
						homeChannelRequest.setNotUpdateDays(10);
						
						List<SecurityHomeChannel>  homeChannelList=securityHomeChannelServiceImpl.queryList(homeChannelRequest);
						if(homeChannelList!=null && homeChannelList.size()>0){
							sendSingleSite(code,homeChannelList, requestUrl);
						}
					}else{//组织单位
						
						/**
						 * 1 获取该组织单位下面的所有的站点
						 * 2 根据查询出来的站点，查询获取未更新天数为10的记录
						 * 		如果存在记录，  做如下操作： 
						 * 			 查询微信绑定账户信息表，发送微信模板消息；
						 * 			查询基本信息表，获取联系人邮箱，发送邮件预警
						 */
						DatabaseLinkRequest linkRequest=new DatabaseLinkRequest();
						linkRequest.setOrgSiteCode(code);
						linkRequest.setPageSize(Integer.MAX_VALUE);
						
						List<DatabaseLink>  linkList=databaseLinkServiceImpl.queryList(linkRequest);
						if(linkList!=null && linkList.size()>0){
							
							SecurityHomeChannelRequest homeChannelRequest=new SecurityHomeChannelRequest();
							homeChannelRequest.setDatabaseLinkList(linkList);
							homeChannelRequest.setScanDate(scanDate);
							homeChannelRequest.setType(1);//类型（1首页，2栏目）
							homeChannelRequest.setNotUpdateDays(10);
							homeChannelRequest.setPageSize(Integer.MAX_VALUE);
							
							List<SecurityHomeChannel>  homeChannelList=securityHomeChannelServiceImpl.queryList(homeChannelRequest);
							if(homeChannelList!=null && homeChannelList.size()>0){
								sendSingleSite(code,homeChannelList, requestUrl);
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
	 * @Description: 单个网站发送微信预警和邮件预警（首页不更新）
	 * @author cuichx --- 2016-6-12下午6:27:46     
	 * @param homeChannelList  首页不更新列表
	 * @param requestUrl 发送微信模板url
	 */
	public void sendSingleSite(String code,List<SecurityHomeChannel>  homeChannelList,String requestUrl){
		try {
			if(homeChannelList!=null && homeChannelList.size()>0){
				String scanTime=DateUtils.formatStandardDateTime(new Date());
				
				//封装发送邮件的集合
				List<Map<String, Object>> homeList=new ArrayList<Map<String,Object>>();
				
				for (SecurityHomeChannel securityHomeChannel : homeChannelList) {
					
					//控制只发送正常网站   例外和关停的不发送
					DatabaseInfoRequest dRequest=new DatabaseInfoRequest();
					dRequest.setSiteCode(securityHomeChannel.getSiteCode());
					dRequest.setIsexp(1);//正常网站
					List<DatabaseInfo> dList=databaseInfoServiceImpl.queryList(dRequest);
					logger.info("siteCode:"+securityHomeChannel.getSiteCode());
					if(dList!=null && dList.size()>0){
						String siteName=securityHomeChannel.getName();//栏目名称/网站名称
						String url=securityHomeChannel.getUrl();//网址
						String modifyDate=securityHomeChannel.getModifyDate();//最后更新日期
						
						Map<String, Object> map=new HashMap<String, Object>();
						map.put("siteCode", securityHomeChannel.getSiteCode());
						map.put("siteName", siteName);
						map.put("url", url);
						map.put("modifyDate", modifyDate);
						
						/**
						 * 发送微信预警模板消息
						 */
						//根据网站标识码、绑定状态查询微信绑定账户信息表，获取要接受预警信息的 openId
						AccountBindInfoRequest accRequest=new AccountBindInfoRequest();
						accRequest.setSiteCode(code);
						accRequest.setStatus(AccountBindStatus.ACCOUNT_BIND.getCode());
						accRequest.setPageSize(Integer.MAX_VALUE);
						
						List<AccountBindInfo> accList=accountBindInfoServiceImpl.queryList(accRequest);
						if(accList!=null && accList.size()>0){
							List<String> openIdList=new ArrayList<String>();
							
							if(code.length()==6){//组织单位
								for (int j = 0; j < accList.size(); j++) {
									AccountBindInfo accInfo=accList.get(j);
									/**
									 * 接收分类处理
									 * 如果 门户和站群都接收--直接循环
									 * 如果 门户接收 站群不接收--判断网站是否为门户，只有门户才发送
									 * 如果 门户不接收，站群接收--判断网站非门户，就发送
									 */
									String menCode="";
									DatabaseLinkRequest linkRequest=new DatabaseLinkRequest();
									linkRequest.setOrgSiteCode(code);
									linkRequest.setType(1);
									List<DatabaseLink> linkList=databaseLinkServiceImpl.queryList(linkRequest);
									if(linkList!=null && linkList.size()>0){
										menCode=linkList.get(0).getSiteCode();
										
										if(accInfo.getLocalhostEarlyStatus()==1 && accInfo.getSiteListEarlyStatus()==0){
											if(menCode.equals(securityHomeChannel.getSiteCode())){
												openIdList.add(accList.get(j).getOpenId());
											}
										}else if(accInfo.getLocalhostEarlyStatus()==0 && accInfo.getSiteListEarlyStatus()==1){
											if(!menCode.equals(securityHomeChannel.getSiteCode())){
												openIdList.add(accList.get(j).getOpenId());
											}
										}else if(accInfo.getLocalhostEarlyStatus()==1 && accInfo.getSiteListEarlyStatus()==1){
											openIdList.add(accList.get(j).getOpenId());
										}
									}
									//openIdList.add(accList.get(j).getOpenId());
								}
							}else{//填报单位
								for (int j = 0; j < accList.size(); j++) {
									AccountBindInfo accInfo=accList.get(j);
									/**
									 * 只要门户接收 就发送  
									 */
									if(accInfo.getLocalhostEarlyStatus()==1){
										openIdList.add(accList.get(j).getOpenId());
									}
								}
							}

							//获取模板消息
							getSendModelHomeNotUpdate(map,requestUrl,openIdList);
						}
						
						map.put("type", "首页超过10天未更新");
						map.put("scanTime", scanTime);
						homeList.add(map);
					}

				}
				/**
				 * 发送邮件预警消息
				 */
				//根据网站标识码，查询基本信息表，获取联系人邮箱地址
				//获取需要发送的emil
				String email="";
				DatabaseInfoRequest dataRequest=new DatabaseInfoRequest();
				dataRequest.setIsexp(DatabaseInfoType.NORMAL.getCode());//1正常，2例外，3关停
				dataRequest.setEmailReceive2(TrueOrFalseType.TRUE.getCode());//联系人是否接收邮箱（0：否，1：是
				if(code.length()==6){//组织单位
					dataRequest.setSiteCodeLike(code);
					dataRequest.setIsorganizational(1);
				}else{
					dataRequest.setSiteCode(code);
				}
				List<DatabaseInfo>  dataList=databaseInfoServiceImpl.queryList(dataRequest);
				if(dataList!=null && dataList.size()>0){
					email=dataList.get(0).getEmail2();
				}
				if(StringUtils.isNotEmpty(email) && (homeList!=null && homeList.size()>0)){
					Map<String, Object> modelMap=new HashMap<String, Object>();
					modelMap.put("scanTime", scanTime);
					modelMap.put("editList", homeList);
					modelMap.put("newEarlNum", homeList.size());
					
					logger.info("homeNotUpdateData code:"+code+"  email:"+email);
					
					boolean resultSend = SendEmail.sendEmailHomeNotUpdate("监测到1次预警信息- 开普云政府网站云监管平台", "yujing_t2.ftl", modelMap, email);
					if(resultSend){
						logger.info("预警定时发送邮箱成功");
					}else{
						logger.info("预警定时发送邮箱失败");
					}
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * @Description: 首页不更新预警--模板消息
	 * @author cuichx --- 2016-5-25下午4:01:13     
	 * @param map
	 * @return
	 */
	public void getSendModelHomeNotUpdate(Map<String,Object> map,String requestUrl,List<String> openIdList){

		if(openIdList!=null && openIdList.size()>0){
			String siteName=(String) map.get("siteName");//网站名称
			String siteCode=(String) map.get("siteCode");//网站标识码
			String url=(String) map.get("url");//网站url
			String modifyDate=(String) map.get("modifyDate");//最后更新日期
			
			String type="首页不更新";
			String desStr="首页超过10天未更新";
			String scanTime=DateUtils.formatStandardDateTime(new Date());
			
			for (int i = 0; i < openIdList.size(); i++) {
				String openId=openIdList.get(i);//客户微信号唯一标识
				String template_id=prop.getProperty("templateEarlyTB");
				
				//创建发送微信的模板对象
				WxSendModel wxSendModel=new WxSendModel(); 
				wxSendModel.setTouser(openId);//微信客户唯一标识
				wxSendModel.setTemplate_id(template_id);//模板编号
				//wxSendModel.setUrl(url);//点击详情跳转url
				wxSendModel.setTopcolor("#000000");//颜色
				
				Map<String, WxSendData> paramMap=new HashMap<String, WxSendData>();
				//标题
				WxSendData first=new WxSendData();
				first.setValue("首页信息不更新预警");
				first.setColor("#000000");
				paramMap.put("first", first);
				//网站名称
				WxSendData keyword1=new WxSendData();
				keyword1.setValue(siteName);
				keyword1.setColor("#173177");
				paramMap.put("keyword1", keyword1);
				
				//网站标识码
				WxSendData keyword2=new WxSendData();
				keyword2.setValue(siteCode);
				keyword2.setColor("#173177");
				paramMap.put("keyword2", keyword2);
				
				//问题类型
				WxSendData keyword3=new WxSendData();
				keyword3.setValue(type);
				keyword3.setColor("#173177");
				paramMap.put("keyword3", keyword3);
				
				//问题描述
				WxSendData keyword4=new WxSendData();
				keyword4.setValue(desStr);
				keyword4.setColor("#173177");
				paramMap.put("keyword4", keyword4);
				
				//问题描述
				WxSendData keyword5=new WxSendData();
				keyword5.setValue(scanTime);
				keyword5.setColor("#173177");
				paramMap.put("keyword5", keyword5);
				
				//问题页面--写到remark中
				WxSendData remark=new WxSendData();
				remark.setValue("最后更新日期： "+modifyDate+"\n"+"url:"+url);
				remark.setColor("#ff0000");
				paramMap.put("remark", remark);
				
				wxSendModel.setData(paramMap);
				
				//发送模板消息
				JSONObject httpRequest = CommonUtils.httpRequest(requestUrl, "POST", JSONObject.fromObject(wxSendModel).toString());
				String errmsg = (String) httpRequest.get("errmsg");
				logger.info("errmsg:"+errmsg+"  siteCode:"+siteCode+" openId:"+openId);
			}
		}
	}

	/**--------------------------------------------免费用户发送日报（日/次）-------------------------------------------------------*/
	/**
	 * 查询绑定账户信息表，获取免费用户，做如下处理
	 * 
	 * 	组织单位  获取该组织单位下面的所有的站点信息,通过获取的站点信息查询当日首页连不通情况
	 *  
	 *  
	 *  填报单位  直接查询站点信息表
	 * 
	 */
	public void getConnDailyData(){
		
		//查询微信绑定账户信息表，获取绑定的免费用户
		Map<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("status", AccountBindStatus.ACCOUNT_BIND.getCode());//绑定账户状态
		paramMap.put("isCustomer", TrueOrFalseType.FALSE.getCode());//免费用户
		List<AccountBindInfo>  accountList=accountBindInfoServiceImpl.queryListByMap(paramMap);
		if(accountList!=null && accountList.size()>0){
			//验证微信凭证的access_token是否超时
			String access_token=CommonUtils.getTokenFromServlet();
			String  requestUrl="https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+access_token;
			//获取昨天日期
			String scanDate=DateUtils.getYesterdayStr();
			
			//遍历免费的微信绑定账户
			for (AccountBindInfo accountBindInfo : accountList) {
				String bindSiteCode=accountBindInfo.getSiteCode();
				
				if(bindSiteCode.length()==10){//填报单位绑定账户
					//查询连不通统计表，获取昨天连不通次数
					ConnectionAllRequest connRequest=new ConnectionAllRequest();
					connRequest.setSiteCode(bindSiteCode);
					connRequest.setScanDate(scanDate);
					connRequest.setType(ConnectionType.HOME.getCode());
					connRequest.setErrorNum(0);//网站连不通个数
					
					List<ConnectionAll> connList=connectionAllServiceImpl.queryList(connRequest);
					if(connList!=null && connList.size()>0){
						
						ConnectionAll connAll=connList.get(0);
						//根据网站标识码查询基本信息表，获取网站名称
						
						DatabaseInfoRequest dataRequest=new DatabaseInfoRequest();
						dataRequest.setSiteCode(bindSiteCode);
						
						String siteName="";//网站名称
						List<DatabaseInfo> dataList=databaseInfoServiceImpl.queryList(dataRequest);
						if(dataList!=null && dataList.size()>0){
							siteName=dataList.get(0).getName();
						}
						
						Integer errorNum=connAll.getErrorNum();//超时次数
						
						Map<String, Object> map=new HashMap<String, Object>();
						map.put("siteCode", bindSiteCode);
						map.put("siteName", siteName);
						map.put("errorNum", errorNum);
						map.put("scanDate", scanDate);
						
						//获取绑定该填报单位的微信用户
						AccountBindInfoRequest accountRequest=new AccountBindInfoRequest();
						accountRequest.setSiteCode(bindSiteCode);//网站标识码
						accountRequest.setStatus(AccountBindStatus.ACCOUNT_BIND.getCode());//
						accountRequest.setIsCustomer(TrueOrFalseType.FALSE.getCode());//免费用户
						accountRequest.setPageSize(Integer.MAX_VALUE);
						
						List<AccountBindInfo>  openIdList=accountBindInfoServiceImpl.queryList(accountRequest);
						getSendHome(map,requestUrl,openIdList);
					}
				}else{//组织单位绑定账户
					
					//查询基本信息关联表  获取该组织单位下面的所有的站点信息
					DatabaseLinkRequest linkRequest=new DatabaseLinkRequest();
					linkRequest.setOrgSiteCode(bindSiteCode);
					List<DatabaseLink> linkList=databaseLinkServiceImpl.queryList(linkRequest);
					
					if(linkList!=null && linkList.size()>0){
						//查询连通性统计表  ，获取连不通总次数
						ConnectionAllRequest connRequest=new ConnectionAllRequest();
						connRequest.setDataLinkList(linkList);
						connRequest.setScanDate(scanDate);
						connRequest.setType(ConnectionType.HOME.getCode());
						connRequest.setErrorNum(0);//网站连不通个数
						
						Integer errorNum=(Integer)connectionAllServiceImpl.queryErrorNumSum(connRequest);
						
						//获取组织单位名称
						String orgSiteName="";
						DatabaseOrgInfoRequest dataOrgRequest=new DatabaseOrgInfoRequest();
						dataOrgRequest.setStieCode(bindSiteCode);
						
						List<DatabaseOrgInfo> dataOrgList=databaseOrgInfoServiceImpl.queryList(dataOrgRequest);
						if(dataOrgList!=null && dataOrgList.size()>0){
							orgSiteName=dataOrgList.get(0).getName();
						}
						Map<String, Object> map=new HashMap<String, Object>();
						map.put("siteCode", bindSiteCode);
						map.put("siteName", orgSiteName);
						map.put("errorNum", errorNum);
						map.put("scanDate", scanDate);
						
						//获取绑定该组织单位的所有
						//获取绑定该填报单位的微信用户
						AccountBindInfoRequest accountRequest=new AccountBindInfoRequest();
						accountRequest.setSiteCode(bindSiteCode);//网站标识码
						accountRequest.setStatus(AccountBindStatus.ACCOUNT_BIND.getCode());//
						accountRequest.setIsCustomer(TrueOrFalseType.FALSE.getCode());//免费用户
						accountRequest.setPageSize(Integer.MAX_VALUE);
						
						List<AccountBindInfo>  openIdList=accountBindInfoServiceImpl.queryList(accountRequest);
						getSendHome(map,requestUrl,openIdList);
					}
				}
			}
		}
	}
	
	/**
	 * @Description: 发送模板消息
	 * @author cuichx --- 2016-5-25下午3:42:31     
	 * @param map
	 */
	public void getSendHome(Map<String, Object> map,String requestUrl,List<AccountBindInfo>  openIdList){
		
		if(openIdList!=null && openIdList.size()>0){
			
			for (AccountBindInfo accountBindInfo : openIdList) {
				
				if(accountBindInfo.getSiteCode().length()==6){//站群首页连不通日报
					//封装模板消息
					//封装模板消息
					Map<String, Object> modelMap=new HashMap<String, Object>();
					modelMap.put("orgSiteCode", map.get("siteCode"));
					modelMap.put("orgSiteName", map.get("siteName"));
					modelMap.put("errorNum", map.get("errorNum"));
					modelMap.put("scanDate", map.get("scanDate"));
					modelMap.put("openId", accountBindInfo.getOpenId());
					
					//获取模板消息
					String jsonStr=getSendModelOrg(modelMap);
					//发送模板消息
					JSONObject httpRequest = CommonUtils.httpRequest(requestUrl, "POST", jsonStr);
					String errmsg = (String) httpRequest.get("errmsg");
					logger.info("errmsg:"+errmsg);
				}else{//网站首页连不通日报
					
					//封装模板消息
					Map<String, Object> modelMap=new HashMap<String, Object>();
					modelMap.put("siteCode", map.get("siteCode"));
					modelMap.put("siteName", map.get("siteName"));
					modelMap.put("errorNum", map.get("errorNum"));
					modelMap.put("scanDate", map.get("scanDate"));
					modelMap.put("openId", accountBindInfo.getOpenId());
					
					//获取模板消息
					String jsonStr=getSendModel(modelMap);
					
					//发送模板消息
					JSONObject httpRequest = CommonUtils.httpRequest(requestUrl, "POST", jsonStr);
					String errmsg = (String) httpRequest.get("errmsg");
					logger.info("errmsg:"+errmsg);
				}
			}
		}
	}
	
	
	/**
	 * @Description: 网站首页连不通日报模板
	 * @author cuichx --- 2016-5-25下午4:01:13     
	 * @param map
	 * @return
	 */
	@SuppressWarnings("static-access")
	public String getSendModel(Map<String,Object> map){
		
		String openId=(String) map.get("openId");//客户微信号唯一标识
		String siteName=(String) map.get("siteName");//网站名称
		String siteCode=(String) map.get("siteCode");//网站标识码
		String errorNum=String.valueOf( map.get("errorNum"));//连不通个数
		String scanDate=(String) map.get("scanDate");//统计日期
		
		//String requestUrl=prop.getProperty("requestUrl");//点击详情链接使用
		//String url=requestUrl+"/token_earlyDetailIndex.action?openId="+openId;
		String template_id=prop.getProperty("templateDailyTB");
		//创建发送微信的模板对象
		WxSendModel wxSendModel=new WxSendModel(); 
		wxSendModel.setTouser(openId);//微信客户唯一标识
		wxSendModel.setTemplate_id(template_id);//模板编号
		//wxSendModel.setUrl(url);//点击详情跳转url
		wxSendModel.setTopcolor("#000000");//颜色
		
		Map<String, WxSendData> paramMap=new HashMap<String, WxSendData>();
		//标题
		WxSendData first=new WxSendData();
		first.setValue("网站首页连不通日报");
		first.setColor("#000000");
		paramMap.put("first", first);
		//网站名称
		WxSendData keyword1=new WxSendData();
		keyword1.setValue(siteName);
		keyword1.setColor("#173177");
		paramMap.put("keyword1", keyword1);
		
		//标识码
		WxSendData keyword2=new WxSendData();
		keyword2.setValue(siteCode);
		keyword2.setColor("#173177");
		paramMap.put("keyword2", keyword2);
		
		//问题类型
		WxSendData keyword3=new WxSendData();
		keyword3.setValue("首页连不通");
		keyword3.setColor("#173177");
		paramMap.put("keyword3", keyword3);
		
		//统计日期
		WxSendData keyword4=new WxSendData();
		keyword4.setValue(scanDate);
		keyword4.setColor("#173177");
		paramMap.put("keyword4", keyword4);
		
		//首页不连通个数
		WxSendData keyword5=new WxSendData();
		keyword5.setValue(errorNum);
		keyword5.setColor("#ff0000");
		paramMap.put("keyword5", keyword5);
		
		//问题页面--写到remark中
/*		WxSendData remark=new WxSendData();
		remark.setValue("首页连不通个数："+errorNum+"\n");
		remark.setColor("#ff0000");
		paramMap.put("remark", remark);*/
		
		wxSendModel.setData(paramMap);
		return JSONObject.fromObject(wxSendModel).toString();
	} 
	/**
	 * @Description: 站群首页连不通日报模板
	 * @author cuichx --- 2016-5-25下午4:01:13     
	 * @param map
	 * @return
	 */
	@SuppressWarnings("static-access")
	public String getSendModelOrg(Map<String,Object> map){
		
		String openId=(String) map.get("openId");//客户微信号唯一标识
		String siteName=(String) map.get("orgSiteName");//网站名称
		String siteCode=(String) map.get("orgSiteCode");//网站标识码
		String errorNum=String.valueOf(map.get("errorNum")) ;//连不通个数
		String scanDate=(String) map.get("scanDate");//统计日期
		
		
		//String requestUrl=prop.getProperty("requestUrl");//点击详情链接使用
		//String url=requestUrl+"/token_earlyDetailIndex.action?openId="+openId;
		String template_id=prop.getProperty("templateDailyORG");
		//创建发送微信的模板对象
		WxSendModel wxSendModel=new WxSendModel(); 
		wxSendModel.setTouser(openId);//微信客户唯一标识
		wxSendModel.setTemplate_id(template_id);//模板编号
		//wxSendModel.setUrl(url);//点击详情跳转url
		wxSendModel.setTopcolor("#000000");//颜色
		
		Map<String, WxSendData> paramMap=new HashMap<String, WxSendData>();
		//标题
		WxSendData first=new WxSendData();
		first.setValue("站群首页连不通日报");
		first.setColor("#000000");
		paramMap.put("first", first);
		//组织单位名称
		WxSendData keyword1=new WxSendData();
		keyword1.setValue(siteName);
		keyword1.setColor("#173177");
		paramMap.put("keyword1", keyword1);
		
		//组织单位编码
		WxSendData keyword2=new WxSendData();
		keyword2.setValue(siteCode);
		keyword2.setColor("#173177");
		paramMap.put("keyword2", keyword2);
		
		//首页连不通个数
		WxSendData keyword3=new WxSendData();
		keyword3.setValue(errorNum);
		keyword3.setColor("#ff0000");
		paramMap.put("keyword3", keyword3);
		
		//统计日期
		WxSendData keyword4=new WxSendData();
		keyword4.setValue(scanDate);
		keyword4.setColor("#173177");
		paramMap.put("keyword4", keyword4);

		
		//问题页面--写到remark中
/*		WxSendData remark=new WxSendData();
		remark.setValue("统计日期:"+scanDate+"\n"+"首页连不通个数："+errorNum+"\n");
		remark.setColor("#ff0000");
		paramMap.put("remark", remark);*/
		
		
		wxSendModel.setData(paramMap);
		return JSONObject.fromObject(wxSendModel).toString();
	} 

}
