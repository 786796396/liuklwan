package com.ucap.cloud_web.service.impl;


import java.util.HashMap;
import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.MTaskoverview;import com.ucap.cloud_web.entity.Result;
import com.ucap.cloud_web.service.IMTaskoverviewService;import com.ucap.cloud_web.dao.MTaskoverviewDao;import org.springframework.stereotype.Service;import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.MTaskdetailRequest;
import com.ucap.cloud_web.dto.MTaskoverviewRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>liujc<br>* <b>日期：</b> 2016-10-17 15:16:51 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@Servicepublic class MTaskoverviewServiceImpl implements IMTaskoverviewService {


	@Autowired	private MTaskoverviewDao mTaskoverviewDao;	@Override	public int add(MTaskoverview mTaskoverview){		return mTaskoverviewDao.add(mTaskoverview);	}
	@Override	public MTaskoverview get(Integer id){		return mTaskoverviewDao.get(id);	}
	@Override	public void update(MTaskoverview mTaskoverview){		mTaskoverviewDao.update(mTaskoverview);	}
	@Override	public void delete(Integer id){		mTaskoverviewDao.delete(id);	}
	@Override	public PageVo<MTaskoverview> query(MTaskoverviewRequest request) {		List<MTaskoverview> mTaskoverview = queryList(request);		PageVo<MTaskoverview> pv = new PageVo<MTaskoverview>();		int count = queryCount(request);		pv.setData(mTaskoverview);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(MTaskoverviewRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return mTaskoverviewDao.queryCount(param);	}
	@Override	public List<MTaskoverview> queryList(MTaskoverviewRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<MTaskoverview> list = mTaskoverviewDao.query(param);		return list;	}
	@Override
	public List<Result> queryResultList(MTaskoverviewRequest request) {
		Map<String, Object> param = QueryUtils.getQueryMap(request);
		List<Result> list = mTaskoverviewDao.queryResultList(param);
		return list;
	}

	@Override
	public List<MTaskoverviewRequest> getMTaskoverMap(HashMap<String, Object> hashMap) {
		return mTaskoverviewDao.getMTaskoverMap(hashMap);
	}}

