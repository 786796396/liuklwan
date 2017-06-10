package com.ucap.cloud_web.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.publics.util.page.PageVo;
import com.publics.util.utils.QueryUtils;
import com.ucap.cloud_web.dao.ConnectionAllHomeDao;
import com.ucap.cloud_web.dto.ConnectionAllHomeRequest;
import com.ucap.cloud_web.dtoResponse.ConnectionAllHomeResponse;
import com.ucap.cloud_web.entity.ConnectionAllHome;
import com.ucap.cloud_web.service.IConnectionAllHomeService;


/**


	@Autowired







	@Override
	public List<ConnectionAllHomeResponse> getConnectionAllHomeList(HashMap<String, Object> hashMap) {
		return connectionAllHomeDao.getConnectionAllHomeList(hashMap);
	}

