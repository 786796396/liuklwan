package com.ucap.cloud_web.service;


import java.util.List;import com.publics.util.page.PageVo;import com.ucap.cloud_web.dto.SecurityChannelRequest;import com.ucap.cloud_web.entity.SecurityChannel;


/*** <br>* <b>作者：</b>SunJiang<br>* <b>日期：</b> 2015-10-30 09:53:39 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/public interface ISecurityChannelService {


	/**	* 添加数据	* @param securityChannel			对象			(必填)	*/	public void add(SecurityChannel securityChannel);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return securityChannel	*/	public SecurityChannel get(Integer id);

	/**	* 修改数据	* @param SecurityChannel			对象			(必填)	*/	public void update(SecurityChannel securityChannel);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<SecurityChannel>	*/	public PageVo<SecurityChannel> query(SecurityChannelRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(SecurityChannelRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<SecurityChannel>	*/	public List<SecurityChannel> queryList(SecurityChannelRequest request);
	
}

