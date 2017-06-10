package com.ucap.cloud_web.service.impl;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.publics.util.page.PageVo;
import com.publics.util.utils.QueryUtils;
import com.ucap.cloud_web.dao.StatisticsSiteChannelDao;
import com.ucap.cloud_web.dto.StatisticsSiteChannelRequest;
import com.ucap.cloud_web.entity.StatisticsSiteChannel;
import com.ucap.cloud_web.service.IStatisticsSiteChannelService;


/*** <br>* <b>作者：</b>qingjy<br>* <b>日期：</b> 2017-05-03 19:08:59 <br>* <b>版权所有：<b>版权所有(C) 2017<br>*/@Servicepublic class StatisticsSiteChannelServiceImpl implements IStatisticsSiteChannelService {


	@Autowired	private StatisticsSiteChannelDao statisticsSiteChannelDao;	@Override	public int add(StatisticsSiteChannel statisticsSiteChannel){		return statisticsSiteChannelDao.add(statisticsSiteChannel);	}
	@Override	public StatisticsSiteChannel get(Integer id){		return statisticsSiteChannelDao.get(id);	}
	@Override	public void update(StatisticsSiteChannel statisticsSiteChannel){		statisticsSiteChannelDao.update(statisticsSiteChannel);	}
	@Override	public void delete(Integer id){		statisticsSiteChannelDao.delete(id);	}
	@Override	public PageVo<StatisticsSiteChannel> query(StatisticsSiteChannelRequest request) {		List<StatisticsSiteChannel> statisticsSiteChannel = queryList(request);		PageVo<StatisticsSiteChannel> pv = new PageVo<StatisticsSiteChannel>();		int count = queryCount(request);		pv.setData(statisticsSiteChannel);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(StatisticsSiteChannelRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return statisticsSiteChannelDao.queryCount(param);	}
	@Override	public List<StatisticsSiteChannel> queryList(StatisticsSiteChannelRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<StatisticsSiteChannel> list = statisticsSiteChannelDao.query(param);		return list;	}

	@Override
	public List<StatisticsSiteChannel> getMapInfoList(StatisticsSiteChannelRequest req) {
		Map<String, Object> param = QueryUtils.getQueryMap(req);
		return statisticsSiteChannelDao.getMapInfoList(param);
	}
}

