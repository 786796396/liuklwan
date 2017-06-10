package com.ucap.cloud_web.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.publics.util.page.PageVo;
import com.publics.util.utils.QueryUtils;
import com.ucap.cloud_web.dao.CorrectContentDetailDao;
import com.ucap.cloud_web.dto.CorrectContentDetailRequest;
import com.ucap.cloud_web.entity.CorrectContentDetail;
import com.ucap.cloud_web.service.ICorrectContentDetailService;


/*** <br>* <b>作者：</b>Nora<br>* <b>日期：</b> 2015-10-29 11:19:33 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/@Servicepublic class CorrectContentDetailServiceImpl implements ICorrectContentDetailService {


	@Autowired	private CorrectContentDetailDao correctContentDetailDao;	@Override	public void add(CorrectContentDetail correctContentDetail){		correctContentDetailDao.add(correctContentDetail);	}
	@Override	public CorrectContentDetail get(Integer id){		return correctContentDetailDao.get(id);	}
	@Override	public void update(CorrectContentDetail correctContentDetail){		correctContentDetailDao.update(correctContentDetail);	}
	@Override	public void delete(Integer id){		correctContentDetailDao.delete(id);	}
	@Override	public PageVo<CorrectContentDetail> query(CorrectContentDetailRequest request) {		List<CorrectContentDetail> correctContentDetail = queryList(request);		PageVo<CorrectContentDetail> pv = new PageVo<CorrectContentDetail>();		int count = queryCount(request);		pv.setData(correctContentDetail);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(CorrectContentDetailRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);
		if(request.getCorrectTypeArray()!=null){
			param.put("correctTypeArray", request.getCorrectTypeArray());
		}
		if(request.getDatabaseList()!=null && request.getDatabaseList().size()>0){
			param.put("databaseList", request.getDatabaseList());
		}		return correctContentDetailDao.queryCount(param);	}
	@Override	public List<CorrectContentDetail> queryList(CorrectContentDetailRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);
		if(request.getCorrectTypeArray()!=null){
			param.put("correctTypeArray", request.getCorrectTypeArray());
		}
		if(request.getDatabaseList()!=null && request.getDatabaseList().size()>0){
			param.put("databaseList", request.getDatabaseList());
		}		List<CorrectContentDetail> list = correctContentDetailDao.query(param);		return list;	}

	@Override
	public List<CorrectContentDetailRequest> queryCorrectLine(
			HashMap<String, Object> hashMap) {
		return correctContentDetailDao.queryCorrectLine(hashMap);
	}

	@Override
	public List<CorrectContentDetailRequest> queryWrongNum(
			HashMap<String, Object> hashMap) {
		return correctContentDetailDao.queryWrongNum(hashMap);
	}

	@Override
	public List<CorrectContentDetailRequest> getCorrectLine(
			Map<Object, Object> parapMap) {
		return correctContentDetailDao.getCorrectLine(parapMap);
	}

	@Override
	public List<CorrectContentDetailRequest> getCorrectContentList(HashMap<String, Object> hashMap) {
		return correctContentDetailDao.getCorrectContentList(hashMap);
	}

	@Override
	public int getCorrectContentListCount(HashMap<String, Object> paraMap) {
		// TODO Auto-generated method stub
		return correctContentDetailDao.getCorrectContentListCount(paraMap);
	}
}

