package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.ConfigTimer;import com.ucap.cloud_web.service.IConfigTimerService;import com.ucap.cloud_web.dao.ConfigTimerDao;import org.springframework.stereotype.Service;import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.ConfigTimerRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>Na.Y<br>* <b>日期：</b> 2016-09-14 09:49:59 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@Servicepublic class ConfigTimerServiceImpl implements IConfigTimerService {


	@Autowired	private ConfigTimerDao configTimerDao;	@Override	public int add(ConfigTimer configTimer){		return configTimerDao.add(configTimer);	}
	@Override	public ConfigTimer get(Integer id){		return configTimerDao.get(id);	}
	@Override	public void update(ConfigTimer configTimer){		configTimerDao.update(configTimer);	}
	@Override	public void delete(Integer id){		configTimerDao.delete(id);	}
	@Override	public PageVo<ConfigTimer> query(ConfigTimerRequest request) {		List<ConfigTimer> configTimer = queryList(request);		PageVo<ConfigTimer> pv = new PageVo<ConfigTimer>();		int count = queryCount(request);		pv.setData(configTimer);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(ConfigTimerRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return configTimerDao.queryCount(param);	}
	@Override	public List<ConfigTimer> queryList(ConfigTimerRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<ConfigTimer> list = configTimerDao.query(param);		return list;	}
}

