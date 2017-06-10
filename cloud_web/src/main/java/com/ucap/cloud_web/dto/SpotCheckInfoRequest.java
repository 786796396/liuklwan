package com.ucap.cloud_web.dto;


import com.publics.util.page.Query;


/*** 前台页面传递基础数据<br>* <b>作者：</b>yuangw@ucap.com.cn<br>* <b>日期：</b> 2016-04-19 11:16:09 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@SuppressWarnings("serial")public class SpotCheckInfoRequest extends Query {

	private String siteCode;//网站标识码
	
	private Integer contractInfoId;//合同信息表id
	

	public Integer getContractInfoId() {
		return contractInfoId;
	}
	public void setContractInfoId(Integer contractInfoId) {
		this.contractInfoId = contractInfoId;
	}
	public String getSiteCode() {
		return siteCode;
	}
	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}
}

