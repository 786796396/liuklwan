package com.ucap.cloud_web.service.impl;


import java.util.List;


/**


	@Autowired







	@Override
	public List<PaRatingChannel> getImgsAndAttch(PaRatingChannelRequest pRequest) {
		Map<String, Object> param = QueryUtils.getQueryMap(pRequest);
		List<PaRatingChannel> list = paRatingChannelDao.getImgsAndAttch(param);
		return list;
	}

	@Override
	public List<PaRatingChannel> queryJoinList(PaRatingChannelRequest ratingChannelRequery) {
		Map<String, Object> param = QueryUtils.getQueryMap(ratingChannelRequery);
		List<PaRatingChannel> list = paRatingChannelDao.queryJoinList(param);
		return list;
	}

