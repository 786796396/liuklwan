package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.AccountBindInfoLog;import com.ucap.cloud_web.service.IAccountBindInfoLogService;import com.ucap.cloud_web.dao.AccountBindInfoLogDao;import org.springframework.stereotype.Service;import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.AccountBindInfoLogRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2017-05-02 19:42:09 <br>* <b>版权所有：<b>版权所有(C) 2017<br>*/@Servicepublic class AccountBindInfoLogServiceImpl implements IAccountBindInfoLogService {


	@Autowired	private AccountBindInfoLogDao accountBindInfoLogDao;	@Override	public int add(AccountBindInfoLog accountBindInfoLog){		return accountBindInfoLogDao.add(accountBindInfoLog);	}
	@Override	public AccountBindInfoLog get(Integer id){		return accountBindInfoLogDao.get(id);	}
	@Override	public void update(AccountBindInfoLog accountBindInfoLog){		accountBindInfoLogDao.update(accountBindInfoLog);	}
	@Override	public void delete(Integer id){		accountBindInfoLogDao.delete(id);	}
	@Override	public PageVo<AccountBindInfoLog> query(AccountBindInfoLogRequest request) {		List<AccountBindInfoLog> accountBindInfoLog = queryList(request);		PageVo<AccountBindInfoLog> pv = new PageVo<AccountBindInfoLog>();		int count = queryCount(request);		pv.setData(accountBindInfoLog);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(AccountBindInfoLogRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return accountBindInfoLogDao.queryCount(param);	}
	@Override	public List<AccountBindInfoLog> queryList(AccountBindInfoLogRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<AccountBindInfoLog> list = accountBindInfoLogDao.query(param);		return list;	}
}

