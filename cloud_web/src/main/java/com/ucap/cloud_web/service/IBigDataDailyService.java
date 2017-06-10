package com.ucap.cloud_web.service;


import java.util.List;

import com.publics.util.page.PageVo;
import com.ucap.cloud_web.dto.BigDataDailyRequest;
import com.ucap.cloud_web.dtoResponse.BigDataDailyResponse;
import com.ucap.cloud_web.entity.BigDataDaily;


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
	 * @描述:获取 下级地方门户
	 * @作者:liujc@ucap.com.cn
	 * @时间:2017-3-1下午1:40:13 
	 * @param sRequest
	 * @return
	 */
	public List<BigDataDailyResponse> queryOrganizations(BigDataDailyRequest sRequest);
	/**
	 * 
	 * @描述:获取 本级站点
	 * @作者:liujc@ucap.com.cn
	 * @时间:2017-3-1下午1:40:21 
	 * @param sRequest
	 * @return
	 */
	public List<BigDataDailyResponse> queryNatives(BigDataDailyRequest sRequest);
}
