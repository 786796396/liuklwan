package com.ucap.cloud_web.service;


import java.util.List;


/**


	/**

	/**

	/**

	/**

	/**

	/**

	/**
	/**
	 * linhb 2016-08-31 
	 * 按照 一级指标分组 目的 获取所有的一级指标
	 * @param pRequest
	 * @return
	 */
	public List<PaRatingDetail> groupByOneName(PaRatingDetailRequest pRequest);
	/**
	 * linhb 2016-09-03
	 * 通过自评详情 id 获取 已经填报的数据（栏目名称  url 等）
	 * @param pRequest
	 * @return
	 */
	public List<PaRatingDetail> getWriteData(PaRatingDetailRequest pRequest);

}
