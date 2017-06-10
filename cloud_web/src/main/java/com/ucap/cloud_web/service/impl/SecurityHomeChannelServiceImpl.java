package com.ucap.cloud_web.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.publics.util.page.PageVo;
import com.publics.util.utils.QueryUtils;
import com.ucap.cloud_web.dao.SecurityHomeChannelDao;
import com.ucap.cloud_web.dto.SecurityHomeChannelRequest;
import com.ucap.cloud_web.entity.SecurityHomeChannel;
import com.ucap.cloud_web.service.ISecurityHomeChannelService;


/*** <br>* <b>作者：</b>SunJiang<br>* <b>日期：</b> 2015-12-03 15:22:56 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/@Servicepublic class SecurityHomeChannelServiceImpl implements ISecurityHomeChannelService {


	@Autowired	private SecurityHomeChannelDao securityHomeChannelDao;	@Override	public void add(SecurityHomeChannel securityHomeChannel){		securityHomeChannelDao.add(securityHomeChannel);	}
	@Override	public SecurityHomeChannel get(Integer id){		return securityHomeChannelDao.get(id);	}
	@Override	public void update(SecurityHomeChannel securityHomeChannel){		securityHomeChannelDao.update(securityHomeChannel);	}
	@Override	public void delete(Integer id){		securityHomeChannelDao.delete(id);	}
	@Override	public PageVo<SecurityHomeChannel> query(SecurityHomeChannelRequest request) {		List<SecurityHomeChannel> securityHomeChannel = queryList(request);		PageVo<SecurityHomeChannel> pv = new PageVo<SecurityHomeChannel>();		int count = queryCount(request);		pv.setData(securityHomeChannel);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(SecurityHomeChannelRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);
		param.put("channelTypes", request.getChannelTypes());
		if(request.getDatabaseList()!=null && request.getDatabaseList().size()>0){
			param.put("databaseList", request.getDatabaseList());
		}		return securityHomeChannelDao.queryCount(param);	}
	@Override	public List<SecurityHomeChannel> queryList(SecurityHomeChannelRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);
		param.put("channelTypes", request.getChannelTypes());
		
		if(request.getDatabaseList()!=null && request.getDatabaseList().size()>0){
			param.put("databaseList", request.getDatabaseList());
		}
		if(request.getDatabaseLinkList()!=null && request.getDatabaseLinkList().size()>0){
			param.put("databaseLinkList", request.getDatabaseLinkList());
		}		List<SecurityHomeChannel> list = securityHomeChannelDao.query(param);		return list;	}
	@Override
	public List<SecurityHomeChannel> queryListTb(SecurityHomeChannelRequest request) {
		Map<String, Object> param = QueryUtils.getQueryMap(request);
		param.put("channelTypes", request.getChannelTypes());
		
		if(request.getDatabaseList()!=null && request.getDatabaseList().size()>0){
			param.put("databaseList", request.getDatabaseList());
		}
		if(request.getDatabaseLinkList()!=null && request.getDatabaseLinkList().size()>0){
			param.put("databaseLinkList", request.getDatabaseLinkList());
		}
		List<SecurityHomeChannel> list = securityHomeChannelDao.queryTb(param);
		return list;
	}
	@Override
	public List<SecurityHomeChannelRequest> getLineList(HashMap<String, Object> map) {
		return securityHomeChannelDao.getLineList(map);
	}
	@Override
	public List<SecurityHomeChannelRequest> getUnUpdateDays(Map<String, Object> map) {
		return securityHomeChannelDao.getUnUpdateDays(map);
	}
	@Override
	public List<SecurityHomeChannelRequest> getUNUpdateSum(Map<String, Object> map) {
		return securityHomeChannelDao.getUNUpdateSum(map);
	}

	@Override
	public List<SecurityHomeChannelRequest> queryByMap(
			Map<String, Object> paramMap) {
		return securityHomeChannelDao.queryByMap(paramMap);
	}

	@Override
	public List<SecurityHomeChannelRequest> queryCountByMap(
			Map<String, Object> paramMap) {
		
		return securityHomeChannelDao.queryCountByMap(paramMap);
	}

	@Override
	public int queryCountByType(Map<String, Object> paramMap) {
		return securityHomeChannelDao.queryCountByType(paramMap);
	}

	@Override
	public List<SecurityHomeChannel> queryByTypeDeail(
			Map<String, Object> paramMap) {
		return securityHomeChannelDao.queryByTypeDeail(paramMap);
	}

	@Override
	public PageVo<SecurityHomeChannel> queryReport(
			SecurityHomeChannelRequest request) {

		Map<String, Object> param = QueryUtils.getQueryMap(request);
		param.put("channelTypes", request.getChannelTypes());
		if(request.getDatabaseList()!=null && request.getDatabaseList().size()>0){
			param.put("databaseList", request.getDatabaseList());
		}
		List<SecurityHomeChannel> securityHomeChannel = securityHomeChannelDao.queryReport(param);
	
//		List<SecurityHomeChannel> securityHomeChannel = queryList(request);

		PageVo<SecurityHomeChannel> pv = new PageVo<SecurityHomeChannel>();
		int count = queryReportCount(request);

		pv.setData(securityHomeChannel);
		pv.setPageNo(request.getPageNo());
		pv.setPageSize(request.getPageSize());
		pv.setRecordSize(count);
		return pv;
	}
	
	@Override
	public List<SecurityHomeChannel> queryReportList(SecurityHomeChannelRequest request){
		Map<String, Object> param = QueryUtils.getQueryMap(request);
		param.put("channelTypes", request.getChannelTypes());
		if(request.getDatabaseList()!=null && request.getDatabaseList().size()>0){
			param.put("databaseList", request.getDatabaseList());
		}
		List<SecurityHomeChannel> securityHomeChannel = securityHomeChannelDao.queryReport(param);
		return securityHomeChannel;
	}
	@Override
	public int queryReportCount(SecurityHomeChannelRequest request) {
		Map<String, Object> param = QueryUtils.getQueryMap(request);
		param.put("channelTypes", request.getChannelTypes());
		if(request.getDatabaseList()!=null && request.getDatabaseList().size()>0){
			param.put("databaseList", request.getDatabaseList());
		}
		return securityHomeChannelDao.queryReportCount(param);
	}

	@Override
	public List<SecurityHomeChannel> queryReportAndNoAuto(
			SecurityHomeChannelRequest securityHomeChannelRequest) {
		Map<String, Object> param = QueryUtils.getQueryMap(securityHomeChannelRequest);
		return securityHomeChannelDao.queryReportAndNoAuto(param);
	}

	@Override
	public int queryHomeNotUpByMap(Map<String, Object> paramMap) {
		return securityHomeChannelDao.queryHomeNotUpByMap(paramMap);
	}
	
	/**
	 * 获取基本信息：栏目不更新数据
	 * 
	 * @param siteCode
	 * @param startDate
	 * @param endDate
	 * @param isManual  是否手动 1：手动   0：机器
	 * @return
	 */
	public List<SecurityHomeChannel> getSecurityChannel(String siteCode, String startDate, String endDate,
			int servicePeroidId,boolean isManual) {
		SecurityHomeChannelRequest securityHomeChannelRequest = new SecurityHomeChannelRequest();

		securityHomeChannelRequest.setSiteCode(siteCode);
		securityHomeChannelRequest.setBeginScanDate(startDate);
		securityHomeChannelRequest.setEndScanDate(endDate);
		securityHomeChannelRequest.setPageSize(Integer.MAX_VALUE);

		if (isManual) {
			securityHomeChannelRequest.setServicePeriodId(servicePeroidId);
			// / 查询出人工的
			return queryReportList(securityHomeChannelRequest);
		} else {
			// 机器
			// / 查询出机器的
			return queryReportAndNoAuto(securityHomeChannelRequest);
		}

		
	}

	@Override
	public List<SecurityHomeChannelRequest> queryBasicNum(
			Map<String, Object> paramMap) {
		return securityHomeChannelDao.queryBasicNum(paramMap);
	}

	@Override
	public List<SecurityHomeChannelRequest> queryListByMap(Map<String, Object> paramMap) {
		return securityHomeChannelDao.queryListByMap(paramMap);
	}

	@Override
	public List<SecurityHomeChannelRequest> getSecurityHomeList(HashMap<String, Object> hashMap) {
		return securityHomeChannelDao.getSecurityHomeList(hashMap);
	}

	@Override
	public int querySiteNumByMap(Map<String, Object> map) {
		return securityHomeChannelDao.querySiteNumByMap(map);
	}

	@Override
	public int querySumByMap(Map<String, Object> map) {
		return securityHomeChannelDao.querySumByMap(map);
	}

	@Override
	public List<SecurityHomeChannelRequest> queryNumByType(
			HashMap<String, Object> paraMap) {
		return securityHomeChannelDao.queryNumByType(paraMap);
	}


	@Override
	public List<SecurityHomeChannel> queryListTbNormal(
			SecurityHomeChannelRequest request) {
		Map<String, Object> param = QueryUtils.getQueryMap(request);
		param.put("channelTypes", request.getChannelTypes());
		
		if(request.getDatabaseList()!=null && request.getDatabaseList().size()>0){
			param.put("databaseList", request.getDatabaseList());
		}
		if(request.getDatabaseLinkList()!=null && request.getDatabaseLinkList().size()>0){
			param.put("databaseLinkList", request.getDatabaseLinkList());
		}
		List<SecurityHomeChannel> list = securityHomeChannelDao.queryListTbNormal(param);
		return list;
	}

	@Override
	public int getSecurityHomeListCount(HashMap<String, Object> paraMap) {
		return securityHomeChannelDao.getSecurityHomeListCount(paraMap);
	}

	@Override
	public int queryNumByTypeCount(HashMap<String, Object> paraMap) {
		return securityHomeChannelDao.queryNumByTypeCount(paraMap);
	}
}

