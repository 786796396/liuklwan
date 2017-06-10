package com.ucap.cloud_web.action;

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

import com.publics.util.page.QueryOrder;
import com.publics.util.page.QueryOrderType;
import com.publics.util.utils.DateUtils;
import com.publics.util.utils.StringUtils;
import com.ucap.cloud_web.dto.MonitorIncludeRequest;
import com.ucap.cloud_web.entity.MonitorInclude;
import com.ucap.cloud_web.service.IMonitorIncludeService;
import com.ucap.cloud_web.util.ExportExcel;



/**
 * <p>Description: 百度收录填报单位</p>
 * <p>@Package：com.ucap.cloud_web.action </p>
 * <p>Title: MonitorIncludeAction </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：cuichx </p>
 * <p>@date：2016-11-3上午10:25:42 </p>
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class MonitorIncludeAction extends BaseAction {

	@Autowired
	private IMonitorIncludeService monitorIncludeServiceImpl;
	
	
	private static Log logger = LogFactory.getLog(MonitorIncludeAction.class);
	
	private Integer days;
	
	/**
	 * @Description: 百度收录  填报单位页面初始化
	 * @author cuichx --- 2016-11-3上午10:26:53     
	 * @return
	 */
	public String index(){
		//siteCode处理由组织单位跳转到该页面时，session的修改
		String siteCode = request.getParameter("siteCode");
		if (StringUtils.isNotEmpty(siteCode)) {
			setCurrentShiroUser(siteCode);
		}
			//从session中获取10位填报单位网站标识码
		String	siteCodeTwo = getCurrentUserInfo().getChildSiteCode();
		if (StringUtils.isEmpty(siteCodeTwo)) {
			siteCodeTwo = getCurrentUserInfo().getSiteCode();
		}
		request.setAttribute("siteCode", siteCodeTwo);
		return "success";
	}
	
	/**
	 * @Description: 填报单位---搜索引擎收录数据列表查询
	 * @author cuichx --- 2016-11-3下午12:51:57
	 */
	public void  getMonitorDataList() {
		
		Map<String, Object> resultMap=new HashMap<String, Object>();
		
		try {
			
			//从session中获取10位填报单位网站标识码
			String siteCode = getCurrentUserInfo().getChildSiteCode();
			if (StringUtils.isEmpty(siteCode)) {
				siteCode = getCurrentUserInfo().getSiteCode();
			}
			
			String days=request.getParameter("days");//查询时间段
			//String keyWord=request.getParameter("keyWord");//关键字查询
			
			String beginDate="";//开始日期+
//			String endDate=DateUtils.formatStandardDate(new Date());//当前日期
			//获取昨天的日期
			String endDate = DateUtils.getNextDay(new Date(), -1);
			logger.info("====getMonitorDataList==siteCode:"+siteCode
					+"=====days:"+days+"=====beginDate:"+beginDate
					+"=====endDate:"+endDate);
			
			if(StringUtils.isNotEmpty(days)){
				beginDate=DateUtils.getNextDay(new Date(), Integer.valueOf(days));
			}else{
				beginDate=DateUtils.getNextDay(new Date(), -1);
			}
			
			//封装查询条件
			MonitorIncludeRequest monitorRequest=new MonitorIncludeRequest();
			monitorRequest.setSiteCode(siteCode);
			monitorRequest.setBeginScanDate(beginDate);
			monitorRequest.setEndScanDate(endDate);
			monitorRequest.setPageSize(Integer.MAX_VALUE);
			
			//设置默认排序字段
			List<QueryOrder> queryOrderList=new ArrayList<QueryOrder>();
			QueryOrder siteQueryOrder=new QueryOrder("scan_date",QueryOrderType.DESC);
			queryOrderList.add(siteQueryOrder);
			monitorRequest.setQueryOrderList(queryOrderList);
			
			
			List<MonitorInclude>  monitorList=monitorIncludeServiceImpl.queryList(monitorRequest);
			if(monitorList!=null && monitorList.size()>0){
				
				List<Map<String, Object>>  returnList=new ArrayList<Map<String,Object>>();
				for (MonitorInclude monitorInclude : monitorList) {
					Map<String, Object> map=new HashMap<String, Object>();
					//百度收录数_站点
					Integer baiduSlWebsite=0;
					//百度收录数_域
					Integer baiduSlDomainsite=0;
					//采集时间
					String crawlDate=DateUtils.formatStandardDateTime(monitorInclude.getCrawlDate());
					
					if(monitorInclude.getBaiduSlWebsite()!=null){
						baiduSlWebsite=monitorInclude.getBaiduSlWebsite();
					}
					if(monitorInclude.getBaiduSlDomainsite()!=null){
						baiduSlDomainsite=monitorInclude.getBaiduSlDomainsite();
					}
					map.put("scanDate", monitorInclude.getScanDate());
					map.put("crawlDate", crawlDate);
					map.put("baiduSlWebsite", baiduSlWebsite);
					map.put("baiduSlDomainsite", baiduSlDomainsite);
					
					returnList.add(map);
				}
				resultMap.put("returnList", returnList);
				resultMap.put("totalNum", returnList.size());
			}else{
				resultMap.put("errmsg", "暂无搜索引擎数据");
			}
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("errmsg", "查询列表数据异常！");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		}
	}
	
	public void exportExcel(){

		
		//从session中获取10位填报单位网站标识码
		String siteCode=getCurrentUserInfo().getChildSiteCode();
		if(StringUtils.isEmpty(siteCode)){
			siteCode=getCurrentUserInfo().getSiteCode();
		}
	
		try {
			MonitorIncludeRequest monitorIncludeRequest=new MonitorIncludeRequest();
			//获取days天扫描的日期
			String startDate = DateUtils.getNextDay(new Date(), days);
			monitorIncludeRequest.setBeginScanDate(startDate);
			//获取昨天的日期
			String endDate = DateUtils.getNextDay(new Date(), -1);
			monitorIncludeRequest.setEndScanDate(endDate);	
			
	
			ArrayList<Object[]> list = new ArrayList<Object[]>();
			Object[] obj1 = new Object[]{"序号","更新时间","站点收录网页数","域收录网页数"};
//			Object[] obj1 = new Object[]{"序号","更新时间","网页收录总数（站内）","网页收录总数（域内）"};
			list.add(obj1);
			String fileName = "搜索引擎收录明细("+DateUtils.formatStandardDate(new Date())+").xls";
			String title = "搜索引擎收录明细"; 

			monitorIncludeRequest.setSiteCode(siteCode);
			monitorIncludeRequest.setPageSize(Integer.MAX_VALUE);
			List<QueryOrder> queryOrderList = new ArrayList<QueryOrder>();
			queryOrderList.add(new QueryOrder("scan_date", QueryOrderType.DESC));
			monitorIncludeRequest.setQueryOrderList(queryOrderList);
			monitorIncludeRequest.setPageSize(Integer.MAX_VALUE);
			List<MonitorInclude> queryList=monitorIncludeServiceImpl.queryList(monitorIncludeRequest);
			if(queryList.size()>0){
				for (int i = 0; i < queryList.size(); i++){
					MonitorInclude monitorInclude = queryList.get(i);
					Object[] obj = new Object[4];
					obj[0]=i+1;
//					String scanTime = monitorInclude.getCrawlDate()!=null?DateUtils.formatStandardDateTime(monitorInclude.getCrawlDate()):"";
					obj[1] = monitorInclude.getScanDate();
					
					Integer baiduSlWebsite= monitorInclude.getBaiduSlWebsite();
					obj[2]=baiduSlWebsite;
					Integer baiduSlDomainsite = monitorInclude.getBaiduSlDomainsite();
					obj[3] = baiduSlDomainsite;
					list.add(obj);
				}
			}
			ExportExcel.monitorIncludeExcel(fileName, title, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	
	}

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}
}
