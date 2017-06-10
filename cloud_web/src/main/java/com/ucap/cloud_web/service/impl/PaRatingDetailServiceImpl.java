package com.ucap.cloud_web.service.impl;


import java.util.List;


/**


	@Autowired







	@Override
	public List<PaRatingDetail> groupByOneName(PaRatingDetailRequest pRequest) {
		Map<String, Object> param = QueryUtils.getQueryMap(pRequest);
		List<PaRatingDetail> list = paRatingDetailDao.groupByOneName(param);
		return list;
	}

	@Override
	public List<PaRatingDetail> getWriteData(PaRatingDetailRequest pRequest) {
		Map<String, Object> param = QueryUtils.getQueryMap(pRequest);
		List<PaRatingDetail> list = paRatingDetailDao.getWriteData(param);
		return list;
	}

