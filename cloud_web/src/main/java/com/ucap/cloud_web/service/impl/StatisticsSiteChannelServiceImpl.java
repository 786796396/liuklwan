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


/**


	@Autowired







	@Override
	public List<StatisticsSiteChannel> getMapInfoList(StatisticsSiteChannelRequest req) {
		Map<String, Object> param = QueryUtils.getQueryMap(req);
		return statisticsSiteChannelDao.getMapInfoList(param);
	}

