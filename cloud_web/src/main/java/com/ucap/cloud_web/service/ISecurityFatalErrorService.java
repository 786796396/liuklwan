package com.ucap.cloud_web.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.publics.util.page.PageVo;
import com.ucap.cloud_web.entity.DicFatalError;
import com.ucap.cloud_web.entity.SecurityFatalError;



/**


	/**

	/**

	/**

	/**

	/**

	/**

	/**
	/**
	 * @Description:根据tree表查询  某个组织单位下  某个服务周期内  各个站点的严重问题统计数据 
	 * @author cuichx --- 2017-4-7下午2:40:49     
	 * @param paraMap
	 * @return
	 */
	public List<SecurityFatalErrorRequest> queryFatalErrorInfoByTree(
			HashMap<String, Object> paraMap);
	/**
	 * @Description:根据tree表查询  某个组织单位下  某个服务周期内  各个站点的严重问题统计数据 ----总记录数
	 * @author cuichx --- 2017-4-7下午2:40:49     
	 * @param paraMap
	 * @return
	 */
	public int queryFatalErrorInfoByTreeCount(HashMap<String, Object> paraMap);
}
