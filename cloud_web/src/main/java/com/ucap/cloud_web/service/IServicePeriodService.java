package com.ucap.cloud_web.service;

import java.util.List;
import java.util.Map;

import com.publics.util.page.PageVo;
import com.ucap.cloud_web.dto.ServicePeriodRequest;
import com.ucap.cloud_web.entity.ServicePeriod;


/*** <br>* <b>作者：</b>Sunjiang<br>* <b>日期：</b> 2016-03-05 16:17:04 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public interface IServicePeriodService {


	/**	* 添加数据	* @param servicePeriod			对象			(必填)	*/	public void add(ServicePeriod servicePeriod);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return servicePeriod	*/	public ServicePeriod get(Integer id);

	/**	* 修改数据	* @param ServicePeriod			对象			(必填)	*/	public void update(ServicePeriod servicePeriod);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<ServicePeriod>	*/	public PageVo<ServicePeriod> query(ServicePeriodRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(ServicePeriodRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<ServicePeriod>	*/	public List<ServicePeriod> queryList(ServicePeriodRequest request);
	
	/**
	 * 将参数封装到map集合中查询对象集合
	 * @param paramMap
	 * @return List<ServicePeriodRequest> 
	 */
	public List<ServicePeriodRequest> queryByGroup(Map<String, Object> paramMap);

	/**
	 * 根据组织单位id查询其服务周期链接relationPeriod表
	 * @param siteCode
	 * @return
	 */
	public List<ServicePeriodRequest> queryByRelationPeriod(ServicePeriodRequest request);

	public List<ServicePeriodRequest> queryByRelationPeriodBasic(
			Map<String, Object> param);
	/**
	 * 获取高级版服务周期
	 * @param paramMap
	 * @return List<ServicePeriod> 
	 */
	public List<ServicePeriod> queryAdvanceService(Map<String, Object> map);

}

