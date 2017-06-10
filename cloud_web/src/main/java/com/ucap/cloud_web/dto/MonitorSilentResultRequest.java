package com.ucap.cloud_web.dto;


import com.publics.util.page.Query;


/*** 前台页面传递基础数据<br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-11-04 11:24:50 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@SuppressWarnings("serial")public class MonitorSilentResultRequest extends Query {
	
	private String name;
	private String siteCode;
	private String url;  // 网址
	private String scanDate;
	private String startDate;
	private String endDate;
	private String riskNum;  // 风险值
	private String fragilityNum;  //脆弱性问题数
	private String trojanNum;  //挂马问题数
	private String tamperNum;  //变更/篡改问题数
	private String darkNhainNum;  //网站暗链数
	private String leakedNum;  //内容泄漏数
	private int resType; //类型（默认0昨天，1一周，2两周，3一个月）
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSiteCode() {
		return siteCode;
	}
	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getScanDate() {
		return scanDate;
	}
	public void setScanDate(String scanDate) {
		this.scanDate = scanDate;
	}
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
	public String getRiskNum() {
		return riskNum;
	}
	public void setRiskNum(String riskNum) {
		this.riskNum = riskNum;
	}
	public String getFragilityNum() {
		return fragilityNum;
	}
	public void setFragilityNum(String fragilityNum) {
		this.fragilityNum = fragilityNum;
	}
	public String getTrojanNum() {
		return trojanNum;
	}
	public void setTrojanNum(String trojanNum) {
		this.trojanNum = trojanNum;
	}
	public String getTamperNum() {
		return tamperNum;
	}
	public void setTamperNum(String tamperNum) {
		this.tamperNum = tamperNum;
	}
	public String getDarkNhainNum() {
		return darkNhainNum;
	}
	public void setDarkNhainNum(String darkNhainNum) {
		this.darkNhainNum = darkNhainNum;
	}
	public String getLeakedNum() {
		return leakedNum;
	}
	public void setLeakedNum(String leakedNum) {
		this.leakedNum = leakedNum;
	}
	public int getResType() {
		return resType;
	}
	public void setResType(int resType) {
		this.resType = resType;
	}
	
}

