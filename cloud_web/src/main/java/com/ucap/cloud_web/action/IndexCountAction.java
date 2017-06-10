package com.ucap.cloud_web.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;

import com.publics.util.page.QueryOrder;
import com.publics.util.page.QueryOrderType;
import com.publics.util.utils.DateUtils;
import com.publics.util.utils.StringUtils;
import com.ucap.cloud_web.constant.DatabaseLinkType;
import com.ucap.cloud_web.dto.DetectionOrgCountRequest;
import com.ucap.cloud_web.dto.MTaskoverviewRequest;
import com.ucap.cloud_web.entity.DetectionOrgCount;
import com.ucap.cloud_web.entity.MTaskoverview;
import com.ucap.cloud_web.service.IDetectionOrgCountService;
import com.ucap.cloud_web.service.IMTaskoverviewService;

/**
 * <p>Description: 健康指数</p>
 * <p>@Package：com.ucap.cloud_web.action </p>
 * <p>Title: IndexCountAction </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：sunjiang </p>
 * <p>@date：2016-1-5下午6:05:09 </p>
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class IndexCountAction extends BaseAction{
	@Autowired
	private IDetectionOrgCountService detectionOrgCountServiceImpl;
	@Autowired
	private IMTaskoverviewService MTaskoverviewServiceImpl;

	
	
	public void getIndexSumMTask(){
	
		try {
			HashMap<Object, Object> map = new HashMap<Object, Object>();
			String currentSiteCode = getCurrentSiteCode();
			
			/** 查询大数据的健康指数   **/
			MTaskoverviewRequest mTaskRequest = new MTaskoverviewRequest();
			String countDay = DateUtils.formatShortDate(DateUtils.getYesterdaytime()); //统计时间
			mTaskRequest.setTaskid(currentSiteCode); //标识码
			mTaskRequest.setCountday(countDay); //统计时间
			List<MTaskoverview> mTaskList = MTaskoverviewServiceImpl.queryList(mTaskRequest);
			
			if(!CollectionUtils.isEmpty(mTaskList) && mTaskList.size() > 0){
				MTaskoverview mTask = mTaskList.get(0);
				//折算分数
				map.put("convertScores",mTask.getHealthindex() == null?0:mTask.getHealthindex());
			}
			
			writerPrint(JSONObject.fromObject(map).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description: 组织单位--健康指数
	 * @author sunjiang --- 2016-1-5下午8:37:45
	 */
	public void getIndexSum(){
		try {
			HashMap<Object, Object> map = new HashMap<Object, Object>();
			
			String currentSiteCode = getCurrentSiteCode();
//			String scanDate = DateUtils.getNextDay(new Date(), -1);
			String scanDate = queryHomePageDate();
			/***查询 DetectionOrgCount表 start*****/
			DetectionOrgCountRequest detectionOrgCountRequest=new DetectionOrgCountRequest();
			detectionOrgCountRequest.setScanDate(scanDate);
			detectionOrgCountRequest.setSiteCode(currentSiteCode);
			detectionOrgCountRequest.setType(DatabaseLinkType.ALL.getCode().toString());
			List<QueryOrder> queryOrderOrgList=new ArrayList<QueryOrder>();
			QueryOrder queryOrder=new QueryOrder("scan_date",QueryOrderType.ASC);
			queryOrderOrgList.add(queryOrder);
			detectionOrgCountRequest.setQueryOrderList(queryOrderOrgList);

		
			List<DetectionOrgCount> detectionOrgCountList=detectionOrgCountServiceImpl.queryList(detectionOrgCountRequest);
			for(int i=0;i<detectionOrgCountList.size();i++){
				DetectionOrgCount detectionOrgCount =detectionOrgCountList.get(i);
				String indexCount=detectionOrgCount.getIndexCount();//健康指数（平均）
				if(null == indexCount){
					indexCount="0";
				}
				String leadAvgRate=detectionOrgCount.getLeadAvgRate();//领先全国
				if(null == leadAvgRate){
					leadAvgRate="0";
				}
				String leadYesterday=detectionOrgCount.getLeadYesterday();//相比昨天数
				if(null == leadYesterday){
					leadYesterday="0";
				}
				String leadYesterdayRate=detectionOrgCount.getLeadYesterdayRate();//相比昨天比
				if(null == leadYesterdayRate){
					leadYesterdayRate="0";
				}
				//领先全国比
				map.put("leadSum",leadAvgRate);
				//折算分数
				map.put("convertScores",indexCount);
				//健康分数
				map.put("totalSumNumber",indexCount);
				//相差多少
				map.put("differential", leadYesterday);
				map.put("differentialRate", leadYesterdayRate);
			}
			if(detectionOrgCountList.size()==0){
				//领先全国比
				map.put("leadSum",0);
				//折算分数
				map.put("convertScores",0);
				//健康分数
				map.put("totalSumNumber",0);
				//相差多少
				map.put("differential", 0);
				map.put("differentialRate", 0);
			}

			
			
			writerPrint(JSONObject.fromObject(map).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * @Description: //健康指数折线图
	 * @author sunjiang --- 2016-1-6上午10:43:04
	 */
	public void getLineSum(){
		try {
			HashMap<Object, Object> map = new HashMap<Object, Object>();
			String currentSiteCode = getCurrentSiteCode();
			//判断是否部委
			String subSiteCode = currentSiteCode.substring(0, 2);
			map.put("isBm", subSiteCode);
			map.put("siteCode", currentSiteCode);
			
			ArrayList<Object> indexlist = new ArrayList<Object>();
			ArrayList<Object> datelist = new ArrayList<Object>();
			ArrayList<Object> allIndexlist = new ArrayList<Object>();
			
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
			List<DetectionOrgCount> detectionOrgCountList=detectionOrgCountServiceImpl.queryList(detectionOrgCountRequest);
			
			if(!CollectionUtils.isEmpty(detectionOrgCountList)){
				for (DetectionOrgCount detectionOrgCount : detectionOrgCountList) {
					String totalSum =detectionOrgCount.getIndexCount();//非全国健康指数（平均）
					String scanDate =detectionOrgCount.getScanDate();
					String allTotalSum=detectionOrgCount.getIndexCountAvg();//全国健康指数（平均）
					indexlist.add(totalSum);
					datelist.add(StringUtils.getPrettyNumber(DateUtils.getDayStr(scanDate))+"日");
					allIndexlist.add(allTotalSum);
				}
			}
				
			map.put("indexlist", indexlist);
			map.put("datelist", datelist);
			map.put("allIndexlist", allIndexlist);
			
			writerPrint(JSONObject.fromObject(map).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
