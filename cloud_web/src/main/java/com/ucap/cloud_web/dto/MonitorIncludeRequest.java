package com.ucap.cloud_web.dto;


import com.publics.util.page.Query;


/*** 前台页面传递基础数据<br>* <b>作者：</b>liujc<br>* <b>日期：</b> 2016-11-01 16:07:23 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@SuppressWarnings("serial")public class MonitorIncludeRequest extends Query {
	private String siteCode;
	private String scanDate;
	private String beginScanDate;
	private String endScanDate;
	private Integer parentId;
	private String code;
	
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
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
	public String getScanDate() {
		return scanDate;
	}
	public void setScanDate(String scanDate) {
		this.scanDate = scanDate;
	}
	public String getBeginScanDate() {
		return beginScanDate;
	}
	public void setBeginScanDate(String beginScanDate) {
		this.beginScanDate = beginScanDate;
	}
	public String getEndScanDate() {
		return endScanDate;
	}
	public void setEndScanDate(String endScanDate) {
		this.endScanDate = endScanDate;
	}

}

