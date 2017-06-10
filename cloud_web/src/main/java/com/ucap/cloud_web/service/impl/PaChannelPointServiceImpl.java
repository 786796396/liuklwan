package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.PaChannelPoint;import com.ucap.cloud_web.service.IPaChannelPointService;import com.ucap.cloud_web.dao.PaChannelPointDao;import org.springframework.stereotype.Service;import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.PaChannelPointRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-09-23 15:04:23 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@Servicepublic class PaChannelPointServiceImpl implements IPaChannelPointService {


	@Autowired	private PaChannelPointDao paChannelPointDao;	@Override	public int add(PaChannelPoint paChannelPoint){		return paChannelPointDao.add(paChannelPoint);	}
	@Override	public PaChannelPoint get(Integer id){		return paChannelPointDao.get(id);	}
	@Override	public void update(PaChannelPoint paChannelPoint){		paChannelPointDao.update(paChannelPoint);	}
	@Override	public void delete(Integer id){		paChannelPointDao.delete(id);	}
	@Override	public PageVo<PaChannelPoint> query(PaChannelPointRequest request) {		List<PaChannelPoint> paChannelPoint = queryList(request);		PageVo<PaChannelPoint> pv = new PageVo<PaChannelPoint>();		int count = queryCount(request);		pv.setData(paChannelPoint);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(PaChannelPointRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return paChannelPointDao.queryCount(param);	}
	@Override	public List<PaChannelPoint> queryList(PaChannelPointRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<PaChannelPoint> list = paChannelPointDao.query(param);		return list;	}
}

