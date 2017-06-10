package com.ucap.cloud_web.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.publics.util.page.PageVo;import com.ucap.cloud_web.dto.SpSiteRequest;import com.ucap.cloud_web.entity.SpSite;


/*** <br>* <b>作者：</b>liujc<br>* <b>日期：</b> 2016-11-22 10:30:41 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public interface ISpSiteService {


	/**	* 添加数据	* @param spSite			对象			(必填)	*/	public int add(SpSite spSite);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return spSite	*/	public SpSite get(Integer id);

	/**	* 修改数据	* @param SpSite			对象			(必填)	*/	public void update(SpSite spSite);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<SpSite>	*/	public PageVo<SpSite> query(SpSiteRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(SpSiteRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<SpSite>	*/	public List<SpSite> queryList(SpSiteRequest request);

	/**
	 * @描述:根据域名获取siteCode
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2016年12月9日下午1:23:32 
	 * @return 
	 */
	
	public Map<String, Object> domainName(String uri);

	/**
	 * @描述:查询省的域名
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2016年12月14日上午9:47:38 
	 * @param spMap
	 * @return 
	 */
	
	public List<SpSite> getSpSiteList(HashMap<String, Object> spMap);

}

