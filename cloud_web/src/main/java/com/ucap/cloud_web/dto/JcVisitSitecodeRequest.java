package com.ucap.cloud_web.dto;


import com.publics.util.page.Query;


/*** 前台页面传递基础数据<br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-12-13 20:29:02 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@SuppressWarnings("serial")public class JcVisitSitecodeRequest extends Query {
	  
	private Integer parentId;
	
	private String endDate;
	private String siteCode;
	private String scanDate;
	private Integer isBm;

	
	public Integer getIsBm() {
		return isBm;
	}

	public void setIsBm(Integer isBm) {
		this.isBm = isBm;
	}

	public String getScanDate() {
		return scanDate;
	}

	public void setScanDate(String scanDate) {
		this.scanDate = scanDate;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}


	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	
	
	
	

}

