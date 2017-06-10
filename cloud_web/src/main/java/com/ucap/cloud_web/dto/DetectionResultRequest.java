package com.ucap.cloud_web.dto;


import java.util.List;
import com.publics.util.page.Query;
import com.ucap.cloud_web.entity.DatabaseInfo;

/*** 前台页面传递基础数据<br>* <b>作者：</b>SunJiang<br>* <b>日期：</b> 2015-11-20 16:56:11 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/@SuppressWarnings("serial")public class DetectionResultRequest extends Query {
	/**
	 * 网站标识码
	 */
	private String siteCode;
	/**
	 * 扫描时间
	 */
	private String scanDate;
	/**
	 * list数组
	 */
	private List<DatabaseInfo> ids;
	/**
	 * 类型
	 */
	private Integer type;
	/**
	 * 开始时间
	 */
	private String beginScanDate;
	/**
	 * 结束时间
	 */
	private String endScanDate;
	/**
	 * 类型数组
	 */
	private List<Object> typelist;
	


	public List<DatabaseInfo> getIds() {
		return ids;
	}

	public void setIds(List<DatabaseInfo> ids) {
		this.ids = ids;
	}

	public List<Object> getTypelist() {
		return typelist;
	}

	public void setTypelist(List<Object> typelist) {
		this.typelist = typelist;
	}

	public String getBeginScanDate() {
		return beginScanDate;
	}

	public void setBeginScanDate(String beginScanDate) {
		this.beginScanDate = beginScanDate;
	}

	public String getEndScanDate() {
		return endScanDate;
	}

	public void setEndScanDate(String endScanDate) {
		this.endScanDate = endScanDate;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	

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


}

