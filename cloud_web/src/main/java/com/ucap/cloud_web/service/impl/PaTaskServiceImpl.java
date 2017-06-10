package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.PaTask;import com.ucap.cloud_web.service.IPaTaskService;import com.ucap.cloud_web.dao.PaTaskDao;import org.springframework.stereotype.Service;import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.PaTaskRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-08-23 10:59:24 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@Servicepublic class PaTaskServiceImpl implements IPaTaskService {


	@Autowired	private PaTaskDao paTaskDao;	@Override	public void add(PaTask paTask){		paTaskDao.add(paTask);	}
	@Override	public PaTask get(Integer id){		return paTaskDao.get(id);	}
	@Override	public void update(PaTask paTask){		paTaskDao.update(paTask);	}
	@Override	public void delete(Integer id){		paTaskDao.delete(id);	}
	@Override	public PageVo<PaTask> query(PaTaskRequest request) {		List<PaTask> paTask = queryList(request);		PageVo<PaTask> pv = new PageVo<PaTask>();		int count = queryCount(request);		pv.setData(paTask);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(PaTaskRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return paTaskDao.queryCount(param);	}
	@Override	public List<PaTask> queryList(PaTaskRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<PaTask> list = paTaskDao.query(param);		return list;	}

	@Override
	public List<PaTask> queryJoinTarget(PaTaskRequest pRequest) {
		Map<String, Object> param = QueryUtils.getQueryMap(pRequest);
		return paTaskDao.queryJoinTarget(param);
	}
}

