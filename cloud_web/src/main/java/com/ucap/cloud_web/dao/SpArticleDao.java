package com.ucap.cloud_web.dao;


import com.ucap.cloud_web.entity.SpArticle;

import java.util.List;
import java.util.Map;

import com.publics.util.dao.GenericDao;/*** <br>* <b>作者：</b>liujc<br>* <b>日期：</b> 2016-11-22 10:33:04 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public interface SpArticleDao extends GenericDao<SpArticle>{

	/**
	 * @描述:查询关联文章权限表的文章
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2016年12月6日上午10:10:36 
	 * @param param
	 * @return 
	 */
	
	public List<SpArticle> getSpArtList(Map<String, Object> param);}

