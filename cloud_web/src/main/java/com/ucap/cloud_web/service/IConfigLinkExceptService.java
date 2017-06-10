package com.ucap.cloud_web.service;


import java.util.List;import com.publics.util.page.PageVo;import com.ucap.cloud_web.dto.ConfigLinkExceptRequest;import com.ucap.cloud_web.entity.ConfigLinkExcept;


/*** <br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-08-16 15:13:23 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public interface IConfigLinkExceptService {


	/**	* 添加数据	* @param configLinkExcept			对象			(必填)	*/	public void add(ConfigLinkExcept configLinkExcept);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return configLinkExcept	*/	public ConfigLinkExcept get(Integer id);

	/**	* 修改数据	* @param ConfigLinkExcept			对象			(必填)	*/	public void update(ConfigLinkExcept configLinkExcept);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<ConfigLinkExcept>	*/	public PageVo<ConfigLinkExcept> query(ConfigLinkExceptRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(ConfigLinkExceptRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<ConfigLinkExcept>	*/	public List<ConfigLinkExcept> queryList(ConfigLinkExceptRequest request);

}

