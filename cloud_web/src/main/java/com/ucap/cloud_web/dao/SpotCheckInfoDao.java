package com.ucap.cloud_web.dao;


import java.util.List;
import java.util.Map;

import com.publics.util.dao.GenericDao;
import com.ucap.cloud_web.entity.SpotCheckInfo;
/*** <br>* <b>作者：</b>yuangw@ucap.com.cn<br>* <b>日期：</b> 2016-04-19 11:16:09 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public interface SpotCheckInfoDao extends GenericDao<SpotCheckInfo>{	public List<SpotCheckInfo> findBySpotCheckInfoCode(Map<String, Object> param);}

