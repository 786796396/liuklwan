package com.ucap.cloud_web.service;


import java.util.List;import java.util.Map;

import com.publics.util.page.PageVo;import com.ucap.cloud_web.dto.EarlyInfoRequest;import com.ucap.cloud_web.entity.EarlyInfo;


/*** <br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2015-10-29 11:05:30 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/public interface IEarlyInfoService {


	/**	* 添加数据	* @param earlyInfo			对象			(必填)	*/	public void add(EarlyInfo earlyInfo);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return earlyInfo	*/	public EarlyInfo get(Integer id);

	/**	* 修改数据	* @param EarlyInfo			对象			(必填)	*/	public void update(EarlyInfo earlyInfo);
	/**
	 * @Description: 更新新预警数
	 * @author sunjiang --- 2016-1-25下午5:42:26     
	 * @param earlyInfo
	 */
	public void updateNewEarlyNum(EarlyInfo earlyInfo);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<EarlyInfo>	*/	public PageVo<EarlyInfo> query(EarlyInfoRequest request);
	
	/**
	 * @Description: 分页   新预警的和
	 * @author sunjiang --- 2016-1-22上午11:31:56     
	 * @param request
	 * @return
	 */
	public PageVo<EarlyInfo> queryEarlyInfo(EarlyInfoRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(EarlyInfoRequest request);
	/**
	 * @Description: 查询总数
	 * @author sunjiang --- 2016-1-26上午10:40:13     
	 * @param request
	 * @return
	 */
	public int queryEarlyInfoCount(EarlyInfoRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<EarlyInfo>	*/	public List<EarlyInfo> queryList(EarlyInfoRequest request);
	
	/**
	 * @Description: 每个站站的预警总数
	 * @author sunjiang --- 2016-3-29下午4:26:55     
	 * @param request
	 * @return
	 */
	public List<EarlyInfo> queryEarlyInfoList(EarlyInfoRequest request);

	/**
	 * @Description: 获取组织机构下的所有新预警数
	 * @author cuichx --- 2015-12-18下午4:34:49     
	 * @param param
	 * @return
	 */
	public EarlyInfo querySum(Map<String, Object> param);
	/**
	 * @Description: 查询新预警数
	 * @author sunjiang --- 2016-1-25下午5:52:03     
	 * @param param
	 * @return
	 */
	public EarlyInfo queryNewEarlyNum(Map<String, Object> param);

}

