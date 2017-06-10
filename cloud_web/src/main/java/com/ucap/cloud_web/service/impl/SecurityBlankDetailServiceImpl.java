package com.ucap.cloud_web.service.impl;


import java.util.HashMap;
import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.SecurityBlankDetail;import com.ucap.cloud_web.service.ISecurityBlankDetailService;import com.ucap.cloud_web.dao.SecurityBlankDetailDao;import org.springframework.stereotype.Service;import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.SecurityBlankDetailRequest;import com.ucap.cloud_web.dtoResponse.SecurityGuaranteeResponse;

import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>SunJiang<br>* <b>日期：</b> 2015-10-30 09:51:21 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/@Servicepublic class SecurityBlankDetailServiceImpl implements ISecurityBlankDetailService {


	@Autowired	private SecurityBlankDetailDao securityBlankDetailDao;	@Override	public void add(SecurityBlankDetail securityBlankDetail){		securityBlankDetailDao.add(securityBlankDetail);	}
	@Override	public SecurityBlankDetail get(Integer id){		return securityBlankDetailDao.get(id);	}
	@Override	public void update(SecurityBlankDetail securityBlankDetail){		securityBlankDetailDao.update(securityBlankDetail);	}
	@Override	public void delete(Integer id){		securityBlankDetailDao.delete(id);	}
	@Override	public PageVo<SecurityBlankDetail> query(SecurityBlankDetailRequest request) {		List<SecurityBlankDetail> securityBlankDetail = queryList(request);		PageVo<SecurityBlankDetail> pv = new PageVo<SecurityBlankDetail>();		int count = queryCount(request);		pv.setData(securityBlankDetail);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(SecurityBlankDetailRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return securityBlankDetailDao.queryCount(param);	}
	@Override	public List<SecurityBlankDetail> queryList(SecurityBlankDetailRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<SecurityBlankDetail> list = securityBlankDetailDao.query(param);		return list;	}

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
	
	
}

