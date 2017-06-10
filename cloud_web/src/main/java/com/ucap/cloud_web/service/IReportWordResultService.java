package com.ucap.cloud_web.service;


import java.util.List;

import com.publics.util.page.PageVo;
import com.ucap.cloud_web.dto.ReportWordResultRequest;


/**


	/**

	/**

	/**

	/**

	/**
	/**
	 * @Description: 将条件封装到map中，查询报告管理表
	 * @author cuichx --- 2015-12-11下午1:45:25     
	 * @param paramMap
	 * @return
	 */
	public List<ReportWordResult> queryByMap(Map<String, Object> paramMap);
	
	/**
	 * @Description: 将条件封装到map中，查询下载报告数据
	 * @author: yangshuai --- 2016-6-7下午1:54:11
	 * @return
	 */
	public List<ReportWordResult> queryByMapWord(Map<String, Object> paramMap);

	/**
	 * @Description: 讲条件封装到map集合中，联表查询报告表、站点信息表和服务周期表
	 * @author cuichx --- 2016-2-2下午4:11:45     
	 * @param paramMap
	 * @return
	 */
	public List<ReportWordResult> findSiteByMap(Map<String, Object> paramMap);
	/**
	 * 根据ReportWordResult 查询ReportWordResult
	 * 
	 * @param customerCode
	 * @return
	 */
	public ReportWordResult findBySitCode(String siteCode);
	/**
	 * 下级整改通知，分页
	 * @param 
	 * @author: liukl --- 2017年1月11日18:30:19
	 * @return
	 */
	public PageVo<ReportWordResult> queryRectificationNotice(
			ReportWordResultRequest wordRequest);
}
