package com.ucap.cloud_web.service;


import java.util.List;import com.publics.util.page.PageVo;import com.ucap.cloud_web.dto.BigSiteOverviewRequest;import com.ucap.cloud_web.entity.BigSiteOverview;


/*** <br>* <b>作者：</b>liukl<br>* <b>日期：</b> 2017-03-08 14:46:28 <br>* <b>版权所有：<b>版权所有(C) 2017<br>*/public interface IBigSiteOverviewService {


	/**	* 添加数据	* @param bigSiteOverview			对象			(必填)	*/	public int add(BigSiteOverview bigSiteOverview);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return bigSiteOverview	*/	public BigSiteOverview get(Integer id);

	/**	* 修改数据	* @param BigSiteOverview			对象			(必填)	*/	public void update(BigSiteOverview bigSiteOverview);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<BigSiteOverview>	*/	public PageVo<BigSiteOverview> query(BigSiteOverviewRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(BigSiteOverviewRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<BigSiteOverview>	*/	public List<BigSiteOverview> queryList(BigSiteOverviewRequest request);

}

