package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.SpotCheckInfo;import com.ucap.cloud_web.service.ISpotCheckInfoService;import com.ucap.cloud_web.dao.SpotCheckInfoDao;import org.springframework.stereotype.Service;import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.SpotCheckInfoRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>yuangw@ucap.com.cn<br>* <b>日期：</b> 2016-04-19 11:16:09 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@Servicepublic class SpotCheckInfoServiceImpl implements ISpotCheckInfoService {


	@Autowired	private SpotCheckInfoDao spotCheckInfoDao;	@Override	public void add(SpotCheckInfo spotCheckInfo){		spotCheckInfoDao.add(spotCheckInfo);	}
	@Override	public SpotCheckInfo get(Integer id){		return spotCheckInfoDao.get(id);	}
	@Override	public void update(SpotCheckInfo spotCheckInfo){		spotCheckInfoDao.update(spotCheckInfo);	}
	@Override	public void delete(Integer id){		spotCheckInfoDao.delete(id);	}
	@Override	public PageVo<SpotCheckInfo> query(SpotCheckInfoRequest request) {		List<SpotCheckInfo> spotCheckInfo = queryList(request);		PageVo<SpotCheckInfo> pv = new PageVo<SpotCheckInfo>();		int count = queryCount(request);		pv.setData(spotCheckInfo);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(SpotCheckInfoRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return spotCheckInfoDao.queryCount(param);	}
	@Override	public List<SpotCheckInfo> queryList(SpotCheckInfoRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<SpotCheckInfo> list = spotCheckInfoDao.query(param);		return list;	}
			@Override	public List<SpotCheckInfo> findBySpotCheckInfoCode(SpotCheckInfoRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return spotCheckInfoDao.findBySpotCheckInfoCode(param);	}}

