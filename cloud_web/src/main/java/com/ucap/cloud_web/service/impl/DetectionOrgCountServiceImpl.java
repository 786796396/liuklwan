package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.DetectionOrgCount;import com.ucap.cloud_web.service.IDetectionOrgCountService;import com.ucap.cloud_web.dao.DetectionOrgCountDao;import org.springframework.stereotype.Service;import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.DetectionOrgCountRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>liujc<br>* <b>日期：</b> 2016-05-20 11:36:17 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@Servicepublic class DetectionOrgCountServiceImpl implements IDetectionOrgCountService {


	@Autowired	private DetectionOrgCountDao detectionOrgCountDao;	@Override	public void add(DetectionOrgCount detectionOrgCount){		detectionOrgCountDao.add(detectionOrgCount);	}
	@Override	public DetectionOrgCount get(Integer id){		return detectionOrgCountDao.get(id);	}
	@Override	public void update(DetectionOrgCount detectionOrgCount){		detectionOrgCountDao.update(detectionOrgCount);	}
	@Override	public void delete(Integer id){		detectionOrgCountDao.delete(id);	}
	@Override	public PageVo<DetectionOrgCount> query(DetectionOrgCountRequest request) {		List<DetectionOrgCount> detectionOrgCount = queryList(request);		PageVo<DetectionOrgCount> pv = new PageVo<DetectionOrgCount>();		int count = queryCount(request);		pv.setData(detectionOrgCount);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(DetectionOrgCountRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return detectionOrgCountDao.queryCount(param);	}
	@Override	public List<DetectionOrgCount> queryList(DetectionOrgCountRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<DetectionOrgCount> list = detectionOrgCountDao.query(param);		return list;	}
}

