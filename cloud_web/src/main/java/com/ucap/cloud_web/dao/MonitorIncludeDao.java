package com.ucap.cloud_web.dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.publics.util.dao.GenericDao;
import com.ucap.cloud_web.dtoResponse.SearchEngineResponse;
import com.ucap.cloud_web.entity.MonitorInclude;/*** <br>* <b>作者：</b>liujc<br>* <b>日期：</b> 2016-11-01 16:07:23 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public interface MonitorIncludeDao extends GenericDao<MonitorInclude>{

	/**
	 * @描述:查询每个填表单位最大日期
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2016年11月30日下午4:35:31 
	 * @param map
	 * @return 
	 */
	
	public String queryTBMaxDate(Map<String, Object> map);
	/**
	 * 
	 * @描述:连接 tree 表获取数据
	 * @作者:linhb@ucap.com.cn
	 * @时间:2016年12月24日下午2:26:19 
	 * @param param
	 * @return
	 */
	public List<MonitorInclude> queryNatives(Map<String, Object> param);
	/**
	 * 
	 * @描述:点击 检测网站数量 获取数据
	 * @作者:linhb@ucap.com.cn
	 * @时间:2016年12月26日下午2:06:45 
	 * @param sRequest
	 * @return
	 */
	public List<MonitorInclude> querySiteData(Map<String, Object> param);

	/**
	 * @描述:获取搜索引擎收录数据
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2017年3月24日上午10:34:35
	 * @param hashMap
	 * @return
	 */

	public List<SearchEngineResponse> getSearchEngineList(HashMap<String, Object> hashMap);}

