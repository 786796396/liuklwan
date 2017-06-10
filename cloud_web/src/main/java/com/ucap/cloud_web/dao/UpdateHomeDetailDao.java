package com.ucap.cloud_web.dao;


import java.util.Map;
import com.ucap.cloud_web.entity.UpdateHomeDetail;import com.publics.util.dao.GenericDao;/*** <br>* <b>作者：</b>SunJiang<br>* <b>日期：</b> 2015-10-30 09:51:22 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/public interface UpdateHomeDetailDao extends GenericDao<UpdateHomeDetail>{	
	 int queryBetweenDateCount(Map<String, Object> params);
	 
	 UpdateHomeDetail getNearest(Map<String, Object> params);}

