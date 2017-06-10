package com.ucap.cloud_web.dao;


import java.util.HashMap;
import java.util.List;

import com.publics.util.dao.GenericDao;
import com.ucap.cloud_web.dto.MonitorSilentResultRequest;
import com.ucap.cloud_web.entity.MonitorSilentResult;/*** <br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-11-04 11:24:50 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public interface MonitorSilentResultDao extends GenericDao<MonitorSilentResult>{

	public List<MonitorSilentResultRequest> getMonResMap(HashMap<String, Object> hashMap);}

