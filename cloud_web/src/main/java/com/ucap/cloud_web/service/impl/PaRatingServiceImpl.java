package com.ucap.cloud_web.service.impl;


import java.util.List;
import com.ucap.cloud_web.service.IPaRatingService;


/**


	@Autowired







	@Override
	public List<PaRatingDetail> queryFour(PaRatingRequest paRatingRequest) {
		Map<String, Object> param = QueryUtils.getQueryMap(paRatingRequest);
		return paRatingDao.queryFour(param);
	}

