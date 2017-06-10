package com.ucap.cloud_web.dto;


import java.util.List;

import com.publics.util.page.Query;
import com.ucap.cloud_web.entity.DatabaseInfo;


/**
	//网站标识码
	private String siteCode;
	//扫描时间(YYYY-MM-dd)
	private String scanDate;
	//栏目类型
	private Integer dicChannelId;
	/**
	 * 封装数据库中查询出来的数据
	 */
	private Integer updateNum;
	private String returnTime;
	private String siteName;
	private String homePageUrl;
	private String jumpPageUrl;
	
	private String beginScanDate;
	private String endScanDate;
	
	/**
	 * sitecode 数组
	 */
	private List<DatabaseInfo> ids;
	
	

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
	public List<DatabaseInfo> getIds() {
		return ids;
	}
	public void setIds(List<DatabaseInfo> ids) {
		this.ids = ids;
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
	public Integer getDicChannelId() {
		return dicChannelId;
	}
	public void setDicChannelId(Integer dicChannelId) {
		this.dicChannelId = dicChannelId;
	}
	public Integer getUpdateNum() {
		return updateNum;
	}
	public void setUpdateNum(Integer updateNum) {
		this.updateNum = updateNum;
	}
	public String getReturnTime() {
		return returnTime;
	}
	public void setReturnTime(String returnTime) {
		this.returnTime = returnTime;
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
