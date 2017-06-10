package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.UserLog;import com.ucap.cloud_web.service.IUserLogService;import com.ucap.cloud_web.dao.UserLogDao;import org.springframework.stereotype.Service;import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.UserLogRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>ccx<br>* <b>日期：</b> 2016-03-05 17:42:26 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@Servicepublic class UserLogServiceImpl implements IUserLogService {


	@Autowired	private UserLogDao userLogDao;	@Override	public void add(UserLog userLog){
			userLogDao.add(userLog);
	}
	@Override	public UserLog get(Integer id){		return userLogDao.get(id);	}
	@Override	public void update(UserLog userLog){		userLogDao.update(userLog);	}
	@Override	public void delete(Integer id){		userLogDao.delete(id);	}
	@Override	public PageVo<UserLog> query(UserLogRequest request) {		List<UserLog> userLog = queryList(request);		PageVo<UserLog> pv = new PageVo<UserLog>();		int count = queryCount(request);		pv.setData(userLog);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(UserLogRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return userLogDao.queryCount(param);	}
	@Override	public List<UserLog> queryList(UserLogRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<UserLog> list = userLogDao.query(param);		return list;	}
}

