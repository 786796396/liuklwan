package com.ucap.cloud_web.dto;

import java.util.List;


import com.publics.util.page.Query;
import com.ucap.cloud_web.entity.DatabaseInfo;

/**
 * 前台页面传递基础数据<br>
 * <b>作者：</b>Nora<br>
 * <b>日期：</b> 2015-10-29 11:19:33 <br>
 * <b>版权所有：<b>版权所有(C) 2015<br>
 */
@SuppressWarnings("serial")
public class CorrectContentDetailRequest extends Query {

	// 网站标识码
	private String siteCode;
	//网站名称
	private String siteName;

	//首页URl
	private String homePageUrl;
	
	//跳转url
	private String jumpPageUrl;
	
	//是否门户
	private Integer isorganizational;
	
	//主办单位
	private String director;
	
	
	// 期号
	private Integer servicePeriodId;

	// 内容正确性类型（0：确定，1：疑似，2：可能）
	private Integer correctType;
	//问题描述，用于条件查询
	private String problemDesc;
	private String beginTime;
	private String endTime;
	//关键字（用于查询）
	private String keyWord;
	//业务时间（扫描时间）
	private String scanDate;
	//开始时间
	private String beginScanDate;
	//结束时间
	private String endScanDate;
	
	private int[] correctTypeArray;
	
	private Integer wrongNum;
	
	private String notType;
	
	private Integer exposure;
	
	private List<DatabaseInfo> databaseList;
	
	
	public List<DatabaseInfo> getDatabaseList() {
		return databaseList;
	}
	public void setDatabaseList(List<DatabaseInfo> databaseList) {
		this.databaseList = databaseList;
	}
	public Integer getExposure() {
		return exposure;
	}
	public void setExposure(Integer exposure) {
		this.exposure = exposure;
	}
	public String getNotType() {
		return notType;
	}
	public void setNotType(String notType) {
		this.notType = notType;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
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
	public Integer getIsorganizational() {
		return isorganizational;
	}
	public void setIsorganizational(Integer isorganizational) {
		this.isorganizational = isorganizational;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public Integer getWrongNum() {
		return wrongNum;
	}
	public void setWrongNum(Integer wrongNum) {
		this.wrongNum = wrongNum;
	}
	public int[] getCorrectTypeArray() {
		return correctTypeArray;
	}
	public void setCorrectTypeArray(int[] correctTypeArray) {
		this.correctTypeArray = correctTypeArray;
	}
	public String getScanDate() {
		return scanDate;
	}
	public void setScanDate(String scanDate) {
		this.scanDate = scanDate;
	}
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
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
	public Integer getCorrectType() {
		return correctType;
	}

	public void setCorrectType(Integer correctType) {
		this.correctType = correctType;
	}

	public String getProblemDesc() {
		return problemDesc;
	}

	public void setProblemDesc(String problemDesc) {
		this.problemDesc = problemDesc;
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
