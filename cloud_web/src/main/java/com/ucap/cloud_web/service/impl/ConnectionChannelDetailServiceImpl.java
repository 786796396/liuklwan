package com.ucap.cloud_web.service.impl;


import java.util.List;


/**


	@Autowired






		List<ConnectionChannelDetail> list = connectionChannelDetailDao.query(param);

	@Override
	public List<ConnectionChannelDetailRequest> queryListByGroup(
			Map<String, Object> paraMap) {
		return connectionChannelDetailDao.queryListByGroup(paraMap);
	}

	@Override
	public int queryListByGroupCount(Map<String, Object> paraMap) {
		return connectionChannelDetailDao.queryListByGroupCount(paraMap);
	}

