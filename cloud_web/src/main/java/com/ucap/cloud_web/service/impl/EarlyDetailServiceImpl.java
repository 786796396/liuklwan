package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.EarlyDetail;import com.ucap.cloud_web.service.IEarlyDetailService;import com.ucap.cloud_web.dao.EarlyDetailDao;import org.springframework.stereotype.Service;import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.EarlyDetailRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2015-10-29 11:05:04 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/@Servicepublic class EarlyDetailServiceImpl implements IEarlyDetailService {


	@Autowired	private EarlyDetailDao earlyDetailDao;	@Override	public void add(EarlyDetail earlyDetail){		earlyDetailDao.add(earlyDetail);	}
	@Override	public EarlyDetail get(Integer id){		return earlyDetailDao.get(id);	}
	@Override	public void update(EarlyDetail earlyDetail){		earlyDetailDao.update(earlyDetail);	}
	@Override	public void delete(Integer id){		earlyDetailDao.delete(id);	}
	@Override	public PageVo<EarlyDetail> query(EarlyDetailRequest request) {		List<EarlyDetail> earlyDetail = queryList(request);		PageVo<EarlyDetail> pv = new PageVo<EarlyDetail>();		int count = queryCount(request);				pv.setData(earlyDetail);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(EarlyDetailRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);
		if(request.getWebsiteList()!=null && request.getWebsiteList().size()>0 ){
			param.put("websiteList", request.getWebsiteList());
		}
		if(request.getLinkList()!=null && request.getLinkList().size()>0){
			param.put("linkList", request.getLinkList());
		}
		return earlyDetailDao.queryCount(param);	}
	@Override	public List<EarlyDetail> queryList(EarlyDetailRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);
		if(request.getWebsiteList()!=null && request.getWebsiteList().size()>0 ){
			param.put("websiteList", request.getWebsiteList());
		}
		if(request.getLinkList()!=null && request.getLinkList().size()>0){
			param.put("linkList", request.getLinkList());
		}		List<EarlyDetail> list = earlyDetailDao.query(param);		return list;	}

	@Override
	public List<EarlyDetailRequest> queryByMap(Map<String, Object> paramMap) {
		return earlyDetailDao.queryByMap(paramMap);
	}
	@Override
	public List<EarlyDetail> queryDailyInfo(EarlyDetailRequest request) {
		Map<String, Object> param = QueryUtils.getQueryMap(request);

		List<EarlyDetail> list = earlyDetailDao.queryDailyInfo(param);
		return list;
	}

	@Override
	public List<EarlyDetail> querySiteEarlyInfo(EarlyDetailRequest request) {
		// TODO Auto-generated method stub
		Map<String, Object> param = QueryUtils.getQueryMap(request);
		return earlyDetailDao.querySiteEarlyInfo(param);
	}

	@Override
	public List<EarlyDetail> queryDatas(EarlyDetailRequest request) {
		Map<String, Object> param = QueryUtils.getQueryMap(request);
		return earlyDetailDao.queryDatas(param);
	}

	@Override
	public List<EarlyDetail> queryNoConDatas(EarlyDetailRequest eRequest) {
		Map<String, Object> param = QueryUtils.getQueryMap(eRequest);
		return earlyDetailDao.queryNoConDatas(param);
	}
}

