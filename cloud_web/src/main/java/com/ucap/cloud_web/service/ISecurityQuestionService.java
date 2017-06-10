package com.ucap.cloud_web.service;


import java.util.List;import com.publics.util.page.PageVo;import com.ucap.cloud_web.dto.SecurityQuestionRequest;import com.ucap.cloud_web.entity.SecurityQuestion;


/*** <br>* <b>作者：</b>liujc<br>* <b>日期：</b> 2016-09-02 09:18:15 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public interface ISecurityQuestionService {


	/**	* 添加数据	* @param securityQuestion			对象			(必填)	*/	public int add(SecurityQuestion securityQuestion);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return securityQuestion	*/	public SecurityQuestion get(Integer id);

	/**	* 修改数据	* @param SecurityQuestion			对象			(必填)	*/	public void update(SecurityQuestion securityQuestion);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<SecurityQuestion>	*/	public PageVo<SecurityQuestion> query(SecurityQuestionRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(SecurityQuestionRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<SecurityQuestion>	*/	public List<SecurityQuestion> queryList(SecurityQuestionRequest request);

}

