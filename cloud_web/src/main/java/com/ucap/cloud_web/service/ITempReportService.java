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
	 * linhb 2016-09-13
	 * 组织单位按照条件查询
	 * @param tRequest
	 * @return 
	 */
	
	public List<TempReport> joinLinkData(TempReportRequest tRequest);
	
	
	/**
	 * 
	 * @描述:查询下级省市县  
	 * @作者:liukl@ucap.com.cn
	 * @时间:2016年12月29日20:32:46
	 * @param TempReportRequest
	 * @return
	 */
	public PageVo<TempReport> queryLowerSubordinateUnits(
			TempReportRequest tRequest);

}
