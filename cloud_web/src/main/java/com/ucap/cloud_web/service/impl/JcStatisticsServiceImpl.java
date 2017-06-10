package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.JcStatistics;import com.ucap.cloud_web.service.IJcStatisticsService;import com.ucap.cloud_web.dao.JcStatisticsDao;import org.springframework.stereotype.Service;import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.JcStatisticsRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-10-09 09:53:32 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@Servicepublic class JcStatisticsServiceImpl implements IJcStatisticsService {


	@Autowired	private JcStatisticsDao jcStatisticsDao;	@Override	public int add(JcStatistics jcStatistics){		return jcStatisticsDao.add(jcStatistics);	}
	@Override	public JcStatistics get(Integer id){		return jcStatisticsDao.get(id);	}
	@Override	public void update(JcStatistics jcStatistics){		jcStatisticsDao.update(jcStatistics);	}
	@Override	public void delete(Integer id){		jcStatisticsDao.delete(id);	}
	@Override	public PageVo<JcStatistics> query(JcStatisticsRequest request) {		List<JcStatistics> jcStatistics = queryList(request);		PageVo<JcStatistics> pv = new PageVo<JcStatistics>();		int count = queryCount(request);		pv.setData(jcStatistics);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(JcStatisticsRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return jcStatisticsDao.queryCount(param);	}
	@Override	public List<JcStatistics> queryList(JcStatisticsRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<JcStatistics> list = jcStatisticsDao.query(param);		return list;	}
}

