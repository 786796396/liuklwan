package com.ucap.cloud_web.service.impl;


import java.util.HashMap;
import java.util.List;
import com.ucap.cloud_web.entity.LinkAllInfo;
import org.springframework.stereotype.Service;
import com.publics.util.page.PageVo;
import org.springframework.beans.factory.annotation.Autowired;


/**


	@Autowired






		if(request.getServiceList() !=null){
			param.put("serviceList", request.getServiceList());
		}

	@Override
	public List<LinkAllInfoRequest> queryByMap(Map<String, Object> map) {
		List<LinkAllInfoRequest> list = linkAllInfoDao.queryByMap(map);
		return list;
	}

	@Override
	public List<LinkAllInfo> getAllline(Map<String, Object> map) {
		return linkAllInfoDao.getAllline(map);
	}

	@Override
	public List<LinkAllInfo> getAlllineOrg(Map<String, Object> map) {
		return linkAllInfoDao.getAlllineOrg(map);
	}

	@Override
	public List<LinkAllInfoRequest> getAllLinkSum(Map<String, Object> map) {
		return linkAllInfoDao.getAllLinkSum(map);
	}

	@Override
	public List<LinkAllInfoRequest> queryLinkAllInfoByTree(
			HashMap<String, Object> paraMap) {
		return linkAllInfoDao.queryLinkAllInfoByTree(paraMap);
	}

	@Override
	public int queryLinkAllInfoByTreeCount(HashMap<String, Object> paraMap) {
		return linkAllInfoDao.queryLinkAllInfoByTreeCount(paraMap);
	}

