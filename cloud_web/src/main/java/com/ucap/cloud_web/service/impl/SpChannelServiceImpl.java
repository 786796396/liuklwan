package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.SpChannel;import com.ucap.cloud_web.service.ISpChannelService;import com.ucap.cloud_web.dao.SpChannelDao;import org.springframework.stereotype.Service;import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.SpChannelRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>liujc<br>* <b>日期：</b> 2016-11-22 10:32:16 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@Servicepublic class SpChannelServiceImpl implements ISpChannelService {


	@Autowired	private SpChannelDao spChannelDao;	@Override	public int add(SpChannel spChannel){		return spChannelDao.add(spChannel);	}
	@Override	public SpChannel get(Integer id){		return spChannelDao.get(id);	}
	@Override	public void update(SpChannel spChannel){		spChannelDao.update(spChannel);	}
	@Override	public void delete(Integer id){		spChannelDao.delete(id);	}
	@Override	public PageVo<SpChannel> query(SpChannelRequest request) {		List<SpChannel> spChannel = queryList(request);		PageVo<SpChannel> pv = new PageVo<SpChannel>();		int count = queryCount(request);		pv.setData(spChannel);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(SpChannelRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return spChannelDao.queryCount(param);	}
	@Override	public List<SpChannel> queryList(SpChannelRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<SpChannel> list = spChannelDao.query(param);		return list;	}
}

