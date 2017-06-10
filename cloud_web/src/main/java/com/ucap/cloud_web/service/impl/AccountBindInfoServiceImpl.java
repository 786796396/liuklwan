package com.ucap.cloud_web.service.impl;


import java.util.List;


/**


	@Autowired






	@Override
	public AccountBindInfoRequest queryByMap(Map<String, Object> paramMap) {
		return accountBindInfoDao.queryByMap(paramMap);
	}
	@Override
	public List<AccountBindInfoRequest> queryBySiteCode(Map<String, Object> map) {
		return accountBindInfoDao.queryBySiteCode(map);
	}

	@Override
	public List<AccountBindInfoRequest> queryEarlyInfo(
			Map<String, Object> earlyMap) {
		return accountBindInfoDao.queryEarlyInfo(earlyMap);
	}



	@Override
	public List<AccountBindInfoRequest> queryTBSite(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return accountBindInfoDao.queryTBSite(paramMap);
	}

	@Override
	public List<AccountBindInfoRequest> queryTBByMap(
			Map<String, Object> paramMap) {
		return accountBindInfoDao.queryTBByMap(paramMap);
	}

	@Override
	public List<AccountBindInfo> queryListByMap(Map<String, Object> paramMap) {
		return accountBindInfoDao.queryListByMap(paramMap);
	}
