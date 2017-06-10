package com.ucap.cloud_web.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.publics.util.page.PageVo;
import com.publics.util.utils.QueryUtils;
import com.ucap.cloud_web.dao.SecurityResponseDao;
import com.ucap.cloud_web.dto.SecurityBlankInfoRequest;
import com.ucap.cloud_web.dto.SecurityResponseRequest;
import com.ucap.cloud_web.dtoResponse.SecurityGuaranteeResponse;
import com.ucap.cloud_web.entity.SecurityResponse;
import com.ucap.cloud_web.service.ISecurityResponseService;


/**


	@Autowired







	@Override
	@Override
	public List<SecurityResponseRequest> queryByGroup(
			Map<String, Object> paramMap) {
		List<SecurityResponseRequest> list=securityResponseDao.queryByGroup(paramMap);
		return list;
	}

	@Override
	public List<SecurityResponseRequest> getProblemNum(
			Map<String, Object> paramMap) {
		return securityResponseDao.getProblemNum(paramMap);
	}

	@Override
	public List<SecurityResponseRequest> queryListByChannelName(
			SecurityResponseRequest request) {
		Map<String, Object> param = QueryUtils.getQueryMap(request);
		return securityResponseDao.queryListByChannelName(param);
	}

	@Override
	public List<SecurityResponseRequest> queryResponseNum(
			Map<String, Object> paramMap) {
		return securityResponseDao.queryResponseNum(paramMap);
	}

	@Override
	public List<SecurityGuaranteeResponse> getResponseNumber(HashMap<String, Object> hashMap) {
		return securityResponseDao.getResponseNumber(hashMap);
	}

	@Override
	public List<SecurityResponseRequest> queryInfoByTree(
			HashMap<String, Object> paraMap) {
		return securityResponseDao.queryInfoByTree(paraMap);
	}

	@Override
	public int queryInfoByTreeCount(HashMap<String, Object> paraMap) {
		return securityResponseDao.queryInfoByTreeCount(paraMap);
	}

}
