package com.ucap.cloud_web.dao;


import java.util.List;
import java.util.Map;

import com.ucap.cloud_web.dto.EarlyDetailRequest;
import com.ucap.cloud_web.dto.EarlyDetailTempRequest;
import com.ucap.cloud_web.entity.EarlyDetailTemp;import com.publics.util.dao.GenericDao;/*** <br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2017-03-14 14:39:10 <br>* <b>版权所有：<b>版权所有(C) 2017<br>*/public interface EarlyDetailTempDao extends GenericDao<EarlyDetailTemp>{

	/**
	 * @Description: 获取实时预警信息
	 * @author cuichx --- 2017-3-14下午3:33:33     
	 * @param earlyDetailRequest
	 * @return
	 */
	List<EarlyDetailTemp> querySiteEarlyInfo(
			EarlyDetailTempRequest earlyDetailTempRequest);
	/**
	 * @Description:   获取今天所有首页连不通数据
	 * @author: cuichx --- 2017年4月13日下午5:02:53
	 * @param paraMap
	 * @return
	 */
	List<EarlyDetailTempRequest> queryEarlyTempByMap(Map<String, Object> paraMap);
	/**
	 * @Description:   获取今天所有首页连不通数据----记录数
	 * @author: cuichx --- 2017年4月13日下午5:02:53
	 * @param paraMap
	 * @return
	 */
	int queryEarlyTempByMapCount(Map<String, Object> paraMap);
	/**
	 * @Description:   获取今天所有首页连不通数据----记录数
	 * @author: cuichx --- 2017年4月13日下午5:02:53
	 * @param paraMap
	 * @return
	 */
	List<EarlyDetailTempRequest> queryEarlyTempByMapCountTb(Map<String, Object> paraMap);}

