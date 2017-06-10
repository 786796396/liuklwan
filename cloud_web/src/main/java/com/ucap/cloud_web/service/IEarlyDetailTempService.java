package com.ucap.cloud_web.service;


import java.util.List;
import java.util.Map;

import com.publics.util.page.PageVo;
import com.ucap.cloud_web.dto.EarlyDetailTempRequest;
import com.ucap.cloud_web.entity.ConfigEarly;
import com.ucap.cloud_web.entity.EarlyDetailTemp;


/*** <br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2017-03-14 14:39:10 <br>* <b>版权所有：<b>版权所有(C) 2017<br>*/public interface IEarlyDetailTempService {


	/**	* 添加数据	* @param earlyDetailTemp			对象			(必填)	*/	public int add(EarlyDetailTemp earlyDetailTemp);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return earlyDetailTemp	*/	public EarlyDetailTemp get(Integer id);

	/**	* 修改数据	* @param EarlyDetailTemp			对象			(必填)	*/	public void update(EarlyDetailTemp earlyDetailTemp);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<EarlyDetailTemp>	*/	public PageVo<EarlyDetailTemp> query(EarlyDetailTempRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(EarlyDetailTempRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<EarlyDetailTemp>	*/	public List<EarlyDetailTemp> queryList(EarlyDetailTempRequest request);

	/**
	 * @Description: 获取实时预警信息
	 * @author cuichx --- 2017-3-14下午3:32:37     
	 * @param earlyDetailRequest
	 * @return
	 */
	public List<EarlyDetailTemp> querySiteEarlyInfo(EarlyDetailTempRequest earlyDetailTempRequest);

	/**
	 * @Description:   根据新老合配置数据 对比  维护增删预警池数据
	 * @author: renpb --- 2017年4月13日下午5:02:53
	 * @return
	 */
	public void updateEarlyDetailTemp(ConfigEarly oldConfigEarly,ConfigEarly nowConfigEarly);
	/**
	 * @Description:   获取今天所有首页连不通数据
	 * @author: cuichx --- 2017年4月13日下午5:02:53
	 * @param paraMap
	 * @return
	 */
	public List<EarlyDetailTempRequest> queryEarlyTempByMap(
			Map<String, Object> paraMap);
	/**
	 * @Description:   获取今天所有首页连不通数据----记录数
	 * @author: cuichx --- 2017年4月13日下午5:02:53
	 * @param paraMap
	 * @return
	 */
	public int queryEarlyTempByMapCount(Map<String, Object> paraMap);
	/**
	 * @Description:   获取今天所有首页连不通数据(填报单位)
	 * @author: cuichx --- 2017年4月13日下午5:02:53
	 * @param paraMap
	 * @return
	 */
	public List<EarlyDetailTempRequest> queryEarlyTempByMapCountTb(Map<String, Object> paraMap);
}

