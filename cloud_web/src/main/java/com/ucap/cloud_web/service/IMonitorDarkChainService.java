package com.ucap.cloud_web.service;


import java.util.List;import com.publics.util.page.PageVo;import com.ucap.cloud_web.dto.MonitorDarkChainRequest;import com.ucap.cloud_web.entity.MonitorDarkChain;


/*** <br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-11-04 11:24:50 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public interface IMonitorDarkChainService {


	/**	* 添加数据	* @param monitorDarkChain			对象			(必填)	*/	public int add(MonitorDarkChain monitorDarkChain);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return monitorDarkChain	*/	public MonitorDarkChain get(Integer id);

	/**	* 修改数据	* @param MonitorDarkChain			对象			(必填)	*/	public void update(MonitorDarkChain monitorDarkChain);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<MonitorDarkChain>	*/	public PageVo<MonitorDarkChain> query(MonitorDarkChainRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(MonitorDarkChainRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<MonitorDarkChain>	*/	public List<MonitorDarkChain> queryList(MonitorDarkChainRequest request);

}

