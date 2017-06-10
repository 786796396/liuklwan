package com.ucap.cloud_web.dao;


import java.util.HashMap;
import java.util.List;

import com.publics.util.dao.GenericDao;
import com.ucap.cloud_web.dtoResponse.ConnectionAllHomeResponse;
import com.ucap.cloud_web.entity.ConnectionAllHome;

	/**
	 * @描述:获取首页连通性数据
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2017年3月30日下午3:29:47
	 * @param hashMap
	 * @return
	 */

	List<ConnectionAllHomeResponse> getConnectionAllHomeList(HashMap<String, Object> hashMap);

