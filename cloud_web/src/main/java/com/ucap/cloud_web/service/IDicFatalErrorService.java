package com.ucap.cloud_web.service;


import java.util.List;import com.publics.util.page.PageVo;import com.ucap.cloud_web.dto.DicFatalErrorRequest;import com.ucap.cloud_web.entity.DicFatalError;


/*** <br>* <b>作者：</b>liujc<br>* <b>日期：</b> 2017-05-19 09:38:52 <br>* <b>版权所有：<b>版权所有(C) 2017<br>*/public interface IDicFatalErrorService {


	/**	* 添加数据	* @param dicFatalError			对象			(必填)	*/	public int add(DicFatalError dicFatalError);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return dicFatalError	*/	public DicFatalError get(Integer id);

	/**	* 修改数据	* @param DicFatalError			对象			(必填)	*/	public void update(DicFatalError dicFatalError);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<DicFatalError>	*/	public PageVo<DicFatalError> query(DicFatalErrorRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(DicFatalErrorRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<DicFatalError>	*/	public List<DicFatalError> queryList(DicFatalErrorRequest request);

}

