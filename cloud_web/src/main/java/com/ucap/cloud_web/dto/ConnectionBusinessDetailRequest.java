package com.ucap.cloud_web.dto;

import com.publics.util.page.Query;

/**
 * 前台页面传递基础数据<br>
 * <b>作者：</b>Nora<br>
 * <b>日期：</b> 2015-10-29 11:19:33 <br>
 * <b>版权所有：<b>版权所有(C) 2015<br>
 */
@SuppressWarnings("serial")
public class ConnectionBusinessDetailRequest extends Query {

	// 网站标识码
	private String siteCode;

	// 扫描日期（yyyy-dd-mm）
	private String scanDate;

	// 连通状态
	private int state;
	//问题代码
	private String questionCode;

	private String startDate;
	private String endDate;
	
	private String systemName;
	private String url;
	private String succeedSum;
	
	private String scanTime2;//最大扫描时间
	
	//分组
	private String groupBy;
	




	public String getScanTime2() {
		return scanTime2;
	}

	public void setScanTime2(String scanTime2) {
		this.scanTime2 = scanTime2;
	}

	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSucceedSum() {
		return succeedSum;
	}

	public void setSucceedSum(String succeedSum) {
		this.succeedSum = succeedSum;
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

	public String getQuestionCode() {
		return questionCode;
	}

	public void setQuestionCode(String questionCode) {
		this.questionCode = questionCode;
	}

	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	public String getScanDate() {
		return scanDate;
	}

	public void setScanDate(String scanDate) {
		this.scanDate = scanDate;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getGroupBy() {
		return groupBy;
	}

	public void setGroupBy(String groupBy) {
		this.groupBy = groupBy;
	}


}
