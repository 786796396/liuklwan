package com.ucap.cloud_web.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.publics.util.page.PageVo;
import com.ucap.cloud_web.dto.MonitorIncludeRequest;
import com.ucap.cloud_web.dtoResponse.SearchEngineResponse;
import com.ucap.cloud_web.entity.MonitorInclude;


/*** <br>* <b>作者：</b>liujc<br>* <b>日期：</b> 2016-11-01 16:07:23 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public interface IMonitorIncludeService {


	/**	* 添加数据	* @param monitorInclude			对象			(必填)	*/	public int add(MonitorInclude monitorInclude);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return monitorInclude	*/	public MonitorInclude get(Integer id);

	/**	* 修改数据	* @param MonitorInclude			对象			(必填)	*/	public void update(MonitorInclude monitorInclude);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<MonitorInclude>	*/	public PageVo<MonitorInclude> query(MonitorIncludeRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(MonitorIncludeRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<MonitorInclude>	*/	public List<MonitorInclude> queryList(MonitorIncludeRequest request);

	/**
	 * @描述:查询每个填报单位最大日期
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2016年11月30日下午4:33:27 
	 * @param map
	 * @return 
	 */
	
	public String queryTBMaxDate(Map<String, Object> map);
	/**
	 * 
	 * @描述:联合  tree 表  获取数据
	 * @作者:linhb@ucap.com.cn
	 * @时间:2016年12月24日下午2:22:22 
	 * @param sRequest
	 * @return
	 */
	public List<MonitorInclude> queryNatives(MonitorIncludeRequest sRequest);
	/**
	 * 
	 * @描述:点击 检测网站数量 获取数据
	 * @作者:linhb@ucap.com.cn
	 * @时间:2016年12月26日下午2:06:45 
	 * @param sRequest
	 * @return
	 */
	public List<MonitorInclude> querySiteData(MonitorIncludeRequest sRequest);

	/**
	 * @描述:获取搜索引擎收录数据
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2017年3月24日上午10:33:52
	 * @param hashMap
	 * @return
	 */

	public List<SearchEngineResponse> getSearchEngineList(HashMap<String, Object> hashMap);

}

