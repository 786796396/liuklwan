package com.ucap.cloud_web.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.publics.util.page.PageVo;
import com.publics.util.utils.QueryUtils;
import com.ucap.cloud_web.dao.DetectionResultDao;
import com.ucap.cloud_web.dto.DetectionResultRequest;
import com.ucap.cloud_web.entity.DetectionResult;
import com.ucap.cloud_web.service.IDetectionResultService;


/**


	@Autowired






		if(request.getIds()!=null && request.getIds().size()>0){
			param.put("ids", request.getIds());
		}
		param.put("start", null);
		param.put("pageSize", null);
		List<DetectionResult> list = detectionResultDao.query(param);

	@Override
	public String maxScanDate() {
		return detectionResultDao.maxScanDate();
	}

	@Override
	public DetectionResult querySum(HashMap<String, Object> param) {
		return detectionResultDao.querySum(param);
	}

	@Override
	public void batchUpdate(HashMap<String, Object> param) {
		detectionResultDao.batchUpdate(param);
	}

	@Override
	public int getListCount(HashMap<String, Object> param) {
		return detectionResultDao.getListCount(param);
	}

	@Override
	public List<DetectionResult> getList(HashMap<String, Object> paramMap) {
		return detectionResultDao.getList(paramMap);
	}

