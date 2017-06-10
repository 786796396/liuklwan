package com.ucap.cloud_web.action;

import java.util.ArrayList;
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

import com.publics.util.page.PageVo;
import com.publics.util.page.QueryOrder;
import com.publics.util.page.QueryOrderType;
import com.publics.util.utils.DateUtils;
import com.publics.util.utils.StringUtils;
import com.ucap.cloud_web.UrlAdapterVar;
import com.ucap.cloud_web.bizService.DatabaseTreeBizService;
import com.ucap.cloud_web.common.ComparatorHashMap;
import com.ucap.cloud_web.constant.CacheType;
import com.ucap.cloud_web.constant.DatabaseInfoType;
import com.ucap.cloud_web.dto.ChannelPointRequest;
import com.ucap.cloud_web.dto.DatabaseInfoRequest;
import com.ucap.cloud_web.dto.DatabaseTreeInfoRequest;
import com.ucap.cloud_web.dto.UpdateChannelDetailRequest;
import com.ucap.cloud_web.dto.UpdateChannelInfoRequest;
import com.ucap.cloud_web.entity.DatabaseInfo;
import com.ucap.cloud_web.entity.DatabaseTreeInfo;
import com.ucap.cloud_web.entity.DicChannel;
import com.ucap.cloud_web.entity.UpdateChannelDetail;
import com.ucap.cloud_web.service.IChannelPointService;
import com.ucap.cloud_web.service.IDatabaseInfoService;
import com.ucap.cloud_web.service.IDicChannelService;
import com.ucap.cloud_web.service.IUpdateChannelDetailService;
import com.ucap.cloud_web.service.IUpdateChannelInfoService;
import com.ucap.cloud_web.util.CommonUtils;
import com.ucap.cloud_web.util.ExportExcel;
import com.ucap.cloud_web.util.MonitoringCacheUtils;

