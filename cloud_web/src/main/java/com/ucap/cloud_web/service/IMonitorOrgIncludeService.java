package com.ucap.cloud_web.service;


import java.util.HashMap;
import java.util.List;import com.publics.util.page.PageVo;import com.ucap.cloud_web.dto.MonitorOrgIncludeRequest;
import com.ucap.cloud_web.entity.JcVisitOrg;
import com.ucap.cloud_web.entity.MonitorOrgInclude;


/*** <br>* <b>作者：</b>liujc<br>* <b>日期：</b> 2016-11-02 11:00:54 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public interface IMonitorOrgIncludeService {


	/**	* 添加数据	* @param monitorOrgInclude			对象			(必填)	*/	public int add(MonitorOrgInclude monitorOrgInclude);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return monitorOrgInclude	*/	public MonitorOrgInclude get(Integer id);

	/**	* 修改数据	* @param MonitorOrgInclude			对象			(必填)	*/	public void update(MonitorOrgInclude monitorOrgInclude);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<MonitorOrgInclude>	*/	public PageVo<MonitorOrgInclude> query(MonitorOrgIncludeRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(MonitorOrgIncludeRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<MonitorOrgInclude>	*/	public List<MonitorOrgInclude> queryList(MonitorOrgIncludeRequest request);

	/**
	 * @描述:查询组织单位最大日期 
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2016年11月30日下午4:44:58 
	 * @param map
	 * @return 
	 */
	
	public String queryMaxDate(HashMap<String, Object> map);
	/**
	 * 
	 * @描述: 链接 tree 表获取数据
	 * @作者:linhb@ucap.com.cn
	 * @时间:2016年12月24日下午2:21:45 
	 * @param rRequest
	 * @return
	 */
	public List<MonitorOrgInclude> getOrgData(MonitorOrgIncludeRequest rRequest);

}

