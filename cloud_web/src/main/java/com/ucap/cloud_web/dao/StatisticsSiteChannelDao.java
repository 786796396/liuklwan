package com.ucap.cloud_web.dao;


import java.util.List;
import java.util.Map;

import com.publics.util.dao.GenericDao;
import com.ucap.cloud_web.entity.StatisticsSiteChannel;/*** <br>* <b>作者：</b>qingjy<br>* <b>日期：</b> 2017-05-03 19:08:59 <br>* <b>版权所有：<b>版权所有(C) 2017<br>*/public interface StatisticsSiteChannelDao extends GenericDao<StatisticsSiteChannel>{

	/**
	 * @描述:地图数据
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2017年5月3日下午7:21:45
	 * @param param
	 * @return
	 */

	List<StatisticsSiteChannel> getMapInfoList(Map<String, Object> param);
}

