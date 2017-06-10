package com.ucap.cloud_web.dao;


import java.util.List;
import java.util.Map;

import com.ucap.cloud_web.entity.PaTask;import com.publics.util.dao.GenericDao;/*** <br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-08-23 10:59:24 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public interface PaTaskDao extends GenericDao<PaTask>{
	/**
	 * 关联 任务关联表  通过siteCode获取数据
	 * linhb 2016-08-29
	 * @param pRequest
	 * @return
	 */
	List<PaTask> queryJoinTarget(Map<String, Object> param);}

