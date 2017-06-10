package com.ucap.cloud_web.dao;


import java.util.Map;

import com.ucap.cloud_web.entity.SysAttach;import com.publics.util.dao.GenericDao;/*** <br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-08-23 10:59:24 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public interface SysAttachDao extends GenericDao<SysAttach>{

	/**
	 * 通过表明  主键值 删除数据
	 * @param param
	 */
	void deleteByTbNameAndId(Map<String, Object> param);}

