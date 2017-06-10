package com.ucap.cloud_web.service.impl;


import java.util.List;


/**


	@Autowired






	@Override
	public List<SpotCheckSchedule> queryBatch(SpotCheckScheduleRequest request) {
		Map<String, Object> param = QueryUtils.getQueryMap(request);
		List<SpotCheckSchedule> list = spotCheckScheduleDao.queryBatch(param);
		return list;
	}
	@Override
	public List<SpotCheckSchedule> spotCheckReportUpList(
			SpotCheckScheduleRequest request) {
		// TODO Auto-generated method stub
		Map<String, Object> param = QueryUtils.getQueryMap(request);
		List<SpotCheckSchedule> list = spotCheckScheduleDao.spotCheckReportUpList(param);
		return list;
	}
