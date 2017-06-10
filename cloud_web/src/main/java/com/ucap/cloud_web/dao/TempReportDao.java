package com.ucap.cloud_web.dao;


import java.util.List;
import java.util.Map;

import com.ucap.cloud_web.entity.TempReport;
	/**
	 * linhb 2016-09-13
	 * 组织单位按照条件查询
	 * @param tRequest
	 * @return 
	 */
	
	List<TempReport> joinLinkData(Map<String, Object> param);
	
	
	/**
	 * liukl 2016年12月29日20:41:25
	 * 下级单位数据
	 * @param tRequest
	 * @return 
	 */
	List<TempReport> queryqueryListSubordinateUnits(Map<String, Object> param);

	/**
	 * liukl 2016年12月29日20:41:25
	 * 下级单位数据总数
	 * @param tRequest
	 * @return 
	 */
	int queryCountLowerSubordinateUnits(Map<String, Object> param);
