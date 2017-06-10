package com.ucap.cloud_web.service;


import java.util.List;import com.publics.util.page.PageVo;import com.ucap.cloud_web.dto.SysAttachRequest;import com.ucap.cloud_web.entity.SysAttach;


/*** <br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-08-23 10:59:24 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public interface ISysAttachService {


	/**	* 添加数据	* @param sysAttach			对象			(必填)	*/	public void add(SysAttach sysAttach);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return sysAttach	*/	public SysAttach get(Integer id);

	/**	* 修改数据	* @param SysAttach			对象			(必填)	*/	public void update(SysAttach sysAttach);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<SysAttach>	*/	public PageVo<SysAttach> query(SysAttachRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(SysAttachRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<SysAttach>	*/	public List<SysAttach> queryList(SysAttachRequest request);
	/**
	 * 删除  通过  表名  id值
	 * @param sRequest
	 */
	public void deleteByTbNameAndId(SysAttachRequest sRequest);

}

