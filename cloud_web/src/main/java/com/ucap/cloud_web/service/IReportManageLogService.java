package com.ucap.cloud_web.service;


import java.util.List;import java.util.Map;
import com.publics.util.page.PageVo;import com.ucap.cloud_web.dto.ReportManageLogRequest;import com.ucap.cloud_web.entity.ReportManageLog;


/*** <br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2015-10-29 11:11:37 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/public interface IReportManageLogService {


	/**	* 添加数据	* @param reportManageLog			对象			(必填)	*/	public void add(ReportManageLog reportManageLog);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return reportManageLog	*/	public ReportManageLog get(Integer id);

	/**	* 修改数据	* @param ReportManageLogAction			对象			(必填)	*/	public void update(ReportManageLog reportManageLog);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<ReportManageLog>	*/	public PageVo<ReportManageLog> query(ReportManageLogRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(ReportManageLogRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<ReportManageLog>	*/	public List<ReportManageLog> queryList(ReportManageLogRequest request);
	/**
	 * @Description:将参数封装到map中，查询报告管理 
	 * @author cuichx --- 2015-12-10下午1:01:08     
	 * @param paramMap
	 * @return
	 */
	public List<ReportManageLogRequest> queryByMap(Map<String, Object> paramMap);

	/**
	 * @Description: 报告管理发送成功失败统计
	 * @author cuichx --- 2015-12-18下午6:47:22     
	 * @param param
	 * @return
	 */
	public ReportManageLogRequest querySum(Map<String, Object> param);

	/**
	 * @Description: 将参数封装到map中，查询报告管理 
	 * @author cuichx --- 2016-1-21下午5:14:52     
	 * @param map
	 * @return
	 */
	public List<ReportManageLogRequest> queryListByMap(Map<String, Object> map);


}

