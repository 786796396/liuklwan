package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.DatabaseOrgInfo;import com.ucap.cloud_web.service.IDatabaseOrgInfoService;import com.ucap.cloud_web.dao.DatabaseOrgInfoDao;import org.springframework.stereotype.Service;import org.springframework.util.CollectionUtils;

import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.DatabaseOrgInfoRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2016-03-17 18:11:30 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@Servicepublic class DatabaseOrgInfoServiceImpl implements IDatabaseOrgInfoService {


	@Autowired	private DatabaseOrgInfoDao databaseOrgInfoDao;	@Override	public void add(DatabaseOrgInfo databaseOrgInfo){		databaseOrgInfoDao.add(databaseOrgInfo);	}
	@Override	public DatabaseOrgInfo get(Integer id){		return databaseOrgInfoDao.get(id);	}
	@Override	public void update(DatabaseOrgInfo databaseOrgInfo){		databaseOrgInfoDao.update(databaseOrgInfo);	}
	@Override	public void delete(Integer id){		databaseOrgInfoDao.delete(id);	}
	@Override	public PageVo<DatabaseOrgInfo> query(DatabaseOrgInfoRequest request) {		List<DatabaseOrgInfo> databaseOrgInfo = queryList(request);		PageVo<DatabaseOrgInfo> pv = new PageVo<DatabaseOrgInfo>();		int count = queryCount(request);		pv.setData(databaseOrgInfo);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(DatabaseOrgInfoRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return databaseOrgInfoDao.queryCount(param);	}
	@Override	public List<DatabaseOrgInfo> queryList(DatabaseOrgInfoRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<DatabaseOrgInfo> list = databaseOrgInfoDao.query(param);		return list;	}

	@Override
	public DatabaseOrgInfo getDatabaseOrgByCode(String siteCode) {
		DatabaseOrgInfoRequest request = new DatabaseOrgInfoRequest();
		request.setStieCode(siteCode);
		List<DatabaseOrgInfo> databaseOrgInfoList = queryList(request);
		if(!CollectionUtils.isEmpty(databaseOrgInfoList)){
			return databaseOrgInfoList.get(0);
		}
		return null;
	}
}

