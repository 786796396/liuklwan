package com.ucap.cloud_web.service;


import java.util.List;import java.util.Map;

import com.publics.util.page.PageVo;import com.ucap.cloud_web.dto.ConfigEarlyRequest;import com.ucap.cloud_web.dto.EarlyDetailRequest;
import com.ucap.cloud_web.entity.ConfigEarly;
import com.ucap.cloud_web.entity.EarlyDetail;


/*** <br>* <b>作者：</b>liujc<br>* <b>日期：</b> 2016-07-20 14:57:52 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public interface IConfigEarlyService {


	/**	* 添加数据	* @param configEarly			对象			(必填)	*/	public void add(ConfigEarly configEarly);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return configEarly	*/	public ConfigEarly get(Integer id);

	/**	* 修改数据	* @param ConfigEarly			对象			(必填)	*/	public void update(ConfigEarly configEarly);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<ConfigEarly>	*/	public PageVo<ConfigEarly> query(ConfigEarlyRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(ConfigEarlyRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<ConfigEarly>	*/	public List<ConfigEarly> queryList(ConfigEarlyRequest request);
	/**
	 * @Title: 查询组织单位单站预警 6位sitecode
	 * @Description:
	 * @author liujc@ucap.com.cn	2016-8-2上午9:28:17
	 * @param request
	 * @return
	 */
	public List<ConfigEarly> queryOrgSingleSiteCode(ConfigEarlyRequest request);

	/**
	 * @Description:查询配置信息表 
	 * @author cuichx --- 2016-10-27下午6:16:16     
	 * @param paramMap
	 * @return
	 */
	public List<ConfigEarly> findByMap(Map<String, Object> paramMap);

}

