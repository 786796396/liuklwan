package com.ucap.cloud_web.service;


import java.util.HashMap;
import java.util.List;
import com.ucap.cloud_web.entity.JcVisitOrg;
import com.ucap.cloud_web.entity.MonitorOrgInclude;


/**


	/**

	/**

	/**

	/**

	/**

	/**

	/**

	/**
	 * @描述:查询组织单位最大日期 
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2016年11月30日下午4:44:58 
	 * @param map
	 * @return 
	 */
	
	public String queryMaxDate(HashMap<String, Object> map);
	/**
	 * 
	 * @描述: 链接 tree 表获取数据
	 * @作者:linhb@ucap.com.cn
	 * @时间:2016年12月24日下午2:21:45 
	 * @param rRequest
	 * @return
	 */
	public List<MonitorOrgInclude> getOrgData(MonitorOrgIncludeRequest rRequest);

}
