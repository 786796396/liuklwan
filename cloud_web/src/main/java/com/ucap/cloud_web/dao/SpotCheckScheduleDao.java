package com.ucap.cloud_web.dao;


import java.util.List;
import java.util.Map;

import com.ucap.cloud_web.entity.SpotCheckSchedule;import com.publics.util.dao.GenericDao;/*** <br>* <b>作者：</b>yuangw@ucap.com.cn<br>* <b>日期：</b> 2016-04-19 11:16:49 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public interface SpotCheckScheduleDao extends GenericDao<SpotCheckSchedule>{	/**
	 * @Description: 查询任务批次信息
	 * @author yuangw@ucap.com.cn 
	 * @param paramMap
	 * @return
	 */
	List<SpotCheckSchedule> queryBatch(Map<String, Object> paramMap);

	/**
	 * @Description: 抽查汇报集合查询
	 * @author liukl@ucap.com.cn 
	 * @param paramMap
	 * @return
	 */
	List<SpotCheckSchedule> spotCheckReportUpList(Map<String, Object> param);}

