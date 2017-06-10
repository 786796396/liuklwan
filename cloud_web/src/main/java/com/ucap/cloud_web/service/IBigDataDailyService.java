package com.ucap.cloud_web.service;


import java.util.List;

import com.publics.util.page.PageVo;
import com.ucap.cloud_web.dto.BigDataDailyRequest;
import com.ucap.cloud_web.dtoResponse.BigDataDailyResponse;
import com.ucap.cloud_web.entity.BigDataDaily;


/*** <br>* <b>作者：</b>liujc<br>* <b>日期：</b> 2017-02-28 18:11:20 <br>* <b>版权所有：<b>版权所有(C) 2017<br>*/public interface IBigDataDailyService {


	/**	* 添加数据	* @param bigDataDaily			对象			(必填)	*/	public int add(BigDataDaily bigDataDaily);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return bigDataDaily	*/	public BigDataDaily get(Integer id);

	/**	* 修改数据	* @param BigDataDaily			对象			(必填)	*/	public void update(BigDataDaily bigDataDaily);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<BigDataDaily>	*/	public PageVo<BigDataDaily> query(BigDataDailyRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(BigDataDailyRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<BigDataDaily>	*/	public List<BigDataDaily> queryList(BigDataDailyRequest request);
	/**
	 * 
	 * @描述:获取 下级地方门户
	 * @作者:liujc@ucap.com.cn
	 * @时间:2017-3-1下午1:40:13 
	 * @param sRequest
	 * @return
	 */
	public List<BigDataDailyResponse> queryOrganizations(BigDataDailyRequest sRequest);
	/**
	 * 
	 * @描述:获取 本级站点
	 * @作者:liujc@ucap.com.cn
	 * @时间:2017-3-1下午1:40:21 
	 * @param sRequest
	 * @return
	 */
	public List<BigDataDailyResponse> queryNatives(BigDataDailyRequest sRequest);
}

