package com.ucap.cloud_web.dao;


import java.util.List;
import java.util.Map;

import com.ucap.cloud_web.entity.ConfigEarly;
import com.publics.util.dao.GenericDao;
	 * @Title: 查询组织单位单站预警 6位sitecode
	 * @Description:
	 * @author liujc@ucap.com.cn	2016-8-1下午5:51:41
	 * @param paramMap
	 * @return
	 */
	List<ConfigEarly> queryOrgSingleSiteCode(Map<String, Object> paramMap);

	/**
	 * @Description:查询配置信息表 
	 * @author cuichx --- 2016-10-27下午6:16:16     
	 * @param paramMap
	 * @return
	 */
	List<ConfigEarly> findByMap(Map<String, Object> paramMap);
