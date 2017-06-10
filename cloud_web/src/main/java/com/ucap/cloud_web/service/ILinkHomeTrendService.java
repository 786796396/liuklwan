package com.ucap.cloud_web.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.publics.util.page.PageVo;
import com.ucap.cloud_web.dto.LinkHomeTrendRequest;
import com.ucap.cloud_web.entity.LinkHomeTrend;


/**


	/**

	/**

	/**

	/**

	/**

	/**

	/**

	/**
	 * 将参数封装到map中查询首页链接可用性表
	 * @param request				前台参数			(必填)   
	 * @param map
	 * @return List<LinkHomeTrend>
	 */
	public List<LinkHomeTrend> queryByMap(Map<String, Object> map);
	/**
	 * @Description:组织单位-首页：首页不可用链接折线图 
	 * @author sunjiang --- 2015年11月27日上午8:59:43     
	 * @param map
	 * @return
	 */
	List<LinkHomeTrend> getHomeLine(Map<String, Object> map);
	/** @Description: 组织单位-首页：首页不可用链接监测结果
	 * @author zhurk --- 2015-12-1下午7:27:02     
	 * @param map
	 * @return           
	*/
	List<LinkHomeTrendRequest> getHomeSum(Map<String, Object> map);
	
	/**
	 * @Description: 昨日首页链接可用性表 中死链总数
	 * @author cuichx --- 2016-8-23下午9:40:08     
	 * @param paramMap
	 * @return
	 */
	public int queryHomeDataByMap(Map<String, Object> paramMap);

	/**
	 * @描述:查询日常基础-首页链接可用性
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2016年12月22日上午9:27:49
	 * @param hashMap
	 * @return
	 */

	public List<LinkHomeTrendRequest> getHomeOrgList(HashMap<String, Object> hashMap);
	/**
	 * @Description:获取某个组织单位下  某日死链数大于1的所有站点及统计信息 
	 * @author cuichx --- 2017-3-29下午5:20:59     
	 * @param paraMap
	 * @return
	 */
	public List<LinkHomeTrendRequest> queryTotalOrgByMap(
			HashMap<String, Object> paraMap);
	/**
	 * @Description:获取某个组织单位下  某日死链数大于1的所有站点及统计信息 ----记录数
	 * @author cuichx --- 2017-3-29下午5:20:59     
	 * @param paraMap
	 * @return
	 */
	public int queryTotalOrgByMapCount(HashMap<String, Object> paraMap);
}
