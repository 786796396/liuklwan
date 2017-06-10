package com.ucap.cloud_web.dao;


import java.util.List;
import java.util.Map;

import com.publics.util.dao.GenericDao;
import com.ucap.cloud_web.dto.DatabaseLinkRequest;
import com.ucap.cloud_web.entity.DatabaseLink;/*** <br>* <b>作者：</b>SunJiang<br>* <b>日期：</b> 2016-03-21 16:16:55 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public interface DatabaseLinkDao extends GenericDao<DatabaseLink>{
	/**
	 * @Description: 分组查询
	 * @author sunjiang --- 2016-3-21下午4:34:11     
	 * @param request
	 * @return
	 */
	public List<DatabaseLinkRequest> queryCountBySiteCode(DatabaseLinkRequest request);
	/**
	 * @Title: 获取组织单位 单站预警 10位sitecode
	 * @Description:
	 * @author liujc@ucap.com.cn	2016-8-2上午10:51:01
	 * @param request
	 * @return
	 */
	public List<DatabaseLink> queryEarlySingleSiteCode(DatabaseLinkRequest request);
	/**
	 * 
	 * @描述:填报单位 手动预警  ，查询 收费的组织单位
	 * @作者:linhb@ucap.com.cn
	 * @时间:2016年11月22日下午4:37:25 
	 * @param param
	 * @return
	 */
	public List<DatabaseLink> queryJoinContract(Map<String, Object> param);
}

