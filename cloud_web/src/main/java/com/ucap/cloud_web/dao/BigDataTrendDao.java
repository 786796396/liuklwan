package com.ucap.cloud_web.dao;


import java.util.List;
import java.util.Map;

import com.ucap.cloud_web.dto.BigDataTrendRequest;
import com.ucap.cloud_web.entity.BigDataTrend;import com.publics.util.dao.GenericDao;/*** <br>* <b>作者：</b>zhaoDY<br>* <b>日期：</b> 2017-02-14 19:11:25 <br>* <b>版权所有：<b>版权所有(C) 2017<br>*/public interface BigDataTrendDao extends GenericDao<BigDataTrend>{

	//查询地方群站信息集合
	List<BigDataTrend> sitesList(Map<String, Object> param);
	
	//查询地方门户信息集合
	List<BigDataTrend> portalList(Map<String, Object> param);
	
	//查询本级站点信息集合
	List<BigDataTrend> balanceList(Map<String, Object> param);

	
	}

