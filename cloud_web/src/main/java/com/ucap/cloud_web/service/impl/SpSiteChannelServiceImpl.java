package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.SpSiteChannel;import com.ucap.cloud_web.service.ISpSiteChannelService;import com.ucap.cloud_web.dao.SpSiteChannelDao;import org.springframework.stereotype.Service;import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.SpSiteChannelRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>liujc<br>* <b>日期：</b> 2016-11-22 10:31:39 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@Servicepublic class SpSiteChannelServiceImpl implements ISpSiteChannelService {


	@Autowired	private SpSiteChannelDao spSiteChannelDao;	@Override	public int add(SpSiteChannel spSiteChannel){		return spSiteChannelDao.add(spSiteChannel);	}
	@Override	public SpSiteChannel get(Integer id){		return spSiteChannelDao.get(id);	}
	@Override	public void update(SpSiteChannel spSiteChannel){		spSiteChannelDao.update(spSiteChannel);	}
	@Override	public void delete(Integer id){		spSiteChannelDao.delete(id);	}
	@Override	public PageVo<SpSiteChannel> query(SpSiteChannelRequest request) {		List<SpSiteChannel> spSiteChannel = queryList(request);		PageVo<SpSiteChannel> pv = new PageVo<SpSiteChannel>();		int count = queryCount(request);		pv.setData(spSiteChannel);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(SpSiteChannelRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return spSiteChannelDao.queryCount(param);	}
	@Override	public List<SpSiteChannel> queryList(SpSiteChannelRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<SpSiteChannel> list = spSiteChannelDao.query(param);		return list;	}
}

