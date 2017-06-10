package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.SpotCheckResult;import com.ucap.cloud_web.service.ISpotCheckResultService;import com.ucap.cloud_web.dao.SpotCheckResultDao;import org.springframework.stereotype.Service;import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.SpotCheckResultRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>yuangw@ucap.com.cn<br>* <b>日期：</b> 2016-04-19 11:16:29 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@Servicepublic class SpotCheckResultServiceImpl implements ISpotCheckResultService {


	@Autowired	private SpotCheckResultDao spotCheckResultDao;	@Override	public void add(SpotCheckResult spotCheckResult){		spotCheckResultDao.add(spotCheckResult);	}
	@Override	public SpotCheckResult get(Integer id){		return spotCheckResultDao.get(id);	}
	@Override	public void update(SpotCheckResult spotCheckResult){		spotCheckResultDao.update(spotCheckResult);	}
	@Override	public void delete(Integer id){		spotCheckResultDao.delete(id);	}
	@Override	public PageVo<SpotCheckResult> query(SpotCheckResultRequest request) {		List<SpotCheckResult> spotCheckResult = queryList(request);		PageVo<SpotCheckResult> pv = new PageVo<SpotCheckResult>();		int count = queryCount(request);		pv.setData(spotCheckResult);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(SpotCheckResultRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return spotCheckResultDao.queryCount(param);	}
	@Override	public List<SpotCheckResult> queryList(SpotCheckResultRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);
		if(request.getSiteCodes()!=null && request.getSiteCodes().length>0){
			param.put("siteCodes", request.getSiteCodes());
		}		List<SpotCheckResult> list = spotCheckResultDao.query(param);		return list;	}

	@Override
	public List<SpotCheckResultRequest> queryByMap(Map<String, Object> params) {
		return spotCheckResultDao.queryByMap(params);
	}

	@Override
	public void deleteBatchBySchedule(int spotCheckSchedule) {
		spotCheckResultDao.deleteBatchBySchedule(spotCheckSchedule);
		
	}
}

