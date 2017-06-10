package com.ucap.cloud_web.dao;


import java.util.List;
import java.util.Map;

import com.publics.util.dao.GenericDao;
import com.ucap.cloud_web.dtoResponse.BigOrgDailyResponse;
import com.ucap.cloud_web.entity.BigOrgDaily;
/*** <br>* <b>作者：</b>liujc<br>* <b>日期：</b> 2017-02-28 18:03:30 <br>* <b>版权所有：<b>版权所有(C) 2017<br>*/public interface BigOrgDailyDao extends GenericDao<BigOrgDaily>{	/**
	 * 
	 * @param rRequest 
	 * @描述:获取下级战群的数据
	 * @作者:linhb@ucap.com.cn
	 * @时间:2016年12月13日下午4:21:54
	 */
	List<BigOrgDailyResponse> getOrgData(Map<String, Object> param);}

