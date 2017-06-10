package com.ucap.cloud_web.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.publics.util.page.PageVo;
import com.publics.util.utils.QueryUtils;
import com.ucap.cloud_web.dao.ConnectionAllHomeDao;
import com.ucap.cloud_web.dto.ConnectionAllHomeRequest;
import com.ucap.cloud_web.dtoResponse.ConnectionAllHomeResponse;
import com.ucap.cloud_web.entity.ConnectionAllHome;
import com.ucap.cloud_web.service.IConnectionAllHomeService;


/*** <br>* <b>作者：</b>qingjy<br>* <b>日期：</b> 2017-03-30 15:18:32 <br>* <b>版权所有：<b>版权所有(C) 2017<br>*/@Servicepublic class ConnectionAllHomeServiceImpl implements IConnectionAllHomeService {


	@Autowired	private ConnectionAllHomeDao connectionAllHomeDao;	@Override	public int add(ConnectionAllHome connectionAllHome){		return connectionAllHomeDao.add(connectionAllHome);	}
	@Override	public ConnectionAllHome get(Integer id){		return connectionAllHomeDao.get(id);	}
	@Override	public void update(ConnectionAllHome connectionAllHome){		connectionAllHomeDao.update(connectionAllHome);	}
	@Override	public void delete(Integer id){		connectionAllHomeDao.delete(id);	}
	@Override	public PageVo<ConnectionAllHome> query(ConnectionAllHomeRequest request) {		List<ConnectionAllHome> connectionAllHome = queryList(request);		PageVo<ConnectionAllHome> pv = new PageVo<ConnectionAllHome>();		int count = queryCount(request);		pv.setData(connectionAllHome);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(ConnectionAllHomeRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return connectionAllHomeDao.queryCount(param);	}
	@Override	public List<ConnectionAllHome> queryList(ConnectionAllHomeRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<ConnectionAllHome> list = connectionAllHomeDao.query(param);		return list;	}

	@Override
	public List<ConnectionAllHomeResponse> getConnectionAllHomeList(HashMap<String, Object> hashMap) {
		return connectionAllHomeDao.getConnectionAllHomeList(hashMap);
	}
}

