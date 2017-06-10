package com.ucap.cloud_web.dao;


import java.util.List;
import java.util.Map;

import com.ucap.cloud_web.dto.BigDataTrendRequest;
import com.ucap.cloud_web.entity.BigDataTrend;

	//查询地方群站信息集合
	List<BigDataTrend> sitesList(Map<String, Object> param);
	
	//查询地方门户信息集合
	List<BigDataTrend> portalList(Map<String, Object> param);
	
	//查询本级站点信息集合
	List<BigDataTrend> balanceList(Map<String, Object> param);

	
	
