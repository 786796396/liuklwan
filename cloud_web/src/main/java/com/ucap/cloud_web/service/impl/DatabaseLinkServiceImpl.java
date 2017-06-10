package com.ucap.cloud_web.service.impl;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.publics.util.page.PageVo;
import com.publics.util.utils.QueryUtils;
import com.ucap.cloud_web.dao.DatabaseLinkDao;
import com.ucap.cloud_web.dto.DatabaseLinkRequest;
import com.ucap.cloud_web.entity.DatabaseLink;
import com.ucap.cloud_web.service.IDatabaseLinkService;


/*** <br>* <b>作者：</b>SunJiang<br>* <b>日期：</b> 2016-03-21 16:16:55 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@Servicepublic class DatabaseLinkServiceImpl implements IDatabaseLinkService {


	@Autowired	private DatabaseLinkDao databaseLinkDao;	@Override	public void add(DatabaseLink databaseLink){		databaseLinkDao.add(databaseLink);	}
	@Override	public DatabaseLink get(Integer id){		return databaseLinkDao.get(id);	}
	@Override	public void update(DatabaseLink databaseLink){		databaseLinkDao.update(databaseLink);	}
	@Override	public void delete(Integer id){		databaseLinkDao.delete(id);	}
	@Override	public PageVo<DatabaseLink> query(DatabaseLinkRequest request) {		List<DatabaseLink> databaseLink = queryList(request);		PageVo<DatabaseLink> pv = new PageVo<DatabaseLink>();		int count = queryCount(request);		pv.setData(databaseLink);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(DatabaseLinkRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return databaseLinkDao.queryCount(param);	}
	@Override	public List<DatabaseLink> queryList(DatabaseLinkRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<DatabaseLink> list = databaseLinkDao.query(param);		return list;	}

	@Override
	public List<DatabaseLinkRequest> queryCountBySiteCode(DatabaseLinkRequest request) {
		return databaseLinkDao.queryCountBySiteCode(request);
	}

	@Override
	public List<DatabaseLink> queryEarlySingleSiteCode(
			DatabaseLinkRequest request) {
		return databaseLinkDao.queryEarlySingleSiteCode(request);
	}

	@Override
	public List<DatabaseLink> queryJoinContract(DatabaseLinkRequest dataBaseLinkRequest) {
		Map<String, Object> param = QueryUtils.getQueryMap(dataBaseLinkRequest);
		List<DatabaseLink> list = databaseLinkDao.queryJoinContract(param);
		return list;
	}
}

