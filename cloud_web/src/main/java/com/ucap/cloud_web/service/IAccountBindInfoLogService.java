package com.ucap.cloud_web.service;


import java.util.List;import com.publics.util.page.PageVo;import com.ucap.cloud_web.dto.AccountBindInfoLogRequest;import com.ucap.cloud_web.entity.AccountBindInfoLog;


/*** <br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2017-05-02 19:42:09 <br>* <b>版权所有：<b>版权所有(C) 2017<br>*/public interface IAccountBindInfoLogService {


	/**	* 添加数据	* @param accountBindInfoLog			对象			(必填)	*/	public int add(AccountBindInfoLog accountBindInfoLog);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return accountBindInfoLog	*/	public AccountBindInfoLog get(Integer id);

	/**	* 修改数据	* @param AccountBindInfoLog			对象			(必填)	*/	public void update(AccountBindInfoLog accountBindInfoLog);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<AccountBindInfoLog>	*/	public PageVo<AccountBindInfoLog> query(AccountBindInfoLogRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(AccountBindInfoLogRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<AccountBindInfoLog>	*/	public List<AccountBindInfoLog> queryList(AccountBindInfoLogRequest request);

}

