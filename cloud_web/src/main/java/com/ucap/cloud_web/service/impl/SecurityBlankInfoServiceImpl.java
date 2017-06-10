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


/*** <br>* <b>作者：</b>SunJiang<br>* <b>日期：</b> 2015-10-30 09:51:21 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/@Servicepublic class SecurityBlankInfoServiceImpl implements ISecurityBlankInfoService {


	@Autowired	private SecurityBlankInfoDao securityBlankInfoDao;	@Override	public void add(SecurityBlankInfo securityBlankInfo){		securityBlankInfoDao.add(securityBlankInfo);	}
	@Override	public SecurityBlankInfo get(Integer id){		return securityBlankInfoDao.get(id);	}
	@Override	public void update(SecurityBlankInfo securityBlankInfo){		securityBlankInfoDao.update(securityBlankInfo);	}
	@Override	public void delete(Integer id){		securityBlankInfoDao.delete(id);	}
	@Override	public PageVo<SecurityBlankInfo> query(SecurityBlankInfoRequest request) {		List<SecurityBlankInfo> securityBlankInfo = queryList(request);		PageVo<SecurityBlankInfo> pv = new PageVo<SecurityBlankInfo>();		int count = queryCount(request);		pv.setData(securityBlankInfo);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(SecurityBlankInfoRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return securityBlankInfoDao.queryCount(param);	}
	@Override	public List<SecurityBlankInfo> queryList(SecurityBlankInfoRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<SecurityBlankInfo> list = securityBlankInfoDao.query(param);		return list;	}

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

