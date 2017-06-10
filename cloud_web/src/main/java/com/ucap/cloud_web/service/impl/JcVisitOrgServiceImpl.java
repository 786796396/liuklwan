package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.JcVisitOrg;import com.ucap.cloud_web.service.IJcVisitOrgService;import com.ucap.cloud_web.dao.JcVisitOrgDao;import org.springframework.stereotype.Service;import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.JcVisitOrgRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-12-13 08:58:19 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@Servicepublic class JcVisitOrgServiceImpl implements IJcVisitOrgService {


	@Autowired	private JcVisitOrgDao jcVisitOrgDao;	@Override	public int add(JcVisitOrg jcVisitOrg){		return jcVisitOrgDao.add(jcVisitOrg);	}
	@Override	public JcVisitOrg get(Integer id){		return jcVisitOrgDao.get(id);	}
	@Override	public void update(JcVisitOrg jcVisitOrg){		jcVisitOrgDao.update(jcVisitOrg);	}
	@Override	public void delete(Integer id){		jcVisitOrgDao.delete(id);	}
	@Override	public PageVo<JcVisitOrg> query(JcVisitOrgRequest request) {		List<JcVisitOrg> jcVisitOrg = queryList(request);		PageVo<JcVisitOrg> pv = new PageVo<JcVisitOrg>();		int count = queryCount(request);		pv.setData(jcVisitOrg);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(JcVisitOrgRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return jcVisitOrgDao.queryCount(param);	}
	@Override	public List<JcVisitOrg> queryList(JcVisitOrgRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<JcVisitOrg> list = jcVisitOrgDao.query(param);		return list;	}

	@Override
	public List<JcVisitOrg> getOrgData(JcVisitOrgRequest request) {
		Map<String, Object> param = QueryUtils.getQueryMap(request);
		List<JcVisitOrg> list = jcVisitOrgDao.getOrgData(param);
		return list;
	}
}

