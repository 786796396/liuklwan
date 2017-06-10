package com.ucap.cloud_web.dao;


import com.ucap.cloud_web.dto.BigDataXmlRequest;
import com.ucap.cloud_web.entity.BigDataXml;import com.publics.util.dao.GenericDao;/*** <br>* <b>作者：</b>liujc<br>* <b>日期：</b> 2016-07-21 10:29:02 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public interface BigDataXmlDao extends GenericDao<BigDataXml>{	public void delete(BigDataXmlRequest request);}

