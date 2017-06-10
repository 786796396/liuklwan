package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.SecurityOthers;import com.ucap.cloud_web.service.ISecurityOthersService;import com.ucap.cloud_web.dao.SecurityOthersDao;import org.springframework.stereotype.Service;import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.SecurityOthersRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2016-06-23 15:27:08 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@Servicepublic class SecurityOthersServiceImpl implements ISecurityOthersService {


	@Autowired	private SecurityOthersDao securityOthersDao;	@Override	public void add(SecurityOthers securityOthers){		securityOthersDao.add(securityOthers);	}
	@Override	public SecurityOthers get(Integer id){		return securityOthersDao.get(id);	}
	@Override	public void update(SecurityOthers securityOthers){		securityOthersDao.update(securityOthers);	}
	@Override	public void delete(Integer id){		securityOthersDao.delete(id);	}
	@Override	public PageVo<SecurityOthers> query(SecurityOthersRequest request) {		List<SecurityOthers> securityOthers = queryList(request);		PageVo<SecurityOthers> pv = new PageVo<SecurityOthers>();		int count = queryCount(request);		pv.setData(securityOthers);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(SecurityOthersRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return securityOthersDao.queryCount(param);	}
	@Override	public List<SecurityOthers> queryList(SecurityOthersRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<SecurityOthers> list = securityOthersDao.query(param);		return list;	}
}

