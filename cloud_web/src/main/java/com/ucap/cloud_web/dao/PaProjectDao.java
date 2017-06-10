package com.ucap.cloud_web.dao;


import java.util.List;
import java.util.Map;

import com.ucap.cloud_web.entity.PaProject;
import com.publics.util.dao.GenericDao;
	/**
	 * 通过项目表中的  site_code 获取该项目表下面的任务
	 * linhb 2016-8-23
	 * @param paProjectRequest
	 * @return
	 */
	List<PaTask> queryTaskList(Map<String, Object> param);
