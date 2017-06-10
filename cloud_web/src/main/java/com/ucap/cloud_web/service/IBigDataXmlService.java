package com.ucap.cloud_web.service;


import java.util.List;import com.publics.util.page.PageVo;import com.ucap.cloud_web.dto.BigDataXmlRequest;import com.ucap.cloud_web.entity.BigDataXml;


/*** <br>* <b>作者：</b>liujc<br>* <b>日期：</b> 2016-07-21 10:29:02 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public interface IBigDataXmlService {


	/**	* 添加数据	* @param bigDataXml			对象			(必填)	*/	public void add(BigDataXml bigDataXml);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return bigDataXml	*/	public BigDataXml get(Integer id);

	/**	* 修改数据	* @param BigDataXml			对象			(必填)	*/	public void update(BigDataXml bigDataXml);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(BigDataXmlRequest request);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<BigDataXml>	*/	public PageVo<BigDataXml> query(BigDataXmlRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(BigDataXmlRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<BigDataXml>	*/	public List<BigDataXml> queryList(BigDataXmlRequest request);

}

