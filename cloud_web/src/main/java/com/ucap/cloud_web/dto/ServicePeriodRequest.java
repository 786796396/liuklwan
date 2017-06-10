package com.ucap.cloud_web.dto;


import java.util.List;

import com.publics.util.page.Query;
import com.ucap.cloud_web.entity.ContractInfo;
import com.ucap.cloud_web.entity.RelationsPeriod;


/*** 前台页面传递基础数据<br>* <b>作者：</b>SunJiang<br>* <b>日期：</b> 2015-10-30 09:51:21 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*//**<p>Description: </p>
 * <p>@Package：com.ucap.cloud_web.dto </p>
 * <p>Title: ServicePeriodRequest </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：cuichx </p>
 * <p>@date：2015-11-16下午1:45:30 </p>
 */
@SuppressWarnings("serial")public class ServicePeriodRequest extends Query {
	
	//id
	private Integer id;
	//合同ID
	private Integer contractInfoId;
	
	//当前时间
	private String nowTime;
	
	//服务报告周期状态0：未开始服务，1：服务中，2已完成服务
	private Integer status;
	
	//套餐类型
	private Integer comboI;
	
	//套餐类型数组
	private int[] comboIdArr;

	//状态数组
	private int[] statasArray;
	
	private List<RelationsPeriod> list;
	
	private List<ContractInfo> contractList;

	// 新产品list
	//private List<CrmProductsResponse> crmProductsList;
	
	//抽查进度表id
	private Integer spotCheckScheduleId;
	private String searchBunch;//查询条件
	/*全面监测start*/
	//周期任务号
	private String servicePeriodNum;
	//开始时间
	private String beginTime;
	//结束时间
	private String endTime;
	//合同ID分组count
	private Integer periodNum;
	//周期状态
	private Integer periodStatus;
	/*全面监测end*/
	
	
	private String siteCode;
	
	//默认查询周期开始时间
	private String startDateTime;
	//周期开始时间
	private String startDate;
	//周期结束时间
	private String endDate;
	//周期关联表 siteCode
	private String siteCodeRTb;
	//合同id集合
	private List<Integer> contractInfoIdList;
	

	


	public List<Integer> getContractInfoIdList() {
		return contractInfoIdList;
	}

	public void setContractInfoIdList(List<Integer> contractInfoIdList) {
		this.contractInfoIdList = contractInfoIdList;
	}

	public String getSiteCodeRTb() {
		return siteCodeRTb;
	}

	public void setSiteCodeRTb(String siteCodeRTb) {
		this.siteCodeRTb = siteCodeRTb;
	}

	public String getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(String startDateTime) {
		this.startDateTime = startDateTime;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Integer getSpotCheckScheduleId() {
		return spotCheckScheduleId;
	}

	public void setSpotCheckScheduleId(Integer spotCheckScheduleId) {
		this.spotCheckScheduleId = spotCheckScheduleId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<RelationsPeriod> getList() {
		return list;
	}

	public void setList(List<RelationsPeriod> list) {
		this.list = list;
	}

	public int[] getStatasArray() {
		return statasArray;
	}

	public void setStatasArray(int[] statasArray) {
		this.statasArray = statasArray;
	}

	public Integer getComboI() {
		return comboI;
	}

	public void setComboI(Integer comboI) {
		this.comboI = comboI;
	}

	public int[] getComboIdArr() {
		return comboIdArr;
	}

	public void setComboIdArr(int[] comboIdArr) {
		this.comboIdArr = comboIdArr;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getContractInfoId() {
		return contractInfoId;
	}

	public void setContractInfoId(Integer contractInfoId) {
		this.contractInfoId = contractInfoId;
	}

	public String getNowTime() {
		return nowTime;
	}

	public void setNowTime(String nowTime) {
		this.nowTime = nowTime;
	}

	public String getSearchBunch() {
		return searchBunch;
	}

	public void setSearchBunch(String searchBunch) {
		this.searchBunch = searchBunch;
	}

	public String getServicePeriodNum() {
		return servicePeriodNum;
	}

	public void setServicePeriodNum(String servicePeriodNum) {
		this.servicePeriodNum = servicePeriodNum;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Integer getPeriodNum() {
		return periodNum;
	}

	public void setPeriodNum(Integer periodNum) {
		this.periodNum = periodNum;
	}

	public Integer getPeriodStatus() {
		return periodStatus;
	}

	public void setPeriodStatus(Integer periodStatus) {
		this.periodStatus = periodStatus;
	}

	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	public List<ContractInfo> getContractList() {
		return contractList;
	}

	public void setContractList(List<ContractInfo> contractList) {
		this.contractList = contractList;
	}

/*	public List<CrmProductsResponse> getCrmProductsList() {
		return crmProductsList;
	}

	public void setCrmProductsList(List<CrmProductsResponse> crmProductsList) {
		this.crmProductsList = crmProductsList;
	}*/
	
}

