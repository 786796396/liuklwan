package com.ucap.cloud_web.dto;


import java.util.List;

import com.publics.util.page.Query;
import com.ucap.cloud_web.entity.MonitorTaskSilent;


/**
	private String SiteCode;
	private String startDate;
	private String endDate;
	private String type;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

}
