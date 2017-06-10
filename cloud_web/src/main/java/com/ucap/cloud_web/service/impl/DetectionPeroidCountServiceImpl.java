package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.DetectionPeroidCount;import com.ucap.cloud_web.service.IDetectionPeroidCountService;import com.ucap.cloud_web.dao.DetectionPeroidCountDao;import org.springframework.stereotype.Service;import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.DetectionPeroidCountRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>Na.Y<br>* <b>日期：</b> 2016-09-13 14:51:28 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@Servicepublic class DetectionPeroidCountServiceImpl implements IDetectionPeroidCountService {


	@Autowired	private DetectionPeroidCountDao detectionPeroidCountDao;	@Override	public int add(DetectionPeroidCount detectionPeroidCount){		return detectionPeroidCountDao.add(detectionPeroidCount);	}
	@Override	public DetectionPeroidCount get(Integer id){		return detectionPeroidCountDao.get(id);	}
	@Override	public void update(DetectionPeroidCount detectionPeroidCount){		detectionPeroidCountDao.update(detectionPeroidCount);	}
	@Override	public void delete(Integer id){		detectionPeroidCountDao.delete(id);	}
	@Override	public PageVo<DetectionPeroidCount> query(DetectionPeroidCountRequest request) {		List<DetectionPeroidCount> detectionPeroidCount = queryList(request);		PageVo<DetectionPeroidCount> pv = new PageVo<DetectionPeroidCount>();		int count = queryCount(request);		pv.setData(detectionPeroidCount);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(DetectionPeroidCountRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return detectionPeroidCountDao.queryCount(param);	}
	@Override	public List<DetectionPeroidCount> queryList(DetectionPeroidCountRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<DetectionPeroidCount> list = detectionPeroidCountDao.query(param);		return list;	}
}

