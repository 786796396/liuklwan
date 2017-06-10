package com.ucap.cloud_web.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.publics.util.dao.GenericDao;import com.ucap.cloud_web.dto.SecurityFatalErrorRequest;
import com.ucap.cloud_web.entity.DicFatalError;
import com.ucap.cloud_web.entity.SecurityFatalError;
/*** <br>* <b>作者：</b>sunjq<br>* <b>日期：</b> 2016-05-09 11:53:09 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public interface SecurityFatalErrorDao extends GenericDao<SecurityFatalError>{

	/**
	 * @Description:根据tree表查询  某个组织单位下  某个服务周期内  各个站点的严重问题统计数据 
	 * @author cuichx --- 2017-4-7下午2:40:49     
	 * @param paraMap
	 * @return
	 */
	List<SecurityFatalErrorRequest> queryFatalErrorInfoByTree(
			HashMap<String, Object> paraMap);
	/**
	 * @Description:根据tree表查询  某个组织单位下  某个服务周期内  各个站点的严重问题统计数据 ----总记录数
	 * @author cuichx --- 2017-4-7下午2:40:49     
	 * @param paraMap
	 * @return
	 */
	int queryFatalErrorInfoByTreeCount(HashMap<String, Object> paraMap);
}

