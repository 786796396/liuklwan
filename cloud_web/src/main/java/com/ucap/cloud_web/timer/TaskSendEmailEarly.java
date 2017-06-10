package com.ucap.cloud_web.timer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.publics.util.utils.DateUtils;
import com.publics.util.utils.StringUtils;
import com.ucap.cloud_web.UrlAdapterVar;
import com.ucap.cloud_web.constant.EarlySendType;
import com.ucap.cloud_web.dto.ContractInfoRequest;
import com.ucap.cloud_web.dto.DatabaseInfoRequest;
import com.ucap.cloud_web.dto.DatabaseLinkRequest;
import com.ucap.cloud_web.dto.EarlyDetailRequest;
import com.ucap.cloud_web.dto.EarlyInfoRequest;
import com.ucap.cloud_web.entity.ContractInfo;
import com.ucap.cloud_web.entity.DatabaseInfo;
import com.ucap.cloud_web.entity.DatabaseLink;
import com.ucap.cloud_web.entity.EarlyDetail;
import com.ucap.cloud_web.service.IContractInfoService;
import com.ucap.cloud_web.service.IDatabaseInfoService;
import com.ucap.cloud_web.service.IDatabaseLinkService;
import com.ucap.cloud_web.service.IEarlyDetailService;
import com.ucap.cloud_web.util.SendEmail;

@Service("taskSendEmailEarly")
public class TaskSendEmailEarly {

	private static Log logger =  LogFactory.getLog(TaskSendEmailEarly.class);
	@Autowired
	private IEarlyDetailService earlyDetailServiceImpl;
	
	@Autowired
	private IDatabaseInfoService databaseInfoServiceImpl;
	
	@Autowired
	private UrlAdapterVar urlAdapterVar;
	
	@Autowired
	private IDatabaseLinkService databaseLinkServiceImpl;
	
	@Autowired
	private IContractInfoService contractInfoServiceImpl;
	
