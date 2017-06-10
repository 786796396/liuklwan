package com.ucap.cloud_web.service;


import java.util.List;import com.publics.util.page.PageVo;import com.ucap.cloud_web.dto.PaAdviceRequest;import com.ucap.cloud_web.entity.PaAdvice;


/*** <br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-08-26 09:34:34 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public interface IPaAdviceService {


	/**	* 添加数据	* @param paAdvice			对象			(必填)	*/	public void add(PaAdvice paAdvice);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return paAdvice	*/	public PaAdvice get(Integer id);

	/**	* 修改数据	* @param PaAdvice			对象			(必填)	*/	public void update(PaAdvice paAdvice);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<PaAdvice>	*/	public PageVo<PaAdvice> query(PaAdviceRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(PaAdviceRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<PaAdvice>	*/	public List<PaAdvice> queryList(PaAdviceRequest request);
	/**
	 * 通过 id 修改  advice
	 * @param aaRequest
	 */
	public void updateById(PaAdviceRequest aaRequest);

}

