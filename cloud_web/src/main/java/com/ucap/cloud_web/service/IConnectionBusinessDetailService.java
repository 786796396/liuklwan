package com.ucap.cloud_web.service;

import java.util.List;
import java.util.Map;

import com.publics.util.page.PageVo;
import com.ucap.cloud_web.dto.ConnectionBusinessDetailRequest;
import com.ucap.cloud_web.entity.ConnectionBusinessDetail;

/**
 * <br>
 * <b>作者：</b>Nora<br>
 * <b>日期：</b> 2015-10-29 11:19:33 <br>
 * <b>版权所有：<b>版权所有(C) 2015<br>
 */
public interface IConnectionBusinessDetailService {

	/**
	 * 添加数据
	 * 
	 * @param connectionBusinessDetail
	 *            对象 (必填)
	 */
	public void add(ConnectionBusinessDetail connectionBusinessDetail);

	/**
	 * 通过主键获取对象数据
	 * 
	 * @param id
	 *            主键 (必填)
	 * @return connectionBusinessDetail
	 */
	public ConnectionBusinessDetail get(Integer id);

	/**
	 * 修改数据
	 * 
	 * @param ConnectionBusinessDetail
	 *            对象 (必填)
	 */
	public void update(ConnectionBusinessDetail connectionBusinessDetail);

	/**
	 * 通过id删除数据
	 * 
	 * @param id
	 *            对象 (必填)
	 */
	public void delete(Integer id);

	/**
	 * 通过对象获取分页数据
	 * 
	 * @param request
	 *            dto对象 (必填)
	 * @return PageVo<ConnectionBusinessDetail>
	 */
	public PageVo<ConnectionBusinessDetail> query(
			ConnectionBusinessDetailRequest request);

	/**
	 * 查询总条数
	 * 
	 * @param request
	 *            前台参数 (必填)
	 * @return int
	 */
	public int queryCount(ConnectionBusinessDetailRequest request);

	/**
	 * 查询对象集合
	 * 
	 * @param request
	 *            前台参数 (必填)
	 * @return List<ConnectionBusinessDetail>
	 */
	public List<ConnectionBusinessDetail> queryList(
			ConnectionBusinessDetailRequest request);
	/**
	 * 获取每个站点中每个栏目的最后不连通的数据
	 * @param paraMap
	 * @return
	 */
	public List<ConnectionBusinessDetailRequest> queryListByGroup(
			Map<String, Object> paraMap);
	/**
	 * 获取每个站点中每个栏目的最后不连通的数据---总记录数
	 * @param paraMap
	 * @return
	 */
	public int queryListByGroupCount(Map<String, Object> paraMap);
}
