package com.ucap.cloud_web.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.publics.util.page.PageVo;
import com.ucap.cloud_web.dto.ConnectionHomeDetailRequest;
import com.ucap.cloud_web.entity.ConnectionHomeDetail;
import com.ucap.cloud_web.entity.ContractInfo;

/**
 * <br>
 * <b>作者：</b>Nora<br>
 * <b>日期：</b> 2015-10-29 11:19:33 <br>
 * <b>版权所有：<b>版权所有(C) 2015<br>
 */
public interface IConnectionHomeDetailService {

	/**
	 * 添加数据
	 * 
	 * @param connectionHomeDetail
	 *            对象 (必填)
	 */
	public void add(ConnectionHomeDetail connectionHomeDetail);

	/**
	 * 通过主键获取对象数据
	 * 
	 * @param id
	 *            主键 (必填)
	 * @return connectionHomeDetail
	 */
	public ConnectionHomeDetail get(Integer id);

	/**
	 * 修改数据
	 * 
	 * @param ConnectionHomeDetail
	 *            对象 (必填)
	 */
	public void update(ConnectionHomeDetail connectionHomeDetail);

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
	 * @return PageVo<ConnectionHomeDetail>
	 */
	public PageVo<ConnectionHomeDetail> query(
			ConnectionHomeDetailRequest request);

	/**
	 * 查询总条数
	 * 
	 * @param request
	 *            前台参数 (必填)
	 * @return int
	 */
	public int queryCount(ConnectionHomeDetailRequest request);

	/**
	 * 查询对象集合
	 * 
	 * @param request
	 *            前台参数 (必填)
	 * @return List<ConnectionHomeDetail>
	 */
	public List<ConnectionHomeDetail> queryList(
			ConnectionHomeDetailRequest request);

	/**
	 * 根据扫描日期查询对象集合
	 * 
	 * @param request
	 *            前台参数 (必填)
	 * @return List<ConnectionHomeDetail>
	 */
	public List<ConnectionHomeDetail> queryListByScanDate(
			ConnectionHomeDetailRequest request);

	/**
	 * 判断安全服务
	 * @param siteCode
	 * @param silentNum
	 */
	public HashMap<String, Object> securityServices(List<ContractInfo> list, String siteCode, int silentNum);

	/**
	 * 新产品信息 判断安全服务
	 * 
	 * @param siteCode
	 * @param silentNum
	 */
	// public HashMap<String, Object>
	// crmSecurityServices(List<CrmProductsResponse> list, String siteCode, int
	// silentNum);

	/**
	 * 队列时间
	 * @param siteCode
	 * @author qinjy
	 * @return
	 */
	public String queueTime(String siteCode);

	/**
	 * @Description: 获取某个组织单位下   某天的每个站点最后一次不连通的详情
	 * @author cuichx --- 2017-3-29上午9:33:59     
	 * @param paraMap
	 * @return
	 */
	public List<ConnectionHomeDetailRequest> queryConnByMap(
			Map<String, Object> paraMap);

}
