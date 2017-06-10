package com.ucap.cloud_web.dto;

import com.publics.util.page.Query;

/**
 * 前台页面传递基础数据<br>
 * <b>作者：</b>Na.Y<br>
 * <b>日期：</b> 2016-09-13 14:51:28 <br>
 * <b>版权所有：<b>版权所有(C) 2016<br>
 */
@SuppressWarnings("serial")
public class DetectionPeroidCountRequest extends Query {

	// 网站标识码
	private String siteCode;

	// 服务周期Id
	private Integer servicePeroidId;
	private Integer siteCheckResult;
	
	//当前日期
	private String scanDate;
	
	
	
	public String getScanDate() {
		return scanDate;
	}

	public void setScanDate(String scanDate) {
		this.scanDate = scanDate;
	}

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

	public Integer getSiteCheckResult() {
		return siteCheckResult;
	}

	public void setSiteCheckResult(Integer siteCheckResult) {
		this.siteCheckResult = siteCheckResult;
	}

}
