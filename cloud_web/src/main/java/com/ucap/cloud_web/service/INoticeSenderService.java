package com.ucap.cloud_web.service;


import java.util.List;import com.publics.util.page.PageVo;import com.ucap.cloud_web.dto.NoticeSenderRequest;import com.ucap.cloud_web.entity.NoticeSender;


/*** <br>* <b>作者：</b>Na.Y<br>* <b>日期：</b> 2016-09-21 13:43:19 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public interface INoticeSenderService {


	/**	* 添加数据	* @param noticeSender			对象			(必填)	*/	public int add(NoticeSender noticeSender);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return noticeSender	*/	public NoticeSender get(Integer id);

	/**	* 修改数据	* @param NoticeSender			对象			(必填)	*/	public void update(NoticeSender noticeSender);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<NoticeSender>	*/	public PageVo<NoticeSender> query(NoticeSenderRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(NoticeSenderRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<NoticeSender>	*/	public List<NoticeSender> queryList(NoticeSenderRequest request);

}

