package com.ucap.cloud_web.service.impl;


import java.util.List;


/**


	@Autowired






		if(request.getCurrentNextSiteCode()!=null && request.getCurrentNextSiteCode().size()>0){
			param.put("currentNextSiteCode", request.getCurrentNextSiteCode());
		}
		List<ReportManageLog> list = reportManageLogDao.query(param);

	@Override
	public List<ReportManageLogRequest> queryByMap(Map<String, Object> paramMap) {
		return reportManageLogDao.queryByMap(paramMap);
	}

	@Override
	public ReportManageLogRequest querySum(Map<String, Object> param) {
		return reportManageLogDao.querySum(param);
	}

	@Override
	public List<ReportManageLogRequest> queryListByMap(Map<String, Object> map) {
		return reportManageLogDao.queryListByMap(map);
	}



