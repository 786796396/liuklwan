package com.ucap.cloud_web.dao;


import java.util.List;
import java.util.Map;

import com.ucap.cloud_web.entity.PaTask;
	/**
	 * 关联 任务关联表  通过siteCode获取数据
	 * linhb 2016-08-29
	 * @param pRequest
	 * @return
	 */
	List<PaTask> queryJoinTarget(Map<String, Object> param);
