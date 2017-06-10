package com.ucap.cloud_web.service;


import java.util.List;import com.ucap.cloud_web.dto.DicSiteRequest;import com.ucap.cloud_web.entity.DicSite;


/*** <br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2015-10-30 15:52:30 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/public interface IDicSiteService {


	/**	* 添加数据	* @param dicSite			对象			(必填)	*/	public void add(DicSite dicSite);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return dicSite	*/	public DicSite get(Integer id);

	/**	* 修改数据	* @param DicSite			对象			(必填)	*/	public void update(DicSite dicSite);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);


	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<DicSite>	*/	public List<DicSite> queryList(DicSiteRequest request);

}

