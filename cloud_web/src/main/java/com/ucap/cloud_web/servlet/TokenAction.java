package com.ucap.cloud_web.servlet;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.publics.util.page.PageVo;
import com.publics.util.page.QueryOrder;
import com.publics.util.page.QueryOrderType;
import com.publics.util.utils.DateUtils;
import com.publics.util.utils.StringUtils;
import com.ucap.cloud_web.action.BaseAction;
import com.ucap.cloud_web.constant.AccountBindStatus;
import com.ucap.cloud_web.constant.AccountType;
import com.ucap.cloud_web.constant.ConnectionType;
import com.ucap.cloud_web.constant.CorrectType;
import com.ucap.cloud_web.constant.DatabaseLinkType;
import com.ucap.cloud_web.constant.EarlyType;
import com.ucap.cloud_web.constant.QuestionType;
import com.ucap.cloud_web.constant.ReceiveType;
import com.ucap.cloud_web.constant.SecurityHomeChannelType;
import com.ucap.cloud_web.constant.TrueOrFalseType;
import com.ucap.cloud_web.dto.AccountBindInfoRequest;
import com.ucap.cloud_web.dto.ConnectionAllRequest;
import com.ucap.cloud_web.dto.ContractInfoRequest;
import com.ucap.cloud_web.dto.CorrectContentDetailRequest;
import com.ucap.cloud_web.dto.DatabaseInfoRequest;
import com.ucap.cloud_web.dto.DatabaseLinkRequest;
import com.ucap.cloud_web.dto.DatabaseOrgInfoRequest;
import com.ucap.cloud_web.dto.DetectionOrgCountRequest;
import com.ucap.cloud_web.dto.DetectionResultRequest;
import com.ucap.cloud_web.dto.EarlyDetailRequest;
import com.ucap.cloud_web.dto.IndexCountRequest;
import com.ucap.cloud_web.dto.LinkHomeAvailableRequest;
import com.ucap.cloud_web.dto.RelationsPeriodRequest;
import com.ucap.cloud_web.dto.ReportManageLogRequest;
import com.ucap.cloud_web.dto.SecurityHomeChannelRequest;
import com.ucap.cloud_web.dto.ServicePeriodRequest;
import com.ucap.cloud_web.dto.UpdateHomeDetailRequest;
import com.ucap.cloud_web.entity.AccountBindInfo;
import com.ucap.cloud_web.entity.ConnectionAll;
import com.ucap.cloud_web.entity.ContractInfo;
import com.ucap.cloud_web.entity.CorrectContentDetail;
import com.ucap.cloud_web.entity.DatabaseInfo;
import com.ucap.cloud_web.entity.DatabaseOrgInfo;
import com.ucap.cloud_web.entity.DetectionOrgCount;
import com.ucap.cloud_web.entity.DetectionResult;
import com.ucap.cloud_web.entity.EarlyDetail;
import com.ucap.cloud_web.entity.LinkHomeAvailable;
import com.ucap.cloud_web.entity.RelationsPeriod;
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
import com.ucap.cloud_web.service.IDetectionOrgCountService;
import com.ucap.cloud_web.service.IDetectionResultService;
import com.ucap.cloud_web.service.IEarlyDetailService;
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
 * <p>Description:微信版开发 </p>
 * <p>@Package：com.ucap.cloud_web.servlet </p>
 * <p>Title: TokenAction </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：cuichx </p>
 * <p>@date：2015-12-23下午7:37:20 </p>
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class TokenAction extends BaseAction{
	private static Log logger = LogFactory.getLog(TokenAction.class);

	
	
	@Autowired
	private  IAccountBindInfoService accountBindInfoServiceImpl;
	
	@Autowired
	private  IDatabaseInfoService databaseInfoServiceImpl;
	
//	@Autowired
//	private  IWebsiteInfoService websiteInfoServiceImpl;
	
	@Autowired
	private  IDetectionResultService detectionResultServiceImpl;
	
	@Autowired
	private  IEarlyDetailService earlyDetailServiceImpl;//预警信息详情表
	
	@Autowired
	private  IReportManageLogService reportManageLogServiceImpl;//报告管理表
	
	@Autowired
	private IServicePeriodService servicePeriodServiceImpl;//服务周期对象
	
	@Autowired
	private IConnectionAllService connectionAllServiceImpl;

	@Autowired
	private IIndexCountService indexCountServiceImpl;//健康指数统计表
	@Autowired
	private ISecurityHomeChannelService securityHomeChannelServiceImpl;//首页栏目信息不更新表

	@Autowired
	private IContractInfoService contractInfoServiceImpl;//合同信息表
	
	@Autowired
	private IRelationsPeriodService relationsPeriodServiceImpl;//服务周期中间表
	
	@Autowired
	private IDatabaseLinkService databaseLinkServiceImpl;
	
	@Autowired
	private ICorrectContentDetailService correctContentDetailServiceImpl;
	
	@Autowired
	private ILinkHomeAvailableService linkHomeAvailableServiceImpl;
	
	@Autowired
	private IUpdateHomeDetailService updateHomeDetailServiceImpl;
	
	@Autowired
	private IDatabaseOrgInfoService databaseOrgInfoServiceImpl;
	
	@Autowired
	private IDetectionOrgCountService detectionOrgCountServiceImpl;
	
		
	/**
	 * 当前监测结果页面数据初始化集合
	 */
	private Map<String, Object> resultMap=null;
	public Map<String, Object> getResultMap() {
		return resultMap;
	}
	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	
	/**
	 * @Description: 监测结果页面--跳转到内容保障问题页面
	 * @author cuichx --- 2016-4-13下午3:38:07     
	 * @return
	 */
	public String securityIndex(){
		resultMap=new HashMap<String, Object>();
		String siteCode=request.getParameter("siteCode");//网站标识码
		String level=request.getParameter("level");//组织机构级别
		String menuType=request.getParameter("menuType");//类型
		String openId=request.getParameter("openId");//微信唯一标识
		
		logger.info("correctContentIndex siteCode:"+siteCode+"  level:"+level+"  menuType"+"   openId:"+openId);
		resultMap.put("openId", openId);
		resultMap.put("siteCode", siteCode);
		resultMap.put("level", level);
		resultMap.put("menuType", menuType);
		
		return "weixin";
	}
	/**
	 * @Description: 获取内容保障问题页面数据
	 * @author cuichx --- 2016-4-13下午3:39:35
	 */
	public void getsecurity(){
		Map<String, Object> returnMap=new HashMap<String, Object>();
		try {
			String siteCode="";//网站标识码
			String level="";//组织机构级别
			String menuType="";//类型
			String pageNo="";//查询页数
			JSONObject jo=getJSONObject();
			if(jo!=null && !"".equals(jo)){
				siteCode=jo.getString("siteCode");
				level=jo.getString("level");
				menuType=jo.getString("menuType");
				pageNo=jo.getString("counter");
			}
			
			logger.info("getConnectionAll siteCode:"+siteCode+"  level:"+level+"  menuType:"+menuType+"  pageNo:"+pageNo);
			List<DatabaseInfo> databaseList=new ArrayList<DatabaseInfo>();
			
			
			//封装查询条件
			SecurityHomeChannelRequest homeRequest=new SecurityHomeChannelRequest();
			
			if(StringUtils.isNotEmpty(siteCode)){
				if(siteCode.length()==6){//组织单位
					databaseList=queryDatebaseInfoListByType2(Integer.valueOf(level), menuType, siteCode);
					homeRequest.setDatabaseList(databaseList);//网站集合
				}else{//填报单位
					homeRequest.setSiteCode(siteCode);
				}
			}
			if(StringUtils.isNotEmpty(pageNo)){
				homeRequest.setPageNo(Integer.valueOf(pageNo));
			}
			//获取昨天以前的数据
			homeRequest.setScanDateLow(DateUtils.getYesterdayStr());
			
			//每次加载四条数据
			homeRequest.setPageSize(4);
			//设置排序字段
			List<QueryOrder> querySiteList=new ArrayList<QueryOrder>();
			QueryOrder siteQueryOrder=new QueryOrder("scan_date",QueryOrderType.DESC);
			querySiteList.add(siteQueryOrder);
			homeRequest.setQueryOrderList(querySiteList);
			homeRequest.setWxFlag("true");
			
			PageVo<SecurityHomeChannel>  homePage=securityHomeChannelServiceImpl.query(homeRequest);
			List<SecurityHomeChannel> homeList=homePage.getData();
			
			List<Object> returnList=new ArrayList<Object>();
			if(homeList!=null && homeList.size()>0){
				for (int i = 0; i < homeList.size(); i++) {
					SecurityHomeChannel homeChannel=homeList.get(i);
					Map<String, Object> map=new HashMap<String, Object>();
					//网站标识码
					if(StringUtils.isNotEmpty(homeChannel.getSiteCode())){
						map.put("siteCode", homeChannel.getSiteCode());
					}else{
						map.put("siteCode", "");
					}
					
					//网站名称
					DatabaseInfoRequest databaseInfoRequest=new DatabaseInfoRequest();
					databaseInfoRequest.setSiteCode(homeChannel.getSiteCode());
					
					List<DatabaseInfo> databaseinfoList=databaseInfoServiceImpl.queryList(databaseInfoRequest);
					if(databaseinfoList!=null && databaseinfoList.size()>0){
						DatabaseInfo databaseInfo=databaseinfoList.get(0);
						map.put("siteName", databaseInfo.getName());
					}else{
						map.put("siteName", "");
					}
					
					String name="";//栏目名称
					if(StringUtils.isNotEmpty(homeChannel.getName())){
						name=homeChannel.getName();
					}
					map.put("name", name);
					
					
					
					//类型
					Integer type=homeChannel.getType();
					map.put("type", type);
					
					//类型名称
					String typeName="";
					if(homeChannel.getType()>0){
						typeName=SecurityHomeChannelType.getNameByCode(homeChannel.getType());
					}
            		map.put("typeName", typeName);

            		
            		if(StringUtils.isNotEmpty(homeChannel.getUrl())){
            			map.put("url", homeChannel.getUrl());
            		}else{
            			map.put("url", "");
            		}				
					
            		String scanDate="";
					//扫描日期
					if(StringUtils.isNotEmpty(homeChannel.getScanDate())){
						//将日期转换为年月日格式
						scanDate=DateUtils.formatDate(DateUtils.parseStandardDate(homeChannel.getScanDate()));
					}
					map.put("scanDate",scanDate);
					
					//最后更新日期
					String modifyDate="";
					if(StringUtils.isNotEmpty(homeChannel.getModifyDate())){
						modifyDate=DateUtils.formatDate(DateUtils.parseStandardDate(homeChannel.getModifyDate()));
					}
					map.put("modifyDate",modifyDate);
					
					//未更新天数
					Integer notUpdateNum=homeChannel.getNotUpdateNum();
					map.put("notUpdateNum", notUpdateNum);
					
					returnList.add(map);
				}
    			
				returnMap.put("resultList", returnList);
				returnMap.put("pageTotal", homePage.getPageTotal());
				returnMap.put("recordSize", homePage.getRecordSize());
    			
    			writerPrint(JSONObject.fromObject(returnMap).toString());
    		}else{
    			returnMap.put("errorMsg", "暂无内容保障问题数据");
    			writerPrint(JSONObject.fromObject(returnMap).toString());
    			return;
    		}
			
		} catch (Exception e) {
			e.printStackTrace();
			returnMap.put("errorMsg", "获取内容保障问题异常");
			writerPrint(JSONObject.fromObject(returnMap).toString());
		}
	}
	
	/**
	 * @Description: 监测结果页面--跳转到内容更新页面
	 * @author cuichx --- 2016-4-13下午3:38:07     
	 * @return
	 */
	public String updateIndex(){
		resultMap=new HashMap<String, Object>();
		String siteCode=request.getParameter("siteCode");//网站标识码
		String level=request.getParameter("level");//组织机构级别
		String menuType=request.getParameter("menuType");//类型
		String openId=request.getParameter("openId");//微信唯一标识
		
		logger.info("correctContentIndex siteCode:"+siteCode+"  level:"+level+"  menuType"+"   openId:"+openId);
		resultMap.put("openId", openId);
		resultMap.put("siteCode", siteCode);
		resultMap.put("level", level);
		resultMap.put("menuType", menuType);
		
		return "weixin";
	}
	/**
	 * @Description: 获取内容更新页面数据
	 * @author cuichx --- 2016-4-13下午3:39:35
	 */
	public void getUpdate(){
		Map<String, Object> returnMap=new HashMap<String, Object>();
		try {
			String siteCode="";//网站标识码
			String level="";//组织机构级别
			String menuType="";//类型
			String pageNo="";//查询页数
			JSONObject jo=getJSONObject();
			if(jo!=null && !"".equals(jo)){
				siteCode=jo.getString("siteCode");
				level=jo.getString("level");
				menuType=jo.getString("menuType");
				pageNo=jo.getString("counter");
			}
			
			logger.info("getUpdate siteCode:"+siteCode+"  level:"+level+"  menuType:"+menuType+"  pageNo:"+pageNo);
			List<DatabaseInfo> databaseList=new ArrayList<DatabaseInfo>();
			
			//封装查询条件
			UpdateHomeDetailRequest homeRequest=new UpdateHomeDetailRequest();
			
			if(StringUtils.isNotEmpty(siteCode)){
				if(siteCode.length()==6){//组织单位
					databaseList=queryDatebaseInfoListByType2(Integer.valueOf(level), menuType, siteCode);
					homeRequest.setDatabaseList(databaseList);//网站集合
				}else{//填报单位
					homeRequest.setSiteCode(siteCode);
				}
			}
			
			if(StringUtils.isNotEmpty(pageNo)){
				homeRequest.setPageNo(Integer.valueOf(pageNo));
			}
			//每次加载四条数据
			homeRequest.setPageSize(4);
			//设置排序字段
			List<QueryOrder> querySiteList=new ArrayList<QueryOrder>();
			QueryOrder siteQueryOrder=new QueryOrder("scan_date",QueryOrderType.DESC);
			querySiteList.add(siteQueryOrder);
			homeRequest.setQueryOrderList(querySiteList);
			
			PageVo<UpdateHomeDetail>  homePage=updateHomeDetailServiceImpl.query(homeRequest);
			List<UpdateHomeDetail> contentList=homePage.getData();
			
			List<Object> returnList=new ArrayList<Object>();
			if(contentList!=null && contentList.size()>0){
				for (int i = 0; i < contentList.size(); i++) {
					UpdateHomeDetail homeDetail=contentList.get(i);
					Map<String, Object> map=new HashMap<String, Object>();
					//网站标识码
					if(StringUtils.isNotEmpty(homeDetail.getSiteCode())){
						map.put("siteCode", homeDetail.getSiteCode());
					}else{
						map.put("siteCode", "");
					}
					
					//网站名称
					DatabaseInfoRequest databaseInfoRequest=new DatabaseInfoRequest();
					databaseInfoRequest.setSiteCode(homeDetail.getSiteCode());
					
					List<DatabaseInfo> databaseinfoList=databaseInfoServiceImpl.queryList(databaseInfoRequest);
					if(databaseinfoList!=null && databaseinfoList.size()>0){
						DatabaseInfo databaseInfo=databaseinfoList.get(0);
						map.put("siteName", databaseInfo.getName());
					}else{
						map.put("siteName", "");
					}
					
            		map.put("title", homeDetail.getTitle());//标题
            		
            		if(StringUtils.isNotEmpty(homeDetail.getUrl())){
            			map.put("url", homeDetail.getUrl());//跳转网址
            		}else{
            			map.put("url", "");//首页网址
            		}				
					
					//扫描日期
					if(StringUtils.isNotEmpty(homeDetail.getScanDate())){
						//将日期转换为年月日格式
						map.put("scanDate", DateUtils.formatDate(DateUtils.parseStandardDate(homeDetail.getScanDate())));
					}
					
					String updateTime="";//更新时间
					if(StringUtils.isNotEmpty(homeDetail.getUpdateTime())){
						updateTime=DateUtils.formatStandardDate(DateUtils.parseStandardDate(homeDetail.getUpdateTime()));
					}
					map.put("updateTime", updateTime);
					returnList.add(map);
				}
    			
				returnMap.put("resultList", returnList);
				returnMap.put("pageTotal", homePage.getPageTotal());
				returnMap.put("recordSize", homePage.getRecordSize());
    			
    			writerPrint(JSONObject.fromObject(returnMap).toString());
    		}else{
    			returnMap.put("errorMsg", "暂无更新统计数据");
    			writerPrint(JSONObject.fromObject(returnMap).toString());
    			return;
    		}
			
		} catch (Exception e) {
			e.printStackTrace();
			returnMap.put("errorMsg", "获取更新统计数据异常");
			writerPrint(JSONObject.fromObject(returnMap).toString());
		}
	}
	
	
	/**
	 * @Description: 监测结果页面--跳转到错别字页面
	 * @author cuichx --- 2016-4-13下午3:38:07     
	 * @return
	 */
	public String correctContentIndex(){
		resultMap=new HashMap<String, Object>();
		String siteCode=request.getParameter("siteCode");//网站标识码
		String level=request.getParameter("level");//组织机构级别
		String menuType=request.getParameter("menuType");//类型
		String openId=request.getParameter("openId");//微信唯一标识
		
		logger.info("correctContentIndex siteCode:"+siteCode+"  level:"+level+"  menuType"+"   openId:"+openId);
		resultMap.put("openId", openId);
		resultMap.put("siteCode", siteCode);
		resultMap.put("level", level);
		resultMap.put("menuType", menuType);
		
		return "weixin";
	}
	
	/**
	 * @Description: 获取内容正确性页面数据
	 * @author cuichx --- 2016-4-13下午3:39:35
	 */
	public void getCorrectContent(){
		Map<String, Object> returnMap=new HashMap<String, Object>();
		try {
			String siteCode="";//网站标识码
			String level="";//组织机构级别
			String menuType="";//类型
			String pageNo="";//查询页数
			JSONObject jo=getJSONObject();
			if(jo!=null && !"".equals(jo)){
				siteCode=jo.getString("siteCode");
				level=jo.getString("level");
				menuType=jo.getString("menuType");
				pageNo=jo.getString("counter");
			}
			
			logger.info("getCorrectContent siteCode:"+siteCode+"  level:"+level+"  menuType:"+menuType+"  pageNo:"+pageNo);
			List<DatabaseInfo> databaseList=new ArrayList<DatabaseInfo>();
			//封装查询条件
			CorrectContentDetailRequest correctContentrRequest=new CorrectContentDetailRequest();
			
			if(StringUtils.isNotEmpty(siteCode)){
				if(siteCode.length()==6){//组织单位
					databaseList=queryDatebaseInfoListByType2(Integer.valueOf(level), menuType, siteCode);
					correctContentrRequest.setDatabaseList(databaseList);//网站集合
				}else{//填报单位
					correctContentrRequest.setSiteCode(siteCode);
				}
			}
			
			if(StringUtils.isNotEmpty(pageNo)){
				correctContentrRequest.setPageNo(Integer.valueOf(pageNo));
			}
			//每次加载四条数据
			correctContentrRequest.setPageSize(4);
			//内容正确性类型--严重错误
			correctContentrRequest.setCorrectType(CorrectType.SERIOUS.getCode());
			//是否曝光数据
			correctContentrRequest.setExposure(TrueOrFalseType.TRUE.getCode());//是否曝光(0:否; 1:是)
			//设置排序字段
			List<QueryOrder> querySiteList=new ArrayList<QueryOrder>();
			QueryOrder siteQueryOrder=new QueryOrder("scan_date",QueryOrderType.DESC);
			querySiteList.add(siteQueryOrder);
			correctContentrRequest.setQueryOrderList(querySiteList);
			
			PageVo<CorrectContentDetail>  contentPage=correctContentDetailServiceImpl.query(correctContentrRequest);
			List<CorrectContentDetail> contentList=contentPage.getData();
			
			List<Object> returnList=new ArrayList<Object>();
			if(contentList!=null && contentList.size()>0){
				for (int i = 0; i < contentList.size(); i++) {
					CorrectContentDetail correctContentDetail=contentList.get(i);
					Map<String, Object> map=new HashMap<String, Object>();
					//网站标识码
					if(StringUtils.isNotEmpty(correctContentDetail.getSiteCode())){
						map.put("siteCode", correctContentDetail.getSiteCode());
					}else{
						map.put("siteCode", "");
					}
					
					//网站名称
					DatabaseInfoRequest databaseInfoRequest=new DatabaseInfoRequest();
					databaseInfoRequest.setSiteCode(correctContentDetail.getSiteCode());
					
					List<DatabaseInfo> databaseinfoList=databaseInfoServiceImpl.queryList(databaseInfoRequest);
					if(databaseinfoList!=null && databaseinfoList.size()>0){
						DatabaseInfo databaseInfo=databaseinfoList.get(0);
						map.put("siteName", databaseInfo.getName());
					}else{
						map.put("siteName", "");
					}
					
            		map.put("correctType", CorrectType.getNameByCode(correctContentDetail.getCorrectType()));//错误类型

            		//疑似错误
            		if(StringUtils.isNotEmpty(correctContentDetail.getWrongTerm())){
            			map.put("wrongTerm", correctContentDetail.getWrongTerm());
            		}else{
            			map.put("wrongTerm", "");
            		}
            		//推荐修改
            		if(StringUtils.isNotEmpty(correctContentDetail.getExpectTerms())){
            			map.put("expectTerms", correctContentDetail.getExpectTerms());
            		}else{
            			map.put("expectTerms", "");
            		}
            		
            		if(StringUtils.isNotEmpty(correctContentDetail.getUrl())){
            			map.put("url", correctContentDetail.getUrl());//跳转网址
            		}else{
            			map.put("url", "");//首页网址
            		}				
					
					//扫描日期
					if(StringUtils.isNotEmpty(correctContentDetail.getScanDate())){
						//将日期转换为年月日格式
						map.put("scanDate", DateUtils.formatDate(DateUtils.parseStandardDate(correctContentDetail.getScanDate())));
					}
					returnList.add(map);
				}
				
    			
				returnMap.put("resultList", returnList);
				returnMap.put("pageTotal", contentPage.getPageTotal());
				returnMap.put("recordSize", contentPage.getRecordSize());
    			
    			writerPrint(JSONObject.fromObject(returnMap).toString());
    		}else{
    			returnMap.put("errorMsg", "暂无错别字数据");
    			writerPrint(JSONObject.fromObject(returnMap).toString());
    			return;
    		}
			
		} catch (Exception e) {
			e.printStackTrace();
			returnMap.put("errorMsg", "获取错别字数据异常");
			writerPrint(JSONObject.fromObject(returnMap).toString());
		}
	}
	
	
	
	/**
	 * @Description: 首页不可用链接页面初始化
	 * @author cuichx --- 2016-3-30上午11:35:11     
	 * @return
	 */
	public String linkHomeIndex(){
		resultMap=new HashMap<String, Object>();
		String siteCode=request.getParameter("siteCode");//网站标识码
		String level=request.getParameter("level");//组织机构级别
		String menuType=request.getParameter("menuType");//类型
		String openId=request.getParameter("openId");//微信唯一标识
		
		logger.info("linkHomeIndex siteCode:"+siteCode+"  level:"+level+"  menuType"+"   openId:"+openId);
		resultMap.put("openId", openId);
		resultMap.put("siteCode", siteCode);
		resultMap.put("level", level);
		resultMap.put("menuType", menuType);
		
		return "weixin";
	}
	/**
	 * @Description: 首页不可用链接页面数据获取--按扫描时间倒序排列
	 * @author cuichx --- 2016-3-30上午11:38:11
	 */
	public void getLinkHome(){
		Map<String, Object> returnMap=new HashMap<String, Object>();
		try {
			
			String siteCode="";//网站标识码
			String level="";//组织机构级别
			String menuType="";//类型
			String pageNo="";//查询页数
			JSONObject jo=getJSONObject();
			if(jo!=null && !"".equals(jo)){
				siteCode=jo.getString("siteCode");
				level=jo.getString("level");
				menuType=jo.getString("menuType");
				pageNo=jo.getString("counter");
			}
			
			logger.info("getLinkHome siteCode:"+siteCode+"  level:"+level+"  menuType:"+menuType+"  pageNo:"+pageNo);
			List<DatabaseInfo> databaseList=new ArrayList<DatabaseInfo>();

			//封装查询条件
			LinkHomeAvailableRequest linkHomerRequest=new LinkHomeAvailableRequest();
			
			if(StringUtils.isNotEmpty(siteCode)){
				if(siteCode.length()==6){//组织单位
					databaseList=queryDatebaseInfoListByType2(Integer.valueOf(level), menuType, siteCode);
					linkHomerRequest.setDatabaseList(databaseList);//网站集合
				}else{//填报单位
					linkHomerRequest.setSiteCode(siteCode);
				}
			}
			
			if(StringUtils.isNotEmpty(pageNo)){
				linkHomerRequest.setPageNo(Integer.valueOf(pageNo));
			}
			//每次加载四条数据
			linkHomerRequest.setPageSize(4);
			//设置排序字段
			List<QueryOrder> querySiteList=new ArrayList<QueryOrder>();
			QueryOrder siteQueryOrder=new QueryOrder("scan_time",QueryOrderType.DESC);
			querySiteList.add(siteQueryOrder);
			linkHomerRequest.setQueryOrderList(querySiteList);
			
			PageVo<LinkHomeAvailable>  linkPage=linkHomeAvailableServiceImpl.query(linkHomerRequest);
			List<LinkHomeAvailable> linkList=linkPage.getData();
			
			List<Object> returnList=new ArrayList<Object>();
			if(linkList!=null && linkList.size()>0){
				for (int i = 0; i < linkList.size(); i++) {
					LinkHomeAvailable linkHomeAvailable=linkList.get(i);
					Map<String, Object> map=new HashMap<String, Object>();
					//网站标识码
					if(StringUtils.isNotEmpty(linkHomeAvailable.getSiteCode())){
						map.put("siteCode", linkHomeAvailable.getSiteCode());
					}else{
						map.put("siteCode", "");
					}
					
					//网站名称
					DatabaseInfoRequest databaseInfoRequest=new DatabaseInfoRequest();
					databaseInfoRequest.setSiteCode(linkHomeAvailable.getSiteCode());
					
					List<DatabaseInfo> databaseinfoList=databaseInfoServiceImpl.queryList(databaseInfoRequest);
					if(databaseinfoList!=null && databaseinfoList.size()>0){
						DatabaseInfo databaseInfo=databaseinfoList.get(0);
						map.put("siteName", databaseInfo.getName());
					}else{
						map.put("siteName", "");
					}
					
					String linkTitle="";//链接标题
					if(StringUtils.isNotEmpty(linkHomeAvailable.getLinkTitle())){
						linkTitle=linkHomeAvailable.getLinkTitle();
					}
					map.put("linkTitle", linkTitle);
					
					String url="";//链接url
					if(StringUtils.isNotEmpty(linkHomeAvailable.getUrl())){
						url= linkHomeAvailable.getUrl();
					}
					map.put("url", url);
					
					//问题描述
					String questionDescribe="";
					if(StringUtils.isNotEmpty(linkHomeAvailable.getQuestionCode())){
						questionDescribe=linkHomeAvailable.getQuestionCode()+" "+QuestionType.getNameByCode(linkHomeAvailable.getQuestionCode());
					}
					map.put("questionDescribe", questionDescribe);
					
					//扫描日期
					if(StringUtils.isNotEmpty(linkHomeAvailable.getScanDate())){
						//将日期转换为年月日格式
						map.put("scanDate", DateUtils.formatDate(DateUtils.parseStandardDate(linkHomeAvailable.getScanDate())));
					}
					
					//监测时间
					if(StringUtils.isNotEmpty(linkHomeAvailable.getScanTime())){
						map.put("scanTime", DateUtils.formatStandardDateTime(DateUtils.parseStandardDateTime(linkHomeAvailable.getScanTime())));
					}
					returnList.add(map);
				}
				
    			
				returnMap.put("resultList", returnList);
				returnMap.put("pageTotal", linkPage.getPageTotal());
				returnMap.put("recordSize", linkPage.getRecordSize());
    			
    			writerPrint(JSONObject.fromObject(returnMap).toString());
    		}else{
    			returnMap.put("errorMsg", "暂无网站连不通数据");
    			writerPrint(JSONObject.fromObject(returnMap).toString());
    			return;
    		}
			
		} catch (Exception e) {
			e.printStackTrace();
			returnMap.put("errorMsg", "获取网站连不通数据异常");
			writerPrint(JSONObject.fromObject(returnMap).toString());
		}
	}
	
	
	/**
	 * @Description: 网站连不通页面初始化
	 * @author cuichx --- 2016-3-30上午11:35:11     
	 * @return
	 */
	public String connectionAllIndex(){
		resultMap=new HashMap<String, Object>();
		String siteCode=request.getParameter("siteCode");//网站标识码
		String level=request.getParameter("level");//组织机构级别
		String menuType=request.getParameter("menuType");//类型
		String openId=request.getParameter("openId");//微信唯一标识
		
		logger.info("connectionAllIndex siteCode:"+siteCode+"  level:"+level+"  menuType"+"   openId:"+openId);
		resultMap.put("openId", openId);
		resultMap.put("siteCode", siteCode);
		resultMap.put("level", level);
		resultMap.put("menuType", menuType);
		
		return "weixin";
	}
	
	/**
	 * @Description: 网站连不通页面数据获取--按扫描时间倒序排列
	 * @author cuichx --- 2016-3-30上午11:38:11
	 */
	public void getConnectionAll(){
		Map<String, Object> returnMap=new HashMap<String, Object>();
		try {
			
			String siteCode="";//网站标识码
			String level="";//组织机构级别
			String menuType="";//类型
			String pageNo="";//查询页数
			JSONObject jo=getJSONObject();
			if(jo!=null && !"".equals(jo)){
				siteCode=jo.getString("siteCode");
				level=jo.getString("level");
				menuType=jo.getString("menuType");
				pageNo=jo.getString("counter");
			}
			
			logger.info("getConnectionAll siteCode:"+siteCode+"  level:"+level+"  menuType:"+menuType+"  pageNo:"+pageNo);
			
			List<DatabaseInfo> databaseList=new ArrayList<DatabaseInfo>();
			
			//封装查询条件
			ConnectionAllRequest connectionRequest=new ConnectionAllRequest();
			
			if(StringUtils.isNotEmpty(siteCode)){
				if(siteCode.length()==6){//组织单位
					databaseList=queryDatebaseInfoListByType2(Integer.valueOf(level), menuType, siteCode);
					connectionRequest.setDatabaseList(databaseList);//网站集合
				}else{//填报单位
					connectionRequest.setSiteCode(siteCode);
				}
			}

			if(StringUtils.isNotEmpty(pageNo)){
				connectionRequest.setPageNo(Integer.valueOf(pageNo));
			}
			connectionRequest.setPageSize(4);
			//设置排序字段
			List<QueryOrder> querySiteList=new ArrayList<QueryOrder>();
			QueryOrder siteQueryOrder=new QueryOrder("scan_date",QueryOrderType.DESC);
			querySiteList.add(siteQueryOrder);
			connectionRequest.setQueryOrderList(querySiteList);
			
			PageVo<ConnectionAll>  connPage=connectionAllServiceImpl.query(connectionRequest);
			List<ConnectionAll> connList=connPage.getData();
			
			List<Object> returnList=new ArrayList<Object>();
			if(connList!=null && connList.size()>0){
				for (int i = 0; i < connList.size(); i++) {
					ConnectionAll connectionAll=connList.get(i);
					Map<String, Object> map=new HashMap<String, Object>();
					//网站标识码
					if(StringUtils.isNotEmpty(connectionAll.getSiteCode())){
						map.put("siteCode", connectionAll.getSiteCode());
					}else{
						map.put("siteCode", "");
					}
					
					//网站名称
					DatabaseInfoRequest databaseInfoRequest=new DatabaseInfoRequest();
					databaseInfoRequest.setSiteCode(connectionAll.getSiteCode());
					
					List<DatabaseInfo> databaseinfoList=databaseInfoServiceImpl.queryList(databaseInfoRequest);
					if(databaseinfoList!=null && databaseinfoList.size()>0){
						DatabaseInfo databaseInfo=databaseinfoList.get(0);
						map.put("siteName", databaseInfo.getName());
					}else{
						map.put("siteName", "");
					}
					
					//连不通类型
					String typeName="";
					if(StringUtils.isNotEmpty(connectionAll.getType()+"")){
						typeName=ConnectionType.getNameByCode(connectionAll.getType());
					}
					map.put("typeName", typeName);
					
					String url="";
					if(StringUtils.isNotEmpty(connectionAll.getUrl())){
						url= connectionAll.getUrl();
					}
					map.put("url", url);					
					
					//超时次数
					int errorNum=0;
					if(connectionAll.getErrorNum()>0){
						errorNum= connectionAll.getErrorNum();
					}
					map.put("errorNum", errorNum);
					
					//总次数
					int connectionSum=0;
					if(connectionAll.getConnectionSum()>0){
						connectionSum=connectionAll.getConnectionSum();
					}
					//超时比率
					if(connectionSum>0){
						map.put("errorProportion", StringUtils.formatDouble(2, 100.00*errorNum/connectionSum)+"%");
					}else{
						map.put("errorProportion","0%");
					}
					
					//扫描日期
					if(StringUtils.isNotEmpty(connectionAll.getScanDate())){
						//将日期转换为年月日格式
						map.put("scanDate", DateUtils.formatDate(DateUtils.parseStandardDate(connectionAll.getScanDate())));
					}
					returnList.add(map);
				}
    			
				returnMap.put("resultList", returnList);
				returnMap.put("pageTotal", connPage.getPageTotal());
				returnMap.put("recordSize", connPage.getRecordSize());
    			writerPrint(JSONObject.fromObject(returnMap).toString());
    		}else{
    			returnMap.put("errorMsg", "暂无网站连不通数据");
    			writerPrint(JSONObject.fromObject(returnMap).toString());
    			return;
    		}
			
		} catch (Exception e) {
			e.printStackTrace();
			returnMap.put("errorMsg", "获取网站连不通数据异常");
			writerPrint(JSONObject.fromObject(returnMap).toString());
		}
	}
	
	
	/**
	 * @Description:健康指数排名页面初始化 
	 * @author cuichx --- 2016-1-5下午10:09:04     
	 * @return
	 */
	public String indexCountIndex(){
		resultMap=new HashMap<String, Object>();
		String siteCode=request.getParameter("siteCode");//网站标识码
		String level=request.getParameter("level");//组织机构级别
		String menuType=request.getParameter("menuType");//类型
		String openId=request.getParameter("openId");//微信唯一标识
		
		logger.info("indexCountIndex siteCode:"+siteCode+"  level:"+level+"  menuType"+"   openId:"+openId);
		resultMap.put("openId", openId);
		resultMap.put("siteCode", siteCode);
		resultMap.put("level", level);
		resultMap.put("menuType", menuType);
		
		return "weixin";
	}
	
	/**
	 * @Description:健康指数排名页面数据获取
	 * @author cuichx --- 2016-1-4下午7:42:02
	 */
	public void getIndexCount(){
		Map<String, Object> returnMap=new HashMap<String, Object>();
		String siteCode=request.getParameter("siteCode");//网站标识码
		String level=request.getParameter("level");//组织机构级别
		String menuType=request.getParameter("menuType");//类型
		logger.info("getIndexCount siteCode:"+siteCode+"  level："+level+"  menuType:"+menuType);
		try {
			List<DatabaseInfo> websiteList=new ArrayList<DatabaseInfo>();

			websiteList=queryDatebaseInfoListByType2(Integer.valueOf(level), menuType, siteCode);
	    	String scanDate=DateUtils.getYesterdayStr();//获取昨天日期
	    	
	    	HashMap<String, Object> param=new HashMap<String, Object>();
	    	param.put("websiteList", websiteList);
	    	param.put("scanDate", scanDate);
	    	//param.put("type", IndexCountType.NEED_MONEY.getCode());//付费版
	    	
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
			logger.info("returnMap:"+returnMap);
			writerPrint(JSONObject.fromObject(returnMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description: 健康指数排名所有
	 * @author cuichx --- 2016-3-30下午8:19:37
	 */
	public void getIndexCountAll(){
		
		Map<String, Object> returnMap=new HashMap<String, Object>();
		String siteCode=request.getParameter("siteCode");//网站标识码
		String level=request.getParameter("level");//组织机构级别
		String menuType=request.getParameter("menuType");//类型
		logger.info("getIndexCountAll siteCode:"+siteCode+"  level："+level+"  menuType:"+menuType);
		try {
			List<DatabaseInfo> websiteList=new ArrayList<DatabaseInfo>();
			websiteList=queryDatebaseInfoListByType2(Integer.valueOf(level), menuType, siteCode);
	    	String scanDate=DateUtils.getYesterdayStr();//获取昨天日期
	    	
	    	HashMap<String, Object> param=new HashMap<String, Object>();
	    	param.put("websiteList", websiteList);
	    	param.put("scanDate", scanDate);
	    	
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
	 * @Description: 检测结果--组织单位
	 * @author cuichx --- 2015-12-21上午10:14:34     
	 * @return
	 */
	public String checkResultIndex(){
		
		logger.info("checkResultIndex begin");
		resultMap=new HashMap<String, Object>();
		try {
			String openId=request.getParameter("openId");
			if(StringUtils.isEmpty(openId)){
				String code=request.getParameter("code");
				logger.info("checkResultIndex openId:"+openId+"  code:"+code);
				WeixinOauth2Token weixinOauth2Token=CommonUtils.getWeixinOauth2Token(code);
			    //用户唯一标识
			    openId=weixinOauth2Token.getOpenId();
			}
			//String openId="oTfZiwy1Mc14i3w97q67B1Nt9BTM";
			//String openId="oTfZiw4AY4OB9B78s752TI1Yt0JA";
			logger.info("checkResultIndex openId:"+openId);
			
			if(StringUtils.isNotEmpty(openId)){
			    //判断该微信用户是否绑定帐户
			    Boolean isOrNot=bindAccountOrNot(openId);
			    if(isOrNot){//帐户已经绑定
					//根据微信用户唯一标识查询绑定帐户信息表
					AccountBindInfoRequest accRequest=new AccountBindInfoRequest();
					accRequest.setOpenId(openId);
					accRequest.setStatus(AccountBindStatus.ACCOUNT_BIND.getCode());
					List<AccountBindInfo>  accList=accountBindInfoServiceImpl.queryList(accRequest);
					
					String siteCode="";
					String siteName="";
					if(accList!=null){
						siteCode=accList.get(0).getSiteCode();
						siteName=accList.get(0).getSiteName();
					}
					//判断网站标识码的长度 如果为6位，走组织单位逻辑处理；如果为10位,走填报单位逻辑
					if(siteCode.length()==6){//组织单位
							//当前登录人的级别
							ShiroUser  user=getCountWebsiteInfo(siteCode);
							
							int count=user.getCount();//全部
							int currentCount=user.getCurrentCount();//本级部门
							int nextCount=user.getNextCount();//下属单位
							
							String level=CommonUtils.getLevel(siteCode);
							resultMap=currentCheckResult(level, "", siteCode);
							
							resultMap.put("count", count);
							resultMap.put("currentCount", currentCount);
							resultMap.put("nextCount", nextCount);
							resultMap.put("siteCode", siteCode);
							resultMap.put("level", level);
							resultMap.put("siteName", siteName);
							resultMap.put("openId", openId);
							//resultMap.put("hrefLink", "/token_indexCountIndex.action?siteCode="+siteCode+"&level="+level+"&openId="+openId+"&menuType=");
						
					}else{//10位，填报单位逻辑
						resultMap=checkResultTBIndex(siteCode);
						resultMap.put("siteCode", siteCode);
						resultMap.put("siteName", siteName);
					}
			    }else{//帐号未绑定，跳转到绑定帐户页面,同时给微信客户发送一条提示信息
			    	request.getRequestDispatcher("/token_accountBindIndex.action?openId="+openId+"&fromType=checkResult").forward(request, response);
			    }
			}else{
				resultMap.put("errorMsg", "微信用户的唯一标识不能为空");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("errorMsg", "获取检测结果失败！");
		}
		return "weixin";
	}
	
	/**
	 * @Description: 检测结果---填报单位
	 * @author cuichx --- 2015-12-28下午12:47:34     
	 * @return
	 */
	public Map<String, Object> checkResultTBIndex(String siteCode){
		Map<String, Object> checkMap=new HashMap<String, Object>();
		try {
			
			String scanDate=DateUtils.getYesterdayStr();//获取昨天的日期YYYY-MM-dd
			
			/**
			 * 健康指数
			 */
        	String totalSum="0";//昨日健康指数
        	String convertScores="0";//折算分数
        	String gtPercent="0";//领先全国网站的百分比
        	
        	/**
        	 * 色块统计数据
        	 */
        	Integer connNumTB=0;//网站连通性     不连通统计总数
        	Integer linkNumTB=0;//链接不可用性统计总数--只统计首页链接不可用的
        	Integer websiteSafeTB=0;//网络安全
        	Integer contGuaranteNumTB=0;//内容保障问题总数
        	Integer contCorrectNumTB=0;//内容正确性
        	Integer contUpdateTB=0;//内容更新统计总数 
			
			//通过网站标识码、昨天日期查询 检测结果列表
			DetectionResultRequest detectionRequest=new DetectionResultRequest();
			detectionRequest.setSiteCode(siteCode);
			detectionRequest.setScanDate(scanDate);
			List<DetectionResult> detectionList=detectionResultServiceImpl.queryList(detectionRequest);
			if(detectionList!=null && detectionList.size()>0){
				DetectionResult result=detectionList.get(0);
				
				totalSum=result.getIndexCount();//昨日健康指数
				convertScores=result.getIndexCount();//折算分数
				gtPercent=result.getLeadAvgRate();//领先全国网站的百分比
				
				connNumTB=result.getConnNum();//网站连通性     不连通统计总数
				linkNumTB=result.getLinkNum();//链接不可用性统计总数--只统计首页链接不可用的
				contGuaranteNumTB=result.getContGuaranteNum();//内容保障问题总数
				contCorrectNumTB=result.getContCorrectNum();//内容正确性
				contUpdateTB=result.getContUpdate();//内容更新统计总数 
			}
			
			checkMap.put("totalSum", totalSum);//健康指数
			checkMap.put("zhesuan", convertScores);//折算分数
			checkMap.put("gtPercent", gtPercent);//健康指数领先全国网站的百分比
			
			checkMap.put("connNumTB", connNumTB);//网站不连通个数
			checkMap.put("linkNumTB", linkNumTB);//不可用链接个数
			checkMap.put("contGuaranteNumTB", contGuaranteNumTB);//内容保障问题个数
			checkMap.put("contCorrectNumTB", contCorrectNumTB);//内容正确性
			checkMap.put("websiteSafeTB", websiteSafeTB);//网络安全
			checkMap.put("contUpdateTB", contUpdateTB);//内容更新个数
			
			
			
			} catch (Exception e) {
			e.printStackTrace();
			checkMap.put("errorMsg", "获取检测结果失败！");
		}
		return checkMap;
	}
	/**
	 * @Description: 当前检测结果页面功能--页面发送ajax请求
	 * @author cuichx --- 2015-12-24下午4:14:59     
	 * @return
	 */
	public void getcurrentCheckResult(){
		Map<String, Object> returnMap=new HashMap<String, Object>();
		//获取页面传递过来的数据
		String siteCode=request.getParameter("siteCode");//当前网站标识码
		String menuType=request.getParameter("menuType");
		String level=request.getParameter("level");
		logger.info("getcurrentCheckResult siteCode:"+siteCode+"  menuType:"+menuType+"  level:"+level);

		
		try {
			returnMap=currentCheckResult(level,menuType,siteCode);
			logger.info("getcurrentCheckResult:"+returnMap);
			writerPrint(JSONObject.fromObject(returnMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Description: 获取统计数据
	 * @author cuichx --- 2015-12-24下午4:15:19     
	 * @param level
	 * @param menuType 
	 * @param siteCode 
	 * @return
	 */
    public  Map<String,Object> currentCheckResult(String level,String menuType,String siteCode) {
    	Map<String,Object> map=new HashMap<String, Object>();
    	try {
    		
    		String scanDate=DateUtils.getYesterdayStr();//获取昨天的日期
        	//统计昨天的数据   
        	Integer connNum=0;//网站不连通个数
        	Integer linkNum=0;//不可用链接个数
        	Integer contUpdate=0;//内容更新个数
        	Integer websiteSafe=0;//网络安全
        	Integer contGuaranteNum=0;//内容保障问题个数
        	Integer contCorrectNum=0;//内容正确性
        	
        	String totalSum="0";//健康指数
        	String convertScores="0";//折算分数
    		String gtPercent="0";//领先全国健康指数百分比
    		//根据当前登录组织机构级别以及menuType， 获取检测结果列表中的type
    		Integer type=getDetectionOrgType(Integer.valueOf(level), menuType);
    		
    		
    		if(type!=1){//非门户
        		//通过组织机构编码、扫描日期、类型查询组织机构检测结果列表
        		DetectionOrgCountRequest orgResultRequest=new DetectionOrgCountRequest();
        		orgResultRequest.setSiteCode(siteCode);
        		orgResultRequest.setScanDate(scanDate);
        		orgResultRequest.setType(String.valueOf(type));
        		List<DetectionOrgCount> orgList=detectionOrgCountServiceImpl.queryList(orgResultRequest);
        		if(orgList!=null && orgList.size()>0){
        			DetectionOrgCount orgCount=orgList.get(0);
        			
        			totalSum=orgCount.getIndexCount();//健康指数
        			convertScores=orgCount.getIndexCount();//折算分数
        			gtPercent=orgCount.getLeadAvgRate();//领先全国健康指数百分比
        			
        			connNum=Integer.valueOf(orgCount.getConnNum());//连不通
        			linkNum=orgCount.getLinkNum();//不可用连接
        			contUpdate=orgCount.getContUpdate();//内容更新
        			contGuaranteNum=orgCount.getContGuaranteNum();//内容保障问题
        			contCorrectNum=orgCount.getContCorrectNum();//内容正确性
        		}
    		}else{//门户网站
    			
    			DatabaseInfoRequest dataInfoRequest=new DatabaseInfoRequest();
    			dataInfoRequest.setSiteCodeLike(siteCode);
    			dataInfoRequest.setIsorganizational(1);
    			List<DatabaseInfo>  infoList=databaseInfoServiceImpl.queryList(dataInfoRequest);
    			String siteCodeTb=infoList.get(0).getSiteCode();
    			
    			DetectionResultRequest resultRequest=new DetectionResultRequest();
    			resultRequest.setSiteCode(siteCodeTb);
    			resultRequest.setScanDate(scanDate);
    			
    			List<DetectionResult> resultList=detectionResultServiceImpl.queryList(resultRequest);
    			if(resultList!=null && resultList.size()>0){
    				DetectionResult result=resultList.get(0);
    				
        			totalSum=result.getIndexCount();//健康指数
        			convertScores=result.getIndexCount();//折算分数
        			gtPercent=result.getLeadAvgRate();//领先全国健康指数百分比
        			
        			connNum=Integer.valueOf(result.getConnNum());//连不通
        			linkNum=result.getLinkNum();//不可用连接
        			contUpdate=result.getContUpdate();//内容更新
        			contGuaranteNum=result.getContGuaranteNum();//内容保障问题
        			contCorrectNum=result.getContCorrectNum();//内容正确性
    				
    			}
    			
    		}

			map.put("totalSum", totalSum);//健康指数统计总分
			map.put("zhesuan", convertScores);//折算分数
			map.put("gtPercent", gtPercent);//领先全国健康指数百分比
			
        	map.put("connNum", connNum);//网站不连通个数
        	map.put("linkNum", linkNum);//不可用链接个数
        	map.put("contGuaranteNum", contGuaranteNum);//内容保障问题个数
        	map.put("contCorrectNum", contCorrectNum);//内容正确性
        	map.put("websiteSafe", websiteSafe);//网络安全
        	map.put("contUpdate", contUpdate);//内容更新个数
        	return map;
		} catch (Exception e) {
			e.printStackTrace();
			map.put("errmsg", "获取当前检测结果失败！");
			return map;
		}
    	
	}
    /**
     * @Description: 根据网站标识码集合，查询与预警详情表
     * @author cuichx --- 2015-12-18下午4:51:09     
     * @return
     */
    public  String earlyDetailIndex(){
    	
    	logger.info("earlyDetailIndex begin");
    	resultMap=new HashMap<String, Object>();
    	try {
    		String openId=request.getParameter("openId");
			if(StringUtils.isEmpty(openId)){
				String code=request.getParameter("code");
				logger.info("earlyDetailIndex openId:"+openId+"  code:"+code);
				WeixinOauth2Token weixinOauth2Token=CommonUtils.getWeixinOauth2Token(code);
			    //用户唯一标识
			    openId=weixinOauth2Token.getOpenId();
			}
    		//String openId="oTfZiwy1Mc14i3w97q67B1Nt9BTM";
    		//String openId="oTfZiw4AY4OB9B78s752TI1Yt0JA";
			logger.info("openId:"+openId);
			if(StringUtils.isNotEmpty(openId)){
	    		AccountBindInfoRequest accBindRequest=new AccountBindInfoRequest();
	    		accBindRequest.setOpenId(openId);
	    		accBindRequest.setStatus(AccountBindStatus.ACCOUNT_BIND.getCode());//绑定帐户信息
	    		
	    		 List<AccountBindInfo> accBindList=accountBindInfoServiceImpl.queryList(accBindRequest);
	    		 if(accBindList!=null && accBindList.size()>0){
	    	    	String siteCode=accBindList.get(0).getSiteCode();
	    	    	resultMap.put("siteCode", siteCode);
	    	    	resultMap.put("openId", openId);
	    		 }else{//未绑定帐户，跳转到绑定帐户页面
	 		    	request.getRequestDispatcher("/token_accountBindIndex.action?openId="+openId+"&fromType=earlyDetail").forward(request, response);
	 		    }
			}else{
				resultMap.put("errorMsg", "微信用户唯一标识不能为空");
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}
    	return "weixin";
    }
    /**
     * @Description: 预警详情页面发送ajax请求，获取预警详情信息
     * @author cuichx --- 2016-4-12上午9:46:28
     */
    public void getEarlyDetail(){
    	//封装页面查询数据
    	Map<String,Object> resultMap=new HashMap<String, Object>();
    	
    	//获取页面传递的参数
    	String siteCode=request.getParameter("siteCode");//网站标识码
    	String pageNo=request.getParameter("counter");//查询起始条数
    	
    	logger.info("getEarlyDetail siteCode:"+siteCode+"  pageNo:"+pageNo);
    	
    	try {
    		EarlyDetailRequest earlyRequest= new EarlyDetailRequest();
    		if(StringUtils.isNotEmpty(pageNo)){
    			earlyRequest.setPageNo(Integer.valueOf(pageNo));
    		}
    		earlyRequest.setPageSize(4);//每次下拉展示多加载4条
    		if(StringUtils.isNotEmpty(siteCode)){
    			if(siteCode.length()==6){//组织单位
                	//获取当前组织结构下面的所有网站标识码集合
                	List<DatabaseInfo> websiteList=new ArrayList<DatabaseInfo>();
                	
                	String level=CommonUtils.getLevel(siteCode);
                	websiteList=queryDatebaseInfoListByType2(Integer.valueOf(level), "", siteCode);
                	
                	earlyRequest.setWebsiteList(websiteList);
                	earlyRequest.setCheckType(1);//正常合同
                	earlyRequest.setTypes("1,6");//1-首页连通性  6 内容正确性
    			}else{//填报单位
    				earlyRequest.setSiteCode(siteCode);
                	earlyRequest.setCheckType(1);//正常合同
                	earlyRequest.setTypes("1,6");//1-首页连通性  6 内容正确性
    			}
    			
				List<QueryOrder> querySiteList=new ArrayList<QueryOrder>();
				QueryOrder siteQueryOrder=new QueryOrder("scan_time",QueryOrderType.DESC);
				querySiteList.add(siteQueryOrder);
				earlyRequest.setQueryOrderList(querySiteList);
    			
    			
        		PageVo<EarlyDetail> earlyPage=earlyDetailServiceImpl.query(earlyRequest);
        		List<EarlyDetail> earlyList =earlyPage.getData();
        		if(earlyList!=null && earlyList.size()>0){
        	    	// 预警详情表页面数据初始化集合
        	    	List<Map<String, Object>> resultList=new ArrayList<Map<String, Object>>();
        			//遍历封装页面需要的数据
        			for (EarlyDetail earlyDetail : earlyList) {
                		Map<String, Object> map=new HashMap<String, Object>();
                		
                		//获取网站名称
                		String siteName="";
                		DatabaseInfoRequest dataRequest=new DatabaseInfoRequest();
                		dataRequest.setSiteCode(earlyDetail.getSiteCode());
                		List<DatabaseInfo> dataList=databaseInfoServiceImpl.queryList(dataRequest);
                		if(dataList!=null && dataList.size()>0){
                			siteName=dataList.get(0).getName();
                		}
                		map.put("siteName", siteName);
                		
                		if(StringUtils.isNotEmpty(earlyDetail.getScanTime())){
                    		Date dt=DateUtils.parseStandardDate(earlyDetail.getScanTime());
                    		map.put("scanDate", DateUtils.formatDate(dt));
                		}else{
                			map.put("scanDate", "");
                		}
                		
                		map.put("siteCode", earlyDetail.getSiteCode());//网站标识码
                		
                		map.put("dicChannelId", EarlyType.getNameByCode(earlyDetail.getType()));//预警类型
                		
                		map.put("type", earlyDetail.getType());
                		
                		String queDescribe="";
                		if(StringUtils.isNotEmpty(earlyDetail.getErrorCode())){
                			queDescribe = earlyDetail.getErrorCode() + " " + QuestionType.getNameByCode(earlyDetail.getErrorCode()+ "");//问题描述
                		}
                		map.put("queDescribe", queDescribe);//问题描述

                		//疑似错误
                		if(StringUtils.isNotEmpty(earlyDetail.getWrongTerm())){
                			map.put("wrongTerm", earlyDetail.getWrongTerm());
                		}else{
                			map.put("wrongTerm", "");
                		}
                		//推荐修改
                		if(StringUtils.isNotEmpty(earlyDetail.getExpectTerms())){
                			map.put("expectTerms", earlyDetail.getExpectTerms());
                		}else{
                			map.put("expectTerms", "");
                		}
               
                		map.put("scanTime",earlyDetail.getScanTime());
                		
                		if(StringUtils.isNotEmpty(earlyDetail.getUrl())){
                			map.put("url", earlyDetail.getUrl());//跳转网址
                		}else{
                			map.put("url", "");//首页网址
                		}
                		resultList.add(map);
					}
        			
        			resultMap.put("resultList", resultList);
        			resultMap.put("pageTotal", earlyPage.getPageTotal());
        			resultMap.put("recordSize", earlyPage.getRecordSize());
        			
        			writerPrint(JSONObject.fromObject(resultMap).toString());
        		}else{
        			resultMap.put("errorMsg", "暂无预警数据信息");
        			writerPrint(JSONObject.fromObject(resultMap).toString());
        			return;
        		}
    		}else{
    			resultMap.put("errorMsg", "页面传递的网站标识码不能为空");
    			writerPrint(JSONObject.fromObject(resultMap).toString());
    			return;
    		}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("errorMsg", "获取预警信息异常");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		}
    }
    /**
     * @Description: 帐户绑定帐户信息表
     * @author cuichx --- 2015-12-27下午3:46:03     
     * @return
     */
    public String accountBindIndex(){
    	resultMap=new HashMap<String, Object>();
    	try {
    		//区别是由那个页面跳转过来的    监测结果页面  预警页面  管理中心页面
    		String fromType=request.getParameter("fromType");
    		String openId=request.getParameter("openId");//微信客户唯一标识
			if(StringUtils.isEmpty(openId)){
	    		String code=request.getParameter("code");
	    		logger.info("accountBindIndex openId:"+openId+"  code:"+code);
				WeixinOauth2Token weixinOauth2Token=CommonUtils.getWeixinOauth2Token(code);
			    //用户唯一标识
			    openId=weixinOauth2Token.getOpenId();
			}
			
			//String openId="oTfZiwy1Mc14i3w97q67B1Nt9BTM";
			logger.info("accountBindIndex openId:"+openId);
			if(StringUtils.isNotEmpty(openId)){
				//判断帐户是否已经绑定帐户，如果用户已经绑定帐户，跳转到绑定帐户成功页面；如果未绑定则跳转到绑定帐户页面
				AccountBindInfoRequest accRequest=new AccountBindInfoRequest();
				accRequest.setOpenId(openId);
				accRequest.setStatus(AccountBindStatus.ACCOUNT_BIND.getCode());//绑定帐户有效
				List<AccountBindInfo> accBindList=accountBindInfoServiceImpl.queryList(accRequest);
				if(accBindList!=null && accBindList.size()>0){//绑定帐户已经存在
					String siteCode=accBindList.get(0).getSiteCode();
					request.getRequestDispatcher("/token_removeAccountBindIndex.action?siteCode="+siteCode+"&openId="+openId).forward(request, response);
				}else{
			    	resultMap.put("openId", openId);//用户唯一标识
			    	resultMap.put("fromType", fromType);//标识是从哪个页面跳转到绑定帐户页面
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
    	return "weixin";
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
        		List<DatabaseInfo> dataInfoList=null;
        		Boolean flag=false;
        		if(siteCode.length()==6){//组织单位
            		//组织单位的话，需要发送一个http请求其他系统，截取其他系统中的返回值
        			/*String param = "{\"email\":\""+siteCode+"\",\"pass\":\""+pwd+"\"}";
        			temp = CommonUtils.post(urlAdapterVar.getLoginHttpPostUrl(),param);
        			logger.info("https请求验证客户编号和密码  temp:"+temp);*/
					//组织单位登录时，帐号和密码验证
					DatabaseOrgInfoRequest dataOrgrRequest=new DatabaseOrgInfoRequest();
					dataOrgrRequest.setStieCode(siteCode);
					dataOrgrRequest.setVcode(pwd);
					List<DatabaseOrgInfo> dataOrgList=databaseOrgInfoServiceImpl.queryList(dataOrgrRequest);
					if(dataOrgList!=null && dataOrgList.size()>0){
						flag=true;
					}
        		}else{//长度为10，填报单位
        			//根据网站标识码，查询database_info表，判断输入的用户面和密码是否正确
        			DatabaseInfoRequest databaseRequest=new DatabaseInfoRequest();
        			databaseRequest.setSiteCodeLike(siteCode);
        			databaseRequest.setVcode(pwd);
        			dataInfoList=databaseInfoServiceImpl.queryList(databaseRequest);
        			/**
        			 * 如果填报单位为门户网站，需要到组织单位去绑定对应的组织单位帐号，不能直接绑定填报单位帐号
        			 */
/*        			if(dataInfoList!=null && dataInfoList.size()>0){
        				int isorganizational=dataInfoList.get(0).getIsorganizational();
        				if(isorganizational==1){//门户网站
        					returnMap.put("jumpPage", "请用您的组织单位帐号进行绑定！");
        					writerPrint(JSONObject.fromObject(returnMap).toString());
        					return;
        				}
        			}*/
        		}
        		
        		//用户名和密码验证成功
    			if(flag || (dataInfoList!=null && dataInfoList.size()>0)){
    				contractInfoRequest.setTypeFlag(1);//正式合同   非抽查合同
    				contractInfoRequest.setNowTime(DateUtils.formatStandardDate(new Date()));//判断合同唯一性
    	    		//根据网站标识码查询合同信息表
    				List<ContractInfo> contractList=contractInfoServiceImpl.queryList(contractInfoRequest);
        			/**
        			 * 组织单位处理逻辑
        			 * 		判断合同是否存在（siteCode判断），如果存在，查询微信绑定帐户信息表是否有记录(openId查询)，
        			 * 				如果有记录，说明该微信用户已经绑定过了，提示错误信息；如果没有记录，则绑定该微信用户信息
        			 * 填报单位处理逻辑：
        			 * 		判断合同是否存在（siteCode判断）
        			 * 			如果存在，处理逻辑和组织单位逻辑相同
        			 * 			如果不存在，也将该微信用户保存到微信绑定帐户信息表中，将status状态设置为0（仅能查看信息，不能进行任何操作）
        			 */
    	    		
        			if(siteCode.length()==10 && (contractList==null || contractList.size()==0)){//填报单位且没有合同
        				AccountBindInfoRequest accRequest=new AccountBindInfoRequest();
        				accRequest.setOpenId(openId);
        				accRequest.setStatus(AccountBindStatus.ACCOUNT_BIND.getCode());
        				//查询微信绑定帐户新表中是否存在记录，如果存在记录，提示错误信息（该微信用户已经绑定！），
        				//如果不存在记录，保存该信息并将is_customer设置为0（仅查看）
        				List<AccountBindInfo> accountList=accountBindInfoServiceImpl.queryList(accRequest);
        				if(accountList!=null && accountList.size()>0){
        					//存在为解绑记录    提示错误信息
        					if(accountList.get(0).getStatus()==AccountBindStatus.ACCOUNT_BIND.getCode()){
        						returnMap.put("errormsg", "该微信用户已经绑定！");
        						writerPrint(JSONObject.fromObject(returnMap).toString());
        						return;
        					}
        				}else{//如果不存在，添加
        					
        					/**
        					 * 获取微信昵称
        					 */
                    		String nickname="";
                        	//获取访问权限access_token
                        	String access_token=CommonUtils.getTokenFromServlet();
                        	//拼装请求url
                        	String requestUrl="https://api.weixin.qq.com/cgi-bin/user/info?access_token="+
                        				access_token+"&openid="+openId+"&lang=zh_CN";
                        	//发送https请求获取微信用户的昵称
                        	JSONObject jsonStr=CommonUtils.httpRequest(requestUrl, "POST", null);
                        	logger.info("httpRequest nickname_jsonStr:"+jsonStr);
                        	if(StringUtils.isNotNull(jsonStr)){
                        		nickname=(String) jsonStr.get("nickname");
                        		//过滤掉微信昵称中的emoji表情字符串
                        		nickname=CommonUtils.removeFaceCharacter(nickname);
                        		
                        		logger.info(((String) jsonStr.get("nickname"))+":removeFaceCharacter after:"+nickname);
                        	}

                			/**
                			 * 获取网站名称
                			 */
                			DatabaseInfoRequest datarInfoRequest=new DatabaseInfoRequest();
                			datarInfoRequest.setSiteCode(siteCode);
                			List<DatabaseInfo>  dataList=databaseInfoServiceImpl.queryList(datarInfoRequest);
                			String siteName=dataList.get(0).getName();
        					
                			logger.info("saveAccountBind TB  siteName:"+siteName);
                			AccountBindInfo accountBindInfo=new AccountBindInfo();
                			accountBindInfo.setOpenId(openId);
                			accountBindInfo.setSiteCode(siteCode);
                			accountBindInfo.setSiteName(siteName);
                			accountBindInfo.setNickname(nickname);//微信用户昵称
                			accountBindInfo.setStatus(AccountBindStatus.ACCOUNT_BIND.getCode());//绑定
                			accountBindInfo.setIsCustomer(TrueOrFalseType.FALSE.getCode());//该填报单位不存在客户信息
                			//accountBindInfo.setSiteListReportStatus(ReceiveType.IS_RECEIVE.getCode());//默认接收站群报告通知
                			accountBindInfo.setSiteListEarlyStatus(ReceiveType.IS_RECEIVE.getCode());//默认接收站群预警通知
                			//accountBindInfo.setLocalhostReportStatus(ReceiveType.IS_RECEIVE.getCode());//默认接收本机门户报告通知
                			accountBindInfo.setLocalhostEarlyStatus(ReceiveType.IS_RECEIVE.getCode());//默认接收本机门户预警通知
                			accountBindInfo.setCreateTime(DateUtils.parseStandardDateTime(
                						DateUtils.formatStandardDateTime(new Date())));//创建时间
                			accountBindInfoServiceImpl.add(accountBindInfo);
                			
                			returnMap.put("success", "微信绑定成功！");
                			writerPrint(JSONObject.fromObject(returnMap).toString());
        				}
        			}else{//组织单位和填报单位（有合同）
        				//CustomerInfo customerInfo=custList.get(0);
        				String companyName="";//绑定帐户名称
        				if(siteCode.length()==6){//组织单位,查询database_org_info获取组织单位名称
        					DatabaseOrgInfoRequest orgInfoRequest=new DatabaseOrgInfoRequest();
        					orgInfoRequest.setStieCode(siteCode);
        					List<DatabaseOrgInfo>  orgList=databaseOrgInfoServiceImpl.queryList(orgInfoRequest);
        					companyName=orgList.get(0).getName();
        				}else{//填报单位  查询database_info表获取网站名称
               				DatabaseInfoRequest databaseRequest=new DatabaseInfoRequest();
            				databaseRequest.setSiteCode(siteCode);
            				List<DatabaseInfo> dataList=databaseInfoServiceImpl.queryList(databaseRequest);
            				companyName=dataList.get(0).getName();
        				}
        				
        				logger.info("saveAccountBind ORG companyName:"+companyName);
                		//(微信唯一标示+绑定状态) 判断是否已经绑定了帐号，如果绑定了，提示错误信息
            			paramMap.put("status", AccountBindStatus.ACCOUNT_BIND.getCode());//状态为绑定的
            			paramMap.remove("pwd");
            			paramMap.remove("siteCode");
            			AccountBindInfoRequest accBindRequest=accountBindInfoServiceImpl.queryByMap(paramMap);
                		if(accBindRequest!=null){
        					//存在    提示错误信息
        					if(accBindRequest.getStatus()==AccountBindStatus.ACCOUNT_BIND.getCode()){
        						returnMap.put("errormsg", "该微信用户已经绑定！");
        						writerPrint(JSONObject.fromObject(returnMap).toString());
        						return;
        					}
                		}else{
                    		String nickname="";
                        	//获取访问权限access_token
                        	String access_token=CommonUtils.getTokenFromServlet();
                        	//拼装请求url
                        	String requestUrl="https://api.weixin.qq.com/cgi-bin/user/info?access_token="+
                        				access_token+"&openid="+openId+"&lang=zh_CN";
                        	//发送https请求获取微信用户的昵称
                        	JSONObject jsonStr=CommonUtils.httpRequest(requestUrl, "GET", null);
                        	if(StringUtils.isNotNull(jsonStr)){
                        		nickname=(String) jsonStr.get("nickname");
                        		//过滤掉微信昵称中的emoji表情字符串
                        		nickname=CommonUtils.removeFaceCharacter(nickname);
                        	}
                        	
                			AccountBindInfo accountBindInfo=new AccountBindInfo();
                			accountBindInfo.setOpenId(openId);
                			accountBindInfo.setSiteCode(siteCode);
                			accountBindInfo.setSiteName(companyName);
                			accountBindInfo.setNickname(nickname);//微信用户昵称
                			accountBindInfo.setStatus(AccountBindStatus.ACCOUNT_BIND.getCode());//绑定状态
                			if(contractList!=null && contractList.size()>0){
                				accountBindInfo.setIsCustomer(TrueOrFalseType.TRUE.getCode());//该填报单位或者组织单位有合同
                			}else{
                				accountBindInfo.setIsCustomer(TrueOrFalseType.FALSE.getCode());//该组织单位没有合同
                			}
                			//accountBindInfo.setSiteListReportStatus(ReceiveType.IS_RECEIVE.getCode());//默认接收站群报告通知
                			accountBindInfo.setSiteListEarlyStatus(ReceiveType.IS_RECEIVE.getCode());//默认接收站群预警通知
                			//accountBindInfo.setLocalhostReportStatus(ReceiveType.IS_RECEIVE.getCode());//默认接收本机门户报告通知
                			accountBindInfo.setLocalhostEarlyStatus(ReceiveType.IS_RECEIVE.getCode());//默认接收本机门户预警通知
                			accountBindInfo.setCreateTime(DateUtils.parseStandardDateTime(
                						DateUtils.formatStandardDateTime(new Date())));//创建时间
                			accountBindInfoServiceImpl.add(accountBindInfo);
                			
                			returnMap.put("success", "微信绑定成功！");
                			writerPrint(JSONObject.fromObject(returnMap).toString());
                		}
    	    		}
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
     * @Description: 管理中心页面初始化--组织单位、填报单位
     * @author cuichx --- 2015-12-27下午6:02:13     
     * @return
     */
    public String managementCenterIndex(){
    	resultMap=new HashMap<String, Object>();
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
			resultMap.put("openId", openId);
			if(StringUtils.isNotEmpty(openId)){
			    //判断该微信用户是否绑定帐户
			    Boolean isOrNot=bindAccountOrNot(openId);
			    if(isOrNot){//帐户已经绑定
		    		//根据微信用户唯一标识查询该微信是否已经绑定了帐号
		    		AccountBindInfoRequest accRequest=new AccountBindInfoRequest();
		    		accRequest.setOpenId(openId);
		    		accRequest.setStatus(AccountBindStatus.ACCOUNT_BIND.getCode());
		    		List<AccountBindInfo> accList=accountBindInfoServiceImpl.queryList(accRequest);
		    		if(accList!=null && accList.size()>0){
		    			resultMap.put("siteCode", accList.get(0).getSiteCode());
		    		}
			    }else{//未绑定帐户，跳转到绑定帐户页面
			    	request.getRequestDispatcher("/token_accountBindIndex.action?openId="+openId+"&fromType=managementCenter").forward(request, response);
			    }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return "weixin";
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
        		/**
        		 * 接受设置页面的信息
        		 */
/*        		DatabaseInfoRequest dataRequest=new DatabaseInfoRequest();
        		dataRequest.setSiteCodeLike(siteCode);
        		dataRequest.setIsexp(1);
        		dataRequest.setIsorganizational(1);
        		List<DatabaseInfo> databList=databaseInfoServiceImpl.queryList(dataRequest);*/
    			
				DatabaseOrgInfoRequest orgInfoRequest=new DatabaseOrgInfoRequest();
				orgInfoRequest.setStieCode(siteCode);
				List<DatabaseOrgInfo>  orgList=databaseOrgInfoServiceImpl.queryList(orgInfoRequest);
        		
        		if(orgList!=null && orgList.size()>0){
        			DatabaseOrgInfo dataInfo=orgList.get(0);
            		/**
            		 * 管理信息页面的信息
            		 */
            		returnMap.put("siteCode", siteCode);//客户编号（绑定帐户）
        			if(siteCode.length()==10){//帐户类型为 填报单位
        				returnMap.put("accountType",AccountType.ACCOUNT_TB.getName());
        			}else{//帐户类型为组织单位
        				returnMap.put("accountType",AccountType.ACCOUNT_ORG.getName());
        			}
        			
        			returnMap.put("localMHSiteName", dataInfo.getName());//门户网站名称
        			returnMap.put("companyName", dataInfo.getName());//单位名称
 
        			//查询绑定帐户信息表
        			AccountBindInfoRequest accountRequest=new AccountBindInfoRequest();
        			accountRequest.setSiteCode(siteCode);
        			accountRequest.setOpenId(openId);
        			accountRequest.setStatus( AccountBindStatus.ACCOUNT_BIND.getCode());//状态为绑定的
        			
        			String level=CommonUtils.getLevel(siteCode);
        			List<DatabaseInfo> baseList=queryDatebaseInfoListByType2(Integer.valueOf(level), "", siteCode);
        			if(baseList!=null && baseList.size()>0){
        				returnMap.put("siteSum",baseList.size());//网站总数
        			}else{
        				returnMap.put("siteSum", 0);//网站总数
        			}
        			
        			List<AccountBindInfo> accountList=accountBindInfoServiceImpl.queryList(accountRequest);
            		if(accountList!=null && accountList.size()>0){
            			AccountBindInfo accountInfo=accountList.get(0);
            			returnMap.put("localhostEarlyStatus", accountInfo.getLocalhostEarlyStatus());//本级门户预警
            			//returnMap.put("localhostReportStatus", accBindRequest.getLocalhostReportStatus());//本级门户报告通知
            			returnMap.put("siteListEarlyStatus", accountInfo.getSiteListEarlyStatus());//站群预警
            			//returnMap.put("siteListReportStatus", accBindRequest.getSiteListReportStatus());//站群报告通知

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
	 * @Description:解除绑定帐户页面
	 * @author cuichx --- 2016-1-7下午7:31:12     
	 * @return
	 */
	public String removeAccountBindIndex(){
		
		resultMap=new HashMap<String, Object>();
		
		//String siteCode="110000";
		String siteCode=request.getParameter("siteCode");
		String openId=request.getParameter("openId");
		logger.info("removeAccountBindIndex siteCode:"+siteCode+"   openId:"+openId);
		
		
		resultMap.put("siteCode", siteCode);
		resultMap.put("openId", openId);
		String custType="";
		//判断帐户类型
		if(siteCode.length()==10){//填报单位
			custType=AccountType.ACCOUNT_TB.getName();
		}else{//组织单位
			custType=AccountType.ACCOUNT_ORG.getName();
		}
		resultMap.put("custType", custType);
		
		int siteNum=0;//获取网站个数
		if(siteCode.length()==6){//组织单位
			Map<String, Object> paramMap=new HashMap<String, Object>();
			paramMap.put("siteCode", siteCode);
			
			String level=CommonUtils.getLevel(siteCode);
			
			List<DatabaseInfo>  currentNextSiteCode=queryDatebaseInfoListByType2(Integer.valueOf(level), "", siteCode);
	 		if(currentNextSiteCode!=null && currentNextSiteCode.size()>0){
	 			siteNum=currentNextSiteCode.size();
	 		}
		}else{//填报单位
			siteNum=1;
		}

 		resultMap.put("siteNum", siteNum);
 		
 		//查询单位名称
/*		DatabaseInfoRequest databaseRequest=new DatabaseInfoRequest();
		databaseRequest.setSiteCodeLike(siteCode);
		if(siteCode.length()==6){//组织单位获取门户的单位名称
			databaseRequest.setIsorganizational(1);//门户
		}
		List<DatabaseInfo> dataList=databaseInfoServiceImpl.queryList(databaseRequest);
		String companyName=dataList.get(0).getDirector();*/
 		
 		String companyName="";
		if(siteCode.length()==6){//组织单位,查询database_org_info获取组织单位名称
			DatabaseOrgInfoRequest orgInfoRequest=new DatabaseOrgInfoRequest();
			orgInfoRequest.setStieCode(siteCode);
			List<DatabaseOrgInfo>  orgList=databaseOrgInfoServiceImpl.queryList(orgInfoRequest);
			companyName=orgList.get(0).getName();
		}else{//填报单位  查询database_info表获取网站名称
			DatabaseInfoRequest databaseRequest=new DatabaseInfoRequest();
			databaseRequest.setSiteCodeLike(siteCode);
			if(siteCode.length()==6){//组织单位获取门户的单位名称
				databaseRequest.setIsorganizational(1);//门户
			}
			List<DatabaseInfo> dataList=databaseInfoServiceImpl.queryList(databaseRequest);
			companyName=dataList.get(0).getDirector();
		}
 		
 		resultMap.put("custName", companyName);
 		
		return "weixin";
	}
    /**
     * @Description: 管理中心页面--点解解除绑定按钮触发事件--组织单位或者填报单位
     * @author cuichx --- 2015-12-28上午10:39:49
     */
    public void removeBindAccount(){
    	Map<String, Object> returnMap=new HashMap<String, Object>();
    	
    	//String siteCode=request.getParameter("siteCode");
    	String openId=request.getParameter("openId");
    	logger.info("removeBindAccount openId:"+openId);
    	try {
    		if(StringUtils.isNotEmpty(openId)){
            	AccountBindInfoRequest accountBindInfoRequest=new AccountBindInfoRequest();
            	accountBindInfoRequest.setOpenId(openId);
            	accountBindInfoRequest.setStatus(AccountBindStatus.ACCOUNT_BIND.getCode());
            	//通过客户唯一标识查询帐户绑定信息表
            	List<AccountBindInfo> accountList=accountBindInfoServiceImpl.queryList(accountBindInfoRequest);
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
    	String cb1_statusValue=request.getParameter("cb1_statusValue");//接收设置页面--站群报告通知
    	String cb2_statusValue=request.getParameter("cb2_statusValue");//接收设置页面--站群预警通知
    	
    	String cb3_statusValue=request.getParameter("cb3_statusValue");//接收设置页面--本级门户报告通知
    	String cb4_statusValue=request.getParameter("cb4_statusValue");//接收设置页面--本级门户预警通知
    	
    	String cb5_statusValue=request.getParameter("cb5_statusValue");//接收设置页面--填报单位报告通知
    	String cb6_statusValue=request.getParameter("cb6_statusValue");//接收设置页面--填报单位预警通知
    	
    	logger.info("changeStatusValue cb2_statusValue:"+cb2_statusValue+"  cb4_statusValue:"+cb4_statusValue+"  cb6_statusValue:"+cb6_statusValue);
		try {
			//根据客户编码和绑定状态查询帐户绑定信息表
	    	AccountBindInfoRequest accountRequest=new AccountBindInfoRequest();
	    	if(StringUtils.isNotEmpty(openId)){
	    		accountRequest.setOpenId(openId);
		    	accountRequest.setStatus(AccountBindStatus.ACCOUNT_BIND.getCode());
		    	List<AccountBindInfo> accountList=accountBindInfoServiceImpl.queryList(accountRequest);
		    	if(accountList!=null && accountList.size()>0){
		    		AccountBindInfo accountBindInfo=accountList.get(0);
		        	if(StringUtils.isNotEmpty(cb1_statusValue)){
		        		accountBindInfo.setSiteListReportStatus(Integer.valueOf(cb1_statusValue));
		        	}
		        	if(StringUtils.isNotEmpty(cb2_statusValue)){
		        		accountBindInfo.setSiteListEarlyStatus(Integer.valueOf(cb2_statusValue));
		        	}
		        	if(StringUtils.isNotEmpty(cb3_statusValue)){
		        		accountBindInfo.setLocalhostReportStatus(Integer.valueOf(cb3_statusValue));
		        	}
		        	if(StringUtils.isNotEmpty(cb4_statusValue)){
		        		accountBindInfo.setLocalhostEarlyStatus(Integer.valueOf(cb4_statusValue));
		        	}
		        	if(StringUtils.isNotEmpty(cb5_statusValue)){
		        		accountBindInfo.setLocalhostReportStatus(Integer.valueOf(cb5_statusValue));
		        	}
		        	if(StringUtils.isNotEmpty(cb6_statusValue)){
		        		accountBindInfo.setLocalhostEarlyStatus(Integer.valueOf(cb6_statusValue));
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
     * @Description: 谁关注了我 模块数据初始化
     * @author cuichx --- 2016-1-12下午9:17:55
     */
    public void focusInit(){
    	List<Object> returnList=new ArrayList<Object>();
    	
    	//获取页面传递过来的参数
    	String siteCode=request.getParameter("siteCode");//客户编码
    	String openId=request.getParameter("openId");//微信客户唯一标识
    	AccountBindInfoRequest accRequest=new AccountBindInfoRequest();
    	accRequest.setSiteCode(siteCode);
    	accRequest.setStatus(AccountBindStatus.ACCOUNT_BIND.getCode());//绑定状态
    	accRequest.setPageSize(Integer.MAX_VALUE);
    	accRequest.setNotOpenId(openId);
    	//按创建时间倒序排列
		List<QueryOrder> querySiteList=new ArrayList<QueryOrder>();
		QueryOrder siteQueryOrder=new QueryOrder("create_time",QueryOrderType.DESC);
		querySiteList.add(siteQueryOrder);
		accRequest.setQueryOrderList(querySiteList);
		try {
	    	//通过客户编号和微信唯一标识码  获取微信绑定帐户信息表中所有的微信用户昵称并按创建时间倒序排列
			List<AccountBindInfo> accountList=accountBindInfoServiceImpl.queryList(accRequest);
			if(accountList!=null && accountList.size()>0){
				for (AccountBindInfo accountBindInfo : accountList) {
					if(StringUtils.isNotEmpty(accountBindInfo.getNickname())){
						Map<String, Object> map=new HashMap<String, Object>();
						map.put("nickname", accountBindInfo.getNickname());
						returnList.add(map);
					}
				}
			}
			writerPrint(JSONArray.fromObject(returnList).toString());
		} catch (Exception e) {
			e.printStackTrace();
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
    			/**
        		 * 填报单位的处理逻辑
        		 * 	查询客户信息表，判断该客户是否存在
        		 * 		如果存在，走正常的逻辑，解除按钮可用，可以设置接收报告通知和预警通知可用
        		 * 		如果不存在，但是服务周期表和服务周期中间表中存在数据，则只能查看，不能进行其他操作
        		 * 
        		 */
    			
        		//查询绑定帐户信息表,判断是否有记录
        		AccountBindInfoRequest accBindRequest=new AccountBindInfoRequest();
        		accBindRequest.setOpenId(openId);
        		accBindRequest.setStatus(AccountBindStatus.ACCOUNT_BIND.getCode());
        		List<AccountBindInfo> accList=accountBindInfoServiceImpl.queryList(accBindRequest);
        		if(accList!=null && accList.size()>0){
        			AccountBindInfo accountInfo=accList.get(0);
        			int isCustomer=accountInfo.getIsCustomer();//填报单位是否为客户
        			returnMap.put("isCustomer", isCustomer);//是否有合同
    				//根据网站标识码，查询站点信息表，获取信息
        			DatabaseInfoRequest datarRequest=new DatabaseInfoRequest();
        			datarRequest.setSiteCode(siteCode);
        			//datarRequest.setIsexp(DatabaseInfoType.NORMAL.getCode());
        			
        			List<DatabaseInfo> dataList=databaseInfoServiceImpl.queryList(datarRequest);
        			String url="";
        			String companyName="";//单位名称
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
    				}
        			//基本信息
        			returnMap.put("siteCode", siteCode);//标识码
        			returnMap.put("accountType",AccountType.ACCOUNT_TB.getName());//客户类型主办单位
        			returnMap.put("siteName", accountInfo.getSiteName());//网站名称
        			returnMap.put("url", url);//标识码
        			returnMap.put("companyName", companyName);//单位名称
        			returnMap.put("earlyStatusTB", accountInfo.getLocalhostEarlyStatus());//预警通知
        			//returnMap.put("reportStatusTB", accountInfo.getLocalhostReportStatus());//报告通知
        			
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
	 * @Description: 判断用户是否绑定帐户
	 * @author cuichx --- 2016-1-7下午5:33:50     
	 * @param openId 微信唯一标识
	 * @return
	 */
	public Boolean bindAccountOrNot(String openId){
		//根据微信用户唯一标识查询绑定帐户信息表
		AccountBindInfoRequest accRequest=new AccountBindInfoRequest();
		accRequest.setOpenId(openId);
		accRequest.setStatus(AccountBindStatus.ACCOUNT_BIND.getCode());
		List<AccountBindInfo>  accList=accountBindInfoServiceImpl.queryList(accRequest);
		
		if(accList!=null && accList.size()>0){//该客户已经绑定
			return true;
		}else{//该客户未绑定
			return false;
		}
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
			
			DatabaseLinkRequest param = new DatabaseLinkRequest();
			param.setOrgSiteCode(siteCode);
			
			List<DatabaseLinkRequest> queryCountBySiteCode = databaseLinkServiceImpl.queryCountBySiteCode(param);
			for (DatabaseLinkRequest databaseLinkRequest : queryCountBySiteCode) {
				
				
				Integer type = databaseLinkRequest.getType();
				int num = 0;
					
				if(type==DatabaseLinkType.ISORGANIZATIONAL.getCode()){//本级门户
					
					Integer databaseInfoId = databaseLinkRequest.getDatabaseInfoId();
					
					if(databaseInfoId!=null&&databaseInfoId!=0){
						
						DatabaseInfo databaseInfo = databaseInfoServiceImpl.get(databaseInfoId);
						if(databaseInfo!=null){
							
							shiroUser.setCurrentName(databaseInfo.getName());
							shiroUser.setCurrentSiteCode(databaseInfo.getSiteCode());
							num = 1;
						}
						
					}
					
				}else if(type==DatabaseLinkType.DEPARTMENT.getCode()){//本级部门
					num =databaseLinkRequest.getCount();
					shiroUser.setCurrentCount(num);
					
				}else if(type==DatabaseLinkType.UNIT.getCode()){//下属单位
					num =databaseLinkRequest.getCount();	
					shiroUser.setNextCount(num);
					
				}else if(type==DatabaseLinkType.OTHER.getCode()){
					num =databaseLinkRequest.getCount();
					shiroUser.setOtherCount(num);
				}
				count += num;
				
			}
			
			shiroUser.setCount(count);
			
			return shiroUser;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * @Description: 根据siteCode获取当前周期
	 * @author cuichx --- 2016-3-6上午2:11:21     
	 * @param comboIdArr 1常态化检测  2普通版 3 高级版
	 * @return
	 */

	public ServicePeriod getCurrentPeriodWX(String siteCode){
		ServicePeriod servicePeriod=null;
		try {
			/**
			 * 组织单位：直接查询合同信息表和服务周期表，查询有结果--存在周期，否则不存在周期对象
			 *
			 * 填报单位：
			 * 		通过网站标识码查询合同信息表，
			 * 			如果存在，说明该填报单位为自己买单，
			 * 				直接查询服务周期表，获取当前周期
			 * 			如果不存在，说明是省级组织单位买单，
			 * 				通过网站标识码+当前时间，查询服务周期中间表，获取服务周期id，再通过服务周期id,查询服务周期表，获取当前周期对象
			 * 
			 * 
			 */

			/**
			 * 根据网站标识码查询客户查询合同信息表
			 * 		如果合同信息表存在，通过合同信息表id查询服务周期表+状态为1服务中，获取当前服务周期
			 */
			ContractInfoRequest contractInfoRequest=new ContractInfoRequest();
			contractInfoRequest.setSiteCode(siteCode);
			List<ContractInfo>  contractList=contractInfoServiceImpl.queryList(contractInfoRequest);
			if(contractList!=null && contractList.size()>0){//买单的组织单位和买单填报单位
				ContractInfo contractInfo=contractList.get(0);
				int contractId=contractInfo.getId();
				
				ServicePeriodRequest servicePD=new ServicePeriodRequest();
				servicePD.setContractInfoId(contractId);
				int[] comboIdArr={3,4};
				servicePD.setComboIdArr(comboIdArr);//套餐类型为普通版和高级版
				servicePD.setStatus(1);//服务中
				
				List<ServicePeriod>  serviceList=servicePeriodServiceImpl.queryList(servicePD);
				if(serviceList!=null && serviceList.size()>0){
					servicePeriod=serviceList.get(0);
				}
			}else{
				//未买单的组织单位---返回空--不知道如何处理
				
				//未买单的填报单位，做如下处理
				if(siteCode.length()==10){
					RelationsPeriodRequest relaRequest=new RelationsPeriodRequest();
					relaRequest.setSiteCode(siteCode);
					relaRequest.setNowTime(DateUtils.formatStandardDate(new Date()));
					List<RelationsPeriod>  relaList=relationsPeriodServiceImpl.queryList(relaRequest);
					if(relaList!=null && relaList.size()>0){
						RelationsPeriod relaPeriod=relaList.get(0);
						int servicePeriodId=relaPeriod.getServicePeriodId();
						
						ServicePeriodRequest serviceRequest=new ServicePeriodRequest();
						serviceRequest.setId(servicePeriodId);
						int[] statasArray={1,2};//服务中（当前周期）和已完成服务（上一周期之前）
						serviceRequest.setStatasArray(statasArray);
						int[] comboIdArr={3,4};
						serviceRequest.setComboIdArr(comboIdArr);//套餐类型为普通版和高级版
						
						List<ServicePeriod>  servicePdList=servicePeriodServiceImpl.queryList(serviceRequest);
						if(servicePdList!=null && servicePdList.size()>0){
							servicePeriod=servicePdList.get(0);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return servicePeriod;
	}
	
	
	
    /**
     * @Description: 报告管理页面初始化--组织单位
     * @author cuichx --- 2015-12-18下午6:30:48     
     * @return
     */
    public  String reportManageLogIndex(){
    	
    	resultMap=new HashMap<String, Object>();
    	try{
        	//获取页面传送的参数
        	String openId=request.getParameter("openId");
    		//String openId="oTfZiwy1Mc14i3w97q67B1Nt9BTM";
        	//String openId="oTfZiw4AY4OB9B78s752TI1Yt0JA";
        	if(StringUtils.isNotEmpty(openId)){
        		resultMap.put("openId", openId);
        	}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return "weixin";
    }
    
    /**
     * @Description: 报告管理页面详情--组织单位
     * @author cuichx --- 2015-12-18下午6:30:48     
     * @return
     */
    public  void getReportManageLog(){
    	Map<String,Object> returnMap=new HashMap<String, Object>();
    	try{
        	//获取页面传送的参数
        	String openId=request.getParameter("openId");
        	logger.info("getReportManageLog openId:"+openId);
        	if(StringUtils.isNotEmpty(openId)){
        		Map<String, Object> paramMap=new HashMap<String, Object>();
        		paramMap.put("openId", openId);
        		paramMap.put("status", AccountBindStatus.ACCOUNT_BIND.getCode());//状态为绑定的
        		//通过用户唯一标识查询查询微信关联帐户表，获取该用户对应的网站标识码
        		AccountBindInfoRequest accBindRequest=accountBindInfoServiceImpl.queryByMap(paramMap);
        		String siteCode=accBindRequest.getSiteCode();
        		
        		
        		String level=CommonUtils.getLevel(siteCode);
            	//获取当前组织结构下面的所有网站标识码集合
            	List<DatabaseInfo> websiteList=new ArrayList<DatabaseInfo>();
            	
            	websiteList=queryDatebaseInfoListByType2(Integer.valueOf(level), "", siteCode);

            	Map<String, Object> map=new HashMap<String, Object>();
            	map.put("currentNextSiteCode", websiteList);
            	map.put("createTime", DateUtils.formatStandardDate(new Date()));//创建日期
            	map.put("nowTime", DateUtils.formatStandardDateTime(new Date()));//网站标识码去重使用
            	
            	//封装报告列表数据
            	List<Object> returnList=new ArrayList<Object>();
            	List<ReportManageLogRequest> rmlsList =reportManageLogServiceImpl.queryListByMap(map);
            	if(rmlsList!=null && rmlsList.size()>0){
            		returnMap.put("periodNum", rmlsList.get(0).getPeriodNum());//期数
            		returnMap.put("startTime",DateUtils.formatShortDate(rmlsList.get(0).getStartTime()));//上周期开始时间
            		for(int i=0;i<rmlsList.size();i++){
            			Map<String, Object> reportMap=new HashMap<String, Object>();
            			reportMap.put("siteName", rmlsList.get(i).getSiteName());//网站名称
            			
/*            			String sendState="";
            			if(rmlsList.get(i).getSendState()==0){
            				sendState=SendStateType.FAILURE.getName();
            			}else{
            				sendState=SendStateType.SUCCESS.getName();
            			}*/
            			reportMap.put("sendState",rmlsList.get(i).getSendState());//发送状态
            			
            			returnList.add(reportMap);
            		}
            		returnMap.put("returnList", returnList);//报告列表数据
            	}else{
            		returnMap.put("errormsg", "报告日志表中不存在要查询的数据！");//报告列表数据
            	}
            }else{
            	returnMap.put("errormsg", "微信用户唯一标识码不能为空！");//报告列表数据
            }
        	
        	logger.info("getReportManageLog returnMap:"+returnMap);
        	writerPrint(JSONObject.fromObject(returnMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    /**
     * @Description: 报告管理发送情况统计
     * @author cuichx --- 2015-12-18下午6:30:48     
     * @return
     */
    public  Map<String,Object> getReportManageLog(String siteCode,String periodNum){
    	
    	Map<String , Object> dateMap=new HashMap<String, Object>();
    	
    	if(StringUtils.isNotEmpty(siteCode)){
    		dateMap.put("siteCode", siteCode);
    		
        	//获取当前组织结构下面的所有网站标识码集合
        	List<DatabaseInfo> websiteList=new ArrayList<DatabaseInfo>();
        	String level=CommonUtils.getLevel(siteCode);
        	websiteList=queryDatebaseInfoListByType2(Integer.valueOf(level), "", siteCode);
        	
        	//根据网站标识码集合,查询预警表,获取该组织结构下面的预警信息统计数据
        	Map<String, Object> param=new HashMap<String, Object>();
        	param.put("websiteList", websiteList);
        	param.put("periodNum", periodNum);
        	int successNum=0;//每期报告发送成功次数
        	int errorNum=0;//每期报告发送失败次数
        	int siteCodeNum=0;//每期的发送报告的网站个数
        	ReportManageLogRequest rmlogRequest=reportManageLogServiceImpl.querySum(param);
        	if(rmlogRequest!=null){
        		successNum=rmlogRequest.getSuccessNum();
        		errorNum=rmlogRequest.getErrorNum();
        		siteCodeNum=rmlogRequest.getSiteCodeNum();
        		
        		dateMap.put("successNum", successNum);
        		dateMap.put("errorNum", errorNum);
        		dateMap.put("siteCodeNum", siteCodeNum);
        	}
    	}
    	return dateMap;
    }
    

    /**
     * @Description: 根据当前登录组织机构级别以及menuType，
     * 				  获取检测结果列表中的type(1本级门户，2本级部门，3下属单位，4例外，5关停，6其他)
     * @author cuichx --- 2016-5-31下午2:54:48     
     * @return
     */
    public Integer getDetectionOrgType(Integer level, String menuType){
    	
    	Integer type=0;
    	
    	if(StringUtils.isEmpty(menuType)){//全部
    		type=DatabaseLinkType.ALL.getCode();
    	}else if("5".equals(menuType)){//省本级门户  市本级门户  县本级门户
    		type=DatabaseLinkType.ISORGANIZATIONAL.getCode();
    	}else if((level==2 && "1".equals(menuType)) ||
    			 (level==3 && "3".equals(menuType)) ||
    			 (level==4 && "3".equals(menuType))){//省本级部门   市本级部门  县本级部门
    		
    		type=DatabaseLinkType.DEPARTMENT.getCode();
    	}else if((level==2 && "2".equals(menuType)) ||
    			(level==3 && "4".equals(menuType))){//省下属单位  市下属单位  县不存在下属单位
    		type=DatabaseLinkType.UNIT.getCode();
    	}
    	
    	return type;
    	
    }
 
}
