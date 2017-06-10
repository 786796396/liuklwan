package com.ucap.cloud_web.dao;


import java.util.List;
import java.util.Map;

import com.ucap.cloud_web.entity.PaRating;import com.ucap.cloud_web.entity.PaRatingDetail;
import com.publics.util.dao.GenericDao;/*** <br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-08-23 10:59:23 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public interface PaRatingDao extends GenericDao<PaRating>{
	/**
	 * linhb 2016-08-25
	 * 通过  任务id 任务关联 id  获取 自评内容
	 * @param paRatingRequest
	 * @return
	 */
	List<PaRatingDetail> queryFour(Map<String, Object> param);

}

