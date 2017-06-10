package com.ucap.cloud_web.service;


import java.util.List;import com.publics.util.page.PageVo;import com.ucap.cloud_web.dto.SpSiteChannelRequest;import com.ucap.cloud_web.entity.SpSiteChannel;


/*** <br>* <b>作者：</b>liujc<br>* <b>日期：</b> 2016-11-22 10:31:39 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public interface ISpSiteChannelService {


	/**	* 添加数据	* @param spSiteChannel			对象			(必填)	*/	public int add(SpSiteChannel spSiteChannel);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return spSiteChannel	*/	public SpSiteChannel get(Integer id);

	/**	* 修改数据	* @param SpSiteChannel			对象			(必填)	*/	public void update(SpSiteChannel spSiteChannel);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<SpSiteChannel>	*/	public PageVo<SpSiteChannel> query(SpSiteChannelRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(SpSiteChannelRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<SpSiteChannel>	*/	public List<SpSiteChannel> queryList(SpSiteChannelRequest request);

}

