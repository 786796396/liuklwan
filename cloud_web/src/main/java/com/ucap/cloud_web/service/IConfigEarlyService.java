package com.ucap.cloud_web.service;


import java.util.List;

import com.publics.util.page.PageVo;
import com.ucap.cloud_web.entity.ConfigEarly;
import com.ucap.cloud_web.entity.EarlyDetail;


/**


	/**

	/**

	/**

	/**

	/**

	/**

	/**
	/**
	 * @Title: 查询组织单位单站预警 6位sitecode
	 * @Description:
	 * @author liujc@ucap.com.cn	2016-8-2上午9:28:17
	 * @param request
	 * @return
	 */
	public List<ConfigEarly> queryOrgSingleSiteCode(ConfigEarlyRequest request);

	/**
	 * @Description:查询配置信息表 
	 * @author cuichx --- 2016-10-27下午6:16:16     
	 * @param paramMap
	 * @return
	 */
	public List<ConfigEarly> findByMap(Map<String, Object> paramMap);

}
