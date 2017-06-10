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


/*** <br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2015-10-29 11:06:29 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/@Servicepublic class LinkHomeTrendServiceImpl implements ILinkHomeTrendService {


	@Autowired	private LinkHomeTrendDao linkHomeTrendDao;	@Override	public void add(LinkHomeTrend linkHomeTrend){		linkHomeTrendDao.add(linkHomeTrend);	}
	@Override	public LinkHomeTrend get(Integer id){		return linkHomeTrendDao.get(id);	}
	@Override	public void update(LinkHomeTrend linkHomeTrend){		linkHomeTrendDao.update(linkHomeTrend);	}
	@Override	public void delete(Integer id){		linkHomeTrendDao.delete(id);	}
	@Override	public PageVo<LinkHomeTrend> query(LinkHomeTrendRequest request) {		List<LinkHomeTrend> linkHomeTrend = queryList(request);		PageVo<LinkHomeTrend> pv = new PageVo<LinkHomeTrend>();		int count = queryCount(request);		pv.setData(linkHomeTrend);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(LinkHomeTrendRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return linkHomeTrendDao.queryCount(param);	}
	@Override	public List<LinkHomeTrend> queryList(LinkHomeTrendRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<LinkHomeTrend> list = linkHomeTrendDao.query(param);		return list;	}

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
}

