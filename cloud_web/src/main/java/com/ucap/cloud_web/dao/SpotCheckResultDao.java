package com.ucap.cloud_web.dao;


import java.util.List;
import java.util.Map;

import com.ucap.cloud_web.dto.SpotCheckResultRequest;
import com.ucap.cloud_web.entity.SpotCheckResult;import com.publics.util.dao.GenericDao;/*** <br>* <b>作者：</b>yuangw@ucap.com.cn<br>* <b>日期：</b> 2016-04-19 11:16:29 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public interface SpotCheckResultDao extends GenericDao<SpotCheckResult>{

	/**
	 * @Description: 获取每个省的抽查站点个数
	 * @author cuichx --- 2016-5-3下午7:17:36     
	 * @param params
	 * @return
	 */
	List<SpotCheckResultRequest> queryByMap(Map<String, Object> params);
	
	/**
	 * 按抽查进度批量删除
	 * @param spotCheckSchedule
	 */
	void deleteBatchBySchedule(int spotCheckSchedule);}

