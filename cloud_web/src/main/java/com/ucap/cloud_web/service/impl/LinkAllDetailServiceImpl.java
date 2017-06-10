package com.ucap.cloud_web.service.impl;


import java.util.List;


/**


	@Autowired





		if(request.getErrorCodeArr()!=null && request.getErrorCodeArr().length>0){
			param.put("errorCodeArr", request.getErrorCodeArr());
		}

		if(request.getErrorCodeArr()!=null && request.getErrorCodeArr().length>0){
			param.put("errorCodeArr", request.getErrorCodeArr());
		}
		return list;
	public int queryNoExceptCount(LinkAllDetailRequest request) {
		Map<String, Object> param = QueryUtils.getQueryMap(request);
		if(request.getErrorCodeArr()!=null && request.getErrorCodeArr().length>0){
			param.put("errorCodeArr", request.getErrorCodeArr());
		}
		return linkAllDetailDao.queryNoExceptCount(param);
	}
	@Override
	public List<LinkAllDetail> queryNoExceptList(LinkAllDetailRequest request) {
		Map<String, Object> param = QueryUtils.getQueryMap(request);
		List<LinkAllDetail> list = linkAllDetailDao.queryNoExceptList(param);
		return list;
	}

	@Override
	public PageVo<LinkAllDetail> queryNoExcept(LinkAllDetailRequest request) {
		List<LinkAllDetail> linkAllDetail = queryNoExceptList(request);

		PageVo<LinkAllDetail> pv = new PageVo<LinkAllDetail>();
		int count = queryNoExceptCount(request);

		pv.setData(linkAllDetail);
		pv.setPageNo(request.getPageNo());
		pv.setPageSize(request.getPageSize());
		pv.setRecordSize(count);
		return pv;
	}

	@Override
	public List<LinkAllDetailRequest> querySumGroupBy(
			Map<String, Object> paramMap) {
		return linkAllDetailDao.querySumGroupBy(paramMap);
	}

	@Override
	public List<LinkAllDetailRequest> queryByCode(Map<Object, Object> paramMap) {
		return linkAllDetailDao.queryByCode(paramMap);
	}
