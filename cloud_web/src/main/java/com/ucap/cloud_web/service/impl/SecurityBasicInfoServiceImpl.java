package com.ucap.cloud_web.service.impl;


import java.util.HashMap;
import java.util.List;


/**


	@Autowired







	@Override
	public List<SecurityBasicInfoRequest> getProblemNum(
			HashMap<String, Object> hashMap) {
		return securityBasicInfoDao.getProblemNum(hashMap);
	}

	@Override
	public List<SecurityBasicInfoRequest> queryBasicNum(Map<String, Object> paramMap) {
		return securityBasicInfoDao.queryBasicNum(paramMap);
	}

