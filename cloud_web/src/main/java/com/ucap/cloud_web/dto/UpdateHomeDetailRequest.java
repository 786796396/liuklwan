package com.ucap.cloud_web.dto;

import java.util.List;

import com.publics.util.page.Query;
import com.ucap.cloud_web.entity.DatabaseInfo;

/**
 * 前台页面传递基础数据<br>
 * <b>作者：</b>SunJiang<br>
 * <b>日期：</b> 2015-10-30 09:51:22 <br>
 * <b>版权所有：<b>版权所有(C) 2015<br>
 */
@SuppressWarnings("serial")
public class UpdateHomeDetailRequest extends Query {

	/**
	 * 网站标示码
	 */
	private String siteCode;
	private String beginScanDate;// 扫描起始时间
	private String endScanDate;// 扫描结束时间
	private String term;// 查询条件
	/**
	 * 日期
	 */
	private String date;
	private String title;// 标题，用于查关键字

	private String homeUrl;

	// 封装数据库中查询出来的数据
	private String siteName;
	private String homePageUrl;
	private Integer updateNum;
	private Integer notUpdateDays;

	private String updateTime;
	
	private String jumpPageUrl;//跳转url
	private List<DatabaseInfo> databaseList;
	
	private Integer start;
	private Integer pageSize;
	
	
	
	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public List<DatabaseInfo> getDatabaseList() {
		return databaseList;
	}

	public void setDatabaseList(List<DatabaseInfo> databaseList) {
		this.databaseList = databaseList;
	}

	public String getJumpPageUrl() {
		return jumpPageUrl;
	}

	public void setJumpPageUrl(String jumpPageUrl) {
		this.jumpPageUrl = jumpPageUrl;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
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

	public Integer getUpdateNum() {
		return updateNum;
	}

	public void setUpdateNum(Integer updateNum) {
		this.updateNum = updateNum;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getNotUpdateDays() {
		return notUpdateDays;
	}

	public void setNotUpdateDays(Integer notUpdateDays) {
		this.notUpdateDays = notUpdateDays;
	}

	public String getHomeUrl() {
		return homeUrl;
	}

	public void setHomeUrl(String homeUrl) {
		this.homeUrl = homeUrl;
	}




}
