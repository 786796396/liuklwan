package com.ucap.cloud_web.service.impl;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.publics.util.page.PageVo;
import com.publics.util.utils.QueryUtils;
import com.ucap.cloud_web.dao.ContractInfoDao;
import com.ucap.cloud_web.dto.ContractInfoRequest;
import com.ucap.cloud_web.entity.ContractInfo;
import com.ucap.cloud_web.service.IContractInfoService;


/**


	@Autowired






		if(!CollectionUtils.isEmpty(request.getIdsOrgSiteCode())){
			param.put("idsOrgSiteCode", request.getIdsOrgSiteCode());
		}
		if(request.getExecuteStatusArr()!=null && request.getExecuteStatusArr().length>0){
			param.put("executeStatusArr", request.getExecuteStatusArr());
		}
		if(request.getDatabaseTreeList()!=null && request.getDatabaseTreeList().size()>0){
			param.put("databaseTreeList", request.getDatabaseTreeList());
		}

	@Override
	public List<ContractInfo> queryContractByMap(Map<String, Object> map) {
		return contractInfoDao.queryContractByMap(map);
	}

	@Override
	public List<ContractInfoRequest> queryListByMap(Map<String, Object> paramMap) {
		return contractInfoDao.queryListByMap(paramMap);
	}

	@Override
	public List<ContractInfo> queryByParentCon(ContractInfoRequest conRequest2) {
		Map<String, Object> param = QueryUtils.getQueryMap(conRequest2);
		if(conRequest2.getExecuteStatusArr()!=null && conRequest2.getExecuteStatusArr().length>0){
			param.put("executeStatusArr", conRequest2.getExecuteStatusArr());
		}
		return contractInfoDao.queryByParentCon(param);
	}

	@Override
	public List<ContractInfo> queryOrgContractBySite(Map<String, Object> map) {
		return contractInfoDao.queryOrgContractBySite(map);
	}

