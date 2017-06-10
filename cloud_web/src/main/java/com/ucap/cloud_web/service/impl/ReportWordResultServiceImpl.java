package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.ReportWordResult;import com.ucap.cloud_web.service.IReportWordResultService;import com.ucap.cloud_web.dao.ReportWordResultDao;import org.springframework.stereotype.Service;
import com.publics.util.page.PageVo;
import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.ReportWordResultRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2015-10-29 11:11:46 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/@Servicepublic class ReportWordResultServiceImpl implements IReportWordResultService {


	@Autowired	private ReportWordResultDao reportWordResultDao;	@Override	public void add(ReportWordResult reportWordResult){		reportWordResultDao.add(reportWordResult);	}
	@Override	public ReportWordResult get(Integer id){		return reportWordResultDao.get(id);	}
	@Override	public void update(ReportWordResult reportWordResult){		reportWordResultDao.update(reportWordResult);	}
	@Override	public void delete(Integer id){		reportWordResultDao.delete(id);	}
	@Override	public List<ReportWordResult> queryList(ReportWordResultRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<ReportWordResult> list = reportWordResultDao.query(param);		return list;	}

	@Override
	public List<ReportWordResult> queryByMap(Map<String, Object> paramMap) {
		return reportWordResultDao.queryByMap(paramMap);
	}
	
	@Override
	public List<ReportWordResult> queryByMapWord(Map<String, Object> paramMap) {
		return reportWordResultDao.queryByMapWord(paramMap);
	}
	
	@Override
	public List<ReportWordResult> findSiteByMap(Map<String, Object> paramMap) {
		return reportWordResultDao.findSiteByMap(paramMap);
	}

	@Override
	public ReportWordResult findBySitCode(String siteCode) {
		return reportWordResultDao.findBySitCode(siteCode);
	}
	
	@Override
	public PageVo<ReportWordResult> queryRectificationNotice(
			ReportWordResultRequest wordRequest) {
		// TODO Auto-generated method stub
		Map<String, Object> paramMap = QueryUtils.getQueryMap(wordRequest);
		if(wordRequest.getList()!=null&&wordRequest.getList().size()>1){
			paramMap.put("list", wordRequest.getList());
		}
		List<ReportWordResult> reportWordList = reportWordResultDao.queryByMapWord(paramMap);
		PageVo<ReportWordResult> pv = new PageVo<ReportWordResult>();
		int count = queryByMapWordList(wordRequest);
		pv.setData(reportWordList);
		pv.setPageNo(wordRequest.getPageNo());
		pv.setPageSize(wordRequest.getPageSize());
		pv.setRecordSize(count);
		return pv;
	}

	private int queryByMapWordList(ReportWordResultRequest wordRequest) {
		// TODO Auto-generated method stub
		Map<String, Object> param = QueryUtils.getQueryMap(wordRequest);
		if(wordRequest.getList()!=null&&wordRequest.getList().size()>1){
			param.put("list", wordRequest.getList());
		}
		return reportWordResultDao.queryByMapWordCount(param);
	}
}

