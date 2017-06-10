package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.MonitorFragility;import com.ucap.cloud_web.service.IMonitorFragilityService;import com.ucap.cloud_web.dao.MonitorFragilityDao;import org.springframework.stereotype.Service;import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.MonitorFragilityRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-11-04 11:24:50 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@Servicepublic class MonitorFragilityServiceImpl implements IMonitorFragilityService {


	@Autowired	private MonitorFragilityDao monitorFragilityDao;	@Override	public int add(MonitorFragility monitorFragility){		return monitorFragilityDao.add(monitorFragility);	}
	@Override	public MonitorFragility get(Integer id){		return monitorFragilityDao.get(id);	}
	@Override	public void update(MonitorFragility monitorFragility){		monitorFragilityDao.update(monitorFragility);	}
	@Override	public void delete(Integer id){		monitorFragilityDao.delete(id);	}
	@Override	public PageVo<MonitorFragility> query(MonitorFragilityRequest request) {		List<MonitorFragility> monitorFragility = queryList(request);		PageVo<MonitorFragility> pv = new PageVo<MonitorFragility>();		int count = queryCount(request);		pv.setData(monitorFragility);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(MonitorFragilityRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return monitorFragilityDao.queryCount(param);	}
	@Override	public List<MonitorFragility> queryList(MonitorFragilityRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);
		if(request.getStartEndDates()!=null && request.getStartEndDates().size()>0){
			param.put("startEndDates", request.getStartEndDates());
		}		List<MonitorFragility> list = monitorFragilityDao.query(param);		return list;	}
}

