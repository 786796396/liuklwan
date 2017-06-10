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


/**


	@Autowired






		if (request.getExecuteStatusArr() != null) {
			param.put("executeStatusArr", request.getExecuteStatusArr());
		}
			param.put("productTypeArr", request.getProductTypeArr());
		}

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

