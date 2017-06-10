package com.ucap.cloud_web.service;


import java.util.List;import java.util.Map;

import com.publics.util.page.PageVo;import com.ucap.cloud_web.dto.DicConfigRequest;import com.ucap.cloud_web.entity.DicConfig;


/*** <br>* <b>作者：</b>yangshuai<br>* <b>日期：</b> 2016-05-24 15:52:08 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public interface IDicConfigService {


	/**	* 添加数据	* @param dicConfig			对象			(必填)	*/	public void add(DicConfig dicConfig);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return dicConfig	*/	public DicConfig get(Integer id);

	/**	* 修改数据	* @param DicConfig			对象			(必填)	*/	public void update(DicConfig dicConfig);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<DicConfig>	*/	public PageVo<DicConfig> query(DicConfigRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(DicConfigRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<DicConfig>	*/	public List<DicConfig> queryList(DicConfigRequest request);

	/**
	 * @Description: 
	 * @author: yangshuai --- 2016-5-24下午4:14:37
	 * @param map
	 */
	public List<DicConfigRequest> queryListByMap(Map<String, Object> params);

}

