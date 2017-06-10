package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.PaTarget;import com.ucap.cloud_web.service.IPaTargetService;import com.ucap.cloud_web.dao.PaTargetDao;import org.springframework.stereotype.Service;import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.PaTargetRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-08-24 13:38:42 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@Servicepublic class PaTargetServiceImpl implements IPaTargetService {


	@Autowired	private PaTargetDao paTargetDao;	@Override	public void add(PaTarget paTarget){		paTargetDao.add(paTarget);	}
	@Override	public PaTarget get(Integer id){		return paTargetDao.get(id);	}
	@Override	public void update(PaTarget paTarget){		paTargetDao.update(paTarget);	}
	@Override	public void delete(Integer id){		paTargetDao.delete(id);	}
	@Override	public PageVo<PaTarget> query(PaTargetRequest request) {		List<PaTarget> paTarget = queryList(request);		PageVo<PaTarget> pv = new PageVo<PaTarget>();		int count = queryCount(request);		pv.setData(paTarget);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(PaTargetRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return paTargetDao.queryCount(param);	}
	@Override	public List<PaTarget> queryList(PaTargetRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<PaTarget> list = paTargetDao.query(param);		return list;	}

	@Override
	public List<PaTarget> queryByIds(PaTargetRequest paTargetRequest) {
		Map<String, Object> param = QueryUtils.getQueryMap(paTargetRequest);
		return paTargetDao.queryByIdss(param);
	}
}

