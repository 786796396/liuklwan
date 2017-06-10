package com.ucap.cloud_web.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.publics.util.page.PageVo;
import com.ucap.cloud_web.dto.CorrectContentDetailRequest;
import com.ucap.cloud_web.entity.CorrectContentDetail;


/*** <br>* <b>作者：</b>Nora<br>* <b>日期：</b> 2015-10-29 11:19:33 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/public interface ICorrectContentDetailService {


	/**	* 添加数据	* @param correctContentDetail			对象			(必填)	*/	public void add(CorrectContentDetail correctContentDetail);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return correctContentDetail	*/	public CorrectContentDetail get(Integer id);

	/**	* 修改数据	* @param CorrectContentDetail			对象			(必填)	*/	public void update(CorrectContentDetail correctContentDetail);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<CorrectContentDetail>	*/	public PageVo<CorrectContentDetail> query(CorrectContentDetailRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(CorrectContentDetailRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<CorrectContentDetail>	*/	public List<CorrectContentDetail> queryList(CorrectContentDetailRequest request);
	/**
	 * @Description: 内容正确性每日个数统计查询表
	 * @author cuichx --- 2016-3-17下午10:11:35     
	 * @param hashMap
	 * @return
	 */
	public List<CorrectContentDetailRequest> queryCorrectLine(
			HashMap<String, Object> hashMap);
	
	/**
	 * @Description: 查询内容正确性详情表和databaseInfo表
	 * @author cuichx --- 2016-3-17下午10:58:20     
	 * @param hashMap
	 * @return
	 */
	public List<CorrectContentDetailRequest> queryWrongNum(
			HashMap<String, Object> hashMap);
	
	/**
	 * @Description: 组织单位首页面折线图
	 * @author sunjiang --- 2016-3-31上午11:15:39     
	 * @param hashMap
	 * @return
	 */
	List<CorrectContentDetailRequest> getCorrectLine(Map<Object, Object> parapMap);

	/**
	 * @描述:日常监测-疑似错别字数据
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2016年12月23日下午3:10:33
	 * @param hashMap
	 * @return
	 */

	public List<CorrectContentDetailRequest> getCorrectContentList(HashMap<String, Object> hashMap);
	/**
	 * @描述:日常监测-疑似错别字数据---总记录数
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2016年12月23日下午3:10:33
	 * @param hashMap
	 * @return
	 */
	public int getCorrectContentListCount(HashMap<String, Object> paraMap);

}

