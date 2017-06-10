package com.ucap.cloud_web.service;


import java.util.List;import java.util.Map;

import com.publics.util.page.PageVo;import com.ucap.cloud_web.dto.LinkHomeAvailableRequest;import com.ucap.cloud_web.entity.LinkHomeAvailable;


/*** <br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2015-10-29 11:06:19 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/public interface ILinkHomeAvailableService {


	/**	* 添加数据	* @param linkHomeAvailable			对象			(必填)	*/	public void add(LinkHomeAvailable linkHomeAvailable);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return linkHomeAvailable	*/	public LinkHomeAvailable get(Integer id);

	/**	* 修改数据	* @param LinkHomeAvailable			对象			(必填)	*/	public void update(LinkHomeAvailable linkHomeAvailable);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<LinkHomeAvailable>	*/	public PageVo<LinkHomeAvailable> query(LinkHomeAvailableRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(LinkHomeAvailableRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<LinkHomeAvailable>	*/	public List<LinkHomeAvailable> queryList(LinkHomeAvailableRequest request);
	/**
	 * @Description:分类统计  每种类型的个数 
	 * @author cuichx --- 2017-3-30上午11:32:01     
	 * @param paramMap
	 * @return
	 */
	public List<LinkHomeAvailableRequest> queryGroupBy(
			Map<String, Object> paramMap);


}

