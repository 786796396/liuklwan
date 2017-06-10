package com.ucap.cloud_web.dao;


import java.util.List;
import java.util.Map;

import com.ucap.cloud_web.dto.DicConfigRequest;
import com.ucap.cloud_web.entity.DicConfig;import com.publics.util.dao.GenericDao;/*** <br>* <b>作者：</b>yangshuai<br>* <b>日期：</b> 2016-05-24 15:52:08 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public interface DicConfigDao extends GenericDao<DicConfig>{
	/**
	 * @Description: 获取各省多级别网站个数
	 * @author: yangshuai --- 2016-5-14下午2:27:08
	 * @param map
	 * @return
	 */
	List<DicConfigRequest> queryListByMap(Map<String, Object> map);}

