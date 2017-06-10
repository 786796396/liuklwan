package com.ucap.cloud_web.dao;


import java.util.List;
import java.util.Map;

import com.ucap.cloud_web.dto.DicConfigRequest;
import com.ucap.cloud_web.entity.DicConfig;
	/**
	 * @Description: 获取各省多级别网站个数
	 * @author: yangshuai --- 2016-5-14下午2:27:08
	 * @param map
	 * @return
	 */
	List<DicConfigRequest> queryListByMap(Map<String, Object> map);
