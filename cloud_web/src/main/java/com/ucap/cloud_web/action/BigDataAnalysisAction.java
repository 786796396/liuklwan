package com.ucap.cloud_web.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.publics.util.page.PageVo;
import com.publics.util.page.QueryOrder;
import com.publics.util.page.QueryOrderType;
import com.publics.util.utils.DateUtils;
import com.publics.util.utils.StringUtils;
import com.ucap.cloud_web.bizService.DatabaseBizService;
import com.ucap.cloud_web.constant.BigDataDailyRadioType;
import com.ucap.cloud_web.constant.UpdateStatusType;
import com.ucap.cloud_web.dto.BigAuthDetailRequest;
import com.ucap.cloud_web.dto.BigDataDailyRequest;
import com.ucap.cloud_web.dto.BigOrgDailyRequest;
import com.ucap.cloud_web.dto.DatabaseOrgInfoRequest;
import com.ucap.cloud_web.dto.DatabaseTreeInfoRequest;
import com.ucap.cloud_web.dto.UpdateContentCountRequest;
import com.ucap.cloud_web.dto.UpdateHomeDetailRequest;
import com.ucap.cloud_web.dtoResponse.BigDataDailyResponse;
import com.ucap.cloud_web.dtoResponse.BigOrgDailyResponse;
import com.ucap.cloud_web.entity.BigAuthDetail;
import com.ucap.cloud_web.entity.DatabaseInfo;
import com.ucap.cloud_web.entity.DatabaseOrgInfo;
import com.ucap.cloud_web.entity.DatabaseTreeInfo;
import com.ucap.cloud_web.entity.Result;
import com.ucap.cloud_web.entity.UpdateContentCount;
import com.ucap.cloud_web.entity.UpdateHomeDetail;
import com.ucap.cloud_web.service.IBigAuthDetailService;
import com.ucap.cloud_web.service.IBigDataAnalysisService;
import com.ucap.cloud_web.service.IBigDataDailyService;
import com.ucap.cloud_web.service.IBigOrgDailyService;
import com.ucap.cloud_web.service.IDatabaseInfoService;
import com.ucap.cloud_web.service.IDatabaseOrgInfoService;
import com.ucap.cloud_web.service.IDatabaseTreeInfoService;
import com.ucap.cloud_web.service.IUpdateContentCountService;
import com.ucap.cloud_web.service.IUpdateHomeDetailService;
import com.ucap.cloud_web.util.Des;
import com.ucap.cloud_web.util.ExportExcel;


