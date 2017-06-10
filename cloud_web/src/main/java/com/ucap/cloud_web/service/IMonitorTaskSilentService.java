package com.ucap.cloud_web.service;


import java.util.List;import com.publics.util.page.PageVo;import com.ucap.cloud_web.dto.MonitorTaskSilentRequest;import com.ucap.cloud_web.entity.MonitorTaskSilent;


/*** <br>* <b>作者：</b>qingjy<br>* <b>日期：</b> 2016-11-07 16:41:57 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public interface IMonitorTaskSilentService {


	/**	* 添加数据	* @param monitorTaskSilent			对象			(必填)	*/	public int add(MonitorTaskSilent monitorTaskSilent);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return monitorTaskSilent	*/	public MonitorTaskSilent get(Integer id);

	/**	* 修改数据	* @param MonitorTaskSilent			对象			(必填)	*/	public void update(MonitorTaskSilent monitorTaskSilent);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<MonitorTaskSilent>	*/	public PageVo<MonitorTaskSilent> query(MonitorTaskSilentRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(MonitorTaskSilentRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<MonitorTaskSilent>	*/	public List<MonitorTaskSilent> queryList(MonitorTaskSilentRequest request);

}

