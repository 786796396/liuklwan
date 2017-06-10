package com.ucap.cloud_web.service;

import java.util.List;
import java.util.Map;

import com.publics.util.page.PageVo;
import com.ucap.cloud_web.dto.ServicePeriodRequest;
import com.ucap.cloud_web.entity.ServicePeriod;


/**


	/**

	/**

	/**

	/**

	/**

	/**

	/**
	
	/**
	 * 将参数封装到map集合中查询对象集合
	 * @param paramMap
	 * @return List<ServicePeriodRequest> 
	 */
	public List<ServicePeriodRequest> queryByGroup(Map<String, Object> paramMap);

	/**
	 * 根据组织单位id查询其服务周期链接relationPeriod表
	 * @param siteCode
	 * @return
	 */
	public List<ServicePeriodRequest> queryByRelationPeriod(ServicePeriodRequest request);

	public List<ServicePeriodRequest> queryByRelationPeriodBasic(
			Map<String, Object> param);
	/**
	 * 获取高级版服务周期
	 * @param paramMap
	 * @return List<ServicePeriod> 
	 */
	public List<ServicePeriod> queryAdvanceService(Map<String, Object> map);

}
