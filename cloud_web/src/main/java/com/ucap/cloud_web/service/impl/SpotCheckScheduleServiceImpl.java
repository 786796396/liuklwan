package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.SpotCheckSchedule;import com.ucap.cloud_web.service.ISpotCheckScheduleService;import com.ucap.cloud_web.dao.SpotCheckScheduleDao;import org.springframework.stereotype.Service;import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.SpotCheckScheduleRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>yuangw@ucap.com.cn<br>* <b>日期：</b> 2016-04-19 11:16:49 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@Servicepublic class SpotCheckScheduleServiceImpl implements ISpotCheckScheduleService {


	@Autowired	private SpotCheckScheduleDao spotCheckScheduleDao;	@Override	public void add(SpotCheckSchedule spotCheckSchedule){		spotCheckScheduleDao.add(spotCheckSchedule);	}
	@Override	public SpotCheckSchedule get(Integer id){		return spotCheckScheduleDao.get(id);	}
	@Override	public void update(SpotCheckSchedule spotCheckSchedule){		spotCheckScheduleDao.update(spotCheckSchedule);	}
	@Override	public void delete(Integer id){		spotCheckScheduleDao.delete(id);	}
	@Override	public PageVo<SpotCheckSchedule> query(SpotCheckScheduleRequest request) {		List<SpotCheckSchedule> spotCheckSchedule = queryList(request);		PageVo<SpotCheckSchedule> pv = new PageVo<SpotCheckSchedule>();		int count = queryCount(request);		pv.setData(spotCheckSchedule);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(SpotCheckScheduleRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return spotCheckScheduleDao.queryCount(param);	}
	@Override	public List<SpotCheckSchedule> queryList(SpotCheckScheduleRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<SpotCheckSchedule> list = spotCheckScheduleDao.query(param);		return list;	}
	@Override
	public List<SpotCheckSchedule> queryBatch(SpotCheckScheduleRequest request) {
		Map<String, Object> param = QueryUtils.getQueryMap(request);
		List<SpotCheckSchedule> list = spotCheckScheduleDao.queryBatch(param);
		return list;
	}
	@Override
	public List<SpotCheckSchedule> spotCheckReportUpList(
			SpotCheckScheduleRequest request) {
		// TODO Auto-generated method stub
		Map<String, Object> param = QueryUtils.getQueryMap(request);
		List<SpotCheckSchedule> list = spotCheckScheduleDao.spotCheckReportUpList(param);
		return list;
	}}

