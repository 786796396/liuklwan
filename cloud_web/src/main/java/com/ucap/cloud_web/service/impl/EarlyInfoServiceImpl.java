package com.ucap.cloud_web.service.impl;


import java.util.List;

import com.publics.util.page.PageVo;


/**


	@Autowired





		if(!CollectionUtils.isEmpty(request.getDatabaseInfoList())){
			param.put("databaseInfoList", request.getDatabaseInfoList());
		}

		if(!CollectionUtils.isEmpty(request.getDatabaseInfoList())){
			param.put("databaseInfoList", request.getDatabaseInfoList());
		}
	@Override
	public List<EarlyInfo> queryEarlyInfoList(EarlyInfoRequest request) {
		Map<String, Object> param = QueryUtils.getQueryMap(request);
		if(!CollectionUtils.isEmpty(request.getDatabaseInfoList())){
			param.put("databaseInfoList", request.getDatabaseInfoList());
		}
		List<EarlyInfo> list = earlyInfoDao.queryEarlyInfo(param);
		return list;
	}


	@Override
	public EarlyInfo querySum(Map<String, Object> param) {
		return earlyInfoDao.querySum(param);
	}

	@Override
	public PageVo<EarlyInfo> queryEarlyInfo(EarlyInfoRequest request) {
		
		Map<String, Object> param = QueryUtils.getQueryMap(request);
		if(!CollectionUtils.isEmpty(request.getDatabaseInfoList())){
			param.put("databaseInfoList", request.getDatabaseInfoList());
		}
		List<EarlyInfo> earlyInfo = earlyInfoDao.queryEarlyInfo(param);

		PageVo<EarlyInfo> pv = new PageVo<EarlyInfo>();
		
		int count = queryEarlyInfoCount(request);
		
		pv.setData(earlyInfo);
		pv.setPageNo(request.getPageNo());
		pv.setPageSize(request.getPageSize());
		pv.setRecordSize(count);
		return pv;
	}

	@Override
	public void updateNewEarlyNum(EarlyInfo earlyInfo) {
		earlyInfoDao.updateNewEarlyNum(earlyInfo);
	}

	@Override
	public EarlyInfo queryNewEarlyNum(Map<String, Object> param) {
		return earlyInfoDao.queryNewEarlyNum(param);
	}

	@Override
	public int queryEarlyInfoCount(EarlyInfoRequest request) {
		return earlyInfoDao.queryEarlyInfoCount(request);
	}
}
