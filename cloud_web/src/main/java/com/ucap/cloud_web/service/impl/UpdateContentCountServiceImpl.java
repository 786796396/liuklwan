package com.ucap.cloud_web.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.publics.util.page.PageVo;
import com.publics.util.utils.QueryUtils;
import com.ucap.cloud_web.dao.UpdateContentCountDao;
import com.ucap.cloud_web.dto.UpdateContentCountRequest;
import com.ucap.cloud_web.entity.UpdateContentCount;
import com.ucap.cloud_web.service.IUpdateContentCountService;

/**
 * <br>
 * <b>作者：</b>SunJiang<br>
 * <b>日期：</b> 2015-10-30 09:51:22 <br>
 * <b>版权所有：<b>版权所有(C) 2015<br>
 */

@Service
public class UpdateContentCountServiceImpl implements
		IUpdateContentCountService {

	@Autowired
	private UpdateContentCountDao updateContentCountDao;

	@Override
	public void add(UpdateContentCount updateContentCount) {
		updateContentCountDao.add(updateContentCount);
	}

	@Override
	public UpdateContentCount get(Integer id) {
		return updateContentCountDao.get(id);
	}

	@Override
	public void update(UpdateContentCount updateContentCount) {
		updateContentCountDao.update(updateContentCount);
	}

	@Override
	public void delete(Integer id) {
		updateContentCountDao.delete(id);
	}

	@Override
	public PageVo<UpdateContentCount> query(UpdateContentCountRequest request) {
		List<UpdateContentCount> updateContentCount = queryList(request);

		PageVo<UpdateContentCount> pv = new PageVo<UpdateContentCount>();
		int count = queryCount(request);

		pv.setData(updateContentCount);
		pv.setPageNo(request.getPageNo());
		pv.setPageSize(request.getPageSize());
		pv.setRecordSize(count);
		return pv;
	}

	@Override
	public int queryCount(UpdateContentCountRequest request) {
		Map<String, Object> param = QueryUtils.getQueryMap(request);
		if(request.getLinkList()!=null && request.getLinkList().size()>0){
			param.put("linkList", request.getLinkList());
		}
		return updateContentCountDao.queryCount(param);
	}

	@Override
	public List<UpdateContentCount> queryList(UpdateContentCountRequest request) {
		Map<String, Object> param = QueryUtils.getQueryMap(request);
		param.put("start", null);
		param.put("pageSize", null);
		List<UpdateContentCount> list = updateContentCountDao.query(param);
		return list;
	}

	@Override
	public List<UpdateContentCount> queryByMap(Map<String, Object> map) {
		List<UpdateContentCount> list = updateContentCountDao.queryByMap(map);
		return list;
	}

	@Override
	public List<UpdateContentCountRequest> queryCountLine(
			Map<String, Object> map) {
		return updateContentCountDao.queryCountLine(map);
	}

	@Override
	public List<UpdateContentCount> queryCountAnalyzeLine(
			UpdateContentCountRequest request) {
		return updateContentCountDao.queryCountAnalyzeLine(request);
	}

	@Override
	public List<UpdateContentCountRequest> querySumOrg(Map<String, Object> map) {
		return updateContentCountDao.querySumOrg(map);
	}

	@Override
	public List<UpdateContentCountRequest> queryChannelName(
			Map<String, Object> map) {
		return updateContentCountDao.queryChannelName(map);
	}

	@Override
	public List<UpdateContentCountRequest> querySumByMap(
			HashMap<String, Object> hashMap) {
		return updateContentCountDao.querySumByMap(hashMap);
	}

	@Override
	public List<UpdateContentCountRequest> queryNotUpdateByMap(
			Map<String, Object> paramMap) {
		
		return updateContentCountDao.queryNotUpdateByMap(paramMap);
	}

	@Override
	public int queryUpdateConByMap(Map<String, Object> paramMap) {
		return updateContentCountDao.queryUpdateConByMap(paramMap);
	}

	@Override
	public List<UpdateContentCountRequest> getUpdateHomeList(HashMap<String, Object> hashMap) {
		return updateContentCountDao.getUpdateHomeList(hashMap);
	}

	@Override
	public List<UpdateContentCountRequest> queryTotalByMap(
			Map<String, Object> updateMap) {
		return updateContentCountDao.queryTotalByMap(updateMap);
	}

	@Override
	public int queryTotalByMapCount(Map<String, Object> updateMap) {
		return updateContentCountDao.queryTotalByMapCount(updateMap);
	}

	@Override
	public int getUpdateHomeListCount(HashMap<String, Object> paraMap) {
		return updateContentCountDao.getUpdateHomeListCount(paraMap);
	}

}
