package com.ucap.cloud_web.service;


import java.util.HashMap;
import java.util.List;

import com.publics.util.page.PageVo;
import com.ucap.cloud_web.dto.JcVisitSitecodeRequest;
import com.ucap.cloud_web.dtoResponse.JcVisitSitecodeResponse;
import com.ucap.cloud_web.entity.JcVisitSitecode;


/**


	/**

	/**

	/**

	/**

	/**

	/**

	/**
	/**
	 * 
	 * @描述:关联 tree表  获取 站点数据
	 * @作者:linhb@ucap.com.cn
	 * @时间:2016年12月14日上午9:38:08 
	 * @param sRequest
	 * @return
	 */
	public List<JcVisitSitecode> querySiteCodes(JcVisitSitecodeRequest sRequest);
	/**
	 * 
	 * @描述:获取 下级地方门户
	 * @作者:linhb@ucap.com.cn
	 * @时间:2016年12月13日下午4:21:54
	 */
	public List<JcVisitSitecode> queryOrganizations(JcVisitSitecodeRequest sRequest);
	/**
	 * 
	 * @描述:获取 本级站点 
	 * @作者:linhb@ucap.com.cn
	 * @时间:2016年12月13日下午4:21:54
	 */
	public List<JcVisitSitecode> queryNatives(JcVisitSitecodeRequest sRequest);

	/**
	 * @描述:获取日常监测-网站访问量数据
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2017年3月22日下午5:48:34
	 * @param hashMap
	 * @return
	 */

	public List<JcVisitSitecodeResponse> getSiteVisitsList(HashMap<String, Object> hashMap);

}
