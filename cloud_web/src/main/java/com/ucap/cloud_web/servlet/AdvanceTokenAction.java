package com.ucap.cloud_web.servlet;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


import com.publics.util.page.QueryOrder;
import com.publics.util.page.QueryOrderType;
import com.publics.util.utils.DateUtils;
import com.publics.util.utils.StringUtils;
import com.ucap.cloud_web.UrlAdapterVar;
import com.ucap.cloud_web.action.BaseAction;
import com.ucap.cloud_web.constant.QuestionTypeWeiXin;
import com.ucap.cloud_web.constant.SecurityServiceType;



import com.ucap.cloud_web.dto.LinkAllDetailRequest;
import com.ucap.cloud_web.dto.LinkAllInfoRequest;

import com.ucap.cloud_web.dto.DetectionPeriodSiteRequest;
import com.ucap.cloud_web.dto.DicConfigRequest;
import com.ucap.cloud_web.dto.SecurityBlankDetailRequest;
import com.ucap.cloud_web.dto.SecurityFatalErrorRequest;
import com.ucap.cloud_web.dto.SecurityResponseRequest;
import com.ucap.cloud_web.dto.SecurityServcieRequest;
import com.ucap.cloud_web.entity.DetectionPeriodSite;
import com.ucap.cloud_web.entity.DicInteractProblem;
import com.ucap.cloud_web.entity.LinkAllInfo;
import com.ucap.cloud_web.entity.SecurityBlankDetail;
import com.ucap.cloud_web.entity.SecurityFatalError;
import com.ucap.cloud_web.entity.SecurityResponse;
import com.ucap.cloud_web.entity.SecurityServcie;
import com.ucap.cloud_web.entity.ServicePeriod;
import com.ucap.cloud_web.service.IDetectionPeriodSiteService;
import com.ucap.cloud_web.service.IDicConfigService;
import com.ucap.cloud_web.service.IDicInteractProblemService;
import com.ucap.cloud_web.service.ILinkAllDetailService;
import com.ucap.cloud_web.service.ILinkAllInfoService;
import com.ucap.cloud_web.service.ISecurityBlankDetailService;
import com.ucap.cloud_web.service.ISecurityFatalErrorService;
import com.ucap.cloud_web.service.ISecurityResponseService;
import com.ucap.cloud_web.service.ISecurityServcieService;
import com.ucap.cloud_web.service.IServicePeriodService;

