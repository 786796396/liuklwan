package com.ucap.cloud_web.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.publics.util.page.PageVo;
import com.publics.util.utils.QueryUtils;
import com.ucap.cloud_web.dao.JcVisitSitecodeDao;
import com.ucap.cloud_web.dto.JcVisitSitecodeRequest;
import com.ucap.cloud_web.dtoResponse.JcVisitSitecodeResponse;
import com.ucap.cloud_web.entity.JcVisitSitecode;
import com.ucap.cloud_web.service.IJcVisitSitecodeService;


/*** <br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-12-13 20:29:02 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@Servicepublic class JcVisitSitecodeServiceImpl implements IJcVisitSitecodeService {


	@Autowired	private JcVisitSitecodeDao jcVisitSitecodeDao;	@Override	public int add(JcVisitSitecode jcVisitSitecode){		return jcVisitSitecodeDao.add(jcVisitSitecode);	}
	@Override	public JcVisitSitecode get(Integer id){		return jcVisitSitecodeDao.get(id);	}
	@Override	public void update(JcVisitSitecode jcVisitSitecode){		jcVisitSitecodeDao.update(jcVisitSitecode);	}
	@Override	public void delete(Integer id){		jcVisitSitecodeDao.delete(id);	}
	@Override	public PageVo<JcVisitSitecode> query(JcVisitSitecodeRequest request) {		List<JcVisitSitecode> jcVisitSitecode = queryList(request);		PageVo<JcVisitSitecode> pv = new PageVo<JcVisitSitecode>();		int count = queryCount(request);		pv.setData(jcVisitSitecode);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(JcVisitSitecodeRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return jcVisitSitecodeDao.queryCount(param);	}
	@Override	public List<JcVisitSitecode> queryList(JcVisitSitecodeRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<JcVisitSitecode> list = jcVisitSitecodeDao.query(param);		return list;	}

	@Override
	public List<JcVisitSitecode> querySiteCodes(JcVisitSitecodeRequest sRequest) {
		Map<String, Object> param = QueryUtils.getQueryMap(sRequest);
		List<JcVisitSitecode> list = jcVisitSitecodeDao.querySiteCodes(param);
		return list;
	}

	@Override
	public List<JcVisitSitecode> queryOrganizations(JcVisitSitecodeRequest sRequest) {
		Map<String, Object> param = QueryUtils.getQueryMap(sRequest);
		List<JcVisitSitecode> list = jcVisitSitecodeDao.queryOrganizations(param);
		return list;
	}

	@Override
	public List<JcVisitSitecode> queryNatives(JcVisitSitecodeRequest sRequest) {
		Map<String, Object> param = QueryUtils.getQueryMap(sRequest);
		List<JcVisitSitecode> list = jcVisitSitecodeDao.queryNatives(param);
		return list;
	}

	@Override
	public List<JcVisitSitecodeResponse> getSiteVisitsList(HashMap<String, Object> hashMap) {
		return jcVisitSitecodeDao.getSiteVisitsList(hashMap);
	}
}

