package com.ucap.cloud_web.service;


import java.util.List;import com.publics.util.page.PageVo;import com.ucap.cloud_web.dto.ChannelPointTempRequest;import com.ucap.cloud_web.entity.ChannelPointTemp;


/*** <br>* <b>作者：</b>qingjy<br>* <b>日期：</b> 2016-11-29 17:18:44 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public interface IChannelPointTempService {


	/**	* 添加数据	* @param channelPointTemp			对象			(必填)	*/	public int add(ChannelPointTemp channelPointTemp);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return channelPointTemp	*/	public ChannelPointTemp get(Integer id);

	/**	* 修改数据	* @param ChannelPointTemp			对象			(必填)	*/	public void update(ChannelPointTemp channelPointTemp);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<ChannelPointTemp>	*/	public PageVo<ChannelPointTemp> query(ChannelPointTempRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(ChannelPointTempRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<ChannelPointTemp>	*/	public List<ChannelPointTemp> queryList(ChannelPointTempRequest request);

}

