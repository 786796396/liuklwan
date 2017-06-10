package com.ucap.cloud_web.dto;


import com.publics.util.page.Query;


/**

	//标识码
	private String siteCode;
	//域名
	private String domain;
	//状态（0：未开通，1：开通，2：停用）
	private Integer status;
	//删除标识（0：正常，1：删除）
	private Integer delFlag;
	
	//模板类型（1:模板A,2:模板B）
	private Integer templateType;
	
	private String nowDate;

	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	public Integer getTemplateType() {
		return templateType;
	}

	public void setTemplateType(Integer templateType) {
		this.templateType = templateType;
	}

	public String getNowDate() {
		return nowDate;
	}

	public void setNowDate(String nowDate) {
		this.nowDate = nowDate;
	}
	
}
