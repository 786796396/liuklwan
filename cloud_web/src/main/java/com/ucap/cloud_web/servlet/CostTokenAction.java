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


import com.publics.util.page.PageVo;
import com.publics.util.page.QueryOrder;
import com.publics.util.page.QueryOrderType;
import com.publics.util.utils.DateUtils;
import com.publics.util.utils.StringUtils;
import com.ucap.cloud_web.UrlAdapterVar;
import com.ucap.cloud_web.action.BaseAction;
import com.ucap.cloud_web.constant.CorrectType;
import com.ucap.cloud_web.constant.QuestionTypeWeiXin;


import com.ucap.cloud_web.dto.ConnectionAllRequest;
import com.ucap.cloud_web.dto.ConnectionBusinessDetailRequest;
import com.ucap.cloud_web.dto.ConnectionChannelDetailRequest;
import com.ucap.cloud_web.dto.CorrectContentDetailRequest;
import com.ucap.cloud_web.dto.DatabaseInfoRequest;
import com.ucap.cloud_web.dto.DetectionResultRequest;
import com.ucap.cloud_web.dto.SecurityHomeChannelRequest;
import com.ucap.cloud_web.dto.UpdateChannelDetailRequest;
import com.ucap.cloud_web.dto.UpdateContentCountRequest;
import com.ucap.cloud_web.entity.CorrectContentDetail;
import com.ucap.cloud_web.entity.DatabaseInfo;
import com.ucap.cloud_web.entity.DetectionResult;
import com.ucap.cloud_web.entity.DicChannel;
import com.ucap.cloud_web.entity.SecurityHomeChannel;
import com.ucap.cloud_web.entity.UpdateChannelDetail;
import com.ucap.cloud_web.service.IConnectionAllService;
import com.ucap.cloud_web.service.IConnectionBusinessDetailService;
import com.ucap.cloud_web.service.IConnectionChannelDetailService;
import com.ucap.cloud_web.service.ICorrectContentDetailService;
import com.ucap.cloud_web.service.IDatabaseInfoService;
import com.ucap.cloud_web.service.IDetectionResultService;
import com.ucap.cloud_web.service.IDicChannelService;
import com.ucap.cloud_web.service.ISecurityHomeChannelService;
import com.ucap.cloud_web.service.IUpdateChannelDetailService;
import com.ucap.cloud_web.service.IUpdateContentCountService;

