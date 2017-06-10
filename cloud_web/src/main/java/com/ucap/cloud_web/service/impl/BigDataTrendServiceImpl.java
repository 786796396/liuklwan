package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.BigDataTrend;import com.ucap.cloud_web.service.IBigDataTrendService;import com.ucap.cloud_web.dao.BigDataTrendDao;import org.springframework.stereotype.Service;import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.BigDataTrendRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>zhaoDY<br>* <b>日期：</b> 2017-02-14 19:11:25 <br>* <b>版权所有：<b>版权所有(C) 2017<br>*/@Servicepublic class BigDataTrendServiceImpl implements IBigDataTrendService {


	@Autowired	private BigDataTrendDao bigDataTrendDao;	@Override	public int add(BigDataTrend bigDataTrend){		return bigDataTrendDao.add(bigDataTrend);	}
	@Override	public BigDataTrend get(Integer id){		return bigDataTrendDao.get(id);	}
	@Override	public void update(BigDataTrend bigDataTrend){		bigDataTrendDao.update(bigDataTrend);	}
	@Override	public void delete(Integer id){		bigDataTrendDao.delete(id);	}
	@Override	public PageVo<BigDataTrend> query(BigDataTrendRequest request) {		List<BigDataTrend> bigDataTrend = queryList(request);		PageVo<BigDataTrend> pv = new PageVo<BigDataTrend>();		int count = queryCount(request);		pv.setData(bigDataTrend);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(BigDataTrendRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return bigDataTrendDao.queryCount(param);	}
	@Override	public List<BigDataTrend> queryList(BigDataTrendRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<BigDataTrend> list = bigDataTrendDao.query(param);		return list;	}

	@Override
	public List<BigDataTrend> sitesList(BigDataTrendRequest request) {
		Map<String, Object> param = QueryUtils.getQueryMap(request);
		List<BigDataTrend> sitesList = bigDataTrendDao.sitesList(param);
		return sitesList;
	}

	@Override
	public List<BigDataTrend> portalList(BigDataTrendRequest request) {
		Map<String, Object> param = QueryUtils.getQueryMap(request);
		List<BigDataTrend> portalList = bigDataTrendDao.portalList(param);
		return portalList;
	}

	@Override
	public List<BigDataTrend> balanceList(BigDataTrendRequest request) {
		Map<String, Object> param = QueryUtils.getQueryMap(request);
		List<BigDataTrend> balanceList = bigDataTrendDao.balanceList(param);
		return balanceList;
	}
}

