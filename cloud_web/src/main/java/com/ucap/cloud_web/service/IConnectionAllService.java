package com.ucap.cloud_web.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.publics.util.page.PageVo;
import com.ucap.cloud_web.dto.ConnectionAllRequest;
import com.ucap.cloud_web.dto.xstream.Root;
import com.ucap.cloud_web.entity.ConnectionAll;

/**
 * <br>
 * <b>作者：</b>Nora<br>
 * <b>日期：</b> 2015-10-29 11:19:33 <br>
 * <b>版权所有：<b>版权所有(C) 2015<br>
 */
public interface IConnectionAllService {

	/**
	 * 添加数据
	 * 
	 * @param connectionAll
	 *            对象 (必填)
	 */
	public void add(ConnectionAll connectionAll);

	/**
	 * 通过主键获取对象数据
	 * 
	 * @param id
	 *            主键 (必填)
	 * @return connectionAll
	 */
	public ConnectionAll get(Integer id);
	
	/**
	* 通过对象获取分页数据
	* @param request				dto对象			(必填)
	* @return	PageVo<ServicePeriod>
	*/
	public PageVo<ConnectionAll> query(ConnectionAllRequest request);

	/**
	 * 修改数据
	 * 
	 * @param ConnectionAll
	 *            对象 (必填)
	 */
	public void update(ConnectionAll connectionAll);

	/**
	 * 通过id删除数据
	 * 
	 * @param id
	 *            对象 (必填)
	 */
	public void delete(Integer id);

	/**
	 * 查询对象集合
	 * 
	 * 根据类型和业务时间查询
	 * 
	 * @param request
	 *            前台参数 (必填)
	 * @return List<ConnectionAll>
	 */
	public List<ConnectionAll> queryList(ConnectionAllRequest request);
	
	
	/** @Description: 通过参数查询连通性表的总次数
	 * @author sunjiaqi --- 2015-11-11下午05:59:34     
	 * @param request
	 * @return           
	*/
	public Object queryConnectionSum(ConnectionAllRequest request);
	
	
	/** @Description: 通过参数查询连通性表的超时次数
	 * @author sunjiaqi --- 2015-11-11下午06:02:30     
	 * @param request
	 * @return           
	*/
	public Object queryErrorNumSum(ConnectionAllRequest request);
	
	
	
	/**
	 * @Description: 将参数封装到map中查询连通性统计表
	 * @author cuichx --- 2015-11-9下午8:47:16     
	 * @param map
	 * @return
	 */
	public List<ConnectionAll> queryByMap(Map<String, Object> map);

	/**
	 * @Description: 网站连通性 柱状图
	 * @author sunjiang --- 2015年11月12日下午8:30:15     
	 * @param map
	 * @return
	 */
	public List<ConnectionAllRequest> getHomeBar(Map<String, Object> map);
	/** @Description: 组织单位首页连通性监测结果
	 * @author zhurk --- 2015-12-1下午8:51:35     
	 * @param map
	 * @return           
	*/
	public List<ConnectionAllRequest> getHomeSum(Map<String, Object> map);
	/** @Description: 组织单位业务系统和关键栏目连通性监测结果
	 * @author zhurk --- 2015-12-1下午9:10:47     
	 * @param map
	 * @return           
	*/
	public List<ConnectionAllRequest> getOtherSum(Map<String, Object> map);
	
	/**
	 * @Description: 业务系统连通统计分析
	 * @author sunjiang --- 2016-3-8下午4:44:41     
	 * @param connectionBusinessDetailRequest
	 * @return
	 */
	public List<ConnectionAll> getConnectionAllInfo(ConnectionAllRequest connectionAllRequest);
	/**
	 * @Description: 统计问题条数
	 * @author cuichx --- 2016-3-30下午1:50:59     
	 * @param request
	 * @return
	 */
	int queryCount(ConnectionAllRequest request);
	
	public ConnectionAllRequest queryBetweenDate(ConnectionAllRequest connectionAllRequest);
	public List<ConnectionAllRequest> getSumInfoGroupByName(
			ConnectionAllRequest connectionAllRequest);
	/**
	 * @Description: 连续几天连通比例超过指定数值
	 * @author cuichx --- 2016-6-13上午11:11:20     
	 * @param paramMap
	 * @return
	 */
	public List<ConnectionAllRequest> queryNotConnByMap(
			Map<String, Object> paramMap);
	/**
	 * @Description: 查询首页连不通比例超过3%的数据
	 * @author cuichx --- 2016-7-14上午11:47:13     
	 * @param paramMap
	 * @return
	 */
	public List<ConnectionAll> queryHomePer(Map<String, Object> paramMap);
	/**
	 * 获取7天的不连通占比
	 * @author linhb --- 2016-8-22上午11:47:13     
	 * @param conRequest 
	 * @return
	 */
	public List<ConnectionAll> queryPerSeven(ConnectionAllRequest conRequest);

	/**
	 * @Description: 昨日不连通总次数占连接总次数
	 * @author cuichx --- 2016-8-23下午9:05:01     
	 * @param paramMap
	 * @return
	 */
	public double queryConnAvgByMap(Map<String, Object> paramMap);
	/**
	 * @Description:  首页连不通(站点数）
	 * @author cuichx --- 2016-8-23下午9:21:57     
	 * @param paramMap
	 * @return
	 */
	public int queryConnCountByMap(Map<String, Object> paramMap);
	/**
	 * 获取接口连通性root bean对象  
	 * @author  qinjy
	 * @param siteCode
	 * @param type
	 * @return
	 */
	public Root connectivityByRoot(String siteCode, String dateStr, String type);
	/**
	 * @描述:获取关键栏目接口连通性详情root bean对象    单个栏目的详情
	 * @作者:liujc@ucap.com.cn
	 * @时间:2017-5-19下午4:27:49 
	 * @param encodeurl
	 * @param dateStr
	 * @param code
	 * @param queue
	 * @return
	 */
	public Root channelStatisticsInfo(String encodeurl, String dateStr,String code, String queue);
	/**
	 * 获取接口连通性list数据
	 * @author  qinjy
	 * @param siteCode
	 * @param type
	 * @param list
	 * @return
	 */
	public ArrayList<Object[]> connectivityList(String siteCode, String dateStr, String type, ArrayList<Object[]> list);

	/**
	 * @描述:网站连通率数据
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2016年12月24日上午11:09:53
	 * @param hashMap
	 * @return
	 */

	public List<ConnectionAllRequest> getwebConnectedList(HashMap<String, Object> hashMap);

	/**
	 * @Description: 首页连不通(总次数）
	 * @author cuichx --- 2017-3-13下午2:59:01     
	 * @param paramMap
	 * @return
	 */
	public int queryConnCountByMap2(Map<String, Object> paramMap);
	/**
	 * @描述:统计不同类型的不同站点连不通栏目数--总记录数
	 * @作者:cuichx
	 * @时间:2016年12月24日上午11:09:53
	 * @param hashMap
	 * @return
	 */
	public int getwebConnectedList2Count(HashMap<String, Object> paraMap);
	
	/**
	 * @描述:网站连通率数据
	 * @作者:cuichx
	 * @时间:2016年12月24日上午11:09:53
	 * @param hashMap
	 * @return
	 */
	public List<ConnectionAllRequest> getwebConnectedList2(HashMap<String, Object> hashMap);


}
