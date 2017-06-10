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

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.publics.util.page.QueryOrder;
import com.publics.util.page.QueryOrderType;
import com.publics.util.utils.DateUtils;
import com.publics.util.utils.StringUtils;
import com.ucap.cloud_web.bizService.DatabaseBizService;
import com.ucap.cloud_web.common.ComparatorColumnHashMap;
import com.ucap.cloud_web.common.DicUtils;
import com.ucap.cloud_web.constant.CacheType;
import com.ucap.cloud_web.constant.ConnectionState;
import com.ucap.cloud_web.constant.ConnectionType;
import com.ucap.cloud_web.constant.DatabaseInfoType;
import com.ucap.cloud_web.constant.DatabaseLinkType;
import com.ucap.cloud_web.constant.DatabaseTreeInfoType;
import com.ucap.cloud_web.constant.DetectionResultType;
import com.ucap.cloud_web.constant.QuestionType;
import com.ucap.cloud_web.constant.QueueScanningType;
import com.ucap.cloud_web.constant.QueueType;
import com.ucap.cloud_web.dto.ConnectionAllRequest;
import com.ucap.cloud_web.dto.ConnectionHomeDetailRequest;
import com.ucap.cloud_web.dto.DatabaseInfoRequest;
import com.ucap.cloud_web.dto.DetectionOrgCountRequest;
import com.ucap.cloud_web.dto.xstream.Root;
import com.ucap.cloud_web.dtoResponse.ConnectionAllHomeResponse;
import com.ucap.cloud_web.entity.ConnectionAll;
import com.ucap.cloud_web.entity.ConnectionHomeDetail;
import com.ucap.cloud_web.entity.ContractInfo;
import com.ucap.cloud_web.entity.DatabaseInfo;
import com.ucap.cloud_web.entity.Detail;
import com.ucap.cloud_web.entity.DetectionOrgCount;
import com.ucap.cloud_web.entity.DetectionResult;
import com.ucap.cloud_web.entity.DicItem;
import com.ucap.cloud_web.entity.EarlyInfo;
import com.ucap.cloud_web.entity.Result;
import com.ucap.cloud_web.service.IConnectionAllHomeService;
import com.ucap.cloud_web.service.IConnectionAllService;
import com.ucap.cloud_web.service.IConnectionHomeDetailService;
import com.ucap.cloud_web.service.IDatabaseInfoService;
import com.ucap.cloud_web.service.IDetectionOrgCountService;
import com.ucap.cloud_web.service.IDetectionResultService;
import com.ucap.cloud_web.service.IEarlyInfoService;
import com.ucap.cloud_web.util.ExportExcel;
import com.ucap.cloud_web.util.MonitoringCacheUtils;

/**
 * <p>Description: 首页连通性详情表</p>
 * <p>@Package：com.ucap.cloud_web.action </p>
 * <p>Title: ConnectionHomeDetailAction </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：sunjiang </p>
 * <p>@date：2015年11月6日上午9:58:52 </p>
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class ConnectionHomeDetailAction extends BaseAction{
	
	private static Log logger = LogFactory.getLog(ConnectionHomeDetailAction.class);
	
	@Autowired
	private IConnectionHomeDetailService connectionHomeDetailServiceImpl;
	@Autowired
	private IConnectionAllService connectionAllServiceImpl;
	@Autowired
	private IDetectionResultService detectionResultServiceImpl;
	@Autowired
	private IEarlyInfoService earlyInfoServiceImpl;
	@Autowired
	private IDatabaseInfoService databaseInfoServiceImpl;
	@Autowired
	private IDetectionOrgCountService detectionOrgCountServiceImpl;
	@Autowired
	private DatabaseBizService databaseBizServiceImpl;
	@Autowired
	private IConnectionAllHomeService connectionAllHomeServiceImpl;
	@Autowired
	private DicUtils dicUtils;
	
	private List<Object> connDetList= new ArrayList<Object>();
	
	/**
	 * 昨天的日期   yyyy-MM-DD
	 */
	private String yesterdayStr;
	/**
	 * 当天的日期   yyyy-MM-DD
	 */
	private String dateStr;
	/**
	 * 成功次数
	 */
	private int successNum = 0;
	/**
	 * 不连通次数
	 */
	private int errorNum = 0;
	/**
	 * 成功占比
	 */
	private String successProportion = "0";
	/**
	 * 不连通占比
	 */
	private String errorProportion = "0";
	private HashMap<String, Object> result_map = new HashMap<String, Object>();	

	/************************************* 日常监测-首页连通率 开始********************************************/

	/**
	 * 
	 * @描述:跳转首页连通率页面
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2016年12月20日下午1:38:25
	 * @return
	 */
	public String webSiteConnected() {
		List<DicItem> dicList = dicUtils.getDictList("siteType");
		request.setAttribute("dicList", dicList); // 网站类别
		request.setAttribute("yesterday", DateUtils.getYesterdayStr()); // 昨天
		return "success";
	}

	/**
	 * 
	 * @描述:首页连通性table
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2017年3月30日下午3:23:06
	 */
	public void connectionAllHomeTable() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
		String siteType = request.getParameter("siteType"); // 网站类别
		String queue = request.getParameter("monitoringId"); // 监测频率
		String startDate = request.getParameter("startDate"); // 开始时间
		String endDate = request.getParameter("endDate"); // 结束时间
		String percentage = request.getParameter("percentage"); // 超过5%的
		String type = request.getParameter("type"); // 类型 1首页面连通性、2、业务系统连通性、3关键栏目连通性
		try {
			// 获取当前组织机构编码
			String siteCode = getCurrentSiteCode();

			Integer pe = 0;
			if (StringUtils.isNotEmpty(percentage)) {
				pe = Integer.valueOf(percentage);
			}
			Integer count = 0;
			Integer qu = 0;
			String quName = "";
			float errorPro = 0; // 超时占比
			Integer homeUrlNum = 0; // 首页url总数
			float allConNum = 0; // 连接总数
			float allConnectedNum = 0; // 连通总数
			float allNoConnectedNum = 0; // 连不通总数
			String allConnectedRate = ""; // 总连通率
			String allNoConnectedRate = ""; // 总不连通率
			
			Integer columnNum = 0; // 栏目总数
			String noConnectedRate = ""; // 不连通率
			float noConnectedNum = 0; // 连不通数
			float conNum = 0; // 连接数
			String connectedRate = ""; // 连通率
			float connectedNum = 0; // 连通数
			
			HashMap<String, Object> hashMap = new HashMap<String, Object>();
			hashMap.put("orgSiteCode", siteCode);
			hashMap.put("startDate", startDate);
			hashMap.put("endDate", endDate);
			hashMap.put("isLink", DatabaseTreeInfoType.ISLINK.getCode());
			if (StringUtils.isNotEmpty(siteType) && !siteType.equals("0")) {
				hashMap.put("siteType", siteType);
			}
			if (StringUtils.isNotEmpty(queue) && !queue.equals("0")) {
				hashMap.put("queue", queue);
			}
			if (StringUtils.isNotEmpty(type)) {
				hashMap.put("type", type);
			}
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
						item.put("url", con.getHomePageUrl());// 首页url地址
					}
					qu = con.getQueue();
					quName = QueueType.getNameByCode(qu);
					
					item.put("connectionSum", con.getConnectionSum());
					item.put("successNum", con.getSuccessNum());
					item.put("successProportion", con.getSuccessProportion());
					item.put("errorNum", con.getErrorNum());
					item.put("errorProportion", con.getErrorProportion());
					item.put("countSum", con.getChannelNum());//2.3
					noConnectedNum = con.getErrorNum();
					conNum = con.getConnectionSum();
					connectedNum = con.getSuccessNum();
					if (StringUtils.isNotEmpty(con.getErrorProportion())) {
						errorPro = Float.parseFloat(con.getErrorProportion());
					}
					if (errorPro < pe) {
						count++;
						continue;
					}
					if(connectedNum == 0 && noConnectedNum == 0 ){
						item.put("quName", "未监测");
					}else{
						item.put("quName", quName + "一次");
					}
					items.add(item);
					homeUrlNum++;
					allConNum += con.getConnectionSum();
					allConnectedNum += con.getSuccessNum();
					allNoConnectedNum += con.getErrorNum();
					
					
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
					item.put("successThan", connectedRate);
					
					
					columnNum += con.getChannelNum();
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
				}
				
				if (allConnectedNum == 0) { // 被除数为0的情况下
					allConnectedRate = "0";
				} else if (allConNum == 0) { // 除数为0的情况下
					allConnectedRate = "0";
				} else {
					allConnectedRate = totalPercentage(allConnectedNum, allConNum);
					if (allConnectedRate.equals("0")) { // 基数太小得到的数据为0.00默认改为0.01
						allConnectedRate = "0.01";
					}
				}
				resultMap.put("allSuccessThan", allConnectedRate + "%");
				if (allNoConnectedNum == 0) { // 被除数为0的情况下
					allNoConnectedRate = "0";
				} else if (allConNum == 0) { // 除数为0的情况下
					allNoConnectedRate = "0";
				} else {
					allNoConnectedRate = totalPercentage(allNoConnectedNum, allConNum);
					Float errorFloat = Float.valueOf(allNoConnectedRate);
					if (allNoConnectedRate.equals("0")) { // 基数太小得到的数据为0.00默认改为0.01
						allNoConnectedRate = "0.01";
					}else if(errorFloat > 100){
						allNoConnectedRate = "100";
					}else if(errorFloat < 0){
						allNoConnectedRate = "0";
					}
				}
				resultMap.put("allErrorThan", allNoConnectedRate + "%");

			}
			Integer num = 0;
			if (pe == 0) {
				num = queryList.size();
			} else {
				num = queryList.size() - count;
			}
			
