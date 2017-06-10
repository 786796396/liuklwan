package com.ucap.cloud_web.service.impl;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.publics.util.page.PageVo;
import com.publics.util.utils.QueryUtils;
import com.ucap.cloud_web.dao.BigDataDailyDao;
import com.ucap.cloud_web.dto.BigDataDailyRequest;
import com.ucap.cloud_web.dtoResponse.BigDataDailyResponse;
import com.ucap.cloud_web.entity.BigDataDaily;
import com.ucap.cloud_web.service.IBigDataDailyService;


/*** <br>* <b>作者：</b>liujc<br>* <b>日期：</b> 2017-02-28 18:11:20 <br>* <b>版权所有：<b>版权所有(C) 2017<br>*/@Servicepublic class BigDataDailyServiceImpl implements IBigDataDailyService {


	@Autowired	private BigDataDailyDao bigDataDailyDao;	@Override	public int add(BigDataDaily bigDataDaily){		return bigDataDailyDao.add(bigDataDaily);	}
	@Override	public BigDataDaily get(Integer id){		return bigDataDailyDao.get(id);	}
	@Override	public void update(BigDataDaily bigDataDaily){		bigDataDailyDao.update(bigDataDaily);	}
	@Override	public void delete(Integer id){		bigDataDailyDao.delete(id);	}
	@Override	public PageVo<BigDataDaily> query(BigDataDailyRequest request) {		List<BigDataDaily> bigDataDaily = queryList(request);		PageVo<BigDataDaily> pv = new PageVo<BigDataDaily>();		int count = queryCount(request);		pv.setData(bigDataDaily);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(BigDataDailyRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return bigDataDailyDao.queryCount(param);	}
	@Override	public List<BigDataDaily> queryList(BigDataDailyRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<BigDataDaily> list = bigDataDailyDao.query(param);		return list;	}
	@Override
	public List<BigDataDailyResponse> queryOrganizations(BigDataDailyRequest sRequest) {
		Map<String, Object> param = QueryUtils.getQueryMap(sRequest);
		List<BigDataDailyResponse> list = bigDataDailyDao.queryOrganizations(param);
		return list;
	}

	@Override
	public List<BigDataDailyResponse> queryNatives(BigDataDailyRequest sRequest) {
		Map<String, Object> param = QueryUtils.getQueryMap(sRequest);
		List<BigDataDailyResponse> list = bigDataDailyDao.queryNatives(param);
		return list;
	}
}

