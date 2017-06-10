package com.ucap.cloud_web.service;


import java.util.List;import com.publics.util.page.PageVo;import com.ucap.cloud_web.dto.UserLogRequest;import com.ucap.cloud_web.entity.UserLog;


/*** <br>* <b>作者：</b>ccx<br>* <b>日期：</b> 2016-03-05 17:42:26 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public interface IUserLogService {


	/**	* 添加数据	* @param userLog			对象			(必填)	*/	public void add(UserLog userLog);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return userLog	*/	public UserLog get(Integer id);

	/**	* 修改数据	* @param UserLog			对象			(必填)	*/	public void update(UserLog userLog);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<UserLog>	*/	public PageVo<UserLog> query(UserLogRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(UserLogRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<UserLog>	*/	public List<UserLog> queryList(UserLogRequest request);

}

