package com.ucap.cloud_web.service;


import java.util.List;import com.ucap.cloud_web.dto.DicInteractProblemRequest;import com.ucap.cloud_web.entity.DicInteractProblem;


/*** <br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2015-10-30 14:55:01 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/public interface IDicInteractProblemService {


	/**	* 添加数据	* @param dicInteractProblem			对象			(必填)	*/	public void add(DicInteractProblem dicInteractProblem);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return dicInteractProblem	*/	public DicInteractProblem get(Integer id);

	/**	* 修改数据	* @param DicInteractProblem			对象			(必填)	*/	public void update(DicInteractProblem dicInteractProblem);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);


	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<DicInteractProblem>	*/	public List<DicInteractProblem> queryList(DicInteractProblemRequest request);

}

