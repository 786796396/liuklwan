package com.ucap.cloud_web.dao;


import java.util.List;
import java.util.Map;

import com.publics.util.dao.GenericDao;
import com.ucap.cloud_web.dto.ConnectionBusinessDetailRequest;
import com.ucap.cloud_web.entity.ConnectionBusinessDetail;
/*** <br>* <b>作者：</b>Nora<br>* <b>日期：</b> 2015-10-29 11:19:33 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/public interface ConnectionBusinessDetailDao extends GenericDao<ConnectionBusinessDetail>{
	public  List<ConnectionBusinessDetailRequest> getSumInfoGroupByName(Map<String, Object> param);

	/**
	 * 获取每个站点中每个栏目的最后不连通的数据
	 * @param paraMap
	 * @return
	 */
	public List<ConnectionBusinessDetailRequest> queryListByGroup(
			Map<String, Object> paraMap);
	/**
	 * 获取每个站点中每个栏目的最后不连通的数据---总记录数
	 * @param paraMap
	 * @return
	 */
	public int queryListByGroupCount(Map<String, Object> paraMap);}

