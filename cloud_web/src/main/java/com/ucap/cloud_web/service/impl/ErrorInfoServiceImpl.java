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


/**


	@Autowired




		int count = queryCount(request);



	@Override

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

