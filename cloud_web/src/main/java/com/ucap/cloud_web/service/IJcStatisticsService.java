package com.ucap.cloud_web.service;


import java.util.List;import com.publics.util.page.PageVo;import com.ucap.cloud_web.dto.JcStatisticsRequest;import com.ucap.cloud_web.entity.JcStatistics;


/*** <br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-10-09 09:53:32 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public interface IJcStatisticsService {


	/**	* 添加数据	* @param jcStatistics			对象			(必填)	*/	public int add(JcStatistics jcStatistics);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return jcStatistics	*/	public JcStatistics get(Integer id);

	/**	* 修改数据	* @param JcStatistics			对象			(必填)	*/	public void update(JcStatistics jcStatistics);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<JcStatistics>	*/	public PageVo<JcStatistics> query(JcStatisticsRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(JcStatisticsRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<JcStatistics>	*/	public List<JcStatistics> queryList(JcStatisticsRequest request);

}

