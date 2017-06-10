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


/**


	@Autowired





		if(request.getCorrectTypeArray()!=null){
			param.put("correctTypeArray", request.getCorrectTypeArray());
		}
		if(request.getDatabaseList()!=null && request.getDatabaseList().size()>0){
			param.put("databaseList", request.getDatabaseList());
		}

		if(request.getCorrectTypeArray()!=null){
			param.put("correctTypeArray", request.getCorrectTypeArray());
		}
		if(request.getDatabaseList()!=null && request.getDatabaseList().size()>0){
			param.put("databaseList", request.getDatabaseList());
		}

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

