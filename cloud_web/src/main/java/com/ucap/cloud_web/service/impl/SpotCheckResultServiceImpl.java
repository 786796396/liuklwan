package com.ucap.cloud_web.service.impl;


import java.util.List;


/**


	@Autowired






		if(request.getSiteCodes()!=null && request.getSiteCodes().length>0){
			param.put("siteCodes", request.getSiteCodes());
		}

	@Override
	public List<SpotCheckResultRequest> queryByMap(Map<String, Object> params) {
		return spotCheckResultDao.queryByMap(params);
	}

	@Override
	public void deleteBatchBySchedule(int spotCheckSchedule) {
		spotCheckResultDao.deleteBatchBySchedule(spotCheckSchedule);
		
	}

