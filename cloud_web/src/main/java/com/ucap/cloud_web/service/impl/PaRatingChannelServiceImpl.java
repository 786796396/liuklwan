package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.PaRatingChannel;import com.ucap.cloud_web.service.IPaRatingChannelService;import com.ucap.cloud_web.dao.PaRatingChannelDao;import org.springframework.stereotype.Service;import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.PaRatingChannelRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-08-23 10:59:23 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@Servicepublic class PaRatingChannelServiceImpl implements IPaRatingChannelService {


	@Autowired	private PaRatingChannelDao paRatingChannelDao;	@Override	public void add(PaRatingChannel paRatingChannel){		paRatingChannelDao.add(paRatingChannel);	}
	@Override	public PaRatingChannel get(Integer id){		return paRatingChannelDao.get(id);	}
	@Override	public void update(PaRatingChannel paRatingChannel){		paRatingChannelDao.update(paRatingChannel);	}
	@Override	public void delete(Integer id){		paRatingChannelDao.delete(id);	}
	@Override	public PageVo<PaRatingChannel> query(PaRatingChannelRequest request) {		List<PaRatingChannel> paRatingChannel = queryList(request);		PageVo<PaRatingChannel> pv = new PageVo<PaRatingChannel>();		int count = queryCount(request);		pv.setData(paRatingChannel);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(PaRatingChannelRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return paRatingChannelDao.queryCount(param);	}
	@Override	public List<PaRatingChannel> queryList(PaRatingChannelRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<PaRatingChannel> list = paRatingChannelDao.query(param);		return list;	}

	@Override
	public List<PaRatingChannel> getImgsAndAttch(PaRatingChannelRequest pRequest) {
		Map<String, Object> param = QueryUtils.getQueryMap(pRequest);
		List<PaRatingChannel> list = paRatingChannelDao.getImgsAndAttch(param);
		return list;
	}

	@Override
	public List<PaRatingChannel> queryJoinList(PaRatingChannelRequest ratingChannelRequery) {
		Map<String, Object> param = QueryUtils.getQueryMap(ratingChannelRequery);
		List<PaRatingChannel> list = paRatingChannelDao.queryJoinList(param);
		return list;
	}
}

