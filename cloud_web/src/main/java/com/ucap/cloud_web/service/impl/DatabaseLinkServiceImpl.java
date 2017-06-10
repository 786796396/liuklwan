package com.ucap.cloud_web.service.impl;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.publics.util.page.PageVo;
import com.publics.util.utils.QueryUtils;
import com.ucap.cloud_web.dao.DatabaseLinkDao;
import com.ucap.cloud_web.dto.DatabaseLinkRequest;
import com.ucap.cloud_web.entity.DatabaseLink;
import com.ucap.cloud_web.service.IDatabaseLinkService;


/**


	@Autowired







	@Override
	public List<DatabaseLinkRequest> queryCountBySiteCode(DatabaseLinkRequest request) {
		return databaseLinkDao.queryCountBySiteCode(request);
	}

	@Override
	public List<DatabaseLink> queryEarlySingleSiteCode(
			DatabaseLinkRequest request) {
		return databaseLinkDao.queryEarlySingleSiteCode(request);
	}

	@Override
	public List<DatabaseLink> queryJoinContract(DatabaseLinkRequest dataBaseLinkRequest) {
		Map<String, Object> param = QueryUtils.getQueryMap(dataBaseLinkRequest);
		List<DatabaseLink> list = databaseLinkDao.queryJoinContract(param);
		return list;
	}

