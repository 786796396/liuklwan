package com.ucap.cloud_web.dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ucap.cloud_web.dto.LinkAllInfoRequest;
import com.ucap.cloud_web.entity.LinkAllInfo;import com.publics.util.dao.GenericDao;/*** <br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2015-10-29 11:06:08 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/public interface LinkAllInfoDao extends GenericDao<LinkAllInfo>{

	/**
	 * @Description: 将参数封装到map集合中，查询全站链接可用性表
	 * @author cuichx --- 2015-11-9下午2:35:08     
	 * @param map
	 * @return
	 */
	List<LinkAllInfoRequest> queryByMap(Map<String, Object> map);
	/**
	 * @Description: 填报单位首页面；全站不可用链接折线图
	 * @author sunjiang --- 2015年11月27日上午9:22:56     
	 * @param map
	 * @return
	 */	List<LinkAllInfo> getAllline(Map<String, Object> map);
	/**
	 * @Description: 组织单位：全站不可用链接折线图
	 * @author sunjiang --- 2015年11月28日下午6:36:27     
	 * @param map
	 * @return
	 */	List<LinkAllInfo> getAlllineOrg(Map<String, Object> map);	/** @Description: 组织单位：全站不可用链接监测结果
	 * @author zhurk --- 2015-11-29下午6:40:29     
	 * @param map
	 * @return           
	*/
	List<LinkAllInfoRequest> getAllLinkSum(Map<String, Object> map);
	/**
	 * @Description: 根据tree表查询  某个组织机构下面 某个服务周期内  各个站点的死链个数
	 * @author cuichx --- 2017-4-7下午5:34:17     
	 * @param paraMap
	 * @return
	 */
	List<LinkAllInfoRequest> queryLinkAllInfoByTree(
			HashMap<String, Object> paraMap);
	/**
	 * @Description: 根据tree表查询  某个组织机构下面 某个服务周期内  各个站点的死链个数--总记录数
	 * @author cuichx --- 2017-4-7下午5:31:40     
	 * @param paraMap
	 * @return
	 */
	int queryLinkAllInfoByTreeCount(HashMap<String, Object> paraMap);}

