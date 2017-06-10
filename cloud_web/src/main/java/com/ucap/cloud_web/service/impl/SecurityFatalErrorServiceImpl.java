package com.ucap.cloud_web.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.publics.util.page.PageVo;
import com.publics.util.utils.QueryUtils;
import com.ucap.cloud_web.dao.SecurityFatalErrorDao;
import com.ucap.cloud_web.dto.SecurityFatalErrorRequest;
import com.ucap.cloud_web.entity.SecurityFatalError;
import com.ucap.cloud_web.service.ISecurityFatalErrorService;


/**


	@Autowired







	@Override
	public List<SecurityFatalErrorRequest> queryFatalErrorInfoByTree(
			HashMap<String, Object> paraMap) {
		return securityFatalErrorDao.queryFatalErrorInfoByTree(paraMap);
	}

	@Override
	public int queryFatalErrorInfoByTreeCount(HashMap<String, Object> paraMap) {
		return securityFatalErrorDao.queryFatalErrorInfoByTreeCount(paraMap);
	}
}
