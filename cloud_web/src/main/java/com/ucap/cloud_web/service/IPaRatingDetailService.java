package com.ucap.cloud_web.service;


import java.util.List;import com.publics.util.page.PageVo;import com.ucap.cloud_web.dto.PaRatingDetailRequest;import com.ucap.cloud_web.entity.PaRatingDetail;


/*** <br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-08-23 10:59:23 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public interface IPaRatingDetailService {


	/**	* 添加数据	* @param paRatingDetail			对象			(必填)	*/	public void add(PaRatingDetail paRatingDetail);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return paRatingDetail	*/	public PaRatingDetail get(Integer id);

	/**	* 修改数据	* @param PaRatingDetail			对象			(必填)	*/	public void update(PaRatingDetail paRatingDetail);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<PaRatingDetail>	*/	public PageVo<PaRatingDetail> query(PaRatingDetailRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(PaRatingDetailRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<PaRatingDetail>	*/	public List<PaRatingDetail> queryList(PaRatingDetailRequest request);
	/**
	 * linhb 2016-08-31 
	 * 按照 一级指标分组 目的 获取所有的一级指标
	 * @param pRequest
	 * @return
	 */
	public List<PaRatingDetail> groupByOneName(PaRatingDetailRequest pRequest);
	/**
	 * linhb 2016-09-03
	 * 通过自评详情 id 获取 已经填报的数据（栏目名称  url 等）
	 * @param pRequest
	 * @return
	 */
	public List<PaRatingDetail> getWriteData(PaRatingDetailRequest pRequest);

}

