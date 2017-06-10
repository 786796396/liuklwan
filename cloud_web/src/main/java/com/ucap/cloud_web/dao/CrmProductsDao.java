package com.ucap.cloud_web.dao;


import java.util.List;
import java.util.Map;

import com.publics.util.dao.GenericDao;
import com.ucap.cloud_web.dto.CrmProductsRequest;
import com.ucap.cloud_web.dtoResponse.CrmProductsResponse;
import com.ucap.cloud_web.entity.CrmProducts;/*** <br>* <b>作者：</b>qingjy<br>* <b>日期：</b> 2017-03-08 11:53:59 <br>* <b>版权所有：<b>版权所有(C) 2017<br>*/public interface CrmProductsDao extends GenericDao<CrmProducts>{

	/**
	 * @描述:根据标识码、产品分类、日期获取产品信息
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2017年3月8日下午5:08:14
	 * @param request
	 * @return
	 */

	List<CrmProductsResponse> getCrmProductsList(CrmProductsRequest request);

	/**
	 * @描述:获取安全的产品信息
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2017年3月13日下午5:07:34
	 * @param crmSeReq
	 * @return
	 */

	List<CrmProductsResponse> getSecurityProducts(CrmProductsRequest crmSeReq);

	/**
	 * @描述:获取有效产品及产品当前的服务周期(预警)
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2017年3月15日下午4:49:39
	 * @param crmReq
	 * @return
	 */

	List<CrmProductsResponse> getTaskCrmProductsList(CrmProductsRequest crmReq);

	/**
	 * @描述:获取上级产品信息
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2017年3月16日下午2:18:26
	 * @param crmReq
	 * @return
	 */

	List<CrmProductsResponse> getOnCrmProductsList(CrmProductsRequest crmReq);

	/**
	 * @描述:获取历史安全合同信息
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2017年3月24日下午4:23:15
	 * @param crmReq
	 * @return
	 */

	List<CrmProductsResponse> historyCrmProductsList(CrmProductsRequest crmReq);
	/**
	 * @Description: 通过填报单位标识码，获取所有的买单且允许填报单位查看的组织单位合同编号集合 
	 * @author cuichx --- 2017-3-22下午6:32:14     
	 * @param map
	 * @return
	 */
	List<Integer> queryByMap(Map<String, Object> map);
}