	/**
	 * @Description: 获取DatabaseInfo
	 * @author sunjiang --- 2016-3-17下午3:53:51
	 */
	public DatabaseInfo getDatabaseInfo(String siteCode){
		DatabaseInfo databaseInfo = new DatabaseInfo();
		try {
			siteCode = siteCode.trim();
			DatabaseInfoRequest databaseInfoRequest = new DatabaseInfoRequest();
			databaseInfoRequest.setSiteCode(siteCode);
			databaseInfoRequest.setPageSize(Integer.MAX_VALUE);
			
			List<DatabaseInfo> queryList = databaseInfoServiceImpl.queryList(databaseInfoRequest);
			if(!CollectionUtils.isEmpty(queryList)){
				
				databaseInfo = queryList.get(0);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return databaseInfo;
	}
	
	/**
	 * @Description:预警发送邮件 
	 * @author sunjiang --- 2016-3-17下午3:36:30
	 */
	public void sendEmailByEarly(){
		
		try {
			
			EarlyDetailRequest earlyDetailRequest = new EarlyDetailRequest();
			earlyDetailRequest.setScanTime(DateUtils.getYesterdayStr());
			earlyDetailRequest.setTypes("1,6");
			earlyDetailRequest.setGroupBy("site_code");
			earlyDetailRequest.setSendEmailState(0);
			earlyDetailRequest.setPageSize(Integer.MAX_VALUE);
			
			List<EarlyDetail> detailList = earlyDetailServiceImpl.queryList(earlyDetailRequest);
			
			if(!CollectionUtils.isEmpty(detailList)){
				
				
				for (EarlyDetail earlyDetail : detailList) {
					//显示的详情
					ArrayList<Object> datalist = new ArrayList<Object>();
					Map<Object, Object> map = new HashMap<Object, Object>();
					String webPath = urlAdapterVar.getWebPath();
					
					String siteCode = earlyDetail.getSiteCode();
					//获取需要发送的email
					String email= getSendDatabaseInfo(siteCode);
					
					if(StringUtils.isEmpty(email)){
						continue;
					}
					
					DatabaseInfoRequest dataRequest=new DatabaseInfoRequest();
					dataRequest.setSiteCode(siteCode);
					
					List<DatabaseInfo> dataInfoList=databaseInfoServiceImpl.queryList(dataRequest);
					if(dataInfoList!=null && dataInfoList.size()>0){
						
						DatabaseInfo databaseInfo=dataInfoList.get(0);
						String siteName = databaseInfo.getName();
						
						String director = databaseInfo.getDirector();
						String url = "";
						
						String jumpUrl = databaseInfo.getJumpUrl();
						if(StringUtils.isNotEmpty(jumpUrl)){
							url = jumpUrl;
						}else {
							url = databaseInfo.getUrl();
						}
						earlyDetailRequest.setGroupBy("type");
						earlyDetailRequest.setSiteCode(siteCode);
						List<EarlyDetail> detailTypeList = earlyDetailServiceImpl.queryList(earlyDetailRequest);
						
						int sumCount = 0;
						
						if(!CollectionUtils.isEmpty(detailTypeList)){
							
							for (EarlyDetail earlyDetailNum : detailTypeList) {
								
								
								int type = earlyDetailNum.getType();
								//类型
								String typeStr = "";
								
								for (EarlySendType earlyType:EarlySendType.values()) {
									if(earlyType.getCode()==type){
										typeStr = earlyType.getName();
									}
								}
								
								EarlyDetailRequest earlyRequest =new EarlyDetailRequest();
								earlyRequest.setPageSize(Integer.MAX_VALUE);
								earlyRequest.setScanTime(DateUtils.getYesterdayStr());
								earlyRequest.setSiteCode(siteCode);
								earlyRequest.setType(type);
								
								//个数
								int count = earlyDetailServiceImpl.queryCount(earlyRequest);
								
								
								sumCount = sumCount+count ;
								
								
								EarlyInfoRequest early = new EarlyInfoRequest();
								
								early.setEarlyType(typeStr);
								early.setEarlyNum(count);
								datalist.add(early);
								
								
								
								
								earlyDetailNum.setSendEmailState(1);
								earlyDetailNum.setSendEmailTime(DateUtils.getNow());
								earlyDetailServiceImpl.update(earlyDetailNum);
								
								
							}
							
						}
						

						
						map.put("url", url);
						map.put("siteCode", siteCode);
						map.put("siteName", siteName);
						map.put("director", director);
						map.put("webPath", webPath);
						map.put("nowTime", DateUtils.formatDate(DateUtils.getYesterdaytime()));
						//预警个数
						map.put("newEarlNum", sumCount);
						map.put("edList", datalist);
						
						boolean resultSend = SendEmail.sendEmail("监测到"+sumCount+"次预警信息- 开普云政府网站云监管平台", "yujing.ftl", map, email);
//								boolean resultSend = SendEmail.sendEmail("监测到"+edList.size()+"次预警信息- 开普云政府网站云监管平台", "yujing.ftl", map, "fuguoqing@163.com");
						if(resultSend){
							
							logger.error("预警定时发送邮箱成功");
						}else{
							
							logger.error("预警定时发送邮箱失败");
							
						}
					}

						
				}
				
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * @Description: 获取需要发送预警的email
	 * @author sunjiang --- 2016-3-29下午4:19:38     
	 * @return
	 */
	public String getSendDatabaseInfo(String siteCode){
		
		siteCode = siteCode.trim();
		
		String email="";
		
		DatabaseInfoRequest databaseInfoRequest = new DatabaseInfoRequest();
		
		try {
			
			ContractInfoRequest cont = new ContractInfoRequest();
			cont.setNowTime(DateUtils.getYesterdayStr());
			cont.setSiteCode(siteCode);
			List<ContractInfo> contractlist = contractInfoServiceImpl.queryList(cont);
			
			//判断是填报单位买的单，还是组织单位买的，不为空则为填报单位买的单
			if(CollectionUtils.isEmpty(contractlist)){
				DatabaseLinkRequest databaseLinkRequest = new DatabaseLinkRequest();
				databaseLinkRequest.setPageSize(Integer.MAX_VALUE);
				databaseLinkRequest.setSiteCode(siteCode);
				List<DatabaseLink> queryList = databaseLinkServiceImpl.queryList(databaseLinkRequest);
				
				ContractInfoRequest contractInfoRequest = new ContractInfoRequest();
				contractInfoRequest.setIdsOrgSiteCode(queryList);
				contractInfoRequest.setNowTime(DateUtils.getYesterdayStr());
				List<ContractInfo> contractInfoList = contractInfoServiceImpl.queryList(contractInfoRequest);
				
				
				
				String xianSiteCode = "0";
				String shiSiteCode = "0";
				String shengSiteCode = "0";
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
						//省级
						}else{
							shengSiteCode = orgSiteCode;
						}
						
					}
				}
				if(!xianSiteCode.equals("0")){
					siteCode = xianSiteCode;
				}else if(!shiSiteCode.equals("0")){
					siteCode = shiSiteCode;
				}else{
					siteCode = shengSiteCode;
				}
				
				databaseInfoRequest.setIsorganizational(1);
			}else{
				
				siteCode = contractlist.get(0).getSiteCode();
				
				
			}
			
			databaseInfoRequest.setSiteCodeLike(siteCode);
			databaseInfoRequest.setEmailReceive2(1);//联系人是否接收邮箱（0：否，1：是）	
			
			if(siteCode.length()==6){//组织单位-获取门户网站的邮箱
				databaseInfoRequest.setIsorganizational(1);
			}
			List<DatabaseInfo> databaseList = databaseInfoServiceImpl.queryList(databaseInfoRequest);
			if(!CollectionUtils.isEmpty(databaseList)){
				
				email = databaseList.get(0).getEmail();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return email;
	}
}
