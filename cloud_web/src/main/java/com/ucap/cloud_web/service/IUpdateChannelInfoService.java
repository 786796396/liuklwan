package com.ucap.cloud_web.service;


import java.util.List;import java.util.Map;

import com.publics.util.page.PageVo;import com.ucap.cloud_web.dto.SecurityBlankInfoRequest;
import com.ucap.cloud_web.dto.UpdateChannelInfoRequest;import com.ucap.cloud_web.entity.UpdateChannelInfo;


/*** <br>* <b>作者：</b>SunJiang<br>* <b>日期：</b> 2015-10-30 09:51:22 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/public interface IUpdateChannelInfoService {


	/**	* 添加数据	* @param updateChannelInfo			对象			(必填)	*/	public void add(UpdateChannelInfo updateChannelInfo);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return updateChannelInfo	*/	public UpdateChannelInfo get(Integer id);

	/**	* 修改数据	* @param UpdateChannelInfo			对象			(必填)	*/	public void update(UpdateChannelInfo updateChannelInfo);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<UpdateChannelInfo>	*/	public PageVo<UpdateChannelInfo> query(UpdateChannelInfoRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(UpdateChannelInfoRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<UpdateChannelInfo>	*/	public List<UpdateChannelInfo> queryList(UpdateChannelInfoRequest request);
	/**
	 * @Description: 重点监测栏目的总数
	 * @author sunjiang --- 2015年11月19日下午5:38:38     
	 * @return
	 */
	public List<SecurityBlankInfoRequest> queryPonitNum(Map<String,Object> map);
	/**
	 * @Description: 栏目更新的数量
	 * @author sunjiang --- 2015年11月19日下午5:38:43     
	 * @return
	 */
	public List<SecurityBlankInfoRequest> queryChannelNum(Map<String,Object> map);
	/**
	* 查询对象集合
	* @param paramMap				前台参数			(必填)
	* @return	List<UpdateChannelInfoRequest>
	*/
	public List<UpdateChannelInfoRequest> queryByGroup(
			Map<String, Object> paramMap);
	/** @Description: 组织单位--当前监测结果--首页更新
	 * @author zhurk --- 2015-12-1下午8:00:35     
	 * @param map
	 * @return           
	*/
	public List<UpdateChannelInfoRequest> getChannelNum(
			Map<String, Object> map);
	/**
	 * @Description:组织单位 获取栏目总数 
	 * @author sunjiang --- 2015-12-4下午1:57:41     
	 * @param paramMap
	 * @return
	 */
	public List<UpdateChannelInfo> querySumUpdateNum(Map<String, Object> paramMap);
	/**
	 * @Description: 统计一段时间内所有的栏目更新数
	 * @author cuichx --- 2016-3-17上午1:44:38     
	 * @param infoRequest
	 * @return
	 */
	public List<UpdateChannelInfoRequest> queryListSum(
			Map<String, Object> paramMap);
}

