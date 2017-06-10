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
import org.springframework.util.CollectionUtils;

import com.publics.util.page.QueryOrder;
import com.publics.util.page.QueryOrderType;
import com.publics.util.utils.DateUtils;
import com.publics.util.utils.StringUtils;
import com.ucap.cloud_web.dto.DatabaseInfoRequest;
import com.ucap.cloud_web.dto.TaskDailyRequest;
import com.ucap.cloud_web.dto.UpdateChannelInfoRequest;
import com.ucap.cloud_web.dto.UpdateContentCountRequest;
import com.ucap.cloud_web.entity.DatabaseInfo;
import com.ucap.cloud_web.entity.DicChannel;
import com.ucap.cloud_web.entity.TaskDaily;
import com.ucap.cloud_web.entity.UpdateChannelInfo;
import com.ucap.cloud_web.entity.UpdateContentCount;
import com.ucap.cloud_web.service.IDatabaseInfoService;
import com.ucap.cloud_web.service.IDicChannelService;
import com.ucap.cloud_web.service.ITaskDailyService;
import com.ucap.cloud_web.service.IUpdateChannelInfoService;
import com.ucap.cloud_web.service.IUpdateContentCountService;

/**
 * <p>Description: 内容更新与分析-内容分析</p>
 * <p>@Package：com.ucap.cloud_web.action </p>
 * <p>Title: UpdateContentAnalyzeAction </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：Nora </p>
 * <p>@date：2016-3-8下午3:20:50 </p>
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class UpdateContentAnalyzeAction extends BaseAction {
	@Autowired
	private IUpdateContentCountService updateContentCountServiceImpl;

	@Autowired
	private IUpdateChannelInfoService updateChannelInfoServiceImpl;

	@Autowired
	private IDicChannelService dicChannelServiceImpl;

	@Autowired
	private ITaskDailyService taskDailyServiceImpl;
	@Autowired
	private IDatabaseInfoService databaseInfoServiceImpl;
	HashMap<String, Object> resultMap = new HashMap<String, Object>();
	// 昨天时间：yyyy-mm-dd
	// private String yesterDayStr = DateUtils.getYesterdayStr();
	private String menuType;
	private Logger logger = Logger.getLogger(this.getClass());

	/**
	 * @Description: 组织单位：内容更新与分析-内容分析页面
	 * @author Nora --- 2015年11月23日上午10:45:49
	 * @return
	 */
	public String indexOrg() {
		String currentSiteCode = getCurrentSiteCode();
		DatabaseInfoRequest databaseInfoRequest = new DatabaseInfoRequest();
		databaseInfoRequest.setSiteCodeLike(currentSiteCode);
		List<DatabaseInfo> queryList = databaseInfoServiceImpl.queryList(databaseInfoRequest);
		if(queryList.size()>0){
			DatabaseInfo databaseInfo = queryList.get(0);
			//当前登录用户的级别
			resultMap.put("level", databaseInfo.getLevel());
			if(databaseInfo.getLevel().equals("1")){
				resultMap.put("type", 1);
			}else if(databaseInfo.getLevel().equals("2")){
				resultMap.put("type", 2);
			}else{
				resultMap.put("type", 3);
			}
		}
		return "success";
	}
	
	/**
	 * @Description: 填报单位：内容更新与分析-内容分析页面
	 * @author Nora --- 2015年11月23日上午10:45:49
	 * @return
	 */
	public String index() {
		//siteCode处理由组织单位跳转到该页面时，session的修改
		String siteCode = request.getParameter("siteCode");
		
		if(StringUtils.isNotEmpty(siteCode)){
			setCurrentShiroUser(siteCode);
		}
		return "success";
	}

	/**
	 * @Description: 饼状图 内容分析  @author Nora --- 2015-11-23下午18:01:53     
	 *                    @return      
	 */
	public void loadContentCount() {

		Map<String, Object> map = new HashMap<String, Object>();

		List<Map<String, Object>> listChannel = new ArrayList<Map<String, Object>>();
		try {

			String scanDate = StringUtils.isEmpty(request
					.getParameter("scanDate")) ? DateUtils.getYesterdayStr()
					: request.getParameter("scanDate");

			/*ShiroUser shiroUser = getCurrentUserInfo();
			String siteCode = shiroUser.getSiteCode();*/
			
			//从session中获取10位填报单位网站标识码
			String siteCode=getCurrentUserInfo().getChildSiteCode();
			if(StringUtils.isEmpty(siteCode)){
				siteCode=getCurrentUserInfo().getSiteCode();
			}

			// 总数
			int sumNum = getSumNumUpdateChannel(siteCode, scanDate);

			map.put("sumNum", sumNum);
			map.put("sumProportion", 100);
			map.put("scanDate",
					DateUtils.formatDate(DateUtils.parseStandardDate(scanDate)));
			map.put("isHasPrevious",
					isHasPrevious(siteCode,
							DateUtils.parseStandardDate(scanDate)));

			map.put("isHasNext",
					isHasNext(DateUtils.parseStandardDate(scanDate)));

			map.put("previousDate", DateUtils.getNextDay(scanDate, "-1"));

			map.put("nextDate", DateUtils.getNextDay(scanDate, "1"));

			// DateUtils.f

			UpdateChannelInfoRequest updateChannelInfoRequest = new UpdateChannelInfoRequest();
			updateChannelInfoRequest.setScanDate(scanDate);
			updateChannelInfoRequest.setSiteCode(siteCode);
			List<UpdateChannelInfo> listUpdateChannelInfo = updateChannelInfoServiceImpl
					.queryList(updateChannelInfoRequest);

			if (CollectionUtils.isEmpty(listUpdateChannelInfo)) {
				map.put("listChannel", listChannel);
				writerPrint(JSONObject.fromObject(map).toString());
				return;
			}

			for (UpdateChannelInfo updateChannelInfo : listUpdateChannelInfo) {
				DicChannel dicChannel = dicChannelServiceImpl
						.get(updateChannelInfo.getDicChannelId());

				if (null == dicChannel) {
					continue;
				}

				if (updateChannelInfo.getUpdateNum() == 0) {
					continue;
				}

				Map<String, Object> channelMap = new HashMap<String, Object>();
				channelMap.put("channelName", dicChannel.getChannelName());
				channelMap.put("updateNum", updateChannelInfo.getUpdateNum());
				channelMap
						.put("proportion",StringUtils.getPrettyNumber(getProportion(updateChannelInfo.getUpdateNum(),
								sumNum))
								);

				listChannel.add(channelMap);
			}

			map.put("listChannel", listChannel);
			writerPrint(JSONObject.fromObject(map).toString());

		} catch (Exception e) {
			e.printStackTrace();
			logger.info("获取内容分析-信息分类统计数据失败");
			map.put("errmsg", "获取内容分析-信息分类统计数据失败！");
			writerPrint(JSONObject.fromObject(map).toString());
		}
	}
	/**
	 * @Description: 组织单位，内容分析，折线图
	 * @author sunjiang --- 2015-12-8下午4:45:01
	 */
