package com.ucap.cloud_web.service;


import java.util.List;

import com.publics.util.page.PageVo;


/**


	/**

	/**

	/**

	/**

	/**

	/**

	/**

	/**
	 * @Description:将查询条件封装到map集合中，查询后台数据 
	 * @author cuichx --- 2015-12-18下午5:28:50     
	 * @param paramMap
	 * @return
	 */
	public List<EarlyDetailRequest> queryByMap(Map<String, Object> paramMap);
	/**
	 * @Title: 组织单位预警日报信息
	 * @Description:
	 * @author liujc@ucap.com.cn	2016-7-29下午4:42:13
	 * @param request
	 * @return
	 */
	public List<EarlyDetail> queryDailyInfo(EarlyDetailRequest request);
	/**
	 * @Title: 站点预警信息 10位 sitecode
	 * @Description:
	 * @author liujc@ucap.com.cn	2016-8-3上午11:36:43
	 * @param request
	 * @return
	 */
	public List<EarlyDetail> querySiteEarlyInfo(EarlyDetailRequest request);

	/**
	 * @Title: 获取  播报数据
	 * @Description:
	 * @author linhb@ucap.com.cn	2016-10-09上午11:36:43
	 * @param eRequest 
	 * @return
	 */
	public List<EarlyDetail> queryDatas(EarlyDetailRequest eRequest);
	/**
	 * @Title: 获取  播报数据 首页不连通
	 * @Description:
	 * @author linhb@ucap.com.cn	2016-10-09上午11:36:43
	 * @param eRequest 
	 * @return
	 */
	public List<EarlyDetail> queryNoConDatas(EarlyDetailRequest eRequest);
}
