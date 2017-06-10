package com.ucap.cloud_web.dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ucap.cloud_web.dto.SecurityBasicInfoRequest;
import com.ucap.cloud_web.entity.SecurityBasicInfo;import com.publics.util.dao.GenericDao;/*** <br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2016-04-12 10:13:05 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public interface SecurityBasicInfoDao extends GenericDao<SecurityBasicInfo>{
	
	
	/**
	 * @Description: 获取组织单位内容保障问题-基本信息问题统计列表
	 * @author cuichx --- 2016-3-30下午6:19:00     
	 * @param hashMap
	 * @return
	 */
	List<SecurityBasicInfoRequest> getProblemNum(HashMap<String, Object> hashMap);

	/**
	 * @Description: 同一监测周期  基本信息不更新>=8个
	 * @author cuichx --- 2016-7-15下午12:10:21     
	 * @param paramMap
	 * @return
	 */
	List<SecurityBasicInfoRequest> queryBasicNum(Map<String, Object> paramMap);}

