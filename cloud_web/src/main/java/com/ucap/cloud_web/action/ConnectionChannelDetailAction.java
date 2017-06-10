package com.ucap.cloud_web.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
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
import com.ucap.cloud_web.common.ComparatorColumnHashMap;
import com.ucap.cloud_web.common.DicUtils;
import com.ucap.cloud_web.constant.ConnectionState;
import com.ucap.cloud_web.constant.ConnectionType;
import com.ucap.cloud_web.constant.DatabaseTreeInfoType;
import com.ucap.cloud_web.constant.QuestionType;
import com.ucap.cloud_web.constant.QueueScanningType;
import com.ucap.cloud_web.constant.QueueType;
import com.ucap.cloud_web.dto.ChannelPointRequest;
import com.ucap.cloud_web.dto.ConnectionAllRequest;
import com.ucap.cloud_web.dto.ConnectionChannelDetailRequest;
import com.ucap.cloud_web.dto.DatabaseInfoRequest;
import com.ucap.cloud_web.dto.xstream.Root;
import com.ucap.cloud_web.dtoResponse.ConnectionAllHomeResponse;
import com.ucap.cloud_web.entity.ChannelPoint;
import com.ucap.cloud_web.entity.ConnectionAll;
import com.ucap.cloud_web.entity.ConnectionChannelDetail;
import com.ucap.cloud_web.entity.DatabaseInfo;
import com.ucap.cloud_web.entity.Detail;
import com.ucap.cloud_web.entity.DicConfig;
import com.ucap.cloud_web.entity.DicItem;
import com.ucap.cloud_web.entity.Result;
import com.ucap.cloud_web.service.IChannelPointService;
import com.ucap.cloud_web.service.IConnectionAllHomeService;
import com.ucap.cloud_web.service.IConnectionAllService;
import com.ucap.cloud_web.service.IConnectionChannelDetailService;
import com.ucap.cloud_web.service.IConnectionHomeDetailService;
import com.ucap.cloud_web.service.IDatabaseInfoService;
import com.ucap.cloud_web.service.IDicConfigService;
import com.ucap.cloud_web.util.CommonUtils;
import com.ucap.cloud_web.util.ExportExcel;
/**
 * <p>Description: 关键栏目连通性详情</p>
 * <p>@Package：com.ucap.cloud_web.action </p>
 * <p>Title: ConnectionChannelDetailAction </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：sunjiang </p>
 * <p>@date：2015年11月10日上午10:29:44 </p>
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class ConnectionChannelDetailAction extends BaseAction{
	/**
	 * log日志加载
	 */
	private static Log logger = LogFactory.getLog(ConnectionChannelDetailAction.class);
	
	@Autowired
	private IConnectionChannelDetailService connectionChannelDetailServiceImpl;
	@Autowired
	private IConnectionAllService connectionAllServiceImpl;
	
	@Autowired
	private IDatabaseInfoService databaseInfoServiceImpl;
	@Autowired
	private IChannelPointService channelPointServiceImpl;
	@Autowired
	private IConnectionHomeDetailService connectionHomeDetailServiceImpl;
	@Autowired
	private DicUtils dicUtils;
	@Autowired
	private IConnectionAllHomeService connectionAllHomeServiceImpl;
	@Autowired
	private IDicConfigService dicConfigServiceImpl;
	private List<Object> connChannelList= new ArrayList<Object>();
	/**
	 * 昨天的日期   yyyy-MM-DD
	 */
	private String yesterdayStr;
	/**
	 * 当天的日期   yyyy-MM-DD
	 */
	private String dateStr;
	
	private List<Object> connChanneList= new ArrayList<Object>();
	
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
	//0:实时 1：非实时
	private String realTimeType;
	private String chartFailNum;
	/************************************* 栏目检测-关键栏目连通率 开始********************************************/
	
	public String indexOrg() {
		String col = request.getParameter("col"); // 定位tab
		if (StringUtils.isEmpty(col)) {
			col = "col";
		}
		List<DicItem> dicList = dicUtils.getDictList("siteType");
		request.setAttribute("col", col);
		request.setAttribute("dicList", dicList); // 网站类别
		request.setAttribute("yesterday", DateUtils.getYesterdayStr()); // 昨天
		return "success";
	}
	
	/**
	 * 
	 * @描述:关键栏目/业务栏目 连通率excel导出
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2017年1月10日下午7:19:13
	 */
	public void columnConnectionTableExcel() {
		ArrayList<Object[]> list = new ArrayList<Object[]>();
		String siteType = request.getParameter("siteType"); // 网站类别
		String queue = request.getParameter("monitoringId"); // 监测频率
		String startDate = request.getParameter("startDate"); // 开始时间
		String endDate = request.getParameter("endDate"); // 结束时间
		String type = request.getParameter("type"); // 类型
													// 1首页面连通性、2、业务系统连通性、3关键栏目连通性
		List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();

		try {
			// 获取当前组织机构编码
			String siteCode = getCurrentSiteCode();

			Object[] obj1 = new Object[] {};
			String fileName = "";
			String title = "";
			if (type.equals("2")) {
				obj1 = new Object[] { "网站标识码", "网站名称", "首页url", "业务系统数量", "监测频率", "连接总次数", "连通次数", "连通率", "连不通次数",
						"不连通率" };
				fileName = "栏目检测-业务栏目连通率(" + DateUtils.formatStandardDate(new Date()) + ").xls";
				title = "业务栏目连通率";
			} else if (type.equals("3")) {
				obj1 = new Object[] { "网站标识码", "网站名称", "首页url", "关键栏目数量", "监测频率", "连接总次数", "连通次数", "连通率", "连不通次数",
						"不连通率" };
				fileName = "栏目检测-关键栏目连通率(" + DateUtils.formatStandardDate(new Date()) + ").xls";
				title = "关键栏目连通率";
			}

			list.add(obj1);

			Integer qu = 0;
			String quName = "";
			float conNum = 0; // 连接数
			float connectedNum = 0; // 连通数
			float noConnectedNum = 0; // 连不通数
			String connectedRate = ""; // 连通率
			String noConnectedRate = ""; // 不连通率
			HashMap<String, Object> hashMap = new HashMap<String, Object>();
			hashMap.put("orgSiteCode", siteCode);
			hashMap.put("startDate", startDate);
			hashMap.put("endDate", endDate);
			hashMap.put("type", type);
			hashMap.put("isLink", DatabaseTreeInfoType.ISLINK.getCode());
			if (StringUtils.isNotEmpty(siteType) && !siteType.equals("0")) {
				hashMap.put("siteType", siteType);
			}
			if (StringUtils.isNotEmpty(queue) && !queue.equals("0")) {
				hashMap.put("queue", queue);
			}
//			String cacheConnectionAllExcel = CacheType.MONITORING_CONNECTIONALLEXCEL.getName();
//			String conkey = cacheConnectionAllExcel + siteCode + startDate + endDate + queue + siteType + type; // 缓存名
//			List<ConnectionAllRequest> queryList = (List<ConnectionAllRequest>) MonitoringCacheUtils.get(conkey); // 查询缓存中是否存在
//			if (queryList == null) {
//				queryList = connectionAllServiceImpl.getwebConnectedList(hashMap);
//				MonitoringCacheUtils.put(conkey, queryList); // 将数据存到缓存中
//			}
			List<ConnectionAllHomeResponse> queryList = connectionAllHomeServiceImpl.getConnectionAllHomeList(hashMap);
			if (queryList != null && queryList.size() > 0) {
				for (int i = 0; i < queryList.size(); i++) {
					ConnectionAllHomeResponse con = queryList.get(i);
					Map<String, Object> item = new HashMap<String, Object>();
					item.put("siteCode", con.getSiteCode());
					item.put("siteName", con.getSiteName());
					
					if (StringUtils.isNotEmpty(con.getJumpPageUrl())) {
						item.put("url", con.getJumpPageUrl());// 跳转url地址
					} else {
						item.put("url", con.getHomePageUrl());// 跳转url地址
					}
					item.put("channelNum", con.getChannelNum());
						qu = con.getQueue();
						quName = QueueType.getNameByCode(qu);
						item.put("connectionSum", con.getConnectionSum());
						item.put("successNum", con.getSuccessNum());
						conNum = con.getConnectionSum();
						connectedNum = con.getSuccessNum();
						noConnectedNum = con.getErrorNum();
						if (connectedNum == 0) { // 被除数为0的情况下
							connectedRate = "0";
						} else if (conNum == 0) { // 除数为0的情况下
							connectedRate = "0";
						} else {
							connectedRate = totalPercentage(connectedNum, conNum);
							Float connectedRateF = Float.valueOf(connectedRate);
							if (connectedRate.equals("0")) { // 基数太小得到的数据为0.00默认改为0.01
								connectedRate = "0.01";
							}else if(connectedRateF > 100){
								connectedRate = "100";
							}else if(connectedRateF < 0){
								connectedRate = "0";
							}
						}
						item.put("connectedRate", connectedRate + "%");
						item.put("errorNum", con.getErrorNum());
						if(connectedNum == 0 && noConnectedNum == 0 ){
							item.put("quName", "未监测");
						}else{
							item.put("quName", quName + "一次");
						}
						if(connectedNum == 0 && noConnectedNum == 0 ){
							noConnectedRate = "0";
						}else if(connectedNum == conNum){
							noConnectedRate="0";
						}else if (noConnectedNum == 0) { // 被除数为0的情况下
							noConnectedRate = "100";
						} else if (conNum == 0) { // 除数为0的情况下
							noConnectedRate = "0";
						} else {
							noConnectedRate = totalPercentage(noConnectedNum, conNum);
							Float noConnectedRateF = Float.valueOf(noConnectedRate);
							if (noConnectedRate.equals("0")) { // 基数太小得到的数据为0.00默认改为0.01
								noConnectedRate = "0.01";
							}else if(noConnectedRateF > 100){
								noConnectedRate = "100";
							}else if(noConnectedRateF < 0){
								noConnectedRate = "0";
							}
						} 
						item.put("errorThan", noConnectedRate);
					items.add(item);
				}
			}
			ComparatorColumnHashMap comparatorHashMapTotal = new ComparatorColumnHashMap();
			Collections.sort(items, comparatorHashMapTotal);
			if(items != null && items.size()>0){
				for (Map<String, Object> map : items) {
					Object[] obj = new Object[10];
					obj[0] = (String) map.get("siteCode");
					obj[1] = (String) map.get("siteName");
					obj[2] = (String) map.get("url");
					obj[3] = (Integer) map.get("channelNum");
					if((Integer) map.get("channelNum") == 0){
						obj[4] = "-";
						obj[5] = "-";
						obj[6] = "-";
						obj[7] = "-";
						obj[8] = "-";
						obj[9] = "-";
					}else{
						obj[4] = (String) map.get("quName");
						obj[5] = (Integer) map.get("connectionSum");
						obj[6] = (Integer) map.get("successNum");
						obj[7] = (String) map.get("connectedRate");
						obj[8] = (Integer) map.get("errorNum");
						obj[9] = (String) map.get("errorThan") + "%";
					}
					list.add(obj);
				}
			}
			ExportExcel.webSiteConnectedTableExcel(fileName, title, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/************************************* 栏目检测-关键栏目连通率  结束********************************************/
	
	
	/** @Description: 页面
	 * @author zhurk --- 2015-11-24下午4:25:23     
	 * @return           
	*/
	public String index(){
		
		//siteCode处理由组织单位跳转到该页面时，session的修改
		String siteCode = request.getParameter("siteCode");
		
		if(StringUtils.isNotEmpty(siteCode)){
			setCurrentShiroUser(siteCode);
		}
		siteCode = getCurrentUserInfo().getChildSiteCode();
		String que = connectionHomeDetailServiceImpl.queueTime(siteCode);
		request.setAttribute("que", que);
		request.setAttribute("dateStr", DateUtils.formatStandardDate(new Date()));
		request.setAttribute("yesterdayStr", DateUtils.getYesterdayStr());
		return "success";
	}

	/**
	 * 
	 * @描述:关键栏目连通统计分析重构
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2017年3月24日上午11:34:37
	 */
	public void getChannelStatistics() {
//		ArrayList<Object> list = new ArrayList<Object>();
		// 从session中获取10位填报单位网站标识码
		String siteCode = getCurrentUserInfo().getChildSiteCode();
		if (StringUtils.isEmpty(siteCode)) {
			siteCode = getCurrentUserInfo().getSiteCode();
		}
		String date = request.getParameter("date"); // date是前台选中的日期
		if (StringUtils.isEmpty(date)) {
			date = DateUtils.getYesterdayStr(); // 昨天的时间
		}
		try {
			HashMap<String, Object> mapList = new HashMap<String, Object>();
			ChannelPointRequest channelPointRequest = new ChannelPointRequest();
			channelPointRequest.setSiteCode(siteCode);
			channelPointRequest.setChannelType(1);// 0 业务连通性
			channelPointRequest.setStatus(1);// 1监测中
			List<ChannelPoint> channelPointList = channelPointServiceImpl.queryList(channelPointRequest);
			// 分类
			ConnectionAllRequest allRequest = new ConnectionAllRequest();
			allRequest.setSiteCode(siteCode);
			allRequest.setType(ConnectionType.CHANNEL.getCode());
			allRequest.setPageSize(Integer.MAX_VALUE);
			allRequest.setScanDate(date);

			Integer successCountNum = 0;
			Integer errorCountNum = 0;
			Integer connectionCountNum = 0;
			//当天实时数据
			if (channelPointList.size() > 0) {
			if(realTimeType.equals("0")){
				Integer failNum = 0;
				String type = ConnectionType.BUSINESS.getCode().toString();  // 本地  2 业务 3 栏目 /接口  2 栏目  3 业务
				Root root = connectionAllServiceImpl.connectivityByRoot(siteCode, date, type);
				if(root.getResponse().equals("success")){
					if(StringUtils.isNotEmpty(root.getTotalfailnum())){
						failNum = Integer.valueOf(root.getTotalfailnum());
					}
					allRequest.setScanDate(DateUtils.getYesterdayStr());
					//获得 该sitecode 所有的关键栏目  并转为map
					HashMap<String, String> resultMap = new HashMap<String, String>();
					
					
					List<Result> rootlist = root.getResults();
					if (rootlist.size() > 0 && rootlist != null) {
						for (Result re : rootlist) {
							resultMap.put(re.getEncodeurl(), re.getFailnum());
						}
							
					}
					Integer errorNum = 0;//每个关键栏目的失败次数
					for (ChannelPoint channelPoint : channelPointList) {
						
						String channelName = channelPoint.getChannelName() == null ? "" : channelPoint.getChannelName();
						//如果接口返回的数据为空
						if(resultMap.size()==0){
							errorNum=0;
						}else{
							if(StringUtils.isNotEmpty(resultMap.get(channelPoint.getEncodeUrl()))){
								errorNum=Integer.valueOf(resultMap.get(channelPoint.getEncodeUrl()));
							}else{
								errorNum=0;
							}
							
						}
						String url="";
						if (StringUtils.isNotEmpty(channelPoint.getJumpPageUrl())) {
							url=channelPoint.getJumpPageUrl();
						} else {
							url=channelPoint.getChannelUrl();
						}
						
						HashMap<String, Object> map = new HashMap<String, Object>();
						map.put("successNum", "-");
						map.put("errorNum", errorNum);
						map.put("successThan", "-");
						map.put("errorThan", "-");
						map.put("connectionSum", "-");
						map.put("name", channelName);
						
						map.put("encodeurl", channelPoint.getEncodeUrl());
						map.put("url", url);
						connChannelList.add(map);
						errorCountNum += errorNum;
					}
				}
				mapList.put("chartFailNum", failNum);//折线图的当天不连通总次数
				
			}else{
				//非实时数据
				
					for (ChannelPoint channelPoint : channelPointList) {
						if (StringUtils.isNotEmpty(channelPoint.getJumpPageUrl())) {
							allRequest.setUrl(channelPoint.getJumpPageUrl());
						} else {
							allRequest.setUrl(channelPoint.getChannelUrl());
						}
						List<ConnectionAll> connAllList = connectionAllServiceImpl.queryList(allRequest);
						if (connAllList.size() > 0) {
							for (ConnectionAll connectionAll : connAllList) {
								HashMap<String, Object> map = new HashMap<String, Object>();
								Integer successNum = connectionAll.getSuccessNum();
								Integer errorNum = connectionAll.getErrorNum();
								Integer connectionSum = connectionAll.getConnectionSum();
								String successThan = StringUtils
										.getPrettyNumber(connectionAll.getSuccessProportion());
								String errorThan = StringUtils.getPrettyNumber(connectionAll.getErrorProportion());
								String name = connectionAll.getName() == null ? "" : connectionAll.getName();
								String url = connectionAll.getUrl();
								
								Float successF = Float.valueOf(successThan);
					             if (successF.floatValue() > 100.0F)
					                successThan = "100";
					              else if (successF.floatValue() < 0.0F) {
					                successThan = "0";
					              }

					              Float errorF = Float.valueOf(errorThan);
					              if (errorF.floatValue() > 100.0F)
					                errorThan = "100";
					              else if (errorF.floatValue() < 0.0F) {
					                errorThan = "0";
					              }

								map.put("successNum", successNum);
								map.put("errorNum", errorNum);
								map.put("connectionSum", connectionSum);
								map.put("successThan", successThan);
								map.put("errorThan", errorThan);
								map.put("name", name);
								map.put("url", url);
								connChannelList.add(map);

								successCountNum += successNum;
								errorCountNum += errorNum;
								connectionCountNum += connectionSum;
							}
							successProportion = StringUtils.getPrettyNumber(
									StringUtils.formatDouble(2, (double) successCountNum / connectionCountNum * 100));
							if(StringUtils.isNotEmpty(successProportion)){
								Double spD = Double.valueOf(successProportion);
								if(spD.doubleValue() > 100.0D){
									successProportion = "100";
								}else if(spD.doubleValue() < 0.0D){
									successProportion = "0";
								}
							}
						
							errorProportion = StringUtils.getPrettyNumber(
									StringUtils.formatDouble(2, (double) errorCountNum / connectionCountNum * 100));
							if(StringUtils.isNotEmpty(errorProportion)){
								Double epD = Double.valueOf(errorProportion);
								if(epD.doubleValue() > 100.0D){
									errorProportion = "100";
								}else if(epD.doubleValue() < 0.0D){
									errorProportion = "0";
								}
							}
							
						} else {
							Integer successNum = 0;
							Integer errorNum = 0;
							Integer connectionSum = 0;

							// 通过网站标识码查询databaseInfo表，判断客户是否为收费客户
							DatabaseInfoRequest databaseRequest = new DatabaseInfoRequest();
							databaseRequest.setSiteCode(siteCode);
							List<DatabaseInfo> databaseInfoList = databaseInfoServiceImpl.queryList(databaseRequest);
							if (databaseInfoList != null && databaseInfoList.size() > 0) {
								DatabaseInfo databaseInfo = databaseInfoList.get(0);
								if (databaseInfo.getIsScan() == 1) {
									Integer qu = databaseInfo.getQueue();
									if (qu != 0 && qu != null) {
										String que = QueueScanningType.getNameByCode(qu);
										successNum = Integer.valueOf(que);
									}
								} else {
									// 1天一次
									successNum = 1;
								}
								HashMap<String, Object> map = new HashMap<String, Object>();
								map.put("successNum", successNum);
								map.put("errorNum", 0);
								map.put("successThan", 100.00);
								map.put("errorThan", 0.00);
								map.put("connectionSum", successNum);
								String chaName = channelPoint.getChannelName();
								if (chaName != null) {
									map.put("name", chaName);
								} else {
									map.put("name", "");
								}

								map.put("url", allRequest.getUrl());
								connChannelList.add(map);

								connectionSum += successNum; // 每条总次数
								successCountNum += successNum;
								errorCountNum += errorNum;
								connectionCountNum += connectionSum;

								successProportion = StringUtils
										.getPrettyNumber(StringUtils.formatDouble(2, (double) successCountNum / connectionCountNum * 100));
								if(StringUtils.isNotEmpty(successProportion)){
									Double spD = Double.valueOf(successProportion);
									if(spD.doubleValue() > 100.0D){
										successProportion = "100";
									}else if(spD.doubleValue() < 0.0D){
										successProportion = "0";
									}
								}
								
								errorProportion = StringUtils
										.getPrettyNumber(StringUtils.formatDouble(2, (double) errorCountNum / connectionCountNum * 100));
								if(StringUtils.isNotEmpty(errorProportion)){
									Double epD = Double.valueOf(errorProportion);
									if(epD.doubleValue() > 100.0D){
										errorProportion = "100";
									}else if(epD.doubleValue() < 0.0D){
										errorProportion = "0";
									}
								}
							}
						}
				}
			}
			} else {
				successProportion = "false";
				errorProportion = "false";
			}
			mapList.put("date", date);
			mapList.put("dateStr", DateUtils.formatStandardDate(new Date()));
			mapList.put("successCountNum", successCountNum);
			mapList.put("errorCountNum", errorCountNum);
			mapList.put("connectionCountNum", connectionCountNum);
			mapList.put("successProportion", successProportion);
			mapList.put("errorProportion", errorProportion);
			mapList.put("connChannelList", connChannelList);
			writerPrint(JSONObject.fromObject(mapList).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * @描述:关键栏目连通性统计分析 详情
	 * @作者:liujc@ucap.com.cn
	 * @时间:2017-5-19下午4:11:09
	 */
	public void channelStatisticsInfo(){
		try {
			HashMap<String, Object> returnMap = new HashMap<String, Object>();
			List<Object> returnList = new ArrayList<Object>();
			String siteCode = getCurrentUserInfo().getChildSiteCode();
			if (StringUtils.isEmpty(siteCode)) {
				siteCode = getCurrentUserInfo().getSiteCode();
			}
			String encodeurl=request.getParameter("encodeurl");//加密url
			String url=request.getParameter("url");//url
			DicConfig dicConfig=dicConfigServiceImpl.get(2);
			String code=dicConfig.getValue();//异常码404
			String queue="";//队列 5分钟 15分钟
			if(realTimeType.equals("0")){
			DatabaseInfoRequest request=new DatabaseInfoRequest();
			request.setSiteCode(siteCode);
			List<DatabaseInfo> databaseInfoList=databaseInfoServiceImpl.queryList(request);
			if(databaseInfoList.size()>0){
				queue=String.valueOf(databaseInfoList.get(0).getQueue());
			}
			
			String dateStr=DateUtils.formatStandardDate(new Date());//当前时件
			
			Root root = connectionAllServiceImpl.channelStatisticsInfo( encodeurl,  dateStr, code,  queue);
			
			if(root.getResponse().equals("success")){
				List<Result> results=root.getResults();
				for(Result result:results){
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("stime", result.getStime());
					String questionCode = result.getCode();
					if (StringUtils.isNotEmpty(questionCode)) {
						map.put("questionCode", questionCode);
						map.put("questionDescribe",questionCode + QuestionType.getNameByCode(questionCode));
					} else {
						map.put("questionCode", "");
						map.put("questionDescribe", "");
					}
					//因为取的全是超时的 所以直接写死了
					map.put("stateName", ConnectionState.TIMEOUT.getName());
					returnList.add(map);
				}
			}
		}else if(realTimeType.equals("1")){
				//非实时
				 // 2 "关键栏目连通性"
	    		ConnectionChannelDetailRequest connChannelrequest = new ConnectionChannelDetailRequest();
	    		connChannelrequest.setState(ConnectionState.TIMEOUT.getCode());
	    		connChannelrequest.setScanDate(dateStr);
	    		connChannelrequest.setSiteCode(siteCode);
	    		connChannelrequest.setUrl(url);
	    		connChannelrequest.setPageSize(Integer.MAX_VALUE);
	    		List<QueryOrder> querySiteList=new ArrayList<QueryOrder>();
        		QueryOrder siteQueryOrder=new QueryOrder("scan_date",QueryOrderType.DESC);
    			querySiteList.add(siteQueryOrder);
	    		connChannelrequest.setQueryOrderList(querySiteList);
	    		PageVo<ConnectionChannelDetail> query = connectionChannelDetailServiceImpl.query(connChannelrequest);
	    		List<ConnectionChannelDetail> data = query.getData();
				if(data != null && data.size() > 0){
					for(ConnectionChannelDetail c : data){
						HashMap<String, Object> map = new HashMap<String, Object>();
						map.put("stime",  c.getScanTime());
						String questionCode = c.getQuestionCode();
						if(StringUtils.isNotEmpty(questionCode)){
							map.put("questionCode", questionCode);
							map.put("questionDescribe", questionCode+QuestionType.getNameByCode(questionCode));
						}else{
							map.put("questionCode", "");
							map.put("questionDescribe", "");
						}
						//因为取的全是超时的 所以直接写死了
						map.put("stateName", ConnectionState.TIMEOUT.getName());
						returnList.add(map);
	    			}
				}
			}
			
		
			returnMap.put("body", returnList);
			returnMap.put("bodySize", returnList.size());
			writerPrint(JSONObject.fromObject(returnMap).toString());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	/**调接口数据
	 * @Description: 关键栏目连通性的时间坐标折线图
	 * @author qinjy
	 */
	public void getChannelLine(){
		try {
			//从session中获取10位填报单位网站标识码
			String siteCode=getCurrentUserInfo().getChildSiteCode();
			if(StringUtils.isEmpty(siteCode)){
				siteCode=getCurrentUserInfo().getSiteCode();
			}
			// 获取前14天的开始时间
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
			paramMap.put("type", ConnectionType.CHANNEL.getCode());

			ArrayList<Object> list = new ArrayList<Object>();
			List<ConnectionAllRequest> queryList = connectionAllServiceImpl.getHomeBar(paramMap);
			
			if(queryList!=null && queryList.size()>0){
				for(int i=0;i<dateList.size();i++){
					String dateStr1=DateUtils.formatStandardDate(dateList.get(i));
					Map<String, Object> map=new HashMap<String, Object>();
					for(int j=0;j<queryList.size();j++){
						ConnectionAllRequest connAll = queryList.get(j);
						if(connAll.getScanDate().equals(dateStr1)){
							map.put("errorNum", connAll.getChannelNum());
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
			list.add("{errorNum=" + chartFailNum + ", scanDate=" + dateStr + "}");
//			dateStr = DateUtils.formatStandardDate(new Date()); // 当天时间
//			if(StringUtils.isNotEmpty(date)){
//				dateStr = date;
//			}
//			Integer failNum = 0;
//			String type = ConnectionType.BUSINESS.getCode().toString();  // 本地  2 业务 3 栏目 /接口  2 栏目  3 业务
//			Root root = connectionAllServiceImpl.connectivityByRoot(siteCode, dateStr, type);
//			if(root.getResponse().equals("success")){
//					if(StringUtils.isNotEmpty(root.getTotalfailnum())){
//						failNum = Integer.valueOf(root.getTotalfailnum());
//					}
//				if(StringUtils.isNotEmpty(root.getDate())){ 
//					dateStr = root.getDate();
//				}else {
//					dateStr = DateUtils.formatStandardDate(new Date());  //  当天时间
//				}
//				list.add("{errorNum=" + failNum + ", scanDate=" + dateStr + "}");
//			}
			logger.info("关键栏目连通list="+list);
			writerPrint(JSONArray.fromObject(list).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**调接口数据
	 * @Description: 关键栏目连通性的时间坐标折线图以及监测结果列表
	 * @author liukl 
	 */
	public void getChannelLineAndList(){
		Map<String, Object> map_list = new HashMap<String, Object>();
		try {
			//从session中获取10位填报单位网站标识码
			String siteCode=getCurrentUserInfo().getChildSiteCode();
			if(StringUtils.isEmpty(siteCode)){
				siteCode=getCurrentUserInfo().getSiteCode();
			}
			// 获取前14天的开始时间
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
			paramMap.put("type", ConnectionType.CHANNEL.getCode());

			ArrayList<Object> list = new ArrayList<Object>();
			List<ConnectionAllRequest> queryList = connectionAllServiceImpl.getHomeBar(paramMap);
			if(queryList!=null && queryList.size()>0){
				for(int i=0;i<dateList.size();i++){
					String dateStr1=DateUtils.formatStandardDate(dateList.get(i));
					Map<String, Object> map=new HashMap<String, Object>();
					for(int j=0;j<queryList.size();j++){
						ConnectionAllRequest connAll = queryList.get(j);
						if(connAll.getScanDate().equals(dateStr1)){
							map.put("errorNum", connAll.getChannelNum());
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
			String type = ConnectionType.BUSINESS.getCode().toString();  // 本地  2 业务 3 栏目 /接口  2 栏目  3 业务
			Root root = connectionAllServiceImpl.connectivityByRoot(siteCode, dateStr, type);
			if(root.getResponse().equals("success")){
					if(StringUtils.isNotEmpty(root.getTotalfailnum())){
						failNum = Integer.valueOf(root.getTotalfailnum());
					}
				if(StringUtils.isNotEmpty(root.getDate())){ 
					dateStr = root.getDate();
				}else {
					dateStr = DateUtils.formatStandardDate(new Date());  //  当天时间
				}
				list.add("{errorNum=" + failNum + ", scanDate=" + dateStr + "}");
			}
//			logger.info("关键栏目连通list="+list);
			//---------------------------------------上面是图表，下面是列表
//			String type = "";							 //      接口： 1 "首页连通性";3 "业务系统连通性";2 "关键栏目连通性"
			String code = request.getParameter("code");  // 	本地库：1 "首页连通性";2 "业务系统连通性";3 "关键栏目连通性"
//			if (StringUtils.isNotEmpty(code)) {
//				type = code;
//			}
			String date = request.getParameter("date");
			dateStr = DateUtils.formatStandardDate(new Date()); // 当天时间
			if(StringUtils.isEmpty(date)){
				date = dateStr;
			}
			
			ArrayList<Object> mergeList = new ArrayList<Object>();
			ChannelPointRequest res = new ChannelPointRequest();
			res.setPageSize(Integer.MAX_VALUE);
			List<ChannelPoint> channelList = new ArrayList<ChannelPoint>();
			
//			Root root = connectionAllServiceImpl.connectivityByRoot(siteCode, dateStr, type);
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
							mergeList.add(map);
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
	/**
	 * @Description: 分页列表
	 * @author sunjiang --- 2015年11月10日下午5:00:46
	 */
	public void queryList(){
		//从session中获取10位填报单位网站标识码
		String siteCode=getCurrentUserInfo().getChildSiteCode();
		if(StringUtils.isEmpty(siteCode)){
			siteCode=getCurrentUserInfo().getSiteCode();
		}
		ConnectionChannelDetailRequest connChannelrequest = new ConnectionChannelDetailRequest();
		connChannelrequest.setSiteCode(siteCode);
		/**
		 * key表示前台输入的关键字
		 */
		String key = request.getParameter("key");
		//date是前台选中的日期
		String date = request.getParameter("date");
		String yesterdayStr=DateUtils.getYesterdayStr();
		String pos = request.getParameter("pos");
		String size = request.getParameter("size");
		//获取排序条件
		String sSortDir_0 = request.getParameter("sSortDir_0");//控制升序和降序
		String soraFiel = request.getParameter("mDataProp_"+request.getParameter("iSortCol_0"));//获取要排序的字段

		//排序字段
		if(soraFiel!=null){
			List<QueryOrder> querySiteList=new ArrayList<QueryOrder>();
			if(!"".equals(sSortDir_0) && "asc".equals(sSortDir_0)){
				QueryOrder siteQueryOrder=new QueryOrder("scan_time",QueryOrderType.ASC);
				querySiteList.add(siteQueryOrder);
				connChannelrequest.setQueryOrderList(querySiteList);
			}else{
				QueryOrder siteQueryOrder=new QueryOrder("scan_time",QueryOrderType.DESC);
				querySiteList.add(siteQueryOrder);
				connChannelrequest.setQueryOrderList(querySiteList);
			}
		}
		if (StringUtils.isNotEmpty(date)) {
			connChannelrequest.setScanDate(date);
		}else{
			connChannelrequest.setScanDate(yesterdayStr);
		}
		if (StringUtils.isNotEmpty(key)) {
			connChannelrequest.setQuestionCode(key);
		}

		if(StringUtils.isNotEmpty(pos)){
			connChannelrequest.setPageNo(Integer.parseInt(pos));
		}
		if(StringUtils.isNotEmpty(size)){
			connChannelrequest.setPageSize(Integer.parseInt(size));
		}
		connChannelrequest.setState(ConnectionState.TIMEOUT.getCode());
		
		
		
		ArrayList<Object> list = new ArrayList<Object>();
		HashMap<String, Object> map_list = new HashMap<String, Object>();
		PageVo<ConnectionChannelDetail> query = connectionChannelDetailServiceImpl.query(connChannelrequest);
		List<ConnectionChannelDetail> data = query.getData();

		for (int i = 0; i < data.size(); i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			ConnectionChannelDetail connectionChannelDetail = data.get(i);
			String systemName = connectionChannelDetail.getChannelName();
			map.put("systemName", systemName);
			//每页起始条数
			//${status.index + 1 + ((page.pageNum - 1) * page.numPerPage)}
			int beginNum=i+((Integer.valueOf(pos)-1)*Integer.valueOf(size));
			map.put("dataNumber", beginNum+1);
			String url = connectionChannelDetail.getUrl();
			map.put("url", url);
			String scanDate = connectionChannelDetail.getScanTime();
			map.put("scanDate", scanDate);
			int state = connectionChannelDetail.getState();
			for (ConnectionState connectionState : ConnectionState.values()) {
				Integer code = connectionState.getCode();
				if(state==code){
					map.put("stateName", connectionState.getName());
				}
			}
			String questionCode = connectionChannelDetail.getQuestionCode();
			if(StringUtils.isNotEmpty(questionCode)){
				String questionDescribe=QuestionType.getNameByCode(questionCode);
				if(StringUtils.isNotEmpty(questionDescribe)){
					map.put("questionDescribe", questionCode+"　　"+questionDescribe);
				}else{
					map.put("questionDescribe", questionCode);
				}
			}else{
				map.put("questionDescribe", "");
			}
			
			String questionDescribe = connectionChannelDetail.getQuestionDescribe();
			map.put("questionDescribe", questionCode+"　　"+questionDescribe);
			list.add(map);
		}
		map_list.put("body", list);
		map_list.put("totalRecords", data.size());
		map_list.put("iTotalDisplayRecords", query.getRecordSize());
		map_list.put("hasMoreItems",true);
		writerPrint(JSONObject.fromObject(map_list).toString());
	}

	/**	调接口数据 
	 * @Description: 关键栏目Excel导出
	 * @author qinjy                
	*/
	public void channelExcel(){
		//从session中获取10位填报单位网站标识码
		String siteCode=getCurrentUserInfo().getChildSiteCode();
		if(StringUtils.isEmpty(siteCode)){
			siteCode=getCurrentUserInfo().getSiteCode();
		}
		String type = "";
		String code = request.getParameter("code");  // 	1 "首页连通性";3 "业务系统连通性";2 "关键栏目连通性"
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
		Object[] obj1 = new Object[]{"序号","关键栏目名称","URL","时间","连通状态","问题描述"};
		list.add(obj1);
		String fileName ="网站连通性-关键栏目连通性监测结果("+dateStr+").xls";
		String title = "关键栏目连通性监测结果"; 
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
    					Object[] obj = new Object[6];
						i++;
						obj[0] = i;
						obj[1] = c.getChannelName();
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
			logger.info("关键栏目连通性 excel 导出"+"  siteCode :"+siteCode);
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

	public List<Object> getConnChanneList() {
		return connChanneList;
	}

	public void setConnChanneList(List<Object> connChanneList) {
		this.connChanneList = connChanneList;
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

	public String getRealTimeType() {
		return realTimeType;
	}

	public void setRealTimeType(String realTimeType) {
		this.realTimeType = realTimeType;
	}

	public String getChartFailNum() {
		return chartFailNum;
	}

	public void setChartFailNum(String chartFailNum) {
		this.chartFailNum = chartFailNum;
	}

	public String getDateStr() {
		return dateStr;
	}

	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}
}
