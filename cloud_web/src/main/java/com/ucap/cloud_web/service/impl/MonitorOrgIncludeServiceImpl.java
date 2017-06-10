package com.ucap.cloud_web.service.impl;


import java.util.HashMap;
import java.util.List;

import com.ucap.cloud_web.entity.JcVisitOrg;
import com.ucap.cloud_web.entity.MonitorOrgInclude;


/**


	@Autowired






	@Override
	public String queryMaxDate(HashMap<String, Object> map){
		return monitorOrgIncludeDao.queryMaxDate(map);
	}

	@Override
	public List<MonitorOrgInclude> getOrgData(MonitorOrgIncludeRequest rRequest) {
		Map<String, Object> param = QueryUtils.getQueryMap(rRequest);
		List<MonitorOrgInclude> list = monitorOrgIncludeDao.getOrgData(param);
		return list;
	}
