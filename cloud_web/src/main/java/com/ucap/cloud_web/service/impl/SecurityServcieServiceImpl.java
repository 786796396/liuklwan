package com.ucap.cloud_web.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.publics.util.page.PageVo;
import com.publics.util.utils.QueryUtils;
import com.ucap.cloud_web.dao.SecurityServcieDao;
import com.ucap.cloud_web.dto.SecurityBlankInfoRequest;
import com.ucap.cloud_web.dto.SecurityServcieRequest;
import com.ucap.cloud_web.dtoResponse.SecurityGuaranteeResponse;
import com.ucap.cloud_web.entity.SecurityServcie;
import com.ucap.cloud_web.service.ISecurityServcieService;


/**


	@Autowired






	@Override
	@Override
	public List<SecurityServcieRequest> queryByGroup(
			Map<String, Object> paramMap) {
		List<SecurityServcieRequest> list=securityServcieDao.queryByGroup(paramMap);
		return list;
	}

	@Override
	public List<SecurityServcieRequest> getProblemNum(
			Map<String, Object> paramMap) {
		return securityServcieDao.getProblemNum(paramMap);
	}

	@Override
	public int queryCountByType(SecurityServcieRequest request) {
		Map<String, Object> param = QueryUtils.getQueryMap(request);
		
		return securityServcieDao.queryCountByType(param);
	}

	@Override
	public List<SecurityGuaranteeResponse> getServiceNumber(HashMap<String, Object> hashMap) {
		return securityServcieDao.getServiceNumber(hashMap);
	}

	@Override
	public List<SecurityServcieRequest> queryServiceInfoByTree(
			HashMap<String, Object> paraMap) {
		return securityServcieDao.queryServiceInfoByTree(paraMap);
	}

	@Override
	public int queryServiceInfoByTreeCount(HashMap<String, Object> paraMap) {
		return securityServcieDao.queryServiceInfoByTreeCount(paraMap);
	}


}
