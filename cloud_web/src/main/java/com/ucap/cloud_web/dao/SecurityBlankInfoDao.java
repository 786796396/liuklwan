package com.ucap.cloud_web.dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.publics.util.dao.GenericDao;
import com.ucap.cloud_web.dto.SecurityBlankInfoRequest;
import com.ucap.cloud_web.dtoResponse.SecurityGuaranteeResponse;
import com.ucap.cloud_web.entity.SecurityBlankInfo;

	/**
	 * 将条件封装到map集合中，查询数据
	 * @author cuichx --- 2015-11-17下午8:52:19     
	 * @param paramMap
	 * @return
	 */
	List<SecurityBlankInfoRequest> queryByMap(Map<String, Object> paramMap);
	 * @author zhurk --- 2015-12-2下午3:38:52     
	 * @param map
	 * @return           
	*/
	public List<SecurityBlankInfoRequest> getBlankNum(Map<String, Object> map);
	/**
	 * @Description:查询空白个数超过n个 
	 * @author cuichx --- 2016-7-15上午11:14:19     
	 * @param paramMap
	 * @return
	 */
	
	List<SecurityBlankInfoRequest> queryBlankNum(Map<String, Object> paramMap);
	
	/**
	 * @Description:  根据siteCode和ServicePeriodId 查询当前站点空白栏目个数
	 * @author luocheng --- 2017-01-22   
	 * @param paramMap
	 * @return
	 */
	List<SecurityGuaranteeResponse> getBlankNumber(HashMap<String, Object> hashMap);
