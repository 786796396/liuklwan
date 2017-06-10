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


/*** <br>* <b>作者：</b>ccx<br>* <b>日期：</b> 2016-03-04 20:20:38 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@Servicepublic class ContractInfoServiceImpl implements IContractInfoService {


	@Autowired	private ContractInfoDao contractInfoDao;	@Override	public void add(ContractInfo contractInfo){		contractInfoDao.add(contractInfo);	}
	@Override	public ContractInfo get(Integer id){		return contractInfoDao.get(id);	}
	@Override	public void update(ContractInfo contractInfo){		contractInfoDao.update(contractInfo);	}
	@Override	public void delete(Integer id){		contractInfoDao.delete(id);	}
	@Override	public PageVo<ContractInfo> query(ContractInfoRequest request) {		List<ContractInfo> contractInfo = queryList(request);		PageVo<ContractInfo> pv = new PageVo<ContractInfo>();		int count = queryCount(request);		pv.setData(contractInfo);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(ContractInfoRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return contractInfoDao.queryCount(param);	}
	@Override	public List<ContractInfo> queryList(ContractInfoRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);
		if(!CollectionUtils.isEmpty(request.getIdsOrgSiteCode())){
			param.put("idsOrgSiteCode", request.getIdsOrgSiteCode());
		}
		if(request.getExecuteStatusArr()!=null && request.getExecuteStatusArr().length>0){
			param.put("executeStatusArr", request.getExecuteStatusArr());
		}
		if(request.getDatabaseTreeList()!=null && request.getDatabaseTreeList().size()>0){
			param.put("databaseTreeList", request.getDatabaseTreeList());
		}		List<ContractInfo> list = contractInfoDao.query(param);		return list;	}

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
}

