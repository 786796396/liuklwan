package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.PaTargetTask;import com.ucap.cloud_web.service.IPaTargetTaskService;import com.ucap.cloud_web.dao.PaTargetTaskDao;import org.springframework.stereotype.Service;import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.PaTargetTaskRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-08-23 10:59:24 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@Servicepublic class PaTargetTaskServiceImpl implements IPaTargetTaskService {


	@Autowired	private PaTargetTaskDao paTargetTaskDao;	@Override	public void add(PaTargetTask paTargetTask){		paTargetTaskDao.add(paTargetTask);	}
	@Override	public PaTargetTask get(Integer id){		return paTargetTaskDao.get(id);	}
	@Override	public void update(PaTargetTask paTargetTask){		paTargetTaskDao.update(paTargetTask);	}
	@Override	public void delete(Integer id){		paTargetTaskDao.delete(id);	}
	@Override	public PageVo<PaTargetTask> query(PaTargetTaskRequest request) {		List<PaTargetTask> paTargetTask = queryList(request);		PageVo<PaTargetTask> pv = new PageVo<PaTargetTask>();		int count = queryCount(request);		pv.setData(paTargetTask);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(PaTargetTaskRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return paTargetTaskDao.queryCount(param);	}
	@Override	public List<PaTargetTask> queryList(PaTargetTaskRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<PaTargetTask> list = paTargetTaskDao.query(param);		return list;	}
}

