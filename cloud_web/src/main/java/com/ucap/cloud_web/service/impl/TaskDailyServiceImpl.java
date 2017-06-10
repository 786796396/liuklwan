package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.TaskDaily;import com.ucap.cloud_web.service.ITaskDailyService;import com.ucap.cloud_web.dao.TaskDailyDao;import org.springframework.stereotype.Service;import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.TaskDailyRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>SunJiang<br>* <b>日期：</b> 2015-10-30 09:51:22 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/@Servicepublic class TaskDailyServiceImpl implements ITaskDailyService {


	@Autowired	private TaskDailyDao taskDailyDao;	@Override	public void add(TaskDaily taskDaily){		taskDailyDao.add(taskDaily);	}
	@Override	public TaskDaily get(Integer id){		return taskDailyDao.get(id);	}
	@Override	public void update(TaskDaily taskDaily){		taskDailyDao.update(taskDaily);	}
	@Override	public void delete(Integer id){		taskDailyDao.delete(id);	}
	@Override	public PageVo<TaskDaily> query(TaskDailyRequest request) {		List<TaskDaily> taskDaily = queryList(request);		PageVo<TaskDaily> pv = new PageVo<TaskDaily>();		int count = queryCount(request);		pv.setData(taskDaily);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(TaskDailyRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return taskDailyDao.queryCount(param);	}
	@Override	public List<TaskDaily> queryList(TaskDailyRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);
		param.put("ids",request.getIds()==null?null:request.getIds());		List<TaskDaily> list = taskDailyDao.query(param);		return list;	}
}

