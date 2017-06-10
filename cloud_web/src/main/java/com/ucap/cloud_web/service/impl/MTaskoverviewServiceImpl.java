package com.ucap.cloud_web.service.impl;


import java.util.HashMap;
import java.util.List;
import com.ucap.cloud_web.service.IMTaskoverviewService;
import com.ucap.cloud_web.dto.MTaskoverviewRequest;


/**


	@Autowired






	@Override
	public List<Result> queryResultList(MTaskoverviewRequest request) {
		Map<String, Object> param = QueryUtils.getQueryMap(request);
		List<Result> list = mTaskoverviewDao.queryResultList(param);
		return list;
	}

	@Override
	public List<MTaskoverviewRequest> getMTaskoverMap(HashMap<String, Object> hashMap) {
		return mTaskoverviewDao.getMTaskoverMap(hashMap);
	}
