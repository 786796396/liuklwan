package com.ucap.cloud_web.service.impl;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.publics.util.page.PageVo;
import com.publics.util.page.QueryOrder;
import com.publics.util.page.QueryOrderType;
import com.publics.util.utils.DateUtils;
import com.publics.util.utils.QueryUtils;
import com.ucap.cloud_web.constant.QueueType;
import com.ucap.cloud_web.dao.ConnectionHomeDetailDao;
import com.ucap.cloud_web.dto.ConnectionHomeDetailRequest;
import com.ucap.cloud_web.dto.MonitorTaskSilentRequest;
import com.ucap.cloud_web.entity.ConnectionHomeDetail;
import com.ucap.cloud_web.entity.ContractInfo;
import com.ucap.cloud_web.entity.DatabaseInfo;
import com.ucap.cloud_web.entity.MonitorTaskSilent;
import com.ucap.cloud_web.service.IConnectionHomeDetailService;
import com.ucap.cloud_web.service.IDatabaseInfoService;
import com.ucap.cloud_web.service.IMonitorTaskSilentService;


/*** <br>* <b>作者：</b>Nora<br>* <b>日期：</b> 2015-10-29 11:19:33 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/@Service
public class ConnectionHomeDetailServiceImpl implements IConnectionHomeDetailService {


	@Autowired	private ConnectionHomeDetailDao connectionHomeDetailDao;
	@Autowired
	private IMonitorTaskSilentService monitorTaskSilentServiceImpl;
	@Autowired
	private IDatabaseInfoService databaseInfoServiceImpl;	@Override	public void add(ConnectionHomeDetail connectionHomeDetail){		connectionHomeDetailDao.add(connectionHomeDetail);	}
	@Override	public ConnectionHomeDetail get(Integer id){		return connectionHomeDetailDao.get(id);	}
	@Override	public void update(ConnectionHomeDetail connectionHomeDetail){		connectionHomeDetailDao.update(connectionHomeDetail);	}
	@Override	public void delete(Integer id){		connectionHomeDetailDao.delete(id);	}
	@Override	public PageVo<ConnectionHomeDetail> query(ConnectionHomeDetailRequest request) {		List<ConnectionHomeDetail> connectionHomeDetail = queryList(request);		PageVo<ConnectionHomeDetail> pv = new PageVo<ConnectionHomeDetail>();		int count = queryCount(request);		pv.setData(connectionHomeDetail);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(ConnectionHomeDetailRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return connectionHomeDetailDao.queryCount(param);	}
	@Override	public List<ConnectionHomeDetail> queryList(ConnectionHomeDetailRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<ConnectionHomeDetail> list = connectionHomeDetailDao.query(param);		return list;	}

	@Override
	public List<ConnectionHomeDetail> queryListByScanDate(
			ConnectionHomeDetailRequest request) {
		Map<String, Object> param = QueryUtils.getQueryMap(request);
		List<ConnectionHomeDetail> list = connectionHomeDetailDao.query(param);
		return list;
	}

	@Override
	public HashMap<String, Object> securityServices(List<ContractInfo> contractList, String siteCode, int silentNum) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			if (contractList != null && contractList.size() > 0) { // 合同是否存在
				List<Integer> contractIds = new ArrayList<Integer>();
				Integer conId = contractList.get(0).getId(); // 合同id
				contractIds.add(conId);
				MonitorTaskSilentRequest req = new MonitorTaskSilentRequest();
				req.setSiteCode(siteCode);
				req.setContractIds(contractIds);
				req.setPageSize(Integer.MAX_VALUE);
				req.setNowTime(DateUtils.formatStandardDateTime(new Date()));
				List<QueryOrder> queryOrderOrgList = new ArrayList<QueryOrder>();
				QueryOrder queryOrder = new QueryOrder("end_date", QueryOrderType.DESC);
				queryOrderOrgList.add(queryOrder);
				req.setQueryOrderList(queryOrderOrgList);
				List<MonitorTaskSilent> monList = monitorTaskSilentServiceImpl.queryList(req);
				MonitorTaskSilent mon = null;
				if (monList != null && monList.size() > 0) { // 任务是否存在(当前时间查)
					mon = monList.get(0);
				} else {
					req.setNowTime(null);
					req.setEndTime(DateUtils.formatStandardDate(new Date())); // 结束时间
					List<MonitorTaskSilent> monTaskList = monitorTaskSilentServiceImpl.queryList(req);
					if (monTaskList != null && monTaskList.size() > 0) { // 任务是否存在(小于当前时间的最近时间)
						mon = monTaskList.get(0);
					} else {
						map.put("fontColor", "#08c"); // 颜色
						map.put("titleName", "未开通安全监测服务或服务已到期"); // 弹框
						map.put("silentNum", "未监测"); // 安全问题个数
					}
				}
				if (mon != null) {
					Date sta = mon.getStartDate();
					Date end = mon.getEndDate();
					if (sta != null && end != null) {
						String da = DateUtils.formatStandardDate(new Date()); // 当前时间
						DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
						Date curDate = dateFormat.parse(da);
						int i = curDate.compareTo(sta);
						int j = curDate.compareTo(end);
						if (i < 0) { // 未开始
							map.put("fontColor", "#08c"); // 颜色
							map.put("titleName", "未开通安全监测服务或服务已到期"); // 弹框
							map.put("silentNum", "未监测");
						} else if ((i == 0 || i > 0) && (j < 0 || j == 0)) { // 进行中
							map.put("fontColor", "#08c"); // 颜色
							map.put("titleName", "已监测"); // 弹框
							map.put("silentNum", silentNum); // 安全问题个数
						} else if (j > 0) { // 以结束
							map.put("fontColor", "red"); // 颜色
							map.put("titleName", "安全监测服务已到期，显示最后一天问题数"); // 弹框
							map.put("silentNum", silentNum); // 安全问题个数
						}
					} else {
						map.put("fontColor", "#08c"); // 颜色
						map.put("titleName", "未开通安全监测服务或服务已到期"); // 弹框
						map.put("silentNum", "未知"); // 安全问题个数
					}
				} else {
					map.put("fontColor", "#08c"); // 颜色
					map.put("titleName", "未开通安全监测服务或服务已到期"); // 弹框
					map.put("silentNum", "未知"); // 安全问题个数
				}
			} else {
				map.put("fontColor", "#08c"); // 颜色
				map.put("titleName", "未开通安全监测服务或服务已到期"); // 弹框
				map.put("silentNum", "未监测"); // 安全问题个数
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	/** 新产品信息 **/
	/*@Override
	public HashMap<String, Object> crmSecurityServices(List<CrmProductsResponse> list, String siteCode,
			int silentNum) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			if (CollectionUtils.isNotEmpty(list)) { // 合同是否存在
				List<Integer> contractIds = new ArrayList<Integer>();
				Integer conId = list.get(0).getId(); // 合同id
				contractIds.add(conId);
				MonitorTaskSilentRequest req = new MonitorTaskSilentRequest();
				req.setSiteCode(siteCode);
				req.setContractIds(contractIds);
				req.setPageSize(Integer.MAX_VALUE);
				req.setNowTime(DateUtils.formatStandardDateTime(new Date()));
				List<QueryOrder> queryOrderOrgList=new ArrayList<QueryOrder>();
				QueryOrder queryOrder=new QueryOrder("end_date",QueryOrderType.DESC);
				queryOrderOrgList.add(queryOrder);
				req.setQueryOrderList(queryOrderOrgList);
				List<MonitorTaskSilent> monList = monitorTaskSilentServiceImpl.queryList(req);
				MonitorTaskSilent mon = null;
					if(monList != null && monList.size() > 0){  //  任务是否存在(当前时间查)
						mon = monList.get(0);
					}else {
						req.setNowTime(null);
						req.setEndTime(DateUtils.formatStandardDate(new Date()));  // 结束时间
						List<MonitorTaskSilent> monTaskList = monitorTaskSilentServiceImpl.queryList(req);
						if(monTaskList != null && monTaskList.size() > 0){   //  任务是否存在(小于当前时间的最近时间)
							mon = monTaskList.get(0);
						}else {
							map.put("fontColor", "#08c");  //  颜色
							map.put("titleName", "未开通安全监测服务或服务已到期");  //  弹框
							map.put("silentNum", "未监测");  //  安全问题个数
						}
					}
					if(mon != null){
						Date sta = mon.getStartDate();
						Date end = mon.getEndDate();
						if(sta != null && end != null){
							String da = DateUtils.formatStandardDate(new Date());  // 当前时间
							DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
							Date curDate = dateFormat.parse(da);
							int i = curDate.compareTo(sta); 
							int j = curDate.compareTo(end);
							if(i < 0){  //  未开始  
								map.put("fontColor", "#08c");  //  颜色
								map.put("titleName", "未开通安全监测服务或服务已到期");  //  弹框
								map.put("silentNum", "未监测");
							}else if((i == 0 || i > 0) && (j < 0 || j == 0)){  //  进行中
								map.put("fontColor", "#08c");  //  颜色
								map.put("titleName", "已监测");  //  弹框
								map.put("silentNum", silentNum);  //  安全问题个数
							}else if(j > 0){  //  以结束
								map.put("fontColor", "red");  //  颜色
								map.put("titleName", "安全监测服务已到期，显示最后一天问题数");  //  弹框
								map.put("silentNum", silentNum);  //  安全问题个数
							}
						}else {
							map.put("fontColor", "#08c");  //  颜色
							map.put("titleName", "未开通安全监测服务或服务已到期");  //  弹框
							map.put("silentNum", "未知");  //  安全问题个数
						}
					}else {
						map.put("fontColor", "#08c");  //  颜色
						map.put("titleName", "未开通安全监测服务或服务已到期");  //  弹框
						map.put("silentNum", "未知");  //  安全问题个数
					}
				}else {
					map.put("fontColor", "#08c");  //  颜色
					map.put("titleName", "未开通安全监测服务或服务已到期");  //  弹框
					map.put("silentNum", "未监测");  //  安全问题个数
				}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}*/

	@Override
	public String queueTime(String siteCode) {
		String que = null;
		try {
			DatabaseInfo data = new DatabaseInfo();
			data = databaseInfoServiceImpl.findByDatabaseInfoCode(siteCode);
			if(data != null){
				int q = data.getQueue();
				que = q == 1 ? QueueType.Queue_5.getName() : q == 3 ? QueueType.Queue_15.getName() : q == 6 ? QueueType.Queue_day.getName() : "";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return que;
	}

	@Override
	public List<ConnectionHomeDetailRequest> queryConnByMap(
			Map<String, Object> paraMap) {
		return connectionHomeDetailDao.queryConnByMap(paraMap);
	}
}

