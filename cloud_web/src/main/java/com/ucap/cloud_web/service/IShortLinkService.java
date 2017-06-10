package com.ucap.cloud_web.service;


import java.util.List;import com.publics.util.page.PageVo;import com.ucap.cloud_web.dto.ShortLinkRequest;import com.ucap.cloud_web.entity.ShortLink;


/*** <br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2017-05-16 11:23:43 <br>* <b>版权所有：<b>版权所有(C) 2017<br>*/public interface IShortLinkService {


	/**	* 添加数据	* @param shortLink			对象			(必填)	*/	public int add(ShortLink shortLink);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return shortLink	*/	public ShortLink get(Integer id);

	/**	* 修改数据	* @param ShortLink			对象			(必填)	*/	public void update(ShortLink shortLink);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<ShortLink>	*/	public PageVo<ShortLink> query(ShortLinkRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(ShortLinkRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<ShortLink>	*/	public List<ShortLink> queryList(ShortLinkRequest request);

}

