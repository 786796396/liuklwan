package com.ucap.cloud_web.dao;


import java.util.List;
import java.util.Map;

import com.ucap.cloud_web.entity.SpotCheckSchedule;
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
	List<SpotCheckSchedule> spotCheckReportUpList(Map<String, Object> param);