/**
 * <p>Description:微信版开发---付费标准版 </p>
 * <p>@Package：com.ucap.cloud_web.servlet </p>
 * <p>Title: TokenAction </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：cuichx </p>
 * <p>@date：2015-12-23下午7:37:20 </p>
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class CostTokenAction extends BaseAction{
	private static Log logger = LogFactory.getLog(CostTokenAction.class);
	@Autowired
	private IDatabaseInfoService databaseInfoServiceImpl;
	@Autowired
	private IUpdateContentCountService updateContentCountServiceImpl;//更新量统计
	@Autowired
	private ISecurityHomeChannelService securityHomeChannelServiceImpl;
	@Autowired
	private UrlAdapterVar urlAdapterVar;
	@Autowired
	private IConnectionAllService connectionAllServiceImpl;
	@Autowired
	private IConnectionChannelDetailService connectionChannelDetailServiceImpl;
	@Autowired
	private IConnectionBusinessDetailService connectionBusinessDetailServiceImpl;
	@Autowired
	private IUpdateChannelDetailService updateChannelDetailServiceImpl;
	@Autowired
	private IDicChannelService dicChannelServiceImpl;
	@Autowired
	private ICorrectContentDetailService correctContentDetailServiceImpl;
	@Autowired
	private IDetectionResultService detectionResultServiceImpl;
	//初始化页面数据使用
	private Map<String, Object> initMap=null;
	
	Map<String, Object> resultMap=new HashMap<String, Object>();
	
	//String scanDate=DateUtils.getNextDay(new Date(), -2);//获取前天日期
	String scanDate=DateUtils.getYesterdayStr();//获取昨天日期
	//String scanDate="2017-05-14";//获取昨天日期
	
	/**
	 * @Description: 错别字---填报单位
	 * @author cuichx --- 2017-4-1下午6:44:54
	 */
	public void getContCorrectTbData(){
		String siteCode=request.getParameter("siteCode");//网站标识码
		String pageNum=request.getParameter("pageNum");//当前页数
		String pageSize=request.getParameter("pageSize");//每个大小
		try {
			
			CorrectContentDetailRequest corrRequest=new CorrectContentDetailRequest();
			corrRequest.setSiteCode(siteCode);
			corrRequest.setScanDate(scanDate);
			corrRequest.setExposure(1);

			logger.info("getContCorrectTbData siteCode:"+siteCode);
			
			int pageTotal=correctContentDetailServiceImpl.queryCount(corrRequest);
			
			corrRequest.setPageNo(Integer.valueOf(pageNum));
			corrRequest.setPageSize(Integer.valueOf(pageSize));
			//设置排序字段
			List<QueryOrder> queryOrderList=new ArrayList<QueryOrder>();
			QueryOrder siteQueryOrder=new QueryOrder("modify_time",QueryOrderType.DESC);
			queryOrderList.add(siteQueryOrder);
			corrRequest.setQueryOrderList(queryOrderList);
			
			List<CorrectContentDetail>  corrList=correctContentDetailServiceImpl.queryList(corrRequest);
			if(corrList!=null && corrList.size()>0){
				List<Map<String, Object>> returnList=new ArrayList<Map<String,Object>>();
				for (CorrectContentDetail correctContentDetail : corrList) {
					Map<String, Object> map=new HashMap<String, Object>();
					map.put("wrongTerm", correctContentDetail.getWrongTerm()!=null?correctContentDetail.getWrongTerm():"");
					map.put("expectTerms", correctContentDetail.getExpectTerms()!=null?correctContentDetail.getWrongTerm():"");
					map.put("correctType", CorrectType.getNameByCode(correctContentDetail.getCorrectType()));
					map.put("scanDate", correctContentDetail.getScanDate());
					map.put("url", correctContentDetail.getUrl()!=null?correctContentDetail.getUrl():"");
					//快照
					String imgUrl=correctContentDetail.getImgUrl();
					if (correctContentDetail.getOperator() == -1) {
						// 机器扫描
						DatabaseInfoRequest databaseInfoRequest=new DatabaseInfoRequest();
						databaseInfoRequest.setSiteCode(siteCode);
						List<DatabaseInfo> databaseList=databaseInfoServiceImpl.queryList(databaseInfoRequest);
						if(databaseList!=null && databaseList.size()>0){
							DatabaseInfo databaseInfo=databaseList.get(0);
							if (org.apache.commons.lang3.StringUtils.isNotBlank(correctContentDetail.getUrl())) {
							map.put("imgUrl", urlAdapterVar.getWronglyImg() + "encodeurl=" + databaseInfo.getEncodeUrl()
									+ "&url=" + correctContentDetail.getUrl());
							}else {
								map.put("imgUrl", "");
							}
						}
					} else {
						// 人工添加
						if (org.apache.commons.lang3.StringUtils.isNotBlank(imgUrl)) {
							map.put("imgUrl", correctContentDetail.getUrl());
						} else {
							map.put("imgUrl", "");
						}
					}
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
	 * @Description: 错别字----填报单位
	 * @author cuichx --- 2017-4-1下午6:40:27     
	 * @return
	 */
	public String contCorrectTbIndex(){
		initMap=new HashMap<String, Object>();
		String siteCode=request.getParameter("siteCode");//网站标识码
		String siteName=request.getParameter("siteName");//网站名称
		String contCorrectNum=request.getParameter("contCorrectNum");//错别字个数
		
		logger.info("contCorrectTbIndex siteCode:"+siteCode+"  siteName:"+siteName
				+"  contCorrectNum："+contCorrectNum);
		
		if(StringUtils.isEmpty(contCorrectNum)){
			List<DetectionResult>  dtList=detectionResultList(siteCode, scanDate);
			if(dtList!=null && dtList.size()>0){
				contCorrectNum=String.valueOf(dtList.get(0).getContCorrectNum());
			}else{
				contCorrectNum="0";
			}
		}
		
		initMap.put("siteCode", siteCode);
		initMap.put("siteName", siteName);
		initMap.put("contCorrectNum", contCorrectNum);
		initMap.put("scanDate", scanDate);
		
		return "newWeiXin";
	}
	/**
	 * @Description:错别字数据统计---组织单位 
	 * @author cuichx --- 2017-4-1下午5:25:58
	 */
	public void getContCorrectOrgData(){
		String orgSiteCode=request.getParameter("siteCode");//网站标识码
		String checkType=request.getParameter("checkType");//组织机构级别
		String pageNum=request.getParameter("pageNum");//当前页数
		String pageSize=request.getParameter("pageSize");//每个大小
		
		logger.info("getContCorrectOrgData orgSiteCode:"+orgSiteCode+"  checkType："+checkType);
		try {
			
			HashMap<String, Object> paraMap = new HashMap<String, Object>();
			paraMap.put("orgSiteCode", orgSiteCode);
			if(!"0".equals(checkType)){
				paraMap.put("siteType", checkType);
			}
			paraMap.put("isExp", 1);
			paraMap.put("isLink", 1);
			paraMap.put("scanDate", scanDate);
			
			
			int pageTotal=correctContentDetailServiceImpl.getCorrectContentListCount(paraMap);
			Integer begin=(Integer.valueOf(pageNum)-1)*Integer.valueOf(pageSize);
			Integer end=Integer.valueOf(pageSize);
			paraMap.put("begin", begin);
			paraMap.put("end", end);
			
			List<CorrectContentDetailRequest>  correctList=correctContentDetailServiceImpl.getCorrectContentList(paraMap);
			if(correctList!=null && correctList.size()>0){
				List<Map<String, Object>> returnList=new ArrayList<Map<String,Object>>();
				
				for (CorrectContentDetailRequest corRequest : correctList) {
					Map<String, Object> map=new HashMap<String, Object>();
					map.put("siteCode", corRequest.getSiteCode()!=null?corRequest.getSiteCode():"");
					map.put("siteName", corRequest.getSiteName()!=null?corRequest.getSiteName():"");
					map.put("wrongNum", corRequest.getWrongNum()!=null?corRequest.getWrongNum():0);
					
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
	 * @Description:  错别字--组织单位
	 * @author cuichx --- 2017-4-1下午5:24:04     
	 * @return
	 */
	public String contCorrectOrgIndex(){
		initMap=new HashMap<String, Object>();
		String siteCode=request.getParameter("siteCode");//网站标识码
		String checkType=request.getParameter("checkType");//组织机构级别
		String contCorrectNum=request.getParameter("contCorrectNum");//错别字个数
		String contCorrectSite=request.getParameter("contCorrectSite");//错别字分布站点
		logger.info("contCorrectOrgIndex siteCode:"+siteCode+"  checkType："+checkType
				+"  contCorrectNum："+contCorrectNum
				+"  contCorrectSite："+contCorrectSite);
		
		initMap.put("siteCode", siteCode);
		initMap.put("checkType", checkType);
		initMap.put("contCorrectNum", contCorrectNum);
		initMap.put("contCorrectSite", contCorrectSite);
		initMap.put("scanDate", scanDate);
		
		return "newWeiXin";
	}
	/**
	 * @Description: 栏目更新不及时统计数据---填报单位
	 * @author cuichx --- 2017-3-31下午3:56:37
	 */
	public void getSecurityChannelTbData(){
		
		
		String siteCode=request.getParameter("siteCode");//网站标识码
		String selectType=request.getParameter("selectType");//下拉框类型
/*		String pageNum=request.getParameter("pageNum");//当前页数
		String pageSize=request.getParameter("pageSize");//每个大小
*/		
		logger.info("getSecurityChannelTbData siteCode:"+siteCode+"  scanDate:"+scanDate);
		try {
			
			SecurityHomeChannelRequest securityHomeChannelRequest = new SecurityHomeChannelRequest();
			securityHomeChannelRequest.setSiteCode(siteCode);
			securityHomeChannelRequest.setScanDate(scanDate);
			securityHomeChannelRequest.setType(2);
			if(StringUtils.isNotEmpty(selectType) && !"4".equals(selectType)){
				securityHomeChannelRequest.setSelectType(selectType);
			}
			securityHomeChannelRequest.setFlag(true);
			//排序顺序
			List<QueryOrder> queryList = new ArrayList<QueryOrder>();
			queryList.add(new QueryOrder("user_id", QueryOrderType.DESC));
			securityHomeChannelRequest.setQueryOrderList(queryList); 
			
			PageVo<SecurityHomeChannel> pageVo=securityHomeChannelServiceImpl.queryReport(securityHomeChannelRequest);
			List<SecurityHomeChannel>  channelList=pageVo.getData();
			
			List<Map<String, Object>> returnList=new ArrayList<Map<String,Object>>();
			List<SecurityHomeChannel> list = new ArrayList<SecurityHomeChannel>();
			if (channelList != null && channelList.size() > 0) {
				// url servicePeroidId 排重
				list = removeDuplicate(channelList);
				
				for (SecurityHomeChannel homeChannel : list) {
					Map<String, Object> map=new HashMap<String, Object>();
					String channelTypeName="";
					DicChannel dicChannel=dicChannelServiceImpl.get(homeChannel.getChannelType());
					if(dicChannel!=null && !"".equals(dicChannel)){
						channelTypeName=dicChannel.getChannelName();
					}
					map.put("url", homeChannel.getUrl()!=null?homeChannel.getUrl():"");
					map.put("channelTypeName", channelTypeName);
					map.put("channelName", homeChannel.getName()!=null?homeChannel.getName():"");
					map.put("scanDate",scanDate);
					map.put("updateTime",homeChannel.getModifyDate()!=null?homeChannel.getModifyDate():0);
					map.put("notUpdateNum", homeChannel.getNotUpdateNum()!=null?homeChannel.getNotUpdateNum():0);
					//initMap.put("imageUrl", urlAdapterVar.getImgUrl()+homeChannel.getImageUrl());
					String imagUrl=homeChannel.getImageUrl();
					if(StringUtils.isNotEmpty(imagUrl)){
						if(imagUrl.startsWith("htm")){
							map.put("imgUrl", urlAdapterVar.getImgUrl()+imagUrl);//快照
						}else{
							map.put("imgUrl", imagUrl);//问题截图
						}
					}else{
						map.put("imgUrl", "");//快照
					}
					
					returnList.add(map);
				}
				resultMap.put("returnList", returnList);
			}
			
		 /*  SecurityHomeChannelRequest homeRequest=new SecurityHomeChannelRequest();
		   homeRequest.setSiteCode(siteCode);
		   homeRequest.setScanDate(scanDate);
		   homeRequest.setSelectType(selectType);
		   homeRequest.setFlag(false);
		   
		   int pageTotal=securityHomeChannelServiceImpl.queryCount(homeRequest);
		   
		   homeRequest.setPageNo(Integer.valueOf(pageNum));
		   homeRequest.setPageSize(Integer.valueOf(pageSize));
			//设置排序字段
			List<QueryOrder> queryOrderList=new ArrayList<QueryOrder>();
			QueryOrder siteQueryOrder=new QueryOrder("not_update_num",QueryOrderType.DESC);
			queryOrderList.add(siteQueryOrder);
			homeRequest.setQueryOrderList(queryOrderList);
		   
		   List<SecurityHomeChannel>  homeChannelList=securityHomeChannelServiceImpl.queryList(homeRequest);
			if(homeChannelList!=null && homeChannelList.size()>0){
				List<Map<String, Object>> returnList=new ArrayList<Map<String,Object>>();
				for (SecurityHomeChannel homeChannel : homeChannelList) {
					Map<String, Object> map=new HashMap<String, Object>();
					
					String channelTypeName="";
					DicChannel dicChannel=dicChannelServiceImpl.get(homeChannel.getChannelType());
					if(dicChannel!=null && !"".equals(dicChannel)){
						channelTypeName=dicChannel.getChannelName();
					}
					map.put("channelTypeName", channelTypeName);
					map.put("channelName", homeChannel.getName()!=null?homeChannel.getName():"");
					map.put("scanDate",scanDate);
					map.put("updateTime",homeChannel.getModifyDate()!=null?homeChannel.getModifyDate():0);
					map.put("notUpdateNum", homeChannel.getNotUpdateNum()!=null?homeChannel.getNotUpdateNum():0);
					//initMap.put("imageUrl", urlAdapterVar.getImgUrl()+homeChannel.getImageUrl());
					String imagUrl=homeChannel.getImageUrl();
					if(StringUtils.isNotEmpty(imagUrl)){
						if(imagUrl.startsWith("htm")){
							map.put("imgUrl", urlAdapterVar.getImgUrl()+imagUrl);//快照
						}else{
							map.put("imgUrl", imagUrl);//问题截图
						}
					}else{
						map.put("imgUrl", "");//快照
					}
					
					returnList.add(map);
				}
				resultMap.put("returnList", returnList);
				resultMap.put("pageTotal", pageTotal);
			}*/
	    	writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description: 栏目更新不及时--填报单位
	 * @author cuichx --- 2017-3-31下午6:17:10     
	 * @return
	 */
	public String securityChannelTbIndex(){
		initMap=new HashMap<String, Object>();
		String siteCode=request.getParameter("siteCode");//网站标识码
		String notUpdateNum=request.getParameter("notUpdateNum");//更新不及时记录
		String siteName=request.getParameter("siteName");//网站名称
		logger.info("securityChannelTbIndex siteCode:"+siteCode+"  notUpdateNum："+notUpdateNum
				+"  scanDate："+scanDate+"  siteName:"+siteName);
		
		if(StringUtils.isEmpty(notUpdateNum)){
			List<DetectionResult>  dtList=detectionResultList(siteCode, scanDate);
			if(dtList!=null && dtList.size()>0){
				notUpdateNum=String.valueOf(dtList.get(0).getSecurityChannel());
			}else{
				notUpdateNum="0";
			}
			
		}
		initMap.put("siteCode", siteCode);
		initMap.put("notUpdateNum", notUpdateNum);
		initMap.put("scanDate", scanDate);
		initMap.put("siteName", siteName);
		
		return "newWeiXin";
	}
	
	
	/**
	 * @Description: 栏目更新不及时统计数据---组织单位
	 * @author cuichx --- 2017-3-31下午3:56:37
	 */
	public void getSecurityChannelOrgData(){
		String orgSiteCode=request.getParameter("siteCode");//网站标识码
		String checkType=request.getParameter("checkType");//组织机构级别
		String pageNum=request.getParameter("pageNum");//当前页数
		String pageSize=request.getParameter("pageSize");//每个大小
		
		logger.info("getSecurityChannelOrgData orgSiteCode:"+orgSiteCode+"  checkType："+checkType+"  scanDate:"+scanDate);
		try {
			//获取站点标识码集合
			HashMap<String, Object> paraMap=new HashMap<String, Object>();
			paraMap.put("orgSiteCode", orgSiteCode);
			if(!"0".equals(checkType)){
				paraMap.put("siteType", checkType);
			}
			paraMap.put("isExp", 1);
			paraMap.put("isLink", 1);
			paraMap.put("scanDate", scanDate);
			paraMap.put("flag", "false");//栏目更新量
			paraMap.put("selectType", 4);//组织单位统计所有类型
			
			int pageTotal=securityHomeChannelServiceImpl.queryNumByTypeCount(paraMap);
			
			Integer begin=(Integer.valueOf(pageNum)-1)*Integer.valueOf(pageSize);
			Integer end=Integer.valueOf(pageSize);
			paraMap.put("begin", begin);
			paraMap.put("end", end);
			List<SecurityHomeChannelRequest>  homeChannelList=securityHomeChannelServiceImpl.queryNumByType(paraMap);
			if(homeChannelList!=null && homeChannelList.size()>0){
				List<Map<String, Object>> returnList=new ArrayList<Map<String,Object>>();
				for (SecurityHomeChannelRequest homeChannelRequest : homeChannelList) {
					Map<String, Object> map=new HashMap<String, Object>();
					map.put("siteCode", homeChannelRequest.getSiteCode()!=null?homeChannelRequest.getSiteCode():"");
					map.put("siteName", homeChannelRequest.getSiteName()!=null?homeChannelRequest.getSiteName():"");
					map.put("notUpdateNum", homeChannelRequest.getNotUpSiteNum()!=null?homeChannelRequest.getNotUpSiteNum():0);
					
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
	 * @Description: 栏目更新不及时--组织单位
	 * @author cuichx --- 2017-3-31下午3:53:09     
	 * @return
	 */
	public String securityChannelOrgIndex(){
		initMap=new HashMap<String, Object>();
		String siteCode=request.getParameter("siteCode");//网站标识码
		String checkType=request.getParameter("checkType");//组织机构级别
		String securityChannel=request.getParameter("securityChannel");//栏目更新不及时量
		String securityChannelSite=request.getParameter("securityChannelSite");//栏目更新不及时分布站点
		logger.info("updateChannelOrgIndex siteCode:"+siteCode+"  checkType："+checkType
				+"  securityChannel："+securityChannel
				+"  securityChannelSite："+securityChannelSite);

		initMap.put("siteCode", siteCode);
		initMap.put("checkType", checkType);
		initMap.put("securityChannel", securityChannel);
		initMap.put("securityChannelSite", securityChannelSite);
		initMap.put("scanDate", scanDate);
		
		return "newWeiXin";
	}
	
	
	/**
	 * @Description: 栏目更新量数据---填报单位
	 * @author cuichx --- 2017-3-28下午3:10:46
	 */
	public void getUpdateChannelTbData(){
		
		resultMap=new HashMap<String, Object>();
		String siteCode=request.getParameter("siteCode");//网站标识码
		String pageNum=request.getParameter("pageNum");//当前页数
		String pageSize=request.getParameter("pageSize");//每个大小
		
		logger.info("getUpdateChannelTbData siteCode:"+siteCode+"  scanDate："+scanDate);
		try {
			UpdateChannelDetailRequest channelRequest=new UpdateChannelDetailRequest();
			channelRequest.setSiteCode(siteCode);
			channelRequest.setScanDate(scanDate);
			int pageTotal=updateChannelDetailServiceImpl.queryCount(channelRequest);
			channelRequest.setPageNo(Integer.valueOf(pageNum));
			channelRequest.setPageSize(Integer.valueOf(pageSize));
			
			//设置排序字段
			List<QueryOrder> queryOrderList=new ArrayList<QueryOrder>();
			QueryOrder siteQueryOrder=new QueryOrder("last_time",QueryOrderType.ASC);
			queryOrderList.add(siteQueryOrder);
			channelRequest.setQueryOrderList(queryOrderList);
			
			List<UpdateChannelDetail>  channelList=updateChannelDetailServiceImpl.queryList(channelRequest);
			if(channelList!=null && channelList.size()>0){
				List<Map<String, Object>> returnList=new ArrayList<Map<String,Object>>();
				for (UpdateChannelDetail updateChannelDetail : channelList) {
					Map<String, Object> map=new HashMap<String, Object>();
					map.put("channelName", updateChannelDetail.getChannelName());
					map.put("url", updateChannelDetail.getUrl());
					String dicChannelName="";
					DicChannel  dicChannel=dicChannelServiceImpl.get(updateChannelDetail.getDicChannelId());
					if(dicChannel!=null &&  !"".equals(dicChannel)){
						dicChannelName=dicChannel.getChannelName();
					}
					map.put("dicChannelName",dicChannelName);
					map.put("title", updateChannelDetail.getTitle());
					
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
	 * @Description:栏目更新量 --填报单位
	 * @author cuichx --- 2017-3-28下午1:32:05
	 */
	public String updateChannelTbIndex(){
		initMap=new HashMap<String, Object>();
		String siteCode=request.getParameter("siteCode");//网站标识码
		String updateChannel=request.getParameter("updateChannel");//栏目更新量
		String siteName=request.getParameter("siteName");//网站名称
		logger.info("updateChannelTbIndex siteCode:"+siteCode+"  updateChannel："+updateChannel
				+"  scanDate："+scanDate+"  siteName:"+siteName);
		
		initMap.put("siteCode", siteCode);
		initMap.put("updateChannel", updateChannel);
		initMap.put("scanDate", scanDate);
		initMap.put("siteName", siteName);
		
		return "newWeiXin";
	}
	
	/**
	 * @Description: 栏目更新量数据---组织单位
	 * @author cuichx --- 2017-3-28下午3:10:46
	 */
	public void getUpdateChannelOrgData(){
		String orgSiteCode=request.getParameter("siteCode");//网站标识码
		String checkType=request.getParameter("checkType");//组织机构级别
		String pageNum=request.getParameter("pageNum");//当前页数
		String pageSize=request.getParameter("pageSize");//每个大小
		
		logger.info("getUpdateChannelOrgData orgSiteCode:"+orgSiteCode+"  checkType："+checkType+"  scanDate:"+scanDate);
		try {
			//获取站点标识码集合
			HashMap<String, Object> paraMap=new HashMap<String, Object>();
			paraMap.put("orgSiteCode", orgSiteCode);
			if(!"0".equals(checkType)){
				paraMap.put("siteType", checkType);
			}
			paraMap.put("isExp", 1);
			paraMap.put("isLink", 1);
			paraMap.put("scanDate", scanDate);
			paraMap.put("type", 1);//栏目更新量
			paraMap.put("updateNum", 1);
			
			int pageTotal=updateContentCountServiceImpl.getUpdateHomeListCount(paraMap);
			
			Integer begin=(Integer.valueOf(pageNum)-1)*Integer.valueOf(pageSize);
			Integer end=Integer.valueOf(pageSize);
			paraMap.put("begin", begin);
			paraMap.put("end", end);
			List<UpdateContentCountRequest> homeList= updateContentCountServiceImpl.getUpdateHomeList(paraMap);
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
					map.put("updateNum", homeRequest.getUpdateNum());
					
					list.add(map);
					
				}
				resultMap.put("list", list);
				resultMap.put("pageTotal", pageTotal);
			}
	    	writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * @Description:栏目更新量---组织单位
	 * @author cuichx --- 2017-3-28下午1:32:05
	 */
	public String updateChannelOrgIndex(){
		initMap=new HashMap<String, Object>();
		String siteCode=request.getParameter("siteCode");//网站标识码
		String checkType=request.getParameter("checkType");//组织机构级别
		String updateChannel=request.getParameter("updateChannel");//栏目更新量
		String updateChannelSite=request.getParameter("updateChannelSite");//栏目更新分布站点
		logger.info("updateChannelOrgIndex siteCode:"+siteCode+"  checkType："+checkType
				+"  updateChannel："+updateChannel
				+"  updateChannelSite："+updateChannelSite);
		
		initMap.put("siteCode", siteCode);
		initMap.put("checkType", checkType);
		initMap.put("updateChannel", updateChannel);
		initMap.put("updateChannelSite", updateChannelSite);
		initMap.put("scanDate",scanDate);
		
		return "newWeiXin";
	}
	
	
	/**
	 * @Description: 业务系统不连通数据---填报单位
	 * @author cuichx --- 2017-3-29下午5:11:35
	 */
	public void getConnBusinessTbData(){
		String siteCode=request.getParameter("siteCode");
		String pageNum=request.getParameter("pageNum");//当前页数
		String pageSize=request.getParameter("pageSize");//每个大小
		
		Map<String, Object> paraMap=new HashMap<String, Object>();
		paraMap.put("siteCode", siteCode);
		paraMap.put("scanDate", scanDate);
		paraMap.put("groupBy", "url");
		try{
			
			int pageTotal=connectionBusinessDetailServiceImpl.queryListByGroupCount(paraMap);
			Integer begin=(Integer.valueOf(pageNum)-1)*Integer.valueOf(pageSize);
			Integer end=Integer.valueOf(pageSize);
			paraMap.put("begin", begin);
			paraMap.put("end", end);

			List<ConnectionBusinessDetailRequest>  channelList=connectionBusinessDetailServiceImpl.queryListByGroup(paraMap);
			if(channelList!=null && channelList.size()>0){
				List<Map<String, Object>> returnList=new ArrayList<Map<String,Object>>();
				for (ConnectionBusinessDetailRequest connectionBusinessDetail : channelList) {
					Map<String, Object> map=new HashMap<String, Object>();
					map.put("systemName", connectionBusinessDetail.getSystemName()!=null?connectionBusinessDetail.getSystemName():"");
					map.put("url", connectionBusinessDetail.getUrl()!=null?connectionBusinessDetail.getUrl():"");
					map.put("scanTime", connectionBusinessDetail.getScanTime2()!=null?connectionBusinessDetail.getScanTime2():"");
					
					String questionDescribe="";
					if(connectionBusinessDetail.getQuestionCode()!=null){
						questionDescribe=connectionBusinessDetail.getQuestionCode()
								+QuestionTypeWeiXin.getNameByCode(connectionBusinessDetail.getQuestionCode());
					}
					map.put("questionDescribe", questionDescribe);
					
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
	 * @Description: 业务系统不连通---填报单位
	 * @author cuichx --- 2017-3-30下午7:01:51     
	 * @return
	 */
	public String connBusinessTbIndex(){
		
		initMap=new HashMap<String, Object>();
		String siteCode=request.getParameter("siteCode");
		String connBusiness=request.getParameter("connBusiness");
		String siteName=request.getParameter("siteName");
		
		initMap.put("siteCode", siteCode);
		initMap.put("connBusiness", connBusiness);
		initMap.put("siteName", siteName);
		initMap.put("scanDate", scanDate);
		
		return "newWeiXin";
	}
	
	/**
	 * @Description: 业务系统不连通--组织单位
	 * @author cuichx --- 2017-3-29下午5:11:35
	 */
	public void getConnBusinessOrgData(){
		String orgSiteCode=request.getParameter("siteCode");
		String checkType=request.getParameter("checkType");
		String pageNum=request.getParameter("pageNum");//当前页数
		String pageSize=request.getParameter("pageSize");//每个大小
		
		HashMap<String, Object> paraMap=new HashMap<String, Object>();
		paraMap.put("orgSiteCode", orgSiteCode);
		if(StringUtils.isNotEmpty(checkType) && !"0".equals(checkType)){
			paraMap.put("siteType", checkType);
		}
		paraMap.put("isExp", 1);
		paraMap.put("isLink", 1);
		paraMap.put("scanDate", scanDate);
		paraMap.put("type", 2);
		paraMap.put("error", 0);
		try {
			int pageTotal= connectionAllServiceImpl.getwebConnectedList2Count(paraMap);
			
			Integer begin=(Integer.valueOf(pageNum)-1)*Integer.valueOf(pageSize);
			Integer end=Integer.valueOf(pageSize);
			paraMap.put("begin", begin);
			paraMap.put("end", end);
			List<ConnectionAllRequest> connChannelList= connectionAllServiceImpl.getwebConnectedList2(paraMap);
			if(connChannelList!=null && connChannelList.size()>0){
				List<Map<String, Object>> returnList=new ArrayList<Map<String,Object>>();
				for (ConnectionAllRequest connectionAllRequest : connChannelList) {
					Map<String, Object> map=new HashMap<String, Object>();
					map.put("siteCode", connectionAllRequest.getSiteCode()!=null?connectionAllRequest.getSiteCode():"");
					map.put("siteName", connectionAllRequest.getSiteName()!=null?connectionAllRequest.getSiteName():"");
					map.put("errorNum", connectionAllRequest.getChannelSum()!=null?connectionAllRequest.getChannelSum():0);
					
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
	 * @Description: 业务系统不连通---组织单位
	 * @author cuichx --- 2017-3-30下午7:01:51     
	 * @return
	 */
	public String connBusinessOrgIndex(){
		
		initMap=new HashMap<String, Object>();
		String siteCode=request.getParameter("siteCode");
		String checkType=request.getParameter("checkType");
		String connBusiness=request.getParameter("connBusiness");
		String connBusinessSite=request.getParameter("connBusinessSite");
		
		initMap.put("siteCode", siteCode);
		initMap.put("checkType", checkType);
		initMap.put("connBusiness", connBusiness);
		initMap.put("connBusinessSite", connBusinessSite);
		initMap.put("scanDate", scanDate);
		
		return "newWeiXin";
	}
	
	/**
	 * @Description: 关键栏目不连通获取页面数据-填报单位
	 * @author cuichx --- 2017-3-31上午9:49:22
	 */
	public void getConnChannelTbData(){
		String siteCode=request.getParameter("siteCode");
		String pageNum=request.getParameter("pageNum");//当前页数
		String pageSize=request.getParameter("pageSize");//每个大小
		Map<String, Object> paraMap=new HashMap<String, Object>();
		paraMap.put("siteCode", siteCode);
		paraMap.put("scanDate", scanDate);
		paraMap.put("groupBy", "url");

		try{
			int pageTotal=connectionChannelDetailServiceImpl.queryListByGroupCount(paraMap);
			
			Integer begin=(Integer.valueOf(pageNum)-1)*Integer.valueOf(pageSize);
			Integer end=Integer.valueOf(pageSize);
			paraMap.put("begin", begin);
			paraMap.put("end", end);

			List<ConnectionChannelDetailRequest>  channelList=connectionChannelDetailServiceImpl.queryListByGroup(paraMap);
			if(channelList!=null && channelList.size()>0){
				List<Map<String, Object>> returnList=new ArrayList<Map<String,Object>>();
				for (ConnectionChannelDetailRequest connectionChannelDetail : channelList) {
					Map<String, Object> map=new HashMap<String, Object>();
					map.put("channelName", connectionChannelDetail.getChannelName()!=null?connectionChannelDetail.getChannelName():"");
					map.put("url", connectionChannelDetail.getUrl()!=null?connectionChannelDetail.getUrl():"");
					map.put("scanTime", connectionChannelDetail.getScanTime2()!=null?connectionChannelDetail.getScanTime2():"");
					String questionDescribe="";
					if(connectionChannelDetail.getQuestionCode()!=null){
						questionDescribe=connectionChannelDetail.getQuestionCode()
								+QuestionTypeWeiXin.getNameByCode(connectionChannelDetail.getQuestionCode());
					}
					map.put("questionDescribe", questionDescribe);
					
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
	 * @Description: 关键栏目不连通---填报单位
	 * @author cuichx --- 2017-3-30下午7:01:51     
	 * @return
	 */
	public String connChannelTbIndex(){
		
		initMap=new HashMap<String, Object>();
		String siteCode=request.getParameter("siteCode");
		String connChannel=request.getParameter("connChannel");
		String siteName=request.getParameter("siteName");
		
		initMap.put("siteCode", siteCode);
		initMap.put("connChannel", connChannel);
		initMap.put("siteName", siteName);
		initMap.put("scanDate", scanDate);
		
		return "newWeiXin";
	}

	/**
	 * @Description: 关键栏目不连通--组织单位
	 * @author cuichx --- 2017-3-29下午5:11:35
	 */
	public void getConnChannelOrgData(){
		String orgSiteCode=request.getParameter("siteCode");
		String checkType=request.getParameter("checkType");
		String pageNum=request.getParameter("pageNum");//当前页数
		String pageSize=request.getParameter("pageSize");//每个大小
		
		HashMap<String, Object> paraMap=new HashMap<String, Object>();
		paraMap.put("orgSiteCode", orgSiteCode);
		if(StringUtils.isNotEmpty(checkType) && !"0".equals(checkType)){
			paraMap.put("siteType", checkType);
		}
		paraMap.put("isExp", 1);
		paraMap.put("isLink", 1);
		paraMap.put("scanDate", scanDate);
		paraMap.put("type", 3);
		paraMap.put("error", 0);
		
		try {
			int pageTotal=connectionAllServiceImpl.getwebConnectedList2Count(paraMap);
			
			Integer begin=(Integer.valueOf(pageNum)-1)*Integer.valueOf(pageSize);
			Integer end=Integer.valueOf(pageSize);
			paraMap.put("begin", begin);
			paraMap.put("end", end);
			
			List<ConnectionAllRequest> connChannelList= connectionAllServiceImpl.getwebConnectedList2(paraMap);
			if(connChannelList!=null && connChannelList.size()>0){
				List<Map<String, Object>> returnList=new ArrayList<Map<String,Object>>();
				for (ConnectionAllRequest connectionAllRequest : connChannelList) {
					Map<String, Object> map=new HashMap<String, Object>();
					map.put("siteCode", connectionAllRequest.getSiteCode()!=null?connectionAllRequest.getSiteCode():"");
					map.put("siteName", connectionAllRequest.getSiteName()!=null?connectionAllRequest.getSiteName():"");
					map.put("errorNum", connectionAllRequest.getChannelSum()!=null?connectionAllRequest.getChannelSum():0);
					
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
	 * @Description:关键栏目不连通---组织单位 
	 * @author cuichx --- 2017-3-29下午5:02:58     
	 * @return
	 */
	public String connChannelOrgIndex(){
		
		initMap=new HashMap<String, Object>();
		String siteCode=request.getParameter("siteCode");
		String checkType=request.getParameter("checkType");
		String connChannel=request.getParameter("connChannel");
		String connChannelSite=request.getParameter("connChannelSite");
		
		initMap.put("siteCode", siteCode);
		initMap.put("checkType", checkType);
		initMap.put("connChannel", connChannel);
		initMap.put("connChannelSite", connChannelSite);
		initMap.put("scanDate", scanDate);
		
		return "newWeiXin";
	}
	/**
	 * 
	 * @Description: url servicePeroidId 排重
	 * @author: sunjy --- 2016年9月23日下午6:03:29
	 * @return
	 * @throws Exception
	 */
	public List<SecurityHomeChannel> removeDuplicate(List<SecurityHomeChannel> list){
		List<SecurityHomeChannel> channelList=new ArrayList<SecurityHomeChannel>();
		
		for(int i=0;i<list.size();i++){
			if(i==0){//先添加一条数据
				channelList.add(list.get(i));
			}else{
				boolean flag=true;
				for(int j=0;j<channelList.size();j++){
					String newUrl=channelList.get(j).getUrl();
					String oldUrl=list.get(i).getUrl();
					if(newUrl.equals(oldUrl)){
						flag=false;
						break;
					}
				}
				if(flag){
					channelList.add(list.get(i));
				}
			}
		}
		return channelList;
	}
	/**
	 * 获取收费版统计数据（填报单位）
	 * @param siteCode
	 * @param scanDate
	 * @return
	 */
	public List<DetectionResult>  detectionResultList(String siteCode,String scanDate){
		DetectionResultRequest dtRequest=new DetectionResultRequest();
		dtRequest.setSiteCode(siteCode);
		dtRequest.setScanDate(scanDate);
		List<DetectionResult>  dtList=detectionResultServiceImpl.queryList(dtRequest);
		if(dtList!=null && dtList.size()>0){
			return dtList;
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
