package com.ucap.cloud_web.service;


import java.util.List;import com.publics.util.page.PageVo;import com.ucap.cloud_web.dto.SpotSecurityReportRequest;import com.ucap.cloud_web.entity.SpotSecurityReport;


/*** <br>* <b>作者：</b>liukl<br>* <b>日期：</b> 2017-03-21 18:49:16 <br>* <b>版权所有：<b>版权所有(C) 2017<br>*/public interface ISpotSecurityReportService {


	/**	* 添加数据	* @param spotSecurityReport			对象			(必填)	*/	public int add(SpotSecurityReport spotSecurityReport);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return spotSecurityReport	*/	public SpotSecurityReport get(Integer id);

	/**	* 修改数据	* @param SpotSecurityReport			对象			(必填)	*/	public void update(SpotSecurityReport spotSecurityReport);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<SpotSecurityReport>	*/	public PageVo<SpotSecurityReport> query(SpotSecurityReportRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(SpotSecurityReportRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<SpotSecurityReport>	*/	public List<SpotSecurityReport> queryList(SpotSecurityReportRequest request);

}

