package com.ucap.cloud_web.dao;


import java.util.List;
import java.util.Map;

import com.ucap.cloud_web.dto.ConnectionHomeDetailRequest;
import com.ucap.cloud_web.entity.ConnectionHomeDetail;import com.publics.util.dao.GenericDao;/*** <br>* <b>作者：</b>Nora<br>* <b>日期：</b> 2015-10-29 11:19:33 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/public interface ConnectionHomeDetailDao extends GenericDao<ConnectionHomeDetail>{
	
	/**
	 * @Description: 获取某个组织单位下   某天的每个站点最后一次不连通的详情
	 * @author cuichx --- 2017-3-29上午9:33:59     
	 * @param paraMap
	 * @return
	 */
	List<ConnectionHomeDetailRequest> queryConnByMap(Map<String, Object> paraMap);}

