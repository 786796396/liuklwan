package com.ucap.cloud_web.dto;


import com.publics.util.page.Query;


/**
* 前台页面传递基础数据<br>
* <b>作者：</b>liujc<br>
* <b>日期：</b> 2016-10-17 15:03:01 <br>
* <b>版权所有：<b>版权所有(C) 2016<br>
*/
@SuppressWarnings("serial")
public class MTaskdetailRequest extends Query {
	private String siteCode;
	private String linkerrprop7;//前七天首页不连通占比
	private String countday;//查询日期
	private String linkerrpropStr;
	private String linkerrpropVal;
	private String noupdatestatusStr;//不更新状态 字段名
	private String noupdatestatus;//不更新状态val
	private String siteName;//网站名称
	
	
	
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public String getLinkerrprop7() {
		return linkerrprop7;
	}
	public void setLinkerrprop7(String linkerrprop7) {
		this.linkerrprop7 = linkerrprop7;
	}
	public String getCountday() {
		return countday;
	}
	public void setCountday(String countday) {
		this.countday = countday;
	}
	public String getLinkerrpropStr() {
		return linkerrpropStr;
	}
	public void setLinkerrpropStr(String linkerrpropStr) {
		this.linkerrpropStr = linkerrpropStr;
	}
	public String getLinkerrpropVal() {
		return linkerrpropVal;
	}
	public void setLinkerrpropVal(String linkerrpropVal) {
		this.linkerrpropVal = linkerrpropVal;
	}
	public String getNoupdatestatusStr() {
		return noupdatestatusStr;
	}
	public void setNoupdatestatusStr(String noupdatestatusStr) {
		this.noupdatestatusStr = noupdatestatusStr;
	}
	public String getNoupdatestatus() {
		return noupdatestatus;
	}
	public void setNoupdatestatus(String noupdatestatus) {
		this.noupdatestatus = noupdatestatus;
	}
	public String getSiteCode() {
		return siteCode;
	}
	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}
	

}

