package com.ucap.cloud_web.servlet;


import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javassist.compiler.ast.NewExpr;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.solr.common.util.Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


import com.publics.util.page.QueryOrder;
import com.publics.util.page.QueryOrderType;
import com.publics.util.utils.DateUtils;
import com.publics.util.utils.StringUtils;
import com.thoughtworks.xstream.mapper.Mapper.Null;
import com.ucap.cloud_web.UrlAdapterVar;
import com.ucap.cloud_web.action.BaseAction;
import com.ucap.cloud_web.bizService.DatabaseBizService;
import com.ucap.cloud_web.constant.AccountBindStatus;
import com.ucap.cloud_web.constant.AccountType;
import com.ucap.cloud_web.constant.ConnectionType;

import com.ucap.cloud_web.constant.ReceiveType;

import com.ucap.cloud_web.dto.AccountBindInfoRequest;
import com.ucap.cloud_web.dto.ConnectionAllRequest;
import com.ucap.cloud_web.dto.ContractInfoRequest;
import com.ucap.cloud_web.dto.CorrectContentDetailRequest;
import com.ucap.cloud_web.dto.DatabaseInfoRequest;
import com.ucap.cloud_web.dto.DatabaseLinkRequest;
import com.ucap.cloud_web.dto.DatabaseOrgInfoRequest;
import com.ucap.cloud_web.dto.DatabaseTreeInfoRequest;
import com.ucap.cloud_web.dto.DetectionOrgCountRequest;
import com.ucap.cloud_web.dto.DetectionPeriodOrgRequest;
import com.ucap.cloud_web.dto.DetectionPeriodSiteRequest;
import com.ucap.cloud_web.dto.DetectionPeroidCountRequest;
import com.ucap.cloud_web.dto.DetectionResultRequest;
import com.ucap.cloud_web.dto.EarlyDetailRequest;
import com.ucap.cloud_web.dto.EarlyDetailTempRequest;
import com.ucap.cloud_web.dto.IndexCountRequest;
import com.ucap.cloud_web.dto.LinkHomeAvailableRequest;
import com.ucap.cloud_web.dto.RelationsPeriodRequest;
import com.ucap.cloud_web.dto.ReportManageLogRequest;
import com.ucap.cloud_web.dto.SecurityHomeChannelRequest;
import com.ucap.cloud_web.dto.ServicePeriodRequest;
import com.ucap.cloud_web.dto.UpdateHomeDetailRequest;
import com.ucap.cloud_web.dto.xstream.Root;
import com.ucap.cloud_web.dto.xstream.XstreamXmlParseBeanUtil;
import com.ucap.cloud_web.entity.AccountBindInfo;
import com.ucap.cloud_web.entity.ConnectionAll;
import com.ucap.cloud_web.entity.ContractInfo;
import com.ucap.cloud_web.entity.CorrectContentDetail;
import com.ucap.cloud_web.entity.DatabaseInfo;
import com.ucap.cloud_web.entity.DatabaseOrgInfo;
import com.ucap.cloud_web.entity.DatabaseTreeInfo;
import com.ucap.cloud_web.entity.Detail;
import com.ucap.cloud_web.entity.DetectionOrgCount;
import com.ucap.cloud_web.entity.DetectionPeriodOrg;
import com.ucap.cloud_web.entity.DetectionPeriodSite;
import com.ucap.cloud_web.entity.DetectionPeroidCount;
import com.ucap.cloud_web.entity.DetectionResult;
import com.ucap.cloud_web.entity.EarlyDetail;
import com.ucap.cloud_web.entity.LinkHomeAvailable;
import com.ucap.cloud_web.entity.RelationsPeriod;
import com.ucap.cloud_web.entity.Result;
import com.ucap.cloud_web.entity.SecurityHomeChannel;
import com.ucap.cloud_web.entity.ServicePeriod;
import com.ucap.cloud_web.entity.UpdateHomeDetail;
import com.ucap.cloud_web.service.IAccountBindInfoService;
import com.ucap.cloud_web.service.IConnectionAllService;
import com.ucap.cloud_web.service.IContractInfoService;
import com.ucap.cloud_web.service.ICorrectContentDetailService;
import com.ucap.cloud_web.service.IDatabaseInfoService;
import com.ucap.cloud_web.service.IDatabaseLinkService;
import com.ucap.cloud_web.service.IDatabaseOrgInfoService;
import com.ucap.cloud_web.service.IDatabaseTreeInfoService;
import com.ucap.cloud_web.service.IDetectionOrgCountService;
import com.ucap.cloud_web.service.IDetectionPeriodOrgService;
import com.ucap.cloud_web.service.IDetectionPeriodSiteService;
import com.ucap.cloud_web.service.IDetectionPeroidCountService;
import com.ucap.cloud_web.service.IDetectionResultService;
import com.ucap.cloud_web.service.IDicConfigService;
import com.ucap.cloud_web.service.IEarlyDetailService;
import com.ucap.cloud_web.service.IEarlyDetailTempService;
import com.ucap.cloud_web.service.IIndexCountService;
import com.ucap.cloud_web.service.ILinkHomeAvailableService;
import com.ucap.cloud_web.service.IRelationsPeriodService;
import com.ucap.cloud_web.service.IReportManageLogService;
import com.ucap.cloud_web.service.ISecurityHomeChannelService;
import com.ucap.cloud_web.service.IServicePeriodService;
import com.ucap.cloud_web.service.IUpdateHomeDetailService;
import com.ucap.cloud_web.servlet.message.WeixinOauth2Token;
import com.ucap.cloud_web.servlet.util.CommonUtils;
import com.ucap.cloud_web.shiro.ShiroUser;

