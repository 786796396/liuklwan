package com.ucap.cloud_web.dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ucap.cloud_web.dto.MTaskoverviewRequest;
import com.ucap.cloud_web.entity.MTaskoverview;import com.ucap.cloud_web.entity.Result;
import com.publics.util.dao.GenericDao;/*** <br>* <b>作者：</b>liujc<br>* <b>日期：</b> 2016-10-17 15:16:51 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public interface MTaskoverviewDao extends GenericDao<MTaskoverview>{	public List<Result> queryResultList(Map arg0);

	public List<MTaskoverviewRequest> getMTaskoverMap(HashMap<String, Object> hashMap);}

