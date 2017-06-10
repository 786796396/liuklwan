package com.ucap.cloud_web.service;


import java.util.List;import com.publics.util.page.PageVo;import com.ucap.cloud_web.dto.MonitorFragilityRequest;import com.ucap.cloud_web.entity.MonitorFragility;


/*** <br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-11-04 11:24:50 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public interface IMonitorFragilityService {


	/**	* 添加数据	* @param monitorFragility			对象			(必填)	*/	public int add(MonitorFragility monitorFragility);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return monitorFragility	*/	public MonitorFragility get(Integer id);

	/**	* 修改数据	* @param MonitorFragility			对象			(必填)	*/	public void update(MonitorFragility monitorFragility);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<MonitorFragility>	*/	public PageVo<MonitorFragility> query(MonitorFragilityRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(MonitorFragilityRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<MonitorFragility>	*/	public List<MonitorFragility> queryList(MonitorFragilityRequest request);

}

