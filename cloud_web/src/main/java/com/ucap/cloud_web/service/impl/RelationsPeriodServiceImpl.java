package com.ucap.cloud_web.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.publics.util.page.PageVo;
import com.publics.util.utils.QueryUtils;
import com.ucap.cloud_web.dao.RelationsPeriodDao;
import com.ucap.cloud_web.dto.RelationsPeriodRequest;
import com.ucap.cloud_web.dtoResponse.SecurityGuaranteeResponse;
import com.ucap.cloud_web.entity.RelationsPeriod;
import com.ucap.cloud_web.service.IRelationsPeriodService;


/**


	@Autowired







	@Override
	public List<RelationsPeriodRequest> queryByMap(Map<String, Object> pMap) {
		return relationsPeriodDao.queryByMap(pMap);
	}

	@Override
	public List<SecurityGuaranteeResponse> contentSecurityTree(HashMap<String, Object> map) {
		return relationsPeriodDao.contentSecurityTree(map);
	}

