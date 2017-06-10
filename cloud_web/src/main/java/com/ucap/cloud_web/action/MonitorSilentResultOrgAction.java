package com.ucap.cloud_web.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.ucap.cloud_web.constant.DatabaseLinkType;
import com.ucap.cloud_web.dto.MonitorSilentOrgResultRequest;
import com.ucap.cloud_web.dto.MonitorSilentResultRequest;
import com.ucap.cloud_web.entity.ContractInfo;
import com.ucap.cloud_web.entity.MonitorSilentOrgResult;
import com.ucap.cloud_web.service.IConnectionHomeDetailService;
import com.ucap.cloud_web.service.IMonitorSilentOrgResultService;
import com.ucap.cloud_web.service.IMonitorSilentResultService;
import com.ucap.cloud_web.util.ExportExcel;

import net.sf.json.JSONObject;

/**
 * 描述： 安全问题
 * 包：com.ucap.cloud_web.action
 * 文件名称：SecurityQuestion
 * 公司名称：开普互联
 * 作者：liujc@ucap.com.cn
 * 时间：2016-9-2上午10:03:41 
 * @version V1.0
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class MonitorSilentResultOrgAction extends BaseAction{
	@Autowired
	private IMonitorSilentResultService monitorSilentResultServiceImpl;
	@Autowired
	private IMonitorSilentOrgResultService monitorSilentOrgResultServiceImpl;
	@Autowired
	private IConnectionHomeDetailService connectionHomeDetailServiceImpl;
	
	private static Log logger = LogFactory.getLog(MonitorSilentResultOrgAction.class);
	
	
	/**
	 * @Title: 安全问题 index页面
	 * @Description:
	 * @author linhb@ucap.com.cn	2016-11-4上午10:19:44
	 * @return
	 */
	public String indexOrg(){
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
	 * 判断是否监测，进行中，已结束时间周期
	 * @author qinjy
	 */
	public MonitorSilentOrgResult getTimePeriod(String siteCode) {
		MonitorSilentOrgResult m = new MonitorSilentOrgResult();
		try {
			MonitorSilentOrgResultRequest mo = new MonitorSilentOrgResultRequest();
			/**老合同信息**/
			List<ContractInfo> crmList = getContractInfoList(siteCode, DateUtils.formatStandardDate(new Date()));
			/**新产品信息**/
			// Integer[] productTypeArr = { CrmProductsType.SECURITY.getCode()
			// };
			// List<CrmProductsResponse> crmList = getCrmProductsList(siteCode,
			// productTypeArr,
			// DateUtils.formatStandardDate(new Date()), null, null);
			// 查询历史合同
			// CrmProductsRequest crmReq = new CrmProductsRequest();
			// crmReq.setSiteCode(siteCode);
			// crmReq.setNotExecuteStatus(-1);
			// crmReq.setProtoContractCode(1); // 不为空的是历史合同 1
			// crmReq.setNowTime(DateUtils.formatStandardDate(new Date()));
			// List<CrmProductsResponse> historyList =
			// crmProductsServiceImpl.historyCrmProductsList(crmReq);
			//
			// crmList.addAll(historyList);
			if (CollectionUtils.isNotEmpty(crmList)) { // 合同是否存在
				ArrayList<QueryOrder> queryOrderList = new ArrayList<QueryOrder>();
				queryOrderList.add(new QueryOrder("scan_date",QueryOrderType.DESC));
				mo.setQueryOrderList(queryOrderList);
				mo.setPageSize(Integer.MAX_VALUE);
				PageVo<MonitorSilentOrgResult> pageDate = monitorSilentOrgResultServiceImpl.query(mo);
				List<MonitorSilentOrgResult> molist = pageDate.getData();
				if(molist != null && molist.size() > 0){
					m = molist.get(0);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return m;
	}
	
	/**
	 * 获取比较后的周期时间
	 * @author qinjy
	 * @return 
	 */
	public Map<String, String> cycleDate(MonitorSilentOrgResult mon, int defeatTime){
		Map<String, String> map = new HashMap<String, String>();
		try {
			String st = null;
			String ed =  null;
			Date sta = null;
			if(mon != null){
				st = mon.getScanDate(); //周期时间
				if (StringUtils.isNotEmpty(st)) {
					sta = DateUtils.parseStandardDate(st);
				}
			}
			if (sta != null) {
				ed = DateUtils.getNextDay(sta, defeatTime);
			}
	      	map.put("st", st);
	      	map.put("ed", ed);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * @Title: 获取 7 天数据  组织单位
	 * @Description:
	 * @author qinjy
	 */
	public void getSevenDatasOrg(){
		String orgType = request.getParameter("orgType");
		String siteCode = getCurrentSiteCode();
		Map<String,Object> outMap=new HashMap<String, Object>();
		try {
			HashMap<String, Object> hashMap = new HashMap<String, Object>();
			hashMap.put("type", DatabaseLinkType.ALL.getCode());
			
			MonitorSilentOrgResult m = new MonitorSilentOrgResult();
			List<MonitorSilentOrgResult>  mList = new ArrayList<MonitorSilentOrgResult>();
			List<Integer> weaklist = new ArrayList<Integer>();
			List<Integer> horselist = new ArrayList<Integer>();
			List<Integer> updatelist = new ArrayList<Integer>();
			List<Integer> darklist = new ArrayList<Integer>();
			List<Integer> outlist = new ArrayList<Integer>();
			List<Integer> risklist = new ArrayList<Integer>();
			List<String> scanTimelist = new ArrayList<String>();
			
			m = getTimePeriod(siteCode);
			
			if(orgType.equals("2")){   // 0 未监测  1 进行中 2 已过期
				Map<String, String> map = cycleDate(m, -7);
				hashMap.put("startDate", map.get("ed"));
				hashMap.put("endDate", map.get("st"));
				hashMap.put("siteCode", siteCode);
				mList =monitorSilentOrgResultServiceImpl.getMonitorSilentList(hashMap);
			}else if(orgType.equals("1")){
				m.setScanDate(DateUtils.getYesterdayStr());
				Map<String, String> map = cycleDate(m, -7);
				hashMap.put("startDate", map.get("ed"));
				hashMap.put("endDate",  DateUtils.getYesterdayStr());  // 获取昨天日期 
				hashMap.put("siteCode", siteCode);
				mList =monitorSilentOrgResultServiceImpl.getMonitorSilentList(hashMap);
			}else if(orgType.equals("0")){
				mList = null;
			}
			
			if(mList != null && mList.size() > 0){
				for (MonitorSilentOrgResult monitorSilentResult2 : mList) {
					//风险值
					risklist.add(monitorSilentResult2.getRiskNum());
					//脆弱性问题数
					weaklist.add(monitorSilentResult2.getFragilitySum());
					//挂马问题数
					horselist.add(monitorSilentResult2.getTrojanSum());
					//变更/篡改问题数
					updatelist.add(monitorSilentResult2.getTamperSum());
					//网站暗链数
					darklist.add(monitorSilentResult2.getDarkChainSum());
					//内容泄漏数
					outlist.add(monitorSilentResult2.getLeakedSum());
					scanTimelist.add(monitorSilentResult2.getScanDate().replaceAll("-", "/"));
				}
			}

			outMap.put("weaklist", weaklist);
			outMap.put("horselist",horselist);
			outMap.put("updatelist",updatelist);
			outMap.put("darklist",darklist);
			outMap.put("outlist",outlist);
			outMap.put("scanTimelist",scanTimelist);
			logger.info("map_list="+outMap);
			outMap.put("success", "true");
			writerPrint(JSONObject.fromObject(outMap).toString());	
		} catch (Exception e) {
			e.printStackTrace();
			outMap.put("success", "false");
			outMap.put("errorMsg", "查询安全问题详情数据异常！");
			writerPrint(JSONObject.fromObject(outMap).toString());
		}
	}
	
	/**
	 * @Title: 获取 30 天数据 组织单位及列表
	 * @Description:
	 * @author qinjy
	 */
	public void getMonthDatasOrg(){
		String terms = request.getParameter("terms");  
		String orgType = request.getParameter("orgType");
		String res = request.getParameter("res");
		String menuType = request.getParameter("menuType");
		String siteCode = getCurrentSiteCode();
		String type = queryDetectionOrgCountByType(menuType, siteCode);
		if(StringUtils.isNotEmpty(terms)){
		}else {
			terms = null;
		}
		Map<String,Object> outMap=new HashMap<String, Object>();
		try {
			//查询组织安全问题
			HashMap<String, Object> seMap = new HashMap<String, Object>();
			seMap.put("type", Integer.valueOf(type));
			
			MonitorSilentOrgResult m = new MonitorSilentOrgResult();
			String yesterday = null;
			MonitorSilentOrgResult mResult = new MonitorSilentOrgResult();
			List<MonitorSilentOrgResult>  mList = new ArrayList<MonitorSilentOrgResult>();
			List<Integer> weaklist = new ArrayList<Integer>();
			List<Integer> horselist = new ArrayList<Integer>();
			List<Integer> updatelist = new ArrayList<Integer>();
			List<Integer> darklist = new ArrayList<Integer>();
			List<Integer> outlist = new ArrayList<Integer>();
			List<Integer> risklist = new ArrayList<Integer>();
			List<String> scanTimelist = new ArrayList<String>();
			
			m = getTimePeriod(siteCode);
			if(orgType.equals("2")){   // 0 未监测  1 进行中 2 已过期
				Map<String, String> map = cycleDate(m, -30);
				seMap.put("startDate", map.get("ed"));
				seMap.put("endDate", map.get("st"));
				seMap.put("siteCode", siteCode);
				mList =monitorSilentOrgResultServiceImpl.getMonitorSilentList(seMap);
				yesterday = map.get("st");  // 结束时间
			}else if(orgType.equals("1")){
				m.setScanDate(DateUtils.getYesterdayStr());
				Map<String, String> map = cycleDate(m, -30);
				seMap.put("startDate", map.get("ed"));
				seMap.put("endDate", DateUtils.getYesterdayStr());
				seMap.put("siteCode", siteCode);
				mList =monitorSilentOrgResultServiceImpl.getMonitorSilentList(seMap);
				MonitorSilentOrgResult mr = new MonitorSilentOrgResult();
				if(mList != null && mList.size() > 0){
					mr = mList.get(mList.size()-1);
				}
				yesterday = mr.getScanDate();  // 结束(昨天)时间
			}else if(orgType.equals("0")){
				mList = null;
			}
			
			if(mList != null && mList.size() > 0){
				for (MonitorSilentOrgResult monitorSilentResult2 : mList) {
					//风险值
					risklist.add(monitorSilentResult2.getRiskNum());
					//脆弱性问题数
					weaklist.add(monitorSilentResult2.getFragilitySum());
					//挂马问题数
					horselist.add(monitorSilentResult2.getTrojanSum());
					//变更/篡改问题数
					updatelist.add(monitorSilentResult2.getTamperSum());
					//网站暗链数
					darklist.add(monitorSilentResult2.getDarkChainSum());
					//内容泄漏数
					outlist.add(monitorSilentResult2.getLeakedSum());
					scanTimelist.add(monitorSilentResult2.getScanDate().replaceAll("-", "/"));
				}
				
				mResult = mList.get(mList.size()-1);
				outMap.put("risk", mResult.getRiskNum());
				outMap.put("weak", mResult.getFragilitySum());
				outMap.put("horse", mResult.getTrojanSum());
				outMap.put("update", mResult.getTamperSum());
				outMap.put("dark", mResult.getDarkChainSum());
				outMap.put("out", mResult.getLeakedSum());
				
				outMap.put("weakSite", mResult.getFragilitySiteSum());
				outMap.put("horseSite", mResult.getTrojanSiteSum());
				outMap.put("updateSite", mResult.getTamperSiteSum());
				outMap.put("darkSite", mResult.getDarkChainSiteSum());
				outMap.put("outSite", mResult.getLeakedSiteSum());
			}else {
				outMap.put("risk", 0);
				outMap.put("weak", 0);
				outMap.put("horse", 0);
				outMap.put("update", 0);
				outMap.put("dark",0);
				outMap.put("out",0);
				
				outMap.put("weakSite",0);
				outMap.put("horseSite", 0);
				outMap.put("updateSite", 0);
				outMap.put("darkSite", 0);
				outMap.put("outSite", 0);
			}
			
			//  列表
			HashMap<String, Object> hashMap = new HashMap<String, Object>();
			List<Object> list = new ArrayList<Object>();
			
			hashMap.put("siteCode", siteCode);
			hashMap.put("terms", terms);
			hashMap.put("res", res);
			if(res.equals("0")){  // 昨天
				hashMap.put("scanDate", DateUtils.getYesterdayStr());
			}else if(res.equals("1")){ // 一周
				hashMap.put("startDate", DateUtils.getNextDay(new Date(), -7));
				hashMap.put("endDate", DateUtils.getYesterdayStr());
			}else if(res.equals("2")){ // 两周
				hashMap.put("startDate", DateUtils.getNextDay(new Date(), -14));
				hashMap.put("endDate", DateUtils.getYesterdayStr());
			}else if(res.equals("3")){  // 一个月
				hashMap.put("startDate", DateUtils.getNextDay(new Date(), -30));
				hashMap.put("endDate", DateUtils.getYesterdayStr());
			}
			
			List<MonitorSilentResultRequest>  mTBList =monitorSilentResultServiceImpl.getMonResMap(hashMap);  	
			if(mTBList != null && mTBList.size() > 0){
				for(MonitorSilentResultRequest monitor : mTBList){
					Map<String,Object> maps=new HashMap<String, Object>();
					if(monitor != null){
						/**老合同信息**/
						List<ContractInfo> crmList = getContractInfoList(siteCode,
								DateUtils.formatStandardDate(new Date()));
						HashMap<String, Object> secMap = connectionHomeDetailServiceImpl.securityServices(crmList,
								monitor.getSiteCode(), 0);
						/**新产品信息**/
						// Integer[] productTypeArr = {
						// CrmProductsType.SECURITY.getCode() };
						// List<CrmProductsResponse> crmList =
						// getCrmProductsList(siteCode, productTypeArr,
						// DateUtils.formatStandardDate(new Date()), null,
						// null);
						// 查询历史合同
						// CrmProductsRequest crmReq = new CrmProductsRequest();
						// crmReq.setSiteCode(siteCode);
						// crmReq.setNotExecuteStatus(-1);
						// crmReq.setProtoContractCode(1); // 不为空的是历史合同 1
						// crmReq.setNowTime(DateUtils.formatStandardDate(new
						// Date()));
						// List<CrmProductsResponse> historyList =
						// crmProductsServiceImpl.historyCrmProductsList(crmReq);
						//
						// crmList.addAll(historyList);
//						HashMap<String, Object> secMap = connectionHomeDetailServiceImpl.crmSecurityServices(crmList,
//								monitor.getSiteCode(), 0);
						
						maps.put("fontColor", secMap.get("fontColor"));
						maps.put("titleName", secMap.get("titleName"));
						maps.put("siteCode", monitor.getSiteCode());
						maps.put("name", monitor.getName());
						maps.put("url", monitor.getUrl());
						maps.put("riskNum", monitor.getRiskNum());
						maps.put("fragilityNum", monitor.getFragilityNum());
						maps.put("trojanNum", monitor.getTrojanNum());
						maps.put("tamperNum", monitor.getTamperNum());
						maps.put("darkNhainNum", monitor.getDarkNhainNum());
						maps.put("leakedNum", monitor.getLeakedNum());
					}
					list.add(maps);
				}
			}
			
			outMap.put("list",list);
			outMap.put("size",mTBList.size());
			outMap.put("scanDate",yesterday);
			outMap.put("risklist", risklist);
			outMap.put("weaklist", weaklist);
			outMap.put("horselist",horselist);
			outMap.put("updatelist",updatelist);
			outMap.put("darklist",darklist);
			outMap.put("outlist",outlist);
			outMap.put("scanTimelist",scanTimelist);
			logger.info("map_list="+outMap);
			outMap.put("success", "true");
			writerPrint(JSONObject.fromObject(outMap).toString());	
		} catch (Exception e) {
			e.printStackTrace();
			outMap.put("success", "false");
			outMap.put("errorMsg", "查询安全问题详情数据异常！");
			writerPrint(JSONObject.fromObject(outMap).toString());
		}
	}
	
	/**
	 * @Title: 获取问题总数
	 * @Description:
	 * @author qinjy
	 */
	public void getOrgQuesData(){
		String orgType = request.getParameter("orgType");
		String siteCode = getCurrentSiteCode();
		Map<String,Object> outMap=new HashMap<String, Object>();
		try {
			//查询组织安全问题
			HashMap<String, Object> hashMap = new HashMap<String, Object>();
			hashMap.put("type", DatabaseLinkType.ALL.getCode());
			hashMap.put("siteCode", siteCode);
			MonitorSilentOrgResult m = null;
			List<MonitorSilentOrgResult>  mList = new ArrayList<MonitorSilentOrgResult>();
			
			m = getTimePeriod(siteCode);
			Map<String, String> map = cycleDate(m, 0);
			
			if(orgType.equals("2")){   // 0 未监测  1 进行中 2 已过期
				hashMap.put("scanDate", map.get("ed"));
				mList =monitorSilentOrgResultServiceImpl.getMonitorSilentList(hashMap);
			}else if(orgType.equals("1")){
		      	hashMap.put("scanDate", DateUtils.getYesterdayStr());
		      	mList =monitorSilentOrgResultServiceImpl.getMonitorSilentList(hashMap);
			}else if(orgType.equals("0")){
				mList = null;
			}
		
			if(mList != null && mList.size() > 0){
				outMap.put("quesSum",mList.get(0).getSilentSum());
				outMap.put("riskNum", mList.get(0).getRiskNum());
			}else {
				outMap.put("quesSum","0");
				outMap.put("riskNum", "0");
			}
			logger.info("outMap="+outMap);
			outMap.put("success", "true");
			writerPrint(JSONObject.fromObject(outMap).toString());	
		} catch (Exception e) {
			e.printStackTrace();
			outMap.put("success", "false");
			outMap.put("errorMsg", "查询安全问题详情数据异常！");
			writerPrint(JSONObject.fromObject(outMap).toString());
		}
	}
	
	/**
	 * @Description: excel 导出
	 * @author	qinjy
	 */
	public void monitorExcle() {
		String terms = request.getParameter("terms");  
		String res = request.getParameter("res");  
		if(StringUtils.isNotEmpty(terms)){
		}else {
			terms = null;
		}
		String siteCode=getCurrentUserInfo().getSiteCode();
		try {
			//封装数据中查询的结果
			ArrayList<Object[]> list = new ArrayList<Object[]>();
			//excel标题
			Object[] obj1 = new Object[] { "网站标识码", "网站名称", "风险值", "脆弱性问题数", "挂马问题数","变更/篡改问题数", "网站暗链数", "内容泄漏数" };
			list.add(obj1);
			String fileName = "安全问题总揽(" + DateUtils.formatStandardDate(new Date()) + ").xls";
			String title = "安全问题总揽";
			
			HashMap<String, Object> hashMap = new HashMap<String, Object>();
			hashMap.put("siteCode", siteCode);
			hashMap.put("terms", terms);
			hashMap.put("res", res);
			if(res.equals("0")){  // 昨天
				hashMap.put("scanDate", DateUtils.getYesterdayStr());
			}else if(res.equals("1")){ // 一周
				hashMap.put("startDate", DateUtils.getNextDay(new Date(), -7));
				hashMap.put("endDate", DateUtils.getYesterdayStr());
			}else if(res.equals("2")){ // 两周
				hashMap.put("startDate", DateUtils.getNextDay(new Date(), -14));
				hashMap.put("endDate", DateUtils.getYesterdayStr());
			}else if(res.equals("3")){  // 一个月
				hashMap.put("startDate", DateUtils.getNextDay(new Date(), -30));
				hashMap.put("endDate", DateUtils.getYesterdayStr());
			}
			List<MonitorSilentResultRequest>  mTBList =monitorSilentResultServiceImpl.getMonResMap(hashMap);  	
			if(mTBList != null && mTBList.size() > 0){
				for(MonitorSilentResultRequest mon : mTBList){
					Object[] obj = new Object[8];
					if(mon != null){
						
						if(StringUtils.isNotEmpty(mon.getSiteCode())){
							obj[0] = mon.getSiteCode();
						}else {
							obj[0] = "";
						}
						if(StringUtils.isNotEmpty(mon.getName())){
							obj[1] = mon.getName();
						}else {
							obj[1] = "";
						}
						if(StringUtils.isNotEmpty(mon.getRiskNum())){
							obj[2] = mon.getRiskNum();
						}else {
							obj[2] = "";
						}
						if(StringUtils.isNotEmpty(mon.getFragilityNum())){
							obj[3] = mon.getFragilityNum();
						}else {
							obj[3] = "";
						}
						if(StringUtils.isNotEmpty(mon.getTrojanNum())){
							obj[4] = mon.getTrojanNum();
						}else {
							obj[4] = "";
						}
						if(StringUtils.isNotEmpty(mon.getTamperNum())){
							obj[5] = mon.getTamperNum();
						}else {
							obj[5] = "";
						}
						if(StringUtils.isNotEmpty(mon.getDarkNhainNum())){
							obj[6] = mon.getDarkNhainNum();
						}else {
							obj[6] = "";
						}
						if(StringUtils.isNotEmpty(mon.getLeakedNum())){
							obj[7] = mon.getLeakedNum();
						}else {
							obj[7] = "";
						}
					}
					list.add(obj);
				}
			}
			ExportExcel.tempReportExcel(fileName, title, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
