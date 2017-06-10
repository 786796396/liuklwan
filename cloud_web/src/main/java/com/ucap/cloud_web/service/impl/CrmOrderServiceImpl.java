package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.CrmOrder;import com.ucap.cloud_web.service.ICrmOrderService;import com.ucap.cloud_web.dao.CrmOrderDao;import org.springframework.stereotype.Service;import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.CrmOrderRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>qingjy<br>* <b>日期：</b> 2017-03-08 11:52:34 <br>* <b>版权所有：<b>版权所有(C) 2017<br>*/@Servicepublic class CrmOrderServiceImpl implements ICrmOrderService {


	@Autowired	private CrmOrderDao crmOrderDao;	@Override	public int add(CrmOrder crmOrder){		return crmOrderDao.add(crmOrder);	}
	@Override	public CrmOrder get(Integer id){		return crmOrderDao.get(id);	}
	@Override	public void update(CrmOrder crmOrder){		crmOrderDao.update(crmOrder);	}
	@Override	public void delete(Integer id){		crmOrderDao.delete(id);	}
	@Override	public PageVo<CrmOrder> query(CrmOrderRequest request) {		List<CrmOrder> crmOrder = queryList(request);		PageVo<CrmOrder> pv = new PageVo<CrmOrder>();		int count = queryCount(request);		pv.setData(crmOrder);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(CrmOrderRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return crmOrderDao.queryCount(param);	}
	@Override	public List<CrmOrder> queryList(CrmOrderRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<CrmOrder> list = crmOrderDao.query(param);		return list;	}
}

