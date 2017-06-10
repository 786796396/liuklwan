package com.ucap.cloud_web.service;


import java.util.List;import com.publics.util.page.PageVo;import com.ucap.cloud_web.dto.PaProjectRequest;import com.ucap.cloud_web.entity.PaProject;
import com.ucap.cloud_web.entity.PaTask;


/*** <br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-08-23 10:59:23 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public interface IPaProjectService {


	/**	* 添加数据	* @param paProject			对象			(必填)	*/	public void add(PaProject paProject);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return paProject	*/	public PaProject get(Integer id);

	/**	* 修改数据	* @param PaProject			对象			(必填)	*/	public void update(PaProject paProject);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<PaProject>	*/	public PageVo<PaProject> query(PaProjectRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(PaProjectRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<PaProject>	*/	public List<PaProject> queryList(PaProjectRequest request);
	/**
	 * 通过项目表中的  site_code 获取该项目表下面的任务
	 * linhb 2016-8-23
	 * @param paProjectRequest
	 * @return
	 */
	public List<PaTask> queryTaskList(PaProjectRequest paProjectRequest);

}

