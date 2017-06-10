package com.ucap.cloud_web.service.impl;


import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


/**


	@Autowired







	@Override
	public List<SecurityBlankDetailRequest> queryByGroup(
			Map<String, Object> paramMap) {
		List<SecurityBlankDetailRequest> list = securityBlankDetailDao.queryByGroup(paramMap);
		return list;
	}

	@Override
	public List<SecurityBlankDetailRequest> getBlankNum(
			HashMap<String, Object> hashMap) {
		return securityBlankDetailDao.getBlankNum(hashMap);
	}

	@Override
	public List<SecurityBlankDetailRequest> queryBlankNum(
			Map<String, Object> paramMap) {
		return securityBlankDetailDao.queryBlankNum(paramMap);
	}

	@Override
	public List<SecurityGuaranteeResponse> getBlankNumber(
			HashMap<String, Object> map) {
		return securityBlankDetailDao.getBlankNumber(map);
	}

	@Override
	public List<SecurityBlankDetailRequest> queryBlankInfoByTree(
			HashMap<String, Object> paraMap) {
		return securityBlankDetailDao.queryBlankInfoByTree(paraMap);
	}

	@Override
	public int queryBlankInfoByTreeCount(HashMap<String, Object> paraMap) {
		return securityBlankDetailDao.queryBlankInfoByTreeCount(paraMap);
	}
	
	

