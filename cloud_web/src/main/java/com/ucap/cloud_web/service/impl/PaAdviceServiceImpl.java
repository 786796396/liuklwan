package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.PaAdvice;import com.ucap.cloud_web.service.IPaAdviceService;import com.ucap.cloud_web.dao.PaAdviceDao;import org.springframework.stereotype.Service;import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.PaAdviceRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-08-26 09:34:34 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@Servicepublic class PaAdviceServiceImpl implements IPaAdviceService {


	@Autowired	private PaAdviceDao paAdviceDao;	@Override	public void add(PaAdvice paAdvice){		paAdviceDao.add(paAdvice);	}
	@Override	public PaAdvice get(Integer id){		return paAdviceDao.get(id);	}
	@Override	public void update(PaAdvice paAdvice){		paAdviceDao.update(paAdvice);	}
	@Override	public void delete(Integer id){		paAdviceDao.delete(id);	}
	@Override	public PageVo<PaAdvice> query(PaAdviceRequest request) {		List<PaAdvice> paAdvice = queryList(request);		PageVo<PaAdvice> pv = new PageVo<PaAdvice>();		int count = queryCount(request);		pv.setData(paAdvice);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(PaAdviceRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return paAdviceDao.queryCount(param);	}
	@Override	public List<PaAdvice> queryList(PaAdviceRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<PaAdvice> list = paAdviceDao.query(param);		return list;	}

	@Override
	public void updateById(PaAdviceRequest aaRequest) {
		paAdviceDao.updateById(aaRequest);
	}
}

