package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.DicConfig;import com.ucap.cloud_web.service.IDicConfigService;import com.ucap.cloud_web.dao.DicConfigDao;import org.springframework.stereotype.Service;import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.DicConfigRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>yangshuai<br>* <b>日期：</b> 2016-05-24 15:52:08 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@Servicepublic class DicConfigServiceImpl implements IDicConfigService {


	@Autowired	private DicConfigDao dicConfigDao;	@Override	public void add(DicConfig dicConfig){		dicConfigDao.add(dicConfig);	}
	@Override	public DicConfig get(Integer id){		return dicConfigDao.get(id);	}
	@Override	public void update(DicConfig dicConfig){		dicConfigDao.update(dicConfig);	}
	@Override	public void delete(Integer id){		dicConfigDao.delete(id);	}
	@Override	public PageVo<DicConfig> query(DicConfigRequest request) {		List<DicConfig> dicConfig = queryList(request);		PageVo<DicConfig> pv = new PageVo<DicConfig>();		int count = queryCount(request);		pv.setData(dicConfig);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(DicConfigRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return dicConfigDao.queryCount(param);	}
	@Override	public List<DicConfig> queryList(DicConfigRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<DicConfig> list = dicConfigDao.query(param);		return list;	}
	
	@Override
	public List<DicConfigRequest> queryListByMap(Map<String, Object> map) {
		return dicConfigDao.queryListByMap(map);
	}
}

