package com.ucap.cloud_web.service.impl;


import java.util.List;


/**


	@Autowired







	@Override
	public void deleteByTbNameAndId(SysAttachRequest sRequest) {
		Map<String, Object> param = QueryUtils.getQueryMap(sRequest);
		sysAttachDao.deleteByTbNameAndId(param);
		
	}

