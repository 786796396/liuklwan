package com.ucap.cloud_web.service;


import java.util.HashMap;
import java.util.List;import com.publics.util.page.PageVo;import com.ucap.cloud_web.dto.IndexCountRequest;import com.ucap.cloud_web.entity.IndexCount;


/*** <br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2016-01-05 13:11:41 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public interface IIndexCountService {


	/**	* 添加数据	* @param indexCount			对象			(必填)	*/	public void add(IndexCount indexCount);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return indexCount	*/	public IndexCount get(Integer id);

	/**	* 修改数据	* @param IndexCount			对象			(必填)	*/	public void update(IndexCount indexCount);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<IndexCount>	*/	public PageVo<IndexCount> query(IndexCountRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(IndexCountRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<IndexCount>	*/	public List<IndexCount> queryList(IndexCountRequest request);

	/**
	 * @Description: 通过网站标识码+昨天日期，查询健康指数统计表，获取健康总分数，
	 * @author cuichx --- 2016-1-5下午5:12:14     
	 * @param param
	 * @return
	 */
	public IndexCountRequest queryByMap(HashMap<String, Object> param);
	/**
	 * @Description: 查询健康指数统计表
	 * @author cuichx --- 2016-1-5下午8:53:02     
	 * @param param
	 * @return
	 */
	public List<IndexCountRequest> queryListByMap(HashMap<String, Object> param);
	/**
	 * @Description: 健康指数折线图
	 * @author sunjiang --- 2016-1-6下午4:45:54     
	 * @param param
	 * @return
	 */
	List<IndexCount> getLineList(HashMap<String, Object> param);

	/**
	 * @Description: 获取指定套餐类型的数据
	 * @author cuichx --- 2016-1-10下午5:43:20     
	 * @param param
	 * @return
	 */
	public List<IndexCountRequest> getListByMap(HashMap<String, Object> param);

}

