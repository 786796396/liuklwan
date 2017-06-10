package com.ucap.cloud_web.service;


import java.util.List;import com.publics.util.page.PageVo;import com.ucap.cloud_web.dto.BigDataTrendRequest;import com.ucap.cloud_web.entity.BigDataTrend;


/*** <br>* <b>作者：</b>zhaoDY<br>* <b>日期：</b> 2017-02-14 19:11:25 <br>* <b>版权所有：<b>版权所有(C) 2017<br>*/public interface IBigDataTrendService {


	/**	* 添加数据	* @param bigDataTrend			对象			(必填)	*/	public int add(BigDataTrend bigDataTrend);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return bigDataTrend	*/	public BigDataTrend get(Integer id);

	/**	* 修改数据	* @param BigDataTrend			对象			(必填)	*/	public void update(BigDataTrend bigDataTrend);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<BigDataTrend>	*/	public PageVo<BigDataTrend> query(BigDataTrendRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(BigDataTrendRequest request);

	/**	* 查询地方站群对象集合	* @param request				前台参数			(必填)	* @return	List<BigDataTrend>	*/	public List<BigDataTrend> queryList(BigDataTrendRequest request);
	
	/**	 * 查询地方站群对象集合	 * @param request				前台参数			(必填)	 * @return	List<BigDataTrend>	 */	public List<BigDataTrend> sitesList(BigDataTrendRequest request);
	/**	 * 查询地方门户对象集合	 * @param request				前台参数			(必填)	 * @return	List<BigDataTrend>	 */	public List<BigDataTrend> portalList(BigDataTrendRequest request);
	/**	 * 查询本级站点对象集合	 * @param request				前台参数			(必填)	 * @return	List<BigDataTrend>	 */	public List<BigDataTrend> balanceList(BigDataTrendRequest request);

	


}

