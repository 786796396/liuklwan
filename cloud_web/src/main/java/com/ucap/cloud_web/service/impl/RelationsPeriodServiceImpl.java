package com.ucap.cloud_web.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.publics.util.page.PageVo;
import com.publics.util.utils.QueryUtils;
import com.ucap.cloud_web.dao.RelationsPeriodDao;
import com.ucap.cloud_web.dto.RelationsPeriodRequest;
import com.ucap.cloud_web.dtoResponse.SecurityGuaranteeResponse;
import com.ucap.cloud_web.entity.RelationsPeriod;
import com.ucap.cloud_web.service.IRelationsPeriodService;


/*** <br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2016-03-09 10:08:29 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@Servicepublic class RelationsPeriodServiceImpl implements IRelationsPeriodService {


	@Autowired	private RelationsPeriodDao relationsPeriodDao;	@Override	public void add(RelationsPeriod relationsPeriod){		relationsPeriodDao.add(relationsPeriod);	}
	@Override	public RelationsPeriod get(Integer id){		return relationsPeriodDao.get(id);	}
	@Override	public void update(RelationsPeriod relationsPeriod){		relationsPeriodDao.update(relationsPeriod);	}
	@Override	public void delete(Integer id){		relationsPeriodDao.delete(id);	}
	@Override	public PageVo<RelationsPeriod> query(RelationsPeriodRequest request) {		List<RelationsPeriod> relationsPeriod = queryList(request);		PageVo<RelationsPeriod> pv = new PageVo<RelationsPeriod>();		int count = queryCount(request);		pv.setData(relationsPeriod);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(RelationsPeriodRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return relationsPeriodDao.queryCount(param);	}
	@Override	public List<RelationsPeriod> queryList(RelationsPeriodRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<RelationsPeriod> list = relationsPeriodDao.query(param);		return list;	}

	@Override
	public List<RelationsPeriodRequest> queryByMap(Map<String, Object> pMap) {
		return relationsPeriodDao.queryByMap(pMap);
	}

	@Override
	public List<SecurityGuaranteeResponse> contentSecurityTree(HashMap<String, Object> map) {
		return relationsPeriodDao.contentSecurityTree(map);
	}
}

