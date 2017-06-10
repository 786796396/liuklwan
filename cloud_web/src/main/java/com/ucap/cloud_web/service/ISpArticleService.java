package com.ucap.cloud_web.service;


import java.util.List;import com.publics.util.page.PageVo;import com.ucap.cloud_web.dto.SpArticleRequest;import com.ucap.cloud_web.entity.SpArticle;


/*** <br>* <b>作者：</b>liujc<br>* <b>日期：</b> 2016-11-22 10:33:04 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public interface ISpArticleService {


	/**	* 添加数据	* @param spArticle			对象			(必填)	*/	public int add(SpArticle spArticle);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return spArticle	*/	public SpArticle get(Integer id);

	/**	* 修改数据	* @param SpArticle			对象			(必填)	*/	public void update(SpArticle spArticle);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<SpArticle>	*/	public PageVo<SpArticle> query(SpArticleRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(SpArticleRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<SpArticle>	*/	public List<SpArticle> queryList(SpArticleRequest request);

	/**
	 * @描述:查询关联文章权限表的文章
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2016年12月6日上午10:06:55 
	 * @param artreq
	 * @return 
	 */
	
	public List<SpArticle> getSpArtList(SpArticleRequest request);

}

