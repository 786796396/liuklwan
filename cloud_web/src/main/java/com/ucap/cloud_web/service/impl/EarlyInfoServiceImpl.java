package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.EarlyInfo;import com.ucap.cloud_web.service.IEarlyInfoService;import com.ucap.cloud_web.dao.EarlyInfoDao;import org.springframework.stereotype.Service;import org.springframework.util.CollectionUtils;

import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.EarlyInfoRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2015-10-29 11:05:30 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/@Servicepublic class EarlyInfoServiceImpl implements IEarlyInfoService {


	@Autowired	private EarlyInfoDao earlyInfoDao;	@Override	public void add(EarlyInfo earlyInfo){		earlyInfoDao.add(earlyInfo);	}
	@Override	public EarlyInfo get(Integer id){		return earlyInfoDao.get(id);	}
	@Override	public void update(EarlyInfo earlyInfo){		earlyInfoDao.update(earlyInfo);	}
	@Override	public void delete(Integer id){		earlyInfoDao.delete(id);	}
	@Override	public PageVo<EarlyInfo> query(EarlyInfoRequest request) {		List<EarlyInfo> earlyInfo = queryList(request);		PageVo<EarlyInfo> pv = new PageVo<EarlyInfo>();		int count = queryCount(request);		pv.setData(earlyInfo);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(EarlyInfoRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);
		if(!CollectionUtils.isEmpty(request.getDatabaseInfoList())){
			param.put("databaseInfoList", request.getDatabaseInfoList());
		}		return earlyInfoDao.queryCount(param);	}
	@Override	public List<EarlyInfo> queryList(EarlyInfoRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);
		if(!CollectionUtils.isEmpty(request.getDatabaseInfoList())){
			param.put("databaseInfoList", request.getDatabaseInfoList());
		}		List<EarlyInfo> list = earlyInfoDao.query(param);		return list;	}
	@Override
	public List<EarlyInfo> queryEarlyInfoList(EarlyInfoRequest request) {
		Map<String, Object> param = QueryUtils.getQueryMap(request);
		if(!CollectionUtils.isEmpty(request.getDatabaseInfoList())){
			param.put("databaseInfoList", request.getDatabaseInfoList());
		}
		List<EarlyInfo> list = earlyInfoDao.queryEarlyInfo(param);
		return list;
	}


	@Override
	public EarlyInfo querySum(Map<String, Object> param) {
		return earlyInfoDao.querySum(param);
	}

	@Override
	public PageVo<EarlyInfo> queryEarlyInfo(EarlyInfoRequest request) {
		
		Map<String, Object> param = QueryUtils.getQueryMap(request);
		if(!CollectionUtils.isEmpty(request.getDatabaseInfoList())){
			param.put("databaseInfoList", request.getDatabaseInfoList());
		}
		List<EarlyInfo> earlyInfo = earlyInfoDao.queryEarlyInfo(param);

		PageVo<EarlyInfo> pv = new PageVo<EarlyInfo>();
		
		int count = queryEarlyInfoCount(request);
		
		pv.setData(earlyInfo);
		pv.setPageNo(request.getPageNo());
		pv.setPageSize(request.getPageSize());
		pv.setRecordSize(count);
		return pv;
	}

	@Override
	public void updateNewEarlyNum(EarlyInfo earlyInfo) {
		earlyInfoDao.updateNewEarlyNum(earlyInfo);
	}

	@Override
	public EarlyInfo queryNewEarlyNum(Map<String, Object> param) {
		return earlyInfoDao.queryNewEarlyNum(param);
	}

	@Override
	public int queryEarlyInfoCount(EarlyInfoRequest request) {
		return earlyInfoDao.queryEarlyInfoCount(request);
	}
}

