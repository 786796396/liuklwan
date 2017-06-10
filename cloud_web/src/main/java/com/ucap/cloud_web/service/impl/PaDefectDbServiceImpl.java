package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.PaDefectDb;import com.ucap.cloud_web.service.IPaDefectDbService;import com.ucap.cloud_web.dao.PaDefectDbDao;import org.springframework.stereotype.Service;import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.PaDefectDbRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-08-23 10:59:22 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@Servicepublic class PaDefectDbServiceImpl implements IPaDefectDbService {


	@Autowired	private PaDefectDbDao paDefectDbDao;	@Override	public void add(PaDefectDb paDefectDb){		paDefectDbDao.add(paDefectDb);	}
	@Override	public PaDefectDb get(Integer id){		return paDefectDbDao.get(id);	}
	@Override	public void update(PaDefectDb paDefectDb){		paDefectDbDao.update(paDefectDb);	}
	@Override	public void delete(Integer id){		paDefectDbDao.delete(id);	}
	@Override	public PageVo<PaDefectDb> query(PaDefectDbRequest request) {		List<PaDefectDb> paDefectDb = queryList(request);		PageVo<PaDefectDb> pv = new PageVo<PaDefectDb>();		int count = queryCount(request);		pv.setData(paDefectDb);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(PaDefectDbRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return paDefectDbDao.queryCount(param);	}
	@Override	public List<PaDefectDb> queryList(PaDefectDbRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<PaDefectDb> list = paDefectDbDao.query(param);		return list;	}
}

