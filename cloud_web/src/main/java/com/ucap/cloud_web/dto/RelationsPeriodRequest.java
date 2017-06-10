package com.ucap.cloud_web.dto;


import com.publics.util.page.Query;


/**
	//客户编号
	private String siteCode;
	//当前时间
	private String nowTime;
	//周期号
	private Integer servicePeriodId;
	
	//昨天
	private String yesterDay;
	
	//套餐类型
	private Integer comboId;
	
	//全站死链是否扫描(1:是，2否)
	private Integer isScan;
	
	//站點任務
	private String notWebsiteTaskNum;
	
	//周期开始时间
	private String startDate;
	
	//当前时间向前365天
	private String forwardDate;
	
	public String getYesterDay() {
		return yesterDay;
	}

	public void setYesterDay(String yesterDay) {
		this.yesterDay = yesterDay;
	}

	public Integer getComboId() {
		return comboId;
	}

	public void setComboId(Integer comboId) {
		this.comboId = comboId;
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

	public Integer getServicePeriodId() {
		return servicePeriodId;
	}

	public void setServicePeriodId(Integer servicePeriodId) {
		this.servicePeriodId = servicePeriodId;
	}

	public Integer getIsScan() {
		return isScan;
	}

	public void setIsScan(Integer isScan) {
		this.isScan = isScan;
	}

	public String getNotWebsiteTaskNum() {
		return notWebsiteTaskNum;
	}

	public void setNotWebsiteTaskNum(String notWebsiteTaskNum) {
		this.notWebsiteTaskNum = notWebsiteTaskNum;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getForwardDate() {
		return forwardDate;
	}

	public void setForwardDate(String forwardDate) {
		this.forwardDate = forwardDate;
	}

}
