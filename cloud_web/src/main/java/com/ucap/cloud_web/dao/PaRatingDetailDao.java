package com.ucap.cloud_web.dao;


import java.util.List;
import java.util.Map;

import com.ucap.cloud_web.entity.PaRatingDetail;import com.publics.util.dao.GenericDao;/*** <br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-08-23 10:59:23 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public interface PaRatingDetailDao extends GenericDao<PaRatingDetail>{

	/**
	 * linhb 2016-08-31 
	 * 按照 一级指标分组 目的 获取所有的一级指标
	 * @param pRequest
	 * @return
	 */
	List<PaRatingDetail> groupByOneName(Map<String, Object> param);
	/**
	 * linhb 2016-09-03
	 * 通过自评详情 id 获取 已经填报的数据（栏目名称  url 等）
	 * @param parseInt
	 * @return
	 */
	List<PaRatingDetail> getWriteData(Map<String, Object> param);}

