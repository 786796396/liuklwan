package com.ucap.cloud_web.service;


import java.util.List;import java.util.Map;

import com.publics.util.page.PageVo;import com.ucap.cloud_web.dto.EarlyDetailRequest;import com.ucap.cloud_web.entity.EarlyDetail;


/*** <br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2015-10-29 11:05:04 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/public interface IEarlyDetailService {


	/**	* 添加数据	* @param earlyDetail			对象			(必填)	*/	public void add(EarlyDetail earlyDetail);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return earlyDetail	*/	public EarlyDetail get(Integer id);

	/**	* 修改数据	* @param EarlyDetail			对象			(必填)	*/	public void update(EarlyDetail earlyDetail);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<EarlyDetail>	*/	public PageVo<EarlyDetail> query(EarlyDetailRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(EarlyDetailRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<EarlyDetail>	*/	public List<EarlyDetail> queryList(EarlyDetailRequest request);

	/**
	 * @Description:将查询条件封装到map集合中，查询后台数据 
	 * @author cuichx --- 2015-12-18下午5:28:50     
	 * @param paramMap
	 * @return
	 */
	public List<EarlyDetailRequest> queryByMap(Map<String, Object> paramMap);
	/**
	 * @Title: 组织单位预警日报信息
	 * @Description:
	 * @author liujc@ucap.com.cn	2016-7-29下午4:42:13
	 * @param request
	 * @return
	 */
	public List<EarlyDetail> queryDailyInfo(EarlyDetailRequest request);
	/**
	 * @Title: 站点预警信息 10位 sitecode
	 * @Description:
	 * @author liujc@ucap.com.cn	2016-8-3上午11:36:43
	 * @param request
	 * @return
	 */
	public List<EarlyDetail> querySiteEarlyInfo(EarlyDetailRequest request);

	/**
	 * @Title: 获取  播报数据
	 * @Description:
	 * @author linhb@ucap.com.cn	2016-10-09上午11:36:43
	 * @param eRequest 
	 * @return
	 */
	public List<EarlyDetail> queryDatas(EarlyDetailRequest eRequest);
	/**
	 * @Title: 获取  播报数据 首页不连通
	 * @Description:
	 * @author linhb@ucap.com.cn	2016-10-09上午11:36:43
	 * @param eRequest 
	 * @return
	 */
	public List<EarlyDetail> queryNoConDatas(EarlyDetailRequest eRequest);
}

