package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.PaRatingDetail;import com.ucap.cloud_web.service.IPaRatingDetailService;import com.ucap.cloud_web.dao.PaRatingDetailDao;import org.springframework.stereotype.Service;import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.PaRatingDetailRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-08-23 10:59:23 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@Servicepublic class PaRatingDetailServiceImpl implements IPaRatingDetailService {


	@Autowired	private PaRatingDetailDao paRatingDetailDao;	@Override	public void add(PaRatingDetail paRatingDetail){		paRatingDetailDao.add(paRatingDetail);	}
	@Override	public PaRatingDetail get(Integer id){		return paRatingDetailDao.get(id);	}
	@Override	public void update(PaRatingDetail paRatingDetail){		paRatingDetailDao.update(paRatingDetail);	}
	@Override	public void delete(Integer id){		paRatingDetailDao.delete(id);	}
	@Override	public PageVo<PaRatingDetail> query(PaRatingDetailRequest request) {		List<PaRatingDetail> paRatingDetail = queryList(request);		PageVo<PaRatingDetail> pv = new PageVo<PaRatingDetail>();		int count = queryCount(request);		pv.setData(paRatingDetail);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(PaRatingDetailRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return paRatingDetailDao.queryCount(param);	}
	@Override	public List<PaRatingDetail> queryList(PaRatingDetailRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<PaRatingDetail> list = paRatingDetailDao.query(param);		return list;	}

	@Override
	public List<PaRatingDetail> groupByOneName(PaRatingDetailRequest pRequest) {
		Map<String, Object> param = QueryUtils.getQueryMap(pRequest);
		List<PaRatingDetail> list = paRatingDetailDao.groupByOneName(param);
		return list;
	}

	@Override
	public List<PaRatingDetail> getWriteData(PaRatingDetailRequest pRequest) {
		Map<String, Object> param = QueryUtils.getQueryMap(pRequest);
		List<PaRatingDetail> list = paRatingDetailDao.getWriteData(param);
		return list;
	}
}

