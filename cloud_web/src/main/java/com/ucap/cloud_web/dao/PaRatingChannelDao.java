package com.ucap.cloud_web.dao;


import java.util.List;
import java.util.Map;

import com.ucap.cloud_web.entity.PaRatingChannel;
	/**
	 * linhb　2016-09-05
	 * t通过关联详情  表获取  截图  和   附件
	 * @param pRequest
	 * @return
	 */
	List<PaRatingChannel> getImgsAndAttch(Map<String, Object> param);
	/**
	 * linhb　2016-10-21
	 *  通过 ratingDetail id  获取  栏目信息
	 * @param ratingChannelRequery
	 * @return
	 */
	List<PaRatingChannel> queryJoinList(Map<String, Object> param);

