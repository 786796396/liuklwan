package com.ucap.cloud_web.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.publics.util.page.PageVo;


/**


	/**

	/**

	/**

	/**

	/**

	/**

	/**

	/**
	 * @描述:根据域名获取siteCode
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2016年12月9日下午1:23:32 
	 * @return 
	 */
	
	public Map<String, Object> domainName(String uri);

	/**
	 * @描述:查询省的域名
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2016年12月14日上午9:47:38 
	 * @param spMap
	 * @return 
	 */
	
	public List<SpSite> getSpSiteList(HashMap<String, Object> spMap);

}
