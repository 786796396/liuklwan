package com.ucap.cloud_web.dao;


import java.util.HashMap;
import java.util.List;

import com.publics.util.dao.GenericDao;
import com.ucap.cloud_web.entity.SpSite;/*** <br>* <b>作者：</b>liujc<br>* <b>日期：</b> 2016-11-22 10:30:41 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public interface SpSiteDao extends GenericDao<SpSite>{

	SpSite getspSiteBySiteCode(String siteCode);

	/**
	 * @描述::查询省的域名
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2016年12月14日上午9:49:16 
	 * @param spMap
	 * @return 
	 */
	
	List<SpSite> getSpSiteList(HashMap<String, Object> spMap);}

