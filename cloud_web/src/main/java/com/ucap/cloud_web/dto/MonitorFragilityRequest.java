package com.ucap.cloud_web.dto;


import java.util.List;

import com.publics.util.page.Query;
import com.ucap.cloud_web.entity.MonitorTaskSilent;


/*** 前台页面传递基础数据<br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-11-04 11:24:50 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@SuppressWarnings("serial")public class MonitorFragilityRequest extends Query {
	
	private String SiteCode;
	private String startDate;
	private String endDate;
	
	
	//脆弱性类型（0:全部、1：sql注入、2：XSS脚本、3：CGI、4：CSRF、5：应用漏洞、6：表单破解）
	private Integer type;
	// 获取 昨天  今天 数据标识
	private String yesFlag;
	
	private List<MonitorTaskSilent> startEndDates;
	
	
	public List<MonitorTaskSilent> getStartEndDates() {
		return startEndDates;
	}
	public void setStartEndDates(List<MonitorTaskSilent> startEndDates) {
		this.startEndDates = startEndDates;
	}
	public String getYesFlag() {
		return yesFlag;
	}
	public void setYesFlag(String yesFlag) {
		this.yesFlag = yesFlag;
	}
	public String getSiteCode() {
		return SiteCode;
	}
	public void setSiteCode(String siteCode) {
		SiteCode = siteCode;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
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

