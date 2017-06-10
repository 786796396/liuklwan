
package com.ucap.cloud_web.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
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
import com.publics.util.utils.HttpClientUtils;
import com.publics.util.utils.StringUtils;
import com.ucap.cloud_web.bizService.DatabaseBizService;
import com.ucap.cloud_web.constant.AccountBindStatus;
import com.ucap.cloud_web.constant.CrmProductsType;
import com.ucap.cloud_web.constant.SecurityServiceType;
import com.ucap.cloud_web.dto.AccountBindInfoRequest;
import com.ucap.cloud_web.dto.DatabaseInfoRequest;
import com.ucap.cloud_web.dto.DatabaseTreeInfoRequest;
import com.ucap.cloud_web.dto.DetectionResultRequest;
import com.ucap.cloud_web.dto.LinkAllDetailRequest;
import com.ucap.cloud_web.dto.LinkAllInfoRequest;
import com.ucap.cloud_web.dto.MTaskoverviewRequest;
import com.ucap.cloud_web.dto.SecurityBlankDetailRequest;
import com.ucap.cloud_web.dto.SecurityHomeChannelRequest;
import com.ucap.cloud_web.dto.SecurityResponseRequest;
import com.ucap.cloud_web.dto.SecurityServcieRequest;
import com.ucap.cloud_web.dto.ServicePeriodRequest;
import com.ucap.cloud_web.dtoResponse.SecurityGuaranteeResponse;
import com.ucap.cloud_web.entity.AccountBindInfo;
import com.ucap.cloud_web.entity.ContractInfo;
import com.ucap.cloud_web.entity.DatabaseInfo;
import com.ucap.cloud_web.entity.DatabaseTreeInfo;
import com.ucap.cloud_web.entity.DetectionResult;
import com.ucap.cloud_web.entity.DicConfig;
import com.ucap.cloud_web.entity.LinkAllDetail;
import com.ucap.cloud_web.entity.LinkAllInfo;
import com.ucap.cloud_web.entity.MTaskoverview;
import com.ucap.cloud_web.entity.SecurityHomeChannel;
import com.ucap.cloud_web.entity.ServicePeriod;
import com.ucap.cloud_web.service.IAccountBindInfoService;
import com.ucap.cloud_web.service.IDatabaseInfoService;
import com.ucap.cloud_web.service.IDatabaseTreeInfoService;
import com.ucap.cloud_web.service.IDetectionResultService;
import com.ucap.cloud_web.service.IDicConfigService;
import com.ucap.cloud_web.service.ILinkAllDetailService;
import com.ucap.cloud_web.service.ILinkAllInfoService;
import com.ucap.cloud_web.service.IMTaskoverviewService;
import com.ucap.cloud_web.service.ISecurityBlankDetailService;
import com.ucap.cloud_web.service.ISecurityHomeChannelService;
import com.ucap.cloud_web.service.ISecurityResponseService;
import com.ucap.cloud_web.service.ISecurityServcieService;
import com.ucap.cloud_web.service.IServicePeriodService;
import com.ucap.cloud_web.servlet.util.CommonUtils;
import com.ucap.cloud_web.shiro.Constants;


