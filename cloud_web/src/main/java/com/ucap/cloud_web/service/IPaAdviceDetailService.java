package com.ucap.cloud_web.service;


import java.util.List;import com.publics.util.page.PageVo;import com.ucap.cloud_web.dto.PaAdviceDetailRequest;import com.ucap.cloud_web.entity.PaAdviceDetail;


/*** <br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-08-23 10:59:22 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public interface IPaAdviceDetailService {


	/**	* 添加数据	* @param paAdviceDetail			对象			(必填)	*/	public void add(PaAdviceDetail paAdviceDetail);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return paAdviceDetail	*/	public PaAdviceDetail get(Integer id);

	/**	* 修改数据	* @param PaAdviceDetail			对象			(必填)	*/	public void update(PaAdviceDetail paAdviceDetail);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<PaAdviceDetail>	*/	public PageVo<PaAdviceDetail> query(PaAdviceDetailRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(PaAdviceDetailRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<PaAdviceDetail>	*/	public List<PaAdviceDetail> queryList(PaAdviceDetailRequest request);

}

