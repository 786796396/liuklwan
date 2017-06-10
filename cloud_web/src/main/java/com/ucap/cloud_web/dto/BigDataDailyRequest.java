package com.ucap.cloud_web.dto;


import com.publics.util.page.Query;


/*** 前台页面传递基础数据<br>* <b>作者：</b>liujc<br>* <b>日期：</b> 2017-02-28 18:11:20 <br>* <b>版权所有：<b>版权所有(C) 2017<br>*/@SuppressWarnings("serial")public class BigDataDailyRequest extends Query {

	private Integer parentId;
	
	private String countDay;//监测时间
	private String siteCode;
	private Integer isBm;
	private String orgSiteCode;
	private String noupdatestatusStr;//不更新状态 字段名
	private String noupdatestatus;//不更新状态val
	private String siteCodeLength;
	private String codeLike;
	private Integer startIndex;//开始条数
	private Integer pageSize;//一页展示多少条
	private String orderVal;//柱形图的val
	private String noupdatedayNo;//未更新天数不等于-1未知
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getCountDay() {
		return countDay;
	}
	public void setCountDay(String countDay) {
		this.countDay = countDay;
	}
	public String getSiteCode() {
		return siteCode;
	}
	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}
	public Integer getIsBm() {
		return isBm;
	}
	public void setIsBm(Integer isBm) {
		this.isBm = isBm;
	}
	public String getOrgSiteCode() {
		return orgSiteCode;
	}
	public void setOrgSiteCode(String orgSiteCode) {
		this.orgSiteCode = orgSiteCode;
	}
	public String getNoupdatestatusStr() {
		return noupdatestatusStr;
	}
	public void setNoupdatestatusStr(String noupdatestatusStr) {
		this.noupdatestatusStr = noupdatestatusStr;
	}
	public String getNoupdatestatus() {
		return noupdatestatus;
	}
	public void setNoupdatestatus(String noupdatestatus) {
		this.noupdatestatus = noupdatestatus;
	}
	public String getSiteCodeLength() {
		return siteCodeLength;
	}
	public void setSiteCodeLength(String siteCodeLength) {
		this.siteCodeLength = siteCodeLength;
	}
	public String getCodeLike() {
		return codeLike;
	}
	public void setCodeLike(String codeLike) {
		this.codeLike = codeLike;
	}
	public Integer getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public String getOrderVal() {
		return orderVal;
	}
	public void setOrderVal(String orderVal) {
		this.orderVal = orderVal;
	}
	public String getNoupdatedayNo() {
		return noupdatedayNo;
	}
	public void setNoupdatedayNo(String noupdatedayNo) {
		this.noupdatedayNo = noupdatedayNo;
	}
}

