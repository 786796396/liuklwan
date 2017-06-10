package com.ucap.cloud_web.shiro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.publics.util.page.QueryOrder;
import com.publics.util.page.QueryOrderType;
import com.publics.util.utils.DateUtils;
import com.publics.util.utils.StringUtils;
import com.ucap.cloud_web.constant.DatabaseLinkType;
import com.ucap.cloud_web.constant.UserType;
import com.ucap.cloud_web.dto.ContractInfoRequest;
import com.ucap.cloud_web.dto.DatabaseOrgInfoRequest;
import com.ucap.cloud_web.dto.DatabaseTreeInfoRequest;
import com.ucap.cloud_web.dto.MonitorTaskSilentRequest;
import com.ucap.cloud_web.dto.PaProjectRequest;
import com.ucap.cloud_web.dto.PaTaskRequest;
import com.ucap.cloud_web.dto.ReportWordResultRequest;
import com.ucap.cloud_web.dto.ServicePeriodRequest;
import com.ucap.cloud_web.dto.SpotCheckInfoRequest;
import com.ucap.cloud_web.dto.SpotCheckNoticeRequest;
import com.ucap.cloud_web.dto.SpotCheckResultRequest;
import com.ucap.cloud_web.dto.YunOpenInfoRequest;
import com.ucap.cloud_web.dto.YunOpenStateRequest;
import com.ucap.cloud_web.entity.ContractInfo;
import com.ucap.cloud_web.entity.DatabaseInfo;
import com.ucap.cloud_web.entity.DatabaseOrgInfo;
import com.ucap.cloud_web.entity.DatabaseTreeInfo;
import com.ucap.cloud_web.entity.MonitorTaskSilent;
import com.ucap.cloud_web.entity.PaTask;
import com.ucap.cloud_web.entity.ReportWordResult;
import com.ucap.cloud_web.entity.ServicePeriod;
import com.ucap.cloud_web.entity.SpotCheckInfo;
import com.ucap.cloud_web.entity.YunOpenInfo;
import com.ucap.cloud_web.entity.YunOpenState;
import com.ucap.cloud_web.service.IContractInfoService;
import com.ucap.cloud_web.service.IDatabaseInfoService;
import com.ucap.cloud_web.service.IDatabaseOrgInfoService;
import com.ucap.cloud_web.service.IDatabaseTreeInfoService;
import com.ucap.cloud_web.service.IMonitorTaskSilentService;
import com.ucap.cloud_web.service.IPaProjectService;
import com.ucap.cloud_web.service.IPaTaskService;
import com.ucap.cloud_web.service.IReportWordResultService;
import com.ucap.cloud_web.service.IServicePeriodService;
import com.ucap.cloud_web.service.ISpotCheckInfoService;
import com.ucap.cloud_web.service.ISpotCheckNoticeService;
import com.ucap.cloud_web.service.ISpotCheckResultService;
import com.ucap.cloud_web.service.IYunOpenInfoService;
import com.ucap.cloud_web.service.IYunOpenStateService;
import com.ucap.cloud_web.service.exception.IncorrectCaptchaException;

/**
 * Description:自定义Realm
 * 
 * @Package：com.erp.shiro
 * @Title: MyShiroRealm
 * @Company: 开普互联
 * @author：lixuxiang
 * @date：2015-9-19上午11:09:11
 * @version V1.0
 */

public class MyShiroRealm extends AuthorizingRealm {

	@Autowired
	private IDatabaseInfoService databaseInfoServiceImpl;
	@Autowired
	private ISpotCheckInfoService spotCheckInfoServiceImpl;
	@Autowired
	private IReportWordResultService reportWordResultServiceImpl;
	@Autowired
	private IDatabaseOrgInfoService databaseOrgInfoServiceImpl;
	@Autowired
	private IContractInfoService contractInfoServiceImpl;
	
	@Autowired
	private IDatabaseTreeInfoService databaseTreeInfoServiceImpl;
	@Autowired
	private ISpotCheckResultService spotCheckResultServiceImpl;
	@Autowired
	private IServicePeriodService servicePeriodServiceImpl;
	@Autowired
	private ISpotCheckNoticeService spotCheckNoticeServiceImpl;
	@Autowired
	private IPaProjectService paProjectServiceImpl;
	@Autowired
	private IPaTaskService paTaskServiceImpl;
	@Autowired
	private IMonitorTaskSilentService monitorTaskSilentServiceImpl;
	@Autowired
	private IYunOpenStateService yunOpenStateServiceImpl;
	
	
	/**
	 * 授权信息权限验证
	 */
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		return null;
	}

	/**
	 * 认证信息登陆验证
	 */
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		Subject subject = SecurityUtils.getSubject();
		
		ShiroUser shiroUser = new ShiroUser();
		shiroUser.setType(2);
		CaptchaUsernamePasswordToken token = (CaptchaUsernamePasswordToken) authcToken;
		String siteCode = token.getUsername();
		int userType = UserType.TYPE_ORDINARY.getCode();
		
		boolean bool = true;
