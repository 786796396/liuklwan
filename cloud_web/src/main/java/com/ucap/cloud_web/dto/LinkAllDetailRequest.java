package com.ucap.cloud_web.dto;


import com.publics.util.page.Query;


/*** 前台页面传递基础数据<br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2015-10-29 11:05:51 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/@SuppressWarnings("serial")public class LinkAllDetailRequest extends Query {
	/**
	 * 根据期号(或扫描时间)和sitecode查询全站连接可用性进度和趋势表
	 */
	private Integer servicePeriodId;//期号id
	private String siteCode;//网站标识码
	private String scanTime;//扫描时间
	private String startDate;//开始时间
	private String endDate;//结束时间
	private String terms;//页面传送的关键字
	private Integer webType;//站内站外
	private Integer errorCode;//错误代码 确认疑似全站死链
	private String[] errorCodeArr;//错误代码 确认疑似全站死链
	private String groupBy;//分组字段
	private Integer errorNum;//死链个数
	
	
	
	
	public Integer getErrorNum() {
		return errorNum;
	}
	public void setErrorNum(Integer errorNum) {
		this.errorNum = errorNum;
	}
	public String getGroupBy() {
		return groupBy;
	}
	public void setGroupBy(String groupBy) {
		this.groupBy = groupBy;
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
	public String getScanTime() {
		return scanTime;
	}
	public void setScanTime(String scanTime) {
		this.scanTime = scanTime;
	}
	public Integer getServicePeriodId() {
		return servicePeriodId;
	}
	public void setServicePeriodId(Integer servicePeriodId) {
		this.servicePeriodId = servicePeriodId;
	}
	public String getSiteCode() {
		return siteCode;
	}
	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}
	public String getTerms() {
		return terms;
	}
	public void setTerms(String terms) {
		this.terms = terms;
	}
	public Integer getWebType() {
		return webType;
	}
	public void setWebType(Integer webType) {
		this.webType = webType;
	}
	public Integer getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}
	public String[] getErrorCodeArr() {
		return errorCodeArr;
	}
	public void setErrorCodeArr(String[] errorCodeArr) {
		this.errorCodeArr = errorCodeArr;
	}
	
}

