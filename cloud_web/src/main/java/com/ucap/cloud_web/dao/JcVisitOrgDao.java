package com.ucap.cloud_web.dao;


import com.ucap.cloud_web.entity.JcVisitOrg;

import java.util.List;
import java.util.Map;

import com.publics.util.dao.GenericDao;/*** <br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-12-13 08:58:19 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public interface JcVisitOrgDao extends GenericDao<JcVisitOrg>{
	/**
	 * 
	 * @param rRequest 
	 * @描述:获取下级战群的 访问量数据
	 * @作者:linhb@ucap.com.cn
	 * @时间:2016年12月13日下午4:21:54
	 */
	List<JcVisitOrg> getOrgData(Map<String, Object> param);}