//		int count = 0;
//		if(subject.getSession().getAttribute(Constants.KAPTCHA_SESSION) != null){
//			count = (Integer)subject.getSession().getAttribute(Constants.KAPTCHA_SESSION);				
//		}
//		
//		if(count>1){
//			bool = doCaptchaValidate(token);
//		}
//		count++;
//		subject.getSession().setAttribute(Constants.KAPTCHA_SESSION,count);
		
		if (siteCode != null && !"".equals(siteCode) && bool) {
			DatabaseInfo info = new DatabaseInfo();
			int isOrgCost=0;//组织机构登陆默认免费
			int iscost=0;//填报单位登陆默认免费
			int iscostOwn=0;//填报单位是否自己买单  
			int isSafetyService = 1;//  iscost = 1 代表检测中 ；  2 已经过期  0 未购买过
			int isOrgSafetyService = 1;//  isOrgCost = 1 代表检测中 ；  2 已经过期  0 未购买过
			int isHasMap=0;//0 没有地图 1 有地图
			int guideState = 0; //新菜单页面引导状态
			try { 
				String vcode = String.valueOf(token.getPassword());
//				if("bm0100".equals(siteCode)){
//					
//					
//					int checkIsCostbyOrg = checkIsCostbyOrg(siteCode);
//					info.setIscost(checkIsCostbyOrg);
//					
//					
//					info.setSiteCode(siteCode);
//					info.setVcode(vcode);
//					info.setName("国务院办公厅");
//					userType = judgeUserType(siteCode);
//					
//					shiroUser = getCountWebsiteInfo(siteCode);
//					
//				}else 
				
			
				if(siteCode.length()<=6){//组织单位
					//判断组织单位是否买单且有效
					int checkIsCostbyOrg = checkIsCostbyOrg(siteCode);
					isHasMap=getMap(siteCode);
					isOrgCost=checkIsCostbyOrg;
					isOrgSafetyService = checkOrgUsedSafety(siteCode);
					try {
/*						String param = "{\"email\":\""+siteCode+"\",\"pass\":\""+vcode+"\"}";
					
						String temp = post(urlAdapterVar.getLoginHttpPostUrl(),param);
						System.out.println(">>>>>>>>>>"+temp);
						if(temp.indexOf("true")>-1){
							JSONObject json = new JSONObject(temp);
							
							info.setSiteCode(siteCode);
							info.setVcode(vcode);
							info.setName(json.getString("name"));
							userType = judgeUserType(siteCode);
							
							shiroUser = getCountWebsiteInfo(siteCode);
							
							info.setIsorganizational(1);
							
							addDatabaseOrgInfo(info);
							
						}
						System.out.println("############"+temp);
						*/
						//组织单位登录时，账号和密码验证
						DatabaseOrgInfoRequest dataOrgrRequest=new DatabaseOrgInfoRequest();
						dataOrgrRequest.setStieCode(siteCode);
						dataOrgrRequest.setVcode(vcode);
						List<DatabaseOrgInfo> dataOrgList=databaseOrgInfoServiceImpl.queryList(dataOrgrRequest);
						if(dataOrgList!=null && dataOrgList.size()>0){
							DatabaseOrgInfo dataOrgInfo=dataOrgList.get(0);
							
							info.setSiteCode(siteCode);
							info.setVcode(vcode);
							info.setName(dataOrgInfo.getName());
							userType = judgeUserType(siteCode);//组织单位级别
							
							shiroUser = getCountWebsiteInfo(siteCode);
							
							info.setIsorganizational(1);
							
							guideState = dataOrgInfo.getGuideState();
						}
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				} else {//填报单位
//					 根据用户名查找用户是否存在
					info = databaseInfoServiceImpl.getUsers(siteCode);
					userType = UserType.TYPE_ORDINARY.getCode();
					// 账号不存在
					if (info == null) {
						throw new UnknownAccountException();
					}else{
						guideState = info.getGuideState();
					}
					// 填报单位  判断  是否有未启动的  任务；
					PaTaskRequest pRequest = new PaTaskRequest();
					pRequest.setSiteCode(siteCode);
					List<PaTask> list = paTaskServiceImpl.queryJoinTarget(pRequest);
					shiroUser.setPaTargetCount(list.size());
					/**
					 * 判断填报单位是免费用户还是收费用户
					 * 	逻辑处理  
					 * 		通过10位网站标识码，查询合同信息表  
					 * 			一、如果存在记录，说明是填报单位自己买单（iscost=1）
					 * 			二、如果不存在记录，再判断其组织单位是否买单且允许该组织单位下面的填报单位可以查看详情
					 * 				根据10位填报单位，先查询database_link表，获取对应的所有的组织单位编码；
					 * 				通过获取的组织单位编码集合和	允许该组织单位下面的填报单位可以查看详情 两个条件查询合同信息表；
					 * 					如果存在记录  iscost置为1 否则为0
					 */
					
					List<ContractInfo>  contractList=getContractInfoList(siteCode,DateUtils.formatStandardDate(new Date()),null);
					if(contractList !=null && contractList.size()>0){//填报单位自己买单
						iscost=1;
						iscostOwn=1;
					}else{//组织单位买单
						
						//查询database_linK表，获取该填报单位对应的所有的组织单位
						DatabaseTreeInfoRequest databaseTreeInfoRequest=new DatabaseTreeInfoRequest();
						databaseTreeInfoRequest.setSiteCode(siteCode);
						databaseTreeInfoRequest.setPageSize(Integer.MAX_VALUE);
						List<DatabaseTreeInfo>	databaseTreeList=databaseTreeInfoServiceImpl.queryList(databaseTreeInfoRequest);
						if(databaseTreeList!=null && databaseTreeList.size()>0){
							//判断是否存在组织机构  且该组织机构允许填报单位查看所有的详细信息
							List<ContractInfo> conList=getContractInfoList(null, DateUtils.formatStandardDate(new Date()), databaseTreeList);
							if(conList!=null && conList.size()>0){
								iscost=1;
							}
						}
					}
					/**
					 * 判断填报单位是 安全服务运行中  还是 已过期  没有购买过
					 * 	逻辑处理  
					 * 		通过10位网站标识码，查询合同信息表  
					 * 			一、如果存在记录，且合同在运行中，说明是填报单位自己买单 或者 组织单位购买下级可以查看 （isSafetyService=1）
					 * 			二、如果 没有在执行中，则 查看是否购买过；
					 * 			三、未购买过
					 */
//					info.setIscost(info.getIscost());
//					addUserLog(info);
						// 判断是否是  购买过  还是   为开通过
					isSafetyService = checkUsedSafety(siteCode);
				}
				ReportWordResultRequest reportWordResultRequest=new ReportWordResultRequest();
				reportWordResultRequest.setSiteCode(siteCode);
				List<QueryOrder> querySiteList=new ArrayList<QueryOrder>();
				QueryOrder siteQueryOrder=new QueryOrder("create_time",QueryOrderType.DESC);
				querySiteList.add(siteQueryOrder);
				reportWordResultRequest.setQueryOrderList(querySiteList);
				
				List<ReportWordResult> reportList = reportWordResultServiceImpl.queryList(reportWordResultRequest);
				if(reportList != null && reportList.size()>0){
					ReportWordResult reportResult=reportList.get(0);
					shiroUser.setPdfUrl(reportResult.getPdfUrl());
				}
				
//				/**判断是否是购买用户*/
//				CustomerInfo customerInfo = customerInfoServiceImpl.findByCustomerCode(siteCode);
//				if(customerInfo == null){
//					shiroUser.setType(1);
//				}
				
				/**当前购买用户的剩余抽查次数*/
				SpotCheckInfoRequest  spotCheckInfoRequest = new SpotCheckInfoRequest();
				spotCheckInfoRequest.setSiteCode(siteCode);
				ContractInfoRequest conRequest=new ContractInfoRequest();
				conRequest.setSiteCode(siteCode);
				conRequest.setTypeFlag(1);
				List<ContractInfo> conList = contractInfoServiceImpl.queryList(conRequest);
				if(conList!=null && conList.size()>0){
					
					/**
					 * 判断是否有正式合同、临时合同，供全面监测使用，1可显示
					 * 		全面检测      只要存在正式合同或者临时合同即可   --无论是有效的还是历史的
					 */
					ServicePeriodRequest servicePeriodRequest = new ServicePeriodRequest();
					servicePeriodRequest.setContractList(conList);
					List<ServicePeriod> servicePeriodLists = servicePeriodServiceImpl.queryList(servicePeriodRequest);
					if(servicePeriodLists != null && servicePeriodLists.size()>0){
						shiroUser.setCanSeeMonitor(1);
					}else{
						shiroUser.setCanSeeMonitor(0);
					}
				}
				
				/**
				 * 抽查   需要展示历史抽查   以往是否有正式或临时合同
				 */
				Integer[] conStatues={0,1,2};
				conRequest.setExecuteStatusArr(conStatues);//合同状态为0-初始化 1-服务中
				//排序
				List<QueryOrder> queryOrderList = new ArrayList<QueryOrder>();
				QueryOrder siteQueryOrders = new QueryOrder("contract_end_time",QueryOrderType.DESC);
				queryOrderList.add(siteQueryOrders);
				conRequest.setQueryOrderList(queryOrderList);
				
				conList = contractInfoServiceImpl.queryList(conRequest);
				int conListSize = conList.size();//当当前合同conList=0时，判断以往是否有正式或临时合同
				if(conListSize>0){
					shiroUser.setCanSeeSpot(1);
				}
				/**
				 * 查询当前有效的正式合同或者临时合同
				 */
				conRequest.setNowTime(DateUtils.formatStandardDateTime(new Date()));
				conList = contractInfoServiceImpl.queryList(conRequest);
				int spotSum = 0;
				int spotNum = 0;
				if(conList!=null && conList.size()>0){
					spotCheckInfoRequest.setContractInfoId(conList.get(0).getId());
					// 抽查服务统计表
					List<SpotCheckInfo> queryInfo = spotCheckInfoServiceImpl.queryList(spotCheckInfoRequest);
					if(queryInfo!=null && queryInfo.size()>0){
						SpotCheckInfo spotCheckInfo = queryInfo.get(0);
						spotSum = spotCheckInfo.getSpotSum();
						spotNum= spotCheckInfo.getSpotNum();
					}
				}
				shiroUser.setSportCount(spotNum+"/"+spotSum);
				shiroUser.setGuideState(guideState);
				//填报单位是否可以查看生成的word报告
				SpotCheckResultRequest spotCheckResultRequest =new SpotCheckResultRequest();
				spotCheckResultRequest.setSiteCode(siteCode);
				spotCheckResultRequest.setCheckReportResults(0);
				int queryCount = spotCheckResultServiceImpl.queryCount(spotCheckResultRequest);
				SpotCheckNoticeRequest spotCheckNoticeRequest = new SpotCheckNoticeRequest();
				spotCheckNoticeRequest.setSiteCode(siteCode);
				spotCheckNoticeRequest.setCheckReportResults(0);
				int spotCheckNotice = spotCheckNoticeServiceImpl.queryCount(spotCheckNoticeRequest);
				if(queryCount>0 || spotCheckNotice>0){
					shiroUser.setCanSeeWord(1);
				}else{
					shiroUser.setCanSeeWord(0);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			//判断url
			if(info.getJumpUrl() != null && !"".equals(info.getJumpUrl().trim())){
				info.setUrl(info.getJumpUrl());
			}
			
			
			Integer isYunOpen=0;//1申请试用 3登录使用
			YunOpenStateRequest yunopenRequest=new YunOpenStateRequest();
			yunopenRequest.setSiteCode(siteCode);
			yunopenRequest.setYunType(2);//1云搜索2 云分析
			yunopenRequest.setOpenStateStr("1,3");
			
			List<YunOpenState> yunInfoList=yunOpenStateServiceImpl.queryList(yunopenRequest);
			if(yunInfoList!=null && yunInfoList.size()>0){
				isYunOpen=yunInfoList.get(0).getOpenState();
			}
			
			// 将校验交给 //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配
			String updateDate = DateUtils.getYesterdayStr()+" 23:59:59";
			System.out.println("@@@@@@@@@@@@@@@@@:"+info.getSiteCode());
			subject.getSession().setAttribute(Constants.SHIRO_USER,
					new ShiroUser(info.getId(), info.getSiteCode(), info.getName(),
								  info.getUrl(),updateDate,userType,
								  info.getName(),info.getSiteCode(),
								  shiroUser.getCurrentName(),shiroUser.getCurrentSiteCode(),shiroUser.getBmCount(),
								  shiroUser.getType(),shiroUser.getSportCount(),
								  shiroUser.getPdfUrl(),iscost,iscostOwn,isOrgCost,shiroUser.getCurrentCount(),shiroUser.getNextCount(),
								  shiroUser.getExceptionCount(),shiroUser.getCloseCount(),shiroUser.getOtherCount(),shiroUser.getCanSeeWord(),shiroUser.getCanSeeMonitor(),shiroUser.getCanSeeSpot(),
								  info.getIsScan(),info.getIsexp(),"0",null,null,shiroUser.getPaTargetCount(),isSafetyService,isOrgSafetyService,shiroUser.getGuideState(),isHasMap, isYunOpen));
			
			return new SimpleAuthenticationInfo(info.getSiteCode(),
					info.getVcode(), getName());
		}
		return null;
	}
	/**
	 * @Description: 判断 组织单位是否收费（0免费，1收费）
	 * @author sunjiang --- 2016-3-25上午11:44:31     
	 * @param siteCode
	 * @return
	 */
	public int checkIsCostbyOrg(String siteCode){
		int iscost = 0;
		try {
			ContractInfoRequest conRequest=new ContractInfoRequest();
			conRequest.setSiteCode(siteCode);
			conRequest.setNowTime(DateUtils.formatStandardDateTime(new Date()));
			conRequest.setTypeFlag(1);//非抽查合同
			Integer[] conStatues={0,1};
			conRequest.setExecuteStatusArr(conStatues);//合同状态为0-初始化 1-服务中
			
			List<ContractInfo> queryList = contractInfoServiceImpl.queryList(conRequest);
			
			if(!CollectionUtils.isEmpty(queryList)){
				
				iscost = 1;
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return iscost;
	}
	/**
	 * @Description: 左侧菜单网站的个数
	 * @author sunjiang --- 2016-3-25上午11:32:07     
	 * @param siteCode
	 * @return
	 */
	public ShiroUser getCountWebsiteInfo(String siteCode){
		
		try {
			ShiroUser shiroUser = new ShiroUser();
			int count = 0;
			
			DatabaseTreeInfoRequest databaseTreeInfoRequest = new DatabaseTreeInfoRequest();
			//获取下属单位  本级部门  其他
			databaseTreeInfoRequest.setOrgSiteCode(siteCode);
			databaseTreeInfoRequest.setLayerType(1);
			databaseTreeInfoRequest.setIsLink(1);
			List<DatabaseTreeInfoRequest>	databaseTreeList=databaseTreeInfoServiceImpl.queryListByTypeOrIsexp(databaseTreeInfoRequest);
			for (DatabaseTreeInfoRequest databaseTreeInfoRequest2 : databaseTreeList) {
				if(databaseTreeInfoRequest2.getLayerType()==DatabaseLinkType.DEPARTMENT.getCode()){//本级部门
					shiroUser.setCurrentCount(databaseTreeInfoRequest2.getId());
				}else if(databaseTreeInfoRequest2.getLayerType()==DatabaseLinkType.UNIT.getCode()){//下属单位
					shiroUser.setNextCount(databaseTreeInfoRequest2.getId());
				}else if(databaseTreeInfoRequest2.getLayerType()==DatabaseLinkType.OTHER.getCode()){
					shiroUser.setOtherCount(databaseTreeInfoRequest2.getId());
				}
			}
			//获取关停 例外  个数
			databaseTreeInfoRequest.setOrgSiteCode(siteCode);
			databaseTreeInfoRequest.setLayerType(null);
			databaseTreeInfoRequest.setIsexp(1);
			databaseTreeInfoRequest.setIsLink(1);
			List<DatabaseTreeInfoRequest>	dList=databaseTreeInfoServiceImpl.queryListByTypeOrIsexp(databaseTreeInfoRequest);
			for (DatabaseTreeInfoRequest databaseTreeInfoRequest2 : dList) {
				if(databaseTreeInfoRequest2.getIsexp()==2){//例外
					shiroUser.setExceptionCount(databaseTreeInfoRequest2.getId());
				}else if(databaseTreeInfoRequest2.getIsexp()==3){//关停
					shiroUser.setCloseCount(databaseTreeInfoRequest2.getId());
				}
			}
			
			
//			param.setOrgSiteCode(siteCode);
//			param.setIsexp(1);
//			param.setGroupBy("type");
//			int num = 0;
//			//查询  本级门户 本级部门  下属单位  其他
//			List<DatabaseLinkRequest> queryCountBySiteCode = databaseLinkServiceImpl.queryCountBySiteCode(param);
//			for (DatabaseLinkRequest databaseLinkRequest : queryCountBySiteCode) {
//				
//				Integer type = databaseLinkRequest.getType();
//				if(type == 1 || type == 2 || type == 3 || type == 6){
//					num = databaseLinkRequest.getCount();
//					
//					if(type==DatabaseLinkType.ISORGANIZATIONAL.getCode()){//本级门户
//						
//						Integer databaseInfoId = databaseLinkRequest.getDatabaseInfoId();
//						
//						if(databaseInfoId!=null&&databaseInfoId!=0){
//							
//							DatabaseInfo databaseInfo = databaseInfoServiceImpl.get(databaseInfoId);
//							if(databaseInfo!=null){
//								
//								shiroUser.setCurrentName(databaseInfo.getName());
//								shiroUser.setCurrentSiteCode(databaseInfo.getSiteCode());
//								num = 1;
//							}
//							
//						}
//						
//					}else if(type==DatabaseLinkType.DEPARTMENT.getCode()){//本级部门
//						
//						shiroUser.setCurrentCount(num);
//						
//					}else if(type==DatabaseLinkType.UNIT.getCode()){//下属单位
//							
//						shiroUser.setNextCount(num);
//						
//					}else if(type==DatabaseLinkType.OTHER.getCode()){
//						
//						shiroUser.setOtherCount(num);
//					}
//					count += num;
//				}
//				
//			}
//			param.setIsexp(null);
//			param.setGroupBy("isexp");
//			//查询 关停  例外
//			List<DatabaseLinkRequest> qCountBySiteCode = databaseLinkServiceImpl.queryCountBySiteCode(param);
//			
//			for (DatabaseLinkRequest databaseLinkRequest : qCountBySiteCode) {
//				Integer isexp = databaseLinkRequest.getIsexp();
//				if(isexp != null){
//					if(isexp != 1){
//						num = databaseLinkRequest.getCount();
//						if(isexp==2){//例外
//							shiroUser.setExceptionCount(num);
//						}else if(isexp==3){//关停
//							shiroUser.setCloseCount(num);
//						}
//						count += num;
//					}
//				}
//				
//			}
//			shiroUser.setCount(count);
			
			//组织单位  判断  是否有未启动 任务
			PaProjectRequest paProjectRequest = new PaProjectRequest();
			paProjectRequest.setSiteCode(siteCode);
			List<PaTask> list= paProjectServiceImpl.queryTaskList(paProjectRequest);
			shiroUser.setPaTargetCount(list.size());
			return shiroUser;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * 描述:验证组织单位登录账号
	 * 
	 * 作者：ucap	2016-3-19下午6:20:19
	 */
/*	public void addDatabaseOrgInfo(DatabaseInfo info){
		
		try {
			
			DatabaseOrgInfoRequest request = new DatabaseOrgInfoRequest();
			request.setStieCode(info.getSiteCode());
			List<DatabaseOrgInfo> list = databaseOrgInfoServiceImpl.queryList(request);
			
			DatabaseOrgInfo databaseOrgInfo = new DatabaseOrgInfo();
			if(list.size()>0){
				databaseOrgInfo = list.get(0);
				
				databaseOrgInfo.setName(info.getName());
				databaseOrgInfo.setSiteCode(info.getSiteCode());
				databaseOrgInfo.setVcode(info.getVcode());
				databaseOrgInfoServiceImpl.update(databaseOrgInfo);
			} else {
				databaseOrgInfo.setName(info.getName());
				databaseOrgInfo.setSiteCode(info.getSiteCode());
				databaseOrgInfo.setVcode(info.getVcode());
				
				databaseOrgInfoServiceImpl.add(databaseOrgInfo);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	
	/**
	 * 
	 * 描述:post请求，返回json数据
	 * 
	 * 作者：lxx	2015-11-25下午06:27:51
	 * @param strURL
	 * @param params
	 * @return
	 */
	public String post(String strURL, String params) {  
        try {  
            URL url = new URL(strURL);// 创建连接  
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();  
            connection.setDoOutput(true);  
            connection.setDoInput(true);  
            connection.setUseCaches(false);  
            connection.setInstanceFollowRedirects(true);  
            connection.setRequestMethod("POST"); // 设置请求方式  
            connection.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式  
            connection.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式  
            connection.connect();  
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8"); // utf-8编码  
            out.append(params);  
            out.flush();  
            out.close();  
            // 读取响应  
            int length = (int) connection.getResponseCode();// 获取长度  
            if (length == 200) {  
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
                String line;
                String result = "";
                while ((line = in.readLine()) != null) {
                	result += line;
                }
                return result;  
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return "error"; // 自定义错误信息  
    } 
	
	/**
	 * @Description:判断用户的类型
	 * @author kefan-- 2015-11-15 下午7:18:20
	 * @return:用户的类型： 1:国办 2：省份 3:市县 4:填报单位
	 */
	private int judgeUserType(String siteCode) {

		if (siteCode != null) {
			if (siteCode.equals("bm0100")) {
				return UserType.TYPE_ADMIN.getCode();
			}
			// 如果siteCode是以0000结尾的，则表示为组织单位
			if (siteCode.endsWith("0000")) {
				return UserType.TYPE_PROVINCE.getCode();
			} else if (siteCode.endsWith("00")) {
				return UserType.TYPE_CITY.getCode();
			} else {// 标识填报单位
				return UserType.TYPE_COUNTY.getCode();
			}
		}
		return 1;
	}
	
	/**
	 * @Description:使用shiro进行验证码校验
	 * @author kefan-- 2015-11-13 下午8:22:49
	 * @param token
	 *            ：存放登录相关的用户名、密码、验证码等基本信息
	 * @return
	 * @throws IncorrectCaptchaException
	 *             ：验证码错误异常信息
	 */
	protected boolean doCaptchaValidate(CaptchaUsernamePasswordToken token) throws IncorrectCaptchaException {
		String captcha = (String) ServletActionContext.getRequest().getSession()
				.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		if (captcha != null && !captcha.equalsIgnoreCase(token.getCaptcha())) {
			throw new IncorrectCaptchaException("验证码错误！");
		}
		return true;
	}
	
	
	/**
	 * @Description: 根据标识码，获取正式合同
	 * @author cuichx --- 2016-8-17上午11:25:11     
	 * @param siteCode 网站标识码或者组织机构编码
	 * @param nowDate 格式日期字符串（标准格式：yyyy-MM-dd） 
	 * @param List<DatabaseTreeInfo> databaseTreeList DatabaseTreeInfo集合
	 * 
	 * 注意  siteCode 和  dataLinkList  每次只能传一个值
	 * @return
	 */
	public List<ContractInfo> getContractInfoList(String siteCode, String nowDate,List<DatabaseTreeInfo> databaseTreeList) {
		List<ContractInfo> conList = null;
		try {

			ContractInfoRequest conRequest = new ContractInfoRequest();
			if(StringUtils.isNotEmpty(siteCode)){
				conRequest.setSiteCode(siteCode);
			}
			if(databaseTreeList!=null && databaseTreeList.size()>0){
				conRequest.setDatabaseTreeList(databaseTreeList);
				conRequest.setIsSearchTb(1);
			}
			
			conRequest.setTypeFlag(1);//非抽查合同
			Integer[] conStatues = { 0, 1 };
			conRequest.setExecuteStatusArr(conStatues);//合同状态为0-初始化 1-服务中

			if (StringUtils.isNotEmpty(nowDate)) {
				conRequest.setNowTime(nowDate);
			}

			//排序
			List<QueryOrder> queryOrderList = new ArrayList<QueryOrder>();
			QueryOrder siteQueryOrder = new QueryOrder("contract_end_time", QueryOrderType.DESC);
			queryOrderList.add(siteQueryOrder);
			conRequest.setQueryOrderList(queryOrderList);
			conList = contractInfoServiceImpl.queryList(conRequest);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conList;
	}
	
	/**
	 * linhb 判断填报单位  扫描安全  处于什么状态
	 * @param siteCode
	 * @return
	 */
	public int checkUsedSafety(String siteCode){
		int status = 0;// 判断  填报单位的安全任务 状态  0 未开通    1 检测中  2  已过期
		try {
			
			// 组织单位的合同   下属可以看得
			ContractInfoRequest conRequest = new ContractInfoRequest();
			conRequest.setIsSearchTb(1);//组织单位购买的安全业务  填报单位可以查看
			conRequest.setSiteCode(siteCode);
			conRequest.setTypeFlag(1);//非抽查合同
			Integer[] conStatues = { 1, 2 };
			conRequest.setExecuteStatusArr(conStatues);//合同状态为0-初始化 1-服务中
			List<ContractInfo> lList  = contractInfoServiceImpl.queryByParentCon(conRequest);
			
			// 自己的合同  
			ContractInfoRequest coRequest=new ContractInfoRequest();
			coRequest.setSiteCode(siteCode);
			coRequest.setTypeFlag(1);//非抽查合同
			Integer[] coStatues = { 1, 2 };
			coRequest.setExecuteStatusArr(coStatues);//合同状态为0-初始化 1-服务中
			List<ContractInfo> queryList = contractInfoServiceImpl.queryList(coRequest);
			if(lList.size()<1 && queryList.size()<1){
				
				status = 0;//没有正在运行的和 过期的合同
			}else{
				
				// 组织单位的合同   下属可以看得  
				ContractInfoRequest conRequest2 = new ContractInfoRequest();
				conRequest2.setSiteCode(siteCode);
				conRequest2.setTypeFlag(1);//非抽查合同
				conRequest2.setExecuteStatus(1);//运行中的
				conRequest2.setIsSearchTb(1);//组织单位购买的安全业务  填报单位可以查看
				List<ContractInfo> lList2  = contractInfoServiceImpl.queryByParentCon(conRequest2);
				
				
				// 自己的合同  
				ContractInfoRequest coRequest2=new ContractInfoRequest();
				coRequest2.setSiteCode(siteCode);
				coRequest2.setTypeFlag(1);//非抽查合同
				coRequest2.setExecuteStatus(1);//合同状态为 1-服务中
				List<ContractInfo> queryList2 = contractInfoServiceImpl.queryList(coRequest2);
				if(lList2.size()>0 || queryList2.size()>0){//有正在运行中的
					List<Integer> ids = new ArrayList<Integer>();
					for (ContractInfo contractInfo : lList2) {
						ids.add(contractInfo.getId());
					}
					for (ContractInfo contractInfo : queryList2) {
						ids.add(contractInfo.getId());
					}
					
					MonitorTaskSilentRequest rTask = new MonitorTaskSilentRequest();
					rTask.setNowTime(DateUtils.formatStandardDateTime(new Date()));
					rTask.setContractIds(ids);
					rTask.setSiteCode(siteCode);
					List<MonitorTaskSilent> list =  monitorTaskSilentServiceImpl.queryList(rTask);
					if(list.size()>0){
						status = 1;//有正在运行的  安全任务
					}else{
						List<Integer> allIds = new ArrayList<Integer>();
						for (ContractInfo contractInfo : queryList) {
							allIds.add(contractInfo.getId());
						}
						for (ContractInfo contractInfo : lList) {
							allIds.add(contractInfo.getId());
						}
						// 如果是第一次购买    还未到 第一个安全任务   称 未开通  //  处在两个任务中间   称作 已过期
						MonitorTaskSilentRequest rrTask = new MonitorTaskSilentRequest();
						rrTask.setEndNowTime(DateUtils.formatStandardDateTime(new Date()));
						rrTask.setContractIds(allIds);
						rrTask.setSiteCode(siteCode);
						List<MonitorTaskSilent> endList =  monitorTaskSilentServiceImpl.queryList(rrTask);
						if(endList.size()>0){
							status = 2;//有历史安全任务
						}else{
							status = 0;//没有历史安全任务
						}
					}
				}else{//合同全部为过期的
					status=2;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	
	
	/**
	 * linhb 判断组织单位  扫描安全  处于什么状态
	 * @param siteCode
	 * @return
	 */
	public int checkOrgUsedSafety(String siteCode){
		int status = 0;  // 判断  组织单位的安全任务 状态  0 未开通    1 检测中  2  已过期
		try {
			ContractInfoRequest conRequest=new ContractInfoRequest();
			conRequest.setSiteCode(siteCode);
			conRequest.setTypeFlag(1);//非抽查合同
			Integer[] conStatues = { 1, 2 };
			conRequest.setExecuteStatusArr(conStatues);//合同状态为0-初始化 1-服务中
			List<ContractInfo> queryList = contractInfoServiceImpl.queryList(conRequest);
			if(queryList.size()<1){ //进行中  和过期的合同都没有  代表未开通 
				status = 0;
			}else{
				ContractInfoRequest cRequest=new ContractInfoRequest();
				cRequest.setSiteCode(siteCode);
				cRequest.setTypeFlag(1);//非抽查合同
				conRequest.setExecuteStatus(1);//执行中的合同
				List<ContractInfo> qList = contractInfoServiceImpl.queryList(conRequest);
				if(qList.size()>0){//有正在运行中的  合同  （有  未开通    执行中   已过期  三种状态）
					List<Integer> ids = new ArrayList<Integer>();
					for (ContractInfo contractInfo : qList) {
						ids.add(contractInfo.getId());
					}
					
					MonitorTaskSilentRequest rTask = new MonitorTaskSilentRequest();
					rTask.setNowTime(DateUtils.formatStandardDateTime(new Date()));
					rTask.setContractIds(ids);
					List<MonitorTaskSilent> list =  monitorTaskSilentServiceImpl.queryList(rTask);
					if(list.size()>0){
						status = 1;//有正在运行的  安全任务
					}else{
						List<Integer> allIds = new ArrayList<Integer>();
						for (ContractInfo contractInfo : queryList) {
							allIds.add(contractInfo.getId());
						}
						// 如果是第一次购买    还未到 第一个安全任务   称 未开通  //  处在两个任务中间   称作 已过期
						MonitorTaskSilentRequest rrTask = new MonitorTaskSilentRequest();
						rrTask.setEndTime(DateUtils.formatStandardDateTime(new Date()));
						rrTask.setContractIds(allIds);
						List<MonitorTaskSilent> endList =  monitorTaskSilentServiceImpl.queryList(rrTask);
						if(endList.size()>0){
							status = 2;//有历史安全任务
						}else{
							status = 0;//没有历史安全任务
						}
					}
					
				}else{//此时的合同  已经过期了
					status = 2;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	
	
	/**
	 * linhb 判断组织单位  是否具有地图
	 * @param siteCode
	 * @return
	 */
	public int getMap(String siteCode){
		int status = 0;  // 判断  组织单位的安全任务 状态  0 未开通    1 检测中  2  已过期
		try {
			String siteCodes1 ="bm0100,110000,120000,310000,500000,310200,429000,522200,522400,130100,130200,130300,130400,130500,130600,130700,130800,130900,131000,131100,140100,140200,140300,140400,140500,140600,140700,140800,140900,141000,141100,150100,150200,150300,150400,150500,150600,150700,150800,150900,152200,152500,152900,210100,210200,210300,210400,210500,210600,210700,210800,210900,211000,211100,211200,211300,211400,220100,220200,220300,220400,220500,220600,220700,220800,222400,230100,230200,230300,230400,230500,230600,230700,230800,230900,231000,231100,231200,232700,320100,320200,320300,320400,320500,320600,320700,320800,320900,321000,321100,321200,321300,330100,330200,330300,330400,330500,330600,330700,330800";
			String siteCodes2 ="330900,331000,331100,340100,340200,340300,340400,340500,340600,340700,340800,341000,341100,341200,341300,341500,341600,341700,341800,350100,350200,350300,350400,350500,350600,350700,350800,350900,360100,360200,360300,360400,360500,360600,360700,360800,360900,361000,361100,370100,370200,370300,370400,370500,370600,370700,370800,370900,371000,371100,371200,371300,371400,371500,371600,371700,410100,410200,410300,410400,410500,410600,410700,410800,410900,411000,411100,411200,411300,411400,411500,411600,411700,469000,420100,420200,420300,420500,420600,420700,420800,420900,421000,421100,421200,421300,422800,430100,430200,430300,430400,430500,430600,430700,430800,430900,431000,431100,431200,431300";
			String siteCodes3 ="512000,513200,513300,513400,520100,520200,520300,520400,522300,522600,522700,530100,530300,530400,530500,530600,530700,530800,530900,532300,532500,532600,532800,532900,533100,533300,533400,540100,542100,542200,542300,542400,542500,542600,610100,610200,610300,610400,610500,610600,610700,610800,610900,611000,433100,440100,440200,440300,440400,440500,440600,440700,440800,440900,441200,441300,441400,441500,441600,441700,441800,441900,442000,445100,445200,445300,450100,450200,450300,450400,450500,450600,450700,450800,450900,451000,451100,451200,451300,451400,460100,460200,460300,510100,510300,510400,510500,510600,510700,510800,510900,511000,511100,511300,511400,511500,511600,511700,511800,511900";
			String siteCodes4 ="620100,620200,620300,620400,620500,620600,620700,620800,620900,621000,621100,621200,622900,623000,630100,632100,632200,632300,632500,632600,632700,632800,640100,640200,640300,640400,640500,650100,650200,652100,652200,652300,652700,652800,652900,653000,653100,653200,654000,654200,654300,659000,710000,810100,820000,130000,140000,150000,210000,220000,230000,320000,330000,340000,350000,360000,370000,410000,420000,430000,440000,450000,460000,510000,520000,530000,540000,610000,620000,630000,640000,650000";
			
			if(siteCodes1.indexOf(siteCode)>-1||siteCodes2.indexOf(siteCode)>-1||siteCodes3.indexOf(siteCode)>-1||siteCodes4.indexOf(siteCode)>-1){
				status=1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}
}
