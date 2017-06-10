package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.TaskAll;import com.ucap.cloud_web.service.ITaskAllService;import com.ucap.cloud_web.dao.TaskAllDao;import org.springframework.stereotype.Service;import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.TaskAllRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>SunJiang<br>* <b>日期：</b> 2015-10-30 09:51:22 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/@Servicepublic class TaskAllServiceImpl implements ITaskAllService {


	@Autowired	private TaskAllDao taskAllDao;	@Override	public void add(TaskAll taskAll){		taskAllDao.add(taskAll);	}
	@Override	public TaskAll get(Integer id){		return taskAllDao.get(id);	}
	@Override	public void update(TaskAll taskAll){		taskAllDao.update(taskAll);	}
	@Override	public void delete(Integer id){		taskAllDao.delete(id);	}
	@Override	public PageVo<TaskAll> query(TaskAllRequest request) {		List<TaskAll> taskAll = queryList(request);		PageVo<TaskAll> pv = new PageVo<TaskAll>();		int count = queryCount(request);		pv.setData(taskAll);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(TaskAllRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return taskAllDao.queryCount(param);	}
	@Override	public List<TaskAll> queryList(TaskAllRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<TaskAll> list = taskAllDao.query(param);		return list;	}
}

