package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.MonitorDarkChain;import com.ucap.cloud_web.service.IMonitorDarkChainService;import com.ucap.cloud_web.dao.MonitorDarkChainDao;import org.springframework.stereotype.Service;import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.MonitorDarkChainRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-11-04 11:24:50 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@Servicepublic class MonitorDarkChainServiceImpl implements IMonitorDarkChainService {


	@Autowired	private MonitorDarkChainDao monitorDarkChainDao;	@Override	public int add(MonitorDarkChain monitorDarkChain){		return monitorDarkChainDao.add(monitorDarkChain);	}
	@Override	public MonitorDarkChain get(Integer id){		return monitorDarkChainDao.get(id);	}
	@Override	public void update(MonitorDarkChain monitorDarkChain){		monitorDarkChainDao.update(monitorDarkChain);	}
	@Override	public void delete(Integer id){		monitorDarkChainDao.delete(id);	}
	@Override	public PageVo<MonitorDarkChain> query(MonitorDarkChainRequest request) {		List<MonitorDarkChain> monitorDarkChain = queryList(request);		PageVo<MonitorDarkChain> pv = new PageVo<MonitorDarkChain>();		int count = queryCount(request);		pv.setData(monitorDarkChain);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(MonitorDarkChainRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return monitorDarkChainDao.queryCount(param);	}
	@Override	public List<MonitorDarkChain> queryList(MonitorDarkChainRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);
		if(request.getStartEndDates()!=null && request.getStartEndDates().size()>0){
			param.put("startEndDates", request.getStartEndDates());
		}		List<MonitorDarkChain> list = monitorDarkChainDao.query(param);		return list;	}
}

