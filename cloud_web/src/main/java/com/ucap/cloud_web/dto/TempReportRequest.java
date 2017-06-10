package com.ucap.cloud_web.dto;


import com.publics.util.page.Query;


/**
	//网站标识码
	private String siteCode;
	//组织单位网站标识码
	private String orgSiteCode;
	//查询条件的开始日期
	private String startDate;
	//查询条件的结束日期
	private String endDate;

	//查询条件的 网站标识码或者 名称
	private String nameOrSiteCode;
	//查询条件 申报原因
	private String reportReason;
	
	//databaseTreeInfo编码
	private String codeLike;
	//层级+1
	private Integer levelLower;
	//层级 +2
	private Integer levellowerlow;
	
	
	public String getOrgSiteCode() {
		return orgSiteCode;
	}
	public void setOrgSiteCode(String orgSiteCode) {
		this.orgSiteCode = orgSiteCode;
	}
	public String getReportReason() {
		return reportReason;
	}
	public void setReportReason(String reportReason) {
		this.reportReason = reportReason;
	}
	public String getSiteCode() {
		return siteCode;
	}
	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
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
	public String getNameOrSiteCode() {
		return nameOrSiteCode;
	}
	public void setNameOrSiteCode(String nameOrSiteCode) {
		this.nameOrSiteCode = nameOrSiteCode;
	}
	public Integer getLevelLower() {
		return levelLower;
	}
	public void setLevelLower(Integer levelLower) {
		this.levelLower = levelLower;
	}
	public Integer getLevellowerlow() {
		return levellowerlow;
	}
	public void setLevellowerlow(Integer levellowerlow) {
		this.levellowerlow = levellowerlow;
	}
	public String getCodeLike() {
		return codeLike;
	}
	public void setCodeLike(String codeLike) {
		this.codeLike = codeLike;
	}

}
