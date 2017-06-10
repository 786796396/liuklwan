package com.ucap.cloud_web.service;


import java.util.List;import com.publics.util.page.PageVo;import com.ucap.cloud_web.dto.PaTaskRequest;import com.ucap.cloud_web.entity.PaTask;


/*** <br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-08-23 10:59:24 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public interface IPaTaskService {


	/**	* 添加数据	* @param paTask			对象			(必填)	*/	public void add(PaTask paTask);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return paTask	*/	public PaTask get(Integer id);

	/**	* 修改数据	* @param PaTask			对象			(必填)	*/	public void update(PaTask paTask);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<PaTask>	*/	public PageVo<PaTask> query(PaTaskRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(PaTaskRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<PaTask>	*/	public List<PaTask> queryList(PaTaskRequest request);
	/**
	 * 关联 任务关联表  通过siteCode获取数据
	 * linhb 2016-08-29
	 * @param pRequest
	 * @return
	 */
	public List<PaTask> queryJoinTarget(PaTaskRequest pRequest);

}

