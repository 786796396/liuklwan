package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.BigOrgTrend;import com.ucap.cloud_web.service.IBigOrgTrendService;import com.ucap.cloud_web.dao.BigOrgTrendDao;import org.springframework.stereotype.Service;import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.BigOrgTrendRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>zhaoDY<br>* <b>日期：</b> 2017-02-14 19:09:38 <br>* <b>版权所有：<b>版权所有(C) 2017<br>*/@Servicepublic class BigOrgTrendServiceImpl implements IBigOrgTrendService {


	@Autowired	private BigOrgTrendDao bigOrgTrendDao;	@Override	public int add(BigOrgTrend bigOrgTrend){		return bigOrgTrendDao.add(bigOrgTrend);	}
	@Override	public BigOrgTrend get(Integer id){		return bigOrgTrendDao.get(id);	}
	@Override	public void update(BigOrgTrend bigOrgTrend){		bigOrgTrendDao.update(bigOrgTrend);	}
	@Override	public void delete(Integer id){		bigOrgTrendDao.delete(id);	}
	@Override	public PageVo<BigOrgTrend> query(BigOrgTrendRequest request) {		List<BigOrgTrend> bigOrgTrend = queryList(request);		PageVo<BigOrgTrend> pv = new PageVo<BigOrgTrend>();		int count = queryCount(request);		pv.setData(bigOrgTrend);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(BigOrgTrendRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return bigOrgTrendDao.queryCount(param);	}
	@Override	public List<BigOrgTrend> queryList(BigOrgTrendRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<BigOrgTrend> list = bigOrgTrendDao.query(param);		return list;	}
}

