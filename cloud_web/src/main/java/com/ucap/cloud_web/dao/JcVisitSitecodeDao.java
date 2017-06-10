package com.ucap.cloud_web.dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.publics.util.dao.GenericDao;
import com.ucap.cloud_web.dtoResponse.JcVisitSitecodeResponse;
import com.ucap.cloud_web.entity.JcVisitSitecode;/*** <br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-12-13 20:29:02 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public interface JcVisitSitecodeDao extends GenericDao<JcVisitSitecode>{
	/**
	 * 
	 * @描述:关联 tree表  获取 站点数据
	 * @作者:linhb@ucap.com.cn
	 * @时间:2016年12月14日上午9:38:08 
	 * @param sRequest
	 * @return
	 */
	List<JcVisitSitecode> querySiteCodes(Map<String, Object> param);
	/**
	 * 
	 * @描述:获取 下级地方门户
	 * @作者:linhb@ucap.com.cn
	 * @时间:2016年12月13日下午4:21:54
	 */
	List<JcVisitSitecode> queryOrganizations(Map<String, Object> param);
	/**
	 * 
	 * @描述:获取 本级站点
	 * @作者:linhb@ucap.com.cn
	 * @时间:2016年12月13日下午4:21:54
	 */
	List<JcVisitSitecode> queryNatives(Map<String, Object> param);

	/**
	 * @描述:获取日常监测-网站访问量数据
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2017年3月22日下午5:50:05
	 * @param hashMap
	 * @return
	 */

	List<JcVisitSitecodeResponse> getSiteVisitsList(HashMap<String, Object> hashMap);}

