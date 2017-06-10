package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.ReportManageLog;import com.ucap.cloud_web.service.IReportManageLogService;import com.ucap.cloud_web.dao.ReportManageLogDao;import org.springframework.stereotype.Service;import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.ReportManageLogRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2015-10-29 11:11:37 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/@Servicepublic class ReportManageLogServiceImpl implements IReportManageLogService {


	@Autowired	private ReportManageLogDao reportManageLogDao;	@Override	public void add(ReportManageLog reportManageLog){		reportManageLogDao.add(reportManageLog);	}
	@Override	public ReportManageLog get(Integer id){		return reportManageLogDao.get(id);	}
	@Override	public void update(ReportManageLog reportManageLog){		reportManageLogDao.update(reportManageLog);	}
	@Override	public void delete(Integer id){		reportManageLogDao.delete(id);	}
	@Override	public PageVo<ReportManageLog> query(ReportManageLogRequest request) {		List<ReportManageLog> reportManageLog = queryList(request);		PageVo<ReportManageLog> pv = new PageVo<ReportManageLog>();		int count = queryCount(request);		pv.setData(reportManageLog);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(ReportManageLogRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return reportManageLogDao.queryCount(param);	}
	@Override	public List<ReportManageLog> queryList(ReportManageLogRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);
		if(request.getCurrentNextSiteCode()!=null && request.getCurrentNextSiteCode().size()>0){
			param.put("currentNextSiteCode", request.getCurrentNextSiteCode());
		}
		List<ReportManageLog> list = reportManageLogDao.query(param);		return list;	}

	@Override
	public List<ReportManageLogRequest> queryByMap(Map<String, Object> paramMap) {
		return reportManageLogDao.queryByMap(paramMap);
	}

	@Override
	public ReportManageLogRequest querySum(Map<String, Object> param) {
		return reportManageLogDao.querySum(param);
	}

	@Override
	public List<ReportManageLogRequest> queryListByMap(Map<String, Object> map) {
		return reportManageLogDao.queryListByMap(map);
	}


}

