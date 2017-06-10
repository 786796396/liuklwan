package com.ucap.cloud_web.service;


import java.util.List;import com.publics.util.page.PageVo;import com.ucap.cloud_web.dto.EarlyLogRequest;import com.ucap.cloud_web.entity.EarlyLog;


/*** <br>* <b>作者：</b>luocheng<br>* <b>日期：</b> 2017-01-03 11:00:12 <br>* <b>版权所有：<b>版权所有(C) 2017<br>*/public interface IEarlyLogService {


	/**	* 添加数据	* @param earlyLog			对象			(必填)	*/	public int add(EarlyLog earlyLog);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return earlyLog	*/	public EarlyLog get(Integer id);

	/**	* 修改数据	* @param EarlyLog			对象			(必填)	*/	public void update(EarlyLog earlyLog);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<EarlyLog>	*/	public PageVo<EarlyLog> query(EarlyLogRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(EarlyLogRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<EarlyLog>	*/	public List<EarlyLog> queryList(EarlyLogRequest request);

}

