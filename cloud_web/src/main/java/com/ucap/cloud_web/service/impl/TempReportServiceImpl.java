package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.TempReport;import com.ucap.cloud_web.service.ITempReportService;import com.ucap.cloud_web.dao.TempReportDao;import org.springframework.stereotype.Service;import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.TempReportRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-09-13 11:30:36 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@Servicepublic class TempReportServiceImpl implements ITempReportService {


	@Autowired	private TempReportDao tempReportDao;	@Override	public int add(TempReport tempReport){		return tempReportDao.add(tempReport);	}
	@Override	public TempReport get(Integer id){		return tempReportDao.get(id);	}
	@Override	public void update(TempReport tempReport){		tempReportDao.update(tempReport);	}
	@Override	public void delete(Integer id){		tempReportDao.delete(id);	}
	@Override	public PageVo<TempReport> query(TempReportRequest request) {		List<TempReport> tempReport = queryList(request);		PageVo<TempReport> pv = new PageVo<TempReport>();		int count = queryCount(request);		pv.setData(tempReport);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(TempReportRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return tempReportDao.queryCount(param);	}
	@Override	public List<TempReport> queryList(TempReportRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<TempReport> list = tempReportDao.query(param);		return list;	}

	@Override
	public List<TempReport> joinLinkData(TempReportRequest tRequest) {
		Map<String, Object> param = QueryUtils.getQueryMap(tRequest);
		List<TempReport> list = tempReportDao.joinLinkData(param);
		return list;
	}

	
	@Override
	public PageVo<TempReport> queryLowerSubordinateUnits(
			TempReportRequest tRequest) {
		List<TempReport> tempReport = queryListSubordinateUnits(tRequest);

		PageVo<TempReport> pv = new PageVo<TempReport>();
		int count = queryCountLowerSubordinateUnits(tRequest);

		pv.setData(tempReport);
		pv.setPageNo(tRequest.getPageNo());
		pv.setPageSize(tRequest.getPageSize());
		pv.setRecordSize(count);
		return pv;
	}

	private List<TempReport> queryListSubordinateUnits(
			TempReportRequest tRequest) {
		Map<String, Object> param = QueryUtils.getQueryMap(tRequest);
		List<TempReport> list = tempReportDao.queryqueryListSubordinateUnits(param);
		return list;
	}

	private int queryCountLowerSubordinateUnits(TempReportRequest tRequest) {
		Map<String, Object> param = QueryUtils.getQueryMap(tRequest);
		return tempReportDao.queryCountLowerSubordinateUnits(param);
	}
	}