/**
 * <p>Description:微信版开发 ---基本功能（绑定、解绑、管理中心）</p>
 * <p>@Package：com.ucap.cloud_web.servlet </p>
 * <p>Title: TokenAction </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：cuichx </p>
 * <p>@date：2015-12-23下午7:37:20 </p>
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class NewTokenAction extends BaseAction{
	private static Log logger = LogFactory.getLog(NewTokenAction.class);
	@Autowired
	private  IAccountBindInfoService accountBindInfoServiceImpl;
	@Autowired
	private IDatabaseOrgInfoService databaseOrgInfoServiceImpl;
	@Autowired
	private IDatabaseInfoService databaseInfoServiceImpl;
	@Autowired
	private IDatabaseTreeInfoService databaseTreeInfoServiceImpl;
	@Autowired
	private IDetectionResultService detectionResultServiceImpl;
	@Autowired
	private IDetectionPeriodSiteService  detectionPeriodSiteServiceImpl;
	@Autowired
	private IDetectionOrgCountService detectionOrgCountServiceImpl;
	@Autowired
	private IDetectionPeriodOrgService detectionPeriodOrgServiceImpl;
	@Autowired
	private IIndexCountService indexCountServiceImpl;//健康指数统计表
	@Autowired
	private IEarlyDetailTempService earlyDetailTempServiceImpl;
	@Autowired
	private DatabaseBizService databaseBizServiceImpl;
	@Autowired
	private IDicConfigService dicConfigServiceImpl;
	@Autowired
	private UrlAdapterVar urlAdapterVar;

	//初始化页面数据使用
	private Map<String, Object> initMap=null;
	
	Map<String, Object> resultMap=new HashMap<String, Object>();
	
	//填报单位--是否收费 1收费 2免费
	private String isCost="2";
	//填报单位--是否高级版 1-高级版  2非高级版
	private String isAdvance="2";
	//填报单位--是否安全 1-安全  2非安全
	private String isSafte="2";
	
	//组织单位--是否收费 1收费 2免费
	private String isOrgCost="2";
	//组织单位--是否高级版 1-高级版  2非高级版
	private String isOrgAdvance="2";
	//组织单位--是否安全 1-安全  2非安全
	private String isOrgSafte="2";
	
	
	//String scanDate=DateUtils.getNextDay(new Date(), -2);//获取前天日期
	String scanDate=DateUtils.getYesterdayStr();//获取昨天日期
	//String scanDate="2017-05-14";//获取昨天日期
	String nowDate=DateUtils.formatStandardDate(new Date());//当前日期

	/**
	 * @Description:  健康指数排名所有
	 * @author cuichx --- 2017-3-28上午11:15:05
	 */
	public void getIndexCountAll(){
		
		Map<String, Object> returnMap=new HashMap<String, Object>();
		String siteCode=request.getParameter("siteCode");//网站标识码
		String checkType=request.getParameter("checkType");//组织机构级别
		logger.info("getIndexCountAll siteCode:"+siteCode+"  checkType："+checkType);
		try {
			//获取站点标识码集合
			Map<String, Object> paraMap=new HashMap<String, Object>();
			paraMap.put("orgSiteCode", siteCode);
			if(!"0".equals(checkType)){
				paraMap.put("layerType", checkType);
			}
			paraMap.put("isScan", 1);
			paraMap.put("isexp", 1);
			paraMap.put("isLink", 1);
			List<String>  treeList=databaseTreeInfoServiceImpl.queryDownSite2(paraMap);
			
	    	
	    	HashMap<String, Object> param=new HashMap<String, Object>();
	    	param.put("treeList", treeList);
	    	param.put("scanDate", scanDate);
	    	param.put("type", 1);
	    	
	    	List<Object> allList=new ArrayList<Object>();//存放所有健康指数排名
	    	List<IndexCountRequest> indexList=indexCountServiceImpl.queryListByMap(param);
	    	if(indexList!=null && indexList.size()>0){
				for(int i=0;i<indexList.size();i++){
					Map<String, Object> map=new HashMap<String, Object>();
					map.put("siteCode", indexList.get(i).getSiteCode());
					if(StringUtils.isNotEmpty(indexList.get(i).getSiteName())){
						map.put("siteName", indexList.get(i).getSiteName());
					}else{
						map.put("siteName", "");
					}
					//折算分数
					Double convertScores=indexList.get(i).getConvertScores();
					//健康指数分数
					String totalSum=getHealthScores(convertScores,1);

					map.put("totalSum", totalSum);
					allList.add(map);
				}
				returnMap.put("allList", allList);
	    	}else{
	    		returnMap.put("errorMsg", "暂无健康指数排名数据");
	    	}
	    	writerPrint(JSONObject.fromObject(returnMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
			returnMap.put("errorMsg", "获取健康指数排名异常");
			writerPrint(JSONObject.fromObject(returnMap).toString());
		}
	}
	/**
	 * @Description: 健康指数排名页面数据获取
	 * @author cuichx --- 2017-3-28上午11:14:51
	 */
	public void getIndexCount(){
		Map<String, Object> returnMap=new HashMap<String, Object>();
		String siteCode=request.getParameter("siteCode");//网站标识码
		String checkType=request.getParameter("checkType");//组织机构级别
		logger.info("getIndexCount siteCode:"+siteCode+"  checkType："+checkType);
		try {
			//获取站点标识码集合
			Map<String, Object> paraMap=new HashMap<String, Object>();
			paraMap.put("orgSiteCode", siteCode);
			if(!"0".equals(checkType)){
				paraMap.put("layerType", checkType);
			}
			paraMap.put("isScan", 1);
			paraMap.put("isexp", 1);
			paraMap.put("isLink", 1);
			List<String>  treeList=databaseTreeInfoServiceImpl.queryDownSite2(paraMap);
			
			if(treeList!=null && treeList.size()>0){
		    	HashMap<String, Object> param=new HashMap<String, Object>();
		    	param.put("treeList", treeList);
		    	param.put("scanDate", scanDate);
		    	param.put("type", 1);

		    	List<Object> firstList=new ArrayList<Object>();//存放排名前五位的数据
		    	List<Object> lastList=new ArrayList<Object>();//存放排名后五位的数据
		    	
				List<IndexCountRequest> indexList=indexCountServiceImpl.queryListByMap(param);
				if(indexList!=null && indexList.size()>0){
					if(indexList.size()<=5){//查询结果不足5条数据时，处理如下
						for(int i=0;i<indexList.size();i++){
							Map<String, Object> map=new HashMap<String, Object>();
							map.put("siteCode", indexList.get(i).getSiteCode());
							map.put("siteName", indexList.get(i).getSiteName());
							//折算分数
							Double convertScores=indexList.get(i).getConvertScores();
							//健康指数分数
							String totalSum=getHealthScores(convertScores,1);

							map.put("totalSum", totalSum);
							firstList.add(map);
						}
						for(int j=indexList.size()-1;j>=0;j--){
							Map<String, Object> map=new HashMap<String, Object>();
							map.put("siteCode", indexList.get(j).getSiteCode());
							map.put("siteName", indexList.get(j).getSiteName());
							//折算分数
							Double convertScores=indexList.get(j).getConvertScores();
							//健康指数分数
							String totalSum=getHealthScores(convertScores,1);
							map.put("totalSum", totalSum);
							lastList.add(map);
						}
					}else{//查询结果多余五条数据时，处理如下
						for(int i=0;i<5;i++){
							Map<String, Object> map=new HashMap<String, Object>();
							map.put("siteCode", indexList.get(i).getSiteCode());
							map.put("siteName", indexList.get(i).getSiteName());
							//折算分数
							Double convertScores=indexList.get(i).getConvertScores();
							//健康指数分数
							String totalSum=getHealthScores(convertScores,1);
							map.put("totalSum", totalSum);
							firstList.add(map);
						}
						for(int j=indexList.size()-1;j>indexList.size()-6;j--){
							Map<String, Object> map=new HashMap<String, Object>();
							map.put("siteCode", indexList.get(j).getSiteCode());
							map.put("siteName", indexList.get(j).getSiteName());
							//折算分数
							Double convertScores=indexList.get(j).getConvertScores();
							//健康指数分数
							String totalSum=getHealthScores(convertScores,1);
		            		map.put("totalSum", totalSum);
							lastList.add(map);
						}
						
					}
				}
				returnMap.put("firstList", firstList);
				returnMap.put("lastList", lastList);
			}else{
				returnMap.put("errorMsg", "站点信息为空");
				
			}
			logger.info("returnMap:"+returnMap);
			writerPrint(JSONObject.fromObject(returnMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * @Description:健康指数排名页面初始化 
	 * @author cuichx --- 2016-1-5下午10:09:04     
	 * @return
	 */
	public String indexCountIndex(){
		initMap=new HashMap<String, Object>();
		String siteCode=request.getParameter("siteCode");//网站标识码
		String checkType=request.getParameter("checkType");//组织机构级别
		String openId=request.getParameter("openId");//微信唯一标识
		
		logger.info("indexCountIndex siteCode:"+siteCode+"  checkType:"+checkType+"   openId:"+openId);
		initMap.put("openId", openId);
		initMap.put("siteCode", siteCode);
		initMap.put("checkType", checkType);
		
		return "newWeiXin";
	}
	/**
	 * @Description: 组织单位页面初始化
	 * @author cuichx --- 2017-3-23下午8:37:40     
	 * @return
	 */
	public void checkResultOrg(){
		String checkType=request.getParameter("checkType");//选中的下拉框
		String orgSiteCode=request.getParameter("siteCode");//网站标识码
		String isOrgAdvance=request.getParameter("isOrgAdvance");//是否高级版
		String isOrgCost=request.getParameter("isOrgCost");//是否标准版
		String servicePeroidId=request.getParameter("servicePeroidId");//服务周期
		try {
			if(StringUtils.isEmpty(orgSiteCode)){
				resultMap.put("errorMsg", "组织机构编码不能为空");
				writerPrint(JSONObject.fromObject(resultMap).toString());
			}
			if(StringUtils.isEmpty(isOrgAdvance)){
				resultMap.put("errorMsg", "是否高级版不能为空");
				writerPrint(JSONObject.fromObject(resultMap).toString());
			}
			
			if("1".equals(checkType)){
				//获取本机门户网站标识码
				Map<String, Object> paramMap=new HashMap<String, Object>();
				paramMap.put("orgSiteCode", orgSiteCode);
				paramMap.put("layerType", 1);
				paramMap.put("isexp", 1);
				paramMap.put("isLink", 1);
				List<String>  codeList=databaseTreeInfoServiceImpl.queryDownSite2(paramMap);
				if(codeList!=null && codeList.size()>0){
					getCheckResultMHData(codeList.get(0), isOrgAdvance,Integer.valueOf(servicePeroidId));
				}else{
					resultMap.put("errorMsg", "本机门户网站是关停网站或者例外网站");
				}
			}else{//非本机门户
				getCheckResultOrgData(orgSiteCode, checkType, isOrgCost, isOrgAdvance,Integer.valueOf(servicePeroidId));
			}
			System.out.println(JSONObject.fromObject(resultMap).toString());
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("errorMsg", "页面初始化数据异常");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		}
	}
	
	/**
	 * @Description: 组织单位----本机门户数据总计
	 * @author cuichx --- 2017-3-23下午8:37:40     
	 * @return
	 */
	public void getCheckResultMHData(String siteCode,String isAdvance,Integer servicePeroidId){
		
		Double convertScores=1.0;//折算分数
		String indexCount="0";//健康指数
		Integer connHome=0;//首页不连通数
		String leadAvgRate="0.00%";//健康指数领先全国
		Integer connChannel=0;//关键栏目不连通  栏目数
		Integer connBusiness=0;//业务系统不连通  栏目数
		Integer updateHome=0;//首页更新量
		Integer updateChannel=0;//栏目更新量
		Integer linkHome=0;//首页不可用链接
		Integer securityHome=0;//首页更新不及时
		Integer securityChannel=0;//栏目更新不及时
		Integer contCorrectNum=0;//错别字
		
		DetectionResultRequest dtRequest=new DetectionResultRequest();
		dtRequest.setSiteCode(siteCode);
		dtRequest.setScanDate(scanDate);
		try {
			List<DetectionResult>  dtList=detectionResultServiceImpl.queryList(dtRequest);
			if(dtList!=null && dtList.size()>0){
				DetectionResult detectionResult=dtList.get(0);
				 convertScores=detectionResult.getConvertScores();//折算分数
				 indexCount=detectionResult.getIndexCount();//健康指数
				/**
				 * 首页不连通数  实时获取
				 */
				 connHome=getConnHomeTbSsCount(siteCode);//首页不连通数
				 leadAvgRate=detectionResult.getLeadAvgRate()+"%";//健康指数领先全国
				 connChannel=detectionResult.getConnChannelNum();//关键栏目不连通  栏目数
				 connBusiness=detectionResult.getConnBusinessNum();//业务系统不连通  栏目数
				 updateHome=detectionResult.getUpdateHome();//首页更新量
				 updateChannel=detectionResult.getUpdateChannel();//栏目更新量
				 linkHome=detectionResult.getLinkHome();//首页不可用链接
				 securityHome=detectionResult.getSecurityHome();//首页更新不及时
				 securityChannel=detectionResult.getSecurityChannel();//栏目更新不及时
				 contCorrectNum=detectionResult.getContCorrectNum();//错别字
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		resultMap.put("typeSite", 1);
		resultMap.put("convertScores", convertScores);
		resultMap.put("indexCount", indexCount);
		resultMap.put("leadAvgRate", leadAvgRate);
		resultMap.put("connHome", connHome);
		resultMap.put("updateHome", updateHome);
		if(updateHome==null || updateHome==0){
			resultMap.put("updateHomeSite", 0);
		}else{
			resultMap.put("updateHomeSite", 1);
		}
		
		resultMap.put("securityHome", securityHome);
		resultMap.put("linkHome", linkHome);
		if(linkHome==null || linkHome==0){
			resultMap.put("linkHomeSite", 0);
		}else{
			resultMap.put("linkHomeSite", 1);
		}
		resultMap.put("connChannel", connChannel);
		if(connChannel==null || connChannel==0){
			resultMap.put("connChannelSite", 0);
		}else{
			resultMap.put("connChannelSite", 1);
		}
		resultMap.put("connBusiness", connBusiness);
		if(connBusiness==null || connBusiness==0){
			resultMap.put("connBusinessSite", 0);
		}else{
			resultMap.put("connBusinessSite", 1);
		}
		resultMap.put("updateChannel", updateChannel);
		if(updateChannel==null || updateChannel==0){
			resultMap.put("updateChannelSite", 0);
		}else{
			resultMap.put("updateChannelSite", 1);
		}
		resultMap.put("securityChannel", securityChannel);
		if(securityChannel==null || securityChannel==0){
			resultMap.put("securityChannelSite", 0);
		}else{
			resultMap.put("securityChannelSite", 1);
		}
		resultMap.put("contCorrectNum", contCorrectNum);
		if(contCorrectNum==null || contCorrectNum==0){
			resultMap.put("contCorrectSite", 0);
		}else{
			resultMap.put("contCorrectSite", 1);
		}
		
		
		/**
		 * 高级版需要统计人工问题
		 */
		Integer linkAll=0;//全站死链
		Integer securityBlank=0;//空白栏目
		Integer securityResponse=0;//互动回应差
		Integer securityService=0;//服务不实用
		Integer securityFatalError=0;//严重问题
		if(StringUtils.isNotEmpty(isAdvance) && isAdvance.equals("1")){//高级版需要统计人工问题
			DetectionPeriodSiteRequest periodSiteRequest=new DetectionPeriodSiteRequest();
			periodSiteRequest.setSiteCode(siteCode);
			periodSiteRequest.setServicePeroidId(servicePeroidId);
			periodSiteRequest.setNowDate(DateUtils.formatStandardDate(new Date()));
			try {
				List<DetectionPeriodSite>  periodSiteList=detectionPeriodSiteServiceImpl.queryList(periodSiteRequest);
				if(periodSiteList!=null && periodSiteList.size()>0){
					DetectionPeriodSite detectionPeriodSite=periodSiteList.get(0);
					
					 linkAll=detectionPeriodSite.getLinkAll();//全站死链
					 securityBlank=detectionPeriodSite.getSecurityBlank();//空白栏目
					 securityResponse=detectionPeriodSite.getSecurityResponse();//互动回应差
					 securityService=detectionPeriodSite.getSecurityService();//服务不实用
					 securityFatalError=detectionPeriodSite.getSecurityFatalError();//严重问题
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		resultMap.put("servicePeroidId", servicePeroidId!=null?servicePeroidId:"");
		resultMap.put("securityResponse", securityResponse);
		if(securityResponse==null || securityResponse==0){
			resultMap.put("securityResponseSite", 0);
		}else{
			resultMap.put("securityResponseSite", 1);
		}
		resultMap.put("securityService", securityService!=null?securityService:0);
		if(securityService==null || securityService==0){
			resultMap.put("securityServiceSite", 0);
		}else{
			resultMap.put("securityServiceSite", 1);
		}
		resultMap.put("securityBlank", securityBlank!=null?securityBlank:0);
		if(securityBlank==null || securityBlank==0){
			resultMap.put("securityBlankSite", 0);
		}else{
			resultMap.put("securityBlankSite", 1);
		}
		resultMap.put("securityFatalError", securityFatalError!=null?securityFatalError:0);
		if(securityFatalError==null || securityFatalError==0){
			resultMap.put("securityFatalSite", 0);
		}else{
			resultMap.put("securityFatalSite", 1);
		}
		resultMap.put("linkAll", linkAll!=null?linkAll:0);
		if(linkAll==null || linkAll==0){
			resultMap.put("linkAllSite", 0);
		}else{
			resultMap.put("linkAllSite", 1);
		}
	}
	
	/**
	 * @Description: 组织单位----非本机门户数据总计
	 * @author cuichx --- 2017-3-23下午8:37:40     
	 * @return
	 */
	public void getCheckResultOrgData(String orgSiteCode,String checkType,String isOrgCost,String isOrgAdvance,Integer servicePeroidId){
		/**
		 * 日常监测数据
		 */
		Integer typeSite=0;//各类型站点数
		String indexCount="0";//健康指数
		String  leadAvgRate="0.00%";//领先全国
		Integer connHome=0;//首页首页连不通次数  
		Integer updateHome=0;//首页更新总数
		Integer updateHomeSite=0;//首页更新站点数
		Integer securityHome=0;//首页更细不及时
		Integer linkHome=0;//首页链接不可用总数
		Integer linkHomeSite=0;//首页链接不可用站点数
		
		/**
		 * 标准版
		 */
		//关键栏目连不通
		Integer connChannel=0;
		Integer connChannelSite=0;//栏目数
		//业务系统连不通
		Integer connBusiness=0;
		Integer connBusinessSite=0;//栏目数
		//栏目内容更新量
		Integer updateChannel=0;
		Integer updateChannelSite=0;
		//栏目更新不及时
		Integer securityChannel=0;
		Integer securityChannelSite=0;
		//错别字
		Integer contCorrectNum=0;
		Integer contCorrectSite=0;
		
		/**
		 * 高级版
		 */
		//互动回应差
		Integer securityResponse=0;
		Integer securityResponseSite=0;
		//服务不实用
		Integer securityService=0;
		Integer securityServiceSite=0;
		//空白栏目
		Integer securityBlank=0;
		Integer securityBlankSite=0;
		//严重问题
		Integer securityFatalError=0;
		Integer securityFatalSite=0;
		//全站死链
		Integer linkAll=0;
		Integer linkAllSite=0;
		
		try {
			//查询组织单位首页统计数据
			DetectionOrgCountRequest dtOrgCountRequest=new DetectionOrgCountRequest();
			dtOrgCountRequest.setSiteCode(orgSiteCode);
			dtOrgCountRequest.setScanDate(scanDate);
			if(StringUtils.isNotEmpty(checkType)){
				dtOrgCountRequest.setType(checkType);
			}
			List<DetectionOrgCount>  dtOrgList=detectionOrgCountServiceImpl.queryList(dtOrgCountRequest);
			if(dtOrgList!=null && dtOrgList.size()>0){
				
				DetectionOrgCount detectionOrgCount=dtOrgList.get(0);
				
				 typeSite=detectionOrgCount.getTypeSite();//各类型站点数
				 indexCount=detectionOrgCount.getIndexCount();//健康指数
				 leadAvgRate=detectionOrgCount.getLeadAvgRate()+"%";//领先全国
				//首页首页连不通次数  
				 connHome=getConnHomeOrgSsCount(orgSiteCode, checkType);
				
				 updateHome=detectionOrgCount.getUpdateHome();//首页更新总数
				 updateHomeSite=detectionOrgCount.getUpdateHomeSite();//首页更新站点数
				 securityHome=detectionOrgCount.getSecurityHome();//首页更细不及时
				 linkHome=detectionOrgCount.getLinkHome();//首页链接不可用总数
				 linkHomeSite=detectionOrgCount.getLinkHomeSite();//首页链接不可用站点数
				
				//标准版数据
				if("1".equals(isOrgCost)){
					
					//关键栏目连不通
					 connChannel=detectionOrgCount.getConnChannelNum();//栏目数
					 connChannelSite=detectionOrgCount.getConnChannelSite();
					//业务系统连不通
					 connBusiness=detectionOrgCount.getConnBusinessNum();//栏目数
					 connBusinessSite=detectionOrgCount.getConnBusinessSite();
					//栏目内容更新量
					 updateChannel=detectionOrgCount.getUpdateChannel();
					 updateChannelSite=detectionOrgCount.getUpdateChannelSite();
					//栏目更新不及时
					 securityChannel=detectionOrgCount.getSecurityChannel();
					 securityChannelSite=detectionOrgCount.getSecurityChannelSite();
					//错别字
					 contCorrectNum=detectionOrgCount.getContCorrectNum();
					 contCorrectSite=detectionOrgCount.getContCorrectSite();
				}
			}
			//高级版数据
			if("1".equals(isOrgAdvance)){
				DetectionPeriodOrgRequest dtOrgPeriodOrgRequest=new DetectionPeriodOrgRequest();
				dtOrgPeriodOrgRequest.setSiteCode(orgSiteCode);
				dtOrgPeriodOrgRequest.setServicePeroidId(servicePeroidId);
				if(StringUtils.isNotEmpty(checkType)){
					dtOrgPeriodOrgRequest.setType(Integer.valueOf(checkType));
				}
				dtOrgPeriodOrgRequest.setNowDate(scanDate);
				
				List<DetectionPeriodOrg>  periodOrgList=detectionPeriodOrgServiceImpl.queryList(dtOrgPeriodOrgRequest);
				if(periodOrgList!=null && periodOrgList.size()>0){
					DetectionPeriodOrg detectionPeriodOrg=periodOrgList.get(0);
					 servicePeroidId= detectionPeriodOrg.getServicePeroidId();//服务周期id
					//互动回应差
					 securityResponse=detectionPeriodOrg.getSecurityResponse();
					 securityResponseSite=detectionPeriodOrg.getSecurityResponseSite();
					//服务不实用
					 securityService=detectionPeriodOrg.getSecurityService();
					 securityServiceSite=detectionPeriodOrg.getSecurityServiceSite();
					//空白栏目
					 securityBlank=detectionPeriodOrg.getSecurityBlank();
					 securityBlankSite=detectionPeriodOrg.getSecurityBlankSite();
					//严重问题
					 securityFatalError=detectionPeriodOrg.getSecurityFatalError();
					 securityFatalSite=detectionPeriodOrg.getSecurityFatalSite();
					//全站死链
					 linkAll=detectionPeriodOrg.getLinkAll();
					 linkAllSite=detectionPeriodOrg.getLinkAllSite();
				}
			}
			resultMap.put("typeSite", typeSite);
			resultMap.put("indexCount", indexCount);
			resultMap.put("leadAvgRate", leadAvgRate);
			
			resultMap.put("connHome", connHome);
			resultMap.put("updateHome", updateHome);
			resultMap.put("updateHomeSite", updateHomeSite);
			resultMap.put("securityHome", securityHome);
			resultMap.put("linkHome", linkHome);
			resultMap.put("linkHomeSite", linkHomeSite);
			
			resultMap.put("connChannel", connChannel);
			resultMap.put("connChannelSite", connChannelSite);
			resultMap.put("connBusiness", connBusiness);
			resultMap.put("connBusinessSite", connBusinessSite);
			resultMap.put("updateChannel", updateChannel);
			resultMap.put("updateChannelSite", updateChannelSite);
			resultMap.put("securityChannel", securityChannel);
			resultMap.put("securityChannelSite", securityChannelSite);
			resultMap.put("contCorrectNum", contCorrectNum);
			resultMap.put("contCorrectSite", contCorrectSite);
			
			resultMap.put("servicePeroidId", servicePeroidId!=null?servicePeroidId:"");
			resultMap.put("securityResponse", securityResponse);
			resultMap.put("securityResponseSite", securityResponseSite);
			resultMap.put("securityService", securityService);
			resultMap.put("securityServiceSite", securityServiceSite);
			resultMap.put("securityBlank", securityBlank);
			resultMap.put("securityBlankSite", securityBlankSite);
			resultMap.put("securityFatalError", securityFatalError);
			resultMap.put("securityFatalSite", securityFatalSite);
			resultMap.put("linkAll", linkAll);
			resultMap.put("linkAllSite", linkAllSite);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * @Description: 检测结果--组织单位首页面
	 * @author cuichx --- 2015-12-21上午10:14:34     
	 * @return
	 */
	public String checkResultOrgIndex(){
		initMap=new HashMap<String, Object>();
		String openId=request.getParameter("openId");
		String siteCode=request.getParameter("siteCode");
		String siteName=request.getParameter("siteName");
		logger.info("=======checkResultOrgIndex openId:"+openId
				+"===siteCode:"+siteCode+"====siteName:"+siteName);
		try {
			if(StringUtils.isNotEmpty(openId)){
				//根据标识码判断是否有高级版合同 
				Map<String, Object> map1=weiXinShowOrg(siteCode);
				isOrgCost=String.valueOf(map1.get("isOrgCost"));
				isOrgAdvance= String.valueOf(map1.get("isOrgAdvance"));
				
				logger.info("isOrgCost============="+isOrgCost+"====servicePeroidId:"+String.valueOf(map1.get("servicePeroidId")));
				initMap.put("servicePeroidId", String.valueOf(map1.get("servicePeroidId")));
				initMap.put("isOrgCost", isOrgCost);
				initMap.put("isOrgAdvance", isOrgAdvance);
				initMap.put("siteName", siteName);
				initMap.put("siteCode", siteCode);
				initMap.put("openId", openId);
				
			}else{
				initMap.put("errorMsg", "微信用户的唯一标识不能为空");
			}
		} catch (Exception e) {
			e.printStackTrace();
			initMap.put("errorMsg", "获取检测结果失败！");
		}
		return "newWeiXin";
	}
	
	
	/**
	 * @Description: 填报单位页面初始化
	 * @author cuichx --- 2017-3-23下午8:37:40     
	 * @return
	 */
	public void checkResultTB(){
		String siteCode=request.getParameter("siteCode");//网站标识码
		String isAdvance=request.getParameter("isAdvance");//是否高级版
		
		String servicePeroidId=request.getParameter("servicePeroidId");//服务周期
		
		logger.info("========checkResultTB siteCode:"+siteCode+"====isAdvance:"+isAdvance+"=======servicePeroidId:"+servicePeroidId);
		try {
			Double convertScores=1.0;//折算分数
			String indexCount="0";//健康指数
			String leadAvgRate="0.00%";//健康指数领先全国
			Integer connHome=0;//今日不连通个数
			Integer connChannel=0;//关键栏目不连通栏目个数
			Integer connBusiness=0;//业务系统不连通栏目个数
			Integer updateHome=0;//首页更新量
			Integer updateChannel=0;//栏目更新量
			Integer linkHome=0;//首页不可用链接
			Integer securityHome=0;//首页更新不及时
			Integer securityChannel=0;//栏目更新不及时
			Integer contCorrectNum=0;//错别字
			
			DetectionResultRequest dtRequest=new DetectionResultRequest();
			dtRequest.setSiteCode(siteCode);
			dtRequest.setScanDate(scanDate);
			List<DetectionResult>  dtList=detectionResultServiceImpl.queryList(dtRequest);
			if(dtList!=null && dtList.size()>0){
				DetectionResult detectionResult=dtList.get(0);
				 convertScores=detectionResult.getConvertScores();//折算分数
				 indexCount=detectionResult.getIndexCount();//健康指数
				/**
				 * 当日连不通次数--调用借口
				 */
				String uri = databaseBizServiceImpl.getDatabaseUrl(siteCode); // 当前站点url
				String type = ConnectionType.HOME.getCode().toString();
				String connCode=dicConfigServiceImpl.get(2).getValue();// url连通性接口错误编码
				try {
					Root root = connectivityByRoot(siteCode, nowDate, type,connCode);
					if(root.getResponse().equals("success")){
						List<Result> ResultList = root.getResults();
						if(ResultList!=null && ResultList.size()>0){
							Result result=ResultList.get(0);//获取最新的一条
							if (StringUtils.isNotEmpty(result.getUrl())) {
								if(uri.equals(result.getUrl())){
									List<Detail> detailList=result.getDetails();
			    					if(detailList!=null && detailList.size()>0){
			    						connHome=detailList.size();
			    					}
								}
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				 leadAvgRate=detectionResult.getLeadAvgRate();//健康指数领先全国
				 connChannel=detectionResult.getConnChannelNum();//关键栏目不连通栏目个数
				 connBusiness=detectionResult.getConnBusinessNum();//业务系统不连通栏目个数
				 updateHome=detectionResult.getUpdateHome();//首页更新量
				 updateChannel=detectionResult.getUpdateChannel();//栏目更新量
				 linkHome=detectionResult.getLinkHome();//首页不可用链接
				 securityHome=detectionResult.getSecurityHome();//首页更新不及时
				 securityChannel=detectionResult.getSecurityChannel();//栏目更新不及时
				 contCorrectNum=detectionResult.getContCorrectNum();//错别字
			}
			
			resultMap.put("convertScores", convertScores!=null?convertScores:1);
			resultMap.put("indexCount", indexCount!=null?indexCount:0);
			resultMap.put("leadAvgRate", leadAvgRate!=null?leadAvgRate+"%":"0.00%");
			resultMap.put("connHome", connHome);
			resultMap.put("connChannel", connChannel!=null?connChannel:0);
			resultMap.put("connBusiness", connBusiness!=null?connBusiness:0);
			resultMap.put("updateHome", updateHome!=null?updateHome:0);
			resultMap.put("updateChannel", updateChannel!=null?updateChannel:0);
			resultMap.put("linkHome", linkHome!=null?linkHome:0);
			resultMap.put("securityHome", securityHome!=null?securityHome:0);
			resultMap.put("securityChannel", securityChannel!=null?securityChannel:0);
			resultMap.put("contCorrectNum", contCorrectNum!=null?contCorrectNum:0);
			
			
			
			/**
			 * 高级版需要统计人工问题
			 */
			Integer linkAll=0;//全站死链
			Integer securityBlank=0;//空白栏目
			Integer securityResponse=0;//互动回应差
			Integer securityService=0;//服务不实用
			Integer securityFatalError=0;//严重问题
			
			if(StringUtils.isNotEmpty(isAdvance) && isAdvance.equals("1")){
				DetectionPeriodSiteRequest periodSiteRequest=new DetectionPeriodSiteRequest();
				periodSiteRequest.setSiteCode(siteCode);
				periodSiteRequest.setServicePeroidId(Integer.valueOf(servicePeroidId));
				
				List<QueryOrder> queryOrderList=new ArrayList<QueryOrder>();
				QueryOrder siteQueryOrder=new QueryOrder("create_time",QueryOrderType.DESC);
				queryOrderList.add(siteQueryOrder);
				periodSiteRequest.setQueryOrderList(queryOrderList);
				
				List<DetectionPeriodSite>  periodSiteList=detectionPeriodSiteServiceImpl.queryList(periodSiteRequest);
				if(periodSiteList!=null && periodSiteList.size()>0){
					 DetectionPeriodSite detectionPeriodSite=periodSiteList.get(0);
					 linkAll=detectionPeriodSite.getLinkAll();//全站死链
					 securityBlank=detectionPeriodSite.getSecurityBlank();//空白栏目
					 securityResponse=detectionPeriodSite.getSecurityResponse();//互动回应差
					 securityService=detectionPeriodSite.getSecurityService();//服务不实用
					 securityFatalError=detectionPeriodSite.getSecurityFatalError();//严重问题
				}

			}
			resultMap.put("linkAll", linkAll);
			resultMap.put("securityBlank", securityBlank);
			resultMap.put("securityResponse", securityResponse);
			resultMap.put("securityService", securityService);
			resultMap.put("securityFatalError", securityFatalError);
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("errorMsg", "页面初始化数据异常");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		}
	}
	/**
	 * @Description: 检测结果--填报单位首页面
	 * @author cuichx --- 2015-12-21上午10:14:34     
	 * @return
	 */
	public String checkResultTBIndex(){
		initMap=new HashMap<String, Object>();
		try {
			String openId=request.getParameter("openId");
			if(StringUtils.isEmpty(openId)){
				String code=request.getParameter("code");
				logger.info("checkResultTBIndex openId:"+openId+"  code:"+code);
				WeixinOauth2Token weixinOauth2Token=CommonUtils.getWeixinOauth2Token(code);
			    //用户唯一标识
			    openId=weixinOauth2Token.getOpenId();
			}
			//String openId="oTfZiwy1Mc14i3w97q67B1Nt9BTM";
			//String openId="oTfZiw4AY4OB9B78s752TI1Yt0JA";
			logger.info("checkResultTBIndex openId:"+openId);
			
			if(StringUtils.isNotEmpty(openId)){
				//根据微信用户唯一标识查询绑定帐户信息表
				List<AccountBindInfo> accBindList=getAccountList(openId);
				if(accBindList!=null && accBindList.size()>0){
					String siteCode=accBindList.get(0).getSiteCode();//绑定网站标识码
					String siteName=accBindList.get(0).getSiteName();//标题
					if(siteCode.length()==10){//填报单位
						//根据标识码判断是否有高级版合同  判断填报单位是否买单  如果填报单位买单  查询填报单位
						Map<String, Object> map1=weiXinShowTb(siteCode);
						isCost=String.valueOf(map1.get("isCost"));
						isAdvance= String.valueOf(map1.get("isAdvance"));
						
						logger.info("==================servicePeroidId:"+ String.valueOf(map1.get("servicePeroidId")));
						
						initMap.put("servicePeroidId", String.valueOf(map1.get("servicePeroidId")));
						initMap.put("isCost", isCost);
						initMap.put("isAdvance", isAdvance);
						initMap.put("siteName", siteName);
						initMap.put("siteCode", siteCode);
						initMap.put("openId", openId);
					}else{//组织单位
						request.getRequestDispatcher("/newToken_checkResultOrgIndex.action?openId="+openId
								+"&siteCode="+siteCode+"&siteName="+siteName).forward(request, response);
					}
				}else{//帐号未绑定，跳转到绑定帐户页面,同时给微信客户发送一条提示信息
			    	request.getRequestDispatcher("/newToken_accountBindIndex.action?openId="+openId+"&fromType=checkResult").forward(request, response);
			    }
			}else{
				initMap.put("errorMsg", "微信用户的唯一标识不能为空");
			}
		} catch (Exception e) {
			e.printStackTrace();
			initMap.put("errorMsg", "获取检测结果失败！");
		}
		return "newWeiXin";
	}
	 /**
     * @Description: 管理中心页面--点解解除绑定按钮触发事件--组织单位或者填报单位
     * @author cuichx --- 2015-12-28上午10:39:49
     */
    public void removeBindAccount(){
    	Map<String, Object> returnMap=new HashMap<String, Object>();
    	
    	String openId=request.getParameter("openId");
    	logger.info("====removeBindAccount openId:"+openId);
    	try {
    		if(StringUtils.isNotEmpty(openId)){
            	//通过客户唯一标识查询帐户绑定信息表
            	List<AccountBindInfo> accountList=getAccountList(openId);
            	if(accountList!=null && accountList.size()>0){
            		AccountBindInfo accountBindInfo=accountList.get(0);
            		accountBindInfo.setStatus(AccountBindStatus.ACCOUNT_UNBIND.getCode());
            		//解绑时，将微信绑定帐户信息表中的绑定状态置为无效（解绑）
            		accountBindInfoServiceImpl.update(accountBindInfo);
            		returnMap.put("success", "解除绑定帐户成功！");
            	}else{
            		returnMap.put("errormsg", "绑定帐户不存在或已解绑！");
            	}
            	writerPrint(JSONObject.fromObject(returnMap).toString());
    		}else{
    			returnMap.put("errormsg", "微信用户唯一标识不能为空");
    			writerPrint(JSONObject.fromObject(returnMap).toString());
    		}
		} catch (Exception e) {
			e.printStackTrace();
			returnMap.put("errormsg", "解除绑定帐户失败！");
			writerPrint(JSONObject.fromObject(returnMap).toString());
		}
    }
	
	 /**
     * @Description: 设置预警和报告管的状态值，控制发送或者不发送
     * @author cuichx --- 2015-12-28下午4:00:22
     */
    public void changeStatusValue(){
    	
    	Map<String, Object> returnMap=new HashMap<String, Object>();
    	//获取页面传递的参数
    	String openId=request.getParameter("openId");
    	String cb2_statusValue=request.getParameter("cb2_statusValue");//接收设置页面--站群预警/填报单位通知 
    	String cb4_statusValue=request.getParameter("cb4_statusValue");//接收设置页面--本级门户预警通知
    	
    	logger.info("changeStatusValue cb2_statusValue:"+cb2_statusValue+"  cb4_statusValue:"+cb4_statusValue);
		try {
	    	if(StringUtils.isNotEmpty(openId)){
	    		//根据客户编码和绑定状态查询帐户绑定信息表
		    	List<AccountBindInfo> accountList=getAccountList(openId);
		    	if(accountList!=null && accountList.size()>0){
		    		AccountBindInfo accountBindInfo=accountList.get(0);
		        	if(StringUtils.isNotEmpty(cb2_statusValue)){
		        		accountBindInfo.setSiteListEarlyStatus(Integer.valueOf(cb2_statusValue));
		        	}
		        	if(StringUtils.isNotEmpty(cb4_statusValue)){
		        		accountBindInfo.setLocalhostEarlyStatus(Integer.valueOf(cb4_statusValue));
		        	}
		    		//更新数据库中的数据
		        	accountBindInfoServiceImpl.update(accountBindInfo);
		    	}else{
		    		returnMap.put("errormsg", "绑定帐户不存在或已解绑！");
		    	}
	    	}else{
	    		returnMap.put("errormsg", "微信用户唯一标识不存在！");
	    	}
	    	writerPrint(JSONObject.fromObject(returnMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
			returnMap.put("errormsg", "修改失败！");
			writerPrint(JSONObject.fromObject(returnMap).toString());
		}
    }
	 /**
     * @Description: 通过发送ajax请求,获取管理中心页面的数据--填报单位
     * @author cuichx --- 2015-12-27下午6:02:13     
     * @return
     */
    public void getManagementCenterTB(){
    	
    	Map<String, Object> returnMap=new HashMap<String, Object>();
    	String siteCode=request.getParameter("siteCode");
    	String openId=request.getParameter("openId");
    	logger.info("getManagementCenterTB openId:"+openId+"   siteCode:"+siteCode);
    	
    	Map<String, Object> paramMap=new HashMap<String, Object>();
    	if(StringUtils.isNotEmpty(siteCode)){
    		paramMap.put("siteCode", siteCode);
    	}
    	if(StringUtils.isNotEmpty(openId)){
    		paramMap.put("openId", openId);
    	}
    	try {
    		if(StringUtils.isNotEmpty(openId)){
        		//查询绑定帐户信息表,判断是否有记录
        		List<AccountBindInfo> accList=getAccountList(openId);
        		if(accList!=null && accList.size()>0){
        			AccountBindInfo accountInfo=accList.get(0);
    				//根据网站标识码，查询站点信息表，获取信息
        			DatabaseInfoRequest datarRequest=new DatabaseInfoRequest();
        			datarRequest.setSiteCode(siteCode);
        			
        			List<DatabaseInfo> dataList=databaseInfoServiceImpl.queryList(datarRequest);
        			String url="";
        			String companyName="";//单位名称
        			String siteName="";
    				if(dataList!=null && dataList.size()>0){
    					DatabaseInfo dataInfo=dataList.get(0);
    					if(StringUtils.isNotEmpty(dataInfo.getJumpUrl())){
    						url=dataInfo.getJumpUrl();
    					}else{
    						url=dataInfo.getUrl();
    					}
    					if(StringUtils.isEmpty(companyName)){
    						companyName=dataInfo.getDirector();
    					}
    					if(StringUtils.isNotEmpty(dataInfo.getName())){
    						siteName=dataInfo.getName();
    					}
    				}
        			//基本信息
        			returnMap.put("siteCode", siteCode);//标识码
        			returnMap.put("accountType",AccountType.ACCOUNT_TB.getName());//客户类型主办单位
        			returnMap.put("siteName", siteName);//网站名称
        			returnMap.put("url", url);//标识码
        			returnMap.put("companyName", companyName);//主管单位
        			returnMap.put("earlyStatusTB", accountInfo.getSiteListEarlyStatus());//预警通知
        			
        			logger.info("getOrderInfo returnMap:"+returnMap);
        			writerPrint(JSONObject.fromObject(returnMap).toString());
        		}else{
        			returnMap.put("errorMsg", "绑定帐户信息表中无记录");
        			writerPrint(JSONObject.fromObject(returnMap).toString());
        		}
    		}else{
    			returnMap.put("errorMsg", "微信用户唯一标识不存在");
    			writerPrint(JSONObject.fromObject(returnMap).toString());
    		}
		} catch (Exception e) {
			e.printStackTrace();
			returnMap.put("errorMsg", "管理中心页面数据初始化异常");
			writerPrint(JSONObject.fromObject(returnMap).toString());
		}
    }
	
	/**
     * @Description: 通过发送ajax请求,获取管理中心页面的数据--组织单位
     * @author cuichx --- 2015-12-27下午6:02:13     
     * @return
     */
    public void getManagementCenter(){
    	Map<String, Object> returnMap=new HashMap<String, Object>();
    	String siteCode=request.getParameter("siteCode");
    	String openId=request.getParameter("openId");
    	logger.info("getManagementCenter openId:"+openId+"  siteCode:"+siteCode);
    	try {
    		if(StringUtils.isNotEmpty(openId)){
        		/** 接受设置页面的信息*/
				DatabaseOrgInfoRequest orgInfoRequest=new DatabaseOrgInfoRequest();
				orgInfoRequest.setStieCode(siteCode);
				List<DatabaseOrgInfo>  orgList=databaseOrgInfoServiceImpl.queryList(orgInfoRequest);
        		if(orgList!=null && orgList.size()>0){
        			DatabaseOrgInfo dataInfo=orgList.get(0);
            		/**管理信息页面的信息*/
            		returnMap.put("siteCode", siteCode);//客户编号（绑定帐户）
        			if(siteCode.length()==10){//帐户类型为 填报单位
        				returnMap.put("accountType",AccountType.ACCOUNT_TB.getName());
        			}else{//帐户类型为组织单位
        				returnMap.put("accountType",AccountType.ACCOUNT_ORG.getName());
        			}
        			/**
        			 * 获取门户网站名称
        			 */
        			DatabaseTreeInfoRequest treeInfoRequest=new DatabaseTreeInfoRequest();
        			treeInfoRequest.setOrgSiteCode(siteCode);
        			treeInfoRequest.setLayerType(1);
        			treeInfoRequest.setIsLink(1);
        			
        			List<DatabaseTreeInfo>  treeList=databaseTreeInfoServiceImpl.queryList(treeInfoRequest);
        			if(treeList!=null && treeList.size()>0){
        				returnMap.put("localMHSiteName", treeList.get(0).getName());//门户网站名称
        			}else{
        				returnMap.put("localMHSiteName", "");//门户网站名称
        			}
        			returnMap.put("companyName", dataInfo.getName());//单位名称
        			
        			Map<String, Object> pMap=new HashMap<String, Object>();
        			pMap.put("orgSiteCode", siteCode);
        			List<DatabaseTreeInfo>  treeList2=databaseTreeInfoServiceImpl.queryDownSiteInfo(pMap);
        			if(treeList2!=null && treeList2.size()>0){
        				returnMap.put("siteSum",treeList2.size());//网站总数
        			}else{
        				returnMap.put("siteSum", 0);//网站总数
        			}
        			
        			//查询绑定帐户信息表
        			List<AccountBindInfo> accountList=getAccountList(openId);
            		if(accountList!=null && accountList.size()>0){
            			AccountBindInfo accountInfo=accountList.get(0);
            			returnMap.put("localhostEarlyStatus", accountInfo.getLocalhostEarlyStatus());//本级门户预警
            			returnMap.put("siteListEarlyStatus", accountInfo.getSiteListEarlyStatus());//站群预警

            			logger.info("localhostEarlyStatus:"+accountInfo.getLocalhostEarlyStatus()+"  siteListEarlyStatus:"+accountInfo.getSiteListEarlyStatus());
                		writerPrint(JSONObject.fromObject(returnMap).toString());
                    }else{
            			returnMap.put("errormsg", "该客户未绑定或者已经解绑！");
            			writerPrint(JSONObject.fromObject(returnMap).toString());
                    }
        		}else{
        			returnMap.put("errormsg", "该客户不存在本级门户网站,管理中心页面初始化失败！");
        			writerPrint(JSONObject.fromObject(returnMap).toString());
        		}
    		}else{
    			returnMap.put("errormsg", "微信用户唯一标识不能为空！");
    			writerPrint(JSONObject.fromObject(returnMap).toString());
    		}
		} catch (Exception e) {
			e.printStackTrace();
			returnMap.put("errormsg", e.getMessage());
			writerPrint(JSONObject.fromObject(returnMap).toString());
		}
    }
	/**
     * @Description: 管理中心页面初始化--组织单位、填报单位
     * @author cuichx --- 2015-12-27下午6:02:13     
     * @return
     */
    public String managementCenterIndex(){
    	initMap=new HashMap<String, Object>();
    	try {
   		    String openId=request.getParameter("openId");
    		if(StringUtils.isEmpty(openId)){
    			//如果code不为空，说明是点击自定义按进入，否则为点击统计页面中的详情进入
    			String code=request.getParameter("code");
				logger.info("managementCenterIndex openId:"+openId+"  code:"+code);
				WeixinOauth2Token weixinOauth2Token=CommonUtils.getWeixinOauth2Token(code);
			    //用户唯一标识
			    openId=weixinOauth2Token.getOpenId();
    		}
			//String openId="oTfZiwy1Mc14i3w97q67B1Nt9BTM";
			logger.info(" managementCenterIndex openId:"+openId);
			initMap.put("openId", openId);
			if(StringUtils.isNotEmpty(openId)){
	    		//根据微信用户唯一标识查询该微信是否已经绑定了帐号
	    		List<AccountBindInfo> accList=getAccountList(openId);
	    		if(accList!=null && accList.size()>0){
	    			initMap.put("siteCode", accList.get(0).getSiteCode());
	    		}else{//未绑定帐户，跳转到绑定帐户页面
			    	request.getRequestDispatcher("/newToken_accountBindIndex.action?openId="+openId+"&fromType=managementCenter").forward(request, response);
			    }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return "newWeiXin";
    }
    /**
     * @Description: 绑定帐户页面
     * @author cuichx --- 2015-12-27下午3:46:03     
     * @return
     */
    public String accountBindIndex(){
    	initMap=new HashMap<String, Object>();
    	try {
    		//区别是由那个页面跳转过来的    监测结果页面  预警页面  管理中心页面
    		String fromType=request.getParameter("fromType");
    		String openId=request.getParameter("openId");//微信客户唯一标识	
			logger.info("=====accountBindIndex openId:"+openId+"====fromType:"+fromType);
			
			//String openId="oTfZiwy1Mc14i3w97q67B1Nt9BTM";
			logger.info("accountBindIndex openId:"+openId);
			if(StringUtils.isNotEmpty(openId) && StringUtils.isNotEmpty(fromType)){//点击菜单触发
				initMap.put("openId", openId);//用户唯一标识
				initMap.put("fromType", fromType);//标识是从哪个页面跳转到绑定帐户页面
			}else{//点击关注公众号之后的消息中的绑定触发
				
				//判断帐户是否已经绑定帐户，如果用户已经绑定帐户，跳转到绑定帐户成功页面；如果未绑定则跳转到绑定帐户页面
				AccountBindInfoRequest accRequest=new AccountBindInfoRequest();
				accRequest.setOpenId(openId);
				accRequest.setStatus(AccountBindStatus.ACCOUNT_BIND.getCode());//绑定帐户有效
				List<AccountBindInfo> accBindList=accountBindInfoServiceImpl.queryList(accRequest);
				if(accBindList!=null && accBindList.size()>0){//绑定帐户已经存在
					String siteCode=accBindList.get(0).getSiteCode();
					request.getRequestDispatcher("/newToken_removeAccountBindIndex.action?siteCode="+siteCode+"&openId="+openId).forward(request, response);
				}else{
					initMap.put("openId", openId);//用户唯一标识
					initMap.put("fromType", fromType);//标识是从哪个页面跳转到绑定帐户页面
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return "newWeiXin";
    }
    

    /**
     * @Description: 保存帐户绑定帐户信息
     * @author cuichx --- 2015-12-27下午4:00:50
     */
    public void saveAccountBind(){
    	Map<String, Object> returnMap=new HashMap<String, Object>();
    	String openId="";
    	String siteCode="";
    	String pwd="";		
    	try {
        	JSONObject jo=getJSONObject();
        	if(jo!=null && !"".equals(jo)){
        		openId=jo.getString("openId");//微信用户的唯一标识
            	siteCode=jo.getString("siteCode");//客户帐号
            	pwd=jo.getString("password");//密码
        	}
        	logger.info("saveAccountBind openId:"+openId+"  siteCode:"+siteCode+"  pwd:"+pwd);
			ContractInfoRequest contractInfoRequest=new ContractInfoRequest();
	    	Map<String, Object> paramMap=new HashMap<String, Object>();
	    	if(StringUtils.isNotEmpty(openId)){
	    		paramMap.put("openId", openId);
	    	}
	    	if(StringUtils.isNotEmpty(siteCode)){
	    		paramMap.put("siteCode", siteCode);//客户编码
	    		contractInfoRequest.setSiteCode(siteCode);
	    	}
	    	if(StringUtils.isNotEmpty(pwd)){
	    		paramMap.put("pwd", pwd);
	    	}
    	
    	 	if(StringUtils.isNotEmpty(openId)){
        		/**
        		 * 根据上传的客户编号和密码查询，判断该客户输入的帐号和密码是否匹配.
        		 * 		如果是组织单位的话，需要发送http请求验证;
        		 * 		如果是填报单位的话，需要查询database_info表中的帐户和密码进行验证;
        		 */
        		Boolean flag=valivitePassword(siteCode, pwd);
        		//用户名和密码验证成功
    			if(flag){
    				/**
					 * 获取微信昵称
					 */
            		String nickname=getNickname(openId);
            		//绑定帐户名称
            		String name="";//绑定帐户名称
            		if(siteCode.length()==10){//填报单位
            			DatabaseInfoRequest baseRequest=new DatabaseInfoRequest();
            			baseRequest.setSiteCode(siteCode);
            			List<DatabaseInfo> baseList=databaseInfoServiceImpl.queryList(baseRequest);
            			if(baseList!=null && baseList.size()>0){
            				 name=baseList.get(0).getName();
            			}
            		}else{//组织单位
            			DatabaseOrgInfoRequest orgRequest=new DatabaseOrgInfoRequest();
            			orgRequest.setStieCode(siteCode);
            			
            			List<DatabaseOrgInfo>  orgList=databaseOrgInfoServiceImpl.queryList(orgRequest);
            			if(orgList!=null && orgList.size()>0){
            				name=orgList.get(0).getName();
            			}
            			
            		}
        			AccountBindInfo accountBindInfo=new AccountBindInfo();
        			accountBindInfo.setOpenId(openId);
        			accountBindInfo.setSiteCode(siteCode);
        			accountBindInfo.setSiteName(name);
        			accountBindInfo.setNickname(nickname);//微信用户昵称
        			accountBindInfo.setStatus(AccountBindStatus.ACCOUNT_BIND.getCode());//绑定
        			accountBindInfo.setSiteListEarlyStatus(ReceiveType.IS_RECEIVE.getCode());//默认接收站群预警通知
        			accountBindInfo.setLocalhostEarlyStatus(ReceiveType.IS_RECEIVE.getCode());//默认接收本机门户预警通知
        			accountBindInfoServiceImpl.add(accountBindInfo);
        			
        			returnMap.put("success", "微信绑定成功！");
        			writerPrint(JSONObject.fromObject(returnMap).toString());  		
        		}else{
        			returnMap.put("errormsg", "该客户编号不存在或客户编号与密码不一致！");
            		writerPrint(JSONObject.fromObject(returnMap).toString());
            		return;
        		}
    		}else{
        		returnMap.put("errormsg", "微信客户的唯一标识不存在！");
        		writerPrint(JSONObject.fromObject(returnMap).toString());
        		return;
        	}
		} catch (Exception e) {
			e.printStackTrace();
			returnMap.put("errormsg","保存帐户信息异常");
			writerPrint(JSONObject.fromObject(returnMap).toString());
		}
    }

    /**
     * @Description: 绑定帐户、密码校验
     * @author cuichx --- 2017-3-16下午4:45:03     
     * @param siteCode
     * @return
     */
    public boolean valivitePassword(String siteCode,String pwd){
    	if(siteCode.length()==6){//组织单位
			//组织单位登录时，帐号和密码验证
			DatabaseOrgInfoRequest dataOrgrRequest=new DatabaseOrgInfoRequest();
			dataOrgrRequest.setStieCode(siteCode);
			dataOrgrRequest.setVcode(pwd);
			List<DatabaseOrgInfo> dataOrgList=databaseOrgInfoServiceImpl.queryList(dataOrgrRequest);
			if(dataOrgList!=null && dataOrgList.size()>0){
				return true;
			}else{
				return false;
			}
		}else{//长度为10，填报单位
			//根据网站标识码，查询database_info表，判断输入的用户面和密码是否正确
			DatabaseInfoRequest databaseRequest=new DatabaseInfoRequest();
			databaseRequest.setSiteCodeLike(siteCode);
			databaseRequest.setVcode(pwd);
			List<DatabaseInfo> dataInfoList=databaseInfoServiceImpl.queryList(databaseRequest);
			if(dataInfoList!=null && dataInfoList.size()>0){
				return true;
			}else{
				return false;
			}
		}
    }

    /**
	 * @Description:绑定成功页面
	 * @author cuichx --- 2016-1-7下午7:31:12     
	 * @return
	 */
	public String removeAccountBindIndex(){
		
		initMap=new HashMap<String, Object>();
		String siteCode=request.getParameter("siteCode");
		String openId=request.getParameter("openId");
		logger.info("removeAccountBindIndex siteCode:"+siteCode+"   openId:"+openId);
		
		initMap.put("siteCode", siteCode);
		String custType="";//判断帐户类型
		int siteNum=0;//获取网站个数
		String companyName="";//单位名称
		if(siteCode.length()==10){//填报单位
			custType=AccountType.ACCOUNT_TB.getName();
			siteNum=1;
			
			DatabaseInfoRequest databaseRequest=new DatabaseInfoRequest();
			databaseRequest.setSiteCode(siteCode);
			List<DatabaseInfo> dataList=databaseInfoServiceImpl.queryList(databaseRequest);
			if(dataList!=null && dataList.size()>0){
				companyName=dataList.get(0).getName();
			}
		}else{//组织单位
			custType=AccountType.ACCOUNT_ORG.getName();
			Map<String, Object> pMap=new HashMap<String, Object>();
			pMap.put("orgSiteCode", siteCode);
			List<DatabaseTreeInfo>  treeList2=databaseTreeInfoServiceImpl.queryDownSiteInfo(pMap);
			if(treeList2!=null && treeList2.size()>0){
				siteNum=treeList2.size();
			}
			DatabaseOrgInfoRequest orgInfoRequest=new DatabaseOrgInfoRequest();
			orgInfoRequest.setStieCode(siteCode);
			List<DatabaseOrgInfo>  orgList=databaseOrgInfoServiceImpl.queryList(orgInfoRequest);
			if(orgList!=null && orgList.size()>0){
				companyName=orgList.get(0).getName();
			}
		}
		initMap.put("custType", custType);
		initMap.put("siteNum", siteNum);
		initMap.put("custName", companyName);
 		
		return "newWeiXin";
	}
	
    /**
	 * @Description:首页不连通统计数据--（组织单位实时）
	 * @author cuichx --- 2016-1-7下午7:31:12     
	 * @return
	 */
	public Integer getConnHomeOrgSsCount(String orgSiteCode,String checkType){
		
		Map<String, Object> paraMap=new HashMap<String, Object>();
		paraMap.put("orgSiteCode", orgSiteCode);
		if(!"0".equals(checkType)){
			paraMap.put("layerType", checkType);
		}
		paraMap.put("isexp", 1);
		paraMap.put("isLink", 1);
		paraMap.put("scanDate", nowDate);//yyyy-MM-dd
		paraMap.put("type", 1);//首页连通性
		paraMap.put("errorCode", "200");//错误编码非200
		paraMap.put("nowDate", nowDate);
		Integer connHome=earlyDetailTempServiceImpl.queryEarlyTempByMapCount(paraMap);
		logger.info("=================connHome:"+connHome);
		if(connHome==null){
			connHome=0;
		}
		return connHome;
	}
    /**
	 * @Description:首页不连通统计数据--（实时）
	 * @author cuichx --- 2016-1-7下午7:31:12     
	 * @return
	 */
	public Integer getConnHomeTbSsCount(String siteCode){
		
		Map<String, Object> paraMap=new HashMap<String, Object>();
		paraMap.put("siteCode", siteCode);
		paraMap.put("isexp", 1);
		paraMap.put("scanDate", DateUtils.formatStandardDate(new Date()));//yyyy-MM-dd
		paraMap.put("type", 1);//首页连通性
		paraMap.put("errorCode", "200");//错误编码非200
		paraMap.put("nowDate", nowDate);//错误编码非200
		Integer connHome=0;
		List<EarlyDetailTempRequest>  tempList=earlyDetailTempServiceImpl.queryEarlyTempByMapCountTb(paraMap);
		if(tempList!=null && tempList.size()>0){
			connHome=1;
		}
		return connHome;
	}
	/**
	 * 连通性借口调用
	 * http://121.41.11.184:9080/Monitor/siteUrlLinkDetail.ejf?sitecode=4416000024&date=2017-04-11&type=1&detail=1&code=404,403,503,502,504
	 * @param siteCode
	 * @param dateStr
	 * @param type
	 * @return
	 */
	public Root connectivityByRoot(String siteCode, String dateStr, String type,String code) {
		Root root = new Root();
		try {
			String co = urlAdapterVar.getConnectivityUrl() + "?sitecode=" + siteCode + "&date=" + dateStr + "&type="
					+ type + "&detail=1"+"&code="+code;
			root = XstreamXmlParseBeanUtil.getRootByUrl(co);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return root;
	}
	public Map<String, Object> getInitMap() {
		return initMap;
	}
	public void setInitMap(Map<String, Object> initMap) {
		this.initMap = initMap;
	}
}
