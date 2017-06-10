package com.ucap.cloud_web.service;


import java.util.HashMap;
import java.util.List;

import com.publics.util.page.PageVo;


/**


	/**

	/**

	/**

	/**

	/**

	/**

	/**

	/**
	 * 将参数封装到map集合中，查询全站链接可用性表
	 * @param map				前台参数			(必填)
	 * @return
	 */
	public List<LinkAllInfoRequest> queryByMap(Map<String, Object> map);
	/**
	 * @Description:填报单位首页面；全站不可用链接折线图
	 * @author sunjiang --- 2015年11月27日上午9:22:56     
	 * @param map
	 * @return
	 */
	List<LinkAllInfo> getAllline(Map<String, Object> map);
	/**
	 * @Description: 组织单位：全站不可用链接折线图
	 * @author sunjiang --- 2015年11月28日下午6:36:27     
	 * @param map
	 * @return
	 */
	List<LinkAllInfo> getAlllineOrg(Map<String, Object> map);
	/** @Description: 组织单位：全站不可用链接监测结果
	 * @author zhurk --- 2015-11-29下午6:38:38     
	 * @param map
	 * @return           
	*/
	List<LinkAllInfoRequest> getAllLinkSum(Map<String, Object> map);
	/**
	 * @Description: 根据tree表查询  某个组织机构下面 某个服务周期内  各个站点的死链个数
	 * @author cuichx --- 2017-4-7下午5:31:40     
	 * @param paraMap
	 * @return
	 */
	public List<LinkAllInfoRequest> queryLinkAllInfoByTree(
			HashMap<String, Object> paraMap);
	/**
	 * @Description: 根据tree表查询  某个组织机构下面 某个服务周期内  各个站点的死链个数--总记录数
	 * @author cuichx --- 2017-4-7下午5:31:40     
	 * @param paraMap
	 * @return
	 */
	public int queryLinkAllInfoByTreeCount(HashMap<String, Object> paraMap);

}
