package com.ucap.cloud_web.dao;


import java.util.List;
import java.util.Map;

import com.ucap.cloud_web.dto.LinkAllDetailRequest;
import com.ucap.cloud_web.entity.LinkAllDetail;import com.publics.util.dao.GenericDao;/*** <br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2015-10-29 11:05:51 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/public interface LinkAllDetailDao extends GenericDao<LinkAllDetail>{

	int queryNoExceptCount(Map<String, Object> param);
	/**
	 * linhb 2016 - 08 -17
	 * 分页查询查询排除 config_link_except 表中共有的siteCode url
	 * @param param
	 * @return
	 */
	List<LinkAllDetail> queryNoExceptList(Map<String, Object> param);
	/**
	 * @Description: 根据tree表查询  某个组织单位下  某个服务周期内  每类全站死链编码的问题个数 
	 * @author cuichx --- 2017-4-7下午6:44:34     
	 * @param paramMap
	 * @return
	 */
	List<LinkAllDetailRequest> querySumGroupBy(Map<String, Object> paramMap);
	/**
	 * @Description: 统计某个服务周期内  每种死链编码的个数
	 * @author cuichx --- 2017-4-8下午1:04:37     
	 * @param paramMap
	 * @return
	 */
	List<LinkAllDetailRequest> queryByCode(Map<Object, Object> paramMap);}

