package com.ucap.cloud_web.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.publics.util.page.PageVo;
import com.ucap.cloud_web.dto.UpdateContentCountRequest;
import com.ucap.cloud_web.entity.UpdateContentCount;


/*** <br>* <b>作者：</b>SunJiang<br>* <b>日期：</b> 2015-10-30 09:51:22 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/public interface IUpdateContentCountService {


	/**	* 添加数据	* @param updateContentCount			对象			(必填)	*/	public void add(UpdateContentCount updateContentCount);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return updateContentCount	*/	public UpdateContentCount get(Integer id);

	/**	* 修改数据	* @param UpdateContentCount			对象			(必填)	*/	public void update(UpdateContentCount updateContentCount);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<UpdateContentCount>	*/	public PageVo<UpdateContentCount> query(UpdateContentCountRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(UpdateContentCountRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<UpdateContentCount>	*/	public List<UpdateContentCount> queryList(UpdateContentCountRequest request);
	
	/**
	 * @Description: 将条件封账到map中查询内容更新统计表
	 * @author cuichx --- 2015-11-9下午10:00:09     
	 * @param map
	 * @return
	 */
	public List<UpdateContentCount> queryByMap(Map<String, Object> map);
	/**
	 * @Description: 内容更新折线图统计
	 * @author sunjiang --- 2015年11月19日上午10:27:16     
	 * @param map
	 * @return
	 */
	public List<UpdateContentCountRequest> queryCountLine(Map<String, Object> map);
	
	/**
	 * @Description: 内容更新趋势分析折线图统计
	 * @author Nora --- 2015年11月25日上午11:27:16     
	 * @param map
	 * @return
	 */
	public List<UpdateContentCount> queryCountAnalyzeLine(UpdateContentCountRequest request);
	/**
	 * @Description: 内容分析  列表  总数
	 * @author sunjiang --- 2015-12-8上午10:04:51     
	 * @param map
	 * @return
	 */
	public List<UpdateContentCountRequest> querySumOrg(Map<String, Object> map);
	/**
	 * @Description:内容分析  栏目分组
	 * @author sunjiang --- 2015-12-8上午10:29:01     
	 * @param map
	 * @return
	 */
	public List<UpdateContentCountRequest> queryChannelName(Map<String, Object> map);
	/**
	 * @Description: 内容更新--首页更新数据统计
	 * @author cuichx --- 2016-1-13下午3:34:06     
	 * @param hashMap
	 * @return
	 */
	public List<UpdateContentCountRequest> querySumByMap(HashMap<String, Object> hashMap);

	/**
	 * @Description: 连续5天首页更新量为0
	 * @author cuichx --- 2016-6-14上午10:04:57     
	 * @param paramMap
	 * @return
	 */
	public List<UpdateContentCountRequest> queryNotUpdateByMap(
			Map<String, Object> paramMap);

	/**
	 * @Description: 首页或栏目共更新
	 * @author cuichx --- 2016-8-23下午10:15:18     
	 * @param paramMap
	 * @return
	 */
	public int queryUpdateConByMap(Map<String, Object> paramMap);

	/**
	 * @描述:日常监测-更新量数据
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2016年12月23日下午2:20:57
	 * @param hashMap
	 * @return
	 */

	public List<UpdateContentCountRequest> getUpdateHomeList(HashMap<String, Object> hashMap);
	/**
	 * @Description:统计某天  各个站点的首页更新量 
	 * @author cuichx --- 2017-3-28下午2:34:47     
	 * @param updateMap
	 * @return
	 */
	public List<UpdateContentCountRequest> queryTotalByMap(
			Map<String, Object> updateMap);
	/**
	 * @Description:统计某天  各个站点的首页更新量 --记录数
	 * @author cuichx --- 2017-3-28下午2:34:47     
	 * @param updateMap
	 * @return
	 */
	public int queryTotalByMapCount(Map<String, Object> updateMap);
	/**
	 * @描述:日常监测-更新量数据--总记录数
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2016年12月23日下午2:20:57
	 * @param hashMap
	 * @return
	 */
	public int getUpdateHomeListCount(HashMap<String, Object> paraMap);

}

