package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.ReportCollectResult;import com.ucap.cloud_web.service.IReportCollectResultService;import com.ucap.cloud_web.dao.ReportCollectResultDao;import org.springframework.stereotype.Service;import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.ReportCollectResultRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>qingjy<br>* <b>日期：</b> 2016-11-24 21:30:34 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@Servicepublic class ReportCollectResultServiceImpl implements IReportCollectResultService {


	@Autowired	private ReportCollectResultDao reportCollectResultDao;	@Override	public int add(ReportCollectResult reportCollectResult){		return reportCollectResultDao.add(reportCollectResult);	}
	@Override	public ReportCollectResult get(Integer id){		return reportCollectResultDao.get(id);	}
	@Override	public void update(ReportCollectResult reportCollectResult){		reportCollectResultDao.update(reportCollectResult);	}
	@Override	public void delete(Integer id){		reportCollectResultDao.delete(id);	}
	@Override	public PageVo<ReportCollectResult> query(ReportCollectResultRequest request) {		List<ReportCollectResult> reportCollectResult = queryList(request);		PageVo<ReportCollectResult> pv = new PageVo<ReportCollectResult>();		int count = queryCount(request);		pv.setData(reportCollectResult);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(ReportCollectResultRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return reportCollectResultDao.queryCount(param);	}
	@Override	public List<ReportCollectResult> queryList(ReportCollectResultRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<ReportCollectResult> list = reportCollectResultDao.query(param);		return list;	}
}

