package com.ucap.cloud_web.service;


import java.util.List;
import java.util.Map;

import com.publics.util.page.PageVo;
import com.ucap.cloud_web.dto.MTaskdetailRequest;
import com.ucap.cloud_web.entity.MTaskdetail;
import com.ucap.cloud_web.entity.Result;


/**
* <br>
* <b>作者：</b>liujc<br>
* <b>日期：</b> 2016-10-17 15:03:01 <br>
* <b>版权所有：<b>版权所有(C) 2016<br>
*/
public interface IMTaskdetailService {


	/**
	* 添加数据
	* @param mTaskdetail			对象			(必填)
	*/
	public int add(MTaskdetail mTaskdetail);

	/**
	* 通过主键获取对象数据
	* @param id						主键			(必填)
	* @return mTaskdetail
	*/
	public MTaskdetail get(Integer id);

	/**
	* 修改数据
	* @param MTaskdetail			对象			(必填)
	*/
	public void update(MTaskdetail mTaskdetail);

	/**
	* 通过id删除数据
	* @param id						对象			(必填)
	*/
	public void delete(Integer id);

	/**
	* 通过对象获取分页数据
	* @param request				dto对象			(必填)
	* @return	PageVo<MTaskdetail>
	*/
	public PageVo<MTaskdetail> query(MTaskdetailRequest request);

	/**
	* 查询总条数
	* @param request				前台参数			(必填)
	* @return	int
	*/
	public int queryCount(MTaskdetailRequest request);

	/**
	* 查询对象集合
	* @param request				前台参数			(必填)
	* @return	List<MTaskdetail>
	*/
	public List<MTaskdetail> queryList(MTaskdetailRequest request);
	
	/**
	* 查询对象集合
	* @param request				前台参数			(必填)
	* @return	List<MTaskdetail>
	*/
	public List<Result> queryResultList(MTaskdetailRequest request);
	/**
	 * @Description: 查询 昨天、全面监测中 、前7天首页不连通占比超过3%
	 * @author cuichx --- 2016-12-20下午1:22:07     
	 * @param paramMap
	 * @return
	 */
	public List<MTaskdetailRequest> queryByMap(Map<String, Object> paramMap);

	/**
	 * @Description: 前7天首页不连通占比超过3%
	 * @author cuichx --- 2017-3-13下午3:36:02     
	 * @param paramMap
	 * @return
	 */
	public int queryConnPer(Map<String, Object> paramMap);
	/**
	 * @Description:查询某个组织单位下   前7天首页不连通率超过3% 
	 * @author cuichx --- 2017-3-29上午10:45:22     
	 * @param paraMap
	 * @return
	 */
	public List<MTaskdetailRequest> queryPerLin7ByMap(
			Map<String, Object> paraMap);
	/**
	 * @Description:查询某个组织单位下   前7天首页不连通率超过3%----记录数
	 * @author cuichx --- 2017-3-29上午10:45:22     
	 * @param paraMap
	 * @return
	 */
	public int queryPerLin7ByMapCount(Map<String, Object> paraMap);

}

