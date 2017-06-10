package com.ucap.cloud_web.dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.publics.util.dao.GenericDao;
import com.ucap.cloud_web.dto.SecurityBlankInfoRequest;
import com.ucap.cloud_web.dto.SecurityResponseRequest;
import com.ucap.cloud_web.dtoResponse.SecurityGuaranteeResponse;
import com.ucap.cloud_web.entity.SecurityResponse;
/*** <br>* <b>作者：</b>SunJiang<br>* <b>日期：</b> 2015-10-30 09:51:21 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/public interface SecurityResponseDao extends GenericDao<SecurityResponse>{	
	/**
	* 查询对象集合--将参数封装到map中
	* @param map				前台参数			(必填)
	* @return	List<SecurityResponseRequest>
	*/
	List<SecurityResponseRequest> queryByMap(Map<String, Object> map);
/**
	 * @Description: 内容保障分析，互动回应差柱状图
	 * @author sunjiang --- 2015年11月19日下午7:03:17     
	 * @param param
	 * @return
	 */
	public List<SecurityBlankInfoRequest> queryBarNum(Map<String,Object> param);

	/**
	* 查询对象集合--将参数封装到map中
	* @param map				前台参数			(必填)
	* @return	List<SecurityResponseRequest>
	*/
	List<SecurityResponseRequest> queryByGroup(Map<String, Object> paramMap);
	
	List<SecurityResponseRequest> queryListByChannelName(Map<String, Object> paramMap);
	/** @Description: 组织单位-当前监测结果：互动回应差获取问题个数
	 * @author zhurk --- 2015-12-2下午4:31:48     
	 * @param paramMap
	 * @return           
	*/
	List<SecurityResponseRequest> getProblemNum(Map<String, Object> paramMap);
	/**
	 * @Description: 查询互动回应差数据
	 * @author cuichx --- 2016-10-25下午3:20:14     
	 * @param paramMap
	 * @return
	 */
	List<SecurityResponseRequest> queryResponseNum(Map<String, Object> paramMap);
	
	/**
	 * @Description: 根据siteCode和ServicePeriodId 查询当前站点互动回应差栏目个数
	 * @author luocheng --- 2017-01-22    
	 * @param paramMap
	 * @return
	 */
	List<SecurityGuaranteeResponse> getResponseNumber(HashMap<String, Object> hashMap);
	/**
	 * @Description: 根据tree表获取该组织单位下的 互动回应差的数据
	 * @author cuichx --- 2017-4-6下午3:03:22     
	 * @param paraMap
	 * @return
	 */
	List<SecurityResponseRequest> queryInfoByTree(
			HashMap<String, Object> paraMap);
	/**
	 * @Description: 根据tree表获取该组织单位下的 互动回应差的数据--总记录数
	 * @author cuichx --- 2017-4-6下午3:02:09     
	 * @param paraMap
	 * @return
	 */
	int queryInfoByTreeCount(HashMap<String, Object> paraMap);

}