//	public void contentOrgLine() {
//		Map<String, Object> map = new HashMap<String, Object>();
//		try {
//			Map<String, Object> paramMap = new HashMap<String, Object>();
//			int type = (StringUtils.isNotEmpty(request.getParameter("type")) ? Integer.parseInt(request.getParameter("type")):0);
//			String currentSiteCode = getCurrentSiteCode();
//			paramMap.put("siteCode", currentSiteCode);
//			//时间
//			ArrayList<Object> datalist = new ArrayList<Object>();
//			//市门户
//			ArrayList<Object> shiPortalslist = new ArrayList<Object>();
//			//市部门
//			ArrayList<Object> shiDepartmentlist = new ArrayList<Object>();
//			//省门户
//			ArrayList<Object> shengPortalslist = new ArrayList<Object>();
//			//省部门
//			ArrayList<Object> shengDepartmentlist = new ArrayList<Object>();
//			//县
//			ArrayList<Object> xianlist = new ArrayList<Object>();
//			//县门户
//			ArrayList<Object> xianDepartmentlist = new ArrayList<Object>();
//			//市政府
//			ArrayList<Object> shiGovernmentlist = new ArrayList<Object>();
//			if(type==1){
//				paramMap.put("isShi", null);
//				List<UpdateContentCountRequest> querySumOrg = updateContentCountServiceImpl.querySumOrg(paramMap);
//				if(querySumOrg.size()>0){
//					for (UpdateContentCountRequest updateContentCountRequest : querySumOrg) {
//						String scanDate = updateContentCountRequest.getScanDate();
//						int shengPortals = updateContentCountRequest.getShengPortals();
//						int shengDepartment = updateContentCountRequest.getShengDepartment();
//						int shiGovernment = updateContentCountRequest.getShiGovernment();
//						int xian = updateContentCountRequest.getXian();
//						datalist.add(DateUtils.formatStandardDate(scanDate));
//						shengPortalslist.add(shengPortals);
//						shengDepartmentlist.add(shengDepartment);
//						shiGovernmentlist.add(shiGovernment);
//						xianlist.add(xian);
//					}
//				}
//			}else if(type==2){
//				paramMap.put("isShi", "shi");
//				List<UpdateContentCountRequest> querySumOrg = updateContentCountServiceImpl.querySumOrg(paramMap);
//				if(querySumOrg.size()>0){
//					for (UpdateContentCountRequest updateContentCountRequest : querySumOrg) {
//						String scanDate = updateContentCountRequest.getScanDate();
//						int shiPortals = updateContentCountRequest.getShiPortals();
//						int shiDepartment = updateContentCountRequest.getShiDepartment();
//						int xian = updateContentCountRequest.getXian();
//						datalist.add(DateUtils.formatStandardDate(scanDate));
//						shiPortalslist.add(shiPortals);
//						shiDepartmentlist.add(shiDepartment);
//						xianlist.add(xian);
//					}
//				}
//			}else if(type==3){
//				paramMap.put("isShi", "xian");
//				List<UpdateContentCountRequest> querySumOrg = updateContentCountServiceImpl.querySumOrg(paramMap);
//				if(querySumOrg.size()>0){
//					for (UpdateContentCountRequest updateContentCountRequest : querySumOrg) {
//						String scanDate = updateContentCountRequest.getScanDate();
//						int xianDepartment = updateContentCountRequest.getXianDepartment();
//						int xian = updateContentCountRequest.getXian();
//						datalist.add(DateUtils.formatStandardDate(scanDate));
//						xianDepartmentlist.add(xianDepartment);
//						xianlist.add(xian);
//					}
//				}
//			}
//			map.put("datalist", datalist);
//			map.put("shiPortalslist", shiPortalslist);
//			map.put("shiDepartmentlist", shiDepartmentlist);
//			map.put("shengPortalslist", shengPortalslist);
//			map.put("shengDepartmentlist", shengDepartmentlist);
//			map.put("xianlist", xianlist);
//			map.put("xianDepartmentlist", xianDepartmentlist);
//			map.put("shiGovernmentlist", shiGovernmentlist);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		writerPrint(JSONObject.fromObject(map).toString());
//	}
	/**
	 * @Description: 组织单位：饼状图 内容分析
	 * @author sunjiang --- 2015-12-4下午2:37:03
	 */
	public void loadContentCountOrg() {

		Map<String, Object> map = new HashMap<String, Object>();

		List<Map<String, Object>> listChannel = new ArrayList<Map<String, Object>>();
		try {
			String scanDate = StringUtils.isEmpty(request.getParameter("scanDate")) ? DateUtils.getYesterdayStr(): request.getParameter("scanDate");
			map.put("scanDates",scanDate);
			//下拉框选中的全部，省，市，县区
			int level = (StringUtils.isNotEmpty(request.getParameter("level")) ? Integer.parseInt(request.getParameter("level")):0);
			//当前登录的用户级别   省市县
//			int type = (StringUtils.isNotEmpty(request.getParameter("type")) ? Integer.parseInt(request.getParameter("type")):0);
//			
			
			String currentSiteCode = getCurrentSiteCode();
			
			List<DatabaseInfo>  currentNextSiteCode=queryDatebaseInfoListByType(menuType, currentSiteCode);
			
			
			//获取总数
			UpdateContentCountRequest updateContentCountRequest = new UpdateContentCountRequest();
			updateContentCountRequest.setScanDate(scanDate);
			updateContentCountRequest.setIds(currentNextSiteCode);
			//updateContentCountRequest.setSiteCode(currentSiteCode);
			updateContentCountRequest.setType(1);
			List<UpdateContentCount> listUpdateContentCount = updateContentCountServiceImpl.queryCountAnalyzeLine(updateContentCountRequest);
			int sumNum = 0;
			if(listUpdateContentCount.size()>0){
				sumNum = listUpdateContentCount.get(0).getUpdateNum();
				map.put("sumNum", sumNum);
			}
			if(level==0){
				//是否头上一天
				map.put("isHasPrevious",isHasPreviousOrg(currentNextSiteCode,DateUtils.parseStandardDate(scanDate)));
				//是否有下一天
				map.put("isHasNext",isHasNext(DateUtils.parseStandardDate(scanDate)));
			}else{
				map.put("isHasPrevious",true);
				//是否有下一天
				map.put("isHasNext",true);
			}
			map.put("scanDate",DateUtils.formatDate(DateUtils.parseStandardDate(scanDate)));
			map.put("previousDate", DateUtils.getNextDay(scanDate, "-1"));
			map.put("nextDate", DateUtils.getNextDay(scanDate, "1"));

			Map<String, Object> hashMap = new HashMap<String, Object>();
			hashMap.put("scanDate", scanDate);
			hashMap.put("ids", currentNextSiteCode);
			//hashMap.put("siteCode", currentSiteCode);
			List<UpdateChannelInfo> listUpdateChannelInfo = updateChannelInfoServiceImpl.querySumUpdateNum(hashMap);
			if(listUpdateChannelInfo.size()>0){
				for (UpdateChannelInfo updateChannelInfo : listUpdateChannelInfo) {
					DicChannel dicChannel = dicChannelServiceImpl.get(updateChannelInfo.getDicChannelId());
					if(dicChannel!=null){
						Map<String, Object> channelMap = new HashMap<String, Object>();
						channelMap.put("channelName", dicChannel.getChannelName());
						channelMap.put("updateNum", updateChannelInfo.getUpdateNum());
						if(updateChannelInfo.getUpdateNum()==0&&sumNum==0){
							continue;
						}
						String proportion = StringUtils.getPrettyNumber(StringUtils.formatDouble(2, (updateChannelInfo.getUpdateNum()/(double)sumNum)*100));
						channelMap.put("proportion",proportion);
						if(proportion.equals("0")){
							channelMap.clear();
							continue;
						}
						listChannel.add(channelMap);
					}
					
				}
			}
			if(sumNum==0){
				listChannel.clear();
			}
			map.put("listChannel", listChannel);
			writerPrint(JSONObject.fromObject(map).toString());

		} catch (Exception e) {
			e.printStackTrace();
			logger.info("获取内容分析-信息分类统计数据失败");
			map.put("errmsg", "获取内容分析-信息分类统计数据失败！");
			writerPrint(JSONObject.fromObject(map).toString());
		}
	}
	/**
	 * @Description: 组织单位：列表 内容分析
	 * @author sunjiang --- 2015-12-7下午5:19:26
	 */
	public void loadContentListOrg() {

		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> paramMap = new HashMap<String, Object>();

		List<Map<String, Object>> listChannel = new ArrayList<Map<String, Object>>();
		String scanDate = StringUtils.isEmpty(request.getParameter("scanDate")) ? DateUtils.getYesterdayStr(): request.getParameter("scanDate");
		int type = (StringUtils.isNotEmpty(request.getParameter("type")) ? Integer.parseInt(request.getParameter("type")):0);
		String currentSiteCode = getCurrentSiteCode();
		//获取总数
		paramMap.put("scanDate", scanDate);
		paramMap.put("siteCode", currentSiteCode);
		paramMap.put("type", "1");
		
		map.put("scanDate",DateUtils.formatDate(DateUtils.parseStandardDate(scanDate)));
		//是否头上一天
		List<DatabaseInfo> currentNextSiteCode  =getCurrentNextSiteCode();
		map.put("isHasPrevious",isHasPreviousOrg(currentNextSiteCode,DateUtils.parseStandardDate(scanDate)));
		map.put("previousDate", DateUtils.getNextDay(scanDate, "-1"));
		//是否有下一天
		map.put("isHasNext",isHasNext(DateUtils.parseStandardDate(scanDate)));
		map.put("nextDate", DateUtils.getNextDay(scanDate, "1"));
		try {
			if(type==1){
				paramMap.put("isShi", null);
				int shengDepartmentSum = 0;
				int shengPortalsSum = 0;
				int shiGovernmentSum = 0;
				int xianSum = 0;
				List<UpdateContentCountRequest> querySumOrg = updateContentCountServiceImpl.querySumOrg(paramMap);
				if(querySumOrg.size()>0){
					for (UpdateContentCountRequest updateContentCountRequest : querySumOrg) {
						shengDepartmentSum = updateContentCountRequest.getShengDepartment();
						shengPortalsSum = updateContentCountRequest.getShengPortals();
						shiGovernmentSum = updateContentCountRequest.getShiGovernment();
						xianSum = updateContentCountRequest.getXian();
					}
				}
				List<UpdateContentCountRequest> queryChannelName = updateContentCountServiceImpl.queryChannelName(paramMap);
				if(queryChannelName.size()>0){
					for (UpdateContentCountRequest updateContentCountRequest : queryChannelName) {
						Map<String, Object> channelMap = new HashMap<String, Object>();
						String channelName = updateContentCountRequest.getChannelName();
						int shengDepartment = updateContentCountRequest.getShengDepartment();
						int shengPortals = updateContentCountRequest.getShengPortals();
						int shiGovernment = updateContentCountRequest.getShiGovernment();
						int xian = updateContentCountRequest.getXian();
						channelMap.put("channelName", channelName);
						if(shengDepartmentSum==0){
							channelMap.put("shengDepartmentPercent", 0);
						}else{
							channelMap.put("shengDepartmentPercent", StringUtils.getPrettyNumber(StringUtils.formatDouble(2, (shengDepartment/(double)shengDepartmentSum)*100)));
						}
						if(shengPortalsSum==0){
							channelMap.put("shengPortalsPercent", 0);
						}else{
							channelMap.put("shengPortalsPercent", StringUtils.getPrettyNumber(StringUtils.formatDouble(2, (shengPortals/(double)shengPortalsSum)*100)));
						}
						if(shiGovernmentSum==0){
							channelMap.put("shiGovernmentPercent", 0);
						}else{
							channelMap.put("shiGovernmentPercent", StringUtils.getPrettyNumber(StringUtils.formatDouble(2, (shiGovernment/(double)shiGovernmentSum)*100)));
						}
						if(xianSum==0){
							channelMap.put("xianPercent", 0);
						}else{
							channelMap.put("xianPercent", StringUtils.getPrettyNumber(StringUtils.formatDouble(2, (xian/(double)xianSum)*100)));
						}
						channelMap.put("shengDepartment", shengDepartment);
						channelMap.put("shengPortals", shengPortals);
						channelMap.put("shiGovernment", shiGovernment);
						channelMap.put("xian", xian);
						if(channelMap.get("shengDepartmentPercent").toString().equals("0")&&channelMap.get("shengPortalsPercent").toString().equals("0")&&channelMap.get("shiGovernmentPercent").toString().equals("0")
								&&channelMap.get("xianPercent").toString().equals("0")){
							channelMap.clear();
							continue;
						}
						listChannel.add(channelMap);
					}
					if(shengDepartmentSum==0&&shengPortalsSum==0&&shiGovernmentSum==0&&xianSum==0){
						listChannel.clear();
					}
				}
			}else if(type==2){
				paramMap.put("isShi", "shi");
				int shiDepartmentSum = 0;
                int shiPortalsSum = 0;
                int xianSum = 0;
                List<UpdateContentCountRequest> querySumOrg = updateContentCountServiceImpl.querySumOrg(paramMap);
                if(querySumOrg.size()>0){
                    for (UpdateContentCountRequest updateContentCountRequest : querySumOrg) {
                        shiDepartmentSum = updateContentCountRequest.getShiDepartment();
                        shiPortalsSum = updateContentCountRequest.getShiPortals();
                        xianSum = updateContentCountRequest.getXian();
                    }
                }
                List<UpdateContentCountRequest> queryChannelName = updateContentCountServiceImpl.queryChannelName(paramMap);
                if(queryChannelName.size()>0){
                    for (UpdateContentCountRequest updateContentCountRequest : queryChannelName) {
                        Map<String, Object> channelMap = new HashMap<String, Object>();
                        String channelName = updateContentCountRequest.getChannelName();
                        int shiDepartment = updateContentCountRequest.getShiDepartment();
                        int shiPortals = updateContentCountRequest.getShiPortals();
                        int xian = updateContentCountRequest.getXian();
                        channelMap.put("channelName", channelName);
                        channelMap.put("shiDepartment", shiDepartment);
                        if(shiDepartmentSum==0){
                            shiDepartmentSum = 1;
                            channelMap.put("shiDepartmentPercent", 0);
                        }else{
                            channelMap.put("shiDepartmentPercent", StringUtils.getPrettyNumber(StringUtils.formatDouble(2, (shiDepartment/(double)shiDepartmentSum)*100)));
                        }
                        if(shiPortalsSum==0){
                            channelMap.put("shiPortalsPercent", 0);
                        }else{
                            channelMap.put("shiPortalsPercent", StringUtils.getPrettyNumber(StringUtils.formatDouble(2, (shiPortals/(double)shiPortalsSum)*100)));

                        }
                        if(xianSum==0){
                            channelMap.put("xianPercent", 0);
                        }else{
                            channelMap.put("xianPercent", StringUtils.getPrettyNumber(StringUtils.formatDouble(2, (xian/(double)xianSum)*100)));
                        }
                        channelMap.put("shiPortals", shiPortals);
                        channelMap.put("xian", xian);
                        if(channelMap.get("shiDepartmentPercent").toString().equals("0")&&channelMap.get("shiPortalsPercent").toString().equals("0")&&channelMap.get("xianPercent").toString().equals("0")){
                            channelMap.clear();
                            continue;
                        }
                        listChannel.add(channelMap);
                    }
                }
			}else if(type==3){
				paramMap.put("isShi", "xian");
				int xianDepartmentSum = 0;
				int xianSum = 0;
				List<UpdateContentCountRequest> querySumOrg = updateContentCountServiceImpl.querySumOrg(paramMap);
				if(querySumOrg.size()>0){
					for (UpdateContentCountRequest updateContentCountRequest : querySumOrg) {
						xianDepartmentSum = updateContentCountRequest.getXianDepartment();
						xianSum = updateContentCountRequest.getXian();
					}
				}
				List<UpdateContentCountRequest> queryChannelName = updateContentCountServiceImpl.queryChannelName(paramMap);
				if(queryChannelName.size()>0){
					for (UpdateContentCountRequest updateContentCountRequest : queryChannelName) {
						Map<String, Object> channelMap = new HashMap<String, Object>();
						String channelName = updateContentCountRequest.getChannelName();
						int xianDepartment = updateContentCountRequest.getXianDepartment();
						int xian = updateContentCountRequest.getXian();
						if(xianDepartmentSum==0){
							channelMap.put("xianDepartmentPercent", 0);
						}else{
							channelMap.put("xianDepartmentPercent", StringUtils.getPrettyNumber(StringUtils.formatDouble(2, (xianDepartment/(double)xianDepartmentSum)*100)));
						}
						if(xianSum==0){
							channelMap.put("xianPercent", 0);
						}else{
							channelMap.put("xianPercent", StringUtils.getPrettyNumber(StringUtils.formatDouble(2, (xian/(double)xianSum)*100)));
						}
						channelMap.put("xianDepartment", xianDepartment);
						channelMap.put("xian", xian);
						channelMap.put("channelName", channelName);
						if(channelMap.get("xianDepartmentPercent").toString().equals("0")&&channelMap.get("xianPercent").toString().equals("0")){
							channelMap.clear();
							continue;
						}
						listChannel.add(channelMap);
					}
					if(xianDepartmentSum==0&&xianSum==0){
						listChannel.clear();
					}
				}
			}
			map.put("listChannel", listChannel);
			writerPrint(JSONObject.fromObject(map).toString());

		} catch (Exception e) {
			e.printStackTrace();
			logger.info("获取内容分析-信息分类统计数据失败");
			map.put("errmsg", "获取内容分析-信息分类统计数据失败！");
			writerPrint(JSONObject.fromObject(map).toString());
		}
	}
	/**
	 * @Description: 内容分析的时间坐标折线图
	 * @author Nora --- 2015年11月6日下午6:11:56
	 */
	public void getUpdateContentLine() {
		/*
		 * String siteCode = request.getParameter("siteCode"); if
		 * (StringUtils.isEmpty(siteCode)) { siteCode = "3300000001"; }
		 */

		//从session中获取10位填报单位网站标识码
		String siteCode=getCurrentUserInfo().getChildSiteCode();
		if(StringUtils.isEmpty(siteCode)){
			siteCode=getCurrentUserInfo().getSiteCode();
		}
		
		// 获取前90天的开始时间
		String startDay = DateUtils.getNextDay(new Date(), -90);
		// 获取昨天的日期
		String endDay = DateUtils.getNextDay(new Date(), -1);

		try {

			ArrayList<Object> list = new ArrayList<Object>();
			List<UpdateContentCount> listUpdateContentCount = getUpdateContentCounts(
					siteCode, startDay, endDay);
			for (UpdateContentCount updateContentCount : listUpdateContentCount) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				int updateNum = updateContentCount.getUpdateNum();
				String scanDate = updateContentCount.getScanDate();

				// map.put("siteCode", updateContentCount.getSiteCode());
				map.put("updateNum", updateNum);
				map.put("scanDate", DateUtils.formatStandardDate(scanDate));
				list.add(map);
			}
			writerPrint(JSONArray.fromObject(list).toString());
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("信息日更新趋势分析时间坐标折线图" + "siteCode :" + siteCode);
		}

	}

	/**
	 * 获取一段时间内日统计更新数
	 * 
	 * @param siteCode
	 * @param startDay
	 * @param endDay
	 * @return
	 */
	private List<UpdateContentCount> getUpdateContentCounts(String siteCode,
			String startDay, String endDay) {
		UpdateContentCountRequest request = new UpdateContentCountRequest();

		request.setBeginScanDate(startDay);
		request.setEndScanDate(endDay);
		request.setSiteCode(siteCode);

		return updateContentCountServiceImpl.queryCountAnalyzeLine(request);

	}

	/**
	 * 是否有前一天数据
	 * 
	 * @param siteCode
	 * @return
	 */
	public boolean isHasPrevious(String siteCode, Date scanDate) {
		return true;
	}

	/**
	 * 是否有前一天数据
	 * 
	 * @param siteCode
	 * @return
	 */
	public boolean isHasPreviousOrg(List<DatabaseInfo> siteCode, Date scanDate) {
		TaskDaily taskDaily = getTaskDailyOrg(siteCode);
		if (null == taskDaily) {
			return false;
		}

		if (DateUtils.getDaysBetween2Days(taskDaily.getStartDate(), scanDate) > 0) {
			return true;
		}
		return false;

	}
	/**
	 * 是否有后一天数据
	 * 
	 * @param siteCode
	 * @return
	 */
	public boolean isHasNext(Date scanDate) {

		if (DateUtils.getDaysBetween2Days(scanDate,
				DateUtils.getYesterdaytime()) > 0) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 根据siteCode 获取TaskDaily（最近订单的）
	 * 
	 * @param siteCode
	 * @return
	 */
	@SuppressWarnings("unused")
	private TaskDaily getTaskDaily(String siteCode) {
		TaskDailyRequest request = new TaskDailyRequest();
		request.setSiteCode(siteCode);
		List<QueryOrder> queryOrderList = new ArrayList<QueryOrder>();
		QueryOrder queryOrder = new QueryOrder("create_time",
				QueryOrderType.DESC);
		queryOrderList.add(queryOrder);

		request.setQueryOrderList(queryOrderList);
		List<TaskDaily> list = taskDailyServiceImpl.queryList(request);

		if (CollectionUtils.isEmpty(list)) {
			return null;
		} else {
			return list.get(0);
		}
	}
	
	public TaskDaily getTaskDailyOrg(List<DatabaseInfo> siteCode) {
		TaskDailyRequest request = new TaskDailyRequest();
		request.setIds(siteCode);
		request.setPageSize(Integer.MAX_VALUE);
		List<QueryOrder> queryOrderList = new ArrayList<QueryOrder>();
		QueryOrder queryOrder = new QueryOrder("create_time",
				QueryOrderType.DESC);
		queryOrderList.add(queryOrder);
		
		request.setQueryOrderList(queryOrderList);
		List<TaskDaily> list = taskDailyServiceImpl.queryList(request);
		
		if (CollectionUtils.isEmpty(list)) {
			return null;
		} else {
			return list.get(0);
		}
	}

	/**
	 * 查询栏目更新总数（按扫描时间与网站标识码查询）
	 * 
	 * @param siteCode
	 * @param scanDate
	 * @return
	 */
	private int getSumNumUpdateChannel(String siteCode, String scanDate) {
		// 内容更新按日统计
		UpdateContentCountRequest request = new UpdateContentCountRequest();
		request.setScanDate(scanDate);
		request.setSiteCode(siteCode);
		request.setType(1);

		List<UpdateContentCount> listUpdateContentCount = updateContentCountServiceImpl
				.queryList(request);

		if (CollectionUtils.isEmpty(listUpdateContentCount)) {
			return 0;
		} else {
			return listUpdateContentCount.get(0).getUpdateNum();
		}
	}

	/**
	 * 获取比例（没有%号），保留2位小数点
	 * 
	 * @param num
	 *            数量
	 * @param totalNum
	 *            总数
	 * @return
	 */
	private String getProportion(int num, int totalNum) {

		if (num == 0 || totalNum == 0) {
			return "0";
		}

		double proportion = num * 1.0 / totalNum * 100;

		String result = StringUtils.formatDouble(2, proportion);

		if (result.equals("100.00")) {
			return "100";
		} else {
			return result;
		}
	}

	public HashMap<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(HashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	public String getMenuType() {
		return menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}
}
