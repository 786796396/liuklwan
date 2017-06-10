package com.ucap.cloud_web.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;

import com.publics.util.page.QueryOrder;
import com.publics.util.page.QueryOrderType;
import com.publics.util.utils.DateUtils;
import com.publics.util.utils.StringUtils;
import com.ucap.cloud_web.dto.ConnectionAllRequest;
import com.ucap.cloud_web.dto.DetectionResultRequest;
import com.ucap.cloud_web.dto.LinkAllInfoRequest;
import com.ucap.cloud_web.dto.LinkHomeTrendRequest;
import com.ucap.cloud_web.dto.SecurityHomeChannelRequest;
import com.ucap.cloud_web.dto.UpdateContentCountRequest;
import com.ucap.cloud_web.entity.DetectionResult;
import com.ucap.cloud_web.entity.LinkAllInfo;
import com.ucap.cloud_web.entity.LinkHomeTrend;
import com.ucap.cloud_web.entity.SecurityHomeChannel;
import com.ucap.cloud_web.entity.ServicePeriod;
import com.ucap.cloud_web.service.IConnectionAllService;
import com.ucap.cloud_web.service.IDetectionResultService;
import com.ucap.cloud_web.service.ILinkAllInfoService;
import com.ucap.cloud_web.service.ILinkHomeTrendService;
import com.ucap.cloud_web.service.ISecurityHomeChannelService;
import com.ucap.cloud_web.service.IUpdateContentCountService;

import net.sf.json.JSONObject;

