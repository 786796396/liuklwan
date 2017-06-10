package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.DicChannel;import com.ucap.cloud_web.service.IDicChannelService;import com.ucap.cloud_web.dao.DicChannelDao;import org.springframework.stereotype.Service;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.DicChannelRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2015-10-29 11:17:59 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/@Servicepublic class DicChannelServiceImpl implements IDicChannelService {


	@Autowired	private DicChannelDao dicChannelDao;	@Override	public void add(DicChannel dicChannel){		dicChannelDao.add(dicChannel);	}
	@Override	public DicChannel get(Integer id){		return dicChannelDao.get(id);	}
	@Override	public void update(DicChannel dicChannel){		dicChannelDao.update(dicChannel);	}
	@Override	public void delete(Integer id){		dicChannelDao.delete(id);	}
	@Override	public List<DicChannel> queryList(DicChannelRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<DicChannel> list = dicChannelDao.query(param);		return list;	}
}

