package com.ucap.cloud_web.service.impl;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.publics.util.page.PageVo;
import com.publics.util.utils.QueryUtils;
import com.ucap.cloud_web.dao.BigDataDailyDao;
import com.ucap.cloud_web.dto.BigDataDailyRequest;
import com.ucap.cloud_web.dtoResponse.BigDataDailyResponse;
import com.ucap.cloud_web.entity.BigDataDaily;
import com.ucap.cloud_web.service.IBigDataDailyService;


/**


	@Autowired






	@Override
	public List<BigDataDailyResponse> queryOrganizations(BigDataDailyRequest sRequest) {
		Map<String, Object> param = QueryUtils.getQueryMap(sRequest);
		List<BigDataDailyResponse> list = bigDataDailyDao.queryOrganizations(param);
		return list;
	}

	@Override
	public List<BigDataDailyResponse> queryNatives(BigDataDailyRequest sRequest) {
		Map<String, Object> param = QueryUtils.getQueryMap(sRequest);
		List<BigDataDailyResponse> list = bigDataDailyDao.queryNatives(param);
		return list;
	}
}
