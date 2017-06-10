package com.ucap.cloud_web.service.impl;


import java.util.HashMap;
import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.DetectionPeriodSite;import com.ucap.cloud_web.service.IDetectionPeriodSiteService;import com.ucap.cloud_web.dao.DetectionPeriodSiteDao;import org.springframework.stereotype.Service;import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.DetectionPeriodSiteRequest;
import com.ucap.cloud_web.dtoResponse.SecurityGuaranteeResponse;

import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2017-03-24 10:15:11 <br>* <b>版权所有：<b>版权所有(C) 2017<br>*/@Servicepublic class DetectionPeriodSiteServiceImpl implements IDetectionPeriodSiteService {


	@Autowired	private DetectionPeriodSiteDao detectionPeriodSiteDao;	@Override	public int add(DetectionPeriodSite detectionPeriodSite){		return detectionPeriodSiteDao.add(detectionPeriodSite);	}
	@Override	public DetectionPeriodSite get(Integer id){		return detectionPeriodSiteDao.get(id);	}
	@Override	public void update(DetectionPeriodSite detectionPeriodSite){		detectionPeriodSiteDao.update(detectionPeriodSite);	}
	@Override	public void delete(Integer id){		detectionPeriodSiteDao.delete(id);	}
	@Override	public PageVo<DetectionPeriodSite> query(DetectionPeriodSiteRequest request) {		List<DetectionPeriodSite> detectionPeriodSite = queryList(request);		PageVo<DetectionPeriodSite> pv = new PageVo<DetectionPeriodSite>();		int count = queryCount(request);		pv.setData(detectionPeriodSite);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(DetectionPeriodSiteRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return detectionPeriodSiteDao.queryCount(param);	}
	@Override	public List<DetectionPeriodSite> queryList(DetectionPeriodSiteRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<DetectionPeriodSite> list = detectionPeriodSiteDao.query(param);		return list;	}
}

