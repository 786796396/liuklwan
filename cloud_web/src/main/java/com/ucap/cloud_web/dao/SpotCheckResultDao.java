package com.ucap.cloud_web.dao;


import java.util.List;
import java.util.Map;

import com.ucap.cloud_web.dto.SpotCheckResultRequest;
import com.ucap.cloud_web.entity.SpotCheckResult;

	/**
	 * @Description: 获取每个省的抽查站点个数
	 * @author cuichx --- 2016-5-3下午7:17:36     
	 * @param params
	 * @return
	 */
	List<SpotCheckResultRequest> queryByMap(Map<String, Object> params);
	
	/**
	 * 按抽查进度批量删除
	 * @param spotCheckSchedule
	 */
	void deleteBatchBySchedule(int spotCheckSchedule);
