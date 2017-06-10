package com.ucap.cloud_web.dao;


import java.util.List;
import java.util.Map;

import com.ucap.cloud_web.entity.PaProject;import com.ucap.cloud_web.entity.PaTask;
import com.publics.util.dao.GenericDao;/*** <br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-08-23 10:59:23 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public interface PaProjectDao extends GenericDao<PaProject>{
	/**
	 * 通过项目表中的  site_code 获取该项目表下面的任务
	 * linhb 2016-8-23
	 * @param paProjectRequest
	 * @return
	 */
	List<PaTask> queryTaskList(Map<String, Object> param);}

