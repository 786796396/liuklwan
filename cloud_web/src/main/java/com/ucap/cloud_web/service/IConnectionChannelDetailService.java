package com.ucap.cloud_web.service;


import java.util.List;import java.util.Map;

import com.publics.util.page.PageVo;import com.ucap.cloud_web.dto.ConnectionChannelDetailRequest;import com.ucap.cloud_web.entity.ConnectionChannelDetail;


/*** <br>* <b>作者：</b>Nora<br>* <b>日期：</b> 2015-10-29 11:19:33 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/public interface IConnectionChannelDetailService {


	/**	* 添加数据	* @param connectionChannelDetail			对象			(必填)	*/	public void add(ConnectionChannelDetail connectionChannelDetail);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return connectionChannelDetail	*/	public ConnectionChannelDetail get(Integer id);

	/**	* 修改数据	* @param ConnectionChannelDetail			对象			(必填)	*/	public void update(ConnectionChannelDetail connectionChannelDetail);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<ConnectionChannelDetail>	*/	public PageVo<ConnectionChannelDetail> query(ConnectionChannelDetailRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(ConnectionChannelDetailRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<ConnectionChannelDetail>	*/	public List<ConnectionChannelDetail> queryList(ConnectionChannelDetailRequest request);
	/**
	 * 统计每类栏目的个数
	 * @param paraMap
	 * @return
	 */
	public List<ConnectionChannelDetailRequest> queryListByGroup(
			Map<String, Object> paraMap);
	/**
	 * 统计每类栏目的个数---总记录数
	 * @param paraMap
	 * @return
	 */
	public int queryListByGroupCount(
			Map<String, Object> paraMap);

}

