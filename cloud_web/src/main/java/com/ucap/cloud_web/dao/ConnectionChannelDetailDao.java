package com.ucap.cloud_web.dao;


import java.util.List;
import java.util.Map;

import com.ucap.cloud_web.dto.ConnectionChannelDetailRequest;
import com.ucap.cloud_web.entity.ConnectionChannelDetail;import com.publics.util.dao.GenericDao;/*** <br>* <b>作者：</b>Nora<br>* <b>日期：</b> 2015-10-29 11:19:33 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/public interface ConnectionChannelDetailDao extends GenericDao<ConnectionChannelDetail>{
	/**
	 * 统计每类栏目的个数
	 * @param param
	 * @return
	 */
	List<ConnectionChannelDetailRequest> queryListByGroup(
			Map<String, Object> param);
	/**
	 * 统计每类栏目的个数---总记录数
	 * @param paraMap
	 * @return
	 */
	int queryListByGroupCount(Map<String, Object> paraMap);}

