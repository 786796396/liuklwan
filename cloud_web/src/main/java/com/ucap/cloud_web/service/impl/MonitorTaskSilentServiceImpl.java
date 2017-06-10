package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.MonitorTaskSilent;import com.ucap.cloud_web.service.IMonitorTaskSilentService;import com.ucap.cloud_web.dao.MonitorTaskSilentDao;import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.MonitorTaskSilentRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>qingjy<br>* <b>日期：</b> 2016-11-07 16:41:57 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@Servicepublic class MonitorTaskSilentServiceImpl implements IMonitorTaskSilentService {


	@Autowired	private MonitorTaskSilentDao monitorTaskSilentDao;	@Override	public int add(MonitorTaskSilent monitorTaskSilent){		return monitorTaskSilentDao.add(monitorTaskSilent);	}
	@Override	public MonitorTaskSilent get(Integer id){		return monitorTaskSilentDao.get(id);	}
	@Override	public void update(MonitorTaskSilent monitorTaskSilent){		monitorTaskSilentDao.update(monitorTaskSilent);	}
	@Override	public void delete(Integer id){		monitorTaskSilentDao.delete(id);	}
	@Override	public PageVo<MonitorTaskSilent> query(MonitorTaskSilentRequest request) {		List<MonitorTaskSilent> monitorTaskSilent = queryList(request);		PageVo<MonitorTaskSilent> pv = new PageVo<MonitorTaskSilent>();		int count = queryCount(request);		pv.setData(monitorTaskSilent);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(MonitorTaskSilentRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return monitorTaskSilentDao.queryCount(param);	}
	@Override	public List<MonitorTaskSilent> queryList(MonitorTaskSilentRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);
		if(request.getContractIds()!=null && request.getContractIds().size()>0){
			param.put("contractIds", request.getContractIds());
		}		List<MonitorTaskSilent> list = monitorTaskSilentDao.query(param);		return list;	}
}

