package com.ucap.cloud_web.dto;


import com.publics.util.page.Query;


/*** 前台页面传递基础数据<br>* <b>作者：</b>yuangw@ucap.com.cn<br>* <b>日期：</b> 2016-04-19 11:16:49 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@SuppressWarnings("serial")public class SpotCheckScheduleRequest extends Query {

	 private String siteCode;//网站标识码
	 
	 private String contractInfoId ;//合同ID
	 
	 private String taskName;//任务名称
	 
	 private String beginTime;//开始时间
	 
	 private String endTime;//结束时间
	 
	 private Integer batchNum;//批次
	 
	 private Integer groupNum;//组次

	 private Integer status;//抽查状态 
	 
	 private String reportOrgCode;//上報上級單位
	 
	 
	public Integer getBatchNum() {
		return batchNum;
	}

	public void setBatchNum(Integer batchNum) {
		this.batchNum = batchNum;
	}

	public Integer getGroupNum() {
		return groupNum;
	}

	public void setGroupNum(Integer groupNum) {
		this.groupNum = groupNum;
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

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	public String getContractInfoId() {
		return contractInfoId;
	}

	public void setContractInfoId(String contractInfoId) {
		this.contractInfoId = contractInfoId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getReportOrgCode() {
		return reportOrgCode;
	}

	public void setReportOrgCode(String reportOrgCode) {
		this.reportOrgCode = reportOrgCode;
	}

	
}

