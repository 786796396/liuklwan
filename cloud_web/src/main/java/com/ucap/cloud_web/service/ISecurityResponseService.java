package com.ucap.cloud_web.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.publics.util.page.PageVo;
import com.ucap.cloud_web.dto.SecurityBlankInfoRequest;
import com.ucap.cloud_web.dto.SecurityResponseRequest;
import com.ucap.cloud_web.dtoResponse.SecurityGuaranteeResponse;
import com.ucap.cloud_web.entity.SecurityResponse;


/*** <br>* <b>作者：</b>SunJiang<br>* <b>日期：</b> 2015-10-30 09:51:21 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/public interface ISecurityResponseService {


	/**	* 添加数据	* @param securityResponse			对象			(必填)	*/	public void add(SecurityResponse securityResponse);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return securityResponse	*/	public SecurityResponse get(Integer id);

	/**	* 修改数据  	* @param SecurityResponse			对象			(必填)	*/	public void update(SecurityResponse securityResponse);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<SecurityResponse>	*/	public PageVo<SecurityResponse> query(SecurityResponseRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(SecurityResponseRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<SecurityResponse>	*/	public List<SecurityResponse> queryList(SecurityResponseRequest request);
/**	 * @Description: 内容保障分析，互动回应差柱状图	 * @author sunjiang --- 2015年11月19日下午7:03:17     	 * @param param	 * @return	 */	public List<SecurityBlankInfoRequest> queryBarNum(Map<String,Object> param);
	/**	* 查询对象集合--将参数封装到map中	* @param map				前台参数			(必填)	* @return	List<SecurityResponseRequest>	*/	public List<SecurityResponseRequest> queryByMap(Map<String, Object> map);
	/**
	* 查询对象集合--将参数封装到map中
	* @param map				前台参数			(必填)
	* @return	List<SecurityResponseRequest>
	*/
	public List<SecurityResponseRequest> queryByGroup(
			Map<String, Object> paramMap);
	/** @Description: 组织单位-当前监测结果：互动回应差获取问题个数
	 * @author zhurk --- 2015-12-2下午4:33:24     
	 * @param paramMap
	 * @return           
	*/
	public List<SecurityResponseRequest> getProblemNum(
			Map<String, Object> paramMap);
	
	/** @Description: 分类汇总查询数据
	 * @author sunjiaqi --- 2016-4-29上午10:12:08     
	 * @param request
	 * @return           
	*/
	public List<SecurityResponseRequest> queryListByChannelName(SecurityResponseRequest request);

	/**
	 * @Description: 查询互动回应差数据
	 * @author cuichx --- 2016-10-25下午3:17:20     
	 * @param paramMap
	 * @return
	 */
	public List<SecurityResponseRequest> queryResponseNum(
			Map<String, Object> paramMap);

	
	/**
	 * @Description: 根据siteCode和ServicePeriodId来查询当前站点的   互动回应差的数量
	 * @author luocheng --- 2017-01-22    
	 * @param paramMap
	 * @return
	 */
	public List<SecurityGuaranteeResponse> getResponseNumber(HashMap<String, Object> hashMap);
	/**
	 * @Description: 根据tree表获取该组织单位下的 互动回应差的数据
	 * @author cuichx --- 2017-4-6下午3:02:09     
	 * @param paraMap
	 * @return
	 */
	public List<SecurityResponseRequest> queryInfoByTree(
			HashMap<String, Object> paraMap);
	/**
	 * @Description: 根据tree表获取该组织单位下的 互动回应差的数据--总记录数
	 * @author cuichx --- 2017-4-6下午3:02:09     
	 * @param paraMap
	 * @return
	 */
	public int queryInfoByTreeCount(HashMap<String, Object> paraMap);
}

