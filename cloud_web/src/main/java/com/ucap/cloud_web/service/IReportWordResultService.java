package com.ucap.cloud_web.service;


import java.util.List;import java.util.Map;

import com.publics.util.page.PageVo;
import com.ucap.cloud_web.dto.ReportWordResultRequest;import com.ucap.cloud_web.entity.ReportWordResult;


/*** <br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2015-10-29 11:11:46 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/public interface IReportWordResultService {


	/**	* 添加数据	* @param reportWordResult			对象			(必填)	*/	public void add(ReportWordResult reportWordResult);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return reportWordResult	*/	public ReportWordResult get(Integer id);

	/**	* 修改数据	* @param ReportWordResult			对象			(必填)	*/	public void update(ReportWordResult reportWordResult);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<ReportWordResult>	*/	public List<ReportWordResult> queryList(ReportWordResultRequest request);
	/**
	 * @Description: 将条件封装到map中，查询报告管理表
	 * @author cuichx --- 2015-12-11下午1:45:25     
	 * @param paramMap
	 * @return
	 */
	public List<ReportWordResult> queryByMap(Map<String, Object> paramMap);
	
	/**
	 * @Description: 将条件封装到map中，查询下载报告数据
	 * @author: yangshuai --- 2016-6-7下午1:54:11
	 * @return
	 */
	public List<ReportWordResult> queryByMapWord(Map<String, Object> paramMap);

	/**
	 * @Description: 讲条件封装到map集合中，联表查询报告表、站点信息表和服务周期表
	 * @author cuichx --- 2016-2-2下午4:11:45     
	 * @param paramMap
	 * @return
	 */
	public List<ReportWordResult> findSiteByMap(Map<String, Object> paramMap);
	/**
	 * 根据ReportWordResult 查询ReportWordResult
	 * 
	 * @param customerCode
	 * @return
	 */
	public ReportWordResult findBySitCode(String siteCode);
	/**
	 * 下级整改通知，分页
	 * @param 
	 * @author: liukl --- 2017年1月11日18:30:19
	 * @return
	 */
	public PageVo<ReportWordResult> queryRectificationNotice(
			ReportWordResultRequest wordRequest);
}

