package com.ucap.cloud_web.service;


import java.util.List;import com.publics.util.page.PageVo;import com.ucap.cloud_web.dto.ReportCollectResultRequest;import com.ucap.cloud_web.entity.ReportCollectResult;


/*** <br>* <b>作者：</b>qingjy<br>* <b>日期：</b> 2016-11-24 21:30:34 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public interface IReportCollectResultService {


	/**	* 添加数据	* @param reportCollectResult			对象			(必填)	*/	public int add(ReportCollectResult reportCollectResult);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return reportCollectResult	*/	public ReportCollectResult get(Integer id);

	/**	* 修改数据	* @param ReportCollectResult			对象			(必填)	*/	public void update(ReportCollectResult reportCollectResult);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<ReportCollectResult>	*/	public PageVo<ReportCollectResult> query(ReportCollectResultRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(ReportCollectResultRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<ReportCollectResult>	*/	public List<ReportCollectResult> queryList(ReportCollectResultRequest request);

}

