package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.MonitorLeaked;import com.ucap.cloud_web.service.IMonitorLeakedService;import com.ucap.cloud_web.dao.MonitorLeakedDao;import org.springframework.stereotype.Service;import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.MonitorLeakedRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-11-04 11:24:50 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@Servicepublic class MonitorLeakedServiceImpl implements IMonitorLeakedService {


	@Autowired	private MonitorLeakedDao monitorLeakedDao;	@Override	public int add(MonitorLeaked monitorLeaked){		return monitorLeakedDao.add(monitorLeaked);	}
	@Override	public MonitorLeaked get(Integer id){		return monitorLeakedDao.get(id);	}
	@Override	public void update(MonitorLeaked monitorLeaked){		monitorLeakedDao.update(monitorLeaked);	}
	@Override	public void delete(Integer id){		monitorLeakedDao.delete(id);	}
	@Override	public PageVo<MonitorLeaked> query(MonitorLeakedRequest request) {		List<MonitorLeaked> monitorLeaked = queryList(request);		PageVo<MonitorLeaked> pv = new PageVo<MonitorLeaked>();		int count = queryCount(request);		pv.setData(monitorLeaked);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(MonitorLeakedRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return monitorLeakedDao.queryCount(param);	}
	@Override	public List<MonitorLeaked> queryList(MonitorLeakedRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);
		if(request.getStartEndDates()!=null && request.getStartEndDates().size()>0){
			param.put("startEndDates", request.getStartEndDates());
		}		List<MonitorLeaked> list = monitorLeakedDao.query(param);		return list;	}
}

