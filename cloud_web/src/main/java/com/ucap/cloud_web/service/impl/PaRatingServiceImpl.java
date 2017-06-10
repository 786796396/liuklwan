package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.PaRating;import com.ucap.cloud_web.entity.PaRatingDetail;
import com.ucap.cloud_web.service.IPaRatingService;import com.ucap.cloud_web.dao.PaRatingDao;import org.springframework.stereotype.Service;import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.PaRatingRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-08-23 10:59:23 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@Servicepublic class PaRatingServiceImpl implements IPaRatingService {


	@Autowired	private PaRatingDao paRatingDao;	@Override	public void add(PaRating paRating){		paRatingDao.add(paRating);	}
	@Override	public PaRating get(Integer id){		return paRatingDao.get(id);	}
	@Override	public void update(PaRating paRating){		paRatingDao.update(paRating);	}
	@Override	public void delete(Integer id){		paRatingDao.delete(id);	}
	@Override	public PageVo<PaRating> query(PaRatingRequest request) {		List<PaRating> paRating = queryList(request);		PageVo<PaRating> pv = new PageVo<PaRating>();		int count = queryCount(request);		pv.setData(paRating);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(PaRatingRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return paRatingDao.queryCount(param);	}
	@Override	public List<PaRating> queryList(PaRatingRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<PaRating> list = paRatingDao.query(param);		return list;	}

	@Override
	public List<PaRatingDetail> queryFour(PaRatingRequest paRatingRequest) {
		Map<String, Object> param = QueryUtils.getQueryMap(paRatingRequest);
		return paRatingDao.queryFour(param);
	}
}

