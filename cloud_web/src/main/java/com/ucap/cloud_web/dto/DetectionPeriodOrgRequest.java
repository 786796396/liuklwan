package com.ucap.cloud_web.dto;


import com.publics.util.page.Query;


/*** 前台页面传递基础数据<br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2017-03-24 10:17:35 <br>* <b>版权所有：<b>版权所有(C) 2017<br>*/@SuppressWarnings("serial")public class DetectionPeriodOrgRequest extends Query {
	
	private String siteCode;//标识码
	
	private Integer servicePeroidId;//服务周期id
	
	private Integer type;//类型
	
	private String nowDate;//当前日期

	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	public Integer getServicePeroidId() {
		return servicePeroidId;
	}

	public void setServicePeroidId(Integer servicePeroidId) {
		this.servicePeroidId = servicePeroidId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getNowDate() {
		return nowDate;
	}

	public void setNowDate(String nowDate) {
		this.nowDate = nowDate;
	}
	
	
	
	


}

