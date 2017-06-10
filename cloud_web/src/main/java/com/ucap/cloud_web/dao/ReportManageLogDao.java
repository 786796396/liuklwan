package com.ucap.cloud_web.dao;


import java.util.List;
import java.util.Map;

import com.ucap.cloud_web.dto.ReportManageLogRequest;
import com.ucap.cloud_web.entity.ReportManageLog;import com.publics.util.dao.GenericDao;/*** <br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2015-10-29 11:11:37 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/public interface ReportManageLogDao extends GenericDao<ReportManageLog>{

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
}

