package com.ucap.cloud_web.dao;


import java.util.List;
import java.util.Map;

import com.publics.util.dao.GenericDao;
import com.ucap.cloud_web.dtoResponse.BigOrgDailyResponse;
import com.ucap.cloud_web.entity.BigOrgDaily;
/**
	 * 
	 * @param rRequest 
	 * @描述:获取下级战群的数据
	 * @作者:linhb@ucap.com.cn
	 * @时间:2016年12月13日下午4:21:54
	 */
	List<BigOrgDailyResponse> getOrgData(Map<String, Object> param);
