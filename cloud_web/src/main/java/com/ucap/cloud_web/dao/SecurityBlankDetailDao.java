package com.ucap.cloud_web.dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ucap.cloud_web.dto.SecurityBlankDetailRequest;
import com.ucap.cloud_web.dtoResponse.SecurityGuaranteeResponse;
import com.ucap.cloud_web.entity.SecurityBlankDetail;import com.publics.util.dao.GenericDao;/*** <br>* <b>作者：</b>SunJiang<br>* <b>日期：</b> 2015-10-30 09:51:21 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/public interface SecurityBlankDetailDao extends GenericDao<SecurityBlankDetail>{

	/**
	* 查询对象集合
	* @param paramMap				前台参数			(必填)
	* @return	List<SecurityBlankDetail>
	*/
	List<SecurityBlankDetailRequest> queryByGroup(Map<String, Object> paramMap);
	/**
	 * @Description: 查询空白栏目详情表、服务周期表、基本信息表
	 * @author cuichx --- 2016-3-6上午9:24:53     
	 * @param hashMap
	 * @return
	 */
	List<SecurityBlankDetailRequest> getBlankNum(HashMap<String, Object> hashMap);
	/**
	 * @Description: 空白栏目统计个数
	 * @author cuichx --- 2016-11-23下午4:06:52     
	 * @param paramMap
	 * @return
	 */
	List<SecurityBlankDetailRequest> queryBlankNum(Map<String, Object> paramMap);
	/**
	 * 空白栏目统计
	 * @param map
	 * @return
	 */
	List<SecurityGuaranteeResponse> getBlankNumber(HashMap<String, Object> map);
	/**
	 * @Description:  根据tree表，查询某个组织单位下  各个填报单位  在某个服务周期内的空白栏目数统计
	 * @author cuichx --- 2017-4-7上午11:32:58     
	 * @param paraMap
	 * @return
	 */
	List<SecurityBlankDetailRequest> queryBlankInfoByTree(
			HashMap<String, Object> paraMap);
	/**
	 * @Description: 根据tree表，查询某个组织单位下  各个填报单位  在某个服务周期内的空白栏目数统计--总记录数
	 * @author cuichx --- 2017-4-7上午11:30:38     
	 * @param paraMap
	 * @return
	 */
	int queryBlankInfoByTreeCount(HashMap<String, Object> paraMap);}

