package com.ucap.cloud_web.dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.publics.util.dao.GenericDao;
import com.ucap.cloud_web.dto.ConnectionAllRequest;
import com.ucap.cloud_web.entity.ConnectionAll;
/*** <br>* <b>作者：</b>Nora<br>* <b>日期：</b> 2015-10-29 11:19:33 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/public interface ConnectionAllDao extends GenericDao<ConnectionAll>{

	/**
	 * @Description: 将参数封装到map中查询连通性统计表
	 * @author cuichx --- 2015-11-9下午8:49:09     
	 * @param map
	 * @return
	 */
	List<ConnectionAll> queryByMap(Map<String, Object> map);
	
	public int queryConnectionSum(Map<String,Object> map);
	
	public int queryErrorNumSum(Map<String,Object> map);
	/**
	 * @Description: 网站连通性 柱状图
	 * @author sunjiang --- 2015年11月12日下午8:30:15     
	 * @param map
	 * @return
	 */
	public List<ConnectionAllRequest> getHomeBar(Map<String, Object> map);	/** @Description: 组织单位首页连通性监测结果
	 * @author zhurk --- 2015-12-1下午8:51:35     
	 * @param map
	 * @return           
	*/
	public List<ConnectionAllRequest> getHomeSum(Map<String, Object> map);	public ConnectionAllRequest queryBetweenDate(Map<String, Object> map);	/** @Description: 组织单位业务系统和关键栏目连通性监测结果
	 * @author zhurk --- 2015-12-1下午9:09:58     
	 * @param map
	 * @return           
	*/
	public List<ConnectionAllRequest> getOtherSum(Map<String, Object> map);
	public List<ConnectionAllRequest> getSumInfoGroupByName(Map<String, Object> map);
	
	/**
	 * @Description: 业务系统连通统计分析
	 * @author sunjiang --- 2016-3-8下午4:44:41     
	 * @param connectionBusinessDetailRequest
	 * @return
	 */
	public List<ConnectionAll> getConnectionAllInfo(ConnectionAllRequest connectionAllRequest);

	/**
	 * @Description: 连续几天连通比例超过指定数值
	 * @author cuichx --- 2016-6-13上午11:13:11     
	 * @param paramMap
	 * @return
	 */
	List<ConnectionAllRequest> queryNotConnByMap(Map<String, Object> paramMap);

	/**
	 * @Description: 查询首页连不通比例超过3%的数据
	 * @author cuichx --- 2016-7-14上午11:48:49     
	 * @param paramMap
	 * @return
	 */
	List<ConnectionAll> queryHomePer(Map<String, Object> paramMap);

	/**
	 * 获取7天的不连通占比
	 * @author linhb --- 2016-8-22上午11:47:13     
	 * @param conRequest 
	 * @return
	 */
	List<ConnectionAll> queryPerSeven(Map<String, Object> param);

	/**
	 * @Description: 不连通总次数占连接总次数
	 * @author cuichx --- 2016-8-23下午9:06:04     
	 * @param paramMap
	 * @return
	 */
	double queryConnAvgByMap(Map<String, Object> paramMap);

	/**
	 * @Description: 昨日首页100%连不通的站点个数
	 * @author cuichx --- 2016-8-23下午9:23:07     
	 * @param paramMap
	 * @return
	 */
	int queryConnCountByMap(Map<String, Object> paramMap);

	/**
	 * @描述:网站连通率数据
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2016年12月24日上午11:09:53
	 * @param hashMap
	 * @return
	 */

	List<ConnectionAllRequest> getwebConnectedList(HashMap<String, Object> hashMap);

	/**
	 * @Description:首页连不通(总次数） 
	 * @author cuichx --- 2017-3-13下午3:01:56     
	 * @param paramMap
	 * @return
	 */
	int queryConnCountByMap2(Map<String, Object> paramMap);
	/**
	 * @描述:网站连通率数据--总记录数
	 * @作者:cuichx
	 * @时间:2016年12月24日上午11:09:53
	 * @param hashMap
	 * @return
	 */
	int getwebConnectedList2Count(HashMap<String, Object> paraMap);

	/**
	 * @描述:统计不同类型的不同站点连不通栏目数--总记录数
	 * @作者:cuichx
	 * @时间:2016年12月24日上午11:09:53
	 * @param hashMap
	 * @return
	 */
	List<ConnectionAllRequest> getwebConnectedList2(
			HashMap<String, Object> hashMap);

		}

