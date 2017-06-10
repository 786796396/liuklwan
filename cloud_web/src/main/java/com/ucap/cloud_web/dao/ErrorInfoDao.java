package com.ucap.cloud_web.dao;


import java.util.List;
import java.util.Map;

import com.publics.util.dao.GenericDao;
import com.ucap.cloud_web.entity.ErrorInfo;/*** <br>* <b>作者：</b>qingjy<br>* <b>日期：</b> 2017-04-28 16:08:42 <br>* <b>版权所有：<b>版权所有(C) 2017<br>*/public interface ErrorInfoDao extends GenericDao<ErrorInfo>{

	/**
	 * @描述:分页（关联tree表）
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2017年5月5日下午5:37:57
	 * @param param
	 * @return
	 */

	List<ErrorInfo> getErrorInfoList(Map<String, Object> param);

	/**
	 * @描述:分页（关联tree表）条数
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2017年5月5日下午5:38:05
	 * @param param
	 * @return
	 */

	int getErrorInfoCount(Map<String, Object> param);
}

