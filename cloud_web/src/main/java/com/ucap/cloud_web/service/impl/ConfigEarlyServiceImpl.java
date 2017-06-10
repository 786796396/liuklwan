package com.ucap.cloud_web.service.impl;


import java.util.List;
import com.ucap.cloud_web.service.IConfigEarlyService;

import org.springframework.beans.factory.annotation.Autowired;


/**


	@Autowired






	@Override
	public List<ConfigEarly> queryOrgSingleSiteCode(ConfigEarlyRequest request) {
		Map<String, Object> param = QueryUtils.getQueryMap(request);
		return configEarlyDao.queryOrgSingleSiteCode(param);
	}

	@Override
	public List<ConfigEarly> findByMap(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return configEarlyDao.findByMap(paramMap);
	}
