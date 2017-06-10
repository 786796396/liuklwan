package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.EarlyLog;import com.ucap.cloud_web.service.IEarlyLogService;import com.ucap.cloud_web.dao.EarlyLogDao;import org.springframework.stereotype.Service;import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.EarlyLogRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>luocheng<br>* <b>日期：</b> 2017-01-03 11:00:12 <br>* <b>版权所有：<b>版权所有(C) 2017<br>*/@Servicepublic class EarlyLogServiceImpl implements IEarlyLogService {


	@Autowired	private EarlyLogDao earlyLogDao;	@Override	public int add(EarlyLog earlyLog){		return earlyLogDao.add(earlyLog);	}
	@Override	public EarlyLog get(Integer id){		return earlyLogDao.get(id);	}
	@Override	public void update(EarlyLog earlyLog){		earlyLogDao.update(earlyLog);	}
	@Override	public void delete(Integer id){		earlyLogDao.delete(id);	}
	@Override	public PageVo<EarlyLog> query(EarlyLogRequest request) {		List<EarlyLog> earlyLog = queryList(request);		PageVo<EarlyLog> pv = new PageVo<EarlyLog>();		int count = queryCount(request);		pv.setData(earlyLog);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(EarlyLogRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return earlyLogDao.queryCount(param);	}
	@Override	public List<EarlyLog> queryList(EarlyLogRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<EarlyLog> list = earlyLogDao.query(param);		return list;	}
}

