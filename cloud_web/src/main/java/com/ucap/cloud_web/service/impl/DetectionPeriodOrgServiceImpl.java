package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.DetectionPeriodOrg;import com.ucap.cloud_web.service.IDetectionPeriodOrgService;import com.ucap.cloud_web.dao.DetectionPeriodOrgDao;import org.springframework.stereotype.Service;import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.DetectionPeriodOrgRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2017-03-24 10:17:35 <br>* <b>版权所有：<b>版权所有(C) 2017<br>*/@Servicepublic class DetectionPeriodOrgServiceImpl implements IDetectionPeriodOrgService {


	@Autowired	private DetectionPeriodOrgDao detectionPeriodOrgDao;	@Override	public int add(DetectionPeriodOrg detectionPeriodOrg){		return detectionPeriodOrgDao.add(detectionPeriodOrg);	}
	@Override	public DetectionPeriodOrg get(Integer id){		return detectionPeriodOrgDao.get(id);	}
	@Override	public void update(DetectionPeriodOrg detectionPeriodOrg){		detectionPeriodOrgDao.update(detectionPeriodOrg);	}
	@Override	public void delete(Integer id){		detectionPeriodOrgDao.delete(id);	}
	@Override	public PageVo<DetectionPeriodOrg> query(DetectionPeriodOrgRequest request) {		List<DetectionPeriodOrg> detectionPeriodOrg = queryList(request);		PageVo<DetectionPeriodOrg> pv = new PageVo<DetectionPeriodOrg>();		int count = queryCount(request);		pv.setData(detectionPeriodOrg);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(DetectionPeriodOrgRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return detectionPeriodOrgDao.queryCount(param);	}
	@Override	public List<DetectionPeriodOrg> queryList(DetectionPeriodOrgRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<DetectionPeriodOrg> list = detectionPeriodOrgDao.query(param);		return list;	}
}

