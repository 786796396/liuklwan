package com.ucap.cloud_web.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.publics.util.page.PageVo;
import com.ucap.cloud_web.dto.MonitorIncludeRequest;
import com.ucap.cloud_web.dtoResponse.SearchEngineResponse;
import com.ucap.cloud_web.entity.MonitorInclude;


/**


	/**

	/**

	/**

	/**

	/**

	/**

	/**

	/**
	 * @描述:查询每个填报单位最大日期
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2016年11月30日下午4:33:27 
	 * @param map
	 * @return 
	 */
	
	public String queryTBMaxDate(Map<String, Object> map);
	/**
	 * 
	 * @描述:联合  tree 表  获取数据
	 * @作者:linhb@ucap.com.cn
	 * @时间:2016年12月24日下午2:22:22 
	 * @param sRequest
	 * @return
	 */
	public List<MonitorInclude> queryNatives(MonitorIncludeRequest sRequest);
	/**
	 * 
	 * @描述:点击 检测网站数量 获取数据
	 * @作者:linhb@ucap.com.cn
	 * @时间:2016年12月26日下午2:06:45 
	 * @param sRequest
	 * @return
	 */
	public List<MonitorInclude> querySiteData(MonitorIncludeRequest sRequest);

	/**
	 * @描述:获取搜索引擎收录数据
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2017年3月24日上午10:33:52
	 * @param hashMap
	 * @return
	 */

	public List<SearchEngineResponse> getSearchEngineList(HashMap<String, Object> hashMap);

}
