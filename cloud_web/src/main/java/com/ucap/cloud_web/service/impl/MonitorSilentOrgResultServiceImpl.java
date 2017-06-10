package com.ucap.cloud_web.service.impl;


import java.util.HashMap;
import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.MonitorSilentOrgResult;import com.ucap.cloud_web.service.IMonitorSilentOrgResultService;import com.ucap.cloud_web.dao.MonitorSilentOrgResultDao;import org.springframework.stereotype.Service;import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.MonitorSilentOrgResultRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-11-04 11:24:50 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@Servicepublic class MonitorSilentOrgResultServiceImpl implements IMonitorSilentOrgResultService {


	@Autowired	private MonitorSilentOrgResultDao monitorSilentOrgResultDao;	@Override	public int add(MonitorSilentOrgResult monitorSilentOrgResult){		return monitorSilentOrgResultDao.add(monitorSilentOrgResult);	}
	@Override	public MonitorSilentOrgResult get(Integer id){		return monitorSilentOrgResultDao.get(id);	}
	@Override	public void update(MonitorSilentOrgResult monitorSilentOrgResult){		monitorSilentOrgResultDao.update(monitorSilentOrgResult);	}
	@Override	public void delete(Integer id){		monitorSilentOrgResultDao.delete(id);	}
	@Override	public PageVo<MonitorSilentOrgResult> query(MonitorSilentOrgResultRequest request) {		List<MonitorSilentOrgResult> monitorSilentOrgResult = queryList(request);		PageVo<MonitorSilentOrgResult> pv = new PageVo<MonitorSilentOrgResult>();		int count = queryCount(request);		pv.setData(monitorSilentOrgResult);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(MonitorSilentOrgResultRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return monitorSilentOrgResultDao.queryCount(param);	}
	@Override	public List<MonitorSilentOrgResult> queryList(MonitorSilentOrgResultRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<MonitorSilentOrgResult> list = monitorSilentOrgResultDao.query(param);		return list;	}

	@Override
	public List<MonitorSilentOrgResult> getMonitorSilentList(HashMap<String, Object> hashMap) {
		return monitorSilentOrgResultDao.getMonitorSilentList(hashMap);
	}
}

