package com.ucap.cloud_web.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.publics.util.page.PageVo;
import com.publics.util.utils.QueryUtils;
import com.ucap.cloud_web.dao.SecurityBlankInfoDao;
import com.ucap.cloud_web.dto.SecurityBlankInfoRequest;
import com.ucap.cloud_web.dtoResponse.SecurityGuaranteeResponse;
import com.ucap.cloud_web.entity.SecurityBlankInfo;
import com.ucap.cloud_web.service.ISecurityBlankInfoService;


/**


	@Autowired







	@Override
	public List<SecurityBlankInfoRequest> queryByMap(
			Map<String, Object> paramMap) {
		List<SecurityBlankInfoRequest> list = securityBlankInfoDao.queryByMap(paramMap);
		return list;
	}

	@Override
	public List<SecurityBlankInfoRequest> getBlankNum(Map<String, Object> map) {
		return securityBlankInfoDao.getBlankNum(map);
	}

	@Override
	public List<SecurityBlankInfoRequest> queryBlankNum(Map<String, Object> paramMap) {
		return securityBlankInfoDao.queryBlankNum(paramMap);
	}

	@Override
	public List<SecurityGuaranteeResponse> getBlankNumber(HashMap<String, Object> hashMap) {
		return securityBlankInfoDao.getBlankNumber(hashMap);
	}
	
	
}
