package com.ucap.cloud_web.service.impl;


import java.util.List;


/**


	@Autowired







	@Override
	public List<SpArticle> getSpArtList(SpArticleRequest request) {
		Map<String, Object> param = QueryUtils.getQueryMap(request);
		List<SpArticle> list = spArticleDao.getSpArtList(param);
		return list;
	}

