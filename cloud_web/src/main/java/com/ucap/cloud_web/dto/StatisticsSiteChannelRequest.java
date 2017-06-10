package com.ucap.cloud_web.dto;


import com.publics.util.page.Query;


/*** 前台页面传递基础数据<br>* <b>作者：</b>qingjy<br>* <b>日期：</b> 2017-05-03 19:08:59 <br>* <b>版权所有：<b>版权所有(C) 2017<br>*/@SuppressWarnings("serial")public class StatisticsSiteChannelRequest extends Query {

	private Integer isBm;

	private Integer isBigdata;

	private String tallyDate;

	private String orgSiteCode;

	public Integer getIsBm() {
		return isBm;
	}

	public void setIsBm(Integer isBm) {
		this.isBm = isBm;
	}

	public Integer getIsBigdata() {
		return isBigdata;
	}

	public void setIsBigdata(Integer isBigdata) {
		this.isBigdata = isBigdata;
	}

	public String getTallyDate() {
		return tallyDate;
	}

	public void setTallyDate(String tallyDate) {
		this.tallyDate = tallyDate;
	}

	public String getOrgSiteCode() {
		return orgSiteCode;
	}

	public void setOrgSiteCode(String orgSiteCode) {
		this.orgSiteCode = orgSiteCode;
	}

}