/**
 * <p>Description:组织单位统计数据点击跳转页面处理 </p>
 * <p>@Package：com.ucap.cloud_web.action </p>
 * <p>Title: JumpPageAction </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：cuichx </p>
 * <p>@date：2016-3-22下午5:02:11 </p>
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class JumpPageAction extends BaseAction {
	/**
	 * 网站标识码
	 */
	private String siteCode;
	/**
	 * 跳转指定页面
	 */
	private String menuNum;
	
	private String menuValue;
	
	private String channelIds;
	
	private String isHomePage;
	
	@Autowired
	private IConnectionAllService  connectionAllServiceImpl;
	@Autowired
	private ILinkHomeTrendService linkHomeTrendServiceImpl;
	
	@Autowired
	private ILinkAllInfoService linkAllInfoServiceImpl;
	
	@Autowired
	private ISecurityHomeChannelService securityHomeChannelServiceImpl;
	@Autowired
	private IUpdateContentCountService updateContentCountServiceImpl;
	
	
	@Autowired
	private IDetectionResultService detectionResultServiceImpl;
	
	/**
	 * @Description: 跳转页面
	 * @author cuichx --- 2016-3-22下午5:03:17     
	 * @return
	 */
	public String index(){
		//siteCode处理由组织单位跳转到该页面时，session的修改
		siteCode = request.getParameter("siteCode");
		/**
		 * 主要是用于判断组织单位点击的哪一类统计数据，跳转到填报单位的 
		 * 		menuNum   2--网站连通性   5链接可用性   7内容保障问题   15 更新统计
		 */
		menuNum=request.getParameter("menuNum");
		
		channelIds=request.getParameter("channelIds");
		
		//用于区分来源    1、组织单位首页列表跳转到填报单位    2、13个列表跳转到填报单位
		isHomePage=request.getParameter("isHomePage");
		
		if(StringUtils.isNotEmpty(siteCode)){
			setCurrentShiroUser(siteCode);
		}
		if(Integer.valueOf(menuNum)==2){
			menuValue="#menuWzltx";
		}else if(Integer.valueOf(menuNum)==5){
			menuValue="#menuLjkyx";
		}else if(Integer.valueOf(menuNum)==7){
			menuValue="#menuBzwt";
		}else if(Integer.valueOf(menuNum)==15){
			menuValue="#menuNrgx";
		}
		
		return "success";
	}
	
	
	/**
	 * @Description:更新统计跳转页面 
	 * @author cuichx --- 2016-3-20下午3:58:43
	 */
	public void updateTotalJumpPage(){
		Map<String, Object> resultMap=new HashMap<String, Object>();
		
		try {
			String siteCode=request.getParameter("siteCode");
			String scanDate=DateUtils.getYesterdayStr();//昨天日期
			
			/**
			 * 首页更新和栏目更新统计昨天的数据
			 */
			int updateHomeNum=0;//首页更新个数
			int updateChannelNum=0;//栏目跟新个数
			Map<String, Object> paramMap=new HashMap<String, Object>();
			if(StringUtils.isNotEmpty(siteCode)){
				paramMap.put("siteCode", siteCode);
			}
			paramMap.put("scanDate", scanDate);	
			List<UpdateContentCountRequest>  contentList=updateContentCountServiceImpl.queryCountLine(paramMap);
			if(contentList!=null && contentList.size()>0){
				updateHomeNum=contentList.get(0).getHomeNum();
				updateChannelNum=contentList.get(0).getChannelNum();
			}
			
			
			resultMap.put("updateHomeNum", updateHomeNum);
			resultMap.put("updateChannelNum", updateChannelNum);
			
			writerPrint(JSONObject.fromObject(resultMap).toString());
			
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("errorMsg", "更新统计数据获取异常");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		}
	}
	
	
	
	/**
	 * @Description: 内容保障问题统计跳转页面
	 * @author cuichx --- 2016-3-20下午2:51:18
	 */
	public void securityJumpPage(){
		Map<String, Object> resultMap=new HashMap<String, Object>();
		try {
			String siteCode=request.getParameter("siteCode");
			String scanDate=DateUtils.getYesterdayStr();//获取昨天日期
			

			
			int homeUnUpdateNum=0;//首页不更新个数/首页未更新天数
			int channelUnUpdateNum=0;//栏目不更新个数
			int blankNum=0;//空白栏目
			int responseNum=0;//互动回应差
			int serviceNum=0;//服务不实用
			int basicInfoNum=0;//基本信息
			
			//首页不更新天数    13个列表中的内容保障问题统计的是首页未更新天数超过两周的     栏目信息不更新是有条件查询的
			SecurityHomeChannelRequest securityHomeChannelRequest = new SecurityHomeChannelRequest();
			securityHomeChannelRequest.setType(1);
			securityHomeChannelRequest.setSiteCode(siteCode);
			securityHomeChannelRequest.setScanDate(scanDate);
			ArrayList<Object> queryOrderList = new ArrayList<Object>();
			queryOrderList.add(new QueryOrder("create_time", QueryOrderType.DESC));
			
			List<SecurityHomeChannel> homeList = securityHomeChannelServiceImpl.queryList(securityHomeChannelRequest);
			if(!CollectionUtils.isEmpty(homeList)){
				SecurityHomeChannel securityHomeChannel = homeList.get(0);
				homeUnUpdateNum = securityHomeChannel.getNotUpdateNum();
			}
			
			DetectionResultRequest resultRequest=new DetectionResultRequest();
			resultRequest.setSiteCode(siteCode);
			resultRequest.setScanDate(scanDate);
			List<DetectionResult> resultList=detectionResultServiceImpl.queryList(resultRequest);
			if(resultList!=null && resultList.size()>0){
				DetectionResult result=resultList.get(0);
				
				blankNum=result.getSecurityBlank();
				responseNum=result.getSecurityResponse();
				serviceNum=result.getSecurityService();
				
				
			}
			//基本信息和栏目不更新合并
			SecurityHomeChannelRequest homerequest=new SecurityHomeChannelRequest();
			homerequest.setFlag(true);//全部
			homerequest.setSiteCode(siteCode);
			homerequest.setScanDate(scanDate);
			homerequest.setType(2);
			channelUnUpdateNum=securityHomeChannelServiceImpl.queryCount(homerequest);
/*			*//**
			 * 主要用去区分是组织单位首页面列表的内容保障统计数据，还是
			 *//*
			//String isHomePage=request.getParameter("isHomePage");
			
			*//**
			 * 首页不更新和栏目不更新查询昨天的数据
			 *//*
			int homeUnUpdateNum=0;//首页不更新个数/首页未更新天数
			int channelUnUpdateNum=0;//栏目不更新个数
			
			Map<String, Object>  paramMap=new HashMap<String, Object>();
			if(StringUtils.isNotEmpty(siteCode)){
				paramMap.put("siteCode", siteCode);
			}
			
			paramMap.put("scanDate", scanDate);
			//13个列表中的内容保障问题统计的是首页未更新天数超过两周的     栏目信息不更新是有条件查询的
			SecurityHomeChannelRequest securityHomeChannelRequest = new SecurityHomeChannelRequest();
			securityHomeChannelRequest.setType(1);
			securityHomeChannelRequest.setSiteCode(siteCode);
			securityHomeChannelRequest.setScanDate(scanDate);
			ArrayList<Object> queryOrderList = new ArrayList<Object>();
			queryOrderList.add(new QueryOrder("create_time", QueryOrderType.DESC));
			
			 List<SecurityHomeChannel> homeList = securityHomeChannelServiceImpl.queryList(securityHomeChannelRequest);
				if(!CollectionUtils.isEmpty(homeList)){
					SecurityHomeChannel securityHomeChannel = homeList.get(0);
					homeUnUpdateNum = securityHomeChannel.getNotUpdateNum();
//						homeUnUpdateNum=homeRequest.getNotUpdateDays();
				}
				
				//栏目不更新统计数据获取（昨天的）
				paramMap.put("type", 2);
				
				if(StringUtils.isNotEmpty(channelIds)){//如果该参数为空，默认查询全部
			    	//不全选
					paramMap.put("flag", "false");
					paramMap.put("selectType", channelIds);
			    }else{
			    	//全选
			    	paramMap.put("flag", "true");
			    }
				List<SecurityHomeChannelRequest>  channelList=securityHomeChannelServiceImpl.getUNUpdateSum(paramMap);
				if(channelList!=null && channelList.size()>0){
					SecurityHomeChannelRequest homeChannelRequest=channelList.get(0);
					channelUnUpdateNum=homeChannelRequest.getUnUpdateSum();
				}
			*//**
			 * 空白栏目、互动回应差、服务不实用查询当前周期的统计数据
			 *//*
			int blankNum=0;//空白栏目
			int responseNum=0;//互动回应差
			int serviceNum=0;//服务不实用
			int basicInfoNum=0;//基本信息
			
			//获取当前周期对象
			ServicePeriod  curServicePd=getCurrentPeriod();
			if(curServicePd!=null){
				//空白栏目统计个数
				SecurityBlankInfoRequest securityRequest=new SecurityBlankInfoRequest();
				securityRequest.setSiteCode(siteCode);
				securityRequest.setServicePeriodId(curServicePd.getId());
				
				List<SecurityBlankInfo> blankList=securityBlankInfoServiceImpl.queryList(securityRequest);
				if(blankList!=null && blankList.size()>0){
					SecurityBlankInfo secuBlankInfo=blankList.get(0);
					blankNum=secuBlankInfo.getBlankNum();
				}
				
				//互动回应差
				
				SecurityResponseRequest responseRequest=new SecurityResponseRequest();
				responseRequest.setSiteCode(siteCode);
				responseRequest.setServicePeriodId(curServicePd.getId());
				
				responseNum =securityResponseServiceImpl.queryCount(responseRequest);
				
				//服务不实用
				SecurityServcieRequest serviceRequest=new SecurityServcieRequest();
				serviceRequest.setSiteCode(siteCode);
				serviceRequest.setServicePeriodId(curServicePd.getId());
				
				serviceNum=securityServcieServiceImpl.queryCount(serviceRequest);
				
				//基本信息
				//服务不实用
				SecurityBasicInfoRequest basicInfoRequest=new SecurityBasicInfoRequest();
				basicInfoRequest.setSiteCode(siteCode);
				basicInfoRequest.setServicePeriodId(curServicePd.getId());
				
				basicInfoNum=securityBasicInfoServiceImpl.queryCount(basicInfoRequest);
				
			}*/
			
			
			resultMap.put("homeUnUpdateNum", homeUnUpdateNum);
			resultMap.put("channelUnUpdateNum", channelUnUpdateNum);
			resultMap.put("blankNum", blankNum);
			resultMap.put("responseNum", responseNum);
			resultMap.put("serviceNum", serviceNum);
			resultMap.put("basicInfoNum", basicInfoNum);
			
			writerPrint(JSONObject.fromObject(resultMap).toString());
			
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("errorMsg", "内容保障问题统计数据获取异常");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		}
	}

	/**
	 * @Description: 链接可用性统计跳转页面
	 * @author cuichx --- 2016-3-22下午1:02:21
	 */
	public void linkJumpPage(){
		Map<String, Object> resultMap=new HashMap<String, Object>();
		
		try {
			String siteCode=request.getParameter("siteCode");
			String scanDate =DateUtils.getYesterdayStr();//昨天日期
			//首页链接可用性展示昨天的数据
			LinkHomeTrendRequest linkrRequest=new LinkHomeTrendRequest();
			if(StringUtils.isNotEmpty(siteCode)){
				linkrRequest.setSiteCode(siteCode);
			}
			linkrRequest.setScanDate(scanDate);
			
			/**
			 * 首页链接可用性统计数据
			 */
			int linkHomeNum=0;
			List<LinkHomeTrend> linkHomeList=linkHomeTrendServiceImpl.queryList(linkrRequest);
			if(linkHomeList!=null && linkHomeList.size()>0){
				LinkHomeTrend linkHomeTrend=linkHomeList.get(0);
				linkHomeNum=linkHomeTrend.getWebsiteSum();
			}
			
			/**
			 * 全站链接可用性展示本周期数据
			 */
			int linkAllNum=0;
			//获取当前周期
			/**老合同信息**/
			ServicePeriod servicePd = getCurrentPeriod();
			/**新产品信息**/
			// Integer[] productTypeArr = { CrmProductsType.DETECTION.getCode()
			// };
			// ServicePeriod servicePd =
			// getCurrentServicePeriod(productTypeArr);
			if(servicePd!=null){
				LinkAllInfoRequest linkallRequest=new LinkAllInfoRequest();
				linkallRequest.setSiteCode(siteCode);
				linkallRequest.setServicePeriodId(servicePd.getId());
				
				List<LinkAllInfo> linkAllList=linkAllInfoServiceImpl.queryList(linkallRequest);
				if(linkAllList!=null && linkAllList.size()>0){
					linkAllNum=linkAllList.get(0).getWebsiteSum();
				}
				
			}
			
			resultMap.put("linkHomeNum", linkHomeNum);
			resultMap.put("linkAllNum", linkAllNum);
			
			writerPrint(JSONObject.fromObject(resultMap).toString());
			
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("errorMsg", "链接可用性统计数据获取异常");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		}
	}
	
	
	
	
	/**
	 * @Description: 网站连通性跳转页面
	 * @author cuichx --- 2016-3-22下午7:10:29
	 */
	public void connJumpPage(){
		//封装返回数据
		Map<String, Object> resultMap=new HashMap<String, Object>();
		try {
			String siteCode=request.getParameter("siteCode");
			
			String scanDate=DateUtils.getYesterdayStr();
			
			
			Map<String, Object> paramMap=new HashMap<String, Object>();
			if(StringUtils.isNotEmpty(siteCode)){
				paramMap.put("siteCode", siteCode);
			}
			paramMap.put("scanDate", scanDate);
			
			//首页不连通次数
			int homeConnNum=0;
			//业务系统连通性数据统计
			int busConnNum=0;
			//关键栏目连通性统计（需要关联重点监测栏目表）
			int channelConnNum=0;
			
			List<ConnectionAllRequest>  connAllList=connectionAllServiceImpl.getHomeBar(paramMap);
			if(connAllList!=null && connAllList.size()>0){
				ConnectionAllRequest connAllRequest=connAllList.get(0);
				if(connAllRequest!=null){
					homeConnNum=connAllRequest.getHomeNum();
					busConnNum=connAllRequest.getBuseNum();
					channelConnNum=connAllRequest.getChannelNum();
				
				}
			}
			resultMap.put("homeConnNum", homeConnNum);
			resultMap.put("busConnNum", busConnNum);
			resultMap.put("channelConnNum", channelConnNum);
			
			writerPrint(JSONObject.fromObject(resultMap).toString());
			
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("errorMsg", "获取连通性统计数据异常");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		}
	}

	public String getSiteCode() {
		return siteCode;
	}
	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}
	public String getMenuNum() {
		return menuNum;
	}
	public void setMenuNum(String menuNum) {
		this.menuNum = menuNum;
	}
	public String getMenuValue() {
		return menuValue;
	}
	public void setMenuValue(String menuValue) {
		this.menuValue = menuValue;
	}
	public String getChannelIds() {
		return channelIds;
	}
	public void setChannelIds(String channelIds) {
		this.channelIds = channelIds;
	}
	public String getIsHomePage() {
		return isHomePage;
	}
	public void setIsHomePage(String isHomePage) {
		this.isHomePage = isHomePage;
	}
}
