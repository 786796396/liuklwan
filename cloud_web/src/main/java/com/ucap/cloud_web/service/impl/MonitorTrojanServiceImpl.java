package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.MonitorTrojan;import com.ucap.cloud_web.service.IMonitorTrojanService;import com.ucap.cloud_web.dao.MonitorTrojanDao;import org.springframework.stereotype.Service;import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.MonitorTrojanRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-11-04 11:24:51 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@Servicepublic class MonitorTrojanServiceImpl implements IMonitorTrojanService {


	@Autowired	private MonitorTrojanDao monitorTrojanDao;	@Override	public int add(MonitorTrojan monitorTrojan){		return monitorTrojanDao.add(monitorTrojan);	}
	@Override	public MonitorTrojan get(Integer id){		return monitorTrojanDao.get(id);	}
	@Override	public void update(MonitorTrojan monitorTrojan){		monitorTrojanDao.update(monitorTrojan);	}
	@Override	public void delete(Integer id){		monitorTrojanDao.delete(id);	}
	@Override	public PageVo<MonitorTrojan> query(MonitorTrojanRequest request) {		List<MonitorTrojan> monitorTrojan = queryList(request);		PageVo<MonitorTrojan> pv = new PageVo<MonitorTrojan>();		int count = queryCount(request);		pv.setData(monitorTrojan);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(MonitorTrojanRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return monitorTrojanDao.queryCount(param);	}
	@Override	public List<MonitorTrojan> queryList(MonitorTrojanRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);
		if(request.getStartEndDates()!=null && request.getStartEndDates().size()>0){
			param.put("startEndDates", request.getStartEndDates());
		}		List<MonitorTrojan> list = monitorTrojanDao.query(param);		return list;	}
}

