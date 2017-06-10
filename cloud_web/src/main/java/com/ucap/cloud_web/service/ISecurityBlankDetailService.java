package com.ucap.cloud_web.service;


import java.util.HashMap;
import java.util.List;

import com.publics.util.page.PageVo;
import com.ucap.cloud_web.entity.SecurityBlankDetail;


/**


	/**

	/**

	/**

	/**

	/**

	/**

	/**
	/**
	* 查询对象集合
	* @param paramMap				前台参数			(必填)
	* @return	List<SecurityBlankDetail>
	*/
	public List<SecurityBlankDetailRequest> queryByGroup(
			Map<String, Object> paramMap);

	/**
	 * @Description: 查询空白栏目详情表、服务周期表、基本信息表
	 * @author cuichx --- 2016-3-6上午9:22:17     
	 * @param hashMap
	 * @return
	 */
	public List<SecurityBlankDetailRequest> getBlankNum(
			HashMap<String, Object> hashMap);
	/**
	 * @Description: 空白栏目统计个数
	 * @author cuichx --- 2016-11-23下午4:04:58     
	 * @param paramMap
	 * @return
	 */
	public List<SecurityBlankDetailRequest> queryBlankNum(
			Map<String, Object> paramMap);

	/**
	 * 空表栏目统计
	 * @param map
	 * @return
	 */
	public List<SecurityGuaranteeResponse> getBlankNumber(
			HashMap<String, Object> map);
	/**
	 * @Description: 根据tree表，查询某个组织单位下  各个填报单位  在某个服务周期内的空白栏目数统计
	 * @author cuichx --- 2017-4-7上午11:30:38     
	 * @param paraMap
	 * @return
	 */
	public List<SecurityBlankDetailRequest> queryBlankInfoByTree(
			HashMap<String, Object> paraMap);
	/**
	 * @Description: 根据tree表，查询某个组织单位下  各个填报单位  在某个服务周期内的空白栏目数统计--总记录数
	 * @author cuichx --- 2017-4-7上午11:30:38     
	 * @param paraMap
	 * @return
	 */
	public int queryBlankInfoByTreeCount(HashMap<String, Object> paraMap);

}
