package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.AccountBindId;import com.ucap.cloud_web.service.IAccountBindIdService;import com.ucap.cloud_web.dao.AccountBindIdDao;import org.springframework.stereotype.Service;import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.AccountBindIdRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2017-05-03 10:19:53 <br>* <b>版权所有：<b>版权所有(C) 2017<br>*/@Servicepublic class AccountBindIdServiceImpl implements IAccountBindIdService {


	@Autowired	private AccountBindIdDao accountBindIdDao;	@Override	public int add(AccountBindId accountBindId){		return accountBindIdDao.add(accountBindId);	}
	@Override	public AccountBindId get(Integer id){		return accountBindIdDao.get(id);	}
	@Override	public void update(AccountBindId accountBindId){		accountBindIdDao.update(accountBindId);	}
	@Override	public void delete(Integer id){		accountBindIdDao.delete(id);	}
	@Override	public PageVo<AccountBindId> query(AccountBindIdRequest request) {		List<AccountBindId> accountBindId = queryList(request);		PageVo<AccountBindId> pv = new PageVo<AccountBindId>();		int count = queryCount(request);		pv.setData(accountBindId);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(AccountBindIdRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return accountBindIdDao.queryCount(param);	}
	@Override	public List<AccountBindId> queryList(AccountBindIdRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<AccountBindId> list = accountBindIdDao.query(param);		return list;	}
}

