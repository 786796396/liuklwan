package com.ucap.cloud_web.service.impl;


import java.util.HashMap;
import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.IndexCount;import com.ucap.cloud_web.service.IIndexCountService;import com.ucap.cloud_web.dao.IndexCountDao;import org.springframework.stereotype.Service;import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.IndexCountRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2016-01-05 13:11:41 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@Servicepublic class IndexCountServiceImpl implements IIndexCountService {


	@Autowired	private IndexCountDao indexCountDao;	@Override	public void add(IndexCount indexCount){		indexCountDao.add(indexCount);	}
	@Override	public IndexCount get(Integer id){		return indexCountDao.get(id);	}
	@Override	public void update(IndexCount indexCount){		indexCountDao.update(indexCount);	}
	@Override	public void delete(Integer id){		indexCountDao.delete(id);	}
	@Override	public PageVo<IndexCount> query(IndexCountRequest request) {		List<IndexCount> indexCount = queryList(request);		PageVo<IndexCount> pv = new PageVo<IndexCount>();		int count = queryCount(request);		pv.setData(indexCount);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(IndexCountRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return indexCountDao.queryCount(param);	}
	@Override	public List<IndexCount> queryList(IndexCountRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<IndexCount> list = indexCountDao.query(param);		return list;	}

	@Override
	public IndexCountRequest queryByMap(HashMap<String, Object> param) {
		return indexCountDao.queryByMap(param);
	}

	@Override
	public List<IndexCountRequest> queryListByMap(HashMap<String, Object> param) {
		return indexCountDao.queryListByMap(param);
	}

	@Override
	public List<IndexCount> getLineList(HashMap<String, Object> param) {
		return indexCountDao.getLineList(param);
	}

	@Override
	public List<IndexCountRequest> getListByMap(HashMap<String, Object> param) {
		
		return indexCountDao.getListByMap(param);
	}
}

