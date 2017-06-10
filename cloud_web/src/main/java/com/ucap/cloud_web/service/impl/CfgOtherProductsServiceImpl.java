package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.CfgOtherProducts;import com.ucap.cloud_web.service.ICfgOtherProductsService;import com.ucap.cloud_web.dao.CfgOtherProductsDao;import org.springframework.stereotype.Service;import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.CfgOtherProductsRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>qingjy<br>* <b>日期：</b> 2017-01-03 13:38:10 <br>* <b>版权所有：<b>版权所有(C) 2017<br>*/@Servicepublic class CfgOtherProductsServiceImpl implements ICfgOtherProductsService {


	@Autowired	private CfgOtherProductsDao cfgOtherProductsDao;	@Override	public int add(CfgOtherProducts cfgOtherProducts){		return cfgOtherProductsDao.add(cfgOtherProducts);	}
	@Override	public CfgOtherProducts get(Integer id){		return cfgOtherProductsDao.get(id);	}
	@Override	public void update(CfgOtherProducts cfgOtherProducts){		cfgOtherProductsDao.update(cfgOtherProducts);	}
	@Override	public void delete(Integer id){		cfgOtherProductsDao.delete(id);	}
	@Override	public PageVo<CfgOtherProducts> query(CfgOtherProductsRequest request) {		List<CfgOtherProducts> cfgOtherProducts = queryList(request);		PageVo<CfgOtherProducts> pv = new PageVo<CfgOtherProducts>();		int count = queryCount(request);		pv.setData(cfgOtherProducts);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(CfgOtherProductsRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return cfgOtherProductsDao.queryCount(param);	}
	@Override	public List<CfgOtherProducts> queryList(CfgOtherProductsRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<CfgOtherProducts> list = cfgOtherProductsDao.query(param);		return list;	}
}

