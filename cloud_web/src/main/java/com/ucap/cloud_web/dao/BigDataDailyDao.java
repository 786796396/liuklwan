package com.ucap.cloud_web.dao;


import java.util.List;
import java.util.Map;

import com.publics.util.dao.GenericDao;
import com.ucap.cloud_web.dtoResponse.BigDataDailyResponse;
import com.ucap.cloud_web.entity.BigDataDaily;
/*** <br>* <b>作者：</b>liujc<br>* <b>日期：</b> 2017-02-28 18:11:20 <br>* <b>版权所有：<b>版权所有(C) 2017<br>*/public interface BigDataDailyDao extends GenericDao<BigDataDaily>{	/**
	 * 
	 * @描述:获取 下级地方门户
	 * @作者:liujc@ucap.com.cn
	 * @时间:2017-3-1下午1:37:44 
	 * @param param
	 * @return List<BigDataDailyResponse>
	 */
	List<BigDataDailyResponse> queryOrganizations(Map<String, Object> param);
	/**
	 * 
	 * @描述:获取 本级站点
	 * @作者:liujc@ucap.com.cn
	 * @时间:2017-3-1下午1:37:58 
	 * @param param
	 * @return List<BigDataDailyResponse>
	 */
	List<BigDataDailyResponse> queryNatives(Map<String, Object> param);
}

