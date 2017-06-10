package com.ucap.cloud_web.service;


import java.util.List;
import java.util.Map;

import com.publics.util.page.PageVo;
import com.ucap.cloud_web.dto.EarlyDetailTempRequest;
import com.ucap.cloud_web.entity.ConfigEarly;
import com.ucap.cloud_web.entity.EarlyDetailTemp;


/**


	/**

	/**

	/**

	/**

	/**

	/**

	/**

	/**
	 * @Description: 获取实时预警信息
	 * @author cuichx --- 2017-3-14下午3:32:37     
	 * @param earlyDetailRequest
	 * @return
	 */
	public List<EarlyDetailTemp> querySiteEarlyInfo(EarlyDetailTempRequest earlyDetailTempRequest);

	/**
	 * @Description:   根据新老合配置数据 对比  维护增删预警池数据
	 * @author: renpb --- 2017年4月13日下午5:02:53
	 * @return
	 */
	public void updateEarlyDetailTemp(ConfigEarly oldConfigEarly,ConfigEarly nowConfigEarly);
	/**
	 * @Description:   获取今天所有首页连不通数据
	 * @author: cuichx --- 2017年4月13日下午5:02:53
	 * @param paraMap
	 * @return
	 */
	public List<EarlyDetailTempRequest> queryEarlyTempByMap(
			Map<String, Object> paraMap);
	/**
	 * @Description:   获取今天所有首页连不通数据----记录数
	 * @author: cuichx --- 2017年4月13日下午5:02:53
	 * @param paraMap
	 * @return
	 */
	public int queryEarlyTempByMapCount(Map<String, Object> paraMap);
	/**
	 * @Description:   获取今天所有首页连不通数据(填报单位)
	 * @author: cuichx --- 2017年4月13日下午5:02:53
	 * @param paraMap
	 * @return
	 */
	public List<EarlyDetailTempRequest> queryEarlyTempByMapCountTb(Map<String, Object> paraMap);
}
