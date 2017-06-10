package com.ucap.cloud_web.dto;


import com.publics.util.page.Query;


/*** 前台页面传递基础数据<br>* <b>作者：</b>liujc<br>* <b>日期：</b> 2016-05-09 11:53:09 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@SuppressWarnings("serial")public class SecurityFatalErrorRequest extends Query {


	private String name;   //问题名称
	private String siteCode;
	private Integer type;

	private String scanDate;
	
	private String startDate;
	private String endDate;
	
	private String siteName;//网站名称
	private Integer fatalErrorNum;//严重问题统计个数
	private Integer servicePeriodId;//服务周期id
	private Integer serviceSum;//每类问题的个数
	private Integer problemTypeId;//问题类型id
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getServiceSum() {
		return serviceSum;
	}

	public void setServiceSum(Integer serviceSum) {
		this.serviceSum = serviceSum;
	}

	public Integer getProblemTypeId() {
		return problemTypeId;
	}

	public void setProblemTypeId(Integer problemTypeId) {
		this.problemTypeId = problemTypeId;
	}

	public Integer getServicePeriodId() {
		return servicePeriodId;
	}

	public void setServicePeriodId(Integer servicePeriodId) {
		this.servicePeriodId = servicePeriodId;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public Integer getFatalErrorNum() {
		return fatalErrorNum;
	}

	public void setFatalErrorNum(Integer fatalErrorNum) {
		this.fatalErrorNum = fatalErrorNum;
	}

	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	public String getScanDate() {
		return scanDate;
	}

	public void setScanDate(String scanDate) {
		this.scanDate = scanDate;
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


}

