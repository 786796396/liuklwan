package com.ucap.cloud_web.service.impl;


import java.util.List;


/**


	@Autowired






	
	@Override
	public List<DicConfigRequest> queryListByMap(Map<String, Object> map) {
		return dicConfigDao.queryListByMap(map);
	}

