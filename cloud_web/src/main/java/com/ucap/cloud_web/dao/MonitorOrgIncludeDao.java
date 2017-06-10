package com.ucap.cloud_web.dao;


import com.ucap.cloud_web.entity.MonitorOrgInclude;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.publics.util.dao.GenericDao;/*** <br>* <b>作者：</b>liujc<br>* <b>日期：</b> 2016-11-02 11:00:54 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public interface MonitorOrgIncludeDao extends GenericDao<MonitorOrgInclude>{	  public String queryMaxDate(HashMap<String, Object> map);
	/**
	 * 
	 * @描述: 链接  tree 表获取数据
	 * @作者:linhb@ucap.com.cn
	 * @时间:2016年12月24日下午2:25:53 
	 * @param param
	 * @return
	 */
	public List<MonitorOrgInclude> getOrgData(Map<String, Object> param);}

