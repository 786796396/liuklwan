package com.ucap.cloud_web.service;

import java.util.HashMap;
import java.util.List;

import com.publics.util.page.PageVo;
import com.ucap.cloud_web.dto.ChannelPointRequest;
import com.ucap.cloud_web.dtoResponse.ChannelPointResponse;
import com.ucap.cloud_web.dtoResponse.DatabaseInfoResponse;
import com.ucap.cloud_web.entity.ChannelPoint;

/**
 * <br>
 * <b>作者：</b>Nora<br>
 * <b>日期：</b> 2015-10-29 11:19:32 <br>
 * <b>版权所有：<b>版权所有(C) 2015<br>
 */
public interface IChannelPointService {

	/**
	 * 添加数据
	 * 
	 * @param channelPoint
	 *            对象 (必填)
	 */
	public void add(ChannelPoint channelPoint);

	/**
	 * 通过主键获取对象数据
	 * 
	 * @param id
	 *            主键 (必填)
	 * @return channelPoint
	 */
	public ChannelPoint get(Integer id);

	/**
	 * 修改数据
	 * 
	 * @param ChannelPoint
	 *            对象 (必填)
	 */
	public void update(ChannelPoint channelPoint);

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
	 * @return PageVo<ChannelPoint>
	 */
	public PageVo<ChannelPoint> query(ChannelPointRequest request);

	/**
	 * 查询总条数
	 * 
	 * @param request
	 *            前台参数 (必填)
	 * @return int
	 */
	public int queryCount(ChannelPointRequest request);

	/**
	 * 查询对象集合
	 * 
	 * @param request
	 *            前台参数 (必填)
	 * @return List<ChannelPoint>
	 */
	public List<ChannelPoint> queryList(ChannelPointRequest request);

	/**
	 * 在线校验
	 * 
	 * @param channelUrl
	 *            前台参数 (必填)
	 * @return boolean true:校验成功
	 */
	public boolean onLineCheck(String channelUrl);
	
	/**
	 * 查询对象集合
	 * @param request				前台参数			(必填)
	 * @return	List<ChannelPoint>
	 */
	public List<ChannelPointRequest> queryByMap(ChannelPointRequest request);
	
	public int queryCountByType(ChannelPointRequest request);

	/**
	 * @描述:栏目数据
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2017年4月13日下午4:00:54
	 * @param hashMap
	 * @return
	 */

	public List<ChannelPointResponse> getChannelPointInfo(HashMap<String, Object> hashMap);

	/**
	 * @描述:下级站点信息
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2017年4月14日上午10:52:54
	 * @param hashMap
	 * @return
	 */

	public List<DatabaseInfoResponse> getlowerLevelList(HashMap<String, Object> hashMap);
}
