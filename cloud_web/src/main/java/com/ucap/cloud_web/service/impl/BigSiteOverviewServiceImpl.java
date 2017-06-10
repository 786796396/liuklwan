package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.BigSiteOverview;import com.ucap.cloud_web.service.IBigSiteOverviewService;import com.ucap.cloud_web.dao.BigSiteOverviewDao;import org.springframework.stereotype.Service;import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.BigSiteOverviewRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>liukl<br>* <b>日期：</b> 2017-03-08 14:46:28 <br>* <b>版权所有：<b>版权所有(C) 2017<br>*/@Servicepublic class BigSiteOverviewServiceImpl implements IBigSiteOverviewService {


	@Autowired	private BigSiteOverviewDao bigSiteOverviewDao;	@Override	public int add(BigSiteOverview bigSiteOverview){		return bigSiteOverviewDao.add(bigSiteOverview);	}
	@Override	public BigSiteOverview get(Integer id){		return bigSiteOverviewDao.get(id);	}
	@Override	public void update(BigSiteOverview bigSiteOverview){		bigSiteOverviewDao.update(bigSiteOverview);	}
	@Override	public void delete(Integer id){		bigSiteOverviewDao.delete(id);	}
	@Override	public PageVo<BigSiteOverview> query(BigSiteOverviewRequest request) {		List<BigSiteOverview> bigSiteOverview = queryList(request);		PageVo<BigSiteOverview> pv = new PageVo<BigSiteOverview>();		int count = queryCount(request);		pv.setData(bigSiteOverview);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(BigSiteOverviewRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return bigSiteOverviewDao.queryCount(param);	}
	@Override	public List<BigSiteOverview> queryList(BigSiteOverviewRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<BigSiteOverview> list = bigSiteOverviewDao.query(param);		return list;	}
}

