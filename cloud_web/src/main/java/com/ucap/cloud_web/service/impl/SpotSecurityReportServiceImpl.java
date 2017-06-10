package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.SpotSecurityReport;import com.ucap.cloud_web.service.ISpotSecurityReportService;import com.ucap.cloud_web.dao.SpotSecurityReportDao;import org.springframework.stereotype.Service;import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.SpotSecurityReportRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>liukl<br>* <b>日期：</b> 2017-03-21 18:49:16 <br>* <b>版权所有：<b>版权所有(C) 2017<br>*/@Servicepublic class SpotSecurityReportServiceImpl implements ISpotSecurityReportService {


	@Autowired	private SpotSecurityReportDao spotSecurityReportDao;	@Override	public int add(SpotSecurityReport spotSecurityReport){		return spotSecurityReportDao.add(spotSecurityReport);	}
	@Override	public SpotSecurityReport get(Integer id){		return spotSecurityReportDao.get(id);	}
	@Override	public void update(SpotSecurityReport spotSecurityReport){		spotSecurityReportDao.update(spotSecurityReport);	}
	@Override	public void delete(Integer id){		spotSecurityReportDao.delete(id);	}
	@Override	public PageVo<SpotSecurityReport> query(SpotSecurityReportRequest request) {		List<SpotSecurityReport> spotSecurityReport = queryList(request);		PageVo<SpotSecurityReport> pv = new PageVo<SpotSecurityReport>();		int count = queryCount(request);		pv.setData(spotSecurityReport);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(SpotSecurityReportRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return spotSecurityReportDao.queryCount(param);	}
	@Override	public List<SpotSecurityReport> queryList(SpotSecurityReportRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<SpotSecurityReport> list = spotSecurityReportDao.query(param);		return list;	}
}

