package com.ucap.cloud_web.dao;


import java.util.List;
import java.util.Map;

import com.ucap.cloud_web.entity.TempReport;import com.publics.util.dao.GenericDao;/*** <br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-09-13 11:30:36 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public interface TempReportDao extends GenericDao<TempReport>{
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
	int queryCountLowerSubordinateUnits(Map<String, Object> param);}

