package com.ucap.cloud_web.service.impl;


import java.util.List;
import com.ucap.cloud_web.service.IPaProjectService;


/**


	@Autowired







	@Override
	public List<PaTask> queryTaskList(PaProjectRequest paProjectRequest) {
		Map<String, Object> param = QueryUtils.getQueryMap(paProjectRequest);
		List<PaTask> list = paProjectDao.queryTaskList(param);
		return list;
	}

