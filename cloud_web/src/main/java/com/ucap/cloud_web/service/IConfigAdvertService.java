package com.ucap.cloud_web.service;


import java.util.List;import com.publics.util.page.PageVo;import com.ucap.cloud_web.dto.ConfigAdvertRequest;import com.ucap.cloud_web.entity.ConfigAdvert;


/*** <br>* <b>作者：</b>yangshuai<br>* <b>日期：</b> 2016-09-06 19:26:19 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public interface IConfigAdvertService {


	/**	* 添加数据	* @param configAdvert			对象			(必填)	*/	public int add(ConfigAdvert configAdvert);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return configAdvert	*/	public ConfigAdvert get(Integer id);

	/**	* 修改数据	* @param ConfigAdvert			对象			(必填)	*/	public void update(ConfigAdvert configAdvert);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<ConfigAdvert>	*/	public PageVo<ConfigAdvert> query(ConfigAdvertRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(ConfigAdvertRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<ConfigAdvert>	*/	public List<ConfigAdvert> queryList(ConfigAdvertRequest request);

}

