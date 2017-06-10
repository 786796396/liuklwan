package com.ucap.cloud_web.service.impl;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.publics.util.page.PageVo;
import com.publics.util.utils.QueryUtils;
import com.ucap.cloud_web.dao.ErrorInfoDao;
import com.ucap.cloud_web.dto.ErrorInfoRequest;
import com.ucap.cloud_web.entity.ErrorInfo;
import com.ucap.cloud_web.service.IErrorInfoService;


/*** <br>* <b>作者：</b>qingjy<br>* <b>日期：</b> 2017-04-28 16:08:42 <br>* <b>版权所有：<b>版权所有(C) 2017<br>*/@Servicepublic class ErrorInfoServiceImpl implements IErrorInfoService {


	@Autowired	private ErrorInfoDao errorInfoDao;	@Override	public int add(ErrorInfo errorInfo){		return errorInfoDao.add(errorInfo);	}
	@Override	public ErrorInfo get(Integer id){		return errorInfoDao.get(id);	}
	@Override	public void update(ErrorInfo errorInfo){		errorInfoDao.update(errorInfo);	}
	@Override	public void delete(Integer id){		errorInfoDao.delete(id);	}
	@Override	public PageVo<ErrorInfo> query(ErrorInfoRequest request) {		List<ErrorInfo> errorInfo = queryList(request);		PageVo<ErrorInfo> pv = new PageVo<ErrorInfo>();
		int count = queryCount(request);
		pv.setData(errorInfo);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(ErrorInfoRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return errorInfoDao.queryCount(param);	}

	@Override	public List<ErrorInfo> queryList(ErrorInfoRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<ErrorInfo> list = errorInfoDao.query(param);		return list;	}

	@Override
	public PageVo<ErrorInfo> getErrorInfoList(ErrorInfoRequest request) {
		Map<String, Object> param = QueryUtils.getQueryMap(request);
		List<ErrorInfo> list = errorInfoDao.getErrorInfoList(param);

		PageVo<ErrorInfo> pv = new PageVo<ErrorInfo>();
		Integer paging = request.getPaging();
		int count = getErrorInfoCount(request);
		if (paging == 1) {
			if (count > 100) {
				count = 100;
			}
		}

		pv.setData(list);
		pv.setPageNo(request.getPageNo());
		pv.setPageSize(request.getPageSize());
		pv.setRecordSize(count);
		return pv;
	}

	@Override
	public int getErrorInfoCount(ErrorInfoRequest request) {
		Map<String, Object> param = QueryUtils.getQueryMap(request);
		return errorInfoDao.getErrorInfoCount(param);
	}
}

