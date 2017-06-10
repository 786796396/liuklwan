package com.ucap.cloud_web.dao;


import java.util.List;
import java.util.Map;

import com.ucap.cloud_web.dto.ServicePeriodRequest;
import com.ucap.cloud_web.entity.ServicePeriod;import com.publics.util.dao.GenericDao;/*** <br>* <b>作者：</b>SunJiang<br>* <b>日期：</b> 2015-10-30 09:51:21 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/public interface ServicePeriodDao extends GenericDao<ServicePeriod>{
	/**
	 * 将参数封装到map集合中查询对象集合
	 * @param paramMap
	 * @return List<ServicePeriodRequest> 
	 */
	List<ServicePeriodRequest> queryByGroup(Map<String, Object> paramMap);
	
	
	/**
	 * 根据组织单位isteCode查询其周期
	 */
	List<ServicePeriodRequest> queryByRelationPeriod(Map<String, Object> param);

	/**
	 * 获取高级版服务周期
	 * @param paramMap
	 * @return List<ServicePeriod> 
	 */
	List<ServicePeriod> queryAdvanceService(Map<String, Object> map);}

