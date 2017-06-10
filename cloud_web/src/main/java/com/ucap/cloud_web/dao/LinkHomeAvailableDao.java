package com.ucap.cloud_web.dao;


import java.util.List;
import java.util.Map;

import com.ucap.cloud_web.dto.LinkHomeAvailableRequest;
import com.ucap.cloud_web.entity.LinkHomeAvailable;
	
	/**
	 * @Description: 分类统计  每种类型的个数 
	 * @author cuichx --- 2017-3-30上午11:33:01     
	 * @param paramMap
	 * @return
	 */
	List<LinkHomeAvailableRequest> queryGroupBy(Map<String, Object> paramMap);
