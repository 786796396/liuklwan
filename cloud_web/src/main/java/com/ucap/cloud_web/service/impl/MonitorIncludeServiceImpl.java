package com.ucap.cloud_web.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.publics.util.page.PageVo;
import com.publics.util.utils.QueryUtils;
import com.ucap.cloud_web.dao.MonitorIncludeDao;
import com.ucap.cloud_web.dto.MonitorIncludeRequest;
import com.ucap.cloud_web.dtoResponse.SearchEngineResponse;
import com.ucap.cloud_web.entity.MonitorInclude;
import com.ucap.cloud_web.service.IMonitorIncludeService;


/*** <br>* <b>作者：</b>liujc<br>* <b>日期：</b> 2016-11-01 16:07:23 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@Servicepublic class MonitorIncludeServiceImpl implements IMonitorIncludeService {


	@Autowired	private MonitorIncludeDao monitorIncludeDao;	@Override	public int add(MonitorInclude monitorInclude){		return monitorIncludeDao.add(monitorInclude);	}
	@Override	public MonitorInclude get(Integer id){		return monitorIncludeDao.get(id);	}
	@Override	public void update(MonitorInclude monitorInclude){		monitorIncludeDao.update(monitorInclude);	}
	@Override	public void delete(Integer id){		monitorIncludeDao.delete(id);	}
	@Override	public PageVo<MonitorInclude> query(MonitorIncludeRequest request) {		List<MonitorInclude> monitorInclude = queryList(request);		PageVo<MonitorInclude> pv = new PageVo<MonitorInclude>();		int count = queryCount(request);		pv.setData(monitorInclude);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(MonitorIncludeRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return monitorIncludeDao.queryCount(param);	}
	@Override	public List<MonitorInclude> queryList(MonitorIncludeRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<MonitorInclude> list = monitorIncludeDao.query(param);		return list;	}

	@Override
	public String queryTBMaxDate(Map<String, Object> map) {
		return monitorIncludeDao.queryTBMaxDate(map);
	}

	@Override
	public List<MonitorInclude> queryNatives(MonitorIncludeRequest sRequest) {
		Map<String, Object> param = QueryUtils.getQueryMap(sRequest);
		List<MonitorInclude> list = monitorIncludeDao.queryNatives(param);
		return list;
	}

	@Override
	public List<MonitorInclude> querySiteData(MonitorIncludeRequest sRequest) {
		Map<String, Object> param = QueryUtils.getQueryMap(sRequest);
		List<MonitorInclude> list = monitorIncludeDao.querySiteData(param);
		return list;
	}

	@Override
	public List<SearchEngineResponse> getSearchEngineList(HashMap<String, Object> hashMap) {
		return monitorIncludeDao.getSearchEngineList(hashMap);
	}
}

