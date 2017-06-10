package com.ucap.cloud_web.servlet;


import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


import com.ctc.wstx.util.DataUtil;
import com.publics.util.page.QueryOrder;
import com.publics.util.page.QueryOrderType;
import com.publics.util.utils.DateUtils;
import com.publics.util.utils.StringUtils;
import com.ucap.cloud_web.UrlAdapterVar;
import com.ucap.cloud_web.action.BaseAction;
import com.ucap.cloud_web.bizService.DatabaseBizService;
import com.ucap.cloud_web.constant.ConnectionState;
import com.ucap.cloud_web.constant.ConnectionType;
import com.ucap.cloud_web.constant.QuestionType;
import com.ucap.cloud_web.constant.QuestionTypeWeiXin;
import com.ucap.cloud_web.constant.QueueType;


import com.ucap.cloud_web.dto.ConnectionHomeDetailRequest;
import com.ucap.cloud_web.dto.DatabaseInfoRequest;
import com.ucap.cloud_web.dto.DatabaseTreeInfoRequest;
import com.ucap.cloud_web.dto.DicConfigRequest;
import com.ucap.cloud_web.dto.EarlyDetailTempRequest;
import com.ucap.cloud_web.dto.LinkHomeAvailableRequest;
import com.ucap.cloud_web.dto.LinkHomeTrendRequest;
import com.ucap.cloud_web.dto.MTaskdetailRequest;
import com.ucap.cloud_web.dto.SecurityHomeChannelRequest;
import com.ucap.cloud_web.dto.UpdateContentCountRequest;
import com.ucap.cloud_web.dto.UpdateHomeDetailRequest;
import com.ucap.cloud_web.dto.xstream.Root;
import com.ucap.cloud_web.dto.xstream.XstreamXmlParseBeanUtil;

import com.ucap.cloud_web.entity.ConnectionHomeDetail;
import com.ucap.cloud_web.entity.DatabaseInfo;
import com.ucap.cloud_web.entity.DatabaseTreeInfo;
import com.ucap.cloud_web.entity.Detail;
import com.ucap.cloud_web.entity.EarlyDetailTemp;
import com.ucap.cloud_web.entity.LinkHomeAvailable;
import com.ucap.cloud_web.entity.MTaskdetail;
import com.ucap.cloud_web.entity.Result;
import com.ucap.cloud_web.entity.SecurityHomeChannel;
import com.ucap.cloud_web.entity.UpdateHomeDetail;
import com.ucap.cloud_web.service.IConnectionHomeDetailService;
import com.ucap.cloud_web.service.IDatabaseInfoService;
import com.ucap.cloud_web.service.IDatabaseTreeInfoService;
import com.ucap.cloud_web.service.IDicConfigService;
import com.ucap.cloud_web.service.IEarlyDetailTempService;
import com.ucap.cloud_web.service.ILinkHomeAvailableService;
import com.ucap.cloud_web.service.ILinkHomeTrendService;
import com.ucap.cloud_web.service.IMTaskdetailService;
import com.ucap.cloud_web.service.ISecurityHomeChannelService;
import com.ucap.cloud_web.service.IUpdateContentCountService;
import com.ucap.cloud_web.service.IUpdateHomeDetailService;
import com.ucap.cloud_web.util.MapUtil;


