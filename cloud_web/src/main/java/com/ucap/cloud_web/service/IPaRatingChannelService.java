package com.ucap.cloud_web.service;


import java.util.List;import com.publics.util.page.PageVo;import com.ucap.cloud_web.dto.PaRatingChannelRequest;import com.ucap.cloud_web.entity.PaRatingChannel;


/*** <br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-08-23 10:59:23 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public interface IPaRatingChannelService {


	/**	* 添加数据	* @param paRatingChannel			对象			(必填)	*/	public void add(PaRatingChannel paRatingChannel);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return paRatingChannel	*/	public PaRatingChannel get(Integer id);

	/**	* 修改数据	* @param PaRatingChannel			对象			(必填)	*/	public void update(PaRatingChannel paRatingChannel);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<PaRatingChannel>	*/	public PageVo<PaRatingChannel> query(PaRatingChannelRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(PaRatingChannelRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<PaRatingChannel>	*/	public List<PaRatingChannel> queryList(PaRatingChannelRequest request);
	/**
	 *  linhb　2016-09-05
	 * t通过关联详情  表获取  截图  和   附件
	 * @param pRequest
	 * @return
	 */
	public List<PaRatingChannel> getImgsAndAttch(PaRatingChannelRequest pRequest);
	/**
	 * linhb　2016-10-21
	 *  通过 ratingDetail id  获取  栏目信息
	 * @param ratingChannelRequery
	 * @return
	 */
	public List<PaRatingChannel> queryJoinList(PaRatingChannelRequest ratingChannelRequery);


}

