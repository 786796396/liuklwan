package com.ucap.cloud_web.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.publics.util.page.PageVo;
import com.ucap.cloud_web.dto.SecurityBlankInfoRequest;
import com.ucap.cloud_web.dtoResponse.SecurityGuaranteeResponse;
import com.ucap.cloud_web.entity.SecurityBlankInfo;


/**


	/**

	/**

	/**

	/**

	/**

	/**

	/**

	/**
	 * 将条件封装到map集合中，查询数据
	 * @author cuichx --- 2015-11-17下午8:52:19     
	 * @param paramMap
	 * @return
	 */
	public List<SecurityBlankInfoRequest> queryByMap(
			Map<String, Object> paramMap);
	/** @Description: 组织单位内容保障问题-空白栏目获取空白栏目个数
	 * @author zhurk --- 2015-12-2下午3:40:11     
	 * @param map
	 * @return           
	*/
	public List<SecurityBlankInfoRequest> getBlankNum(Map<String, Object> map);

	/**
	 * @Description: 查询空白个数超过n个
	 * @author cuichx --- 2016-7-15上午11:09:51     
	 * @param paramMap
	 * @return
	 */
	public List<SecurityBlankInfoRequest> queryBlankNum(Map<String, Object> paramMap);

	/**
	 * @Description: 根据sietCode和ServicePeriodId查询当前站点的空白栏目的数量
	 * @author luocheng --- 2017-01-22     
	 * @param paramMap
	 * @return
	 */
	public List<SecurityGuaranteeResponse> getBlankNumber(HashMap<String, Object> hashMap);;
}
