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


/*** <br>* <b>作者：</b>SunJiang<br>* <b>日期：</b> 2015-10-30 09:51:21 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/@Servicepublic class SecurityServcieServiceImpl implements ISecurityServcieService {


	@Autowired	private SecurityServcieDao securityServcieDao;	@Override	public void add(SecurityServcie securityServcie){		securityServcieDao.add(securityServcie);	}
	@Override	public SecurityServcie get(Integer id){		return securityServcieDao.get(id);	}
	@Override	public void update(SecurityServcie securityServcie){		securityServcieDao.update(securityServcie);	}
	@Override	public void delete(Integer id){		securityServcieDao.delete(id);	}
	@Override	public PageVo<SecurityServcie> query(SecurityServcieRequest request) {		List<SecurityServcie> securityServcie = queryList(request);		PageVo<SecurityServcie> pv = new PageVo<SecurityServcie>();		int count = queryCount(request);		pv.setData(securityServcie);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(SecurityServcieRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return securityServcieDao.queryCount(param);	}
	@Override	public List<SecurityServcie> queryList(SecurityServcieRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<SecurityServcie> list = securityServcieDao.query(param);		return list;	}
	@Override	public List<SecurityBlankInfoRequest> queryBarNum(Map<String, Object> map) {		return securityServcieDao.queryBarNum(map);	}
	@Override	public List<SecurityServcieRequest> queryByMap(Map<String, Object> paramMap) {		List<SecurityServcieRequest> list=securityServcieDao.queryByMap(paramMap);		return list;	}	@Override
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

