package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.PaAppraisal;import com.ucap.cloud_web.service.IPaAppraisalService;import com.ucap.cloud_web.dao.PaAppraisalDao;import org.springframework.stereotype.Service;import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.PaAppraisalRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-08-23 10:59:22 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@Servicepublic class PaAppraisalServiceImpl implements IPaAppraisalService {


	@Autowired	private PaAppraisalDao paAppraisalDao;	@Override	public void add(PaAppraisal paAppraisal){		paAppraisalDao.add(paAppraisal);	}
	@Override	public PaAppraisal get(Integer id){		return paAppraisalDao.get(id);	}
	@Override	public void update(PaAppraisal paAppraisal){		paAppraisalDao.update(paAppraisal);	}
	@Override	public void delete(Integer id){		paAppraisalDao.delete(id);	}
	@Override	public PageVo<PaAppraisal> query(PaAppraisalRequest request) {		List<PaAppraisal> paAppraisal = queryList(request);		PageVo<PaAppraisal> pv = new PageVo<PaAppraisal>();		int count = queryCount(request);		pv.setData(paAppraisal);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(PaAppraisalRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return paAppraisalDao.queryCount(param);	}
	@Override	public List<PaAppraisal> queryList(PaAppraisalRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<PaAppraisal> list = paAppraisalDao.query(param);		return list;	}
}

