package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.ConnectionChannelDetail;import com.ucap.cloud_web.service.IConnectionChannelDetailService;import com.ucap.cloud_web.dao.ConnectionChannelDetailDao;import org.springframework.stereotype.Service;import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.ConnectionChannelDetailRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>Nora<br>* <b>日期：</b> 2015-10-29 11:19:33 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/@Servicepublic class ConnectionChannelDetailServiceImpl implements IConnectionChannelDetailService {


	@Autowired	private ConnectionChannelDetailDao connectionChannelDetailDao;	@Override	public void add(ConnectionChannelDetail connectionChannelDetail){		connectionChannelDetailDao.add(connectionChannelDetail);	}
	@Override	public ConnectionChannelDetail get(Integer id){		return connectionChannelDetailDao.get(id);	}
	@Override	public void update(ConnectionChannelDetail connectionChannelDetail){		connectionChannelDetailDao.update(connectionChannelDetail);	}
	@Override	public void delete(Integer id){		connectionChannelDetailDao.delete(id);	}
	@Override	public PageVo<ConnectionChannelDetail> query(ConnectionChannelDetailRequest request) {		List<ConnectionChannelDetail> connectionChannelDetail = queryList(request);		PageVo<ConnectionChannelDetail> pv = new PageVo<ConnectionChannelDetail>();		int count = queryCount(request);		pv.setData(connectionChannelDetail);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(ConnectionChannelDetailRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return connectionChannelDetailDao.queryCount(param);	}
	@Override	public List<ConnectionChannelDetail> queryList(ConnectionChannelDetailRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);
		List<ConnectionChannelDetail> list = connectionChannelDetailDao.query(param);		return list;	}

	@Override
	public List<ConnectionChannelDetailRequest> queryListByGroup(
			Map<String, Object> paraMap) {
		return connectionChannelDetailDao.queryListByGroup(paraMap);
	}

	@Override
	public int queryListByGroupCount(Map<String, Object> paraMap) {
		return connectionChannelDetailDao.queryListByGroupCount(paraMap);
	}
}

