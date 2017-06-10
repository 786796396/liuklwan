package com.ucap.cloud_web.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.publics.util.page.PageVo;
import com.publics.util.utils.QueryUtils;
import com.ucap.cloud_web.dao.LinkHomeTrendDao;
import com.ucap.cloud_web.dto.LinkHomeTrendRequest;
import com.ucap.cloud_web.entity.LinkHomeTrend;
import com.ucap.cloud_web.service.ILinkHomeTrendService;


/**


	@Autowired







	@Override
	public List<LinkHomeTrend> queryByMap(Map<String, Object> map) {
		List<LinkHomeTrend> list = linkHomeTrendDao.queryByMap(map);
		return list;
	}

	@Override
	public List<LinkHomeTrend> getHomeLine(Map<String, Object> map) {
		return linkHomeTrendDao.getHomeLine(map);
	}

	@Override
	public List<LinkHomeTrendRequest> getHomeSum(Map<String, Object> map) {
		return linkHomeTrendDao.getHomeSum(map);
	}

	@Override
	public int queryHomeDataByMap(Map<String, Object> paramMap) {
		return linkHomeTrendDao.queryHomeDataByMap(paramMap);
	}

	@Override
	public List<LinkHomeTrendRequest> getHomeOrgList(HashMap<String, Object> hashMap) {
		return linkHomeTrendDao.getHomeOrgList(hashMap);
	}

	@Override
	public List<LinkHomeTrendRequest> queryTotalOrgByMap(
			HashMap<String, Object> paraMap) {
		return linkHomeTrendDao.queryTotalOrgByMap(paraMap);
	}

	@Override
	public int queryTotalOrgByMapCount(HashMap<String, Object> paraMap) {
		return linkHomeTrendDao.queryTotalOrgByMapCount(paraMap);
	}

