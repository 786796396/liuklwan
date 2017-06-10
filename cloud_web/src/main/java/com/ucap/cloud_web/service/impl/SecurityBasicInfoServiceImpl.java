package com.ucap.cloud_web.service.impl;


import java.util.HashMap;
import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.SecurityBasicInfo;import com.ucap.cloud_web.service.ISecurityBasicInfoService;import com.ucap.cloud_web.dao.SecurityBasicInfoDao;import org.springframework.stereotype.Service;import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.SecurityBasicInfoRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2016-04-12 10:13:05 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@Servicepublic class SecurityBasicInfoServiceImpl implements ISecurityBasicInfoService {


	@Autowired	private SecurityBasicInfoDao securityBasicInfoDao;	@Override	public void add(SecurityBasicInfo securityBasicInfo){		securityBasicInfoDao.add(securityBasicInfo);	}
	@Override	public SecurityBasicInfo get(Integer id){		return securityBasicInfoDao.get(id);	}
	@Override	public void update(SecurityBasicInfo securityBasicInfo){		securityBasicInfoDao.update(securityBasicInfo);	}
	@Override	public void delete(Integer id){		securityBasicInfoDao.delete(id);	}
	@Override	public PageVo<SecurityBasicInfo> query(SecurityBasicInfoRequest request) {		List<SecurityBasicInfo> securityBasicInfo = queryList(request);		PageVo<SecurityBasicInfo> pv = new PageVo<SecurityBasicInfo>();		int count = queryCount(request);		pv.setData(securityBasicInfo);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(SecurityBasicInfoRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return securityBasicInfoDao.queryCount(param);	}
	@Override	public List<SecurityBasicInfo> queryList(SecurityBasicInfoRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<SecurityBasicInfo> list = securityBasicInfoDao.query(param);		return list;	}

	@Override
	public List<SecurityBasicInfoRequest> getProblemNum(
			HashMap<String, Object> hashMap) {
		return securityBasicInfoDao.getProblemNum(hashMap);
	}

	@Override
	public List<SecurityBasicInfoRequest> queryBasicNum(Map<String, Object> paramMap) {
		return securityBasicInfoDao.queryBasicNum(paramMap);
	}
}

