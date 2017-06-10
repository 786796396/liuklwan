package com.ucap.cloud_web.dao;


import java.util.List;
import java.util.Map;

import com.ucap.cloud_web.dto.EarlyDetailRequest;
import com.ucap.cloud_web.entity.EarlyDetail;import com.publics.util.dao.GenericDao;/*** <br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2015-10-29 11:05:04 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/public interface EarlyDetailDao extends GenericDao<EarlyDetail>{

	/**
	 * @Description: 将查询条件封装到map集合中，查询后台数据 
	 * @author cuichx --- 2015-12-18下午5:29:52     
	 * @param paramMap
	 * @return
	 */
	List<EarlyDetailRequest> queryByMap(Map<String, Object> paramMap);
	
	/**
	 * @Title: 组织单位预警日报信息
	 * @Description:
	 * @author liujc@ucap.com.cn	2016-7-29下午4:36:45
	 * @param paramMap
	 * @return
	 */
	List<EarlyDetail> queryDailyInfo(Map<String, Object> paramMap);
	
	/**
	 * @Title: 
	 * @Description:
	 * @author liujc@ucap.com.cn	2016-8-3上午11:36:19
	 * @param request
	 * @return
	 */
	List<EarlyDetail> querySiteEarlyInfo(Map<String, Object> paramMap);
	/**
	 * @Title: 获取  播报数据
	 * @Description:
	 * @author linhb@ucap.com.cn	2016-10-09上午11:36:43
	 * @return
	 */
	List<EarlyDetail> queryDatas(Map<String, Object> paramMap);
	/**
	 * @Title: 获取  播报数据 获取首页不连通
	 * @Description:
	 * @author linhb@ucap.com.cn	2016-10-09上午11:36:43
	 * @return
	 */ 
	List<EarlyDetail> queryNoConDatas(Map<String, Object> param);	}