/**
 * <p>Description: </p> 栏目更新页面
 * <p>@Package：com.ucap.cloud_web.action </p>
 * <p>Title: UpChannelAction </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：zhurk </p>
 * <p>@date：2015-11-12下午15:13:26 </p>
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class UpdateChannelDetailAction extends BaseAction {
	@Autowired
	private UrlAdapterVar urlAdapterVar;
	@Autowired
	private IUpdateChannelDetailService updateChannelDetailServiceImpl;
	@Autowired
	private  IUpdateChannelInfoService updateChannelInfoServiceImpl;
	@Autowired
	private IDicChannelService dicChannelServiceImpl;
	@Autowired
	private IChannelPointService channelPointServiceImpl;
	@Autowired
	private DatabaseTreeBizService databaseTreeBizServiceImpl;
	@Autowired
	private IDatabaseInfoService databaseInfoServiceImpl;

	private static Log logger = LogFactory.getLog(UpdateChannelDetailAction.class);
	private String menuType;
	
	
	/************************************* 栏目检测-栏目更新情况 开始********************************************/

	/**
	 * 
	 * @描述:栏目更新情况数据
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2017年1月10日下午3:13:59
	 */
	@SuppressWarnings("unchecked")
	public void ProgramUpdate() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
		String startDate = request.getParameter("startDate"); // 开始时间
		String endDate = request.getParameter("endDate"); // 结束时间
		String siteType = request.getParameter("siteType"); // 网站类别
		try {
			// 获取当前组织机构编码
			String siteCode = getCurrentSiteCode();
			// 根据组织机构编码和组织机构的级别，获取对应的网站标识码集合
			String cacheInfo = CacheType.MONITORING_DATABASEINFO.getName();
			String key = cacheInfo + siteCode + siteType; // 缓存名
			List<DatabaseInfo> siteList = (List<DatabaseInfo>) MonitoringCacheUtils.get(key); // 查询缓存中是否存在
			if (siteList == null) {
				List<QueryOrder> querySiteList = new ArrayList<QueryOrder>();
				QueryOrder siteQueryOrder = new QueryOrder("isorganizational", QueryOrderType.DESC);
				querySiteList.add(siteQueryOrder);
				DatabaseTreeInfoRequest request = new DatabaseTreeInfoRequest();
				request.setOrgSiteCode(siteCode);
				if (StringUtils.isNotEmpty(siteType) && !siteType.equals("0")) {
					request.setLayerType(Integer.valueOf(siteType));
				}
				request.setIsLink(1);
				request.setIsexp(DatabaseInfoType.NORMAL.getCode());
				request.setQueryOrderList(querySiteList);
				List<DatabaseTreeInfo> treeList = databaseTreeBizServiceImpl.getDatabaseTreeInfoList(request);
				DatabaseInfoRequest dbRequest = new DatabaseInfoRequest();
				if(CollectionUtils.isNotEmpty(treeList)  && treeList.size() != 0){
					dbRequest.setDatabaseTreeList(treeList);
					dbRequest.setPageSize(Integer.MAX_VALUE);
					siteList =  databaseInfoServiceImpl.query(dbRequest).getData();
					MonitoringCacheUtils.put(key, siteList); // 将数据存到缓存中
				}else{
					siteList = null;
					MonitoringCacheUtils.put(key, siteList); // 将数据存到缓存中
				}
			
			}

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("siteCode", siteCode);
			if(CollectionUtils.isNotEmpty(siteList) && siteList.size() != 0){
				paramMap.put("ids", siteList);
			}
			paramMap.put("beginScanDate", startDate);
			paramMap.put("endScanDate", endDate);
			paramMap.put("siteType", siteType);
			List<UpdateChannelInfoRequest> upChList = columnNum(paramMap);

			ChannelPointRequest channelRequest = new ChannelPointRequest();
			Integer channelCount;
			Integer chaNum = 0; // 栏目总数
			Integer dcNum = 0; // 动态要闻类总数
			Integer noNum = 0; // 通知/政策总数
			Integer plNum = 0; // 人事规划类总数
			Integer cNum = 0; // 总个数
			if (siteList != null && siteList.size() > 0) {
				for (DatabaseInfo da : siteList) {
					Map<String, Object> item = new HashMap<String, Object>();
					item.put("siteCode", da.getSiteCode());
					item.put("siteName", da.getName());
					item.put("director", da.getDirector());
					if (StringUtils.isNotEmpty(da.getJumpUrl())) {
						item.put("url", da.getJumpUrl());// 跳转url地址
					} else {
						item.put("url", da.getUrl());// 首页url地址
					}
					channelRequest.setSiteCode(da.getSiteCode());
					channelRequest.setStatus(1); // 查询该站点 [监测中] 的栏目的数量
					channelCount = channelPointServiceImpl.queryCount(channelRequest);
					item.put("channelCount", channelCount);
					chaNum += channelCount;

					Integer dynamicNum = 0;
					Integer noticeNum = 0;
					Integer planningNum = 0;
					Integer countNum = 0;
					if (upChList != null && upChList.size() > 0) {
						for (UpdateChannelInfoRequest up : upChList) {
							String siCode = up.getSiteCode();
							Integer dicId = up.getDicChannelId();
							if (StringUtils.isNotEmpty(siCode)) {
								if (da.getSiteCode().equals(siCode)) {
									if (dicId == 5) {
										Integer dyCount = up.getUpdateNum();
										dynamicNum += dyCount; 
										dcNum += dyCount;
									}
									if (dicId == 6 || dicId == 7) {
										Integer noCount = 0;
										noCount += up.getUpdateNum();
										noticeNum += noCount;
										noNum += noCount;
									}
									if (dicId == 8 || dicId == 9 || dicId == 10 || dicId == 11 || dicId == 12
											|| dicId == 13 || dicId == 14 || dicId == 15 || dicId == 16) {
										Integer plCount = 0;
										plCount += up.getUpdateNum();
										planningNum += plCount;
										plNum += plCount;
									}
									Integer cnCount = 0;
									cnCount += up.getUpdateNum();
									countNum += cnCount;
									cNum += cnCount;
								}
							}
						}
					}
					item.put("dynamicNum", dynamicNum); // 动态要闻类
					item.put("noticeNum", noticeNum); // 通知/政策
					item.put("planningNum", planningNum); // 人事规划类
					item.put("size", countNum); // 个数
					items.add(item);
				}
			}
			ComparatorHashMap comparatorHashMapTotal = new ComparatorHashMap();
			Collections.sort(items, comparatorHashMapTotal); // 按所有栏目更新条数 进行排序
			resultMap.put("success", "true");
			resultMap.put("chaNum", chaNum);
			resultMap.put("dcNum", dcNum);
			resultMap.put("noNum", noNum);
			resultMap.put("plNum", plNum);
			resultMap.put("cNum", cNum);
			resultMap.put("body", items);
			if(CollectionUtils.isNotEmpty(siteList) && siteList.size() != 0){
				resultMap.put("size", siteList.size()); // 总条数
			}
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", "false");
			resultMap.put("errorMsg", "查询栏目检测-栏目更新情况数据异常！");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		}
	}

	/**
	 * 
	 * @描述:栏目更新情况excel导出
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2017年1月10日下午6:09:21
	 */
	@SuppressWarnings("unchecked")
	public void ProgramUpdateExcel() {
		String startDate = request.getParameter("startDate"); // 开始时间
		String endDate = request.getParameter("endDate"); // 结束时间
		String siteType = request.getParameter("siteType"); // 网站类别
		ArrayList<Object[]> list = new ArrayList<Object[]>();
		List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
		try {
			// 获取当前组织机构编码
			String siteCode = getCurrentSiteCode();
			// 根据组织机构编码和组织机构的级别，获取对应的网站标识码集合
			String cacheInfoExcel = CacheType.MONITORING_DATABASEINFO.getName();
			String key = cacheInfoExcel + siteCode + siteType; // 缓存名
			List<DatabaseInfo> siteList = (List<DatabaseInfo>) MonitoringCacheUtils.get(key); // 查询缓存中是否存在
			if (siteList == null) {
				DatabaseTreeInfoRequest request = new DatabaseTreeInfoRequest();
				request.setOrgSiteCode(siteCode);
				if (StringUtils.isNotEmpty(siteType) && !siteType.equals("0")) {
					request.setLayerType(Integer.valueOf(siteType));
				}
				request.setIsLink(1);
				request.setIsexp(DatabaseInfoType.NORMAL.getCode());
				List<DatabaseTreeInfo> treeList = databaseTreeBizServiceImpl.getDatabaseTreeInfoList(request);
				DatabaseInfoRequest dbRequest = new DatabaseInfoRequest();
				if(CollectionUtils.isNotEmpty(treeList) && treeList.size() != 0){
					dbRequest.setDatabaseTreeList(treeList);
					dbRequest.setPageSize(Integer.MAX_VALUE);
					siteList =  databaseInfoServiceImpl.query(dbRequest).getData();
				}else{
					siteList = null;
				}
			}
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("siteCode", siteCode);
			if(CollectionUtils.isNotEmpty(siteList) && siteList.size() != 0){
				paramMap.put("ids", siteList);
			}
			paramMap.put("beginScanDate", startDate);
			paramMap.put("endScanDate", endDate);
			paramMap.put("siteType", siteType);
			List<UpdateChannelInfoRequest> upChList = columnNum(paramMap);

			Object[] obj1 = new Object[] { "序号", "网站标识码", "网站名称", "网站URL", "检测栏目", "主办单位", "动态要闻类", "人事规划类",
					"通知公告/政策文件类", "所有栏目更新条数" };
			list.add(obj1);
			String fileName = "栏目检测-栏目更新情况(" + DateUtils.formatStandardDate(new Date()) + ").xls";
			String title = "栏目更新情况";

			ChannelPointRequest channelRequest = new ChannelPointRequest();
			int i = 0;
			Integer channelCount;
			if (siteList != null && siteList.size() > 0) {
				for (DatabaseInfo daInfo : siteList) {
					Map<String, Object> item = new HashMap<String, Object>();
					item.put("siteCode", daInfo.getSiteCode());
					item.put("siteName", daInfo.getName());
					
					if (StringUtils.isNotEmpty(daInfo.getJumpUrl())) {
						item.put("url", daInfo.getJumpUrl());// 跳转url地址
					} else {
						item.put("url", daInfo.getUrl());// 首页url地址
					}
					channelRequest.setSiteCode(daInfo.getSiteCode());
					channelRequest.setStatus(1); // 查询该站点 [监测中] 的栏目的数量
					channelCount = channelPointServiceImpl.queryCount(channelRequest);
					item.put("channelCount", channelCount);
					item.put("director", daInfo.getDirector());
					Integer dynamicNum = 0;
					Integer noticeNum = 0;
					Integer planningNum = 0;
					Integer countNum = 0;
					if (upChList != null && upChList.size() > 0) {
						for (UpdateChannelInfoRequest up : upChList) {
							String siCode = up.getSiteCode();
							Integer dicId = up.getDicChannelId();
							if (StringUtils.isNotEmpty(siCode)) {
								if (daInfo.getSiteCode().equals(siCode)) {
									if (dicId == 5) {
										Integer dyCount = up.getUpdateNum();
										dynamicNum += dyCount; 
									}
									if (dicId == 6 || dicId == 7) {
										Integer noCount = 0;
										noCount += up.getUpdateNum();
										noticeNum += noCount;
									}
									if (dicId == 8 || dicId == 9 || dicId == 10 || dicId == 11 || dicId == 12
											|| dicId == 13 || dicId == 14 || dicId == 15 || dicId == 16) {
										Integer plCount = 0;
										plCount += up.getUpdateNum();
										planningNum += plCount;
									}
									Integer cnCount = 0;
									cnCount += up.getUpdateNum();
									countNum += cnCount;
								}
							}
							
						}
						item.put("dynamicNum", dynamicNum); // 动态要闻类
						item.put("noticeNum", noticeNum); // 通知/政策
						item.put("planningNum", planningNum); // 人事规划类
						item.put("size", countNum); // 个数
						items.add(item);
					}
				}
				ComparatorHashMap comparatorHashMapTotal = new ComparatorHashMap();
				Collections.sort(items, comparatorHashMapTotal); // 按所有栏目更新条数 进行排序
				if(items != null && items.size()>0){
					for (Map<String, Object> map : items) {
						i++;
						Object[] obj = new Object[10];
						obj[0] = i;
						obj[1] = (String) map.get("siteCode");
						obj[2] = (String) map.get("siteName");
						obj[3] = (String) map.get("url");
						obj[4] = (Integer) map.get("channelCount");
						obj[5] = (String) map.get("director");
						obj[6] = (Integer) map.get("dynamicNum");
						obj[7] = (Integer) map.get("noticeNum");
						obj[8] = (Integer) map.get("planningNum");
						obj[9] = (Integer) map.get("size");
						list.add(obj);
					}
				}
			}
			ExportExcel.webSiteConnectedTableExcel(fileName, title, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @描述:统计站点下全部栏目数据
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2017年1月10日下午3:58:55
	 */
	@SuppressWarnings("unchecked")
	public List<UpdateChannelInfoRequest> columnNum(Map<String, Object> paramMap) {
		List<UpdateChannelInfoRequest> upChList = new ArrayList<UpdateChannelInfoRequest>();
		String siteCode = (String) paramMap.get("siteCode");
		String startDate = (String) paramMap.get("beginScanDate");
		String endDate = (String) paramMap.get("endScanDate");
		String siteType = (String) paramMap.get("siteType");
		String type = "5,6,7,8,9,10,11,12,13,14,15,16";
		String[] types = type.split(",");
		int array[] = new int[types.length];
		for (int i = 0; i < types.length; i++) {
			array[i] += Integer.parseInt(types[i]);
		}
		paramMap.put("typeList", array);
		try {
			String columnNum = CacheType.MONITORING_COLUMNNUM.getName();
			String key = columnNum + siteCode + siteType + startDate + endDate; // 缓存名
			upChList = (List<UpdateChannelInfoRequest>) MonitoringCacheUtils.get(key); // 查询缓存中是否存在
			if (upChList == null) {
				paramMap.remove("siteCode");
				upChList = updateChannelInfoServiceImpl.queryListSum(paramMap);
				MonitoringCacheUtils.put(key, upChList); // 将数据存到缓存中
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return upChList;
	}
	/************************************* 栏目检测-栏目更新情况 结束********************************************/

	
	/**
	 * @Description: 栏目更新
	 * @author zhurk --- 2015-11-12下午15:13:46     
	 * @return
	 */
	public String index(){
		//siteCode处理由组织单位跳转到该页面时，session的修改
		String siteCode = request.getParameter("siteCode");
		String types = request.getParameter("types");
		if (StringUtils.isEmpty(types)) {
			types = "tp";
		}
		if(StringUtils.isNotEmpty(siteCode)){
			setCurrentShiroUser(siteCode);
		}
		request.setAttribute("types", types);
		return "success";
	}
	/**
	 * @Description: 栏目更新明细列表--分页查询
	 * @author zhurk --- 2015-11-12下午15:17:26
	 */
	public void updateChannelPage(){
		//从session中获取10位填报单位网站标识码
		String siteCode=getCurrentUserInfo().getChildSiteCode();
		if(StringUtils.isEmpty(siteCode)){
			siteCode=getCurrentUserInfo().getSiteCode();
		}
		String key=request.getParameter("key");
		try {
//		key= new String( key.getBytes("iso-8859-1"), "UTF-8");
		//获取昨天的日期
		String yesterday = DateUtils.getYesterdayStr();
		String days=request.getParameter("days");
		String type=request.getParameter("type");
		String[] types = null;
		UpdateChannelDetailRequest updateChannelDetailRequest=new UpdateChannelDetailRequest();
		updateChannelDetailRequest.setSiteCode(siteCode);
		
		if(StringUtils.isNotEmpty(key)){
			updateChannelDetailRequest.setTitle(key);
		}
		if(StringUtils.isNotEmpty(type)){
			types=type.split(",");
		}else{
			type="5,6,7,8,9,10,11,12,13,14,15,16";
			types=type.split(",");
		}
		updateChannelDetailRequest.setTypes(types);
		if(StringUtils.isNotEmpty(days)){
			int delay=Integer.parseInt(days);
			//获取days天前的日期
			String startDate = DateUtils.getNextDay(new Date(), -delay);
			updateChannelDetailRequest.setBeginScanDate(startDate);
			updateChannelDetailRequest.setEndScanDate(yesterday);
		}
		updateChannelDetailRequest.setPageSize(Integer.MAX_VALUE);
		int queryCount=updateChannelDetailServiceImpl.queryCount(updateChannelDetailRequest);
		
		String pos = request.getParameter("pos");
		if(StringUtils.isNotEmpty(pos)){
			updateChannelDetailRequest.setPageNo(Integer.parseInt(pos));
		}
		String size = request.getParameter("size");
		if(StringUtils.isNotEmpty(size)){
			updateChannelDetailRequest.setPageSize(Integer.parseInt(size));
		}
		ArrayList<Object> list = new ArrayList<Object>();
		HashMap<String, Object> map_list = new HashMap<String, Object>();
		
		//获取排序条件
		String sSortDir_0 = request.getParameter("sSortDir_0");//控制升序和降序
		String soraFiel = request.getParameter("mDataProp_"+request.getParameter("iSortCol_0"));//获取要排序的字段
		//排序字段
		if(soraFiel!=null){
			List<QueryOrder> querySiteList=new ArrayList<QueryOrder>();
			if(!"".equals(sSortDir_0) && "asc".equals(sSortDir_0)){
				QueryOrder siteQueryOrder=new QueryOrder("scan_date",QueryOrderType.ASC);
				querySiteList.add(siteQueryOrder);
				updateChannelDetailRequest.setQueryOrderList(querySiteList);
			}else{
				QueryOrder siteQueryOrder=new QueryOrder("scan_date",QueryOrderType.DESC);
				querySiteList.add(siteQueryOrder);
				updateChannelDetailRequest.setQueryOrderList(querySiteList);
			}
		}
	
		PageVo<UpdateChannelDetail> query=updateChannelDetailServiceImpl.query(updateChannelDetailRequest);
		List<UpdateChannelDetail> data=query.getData();
		if(data!=null && data.size()>0){
			for (int i = 0; i < data.size(); i++) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				UpdateChannelDetail updateChannelDetail=data.get(i);
				int beginNum=(Integer.valueOf(pos)-1) * Integer.valueOf(size);
			    map.put("dataNumber", beginNum+i+1);
			    String lastTime = updateChannelDetail.getLastTime();
			    map.put("lastTime", lastTime);
			    String channelName = updateChannelDetail.getChannelName();
			    map.put("channelName", channelName);
			    String title = updateChannelDetail.getTitle();
			    map.put("title",title);
			    int dicChannelId = updateChannelDetail.getDicChannelId();
				DicChannel dicChannel = dicChannelServiceImpl.get(dicChannelId);
				map.put("dicChannelName", dicChannel.getChannelName());
			    String url = updateChannelDetail.getLinkUrl();
				//String url = updateChannelDetail.getUrl();
			    map.put("url", url);
			    String imgUrl = updateChannelDetail.getImgUrl();
			    imgUrl=urlAdapterVar.getImgUrl() + imgUrl;
			    map.put("imgUrl",imgUrl);
				list.add(map);
			}
			
		}
		
		map_list.put("total",queryCount);
		map_list.put("exists", data.size());
		map_list.put("body", list);
		map_list.put("totalRecords", query.getRecordSize());
		map_list.put("iTotalDisplayRecords", query.getRecordSize());
		map_list.put("hasMoreItems",true);
		writerPrint(JSONObject.fromObject(map_list).toString());
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("siteCode"+siteCode+"key:"+key);
		}
	}
	
	/**
	 * @Description: 填报单位--栏目更新页面--栏目更新总体情况柱状图
	 * @author cuichx --- 2015-11-13下午3:13:40
	 */
	public void updateChannelBar(){
		//Map<String,Object> resultMap=new HashMap<String, Object>();
		List<Object> list=new ArrayList<Object>();
		try {
			//从session中获取10位填报单位网站标识码
			String siteCode=getCurrentUserInfo().getChildSiteCode();
			if(StringUtils.isEmpty(siteCode)){
				siteCode=getCurrentUserInfo().getSiteCode();
			}
			String scanDate=DateUtils.getYesterdayStr();
			String days=request.getParameter("days");
			String type=request.getParameter("type");
			String[] types = null;
			if(StringUtils.isNotEmpty(type)){
				types=type.split(",");
			}else{
				type="5,6,7,8,9,10,11,12,13,14,15,16";
				types=type.split(",");
			}
			for(int i=0;i< types.length;i++){
				String channelSonId=types[i];
				if(StringUtils.isNotEmpty(channelSonId)){
					int dicId=Integer.valueOf(channelSonId);
					
					DicChannel dicChannel=dicChannelServiceImpl.get(dicId);
					String channelName=dicChannel.getChannelName();//获取栏目名称
					//通过网站表示码和扫描日期获取栏目更新趋势表
					Map<String, Object> paramMap=new HashMap<String, Object>();
					paramMap.put("siteCode", siteCode);
					paramMap.put("dicChannelId", dicId);
					if(StringUtils.isNotEmpty(days)){
						String startDate = DateUtils.getNextDay(new Date(), -(Integer.valueOf(days)));
						paramMap.put("beginScanDate", startDate);
						paramMap.put("endScanDate", scanDate);
					}
					List<UpdateChannelInfoRequest> twoWeekList=new ArrayList<UpdateChannelInfoRequest>();
					twoWeekList=updateChannelInfoServiceImpl.queryListSum(paramMap);
					if(twoWeekList!=null && twoWeekList.size()>0){
						UpdateChannelInfoRequest updateChannelInfo=twoWeekList.get(0);
						int updateNum=updateChannelInfo.getUpdateNum();
						Map<String, Object> map=new HashMap<String, Object>();
						map.put("updateNum", updateNum);
						map.put("channelName", channelName);
						list.add(map);
					}
				}
			}
			logger.info(list);
			writerPrint(JSONArray.fromObject(list).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** @Description: 栏目更新明细--Excel导出  --根据页面传递的条件导出
	 * @author zhurk --- 2015-11-20下午3:09:24                
	*/
	public void updateChannelExcel(){
		try {
			UpdateChannelDetailRequest updateChannelDetailRequest=new UpdateChannelDetailRequest();
			ArrayList<Object[]> list = new ArrayList<Object[]>();
			Object[] obj1 = new Object[]{"序号","更新时间","信息标题","栏目名称","分类分类","栏目URL","快照"};
			list.add(obj1);
			String fileName = "内容更新-栏目更新明细("+DateUtils.formatStandardDate(new Date())+").xls";
			
			
			//从session中获取10位填报单位网站标识码
			String siteCode=getCurrentUserInfo().getChildSiteCode();
			if(StringUtils.isEmpty(siteCode)){
				siteCode=getCurrentUserInfo().getSiteCode();
			}
			updateChannelDetailRequest.setSiteCode(siteCode);
			String days=request.getParameter("days");//单选按钮对应的时间段
			String type=request.getParameter("type");//checkbox对应的类型
			String key=request.getParameter("key");//关键字
			String[] types=null;
	
			 
			String firstTittle="";
			String lastTile="";
			firstTittle=getFirstTitle(type);
			lastTile=getLastTile(days);
			String title = "应【"+firstTittle+"】内更新的栏目，近【"+lastTile+"】的信息更新明细";
			//获取days天前的日期
			String startDate = DateUtils.getNextDay(new Date(), -Integer.valueOf(days));
			updateChannelDetailRequest.setBeginScanDate(startDate);
			updateChannelDetailRequest.setEndScanDate(DateUtils.getYesterdayStr());
			updateChannelDetailRequest.setSiteCode(siteCode);
			if(StringUtils.isNotEmpty(type)){
				types=type.split(",");
			}else{
				type="5,6,7,8,9,10,11,12,13,14,15,16";
				types=type.split(",");
			}
			updateChannelDetailRequest.setTypes(types);
			updateChannelDetailRequest.setTitle(key);
			updateChannelDetailRequest.setPageSize(Integer.MAX_VALUE);
			
			List<QueryOrder> UHDqueryOrderList = new ArrayList<QueryOrder>();
			UHDqueryOrderList.add(new QueryOrder("scan_date", QueryOrderType.DESC));
			updateChannelDetailRequest.setQueryOrderList(UHDqueryOrderList);
		
			List<UpdateChannelDetail> queryList = updateChannelDetailServiceImpl.queryList(updateChannelDetailRequest);
			if(queryList.size()>0){
				for (int i = 0; i < queryList.size(); i++){
					UpdateChannelDetail updateChannelDetail = queryList.get(i);
					//信息更新时间
					String lastTime = updateChannelDetail.getLastTime()!=null?updateChannelDetail.getLastTime():"";
					//信息标题
					String titleName= updateChannelDetail.getTitle()!=null?updateChannelDetail.getTitle():"";
					//栏目名称
					String channelName= updateChannelDetail.getChannelName()!=null?updateChannelDetail.getChannelName():"";
					//栏目分类
					int dicChannelId = updateChannelDetail.getDicChannelId();
					DicChannel dicChannel = dicChannelServiceImpl.get(dicChannelId);
					//栏目URL
					String Url = updateChannelDetail.getLinkUrl()!=null?updateChannelDetail.getLinkUrl():"";
					//String Url = updateChannelDetail.getUrl()!=null?updateChannelDetail.getUrl():"";
					//快照
					String imgUrl=updateChannelDetail.getImgUrl()!=null?updateChannelDetail.getImgUrl():"";
					
					Object[] obj = new Object[7];
					obj[0]=i+1;
					obj[1] = lastTime;
					obj[2]=titleName;
					obj[3]=channelName;
					obj[4]=dicChannel.getChannelName();
					obj[5] = CommonUtils.setHttpUrl(Url); //  判断是否有http头
					obj[6]=urlAdapterVar.getImgUrl() +imgUrl;
					list.add(obj);
				}
			}
			ExportExcel.contentUpdateFourExcel(fileName, title, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Description: 根据页面上送的类型，获取导出excel的首标题
	 * @author cuichx --- 2015-12-10下午8:34:25     
	 * @param type
	 * @return
	 */
	private String getFirstTitle(String type) {
		String firstTitle="";
		//String title = "应【两周、六个月、一年】内更新的栏目，近【1周/2周/3个月/6个月/1年】的信息更新明细"; 
		if(type.equals("5,")){
			firstTitle="两周";
		}else if(type.equals("6,7,")){
			firstTitle="六个月";
		}else if(type.equals("8,9,10,11,12,13,14,15,16,")){
			firstTitle="一年";
		}else if(type.equals("5,6,7,")){
			firstTitle="两周、六个月";
		}else if(type.equals("5,8,9,10,11,12,13,14,15,16,")){
			firstTitle="两周、一年";
		}else if(type.equals("6,7,8,9,10,11,12,13,14,15,16,")){
			firstTitle="六个月、一年";
		}else{
			firstTitle="两周、六个月、一年";
		}
		return firstTitle;
	}
	
	/**
	 * @Description: 根据页面上送的时间，获取导出excel的第二标题
	 * @author cuichx --- 2015-12-10下午8:34:25     
	 * @param type
	 * @return
	 */
	private String getLastTile(String days) {
		String lastTitle="";
		//String title = "应【两周、六个月、一年】内更新的栏目，近【1周/2周/3个月/6个月/1年】的信息更新明细"; 
		if(days.equals("7")){
			lastTitle="1周";
		}else if(days.equals("14")){
			lastTitle="2周";
		}else if(days.equals("90")){
			lastTitle="3个月";
		}else if(days.equals("180")){
			lastTitle="6个月";
		}else{
			lastTitle="1年";
		}
		return lastTitle;
	}
	public String getMenuType() {
		return menuType;
	}
	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}
	
}
