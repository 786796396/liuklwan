package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.SecurityHomeSituation;import com.ucap.cloud_web.service.ISecurityHomeSituationService;import com.ucap.cloud_web.dao.SecurityHomeSituationDao;import org.springframework.stereotype.Service;import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.SecurityHomeSituationRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>liujc<br>* <b>日期：</b> 2016-11-30 16:35:27 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@Servicepublic class SecurityHomeSituationServiceImpl implements ISecurityHomeSituationService {


	@Autowired	private SecurityHomeSituationDao securityHomeSituationDao;	@Override	public int add(SecurityHomeSituation securityHomeSituation){		return securityHomeSituationDao.add(securityHomeSituation);	}
	@Override	public SecurityHomeSituation get(Integer id){		return securityHomeSituationDao.get(id);	}
	@Override	public void update(SecurityHomeSituation securityHomeSituation){		securityHomeSituationDao.update(securityHomeSituation);	}
	@Override	public void delete(Integer id){		securityHomeSituationDao.delete(id);	}
	@Override	public PageVo<SecurityHomeSituation> query(SecurityHomeSituationRequest request) {		List<SecurityHomeSituation> securityHomeSituation = queryList(request);		PageVo<SecurityHomeSituation> pv = new PageVo<SecurityHomeSituation>();		int count = queryCount(request);		pv.setData(securityHomeSituation);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(SecurityHomeSituationRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return securityHomeSituationDao.queryCount(param);	}
	@Override	public List<SecurityHomeSituation> queryList(SecurityHomeSituationRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<SecurityHomeSituation> list = securityHomeSituationDao.query(param);		return list;	}
}

