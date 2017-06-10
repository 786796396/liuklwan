package com.ucap.cloud_web.service;


import java.util.List;import com.publics.util.page.PageVo;import com.ucap.cloud_web.dto.SecurityOthersRequest;import com.ucap.cloud_web.entity.SecurityOthers;


/*** <br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2016-06-23 15:27:08 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public interface ISecurityOthersService {


	/**	* 添加数据	* @param securityOthers			对象			(必填)	*/	public void add(SecurityOthers securityOthers);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return securityOthers	*/	public SecurityOthers get(Integer id);

	/**	* 修改数据	* @param SecurityOthers			对象			(必填)	*/	public void update(SecurityOthers securityOthers);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<SecurityOthers>	*/	public PageVo<SecurityOthers> query(SecurityOthersRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(SecurityOthersRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<SecurityOthers>	*/	public List<SecurityOthers> queryList(SecurityOthersRequest request);

}

