package com.ucap.cloud_web.service;


import java.util.List;import com.publics.util.page.PageVo;import com.ucap.cloud_web.dto.TempReportRequest;import com.ucap.cloud_web.entity.TempReport;


/*** <br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-09-13 11:30:36 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public interface ITempReportService {


	/**	* 添加数据	* @param tempReport			对象			(必填)	*/	public int add(TempReport tempReport);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return tempReport	*/	public TempReport get(Integer id);

	/**	* 修改数据	* @param TempReport			对象			(必填)	*/	public void update(TempReport tempReport);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<TempReport>	*/	public PageVo<TempReport> query(TempReportRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(TempReportRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<TempReport>	*/	public List<TempReport> queryList(TempReportRequest request);

	/**
	 * linhb 2016-09-13
	 * 组织单位按照条件查询
	 * @param tRequest
	 * @return 
	 */
	
	public List<TempReport> joinLinkData(TempReportRequest tRequest);
	
	
	/**
	 * 
	 * @描述:查询下级省市县  
	 * @作者:liukl@ucap.com.cn
	 * @时间:2016年12月29日20:32:46
	 * @param TempReportRequest
	 * @return
	 */
	public PageVo<TempReport> queryLowerSubordinateUnits(
			TempReportRequest tRequest);

}

