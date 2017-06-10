package com.ucap.cloud_web.service;


import java.util.List;import com.publics.util.page.PageVo;import com.ucap.cloud_web.dto.TaskAllRequest;import com.ucap.cloud_web.entity.TaskAll;


/*** <br>* <b>作者：</b>SunJiang<br>* <b>日期：</b> 2015-10-30 09:51:22 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/public interface ITaskAllService {


	/**	* 添加数据	* @param taskAll			对象			(必填)	*/	public void add(TaskAll taskAll);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return taskAll	*/	public TaskAll get(Integer id);

	/**	* 修改数据	* @param TaskAll			对象			(必填)	*/	public void update(TaskAll taskAll);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<TaskAll>	*/	public PageVo<TaskAll> query(TaskAllRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(TaskAllRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<TaskAll>	*/	public List<TaskAll> queryList(TaskAllRequest request);

}

