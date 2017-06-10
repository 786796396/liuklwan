package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.ChannelPointTemp;import com.ucap.cloud_web.service.IChannelPointTempService;import com.ucap.cloud_web.dao.ChannelPointTempDao;import org.springframework.stereotype.Service;import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.ChannelPointTempRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>qingjy<br>* <b>日期：</b> 2016-11-29 17:18:44 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@Servicepublic class ChannelPointTempServiceImpl implements IChannelPointTempService {


	@Autowired	private ChannelPointTempDao channelPointTempDao;	@Override	public int add(ChannelPointTemp channelPointTemp){		return channelPointTempDao.add(channelPointTemp);	}
	@Override	public ChannelPointTemp get(Integer id){		return channelPointTempDao.get(id);	}
	@Override	public void update(ChannelPointTemp channelPointTemp){		channelPointTempDao.update(channelPointTemp);	}
	@Override	public void delete(Integer id){		channelPointTempDao.delete(id);	}
	@Override	public PageVo<ChannelPointTemp> query(ChannelPointTempRequest request) {		List<ChannelPointTemp> channelPointTemp = queryList(request);		PageVo<ChannelPointTemp> pv = new PageVo<ChannelPointTemp>();		int count = queryCount(request);		pv.setData(channelPointTemp);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(ChannelPointTempRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return channelPointTempDao.queryCount(param);	}
	@Override	public List<ChannelPointTemp> queryList(ChannelPointTempRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<ChannelPointTemp> list = channelPointTempDao.query(param);		return list;	}
}

