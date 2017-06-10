package com.ucap.cloud_web.service;


import java.util.List;import com.publics.util.page.PageVo;import com.ucap.cloud_web.dto.SpotCheckScheduleRequest;import com.ucap.cloud_web.entity.SpotCheckSchedule;


/*** <br>* <b>作者：</b>yuangw@ucap.com.cn<br>* <b>日期：</b> 2016-04-19 11:16:49 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public interface ISpotCheckScheduleService {


	/**	* 添加数据	* @param spotCheckSchedule			对象			(必填)	*/	public void add(SpotCheckSchedule spotCheckSchedule);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return spotCheckSchedule	*/	public SpotCheckSchedule get(Integer id);

	/**	* 修改数据	* @param SpotCheckSchedule			对象			(必填)	*/	public void update(SpotCheckSchedule spotCheckSchedule);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<SpotCheckSchedule>	*/	public PageVo<SpotCheckSchedule> query(SpotCheckScheduleRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(SpotCheckScheduleRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<SpotCheckSchedule>	*/	public List<SpotCheckSchedule> queryList(SpotCheckScheduleRequest request);
	
	/**
	* 查询任务批次集合
	* @param request				前台参数			(必填)
	* @return	List<SpotCheckSchedule>
	*/
	public List<SpotCheckSchedule> queryBatch(SpotCheckScheduleRequest request);
	/**
	* 查询抽查汇报书集合
	* @param request				前台参数			(必填)
	* @return	List<SpotCheckSchedule>
	*/
	public List<SpotCheckSchedule> spotCheckReportUpList(SpotCheckScheduleRequest request);
			

}