//			if(!type.equals("1")){
//			}
			ComparatorColumnHashMap comparatorHashMapTotal = new ComparatorColumnHashMap();
			Collections.sort(items, comparatorHashMapTotal);

			
			resultMap.put("success", "true");
			resultMap.put("columnNum", columnNum);//2.3
			
			resultMap.put("homeUrlNum", homeUrlNum);
			resultMap.put("allConNum", allConNum);
			resultMap.put("allConnectedNum", allConnectedNum);
			resultMap.put("allNoConnectedNum", allNoConnectedNum);
			resultMap.put("body", items);
			resultMap.put("size", num); // 总条数
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", "false");
			resultMap.put("errorMsg", "查询连通率数据异常！");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		}
	}

	/**
	 * 
	 * @描述:首页连通性Excel
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2017年3月30日下午5:14:29
	 */
	public void connectionAllHomeTableExcel() {
		ArrayList<Object[]> list = new ArrayList<Object[]>();
		String siteType = request.getParameter("siteType"); // 网站类别
		String queue = request.getParameter("monitoringId"); // 监测频率
		String startDate = request.getParameter("startDate"); // 开始时间
		String endDate = request.getParameter("endDate"); // 结束时间
		String percentage = request.getParameter("percentage"); // 超过5%的
		String type = request.getParameter("type"); // 类型 1首页面连通性、2、业务系统连通性、3关键栏目连通性

		List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
		try {
			// 获取当前组织机构编码
			String siteCode = getCurrentSiteCode();

			Object[] obj1 = new Object[] { "网站标识码", "网站名称", "首页url", "监测频率", "连接总次数", "连通次数", "连通率", "连不通次数", "不连通率" };
			list.add(obj1);
			String fileName = "日常监测-首页连通率(" + DateUtils.formatStandardDate(new Date()) + ").xls";
			String title = "首页连通率";

			Integer pe = 0;
			if (StringUtils.isNotEmpty(percentage)) {
				pe = Integer.valueOf(percentage);
			}
			Integer qu = 0;
			String quName = "";

			HashMap<String, Object> hashMap = new HashMap<String, Object>();
			hashMap.put("orgSiteCode", siteCode);
			hashMap.put("startDate", startDate);
			hashMap.put("endDate", endDate);
			hashMap.put("isLink", DatabaseTreeInfoType.ISLINK.getCode());
			if (StringUtils.isNotEmpty(siteType) && !siteType.equals("0")) {
				hashMap.put("siteType", siteType);
			}
			if (StringUtils.isNotEmpty(queue) && !queue.equals("0")) {
				hashMap.put("queue", queue);
			}
			if (StringUtils.isNotEmpty(type)) {
				hashMap.put("type", type);
			}
			List<ConnectionAllHomeResponse> queryList = connectionAllHomeServiceImpl.getConnectionAllHomeList(hashMap);
			float noConnectedNum = 0; // 连不通数
			String connectedRate = ""; // 连通率
			String noConnectedRate = ""; // 不连通率
			float conNum = 0; // 连接数
			float connectedNum = 0; // 连通数
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
					Object[] obj = new Object[9];
					obj[0] = (String) map.get("siteCode");
					obj[1] = (String) map.get("siteName");
					obj[2] = (String) map.get("url");
					obj[3] = (String) map.get("quName");
					obj[4] = (Integer) map.get("connectionSum");
					obj[5] = (Integer) map.get("successNum");
					obj[6] = (String) map.get("connectedRate");
					obj[7] = (Integer) map.get("errorNum");
					obj[8] = (String) map.get("errorThan") + "%";
					list.add(obj);
				}
			}
			ExportExcel.webSiteConnectedTableExcel(fileName, title, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @描述:业务，栏目连通率数据
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2016年12月20日下午2:11:10
	 */
	@SuppressWarnings("unchecked")
	public void webSiteConnectedTable() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
		String siteType = request.getParameter("siteType"); // 网站类别
		String queue = request.getParameter("monitoringId"); // 监测频率
		String startDate = request.getParameter("startDate"); // 开始时间
		String endDate = request.getParameter("endDate"); // 结束时间
		String percentage = request.getParameter("percentage"); // 超过5%的
		String type = request.getParameter("type"); // 类型 1首页面连通性、2、业务系统连通性、3关键栏目连通性
		
		try {
			// 获取当前组织机构编码
			String siteCode = getCurrentSiteCode();

			Integer pe = 0;
			if (StringUtils.isNotEmpty(percentage)) {
				pe = Integer.valueOf(percentage);
			}
			Integer count = 0;
			Integer qu = 0;
			String quName = "";
			Integer columnNum = 0; // 栏目总数
			float conNum = 0; // 连接数
			float connectedNum = 0; // 连通数
			float noConnectedNum = 0; // 连不通数
			String connectedRate = ""; // 连通率
			String noConnectedRate = ""; // 不连通率
			Integer homeUrlNum = 0; // 首页url总数
			float allConNum = 0; // 连接总数
			float allConnectedNum = 0; // 连通总数
			float allNoConnectedNum = 0; // 连不通总数
			String allConnectedRate = ""; // 总连通率
			String allNoConnectedRate = ""; // 总不连通率

			HashMap<String, Object> hashMap = new HashMap<String, Object>();
			hashMap.put("orgSiteCode", siteCode);
			hashMap.put("startDate", startDate);
			hashMap.put("endDate", endDate);
			hashMap.put("type", type);
			hashMap.put("isLink", DatabaseTreeInfoType.ISLINK.getCode());
			hashMap.put("isExp", DatabaseInfoType.NORMAL.getCode());
			if (StringUtils.isNotEmpty(siteType) && !siteType.equals("0")) {
				hashMap.put("siteType", siteType);
			}
			if (StringUtils.isNotEmpty(queue) && !queue.equals("0")) {
				hashMap.put("queue", queue);
			}
			String cacheConnectionAll = CacheType.MONITORING_CONNECTIONALL.getName();
			String conkey = cacheConnectionAll + siteCode + startDate + endDate + queue + siteType + type; // 缓存名
			List<ConnectionAllRequest> queryList = (List<ConnectionAllRequest>) MonitoringCacheUtils.get(conkey); // 查询缓存中是否存在
			if (queryList == null) {
				queryList = connectionAllServiceImpl.getwebConnectedList(hashMap);
				MonitoringCacheUtils.put(conkey, queryList); // 将数据存到缓存中
			}

			if (queryList != null && queryList.size() > 0) {
				for (int i = 0; i < queryList.size(); i++) {
					ConnectionAllRequest con = queryList.get(i);
					Map<String, Object> item = new HashMap<String, Object>();
					item.put("siteCode", con.getSiteCode());
					item.put("siteName", con.getSiteName());
					item.put("countSum", con.getCountSum());
					if (StringUtils.isNotEmpty(con.getJumpPageUrl())) {
						item.put("url", con.getJumpPageUrl());// 跳转url地址
					} else {
						item.put("url", con.getHomePageUrl());// 首页url地址
					}
					qu = con.getQueue();
					quName = QueueType.getNameByCode(qu);
					item.put("quName", quName + "一次");
					item.put("connectionSum", con.getConnectionSum());
					item.put("successNum", con.getSuccessNum());
					item.put("errorNum", con.getErrorNum());
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
					item.put("successThan", connectedRate);
					if (noConnectedNum == 0) { // 被除数为0的情况下
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
					float noRate = Float.parseFloat(noConnectedRate);
					if (noRate < pe) {
						count++;
						continue;
					}
					item.put("errorThan", noConnectedRate);
					items.add(item);
					homeUrlNum++;
					columnNum += con.getCountSum();
					allConNum += con.getConnectionSum();
					allConnectedNum += con.getSuccessNum();
					allNoConnectedNum += con.getErrorNum();
				}
				if (allConnectedNum == 0) { // 被除数为0的情况下
					allConnectedRate = "0";
				} else if (allConNum == 0) { // 除数为0的情况下
					allConnectedRate = "0";
				} else {
					allConnectedRate = totalPercentage(allConnectedNum, allConNum);
//					Float successFloat = Float.valueOf(allNoConnectedRate);
					if (allConnectedRate.equals("0")) { // 基数太小得到的数据为0.00默认改为0.01
						allConnectedRate = "0.01";
					}
				}
				resultMap.put("allSuccessThan", allConnectedRate + "%");
				if (allNoConnectedNum == 0) { // 被除数为0的情况下
					allNoConnectedRate = "100";
				} else if (allConNum == 0) { // 除数为0的情况下
					allNoConnectedRate = "0";
				} else {
					allNoConnectedRate = totalPercentage(allNoConnectedNum, allConNum);
					Float errorFloat = Float.valueOf(allNoConnectedRate);
					if (allNoConnectedRate.equals("0")) { // 基数太小得到的数据为0.00默认改为0.01
						allNoConnectedRate = "0.01";
					}else if(errorFloat > 100){
						allNoConnectedRate = "100";
					}else if(errorFloat < 0){
						allNoConnectedRate = "0";
					}
				}
				resultMap.put("allErrorThan", allNoConnectedRate + "%");

			}
			Integer num = 0;
			if (pe == 0) {
				num = queryList.size();
			} else {
				num = queryList.size() - count;
			}

			resultMap.put("success", "true");
			resultMap.put("columnNum", columnNum);
			resultMap.put("homeUrlNum", homeUrlNum);
			resultMap.put("allConNum", allConNum);
			resultMap.put("allConnectedNum", allConnectedNum);
			resultMap.put("allNoConnectedNum", allNoConnectedNum);
			resultMap.put("body", items);
			resultMap.put("size", num); // 总条数
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", "false");
			resultMap.put("errorMsg", "查询连通率数据异常！");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		}
	}

	/**
	 * 
	 * @描述:业务，栏目连通率excel导出
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2016年12月24日下午5:07:59
	 */
	@SuppressWarnings("unchecked")
	public void webSiteConnectedTableExcel() {
		ArrayList<Object[]> list = new ArrayList<Object[]>();
		String siteType = request.getParameter("siteType"); // 网站类别
		String queue = request.getParameter("monitoringId"); // 监测频率
		String startDate = request.getParameter("startDate"); // 开始时间
		String endDate = request.getParameter("endDate"); // 结束时间
		String percentage = request.getParameter("percentage"); // 超过5%的
		String type = request.getParameter("type"); // 类型 1首页面连通性、2、业务系统连通性、3关键栏目连通性

		try {
			// 获取当前组织机构编码
			String siteCode = getCurrentSiteCode();

			Object[] obj1 = new Object[] { "网站标识码", "网站名称", "首页url", "监测频率", "连接总次数", "连通次数", "连通率", "连不通次数", "不连通率" };
			list.add(obj1);
			String fileName = "日常监测-首页连通率(" + DateUtils.formatStandardDate(new Date()) + ").xls";
			String title = "首页连通率";

			Integer pe = 0;
			if (StringUtils.isNotEmpty(percentage)) {
				pe = Integer.valueOf(percentage);
			}
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
			hashMap.put("isExp", DatabaseInfoType.NORMAL.getCode());
			if (StringUtils.isNotEmpty(siteType) && !siteType.equals("0")) {
				hashMap.put("siteType", siteType);
			}
			if (StringUtils.isNotEmpty(queue) && !queue.equals("0")) {
				hashMap.put("queue", queue);
			}
			String cacheConnectionAllExcel = CacheType.getNameByCode("4");
			String conkey = cacheConnectionAllExcel + siteCode + startDate + endDate + queue + siteType + type; // 缓存名
			List<ConnectionAllRequest> queryList = (List<ConnectionAllRequest>) MonitoringCacheUtils.get(conkey); // 查询缓存中是否存在
			if (queryList == null) {
				queryList = connectionAllServiceImpl.getwebConnectedList(hashMap);
				MonitoringCacheUtils.put(conkey, queryList); // 将数据存到缓存中
			}

			if (queryList != null && queryList.size() > 0) {
				for (int i = 0; i < queryList.size(); i++) {
					ConnectionAllRequest con = queryList.get(i);
					Object[] obj = new Object[9];
					obj[0] = con.getSiteCode();
					obj[1] = con.getSiteName();

					if (StringUtils.isNotEmpty(con.getJumpPageUrl())) {
						obj[2] = con.getJumpPageUrl();// 跳转url地址
					} else {
						obj[2] = con.getHomePageUrl();// 首页url地址
					}
					qu = con.getQueue();
					quName = QueueType.getNameByCode(qu);
					obj[3] = quName + "一次";
					obj[4] = con.getConnectionSum();
					obj[5] = con.getSuccessNum();

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
					obj[6] = connectedRate + "%";
					obj[7] = con.getErrorNum();
					if (noConnectedNum == 0) { // 被除数为0的情况下
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
					obj[8] = noConnectedRate + "%";
					float noRate = Float.parseFloat(noConnectedRate);
					if (noRate < pe) {
						continue;
					}
					list.add(obj);
				}
			}
			ExportExcel.webSiteConnectedTableExcel(fileName, title, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/************************************* 日常监测-首页连通率 结束********************************************/

	/**
	 * @Description: 页面
	 * @author sunjiang --- 2015年11月6日上午10:45:49     
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
	 * @Description: 饼图，统计，列表数据   
	 * @author qinjy      调接口获取数据
	 */
	public void queryHomelist(){
		
			//从session中获取10位填报单位网站标识码
			String siteCode=getCurrentUserInfo().getChildSiteCode();
			if(StringUtils.isEmpty(siteCode)){
				siteCode=getCurrentUserInfo().getSiteCode();
			}
		
			HashMap<String, Object> map_list = new HashMap<String, Object>();
		
			//昨天的时间
			yesterdayStr = DateUtils.getYesterdayStr();
			String date = request.getParameter("date");
			dateStr = DateUtils.formatStandardDate(new Date()); // 当天时间
			if(StringUtils.isEmpty(date)){
				 date = dateStr;
			}
			String siteBeginServiceDate="";
			
			try {
			ConnectionAllRequest allRequest = new ConnectionAllRequest();
			List<QueryOrder> querySiteList=new ArrayList<QueryOrder>();
			QueryOrder siteQueryOrder=new QueryOrder("scan_date",QueryOrderType.DESC);
			querySiteList.add(siteQueryOrder);
			allRequest.setQueryOrderList(querySiteList);
			
			ConnectionHomeDetailRequest detailRequest = new ConnectionHomeDetailRequest();
			detailRequest.setQueryOrderList(querySiteList);
			/**老合同信息**/
			if (StringUtils.isNotEmpty(date)) {
				allRequest.setScanDate(date);
				detailRequest.setScanDate(date);
				map_list.put("yesterdayStr", DateUtils.formatDate(DateUtils.parseStandardDate(date)));
				map_list.put("yesterdayStrEng", date);// 用于页面给后台传递参数
			} else {
				detailRequest.setScanDate(yesterdayStr);
				map_list.put("yesterdayStr", DateUtils.formatDate(DateUtils.parseStandardDate(yesterdayStr)));
				map_list.put("yesterdayStrEng", yesterdayStr);// 用于页面给后台传递参数
				allRequest.setScanDate(yesterdayStr);
				List<ContractInfo> contractList = getContractInfoList(siteCode,
						DateUtils.formatStandardDate(new Date()));
				if (contractList != null && contractList.size() > 0) {
					siteBeginServiceDate = DateUtils.formatStandardDate(contractList.get(0).getContractBeginTime());
				}
			}
			/** 新产品信息 **/
			// detailRequest.setScanDate(yesterdayStr);
			// map_list.put("yesterdayStr",
			// DateUtils.formatDate(DateUtils.parseStandardDate(yesterdayStr)));
			// map_list.put("yesterdayStrEng", yesterdayStr);// 用于页面给后台传递参数
			// allRequest.setScanDate(yesterdayStr);
			// Integer[] productTypeArr = { CrmProductsType.DETECTION.getCode()
			// };
			// List<CrmProductsResponse> crmList = getCrmProductsList(siteCode,
			// productTypeArr,
			// DateUtils.formatStandardDate(new Date()), null, null);
			// if (CollectionUtils.isNotEmpty(crmList)) {
			// siteBeginServiceDate = crmList.get(0).getBeginTime();
			// }
			//分类
			allRequest.setSiteCode(siteCode);
			allRequest.setType(ConnectionType.HOME.getCode());
			allRequest.setPageSize(Integer.MAX_VALUE);
			
			List<ConnectionAll> connAllList = connectionAllServiceImpl.queryList(allRequest);
			if(connAllList != null && connAllList.size()>0){
				ConnectionAll connectionAll = connAllList.get(0);
				successNum = connectionAll.getSuccessNum();
				errorNum = connectionAll.getErrorNum();
				successProportion = StringUtils.getPrettyNumber(connectionAll.getSuccessProportion());

				errorProportion = StringUtils.getPrettyNumber(connectionAll.getErrorProportion());

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
					errorNum=0;
					successProportion="100";
					errorProportion="0";
				}
			}

			
			if(successNum<0){
				successNum=0;
				successProportion="0";
			}
			if(errorNum<0){
				errorNum=0;
				errorProportion="0";
			}
			if(StringUtils.isNotEmpty(successProportion)){
				if(Float.valueOf(successProportion)>100){
					successProportion="100";
				}else if(Float.valueOf(successProportion)<0){
					successProportion="0";
				}
				
			}else{
				successProportion="0";
			}
			if(StringUtils.isNotEmpty(errorProportion)){
				if(Float.valueOf(errorProportion)>100){
					errorProportion="100";
				}else if(Float.valueOf(errorProportion)<0){
					errorProportion="0";
				}
				
			}else{
				errorProportion="0";
			}
			
			map_list.put("siteBeginServiceDate", siteBeginServiceDate);
			map_list.put("dateStr", DateUtils.formatStandardDate(new Date()));
			map_list.put("successNum", successNum);
			map_list.put("errorNum", errorNum);
			map_list.put("successProportion", successProportion);
			map_list.put("errorProportion", errorProportion);
			map_list.put("connDetList", connDetList);
			logger.info("map_list="+map_list);
			writerPrint(JSONObject.fromObject(map_list).toString());
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("饼图，统计，列表数据异常	"+"		siteCode :"+siteCode+" date :"+date);
		}
	}
	
	/**
	 * @Description: 首页连通性情况 折线图和列表
	 * @author liukl --- 2017年3月30日11:38:31     
	 * @return
	 */
	public void queryHomelistOverview(){
		try {
			//从session中获取10位填报单位网站标识码
			String siteCode=getCurrentUserInfo().getChildSiteCode();
			if(StringUtils.isEmpty(siteCode)){
				siteCode=getCurrentUserInfo().getSiteCode();
			}
				
			HashMap<String, Object> map_list = new HashMap<String, Object>();
				
			//昨天的时间   
			yesterdayStr = DateUtils.getYesterdayStr();
			String date = request.getParameter("date");
			dateStr = DateUtils.formatStandardDate(new Date()); // 当天时间
			if(StringUtils.isEmpty(date)){
					date = dateStr;
			}
			String siteBeginServiceDate="";
			
			
			ConnectionAllRequest allRequest = new ConnectionAllRequest();
			List<QueryOrder> querySiteList=new ArrayList<QueryOrder>();
			QueryOrder siteQueryOrder=new QueryOrder("scan_date",QueryOrderType.DESC);
			querySiteList.add(siteQueryOrder);
			allRequest.setQueryOrderList(querySiteList);
			
			
			ConnectionHomeDetailRequest detailRequest = new ConnectionHomeDetailRequest();
			detailRequest.setQueryOrderList(querySiteList);
			/**老合同信息**/
			if (StringUtils.isNotEmpty(date)) {
				allRequest.setScanDate(date);
				detailRequest.setScanDate(date);
				map_list.put("yesterdayStr", DateUtils.formatDate(DateUtils.parseStandardDate(date)));
				map_list.put("yesterdayStrEng", date);// 用于页面给后台传递参数
			} else {
				detailRequest.setScanDate(yesterdayStr);
				map_list.put("yesterdayStr", DateUtils.formatDate(DateUtils.parseStandardDate(yesterdayStr)));
				map_list.put("yesterdayStrEng", yesterdayStr);// 用于页面给后台传递参数
				allRequest.setScanDate(yesterdayStr);
				List<ContractInfo> contractList = getContractInfoList(siteCode,
						DateUtils.formatStandardDate(new Date()));
				if (contractList != null && contractList.size() > 0) {
					siteBeginServiceDate = DateUtils.formatStandardDate(contractList.get(0).getContractBeginTime());
				}
			}
			
			
			/** 新产品信息 **/
			// detailRequest.setScanDate(yesterdayStr);
			// map_list.put("yesterdayStr",
			// DateUtils.formatDate(DateUtils.parseStandardDate(yesterdayStr)));
			// map_list.put("yesterdayStrEng", yesterdayStr);// 用于页面给后台传递参数
			// allRequest.setScanDate(yesterdayStr);
			// Integer[] productTypeArr = { CrmProductsType.DETECTION.getCode()
			// };
			// List<CrmProductsResponse> crmList = getCrmProductsList(siteCode,
			// productTypeArr,
			// DateUtils.formatStandardDate(new Date()), null, null);
			// if (CollectionUtils.isNotEmpty(crmList)) {
			// siteBeginServiceDate = crmList.get(0).getBeginTime();
			// }
			
			String uri = databaseBizServiceImpl.getDatabaseUrl(siteCode); // 当前站点url
			
			Date d = DateUtils.parseStandardDate(dateStr);   // 当天时间  
			if(StringUtils.isEmpty(date) && date == ""){
				date = yesterdayStr;
			}
			Date da = DateUtils.parseStandardDate(date);     // 前台传值时间
			String type = ConnectionType.HOME.getCode().toString();
			Root root = connectionAllServiceImpl.connectivityByRoot(siteCode, dateStr, type);
            if (d.getTime() <= da.getTime()) {
    			String scanTime = "";
    			String stateName = "";
    			if(root.getResponse().equals("success")){
    				List<Result> rootlist = root.getResults();
    				if(rootlist.size() > 0 && rootlist != null){
    					for (Result re : rootlist) {
							if (StringUtils.isNotEmpty(re.getUrl())) {
								if (uri.equals(re.getUrl())) {
									if (re.getDetails().size() > 0 && re.getDetails() != null) {
										for (Detail de : re.getDetails()) {
											HashMap<String, Object> map = new HashMap<String, Object>();
											scanTime = de.getStime();
											map.put("scanTime", scanTime);
											stateName = ConnectionState.TIMEOUT.getName();
											map.put("stateName", stateName);

											String questionCode = de.getCode();
											if (StringUtils.isNotEmpty(questionCode)) {
												map.put("questionCode", questionCode);
												map.put("questionDescribe", QuestionType.getNameByCode(questionCode));
											} else {
												map.put("questionCode", "");
												map.put("questionDescribe", "");
											}
											connDetList.add(map);
										}
									}
    							}
    						}
    					}
    				}
    			}
			}else{
				detailRequest.setQueryOrderList(querySiteList);
				detailRequest.setState(ConnectionState.TIMEOUT.getCode());
				detailRequest.setSiteCode(siteCode);
				detailRequest.setPageSize(Integer.MAX_VALUE);
				List<ConnectionHomeDetail> queryList = connectionHomeDetailServiceImpl.queryList(detailRequest);
				if(queryList != null && queryList.size() > 0){
					for (ConnectionHomeDetail connectionHomeDetail : queryList) {
						HashMap<String, Object> map = new HashMap<String, Object>();
						String scanTime = connectionHomeDetail.getScanTime();
						map.put("scanTime", scanTime);
						Integer state = connectionHomeDetail.getState();
						if(state!=null){
							if(ConnectionState.TIMEOUT.getCode()==state){
								String stateName = ConnectionState.TIMEOUT.getName();
								map.put("stateName", stateName);
							}
						}else{
							map.put("stateName", "");
						}

//						String questionDescribe = connectionHomeDetail.getQuestionDescribe();
						String questionCode = connectionHomeDetail.getQuestionCode();
						if(StringUtils.isNotEmpty(questionCode)){
							map.put("questionCode", questionCode);
							map.put("questionDescribe", QuestionType.getNameByCode(questionCode));
						}else{
							map.put("questionCode", "");
							map.put("questionDescribe", "");
						}
						connDetList.add(map);
					}
				}
			}
            
            
          //获取前90天的开始时间
			String nextDay = DateUtils.getNextDay(new Date(), -14);
			//获取昨天的日期endDate
			String endDate = DateUtils.getNextDay(new Date(), -1);
			ConnectionAllRequest request = new ConnectionAllRequest();
			
			//获取90天的所有日期
			List<Date> dateList=getDateTimeTwoDay(nextDay,endDate);
			
			//查询连通性统计表中
			List<QueryOrder> querySiteListLine=new ArrayList<QueryOrder>();
			QueryOrder siteQueryOrderLine=new QueryOrder("scan_date",QueryOrderType.ASC);
			querySiteListLine.add(siteQueryOrderLine);
			request.setQueryOrderList(querySiteListLine);
			
			request.setStartDate(nextDay);
			request.setEndDate(endDate);
			request.setPageSize(Integer.MAX_VALUE);
			request.setSiteCode(siteCode);
			request.setType(1);
			ArrayList<Object> list = new ArrayList<Object>();
			List<ConnectionAll> queryList = connectionAllServiceImpl.queryList(request);
			
			if(queryList!=null && queryList.size()>0){
				for(int i=0;i<dateList.size();i++){
					String dateStr1=DateUtils.formatStandardDate(dateList.get(i));
					Map<String, Object> map=new HashMap<String, Object>();
					for(int j=0;j<queryList.size();j++){
						ConnectionAll connAll=queryList.get(j);
						if(connAll.getScanDate().equals(dateStr1)){
							map.put("errorNum", connAll.getErrorNum());
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
            
            
//			String uri = databaseBizServiceImpl.getDatabaseUrl(siteCode); // 当前站点url
//			dateStr = DateUtils.formatStandardDate(new Date()); // 当天时间
//			String type = ConnectionType.HOME.getCode().toString();
//			Root root = connectionAllServiceImpl.connectivityByRoot(siteCode, dateStr, type);
			if(root.getResponse().equals("success")){
				List<Result> results = root.getResults();
				if (CollectionUtils.isNotEmpty(results)) {
					for (Result re : results) {
						if (StringUtils.isNotEmpty(re.getUrl())) {
							if (uri.equals(re.getUrl())) {
								if (StringUtils.isNotEmpty(re.getFailnum())) {
									errorNum = Integer.valueOf(re.getFailnum());
								} else {
									errorNum = 0;
								}
							} else {
								errorNum = 0;
							}
						} else {
							errorNum = 0;
						}
					}
				} else {
					errorNum = 0;
				}

				if(StringUtils.isNotEmpty(root.getDate())){ 
					dateStr = root.getDate();
				}else {
					dateStr = DateUtils.formatStandardDate(new Date());  //  当天时间
				}
				list.add("{errorNum="+errorNum+", scanDate="+dateStr+"}");
			}
			
            map_list.put("siteBeginServiceDate", siteBeginServiceDate);
            map_list.put("list", list);
            map_list.put("connDetList", connDetList);//监测结果列表集合
        	writerPrint(JSONObject.fromObject(map_list).toString());		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description: 组织单位概览页面初始化  全部
	 * @author cuichx --- 2015-11-16上午11:31:42     
	 * @return
	 */
	public String indexOrg(){
		/*
		 * List<DatabaseTreeInfo> treeInfoList = getTreeInfoList(); if
		 * (treeInfoList != null && treeInfoList.size() > 0) { // 组织单位登录 return
		 * "success"; } else { // 填报单位登录 return "loginFill"; }
		 */
		return "success";
	}

	/**
	 * 
	 * @描述:组织单位-首页面:列表+总计
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2017年1月14日下午12:03:21
	 */
	@SuppressWarnings("unused")
	public void getSumResult() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String siteCode = getCurrentSiteCode();
		try {
			// 首页色块数据
			String endDate = queryHomePageDate(); // 获取昨天的日期
			String connNumDetection = null;// 总不连通次数
			Integer coHome = 0; // 首页不连通数
			Integer coChannel = 0; // 栏目不连通次数
			Integer coBusiness = 0; // 业务系统不连通数
			Integer linkHomeDetection = 0;// 总链数
			Integer lkHome = 0; // 首页死链数
			Integer contGuaNum = 0;// 内容保障问题总数
			Integer seHome = 0; // 内容保障：首页不更新
			Integer seChannel = 0; // 内容保障：栏目不更新
			Integer conCorrectNum = 0; // 疑似错别字
			Integer contUpdateDetection = 0; // 内容更新总数
			Integer upHome = 0; // 首页更新数
			Integer upChannel = 0; // 栏目更新数

			DetectionOrgCount deteOrgCount = new DetectionOrgCount();
			List<QueryOrder> queryOrderOrgList = new ArrayList<QueryOrder>(); // 排序
			QueryOrder queryOrder = new QueryOrder("scan_date", QueryOrderType.ASC);
			queryOrderOrgList.add(queryOrder);
			DetectionOrgCountRequest dataReq = new DetectionOrgCountRequest();
			dataReq.setScanDate(endDate);
			dataReq.setSiteCode(siteCode);
			dataReq.setType(DatabaseLinkType.ALL.getCode().toString()); // 类型（0,全部【除了关停与另外】，2本级部门，3下属单位，6其他）
			dataReq.setQueryOrderList(queryOrderOrgList);
			List<DetectionOrgCount> deteList = detectionOrgCountServiceImpl.queryList(dataReq);
			if (deteList != null && deteList.size() > 0) {
				deteOrgCount = deteList.get(0);
				Integer connNum = 0;
				connNum = deteOrgCount.getConnNum();
				if (connNum != null) {
					connNumDetection = connNum.toString();
				} else {
					connNumDetection = "0";
				}
				coHome = deteOrgCount.getConnHome();
				coChannel = deteOrgCount.getConnChannel();
				coBusiness = deteOrgCount.getConnBusiness();
				linkHomeDetection = deteOrgCount.getLinkNum();
				lkHome = deteOrgCount.getLinkHome();
				seHome = deteOrgCount.getSecurityHome();
				seChannel = deteOrgCount.getSecurityChannel();
				conCorrectNum = deteOrgCount.getContCorrectNum();
				contUpdateDetection = deteOrgCount.getContUpdate();
				upHome = deteOrgCount.getUpdateHome();
				upChannel = deteOrgCount.getUpdateChannel();
			}

			HashMap<String, Object> hashMap = new HashMap<String, Object>();
			hashMap.put("orgSiteCode", siteCode);
			hashMap.put("scanDate", endDate);
			hashMap.put("isLink", DatabaseTreeInfoType.ISLINK.getCode());
			hashMap.put("isExp", DatabaseInfoType.NORMAL.getCode());
			hashMap.put("siteType", DatabaseLinkType.ISORGANIZATIONAL.getCode());

			List<Map<String, Object>> levelList = null;
			List<Map<String, Object>> underlingList = null;
			levelList = homeDetectionResult(hashMap); // 获取本级门户
			hashMap.remove("siteType");
			underlingList = homeDetectionResult(hashMap); // 获取下级单位

			// 不连通数
			resultMap.put("conn", connNumDetection == null ? 0 : connNumDetection);
			resultMap.put("coHome", coHome);
			resultMap.put("coChannel", coChannel);
			resultMap.put("coBusiness", coBusiness);
			// 不可用连接
			resultMap.put("link", linkHomeDetection);
			resultMap.put("lkHome", lkHome);
			// 内容保障问题
			contGuaNum = seHome + seChannel;
			resultMap.put("contguarante", contGuaNum);
			resultMap.put("seHome", seHome);
			resultMap.put("seChannel", seChannel);
			// 疑似错别字
			resultMap.put("contcorrect", conCorrectNum);
			// 内容更新
			resultMap.put("contenUpdate", contUpdateDetection);
			resultMap.put("upHome", upHome);
			resultMap.put("upChannel", upChannel);
			resultMap.put("levelList", levelList);
			resultMap.put("listOrg", underlingList);
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Map<String, Object>> homeDetectionResult(HashMap<String, Object> hashMap) {
		List<Map<String, Object>> listOrg = new ArrayList<Map<String, Object>>();
		try {
			// 首页table数据
			String stCode = "";
			Integer silentType = 0; // 0未检测，1检测中，2已结束
			Integer silentNum = 0; // 安全问题个数

			List<DetectionResult> queryList = detectionResultServiceImpl.getList(hashMap);
			if (queryList != null && queryList.size() > 0) {
				for (DetectionResult detectionResult : queryList) {
					HashMap<String, Object> map = new HashMap<String, Object>();
					stCode = detectionResult.getSiteCode();
					map.put("siteCode", stCode);
					silentType = detectionResult.getSilentType();
					silentNum = detectionResult.getSilentNum();
					map.put("silentType", silentType);
					if (silentType == 1) {
						map.put("silentNum", silentNum); // 安全问题个数
					} else if (silentType == 2) {
						map.put("silentNum", silentNum); // 安全问题个数
					}

					map.put("url", detectionResult.getUrl());
					map.put("siteName", detectionResult.getSiteName());
					int connHome = detectionResult.getConnHome();
					map.put("connHome", connHome);
					int linkNum = detectionResult.getLinkNum();
					map.put("linkNum", linkNum);
					Integer securityHome = detectionResult.getSecurityHome();
					map.put("securityHome", securityHome);

					int contCorrectNum = detectionResult.getContCorrectNum();
					map.put("contCorrectNum", contCorrectNum);
					int websiteSafe = detectionResult.getWebsiteSafe();
					map.put("websiteSafe", websiteSafe);
					int contUpdate = detectionResult.getContUpdate();
					map.put("contUpdate", contUpdate);

					double convertScores = detectionResult.getConvertScores();
					int iscost = getCurrentUserInfo().getIsOrgCost();
					map.put("isOrgCost", iscost);
					String totalSumNumber = getHealthScores(convertScores, iscost);
					map.put("convertScores", totalSumNumber);
					// 网站简介
					String director = detectionResult.getDirector();
					map.put("siteManageUnit", director);
					String address = detectionResult.getAddress();
					map.put("officeAddress", address);
					String principalName = detectionResult.getPrincipalName();
					map.put("relationName", principalName);
					String telephone = detectionResult.getTelephone();
					map.put("relationCellphone", telephone);
					String mobile = detectionResult.getMobile();
					map.put("relationPhone", mobile);
					String email = detectionResult.getEmail();
					map.put("relationEmail", email);
					String linkmanName = detectionResult.getLinkmanName();
					map.put("linkman", linkmanName);
					String telephone2 = detectionResult.getTelephone2();
					map.put("linkmanCellphone", telephone2);
					String mobile2 = detectionResult.getMobile2();
					map.put("linkmanPhone", mobile2);
					String email2 = detectionResult.getEmail2();
					map.put("linkmanEmail", email2);
					int isorganizational = detectionResult.getIsorganizational();
					map.put("isorganizational", isorganizational);
					listOrg.add(map);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listOrg;
	}

	/**
	 * @Description: 组织单位-日常监测：excel导出
	 * @author sunjiang --- 2015年11月23日下午3:25:42
	 */
	public void indexOrgExcel() {
		// 获取昨天的日期
		String endDate = queryHomePageDate();
		String siteCode = getCurrentSiteCode();

		ArrayList<Object[]> list = new ArrayList<Object[]>();
		String title = "监测结果概览";
		String fileName = "监测结果概览(" + DateUtils.formatStandardDate(new Date()) + ").xls";
		Object[] obj1 = new Object[] { "网站标识码", "网站名称", "健康指数", "首页不连通(次)", "首页不可用链接(个)", "首页更新情况", "疑似错别字(个)",
				"安全问题(个)", "新稿件(条)" };
		list.add(obj1);
		try {

			Integer silentType = 0; // 0未检测，1检测中，2已结束
			Integer silentNum = 0; // 安全问题个数
			HashMap<String, Object> hashMap = new HashMap<String, Object>();
			hashMap.put("orgSiteCode", siteCode);
			hashMap.put("scanDate", endDate);
			hashMap.put("isLink", DatabaseTreeInfoType.ISLINK.getCode());
			hashMap.put("isExp", DatabaseInfoType.NORMAL.getCode());
			List<DetectionResult> queryList = detectionResultServiceImpl.getList(hashMap);
			if (queryList != null && queryList.size() > 0) {
				for (DetectionResult detectionResult : queryList) {
					Object[] objlist = new Object[9];
					String stCode = detectionResult.getSiteCode();
					String siteName = detectionResult.getSiteName();
					int connHome = detectionResult.getConnHome();
					int linkNum = detectionResult.getLinkNum();
					Integer securityHome = detectionResult.getSecurityHome();
					String updateName = "";
					if (securityHome == 1) {
						updateName = "超过2周";
					} else {
						updateName = "正常更新";
					}
					int contCorrectNum = detectionResult.getContCorrectNum();
					int contUpdate = detectionResult.getContUpdate();
					double convertScores = detectionResult.getConvertScores();
					int iscost = getCurrentUserInfo().getIsOrgCost();
					String totalSumNumber = getHealthScores(convertScores, iscost);

					silentType = detectionResult.getSilentType();
					silentNum = detectionResult.getSilentNum();

					if (silentType == 0) {
						objlist[7] = "未检测";
					} else if (silentType == 1) {
						objlist[7] = silentNum; // 安全问题个数
					} else {
						objlist[7] = silentNum; // 安全问题个数
					}

					objlist[0] = stCode;
					objlist[1] = siteName;
					objlist[2] = totalSumNumber;
					objlist[3] = connHome;
					objlist[4] = linkNum;
					objlist[5] = updateName;
					objlist[6] = contCorrectNum;
					objlist[8] = contUpdate;
					list.add(objlist);
				}
			}
			ExportExcel.checkResultExcel(fileName, title, list);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("ConnectionHomeDetailAction.indexOrgExcel()数据异常");
		}
	}

	/**
	 * @Description: 跳转内容正确性页面
	 * @author sunjiang --- 2015-12-1下午5:45:30
	 * @return
	 */
	public String correct(){
		return "success";
	}

	// /**
	// * @Description:更新newNum
	// * @author sunjiang --- 2015年11月24日下午6:13:21
	// */
	// public void updateNewNum(){
	// try {
	// String type = request.getParameter("type");
	// HashMap<String, Object> hashMap = new HashMap<String, Object>();
	// ServicePeriod before = getPrePeriod();
	// String startTime = DateUtils.formatStandardDate(before.getStartDate());
	// String endTime = DateUtils.formatStandardDate(before.getEndDate());
	// //获取最新的时间 yyyy-mm-dd
	// String maxScanDate = detectionResultServiceImpl.maxScanDate();
	//
	// //获取siteCode
	// String menuType = request.getParameter("menuType");
	// String currentSiteCode = getCurrentSiteCode();
	//
	// List<DatabaseInfo> currentNextSiteCode =
	// queryDatebaseInfoListByType(menuType, currentSiteCode);
	//
	// if(type.equals("3")||type.equals("4")){
	// hashMap.put("beginScanDate", startTime);
	// hashMap.put("endScanDate", endTime);
	// }else{
	// hashMap.put("scanDate", maxScanDate);
	// }
	// hashMap.put("ids", currentNextSiteCode);
	// hashMap.put("newNum", 0);
	// hashMap.put("type", type);
	// detectionResultServiceImpl.batchUpdate(hashMap);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	//
	// }
	/**
	 * @Description: 填报单位-日常监测  --网站连通性
	 * @author sunjiang --- 2015年11月12日下午2:32:43
	 */
	public void getBusinessOrgLine(){
		try {
			//从session中获取10位填报单位网站标识码
			String siteCode=getCurrentUserInfo().getChildSiteCode();
			if(StringUtils.isEmpty(siteCode)){
				siteCode=getCurrentUserInfo().getSiteCode();
			}
			HashMap<String, Object> map_request = new HashMap<String, Object>();
			//获取前90天的开始时间
			String nextDay = DateUtils.getNextDay(new Date(), -90);
			//获取昨天的日期
			String endDate = DateUtils.getNextDay(new Date(), -1);
			
			//获取前90天的所有日期
			map_request.put("startDate", nextDay);
			map_request.put("endDate", endDate);
			map_request.put("siteCode", siteCode);
			HashMap<String, Object> map = new HashMap<String, Object>();
			ArrayList<Object> scanTimelist = new ArrayList<Object>();
			ArrayList<Object> homelist = new ArrayList<Object>();
			ArrayList<Object> businesslist = new ArrayList<Object>();
			ArrayList<Object> channellist = new ArrayList<Object>();
			List<ConnectionAllRequest> queryList = connectionAllServiceImpl.getHomeBar(map_request);
			if(queryList.size()>0){
				for (int i = 0; i < queryList.size(); i++) {
					ConnectionAllRequest connectionAllRequest = queryList.get(i);
					int buseNum = connectionAllRequest.getBuseNum();
					businesslist.add(buseNum);
					int homeNum = connectionAllRequest.getHomeNum();
					homelist.add(homeNum);
					int channelNum = connectionAllRequest.getChannelNum();
					channellist.add(channelNum);
					String scanDate = connectionAllRequest.getScanDate();
					scanTimelist.add(DateUtils.formatStandardDate(scanDate));
				}
			}
			map.put("businesslist", businesslist);
			map.put("homelist", homelist);
			map.put("channellist", channellist);
			map.put("scanTimelist", scanTimelist);
			writerPrint(JSONObject.fromObject(map).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * @Description: 组织单位-日常监测  --网站连通性
	 * @author sunjiang --- 2015年11月12日下午2:32:43
	 */
	public void getBusinesLine(){
		try {
            String currentSiteCode = getCurrentSiteCode();

			String endDate = DateUtils.getNextDay(queryHomePageDate(), "0");
			String nextDay = DateUtils.getNextDay(queryHomePageDate(), "-6");
			
			DetectionOrgCountRequest detectionOrgCountRequest=new DetectionOrgCountRequest();
			detectionOrgCountRequest.setStartDate(nextDay);
			detectionOrgCountRequest.setEndDate(endDate);
			detectionOrgCountRequest.setSiteCode(currentSiteCode);
			detectionOrgCountRequest.setType(DatabaseLinkType.ALL.getCode().toString());
			List<QueryOrder> queryOrderOrgList=new ArrayList<QueryOrder>();
			QueryOrder queryOrder=new QueryOrder("scan_date",QueryOrderType.ASC);
			queryOrderOrgList.add(queryOrder);
			detectionOrgCountRequest.setQueryOrderList(queryOrderOrgList);
			List<DetectionOrgCount> detectionOrgCountList=detectionOrgCountServiceImpl.queryList(detectionOrgCountRequest);
			HashMap<String, Object> map = new HashMap<String, Object>();
			ArrayList<Object> scanTimelist = new ArrayList<Object>();
			ArrayList<Object> homelist = new ArrayList<Object>();
			ArrayList<Object> businesslist = new ArrayList<Object>();
			ArrayList<Object> channellist = new ArrayList<Object>();
			if(detectionOrgCountList.size()>0){
				for (int i = 0; i < detectionOrgCountList.size(); i++) {
					DetectionOrgCount detectionOrgCount = detectionOrgCountList.get(i);
					int buseNum = detectionOrgCount.getConnBusiness();//业务
					businesslist.add(buseNum);
					int homeNum = detectionOrgCount.getConnHome();//首页
					homelist.add(homeNum);
					int channelNum = detectionOrgCount.getConnChannel();//栏目
					channellist.add(channelNum);
					String scanDate = detectionOrgCount.getScanDate();
					scanTimelist.add(DateUtils.formatStandardDate(scanDate));
				
				}
			}
			map.put("businesslist", businesslist);
			map.put("homelist", homelist);
			map.put("channellist", channellist);
			map.put("scanTimelist", scanTimelist);
			writerPrint(JSONObject.fromObject(map).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**  根据接口获取数据
	 * @Description: 首页连通性的时间坐标折线图
	 * @author qinjy
	 */
	public void getHomeTrendLine(){
		//从session中获取10位填报单位网站标识码
		String siteCode=getCurrentUserInfo().getChildSiteCode();
		if(StringUtils.isEmpty(siteCode)){
			siteCode=getCurrentUserInfo().getSiteCode();
		}
		try {
			//获取前90天的开始时间
			String nextDay = DateUtils.getNextDay(new Date(), -14);
			//获取昨天的日期endDate
			String endDate = DateUtils.getNextDay(new Date(), -1);
			ConnectionAllRequest request = new ConnectionAllRequest();
			
			//获取90天的所有日期
			List<Date> dateList=getDateTimeTwoDay(nextDay,endDate);
			
			//查询连通性统计表中
			List<QueryOrder> querySiteList=new ArrayList<QueryOrder>();
			QueryOrder siteQueryOrder=new QueryOrder("scan_date",QueryOrderType.ASC);
			querySiteList.add(siteQueryOrder);
			request.setQueryOrderList(querySiteList);
			
			request.setStartDate(nextDay);
			request.setEndDate(endDate);
			request.setPageSize(Integer.MAX_VALUE);
			request.setSiteCode(siteCode);
			request.setType(1);
			ArrayList<Object> list = new ArrayList<Object>();
			List<ConnectionAll> queryList = connectionAllServiceImpl.queryList(request);
			
			if(queryList!=null && queryList.size()>0){
				for(int i=0;i<dateList.size();i++){
					String dateStr1=DateUtils.formatStandardDate(dateList.get(i));
					Map<String, Object> map=new HashMap<String, Object>();
					for(int j=0;j<queryList.size();j++){
						ConnectionAll connAll=queryList.get(j);
						if(connAll.getScanDate().equals(dateStr1)){
							map.put("errorNum", connAll.getErrorNum());
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
			
			String uri = databaseBizServiceImpl.getDatabaseUrl(siteCode); // 当前站点url
			dateStr = DateUtils.formatStandardDate(new Date()); // 当天时间
			String type = ConnectionType.HOME.getCode().toString();
			Root root = connectionAllServiceImpl.connectivityByRoot(siteCode, dateStr, type);
			if(root.getResponse().equals("success")){
				List<Result> results = root.getResults();
				if (CollectionUtils.isNotEmpty(results)) {
					for (Result re : results) {
						if (StringUtils.isNotEmpty(re.getUrl())) {
							if (uri.equals(re.getUrl())) {
								if (StringUtils.isNotEmpty(re.getFailnum())) {
									errorNum = Integer.valueOf(re.getFailnum());
								} else {
									errorNum = 0;
								}
							} else {
								errorNum = 0;
							}
						} else {
							errorNum = 0;
						}
					}
				} else {
					errorNum = 0;
				}

				if(StringUtils.isNotEmpty(root.getDate())){ 
					dateStr = root.getDate();
				}else {
					dateStr = DateUtils.formatStandardDate(new Date());  //  当天时间
				}
				list.add("{errorNum="+errorNum+", scanDate="+dateStr+"}");
			}
			logger.info("getHomeTrendLine list:"+list);
			writerPrint(JSONArray.fromObject(list).toString());
		} catch (Exception e) {
			e.printStackTrace();
	  }
		
	}	
	
	/**调接口数据
	 * @Description: 首页连通性 excel 导出
	 * @author qinjy
	 */
	public void homeExcel(){
		//从session中获取10位填报单位网站标识码
		String siteCode=getCurrentUserInfo().getChildSiteCode();
		if(StringUtils.isEmpty(siteCode)){
			siteCode=getCurrentUserInfo().getSiteCode();
		}
		String date = request.getParameter("date");
		yesterdayStr = DateUtils.getYesterdayStr();
		dateStr = DateUtils.formatStandardDate(new Date()); // 当天时间
		if(StringUtils.isNotEmpty(date)){
			dateStr = date;
		}
		ArrayList<Object[]> list = new ArrayList<Object[]>();
		Object[] obj1 = new Object[]{"序号","时间","连通状态","问题描述"};
		list.add(obj1);
		String fileName = "网站连通性-首页连通性监测("+dateStr+").xls";
		String title = "首页连通性监测"; 
		String scanTime = "";
		String stateName = "";
		try {
			String uri = databaseBizServiceImpl.getDatabaseUrl(siteCode); // 当前站点url
			//列表
			Date d = DateUtils.parseStandardDate(dateStr);   // 当天时间  
			if(StringUtils.isEmpty(date) && date == ""){
				date = yesterdayStr;
			}
			Date da = DateUtils.parseStandardDate(date);     // 前台传值时间
            if (d.getTime() > da.getTime()) {
            	String type = ConnectionType.HOME.getCode().toString();
    			Root root = connectionAllServiceImpl.connectivityByRoot(siteCode, dateStr, type);
    			if(root.getResponse().equals("success")){
    				List<Result> rootlist = root.getResults();
					int j = 0;
    				if(rootlist.size() > 0 && rootlist != null){
    					for (Result re : rootlist) {
							if (StringUtils.isNotEmpty(re.getUrl())) {
								if (uri.equals(re.getUrl())) {
									if (re.getDetails().size() > 0 && re.getDetails() != null) {
										for (int i = 0; i < re.getDetails().size(); i++) {
											j++;
											Detail de = re.getDetails().get(i);
											scanTime = de.getStime();
											stateName = ConnectionState.TIMEOUT.getName();
											Object[] obj = new Object[4];
											obj[0] = j;
											obj[1] = scanTime;
											obj[2] = stateName;
											String questionCode = de.getCode();
											if (StringUtils.isNotEmpty(questionCode)) {
												obj[3] = questionCode + "　　" + QuestionType.getNameByCode(questionCode);
											} else {
												obj[3] = "";
											}
											list.add(obj);
										}
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
    			ConnectionHomeDetailRequest detailRequest = new ConnectionHomeDetailRequest();
    			detailRequest.setQueryOrderList(querySiteList);
            	detailRequest.setScanDate(date);
            	detailRequest.setState(ConnectionState.TIMEOUT.getCode());
				detailRequest.setSiteCode(siteCode);
				detailRequest.setPageSize(Integer.MAX_VALUE);
				List<ConnectionHomeDetail> queryList = connectionHomeDetailServiceImpl.queryList(detailRequest);
				int i = 0;
				if(queryList != null && queryList.size() > 0){
					for (ConnectionHomeDetail connectionHomeDetail : queryList) {
						i++;
						Object[] obj = new Object[4];
						obj[0] = i;
						obj[1] = connectionHomeDetail.getScanTime();;
						obj[2] = ConnectionState.TIMEOUT.getName();
						String questionCode = connectionHomeDetail.getQuestionCode();
						if(StringUtils.isNotEmpty(questionCode)){
							obj[3] = questionCode+"　　"+QuestionType.getNameByCode(questionCode);
						}else{
							obj[3] = "";
						}
						list.add(obj);
					}
				}
            }
			ExportExcel.websiteConnHomeExcel(fileName, title, list);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("首页连通性 excel 导出"+"  siteCode :"+siteCode);
		}
		
	}
	/**
	 * @Description: 组织单位-日常监测---内容正确性 折线图
	 * @author sunjiang --- 2015年11月20日上午11:35:38
	 */
	public void contentCorrentLine(){
		try {
			Map<String, Object> map = new HashMap<String,Object>();
			ArrayList<Object> correntList = new ArrayList<Object>();
			ArrayList<Object> timeList = new ArrayList<Object>();
					
			String currentSiteCode = getCurrentSiteCode();
			String endDate = DateUtils.getNextDay(queryHomePageDate(), "0");
			String nextDay = DateUtils.getNextDay(queryHomePageDate(), "-6");
			
			//内容正确性
			DetectionOrgCountRequest detectionOrgCountRequest=new DetectionOrgCountRequest();
			detectionOrgCountRequest.setSiteCode(currentSiteCode);
			detectionOrgCountRequest.setStartDate(nextDay);
			detectionOrgCountRequest.setEndDate(endDate);
			detectionOrgCountRequest.setType(DatabaseLinkType.ALL.getCode().toString());
			List<QueryOrder> queryOrderOrgList=new ArrayList<QueryOrder>();
			QueryOrder queryOrder=new QueryOrder("scan_date",QueryOrderType.ASC);
			queryOrderOrgList.add(queryOrder);
			detectionOrgCountRequest.setQueryOrderList(queryOrderOrgList);
			List<DetectionOrgCount> detectionOrgCountList=detectionOrgCountServiceImpl.queryList(detectionOrgCountRequest);
			
			for (DetectionOrgCount detectionOrgCount : detectionOrgCountList) {
				Integer blankNum = detectionOrgCount.getContCorrectNum();
				correntList.add(blankNum);
				timeList.add(DateUtils.formatStandardDate(detectionOrgCount.getScanDate()));
			}
			map.put("correntList", correntList);
			map.put("timeList", timeList);
			writerPrint(JSONObject.fromObject(map).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * @Description: 公共头部预警信息的数量
	 * @author sunjiang --- 2015-11-30下午12:28:15     
	 * @return
	 */
	public String queryEarlyCount(){
		
		try {
			List<DatabaseInfo> currentNextSiteCode = getCurrentNextSiteCode();
			
			//填报单位
			String siteCode = request.getParameter("siteCode");
			if(StringUtils.isNotEmpty(siteCode)){
				ArrayList<DatabaseInfo> list = new ArrayList<DatabaseInfo>();
				DatabaseInfo databaseInfo = new DatabaseInfo();
				databaseInfo.setSiteCode(siteCode);
				list.add(databaseInfo);
				currentNextSiteCode = list;
			}
			//填报单位--end
			HashMap<String, Object> param = new HashMap<String, Object>();
			param.put("websiteList", currentNextSiteCode);
			
			int earlyNum = 0;
			EarlyInfo earlyInfo = earlyInfoServiceImpl.querySum(param);
			if(earlyInfo != null ){
				earlyNum = earlyInfo.getNewEarlyNum();
			}
			
			result_map.put("earlyCount", earlyNum);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	/**
	 * @Description:更新 公共头部预警信息的数量 设置为0 
	 * @author sunjiang --- 2015-11-30下午2:29:32
	 */
	public void updateEarlyCount(){
		try {
			List<DatabaseInfo> currentNextSiteCode = getCurrentNextSiteCode();
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			ArrayList<Object> typelist = new ArrayList<Object>();
			//获取昨天的日期
			String endDate = DateUtils.getNextDay(new Date(), -1);
			paramMap.put("ids", currentNextSiteCode);
			paramMap.put("scanDate", endDate);
			//不连通类型
			typelist.add(DetectionResultType.CONNTYPE.getCode());
			//不可用连接类型
			typelist.add(DetectionResultType.LINKTYPE.getCode());
			//内容更新
			typelist.add(DetectionResultType.CONTUPDATETYPE.getCode());
			paramMap.put("typelist", typelist);
			paramMap.put("newNum", 0);
			detectionResultServiceImpl.batchUpdate(paramMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * @Description: 内容保障问题分析图 
	 * @author sunjiang --- 2015-12-3下午4:45:29
	 * yangshuai --修改-- 2016-6-12下午7:47:37
	 */
	public void getLineList(){
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
            String currentSiteCode = getCurrentSiteCode();
            String end = DateUtils.getNextDay(queryHomePageDate(), "0");
			String begin = DateUtils.getNextDay(queryHomePageDate(), "-6");
			
			DetectionOrgCountRequest detectionOrgCountRequest=new DetectionOrgCountRequest();
			detectionOrgCountRequest.setStartDate(begin);
			detectionOrgCountRequest.setEndDate(end);
			detectionOrgCountRequest.setSiteCode(currentSiteCode);
			detectionOrgCountRequest.setType(DatabaseLinkType.ALL.getCode().toString());
			List<QueryOrder> queryOrderOrgList=new ArrayList<QueryOrder>();
			QueryOrder queryOrder=new QueryOrder("scan_date",QueryOrderType.ASC);
			queryOrderOrgList.add(queryOrder);
			detectionOrgCountRequest.setQueryOrderList(queryOrderOrgList);
			List<DetectionOrgCount> lineList = detectionOrgCountServiceImpl.queryList(detectionOrgCountRequest);
            
			ArrayList<Object> dateList = new ArrayList<Object>();//时间
			ArrayList<Object> homeList = new ArrayList<Object>();//首页不更新
			ArrayList<Object> channelList = new ArrayList<Object>();//栏目不更新
			ArrayList<Object> blankList = new ArrayList<Object>();//空白栏目
			ArrayList<Object> resposeList = new ArrayList<Object>();//互动回应差
			ArrayList<Object> serviceList = new ArrayList<Object>();//服务不实用
			if(lineList.size()>0){
				for (DetectionOrgCount detectionOrgCount : lineList) {
					dateList.add(detectionOrgCount.getScanDate());
					homeList.add(detectionOrgCount.getSecurityHome());
					channelList.add(detectionOrgCount.getSecurityChannel());
					blankList.add(detectionOrgCount.getSecurityBlank());
					resposeList.add(detectionOrgCount.getSecurityResponse());
					serviceList.add(detectionOrgCount.getSecurityService());
				}
			}
			map.put("dateList", dateList);
			map.put("homeList", homeList);
			map.put("channelList", channelList);
			map.put("blankList", blankList);
			map.put("resposeList", resposeList);
			map.put("serviceList", serviceList);
			writerPrint(JSONObject.fromObject(map).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Object> getConnDetList() {
		return connDetList;
	}
	public void setConnDetList(List<Object> connDetList) {
		this.connDetList = connDetList;
	}
	public String getYesterdayStr() {
		return yesterdayStr;
	}
	public void setYesterdayStr(String yesterdayStr) {
		this.yesterdayStr = yesterdayStr;
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
	public HashMap<String, Object> getResult_map() {
		return result_map;
	}
	public void setResult_map(HashMap<String, Object> result_map) {
		this.result_map = result_map;
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
	

}
