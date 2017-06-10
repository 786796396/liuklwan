package com.ucap.cloud_web.service.impl;


import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.publics.util.page.PageVo;
import com.publics.util.utils.DateUtils;
import com.publics.util.utils.QueryUtils;
import com.ucap.cloud_web.common.DicUtils;
import com.ucap.cloud_web.dao.EarlyDetailTempDao;
import com.ucap.cloud_web.dto.ContractInfoRequest;
import com.ucap.cloud_web.dto.EarlyDetailTempRequest;
import com.ucap.cloud_web.entity.ConfigEarly;
import com.ucap.cloud_web.entity.ContractInfo;
import com.ucap.cloud_web.entity.EarlyDetailTemp;
import com.ucap.cloud_web.service.IContractInfoService;
import com.ucap.cloud_web.service.IEarlyDetailTempService;
import com.ucap.cloud_web.util.HttpClientUtils;


/*** <br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2017-03-14 14:39:10 <br>* <b>版权所有：<b>版权所有(C) 2017<br>*/@Servicepublic class EarlyDetailTempServiceImpl implements IEarlyDetailTempService {


	@Autowired	private EarlyDetailTempDao earlyDetailTempDao;	@Autowired
	private IContractInfoService contractInfoServiceImpl;	@Autowired
	private DicUtils dicUtils;	@Override	public int add(EarlyDetailTemp earlyDetailTemp){		return earlyDetailTempDao.add(earlyDetailTemp);	}
	@Override	public EarlyDetailTemp get(Integer id){		return earlyDetailTempDao.get(id);	}
	@Override	public void update(EarlyDetailTemp earlyDetailTemp){		earlyDetailTempDao.update(earlyDetailTemp);	}
	@Override	public void delete(Integer id){		earlyDetailTempDao.delete(id);	}
	@Override	public PageVo<EarlyDetailTemp> query(EarlyDetailTempRequest request) {		List<EarlyDetailTemp> earlyDetailTemp = queryList(request);		PageVo<EarlyDetailTemp> pv = new PageVo<EarlyDetailTemp>();		int count = queryCount(request);		pv.setData(earlyDetailTemp);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(EarlyDetailTempRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return earlyDetailTempDao.queryCount(param);	}
	@Override	public List<EarlyDetailTemp> queryList(EarlyDetailTempRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<EarlyDetailTemp> list = earlyDetailTempDao.query(param);		return list;	}

	@Override
	public List<EarlyDetailTemp> querySiteEarlyInfo(EarlyDetailTempRequest earlyDetailTempRequest) {
		return earlyDetailTempDao.querySiteEarlyInfo(earlyDetailTempRequest);
	}
	
	/**
	 * @Description:   根据新老合配置数据 对比  维护增删预警池数据
	 * @author: renpb --- 2017年4月13日下午5:02:53
	 * @return
	 */
	@Override
	public void updateEarlyDetailTemp(ConfigEarly oldConfig,ConfigEarly nowConfig){
		
		ContractInfoRequest conRequest = new ContractInfoRequest();
		conRequest.setSiteCode(nowConfig.getSiteCode());
		conRequest.setTypeFlag(1);// 非抽查合同
		Integer[] conStatues = { 0, 1, 2 };
		conRequest.setExecuteStatusArr(conStatues);// 合同状态为0-初始化 1-服务中
		conRequest.setNowTime(DateUtils.formatStandardDate(new Date()));
		conRequest.setPageSize(Integer.MAX_VALUE);
		List<ContractInfo> conList = contractInfoServiceImpl.queryList(conRequest);
		//String url = "cloud_backweb/earlyDetailTemp_addOrDelFromConfig.action";
		String url = dicUtils.getValue("addOrDelFromConfig");
		
		System.out.println(" updateEarlyDetailTemp url==================="+url);
		//orgTbStatus   1本组织      2下级全部      3门户       -3非门户   4本组织和下级     0本填报
		for (ContractInfo contractInfo : conList) {
			Map<String, String> paramMap = Maps.newHashMap();
			paramMap.put("siteCode", nowConfig.getSiteCode());
			paramMap.put("contractInfoId",String.valueOf(contractInfo.getId()));
			if(nowConfig.getSiteCode().length() == 6){
				if(nowConfig.getIsNextAllSite() == 1 && oldConfig.getIsNextAllSite() == 2 
						&& nowConfig.getHomeConnection() == 1 && oldConfig.getHomeConnection() == 1){//不接受改接受  下级
					//添加     下级--门户、非门户（全部）
					try {
						paramMap.put("operation","add");
						paramMap.put("orgTbStatus","2");
						String dd = HttpClientUtils.basicGet(url, paramMap);
						System.out.println("---------------------------------------"+dd);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}else if(nowConfig.getIsNextAllSite() == 2 && oldConfig.getIsNextAllSite() == 1 ){//接受改不接受下级
					//删除  下级--门户、非门户（全部）
					try {
						paramMap.put("operation","del");
						paramMap.put("orgTbStatus","2");
						String ss = HttpClientUtils.basicGet(url, paramMap);
						System.out.println(ss+"---------------------------------------");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
				if(nowConfig.getHomeConnection() == 1 && oldConfig.getHomeConnection() == 2) {
					if(nowConfig.getIsNextAllSite() == 1 && oldConfig.getIsNextAllSite() == 1 ){//指标由不勾选改勾选状态
						//添加   本下级
						try {
							paramMap.put("operation","add");
							paramMap.put("orgTbStatus","2");
							String ss = HttpClientUtils.basicGet(url, paramMap);
							System.out.println(ss+"---------------------------------------");
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					if(nowConfig.getIsSiteReceive() == 1 && oldConfig.getIsSiteReceive() == 1 ){//指标由不勾选改勾选状态
						//添加   本组织
						try {
							paramMap.put("operation","add");
							paramMap.put("orgTbStatus","1");
							String ss = HttpClientUtils.basicGet(url, paramMap);
							System.out.println(ss+"---------------------------------------");
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}else if(nowConfig.getHomeConnection() == 2 && oldConfig.getHomeConnection() == 1){//指标由勾选改不勾选状态
					//删除   本组织和下级
					try {
						paramMap.put("operation","del");
						paramMap.put("orgTbStatus","4");
						String ss = HttpClientUtils.basicGet(url, paramMap);
						System.out.println(ss+"---------------------------------------");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if(nowConfig.getIsSiteReceive() == 1){
					if(oldConfig.getIsSiteReceive() == 2  
							&& nowConfig.getHomeConnection() == 1 && oldConfig.getHomeConnection() == 1){//不接受下级改接受下级所有
						//添加  下级--门户、非门户（全部）
						try {
							paramMap.put("operation","add");
							paramMap.put("orgTbStatus","1");
							String ss = HttpClientUtils.basicGet(url, paramMap);
							System.out.println(ss+"---------------------------------------");
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					if(oldConfig.getIsSiteReceive() == 3 
							&& nowConfig.getHomeConnection() == 1 && oldConfig.getHomeConnection() == 1){//只接受门户改接受下级所有
						//添加   下级--非门户
						try {
							paramMap.put("operation","add");
							paramMap.put("orgTbStatus","-3");
							String ss = HttpClientUtils.basicGet(url, paramMap);
							System.out.println(ss+"---------------------------------------");
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}else if(nowConfig.getIsSiteReceive() == 2){//接受门户或接受下级所有改不接受
					//删除   下级--门户、非门户（全部）
					try {
						paramMap.put("operation","del");
						paramMap.put("orgTbStatus","1");
						String ss = HttpClientUtils.basicGet(url, paramMap);
						System.out.println(ss+"---------------------------------------");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}else if(nowConfig.getIsSiteReceive() == 3){
					if(oldConfig.getIsSiteReceive() == 1){//接受下级所有改接受门户
						//删除    非门户
						try {
							paramMap.put("operation","del");
							paramMap.put("orgTbStatus","-3");
							String ss = HttpClientUtils.basicGet(url, paramMap);
							System.out.println(ss+"---------------------------------------");
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					if(oldConfig.getIsSiteReceive() == 2 
							&& nowConfig.getHomeConnection() == 1 && oldConfig.getHomeConnection() == 1){//不接受改接受门户
						//添加   门户
						try {
							paramMap.put("operation","add");
							paramMap.put("orgTbStatus","3");
							String ss = HttpClientUtils.basicGet(url, paramMap);
							System.out.println(ss+"---------------------------------------");
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}else{
				if(nowConfig.getHomeConnection() == 1 && oldConfig.getHomeConnection() == 2
						 && nowConfig.getIsSiteReceive() == 1 && oldConfig.getIsSiteReceive() == 1){//指标由不勾选改勾选状态
					//添加    本填报
					try {
						paramMap.put("operation","add");
						paramMap.put("orgTbStatus","0");
						String ss = HttpClientUtils.basicGet(url, paramMap);
						System.out.println(ss+"---------------------------------------");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if(nowConfig.getHomeConnection() == 2 && oldConfig.getHomeConnection() == 1){//指标由勾选改不勾选状态
					//删除   本填报
					try {
						paramMap.put("operation","del");
						paramMap.put("orgTbStatus","0");
						String ss = HttpClientUtils.basicGet(url, paramMap);
						System.out.println(ss+"---------------------------------------");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if(nowConfig.getIsSiteReceive() == 1 && oldConfig.getIsSiteReceive() == 2
						 && nowConfig.getHomeConnection() == 1 && oldConfig.getHomeConnection() == 1){//不接受改接受
					//添加    本填报
					try {
						paramMap.put("operation","add");
						paramMap.put("orgTbStatus","0");
						String ss = HttpClientUtils.basicGet(url, paramMap);
						System.out.println(ss+"---------------------------------------");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if(nowConfig.getIsSiteReceive() == 2 && oldConfig.getIsSiteReceive() == 1){//接受改不接受
					//删除   本填报
					try {
						paramMap.put("operation","del");
						paramMap.put("orgTbStatus","0");
						String ss = HttpClientUtils.basicGet(url, paramMap);
						System.out.println(ss+"---------------------------------------");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	@Override
	public List<EarlyDetailTempRequest> queryEarlyTempByMap(
			Map<String, Object> paraMap) {
		return earlyDetailTempDao.queryEarlyTempByMap(paraMap);
	}

	@Override
	public int queryEarlyTempByMapCount(Map<String, Object> paraMap) {
		return earlyDetailTempDao.queryEarlyTempByMapCount(paraMap);
	}

	@Override
	public List<EarlyDetailTempRequest> queryEarlyTempByMapCountTb(Map<String, Object> paraMap) {
		return earlyDetailTempDao.queryEarlyTempByMapCountTb(paraMap);
	}}

