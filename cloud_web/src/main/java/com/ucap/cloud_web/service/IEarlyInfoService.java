package com.ucap.cloud_web.service;


import java.util.List;

import com.publics.util.page.PageVo;


/**


	/**

	/**

	/**
	/**
	 * @Description: 更新新预警数
	 * @author sunjiang --- 2016-1-25下午5:42:26     
	 * @param earlyInfo
	 */
	public void updateNewEarlyNum(EarlyInfo earlyInfo);

	/**

	/**
	
	/**
	 * @Description: 分页   新预警的和
	 * @author sunjiang --- 2016-1-22上午11:31:56     
	 * @param request
	 * @return
	 */
	public PageVo<EarlyInfo> queryEarlyInfo(EarlyInfoRequest request);

	/**
	/**
	 * @Description: 查询总数
	 * @author sunjiang --- 2016-1-26上午10:40:13     
	 * @param request
	 * @return
	 */
	public int queryEarlyInfoCount(EarlyInfoRequest request);

	/**
	
	/**
	 * @Description: 每个站站的预警总数
	 * @author sunjiang --- 2016-3-29下午4:26:55     
	 * @param request
	 * @return
	 */
	public List<EarlyInfo> queryEarlyInfoList(EarlyInfoRequest request);

	/**
	 * @Description: 获取组织机构下的所有新预警数
	 * @author cuichx --- 2015-12-18下午4:34:49     
	 * @param param
	 * @return
	 */
	public EarlyInfo querySum(Map<String, Object> param);
	/**
	 * @Description: 查询新预警数
	 * @author sunjiang --- 2016-1-25下午5:52:03     
	 * @param param
	 * @return
	 */
	public EarlyInfo queryNewEarlyNum(Map<String, Object> param);

}
