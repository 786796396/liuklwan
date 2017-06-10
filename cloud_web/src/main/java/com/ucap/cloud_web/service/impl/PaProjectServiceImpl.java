package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.PaProject;import com.ucap.cloud_web.entity.PaTask;
import com.ucap.cloud_web.service.IPaProjectService;import com.ucap.cloud_web.dao.PaProjectDao;import org.springframework.stereotype.Service;import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.PaProjectRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-08-23 10:59:23 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@Servicepublic class PaProjectServiceImpl implements IPaProjectService {


	@Autowired	private PaProjectDao paProjectDao;	@Override	public void add(PaProject paProject){		paProjectDao.add(paProject);	}
	@Override	public PaProject get(Integer id){		return paProjectDao.get(id);	}
	@Override	public void update(PaProject paProject){		paProjectDao.update(paProject);	}
	@Override	public void delete(Integer id){		paProjectDao.delete(id);	}
	@Override	public PageVo<PaProject> query(PaProjectRequest request) {		List<PaProject> paProject = queryList(request);		PageVo<PaProject> pv = new PageVo<PaProject>();		int count = queryCount(request);		pv.setData(paProject);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(PaProjectRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return paProjectDao.queryCount(param);	}
	@Override	public List<PaProject> queryList(PaProjectRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<PaProject> list = paProjectDao.query(param);		return list;	}

	@Override
	public List<PaTask> queryTaskList(PaProjectRequest paProjectRequest) {
		Map<String, Object> param = QueryUtils.getQueryMap(paProjectRequest);
		List<PaTask> list = paProjectDao.queryTaskList(param);
		return list;
	}
}

