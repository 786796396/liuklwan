package com.ucap.cloud_web.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.publics.util.page.PageVo;
import com.publics.util.utils.QueryUtils;
import com.ucap.cloud_web.dao.JcVisitSitecodeDao;
import com.ucap.cloud_web.dto.JcVisitSitecodeRequest;
import com.ucap.cloud_web.dtoResponse.JcVisitSitecodeResponse;
import com.ucap.cloud_web.entity.JcVisitSitecode;
import com.ucap.cloud_web.service.IJcVisitSitecodeService;


/**


	@Autowired







	@Override
	public List<JcVisitSitecode> querySiteCodes(JcVisitSitecodeRequest sRequest) {
		Map<String, Object> param = QueryUtils.getQueryMap(sRequest);
		List<JcVisitSitecode> list = jcVisitSitecodeDao.querySiteCodes(param);
		return list;
	}

	@Override
	public List<JcVisitSitecode> queryOrganizations(JcVisitSitecodeRequest sRequest) {
		Map<String, Object> param = QueryUtils.getQueryMap(sRequest);
		List<JcVisitSitecode> list = jcVisitSitecodeDao.queryOrganizations(param);
		return list;
	}

	@Override
	public List<JcVisitSitecode> queryNatives(JcVisitSitecodeRequest sRequest) {
		Map<String, Object> param = QueryUtils.getQueryMap(sRequest);
		List<JcVisitSitecode> list = jcVisitSitecodeDao.queryNatives(param);
		return list;
	}

	@Override
	public List<JcVisitSitecodeResponse> getSiteVisitsList(HashMap<String, Object> hashMap) {
		return jcVisitSitecodeDao.getSiteVisitsList(hashMap);
	}

