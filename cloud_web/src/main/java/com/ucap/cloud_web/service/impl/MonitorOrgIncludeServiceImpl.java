package com.ucap.cloud_web.service.impl;


import java.util.HashMap;
import java.util.List;import java.util.Map;

import com.ucap.cloud_web.entity.JcVisitOrg;
import com.ucap.cloud_web.entity.MonitorOrgInclude;import com.ucap.cloud_web.service.IMonitorOrgIncludeService;import com.ucap.cloud_web.dao.MonitorOrgIncludeDao;import org.springframework.stereotype.Service;import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.MonitorOrgIncludeRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>liujc<br>* <b>日期：</b> 2016-11-02 11:00:54 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@Servicepublic class MonitorOrgIncludeServiceImpl implements IMonitorOrgIncludeService {


	@Autowired	private MonitorOrgIncludeDao monitorOrgIncludeDao;	@Override	public int add(MonitorOrgInclude monitorOrgInclude){		return monitorOrgIncludeDao.add(monitorOrgInclude);	}
	@Override	public MonitorOrgInclude get(Integer id){		return monitorOrgIncludeDao.get(id);	}
	@Override	public void update(MonitorOrgInclude monitorOrgInclude){		monitorOrgIncludeDao.update(monitorOrgInclude);	}
	@Override	public void delete(Integer id){		monitorOrgIncludeDao.delete(id);	}
	@Override	public PageVo<MonitorOrgInclude> query(MonitorOrgIncludeRequest request) {		List<MonitorOrgInclude> monitorOrgInclude = queryList(request);		PageVo<MonitorOrgInclude> pv = new PageVo<MonitorOrgInclude>();		int count = queryCount(request);		pv.setData(monitorOrgInclude);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(MonitorOrgIncludeRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return monitorOrgIncludeDao.queryCount(param);	}
	@Override	public List<MonitorOrgInclude> queryList(MonitorOrgIncludeRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<MonitorOrgInclude> list = monitorOrgIncludeDao.query(param);		return list;	}
	@Override
	public String queryMaxDate(HashMap<String, Object> map){
		return monitorOrgIncludeDao.queryMaxDate(map);
	}

	@Override
	public List<MonitorOrgInclude> getOrgData(MonitorOrgIncludeRequest rRequest) {
		Map<String, Object> param = QueryUtils.getQueryMap(rRequest);
		List<MonitorOrgInclude> list = monitorOrgIncludeDao.getOrgData(param);
		return list;
	}}

