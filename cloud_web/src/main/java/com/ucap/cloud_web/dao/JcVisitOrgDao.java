package com.ucap.cloud_web.dao;


import com.ucap.cloud_web.entity.JcVisitOrg;

import java.util.List;
import java.util.Map;

import com.publics.util.dao.GenericDao;
	/**
	 * 
	 * @param rRequest 
	 * @描述:获取下级战群的 访问量数据
	 * @作者:linhb@ucap.com.cn
	 * @时间:2016年12月13日下午4:21:54
	 */
	List<JcVisitOrg> getOrgData(Map<String, Object> param);
