package com.ucap.cloud_web.service;


import java.util.List;import com.ucap.cloud_web.dto.DicChannelRequest;import com.ucap.cloud_web.entity.DicChannel;


/*** <br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2015-10-29 11:17:59 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/public interface IDicChannelService {


	/**	* 添加数据	* @param dicChannel			对象			(必填)	*/	public void add(DicChannel dicChannel);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return dicChannel	*/	public DicChannel get(Integer id);

	/**	* 修改数据	* @param DicChannel			对象			(必填)	*/	public void update(DicChannel dicChannel);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<DicChannel>	*/	public List<DicChannel> queryList(DicChannelRequest request);

}

