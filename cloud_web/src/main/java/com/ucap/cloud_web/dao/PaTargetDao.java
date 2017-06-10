package com.ucap.cloud_web.dao;


import java.util.List;
import java.util.Map;

import com.ucap.cloud_web.entity.PaTarget;import com.publics.util.dao.GenericDao;/*** <br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-08-24 13:38:42 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public interface PaTargetDao extends GenericDao<PaTarget>{
	/**
	 * linhb 2016-8-24
	 * 通过项目id 任务id  获取 对象
	 * @param param
	 * @return
	 */
	List<PaTarget> queryByIdss(Map<String, Object> param);}

