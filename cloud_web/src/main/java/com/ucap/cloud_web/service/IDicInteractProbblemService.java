package com.ucap.cloud_web.service;


import java.util.List;import com.publics.util.page.PageVo;import com.ucap.cloud_web.dto.DicInteractProbblemRequest;import com.ucap.cloud_web.entity.DicInteractProbblem;


/*** <br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2015-10-29 11:03:47 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/public interface IDicInteractProbblemService {


	/**	* 添加数据	* @param dicInteractProbblem			对象			(必填)	*/	public void add(DicInteractProbblem dicInteractProbblem);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return dicInteractProbblem	*/	public DicInteractProbblem get(Integer id);

	/**	* 修改数据	* @param DicInteractProbblem			对象			(必填)	*/	public void update(DicInteractProbblem dicInteractProbblem);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<DicInteractProbblem>	*/	public PageVo<DicInteractProbblem> query(DicInteractProbblemRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(DicInteractProbblemRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<DicInteractProbblem>	*/	public List<DicInteractProbblem> queryList(DicInteractProbblemRequest request);

}