/**
 * 描述：数据分析 
 * 包：com.ucap.cloud_web.action
 * 文件名称：DataAnalysisAction
 * 公司名称：开普互联
 * 作者：liujc@ucap.com.cn
 * 时间：2016-5-19上午10:55:33 
 * @version V1.0
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class BigDataAnalysisAction extends BaseAction{
	
	private static Logger logger = Logger.getLogger(BigDataAnalysisAction.class);
	@Autowired
	private IDatabaseInfoService databaseInfoServiceImpl;
	@Autowired
	private IDatabaseOrgInfoService databaseOrgInfoServiceImpl;
	@Autowired
	private IBigDataAnalysisService bigDataAnalysisServiceImpl;
	@Autowired
	private IBigAuthDetailService bigAuthDetailServiceImpl;
	@Autowired
	private IUpdateContentCountService updateContentCountServiceImpl;
	@Autowired
	private IUpdateHomeDetailService updateHomeDetailServiceImpl;
	@Autowired
	private IBigOrgDailyService bigOrgDailyServiceImpl;
	@Autowired
	private IBigDataDailyService bigDataDailyServiceImpl;
	@Autowired
	private DatabaseBizService databaseBizServiceImpl;
	@Autowired
	private IDatabaseTreeInfoService databaseTreeInfoServiceImpl;
	
	private Map<String, Object> rsMap=new HashMap<String, Object>();
	private String siteName;//组织单位名称
	private String returnType;//0返回null
	private Map<String, Object> chartMap=new HashMap<String, Object>();
	private String desCode;
	private String level;
	private String orgSiteCode;
	private String tabId;
	private Integer isBm;//0:地方 1：部委
	private Integer dayNum;//天数
	private String contentType;//0图表 1列表
	private String pageSizeVal;//显示几条数据  -1全部 1前20条   2后20条
	private String radioVal;//选中哪个图表的单选按钮 
	public final String CACHE_DATABASEINFO = "databaseInfo";
	public final String CACHE_RESULT = "result";
	List<String> xlist=new ArrayList<String>();
	List<String> ylist=new ArrayList<String>();
	
	/**
	 * @Title: 最后更新日期 未知提示框  里的  最新一次更新时间 未知原因 
	 * @Description:
	 * @author liujc@ucap.com.cn	2016-8-24下午4:34:25
	 */
	public void findLastDate(){
		try {
			Map<String,Object> map = new HashMap<String, Object>();
			String siteCode = request.getParameter("siteCode");
			//查询最新一次的更新时间
			UpdateHomeDetailRequest request = new UpdateHomeDetailRequest();
			ArrayList<QueryOrder> queryOrderList = new ArrayList<QueryOrder>();
			QueryOrder queryOrder = new QueryOrder("update_time", QueryOrderType.DESC);
			queryOrderList.add(queryOrder);
			request.setQueryOrderList(queryOrderList);
			request.setPageNo(1);
			request.setPageSize(1);
			request.setSiteCode(siteCode);
			PageVo<UpdateHomeDetail> pageVo = updateHomeDetailServiceImpl.query(request);
			List<UpdateHomeDetail> list = pageVo.getData();
			if(list.size() == 1 ){
				String upateTime=list.get(0).getUpdateTime();//最后正常监测更新时间
				String noupdateday=DateUtils.getTwoDay(DateUtils.getTodayStandardStr(), upateTime);
				String updatestatus="<span class='sort-num'>正常更新</span>";
				if(Integer.valueOf(noupdateday)>14){
					updatestatus="<span class='sort-num' style='color:red'>超过两周</span>";
				}
				map.put("upateTime", list.get(0).getUpdateTime());//最后正常监测更新时间
				map.put("updatestatus", updatestatus);//首页不更新
				map.put("noupdateday", noupdateday);//未更新天数
			}else{
				map.put("upateTime", "未知");//最后正常监测更新时间
				map.put("updatestatus", "未知");//首页不更新
				map.put("noupdateday", "未知");//未更新天数
			}
			// 查询不检测的原因
//			DatabaseInfoRequest dRequest = new DatabaseInfoRequest();
//			dRequest.setSiteCode(siteCode);
//			Map<String,Object> pMap = new HashMap<String, Object>();
//			pMap.put("siteCode", siteCode);
//			List<DatabaseInfo> listInfo = databaseInfoServiceImpl.queryIsScan(pMap);
//			if(listInfo.size()>0 && listInfo.get(0).getName() != null){
//				map.put("name","原因 :"+ listInfo.get(0).getName());
//			}else{
//				map.put("name", "原因 : 未知");
//			}
			map.put("name", "原因 : 网站连不通");
			writerPrint(JSONObject.fromObject(map).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * @Title:查询 siteCode加密 信息 
	 * @Description:
	 * @author liujc@ucap.com.cn	2016-8-24上午9:05:44
	 */
	public void queryBigAuthDetail(){
		try {
			BigAuthDetailRequest request = new BigAuthDetailRequest();
			request.setOrgSiteCode(orgSiteCode);
			List<BigAuthDetail> existCnt = bigAuthDetailServiceImpl.queryList(request);
			writerPrint(JSONArray.fromObject(existCnt).toString());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * 描述:验证加密  外部引用日常监测统计
	 * 
	 * 作者：liujc@ucap.com.cn	2016-7-6上午11:42:12
	 * @param desCode
	 * @return
	 */
	public String bigDataAnalysisOut(){
		
		logger.info("BigDataAnalysisAction  BDPermissionValidate  DESCODE==="+desCode);
		String flag=null;
		try {
			if(StringUtils.isEmpty(desCode)){
				//失败
				flag="bigDataError";
			}else{
				String orgSiteCode = Des.strDec(desCode);
				if(StringUtils.isEmpty(orgSiteCode)){
					//失败
					flag="bigDataError";
				}else{
					String tabIdStr="";
					int tabHide = 1;// 1 bm0100 ;2有下级战群 3 没有下级战群；
					//是否存在下级组织单位 如果有返回的是siteCode的id  没有返回0
					DatabaseTreeInfo isNextOrg = databaseBizServiceImpl.isNextOrg(orgSiteCode);
					//  非null有下级战群  null 没有下级战群
					if(isNextOrg != null){
						if(orgSiteCode.equals("bm0100")){
							tabHide=0;//bm0100显示5个tab标签
							tabIdStr="0,1,2,3,4";
						}else{
							if(isNextOrg.getIsBm()==0){
								tabHide=1;//显示 下级地方站群  本级站点 下级地方门户
								tabIdStr="0,1,2";//站群
							}else if(isNextOrg.getIsBm()==1){
								tabHide=2;//显示 部委下级地方站群  本级站点 部委下级地方门户
								tabIdStr="2,3,4";//部委
							}
						}
					}else{
						tabHide=3;//本级站点
						tabIdStr="2";//只显示本级站点
					}
					boolean tabIdFlag=false;
					if(StringUtils.isNotEmpty(tabId)){
						if(tabIdStr.contains(tabId)){
							tabIdFlag=true;
							rsMap.put("tabIdOut", tabId);
						}else{
							flag="bigDataError";
						}
					}else{
						tabIdFlag=true;
						rsMap.put("tabIdOut", "");
					}
					//页面传送过来tabid 在限制的tab范围内
					if(tabIdFlag){
						BigAuthDetailRequest bdpdRequest = new BigAuthDetailRequest();
						bdpdRequest.setOrgSiteCode(orgSiteCode);
						bdpdRequest.setDesCode(desCode);
						bdpdRequest.setStatus(1);//1有效， 2无效
						bdpdRequest.setCurDate(DateUtils.getNextDay(new Date(), 0));
						int qCount = bigAuthDetailServiceImpl.queryCount(bdpdRequest);
						if(qCount < 1){
							//失败
							flag="bigDataError";
						}else{
							//成功
							flag="success";
							DatabaseTreeInfoRequest request=new DatabaseTreeInfoRequest();
							request.setSiteCode(orgSiteCode);
							Integer loginLevel=null;
							List<DatabaseTreeInfo>  levelList=databaseTreeInfoServiceImpl.queryList(request);
							if(levelList.size()>0){
								loginLevel=levelList.get(0).getLevel();
							}
							//查询组织单位名字
							DatabaseOrgInfoRequest databaseOrgInfoRequest=new DatabaseOrgInfoRequest();
							databaseOrgInfoRequest.setStieCode(orgSiteCode);
							List<DatabaseOrgInfo> querylist=databaseOrgInfoServiceImpl.queryList(databaseOrgInfoRequest);
							if(querylist.size()>0){
								siteName=querylist.get(0).getName();
							}
							rsMap.put("loginLevel", loginLevel);
							rsMap.put("level", loginLevel);
							rsMap.put("siteName", siteName);
							rsMap.put("orgSiteCode", orgSiteCode);//加密解析出来的siteCode
							rsMap.put("tabHide", tabHide);
						}
					}
				}
			}
		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	/**
	 * @描述:获取当前组织单位的 level siteName sitecode
	 * @作者:liujc@ucap.com.cn
	 * @时间:2017-3-3下午2:26:55
	 */
	public void initData(){
		String orgSiteCode=request.getParameter("orgSiteCode");
		int tabHide = 1;// 1 bm0100 ;2有下级战群 3 没有下级战群；
		//是否存在下级组织单位 如果有返回的是siteCode的id  没有返回0
		DatabaseTreeInfo isNextOrg = databaseBizServiceImpl.isNextOrg(orgSiteCode);
		//  非null有下级战群  null 没有下级战群
		if(isNextOrg != null){
			if(orgSiteCode.equals("bm0100")){
				tabHide=0;//bm0100显示5个tab标签
			}else{
				if(isNextOrg.getIsBm()==0){
					tabHide=1;//显示 下级地方站群  本级站点 下级地方门户
				}else if(isNextOrg.getIsBm()==1){
					tabHide=2;//显示 部委下级地方站群  本级站点 部委下级地方门户
				}
			}
		}else{
			tabHide=3;//本级站点
		}
	
		if(StringUtils.isNotEmpty(orgSiteCode)){
			DatabaseOrgInfoRequest databaseOrgInfoRequest=new DatabaseOrgInfoRequest();
			databaseOrgInfoRequest.setStieCode(orgSiteCode);
			List<DatabaseOrgInfo> querylist=databaseOrgInfoServiceImpl.queryList(databaseOrgInfoRequest);
			if(querylist.size()>0){
				siteName=querylist.get(0).getName();
			}
			DatabaseTreeInfoRequest request=new DatabaseTreeInfoRequest();
			request.setSiteCode(orgSiteCode);
			List<DatabaseTreeInfo>  levelList=databaseTreeInfoServiceImpl.queryList(request);
			if(levelList.size()>0){
				rsMap.put("level", levelList.get(0).getLevel());
			}
		}
//		logger.info("BigDataAnalysisAction bigDataAnalysis() tabHide:"+rsMap.get("tabHide")+" level:"+level+" orgSiteCode:"+orgSiteCode+" siteName:"+siteName);
		rsMap.put("tabHide", tabHide);
		rsMap.put("orgSiteCode", orgSiteCode);
		rsMap.put("siteName", siteName);
	}
	/**
	 * @描述:点击下一级的时候调用   面包屑的时候也调用
	 * @作者:liujc@ucap.com.cn
	 * @时间:2017-3-3下午2:28:17
	 */
	public void bigDataAnalysis(){
		initData();
		writerPrint(JSONObject.fromObject(rsMap).toString());
	}
	/**
	 * 
	 * @描述:获取下级站群数据
	 * @作者:liujc@ucap.com.cn
	 * @时间:2017-3-2上午10:45:47
	 */
	@SuppressWarnings("unchecked")
	public void getNextCityAndCountry(){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String scanDate=queryDate();
		try {
			int tabHide = 1;
			//是否存在下级组织单位 如果有返回的是siteCode的id  没有返回0
			DatabaseTreeInfo isNextOrg = databaseBizServiceImpl.isNextOrg(orgSiteCode);
			//  非null有下级战群  null 没有下级战群
			if(isNextOrg != null){
				BigOrgDailyRequest rRequest = new BigOrgDailyRequest();
				if(orgSiteCode.equals("bm0100")){
					tabHide=0;//bm0100显示5个tab标签
					rRequest.setIsBm(isBm);//bm0100  会展示5个tab  需要从页面区别部委还是地方
					if(isBm==0){
						tabHide=0;//显示 下级地方站群  本级站点 下级地方门户
					}else{
						tabHide=2;//显示 部委下级地方站群  本级站点 部委下级地方门户
					}
				}else{
					if(isNextOrg.getIsBm()==0){
						tabHide=1;//显示 下级地方站群  本级站点 下级地方门户
					}else if(isNextOrg.getIsBm()==1){
						tabHide=2;//显示 部委下级地方站群  本级站点 部委下级地方门户
					}
					rRequest.setIsBm(isNextOrg.getIsBm());
				}
				rRequest.setParentId(isNextOrg.getParentId());
				
				rRequest.setCountDay(scanDate);
				if("0".equals(contentType)){
					//图表
					Map<String, Object> orderMap=orderCommon();
					rRequest.setQueryOrderList((List<QueryOrder>) orderMap.get("queryOrderList"));
					rRequest.setOrderVal(String.valueOf(orderMap.get("orderVal")));
					if(orderMap.get("startIndex")!=null){
						rRequest.setStartIndex(0);
						rRequest.setPageSize(20);
					}
					
					
				}
				List<BigOrgDailyResponse> list = bigOrgDailyServiceImpl.getOrgData(rRequest);
				if(list!=null && list.size()>0){
					if("0".equals(contentType)){
						for(BigOrgDailyResponse bigOrgDailyResponse:list){
							String yName=bigOrgDailyResponse.getName();
							String xVal=bigOrgDailyResponse.getOrderVal();
							if(StringUtils.isEmpty(yName)){
								yName="";
							}
							if(StringUtils.isEmpty(xVal)){
								xVal="0";
							}
							xlist.add(xVal);
							ylist.add(yName);
						}
					}
				}else{
					tabHide=3;//只显示本级站点tab标签
				}
				resultMap.put("body", list);
			}else{
				//本级站点tab
				tabHide=3;//只显示本级站点tab标签
//				BigDataDailyRequest sRequest = new BigDataDailyRequest();
//				sRequest.setOrgSiteCode(orgSiteCode);
//				sRequest.setCountDay(scanDate);
//				if("0".equals(contentType)){
//					//图表
//					Map<String, Object> orderMap=orderCommon();
//					sRequest.setQueryOrderList((List<QueryOrder>) orderMap.get("queryOrderList"));
//					sRequest.setOrderVal(String.valueOf(orderMap.get("orderVal")));
//				}
//				List<BigDataDailyResponse> sList = bigDataDailyServiceImpl.queryNatives(sRequest);
//				for(BigDataDailyResponse bigDataDailyResponse:sList){
//					String yName=bigDataDailyResponse.getName();
//					String xVal=bigDataDailyResponse.getOrderVal();
//					if(StringUtils.isEmpty(yName)){
//						yName="";
//					}
//					if(StringUtils.isEmpty(xVal)){
//						xVal="0";
//					}
//					xlist.add(xVal);
//					ylist.add(yName);
//				}
//				
//				resultMap.put("body", sList);
			}
			resultMap.put("xlist", xlist);
			resultMap.put("ylist", ylist);
			resultMap.put("tabHide", tabHide);
			resultMap.put("level", level);
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			resultMap.put("errorMsg", "不存在下级站群统计数据");
			writerPrint(JSONObject.fromObject(resultMap).toString());
			e.printStackTrace();
		}
	}
	/**
	 * @描述:排序封装
	 * @作者:liujc@ucap.com.cn
	 * @时间:2017-3-7下午2:59:19
	 */
	public Map<String, Object> orderCommon(){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		ArrayList<QueryOrder> queryOrderList = new ArrayList<QueryOrder>();
		try {
			String orderVal=BigDataDailyRadioType.getNameByCode(radioVal);
			if(dayNum!=1 && dayNum!=null && StringUtils.isNotEmpty(orderVal)
				&& !"8".equals(radioVal)	){
				orderVal=orderVal+dayNum;
			}
			if(StringUtils.isNotEmpty(orderVal)){
				if(!"2".equals(pageSizeVal)){
					//前20条
					QueryOrder queryOrder = new QueryOrder(orderVal, QueryOrderType.DESC);
					queryOrderList.add(queryOrder);
				}else {
					//后20条
					QueryOrder queryOrder = new QueryOrder(orderVal, QueryOrderType.ASC);
					queryOrderList.add(queryOrder);
				}
			}
			//全部的时候不需要限制条数
			if("-1".equals(pageSizeVal)){
				resultMap.put("startIndex", null);
				resultMap.put("pageSize", null);
			}else{
				resultMap.put("startIndex", "0");
				resultMap.put("pageSize", "20");
			}
			if(queryOrderList.size()==0){
				resultMap.put("queryOrderList", null);
			}else{
				resultMap.put("queryOrderList", queryOrderList);
			}
			resultMap.put("orderVal", orderVal);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return resultMap;
	}




	/**
	 * @描述:下一级门户
	 * @作者:liujc@ucap.com.cn
	 * @时间:2017-3-2上午11:10:07
	 */
	@SuppressWarnings("unchecked")
	public void getNextSitesMH(){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String scanDate=queryDate();
//		int tabHide = 1;// 1 bm0100 ;2有下级战群 3 没有下级战群；
		try {
			//是否存在下级组织单位 如果有返回的是siteCode的Bean  没有返回null
			DatabaseTreeInfo isNextOrg = databaseBizServiceImpl.isNextOrg(orgSiteCode);
			if(isNextOrg != null){
				BigDataDailyRequest rRequest = new BigDataDailyRequest();
				if(orgSiteCode.equals("bm0100")){
					rRequest.setIsBm(isBm);//bm0100  会展示5个tab  需要从页面区别部委还是地方
				}else{
					rRequest.setIsBm(isNextOrg.getIsBm());
				}
				rRequest.setParentId(isNextOrg.getParentId());
				rRequest.setCountDay(scanDate);
				if("0".equals(contentType)){
					//图表
					Map<String, Object> orderMap=orderCommon();
					rRequest.setQueryOrderList((List<QueryOrder>) orderMap.get("queryOrderList"));
					rRequest.setOrderVal(String.valueOf(orderMap.get("orderVal")));
					if(orderMap.get("startIndex")!=null){
						rRequest.setStartIndex(0);
						rRequest.setPageSize(20);
					}
					//未更新天数 图表
					if("8".equals(radioVal)){
						rRequest.setNoupdatedayNo("-1");//图表排除掉 未更新天数等于-1的情况
					}
				}
				List<BigDataDailyResponse> list = bigDataDailyServiceImpl.queryOrganizations(rRequest);
				if(list.size()>0){
					if("0".equals(contentType)){
						for(BigDataDailyResponse bigDataDailyResponse:list){
							String yName=bigDataDailyResponse.getName();
							String xVal=bigDataDailyResponse.getOrderVal();
							if(StringUtils.isEmpty(yName)){
								yName="";
							}
							if(StringUtils.isEmpty(xVal)){
								xVal="0";
							}
							xlist.add(xVal);
							ylist.add(yName);
						}
					}
				}else{
					resultMap.put("errorMsg", "不存在下级门户网站的统计数据");
				}
				resultMap.put("xlist", xlist);
				resultMap.put("ylist", ylist);
				resultMap.put("body", list);
			}
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			// TODO: handle exception
			chartMap.put("errorMsg", "不存在下级门户网站的统计数据");
			writerPrint(JSONObject.fromObject(chartMap).toString());
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 
	 * @描述:本级站点
	 * @作者:liujc@ucap.com.cn
	 * @时间:2017-3-2上午11:06:07
	 */
	@SuppressWarnings("unchecked")
	public void getLocalSites(){
		
		Map<String, Object> resultMap=new HashMap<String, Object>();
		String scanDate=queryDate();
		try {
			int tabHide = 1;// 1 bm0100 ;2有下级战群 3 没有下级战群；
			//是否存在下级组织单位 如果有返回的是siteCode的id  没有返回0
//			DatabaseTreeInfo isNextOrg = databaseBizServiceImpl.isNextOrg(orgSiteCode);
			BigDataDailyRequest sRequest = new BigDataDailyRequest();
//			if(isNextOrg !=null){
//				if(orgSiteCode.equals("bm0100")){
//					tabHide=0;//bm0100显示5个tab标签
//				}else{
//					if(isNextOrg.getIsBm()==0){
//						tabHide=1;//显示 下级地方站群  本级站点 下级地方门户
//					}else if(isNextOrg.getIsBm()==1){
//						tabHide=2;//显示 部委下级地方站群  本级站点 部委下级地方门户
//					}
//				}
//			}
			
			sRequest.setOrgSiteCode(orgSiteCode);
			sRequest.setCountDay(scanDate);
			if("0".equals(contentType)){
				//图表
				Map<String, Object> orderMap=orderCommon();
				sRequest.setQueryOrderList((List<QueryOrder>) orderMap.get("queryOrderList"));
				sRequest.setOrderVal(String.valueOf(orderMap.get("orderVal")));
				if(orderMap.get("startIndex")!=null){
					sRequest.setStartIndex(0);
					sRequest.setPageSize(20);
				}
				//未更新天数 图表
				if("8".equals(radioVal)){
					sRequest.setNoupdatedayNo("-1");//图表排除掉 未更新天数等于-1的情况
				}
			}
			List<BigDataDailyResponse> sList = bigDataDailyServiceImpl.queryNatives(sRequest);
			if(sList.size()>0){
				if("0".equals(contentType)){
				for(BigDataDailyResponse bigDataDailyResponse:sList){
					String yName=bigDataDailyResponse.getName();
					String xVal=bigDataDailyResponse.getOrderVal();
					if(StringUtils.isEmpty(yName)){
						yName="";
					}
					if(StringUtils.isEmpty(xVal)){
						xVal="0";
					}
					xlist.add(xVal);
					ylist.add(yName);
				}
				}
				resultMap.put("xlist", xlist);
				resultMap.put("ylist", ylist);
				resultMap.put("body", sList);
			}else{
				resultMap.put("errorMsg", "不存本级站点的统计数据");
			}
			resultMap.put("tabHide", tabHide);
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			// TODO: handle exception
			resultMap.put("errorMsg", "不存本级站点的统计数据");
			e.printStackTrace();
		}
	}
	/**
	 * @描述:网站个数详情和 首页不更新网站个数
	 * @作者:liujc@ucap.com.cn
	 * @时间:2017-3-8下午3:12:15
	 */
	@SuppressWarnings("unchecked")
	public void queryAllSite(){
		Map<String, Object> chartMap=new HashMap<String, Object>();
		String scanDate=queryDate();
		try {
			//封装给页面的数据
//			String orgSiteCode=request.getParameter("orgSiteCode");//组织机构编码
			String dayNum=request.getParameter("dayNum");//天数
			String code=request.getParameter("code");
//			String sitenum=request.getParameter("sitenum");
//			String level=request.getParameter("level");
			Integer urlType=Integer.valueOf(request.getParameter("urlType"));
//			String linkerrprop=request.getParameter("linkerrprop");//不连通占比
//			String linkerrpropVal=request.getParameter("linkerrpropVal");//不连通占比val
			Map<String, Object> resultMap=new HashMap<String, Object>();
			BigDataDailyRequest sRequest = new BigDataDailyRequest();
//			sRequest.setOrgSiteCode(orgSiteCode);
			sRequest.setCountDay(scanDate);
			if(urlType==3){
				if(dayNum.equals("1")){
					sRequest.setNoupdatestatusStr("updatestatus");
				}else{
					sRequest.setNoupdatestatusStr("updatestatus"+dayNum);
				}
				sRequest.setNoupdatestatus("0");//0：不更新 1：更新 -1：未知
			}
				sRequest.setSiteCodeLength("10");
				sRequest.setCodeLike(code);
				if("0".equals(contentType)){
					//图表
					Map<String, Object> orderMap=orderCommon();
					sRequest.setQueryOrderList((List<QueryOrder>) orderMap.get("queryOrderList"));
					sRequest.setOrderVal(String.valueOf(orderMap.get("orderVal")));
					if(orderMap.get("startIndex")!=null){
						sRequest.setStartIndex(0);
						sRequest.setPageSize(20);
					}
					//未更新天数 图表
					if("8".equals(radioVal)){
						sRequest.setNoupdatedayNo("-1");//图表排除掉 未更新天数等于-1的情况
					}
				}
			//查询该组织单位下所有的10位站点 从里面过滤出不更新的站点
			List<BigDataDailyResponse> sList = bigDataDailyServiceImpl.queryNatives(sRequest);
			if(sList.size()>0){
				if("0".equals(contentType)){
					for(BigDataDailyResponse bigDataDailyResponse:sList){
						String yName=bigDataDailyResponse.getName();
						String xVal=bigDataDailyResponse.getOrderVal();
						if(StringUtils.isEmpty(yName)){
							yName="";
						}
						if(StringUtils.isEmpty(xVal)){
							xVal="0";
						}
						xlist.add(xVal);
						ylist.add(yName);
					}
					}
			}else{
				resultMap.put("errorMsg", "不存在统计数据");
			}
		
			resultMap.put("xlist", xlist);
			resultMap.put("ylist", ylist);
			resultMap.put("body", sList);
			writerPrint(JSONObject.fromObject(resultMap).toString());
		
		} catch (Exception e) {
			chartMap.put("errorMsg", "不存在统计数据");
			writerPrint(JSONObject.fromObject(chartMap).toString());
			e.printStackTrace();
		}
		
	}
	
	/**
	 * @Description: 导出站点站点excel
	 * @author cuichx --- 2016-5-30下午4:02:22
	 */
	public void exportSiteExcelDate(){
		
		String orgSiteCode=request.getParameter("orgSiteCode");//组织机构编码
		String dayNum=request.getParameter("dayNum");//时间段
		String level=request.getParameter("level");
		Integer urlType=Integer.valueOf(request.getParameter("urlType"));
		String scanDate=queryDate();
		String code=request.getParameter("code");
		try {
			ArrayList<Object[]> list = new ArrayList<Object[]>();
			
			String title = "";

			String fileName ="";
			
			if(2==urlType){//网站个数
				title="站点个数详情";
				fileName="站点个数详情("+DateUtils.formatStandardDate(new Date())+"_"+dayNum+"天).xls";
			}else if(3==urlType){//首页不更新站点数
				title="首页不更新站点详情";
				fileName="首页不更新站点详情("+DateUtils.formatStandardDate(new Date())+"_"+dayNum+"天).xls";
			}else if(4==urlType){//网站不连通个数详情
				title="网站不连通站点详情";
				fileName="网站不连通站点详情("+DateUtils.formatStandardDate(new Date())+"_"+dayNum+"天).xls";
			}
			
			Object[] obj1 = new Object[]{"组织单位名称/编码","省","市","区/县","健康指数","连不通比例（连不通次数／总次数）","首页死链(个)","首页不更新","网站更新量（条）","最后更新日期","未更新天数","网址"};
			list.add(obj1);
			
			BigDataDailyRequest sRequest = new BigDataDailyRequest();
			sRequest.setCountDay(scanDate);
			if(urlType==3){
				if(dayNum.equals("1")){
					sRequest.setNoupdatestatusStr("updatestatus");
				}else{
					sRequest.setNoupdatestatusStr("updatestatus"+dayNum);
				}
				sRequest.setNoupdatestatus("0");//0：不更新 1：更新 -1：未知
			}
			sRequest.setSiteCodeLength("10");
			sRequest.setCodeLike(code);
			List<BigDataDailyResponse> resultlist = bigDataDailyServiceImpl.queryNatives(sRequest);
			
			Map<String, Object> paramMap=new HashMap<String, Object>();
			paramMap.put("level", level);
			if(StringUtils.isNotEmpty(orgSiteCode)){
				if("bm".equals(orgSiteCode.substring(0, 2))){//部委
					if("1".equals(level)){
						paramMap.put("siteCodeLike", orgSiteCode.substring(0, 4));
					}else{
						paramMap.put("siteCodeLike", orgSiteCode);
					}
				}else{//非部委
					if("1".equals(level)){
						paramMap.put("siteCodeLike", orgSiteCode.substring(0, 2));
					}else if("2".equals(level)){
						if("11,12,50,31".contains(orgSiteCode.substring(0, 2))){//直辖市坐特殊处理
							paramMap.put("siteCodeLike", orgSiteCode);
						}else{
							paramMap.put("siteCodeLike", orgSiteCode.substring(0, 4));
						}
						
					}else if("3".equals(level)){
						paramMap.put("siteCodeLike", orgSiteCode);
					}
					
				}
			}
				if(resultlist!=null && resultlist.size()>0){
					for (int i = 0; i < resultlist.size(); i++) {
						BigDataDailyResponse result=resultlist.get(i);
						Object[] obj = new Object[12];
						
						String lastupdatedate="";//最后更新时间
						String updateStatus="";//首页不更新
						Integer noupdateday=result.getNoupdateday();//未更新天数
						String noupdatedayStr="";
						//未更新天数不等于-1
							if(noupdateday != -1){
								if(noupdateday>14){
									updateStatus=UpdateStatusType.getNameByCode(0);//超过两周
								}else{
									updateStatus=UpdateStatusType.getNameByCode(1);//正常更新
								}
								lastupdatedate=String.valueOf(result.getLastupdatedate());//最后更新日期
								noupdatedayStr=String.valueOf(result.getNoupdateday());//未更新天数
							}else{
								//未更新天数等于-1  都是未知
								updateStatus=UpdateStatusType.getNameByCode(-1);//未知
								lastupdatedate="未知";//最后更新日期
								noupdatedayStr="未知";//未更新天数
							}
							obj[7]=updateStatus;//首页不更新
							obj[9]=lastupdatedate;//最后更新日期
							obj[10]=noupdatedayStr;//未更新天数
						if("1".equals(dayNum)){
							obj[0]=result.getName()+" ("+result.getSitecode()+")";//组织单位名称/编码
							obj[1]=result.getProvince();//省
							obj[2]=result.getCity();//市
							obj[3]=result.getCounty();//区、县
							obj[4]=result.getHealthindex();//健康指数
							obj[5]=result.getLinkerrprop()+"%";//监测不连通率（占比）
							obj[6]=result.getIndexdeadnum();//首页死链(个)
							obj[8]=result.getUpdatenum();//网站更新量（条）
							Integer updatecodesType=unknownType(result.getUpdatecodes(), String.valueOf(result.getLastupdatedate()));
							if(-1 ==updatecodesType){
								//3次中 没有连通成功的
								obj[7]="未知";//首页不更新
								obj[9]="未知";//最后更新日期
								obj[10]="未知";//未更新天数
							}
						}else if("7".equals(dayNum)){
							obj[0]=result.getName()+" ("+result.getSitecode()+")";//组织单位名称/编码
							obj[1]=result.getProvince();//省
							obj[2]=result.getCity();//市
							obj[3]=result.getCounty();//区、县
							obj[4]=result.getHealthindex7();//健康指数
							obj[5]=result.getLinkerrprop7()+"%";//监测不连通率（占比）
							obj[6]=result.getIndexdeadnum7();//首页死链(个)
							obj[8]=result.getUpdatenum7();//网站更新量（条）
							Integer updatecodesType=unknownType(result.getUpdatecodes(), String.valueOf(result.getLastupdatedate()));
							if(-1 ==updatecodesType){
								//3次中 没有连通成功的
								obj[7]="未知";//首页不更新
								obj[9]="未知";//最后更新日期
								obj[10]="未知";//未更新天数
							}
						}else if("14".equals(dayNum)){
							obj[0]=result.getName()+" ("+result.getSitecode()+")";//组织单位名称/编码
							obj[1]=result.getProvince();//省
							obj[2]=result.getCity();//市
							obj[3]=result.getCounty();//区、县
							obj[4]=result.getHealthindex14();//健康指数
							obj[5]=result.getLinkerrprop14()+"%";//监测不连通率（占比）
							obj[6]=result.getIndexdeadnum14();//首页死链(个)
							obj[8]=result.getUpdatenum14();//网站更新量（条）
							Integer updatecodesType=unknownType(result.getUpdatecodes(), String.valueOf(result.getLastupdatedate()));
							if(-1 ==updatecodesType){
								//3次中 没有连通成功的
								obj[7]="未知";//首页不更新
								obj[9]="未知";//最后更新日期
								obj[10]="未知";//未更新天数
							}
						}else {
							obj[0]=result.getName()+" ("+result.getSitecode()+")";//组织单位名称/编码
							obj[1]=result.getProvince();//省
							obj[2]=result.getCity();//市
							obj[3]=result.getCounty();//区、县
							obj[4]=result.getHealthindex30();//健康指数
							obj[5]=result.getLinkerrprop30()+"%";//监测不连通率（占比）
							obj[6]=result.getIndexdeadnum30();//首页死链(个)
							obj[8]=result.getUpdatenum30();//网站更新量（条）
							Integer updatecodesType=unknownType(result.getUpdatecodes(), String.valueOf(result.getLastupdatedate()));
							if(-1 ==updatecodesType){
								//3次中 没有连通成功的
								obj[7]="未知";//首页不更新
								obj[9]="未知";//最后更新日期
								obj[10]="未知";//未更新天数
							}
						}
						
						String url=result.getUrl();
						obj[11]=url;
						list.add(obj);
					}
		}
		ExportExcel.bigDataSiteExcel(fileName,title,list);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	
	/**
	 * @Description: 导出excel数据
	 * @author cuichx --- 2016-5-30上午11:27:23
	 */
	public void exportExcelData(){
		
		String orgSiteCode=request.getParameter("orgSiteCode");//组织机构编码
		String level=request.getParameter("level");//组织机构级别
		String searchDate=request.getParameter("searchDate");//时间段
		String name=request.getParameter("name");//省、市、县名称
		String tabHide=request.getParameter("tabHide");////点击按钮展示个数
		
		try {
			//封装要导出的数据
			List<Map<String, Object>> dtList=new ArrayList<Map<String,Object>>();  
			List<Object[]> stationGroupList = new ArrayList<Object[]>();;//存放地方站群数据，用于定位
			List<Object[]> stationList = new ArrayList<Object[]>();;//存放地方站群数据，用于定位
			String stationGroupTitle = "";//存放地方站群title
			Object[] stationGroupObj = null;//存放地方站群表头
			if("0".equals(tabHide)){//国办bm0100  展示5个按钮  地方站群  门户网站  本级部门  下级部门站群 下级部门门户
				
				//excel名称
				String fileName = "中华人民共和国国务院办公厅_大数据分析("+DateUtils.formatStandardDate(new Date())+"_"+searchDate+"天).xls";
				for(int i=0;i<5;i++){
					Map<String, Object> dataMap=new HashMap<String, Object>();
					
					List<Object[]> list=null;
					if(i==0 || i==3){//下级地方站群和下级部门站群
						list = new ArrayList<Object[]>();
						Object[] obj1 = new Object[]{"组织单位名称/编码","网站个数","健康指数","监测不连通率（占比）","首页不可用链接(个)","首页不更新(网站数)","首页不更新(占比)","平均更新量(条/站)","监测更新量(条)"};
						stationGroupObj = obj1;
						list.add(obj1);
					}else{//下级地方门户  本级站点  下级部门门户
						list = new ArrayList<Object[]>();
						Object[] obj1 = new Object[]{"组织单位名称/编码","省","市","区/县","健康指数","监测不连通率（占比）","首页不可用链接(个)","首页不更新","监测更新量(条)","最后更新时间","未更新天数","网址"};
						list.add(obj1);
					}

					//shell名称 
					String title="";
					if(i==0){//地方站群
						stationGroupTitle="地方站群";
						stationList=totalExcelData(orgSiteCode, level, "0", searchDate, name);
						
					}else if(i==1){//门户网站
						title="门户网站";
						List<Object[]> excelList=siteExcelData(orgSiteCode, level, ""+i, searchDate);
						list.addAll(excelList);
						
						dataMap.put("title", title);
						dataMap.put("list", list);
						dtList.add(dataMap);
					}else if(i==2){//本级部门
						title="本级部门";
						List<Object[]> excelList=siteExcelData(orgSiteCode, level, ""+i, searchDate);
						list.addAll(excelList);
						
						dataMap.put("title", title);
						dataMap.put("list", list);
						dtList.add(0,dataMap);
					}else if(i==3){//下级部门站群
						title="下级部门站群";
						List<Object[]> excelList=totalExcelData(orgSiteCode, level, "1", searchDate, name);
						list.addAll(excelList);
						
						dataMap.put("title", title);
						dataMap.put("list", list);
						dtList.add(dataMap);
					}else{//下级部门门户
						title="下级部门门户";
						List<Object[]> excelList=siteExcelData(orgSiteCode, level, ""+i, searchDate);
						list.addAll(excelList);
						
						dataMap.put("title", title);
						dataMap.put("list", list);
						dtList.add(dataMap);
					}
				}
				stationGroupList.add(stationGroupObj);
				stationGroupList.addAll(stationList);
				Map<String, Object> dataMapMap=new HashMap<String, Object>();
				dataMapMap.put("title", stationGroupTitle);
				dataMapMap.put("list", stationGroupList);
				dtList.add(2,dataMapMap);
				ExportExcel.bigDataExcel(fileName,dtList,searchDate);
			}else if ("2".equals(tabHide)) {//部委  展示3个按钮  本级部门  下级部门站群 下级部门门户
				//excel名称
				String fileName = name+"_大数据分析("+DateUtils.formatStandardDate(new Date())+"_"+searchDate+"天).xls";
				for(int i=2;i<5;i++){
					Map<String, Object> dataMap=new HashMap<String, Object>();
					ArrayList<Object[]> list=null;
					if(i==0 || i==3){//下级地方站群和下级部门站群
						list = new ArrayList<Object[]>();
						Object[] obj1 = new Object[]{"组织单位名称/编码","网站个数","健康指数","监测不连通率（占比）","首页不可用链接(个)","首页不更新(网站数)","首页不更新(占比)","平均更新量(条/站)","监测更新量(条)"};
						list.add(obj1);
					}else{//下级地方门户  本级站点  下级部门门户
						list = new ArrayList<Object[]>();
						Object[] obj1 = new Object[]{"组织单位名称/编码","省","市","区/县","健康指数","监测不连通率（占比）","首页不可用链接(个)","首页不更新","监测更新量(条)","最后更新时间","未更新天数","网址"};
						list.add(obj1);
					}

					//shell名称 
					String title="";
					if(i==2){//本级部门
						title="本级部门";
						List<Object[]> excelList=siteExcelData(orgSiteCode, level, ""+i, searchDate);
						list.addAll(excelList);
						
						dataMap.put("title", title);
						dataMap.put("list", list);
						
						dtList.add(dataMap);
						
					}else if(i==3){//下级部门站群
						title="下级部门站群";
						List<Object[]> excelList=totalExcelData(orgSiteCode, level, "1", searchDate, name);
						list.addAll(excelList);
						
						dataMap.put("title", title);
						dataMap.put("list", list);
						
						dtList.add(dataMap);
					}else{//下级部门门户
						title="下级部门门户";
						List<Object[]> excelList=siteExcelData(orgSiteCode, level, ""+i, searchDate);
						list.addAll(excelList);
						
						dataMap.put("title", title);
						dataMap.put("list", list);
						
						dtList.add(dataMap);
					}
				}
				ExportExcel.bigDataExcel(fileName,dtList,searchDate);
			}else if ("1".equals(tabHide)) {//省级  展示3个按钮  地方站群 门户网站 本级部门
				//excel名称
				String fileName = name+"_大数据分析("+DateUtils.formatStandardDate(new Date())+"_"+searchDate+"天).xls";
				for(int i=0;i<3;i++){
					Map<String, Object> dataMap=new HashMap<String, Object>();
					ArrayList<Object[]> list=null;
					if(i==0 || i==3){//下级地方站群和下级部门站群
						list = new ArrayList<Object[]>();
						Object[] obj1 = new Object[]{"组织单位名称/编码","网站个数","健康指数","监测不连通率（占比）","首页不可用链接(个)","首页不更新(网站数)","首页不更新(占比)","平均更新量(条/站)","监测更新量(条)"};
						stationGroupObj = obj1;
						list.add(obj1);
					}else{//下级地方门户  本级站点  下级部门门户
						list = new ArrayList<Object[]>();
						Object[] obj1 = new Object[]{"组织单位名称/编码","省","市","区/县","健康指数","监测不连通率（占比）","首页不可用链接(个)","首页不更新","监测更新量(条)","最后更新时间","未更新天数","网址"};
						list.add(obj1);
					}

					//shell名称 
					String title="";
					if(i==0){//地方站群
						stationGroupTitle="地方站群";
						stationList=totalExcelData(orgSiteCode, level, "0", searchDate, name);
						
					}else if(i==1){//门户网站
						title="门户网站";
						List<Object[]> excelList=siteExcelData(orgSiteCode, level, ""+i, searchDate);
						list.addAll(excelList);
						
						dataMap.put("title", title);
						dataMap.put("list", list);
						
						dtList.add(dataMap);
					}else{//本级部门
						title="本级部门";
						List<Object[]> excelList=siteExcelData(orgSiteCode, level, ""+i, searchDate);
						list.addAll(excelList);
						
						dataMap.put("title", title);
						dataMap.put("list", list);
						
						dtList.add(0,dataMap);
					}
				}
				stationGroupList.add(stationGroupObj);
				stationGroupList.addAll(stationList);
				Map<String, Object> dataMapMap=new HashMap<String, Object>();
				dataMapMap.put("title", stationGroupTitle);
				dataMapMap.put("list", stationGroupList);
				dtList.add(2,dataMapMap);
				ExportExcel.bigDataExcel(fileName,dtList,searchDate);
			}else{// 本级站点
				//excel名称
				String fileName = name+"_大数据分析("+DateUtils.formatStandardDate(new Date())+"_"+searchDate+"天).xls";
					Map<String, Object> dataMap=new HashMap<String, Object>();
					ArrayList<Object[]> list=null;
					list = new ArrayList<Object[]>();
					Object[] obj1 = new Object[]{"组织单位名称/编码","省","市","区/县","健康指数","监测不连通率（占比）","首页不可用链接(个)","首页不更新(网站数)","监测更新量(条)","最后更新时间","未更新天数","网址"};
					list.add(obj1);

					//shell名称 
					String title="本级站点";
					List<Object[]> excelList=siteExcelData(orgSiteCode, level, "2", searchDate);
					list.addAll(excelList);
					
					dataMap.put("title", title);
					dataMap.put("list", list);
					
					dtList.add(dataMap);
				ExportExcel.bigDataExcel(fileName,dtList,searchDate);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * @Description: 地方站群  下级部门站群导出excel数据
	 * @author cuichx --- 2016-5-30下午12:01:00     
	 * @param orgSiteCode 组织机构编码
	 * @param level 组织机构级别
	 * @param typeFlag bm0100时，0-下一级站群  1下级部门站群
	 * @param searchDate 时间段
	 * @param name 省、市、县名称
	 */
	public List<Object[]> totalExcelData(String orgSiteCode,String level,String typeFlag,String searchDate,String name){
		List<Object[]> list = new ArrayList<Object[]>();
		try {
			//获取下一级站群的所有组织机构
			Map<String, Object> paramMap=new HashMap<String, Object>();
			List<BigOrgDailyResponse> resultlist = new ArrayList<BigOrgDailyResponse>();
			String scanDate=queryDate();
			DatabaseTreeInfo isNextOrg = databaseBizServiceImpl.isNextOrg(orgSiteCode);
			
			if(StringUtils.isNotEmpty(level)){
				paramMap.put("level", level);//组织机构级别
			}
			if(StringUtils.isNotEmpty(orgSiteCode)){
				if(!"bm0100".equals(orgSiteCode)){//
					if("bm".equals(orgSiteCode.substring(0, 2))){
						/**
						 * 部委
						 * 		下级部门站群---获取部委2级或者3级组织机构的统计数据
						 */
						BigOrgDailyRequest rRequest = new BigOrgDailyRequest();
						if(isNextOrg != null){
							rRequest.setIsBm(isNextOrg.getIsBm());
							rRequest.setParentId(isNextOrg.getParentId());
							rRequest.setCountDay(scanDate);
							resultlist = bigOrgDailyServiceImpl.getOrgData(rRequest);
						}
					}else{
						/**
						 * 非部委 
						 * 		下一级站群---获取每个市或者县的统计数据
						 */
						BigOrgDailyRequest rRequest = new BigOrgDailyRequest();
						if(isNextOrg != null){
							rRequest.setIsBm(isNextOrg.getIsBm());
							rRequest.setParentId(isNextOrg.getParentId());
							rRequest.setCountDay(scanDate);
							resultlist = bigOrgDailyServiceImpl.getOrgData(rRequest);
						}
					}
				}else{//国办bm0100
					if("0".equals(typeFlag)){//国办   下一级站群 导出excel
						
						BigOrgDailyRequest rRequest = new BigOrgDailyRequest();
						if(isNextOrg != null){
							rRequest.setIsBm(0);
							rRequest.setParentId(isNextOrg.getParentId());
							rRequest.setCountDay(scanDate);
							resultlist = bigOrgDailyServiceImpl.getOrgData(rRequest);
						}
					}else{//国办  下级部门站群导出excel
						BigOrgDailyRequest rRequest = new BigOrgDailyRequest();
						if(isNextOrg != null){
							rRequest.setIsBm(1);
							rRequest.setParentId(isNextOrg.getParentId());
							rRequest.setCountDay(scanDate);
							resultlist = bigOrgDailyServiceImpl.getOrgData(rRequest);
						}
					}
				}
				
					if(resultlist!=null && resultlist.size()>0){
						for(int i=0;i<resultlist.size();i++){
							Object[] obj = new Object[9];
							if("1".equals(searchDate)){//昨天
								
								BigOrgDailyResponse result=resultlist.get(i);
								if(!result.getTaskid().equals("bm8000")){
								obj[0]=result.getName()+" ("+result.getTaskid()+")";//组织单位名称/编码
								obj[1]=result.getSitenum();//网站个数
								obj[2]=result.getHealthindex();//健康指数
								
								obj[3]=result.getLinkerrsiteprop()+"%";//监测不连通率（占比）
								obj[4]=result.getIndexdeadnum();//首页不可用链接(个)
								obj[5]=result.getNoupdatesitenum();//首页不更新(网站数)
								obj[6]=result.getNoupdatesiteprop()+"%";//首页不更新(占比)
								obj[7]=result.getAveupdatenum();//平均更新量(条/站)
								obj[8]=result.getUpdatenum();//监测更新量(条)
								}
						}else if("7".equals(searchDate)){//七天
							
								BigOrgDailyResponse result=resultlist.get(i);
								if(!result.getTaskid().equals("bm8000")){
								obj[0]=result.getName()+" ("+result.getTaskid()+")";//组织单位名称/编码
								obj[1]=result.getSitenum();//网站个数
								obj[2]=result.getHealthindex7();//健康指数
								
								obj[3]=result.getLinkerrsiteprop7()+"%";//监测不连通率（占比）
								obj[4]=result.getIndexdeadnum7();//首页不可用链接(个)
								obj[5]=result.getNoupdatesitenum7();//首页不更新(网站数)
								obj[6]=result.getNoupdatesiteprop7()+"%";//首页不更新(占比)
								obj[7]=result.getAveupdatenum7();//平均更新量(条/站)
								obj[8]=result.getUpdatenum7();//监测更新量(条)
								}
						}else if("14".equals(searchDate)){//两周
							
								BigOrgDailyResponse result=resultlist.get(i);
								if(!result.getTaskid().equals("bm8000")){
								obj[0]=result.getName()+" ("+result.getTaskid()+")";//组织单位名称/编码
								obj[1]=result.getSitenum();//网站个数
								obj[2]=result.getHealthindex14();//健康指数
								
								obj[3]=result.getLinkerrsiteprop14()+"%";//监测不连通率（占比）
								obj[4]=result.getIndexdeadnum14();//首页不可用链接(个)
								obj[5]=result.getNoupdatesitenum14();//首页不更新(网站数)
								obj[6]=result.getNoupdatesiteprop14()+"%";//首页不更新(占比)
								obj[7]=result.getAveupdatenum14();//平均更新量(条/站)
								obj[8]=result.getUpdatenum14();//监测更新量(条)
								}
						}else{//一个月
							
								BigOrgDailyResponse result=resultlist.get(i);
								if(!result.getTaskid().equals("bm8000")){
								obj[0]=result.getName()+" ("+result.getTaskid()+")";//组织单位名称/编码
								obj[1]=result.getSitenum();//网站个数
								obj[2]=result.getHealthindex30();//健康指数
								
								obj[3]=result.getLinkerrsiteprop30()+"%";//监测不连通率（占比）
								obj[4]=result.getIndexdeadnum30();//首页不可用链接(个)
								obj[5]=result.getNoupdatesitenum30();//首页不更新(网站数)
								obj[6]=result.getNoupdatesiteprop30()+"%";//首页不更新(占比)
								obj[7]=result.getAveupdatenum30();//平均更新量(条/站)
								obj[8]=result.getUpdatenum30();//监测更新量(条)
								}
						}
							list.add(obj);
							
						}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return list;
		
	}
	
	/**
	 * @Description: 下级地方门户 本级部门 下级部门门户导出excel数据
	 * @author cuichx --- 2016-5-30下午1:02:32     
	 * @param orgSiteCode	组织机构编码
	 * @param level	组织机构级别
	 * @param typeFlag	1下一级门户  2本级部门  4部委门户
	 * @param searchDate	时间段
	 */
	public List<Object[]> siteExcelData(String orgSiteCode,String level,String typeFlag,String searchDate){
		
		List<Object[]> list = new ArrayList<Object[]>();
		try {
			//获取下一级站群的所有组织机构
			Map<String, Object> paramMap=new HashMap<String, Object>();
			List<BigDataDailyResponse> resultlist = new ArrayList<BigDataDailyResponse>();
			String scanDate=queryDate();
			DatabaseTreeInfo isNextOrg = databaseBizServiceImpl.isNextOrg(orgSiteCode);
			if(StringUtils.isNotEmpty(level)){
				paramMap.put("level", level);//组织机构级别
			}
			if(!"bm0100".equals(orgSiteCode)){//非国办 
				
				if("1".equals(typeFlag)){//1下一级门户---获取组织机构门户网站网站的统计数据
					
					BigDataDailyRequest rRequest = new BigDataDailyRequest();
					rRequest.setIsBm(isNextOrg.getIsBm());
					rRequest.setParentId(isNextOrg.getParentId());
					rRequest.setCountDay(scanDate);
					resultlist = bigDataDailyServiceImpl.queryOrganizations(rRequest);
				}else if("2".equals(typeFlag)){//2本级部门---获取组织机构本级部门网站的统计数据
					paramMap.remove("level");
					
					BigDataDailyRequest sRequest = new BigDataDailyRequest();
					sRequest.setOrgSiteCode(orgSiteCode);
					sRequest.setCountDay(scanDate);
					resultlist = bigDataDailyServiceImpl.queryNatives(sRequest);
				}else{//4部委门户---获取部委门户网站网站的统计数据
					
					BigDataDailyRequest rRequest = new BigDataDailyRequest();
					rRequest.setIsBm(isNextOrg.getIsBm());
					rRequest.setParentId(isNextOrg.getParentId());
					rRequest.setCountDay(scanDate);
					resultlist = bigDataDailyServiceImpl.queryOrganizations(rRequest);
				}
			}else{//国办 
				if("1".equals(typeFlag)){//1下一级门户---获取组织机构门户网站网站的统计数据
					
					BigDataDailyRequest rRequest = new BigDataDailyRequest();
					rRequest.setIsBm(0);//bm0100  会展示5个tab  需要从页面区别部委还是地方
					rRequest.setParentId(isNextOrg.getParentId());
					rRequest.setCountDay(scanDate);
					resultlist = bigDataDailyServiceImpl.queryOrganizations(rRequest);

				}else if("2".equals(typeFlag)){//2本级部门---获取组织机构本级部门网站的统计数据
					paramMap.remove("level");
					
					BigDataDailyRequest sRequest = new BigDataDailyRequest();
					sRequest.setOrgSiteCode(orgSiteCode);
					sRequest.setCountDay(scanDate);
					resultlist = bigDataDailyServiceImpl.queryNatives(sRequest);
				}else{//4部委门户---获取部委门户网站网站的统计数据

					BigDataDailyRequest rRequest = new BigDataDailyRequest();
					rRequest.setIsBm(1);//bm0100  会展示5个tab  需要从页面区别部委还是地方
					rRequest.setParentId(isNextOrg.getParentId());
					rRequest.setCountDay(scanDate);
					resultlist = bigDataDailyServiceImpl.queryOrganizations(rRequest);
				}
			}
				if(resultlist!=null && resultlist.size()>0){
					for(int i=0;i<resultlist.size();i++){

						BigDataDailyResponse result=resultlist.get(i);
						Object[] obj = new Object[12];
						
						String lastupdatedate="";//最后更新时间
						String updateStatus="";//首页不更新
						Integer noupdateday=result.getNoupdateday();//未更新天数
						String noupdatedayStr="";
						//未更新天数不等于-1
							if(noupdateday != -1){
								if(noupdateday>14){
									updateStatus=UpdateStatusType.getNameByCode(0);//超过两周
								}else{
									updateStatus=UpdateStatusType.getNameByCode(1);//正常更新
								}
								lastupdatedate=String.valueOf(result.getLastupdatedate());//最后更新日期
								noupdatedayStr=String.valueOf(result.getNoupdateday());//未更新天数
							}else{
								//未更新天数等于-1  都是未知
								updateStatus=UpdateStatusType.getNameByCode(-1);//未知
								lastupdatedate="未知";//最后更新日期
								noupdatedayStr="未知";//未更新天数
							}
							obj[7]=updateStatus;//首页不更新
							obj[9]=lastupdatedate;//最后更新日期
							obj[10]=noupdatedayStr;//未更新天数
						if("1".equals(searchDate)){
							obj[0]=result.getName()+" ("+result.getSitecode()+")";//组织单位名称/编码
							obj[1]=result.getProvince();//省
							obj[2]=result.getCity();//市
							obj[3]=result.getCounty();//区、县
							obj[4]=result.getHealthindex();//健康指数
							obj[5]=result.getLinkerrprop()+"%";//监测不连通率（占比）
							obj[6]=result.getIndexdeadnum();//首页死链(个)
							obj[8]=result.getUpdatenum();//网站更新量（条）
							Integer updatecodesType=unknownType(result.getUpdatecodes(), String.valueOf(result.getLastupdatedate()));
							if(-1 ==updatecodesType){
								//3次中 没有连通成功的
								obj[7]="未知";//首页不更新
								obj[9]="未知";//最后更新日期
								obj[10]="未知";//未更新天数
							}
						}else if("7".equals(searchDate)){
							obj[0]=result.getName()+" ("+result.getSitecode()+")";//组织单位名称/编码
							obj[1]=result.getProvince();//省
							obj[2]=result.getCity();//市
							obj[3]=result.getCounty();//区、县
							obj[4]=result.getHealthindex7();//健康指数
							obj[5]=result.getLinkerrprop7()+"%";//监测不连通率（占比）
							obj[6]=result.getIndexdeadnum7();//首页死链(个)
							obj[8]=result.getUpdatenum7();//网站更新量（条）
							Integer updatecodesType=unknownType(result.getUpdatecodes(), String.valueOf(result.getLastupdatedate()));
							if(-1 ==updatecodesType){
								//3次中 没有连通成功的
								obj[7]="未知";//首页不更新
								obj[9]="未知";//最后更新日期
								obj[10]="未知";//未更新天数
							}
						}else if("14".equals(searchDate)){
							obj[0]=result.getName()+" ("+result.getSitecode()+")";//组织单位名称/编码
							obj[1]=result.getProvince();//省
							obj[2]=result.getCity();//市
							obj[3]=result.getCounty();//区、县
							obj[4]=result.getHealthindex14();//健康指数
							obj[5]=result.getLinkerrprop14()+"%";//监测不连通率（占比）
							obj[6]=result.getIndexdeadnum14();//首页死链(个)
							obj[8]=result.getUpdatenum14();//网站更新量（条）
							Integer updatecodesType=unknownType(result.getUpdatecodes(), String.valueOf(result.getLastupdatedate()));
							if(-1 ==updatecodesType){
								//3次中 没有连通成功的
								obj[7]="未知";//首页不更新
								obj[9]="未知";//最后更新日期
								obj[10]="未知";//未更新天数
							}
						}else {
							obj[0]=result.getName()+" ("+result.getSitecode()+")";//组织单位名称/编码
							obj[1]=result.getProvince();//省
							obj[2]=result.getCity();//市
							obj[3]=result.getCounty();//区、县
							obj[4]=result.getHealthindex30();//健康指数
							obj[5]=result.getLinkerrprop30()+"%";//监测不连通率（占比）
							obj[6]=result.getIndexdeadnum30();//首页死链(个)
							obj[8]=result.getUpdatenum30();//网站更新量（条）
							Integer updatecodesType=unknownType(result.getUpdatecodes(), String.valueOf(result.getLastupdatedate()));
							if(-1 ==updatecodesType){
								//3次中 没有连通成功的
								obj[7]="未知";//首页不更新
								obj[9]="未知";//最后更新日期
								obj[10]="未知";//未更新天数
							}
						}
						
						String url=result.getUrl();
						obj[11]=url;
						list.add(obj);
					}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 
	 * @描述: 该方法是 刚开发大数据时  站点分页查询方法 目前没用  下个版本优化会用到
	 * @作者:liujc@ucap.com.cn
	 * @时间:2017-1-12下午5:55:02
	 */
	public void queryAllSitePage(){
		//封装给页面的数据
		Map<String, Object> chartMap=new HashMap<String, Object>();
		String orgSiteCode=request.getParameter("orgSiteCode");//组织机构编码
		Integer dayNum=Integer.valueOf(request.getParameter("dayNum"));//天数
		String sitenum=request.getParameter("sitenum");
		String level=request.getParameter("level");
		Map<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("level", level);
		if(StringUtils.isNotEmpty(orgSiteCode)){
			if("bm".equals(orgSiteCode.substring(0, 2))){//部委
				
				if("1".equals(level)){
					paramMap.put("siteCodeLike", orgSiteCode.substring(0, 4));
				}else{
					paramMap.put("siteCodeLike", orgSiteCode);
				}
			}else{//非部委
				if("1".equals(level)){
					paramMap.put("siteCodeLike", orgSiteCode.substring(0, 2));
				}else if("2".equals(level)){
					if("11,12,50,31".contains(orgSiteCode.substring(0, 2))){//直辖市坐特殊处理
						paramMap.put("siteCodeLike", orgSiteCode);
					}else{
						paramMap.put("siteCodeLike", orgSiteCode.substring(0, 4));
					}
					
				}else if("3".equals(level)){
					paramMap.put("siteCodeLike", orgSiteCode);
				}
				
			}
		}
		String pos = request.getParameter("pos");
		String size = request.getParameter("size");
		if(StringUtils.isNotEmpty(pos)){
			paramMap.put("pageNo",Integer.parseInt(pos));
		}
		
		if(StringUtils.isNotEmpty(size)){
			paramMap.put("pageSize",Integer.parseInt(size));
		}
		List<DatabaseInfo> dataList=databaseInfoServiceImpl.queryAllSite(paramMap);
		if(dataList!=null && dataList.size()>0){
			//调用接口
			Map<String, Object> siteCodeMap=bigDataAnalysisServiceImpl.listToStr(dataList);
			String siteCodeStr=(String) siteCodeMap.get("siteCodeStr");
			siteCodeMap.put("orgSiteCode", orgSiteCode);
			List<Result> resultlist=bigDataAnalysisServiceImpl.bigDataXml(siteCodeStr, 1, siteCodeMap);
			
			String linkerrprop = null;
			String indexdeadnum=null;
			String updatestatus=null;
			String updatenum=null;
			List<Object> bodyList = new ArrayList<Object>();
			for(int i =0;i<resultlist.size();i++){
				Map<String,Object> bodyMap = new HashMap<String, Object>();
				Result result =resultlist.get(i);
				if(1 == dayNum){
					linkerrprop=String.valueOf(result.getLinkerrprop());
					indexdeadnum=String.valueOf(result.getIndexdeadnum());
					updatestatus=result.getUpdatestatus();
					updatenum=String.valueOf(result.getUpdatenum());
				}else if(7 == dayNum){
					linkerrprop=String.valueOf(result.getLinkerrprop7());
					indexdeadnum=String.valueOf(result.getIndexdeadnum7());
					updatestatus=result.getUpdatestatus7();
					updatenum=String.valueOf(result.getUpdatenum7());
				}else if(14 == dayNum){
					linkerrprop=String.valueOf(result.getLinkerrprop14());
					indexdeadnum=String.valueOf(result.getIndexdeadnum14());
					updatestatus=result.getUpdatestatus14();
					updatenum=String.valueOf(result.getUpdatenum14());
				}else if(30 == dayNum){
					linkerrprop=String.valueOf(result.getLinkerrprop30());
					indexdeadnum=String.valueOf(result.getIndexdeadnum30());
					updatestatus=result.getUpdatestatus30();
					updatenum=String.valueOf(result.getUpdatenum30());
				}
				bodyMap.put("sitecode", result.getSitecode()+"/"+result.getName());
				bodyMap.put("linkerrprop", linkerrprop+"%");
				bodyMap.put("indexdeadnum", indexdeadnum);
				bodyMap.put("updatestatus", updatestatus);
				bodyMap.put("updatenum", updatenum);
				bodyList.add(bodyMap);
			}
				chartMap.put("body", bodyList);
				chartMap.put("totalRecords", resultlist.size());
				chartMap.put("iTotalDisplayRecords", sitenum);
				chartMap.put("hasMoreItems",true);
				writerPrint(JSONObject.fromObject(chartMap).toString());
	}else{
		ArrayList<Object> list = new ArrayList<Object>();
		chartMap.put("body", list);
		chartMap.put("totalRecords", 0);
		chartMap.put("iTotalDisplayRecords", 0);
		chartMap.put("hasMoreItems",true);
		writerPrint(JSONObject.fromObject(chartMap).toString());
	}
	}
	/**
	 * 
	 * 描述:获取最后更新时间url
	 * 
	 * 作者：liujc@ucap.com.cn	2016-7-14下午4:50:56
	 */
	public void lastUpdateUrl(){
		try {
			UpdateContentCountRequest request=new UpdateContentCountRequest();
			request.setType(0);
			request.setSiteCode(orgSiteCode);
			request.setScanDate(DateUtils.getYesterdayStr());
			List<UpdateContentCount> list=updateContentCountServiceImpl.queryList(request);
			if(list.size()>0){
				chartMap.put("lastUpdateUrl", list.get(0).getLastUpdateUrl());
			}else{
				chartMap.put("lastUpdateUrl", "");
			}
			writerPrint(JSONObject.fromObject(chartMap).toString());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
	/**
	 * @Title: 未知状态控制
	 * @Description:
	 * @author liujc@ucap.com.cn	2016-10-9上午10:39:29
	 * @return
	 */
	public Integer unknownType(String updatecodes,String lastupdatedate){
		Integer updatecodesType=1;
		
		String[] arr = updatecodes.split(",");
		for(int i=0;i<arr.length;i++){
			if(arr[i].equals("200") || arr[i].equals("301") || arr[i].equals("302")){
				updatecodesType=1;//连通
				break;
			}else{
				updatecodesType=-1;//没有连通
			}
		}
		if(Integer.valueOf(lastupdatedate) ==0){
			updatecodesType=-1;//没有连通
		}
		return updatecodesType;
	}
	
	
	public Map<String, Object> getRsMap() {
		return rsMap;
	}
	public void setRsMap(Map<String, Object> rsMap) {
		this.rsMap = rsMap;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public Integer getDayNum() {
		return dayNum;
	}
	public void setDayNum(Integer dayNum) {
		this.dayNum = dayNum;
	}

	public String getReturnType() {
		return returnType;
	}

	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}
	public String getDesCode() {
		return desCode;
	}
	public void setDesCode(String desCode) {
		this.desCode = desCode;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getOrgSiteCode() {
		return orgSiteCode;
	}
	public void setOrgSiteCode(String orgSiteCode) {
		this.orgSiteCode = orgSiteCode;
	}
	public String getTabId() {
		return tabId;
	}
	public void setTabId(String tabId) {
		this.tabId = tabId;
	}
	public Integer getIsBm() {
		return isBm;
	}
	public void setIsBm(Integer isBm) {
		this.isBm = isBm;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getPageSizeVal() {
		return pageSizeVal;
	}
	public void setPageSizeVal(String pageSizeVal) {
		this.pageSizeVal = pageSizeVal;
	}
	public String getRadioVal() {
		return radioVal;
	}
	public void setRadioVal(String radioVal) {
		this.radioVal = radioVal;
	}
	
	
	
	
	
	
}
