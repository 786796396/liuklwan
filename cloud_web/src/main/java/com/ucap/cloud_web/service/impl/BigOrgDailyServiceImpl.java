package com.ucap.cloud_web.service.impl;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.publics.util.page.PageVo;
import com.publics.util.utils.QueryUtils;
import com.ucap.cloud_web.dao.BigOrgDailyDao;
import com.ucap.cloud_web.dto.BigOrgDailyRequest;
import com.ucap.cloud_web.dtoResponse.BigOrgDailyResponse;
import com.ucap.cloud_web.entity.BigOrgDaily;
import com.ucap.cloud_web.service.IBigOrgDailyService;


/*** <br>* <b>作者：</b>liujc<br>* <b>日期：</b> 2017-02-28 18:03:30 <br>* <b>版权所有：<b>版权所有(C) 2017<br>*/@Servicepublic class BigOrgDailyServiceImpl implements IBigOrgDailyService {


	@Autowired	private BigOrgDailyDao bigOrgDailyDao;	@Override	public int add(BigOrgDaily bigOrgDaily){		return bigOrgDailyDao.add(bigOrgDaily);	}
	@Override	public BigOrgDaily get(Integer id){		return bigOrgDailyDao.get(id);	}
	@Override	public void update(BigOrgDaily bigOrgDaily){		bigOrgDailyDao.update(bigOrgDaily);	}
	@Override	public void delete(Integer id){		bigOrgDailyDao.delete(id);	}
	@Override	public PageVo<BigOrgDaily> query(BigOrgDailyRequest request) {		List<BigOrgDaily> bigOrgDaily = queryList(request);		PageVo<BigOrgDaily> pv = new PageVo<BigOrgDaily>();		int count = queryCount(request);		pv.setData(bigOrgDaily);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(BigOrgDailyRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return bigOrgDailyDao.queryCount(param);	}
	@Override	public List<BigOrgDaily> queryList(BigOrgDailyRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<BigOrgDaily> list = bigOrgDailyDao.query(param);		return list;	}
	@Override
	public List<BigOrgDailyResponse> getOrgData(BigOrgDailyRequest request) {
		Map<String, Object> param = QueryUtils.getQueryMap(request);
		List<BigOrgDailyResponse> list = bigOrgDailyDao.getOrgData(param);
		return list;
	}}

