package com.ucap.cloud_web.dto;



import com.publics.util.page.Query;


/*** 前台页面传递基础数据<br>* <b>作者：</b>yuangw@ucap.com.cn<br>* <b>日期：</b> 2016-04-19 11:16:29 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@SuppressWarnings("serial")public class SpotCheckResultRequest extends Query {
	
	private String orgSiteCode;//组织单位siteCode
	private Integer spotCheckSchedule;//抽查批次
	private String[] siteCodes;//填报单位siteCode数组
	private String siteCode;//填报单位sitecode
	private Integer state;//抽查状态（-1:未提交，0：未启动，1:检查中，2：扫描完成 ，3：报告完成）
	
	private Integer checkReportResult;//报告状态
	
	private Integer checkReportResults;//报告状态，是否[下载报告数据]栏目专用
	
	//统计每个省的站点个数
	private Integer siteNum;
	//省名称
	private String province;

	private String keyWord;//关键字查询--查询网站名称
	
	public Integer getCheckReportResult() {
		return checkReportResult;
	}

	public void setCheckReportResult(Integer checkReportResult) {
		this.checkReportResult = checkReportResult;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public Integer getSiteNum() {
		return siteNum;
	}

	public void setSiteNum(Integer siteNum) {
		this.siteNum = siteNum;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getOrgSiteCode() {
		return orgSiteCode;
	}

	public void setOrgSiteCode(String orgSiteCode) {
		this.orgSiteCode = orgSiteCode;
	}

	public Integer getSpotCheckSchedule() {
		return spotCheckSchedule;
	}

	public void setSpotCheckSchedule(Integer spotCheckSchedule) {
		this.spotCheckSchedule = spotCheckSchedule;
	}

	public String[] getSiteCodes() {
		return siteCodes;
	}

	public void setSiteCodes(String[] siteCodes) {
		this.siteCodes = siteCodes;
	}

	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	public Integer getCheckReportResults() {
		return checkReportResults;
	}

	public void setCheckReportResults(Integer checkReportResults) {
		this.checkReportResults = checkReportResults;
	}

}

