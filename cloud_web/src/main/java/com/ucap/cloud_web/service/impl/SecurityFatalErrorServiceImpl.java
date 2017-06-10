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


/*** <br>* <b>作者：</b>sunjq<br>* <b>日期：</b> 2016-05-09 11:53:09 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@Servicepublic class SecurityFatalErrorServiceImpl implements ISecurityFatalErrorService {


	@Autowired	private SecurityFatalErrorDao securityFatalErrorDao;	@Override	public void add(SecurityFatalError securityFatalError){		securityFatalErrorDao.add(securityFatalError);	}
	@Override	public SecurityFatalError get(Integer id){		return securityFatalErrorDao.get(id);	}
	@Override	public void update(SecurityFatalError securityFatalError){		securityFatalErrorDao.update(securityFatalError);	}
	@Override	public void delete(Integer id){		securityFatalErrorDao.delete(id);	}
	@Override	public PageVo<SecurityFatalError> query(SecurityFatalErrorRequest request) {		List<SecurityFatalError> securityFatalError = queryList(request);		PageVo<SecurityFatalError> pv = new PageVo<SecurityFatalError>();		int count = queryCount(request);		pv.setData(securityFatalError);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(SecurityFatalErrorRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return securityFatalErrorDao.queryCount(param);	}
	@Override	public List<SecurityFatalError> queryList(SecurityFatalErrorRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<SecurityFatalError> list = securityFatalErrorDao.query(param);		return list;	}

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

