package com.ucap.cloud_web.dao;


import com.ucap.cloud_web.entity.MonitorSilentOrgResult;

import java.util.HashMap;
import java.util.List;

import com.publics.util.dao.GenericDao;/*** <br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-11-04 11:24:50 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public interface MonitorSilentOrgResultDao extends GenericDao<MonitorSilentOrgResult>{

	public List<MonitorSilentOrgResult> getMonitorSilentList(HashMap<String, Object> hashMap);}

