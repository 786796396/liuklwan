package com.ucap.cloud_web.dto;


import com.publics.util.page.Query;


/*** 前台页面传递基础数据<br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2017-03-24 10:15:11 <br>* <b>版权所有：<b>版权所有(C) 2017<br>*/@SuppressWarnings("serial")public class DetectionPeriodSiteRequest extends Query {
	
	private String siteCode;//网站标识码
	
	private String nowDate;//日期
	//服务周期Id
	private Integer servicePeroidId;

	
	
	public Integer getServicePeroidId() {
		return servicePeroidId;
	}

	public void setServicePeroidId(Integer servicePeroidId) {
		this.servicePeroidId = servicePeroidId;
	}

	public String getNowDate() {
		return nowDate;
	}

	public void setNowDate(String nowDate) {
		this.nowDate = nowDate;
	}

	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}
	
	
	
	
	
	
	


}

