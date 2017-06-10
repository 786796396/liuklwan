package com.ucap.cloud_web.dto;


import com.publics.util.page.Query;


/*** 前台页面传递基础数据<br>* <b>作者：</b>yangshuai<br>* <b>日期：</b> 2016-06-01 18:43:28 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@SuppressWarnings("serial")public class SpotCheckNoticeRequest extends Query {

	//网站标识码
	private String siteCode;

	//服务周期id
	private Integer servicePeriodId;
	
	//报告状态-用于显示栏目
	private Integer checkReportResults;
	
	//报告状态
	private Integer checkReportResult;
	//检测形式（0:抽查  1:全面检测）
	private Integer type;
	//0:未读未反馈  1：已读未反馈
	private Integer isRead;
	
	private String groupBy;
	
	
	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	public Integer getServicePeriodId() {
		return servicePeriodId;
	}

	public void setServicePeriodId(Integer servicePeriodId) {
		this.servicePeriodId = servicePeriodId;
	}

	public Integer getCheckReportResults() {
		return checkReportResults;
	}

	public void setCheckReportResults(Integer checkReportResults) {
		this.checkReportResults = checkReportResults;
	}

	public Integer getCheckReportResult() {
		return checkReportResult;
	}

	public void setCheckReportResult(Integer checkReportResult) {
		this.checkReportResult = checkReportResult;
	}

	public String getGroupBy() {
		return groupBy;
	}

	public void setGroupBy(String groupBy) {
		this.groupBy = groupBy;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getIsRead() {
		return isRead;
	}

	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}

	
}

