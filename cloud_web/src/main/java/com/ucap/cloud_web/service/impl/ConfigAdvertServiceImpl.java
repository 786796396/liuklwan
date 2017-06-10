package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.ConfigAdvert;import com.ucap.cloud_web.service.IConfigAdvertService;import com.ucap.cloud_web.dao.ConfigAdvertDao;import org.springframework.stereotype.Service;import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.ConfigAdvertRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>yangshuai<br>* <b>日期：</b> 2016-09-06 19:26:19 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@Servicepublic class ConfigAdvertServiceImpl implements IConfigAdvertService {


	@Autowired	private ConfigAdvertDao configAdvertDao;	@Override	public int add(ConfigAdvert configAdvert){		return configAdvertDao.add(configAdvert);	}
	@Override	public ConfigAdvert get(Integer id){		return configAdvertDao.get(id);	}
	@Override	public void update(ConfigAdvert configAdvert){		configAdvertDao.update(configAdvert);	}
	@Override	public void delete(Integer id){		configAdvertDao.delete(id);	}
	@Override	public PageVo<ConfigAdvert> query(ConfigAdvertRequest request) {		List<ConfigAdvert> configAdvert = queryList(request);		PageVo<ConfigAdvert> pv = new PageVo<ConfigAdvert>();		int count = queryCount(request);		pv.setData(configAdvert);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(ConfigAdvertRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return configAdvertDao.queryCount(param);	}
	@Override	public List<ConfigAdvert> queryList(ConfigAdvertRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<ConfigAdvert> list = configAdvertDao.query(param);		return list;	}
}

