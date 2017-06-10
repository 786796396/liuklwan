package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.SpotCheckNotice;import com.ucap.cloud_web.service.ISpotCheckNoticeService;import com.ucap.cloud_web.dao.SpotCheckNoticeDao;import org.springframework.stereotype.Service;import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.SpotCheckNoticeRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>yangshuai<br>* <b>日期：</b> 2016-06-01 18:43:28 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@Servicepublic class SpotCheckNoticeServiceImpl implements ISpotCheckNoticeService {


	@Autowired	private SpotCheckNoticeDao spotCheckNoticeDao;	@Override	public void add(SpotCheckNotice spotCheckNotice){		spotCheckNoticeDao.add(spotCheckNotice);	}
	@Override	public SpotCheckNotice get(Integer id){		return spotCheckNoticeDao.get(id);	}
	@Override	public void update(SpotCheckNotice spotCheckNotice){		spotCheckNoticeDao.update(spotCheckNotice);	}
	@Override	public void delete(Integer id){		spotCheckNoticeDao.delete(id);	}
	@Override	public PageVo<SpotCheckNotice> query(SpotCheckNoticeRequest request) {		List<SpotCheckNotice> spotCheckNotice = queryList(request);		PageVo<SpotCheckNotice> pv = new PageVo<SpotCheckNotice>();		int count = queryCount(request);		pv.setData(spotCheckNotice);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(SpotCheckNoticeRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return spotCheckNoticeDao.queryCount(param);	}
	@Override	public List<SpotCheckNotice> queryList(SpotCheckNoticeRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<SpotCheckNotice> list = spotCheckNoticeDao.query(param);		return list;	}
}

