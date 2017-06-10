package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.AccountBindInfo;import com.ucap.cloud_web.service.IAccountBindInfoService;import com.ucap.cloud_web.dao.AccountBindInfoDao;import org.springframework.stereotype.Service;import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.AccountBindInfoRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2016-01-04 14:48:02 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@Servicepublic class AccountBindInfoServiceImpl implements IAccountBindInfoService {


	@Autowired	private AccountBindInfoDao accountBindInfoDao;	@Override	public void add(AccountBindInfo accountBindInfo){		accountBindInfoDao.add(accountBindInfo);	}
	@Override	public AccountBindInfo get(Integer id){		return accountBindInfoDao.get(id);	}
	@Override	public void update(AccountBindInfo accountBindInfo){		accountBindInfoDao.update(accountBindInfo);	}
	@Override	public void delete(Integer id){		accountBindInfoDao.delete(id);	}
	@Override	public PageVo<AccountBindInfo> query(AccountBindInfoRequest request) {		List<AccountBindInfo> accountBindInfo = queryList(request);		PageVo<AccountBindInfo> pv = new PageVo<AccountBindInfo>();		int count = queryCount(request);		pv.setData(accountBindInfo);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(AccountBindInfoRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return accountBindInfoDao.queryCount(param);	}
	@Override	public List<AccountBindInfo> queryList(AccountBindInfoRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<AccountBindInfo> list = accountBindInfoDao.query(param);		return list;	}
	@Override
	public AccountBindInfoRequest queryByMap(Map<String, Object> paramMap) {
		return accountBindInfoDao.queryByMap(paramMap);
	}
	@Override
	public List<AccountBindInfoRequest> queryBySiteCode(Map<String, Object> map) {
		return accountBindInfoDao.queryBySiteCode(map);
	}

	@Override
	public List<AccountBindInfoRequest> queryEarlyInfo(
			Map<String, Object> earlyMap) {
		return accountBindInfoDao.queryEarlyInfo(earlyMap);
	}



	@Override
	public List<AccountBindInfoRequest> queryTBSite(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return accountBindInfoDao.queryTBSite(paramMap);
	}

	@Override
	public List<AccountBindInfoRequest> queryTBByMap(
			Map<String, Object> paramMap) {
		return accountBindInfoDao.queryTBByMap(paramMap);
	}

	@Override
	public List<AccountBindInfo> queryListByMap(Map<String, Object> paramMap) {
		return accountBindInfoDao.queryListByMap(paramMap);
	}}

