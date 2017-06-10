package com.ucap.cloud_web.service;


import java.util.List;import com.publics.util.page.PageVo;import com.ucap.cloud_web.dto.AccountBindIdRequest;import com.ucap.cloud_web.entity.AccountBindId;


/*** <br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2017-05-03 10:19:53 <br>* <b>版权所有：<b>版权所有(C) 2017<br>*/public interface IAccountBindIdService {


	/**	* 添加数据	* @param accountBindId			对象			(必填)	*/	public int add(AccountBindId accountBindId);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return accountBindId	*/	public AccountBindId get(Integer id);

	/**	* 修改数据	* @param AccountBindId			对象			(必填)	*/	public void update(AccountBindId accountBindId);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<AccountBindId>	*/	public PageVo<AccountBindId> query(AccountBindIdRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(AccountBindIdRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<AccountBindId>	*/	public List<AccountBindId> queryList(AccountBindIdRequest request);

}

