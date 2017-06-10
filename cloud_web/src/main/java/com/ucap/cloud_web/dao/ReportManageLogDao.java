package com.ucap.cloud_web.dao;


import java.util.List;
import java.util.Map;

import com.ucap.cloud_web.dto.ReportManageLogRequest;
import com.ucap.cloud_web.entity.ReportManageLog;

	/**
	 * @Description: 将参数封装到map中，查询报告管理
	 * @author cuichx --- 2015-12-10下午1:06:00     
	 * @param paramMap
	 * @return
	 */
	List<ReportManageLogRequest> queryByMap(Map<String, Object> paramMap);

	/**
	 * @Description: 报告管理发送成功失败统计
	 * @author cuichx --- 2015-12-18下午6:48:22     
	 * @param param
	 * @return
	 */
	ReportManageLogRequest querySum(Map<String, Object> param);

	/**
	 * @Description: 将参数封装到map中，查询报告管理
	 * @author cuichx --- 2016-1-21下午5:15:57     
	 * @param map
	 * @return
	 */
	List<ReportManageLogRequest> queryListByMap(Map<String, Object> map);

