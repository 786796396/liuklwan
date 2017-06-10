package com.ucap.cloud_web.service;


import java.util.List;import com.publics.util.page.PageVo;import com.ucap.cloud_web.dto.LogRequest;import com.ucap.cloud_web.entity.Log;


/*** <br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2015-10-29 11:06:39 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/public interface ILogService {


	/**	* 添加数据	* @param log			对象			(必填)	*/	public void add(Log log);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return log	*/	public Log get(Integer id);

	/**	* 修改数据	* @param Log			对象			(必填)	*/	public void update(Log log);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<Log>	*/	public PageVo<Log> query(LogRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(LogRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<Log>	*/	public List<Log> queryList(LogRequest request);

}

