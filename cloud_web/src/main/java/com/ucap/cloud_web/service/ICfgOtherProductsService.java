package com.ucap.cloud_web.service;


import java.util.List;import com.publics.util.page.PageVo;import com.ucap.cloud_web.dto.CfgOtherProductsRequest;import com.ucap.cloud_web.entity.CfgOtherProducts;


/*** <br>* <b>作者：</b>qingjy<br>* <b>日期：</b> 2017-01-03 13:38:10 <br>* <b>版权所有：<b>版权所有(C) 2017<br>*/public interface ICfgOtherProductsService {


	/**	* 添加数据	* @param cfgOtherProducts			对象			(必填)	*/	public int add(CfgOtherProducts cfgOtherProducts);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return cfgOtherProducts	*/	public CfgOtherProducts get(Integer id);

	/**	* 修改数据	* @param CfgOtherProducts			对象			(必填)	*/	public void update(CfgOtherProducts cfgOtherProducts);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<CfgOtherProducts>	*/	public PageVo<CfgOtherProducts> query(CfgOtherProductsRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(CfgOtherProductsRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<CfgOtherProducts>	*/	public List<CfgOtherProducts> queryList(CfgOtherProductsRequest request);

}

