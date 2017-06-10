package com.ucap.cloud_web.dto;


import com.publics.util.page.Query;


/*** 前台页面传递基础数据<br>* <b>作者：</b>liujc<br>* <b>日期：</b> 2016-07-20 14:57:52 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@SuppressWarnings("serial")public class ConfigEarlyRequest extends Query {
	private String siteCode;
	//siteCode 长度
	private Integer siteCodeLength;
	//1站群  2 单站
	private Integer earlyType;
	//1发日报  2不发送
	private Integer isDailyReceive;
	//是否通知下级填报单位  1通知 2不通知
	private Integer isNextAllSite;
	
	public String getSiteCode() {
		return siteCode;
	}
	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}
	public Integer getEarlyType() {
		return earlyType;
	}
	public void setEarlyType(Integer earlyType) {
		this.earlyType = earlyType;
	}
	public Integer getSiteCodeLength() {
		return siteCodeLength;
	}
	public void setSiteCodeLength(Integer siteCodeLength) {
		this.siteCodeLength = siteCodeLength;
	}
	public Integer getIsDailyReceive() {
		return isDailyReceive;
	}
	public void setIsDailyReceive(Integer isDailyReceive) {
		this.isDailyReceive = isDailyReceive;
	}
	public Integer getIsNextAllSite() {
		return isNextAllSite;
	}
	public void setIsNextAllSite(Integer isNextAllSite) {
		this.isNextAllSite = isNextAllSite;
	}

}

