package com.ucap.cloud_web.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.publics.util.page.PageVo;
import com.ucap.cloud_web.dto.RelationsPeriodRequest;
import com.ucap.cloud_web.dtoResponse.SecurityGuaranteeResponse;
import com.ucap.cloud_web.entity.RelationsPeriod;


/**


	/**

	/**

	/**

	/**

	/**

	/**

	/**

	/**
	 * @Description: 通过网站标识码获取客户编号
	 * @author cuichx --- 2016-3-9上午10:11:19     
	 * @param pMap
	 * @return
	 */
	public List<RelationsPeriodRequest> queryByMap(Map<String, Object> pMap);

	/**
	 * 
	 * @描述:获取内容保障周期下sitecode
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2017年3月7日下午2:45:03
	 * @param map
	 * @return
	 */
	public List<SecurityGuaranteeResponse> contentSecurityTree(HashMap<String, Object> map);

}
