package com.ucap.cloud_web.dto;


import com.publics.util.page.Query;


/**
	
	//网站识别码
	private String siteCode;
	//云产品，云搜索还是云分析
	private Integer yunType;
	//开通状态
	private Integer openState;
	
	private String openStateStr;
	
	
	
	public String getOpenStateStr() {
		return openStateStr;
	}
	public void setOpenStateStr(String openStateStr) {
		this.openStateStr = openStateStr;
	}
	public String getSiteCode() {
		return siteCode;
	}
	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}
	public Integer getYunType() {
		return yunType;
	}
	public void setYunType(Integer yunType) {
		this.yunType = yunType;
	}
	public Integer getOpenState() {
		return openState;
	}
	public void setOpenState(Integer openState) {
		this.openState = openState;
	}
	
	
	
}
