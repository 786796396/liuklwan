package com.ucap.cloud_web.service.impl;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.publics.util.page.PageVo;
import com.publics.util.utils.QueryUtils;
import com.ucap.cloud_web.dao.BigOrgDailyDao;
import com.ucap.cloud_web.dto.BigOrgDailyRequest;
import com.ucap.cloud_web.dtoResponse.BigOrgDailyResponse;
import com.ucap.cloud_web.entity.BigOrgDaily;
import com.ucap.cloud_web.service.IBigOrgDailyService;


/**


	@Autowired






	@Override
	public List<BigOrgDailyResponse> getOrgData(BigOrgDailyRequest request) {
		Map<String, Object> param = QueryUtils.getQueryMap(request);
		List<BigOrgDailyResponse> list = bigOrgDailyDao.getOrgData(param);
		return list;
	}
