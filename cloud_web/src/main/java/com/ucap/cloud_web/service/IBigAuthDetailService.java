package com.ucap.cloud_web.service;


import java.util.List;import com.publics.util.page.PageVo;import com.ucap.cloud_web.dto.BigAuthDetailRequest;import com.ucap.cloud_web.entity.BigAuthDetail;


/*** <br>* <b>作者：</b>sjy<br>* <b>日期：</b> 2016-06-28 11:44:53 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public interface IBigAuthDetailService {


	/**	* 添加数据	* @param bigDataPermissionDetail			对象			(必填)	*/	public void add(BigAuthDetail bigDataPermissionDetail);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return bigDataPermissionDetail	*/	public BigAuthDetail get(Integer id);

	/**	* 修改数据	* @param BigAuthDetail			对象			(必填)	*/	public void update(BigAuthDetail bigDataPermissionDetail);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<BigDataPermissionDetail>	*/	public PageVo<BigAuthDetail> query(BigAuthDetailRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(BigAuthDetailRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<BigDataPermissionDetail>	*/	public List<BigAuthDetail> queryList(BigAuthDetailRequest request);

	




}

