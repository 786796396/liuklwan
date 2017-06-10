package com.ucap.cloud_web.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.publics.util.page.PageVo;
import com.ucap.cloud_web.dto.SecurityHomeChannelRequest;
import com.ucap.cloud_web.entity.SecurityHomeChannel;

/**
 * <br>
 * <b>作者：</b>SunJiang<br>
 * <b>日期：</b> 2015-12-03 15:22:56 <br>
 * <b>版权所有：<b>版权所有(C) 2015<br>
 */
public interface ISecurityHomeChannelService {

	/**
	 * 添加数据
	 * 
	 * @param securityHomeChannel
	 *            对象 (必填)
	 */
	public void add(SecurityHomeChannel securityHomeChannel);

	/**
	 * 通过主键获取对象数据
	 * 
	 * @param id
	 *            主键 (必填)
	 * @return securityHomeChannel
	 */
	public SecurityHomeChannel get(Integer id);

	/**
	 * 修改数据
	 * 
	 * @param SecurityHomeChannel
	 *            对象 (必填)
	 */
	public void update(SecurityHomeChannel securityHomeChannel);

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
	 * @return PageVo<SecurityHomeChannel>
	 */
	public PageVo<SecurityHomeChannel> query(SecurityHomeChannelRequest request);

	/**
	 * 查询总条数
	 * 
	 * @param request
	 *            前台参数 (必填)
	 * @return int
	 */
	public int queryCount(SecurityHomeChannelRequest request);

	/**
	 * 查询对象集合
	 * 
	 * @param request
	 *            前台参数 (必填)
	 * @return List<SecurityHomeChannel>
	 */
	public List<SecurityHomeChannel> queryList(SecurityHomeChannelRequest request);

	/**
	 * 填报单位查询基本信息 查询对象集合
	 * 
	 * @param request
	 *            前台参数 (必填)
	 * @return List<SecurityHomeChannel>
	 */
	public List<SecurityHomeChannel> queryListTb(SecurityHomeChannelRequest request);

	/**
	 * @Description: 内容保障问题分析图
	 * @author sunjiang --- 2015-12-3下午3:42:46
	 * @return
	 */
	public List<SecurityHomeChannelRequest> getLineList(HashMap<String, Object> map);

	/**
	 * @Description: 获取组织单位当前监测结果中的首页不更新的天数
	 * @author zhurk --- 2015-12-1下午9:39:37
	 * @param map
	 * @return
	 */
	public List<SecurityHomeChannelRequest> getUnUpdateDays(Map<String, Object> map);

	/**
	 * @Description: 组织单位当前监测结果 栏目不更新数
	 * @author zhurk --- 2015-12-2下午2:25:43
	 * @param map
	 * @return
	 */
	public List<SecurityHomeChannelRequest> getUNUpdateSum(Map<String, Object> map);

	/**
	 * @Description: 根据map集合中的条件查询首页/栏目不更新表
	 * @author cuichx --- 2016-1-20上午10:24:36
	 * @param paramMap
	 * @return
	 */
	public List<SecurityHomeChannelRequest> queryByMap(Map<String, Object> paramMap);

	/**
	 * @Description:查询昨天栏目/首页不更新数量
	 * @author cuichx --- 2016-3-6下午2:46:22
	 * @param paramMap
	 * @return
	 */
	public List<SecurityHomeChannelRequest> queryCountByMap(Map<String, Object> paramMap);

	public List<SecurityHomeChannel> queryByTypeDeail(Map<String, Object> paramMap);

	public int queryCountByType(Map<String, Object> paramMap);

	/**
	 * 在线预览栏目不更新 分页
	 * 
	 * @param paramMap
	 * @author liujc -----2016-5-16 21:18:20
	 * @return
	 */
	public PageVo<SecurityHomeChannel> queryReport(SecurityHomeChannelRequest request);

	/**
	 * @Title: 在线报告栏目不更新监测结果 list
	 * @Description:
	 * @author liujc@ucap.com.cn 2016-5-16上午9:02:20
	 * @param request
	 * @return
	 */
	public List<SecurityHomeChannel> queryReportList(SecurityHomeChannelRequest request);

	/**
	 * @Title: 在线报告栏目不更新监测结果 count
	 * @Description:
	 * @author liujc@ucap.com.cn 2016-8-24上午9:02:49
	 * @param request
	 * @return
	 */
	public int queryReportCount(SecurityHomeChannelRequest request);

	// 查询机器录入的数据
	public List<SecurityHomeChannel> queryReportAndNoAuto(SecurityHomeChannelRequest securityHomeChannelRequest);

	/**
	 * @Description: 首页超过两周未更新的网站数
	 * @author cuichx --- 2016-8-23下午9:52:07
	 * @param paramMap
	 * @return
	 */
	public int queryHomeNotUpByMap(Map<String, Object> paramMap);

	/**
	 * 获取基本信息：栏目不更新数据 add by Na.Y 20160906
	 * 
	 * @param siteCode
	 * @param startDate
	 * @param endDate
	 * @param isManual
	 *            是否手动 1：手动 0：机器
	 * @return
	 */
	public List<SecurityHomeChannel> getSecurityChannel(String siteCode, String startDate, String endDate,int servicePeroidId,
			boolean isManual);
	/**
	 * @Description: 同一监测周期  基本信息不更新>=8个
	 * @author cuichx --- 2016-11-1上午10:22:33     
	 * @param paramMap
	 * @return
	 */
	public List<SecurityHomeChannelRequest> queryBasicNum(
			Map<String, Object> paramMap);

	/**
	 * @Description: 查询 收费、全面监测、正常、首页超过10天未更新的站点
	 * @author cuichx --- 2016-11-29下午8:36:43     
	 * @param paramMap
	 * @return 
	 */
	public List<SecurityHomeChannelRequest> queryListByMap(Map<String, Object> paramMap);

	/**
	 * @描述:日常监测-内容保障问题数据
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2016年12月23日下午4:02:27
	 * @param hashMap
	 * @return
	 */

	public List<SecurityHomeChannelRequest> getSecurityHomeList(HashMap<String, Object> hashMap);

	/**
	 * @Description: 查询基本信息  --逾期不更新的总站点数
	 * @author cuichx --- 2017-3-14上午9:49:56     
	 * @param map
	 * @return
	 */
	public int querySiteNumByMap(Map<String, Object> map);

	/**
	 * @Description: 查询基本信息  --逾期不更新的栏目总数
	 * @author cuichx --- 2017-3-14上午10:01:42     
	 * @param map
	 * @return
	 */
	public int querySumByMap(Map<String, Object> map);
	/**
	 * @Description: 统计某日  不同类型的栏目更新数
	 * @author cuichx --- 2017-3-31下午6:01:04     
	 * @param paraMap
	 * @return
	 */
	public List<SecurityHomeChannelRequest> queryNumByType(
			HashMap<String, Object> paraMap);


	/**
	 * 查询正常更新的栏目
	 * @author luocheng --- 2017-03-24   
	 */
	public List<SecurityHomeChannel> queryListTbNormal(
			SecurityHomeChannelRequest homerequest);
	/**
	 * @描述:日常监测-内容保障问题数据--记录数
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2016年12月23日下午4:02:27
	 * @param hashMap
	 * @return
	 */
	public int getSecurityHomeListCount(HashMap<String, Object> paraMap);
	/**
	 * @Description: 统计某日  不同类型的栏目更新数--总记录数
	 * @author cuichx --- 2017-3-31下午6:01:04     
	 * @param paraMap
	 * @return
	 */
	public int queryNumByTypeCount(HashMap<String, Object> paraMap);

	
	
}
