package com.ucap.cloud_web.service.impl;


import java.util.List;
import com.publics.util.page.PageVo;
import com.publics.util.utils.QueryUtils;


/**


	@Autowired





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
