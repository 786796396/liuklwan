package com.ucap.cloud_web.dto;


import java.util.List;

import com.publics.util.page.Query;
import com.ucap.cloud_web.entity.DatabaseLink;
import com.ucap.cloud_web.entity.DatabaseTreeInfo;


/*** 前台页面传递基础数据<br>* <b>作者：</b>ccx<br>* <b>日期：</b> 2016-03-04 20:20:38 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@SuppressWarnings("serial")public class ContractInfoRequest extends Query {


	private Integer customerInfoId;//客户信息表id
	
	private String siteCode;//网站标识码
	
	private String nowTime;//现在时间
	
	private List idsOrgSiteCode;
	
	//抽查合同号(C+yyyyMMdd_抽查进度表id_批次_组次)
	private String contractCodeSpot;
	
	//执行状态   作废：-1，初始化：0，服务中：1，服务结束：2
	private Integer executeStatus;//
	//执行状态数组   作废：-1，初始化：0，服务中：1，服务结束：2
	private Integer[] executeStatusArr;
	
	private Integer typeFlag;//判断是1非抽查合同
	
	private Integer isCorrect;//是否校对错别字
	
	private Integer isSearchTb;//填报单位是否能够查看详细信息（1-可以查看  2-不能查看）
	//抽查合同like查询
	private String contractCodeSpotLike;
	
	private List<DatabaseLink> dataLinkList;
	private List<DatabaseTreeInfo> databaseTreeList;
	
	//非空  则查询的  siteCode 为 6位的
	private Integer orgFlag;
	//非空  则查询的  siteCode 为 10位的
	private Integer tbFlag;
	private Integer servicePeriodId;//服务周期id
//	开始时间  
	private String startDate;
//  结束时间
	private String endDate;
	public Integer getServicePeriodId() {
		return servicePeriodId;
	}

	public void setServicePeriodId(Integer servicePeriodId) {
		this.servicePeriodId = servicePeriodId;
	}

	public Integer getTbFlag() {
		return tbFlag;
	}

	public void setTbFlag(Integer tbFlag) {
		this.tbFlag = tbFlag;
	}

	public Integer getOrgFlag() {
		return orgFlag;
	}

	public void setOrgFlag(Integer orgFlag) {
		this.orgFlag = orgFlag;
	}

	public String getContractCodeSpotLike() {
		return contractCodeSpotLike;
	}

	public void setContractCodeSpotLike(String contractCodeSpotLike) {
		this.contractCodeSpotLike = contractCodeSpotLike;
	}

	public List<DatabaseLink> getDataLinkList() {
		return dataLinkList;
	}

	public void setDataLinkList(List<DatabaseLink> dataLinkList) {
		this.dataLinkList = dataLinkList;
	}

	public Integer getIsSearchTb() {
		return isSearchTb;
	}

	public void setIsSearchTb(Integer isSearchTb) {
		this.isSearchTb = isSearchTb;
	}

	public Integer[] getExecuteStatusArr() {
		return executeStatusArr;
	}

	public void setExecuteStatusArr(Integer[] executeStatusArr) {
		this.executeStatusArr = executeStatusArr;
	}

	public Integer getTypeFlag() {
		return typeFlag;
	}

	public void setTypeFlag(Integer typeFlag) {
		this.typeFlag = typeFlag;
	}

	public Integer getExecuteStatus() {
		return executeStatus;
	}

	public void setExecuteStatus(Integer executeStatus) {
		this.executeStatus = executeStatus;
	}

	public String getContractCodeSpot() {
		return contractCodeSpot;
	}

	public void setContractCodeSpot(String contractCodeSpot) {
		this.contractCodeSpot = contractCodeSpot;
	}

	public List getIdsOrgSiteCode() {
		return idsOrgSiteCode;
	}

	public void setIdsOrgSiteCode(List idsOrgSiteCode) {
		this.idsOrgSiteCode = idsOrgSiteCode;
	}

	public String getNowTime() {
		return nowTime;
	}

	public void setNowTime(String nowTime) {
		this.nowTime = nowTime;
	}

	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	public Integer getCustomerInfoId() {
		return customerInfoId;
	}

	public void setCustomerInfoId(Integer customerInfoId) {
		this.customerInfoId = customerInfoId;
	}

	public Integer getIsCorrect() {
		return isCorrect;
	}

	public void setIsCorrect(Integer isCorrect) {
		this.isCorrect = isCorrect;
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

	public List<DatabaseTreeInfo> getDatabaseTreeList() {
		return databaseTreeList;
	}

	public void setDatabaseTreeList(List<DatabaseTreeInfo> databaseTreeList) {
		this.databaseTreeList = databaseTreeList;
	}
	
}

