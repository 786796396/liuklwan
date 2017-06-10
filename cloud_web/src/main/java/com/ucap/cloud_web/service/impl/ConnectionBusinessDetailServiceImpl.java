package com.ucap.cloud_web.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.publics.util.page.PageVo;
import com.publics.util.utils.QueryUtils;
import com.ucap.cloud_web.dao.ConnectionBusinessDetailDao;
import com.ucap.cloud_web.dto.ConnectionBusinessDetailRequest;
import com.ucap.cloud_web.entity.ConnectionBusinessDetail;
import com.ucap.cloud_web.service.IConnectionBusinessDetailService;

/**
 * <br>
 * <b>作者：</b>Nora<br>
 * <b>日期：</b> 2015-10-29 11:19:33 <br>
 * <b>版权所有：<b>版权所有(C) 2015<br>
 */

@Service
public class ConnectionBusinessDetailServiceImpl implements
		IConnectionBusinessDetailService {

	@Autowired
	private ConnectionBusinessDetailDao connectionBusinessDetailDao;

	@Override
	public void add(ConnectionBusinessDetail connectionBusinessDetail) {
		connectionBusinessDetailDao.add(connectionBusinessDetail);
	}

	@Override
	public ConnectionBusinessDetail get(Integer id) {
		return connectionBusinessDetailDao.get(id);
	}

	@Override
	public void update(ConnectionBusinessDetail connectionBusinessDetail) {
		connectionBusinessDetailDao.update(connectionBusinessDetail);
	}

	@Override
	public void delete(Integer id) {
		connectionBusinessDetailDao.delete(id);
	}

	@Override
	public PageVo<ConnectionBusinessDetail> query(
			ConnectionBusinessDetailRequest request) {
		List<ConnectionBusinessDetail> connectionBusinessDetail = queryList(request);

		PageVo<ConnectionBusinessDetail> pv = new PageVo<ConnectionBusinessDetail>();
		int count = queryCount(request);

		pv.setData(connectionBusinessDetail);
		pv.setPageNo(request.getPageNo());
		pv.setPageSize(request.getPageSize());
		pv.setRecordSize(count);
		return pv;
	}

	@Override
	public int queryCount(ConnectionBusinessDetailRequest request) {
		Map<String, Object> param = QueryUtils.getQueryMap(request);
		return connectionBusinessDetailDao.queryCount(param);
	}

	@Override
	public List<ConnectionBusinessDetail> queryList(
			ConnectionBusinessDetailRequest request) {
		Map<String, Object> param = QueryUtils.getQueryMap(request);
		List<ConnectionBusinessDetail> list = connectionBusinessDetailDao
				.query(param);
		return list;
	}

	@Override
	public List<ConnectionBusinessDetailRequest> queryListByGroup(
			Map<String, Object> paraMap) {
		return connectionBusinessDetailDao.queryListByGroup(paraMap);
	}

	@Override
	public int queryListByGroupCount(Map<String, Object> paraMap) {
		// TODO Auto-generated method stub
		return connectionBusinessDetailDao.queryListByGroupCount(paraMap);
	}

}
