package com.ucap.cloud_web.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.publics.util.page.QueryOrder;
import com.publics.util.page.QueryOrderType;
import com.publics.util.utils.DateUtils;
import com.ucap.cloud_web.bizService.DatabaseBizService;
import com.ucap.cloud_web.dto.DatabaseTreeInfoRequest;
import com.ucap.cloud_web.dto.MonitorIncludeRequest;
import com.ucap.cloud_web.dto.MonitorOrgIncludeRequest;
import com.ucap.cloud_web.entity.DatabaseTreeInfo;
import com.ucap.cloud_web.entity.MonitorInclude;
import com.ucap.cloud_web.entity.MonitorOrgInclude;
import com.ucap.cloud_web.service.IDatabaseTreeInfoService;
import com.ucap.cloud_web.service.IMonitorIncludeService;
import com.ucap.cloud_web.service.IMonitorOrgIncludeService;
import com.ucap.cloud_web.util.ExportExcel;
/**
 * 描述：百度搜索大数据分析 
 * 包：com.ucap.cloud_web.action
 * 文件名称：DailyMonitoringStatisticsBaiduAction
 * 公司名称：开普互联
 * 作者：liujc@ucap.com.cn
 * 时间：2016-11-1下午1:10:55 
 * @version V1.0
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class BaiduBigDataAction extends BaseAction{
	@Autowired
	private IDatabaseTreeInfoService databaseTreeInfoServiceImpl;
	@Autowired
	private DatabaseBizService databaseBizServiceImpl;
	@Autowired
	private IMonitorIncludeService monitorIncludeServiceImpl;
	@Autowired
	private IMonitorOrgIncludeService monitorOrgIncludeServiceImpl;
	private String siteCode = "";
	private int dateNum;
	
	private Object[] objOrg = new Object[]{"组织单位名称/编码","检测网站数量(个)","站点收录个数(平均)","域收录个数(平均)"};
	private Object[] objTb  = new Object[]{"填报单位名称/编码","网址","站点收录网页数","域收录网页数","更新时间"};
	public String baiduBigData(){
		try {

			//获取当前登录的  siteCode
			String siteCod = getCurrentSiteCode();
			int id = 0;
			int level = 2;// 1 bm0100 ;2有下级战群 3 没有下级战群； 
			id = databaseBizServiceImpl.isHasChridren(siteCod);
			// flag 0 有下级战群 1 没有下级战群
			if(id>0){
				level = 2;
			}else{
				level = 3;
			}
			
			request.setAttribute("siteCode",siteCod);
			request.setAttribute("level",level);
			request.setAttribute("databaseTreeInfoId",id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	
	/********************大数据查询内容开始Start*******************************************************************/
	
	/**
	 * 
	 * @描述: 大数据  获取数据
	 * @作者:linhb@ucap.com.cn
	 * @时间:2016年12月24日下午1:55:16
	 */
	public void getDatas(){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			int level = 2;// 1 bm0100 ;2有下级战群 3 没有下级战群； 
			int id = databaseBizServiceImpl.isHasChridren(siteCode);
			if(id>0){
				MonitorOrgIncludeRequest rRequest = new MonitorOrgIncludeRequest();
				level = 2;
				rRequest.setParentId(id);
				rRequest.setScanDate(DateUtils.getYesterdayStr());
				List<MonitorOrgInclude> list = monitorOrgIncludeServiceImpl.getOrgData(rRequest);
				resultMap.put("list", list);
				resultMap.put("databaseTreeInfoId",id);
			}else{
				level = 3;
				DatabaseTreeInfoRequest qRequest = new DatabaseTreeInfoRequest();
				qRequest.setSiteCode(siteCode);
				List<DatabaseTreeInfo> dList = databaseTreeInfoServiceImpl.queryList(qRequest);
				if(dList.size()>0){
					MonitorIncludeRequest sRequest = new MonitorIncludeRequest();
					sRequest.setParentId(dList.get(0).getId());
					sRequest.setScanDate(DateUtils.getYesterdayStr());
					List<MonitorInclude> sList = monitorIncludeServiceImpl.queryNatives(sRequest);
					resultMap.put("list", sList);
					resultMap.put("databaseTreeInfoId",dList.get(0).getId());
				}
			}
			
			resultMap.put("level", level);
			resultMap.put("success", "true");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			resultMap.put("success", "false");
			writerPrint(JSONObject.fromObject(resultMap).toString());
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @描述:获取 收录 站点的趋势
	 * @作者:linhb@ucap.com.cn
	 * @时间:2016年12月24日下午5:39:26
	 */
	public void getTrend(){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			if(StringUtils.isNotEmpty(siteCode)){
				if(dateNum>-1){
					dateNum = -14;
				}
				if(siteCode.length()==6){
					MonitorOrgIncludeRequest rRequest = new MonitorOrgIncludeRequest();
					rRequest.setSiteCode(siteCode);
					rRequest.setPageSize(Integer.MAX_VALUE);
					rRequest.setBeginScanDate(DateUtils.getNextDay(new Date(), dateNum));
					rRequest.setEndScanDate(DateUtils.getNextDay(new Date(), -1));
					List<QueryOrder> qList = new ArrayList<QueryOrder>();
					QueryOrder siteQueryOrder=new QueryOrder("scan_date",QueryOrderType.ASC);
					qList.add(siteQueryOrder);
					rRequest.setQueryOrderList(qList);
					
					List<MonitorOrgInclude> list = monitorOrgIncludeServiceImpl.queryList(rRequest);
					resultMap.put("list", list);
				}else{
					MonitorIncludeRequest sRequest = new MonitorIncludeRequest();
					sRequest.setBeginScanDate(DateUtils.getNextDay(new Date(), dateNum));
					sRequest.setEndScanDate(DateUtils.getNextDay(new Date(), -1));
					sRequest.setSiteCode(siteCode);
					sRequest.setPageSize(Integer.MAX_VALUE);
					
					List<QueryOrder> qList = new ArrayList<QueryOrder>();
					QueryOrder siteQueryOrder=new QueryOrder("scan_date",QueryOrderType.ASC);
					qList.add(siteQueryOrder);
					sRequest.setQueryOrderList(qList);
					
					List<MonitorInclude> list = monitorIncludeServiceImpl.queryList(sRequest);
					resultMap.put("list", list);
				}
				resultMap.put("success", "true");
				writerPrint(JSONObject.fromObject(resultMap).toString());
			}
		} catch (Exception e) {
			resultMap.put("success", "false");
			writerPrint(JSONObject.fromObject(resultMap).toString());
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @描述:点击 检测网站数量 获取数据
	 * @作者:linhb@ucap.com.cn
	 * @时间:2016年12月26日下午1:22:36
	 */
	public void querySiteData(){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			if(StringUtils.isNotEmpty(siteCode)){
				List<MonitorInclude> list = querySiteByCode(siteCode);
				resultMap.put("list", list);
				resultMap.put("success", "true");
				writerPrint(JSONObject.fromObject(resultMap).toString());
			}
		} catch (Exception e) {
			resultMap.put("success", "false");
			writerPrint(JSONObject.fromObject(resultMap).toString());
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 
	 * @描述:
	 * @作者:linhb@ucap.com.cn
	 * @时间:2016年12月26日下午4:25:47
	 */
	public List<MonitorInclude>  querySiteByCode(String sited){
		List<MonitorInclude> list = new ArrayList<MonitorInclude>();
		try {
				DatabaseTreeInfoRequest qRequest = new DatabaseTreeInfoRequest();
				qRequest.setSiteCode(sited);
				List<DatabaseTreeInfo> listT = databaseTreeInfoServiceImpl.queryList(qRequest);
				if(listT.size()>0){
					MonitorIncludeRequest sRequest = new MonitorIncludeRequest();
					sRequest.setScanDate(DateUtils.getNextDay(new Date(), -1));
					sRequest.setPageSize(Integer.MAX_VALUE);
					sRequest.setCode(listT.get(0).getCode());
					list = monitorIncludeServiceImpl.querySiteData(sRequest);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 
	 * @描述:导出数据
	 * @作者:linhb@ucap.com.cn
	 * @时间:2016年12月26日下午1:53:03
	 */
	public void exportExcelData(){
		try {
			String flag = request.getParameter("flag");
			List<Map<String, Object>> dtList=new ArrayList<Map<String,Object>>();
			if("0".equals(flag)){//非点击  站点收录 获取数据
				int id = databaseBizServiceImpl.isHasChridren(siteCode);
				
				if(id>0){
					MonitorOrgIncludeRequest rRequest = new MonitorOrgIncludeRequest();
					rRequest.setParentId(id);
					rRequest.setScanDate(DateUtils.getYesterdayStr());
					List<MonitorOrgInclude> list = monitorOrgIncludeServiceImpl.getOrgData(rRequest);
				
					
					Map<String, Object> dataMap1=new HashMap<String, Object>();
					dataMap1.put("title", "搜索引擎收录");
					ArrayList<Object[]> listN=new ArrayList<Object[]>();
					listN.add(objOrg);
					listN.addAll(commonOrg(list));
					dataMap1.put("list", listN);
					dtList.add(dataMap1);
					
				}else{
					DatabaseTreeInfoRequest qRequest = new DatabaseTreeInfoRequest();
					qRequest.setSiteCode(siteCode);
					List<DatabaseTreeInfo> dList = databaseTreeInfoServiceImpl.queryList(qRequest);
					if(dList.size()>0){
						MonitorIncludeRequest sRequest = new MonitorIncludeRequest();
						sRequest.setParentId(dList.get(0).getId());
						sRequest.setScanDate(DateUtils.getYesterdayStr());
						List<MonitorInclude> sList = monitorIncludeServiceImpl.queryNatives(sRequest);
					
						Map<String, Object> dataMap1=new HashMap<String, Object>();
						dataMap1.put("title", "搜索引擎收录");
						ArrayList<Object[]> listN=new ArrayList<Object[]>();
						listN.add(objTb);
						listN.addAll(commonTb(sList));
						dataMap1.put("list", listN);
						dtList.add(dataMap1);
					}
				}
			}else{
				
				String querySiteCode = request.getParameter("querySiteCode");
				if(StringUtils.isNotEmpty(querySiteCode)){
					List<MonitorInclude> list = querySiteByCode(querySiteCode);
					
					Map<String, Object> dataMap1=new HashMap<String, Object>();
					dataMap1.put("title", "搜索引擎收录");
					ArrayList<Object[]> listN=new ArrayList<Object[]>();
					listN.add(objTb);
					listN.addAll(commonTb(list));
					dataMap1.put("list", listN);
					dtList.add(dataMap1);
				}
			}
			
			ExportExcel.getVisitDatas("网站访问量.xls",dtList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @描述:
	 * @作者:linhb@ucap.com.cn
	 * @时间:2016年12月26日下午4:56:02 
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public List<Object[]> commonOrg(List<MonitorOrgInclude> list) throws Exception{
		List<Object[]> rList = new ArrayList<Object[]>();
		for (MonitorOrgInclude monitorOrgInclude : list) {
			Object[] obj = new Object[4];
			obj[0]=monitorOrgInclude.getName()+" ("+monitorOrgInclude.getSiteCode()+")";
			obj[1]=monitorOrgInclude.getSiteSum();
			obj[2]=monitorOrgInclude.getBaiduSiteAvg();
			obj[3]=monitorOrgInclude.getBaiduDomainAvg();
			rList.add(obj);
		}
		return rList;
	}
	/**
	 * 
	 * @描述:
	 * @作者:linhb@ucap.com.cn
	 * @时间:2016年12月26日下午4:56:16 
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public List<Object[]> commonTb(List<MonitorInclude> list) throws Exception{
		List<Object[]> rList = new ArrayList<Object[]>();
		for (MonitorInclude monitorInclude : list) {
			Object[] obj = new Object[5];
			obj[0]=monitorInclude.getName()+" ("+monitorInclude.getSiteCode()+")";
			if(StringUtils.isNotEmpty(monitorInclude.getJumpUrl())){
				obj[1]=monitorInclude.getJumpUrl();
			}else{
				obj[1]=monitorInclude.getUrl();
			}
			obj[2]=monitorInclude.getBaiduSlWebsite();
			obj[3]=monitorInclude.getBaiduSlDomainsite();
			obj[4]=monitorInclude.getScanDate();
			rList.add(obj);
		}
		return rList;
	}
	
	
	public int getDateNum() {
		return dateNum;
	}
	public void setDateNum(int dateNum) {
		this.dateNum = dateNum;
	}
	public String getSiteCode() {
		return siteCode;
	}
	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}
	
	
	
	
}
