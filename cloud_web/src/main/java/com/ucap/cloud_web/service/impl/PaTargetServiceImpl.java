package com.ucap.cloud_web.service.impl;


import java.util.List;


/**


	@Autowired







	@Override
	public List<PaTarget> queryByIds(PaTargetRequest paTargetRequest) {
		Map<String, Object> param = QueryUtils.getQueryMap(paTargetRequest);
		return paTargetDao.queryByIdss(param);
	}

