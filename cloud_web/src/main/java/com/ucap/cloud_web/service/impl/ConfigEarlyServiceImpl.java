package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.ConfigEarly;import com.ucap.cloud_web.entity.EarlyDetail;
import com.ucap.cloud_web.service.IConfigEarlyService;import com.ucap.cloud_web.dao.ConfigEarlyDao;import org.springframework.stereotype.Service;import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.ConfigEarlyRequest;import com.ucap.cloud_web.dto.EarlyDetailRequest;

import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>liujc<br>* <b>日期：</b> 2016-07-20 14:57:52 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@Servicepublic class ConfigEarlyServiceImpl implements IConfigEarlyService {


	@Autowired	private ConfigEarlyDao configEarlyDao;	@Override	public void add(ConfigEarly configEarly){		configEarlyDao.add(configEarly);	}
	@Override	public ConfigEarly get(Integer id){		return configEarlyDao.get(id);	}
	@Override	public void update(ConfigEarly configEarly){		configEarlyDao.update(configEarly);	}
	@Override	public void delete(Integer id){		configEarlyDao.delete(id);	}
	@Override	public PageVo<ConfigEarly> query(ConfigEarlyRequest request) {		List<ConfigEarly> configEarly = queryList(request);		PageVo<ConfigEarly> pv = new PageVo<ConfigEarly>();		int count = queryCount(request);		pv.setData(configEarly);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(ConfigEarlyRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return configEarlyDao.queryCount(param);	}
	@Override	public List<ConfigEarly> queryList(ConfigEarlyRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<ConfigEarly> list = configEarlyDao.query(param);		return list;	}
	@Override
	public List<ConfigEarly> queryOrgSingleSiteCode(ConfigEarlyRequest request) {
		Map<String, Object> param = QueryUtils.getQueryMap(request);
		return configEarlyDao.queryOrgSingleSiteCode(param);
	}

	@Override
	public List<ConfigEarly> findByMap(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return configEarlyDao.findByMap(paramMap);
	}}

