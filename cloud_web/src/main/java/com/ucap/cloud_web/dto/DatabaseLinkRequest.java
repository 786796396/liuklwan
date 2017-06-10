package com.ucap.cloud_web.dto;


import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.publics.util.page.Query;
import com.publics.util.utils.DateUtils;


/*** 前台页面传递基础数据<br>* <b>作者：</b>SunJiang<br>* <b>日期：</b> 2016-03-21 16:16:55 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@SuppressWarnings("serial")public class DatabaseLinkRequest extends Query {
	//站点个数
	private int count;
	
	private String siteCode;
	
	private Integer type;
	
	private Integer isexp;
	
	private Integer databaseInfoId;
	
	private String orgSiteCode;
	
	private String typeIn;
	
	private String groupBy;
	
	private Integer linkStatus;
	
	//服务周期id
	private Integer servicePeroidId;
	
	//服务周期开始时间
	private String startDate;
	private String endDate;
	
	
	
	
	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Integer getServicePeroidId() {
		return servicePeroidId;
	}

	public void setServicePeroidId(Integer servicePeroidId) {
		this.servicePeroidId = servicePeroidId;
	}

	public Integer getLinkStatus() {
		return linkStatus;
	}

	public void setLinkStatus(Integer linkStatus) {
		this.linkStatus = linkStatus;
	}

	public String getGroupBy() {
		return groupBy;
	}

	public void setGroupBy(String groupBy) {
		this.groupBy = groupBy;
	}

	public Integer getIsexp() {
		return isexp;
	}

	public void setIsexp(Integer isexp) {
		this.isexp = isexp;
	}

	public String getTypeIn() {
		return typeIn;
	}

	public void setTypeIn(String typeIn) {
		this.typeIn = typeIn;
	}

	public String getOrgSiteCode() {
		return orgSiteCode;
	}

	public void setOrgSiteCode(String orgSiteCode) {
		this.orgSiteCode = orgSiteCode;
	}

	public Integer getDatabaseInfoId() {
		return databaseInfoId;
	}

	public void setDatabaseInfoId(Integer databaseInfoId) {
		this.databaseInfoId = databaseInfoId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}