/**
 * <p>Description:微信版开发--付费高级版 </p>
 * <p>@Package：com.ucap.cloud_web.servlet </p>
 * <p>Title: TokenAction </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：cuichx </p>
 * <p>@date：2015-12-23下午7:37:20 </p>
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class AdvanceTokenAction extends BaseAction{
	private static Log logger = LogFactory.getLog(AdvanceTokenAction.class);
	@Autowired
	private UrlAdapterVar urlAdapterVar;
	@Autowired
	private IServicePeriodService servicePeriodServiceImpl;
	@Autowired
	private ISecurityResponseService securityResponseServiceImpl;
	@Autowired
	private IDicInteractProblemService dicInteractProblemServiceImpl;
	@Autowired
	private ISecurityServcieService  securityServcieServiceImpl;
	@Autowired
	private ISecurityBlankDetailService securityBlankDetailServiceImpl;
	@Autowired
	private ISecurityFatalErrorService securityFatalErrorServiceImpl;
	@Autowired
	private ILinkAllInfoService linkAllInfoServiceImpl;
	@Autowired
	private ILinkAllDetailService linkAllDetailServiceImpl;
	@Autowired
	private IDicConfigService dicConfigServiceImpl;
	@Autowired
	private IDetectionPeriodSiteService detectionPeriodSiteServiceImpl;
	//初始化页面数据使用
	private Map<String, Object> initMap=null;
	
	Map<String, Object> resultMap=new HashMap<String, Object>();
	
	
	//String scanDate=DateUtils.getNextDay(new Date(), -2);//获取前天日期
	String scanDate=DateUtils.getYesterdayStr();//获取昨天日期
	//String scanDate="2017-05-14";//获取昨天日期
	
	/**
	 * @Description: 全站死链获取数据-填报单位
	 * @author cuichx --- 2017-3-31上午9:49:22
	 */
	public void getLinkAllTbData(){
		String siteCode=request.getParameter("siteCode");
		String servicePeroidId=request.getParameter("servicePeroidId");//服务周期id 
		
		logger.info("getLinkAllTbData=======servicePeroidId:"+servicePeroidId
				+"========siteCode:"+siteCode);
		
		//全站死链编码
		//获取确认死链和疑似死链的错误编码
		Map<String, Object>  params = new HashMap<String, Object>();
		String configId = "20,21";
		String[] codeArray = configId.split(",");
		params.put("configIds", codeArray);
		List<DicConfigRequest> dicConfig = dicConfigServiceImpl.queryListByMap(params);
		
		String confirmCode=dicConfig.get(0).getValue();
		String notConfimCode=dicConfig.get(1).getValue();
		
		String linkCodeStr=confirmCode+","+notConfimCode;
		String[] codeArr=linkCodeStr.split(",");
		
		Map<Object, Object> paramMap=new HashMap<Object, Object>();
		paramMap.put("siteCode", siteCode);
		paramMap.put("servicePeroidId", servicePeroidId);
		paramMap.put("errorCodeArr", codeArr);
		paramMap.put("webType", 1);//站内站外类型> 1:站内，2:站外
		
		try {
			List<LinkAllDetailRequest> linkList=linkAllDetailServiceImpl.queryByCode(paramMap);
			if(linkList!=null && linkList.size()>0){
				List<Map<String, Object>> confrimList=new ArrayList<Map<String,Object>>();
				List<Map<String, Object>> notConfirmList=new ArrayList<Map<String,Object>>();
				for (LinkAllDetailRequest linkRequest : linkList) {
					Map<String, Object> map=new HashMap<String, Object>();
					//死链个数
					map.put("errorNum", linkRequest.getErrorNum()!=null?linkRequest.getErrorNum():"");
					//死链编码
					Integer errorCode=linkRequest.getErrorCode();
					//问题描述
					String questionDescribe="";
					if(StringUtils.isNotEmpty(QuestionTypeWeiXin.getNameByCode(String.valueOf(errorCode))) ){
						questionDescribe=errorCode+" "+QuestionTypeWeiXin.getNameByCode(String.valueOf(errorCode));
					}
					map.put("questionDescribe", questionDescribe);
					
					//确认首页死链
					if(confirmCode.contains(String.valueOf(errorCode))){
						confrimList.add(map);
					}else{
						notConfirmList.add(map);
					}
				}
				resultMap.put("confrimList", confrimList);
				resultMap.put("notConfirmList", notConfirmList);
				
			}
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description: 全站链接不可用---填报单位
	 * @author cuichx --- 2017-4-7下午5:42:30     
	 * @return
	 */
	public String linkAllTbIndex(){
		
		initMap=new HashMap<String, Object>();
		String siteCode=request.getParameter("siteCode");
		String websiteSum=request.getParameter("websiteSum");
		String siteName=request.getParameter("siteName");
		String servicePeroidId=request.getParameter("servicePeroidId");//服务周期id 
		
		logger.info("linkAllTbIndex=======servicePeroidId:"+servicePeroidId
				+"========siteCode:"+siteCode);
		
		if(StringUtils.isEmpty(websiteSum)){//微信模板消息跳转过来的
			LinkAllInfoRequest allRequest=new LinkAllInfoRequest();
			allRequest.setSiteCode(siteCode);
			allRequest.setServicePeriodId(Integer.valueOf(servicePeroidId));
			allRequest.setPageSize(Integer.MAX_VALUE);
			
			 List<LinkAllInfo>  allList=linkAllInfoServiceImpl.queryList(allRequest);
			 if(allList!=null && allList.size()>0){
				 websiteSum=String.valueOf(allList.get(0).getWebsiteSum());
			 }else{
				 websiteSum="0";
			 }
		}
		
		initMap.put("siteCode", siteCode);
		initMap.put("websiteSum",websiteSum);
		initMap.put("siteName", siteName);
		initMap.put("servicePeroidId",servicePeroidId);
		initMap.put("scanDate", scanDate);
		
		return "newWeiXin";
	}
	/**
	 * @Description: 全站不可用链接数据统计-----组织单位
	 * @author cuichx --- 2017-4-7下午5:26:17
	 */
	public void getLinkAllOrgOrgData(){
		String orgSiteCode=request.getParameter("siteCode");
		String checkType=request.getParameter("checkType");
		String servicePeroidId=request.getParameter("servicePeroidId");//服务周期id
		String pageNum=request.getParameter("pageNum");//当前页数
		String pageSize=request.getParameter("pageSize");//每个大小
		
		logger.info("getLinkAllOrgOrgData=======servicePeroidId:"+servicePeroidId
				+"========orgSiteCode:"+orgSiteCode);
		
		HashMap<String, Object> paraMap=new HashMap<String, Object>();
		paraMap.put("orgSiteCode", orgSiteCode);
		if(StringUtils.isNotEmpty(checkType) && !"0".equals(checkType)){
			paraMap.put("siteType", checkType);
		}
		paraMap.put("isExp", 1);
		paraMap.put("isLink", 1);
		paraMap.put("servicePeroidId", servicePeroidId);
		try {
			int pageTotal=linkAllInfoServiceImpl.queryLinkAllInfoByTreeCount(paraMap);
			Integer begin=(Integer.valueOf(pageNum)-1)*Integer.valueOf(pageSize);
			Integer end=Integer.valueOf(pageSize);
			paraMap.put("begin", begin);
			paraMap.put("end", end);
			List<LinkAllInfoRequest> linkList=linkAllInfoServiceImpl.queryLinkAllInfoByTree(paraMap);
			if(linkList!=null && linkList.size()>0){
				List<Map<String, Object>> returnList=new ArrayList<Map<String,Object>>();
				for (LinkAllInfoRequest linkRequest : linkList) {
					Map<String, Object> map=new HashMap<String, Object>();
					map.put("siteCode", linkRequest.getSiteCode()!=null?linkRequest.getSiteCode():"");
					map.put("siteName", linkRequest.getSiteName()!=null?linkRequest.getSiteName():"");
					map.put("websiteSum", linkRequest.getWebsiteSum()!=null?linkRequest.getWebsiteSum():0);
					
					returnList.add(map);
				}
				resultMap.put("returnList", returnList);
				resultMap.put("pageTotal", pageTotal);
			}
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * @Description: 全站链接不可用---组织单位
	 * @author cuichx --- 2017-4-7下午5:18:32     
	 * @return
	 */
	public String linkAllOrgIndex(){
		
		initMap=new HashMap<String, Object>();
		String siteCode=request.getParameter("siteCode");//组织单位编码
		String checkType=request.getParameter("checkType");//下拉框类型
		String linkAll=request.getParameter("linkAll");//当前周期内全站不可应链接个数
		String linkAllSite=request.getParameter("linkAllSite");//当前周期内全站不可用链接分布站点
		String servicePeroidId=request.getParameter("servicePeroidId");//当前服务周期id
		
		logger.info("linkAllOrgIndex=======servicePeroidId:"+servicePeroidId
				+"========siteCode:"+siteCode);
		
		//根据服务周期id,查询服务周期表
		String beginDate="";
		ServicePeriod sPeriod=servicePeriodServiceImpl.get(Integer.valueOf(servicePeroidId));
		if(sPeriod!=null){
			beginDate=DateUtils.formatStandardDate(sPeriod.getStartDate());
		}
		
		initMap.put("siteCode", siteCode);
		initMap.put("checkType", checkType);
		initMap.put("linkAll", linkAll);
		initMap.put("linkAllSite", linkAllSite);
		initMap.put("servicePeroidId", servicePeroidId);
		initMap.put("beginDate", beginDate);
		
		return "newWeiXin";
	}
	/**
	 * @Description: 严重问题获取数据-填报单位
	 * @author cuichx --- 2017-3-31上午9:49:22
	 */
	public void getSecurityFatalErrorTbData(){
		String siteCode=request.getParameter("siteCode");
		String servicePeroidId=request.getParameter("servicePeroidId");//服务周期id 
		
		logger.info("getSecurityFatalErrorTbData=======servicePeroidId:"+servicePeroidId
				+"========siteCode:"+siteCode);
		
		SecurityFatalErrorRequest fatalRequest=new SecurityFatalErrorRequest();
		fatalRequest.setSiteCode(siteCode);
		fatalRequest.setServicePeriodId(Integer.valueOf(servicePeroidId));
		fatalRequest.setScanDate(scanDate);
		fatalRequest.setPageSize(Integer.MAX_VALUE);
		try {
			List<SecurityFatalError> fatalList=securityFatalErrorServiceImpl.queryList(fatalRequest);
			if(fatalList!=null && fatalList.size()>0){
				List<Map<String, Object>> returnList1=new ArrayList<Map<String,Object>>();//1 虚假或伪造内容
				List<Map<String, Object>> returnList2=new ArrayList<Map<String,Object>>();//2 反动、暴力或色情内容
				List<Map<String, Object>> returnList3=new ArrayList<Map<String,Object>>();//3 其它
				for (SecurityFatalError securityFatalError : fatalList) {
					Map<String, Object> map=new HashMap<String, Object>();
					//办事事项
					map.put("name", securityFatalError.getName()!=null?securityFatalError.getName():"");
					//发现时间
					map.put("scanDate", securityFatalError.getScanDate()!=null?securityFatalError.getScanDate():"");
					//问题描述
					String questionDescribe="";
					if(StringUtils.isNotEmpty(securityFatalError.getProblemDesc()) ){
						questionDescribe=securityFatalError.getProblemDesc();
					}
					map.put("questionDescribe", questionDescribe);
					//截图或者快照
					String imgUrl = securityFatalError.getImgUrl();
					if (StringUtils.isNotEmpty(imgUrl)) {
						if (imgUrl.startsWith("htm")) {
							map.put("imgUrl", urlAdapterVar.getImgUrl() + imgUrl);// 快照
						} else {
							map.put("imgUrl", imgUrl);// 问题截图
						}
					}else{
						map.put("imgUrl", "");
					}
					
					//栏目url
					String url="";
					if(StringUtils.isNotEmpty(securityFatalError.getUrl())){
						url=securityFatalError.getUrl();
					}
					map.put("url", url);
					
					//类型：1 虚假或伪造内容 。2 反动、暴力或色情内容。 3 其它
					Integer type=securityFatalError.getType();
					if(type==1){
						returnList1.add(map);
					}else if(type==2){
						returnList2.add(map);
					}else{
						returnList3.add(map);
					}
				}
				resultMap.put("litImgUrl", urlAdapterVar.getLinkUrl());
				resultMap.put("returnList1", returnList1);
				resultMap.put("returnList2", returnList2);
				resultMap.put("returnList3", returnList3);
			}
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description: 严重问题---填报单位
	 * @author cuichx --- 2017-3-30下午7:01:51     
	 * @return
	 */
	public String securityFatalErrorTbIndex(){
		
		initMap=new HashMap<String, Object>();
		String siteCode=request.getParameter("siteCode");
		String fatalErrorNum=request.getParameter("fatalErrorNum");
		String siteName=request.getParameter("siteName");
		String servicePeroidId=request.getParameter("servicePeroidId");//服务周期id 
		
		logger.info("securityFatalErrorTbIndex=======servicePeroidId:"+servicePeroidId
				+"========siteCode:"+siteCode);
		
		if(StringUtils.isEmpty(fatalErrorNum)){//微信模板消息跳转过来
			SecurityFatalErrorRequest fatalRequest=new SecurityFatalErrorRequest();
			fatalRequest.setSiteCode(siteCode);
			fatalRequest.setServicePeriodId(Integer.valueOf(servicePeroidId));
			fatalRequest.setScanDate(scanDate);
			fatalRequest.setPageSize(Integer.MAX_VALUE);
			
			Integer count=securityFatalErrorServiceImpl.queryCount(fatalRequest);
			if(count!=null){
				fatalErrorNum=String.valueOf(count);
			}else{
				fatalErrorNum="0";
			}
		}
		initMap.put("siteCode", siteCode);
		initMap.put("fatalErrorNum",fatalErrorNum);
		initMap.put("siteName", siteName);
		initMap.put("servicePeroidId",servicePeroidId);
		initMap.put("scanDate", scanDate);
		
		return "newWeiXin";
	}
	
	/**
	 * @Description: 严重问题统计数据----组织单位
	 * @author cuichx --- 2017-4-7上午9:32:58
	 */
	public void getSecurityFatalErrorOrgData(){
		String orgSiteCode=request.getParameter("siteCode");
		String checkType=request.getParameter("checkType");
		String servicePeroidId=request.getParameter("servicePeroidId");//服务周期id
		String pageNum=request.getParameter("pageNum");//当前页数
		String pageSize=request.getParameter("pageSize");//每个大小
		
		
		logger.info("getSecurityFatalErrorOrgData=======servicePeroidId:"+servicePeroidId
				+"========orgSiteCode:"+orgSiteCode);
		
		HashMap<String, Object> paraMap=new HashMap<String, Object>();
		paraMap.put("orgSiteCode", orgSiteCode);
		if(StringUtils.isNotEmpty(checkType) && !"0".equals(checkType)){
			paraMap.put("siteType", checkType);
		}
		paraMap.put("isExp", 1);
		paraMap.put("isLink", 1);
		paraMap.put("servicePeroidId", servicePeroidId);
		paraMap.put("scanDate", scanDate);
		paraMap.put("delFlag", 0);//是否删除（0：正常，1：删除；默认0）
		try {

			int pageTotal=securityFatalErrorServiceImpl.queryFatalErrorInfoByTreeCount(paraMap);
			Integer begin=(Integer.valueOf(pageNum)-1)*Integer.valueOf(pageSize);
			Integer end=Integer.valueOf(pageSize);
			paraMap.put("begin", begin);
			paraMap.put("end", end);
			
			List<SecurityFatalErrorRequest> fatalList=securityFatalErrorServiceImpl.queryFatalErrorInfoByTree(paraMap);
			if(fatalList!=null && fatalList.size()>0){
				List<Map<String, Object>> returnList=new ArrayList<Map<String,Object>>();
				for (SecurityFatalErrorRequest fatalRequest : fatalList) {
					Map<String, Object> map=new HashMap<String, Object>();
					map.put("siteCode", fatalRequest.getSiteCode()!=null?fatalRequest.getSiteCode():"");
					map.put("siteName", fatalRequest.getSiteName()!=null?fatalRequest.getSiteName():"");
					map.put("fatalErrorNum", fatalRequest.getFatalErrorNum()!=null?fatalRequest.getFatalErrorNum():0);
					
					returnList.add(map);
				}
				resultMap.put("returnList", returnList);
				resultMap.put("pageTotal", pageTotal);
			}
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * @Description: 严重问题---组织单位
	 * @author cuichx --- 2017-4-7上午9:32:05     
	 * @return
	 */
	public String securityFatalErrorOrgIndex(){
		
		initMap=new HashMap<String, Object>();
		String siteCode=request.getParameter("siteCode");//组织单位编码
		String checkType=request.getParameter("checkType");//下拉框类型
		String securityFatalError=request.getParameter("securityFatalError");//当前周期内严重问题个数
		String securityFatalSite=request.getParameter("securityFatalSite");//当前周期内严重问题分布站点
		String servicePeroidId=request.getParameter("servicePeroidId");//当前服务周期id
		
		
		logger.info("securityFatalErrorOrgIndex=======servicePeroidId:"+servicePeroidId
				+"========siteCode:"+siteCode);
		//根据服务周期id,查询服务周期表
		String beginDate="";
		ServicePeriod sPeriod=servicePeriodServiceImpl.get(Integer.valueOf(servicePeroidId));
		if(sPeriod!=null){
			beginDate=DateUtils.formatStandardDate(sPeriod.getStartDate());
		}
		
		initMap.put("siteCode", siteCode);
		initMap.put("checkType", checkType);
		initMap.put("securityFatalError", securityFatalError);
		initMap.put("securityFatalSite", securityFatalSite);
		initMap.put("servicePeroidId", servicePeroidId);
		initMap.put("beginDate", beginDate);
		
		return "newWeiXin";
	}
	
	/**
	 * @Description: 空白栏目获取数据-填报单位
	 * @author cuichx --- 2017-3-31上午9:49:22
	 */
	public void getSecurityBlankTbData(){
		String siteCode=request.getParameter("siteCode");
		String servicePeroidId=request.getParameter("servicePeroidId");//服务周期id 
		String pageNum=request.getParameter("pageNum");//当前页数
		String pageSize=request.getParameter("pageSize");//每个大小
		String formYJ=request.getParameter("formYJ");//1-微信预警详情点击过来的  0-概览页面点击过来的
		
		logger.info("getSecurityBlankTbData=======servicePeroidId:"+servicePeroidId
				+"========siteCode:"+siteCode);
		
		SecurityBlankDetailRequest blankRequest=new SecurityBlankDetailRequest();
		blankRequest.setSiteCode(siteCode);
		blankRequest.setServicePeriodId(Integer.valueOf(servicePeroidId));
		if("1".equals(formYJ)){//预警点击详情过来的  只查统计昨天的数据
			blankRequest.setScanDate(scanDate);
		}else{
			blankRequest.setEndScanDate(scanDate);
		}
		
		try {
			int pageTotal=securityBlankDetailServiceImpl.queryCount(blankRequest);
			blankRequest.setPageNo(Integer.valueOf(pageNum));
			blankRequest.setPageSize(Integer.valueOf(pageSize));
			
			List<QueryOrder> querySiteList=new ArrayList<QueryOrder>();
			QueryOrder siteQueryOrder=new QueryOrder("scan_date",QueryOrderType.DESC);
			querySiteList.add(siteQueryOrder);
			blankRequest.setQueryOrderList(querySiteList);
			
			List<SecurityBlankDetail>  blankList=securityBlankDetailServiceImpl.queryList(blankRequest);
			if(blankList!=null && blankList.size()>0){
				List<Map<String, Object>> returnList=new ArrayList<Map<String,Object>>();
				for (SecurityBlankDetail securityBlankDetail : blankList) {
					Map<String, Object> map=new HashMap<String, Object>();
					//办事事项
					map.put("channelName", securityBlankDetail.getChannelName()!=null?securityBlankDetail.getChannelName():"");
					//发现时间
					map.put("scanDate", securityBlankDetail.getScanDate()!=null?securityBlankDetail.getScanDate():"");
					//问题描述
					String questionDescribe="";
					if(StringUtils.isNotEmpty(securityBlankDetail.getProblemDesc()) ){
						questionDescribe=securityBlankDetail.getProblemDesc();
					}
					map.put("questionDescribe", questionDescribe);
					//截图或者快照
					String imgUrl = securityBlankDetail.getImgUrl();
					if (StringUtils.isNotEmpty(imgUrl)) {
						if (imgUrl.startsWith("htm")) {
							map.put("imgUrl", urlAdapterVar.getImgUrl() + imgUrl);// 快照
						} else {
							map.put("imgUrl", imgUrl);// 问题截图
						}
					}else{
						map.put("imgUrl", "");
					}
					
					//栏目url
					String url="";
					if(StringUtils.isNotEmpty(securityBlankDetail.getUrl())){
						url=securityBlankDetail.getUrl();
					}
					map.put("url", url);
					
					returnList.add(map);
				}
				resultMap.put("litImgUrl", urlAdapterVar.getLinkUrl());
				resultMap.put("returnList", returnList);
				resultMap.put("pageTotal", pageTotal);
			}
			
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description: 空白栏目---填报单位
	 * @author cuichx --- 2017-3-30下午7:01:51     
	 * @return
	 */
	public String securityBlankTbIndex(){
		initMap=new HashMap<String, Object>();
		String siteCode=request.getParameter("siteCode");
		String securityBlank=request.getParameter("securityBlank");
		String siteName=request.getParameter("siteName");
		String servicePeroidId=request.getParameter("servicePeroidId");//服务周期id 
		
		logger.info("securityBlankTbIndex=======servicePeroidId:"+servicePeroidId
				+"========securityBlank:"+securityBlank);
		
		if(StringUtils.isEmpty(securityBlank)){//微信模板消息跳转过来的
			//查询填报单位空白栏目统计
			SecurityBlankDetailRequest blankRequest=new SecurityBlankDetailRequest();
			blankRequest.setSiteCode(siteCode);
			blankRequest.setServicePeriodId(Integer.valueOf(servicePeroidId));
			blankRequest.setScanDate(scanDate);
			Integer count=securityBlankDetailServiceImpl.queryCount(blankRequest);
			if(count !=null){
				securityBlank=String.valueOf(count);
			}else{
				securityBlank="0";
			}
			initMap.put("formYJ", "1");
		}else{
			initMap.put("formYJ", "0");
		}
		
		initMap.put("siteCode", siteCode);
		initMap.put("securityBlank",securityBlank);
		initMap.put("siteName", siteName);
		initMap.put("servicePeroidId",servicePeroidId);
		initMap.put("scanDate", scanDate);
		
		return "newWeiXin";
	}
	
	/**
	 * @Description: 空白栏目统计数据----组织单位
	 * @author cuichx --- 2017-4-7上午9:32:58
	 */
	public void getSecurityBlankOrgData(){
		String orgSiteCode=request.getParameter("siteCode");
		String checkType=request.getParameter("checkType");
		String servicePeroidId=request.getParameter("servicePeroidId");//服务周期id
		String pageNum=request.getParameter("pageNum");//当前页数
		String pageSize=request.getParameter("pageSize");//每个大小
		
		logger.info("getSecurityBlankOrgData=======servicePeroidId:"+servicePeroidId
				+"========orgSiteCode:"+orgSiteCode);
		
		HashMap<String, Object> paraMap=new HashMap<String, Object>();
		paraMap.put("orgSiteCode", orgSiteCode);
		if(StringUtils.isNotEmpty(checkType) && !"0".equals(checkType)){
			paraMap.put("siteType", checkType);
		}
		paraMap.put("isExp", 1);
		paraMap.put("isLink", 1);
		paraMap.put("servicePeroidId", servicePeroidId);
		paraMap.put("scanDate", scanDate);
		paraMap.put("delFlag", 0);//是否删除（0：正常，1：删除；默认0）
		try {
			int pageTotal=securityBlankDetailServiceImpl.queryBlankInfoByTreeCount(paraMap);

			Integer begin=(Integer.valueOf(pageNum)-1)*Integer.valueOf(pageSize);
			Integer end=Integer.valueOf(pageSize);
			paraMap.put("begin", begin);
			paraMap.put("end", end);
			List<SecurityBlankDetailRequest> blankList=securityBlankDetailServiceImpl.queryBlankInfoByTree(paraMap);
			if(blankList!=null && blankList.size()>0){
				List<Map<String, Object>> returnList=new ArrayList<Map<String,Object>>();
				for (SecurityBlankDetailRequest blankRequest : blankList) {
					Map<String, Object> map=new HashMap<String, Object>();
					map.put("siteCode", blankRequest.getSiteCode()!=null?blankRequest.getSiteCode():"");
					map.put("siteName", blankRequest.getSiteName()!=null?blankRequest.getSiteName():"");
					map.put("blankNum", blankRequest.getBlankNum()!=null?blankRequest.getBlankNum():0);
					
					returnList.add(map);
				}
				resultMap.put("returnList", returnList);
				resultMap.put("pageTotal", pageTotal);
			}
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * @Description: 空白栏目---组织单位
	 * @author cuichx --- 2017-4-7上午9:32:05     
	 * @return
	 */
	public String securityBlankOrgIndex(){
		
		initMap=new HashMap<String, Object>();
		String siteCode=request.getParameter("siteCode");//组织单位编码
		String checkType=request.getParameter("checkType");//下拉框类型
		String securityBlank=request.getParameter("securityBlank");//当前周期内空白栏目个数
		String securityBlankSite=request.getParameter("securityBlankSite");//当前周期内空白栏目分布站点
		String servicePeroidId=request.getParameter("servicePeroidId");//当前服务周期id
		
		logger.info("securityBlankOrgIndex=======servicePeroidId:"+servicePeroidId
				+"========securityBlankSite:"+securityBlankSite);
		
		//根据服务周期id,查询服务周期表
		String beginDate="";
		ServicePeriod sPeriod=servicePeriodServiceImpl.get(Integer.valueOf(servicePeroidId));
		if(sPeriod!=null){
			beginDate=DateUtils.formatStandardDate(sPeriod.getStartDate());
		}
		
		initMap.put("siteCode", siteCode);
		initMap.put("checkType", checkType);
		initMap.put("securityBlank", securityBlank);
		initMap.put("securityBlankSite", securityBlankSite);
		initMap.put("servicePeroidId", servicePeroidId);
		initMap.put("beginDate", beginDate);
		
		return "newWeiXin";
	}
	/**
	 * @Description: 服务不实用获取数据-填报单位
	 * @author cuichx --- 2017-3-31上午9:49:22
	 */
	public void getSecurityServiceTbData(){
		String siteCode=request.getParameter("siteCode");
		String servicePeroidId=request.getParameter("servicePeroidId");//服务周期id 
		String pageNum=request.getParameter("pageNum");//当前页数
		String pageSize=request.getParameter("pageSize");//每个大小
		
		logger.info("getSecurityServiceTbData=======servicePeroidId:"+servicePeroidId
				+"========siteCode:"+siteCode);
		
		SecurityServcieRequest serviceRequest=new SecurityServcieRequest();
		serviceRequest.setSiteCode(siteCode);
		serviceRequest.setServicePeriodId(Integer.valueOf(servicePeroidId));
		serviceRequest.setEndScanDate(scanDate);
		try {
			
			int pageTotal=securityServcieServiceImpl.queryCount(serviceRequest);
			serviceRequest.setPageNo(Integer.valueOf(pageNum));
			
			serviceRequest.setPageSize(Integer.valueOf(pageSize));
			List<QueryOrder> querySiteList=new ArrayList<QueryOrder>();
			QueryOrder siteQueryOrder=new QueryOrder("scan_date",QueryOrderType.DESC);
			querySiteList.add(siteQueryOrder);
			serviceRequest.setQueryOrderList(querySiteList);
			
			List<SecurityServcie>  serviceList=securityServcieServiceImpl.queryList(serviceRequest);
			if(serviceList!=null && serviceList.size()>0){
				List<Map<String, Object>> returnList=new ArrayList<Map<String,Object>>();
				for (SecurityServcie securityServcie : serviceList) {
					Map<String, Object> map=new HashMap<String, Object>();
					//办事事项
					map.put("serviceItem", securityServcie.getServiceItem()!=null?securityServcie.getServiceItem():"");
					//发现时间
					map.put("scanDate", securityServcie.getScanDate()!=null?securityServcie.getScanDate():"");
					//问题描述
					String questionDescribe="";
					if(StringUtils.isNotEmpty(securityServcie.getProblemDesc()) ){
						questionDescribe=securityServcie.getProblemDesc();
					}
					map.put("questionDescribe", questionDescribe);
					// 问题类型名称
					int problemTypeId = securityServcie.getProblemTypeId();
					String problemTypeName = SecurityServiceType.getNameByCode(problemTypeId);
					if (StringUtils.isNotEmpty(problemTypeName)) {
						map.put("problemTypeName", problemTypeName);// 问题类型名称
					} else {
						map.put("problemTypeName", "");
					}
					//截图或者快照
					String imgUrl = securityServcie.getImgUrl();
					if (StringUtils.isNotEmpty(imgUrl)) {
						if (imgUrl.startsWith("htm")) {
							map.put("imgUrl", urlAdapterVar.getImgUrl() + imgUrl);// 快照
						} else {
							map.put("imgUrl", imgUrl);// 问题截图
						}
					}else{
						map.put("imgUrl", "");
					}
					
					returnList.add(map);
				}
				resultMap.put("litImgUrl", urlAdapterVar.getLinkUrl());
				resultMap.put("returnList", returnList);
				resultMap.put("pageTotal", pageTotal);
			}
			
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description: 服务不实用---填报单位
	 * @author cuichx --- 2017-3-30下午7:01:51     
	 * @return
	 */
	public String securityServiceTbIndex(){
		
		initMap=new HashMap<String, Object>();
		String siteCode=request.getParameter("siteCode");
		String securityService=request.getParameter("securityService");
		String siteName=request.getParameter("siteName");
		String servicePeroidId=request.getParameter("servicePeroidId");//服务周期id 
		
		logger.info("securityServiceTbIndex=======servicePeroidId:"+servicePeroidId
				+"========siteCode:"+siteCode);
		
		if(StringUtils.isEmpty(securityService)){//微信模板消息跳转过来
			SecurityServcieRequest serviceRequest=new SecurityServcieRequest();
			serviceRequest.setSiteCode(siteCode);
			serviceRequest.setServicePeriodId(Integer.valueOf(servicePeroidId));
			serviceRequest.setEndScanDate(scanDate);
			serviceRequest.setPageSize(Integer.MAX_VALUE);
			
			Integer count=securityServcieServiceImpl.queryCount(serviceRequest);
			if(count!=null){
				securityService=String.valueOf(count);
			}else{
				securityService="0";
			}
		}
		
		initMap.put("siteCode", siteCode);
		initMap.put("securityService",securityService);
		initMap.put("siteName", siteName);
		initMap.put("servicePeroidId",servicePeroidId);
		initMap.put("scanDate", scanDate);
		
		return "newWeiXin";
	}
	
	/**
	 * @Description: 服务不实用统计数据----组织单位
	 * @author cuichx --- 2017-4-7上午9:32:58
	 */
	public void getSecurityServiceOrgData(){
		String orgSiteCode=request.getParameter("siteCode");
		String checkType=request.getParameter("checkType");
		String servicePeroidId=request.getParameter("servicePeroidId");//服务周期id
		String pageNum=request.getParameter("pageNum");//当前页数
		String pageSize=request.getParameter("pageSize");//每个大小
		
		
		logger.info("getSecurityServiceOrgData=======servicePeroidId:"+servicePeroidId
				+"========orgSiteCode:"+orgSiteCode);
		
		HashMap<String, Object> paraMap=new HashMap<String, Object>();
		paraMap.put("orgSiteCode", orgSiteCode);
		if(StringUtils.isNotEmpty(checkType) && !"0".equals(checkType)){
			paraMap.put("siteType", checkType);
		}
		paraMap.put("isExp", 1);
		paraMap.put("isLink", 1);
		paraMap.put("servicePeroidId", servicePeroidId);
		paraMap.put("delFlag", 0);//是否删除（0：正常，1：删除；默认0）
		paraMap.put("scanDate", scanDate);
		
		try {
			int pageTotal=securityServcieServiceImpl.queryServiceInfoByTreeCount(paraMap);
			Integer begin=(Integer.valueOf(pageNum)-1)*Integer.valueOf(pageSize);
			Integer end=Integer.valueOf(pageSize);
			paraMap.put("begin", begin);
			paraMap.put("end", end);
			List<SecurityServcieRequest> serviceList=securityServcieServiceImpl.queryServiceInfoByTree(paraMap);
			if(serviceList!=null && serviceList.size()>0){
				List<Map<String, Object>> returnList=new ArrayList<Map<String,Object>>();
				for (SecurityServcieRequest serviceRequest : serviceList) {
					Map<String, Object> map=new HashMap<String, Object>();
					map.put("siteCode", serviceRequest.getSiteCode()!=null?serviceRequest.getSiteCode():"");
					map.put("siteName", serviceRequest.getSiteName()!=null?serviceRequest.getSiteName():"");
					map.put("problemNum", serviceRequest.getProblemNum()!=null?serviceRequest.getProblemNum():0);
					
					returnList.add(map);
				}
				resultMap.put("returnList", returnList);
				resultMap.put("pageTotal", pageTotal);
			}else{
				resultMap.put("errorMsg", "查询数据不存在");
			}
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * @Description: 服务不实用---组织单位
	 * @author cuichx --- 2017-4-7上午9:32:05     
	 * @return
	 */
	public String securityServiceOrgIndex(){
		
		initMap=new HashMap<String, Object>();
		String siteCode=request.getParameter("siteCode");//组织单位编码
		String checkType=request.getParameter("checkType");//下拉框类型
		String securityService=request.getParameter("securityService");//当前周期内服务不实用个数
		String securityServiceSite=request.getParameter("securityServiceSite");//当前周期内服务不实用分布站点
		String servicePeroidId=request.getParameter("servicePeroidId");//当前服务周期id
		
		logger.info("securityServiceOrgIndex=======servicePeroidId:"+servicePeroidId
				+"========securityService:"+securityService);
		
		//根据服务周期id,查询服务周期表
		String beginDate="";
		ServicePeriod sPeriod=servicePeriodServiceImpl.get(Integer.valueOf(servicePeroidId));
		if(sPeriod!=null){
			beginDate=DateUtils.formatStandardDate(sPeriod.getStartDate());
		}
		
		initMap.put("siteCode", siteCode);
		initMap.put("checkType", checkType);
		initMap.put("securityService", securityService);
		initMap.put("securityServiceSite", securityServiceSite);
		initMap.put("servicePeroidId", servicePeroidId);
		initMap.put("beginDate", beginDate);
		
		return "newWeiXin";
	}
	
	
	
	/**
	 * @Description: 互动哥回应差获取数据-填报单位
	 * @author cuichx --- 2017-3-31上午9:49:22
	 */
	public void getsecurityResponseTbData(){
		String siteCode=request.getParameter("siteCode");
		String servicePeroidId=request.getParameter("servicePeroidId");//服务周期id 
		String pageNum=request.getParameter("pageNum");//当前页数
		String pageSize=request.getParameter("pageSize");//每个大小
		
		logger.info("getsecurityResponseTbData=======servicePeroidId:"+servicePeroidId
				+"========siteCode:"+siteCode);
		
		SecurityResponseRequest responseRequest=new SecurityResponseRequest();
		responseRequest.setSiteCode(siteCode);
		responseRequest.setServicePeriodId(Integer.valueOf(servicePeroidId));
		responseRequest.setEndScanDate(scanDate);
		try {
			int pageTotal=securityResponseServiceImpl.queryCount(responseRequest);
			responseRequest.setPageNo(Integer.valueOf(pageNum));
			responseRequest.setPageSize(Integer.valueOf(pageSize));
			

			List<QueryOrder> querySiteList=new ArrayList<QueryOrder>();
			QueryOrder siteQueryOrder=new QueryOrder("scan_date",QueryOrderType.DESC);
			querySiteList.add(siteQueryOrder);
			responseRequest.setQueryOrderList(querySiteList);

			List<SecurityResponse>  responseList=securityResponseServiceImpl.queryList(responseRequest);
			if(responseList!=null && responseList.size()>0){
				List<Map<String, Object>> returnList=new ArrayList<Map<String,Object>>();
				for (SecurityResponse securityResponse : responseList) {
					Map<String, Object> map=new HashMap<String, Object>();
					map.put("channelName", securityResponse.getChannelName()!=null?securityResponse.getChannelName():"");
					map.put("scanDate", securityResponse.getScanDate()!=null?securityResponse.getScanDate():"");
					//问题描述
					String questionDescribe="";
					if(StringUtils.isNotEmpty(securityResponse.getProblemDesc()) ){
						questionDescribe=securityResponse.getProblemDesc();
					}
					map.put("questionDescribe", questionDescribe);
					//问题类型 problem_type_id
					
					String  problemType="";
					DicInteractProblem dicInteractProblem=dicInteractProblemServiceImpl.get(securityResponse.getProblemTypeId());
					if(dicInteractProblem!=null){
						problemType=dicInteractProblem.getRemark();
					}
					map.put("problemType", problemType);
					
					String imgUrl = securityResponse.getImgUrl();
					if (StringUtils.isNotEmpty(imgUrl)) {
						if (imgUrl.startsWith("htm")) {
							map.put("imgUrl", urlAdapterVar.getImgUrl() + imgUrl);// 快照
						} else {
							map.put("imgUrl", imgUrl);// 问题截图
						}
					}else{
						map.put("imgUrl", "");
					}
					
					returnList.add(map);
				}
				resultMap.put("litImgUrl", urlAdapterVar.getLinkUrl());
				resultMap.put("returnList", returnList);
				resultMap.put("pageTotal", pageTotal);
			}
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description: 互动回应差---填报单位
	 * @author cuichx --- 2017-3-30下午7:01:51     
	 * @return
	 */
	public String securityResponseTbIndex(){
		
		initMap=new HashMap<String, Object>();
		String siteCode=request.getParameter("siteCode");
		String securityResponse=request.getParameter("securityResponse");
		String siteName=request.getParameter("siteName");
		String servicePeroidId=request.getParameter("servicePeroidId");//服务周期id 
		
		logger.info("securityResponseTbIndex=======servicePeroidId:"+servicePeroidId
				+"========securityResponse:"+securityResponse);
		
		if(StringUtils.isEmpty(securityResponse)){
			List<DetectionPeriodSite>  dtList=dtPeriodSiteList(siteCode, servicePeroidId);
			if(dtList!=null && dtList.size()>0){
				securityResponse=String.valueOf(dtList.get(0).getSecurityResponse());
			}else{
				securityResponse="0";
			}
		}
		initMap.put("siteCode", siteCode);
		initMap.put("securityResponse",securityResponse);
		initMap.put("siteName", siteName);
		initMap.put("servicePeroidId",servicePeroidId);
		initMap.put("scanDate", scanDate);
		
		return "newWeiXin";
	}

	/**
	 * @Description: 互动回应差获取数据--组织单位
	 * @author cuichx --- 2017-3-29下午5:11:35
	 */
	public void getsecurityResponseOrgData(){
		String orgSiteCode=request.getParameter("siteCode");
		String checkType=request.getParameter("checkType");
		String servicePeroidId=request.getParameter("servicePeroidId");//服务周期id
		String pageNum=request.getParameter("pageNum");//当前页数
		String pageSize=request.getParameter("pageSize");//每个大小
		
		logger.info("getsecurityResponseOrgData=======servicePeroidId:"+servicePeroidId
				+"========orgSiteCode:"+orgSiteCode);
		
		HashMap<String, Object> paraMap=new HashMap<String, Object>();
		paraMap.put("orgSiteCode", orgSiteCode);
		if(StringUtils.isNotEmpty(checkType) && !"0".equals(checkType)){
			paraMap.put("siteType", checkType);
		}
		paraMap.put("isExp", 1);
		paraMap.put("isLink", 1);
		paraMap.put("servicePeroidId", servicePeroidId);
		paraMap.put("endScanDate", scanDate);
		
		try {
			int pageTotal=securityResponseServiceImpl.queryInfoByTreeCount(paraMap);
			Integer begin=(Integer.valueOf(pageNum)-1)*Integer.valueOf(pageSize);
			Integer end=Integer.valueOf(pageSize);
			paraMap.put("begin", begin);
			paraMap.put("end", end);
			List<SecurityResponseRequest> responseList=securityResponseServiceImpl.queryInfoByTree(paraMap);
			if(responseList!=null && responseList.size()>0){
				List<Map<String, Object>> returnList=new ArrayList<Map<String,Object>>();
				for (SecurityResponseRequest responseRequest : responseList) {
					Map<String, Object> map=new HashMap<String, Object>();
					map.put("siteCode", responseRequest.getSiteCode()!=null?responseRequest.getSiteCode():"");
					map.put("siteName", responseRequest.getSiteName()!=null?responseRequest.getSiteName():"");
					map.put("problemNum", responseRequest.getProblemNum()!=null?responseRequest.getProblemNum():0);
					
					returnList.add(map);
				}
				resultMap.put("returnList", returnList);
				resultMap.put("pageTotal", pageTotal);
			}
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Description:互动回应差---组织单位 
	 * @author cuichx --- 2017-4-6上午11:09:09     
	 * @return
	 */
	public String securityResponseOrgIndex(){
		
		initMap=new HashMap<String, Object>();
		String siteCode=request.getParameter("siteCode");//组织单位编码
		String checkType=request.getParameter("checkType");//下拉框类型
		String securityResponse=request.getParameter("securityResponse");//当前周期内互动回应差站点个数
		String securityResponseSite=request.getParameter("securityResponseSite");//当前周期内互动回应差分布站点
		String servicePeroidId=request.getParameter("servicePeroidId");//当前服务周期id
		
		logger.info("securityResponseOrgIndex=======servicePeroidId:"+servicePeroidId
				+"========securityResponse:"+securityResponse);
		
		//根据服务周期id,查询服务周期表
		String beginDate="";
		ServicePeriod sPeriod=servicePeriodServiceImpl.get(Integer.valueOf(servicePeroidId));
		if(sPeriod!=null){
			beginDate=DateUtils.formatStandardDate(sPeriod.getStartDate());
		}
		
		initMap.put("siteCode", siteCode);
		initMap.put("checkType", checkType);
		initMap.put("securityResponse", securityResponse);
		initMap.put("securityResponseSite", securityResponseSite);
		initMap.put("servicePeroidId", servicePeroidId);
		initMap.put("beginDate", beginDate);
		
		return "newWeiXin";
	}
	
	/**
	 * 查询填报单位统计数据（高级版）
	 * @param siteCode
	 * @param servicePeroidId
	 * @return
	 */
	public List<DetectionPeriodSite> dtPeriodSiteList(String siteCode,String servicePeroidId){
			//查询填报单位空白栏目统计
			DetectionPeriodSiteRequest deRequest=new DetectionPeriodSiteRequest();
			deRequest.setSiteCode(siteCode);
			deRequest.setServicePeroidId(Integer.valueOf(servicePeroidId));
			List<DetectionPeriodSite> deList=detectionPeriodSiteServiceImpl.queryList(deRequest);
			if(deList!=null && deList.size()>0){
				return deList;
			}else{
				return null;
			}
	}
	
	public Map<String, Object> getInitMap() {
		return initMap;
	}
	public void setInitMap(Map<String, Object> initMap) {
		this.initMap = initMap;
	}
		
	
}
