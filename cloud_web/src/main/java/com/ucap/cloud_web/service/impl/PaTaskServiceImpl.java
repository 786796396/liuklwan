package com.ucap.cloud_web.service.impl;


import java.util.List;


/**


	@Autowired







	@Override
	public List<PaTask> queryJoinTarget(PaTaskRequest pRequest) {
		Map<String, Object> param = QueryUtils.getQueryMap(pRequest);
		return paTaskDao.queryJoinTarget(param);
	}

