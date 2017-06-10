package com.ucap.cloud_web.service;


import java.util.HashMap;
import java.util.List;import java.util.Map;

import com.publics.util.page.PageVo;import com.ucap.cloud_web.dto.SecurityBasicInfoRequest;import com.ucap.cloud_web.entity.SecurityBasicInfo;


/*** <br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2016-04-12 10:13:05 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public interface ISecurityBasicInfoService {


	/**	* 添加数据	* @param securityBasicInfo			对象			(必填)	*/	public void add(SecurityBasicInfo securityBasicInfo);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return securityBasicInfo	*/	public SecurityBasicInfo get(Integer id);

	/**	* 修改数据	* @param SecurityBasicInfo			对象			(必填)	*/	public void update(SecurityBasicInfo securityBasicInfo);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<SecurityBasicInfo>	*/	public PageVo<SecurityBasicInfo> query(SecurityBasicInfoRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(SecurityBasicInfoRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<SecurityBasicInfo>	*/	public List<SecurityBasicInfo> queryList(SecurityBasicInfoRequest request);

	/**
	 * @Description: 获取组织单位内容保障问题-基本信息问题统计列表
	 * @author cuichx --- 2016-3-30下午6:19:00     
	 * @param hashMap
	 * @return
	 */
	public List<SecurityBasicInfoRequest> getProblemNum(
			HashMap<String, Object> hashMap);

	/**
	 * @Description: 同一监测周期  基本信息不更新>=8个
	 * @author cuichx --- 2016-7-15下午12:09:02     
	 * @param paramMap
	 * @return
	 */
	public List<SecurityBasicInfoRequest> queryBasicNum(Map<String, Object> paramMap);

}

