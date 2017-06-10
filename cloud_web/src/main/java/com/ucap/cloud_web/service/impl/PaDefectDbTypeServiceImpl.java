package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.PaDefectDbType;import com.ucap.cloud_web.service.IPaDefectDbTypeService;import com.ucap.cloud_web.dao.PaDefectDbTypeDao;import org.springframework.stereotype.Service;import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.PaDefectDbTypeRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-08-23 10:59:23 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@Servicepublic class PaDefectDbTypeServiceImpl implements IPaDefectDbTypeService {


	@Autowired	private PaDefectDbTypeDao paDefectDbTypeDao;	@Override	public void add(PaDefectDbType paDefectDbType){		paDefectDbTypeDao.add(paDefectDbType);	}
	@Override	public PaDefectDbType get(Integer id){		return paDefectDbTypeDao.get(id);	}
	@Override	public void update(PaDefectDbType paDefectDbType){		paDefectDbTypeDao.update(paDefectDbType);	}
	@Override	public void delete(Integer id){		paDefectDbTypeDao.delete(id);	}
	@Override	public PageVo<PaDefectDbType> query(PaDefectDbTypeRequest request) {		List<PaDefectDbType> paDefectDbType = queryList(request);		PageVo<PaDefectDbType> pv = new PageVo<PaDefectDbType>();		int count = queryCount(request);		pv.setData(paDefectDbType);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(PaDefectDbTypeRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return paDefectDbTypeDao.queryCount(param);	}
	@Override	public List<PaDefectDbType> queryList(PaDefectDbTypeRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<PaDefectDbType> list = paDefectDbTypeDao.query(param);		return list;	}
}

