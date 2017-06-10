package com.ucap.cloud_web.service;


import java.util.HashMap;
import java.util.List;

import com.publics.util.page.PageVo;
import com.ucap.cloud_web.dto.ConnectionAllHomeRequest;
import com.ucap.cloud_web.dtoResponse.ConnectionAllHomeResponse;
import com.ucap.cloud_web.entity.ConnectionAllHome;


/*** <br>* <b>作者：</b>qingjy<br>* <b>日期：</b> 2017-03-30 15:18:32 <br>* <b>版权所有：<b>版权所有(C) 2017<br>*/public interface IConnectionAllHomeService {


	/**	* 添加数据	* @param connectionAllHome			对象			(必填)	*/	public int add(ConnectionAllHome connectionAllHome);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return connectionAllHome	*/	public ConnectionAllHome get(Integer id);

	/**	* 修改数据	* @param ConnectionAllHome			对象			(必填)	*/	public void update(ConnectionAllHome connectionAllHome);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<ConnectionAllHome>	*/	public PageVo<ConnectionAllHome> query(ConnectionAllHomeRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(ConnectionAllHomeRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<ConnectionAllHome>	*/	public List<ConnectionAllHome> queryList(ConnectionAllHomeRequest request);

	/**
	 * @描述:获取首页连通性数据
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2017年3月30日下午3:28:55
	 * @param hashMap
	 * @return
	 */

	public List<ConnectionAllHomeResponse> getConnectionAllHomeList(HashMap<String, Object> hashMap);

}

