package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.Log;import com.ucap.cloud_web.service.ILogService;import com.ucap.cloud_web.dao.LogDao;import org.springframework.stereotype.Service;import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.LogRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2015-10-29 11:06:39 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/@Servicepublic class LogServiceImpl implements ILogService {


	@Autowired	private LogDao logDao;	@Override	public void add(Log log){		logDao.add(log);	}
	@Override	public Log get(Integer id){		return logDao.get(id);	}
	@Override	public void update(Log log){		logDao.update(log);	}
	@Override	public void delete(Integer id){		logDao.delete(id);	}
	@Override	public PageVo<Log> query(LogRequest request) {		List<Log> log = queryList(request);		PageVo<Log> pv = new PageVo<Log>();		int count = queryCount(request);		pv.setData(log);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(LogRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return logDao.queryCount(param);	}
	@Override	public List<Log> queryList(LogRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<Log> list = logDao.query(param);		return list;	}
}

