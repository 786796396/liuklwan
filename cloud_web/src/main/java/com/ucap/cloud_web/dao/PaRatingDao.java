package com.ucap.cloud_web.dao;


import java.util.List;
import java.util.Map;

import com.ucap.cloud_web.entity.PaRating;
import com.publics.util.dao.GenericDao;
	/**
	 * linhb 2016-08-25
	 * 通过  任务id 任务关联 id  获取 自评内容
	 * @param paRatingRequest
	 * @return
	 */
	List<PaRatingDetail> queryFour(Map<String, Object> param);


