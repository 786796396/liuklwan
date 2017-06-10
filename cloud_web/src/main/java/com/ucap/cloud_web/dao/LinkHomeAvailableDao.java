package com.ucap.cloud_web.dao;


import java.util.List;
import java.util.Map;

import com.ucap.cloud_web.dto.LinkHomeAvailableRequest;
import com.ucap.cloud_web.entity.LinkHomeAvailable;import com.publics.util.dao.GenericDao;/*** <br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2015-10-29 11:06:19 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/public interface LinkHomeAvailableDao extends GenericDao<LinkHomeAvailable>{
	
	/**
	 * @Description: 分类统计  每种类型的个数 
	 * @author cuichx --- 2017-3-30上午11:33:01     
	 * @param paramMap
	 * @return
	 */
	List<LinkHomeAvailableRequest> queryGroupBy(Map<String, Object> paramMap);}

