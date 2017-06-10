package com.ucap.cloud_web.dao;


import java.util.List;
import java.util.Map;
import com.ucap.cloud_web.entity.ReportWordResult;

	/**
	 * @Description: 将条件封装到map中，查询报告管理表
	 * @author cuichx --- 2015-12-11下午1:45:25     
	 * @param paramMap
	 * @return
	 */
	List<ReportWordResult> queryByMap(Map<String, Object> paramMap);
	/**
	 * @Description: 讲条件封装到map集合中，联表查询报告表、站点信息表和服务周期表
	 * @author cuichx --- 2016-2-2下午4:11:45     
	 * @param paramMap
	 * @return
	 */
	public List<ReportWordResult> findSiteByMap(Map<String, Object> paramMap);
	/**
	 * @Description: 将条件封装到map中，下载报告数据
	 * @author: yangshuai --- 2016-6-7下午1:55:54
	 * @return
	 */
	List<ReportWordResult> queryByMapWord(Map<String, Object> paramMap);
	public ReportWordResult findBySitCode(String sitCode);
	
	/**
	 * @Description: 下级整改通知分页
	 * @author: Liukl --- 2017年1月11日18:45:24
	 * @return
	 */
	int queryByMapWordCount(Map<String, Object> param);
}
