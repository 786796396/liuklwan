package com.ucap.cloud_web.service;


import java.util.List;import com.publics.util.page.PageVo;import com.ucap.cloud_web.dto.PaRatingRequest;import com.ucap.cloud_web.entity.PaRating;
import com.ucap.cloud_web.entity.PaRatingDetail;


/*** <br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-08-23 10:59:23 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public interface IPaRatingService {


	/**	* 添加数据	* @param paRating			对象			(必填)	*/	public void add(PaRating paRating);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return paRating	*/	public PaRating get(Integer id);

	/**	* 修改数据	* @param PaRating			对象			(必填)	*/	public void update(PaRating paRating);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<PaRating>	*/	public PageVo<PaRating> query(PaRatingRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(PaRatingRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<PaRating>	*/	public List<PaRating> queryList(PaRatingRequest request);
	
	/**
	 * linhb 2016-08-25
	 * 通过  任务id 任务关联 id  获取 自评内容
	 * @param paRatingRequest
	 * @return
	 */
	public List<PaRatingDetail> queryFour(PaRatingRequest paRatingRequest);


}

