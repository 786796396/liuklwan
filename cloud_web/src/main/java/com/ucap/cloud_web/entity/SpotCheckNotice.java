package com.ucap.cloud_web.entity;


import java.util.Date;


/**


	//整改通知id
	
	//组织单位siteCode(发送通知)
	private String orgSiteCode;













	
	//联系人邮箱
	private String email2;
	
	//负责人邮箱
	private String email;
	
	private Integer type;//0抽查 1全面监测
	private Integer isRead;//0:未读未反馈  1：已读未反馈
	//报告状态
	private int checkReportResult;
	
	//是否允许上级查看（用于省对县这种跨级通知反馈）
	private int isAllowUpper;
	
	//上级组织单位编码
	private String upperOrgCode;
	
	//联系人邮箱
	private String email2Upper;
		
	//负责人邮箱
	private String emailUpper;
	
	
	public String getEmail2Upper() {
		return email2Upper;
	}

	public void setEmail2Upper(String email2Upper) {
		this.email2Upper = email2Upper;
	}

	public String getEmailUpper() {
		return emailUpper;
	}

	public void setEmailUpper(String emailUpper) {
		this.emailUpper = emailUpper;
	}

	public String getUpperOrgCode() {
		return upperOrgCode;
	}

	public void setUpperOrgCode(String upperOrgCode) {
		this.upperOrgCode = upperOrgCode;
	}

	public String getOrgSiteCode() {
		return orgSiteCode;
	}

	public void setOrgSiteCode(String orgSiteCode) {
		this.orgSiteCode = orgSiteCode;
	}


	public int getIsAllowUpper() {
		return isAllowUpper;
	}

	public void setIsAllowUpper(int isAllowUpper) {
		this.isAllowUpper = isAllowUpper;
	}

	public int getCheckReportResult() {
		return checkReportResult;
	}

	public void setCheckReportResult(int checkReportResult) {
		this.checkReportResult = checkReportResult;
	}

		this.id=id;
	}

		return id;
	}

		this.director=director;
	}

		return director;
	}

		this.databaseInfoId=databaseInfoId;
	}

		return databaseInfoId;
	}

		this.siteCode=siteCode;
	}

		return siteCode;
	}

		this.servicePeriodId=servicePeriodId;
	}

		return servicePeriodId;
	}

		this.requireTime=requireTime;
	}

		return requireTime;
	}

		this.endTime=endTime;
	}

		return endTime;
	}

		this.noticeRequirement=noticeRequirement;
	}

		return noticeRequirement;
	}

		this.noticeResponse=noticeResponse;
	}

		return noticeResponse;
	}

		this.questionNum=questionNum;
	}

		return questionNum;
	}

		this.noticeReportName=noticeReportName;
	}

		return noticeReportName;
	}

		this.noticeReportUrl=noticeReportUrl;
	}

		return noticeReportUrl;
	}

		this.createTime=createTime;
	}

		return createTime;
	}

		this.modifyTime=modifyTime;
	}

		return modifyTime;
	}

	public String getEmail2() {
		return email2;
	}

	public void setEmail2(String email2) {
		this.email2 = email2;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

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

