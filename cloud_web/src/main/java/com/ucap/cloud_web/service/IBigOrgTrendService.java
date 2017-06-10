package com.ucap.cloud_web.service;


import java.util.List;import com.publics.util.page.PageVo;import com.ucap.cloud_web.dto.BigOrgTrendRequest;import com.ucap.cloud_web.entity.BigOrgTrend;


/*** <br>* <b>作者：</b>zhaoDY<br>* <b>日期：</b> 2017-02-14 19:09:38 <br>* <b>版权所有：<b>版权所有(C) 2017<br>*/public interface IBigOrgTrendService {


	/**	* 添加数据	* @param bigOrgTrend			对象			(必填)	*/	public int add(BigOrgTrend bigOrgTrend);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return bigOrgTrend	*/	public BigOrgTrend get(Integer id);

	/**	* 修改数据	* @param BigOrgTrend			对象			(必填)	*/	public void update(BigOrgTrend bigOrgTrend);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<BigOrgTrend>	*/	public PageVo<BigOrgTrend> query(BigOrgTrendRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(BigOrgTrendRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<BigOrgTrend>	*/	public List<BigOrgTrend> queryList(BigOrgTrendRequest request);

}

