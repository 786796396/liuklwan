package com.ucap.cloud_web.service.impl;


import java.util.HashMap;
import java.util.List;


/**


	@Autowired







	@Override
	public IndexCountRequest queryByMap(HashMap<String, Object> param) {
		return indexCountDao.queryByMap(param);
	}

	@Override
	public List<IndexCountRequest> queryListByMap(HashMap<String, Object> param) {
		return indexCountDao.queryListByMap(param);
	}

	@Override
	public List<IndexCount> getLineList(HashMap<String, Object> param) {
		return indexCountDao.getLineList(param);
	}

	@Override
	public List<IndexCountRequest> getListByMap(HashMap<String, Object> param) {
		
		return indexCountDao.getListByMap(param);
	}

