package com.ucap.cloud_web.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.publics.util.page.PageVo;
import com.publics.util.utils.QueryUtils;
import com.ucap.cloud_web.dao.ChannelPointDao;
import com.ucap.cloud_web.dto.ChannelPointRequest;
import com.ucap.cloud_web.dtoResponse.ChannelPointResponse;
import com.ucap.cloud_web.dtoResponse.DatabaseInfoResponse;
import com.ucap.cloud_web.entity.ChannelPoint;
import com.ucap.cloud_web.service.IChannelPointService;

/**
 * <br>
 * <b>作者：</b>Nora<br>
 * <b>日期：</b> 2015-10-29 11:19:32 <br>
 * <b>版权所有：<b>版权所有(C) 2015<br>
 */

@Service
public class ChannelPointServiceImpl implements IChannelPointService {

	@Autowired
	private ChannelPointDao channelPointDao;

	@Override
	public void add(ChannelPoint channelPoint) {
		channelPointDao.add(channelPoint);
	}

	@Override
	public ChannelPoint get(Integer id) {
		return channelPointDao.get(id);
	}

	@Override
	public void update(ChannelPoint channelPoint) {
		channelPointDao.update(channelPoint);
	}

	@Override
	public void delete(Integer id) {
		channelPointDao.delete(id);
	}

	@Override
	public PageVo<ChannelPoint> query(ChannelPointRequest request) {
		List<ChannelPoint> channelPoint = queryList(request);

		PageVo<ChannelPoint> pv = new PageVo<ChannelPoint>();
		int count = queryCount(request);

		pv.setData(channelPoint);
		pv.setPageNo(request.getPageNo());
		pv.setPageSize(request.getPageSize());
		pv.setRecordSize(count);
		return pv;
	}

	@Override
	public int queryCount(ChannelPointRequest request) {
		Map<String, Object> param = QueryUtils.getQueryMap(request);
		param.put("types", request.getTypes());
		return channelPointDao.queryCount(param);
	}

	@Override
	public List<ChannelPoint> queryList(ChannelPointRequest request) {
		Map<String, Object> param = QueryUtils.getQueryMap(request);
		if(request.getTypes()!=null ){
			param.put("types", request.getTypes());
		}
		if(request.getChannelPointIdArray()!=null){
			param.put("channelPointIdArray", request.getChannelPointIdArray());
		}
		List<ChannelPoint> list = channelPointDao.query(param);
		return list;
	}

	@Override
	public boolean onLineCheck(String channelUrl) {
		return false;
	}

	@Override
	public List<ChannelPointRequest> queryByMap(ChannelPointRequest request) {
		Map<String, Object> param = QueryUtils.getQueryMap(request);
		channelPointDao.queryByMap(param);
		return null;
	}

	@Override
	public int queryCountByType(ChannelPointRequest request) {
		Map<String, Object> param = QueryUtils.getQueryMap(request);
		int i  = channelPointDao.queryCountByType(param);
		return i;
	}

	@Override
	public List<ChannelPointResponse> getChannelPointInfo(HashMap<String, Object> hashMap) {
		return channelPointDao.getChannelPointInfo(hashMap);
	}

	@Override
	public List<DatabaseInfoResponse> getlowerLevelList(HashMap<String, Object> hashMap) {
		return channelPointDao.getlowerLevelList(hashMap);
	}

}
