package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.SecurityChannel;import com.ucap.cloud_web.service.ISecurityChannelService;import com.ucap.cloud_web.dao.SecurityChannelDao;import org.springframework.stereotype.Service;import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.SecurityChannelRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>SunJiang<br>* <b>日期：</b> 2015-10-30 09:53:39 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/@Servicepublic class SecurityChannelServiceImpl implements ISecurityChannelService {


	@Autowired	private SecurityChannelDao securityChannelDao;	@Override	public void add(SecurityChannel securityChannel){		securityChannelDao.add(securityChannel);	}
	@Override	public SecurityChannel get(Integer id){		return securityChannelDao.get(id);	}
	@Override	public void update(SecurityChannel securityChannel){		securityChannelDao.update(securityChannel);	}
	@Override	public void delete(Integer id){		securityChannelDao.delete(id);	}
	@Override	public PageVo<SecurityChannel> query(SecurityChannelRequest request) {		List<SecurityChannel> securityChannel = queryList(request);		PageVo<SecurityChannel> pv = new PageVo<SecurityChannel>();		int count = queryCount(request);		pv.setData(securityChannel);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(SecurityChannelRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return securityChannelDao.queryCount(param);	}
	@Override	public List<SecurityChannel> queryList(SecurityChannelRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<SecurityChannel> list = securityChannelDao.query(param);		return list;	}
}

