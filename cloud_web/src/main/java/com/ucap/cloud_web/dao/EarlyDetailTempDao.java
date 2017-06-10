package com.ucap.cloud_web.dao;


import java.util.List;
import java.util.Map;

import com.ucap.cloud_web.dto.EarlyDetailRequest;
import com.ucap.cloud_web.dto.EarlyDetailTempRequest;
import com.ucap.cloud_web.entity.EarlyDetailTemp;

	/**
	 * @Description: 获取实时预警信息
	 * @author cuichx --- 2017-3-14下午3:33:33     
	 * @param earlyDetailRequest
	 * @return
	 */
	List<EarlyDetailTemp> querySiteEarlyInfo(
			EarlyDetailTempRequest earlyDetailTempRequest);
	/**
	 * @Description:   获取今天所有首页连不通数据
	 * @author: cuichx --- 2017年4月13日下午5:02:53
	 * @param paraMap
	 * @return
	 */
	List<EarlyDetailTempRequest> queryEarlyTempByMap(Map<String, Object> paraMap);
	/**
	 * @Description:   获取今天所有首页连不通数据----记录数
	 * @author: cuichx --- 2017年4月13日下午5:02:53
	 * @param paraMap
	 * @return
	 */
	int queryEarlyTempByMapCount(Map<String, Object> paraMap);
	/**
	 * @Description:   获取今天所有首页连不通数据----记录数
	 * @author: cuichx --- 2017年4月13日下午5:02:53
	 * @param paraMap
	 * @return
	 */
	List<EarlyDetailTempRequest> queryEarlyTempByMapCountTb(Map<String, Object> paraMap);
