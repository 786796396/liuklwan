package com.ucap.cloud_web.service.impl;


import java.util.List;


/**


	@Autowired





		if(request.getWebsiteList()!=null && request.getWebsiteList().size()>0 ){
			param.put("websiteList", request.getWebsiteList());
		}
		if(request.getLinkList()!=null && request.getLinkList().size()>0){
			param.put("linkList", request.getLinkList());
		}
		return earlyDetailDao.queryCount(param);

		if(request.getWebsiteList()!=null && request.getWebsiteList().size()>0 ){
			param.put("websiteList", request.getWebsiteList());
		}
		if(request.getLinkList()!=null && request.getLinkList().size()>0){
			param.put("linkList", request.getLinkList());
		}

	@Override
	public List<EarlyDetailRequest> queryByMap(Map<String, Object> paramMap) {
		return earlyDetailDao.queryByMap(paramMap);
	}
	@Override
	public List<EarlyDetail> queryDailyInfo(EarlyDetailRequest request) {
		Map<String, Object> param = QueryUtils.getQueryMap(request);

		List<EarlyDetail> list = earlyDetailDao.queryDailyInfo(param);
		return list;
	}

	@Override
	public List<EarlyDetail> querySiteEarlyInfo(EarlyDetailRequest request) {
		// TODO Auto-generated method stub
		Map<String, Object> param = QueryUtils.getQueryMap(request);
		return earlyDetailDao.querySiteEarlyInfo(param);
	}

	@Override
	public List<EarlyDetail> queryDatas(EarlyDetailRequest request) {
		Map<String, Object> param = QueryUtils.getQueryMap(request);
		return earlyDetailDao.queryDatas(param);
	}

	@Override
	public List<EarlyDetail> queryNoConDatas(EarlyDetailRequest eRequest) {
		Map<String, Object> param = QueryUtils.getQueryMap(eRequest);
		return earlyDetailDao.queryNoConDatas(param);
	}
}
