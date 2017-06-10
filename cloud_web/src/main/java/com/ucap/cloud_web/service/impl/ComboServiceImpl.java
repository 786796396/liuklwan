package com.ucap.cloud_web.service.impl;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.publics.util.utils.QueryUtils;
import com.ucap.cloud_web.dao.ComboDao;
import com.ucap.cloud_web.dto.ComboRequest;
import com.ucap.cloud_web.entity.Combo;
import com.ucap.cloud_web.service.IComboService;


/**


	@Autowired






	@Override
	public int queryCount(ComboRequest request) {
		Map<String, Object> param = QueryUtils.getQueryMap(request);
		return comboDao.queryCount(param);
	}

