package com.ucap.cloud_web.service.impl;


import java.util.HashMap;
import java.util.List;


/**


	@Autowired







	@Override
	public List<MonitorSilentResultRequest> getMonResMap(HashMap<String, Object> hashMap) {
		return monitorSilentResultDao.getMonResMap(hashMap);
	}

