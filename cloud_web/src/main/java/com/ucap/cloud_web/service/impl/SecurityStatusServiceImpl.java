package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.SecurityStatus;import com.ucap.cloud_web.service.ISecurityStatusService;import com.ucap.cloud_web.dao.SecurityStatusDao;import org.springframework.stereotype.Service;import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.SecurityStatusRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>liujc<br>* <b>日期：</b> 2016-09-02 16:14:53 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@Servicepublic class SecurityStatusServiceImpl implements ISecurityStatusService {


	@Autowired	private SecurityStatusDao securityStatusDao;	@Override	public int add(SecurityStatus securityStatus){		return securityStatusDao.add(securityStatus);	}
	@Override	public SecurityStatus get(Integer id){		return securityStatusDao.get(id);	}
	@Override	public void update(SecurityStatus securityStatus){		securityStatusDao.update(securityStatus);	}
	@Override	public void delete(Integer id){		securityStatusDao.delete(id);	}
	@Override	public PageVo<SecurityStatus> query(SecurityStatusRequest request) {		List<SecurityStatus> securityStatus = queryList(request);		PageVo<SecurityStatus> pv = new PageVo<SecurityStatus>();		int count = queryCount(request);		pv.setData(securityStatus);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(SecurityStatusRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return securityStatusDao.queryCount(param);	}
	@Override	public List<SecurityStatus> queryList(SecurityStatusRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<SecurityStatus> list = securityStatusDao.query(param);		return list;	}
}

