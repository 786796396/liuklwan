package com.ucap.cloud_web.service.impl;


import java.util.HashMap;
import java.util.List;


/**


	@Autowired







	@Override
	public List<MonitorSilentOrgResult> getMonitorSilentList(HashMap<String, Object> hashMap) {
		return monitorSilentOrgResultDao.getMonitorSilentList(hashMap);
	}

