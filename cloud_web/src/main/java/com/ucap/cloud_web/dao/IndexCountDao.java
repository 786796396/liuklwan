package com.ucap.cloud_web.dao;


import java.util.HashMap;
import java.util.List;

import com.ucap.cloud_web.dto.IndexCountRequest;
import com.ucap.cloud_web.entity.IndexCount;

	/**
	 * @Description: 通过网站标识码+昨天日期，查询健康指数统计表，获取健康总分数
	 * @author cuichx --- 2016-1-5下午5:13:18     
	 * @param param
	 * @return
	 */
	IndexCountRequest queryByMap(HashMap<String, Object> param);
	/**
	 * @Description: 查询健康指数统计表
	 * @author cuichx --- 2016-1-5下午8:54:37     
	 * @param param
	 * @return
	 */
	List<IndexCountRequest> queryListByMap(HashMap<String, Object> param);
	/**
	 * @Description: 健康指数折线图
	 * @author sunjiang --- 2016-1-6下午4:45:54     
	 * @param param
	 * @return
	 */
	/**
	 * @Description: 获取指定套餐类型的数据
	 * @author cuichx --- 2016-1-10下午5:44:57     
	 * @param param
	 * @return
	 */
	List<IndexCountRequest> getListByMap(HashMap<String, Object> param);
