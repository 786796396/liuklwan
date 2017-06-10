package com.ucap.cloud_web.service.impl;


import java.util.HashMap;
import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.MonitorSilentResult;import com.ucap.cloud_web.service.IMonitorSilentResultService;import com.ucap.cloud_web.dao.MonitorSilentResultDao;import org.springframework.stereotype.Service;import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.MonitorSilentResultRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-11-04 11:24:50 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@Servicepublic class MonitorSilentResultServiceImpl implements IMonitorSilentResultService {


	@Autowired	private MonitorSilentResultDao monitorSilentResultDao;	@Override	public int add(MonitorSilentResult monitorSilentResult){		return monitorSilentResultDao.add(monitorSilentResult);	}
	@Override	public MonitorSilentResult get(Integer id){		return monitorSilentResultDao.get(id);	}
	@Override	public void update(MonitorSilentResult monitorSilentResult){		monitorSilentResultDao.update(monitorSilentResult);	}
	@Override	public void delete(Integer id){		monitorSilentResultDao.delete(id);	}
	@Override	public PageVo<MonitorSilentResult> query(MonitorSilentResultRequest request) {		List<MonitorSilentResult> monitorSilentResult = queryList(request);		PageVo<MonitorSilentResult> pv = new PageVo<MonitorSilentResult>();		int count = queryCount(request);		pv.setData(monitorSilentResult);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(MonitorSilentResultRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return monitorSilentResultDao.queryCount(param);	}
	@Override	public List<MonitorSilentResult> queryList(MonitorSilentResultRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<MonitorSilentResult> list = monitorSilentResultDao.query(param);		return list;	}

	@Override
	public List<MonitorSilentResultRequest> getMonResMap(HashMap<String, Object> hashMap) {
		return monitorSilentResultDao.getMonResMap(hashMap);
	}
}

