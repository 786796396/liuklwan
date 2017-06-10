package com.ucap.cloud_web.service.impl;


import java.util.List;


/**


	@Autowired







	@Override
	public List<TempReport> joinLinkData(TempReportRequest tRequest) {
		Map<String, Object> param = QueryUtils.getQueryMap(tRequest);
		List<TempReport> list = tempReportDao.joinLinkData(param);
		return list;
	}

	
	@Override
	public PageVo<TempReport> queryLowerSubordinateUnits(
			TempReportRequest tRequest) {
		List<TempReport> tempReport = queryListSubordinateUnits(tRequest);

		PageVo<TempReport> pv = new PageVo<TempReport>();
		int count = queryCountLowerSubordinateUnits(tRequest);

		pv.setData(tempReport);
		pv.setPageNo(tRequest.getPageNo());
		pv.setPageSize(tRequest.getPageSize());
		pv.setRecordSize(count);
		return pv;
	}

	private List<TempReport> queryListSubordinateUnits(
			TempReportRequest tRequest) {
		Map<String, Object> param = QueryUtils.getQueryMap(tRequest);
		List<TempReport> list = tempReportDao.queryqueryListSubordinateUnits(param);
		return list;
	}

	private int queryCountLowerSubordinateUnits(TempReportRequest tRequest) {
		Map<String, Object> param = QueryUtils.getQueryMap(tRequest);
		return tempReportDao.queryCountLowerSubordinateUnits(param);
	}
	
