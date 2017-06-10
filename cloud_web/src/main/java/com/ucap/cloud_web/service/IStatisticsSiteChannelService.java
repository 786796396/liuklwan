package com.ucap.cloud_web.service;


import java.util.List;

import com.publics.util.page.PageVo;
import com.ucap.cloud_web.dto.StatisticsSiteChannelRequest;
import com.ucap.cloud_web.entity.StatisticsSiteChannel;


/*** <br>* <b>作者：</b>qingjy<br>* <b>日期：</b> 2017-05-03 19:08:59 <br>* <b>版权所有：<b>版权所有(C) 2017<br>*/public interface IStatisticsSiteChannelService {


	/**	* 添加数据	* @param statisticsSiteChannel			对象			(必填)	*/	public int add(StatisticsSiteChannel statisticsSiteChannel);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return statisticsSiteChannel	*/	public StatisticsSiteChannel get(Integer id);

	/**	* 修改数据	* @param StatisticsSiteChannel			对象			(必填)	*/	public void update(StatisticsSiteChannel statisticsSiteChannel);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<StatisticsSiteChannel>	*/	public PageVo<StatisticsSiteChannel> query(StatisticsSiteChannelRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(StatisticsSiteChannelRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<StatisticsSiteChannel>	*/	public List<StatisticsSiteChannel> queryList(StatisticsSiteChannelRequest request);

	/**
	 * @描述:地图数据
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2017年5月3日下午7:21:00
	 * @param req
	 * @return
	 */

	public List<StatisticsSiteChannel> getMapInfoList(StatisticsSiteChannelRequest req);

}

