package com.ucap.cloud_web.service;


import java.util.List;import java.util.Map;

import com.publics.util.page.PageVo;import com.ucap.cloud_web.dto.SpotCheckResultRequest;import com.ucap.cloud_web.entity.SpotCheckResult;


/*** <br>* <b>作者：</b>yuangw@ucap.com.cn<br>* <b>日期：</b> 2016-04-19 11:16:29 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public interface ISpotCheckResultService {


	/**	* 添加数据	* @param spotCheckResult			对象			(必填)	*/	public void add(SpotCheckResult spotCheckResult);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return spotCheckResult	*/	public SpotCheckResult get(Integer id);

	/**	* 修改数据	* @param SpotCheckResult			对象			(必填)	*/	public void update(SpotCheckResult spotCheckResult);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<SpotCheckResult>	*/	public PageVo<SpotCheckResult> query(SpotCheckResultRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(SpotCheckResultRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<SpotCheckResult>	*/	public List<SpotCheckResult> queryList(SpotCheckResultRequest request);

	/**
	 * @Description: 获取每个省的抽查站点个数
	 * @author cuichx --- 2016-5-3下午7:16:11     
	 * @param params
	 * @return
	 */
	public List<SpotCheckResultRequest> queryByMap(Map<String, Object> params);
	
	/**
	 * 按抽查进度批量删除
	 * @param spotCheckSchedule
	 */
	void deleteBatchBySchedule(int spotCheckSchedule);

}

