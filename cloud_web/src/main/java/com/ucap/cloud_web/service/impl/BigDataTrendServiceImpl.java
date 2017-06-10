package com.ucap.cloud_web.service.impl;


import java.util.List;


/**


	@Autowired







	@Override
	public List<BigDataTrend> sitesList(BigDataTrendRequest request) {
		Map<String, Object> param = QueryUtils.getQueryMap(request);
		List<BigDataTrend> sitesList = bigDataTrendDao.sitesList(param);
		return sitesList;
	}

	@Override
	public List<BigDataTrend> portalList(BigDataTrendRequest request) {
		Map<String, Object> param = QueryUtils.getQueryMap(request);
		List<BigDataTrend> portalList = bigDataTrendDao.portalList(param);
		return portalList;
	}

	@Override
	public List<BigDataTrend> balanceList(BigDataTrendRequest request) {
		Map<String, Object> param = QueryUtils.getQueryMap(request);
		List<BigDataTrend> balanceList = bigDataTrendDao.balanceList(param);
		return balanceList;
	}

