package com.ucap.cloud_web.dto;


import com.publics.util.page.Query;


/*** 前台页面传递基础数据<br>* <b>作者：</b>SunJiang<br>* <b>日期：</b> 2015-10-30 09:51:21 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/@SuppressWarnings("serial")public class SecurityBlankInfoRequest extends Query {
	/**
	 * 网站标识码
	 */
	private String siteCode;
	/**
	 * 期号id
	 */
	private Integer servicePeriodId;
	private String channelMonse;
	private String beginTime;
	private String endTime;
	private String term;//查询条件
	private String beginScanDate;//起始时间
	private String endScanDate;//结束时间
	
	private Integer isorganizational;//是否门户	
	private String director;//主办单位
	
	
	/**
	 * 用于封装从数据库中查出的数据
	 */
	private Integer blankNum;//空白栏目个数
	private String returnTime;//创建时间的前六位字符串
	private String siteName;
	private String homePageUrl;
	private String jumpPageUrl;
	
	
	
	
	
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
	public Integer getIsorganizational() {
		return isorganizational;
	}
	public void setIsorganizational(Integer isorganizational) {
		this.isorganizational = isorganizational;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public Integer getBlankNum() {
		return blankNum;
	}
	public void setBlankNum(Integer blankNum) {
		this.blankNum = blankNum;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public String getChannelMonse() {
		return channelMonse;
	}
	public void setChannelMonse(String channelMonse) {
		this.channelMonse = channelMonse;
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
	public String getReturnTime() {
		return returnTime;
	}
	public void setReturnTime(String returnTime) {
		this.returnTime = returnTime;
	}
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public String getHomePageUrl() {
		return homePageUrl;
	}
	public void setHomePageUrl(String homePageUrl) {
		this.homePageUrl = homePageUrl;
	}
	public String getJumpPageUrl() {
		return jumpPageUrl;
	}
	public void setJumpPageUrl(String jumpPageUrl) {
		this.jumpPageUrl = jumpPageUrl;
	}
    
}

