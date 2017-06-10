package com.ucap.cloud_web.dao;


import java.util.List;
import java.util.Map;

import com.ucap.cloud_web.dto.ConnectionChannelDetailRequest;
import com.ucap.cloud_web.entity.ConnectionChannelDetail;
	/**
	 * 统计每类栏目的个数
	 * @param param
	 * @return
	 */
	List<ConnectionChannelDetailRequest> queryListByGroup(
			Map<String, Object> param);
	/**
	 * 统计每类栏目的个数---总记录数
	 * @param paraMap
	 * @return
	 */
	int queryListByGroupCount(Map<String, Object> paraMap);
