package com.ucap.cloud_web.service;


import java.util.List;import com.ucap.cloud_web.dto.DicServiceUnuserfulRequest;import com.ucap.cloud_web.entity.DicServiceUnuserful;


/*** <br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2015-10-29 11:04:13 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/public interface IDicServiceUnuserfulService {


	/**	* 添加数据	* @param dicServiceUnuserful			对象			(必填)	*/	public void add(DicServiceUnuserful dicServiceUnuserful);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return dicServiceUnuserful	*/	public DicServiceUnuserful get(Integer id);

	/**	* 修改数据	* @param DicServiceUnuserful			对象			(必填)	*/	public void update(DicServiceUnuserful dicServiceUnuserful);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<DicServiceUnuserful>	*/	public List<DicServiceUnuserful> queryList(DicServiceUnuserfulRequest request);

}

