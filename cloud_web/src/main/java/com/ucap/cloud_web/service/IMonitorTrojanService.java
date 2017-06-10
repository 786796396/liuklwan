package com.ucap.cloud_web.service;


import java.util.List;import com.publics.util.page.PageVo;import com.ucap.cloud_web.dto.MonitorTrojanRequest;import com.ucap.cloud_web.entity.MonitorTrojan;


/*** <br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-11-04 11:24:51 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public interface IMonitorTrojanService {


	/**	* 添加数据	* @param monitorTrojan			对象			(必填)	*/	public int add(MonitorTrojan monitorTrojan);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return monitorTrojan	*/	public MonitorTrojan get(Integer id);

	/**	* 修改数据	* @param MonitorTrojan			对象			(必填)	*/	public void update(MonitorTrojan monitorTrojan);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<MonitorTrojan>	*/	public PageVo<MonitorTrojan> query(MonitorTrojanRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(MonitorTrojanRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<MonitorTrojan>	*/	public List<MonitorTrojan> queryList(MonitorTrojanRequest request);

}

