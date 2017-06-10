package com.ucap.cloud_web.service;


import java.util.HashMap;
import java.util.List;import com.publics.util.page.PageVo;import com.ucap.cloud_web.dto.MonitorSilentResultRequest;import com.ucap.cloud_web.entity.MonitorSilentResult;


/*** <br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-11-04 11:24:50 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public interface IMonitorSilentResultService {


	/**	* 添加数据	* @param monitorSilentResult			对象			(必填)	*/	public int add(MonitorSilentResult monitorSilentResult);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return monitorSilentResult	*/	public MonitorSilentResult get(Integer id);

	/**	* 修改数据	* @param MonitorSilentResult			对象			(必填)	*/	public void update(MonitorSilentResult monitorSilentResult);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<MonitorSilentResult>	*/	public PageVo<MonitorSilentResult> query(MonitorSilentResultRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(MonitorSilentResultRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<MonitorSilentResult>	*/	public List<MonitorSilentResult> queryList(MonitorSilentResultRequest request);

	/**
	 * 关联表查询（database_info）
	 * @param hashMap
	 * @return
	 */
	public List<MonitorSilentResultRequest> getMonResMap(HashMap<String, Object> hashMap);

}

