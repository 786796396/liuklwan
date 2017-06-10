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
import com.ucap.cloud_web.common.DicUtils;
import com.ucap.cloud_web.constant.CacheType;
import com.ucap.cloud_web.constant.ContentUpdateType;
import com.ucap.cloud_web.constant.DatabaseInfoType;
import com.ucap.cloud_web.constant.DatabaseLinkType;
import com.ucap.cloud_web.constant.DatabaseTreeInfoType;
import com.ucap.cloud_web.dto.DetectionOrgCountRequest;
import com.ucap.cloud_web.dto.UpdateContentCountRequest;
import com.ucap.cloud_web.dto.UpdateHomeDetailRequest;
import com.ucap.cloud_web.entity.DetectionOrgCount;
import com.ucap.cloud_web.entity.DicItem;
import com.ucap.cloud_web.entity.UpdateContentCount;
import com.ucap.cloud_web.entity.UpdateHomeDetail;
import com.ucap.cloud_web.service.IDetectionOrgCountService;
import com.ucap.cloud_web.service.IUpdateContentCountService;
import com.ucap.cloud_web.service.IUpdateHomeDetailService;
import com.ucap.cloud_web.util.ExportExcel;
import com.ucap.cloud_web.util.MonitoringCacheUtils;

/**
 * <p>Description: </p> 首页更新页面--填报单位
 * <p>@Package：com.ucap.cloud_web.action </p>
 * <p>Title: UpdateHomeAction </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：cuichx </p>
 * <p>@date：2015-11-12上午9:24:26 </p>
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class UpdateHomeAction extends BaseAction {
	private static Logger logger = Logger.getLogger(UpdateHomeAction.class);
	@Autowired
	private IUpdateHomeDetailService updateHomeDetailServiceImpl;
	@Autowired
	private IUpdateContentCountService  updateContentCountServiceImpl;
	@Autowired
	private IDetectionOrgCountService detectionOrgCountServiceImpl;
	@Autowired
	private DicUtils dicUtils;

	/************************************* 日常监测-首页更新量页面 开始********************************************/
	/**
	 * 
	 * @描述:跳转首页更新量页面--组织单位
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2016年12月20日下午3:07:28
	 * @return
	 */
	public String updateHomeIndexOrg() {
		List<DicItem> dicList = dicUtils.getDictList("siteType");
		request.setAttribute("dicList", dicList); // 网站类别
		request.setAttribute("yesterday", DateUtils.getYesterdayStr()); // 昨天
		return "success";
	}
	
	/**
	 * 
	 * @描述:首页更新量数据
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2016年12月22日上午11:36:45
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public void updateHomeIndexOrgTable() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
		String siteType = request.getParameter("siteType"); // 网站类别
		String startDate = request.getParameter("startDate"); // 开始时间
		String endDate = request.getParameter("endDate"); // 结束时间

		try {
			// 获取当前组织机构编码
			String siteCode = getCurrentSiteCode();

			Integer homeUrlNum = 0; // 首页url总数
			Integer updateNum = 0; // 问题总数
			HashMap<String, Object> hashMap = new HashMap<String, Object>();
			hashMap.put("orgSiteCode", siteCode);
			hashMap.put("type", 0);
			hashMap.put("startDate", startDate);
			hashMap.put("endDate", endDate);
			hashMap.put("isLink", DatabaseTreeInfoType.ISLINK.getCode());
			hashMap.put("isExp", DatabaseInfoType.NORMAL.getCode());
			if (StringUtils.isNotEmpty(siteType) && !siteType.equals("0")) {
				hashMap.put("siteType", siteType);
			}

			String cacheUpdateHome = CacheType.MONITORING_UPDATEHOME.getName();
			String conkey = cacheUpdateHome + siteCode + startDate + endDate + siteType; // 缓存名
			List<UpdateContentCountRequest> queryList = (List<UpdateContentCountRequest>) MonitoringCacheUtils.get(conkey); // 查询缓存中是否存在
			if (queryList == null) {
				queryList = updateContentCountServiceImpl.getUpdateHomeList(hashMap);
				MonitoringCacheUtils.put(conkey, queryList); // 将数据存到缓存中
			}

			if (queryList != null && queryList.size() > 0) {
				for (int i = 0; i < queryList.size(); i++) {
					Map<String, Object> item = new HashMap<String, Object>();
					UpdateContentCountRequest updateHome = queryList.get(i);
					item.put("siteCode", updateHome.getSiteCode());
					item.put("siteName", updateHome.getSiteName());
					if (StringUtils.isNotEmpty(updateHome.getJumpPageUrl())) {
						item.put("url", updateHome.getJumpPageUrl());// URL首页地址
					} else {
						item.put("url", updateHome.getHomePageUrl());// URL首页地址
					}
					// 每个网站的首页更新条数
					item.put("updateCount", updateHome.getUpdateNum());
					items.add(item);
					homeUrlNum++;
					updateNum += updateHome.getUpdateNum();
				}
			}
			resultMap.put("success", "true");
			resultMap.put("body", items);
			resultMap.put("homeUrlNum", homeUrlNum);
			resultMap.put("updateNum", updateNum);
			resultMap.put("size", queryList.size()); // 总条数
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", "false");
			resultMap.put("errorMsg", "查询日常监测-首页更新量数据异常！");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		}
	}

	/**
	 * 
	 * @描述:首页更新量excel导出
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2016年12月22日下午2:09:06
	 */
	@SuppressWarnings("unchecked")
	public void updateHomeIndexOrgTableExcel() {
		ArrayList<Object[]> list = new ArrayList<Object[]>();
		String siteType = request.getParameter("siteType"); // 网站类别
		String startDate = request.getParameter("startDate"); // 开始时间
		String endDate = request.getParameter("endDate"); // 结束时间
		try {
			// 获取当前组织机构编码
			String siteCode = getCurrentSiteCode();

			String title = "首页更新量";
			String fileName = "日常监测-首页更新量(" + DateUtils.formatStandardDate(new Date()) + ").xls";
			Object[] obj1 = new Object[] { "序号", "网站标识码", "网站名称", "首页URL", "更新数量" };
			list.add(obj1);

			HashMap<String, Object> hashMap = new HashMap<String, Object>();
			hashMap.put("orgSiteCode", siteCode);
			hashMap.put("type", 0);
			hashMap.put("startDate", startDate);
			hashMap.put("endDate", endDate);
			hashMap.put("isLink", DatabaseTreeInfoType.ISLINK.getCode());
			hashMap.put("isExp", DatabaseInfoType.NORMAL.getCode());
			if (StringUtils.isNotEmpty(siteType) && !siteType.equals("0")) {
				hashMap.put("siteType", siteType);
			}

			String cacheUpdateHomeExcel = CacheType.MONITORING_UPDATEHOMEEXCEL.getName();
			String conkey = cacheUpdateHomeExcel + siteCode + startDate + endDate + siteType; // 缓存名
			List<UpdateContentCountRequest> queryList = (List<UpdateContentCountRequest>) MonitoringCacheUtils.get(conkey); // 查询缓存中是否存在
			if (queryList == null) {
				queryList = updateContentCountServiceImpl.getUpdateHomeList(hashMap);
				MonitoringCacheUtils.put(conkey, queryList); // 将数据存到缓存中
			}

			if (queryList != null && queryList.size() > 0) {
				for (int i = 0; i < queryList.size(); i++) {
					UpdateContentCountRequest updateHome = queryList.get(i);
					Object[] obj = new Object[5];
					obj[0] = i + 1;
					obj[1] = updateHome.getSiteCode();
					obj[2] = updateHome.getSiteName();
					if (StringUtils.isNotEmpty(updateHome.getJumpPageUrl())) {
						obj[3] = updateHome.getJumpPageUrl();// URL首页地址
					} else {
						obj[3] = updateHome.getHomePageUrl();// URL首页地址
					}
					obj[4] = updateHome.getUpdateNum();
					list.add(obj);
				}
			}
			ExportExcel.organUpdateExcel(fileName, title, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/************************************* 日常监测-首页更新量页面 结束********************************************/
	
	/**
	 * @Description: 首页更新
	 * @author cuichx --- 2015-11-12上午9:26:49     
	 * @return
	 */
	public String updateHomeIndex(){
		try {
			//siteCode处理由组织单位跳转到该页面时，session的修改
			String siteCode = request.getParameter("siteCode");
			if(StringUtils.isNotEmpty(siteCode)){
				setCurrentShiroUser(siteCode);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	
	
	/**
	 * @Description: 首页更新趋势折线图
	 * @author cuichx --- 2015-11-12上午9:30:16
	 */
	public void updateConLine(){
		List<Object> returnList=new ArrayList<Object>();
		try {
			//从session中获取10位填报单位网站标识码
			String siteCode=getCurrentUserInfo().getChildSiteCode();
			if(StringUtils.isEmpty(siteCode)){
				siteCode=getCurrentUserInfo().getSiteCode();
			}
			String scanDate=DateUtils.getYesterdayStr();
			UpdateContentCountRequest updateRequest=new UpdateContentCountRequest();
/*			//设置排序字段
			DatabaseInfoRequest siteRequest=new DatabaseInfoRequest();
			String nowTime=DateUtils.formatStandardDateTime(new Date());
			siteRequest.setNowTime(nowTime);
			siteRequest.setSiteCode(siteCode);*/
				updateRequest.setEndScanDate(scanDate);
				updateRequest.setType(ContentUpdateType.HOME.getCode());
				updateRequest.setSiteCode(siteCode);
				List<QueryOrder> querySiteList=new ArrayList<QueryOrder>();
				QueryOrder siteQueryOrder=new QueryOrder("scan_date",QueryOrderType.ASC);
				querySiteList.add(siteQueryOrder);
				updateRequest.setQueryOrderList(querySiteList);
				
				List<UpdateContentCount> countList=new ArrayList<UpdateContentCount>();
				countList=updateContentCountServiceImpl.queryList(updateRequest);
				
				//将数据处理成前台页面需要的数据
				for (UpdateContentCount updateContentCount : countList) {
					Map<String,Object> map=new HashMap<String, Object>();
					String scanDT=DateUtils.formatStandardDate(updateContentCount.getScanDate());//扫描日期
					int updateNum=updateContentCount.getUpdateNum();//每日更新数量
					map.put("yearMouthDay", scanDT);
					map.put("updateNum", updateNum);
					
					returnList.add(map);
				}
			System.out.println(JSONArray.fromObject(returnList).toString());
			writerPrint(JSONArray.fromObject(returnList).toString());
		
				
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description: 首页更新明细列表--分页查询
	 * @author zhurk --- 2015-11-12上午9:27:52
	 * @throws  
	 */
	public void updateHomePage(){
		//从session中获取10位填报单位网站标识码
		String siteCode=getCurrentUserInfo().getChildSiteCode();
		if(StringUtils.isEmpty(siteCode)){
			siteCode=getCurrentUserInfo().getSiteCode();
		}
		//获取时间选择的天数
		String days=request.getParameter("days");
		//获取关键字查询的关键字
		String key=request.getParameter("key");
		try {
//		key= new String( key.getBytes("iso-8859-1"), "UTF-8");
		UpdateHomeDetailRequest updateHomeDetailRequest=new UpdateHomeDetailRequest();
		updateHomeDetailRequest.setSiteCode(siteCode);
		//int queryCount=updateHomeDetailServiceImpl.queryCount(updateHomeDetailRequest);
		if(StringUtils.isNotEmpty(days)){
			int delay=Integer.parseInt(days);
			//获取days天扫描的日期
			String startDate = DateUtils.getNextDay(new Date(), -delay);
			updateHomeDetailRequest.setBeginScanDate(startDate);
			//获取昨天的日期
			String endDate = DateUtils.getNextDay(new Date(), -1);
			updateHomeDetailRequest.setEndScanDate(endDate);
		}else{//默认查询两周内的更新数据
			String startDate = DateUtils.getNextDay(new Date(), -7);
			updateHomeDetailRequest.setBeginScanDate(startDate);
			//获取昨天的日期
			String endDate = DateUtils.getNextDay(new Date(), -1);
			updateHomeDetailRequest.setEndScanDate(endDate);
		}
		updateHomeDetailRequest.setPageSize(Integer.MAX_VALUE);
		int totalNum=0;
		totalNum=updateHomeDetailServiceImpl.queryCount(updateHomeDetailRequest);
		
		
		String pos = request.getParameter("pos");
		if(StringUtils.isNotEmpty(pos)){
			updateHomeDetailRequest.setPageNo(Integer.parseInt(pos));
		}
		String size = request.getParameter("size");
		if(StringUtils.isNotEmpty(size)){
			updateHomeDetailRequest.setPageSize(Integer.parseInt(size));
		}
		//获取排序条件
		String sSortDir_0 = request.getParameter("sSortDir_0");//控制升序和降序
		String soraFiel = request.getParameter("mDataProp_"+request.getParameter("iSortCol_0"));//获取要排序的字段
		//排序字段
		if(soraFiel!=null){
			List<QueryOrder> querySiteList=new ArrayList<QueryOrder>();
			if(!"".equals(sSortDir_0) && "asc".equals(sSortDir_0)){
				QueryOrder siteQueryOrder=new QueryOrder("update_time",QueryOrderType.ASC);
				querySiteList.add(siteQueryOrder);
				updateHomeDetailRequest.setQueryOrderList(querySiteList);
			}else{
				QueryOrder siteQueryOrder=new QueryOrder("update_time",QueryOrderType.DESC);
				querySiteList.add(siteQueryOrder);
				updateHomeDetailRequest.setQueryOrderList(querySiteList);
			}
		}
		ArrayList<Object> list = new ArrayList<Object>();
		HashMap<String, Object> map_list = new HashMap<String, Object>();
		if(StringUtils.isNotEmpty(key)){
		updateHomeDetailRequest.setTitle(key);
		}
		
		PageVo<UpdateHomeDetail> query=updateHomeDetailServiceImpl.query(updateHomeDetailRequest);
		List<UpdateHomeDetail> data=query.getData();
		
		if(data!=null && data.size()>0){
			for (int i = 0; i < data.size(); i++) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				UpdateHomeDetail updateHomeDetail=data.get(i);
				int beginNum=(Integer.valueOf(pos)-1)*Integer.valueOf(size);
			    map.put("dataNumber", beginNum+i+1);
			    String url = updateHomeDetail.getUrl();
			    map.put("url", url);
			    String updateTime=updateHomeDetail.getUpdateTime();
			    map.put("updateTime",updateTime );
			    String title=updateHomeDetail.getTitle();
				if (!title.equals("null")) {
					map.put("title", title);
				}else{
					map.put("title", "");
				}
				list.add(map);
			}
		}
		
		map_list.put("hide", "#table_data_homeUpdate_hide");
		map_list.put("exists", totalNum);
		map_list.put("body", list);
		map_list.put("totalRecords", query.getRecordSize());
		map_list.put("iTotalDisplayRecords", query.getRecordSize());
		map_list.put("hasMoreItems",true);
		writerPrint(JSONObject.fromObject(map_list).toString());
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("siteCode:"+siteCode+"days:"+days+"key:"+key);
		}
	}
	
	/**
	 * @Description:填报单位：内容更新折线图 
	 * @author sunjiang --- 2015年11月12日下午7:01:30
	 */
	public void contentUpdateLine(){
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		//获取前90天的开始时间
		 String nextDay = DateUtils.getNextDay(new Date(), -91);
		//获取昨天的日期
		 String endDate = DateUtils.getNextDay(new Date(), -1);
		hashMap.put("siteCode", getCurrentUserInfo().getSiteCode());
		hashMap.put("beginScanDate", nextDay);
		hashMap.put("endScanDate", endDate);
		List<UpdateContentCountRequest> queryList = updateContentCountServiceImpl.queryCountLine(hashMap);
		ArrayList<Object> dateList = new ArrayList<Object>();
		ArrayList<Object> homeList = new ArrayList<Object>();
		ArrayList<Object> channelList = new ArrayList<Object>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		if(queryList.size()>0){
			for (int i = 0; i < queryList.size(); i++) {
				UpdateContentCountRequest updateContentCount = queryList.get(i);
				String scanDate = updateContentCount.getScanDate();
				dateList.add(DateUtils.formatStandardDate(scanDate));
				int updateNum = updateContentCount.getHomeNum();
				homeList.add(updateNum);
				int channelNum = updateContentCount.getChannelNum();
				channelList.add(channelNum);
			}
		}
		map.put("dateList", dateList);
		map.put("homeList", homeList);
		map.put("channelList", channelList);
		writerPrint(JSONObject.fromObject(map).toString());
	}
	/**
	 * @Description: 组织单位：内容更新折线图 
	 * @author sunjiang --- 2015年11月28日下午6:00:18
	 */
	public void contentUpdateLineOrg(){
		try {
            String currentSiteCode = getCurrentSiteCode();

            String endDate = DateUtils.getNextDay(queryHomePageDate(), "0");
			String nextDay = DateUtils.getNextDay(queryHomePageDate(), "-6");
			
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
			ArrayList<Object> dateList = new ArrayList<Object>();
			ArrayList<Object> homeList = new ArrayList<Object>();
			ArrayList<Object> channelList = new ArrayList<Object>();
			HashMap<String, Object> map = new HashMap<String, Object>();
			if(detectionOrgCountList.size()>0){
				for (DetectionOrgCount detectionOrgCount : detectionOrgCountList) {
					String scanDate = detectionOrgCount.getScanDate();
					dateList.add(DateUtils.formatStandardDate(scanDate));
					int updateNum = detectionOrgCount.getUpdateHome();
					homeList.add(updateNum);
					int channelNum = detectionOrgCount.getUpdateChannel();
					channelList.add(channelNum);
				}
			}
			map.put("dateList", dateList);
			map.put("homeList", homeList);
			map.put("channelList", channelList);
			writerPrint(JSONObject.fromObject(map).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** @Description:首页更新明细--导出Excel
	 * @author zhurk --- 2015-11-19下午1:25:19                
	*/
	public void updateHomeExcel(){
		
		//从session中获取10位填报单位网站标识码
		String siteCode=getCurrentUserInfo().getChildSiteCode();
		if(StringUtils.isEmpty(siteCode)){
			siteCode=getCurrentUserInfo().getSiteCode();
		}
		//获取时间选择的天数
		String days=request.getParameter("days");
		//获取关键字查询的关键字
		String key=request.getParameter("key");
		
		try {
			UpdateHomeDetailRequest updateHomeDetailRequest=new UpdateHomeDetailRequest();
			if(StringUtils.isNotEmpty(days)){
				int delay=Integer.parseInt(days);
				//获取days天扫描的日期
				String startDate = DateUtils.getNextDay(new Date(), -delay);
				updateHomeDetailRequest.setBeginScanDate(startDate);
				//获取昨天的日期
				String endDate = DateUtils.getNextDay(new Date(), -1);
				updateHomeDetailRequest.setEndScanDate(endDate);
			}else{//默认查询两周内的更新数据
				String startDate = DateUtils.getNextDay(new Date(), -7);
				updateHomeDetailRequest.setBeginScanDate(startDate);
				//获取昨天的日期
				String endDate = DateUtils.getNextDay(new Date(), -1);
				updateHomeDetailRequest.setEndScanDate(endDate);
			}
			
			if(StringUtils.isNotEmpty(key)){
//				key= new String(key.getBytes("iso-8859-1"), "UTF-8");
				updateHomeDetailRequest.setTitle(key);
			}
			ArrayList<Object[]> list = new ArrayList<Object[]>();
			Object[] obj1 = new Object[]{"序号","更新时间","标题","网页URL"};
			list.add(obj1);
			String fileName = "内容更新-首页更新明细("+DateUtils.formatStandardDate(new Date())+").xls";
			String title = "首页更新明细"; 

			updateHomeDetailRequest.setSiteCode(siteCode);
			updateHomeDetailRequest.setPageSize(Integer.MAX_VALUE);
			List<QueryOrder> UHDqueryOrderList = new ArrayList<QueryOrder>();
			UHDqueryOrderList.add(new QueryOrder("scan_date", QueryOrderType.DESC));
			updateHomeDetailRequest.setQueryOrderList(UHDqueryOrderList);
		
			List<UpdateHomeDetail> queryList = updateHomeDetailServiceImpl.queryList(updateHomeDetailRequest);
			if(queryList.size()>0){
				for (int i = 0; i < queryList.size(); i++){
					UpdateHomeDetail updateHomeDetail = queryList.get(i);
					Object[] obj = new Object[4];
					obj[0]=i+1;
					String scanTime = updateHomeDetail.getUpdateTime();
					obj[1] = scanTime;
					String titleName= updateHomeDetail.getTitle();
					obj[2]=titleName;
					String Url = updateHomeDetail.getUrl();
					obj[3] = Url;
					list.add(obj);
				}
			}
			ExportExcel.contentUpdateDetailExcel(fileName, title, list);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("首页更新明细 excel 导出"+"  siteCode :"+siteCode);
		}
		
	}
}
