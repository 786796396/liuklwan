package com.ucap.cloud_web.service;


import java.util.List;import com.publics.util.page.PageVo;import com.ucap.cloud_web.dto.MenuInfoRequest;import com.ucap.cloud_web.entity.MenuInfo;


/*** <br>* <b>作者：</b>qingjy<br>* <b>日期：</b> 2017-03-28 13:59:38 <br>* <b>版权所有：<b>版权所有(C) 2017<br>*/public interface IMenuInfoService {


	/**	* 添加数据	* @param menuInfo			对象			(必填)	*/	public int add(MenuInfo menuInfo);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return menuInfo	*/	public MenuInfo get(Integer id);

	/**	* 修改数据	* @param MenuInfo			对象			(必填)	*/	public void update(MenuInfo menuInfo);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<MenuInfo>	*/	public PageVo<MenuInfo> query(MenuInfoRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(MenuInfoRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<MenuInfo>	*/	public List<MenuInfo> queryList(MenuInfoRequest request);

}

