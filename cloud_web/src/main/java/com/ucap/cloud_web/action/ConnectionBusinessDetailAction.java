package com.ucap.cloud_web.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.ucap.cloud_web.constant.ConnectionState;
import com.ucap.cloud_web.constant.ConnectionType;
import com.ucap.cloud_web.constant.QuestionType;
import com.ucap.cloud_web.constant.QueueScanningType;
import com.ucap.cloud_web.dto.ChannelPointRequest;
import com.ucap.cloud_web.dto.ConnectionAllRequest;
import com.ucap.cloud_web.dto.ConnectionBusinessDetailRequest;
import com.ucap.cloud_web.dto.ConnectionChannelDetailRequest;
import com.ucap.cloud_web.dto.DatabaseInfoRequest;
import com.ucap.cloud_web.dto.xstream.Root;
import com.ucap.cloud_web.entity.ChannelPoint;
import com.ucap.cloud_web.entity.ConnectionAll;
import com.ucap.cloud_web.entity.ConnectionBusinessDetail;
import com.ucap.cloud_web.entity.ConnectionChannelDetail;
import com.ucap.cloud_web.entity.DatabaseInfo;
import com.ucap.cloud_web.entity.Detail;
import com.ucap.cloud_web.entity.Result;
import com.ucap.cloud_web.service.IChannelPointService;
import com.ucap.cloud_web.service.IConnectionAllService;
import com.ucap.cloud_web.service.IConnectionBusinessDetailService;
import com.ucap.cloud_web.service.IConnectionChannelDetailService;
import com.ucap.cloud_web.service.IConnectionHomeDetailService;
import com.ucap.cloud_web.service.IDatabaseInfoService;
import com.ucap.cloud_web.util.CommonUtils;
import com.ucap.cloud_web.util.ExportExcel;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * <p>Description: 业务连通性详情</p>
 * <p>@Package：com.ucap.cloud_web.action </p>
 * <p>Title: ConnBusinessDetailAction </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：sunjiang </p>
 * <p>@date：2015年11月9日上午11:35:35 </p>
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class ConnectionBusinessDetailAction extends BaseAction{
	@Autowired
	private IConnectionAllService connectionAllServiceImpl;
	@Autowired
	private IDatabaseInfoService databaseInfoServiceImpl;
	@Autowired
	private IChannelPointService channelPointServiceImpl;
	@Autowired
	private IConnectionHomeDetailService connectionHomeDetailServiceImpl;
	@Autowired
	private IConnectionBusinessDetailService connectionBusinessDetailServiceImpl;
	@Autowired
	private IConnectionChannelDetailService connectionChannelDetailServiceImpl;
	
	/**
	 * 昨天的日期   yyyy-MM-DD
	 */
	private String yesterdayStr;
	/**
	 * 当天的日期   yyyy-MM-DD
	 */
	private String dateStr;
	
	private List<Object> connBusiList= new ArrayList<Object>();
	/**
	 * log日志加载
	 */
	private static Log logger =  LogFactory.getLog(ConnectionBusinessDetailAction.class);

	/**
	 * 成功次数
	 */
	private int successNum;
	/**
	 * 不连通次数
	 */
	private int errorNum;
	/**
	 * 成功占比
	 */
	private String successProportion="";
	/**
	 * 不连通占比
	 */
	private String errorProportion="";
	
	
	private String menuType;

	
	/** @Description: 页面
	 * @author zhurk --- 2015-11-19上午9:23:39     
	 * @return           
	*/
	public String index(){
		
		//siteCode处理由组织单位跳转到该页面时，session的修改
		String siteCode = request.getParameter("siteCode");
		
		if(StringUtils.isNotEmpty(siteCode)){
			setCurrentShiroUser(siteCode);
		}
		yesterdayStr=DateUtils.formatDate(new Date());
		siteCode = getCurrentUserInfo().getChildSiteCode();
		String que = connectionHomeDetailServiceImpl.queueTime(siteCode);
		String types = request.getParameter("types");
		String columnTB = request.getParameter("columnTB");
		if (StringUtils.isEmpty(types)) {
			types = "tp";
		}
		if (StringUtils.isEmpty(columnTB)) {
			columnTB = "tp";
		}
		request.setAttribute("types", types);
		request.setAttribute("columnTB", columnTB);
		request.setAttribute("que", que);
		request.setAttribute("dateStr", DateUtils.formatStandardDate(new Date()));
		request.setAttribute("yesterdayStr", DateUtils.getYesterdayStr());
		return "success";
	}
	/** @Description: 页面
	 * @author liukl --- 2017年4月7日09:45:34   
	 * @return           
	*/
	public String connectivity(){
		
		//siteCode处理由组织单位跳转到该页面时，session的修改
		String siteCode = request.getParameter("siteCode");
		
		String col=request.getParameter("col");
		if(StringUtils.isEmpty(col)){
			col="0";
		}
		if(StringUtils.isNotEmpty(siteCode)){
			setCurrentShiroUser(siteCode);
		}
		yesterdayStr=DateUtils.formatDate(new Date());
		siteCode = getCurrentUserInfo().getChildSiteCode();
		String que = connectionHomeDetailServiceImpl.queueTime(siteCode);
		String types = request.getParameter("types");
		String columnTB = request.getParameter("columnTB");
		if (StringUtils.isEmpty(types)) {
			types = "tp";
		}
		if (StringUtils.isEmpty(columnTB)) {
			columnTB = "tp";
		}
		request.setAttribute("col", col);
		request.setAttribute("types", types);
		request.setAttribute("columnTB", columnTB);
		request.setAttribute("que", que);
		request.setAttribute("dateStr", DateUtils.formatStandardDate(new Date()));
		request.setAttribute("yesterdayStr", DateUtils.getYesterdayStr());
		return "success";
	}
	/** @Description: 业务系统连通性统计分析
	 * @author zhurk --- 2015-11-19上午9:40:12                
	*/
	public void getBusinessStatistics(){

		try {
			logger.info("getBusinessStatistics begin");
			
			//从session中获取10位填报单位网站标识码
			String siteCode=getCurrentUserInfo().getChildSiteCode();
			if(StringUtils.isEmpty(siteCode)){
				siteCode=getCurrentUserInfo().getSiteCode();
			}
			
			//date是前台选中的日期
			String date = request.getParameter("date");
			logger.info("date:"+date);
			if(StringUtils.isEmpty(date)){
				//获取昨天的日期
				date=DateUtils.getYesterdayStr();
			}
			//昨天的时间  
			yesterdayStr = DateUtils.getYesterdayStr();
			//String siteBeginServiceDate="";
			
			
			HashMap<String, Object> map_list = new HashMap<String, Object>();
			
			ChannelPointRequest channelPointRequest=new ChannelPointRequest();
			channelPointRequest.setSiteCode(siteCode);
			channelPointRequest.setChannelType(0);//0 业务连通性  
			channelPointRequest.setStatus(1);//1监测中
			List<ChannelPoint> channelPointList=channelPointServiceImpl.queryList(channelPointRequest);
			//分类
			ConnectionAllRequest allRequest = new ConnectionAllRequest();
			allRequest.setSiteCode(siteCode);
			allRequest.setType(ConnectionType.BUSINESS.getCode());
			allRequest.setPageSize(Integer.MAX_VALUE);
			allRequest.setScanDate(date);
			if(channelPointList.size()>0){
				int count = 0;
				int success= 0;
				int error = 0;
				for(ChannelPoint channelPoint:channelPointList){
					if(StringUtils.isNotEmpty(channelPoint.getJumpPageUrl())){
						allRequest.setUrl(channelPoint.getJumpPageUrl());
					}else{
						allRequest.setUrl(channelPoint.getChannelUrl());
					}
					List<ConnectionAll> connAllList =connectionAllServiceImpl.queryList(allRequest);
					if(connAllList.size() > 0){
						
						for (ConnectionAll connectionAll : connAllList) {
							HashMap<String, Object> map = new HashMap<String, Object>();
							int successNum_1 = connectionAll.getSuccessNum();
							int errorNum_1 = connectionAll.getErrorNum();
							String successProportion_1 =StringUtils.getPrettyNumber(connectionAll.getSuccessProportion());
					
							String name_1 = connectionAll.getName()==null?"":connectionAll.getName();
							String url_1 = connectionAll.getUrl();
							String errorProportion_1 = StringUtils.getPrettyNumber(connectionAll.getErrorProportion());
							
			
							map.put("successNum", successNum_1);
							map.put("errorNum", errorNum_1);
							map.put("successProportion", successProportion_1);
							map.put("name", name_1);
							map.put("url", url_1);
							map.put("errorProportion", errorProportion_1);
							count += successNum_1+errorNum_1;
							success +=successNum_1;
							error +=errorNum_1;
							connBusiList.add(map);
						}
						successNum = success;
						errorNum = error;
						successProportion = StringUtils.getPrettyNumber(StringUtils.formatDouble(2,(double)success/count*100));
						errorProportion = StringUtils.getPrettyNumber(StringUtils.formatDouble(2,(double)error/count*100));
						
					}else{
						//通过网站标识码查询databaseInfo表，判断客户是否为收费客户
						DatabaseInfoRequest databaseRequest=new DatabaseInfoRequest();
						databaseRequest.setSiteCode(siteCode);
						List<DatabaseInfo> databaseInfoList=  databaseInfoServiceImpl.queryList(databaseRequest);
						if(databaseInfoList!=null && databaseInfoList.size()>0){
							DatabaseInfo databaseInfo=databaseInfoList.get(0);
							if(databaseInfo.getIsScan()==1){
								Integer qu = databaseInfo.getQueue();
								if(qu != 0 && qu != null){
									String que = QueueScanningType.getNameByCode(qu);
									successNum = Integer.valueOf(que);
								}
							}else{
								//1天一次
								successNum=1;
							}
							HashMap<String, Object> map = new HashMap<String, Object>();
							int errorNum_1=0;
							map.put("successNum", successNum);
							map.put("errorNum", 0);
							map.put("successProportion", 100.00);
							map.put("name", channelPoint.getChannelName());
							map.put("url", allRequest.getUrl());
							map.put("errorProportion", 0.00);
							count += successNum+errorNum_1;
							success +=successNum;
							error +=errorNum_1;
							connBusiList.add(map);
							successNum = success;
							errorNum = error;
							successProportion = StringUtils.getPrettyNumber(StringUtils.formatDouble(2,(double)success/count*100));
							errorProportion = StringUtils.getPrettyNumber(StringUtils.formatDouble(2,(double)error/count*100));
						}
					}
				}
			}else{
				successProportion="false";
				errorProportion="false";
			}
			map_list.put("date", date);
			map_list.put("dateStr", DateUtils.formatStandardDate(new Date()));
			map_list.put("successNum",successNum);
			map_list.put("errorNum",errorNum);
			map_list.put("successProportion",successProportion);
			map_list.put("errorProportion",errorProportion);
			map_list.put("connBusiList",connBusiList);
			logger.info("map_list:"+map_list);
			logger.info("getBusinessStatistics end");
			writerPrint(JSONObject.fromObject(map_list).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * 调接口数据
	 * @Description:分页列表
	 * @author qinjy
	 */
	public void queryList(){

		//从session中获取10位填报单位网站标识码
		String siteCode=getCurrentUserInfo().getChildSiteCode();
		if(StringUtils.isEmpty(siteCode)){
			siteCode=getCurrentUserInfo().getSiteCode();
		}
		String type = "";							 //      接口： 1 "首页连通性";3 "业务系统连通性";2 "关键栏目连通性"
		String code = request.getParameter("code");  // 	本地库：1 "首页连通性";2 "业务系统连通性";3 "关键栏目连通性"
		if (StringUtils.isNotEmpty(code)) {
			type = code;
		}
		String date = request.getParameter("date");
		dateStr = DateUtils.formatStandardDate(new Date()); // 当天时间
		if(StringUtils.isEmpty(date)){
			date = dateStr;
		}
		try{

			HashMap<String, Object> map_list = new HashMap<String, Object>();
			ArrayList<Object> list = new ArrayList<Object>();
			ChannelPointRequest res = new ChannelPointRequest();
			res.setPageSize(Integer.MAX_VALUE);
			List<ChannelPoint> channelList = new ArrayList<ChannelPoint>();
			
			Root root = connectionAllServiceImpl.connectivityByRoot(siteCode, dateStr, type);
			String scanTime = "";
			String stateName = "";
			String url = "";
			String encodeurl = "";
			String systemName = "";
			
			//列表
			Date d = DateUtils.parseStandardDate(dateStr);   // 当天时间  
			if(StringUtils.isEmpty(date) && date == ""){
				date = yesterdayStr;
			}
			Date da = DateUtils.parseStandardDate(date);     // 前台传值时间
            if (d.getTime() <= da.getTime()) {
        		if(root.getResponse().equals("success")){
    				List<Result> rootlist = root.getResults();
					if (rootlist.size() > 0 && rootlist != null) {
						int num = 0;
						for (Result re : rootlist) {
							url = re.getUrl();
							encodeurl = re.getEncodeurl(); // 加密后的url
							res.setEncodeUrl(encodeurl);
							channelList = channelPointServiceImpl.queryList(res);
							if (channelList.size() > 0 && channelList != null) {
								ChannelPoint ca = channelList.get(0);
								systemName = ca.getChannelName(); // 栏目名称
								if (re.getDetails().size() > 0 && re.getDetails() != null) {
									for (int i = 0; i < re.getDetails().size(); i++) {
										HashMap<String, Object> map = new HashMap<String, Object>();
										Detail de = re.getDetails().get(i);
										scanTime = de.getStime();
										map.put("scanTime", scanTime);
										stateName = ConnectionState.TIMEOUT.getName();
										map.put("stateName", stateName);

										String questionCode = de.getCode();
										if (StringUtils.isNotEmpty(questionCode)) {
											map.put("questionCode", questionCode);
											map.put("questionDescribe",
													questionCode + QuestionType.getNameByCode(questionCode));
										} else {
											map.put("questionCode", "");
											map.put("questionDescribe", "");
										}
										num++;
										map.put("dataNumber", num);
										map.put("systemName", systemName);
										map.put("url", url);
										list.add(map);
									}
								}
							}
						}
    				}
    			}
            }else{
            	List<QueryOrder> querySiteList=new ArrayList<QueryOrder>();
        		QueryOrder siteQueryOrder=new QueryOrder("scan_date",QueryOrderType.DESC);
    			querySiteList.add(siteQueryOrder);
				if (code.equals("2")) { // 2 "关键栏目连通性"
            		ConnectionChannelDetailRequest connChannelrequest = new ConnectionChannelDetailRequest();
            		connChannelrequest.setState(ConnectionState.TIMEOUT.getCode());
            		connChannelrequest.setScanDate(date);
            		connChannelrequest.setSiteCode(siteCode);
            		connChannelrequest.setPageSize(Integer.MAX_VALUE);
            		connChannelrequest.setQueryOrderList(querySiteList);
            		PageVo<ConnectionChannelDetail> query = connectionChannelDetailServiceImpl.query(connChannelrequest);
            		List<ConnectionChannelDetail> data = query.getData();
        			if(data != null && data.size() > 0){
						int i = 0;
        				for(ConnectionChannelDetail c : data){
        					HashMap<String, Object> map = new HashMap<String, Object>();
        					map.put("scanTime", c.getScanTime());
							stateName = ConnectionState.TIMEOUT.getName();
							map.put("stateName", stateName);

							String questionCode = c.getQuestionCode();
							if(StringUtils.isNotEmpty(questionCode)){
								map.put("questionCode", questionCode);
								map.put("questionDescribe", questionCode+QuestionType.getNameByCode(questionCode));
							}else{
								map.put("questionCode", "");
								map.put("questionDescribe", "");
							}
							i++;
							map.put("dataNumber", i);
							map.put("systemName", c.getChannelName());
							map.put("url", c.getUrl());
							list.add(map);
            			}
        			}
				} else if (code.equals("3")) { // 3 "业务系统连通性"
            		ConnectionBusinessDetailRequest connBusiRequst = new ConnectionBusinessDetailRequest();
            		connBusiRequst.setState(ConnectionState.TIMEOUT.getCode());
            		connBusiRequst.setScanDate(date);
            		connBusiRequst.setSiteCode(siteCode);
            		connBusiRequst.setPageSize(Integer.MAX_VALUE);
            		connBusiRequst.setQueryOrderList(querySiteList);
            		PageVo<ConnectionBusinessDetail> query = connectionBusinessDetailServiceImpl.query(connBusiRequst);
        			List<ConnectionBusinessDetail> data = query.getData();
        			if(data != null && data.size() > 0){
        				int i = 0;
        				for(ConnectionBusinessDetail c : data){
        					HashMap<String, Object> map = new HashMap<String, Object>();
        					map.put("scanTime", c.getScanTime());
							stateName = ConnectionState.TIMEOUT.getName();
							map.put("stateName", stateName);

							String questionCode = c.getQuestionCode();
							if(StringUtils.isNotEmpty(questionCode)){
								map.put("questionCode", questionCode);
								map.put("questionDescribe", questionCode+QuestionType.getNameByCode(questionCode));
							}else{
								map.put("questionCode", "");
								map.put("questionDescribe", "");
							}
							i++;
							map.put("dataNumber", i);
							map.put("systemName", c.getSystemName());
							map.put("url", url);
							list.add(map);
            			}
        			}
            	}
            }
	
			
			map_list.put("body", list);
			map_list.put("hasMoreItems",true);
			writerPrint(JSONObject.fromObject(map_list).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**调接口数据
	 * @Description: 业务连通性的时间坐标折线图
	 * @author qinjy
	 */
	public void getBusinessTrendLine(){
		//从session中获取10位填报单位网站标识码
		String siteCode=getCurrentUserInfo().getChildSiteCode();
		if(StringUtils.isEmpty(siteCode)){
			siteCode=getCurrentUserInfo().getSiteCode();
		}
		String date = request.getParameter("date");
		try {
			//获取前90天的开始时间
			String nextDay = DateUtils.getNextDay(new Date(), -14);
			//获取昨天的日期
			String endDate = DateUtils.getNextDay(new Date(), -1);
			//获取90天的所有日期
			List<Date> dateList=getDateTimeTwoDay(nextDay,endDate);
			
			// 将参数封装到map集合中
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("startDate", nextDay);
			paramMap.put("endDate", endDate);
			paramMap.put("siteCode", siteCode);
			paramMap.put("type", ConnectionType.BUSINESS.getCode());

			ArrayList<Object> list = new ArrayList<Object>();
			List<ConnectionAllRequest> queryList = connectionAllServiceImpl.getHomeBar(paramMap);
			
			if(queryList!=null && queryList.size()>0){
				for(int i=0;i<dateList.size();i++){
					String dateStr1=DateUtils.formatStandardDate(dateList.get(i));
					Map<String, Object> map=new HashMap<String, Object>();
					for(int j=0;j<queryList.size();j++){
						ConnectionAllRequest connAll = queryList.get(j);
						if(connAll.getScanDate().equals(dateStr1)){
							map.put("errorNum", connAll.getBuseNum());
							map.put("scanDate", dateStr1);
						}
					}
					if(map.size()==0){
						map.put("errorNum", 0);
						map.put("scanDate", dateStr1);
					}
					list.add(map);
				}
			}else{
				for(int i=0;i<dateList.size();i++){
					String dateStr1=DateUtils.formatStandardDate(dateList.get(i));
					Map<String, Object> map=new HashMap<String, Object>();
					map.put("errorNum", 0);
					map.put("scanDate", dateStr1);
					list.add(map);
				}
			}
			
			dateStr = DateUtils.formatStandardDate(new Date()); // 当天时间
			if(StringUtils.isNotEmpty(date)){
				dateStr = date;
			}
			Integer failNum = 0;
			String type = ConnectionType.CHANNEL.getCode().toString();  // 本地  2 业务 3 栏目 /接口  2 栏目  3 业务
			Root root = connectionAllServiceImpl.connectivityByRoot(siteCode, dateStr, type);
			if(root.getResponse().equals("success")){
				List<Result> rootlist = root.getResults();
				if (rootlist.size() > 0 && rootlist != null) {
					for (Result re : rootlist) {
						if (StringUtils.isNotEmpty(re.getFailnum())) {
							failNum += Integer.valueOf(re.getFailnum());
						}
					}
				}
				if(StringUtils.isNotEmpty(root.getDate())){ 
					dateStr = root.getDate();
				}else {
					dateStr = DateUtils.formatStandardDate(new Date());  //  当天时间
				}
				list.add("{errorNum=" + failNum + ", scanDate=" + dateStr + "}");
			}
			logger.info("业务系统连通性list="+list);
			writerPrint(JSONArray.fromObject(list).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**调接口数据
	 * @Description: 业务连通性的时间坐标折线图以及列表
	 * @author liukl
	 */
	public void getBusinessTrendLineLAndList(){
		Map<String, Object> map_list = new HashMap<String, Object>();
//		String date = request.getParameter("date");
		try {
			String siteCode=getCurrentUserInfo().getChildSiteCode();
			if(StringUtils.isEmpty(siteCode)){
				siteCode=getCurrentUserInfo().getSiteCode();
			}
			//获取前90天的开始时间
			String nextDay = DateUtils.getNextDay(new Date(), -14);
			//获取昨天的日期
			String endDate = DateUtils.getNextDay(new Date(), -1);
			//获取90天的所有日期
			List<Date> dateList=getDateTimeTwoDay(nextDay,endDate);
			
			// 将参数封装到map集合中
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("startDate", nextDay);
			paramMap.put("endDate", endDate);
			paramMap.put("siteCode", siteCode);
			paramMap.put("type", ConnectionType.BUSINESS.getCode());

			ArrayList<Object> list = new ArrayList<Object>();
			List<ConnectionAllRequest> queryList = connectionAllServiceImpl.getHomeBar(paramMap);
			
			if(queryList!=null && queryList.size()>0){
				for(int i=0;i<dateList.size();i++){
					String dateStr1=DateUtils.formatStandardDate(dateList.get(i));
					Map<String, Object> map=new HashMap<String, Object>();
					for(int j=0;j<queryList.size();j++){
						ConnectionAllRequest connAll = queryList.get(j);
						if(connAll.getScanDate().equals(dateStr1)){
							map.put("errorNum", connAll.getBuseNum());
							map.put("scanDate", dateStr1);
						}
					}
					if(map.size()==0){
						map.put("errorNum", 0);
						map.put("scanDate", dateStr1);
					}
					list.add(map);
				}
			}else{
				for(int i=0;i<dateList.size();i++){
					String dateStr1=DateUtils.formatStandardDate(dateList.get(i));
					Map<String, Object> map=new HashMap<String, Object>();
					map.put("errorNum", 0);
					map.put("scanDate", dateStr1);
					list.add(map);
				}
			}
			
			dateStr = DateUtils.formatStandardDate(new Date()); // 当天时间
//			if(StringUtils.isNotEmpty(date)){
//				dateStr = date;
//			}
			Integer failNum = 0;
			String type = ConnectionType.CHANNEL.getCode().toString();  // 本地  2 业务 3 栏目 /接口  2 栏目  3 业务
			Root root = connectionAllServiceImpl.connectivityByRoot(siteCode, dateStr, type);
			if(root.getResponse().equals("success")){
				List<Result> rootlist = root.getResults();
				if (rootlist.size() > 0 && rootlist != null) {
					for (Result re : rootlist) {
						if (StringUtils.isNotEmpty(re.getFailnum())) {
							failNum += Integer.valueOf(re.getFailnum());
						}
					}
				}
				if(StringUtils.isNotEmpty(root.getDate())){ 
					dateStr = root.getDate();
				}else {
					dateStr = DateUtils.formatStandardDate(new Date());  //  当天时间
				}
				list.add("{errorNum=" + failNum + ", scanDate=" + dateStr + "}");
			}
			logger.info("业务系统连通性list="+list);
			
			
			//上面是图表，下面是列表
			String code = request.getParameter("code");
			String date = request.getParameter("date");
			dateStr = DateUtils.formatStandardDate(new Date()); // 当天时间
			if(StringUtils.isEmpty(date)){
				date = dateStr;
			}
			
			ArrayList<Object> mergeList = new ArrayList<Object>();
			ChannelPointRequest res = new ChannelPointRequest();
			res.setPageSize(Integer.MAX_VALUE);
			List<ChannelPoint> channelList = new ArrayList<ChannelPoint>();
			
			String scanTime = "";
			String stateName = "";
			String url = "";
			String encodeurl = "";
			String systemName = "";
			
			Date d = DateUtils.parseStandardDate(dateStr);   // 当天时间  
			if(StringUtils.isEmpty(date) && date == ""){
				date = yesterdayStr;
			}
			Date da = DateUtils.parseStandardDate(date);     // 前台传值时间
            if (d.getTime() <= da.getTime()) {
        		if(root.getResponse().equals("success")){
    				List<Result> rootlist = root.getResults();
					if (rootlist.size() > 0 && rootlist != null) {
						int num = 0;
						for (Result re : rootlist) {
							url = re.getUrl();
							encodeurl = re.getEncodeurl(); // 加密后的url
							res.setEncodeUrl(encodeurl);
							channelList = channelPointServiceImpl.queryList(res);
							if (channelList.size() > 0 && channelList != null) {
								ChannelPoint ca = channelList.get(0);
								systemName = ca.getChannelName(); // 栏目名称
								if (re.getDetails().size() > 0 && re.getDetails() != null) {
									for (int i = 0; i < re.getDetails().size(); i++) {
										HashMap<String, Object> map = new HashMap<String, Object>();
										Detail de = re.getDetails().get(i);
										scanTime = de.getStime();
										map.put("scanTime", scanTime);
										stateName = ConnectionState.TIMEOUT.getName();
										map.put("stateName", stateName);

										String questionCode = de.getCode();
										if (StringUtils.isNotEmpty(questionCode)) {
											map.put("questionCode", questionCode);
											map.put("questionDescribe",
													questionCode + QuestionType.getNameByCode(questionCode));
										} else {
											map.put("questionCode", "");
											map.put("questionDescribe", "");
										}
										num++;
										map.put("dataNumber", num);
										map.put("systemName", systemName);
										map.put("url", url);
										mergeList.add(map);
									}
								}
							}
						}
    				}
    			}
            }else{
            	List<QueryOrder> querySiteList=new ArrayList<QueryOrder>();
        		QueryOrder siteQueryOrder=new QueryOrder("scan_date",QueryOrderType.DESC);
    			querySiteList.add(siteQueryOrder);
    			if (code.equals("3")) { // 3 "业务系统连通性"
            		ConnectionBusinessDetailRequest connBusiRequst = new ConnectionBusinessDetailRequest();
            		connBusiRequst.setState(ConnectionState.TIMEOUT.getCode());
            		connBusiRequst.setScanDate(date);
            		connBusiRequst.setSiteCode(siteCode);
            		connBusiRequst.setPageSize(Integer.MAX_VALUE);
            		connBusiRequst.setQueryOrderList(querySiteList);
            		PageVo<ConnectionBusinessDetail> query = connectionBusinessDetailServiceImpl.query(connBusiRequst);
        			List<ConnectionBusinessDetail> data = query.getData();
        			if(data != null && data.size() > 0){
        				int i = 0;
        				for(ConnectionBusinessDetail c : data){
        					HashMap<String, Object> map = new HashMap<String, Object>();
        					map.put("scanTime", c.getScanTime());
							stateName = ConnectionState.TIMEOUT.getName();
							map.put("stateName", stateName);

							String questionCode = c.getQuestionCode();
							if(StringUtils.isNotEmpty(questionCode)){
								map.put("questionCode", questionCode);
								map.put("questionDescribe", questionCode+QuestionType.getNameByCode(questionCode));
							}else{
								map.put("questionCode", "");
								map.put("questionDescribe", "");
							}
							i++;
							map.put("dataNumber", i);
							map.put("systemName", c.getSystemName());
							map.put("url", url);
							list.add(map);
            			}
        			}
				} 
            }
			
            map_list.put("body", mergeList);
            map_list.put("list", list);
			map_list.put("hasMoreItems",true);
			writerPrint(JSONObject.fromObject(map_list).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	} 
	

	/**调接口数据 
	 * @Description: 业务系统连通性excel导出
	 * @author qinjy             
	*/
	public  void businessExcel(){
		//从session中获取10位填报单位网站标识码
		String siteCode=getCurrentUserInfo().getChildSiteCode();
		if(StringUtils.isEmpty(siteCode)){
			siteCode=getCurrentUserInfo().getSiteCode();
		}
		String type = "";
		String code = request.getParameter("code");  // 	1 "首页连通性";2 "业务系统连通性";3 "关键栏目连通性"
		if (StringUtils.isNotEmpty(code)) {
			type = code;
		}
		yesterdayStr = DateUtils.getYesterdayStr();
		String date = request.getParameter("date");
		dateStr = DateUtils.formatStandardDate(new Date()); // 当天时间
		if(StringUtils.isEmpty(date)){
			dateStr = date;
		}
		ArrayList<Object[]> list = new ArrayList<Object[]>();
		Object[] obj1 = new Object[]{"序号","业务系统名称","URL","时间","连通状态","问题描述"};
		list.add(obj1);
		String fileName ="网站连通性-业务系统连通性监测结果("+dateStr+").xls";
		String title = "业务系统连通性监测结果"; 
		try {
			Date d = DateUtils.parseStandardDate(dateStr);   // 当天时间  
			if(StringUtils.isEmpty(date) && date == ""){
				date = yesterdayStr;
			}
			Date da = DateUtils.parseStandardDate(date);     // 前台传值时间
            if (d.getTime() <= da.getTime()) {
            	list = connectionAllServiceImpl.connectivityList(siteCode, dateStr, type, list);
            }else{
            	List<QueryOrder> querySiteList=new ArrayList<QueryOrder>();
        		QueryOrder siteQueryOrder=new QueryOrder("scan_date",QueryOrderType.DESC);
    			querySiteList.add(siteQueryOrder);
        		ConnectionBusinessDetailRequest connBusiRequst = new ConnectionBusinessDetailRequest();
        		connBusiRequst.setState(ConnectionState.TIMEOUT.getCode());
        		connBusiRequst.setScanDate(date);
        		connBusiRequst.setSiteCode(siteCode);
        		connBusiRequst.setPageSize(Integer.MAX_VALUE);
        		connBusiRequst.setQueryOrderList(querySiteList);
        		PageVo<ConnectionBusinessDetail> query = connectionBusinessDetailServiceImpl.query(connBusiRequst);
    			List<ConnectionBusinessDetail> data = query.getData();
    			if(data != null && data.size() > 0){
    				int i = 0;
    				for(ConnectionBusinessDetail c : data){
    					Object[] obj = new Object[6];
						i++;
						obj[0] = i;
						obj[1] = c.getSystemName();
						obj[2] = CommonUtils.setHttpUrl(c.getUrl()); //  判断是否有http头
						obj[3] = c.getScanTime();
						obj[4] = ConnectionState.TIMEOUT.getName();
						String questionCode = c.getQuestionCode();
						if(StringUtils.isNotEmpty(questionCode)){
							obj[5] = questionCode+"　　"+QuestionType.getNameByCode(questionCode);
						}else{
							obj[5] = "";
						}
						list.add(obj);
        			}
    			}
            }
			ExportExcel.websiteConnOtherExcel(fileName, title, list);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("业务连通性 excel 导出"+"  siteCode :"+siteCode);
		}
		
	}
	/**
	 * @Description: 
	 * @author cuichx --- 2016-3-8下午12:52:35     
	 * @param beginDate  YYYY-MM-DD hh:mm:ss
	 * @param endDate
	 * @return
	 */
	public List<Date> getDateTimeTwoDay(String beginDate,String endDate){
		
		Date begin=DateUtils.parseStandardDate(beginDate);
		Date end=DateUtils.parseStandardDate(endDate);
		List<Date> lDate = new ArrayList<Date>(); 
		lDate.add(begin);// 把开始时间加入集合  
        Calendar cal = Calendar.getInstance();  
        // 使用给定的 Date 设置此 Calendar 的时间  
        cal.setTime(begin);  
        boolean bContinue = true;  
        while (bContinue) {  
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量  
            cal.add(Calendar.DATE, 1);  
            // 测试此日期是否在指定日期之后  
            if (DateUtils.parseStandardDate(endDate).after(cal.getTime())) {  
                lDate.add(cal.getTime());  
            } else {  
                break;  
            }  
        }  
        lDate.add(end);// 把结束时间加入集合  
		
		return lDate;
	}
	
	public String getYesterdayStr() {
		return yesterdayStr;
	}
	public void setYesterdayStr(String yesterdayStr) {
		this.yesterdayStr = yesterdayStr;
	}
	public List<Object> getConnBusiList() {
		return connBusiList;
	}
	public void setConnBusiList(List<Object> connBusiList) {
		this.connBusiList = connBusiList;
	}
	public IConnectionAllService getConnectionAllServiceImpl() {
		return connectionAllServiceImpl;
	}
	public void setConnectionAllServiceImpl(
			IConnectionAllService connectionAllServiceImpl) {
		this.connectionAllServiceImpl = connectionAllServiceImpl;
	}
	public int getSuccessNum() {
		return successNum;
	}
	public void setSuccessNum(int successNum) {
		this.successNum = successNum;
	}
	public int getErrorNum() {
		return errorNum;
	}
	public void setErrorNum(int errorNum) {
		this.errorNum = errorNum;
	}
	public String getSuccessProportion() {
		return successProportion;
	}
	public void setSuccessProportion(String successProportion) {
		this.successProportion = successProportion;
	}
	public String getErrorProportion() {
		return errorProportion;
	}
	public void setErrorProportion(String errorProportion) {
		this.errorProportion = errorProportion;
	}
	public String getMenuType() {
		return menuType;
	}
	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}
}
