package com.ucap.cloud_web.dto;


import com.publics.util.page.Query;


/**
	/**
	 * 网站标识码
	 */
	private String siteCode;
	/**
	 * 扫描时间
	 */
	private String scanDate;
	//开始时间
	private String startDate;
	//结束时间
	private String endDate;
	//类型（0全部，1本级门户，2本级部门，3下属单位，4例外，5关停，6其他）
	private String type;
	
	public String getSiteCode() {
		return siteCode;
	}
	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

}
