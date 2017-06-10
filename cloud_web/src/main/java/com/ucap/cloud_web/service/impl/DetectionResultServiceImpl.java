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


/*** <br>* <b>作者：</b>SunJiang<br>* <b>日期：</b> 2015-11-20 16:56:11 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/@Servicepublic class DetectionResultServiceImpl implements IDetectionResultService {


	@Autowired	private DetectionResultDao detectionResultDao;	@Override	public void add(DetectionResult detectionResult){		detectionResultDao.add(detectionResult);	}
	@Override	public DetectionResult get(Integer id){		return detectionResultDao.get(id);	}
	@Override	public void update(DetectionResult detectionResult){		detectionResultDao.update(detectionResult);	}
	@Override	public void delete(Integer id){		detectionResultDao.delete(id);	}
	@Override	public PageVo<DetectionResult> query(DetectionResultRequest request) {		List<DetectionResult> detectionResult = queryList(request);		PageVo<DetectionResult> pv = new PageVo<DetectionResult>();		int count = queryCount(request);		pv.setData(detectionResult);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(DetectionResultRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return detectionResultDao.queryCount(param);	}
	@Override	public List<DetectionResult> queryList(DetectionResultRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);
		if(request.getIds()!=null && request.getIds().size()>0){
			param.put("ids", request.getIds());
		}
		param.put("start", null);
		param.put("pageSize", null);
		List<DetectionResult> list = detectionResultDao.query(param);		return list;	}

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
}

