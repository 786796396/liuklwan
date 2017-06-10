package com.ucap.cloud_web.service;


import java.util.List;import java.util.Map;

import com.publics.util.page.PageVo;import com.ucap.cloud_web.dto.LinkAllDetailRequest;import com.ucap.cloud_web.entity.LinkAllDetail;


/*** <br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2015-10-29 11:05:51 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/public interface ILinkAllDetailService {


	/**	* 添加数据	* @param linkAllDetail			对象			(必填)	*/	public void add(LinkAllDetail linkAllDetail);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return linkAllDetail	*/	public LinkAllDetail get(Integer id);

	/**	* 修改数据	* @param LinkAllDetail			对象			(必填)	*/	public void update(LinkAllDetail linkAllDetail);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<LinkAllDetail>	*/	public PageVo<LinkAllDetail> query(LinkAllDetailRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(LinkAllDetailRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<LinkAllDetail>	*/	public List<LinkAllDetail> queryList(LinkAllDetailRequest request);

	public PageVo<LinkAllDetail> queryNoExcept(LinkAllDetailRequest request);
	/**
	 * 查询 config_link_except 表中siteCode 相同时 url不相同的
	 * @param request
	 * @return
	 */
	List<LinkAllDetail> queryNoExceptList(LinkAllDetailRequest request);
	/**
	 * @Description:根据tree表查询  某个组织单位下  某个服务周期内  每类全站死链编码的问题个数 
	 * @author cuichx --- 2017-4-7下午6:42:45     
	 * @param paramMap
	 * @return
	 */
	public List<LinkAllDetailRequest> querySumGroupBy(
			Map<String, Object> paramMap);
	/**
	 * @Description: 统计某个服务周期内  每种死链编码的个数
	 * @author cuichx --- 2017-4-8下午1:03:02     
	 * @param paramMap
	 * @return
	 */
	public List<LinkAllDetailRequest> queryByCode(Map<Object, Object> paramMap);

}