/**
 * <p>Description: </p>概览页面数据初始化
 * <p>@Package：com.ucap.cloud_web.action </p>
 * <p>Title: OverviewAction </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：cuichx </p>
 * <p>@date：2015-11-6上午10:58:02 </p>
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class FillUnitAction extends BaseAction{
	@Autowired
	private ILinkAllInfoService linkAllInfoServiceImpl;
	@Autowired
	private IDatabaseInfoService databaseInfoServiceImpl;
	@Autowired
	private IAccountBindInfoService accountBindInfoServiceImpl;
	@Autowired
	private IDetectionResultService detectionResultServiceImpl;
	@Autowired
	private IDatabaseTreeInfoService databaseTreeInfoServiceImpl;
	@Autowired
	private IMTaskoverviewService MTaskoverviewServiceImpl;
	@Autowired
	private DatabaseBizService databaseBizServiceImpl;
	@Autowired
	private IServicePeriodService servicePeriodServiceImpl;
	@Autowired
	private IDicConfigService dicConfigServiceImpl;
	@Autowired
	private ILinkAllDetailService linkAllDetailServiceImpl;
	@Autowired
	private ISecurityHomeChannelService securityHomeChannelServiceImpl;
	@Autowired
	private ISecurityBlankDetailService securityBlankDetailServiceImpl;
	@Autowired
	private ISecurityResponseService securityResponseServiceImpl;
	@Autowired
	private ISecurityServcieService securityServcieServiceImpl;
	private String siteCode="";
	private String urlStr="";
	// 类型为全面检测产品
	private Integer[] productTypeArr = { CrmProductsType.DETECTION.getCode() };
	
	/**
	 * log日志加载
	 */
	private static Log logger = LogFactory.getLog(FillUnitAction.class);
	
	
	/**
	 * @Description: 页面点击弹出二维码，供用户扫描绑定
	 * @author cuichx --- 2016-6-21下午7:10:02
	 */
	public void clickErWeiImgeUrl(){
		
		Map<String, Object> resultMap=new HashMap<String, Object>();
		
		String siteCode=getCurrentUserInfo().getSiteCode();
		if(siteCode.length()==6){//组织单位登录
			siteCode=getCurrentUserInfo().getChildSiteCode();
		}
		try {
			if(StringUtils.isNotEmpty(siteCode)){
				
/*				String erweiUrl="";
				boolean urlFlag=false;
				while(!urlFlag){
					if(!"".equals(erweiUrl) && !"null".equals(erweiUrl.substring(51, erweiUrl.length()))){
						urlFlag=true;
						break;
					}
					erweiUrl=getErWeiImgeUrl(siteCode);
				}*/
				
				String erweiUrl=getErWeiImgeUrl(siteCode);
				if(StringUtils.isNotEmpty(erweiUrl)){
					resultMap.put("erweiUrl", erweiUrl);
				}else{
					resultMap.put("errorMsg", "调用微信接口获取动态二维码失败！");
				}
			}else{
				resultMap.put("errorMsg", "网站标识码不能为空");
			}
			logger.info("FillUnitAction clickErWeiImgeUrl resultMap:"+resultMap);
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("errorMsg", "调用微信接口获取动态二维码异常！");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		}
	}
	
	/**
	 * @Description: 微信关注二维码页面
	 * @author cuichx --- 2016-2-20下午5:33:04     
	 * @return
	 */
	public String weixin(){
		siteCode=request.getParameter("siteCode");
		urlStr=request.getParameter("urlStr");
		
		return "success";
	}
	/**
	 * @Description: 微信关注二维码页面
	 * @author cuichx --- 2016-2-20下午6:10:25
	 */
	public void clousePage(){
		
		Map<String, Object> resultMap=new HashMap<String, Object>();
		String siteCode=request.getParameter("siteCode");
		try {
			if(StringUtils.isNotEmpty(siteCode)){
				AccountBindInfoRequest accBindInfoRequest=new AccountBindInfoRequest();
				accBindInfoRequest.setSiteCode(siteCode);
				accBindInfoRequest.setStatus(AccountBindStatus.ACCOUNT_BIND.getCode());
				
				List<AccountBindInfo> accountList=accountBindInfoServiceImpl.queryList(accBindInfoRequest);
				if(accountList!=null && accountList.size()>0){
					resultMap.put("success", "微信关注页面关闭成功!");
				}else{
					resultMap.put("errorMsg", "请关注并绑定微信账号!");
				}
			}else{
				resultMap.put("errorMsg", "网站标识码不能为空!");
			}
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("errorMsg", "关闭微信关注页面异常!");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		}
	}
	
	/**
	 * @Description: 概览页面
	 * 用于判断是填报单位登录，还是由组织单位跳转过来的
	 * 		1.如果siteCode不为空，表示组织单位通过超链接条转过来的
	 * 		2.如果siteCode为空，表示填报单位登录
	 * @author cuichx --- 2015-11-9上午9:29:57     
	 * @return
	 */
	public String gailan(){
		String siteCode = request.getParameter("siteCode");
		if (StringUtils.isNotEmpty(siteCode)) {
			setCurrentShiroUser(siteCode);
		} else {
			siteCode = getCurrentUserInfo().getSiteCode();
			/*
			 * List<DatabaseTreeInfo> treeInfoList = getTreeInfoList(); if
			 * (treeInfoList != null && treeInfoList.size() > 0) { return
			 * "loginOrg"; }
			 */
		}
		return "success";
	}
	
	/**
	 * @Description: 生成一个临时二维码的url
	 * @author cuichx --- 2016-6-21下午6:54:02
	 */
	public String getErWeiImgeUrl(String siteCode){
		
		String urlStr="";
		try {
			//查询基本信息表，获取id
			DatabaseInfoRequest dataRequest=new DatabaseInfoRequest();
			dataRequest.setSiteCode(siteCode);
			List<DatabaseInfo>  dataList=databaseInfoServiceImpl.queryList(dataRequest);
			if(dataList!=null && dataList.size()>0){
				Integer id=dataList.get(0).getId();
				
	        	String access_token=CommonUtils.getTokenFromServlet();
	        	logger.info("FillUnitAction getErWeiImgeUrl() access_token:"+access_token);
	        	/**
	        	 * 通过循环调用获取微信二维码接口，直到获取到ticket之后终止循环
	        	 */
	        	boolean tickFlag=false;
	        	String str = "{\"expire_seconds\":\"300000\",\"action_name\":\"QR_LIMIT_SCENE\",\"action_info\":{\"scene\":{\"scene_id\":"+id+"}}}";
	        	while(!tickFlag){
		        	//临时二维码获取ticket
	        		String  requestUrl="https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token="+access_token;
	        		logger.info(" FillUnitAction getErWeiImgeUrl() requestUrl:"+requestUrl);
					//发送https请求获取二维码获取ticket
		        	//JSONObject jsonStr=CommonUtils.httpRequest(requestUrl, "POST", str);
					String returnStr= HttpClientUtils.sendPostCommand(str, requestUrl);
					JSONObject jsonStr=JSONObject.fromObject(returnStr);
					logger.info(" FillUnitAction getErWeiImgeUrl() jsonStr:"+jsonStr); 
		        	if(jsonStr!=null && !"".equals(jsonStr)){
		        		String ticketStr=null;
		        		//二维码ticket
		        		ticketStr=String.valueOf(jsonStr.get("ticket"));
		        		if(!"".equals(ticketStr) && ticketStr!=null  ){
		        			tickFlag=true;
			        		urlStr="https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket="+ticketStr;
			        		logger.info("FillUnitAction getErWeiImgeUrl()  urlStr:"+urlStr);
			        		return urlStr;
		        		}
		        	}
		        	
	        	}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * @Description: 首页面数据统计查询
	 * @author cuichx --- 2015-11-6上午11:02:54     
	 * @return
	 */
	public void homePageSearch(){
		//封装返回给页面的参数
		Map<String,Object>  resultMap=new HashMap<String, Object>();
		try {
			//获取页面传送过来的数据，并将其封装到dto对象
			String siteCode=getCurrentUserInfo().getChildSiteCode();
			if(StringUtils.isEmpty(siteCode)){
				siteCode=getCurrentUserInfo().getSiteCode();
			}
			
			String scanDate=queryHomePageDate();//获取昨天的日期YYYY-MM-dd
			
			//通过网站标识码、昨天日期查询 检测结果列表
			DetectionResultRequest detectionRequest=new DetectionResultRequest();
			detectionRequest.setSiteCode(siteCode);
			detectionRequest.setScanDate(scanDate);
			
			List<DetectionResult> detectionList=detectionResultServiceImpl.queryList(detectionRequest);
			if(detectionList!=null && detectionList.size()>0){
				DetectionResult detectionResult=detectionList.get(0);
				/**
				 * 健康指数统计数据
				 */
				double convertScores=0;//昨天折算分数
				String totalScores="0";//昨天健康指数

				String gtTotalSum="0";//健康指数领先全国的百分比
				String upTotal="0";//相比前天健康分数增加
				String upTotalPer="0";//相比前天健康分数增加的百分比
				
				convertScores=detectionResult.getConvertScores();
				if(StringUtils.isNotEmpty(detectionResult.getIndexCount())){//昨天健康指数
					totalScores=detectionResult.getIndexCount();
				}
				
				if(StringUtils.isNotEmpty(detectionResult.getLeadAvgRate())){//健康指数领先全国的百分比
					gtTotalSum=detectionResult.getLeadAvgRate();
				}
				
				if(StringUtils.isNotEmpty(detectionResult.getLeadYesterday())){//相比前天健康分数增加
					upTotal=detectionResult.getLeadYesterday();
				}
				
				if(StringUtils.isNotEmpty(detectionResult.getLeadYesterdayRate())){//相比前天健康分数增加的百分比
					upTotalPer=detectionResult.getLeadYesterdayRate();
				}
				
				
				/**
				 * 5个色块数据统计
				 */
				int connectionSum=0;// 网站连通性     不连通统计总数
				int connectionHome=0;//首页不连通
				int connectionBus=0;//业务系统不连通
				int connectionChannel=0;//关键栏目连不通
				
				int linkNum=0;//链接可用性
				int linkHome=0;//首页死链数
				int linkAll=0;//全站链接可用性
				
				int contentSum=0;// 内容正确性
				
				int updateContentSum=0;// 内容更新统计总数  
				int updateHome=0;//首页更新量
				int updateChannel=0;//栏目更新
				
				int securitySum=0;
				
				/**
				 * 连通性
				 */
				connectionSum=detectionResult.getConnNum();
				connectionHome=detectionResult.getConnHome();
				connectionBus=detectionResult.getConnBusiness();
				connectionChannel=detectionResult.getConnChannel();
				
				/**
				 * 链接可用性
				 */
				linkHome=detectionResult.getLinkHome();
				ServicePeriod servicePeriod=null;
				//获取高级版的服务周期
				if(getCurrentUserInfo().getSiteCode().length()==6){//组织单位跳转过来来
					servicePeriod=getAdvanceServicePeriod(getCurrentUserInfo().getSiteCode(),null);
				}else{//填报单位登录
					servicePeriod=getAdvanceTbInfo(getCurrentUserInfo().getSiteCode());
				}
				
				/**
				 * 内容保障
				 */
				int securityQuestion=0;//内容保障
				int securityHome=0;//首页不更新
				
				int securityChannel=0;//栏目不更新
				int securityBasic=0;//基本信息
				int securityBlank=0;//空白栏目
				int securityService=0;//服务不实用
				int securityResponse=0;//互动回应差
				
				securityHome=detectionResult.getSecurityHome();

				/**
				 * 基本信息  基本信息包含3部分   1--原来的栏目不更新  2-原来的基本信息-标准版  3-原来的基本信息高级版
				 */
				securityBasic=getSecurityBasicSum(siteCode,servicePeriod);

				if(servicePeriod!=null){
					securityBlank=getSecurityBlankSum(siteCode, servicePeriod);//空白栏目
					securityService=getSecurityServiceSum(siteCode, servicePeriod);//服务不实用
					securityResponse=getSecurityResponseSum(siteCode, servicePeriod);//互动回应差
					
					securityChannel=securityBasic+securityBlank+securityService+securityResponse;
					
					//获取全站链接统计数据
					LinkAllInfoRequest allRequest=new LinkAllInfoRequest();
					allRequest.setSiteCode(siteCode);
					allRequest.setServicePeriodId(servicePeriod.getId());
					
					List<LinkAllInfo> linkAllList=linkAllInfoServiceImpl.queryList(allRequest);
					if(linkAllList!=null && linkAllList.size()>0){
						linkAll=linkAllList.get(0).getWebsiteSum();
					}else{
						linkAll=0;//表示未检测
					}
					linkNum=linkHome+linkAll;
				}else{
					 securityBlank=-1;//空白栏目
					 securityService=-1;//服务不实用
					 securityResponse=-1;//互动回应差
					 
					 securityChannel=securityBasic;
					 
					linkAll=-1;//表示未检测
					linkNum=linkHome;
				}
				securityQuestion=securityHome+securityChannel;
				/**
				 * 疑似错别字
				 */
				contentSum=detectionResult.getContCorrectNum();
				
				/**
				 * 内容更新量
				 */
				updateContentSum=detectionResult.getContUpdate();
				updateHome=detectionResult.getUpdateHome();
				updateChannel=detectionResult.getUpdateChannel();
				/**
				 * 安全问题
				 */
				securitySum=detectionResult.getContGuaranteNum();
				
				//健康指数统计
				resultMap.put("convertScores", convertScores);//昨天折算分数
				resultMap.put("totalScores", totalScores);//昨天健康指数	
				resultMap.put("gtTotalSum", gtTotalSum);//健康指数领先全国的百分比
				resultMap.put("upTotal", upTotal);//相比前天健康分数增加
				resultMap.put("upTotalPer", upTotalPer);//相比前天健康分数增加的百分比

				
				//6个色块数据统计
				resultMap.put("connectionSum", connectionSum);// 网站连通性     不连通统计总数
				resultMap.put("connectionHome", connectionHome);// 首页不连通
				resultMap.put("connectionBus", connectionBus);// 网站连通性     不连通统计总数
				resultMap.put("connectionChannel", connectionChannel);// 首页不连通
				
				resultMap.put("linkNum", linkNum);// 链接不可用性统计总数
				resultMap.put("linkHome", linkHome);//首页
				resultMap.put("linkAll", linkAll);//全站
				
				resultMap.put("contentSum", contentSum);// 内容正确性
				
				resultMap.put("securityQuestion", securityQuestion);//内容保障问题总数
				resultMap.put("securityHome", securityHome);//首页不更新
				resultMap.put("securityChannel", securityChannel);//栏目不更新
				resultMap.put("securityBasic", securityBasic);//基本信息
				resultMap.put("securityBlank", securityBlank);//空白栏目
				resultMap.put("securityService", securityService);//服务不实用
				resultMap.put("securityResponse", securityResponse);//互动回应差
				
				resultMap.put("updateContentSum", updateContentSum);// 内容更新统计总数  
				resultMap.put("updateHome", updateHome);// 首页更新量
				resultMap.put("updateChannel", updateChannel);//栏目更新
				
				resultMap.put("securitySum", securitySum);//安全问题总数
				
			}else{
				resultMap.put("convertScores", 0);//昨天折算分数
				resultMap.put("totalScores", 0);//昨天健康指数	
				resultMap.put("gtTotalSum", 0);//健康指数领先全国的百分比
				resultMap.put("upTotal", 0);//相比前天健康分数增加
				resultMap.put("upTotalPer", 0);//相比前天健康分数增加的百分比
				
				//6个色块数据统计
				resultMap.put("connectionSum", 0);// 网站连通性     不连通统计总数
				resultMap.put("linkSum", 0);// 链接不可用性统计总数--只统计首页链接不可用的
				resultMap.put("securitySum", 0);// 内容正确性
				resultMap.put("contentSum", 0);//内容保障问题总数
				resultMap.put("updateContentSum", 0);// 内容更新统计总数  
				//resultMap.put("securityQuestionNum", 0);//安全问题总数
			}
			resultMap.put("success", "true");
			logger.info("resultMap="+resultMap);
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", "false");
			resultMap.put("errorMsg", "查询监测结果数据异常");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		}
	}

	/**
	 * @Description:基本信息
	 * @author cuichx --- 2017-5-18下午2:05:23
	 */
	public Integer getSecurityBasicSum(String siteCode,ServicePeriod servicePeriod){
		Integer securityBasic=0;
		//查询昨天的基本信息的逾期未更新数据量
		SecurityHomeChannelRequest homeChannelRequest = new SecurityHomeChannelRequest(); // 逾期未更新天数
		homeChannelRequest.setSiteCode(siteCode);
		homeChannelRequest.setAutoInput("auto");// 系统检测
												// 机器输入
		homeChannelRequest.setType(2); // 查询逾期 [栏目]
		homeChannelRequest.setSelectType("4");
		homeChannelRequest.setFlag(false); // 栏目类型
		// 查询出所有栏目的逾期
		homeChannelRequest.setPageSize(Integer.MAX_VALUE);
		homeChannelRequest.setScanDate(DateUtils.getYesterdayStr()); // 查询扫描时间在昨天的
		List<SecurityHomeChannel> securityHomeChannelList = securityHomeChannelServiceImpl
				.queryListTb(homeChannelRequest);
		if(CollectionUtils.isNotEmpty(securityHomeChannelList) && securityHomeChannelList.size() > 0){ 
			securityBasic += securityHomeChannelList.size();
		}
		
		if(servicePeriod !=null){
			homeChannelRequest = new SecurityHomeChannelRequest(); // 逾期未更新天数
			// 有高级版任务时候，默认展示人工检测页面
			homeChannelRequest.setSiteCode(siteCode);
			homeChannelRequest.setNoAutoInput("noAuto"); //人工检测 非机器输入
			homeChannelRequest.setType(2); // 查询逾期 [栏目]
			homeChannelRequest.setSelectType("4");
			homeChannelRequest.setFlag(false); // 栏目类型 查询出所有栏目的逾期
			homeChannelRequest.setPageSize(Integer.MAX_VALUE);
			homeChannelRequest.setServicePeriodId(servicePeriod.getId()); // 查询属于此周期id的逾期未更新[人工查询]
			securityHomeChannelList = securityHomeChannelServiceImpl
					.queryListTb(homeChannelRequest);
			if(CollectionUtils.isNotEmpty(securityHomeChannelList) && securityHomeChannelList.size() > 0){ 
				securityBasic += securityHomeChannelList.size();
			}
		}
		return securityBasic;
	}
	
	/**
	 * @Description:空白栏目个数
	 * @author cuichx --- 2017-5-18下午2:05:23
	 */
	public Integer getSecurityBlankSum(String siteCode,ServicePeriod servicePeriod){
		//空白栏目
		SecurityBlankDetailRequest blankRequest=new SecurityBlankDetailRequest();
		blankRequest.setSiteCode(siteCode);
		blankRequest.setServicePeriodId(servicePeriod.getId());
		
		
		Integer blankSum=securityBlankDetailServiceImpl.queryCount(blankRequest);
		if(blankSum!=null){
			return blankSum;
		}else{
			return 0;
		}
	}
	/**
	 * @Description:服务不实用
	 * @author cuichx --- 2017-5-18下午2:05:23
	 */
	public Integer getSecurityServiceSum(String siteCode,ServicePeriod servicePeriod){
		SecurityServcieRequest serviceRequest=new SecurityServcieRequest();
		serviceRequest.setSiteCode(siteCode);
		serviceRequest.setServicePeriodId(servicePeriod.getId());
		
		Integer serviceSum=securityServcieServiceImpl.queryCount(serviceRequest);
		if(serviceSum!=null){
			return serviceSum;
		}else{
			return 0;
		}
	}
	/**
	 * @Description:互动回应差
	 * @author cuichx --- 2017-5-18下午2:05:23
	 */
	public Integer getSecurityResponseSum(String siteCode,ServicePeriod servicePeriod){
		SecurityResponseRequest responseRequest=new SecurityResponseRequest();
		responseRequest.setSiteCode(siteCode);
		responseRequest.setServicePeriodId(servicePeriod.getId());
		
		Integer responseSum=securityResponseServiceImpl.queryCount(responseRequest);
		if(responseSum!=null){
			return responseSum;
		}else{
			return 0;
		}
	}
	/**
	 * @Description: 健康指数曲线图
	 * @author cuichx --- 2016-1-18下午2:05:23
	 */
	public void indexCountLine(){
		Map<String, Object> resultMap=new HashMap<String, Object>();
		try {
			//获取页面传送过来的数据，并将其封装到dto对象
			String siteCode=getCurrentUserInfo().getChildSiteCode();
			if(StringUtils.isEmpty(siteCode)){
				siteCode=getCurrentUserInfo().getSiteCode();
			}
			
			//日期数据集合
			List<Object> datelist = new ArrayList<Object>();
			//网站健康分数集合
			List<Object> siteList = new ArrayList<Object>();
			//全国健康网站平均分数集合
			List<Object> allSiteList = new ArrayList<Object>();
			
			String siteName="";
			
			DatabaseInfoRequest dataRequest=new DatabaseInfoRequest();
			dataRequest.setSiteCode(siteCode);
			List<DatabaseInfo>  dataList=databaseInfoServiceImpl.queryList(dataRequest);
			if(dataList!=null && dataList.size()>0){
				siteName=dataList.get(0).getName();
				
				String endDate = DateUtils.getNextDay(queryHomePageDate(), "0");
				String nextDay = DateUtils.getNextDay(queryHomePageDate(), "-6");
				
				//通过网站标识码、昨天日期查询 检测结果列表
				DetectionResultRequest detectionRequest=new DetectionResultRequest();
				detectionRequest.setSiteCode(siteCode);
				detectionRequest.setBeginScanDate(nextDay);
				detectionRequest.setEndScanDate(endDate);
				detectionRequest.setPageSize(Integer.MAX_VALUE);
				
				//排序
				List<QueryOrder> queryOrderList=new ArrayList<QueryOrder>();
				QueryOrder siteQueryOrder=new QueryOrder("scan_date",QueryOrderType.ASC);
				queryOrderList.add(siteQueryOrder);
				detectionRequest.setQueryOrderList(queryOrderList);
				
				List<DetectionResult> detectionList=detectionResultServiceImpl.queryList(detectionRequest);
				if(detectionList!=null && detectionList.size()>0){
					for (DetectionResult detectionResult : detectionList) {
						datelist.add(detectionResult.getScanDate());//日期
						siteList.add(detectionResult.getIndexCount());//健康指数
						allSiteList.add(detectionResult.getIndexCountAvg());//全国健康指数
					}
					resultMap.put("siteName", siteName);//当前登录的填报单位名称
					resultMap.put("datelist", datelist);//日期数据集合
					resultMap.put("siteList", siteList);//网站健康分数集合
					resultMap.put("allSiteList", allSiteList);//全国健康网站平均分数集合
					writerPrint(JSONObject.fromObject(resultMap).toString());
				}else{
					resultMap.put("siteName", siteName);//当前登录的填报单位名称
					resultMap.put("datelist", datelist);//日期数据集合
					resultMap.put("siteList", siteList);//网站健康分数集合
					resultMap.put("allSiteList", allSiteList);//全国健康网站平均分数集合
					writerPrint(JSONObject.fromObject(resultMap).toString());
//					resultMap.put("errorMsg", "不存在该站点监测结果数据");
//					writerPrint(JSONObject.fromObject(resultMap).toString());
				}
			}else{
				resultMap.put("siteName", siteName);//当前登录的填报单位名称
				resultMap.put("datelist", datelist);//日期数据集合
				resultMap.put("siteList", siteList);//网站健康分数集合
				resultMap.put("allSiteList", allSiteList);//全国健康网站平均分数集合
				writerPrint(JSONObject.fromObject(resultMap).toString());
//				resultMap.put("errorMsg", "不存在该站点信息");
//				writerPrint(JSONObject.fromObject(resultMap).toString());
			}
		}catch (Exception e) {
			e.printStackTrace();
			resultMap.put("errorMsg", "获取健康指数折线图数据异常");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		}
	}
	
	/**
	 * @Description: 健康指数曲线图
	 * @author linhb --- 2016-10-13下午2:05:23
	 * 仅仅获取  bm01000001  修改  开放 32个省 直辖市 自治区
	 */
	public void indexCountLineBM(){
		Map<String, Object> resultMap=new HashMap<String, Object>();
		try {
			
			String siteCodeOrg = getCurrentSiteCode();
			String siteName="";
			DatabaseTreeInfoRequest dRequest = new DatabaseTreeInfoRequest();
			dRequest.setSiteCode(siteCodeOrg);
			List<DatabaseTreeInfo> list = databaseTreeInfoServiceImpl.queryList(dRequest);
			if(list.size()>0){
				//获取组织单位的门户 站点
				DatabaseTreeInfoRequest ddRequest = new DatabaseTreeInfoRequest();
				//ddRequest.setSiteCodeLength(10);
				//ddRequest.setIsLink(1);
				ddRequest.setIsBigdata(1);
				ddRequest.setIsorganizational(1);
				ddRequest.setOrgSiteCode(siteCodeOrg);
				ddRequest.setLayerType(1);
				//ddRequest.setParentId(list.get(0).getId());
				List<DatabaseTreeInfo> listt = databaseTreeInfoServiceImpl.getDatabaseTreeInfoList(ddRequest);
				if(listt.size()>0){
					siteCode = listt.get(0).getSiteCode();
					siteName = listt.get(0).getName();
				}
			}
			
			//日期数据集合
			List<Object> datelist = new ArrayList<Object>();
			//网站健康分数集合
			List<Object> siteList = new ArrayList<Object>();
			//全国健康网站平均分数集合
			List<Object> allSiteList = new ArrayList<Object>();
			
				
			//获取前90天的开始时间
			String nextDay = DateUtils.getNextDay(new Date(), -7);
			//获取昨天的日期
			String endDate = DateUtils.getNextDay(new Date(), -1);
			//通过网站标识码、昨天日期查询 检测结果列表
			DetectionResultRequest detectionRequest=new DetectionResultRequest();
			detectionRequest.setSiteCode(siteCode);
			detectionRequest.setBeginScanDate(nextDay);
			detectionRequest.setEndScanDate(endDate);
			detectionRequest.setPageSize(Integer.MAX_VALUE);
			
			//排序
			List<QueryOrder> queryOrderList=new ArrayList<QueryOrder>();
			QueryOrder siteQueryOrder=new QueryOrder("scan_date",QueryOrderType.ASC);
			queryOrderList.add(siteQueryOrder);
			detectionRequest.setQueryOrderList(queryOrderList);
			
			List<DetectionResult> detectionList=detectionResultServiceImpl.queryList(detectionRequest);
			if(detectionList!=null && detectionList.size()>0){
				for (DetectionResult detectionResult : detectionList) {
					datelist.add(StringUtils.getPrettyNumber(DateUtils.getDayStr(detectionResult.getScanDate()))+"日");//日期
					siteList.add(detectionResult.getIndexCount());//健康指数
					if("bm0100".equals(siteCodeOrg)){
						allSiteList.add(detectionResult.getIndexCountAvg());//全国健康指数
					}
				}
			}
			if(!"bm0100".equals(siteCodeOrg)){
			/*	DetectionOrgCountRequest detectionOrgCountRequest=new DetectionOrgCountRequest();
				
				detectionOrgCountRequest.setStartDate(nextDay);
				detectionOrgCountRequest.setEndDate(endDate);
				detectionOrgCountRequest.setSiteCode(siteCodeOrg);
				detectionOrgCountRequest.setType("0");
				List<QueryOrder> queryOrderOrgList=new ArrayList<QueryOrder>();
				QueryOrder queryOrder=new QueryOrder("scan_date",QueryOrderType.ASC);
				queryOrderOrgList.add(queryOrder);
				detectionOrgCountRequest.setQueryOrderList(queryOrderOrgList);
				List<DetectionOrgCount> detectionOrgCountList=detectionOrgCountServiceImpl.queryList(detectionOrgCountRequest);
				for (DetectionOrgCount detectionOrgCount : detectionOrgCountList) {
					allSiteList.add(detectionOrgCount.getIndexCount());//健康指数
				}*/
				MTaskoverviewRequest mTaskRequest = new MTaskoverviewRequest();
				mTaskRequest.setStartCountday(getMTaskDay(new Date(), -7));
				mTaskRequest.setEndCountday(getMTaskDay(new Date(), -1));
				mTaskRequest.setTaskid(siteCodeOrg); //标识码
				List<QueryOrder> queryOrderOrgList=new ArrayList<QueryOrder>();
				QueryOrder queryOrder=new QueryOrder("countday",QueryOrderType.ASC);
				queryOrderOrgList.add(queryOrder);
				mTaskRequest.setQueryOrderList(queryOrderOrgList);
				List<MTaskoverview> mTaskList = MTaskoverviewServiceImpl.queryList(mTaskRequest);
				if(CollectionUtils.isNotEmpty(mTaskList) && mTaskList.size() > 0){
					for (MTaskoverview mTaskoverview : mTaskList) {
						allSiteList.add(mTaskoverview.getHealthindex());//健康指数
					}
				}
			}
			resultMap.put("siteName", siteName);//当前登录的填报单位名称
			resultMap.put("datelist", datelist);//日期数据集合
			resultMap.put("siteList", siteList);//网站健康分数集合
			resultMap.put("allSiteList", allSiteList);//全国健康网站平均分数集合
			writerPrint(JSONObject.fromObject(resultMap).toString());
		}catch (Exception e) {
			e.printStackTrace();
			resultMap.put("errorMsg", "获取健康指数折线图数据异常");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		}
	}
	
	   public  String getMTaskDay(Date d, int delay)
	    {
	        try
	        {
	            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
	            String mdate = "";
	            long myTime = d.getTime() / 1000L + (long)(delay * 24 * 60 * 60);
	            d.setTime(myTime * 1000L);
	            mdate = format.format(d);
	            return mdate;
	        }
	        catch(Exception e)
	        {
	            return "";
	        }
	    }
	
	
	
	/**
	 * @Description: 全站链接不可用性折线图
	 * @author cuichx --- 2015-11-9下午1:18:45
	 */
	public void linkAllUnuse(){
		List<Object> list=new ArrayList<Object>();
		try {
			String siteCode=getCurrentUserInfo().getChildSiteCode();
			if(StringUtils.isEmpty(siteCode)){
				siteCode=getCurrentUserInfo().getSiteCode();
			}
			logger.info("linkAllUnuse siteCode:"+siteCode);
			/**老合同信息**/
			List<ServicePeriod> serviceList = getServicePeriodList(1);
			/**新产品信息**/
			// Integer[] productTypeArr = { CrmProductsType.DETECTION.getCode()
			// };
			// List<ServicePeriod> serviceList = getAllServicePeriodList(1,
			// productTypeArr);
			if(serviceList!=null && serviceList.size()>0){
				LinkAllInfoRequest linkAllInfoRequest=new LinkAllInfoRequest();
				linkAllInfoRequest.setSiteCode(siteCode);
				linkAllInfoRequest.setServiceList(serviceList);
				
				//按创建时间正序排列
				List<QueryOrder> querySiteList=new ArrayList<QueryOrder>();
				QueryOrder siteQueryOrder=new QueryOrder("create_time",QueryOrderType.ASC);
				querySiteList.add(siteQueryOrder);
				linkAllInfoRequest.setQueryOrderList(querySiteList);
				
				List<LinkAllInfo> queryList = linkAllInfoServiceImpl.queryList(linkAllInfoRequest);
				if(queryList !=null && queryList.size()>0){
					for (int i = 0; i < queryList.size(); i++) {
						LinkAllInfo linkAllInfo=queryList.get(i);
						HashMap<String, Object> map = new HashMap<String, Object>();
						map.put("periodNum", i+1);
						map.put("websiteSum", linkAllInfo.getWebsiteSum());
						list.add(map);
					}
				}
			}
			logger.info("linkAllUnuse list:"+list);
			writerPrint(JSONArray.fromObject(list).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * @Description: 全站链接不可用性折线图 [重构]
	 * @author luocheng --- 2017-03-16
	 */
	public void linkAllUnuseRebuild() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Object> list = new ArrayList<Object>();
		try {
			String siteCode = getCurrentUserInfo().getSiteCode();
			if (StringUtils.isEmpty(siteCode)) {
				siteCode = getCurrentUserInfo().getChildSiteCode();
			}
			String siteCodeTB = getCurrentUserInfo().getChildSiteCode();
			if (siteCode.length() == 6) {
				resultMap = databaseBizServiceImpl.characterCycle(siteCode,
						siteCodeTB);
			} else {
				resultMap = databaseBizServiceImpl.characterCycle(siteCode,
						null);
			}
			logger.info("linkAllUnuse siteCode:" + siteCode);
			List<ServicePeriod> serviceList = new ArrayList<ServicePeriod>();
			@SuppressWarnings("unchecked")
			List<HashMap<String, Object>> servicePeriodlist = (List<HashMap<String, Object>>) resultMap
					.get("scanCycleList");
			for (HashMap<String, Object> map : servicePeriodlist) {
				Integer spId = (Integer) map.get("id");
				ServicePeriod sp = servicePeriodServiceImpl.get(spId);
				if (sp != null) {
					serviceList.add(sp);
				}
			}
			Collections.reverse(serviceList);
			if (serviceList != null && serviceList.size() > 0) {
				int i = 0;
				for (ServicePeriod sList : serviceList) {
					LinkAllInfoRequest linkAllInfoRequest = new LinkAllInfoRequest();
					linkAllInfoRequest.setSiteCode(siteCodeTB); // 查询当前填报单位
					linkAllInfoRequest.setServicePeriodId(sList.getId()); // 查询对应周期下的
																			// 死链数据

					// 按创建时间正序排列
					List<QueryOrder> querySiteList = new ArrayList<QueryOrder>();
					QueryOrder siteQueryOrder = new QueryOrder("create_time",
							QueryOrderType.ASC);
					querySiteList.add(siteQueryOrder);
					linkAllInfoRequest.setQueryOrderList(querySiteList);
					SimpleDateFormat dateFormat = new SimpleDateFormat(
							"yyyy-MM-dd");
					Date oldDate = dateFormat.parse(Constants.OLDDATETIME);
					List<LinkAllInfo> queryList = linkAllInfoServiceImpl
							.queryList(linkAllInfoRequest);
					HashMap<String, Object> map = new HashMap<String, Object>();
					map.put("periodNum", ++i);
					if (sList.getStartDate().getTime() < oldDate.getTime()) {
						map.put("oldServicePeriod", 1); // 是老规则统计的 周期数据
					} else {
						map.put("oldServicePeriod", 2); // 是新规则统计的 周期数据
					}
					int websiteSum = 0;
					int webSiteDetailSum = 0;
					if (queryList != null && queryList.size() > 0) {
						LinkAllInfo linkAllInfo = queryList.get(0);
						websiteSum = linkAllInfo.getWebsiteSum();
						if(websiteSum == 0){
							LinkAllDetailRequest request = new LinkAllDetailRequest();
							DicConfig dicConfig = dicConfigServiceImpl.get(20);// 取出确认死链配置
							String[] strArray = dicConfig.getValue().split(",");
							request.setSiteCode(siteCodeTB);
							request.setWebType(1);// 站内
							request.setErrorCodeArr(strArray);
							request.setPageSize(Integer.MAX_VALUE);
							request.setServicePeriodId(sList.getId());
							List<LinkAllDetail> linkAllList = linkAllDetailServiceImpl
									.queryList(request);
							webSiteDetailSum = linkAllList.size();
							websiteSum = webSiteDetailSum;
							
							if(websiteSum == 0){
								if(webSiteDetailSum == 0){
									map.put("oldServicePeriod", 2); // 是老规则统计的 周期数据
								}
							}else if(websiteSum != 0){
								map.put("oldServicePeriod", 2); // 是新规则统计的 周期数据
							}
						}
						map.put("websiteSum",websiteSum);
						
					}else{
						map.put("websiteSum",websiteSum);
					} 
					
					map.put("periodStr",sList == null ? "" : sdf.format(sList.getStartDate())+ "至"+ sdf.format(sList.getEndDate()));
					list.add(map);
				}

			}

			logger.info("linkAllUnuse list:" + list);
			writerPrint(JSONArray.fromObject(list).toString());
			Collections.reverse(serviceList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description: 首页链接不可用性折线图
	 * @author cuichx --- 2015-11-9下午7:14:03
	 */
	public void linkHomeUnuse(){
		
		List<Object> homelist=new ArrayList<Object>();
		try {
			String siteCode=getCurrentUserInfo().getChildSiteCode();
			if(StringUtils.isEmpty(siteCode)){
				siteCode=getCurrentUserInfo().getSiteCode();
			}
			String endDate = DateUtils.getNextDay(queryHomePageDate(), "0");
			String nextDay = DateUtils.getNextDay(queryHomePageDate(), "-6");
			
			DetectionResultRequest detectionRequest=new DetectionResultRequest();
			detectionRequest.setSiteCode(siteCode);
			detectionRequest.setBeginScanDate(nextDay);
			detectionRequest.setEndScanDate(endDate);
			
			//排序
			List<QueryOrder> queryOrderList=new ArrayList<QueryOrder>();
			QueryOrder siteQueryOrder=new QueryOrder("scan_date",QueryOrderType.ASC);
			queryOrderList.add(siteQueryOrder);
			detectionRequest.setQueryOrderList(queryOrderList);
			
			List<DetectionResult> detectionList=detectionResultServiceImpl.queryList(detectionRequest);
			if(detectionList!=null && detectionList.size()>0){
				for (DetectionResult detectionResult : detectionList) {
					HashMap<String, Object> map = new HashMap<String, Object>();
					int errorNum = detectionResult.getLinkHome();
					String scanDate =DateUtils.formatStandardDate(detectionResult.getScanDate());
					
					map.put("errorNum", errorNum);
					map.put("scanDate", scanDate);
					homelist.add(map);
				}
			}
			writerPrint(JSONArray.fromObject(homelist).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description: 网站连通性--健康分析--折线图
	 * @author cuichx --- 2015-11-9下午8:41:22
	 */
	public void connectionAllBar(){
		
		Map<String,Object> resultMap=new HashMap<String, Object>();
		try {
			String siteCode=getCurrentUserInfo().getChildSiteCode();
			if(StringUtils.isEmpty(siteCode)){
				siteCode=getCurrentUserInfo().getSiteCode();
			}
			String endDate = DateUtils.getNextDay(queryHomePageDate(), "0");
			String nextDay = DateUtils.getNextDay(queryHomePageDate(), "-6");
			
			//通过网站标识码、昨天日期查询 检测结果列表
			DetectionResultRequest detectionRequest=new DetectionResultRequest();
			detectionRequest.setSiteCode(siteCode);
			detectionRequest.setBeginScanDate(nextDay);
			detectionRequest.setEndScanDate(endDate);
			
			//排序
			List<QueryOrder> queryOrderList=new ArrayList<QueryOrder>();
			QueryOrder siteQueryOrder=new QueryOrder("scan_date",QueryOrderType.ASC);
			queryOrderList.add(siteQueryOrder);
			detectionRequest.setQueryOrderList(queryOrderList);
			
			List<DetectionResult> detectionList=detectionResultServiceImpl.queryList(detectionRequest);
			
			ArrayList<Object> scanTimelist = new ArrayList<Object>();
			ArrayList<Object> homelist = new ArrayList<Object>();//首页不连通
			ArrayList<Object> businesslist = new ArrayList<Object>();//业务系统不连通
			ArrayList<Object> channellist = new ArrayList<Object>();//栏目不连通
			if(detectionList!=null && detectionList.size()>0){
				for (int i = 0; i < detectionList.size(); i++) {
					DetectionResult detectionResult = detectionList.get(i);
					int buseNum = detectionResult.getConnBusiness();
					businesslist.add(buseNum);
					int homeNum = detectionResult.getConnHome();
					homelist.add(homeNum);
					int channelNum =detectionResult.getConnChannel();
					channellist.add(channelNum);
					String scanDate = detectionResult.getScanDate();
					scanTimelist.add(scanDate);
				}
			}
			resultMap.put("businesslist", businesslist);
			resultMap.put("homelist", homelist);
			resultMap.put("channellist", channellist);
			resultMap.put("scanTimelist", scanTimelist);
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description: 内容更新--健康分析--折线图
	 * @author cuichx --- 2015-11-9下午9:54:14
	 */
	public void updateChAndHome(){
		
		Map<String,Object> resultMap=new HashMap<String, Object>();
		try {
			//获取页面参数
			String siteCode=getCurrentUserInfo().getChildSiteCode();
			if(StringUtils.isEmpty(siteCode)){
				siteCode=getCurrentUserInfo().getSiteCode();
			}
			logger.info("siteCode="+siteCode);

			String endDate = DateUtils.getNextDay(queryHomePageDate(), "0");
			String nextDay = DateUtils.getNextDay(queryHomePageDate(), "-6");
			 
			//检测结果列表
			DetectionResultRequest detectionRequest=new DetectionResultRequest();
			detectionRequest.setSiteCode(siteCode);
			detectionRequest.setBeginScanDate(nextDay);
			detectionRequest.setEndScanDate(endDate);
			
			//排序
			List<QueryOrder> queryOrderList=new ArrayList<QueryOrder>();
			QueryOrder siteQueryOrder=new QueryOrder("scan_date",QueryOrderType.ASC);
			queryOrderList.add(siteQueryOrder);
			detectionRequest.setQueryOrderList(queryOrderList);
			
			List<DetectionResult> detectionList=detectionResultServiceImpl.queryList(detectionRequest);
			ArrayList<Object> dateList = new ArrayList<Object>();//日期
			ArrayList<Object> homeList = new ArrayList<Object>();//栏目更新
			ArrayList<Object> channelList = new ArrayList<Object>();//首页更新
			if(detectionList!=null && detectionList.size()>0){
				for (int i = 0; i < detectionList.size(); i++) {
					DetectionResult detectionResult = detectionList.get(i);
					String scanDate = detectionResult.getScanDate();
					dateList.add(DateUtils.formatStandardDate(scanDate));
					int updateNum = detectionResult.getUpdateHome();
					homeList.add(updateNum);
					int channelNum = detectionResult.getUpdateChannel();
					channelList.add(channelNum);
				}
			}
			resultMap.put("dateList", dateList);
			resultMap.put("homeList", homeList);
			resultMap.put("channelList", channelList);
			logger.info("内容更新折线图数据："+resultMap);
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * @Description:内容正确性--健康分析--折线图
	 * @author cuichx --- 2015-11-24上午10:29:37
	 */
	public void correctContentLine(){
		Map<String, Object> resultMap=new HashMap<String, Object>();
		try {
			String siteCode=getCurrentUserInfo().getChildSiteCode();
			if(StringUtils.isEmpty(siteCode)){
					siteCode=getCurrentUserInfo().getSiteCode();
			}
			
			String endDate = DateUtils.getNextDay(queryHomePageDate(), "0");
			String nextDay = DateUtils.getNextDay(queryHomePageDate(), "-6");
			DetectionResultRequest detectionRequest=new DetectionResultRequest();
			detectionRequest.setSiteCode(siteCode);
			detectionRequest.setBeginScanDate(nextDay);
			detectionRequest.setEndScanDate(endDate);
			
			//排序
			List<QueryOrder> queryOrderList=new ArrayList<QueryOrder>();
			QueryOrder siteQueryOrder=new QueryOrder("scan_date",QueryOrderType.ASC);
			queryOrderList.add(siteQueryOrder);
			detectionRequest.setQueryOrderList(queryOrderList);
			
			List<DetectionResult> detectionList=detectionResultServiceImpl.queryList(detectionRequest);
			
			ArrayList<Object> correntList = new ArrayList<Object>();//内容正确性
			ArrayList<Object> timeList = new ArrayList<Object>();//时间
			if(detectionList!=null && detectionList.size()>0){
				for (int i = 0; i < detectionList.size(); i++) {
					DetectionResult detectionResult=detectionList.get(i);
					correntList.add(detectionResult.getContCorrectNum());//问题总数
					timeList.add(detectionResult.getScanDate());//日期
				}
			}
			resultMap.put("correntList", correntList);
			resultMap.put("timeList", timeList);
			logger.info("内容正确性折线图数据："+resultMap);
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description: 内容保障问题--健康分析--堆积图--付费版（按周期展示）
	 * @author cuichx --- 2015-11-24下午12:19:42
	 * 
	 * yangshuai --修改于-- 2016-6-13下午3:34:32
	 */
	public void securityLine(){
		Map<String,Object>  resultMap=new HashMap<String, Object>();
		try {
			String siteCode=getCurrentUserInfo().getChildSiteCode();
			if(StringUtils.isEmpty(siteCode)){
				siteCode=getCurrentUserInfo().getSiteCode();
			}
			logger.info("securityLine siteCode:"+siteCode);
			
			/*HashMap<String, Object> paramMap = new HashMap<String, Object>();
            //获取前90天的开始时间
			 String nextDay = DateUtils.getNextDay(new Date(), -91);
			//获取昨天的日期
			 String endDate = DateUtils.getNextDay(new Date(), -1);
			paramMap.put("siteCode", siteCode);
			paramMap.put("beginScanDate", nextDay);
			paramMap.put("endScanDate", endDate);*/
			
			String endDate = DateUtils.getNextDay(queryHomePageDate(), "0");
			String nextDay = DateUtils.getNextDay(queryHomePageDate(), "-6");
			DetectionResultRequest detectionResultRequest = new DetectionResultRequest();
			detectionResultRequest.setSiteCode(siteCode);
			detectionResultRequest.setBeginScanDate(nextDay);
			detectionResultRequest.setEndScanDate(endDate);
			
			List<QueryOrder> queryOrderList = new ArrayList<QueryOrder>();
			QueryOrder queryOrder = new QueryOrder("scan_date",QueryOrderType.ASC);
			queryOrderList.add(queryOrder);
			detectionResultRequest.setQueryOrderList(queryOrderList);
			
			ArrayList<Object> dateList = new ArrayList<Object>();
			ArrayList<Object> homeList = new ArrayList<Object>();
			ArrayList<Object> channelList = new ArrayList<Object>();
			ArrayList<Object> blankList = new ArrayList<Object>();
			ArrayList<Object> resposeList = new ArrayList<Object>();
			ArrayList<Object> serviceList = new ArrayList<Object>();
			
//			List<SecurityHomeChannelRequest> lineList = securityHomeChannelServiceImpl.getLineList(paramMap);
			List<DetectionResult> lineList = detectionResultServiceImpl.queryList(detectionResultRequest);
			if(lineList.size()>0){
				/*for (SecurityHomeChannelRequest securityHomeChannelRequest : lineList) {
					dateList.add(DateUtils.formatStandardDate(securityHomeChannelRequest.getScanDate()));
	//						dateList.add(securityHomeChannelRequest.getScanDate());
					homeList.add(securityHomeChannelRequest.getHomeNum());
					channelList.add(securityHomeChannelRequest.getChannelNum());
					blankList.add(securityHomeChannelRequest.getBlankChannel());
					resposeList.add(securityHomeChannelRequest.getSecurityResponse());
					serviceList.add(securityHomeChannelRequest.getSecurityService());
				}*/
				for (DetectionResult detectionResult : lineList) {
					dateList.add(detectionResult.getScanDate());
					homeList.add(detectionResult.getSecurityHome());
					channelList.add(detectionResult.getSecurityChannel());
					blankList.add(detectionResult.getSecurityBlank());
					resposeList.add(detectionResult.getSecurityResponse());
					serviceList.add(detectionResult.getSecurityService());
				}
	
				resultMap.put("dateList", dateList);
				resultMap.put("homeList", homeList);
				resultMap.put("channelList", channelList);
				resultMap.put("blankList", blankList);
				resultMap.put("resposeList", resposeList);
				resultMap.put("serviceList", serviceList);
				
				writerPrint(JSONObject.fromObject(resultMap).toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("errorMsg", "获取内容保障问题周期数据异常");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		}
	}
	/**
	 * @Description: 填报单位-首页-内容保障色块 数据查询
	 * @author luocheng--- 2017-03-24
	 */
	public void getTbSecuritySum(){
		int securitySum = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String yesterDayStr = DateUtils.getYesterdayStr(); //取得昨天
		try {
			String siteCode = getCurrentUserInfo().getSiteCode();
			if (StringUtils.isEmpty(siteCode)) {
				siteCode = getCurrentUserInfo().getChildSiteCode();
			}
			String siteCodeTB = getCurrentUserInfo().getChildSiteCode();
			//获得首页是否更新及时
			DetectionResultRequest dRequest = new DetectionResultRequest();
			dRequest.setSiteCode(siteCodeTB);
			dRequest.setScanDate(yesterDayStr);
			List<DetectionResult> dList = detectionResultServiceImpl.queryList(dRequest);
			if(CollectionUtils.isNotEmpty(dList) && dList.size()>0){
				DetectionResult dR = dList.get(0);
				if(dR.getSecurityHome() == 1){ //0为更新及时 1位不及时
					securitySum += 1;
				}
			}
			
			//查询昨天的基本信息的逾期未更新数据量
			SecurityHomeChannelRequest homeChannelRequest = new SecurityHomeChannelRequest(); // 逾期未更新天数
			homeChannelRequest.setSiteCode(siteCodeTB);
			homeChannelRequest.setAutoInput("auto");// 系统检测
													// 机器输入
			homeChannelRequest.setType(2); // 查询逾期 [栏目]
			homeChannelRequest.setSelectType("4");
			homeChannelRequest.setFlag(false); // 栏目类型
			// 查询出所有栏目的逾期
			homeChannelRequest.setPageSize(Integer.MAX_VALUE);
			homeChannelRequest.setScanDate(DateUtils.getYesterdayStr()); // 查询扫描时间在昨天的
			List<SecurityHomeChannel> securityHomeChannelList = securityHomeChannelServiceImpl
					.queryListTb(homeChannelRequest);
			if(CollectionUtils.isNotEmpty(securityHomeChannelList) && securityHomeChannelList.size() > 0){ 
				securitySum += securityHomeChannelList.size();
			}
			
			//获得当前基本信息的周期
			List<Map<String, Object>> spList = essentialinformationMonitoringPeriod();
			if(CollectionUtils.isNotEmpty(spList) && spList.size()>0){
				Map<String, Object> spMap= spList.get(0);
				int spId = (Integer) spMap.get("id");
				ServicePeriod sp = servicePeriodServiceImpl.get(spId);
				Date strat = sp.getStartDate();
				Date end = sp.getEndDate();
				if (yesterDayStr.compareTo(sdf.format(strat)) >= 0 && sdf.format(end).compareTo(yesterDayStr) >= 0) { //昨天在周期内
					homeChannelRequest = new SecurityHomeChannelRequest(); // 逾期未更新天数
					// 有高级版任务时候，默认展示人工检测页面
					homeChannelRequest.setSiteCode(siteCodeTB);
					homeChannelRequest.setNoAutoInput("noAuto"); //人工检测 非机器输入
					homeChannelRequest.setType(2); // 查询逾期 [栏目]
					homeChannelRequest.setSelectType("4");
					homeChannelRequest.setFlag(false); // 栏目类型 查询出所有栏目的逾期
					homeChannelRequest.setPageSize(Integer.MAX_VALUE);
					homeChannelRequest.setServicePeriodId(sp.getId()); // 查询属于此周期id的逾期未更新[人工查询]
					securityHomeChannelList = securityHomeChannelServiceImpl
							.queryListTb(homeChannelRequest);
					if(CollectionUtils.isNotEmpty(securityHomeChannelList) && securityHomeChannelList.size() > 0){ 
						securitySum += securityHomeChannelList.size();
					}
				}
			}
			
			//查询空白栏目等问题的周期
			if (siteCode.length() == 6) {
				resultMap = databaseBizServiceImpl.characterCycle(siteCode,
						siteCodeTB);
			} else {
				resultMap = databaseBizServiceImpl.characterCycle(siteCode,
						null);
			}
			@SuppressWarnings("unchecked")
			List<HashMap<String, Object>> servicePeriodlist = (List<HashMap<String, Object>>) resultMap
					.get("scanCycleList");
			if(CollectionUtils.isNotEmpty(servicePeriodlist) && servicePeriodlist.size() > 0){
				HashMap<String, Object> spMap = servicePeriodlist.get(0);
				int sppId =  (Integer) spMap.get("id");
				//获得最近的一个周期
				ServicePeriod sp = servicePeriodServiceImpl.get(sppId);
				Date strat = sp.getStartDate();
				Date end = sp.getEndDate();
				if (yesterDayStr.compareTo(sdf.format(strat)) >= 0 && sdf.format(end).compareTo(yesterDayStr) >= 0) { //昨天在周期内
					HashMap<String, Object> map = new HashMap<String, Object>();
					map.put("siteCode", siteCodeTB);
					map.put("servicePeriodId", sp.getId()); // 周期id
					List<SecurityGuaranteeResponse> blankList = securityBlankDetailServiceImpl.getBlankNumber(map);
					if(CollectionUtils.isNotEmpty(blankList) && blankList.size()>0){
						securitySum += blankList.get(0).getColumnNum();
					}
					List<SecurityGuaranteeResponse> responseList = securityResponseServiceImpl.getResponseNumber(map);
					if(CollectionUtils.isNotEmpty(responseList) && responseList.size()>0){
						securitySum += responseList.get(0).getColumnNum();
					}
					List<SecurityGuaranteeResponse> serviceList = securityServcieServiceImpl.getServiceNumber(map);
					if(CollectionUtils.isNotEmpty(serviceList) && serviceList.size()>0){
						securitySum += serviceList.get(0).getColumnNum();
					}
				}
			}
			Map<String, Object> numberMap = new HashMap<String, Object>();
			numberMap.put("securitySum", securitySum);
			writerPrint(JSONObject.fromObject(numberMap).toString());
		}catch (Exception e) {
			e.printStackTrace();
			logger.info("填报单位获取首页内容保障色块出现异常");
		}
	}
	
	
	/**
	 * @Description: 填报单位查询服务周期
	 * @author Liukl --- 2017年2月10日10:30:37   
	 * @return
	 */
	public List<Map<String, Object>>  essentialinformationMonitoringPeriod(){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			String siteCode = request.getParameter("siteCode");
			if(StringUtils.isEmpty(siteCode)){
				siteCode=getCurrentUserInfo().getSiteCode();
			}
			ServicePeriodRequest reques = new ServicePeriodRequest();
			// 老合同信息
			List<ContractInfo> crmlist = getContractInfoList(siteCode, DateUtils.formatStandardDate(new Date()));
			// 新产品信息
			// List<CrmProductsResponse> crmlist = new
			// ArrayList<CrmProductsResponse>();
			// crmlist = getCrmProductsList(siteCode, productTypeArr,
			// DateUtils.formatStandardDate(new Date()), null,
			// null);
			reques.setComboI(4);// 高级版任务
			List<ServicePeriodRequest> periodList = null;
			String isSenior = "";
			if (CollectionUtils.isNotEmpty(crmlist)) {
				isSenior = "2";
				for (ContractInfo crm : crmlist) {
					reques.setContractInfoId(crm.getId());
					reques.setStartDate(DateUtils.formatStandardDate(crm.getContractBeginTime()));
				}
				periodList = databaseBizServiceImpl.queryByRelationPeriodBasic(reques);
				if(periodList != null && periodList.size()>0){
					isSenior = "1";
				}
			}else{
				
				DatabaseTreeInfoRequest dbTreeRequetst = new DatabaseTreeInfoRequest();
				dbTreeRequetst.setSiteCode(siteCode);
				dbTreeRequetst.setIsBigdata(1);
				dbTreeRequetst.setPageSize(Integer.MAX_VALUE);
				List<DatabaseTreeInfo> treeList = databaseTreeInfoServiceImpl.queryList(dbTreeRequetst);
				String isSearchTb = "";
				if(treeList!=null && treeList.size()>0){
				for (DatabaseTreeInfo tree : treeList) {
				String zzSiteCode = tree.getOrgSiteCode();
						crmlist = getContractInfoList(zzSiteCode, DateUtils.formatStandardDate(new Date()));
						// crmlist = getCrmProductsList(zzSiteCode,
						// productTypeArr,
						// DateUtils.formatStandardDate(new Date()), null,
						// null);
				reques.setComboI(4);//高级版任务
				reques.setStartDate(DateUtils.formatStandardDate(new Date()));
						if (CollectionUtils.isNotEmpty(crmlist)) {
							for (ContractInfo crm : crmlist) {
								isSearchTb = String.valueOf(crm.getIsSearchTb());
								if (isSearchTb.equals("1")) {
									isSenior = "2";
									reques.setContractInfoId(crm.getId());
									periodList = databaseBizServiceImpl.queryByRelationPeriodBasic(reques);
									if (periodList != null && periodList.size() > 0) {
										isSenior = "1";
							}
								}
							}
						} else {
							isSenior = "2";
						}
			}}}
			//日期拼接
			if (periodList != null && periodList.size() > 0) {
				for (int i = 0; i < periodList.size(); i++) {
					HashMap<String, Object> map = new HashMap<String, Object>();
					StringBuffer sb = new StringBuffer(1000);
					ServicePeriodRequest servicePeriod = periodList.get(i);
					int spId = servicePeriod.getId();
					String startDate = servicePeriod.getStartDate();
					String endDate = servicePeriod.getEndDate();
					if (StringUtils.isEmpty(startDate)) {
						startDate = "";
					}
					if (StringUtils.isEmpty(endDate)) {
						endDate = "";
					}
					sb.append(startDate).append("至").append(endDate);   // 样式  2016-04-08至2016-05-08
					map.put("id", spId);
					map.put("cycleDate", sb.toString());
					list.add(map);
				}
			}
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		return list;
	}


	
	public String getSiteCode() {
		return siteCode;
	}
	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}
	public String getUrlStr() {
		return urlStr;
	}
	public void setUrlStr(String urlStr) {
		this.urlStr = urlStr;
	}
	
}
