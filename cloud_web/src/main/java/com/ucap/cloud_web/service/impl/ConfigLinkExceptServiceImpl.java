package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.ConfigLinkExcept;import com.ucap.cloud_web.service.IConfigLinkExceptService;import com.ucap.cloud_web.dao.ConfigLinkExceptDao;import org.springframework.stereotype.Service;import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.ConfigLinkExceptRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-08-16 15:13:23 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@Servicepublic class ConfigLinkExceptServiceImpl implements IConfigLinkExceptService {


	@Autowired	private ConfigLinkExceptDao configLinkExceptDao;	@Override	public void add(ConfigLinkExcept configLinkExcept){		configLinkExceptDao.add(configLinkExcept);	}
	@Override	public ConfigLinkExcept get(Integer id){		return configLinkExceptDao.get(id);	}
	@Override	public void update(ConfigLinkExcept configLinkExcept){		configLinkExceptDao.update(configLinkExcept);	}
	@Override	public void delete(Integer id){		configLinkExceptDao.delete(id);	}
	@Override	public PageVo<ConfigLinkExcept> query(ConfigLinkExceptRequest request) {		List<ConfigLinkExcept> configLinkExcept = queryList(request);		PageVo<ConfigLinkExcept> pv = new PageVo<ConfigLinkExcept>();		int count = queryCount(request);		pv.setData(configLinkExcept);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(ConfigLinkExceptRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return configLinkExceptDao.queryCount(param);	}
	@Override	public List<ConfigLinkExcept> queryList(ConfigLinkExceptRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<ConfigLinkExcept> list = configLinkExceptDao.query(param);		return list;	}
}

