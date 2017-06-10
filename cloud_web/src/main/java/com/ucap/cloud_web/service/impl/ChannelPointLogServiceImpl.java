package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.ChannelPointLog;import com.ucap.cloud_web.service.IChannelPointLogService;import com.ucap.cloud_web.dao.ChannelPointLogDao;import org.springframework.stereotype.Service;import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.ChannelPointLogRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>qingjy<br>* <b>日期：</b> 2016-11-29 17:19:36 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@Servicepublic class ChannelPointLogServiceImpl implements IChannelPointLogService {


	@Autowired	private ChannelPointLogDao channelPointLogDao;	@Override	public int add(ChannelPointLog channelPointLog){		return channelPointLogDao.add(channelPointLog);	}
	@Override	public ChannelPointLog get(Integer id){		return channelPointLogDao.get(id);	}
	@Override	public void update(ChannelPointLog channelPointLog){		channelPointLogDao.update(channelPointLog);	}
	@Override	public void delete(Integer id){		channelPointLogDao.delete(id);	}
	@Override	public PageVo<ChannelPointLog> query(ChannelPointLogRequest request) {		List<ChannelPointLog> channelPointLog = queryList(request);		PageVo<ChannelPointLog> pv = new PageVo<ChannelPointLog>();		int count = queryCount(request);		pv.setData(channelPointLog);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(ChannelPointLogRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return channelPointLogDao.queryCount(param);	}
	@Override	public List<ChannelPointLog> queryList(ChannelPointLogRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<ChannelPointLog> list = channelPointLogDao.query(param);		return list;	}
}

