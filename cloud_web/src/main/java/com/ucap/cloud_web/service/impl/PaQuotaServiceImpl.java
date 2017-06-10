package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.PaQuota;import com.ucap.cloud_web.service.IPaQuotaService;import com.ucap.cloud_web.dao.PaQuotaDao;import org.springframework.stereotype.Service;import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.PaQuotaRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-08-23 10:59:23 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@Servicepublic class PaQuotaServiceImpl implements IPaQuotaService {


	@Autowired	private PaQuotaDao paQuotaDao;	@Override	public void add(PaQuota paQuota){		paQuotaDao.add(paQuota);	}
	@Override	public PaQuota get(Integer id){		return paQuotaDao.get(id);	}
	@Override	public void update(PaQuota paQuota){		paQuotaDao.update(paQuota);	}
	@Override	public void delete(Integer id){		paQuotaDao.delete(id);	}
	@Override	public PageVo<PaQuota> query(PaQuotaRequest request) {		List<PaQuota> paQuota = queryList(request);		PageVo<PaQuota> pv = new PageVo<PaQuota>();		int count = queryCount(request);		pv.setData(paQuota);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(PaQuotaRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return paQuotaDao.queryCount(param);	}
	@Override	public List<PaQuota> queryList(PaQuotaRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<PaQuota> list = paQuotaDao.query(param);		return list;	}
}

