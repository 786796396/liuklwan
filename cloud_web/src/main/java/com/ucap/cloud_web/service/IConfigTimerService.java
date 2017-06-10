package com.ucap.cloud_web.service;


import java.util.List;import com.publics.util.page.PageVo;import com.ucap.cloud_web.dto.ConfigTimerRequest;import com.ucap.cloud_web.entity.ConfigTimer;


/*** <br>* <b>作者：</b>Na.Y<br>* <b>日期：</b> 2016-09-14 09:49:59 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public interface IConfigTimerService {


	/**	* 添加数据	* @param configTimer			对象			(必填)	*/	public int add(ConfigTimer configTimer);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return configTimer	*/	public ConfigTimer get(Integer id);

	/**	* 修改数据	* @param ConfigTimer			对象			(必填)	*/	public void update(ConfigTimer configTimer);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<ConfigTimer>	*/	public PageVo<ConfigTimer> query(ConfigTimerRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(ConfigTimerRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<ConfigTimer>	*/	public List<ConfigTimer> queryList(ConfigTimerRequest request);

}

