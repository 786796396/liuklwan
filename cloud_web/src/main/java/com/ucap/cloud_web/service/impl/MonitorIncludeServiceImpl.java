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


/**


	@Autowired







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

