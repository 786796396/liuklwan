package com.ucap.cloud_web.dto;


import com.publics.util.page.Query;


/*** 前台页面传递基础数据<br>* <b>作者：</b>qingjy<br>* <b>日期：</b> 2016-11-24 21:30:34 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@SuppressWarnings("serial")public class ReportCollectResultRequest extends Query {
	private String siteCode;
	private String servicePeriodId;
	public String getSiteCode() {
		return siteCode;
	}
	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}
	public String getServicePeriodId() {
		return servicePeriodId;
	}
	public void setServicePeriodId(String servicePeriodId) {
		this.servicePeriodId = servicePeriodId;
	}

}

