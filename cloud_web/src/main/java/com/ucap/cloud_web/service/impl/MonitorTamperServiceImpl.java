package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.MonitorTamper;import com.ucap.cloud_web.service.IMonitorTamperService;import com.ucap.cloud_web.dao.MonitorTamperDao;import org.springframework.stereotype.Service;import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.MonitorTamperRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-11-04 11:24:51 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@Servicepublic class MonitorTamperServiceImpl implements IMonitorTamperService {


	@Autowired	private MonitorTamperDao monitorTamperDao;	@Override	public int add(MonitorTamper monitorTamper){		return monitorTamperDao.add(monitorTamper);	}
	@Override	public MonitorTamper get(Integer id){		return monitorTamperDao.get(id);	}
	@Override	public void update(MonitorTamper monitorTamper){		monitorTamperDao.update(monitorTamper);	}
	@Override	public void delete(Integer id){		monitorTamperDao.delete(id);	}
	@Override	public PageVo<MonitorTamper> query(MonitorTamperRequest request) {		List<MonitorTamper> monitorTamper = queryList(request);		PageVo<MonitorTamper> pv = new PageVo<MonitorTamper>();		int count = queryCount(request);		pv.setData(monitorTamper);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(MonitorTamperRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return monitorTamperDao.queryCount(param);	}
	@Override	public List<MonitorTamper> queryList(MonitorTamperRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);
		if(request.getStartEndDates()!=null && request.getStartEndDates().size()>0){
			param.put("startEndDates", request.getStartEndDates());
		}		List<MonitorTamper> list = monitorTamperDao.query(param);		return list;	}
}

