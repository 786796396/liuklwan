package com.ucap.cloud_web.service;


import java.util.List;import com.publics.util.page.PageVo;import com.ucap.cloud_web.dto.ChannelPointLogRequest;import com.ucap.cloud_web.entity.ChannelPointLog;


/*** <br>* <b>作者：</b>qingjy<br>* <b>日期：</b> 2016-11-29 17:19:36 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public interface IChannelPointLogService {


	/**	* 添加数据	* @param channelPointLog			对象			(必填)	*/	public int add(ChannelPointLog channelPointLog);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return channelPointLog	*/	public ChannelPointLog get(Integer id);

	/**	* 修改数据	* @param ChannelPointLog			对象			(必填)	*/	public void update(ChannelPointLog channelPointLog);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<ChannelPointLog>	*/	public PageVo<ChannelPointLog> query(ChannelPointLogRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(ChannelPointLogRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<ChannelPointLog>	*/	public List<ChannelPointLog> queryList(ChannelPointLogRequest request);

}

