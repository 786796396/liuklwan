package com.ucap.cloud_web.service;


import java.util.List;
import java.util.Map;

import com.publics.util.page.PageVo;
import com.ucap.cloud_web.dto.CrmProductsRequest;
import com.ucap.cloud_web.dtoResponse.CrmProductsResponse;
import com.ucap.cloud_web.entity.CrmProducts;


/*** <br>* <b>作者：</b>qingjy<br>* <b>日期：</b> 2017-03-08 11:53:59 <br>* <b>版权所有：<b>版权所有(C) 2017<br>*/public interface ICrmProductsService {


	/**	* 添加数据	* @param crmProducts			对象			(必填)	*/	public int add(CrmProducts crmProducts);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return crmProducts	*/	public CrmProducts get(Integer id);

	/**	* 修改数据	* @param CrmProducts			对象			(必填)	*/	public void update(CrmProducts crmProducts);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<CrmProducts>	*/	public PageVo<CrmProducts> query(CrmProductsRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(CrmProductsRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<CrmProducts>	*/	public List<CrmProducts> queryList(CrmProductsRequest request);

	/**
	 * @描述:根据标识码、产品分类、日期获取产品信息
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2017年3月8日下午5:05:54
	 * @param request
	 * @return
	 */

	public List<CrmProductsResponse> getCrmProductsList(CrmProductsRequest request);

	/**
	 * @描述:获取安全的产品信息
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2017年3月13日下午5:06:05
	 * @param crmSeReq
	 * @return
	 */

	public List<CrmProductsResponse> getSecurityProducts(CrmProductsRequest crmSeReq);

	/**
	 * @描述:获取有效产品及产品当前的服务周期(预警)
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2017年3月15日下午4:48:48
	 * @param crmReq
	 * @return
	 */

	public List<CrmProductsResponse> getTaskCrmProductsList(CrmProductsRequest crmReq);

	/**
	 * @描述:获取上级产品信息
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2017年3月16日下午2:17:22
	 * @param crmReq
	 * @return
	 */

	public List<CrmProductsResponse> getOnCrmProductsList(CrmProductsRequest crmReq);

	/**
	 * @描述:获取历史安全合同信息
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2017年3月24日下午4:22:34
	 * @param crmReq
	 * @return
	 */

	public List<CrmProductsResponse> historyCrmProductsList(CrmProductsRequest crmReq);

	/**
	 * @Description:通过填报单位标识码，获取所有的买单且允许填报单位查看的组织单位合同编号集合 
	 * @author cuichx --- 2017-3-22下午6:30:18     
	 * @param map
	 * @return
	 */
	public List<Integer> queryByMap(Map<String, Object> map);

}

