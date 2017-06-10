package com.ucap.cloud_web.service.impl;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.publics.util.page.PageVo;
import com.publics.util.utils.QueryUtils;
import com.ucap.cloud_web.constant.TreeVo;
import com.ucap.cloud_web.dao.DatabaseTreeInfoDao;
import com.ucap.cloud_web.dto.DatabaseTreeInfoRequest;
import com.ucap.cloud_web.entity.DatabaseInfo;
import com.ucap.cloud_web.entity.DatabaseTreeInfo;
import com.ucap.cloud_web.service.IDatabaseTreeInfoService;


/**


	@Autowired






		param.put("layerTypeArr", request.getLayerTypeArr());

	@Override
	public List<DatabaseTreeInfo> getDatas(DatabaseTreeInfoRequest rRequest) {
		Map<String, Object> param = QueryUtils.getQueryMap(rRequest);
		List<DatabaseTreeInfo> list = databaseTreeInfoDao.getDatas(param);
		return list;
	}

	@Override
	public PageVo<DatabaseInfo> getNativeDatas(DatabaseTreeInfoRequest sRequest) {
		Map<String, Object> param = QueryUtils.getQueryMap(sRequest);
		List<DatabaseInfo> list = databaseTreeInfoDao.getNativeDatas(param);
		PageVo<DatabaseInfo> pv = new PageVo<DatabaseInfo>();
		int count = getNativeDatasCount(sRequest);
		pv.setData(list);
		pv.setPageNo(sRequest.getPageNo());
		pv.setPageSize(sRequest.getPageSize());
		pv.setRecordSize(count);
		return pv;
	}
	public int getNativeDatasCount(DatabaseTreeInfoRequest sRequest) {
		Map<String, Object> param = QueryUtils.getQueryMap(sRequest);
		return databaseTreeInfoDao.getNativeDatasCount(param);
	}

	@Override
	public List<DatabaseInfo> getNatives(DatabaseTreeInfoRequest sRequest) {
		Map<String, Object> param = QueryUtils.getQueryMap(sRequest);
		List<DatabaseInfo> list = databaseTreeInfoDao.getNatives(param);
		return list;
	}

	@Override
	public List<DatabaseInfo> queryBmLocalSites(
			DatabaseTreeInfoRequest request) {
		// TODO Auto-generated method stub
		return databaseTreeInfoDao.queryBmLocalSites(request);
	}
	@Override
	public List<DatabaseInfo> queryDatabaseInfoList(
			DatabaseTreeInfoRequest request) {
		Map<String, Object> param = QueryUtils.getQueryMap(request);
		List<DatabaseInfo> list = databaseTreeInfoDao
				.queryDatabaseInfoList(param);
		return list;
	}

	@Override
	public List<DatabaseInfo> querySiteDatabaseInfoList(
			DatabaseTreeInfoRequest request) {
		Map<String, Object> param = QueryUtils.getQueryMap(request);
		List<DatabaseInfo> list = databaseTreeInfoDao
				.querySiteDatabaseInfoList(param);
		return list;
	}
	
	@Override
	public List<TreeVo> queryTree(DatabaseTreeInfoRequest treeInfo) {
		// TODO Auto-generated method stub
		return databaseTreeInfoDao.queryTree(treeInfo);
	}
	
	@Override
	public List<TreeVo> queryTreeBM(DatabaseTreeInfoRequest treeInfo) {
		// TODO Auto-generated method stub
		return databaseTreeInfoDao.queryTreeBM(treeInfo);
	}
	
	@Override
	public DatabaseTreeInfo getInfoStates(DatabaseTreeInfoRequest rRequest) {
		Map<String, Object> param = QueryUtils.getQueryMap(rRequest);
		return databaseTreeInfoDao.getInfoStates(param);
	}

	@Override
	public List<DatabaseTreeInfo> queryListJoinInfo(DatabaseTreeInfoRequest databaseTreeInfoRequest) {
		Map<String, Object> param = QueryUtils.getQueryMap(databaseTreeInfoRequest);
		return databaseTreeInfoDao.queryListJoinInfo(param);
	}

	/** 新表方法 */

	public List<DatabaseTreeInfo> getDatabaseTreeInfoList(DatabaseTreeInfoRequest request) {
		return databaseTreeInfoDao.getDatabaseTreeInfoList(request);
	}

	@Override
	public List<DatabaseTreeInfoRequest> queryListByTypeOrIsexp(DatabaseTreeInfoRequest databaseTreeInfoRequest) {
		Map<String, Object> param = QueryUtils.getQueryMap(databaseTreeInfoRequest);
		return databaseTreeInfoDao.queryListByTypeOrIsexp(param);
	}
	@Override
	public List<String> queryDownSite(Map<String, Object> map) {
		return databaseTreeInfoDao.queryDownSite(map);
	}

	@Override
	public List<DatabaseTreeInfo> queryDownSiteInfo(Map<String, Object> map) {
		return databaseTreeInfoDao.queryDownSiteInfo(map);
	}
	@Override
	public List<DatabaseTreeInfo> queryJoinContract(DatabaseTreeInfoRequest databaseTreeInfoRequest) {
		Map<String, Object> param = QueryUtils.getQueryMap(databaseTreeInfoRequest);
		List<DatabaseTreeInfo> list = databaseTreeInfoDao.queryJoinContract(param);
		return list;
	}

	@Override
	public List<String> queryDownSite2(Map<String, Object> paraMap) {
		return databaseTreeInfoDao.queryDownSite2(paraMap);
	}

	@Override
	public Integer getSiteCodeLevel(DatabaseTreeInfoRequest req) {
		Map<String, Object> param = QueryUtils.getQueryMap(req);
		return databaseTreeInfoDao.getSiteCodeLevel(param);
	}
	@Override
	public DatabaseTreeInfo queryUpOrgSiteCode(Map<String, Object> map) {
		return databaseTreeInfoDao.queryUpOrgSiteCode(map);
	}
	@Override
	public List<DatabaseTreeInfoRequest> querySiteinfoByTree(
			Map<String, Object> paraMap) {
		return databaseTreeInfoDao.querySiteinfoByTree(paraMap);
	}