/**
 * <p>Description:微信版开发----免费版</p>
 * <p>@Package：com.ucap.cloud_web.servlet </p>
 * <p>Title: TokenAction </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：cuichx </p>
 * <p>@date：2015-12-23下午7:37:20 </p>
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class FreeTokenAction extends BaseAction{
	private static Log logger = LogFactory.getLog(FreeTokenAction.class);
	@Autowired
	private IDatabaseTreeInfoService databaseTreeInfoServiceImpl;
	@Autowired
	private IUpdateContentCountService updateContentCountServiceImpl;//更新量统计
	@Autowired
	private IUpdateHomeDetailService updateHomeDetailServiceImpl;//首页更新详情
	@Autowired
	private IConnectionHomeDetailService connectionHomeDetailServiceImpl;//首页不更新详情表
	@Autowired
	private IMTaskdetailService MTaskdetailServiceImpl;
	@Autowired
	private ISecurityHomeChannelService securityHomeChannelServiceImpl;
	@Autowired
	private UrlAdapterVar urlAdapterVar;
	@Autowired
	private ILinkHomeTrendService linkHomeTrendServiceImpl;
	@Autowired
	private ILinkHomeAvailableService linkHomeAvailableServiceImpl;
	@Autowired
	private IDicConfigService dicConfigServiceImpl;
	@Autowired
	private DatabaseBizService databaseBizServiceImpl;
	@Autowired
	private IDatabaseInfoService databaseInfoServiceImpl;
	@Autowired
	private IEarlyDetailTempService earlyDetailTempServiceImpl;
	//初始化页面数据使用
	private Map<String, Object> initMap=null;
	
	Map<String, Object> resultMap=new HashMap<String, Object>();
	
	//String scanDate=DateUtils.getNextDay(new Date(), -2);//获取前天日期
	String scanDate=DateUtils.getYesterdayStr();//获取昨天日期
	//String scanDate="2017-05-14";//获取昨天日期
	String nowDate=DateUtils.formatStandardDate(new Date());//当前日期
	/**
	 * @Description: 首页链接不可用统计数据---填报单位 
	 * @author cuichx --- 2017-3-30上午10:43:16
	 */
	public void getLinkHomeTbData(){
		
		String siteCode=request.getParameter("siteCode");
		try {
			//列表数据展示
			LinkHomeAvailableRequest laRequest=new LinkHomeAvailableRequest();
			laRequest.setSiteCode(siteCode);
			laRequest.setScanDate(scanDate);
			laRequest.setPageSize(Integer.MAX_VALUE);
			List<LinkHomeAvailable>  linkList=linkHomeAvailableServiceImpl.queryList(laRequest);
			if(linkList!=null && linkList.size()>0){
				List<Map<String, Object>> returnList=new ArrayList<Map<String,Object>>();
	 			for (LinkHomeAvailable linkHomeAvailable : linkList) {
					Map<String, Object> map=new HashMap<String, Object>();
					map.put("linkTitle", linkHomeAvailable.getLinkTitle()!=null?linkHomeAvailable.getLinkTitle():"无标题");
					map.put("scanTime", linkHomeAvailable.getScanTime()!=null?linkHomeAvailable.getScanTime():"");
					String questionDescribe="";
					if(linkHomeAvailable.getQuestionCode()!=null){
						questionDescribe=linkHomeAvailable.getQuestionCode()+QuestionTypeWeiXin.getNameByCode(linkHomeAvailable.getQuestionCode());
					}
					map.put("questionDescribe", questionDescribe);
					
					if(StringUtils.isNotEmpty(linkHomeAvailable.getUrl())){
						map.put("url", linkHomeAvailable.getUrl());
					}else{
						map.put("url", "");
					}
					
					returnList.add(map);
				}
				resultMap.put("returnList", returnList);
			}
			
			//获取确认死链和疑似死链的错误编码
			Map<String, Object>  params = new HashMap<String, Object>();
			String configId = "20,21";
			String[] codeArray = configId.split(",");
			params.put("configIds", codeArray);
			List<DicConfigRequest> dicConfig = dicConfigServiceImpl.queryListByMap(params);
			
			String confirmCode=dicConfig.get(0).getValue();
			String notConfimCode=dicConfig.get(1).getValue();
			
			String linkCodeStr=confirmCode+","+notConfimCode;
			
			logger.info("=====linkCodeStr:"+linkCodeStr);
			String[] codeArr=linkCodeStr.split(",");
			//根据问题编码进行分类统计
			Map<String, Object> paramMap=new HashMap<String, Object>();
			paramMap.put("siteCode", siteCode);
			paramMap.put("scanDate", scanDate);
			paramMap.put("groupBy", "question_code");//按问题编码分组
			paramMap.put("codeArr", codeArr);
			List<LinkHomeAvailableRequest> groupList=linkHomeAvailableServiceImpl.queryGroupBy(paramMap);
			if(groupList!=null && groupList.size()>0){
				
				List<Map<String, Object>> confrimList=new ArrayList<Map<String,Object>>();
				List<Map<String, Object>> notConfirmList=new ArrayList<Map<String,Object>>();
				
				for (int i = 0; i < groupList.size(); i++) {
					LinkHomeAvailableRequest availableRequest=groupList.get(i);
					Map<String, Object> map=new HashMap<String, Object>();
					String questionDescribe="";
					if(availableRequest.getQuestionCode()!=null){
						questionDescribe=availableRequest.getQuestionCode()+QuestionTypeWeiXin.getNameByCode(availableRequest.getQuestionCode()+"");
					}
					map.put("questionDescribe",questionDescribe);
					map.put("totalNum", availableRequest.getCountNum()!=null?availableRequest.getCountNum():0);
					
					//确认首页死链
					if(confirmCode.contains(String.valueOf(availableRequest.getQuestionCode()))){
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
	 * @Description:首页链接不可用性---填报单位 
	 * @author cuichx --- 2017-3-29下午5:02:58     
	 * @return
	 */
	public String linkHomeTbIndex(){
		
		initMap=new HashMap<String, Object>();
		String siteCode=request.getParameter("siteCode");//网站标识码
		String linkHome=request.getParameter("linkHome");//首页不可用链接总数
		String siteName=request.getParameter("siteName");//网站名称
		
		initMap.put("siteCode", siteCode);
		initMap.put("linkHome", linkHome);
		initMap.put("siteName", siteName);
		initMap.put("scanDate", scanDate);
		
		
		return "newWeiXin";
	}
	
	/**
	 * @Description: 首页不可用链接统计--组织单位
	 * @author cuichx --- 2017-3-29下午5:11:35
	 */
	public void getLinkHomeOrgData(){
		
		String orgSiteCode=request.getParameter("siteCode");
		String checkType=request.getParameter("checkType");
		String pageNum=request.getParameter("pageNum");//页码
		String pageSize=request.getParameter("pageSize");//
		
		//查询首页不更新超过14天的站点信息
		HashMap<String, Object> paraMap=new HashMap<String, Object>();
		paraMap.put("orgSiteCode", orgSiteCode);
		if(StringUtils.isNotEmpty(checkType) && !"0".equals(checkType)){
			paraMap.put("checkType", checkType);
		}
		paraMap.put("isexp", 1);
		paraMap.put("isLink", 1);
		paraMap.put("scanDate", scanDate);
		paraMap.put("linkNum", 0);
		
		int pageTotal=0;//总记录数
		pageTotal=linkHomeTrendServiceImpl.queryTotalOrgByMapCount(paraMap);
		
		
		paraMap.put("pageBegin", (Integer.valueOf(pageNum)-1)*Integer.valueOf(pageSize));
		paraMap.put("pageEnd", Integer.valueOf(pageSize));
		try {
			List<Map<String, Object>> returnList=new ArrayList<Map<String,Object>>();
			List<LinkHomeTrendRequest> linkList=linkHomeTrendServiceImpl.queryTotalOrgByMap(paraMap);
			if(linkList!=null && linkList.size()>0){
				for (int i = 0; i < linkList.size(); i++) {
					LinkHomeTrendRequest linkRequest=linkList.get(i);
					Map<String, Object> map=new HashMap<String, Object>();
					map.put("siteCode", linkRequest.getSiteCode()!=null?linkRequest.getSiteCode():"");
					map.put("siteName", linkRequest.getSiteName()!=null?linkRequest.getSiteName():"");
					map.put("websiteNum", linkRequest.getWebsiteSum()!=null?linkRequest.getWebsiteSum():"");
					
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
	 * @Description:首页链接不可用性---组织单位 
	 * @author cuichx --- 2017-3-29下午5:02:58     
	 * @return
	 */
	public String linkHomeOrgIndex(){
		
		initMap=new HashMap<String, Object>();
		String siteCode=request.getParameter("siteCode");
		String checkType=request.getParameter("checkType");
		String linkHome=request.getParameter("linkHome");
		String linkHomeSite=request.getParameter("linkHomeSite");
		
		initMap.put("siteCode", siteCode);
		initMap.put("checkType", checkType);
		initMap.put("linkHome", linkHome);
		initMap.put("linkHomeSite", linkHomeSite);
		initMap.put("scanDate", scanDate);
		
		
		return "newWeiXin";
	}
	
	/**
	 * @Description: 首页更新不及时--填报单位
	 * @author cuichx --- 2017-3-29下午3:10:09     
	 * @return
	 */
	public String securityHomeTbIndex(){
		initMap=new HashMap<String, Object>();
		
		String siteCode=request.getParameter("siteCode");//网站标识码
		String siteName=request.getParameter("siteName");//网站名称

		SecurityHomeChannelRequest homeRequest=new SecurityHomeChannelRequest();
		homeRequest.setSiteCode(siteCode);
		homeRequest.setScanDate(scanDate);
		homeRequest.setType(1);
		
		try {
			List<SecurityHomeChannel>  homeList=securityHomeChannelServiceImpl.queryList(homeRequest);
			if(homeList!=null && homeList.size()>0){
				SecurityHomeChannel homeChannel=homeList.get(0);
				
				initMap.put("siteCode", siteCode);
				initMap.put("siteName", siteName);
				initMap.put("scanDate", scanDate);
				initMap.put("notUpdateNum", homeChannel.getNotUpdateNum());//未更新天数
				initMap.put("modifyDate", homeChannel.getModifyDate());
				initMap.put("imageUrl", urlAdapterVar.getImgUrl()+homeChannel.getImageUrl());
			}else{
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				DatabaseInfoRequest databaseRequest=new DatabaseInfoRequest();
				databaseRequest.setSiteCodeLike(siteCode);
				List<DatabaseInfo>  databaseList=databaseInfoServiceImpl.queryList(databaseRequest);
				String homeUrl="";
				DatabaseInfo databaseInfo=new DatabaseInfo();
				if(databaseList!=null && databaseList.size()>0){
					databaseInfo=databaseList.get(0);
					homeUrl = StringUtils.isEmpty(databaseInfo.getJumpUrl()) ? databaseInfo.getUrl() : databaseInfo.getJumpUrl();
				}
				UpdateHomeDetailRequest updateHomeDetailRequest = new UpdateHomeDetailRequest();
				updateHomeDetailRequest.setBeginScanDate(scanDate);
				updateHomeDetailRequest.setSiteCode(siteCode);
				updateHomeDetailRequest.setHomeUrl(homeUrl);
				UpdateHomeDetail uhd = updateHomeDetailServiceImpl.getNearest(updateHomeDetailRequest);
				if (uhd != null) {
					String lastUpdateDate = uhd.getUpdateTime();
					String imgUrl="";
					if(StringUtils.isNotEmpty(uhd.getImgUrl()) && StringUtils.isNotNull(uhd.getImgUrl())){
						//快照路径
						imgUrl = urlAdapterVar.getImgUrl() + uhd.getImgUrl();
					}
					Date yesDay = df.parse(scanDate);
					Date d2 = df.parse(lastUpdateDate);
					long diff = yesDay.getTime() - d2.getTime();
					long notUpdateNum = diff / (1000 * 60 * 60 * 24);// 计算出未更新天数
					
					initMap.put("siteCode", siteCode);
					initMap.put("siteName", siteName);
					initMap.put("scanDate", scanDate);
					initMap.put("notUpdateNum", notUpdateNum);//未更新天数
					initMap.put("modifyDate", lastUpdateDate);
					initMap.put("imageUrl",imgUrl);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "newWeiXin";
	}
	/**
	 * @Description: 首页更新不及时数据--组织单位
	 * @author cuichx --- 2017-3-29上午11:36:14
	 */
	public void getsecurityHomeOrgData(){
		String orgSiteCode=request.getParameter("siteCode");
		String checkType=request.getParameter("checkType");
		String pageNum=request.getParameter("pageNum");//当前页数
		String pageSize=request.getParameter("pageSize");//每个大小
		
		logger.info("===FreeTokenAction  getsecurityHomeOrgData orgSiteCode:"+orgSiteCode
				+"   checkType="+checkType);
		try {
			
			//查询首页不更新超过14天的站点信息
			HashMap<String, Object> paraMap=new HashMap<String, Object>();
			paraMap.put("orgSiteCode", orgSiteCode);
			if(!"0".equals(checkType)){
				paraMap.put("siteType", checkType);
			}
			paraMap.put("isexp", 1);
			paraMap.put("isLink", 1);
			paraMap.put("twoWeeks", 14);
			paraMap.put("scanDate", scanDate);
			
			int pageTotal=securityHomeChannelServiceImpl.getSecurityHomeListCount(paraMap);
			
			Integer begin=(Integer.valueOf(pageNum)-1)*Integer.valueOf(pageSize);
			paraMap.put("begin", begin);
			paraMap.put("end", Integer.valueOf(pageSize));
			
			List<SecurityHomeChannelRequest> homeList=securityHomeChannelServiceImpl.getSecurityHomeList(paraMap);
			if(homeList!=null && homeList.size()>0){
				List<Map<String, Object>> returnList=new ArrayList<Map<String,Object>>();
				
				for (int i = 0; i < homeList.size(); i++) {
					SecurityHomeChannelRequest homeRequest=homeList.get(i);
					Map<String, Object> map=new HashMap<String, Object>();
					map.put("siteCode", homeRequest.getSiteCode()!=null?homeRequest.getSiteCode():"");
					map.put("siteName", homeRequest.getSiteName()!=null?homeRequest.getSiteName():"");
					map.put("notUpdateDays", homeRequest.getNotUpdateDays()!=null?homeRequest.getNotUpdateDays():0);
					map.put("updateTime", homeRequest.getUpdateTime()!=null?homeRequest.getUpdateTime():"");
					map.put("scanDate", scanDate);
				//	map.put("imageUrl", urlAdapterVar.getImgUrl()+homeRequest.getImageUrl());
					
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
	 * @Description: 首页更新不及时--组织单位
	 * @author cuichx --- 2017-3-29上午11:29:54     
	 * @return
	 */
	public String securityHomeOrgIndex(){
		
		initMap=new HashMap<String, Object>();
		String siteCode=request.getParameter("siteCode");
		String checkType=request.getParameter("checkType");
		String securityHome=request.getParameter("securityHome");
		logger.info("===FreeTokenAction  securityHomeOrgIndex siteCode:"+siteCode
				+"   checkType="+checkType+"  securityHome:"+securityHome);
		
		initMap.put("siteCode", siteCode);
		initMap.put("checkType", checkType);
		initMap.put("securityHome", securityHome);
		initMap.put("scanDate", scanDate);
		
		return "newWeiXin";
	}
	/**
	 * @Description: 首页更新量数据---填报单位
	 * @author cuichx --- 2017-3-28下午3:10:46
	 */
	public void getUpdateHomeTbData(){
		
		resultMap=new HashMap<String, Object>();
		String siteCode=request.getParameter("siteCode");//网站标识码
		//String scanDate=request.getParameter("scanDate");//检测时间
		String pageNum=request.getParameter("pageNum");//当前页数
		String pageSize=request.getParameter("pageSize");//每个大小
		logger.info("getIndexCountAll siteCode:"+siteCode+"  scanDate："+scanDate);
		try {
			UpdateHomeDetailRequest homeDetailRequest=new UpdateHomeDetailRequest();
			homeDetailRequest.setSiteCode(siteCode);
			homeDetailRequest.setDate(scanDate);
			
			List<QueryOrder> queryOrderList=new ArrayList<QueryOrder>();
			QueryOrder siteQueryOrder=new QueryOrder("scan_date",QueryOrderType.DESC);
			queryOrderList.add(siteQueryOrder);
			homeDetailRequest.setQueryOrderList(queryOrderList);
			
			int pageTotal=updateHomeDetailServiceImpl.queryCount(homeDetailRequest);
			homeDetailRequest.setPageNo(Integer.valueOf(pageNum));
			homeDetailRequest.setPageSize(Integer.valueOf(pageSize));
			
			List<UpdateHomeDetail>  updateDetailList=updateHomeDetailServiceImpl.queryList(homeDetailRequest);
			if(updateDetailList!=null && updateDetailList.size()>0){
				List<Map<String, Object>> titleList=new ArrayList<Map<String, Object>>();
				for (UpdateHomeDetail updateHomeDetail : updateDetailList) {
					Map<String, Object> map=new HashMap<String, Object>();
					String title="";
					if(StringUtils.isNotEmpty(updateHomeDetail.getTitle())){
						title=updateHomeDetail.getTitle();
					}
					map.put("title", title);
					String url="";
					if(StringUtils.isNotEmpty(updateHomeDetail.getUrl())){
						url=updateHomeDetail.getUrl();
					}
					map.put("url", url);
					
					titleList.add(map);
				}
				resultMap.put("pageTotal", pageTotal);
				resultMap.put("titleList", titleList);
			}
			
			resultMap.put("siteCode", siteCode);
			resultMap.put("scanDate", scanDate);
	    	writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("errorMsg", "获取健康指数排名异常");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		}
	}
	
	/**
	 * @Description:首页更新量 --填报单位
	 * @author cuichx --- 2017-3-28下午1:32:05
	 */
	public String updateHomeTbIndex(){
		initMap=new HashMap<String, Object>();
		String siteCode=request.getParameter("siteCode");//网站标识码
		String updateHome=request.getParameter("updateHome");//首页更新量
		//String scanDate=request.getParameter("scanDate");//监测日期
		String siteName=request.getParameter("siteName");//网站名称
/*		if(StringUtils.isNotEmpty(scanDate)){
			scanDate=DateUtils.getYesterdayStr();
		}*/
		logger.info("getIndexCountAll siteCode:"+siteCode+"  updateHome："+updateHome
				+"  scanDate："+scanDate+"  siteName:"+siteName);
		
		initMap.put("siteCode", siteCode);
		initMap.put("updateHome", updateHome);
		initMap.put("scanDate", scanDate);
		initMap.put("siteName", siteName);
		
		return "newWeiXin";
	}
	
	/**
	 * @Description: 首页更新量数据---组织单位
	 * @author cuichx --- 2017-3-28下午3:10:46
	 */
	public void getUpdateHomeData(){
		String orgSiteCode=request.getParameter("siteCode");//网站标识码
		String checkType=request.getParameter("checkType");//组织机构级别
		//String scanDate=request.getParameter("scanDate");//检测时间
		String pageNum=request.getParameter("pageNum");//当前页数
		String pageSize=request.getParameter("pageSize");//每个大小
		
		logger.info("getIndexCountAll orgSiteCode:"+orgSiteCode+"  checkType："+checkType+"  scanDate:"+scanDate);
		try {
			//获取站点标识码集合
			Map<String, Object> paraMap=new HashMap<String, Object>();
			paraMap.put("orgSiteCode", orgSiteCode);
			if(!"0".equals(checkType)){
				paraMap.put("layerType", checkType);
			}
			//paraMap.put("isScan", 1);
			paraMap.put("isexp", 1);
			paraMap.put("isLink", 1);
			List<String>  treeList=databaseTreeInfoServiceImpl.queryDownSite2(paraMap);
			if(treeList!=null && treeList.size()>0){
				
		    	Map<String, Object> updateMap=new HashMap<String, Object>();
		    	updateMap.put("siteCodeList", treeList);
		    	updateMap.put("scanDate", scanDate);
		    	updateMap.put("type", 0);//首页
		    	updateMap.put("updateNum",1);
		    	
		    	
		    	int pageTotal=updateContentCountServiceImpl.queryTotalByMapCount(updateMap);
				Integer begin=(Integer.valueOf(pageNum)-1)*Integer.valueOf(pageSize);
				updateMap.put("begin", begin);
				updateMap.put("end", Integer.valueOf(pageSize));
		    	
				List<UpdateContentCountRequest> homeList= updateContentCountServiceImpl.queryTotalByMap(updateMap);
				if(homeList!=null && homeList.size()>0){
					List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
					for (int i = 0; i < homeList.size(); i++) {
						UpdateContentCountRequest homeRequest=homeList.get(i);
						String siteCode="";
						String siteName="";
						if(StringUtils.isNotEmpty(homeRequest.getSiteCode())){
							siteCode=homeRequest.getSiteCode();
						}
						if(StringUtils.isNotEmpty(homeRequest.getSiteName())){
							siteName=homeRequest.getSiteName();
						}
						Map<String, Object> map=new HashMap<String, Object>();
						map.put("siteCode", siteCode);
						map.put("siteName", siteName);
						map.put("homeNum", homeRequest.getHomeNum());
						
						list.add(map);
						
					}
					resultMap.put("list", list);
					resultMap.put("pageTotal", pageTotal);
				}
			}
	    	writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("errorMsg", "获取健康指数排名异常");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		}
	}
	
	
	/**
	 * @Description:首页更新量---组织单位
	 * @author cuichx --- 2017-3-28下午1:32:05
	 */
	public String updateHomeOrgIndex(){
		initMap=new HashMap<String, Object>();
		String siteCode=request.getParameter("siteCode");//网站标识码
		String checkType=request.getParameter("checkType");//组织机构级别
		String updateHome=request.getParameter("updateHome");//首页更新量
		String updateHomeSite=request.getParameter("updateHomeSite");//首页更新分布站点
		logger.info("getIndexCountAll siteCode:"+siteCode+"  checkType："+checkType
				+"  updateHome："+updateHome
				+"  updateHomeSite："+updateHomeSite);
		
		initMap.put("siteCode", siteCode);
		initMap.put("checkType", checkType);
		initMap.put("updateHome", updateHome);
		initMap.put("updateHomeSite", updateHomeSite);
		initMap.put("scanDate", scanDate);
		
		return "newWeiXin";
	}
	
	
	/**
	 * @Description: 首页不连通数据统计---填报单位
	 * @author cuichx --- 2017-3-28下午3:10:46
	 */
	public void getConnHomeTbData(){
		String siteCode=request.getParameter("siteCode");//网站标识码
		try {
			/**
			 * 当日连不通次数
			 */
			String scanTime="";
			String questionDes="";//问题描述
			Integer pageTotal=0;//今日不连通个数
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
		    						pageTotal=detailList.size();
		    						Detail de=detailList.get(0);
		    						if(StringUtils.isNotEmpty(de.getStime())){
		    							scanTime=de.getStime();
		    						}
		    						String code=de.getCode();//错误码
		    						if(StringUtils.isNotEmpty(code)){
		    							questionDes=code+" "+QuestionTypeWeiXin.getNameByCode(code);
		    						}
		    					}
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			resultMap.put("pageTotal", pageTotal);
			resultMap.put("scanTime", scanTime);
			resultMap.put("questionDes", questionDes);
			writerPrint(JSONObject.fromObject(resultMap).toString());
			
/*			String scanTime="";
			String questionDes="";//问题描述
			EarlyDetailTempRequest earlyTempRequest=new EarlyDetailTempRequest();
			earlyTempRequest.setSiteCode(siteCode);
			earlyTempRequest.setScanDate(nowDate);
			earlyTempRequest.setNotErrorCode("200");
		
			List<EarlyDetailTemp>  earlyTempList=earlyDetailTempServiceImpl.queryList(earlyTempRequest);
			if(earlyTempList!=null && earlyTempList.size()>0){
				EarlyDetailTemp earlyDetailTemp=earlyTempList.get(0);
				
				String code=earlyDetailTemp.getErrorCode();//错误码
				if(StringUtils.isNotEmpty(code)){
					questionDes=code+" "+QuestionTypeWeiXin.getNameByCode(code);
				}
				scanTime=earlyDetailTemp.getLastScanTime();
				resultMap.put("questionDes", questionDes);
				resultMap.put("scanTime", scanTime);
			}
	    	writerPrint(JSONObject.fromObject(resultMap).toString());*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	/**
	 * @Description:首页连不通--填报单位
	 * @author cuichx --- 2017-3-28下午8:08:57     
	 * @return
	 */
	public String connHomeTbIndex(){
		
		initMap=new HashMap<String, Object>();
		String siteCode=request.getParameter("siteCode");//网站标识码
		
		/**
		 * 基本信息数据
		 */
		String siteName="";
		String queueStr="";
		DatabaseInfoRequest dataRequest=new DatabaseInfoRequest();
		dataRequest.setSiteCode(siteCode);
		try {
			List<DatabaseInfo>  dataList=databaseInfoServiceImpl.queryList(dataRequest);
			if(dataList!=null && dataList.size()>0){
				siteName=dataList.get(0).getName();
				queueStr=QueueType.getNameByCode(dataList.get(0).getQueue());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		initMap.put("siteName", siteName);
		initMap.put("queueStr",queueStr);
		
		/**
		 * 7日不连通率统计
		 */
		MTaskdetailRequest mtRequest=new MTaskdetailRequest();
		mtRequest.setSiteCode(siteCode);
		mtRequest.setCountday(DateUtils.formatShortDate(DateUtils.getYesterdaytime()));
		
		Integer notConnNum7=0;
		String notConnPer7="0.00%";
		try {
			List<MTaskdetail>  mtList=MTaskdetailServiceImpl.queryList(mtRequest);
			if(mtList!=null && mtList.size()>0){
				notConnNum7=mtList.get(0).getLinkerrnum();
				notConnPer7=mtList.get(0).getLinkerrprop7()+"%";
			}
			initMap.put("notConnNum7", notConnNum7);
			initMap.put("notConnPer7", notConnPer7);
			initMap.put("siteCode", siteCode);
			initMap.put("scanDate", nowDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "newWeiXin";
		
	}
	
	/**
	 * @Description: 首页不连通---组织单位
	 * @author cuichx --- 2017-3-28下午3:10:46
	 */
	public void connHomeOrgData(){
		String orgSiteCode=request.getParameter("siteCode");//网站标识码
		String checkType=request.getParameter("checkType");//组织机构级别
		String pageNum=request.getParameter("pageNum");//当前页数
		String pageSize=request.getParameter("pageSize");//每个大小
		
		logger.info("getIndexCountAll orgSiteCode:"+orgSiteCode+"  checkType："+checkType);
		try {
			
			//获取站点标识码集合
			Map<String, Object> paraMap=new HashMap<String, Object>();
			paraMap.put("orgSiteCode", orgSiteCode);
			if(!"0".equals(checkType)){
				paraMap.put("layerType", checkType);
			}
			paraMap.put("isexp", 1);
			paraMap.put("isLink", 1);
			paraMap.put("scanDate", DateUtils.formatStandardDate(new Date()));//yyyy-MM-dd
			paraMap.put("type", 1);//首页连通性
			paraMap.put("errorCode", "200");
			paraMap.put("nowDate", nowDate);
			
			int pageTotal=earlyDetailTempServiceImpl.queryEarlyTempByMapCount(paraMap);
			
			Integer begin=(Integer.valueOf(pageNum)-1)*Integer.valueOf(pageSize);
			Integer end=Integer.valueOf(pageSize);
			paraMap.put("begin", begin);
			paraMap.put("end", end);
			//获取首页不连通率超过3%
			List<EarlyDetailTempRequest> earlyTempList=earlyDetailTempServiceImpl.queryEarlyTempByMap(paraMap);
			if(earlyTempList!=null && earlyTempList.size()>0){
				List<Map<String, Object>> returnList=new ArrayList<Map<String,Object>>();//连不通
				for (int i = 0; i < earlyTempList.size(); i++) {
					EarlyDetailTempRequest earlyTempRequest=earlyTempList.get(i);
					Map<String, Object> map=new HashMap<String, Object>();
					map.put("siteCode", earlyTempRequest.getSiteCode()!=null?earlyTempRequest.getSiteCode():"");
					map.put("siteName", earlyTempRequest.getSiteName()!=null?earlyTempRequest.getSiteName():"");
					map.put("scanTime", earlyTempRequest.getLastScanTime()!=null?earlyTempRequest.getLastScanTime():"");
					
					//7日不连通率(截至昨日)  查询大数据表
					String linkerrprop7="0.00%";
					MTaskdetailRequest mtRequest=new MTaskdetailRequest();
					mtRequest.setSiteCode(earlyTempRequest.getSiteCode());
					mtRequest.setCountday(scanDate);
					List<MTaskdetail>  mtList=MTaskdetailServiceImpl.queryList(mtRequest);
					if(mtList!=null && mtList.size()>0){
						if(mtList.get(0).getLinkerrprop7()!=null){
							linkerrprop7=mtList.get(0).getLinkerrprop7()+"%";
						}
					}
					map.put("linkerrprop7", linkerrprop7);
					returnList.add(map);
				}
				resultMap.put("pageTotal", pageTotal);
				resultMap.put("returnList", returnList);
			}
	    	writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("errorMsg", "获取健康指数排名异常");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		}
	}
	
	/**
	 * @Description:首页连不通--组织单位 --一周连通率超过3%
	 * @author cuichx --- 2017-3-28下午8:08:57     
	 * @return
	 */
	public void connHomePerOrgData(){
		
		String orgSiteCode=request.getParameter("siteCode");//网站标识码
		String checkType=request.getParameter("checkType");//组织机构级别
		String pageNum=request.getParameter("pageNum");//当前页数
		String pageSize=request.getParameter("pageSize");//每页展示的数据个数
		
		logger.info("getIndexCountAll orgSiteCode:"+orgSiteCode+"  checkType："+checkType);
		try {
			//封装返回数据
			List<Map<String, Object>> connPer7List=new ArrayList<Map<String,Object>>();//一周不连通率超过3%
			/**
			 * 一周不连通率超过3%
			 */
			//获取站点标识码集合
			Map<String, Object> paraMap=new HashMap<String, Object>();
			paraMap.put("orgSiteCode", orgSiteCode);
			if(!"0".equals(checkType)){
				paraMap.put("layerType", checkType);
			}
			paraMap.put("isexp", 1);
			paraMap.put("isLink", 1);
			paraMap.put("scanDate", scanDate);//yyyy-MM-dd
			paraMap.put("countday", DateUtils.formatShortDate(DateUtils.getYesterdaytime()));//yyyyMMdd
			paraMap.put("linkerrprop", 3);//yyyy-MM-dd
			int countNum=MTaskdetailServiceImpl.queryPerLin7ByMapCount(paraMap);
			
			Integer begin=(Integer.valueOf(pageNum)-1)*Integer.valueOf(pageSize);
			Integer end=Integer.valueOf(pageSize);
			paraMap.put("begin", begin);
			paraMap.put("end", end);
			//获取首页不连通率超过3%
			List<MTaskdetailRequest> mTaskList=MTaskdetailServiceImpl.queryPerLin7ByMap(paraMap);
			if(mTaskList!=null && mTaskList.size()>0){
				for (int i = 0; i < mTaskList.size(); i++) {
					MTaskdetailRequest mTaskdetailRequest=mTaskList.get(i);
					Map<String, Object> map=new HashMap<String, Object>();
					map.put("siteCode", mTaskdetailRequest.getSiteCode()!=null?mTaskdetailRequest.getSiteCode():"");
					map.put("siteName", mTaskdetailRequest.getSiteName()!=null?mTaskdetailRequest.getSiteName():"");
					map.put("linkerrprop7", mTaskdetailRequest.getLinkerrprop7()!=null?mTaskdetailRequest.getLinkerrprop7()+"%":"0.00%");
					
					//最近连不通时间
/*					ConnectionHomeDetailRequest homeRequest=new ConnectionHomeDetailRequest();
					homeRequest.setSiteCode(mTaskdetailRequest.getSiteCode());
					homeRequest.setPageSize(1);
					List<QueryOrder> querySiteList=new ArrayList<QueryOrder>();
					QueryOrder siteQueryOrder=new QueryOrder("scan_date",QueryOrderType.ASC);
					querySiteList.add(siteQueryOrder);
					
					String scanTime="";
					 List<ConnectionHomeDetail>  homeList=connectionHomeDetailServiceImpl.queryList(homeRequest);
					 if(homeList!=null && homeList.size()>0){
						 scanTime=homeList.get(0).getScanTime();
					 }
					 map.put("scanTime", scanTime);*/
					 
					connPer7List.add(map);
				}
				resultMap.put("pageTotal", countNum);
				resultMap.put("connPer7List", connPer7List);
			}
	    	writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("errorMsg", "获取健康指数排名异常");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		}
	}
	/**
	 * @Description:首页连不通--组织单位 
	 * @author cuichx --- 2017-3-28下午8:08:57     
	 * @return
	 */
	public String connHomeOrgIndex(){
		
		initMap=new HashMap<String, Object>();
		String siteCode=request.getParameter("siteCode");//网站标识码
		String checkType=request.getParameter("checkType");//组织机构级别
		String connHome=request.getParameter("connHome");//首页连不通次数
		logger.info("getIndexCountAll siteCode:"+siteCode+"  checkType："+checkType
				+"  connHome："+connHome);
		
		//获取站点标识码集合
		Map<String, Object> paraMap=new HashMap<String, Object>();
		paraMap.put("orgSiteCode", siteCode);
		if(!"0".equals(checkType)){
			paraMap.put("layerType", checkType);
		}
		paraMap.put("isexp", 1);
		paraMap.put("isLink", 1);
		paraMap.put("scanDate", scanDate);//yyyy-MM-dd
		paraMap.put("countday", DateUtils.formatShortDate(DateUtils.getYesterdaytime()));//yyyyMMdd
		paraMap.put("linkerrprop", 3);//yyyy-MM-dd
		Integer countNum=MTaskdetailServiceImpl.queryPerLin7ByMapCount(paraMap);
		
		initMap.put("countNum", countNum!=null?countNum:0);
		initMap.put("siteCode", siteCode);
		initMap.put("checkType", checkType);
		initMap.put("connHome", connHome);
		initMap.put("scanDate", scanDate);
		
		return "newWeiXin";
		
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
