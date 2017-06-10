package com.ucap.cloud_web.service;


import java.util.HashMap;
import java.util.List;import com.publics.util.page.PageVo;import com.ucap.cloud_web.dto.MonitorSilentOrgResultRequest;import com.ucap.cloud_web.entity.MonitorSilentOrgResult;


/*** <br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-11-04 11:24:50 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public interface IMonitorSilentOrgResultService {


	/**	* 添加数据	* @param monitorSilentOrgResult			对象			(必填)	*/	public int add(MonitorSilentOrgResult monitorSilentOrgResult);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return monitorSilentOrgResult	*/	public MonitorSilentOrgResult get(Integer id);

	/**	* 修改数据	* @param MonitorSilentOrgResult			对象			(必填)	*/	public void update(MonitorSilentOrgResult monitorSilentOrgResult);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<MonitorSilentOrgResult>	*/	public PageVo<MonitorSilentOrgResult> query(MonitorSilentOrgResultRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(MonitorSilentOrgResultRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<MonitorSilentOrgResult>	*/	public List<MonitorSilentOrgResult> queryList(MonitorSilentOrgResultRequest request);

	/**
	 * 根据map查询集合
	 * @param hashMap
	 * @return
	 */
	public List<MonitorSilentOrgResult> getMonitorSilentList(HashMap<String, Object> hashMap);

}

