package com.ucap.cloud_web.dao;


import java.util.List;
import java.util.Map;

import com.ucap.cloud_web.entity.PaRatingChannel;import com.publics.util.dao.GenericDao;/*** <br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-08-23 10:59:23 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public interface PaRatingChannelDao extends GenericDao<PaRatingChannel>{
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
}

