package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.PaAdviceDetail;import com.ucap.cloud_web.service.IPaAdviceDetailService;import com.ucap.cloud_web.dao.PaAdviceDetailDao;import org.springframework.stereotype.Service;import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.PaAdviceDetailRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-08-23 10:59:22 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@Servicepublic class PaAdviceDetailServiceImpl implements IPaAdviceDetailService {


	@Autowired	private PaAdviceDetailDao paAdviceDetailDao;	@Override	public void add(PaAdviceDetail paAdviceDetail){		paAdviceDetailDao.add(paAdviceDetail);	}
	@Override	public PaAdviceDetail get(Integer id){		return paAdviceDetailDao.get(id);	}
	@Override	public void update(PaAdviceDetail paAdviceDetail){		paAdviceDetailDao.update(paAdviceDetail);	}
	@Override	public void delete(Integer id){		paAdviceDetailDao.delete(id);	}
	@Override	public PageVo<PaAdviceDetail> query(PaAdviceDetailRequest request) {		List<PaAdviceDetail> paAdviceDetail = queryList(request);		PageVo<PaAdviceDetail> pv = new PageVo<PaAdviceDetail>();		int count = queryCount(request);		pv.setData(paAdviceDetail);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(PaAdviceDetailRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return paAdviceDetailDao.queryCount(param);	}
	@Override	public List<PaAdviceDetail> queryList(PaAdviceDetailRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<PaAdviceDetail> list = paAdviceDetailDao.query(param);		return list;	}
}

