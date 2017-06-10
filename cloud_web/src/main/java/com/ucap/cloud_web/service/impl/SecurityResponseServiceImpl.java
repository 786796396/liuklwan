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


/*** <br>* <b>作者：</b>SunJiang<br>* <b>日期：</b> 2015-10-30 09:51:21 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/@Servicepublic class SecurityResponseServiceImpl implements ISecurityResponseService {


	@Autowired	private SecurityResponseDao securityResponseDao;	@Override	public void add(SecurityResponse securityResponse){		securityResponseDao.add(securityResponse);	}
	@Override	public SecurityResponse get(Integer id){		return securityResponseDao.get(id);	}
	@Override	public void update(SecurityResponse securityResponse){		securityResponseDao.update(securityResponse);	}
	@Override	public void delete(Integer id){		securityResponseDao.delete(id);	}
	@Override	public PageVo<SecurityResponse> query(SecurityResponseRequest request) {		List<SecurityResponse> securityResponse = queryList(request);		PageVo<SecurityResponse> pv = new PageVo<SecurityResponse>();		int count = queryCount(request);		pv.setData(securityResponse);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(SecurityResponseRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return securityResponseDao.queryCount(param);	}
	@Override	public List<SecurityResponse> queryList(SecurityResponseRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<SecurityResponse> list = securityResponseDao.query(param);		return list;	}

	@Override	public List<SecurityBlankInfoRequest> queryBarNum(Map<String, Object> param) {		return securityResponseDao.queryBarNum(param);	}	@Override	public List<SecurityResponseRequest> queryByMap(Map<String, Object> map) {		List<SecurityResponseRequest> list=securityResponseDao.queryByMap(map);		return list;	}
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

