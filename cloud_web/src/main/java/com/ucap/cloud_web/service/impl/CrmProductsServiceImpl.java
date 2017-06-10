package com.ucap.cloud_web.service.impl;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.publics.util.page.PageVo;
import com.publics.util.utils.QueryUtils;
import com.ucap.cloud_web.dao.CrmProductsDao;
import com.ucap.cloud_web.dto.CrmProductsRequest;
import com.ucap.cloud_web.dtoResponse.CrmProductsResponse;
import com.ucap.cloud_web.entity.CrmProducts;
import com.ucap.cloud_web.service.ICrmProductsService;


/*** <br>* <b>作者：</b>qingjy<br>* <b>日期：</b> 2017-03-08 11:53:59 <br>* <b>版权所有：<b>版权所有(C) 2017<br>*/@Servicepublic class CrmProductsServiceImpl implements ICrmProductsService {


	@Autowired	private CrmProductsDao crmProductsDao;	@Override	public int add(CrmProducts crmProducts){		return crmProductsDao.add(crmProducts);	}
	@Override	public CrmProducts get(Integer id){		return crmProductsDao.get(id);	}
	@Override	public void update(CrmProducts crmProducts){		crmProductsDao.update(crmProducts);	}
	@Override	public void delete(Integer id){		crmProductsDao.delete(id);	}
	@Override	public PageVo<CrmProducts> query(CrmProductsRequest request) {		List<CrmProducts> crmProducts = queryList(request);		PageVo<CrmProducts> pv = new PageVo<CrmProducts>();		int count = queryCount(request);		pv.setData(crmProducts);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(CrmProductsRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return crmProductsDao.queryCount(param);	}
	@Override	public List<CrmProducts> queryList(CrmProductsRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);
		if (request.getExecuteStatusArr() != null) {
			param.put("executeStatusArr", request.getExecuteStatusArr());
		}		if (request.getProductTypeArr() != null) {
			param.put("productTypeArr", request.getProductTypeArr());
		}		List<CrmProducts> list = crmProductsDao.query(param);		return list;	}

	@Override
	public List<CrmProductsResponse> getCrmProductsList(CrmProductsRequest request) {
		return crmProductsDao.getCrmProductsList(request);
	}

	@Override
	public List<CrmProductsResponse> getSecurityProducts(CrmProductsRequest crmSeReq) {
		return crmProductsDao.getSecurityProducts(crmSeReq);
	}

	@Override
	public List<CrmProductsResponse> getTaskCrmProductsList(CrmProductsRequest crmReq) {
		return crmProductsDao.getTaskCrmProductsList(crmReq);
	}

	@Override
	public List<CrmProductsResponse> getOnCrmProductsList(CrmProductsRequest crmReq) {
		return crmProductsDao.getOnCrmProductsList(crmReq);
	}

	@Override
	public List<CrmProductsResponse> historyCrmProductsList(CrmProductsRequest crmReq) {
		return crmProductsDao.historyCrmProductsList(crmReq);
	}

	@Override
	public List<Integer> queryByMap(Map<String, Object> map) {
		return crmProductsDao.queryByMap(map);
	}
}

