package com.ucap.cloud_web.service;


import java.util.List;


/**


	/**

	/**

	/**

	/**

	/**
	
	/**
	/** @Description: 计算两个时间内的更新数量
	 * @author sunjiaqi --- 2015-11-19下午03:02:34     
	 * @param beginDate 开始时间
	 * @param endDate  结束时间
	 * @return           
	*/
	public int queryBetweenDateCount(UpdateHomeDetailRequest request);

	/**
	/** @Description:  获取指定日期内
	 * @author sunjiaqi --- 2015-11-20下午05:48:07     
	 * @param request
	 * @return           
	*/
	public UpdateHomeDetail getNearest (UpdateHomeDetailRequest request);

}
