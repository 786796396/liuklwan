package com.ucap.cloud_web.dto;


import java.util.List;

import com.publics.util.page.Query;


/*** 前台页面传递基础数据<br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2015-10-29 11:11:46 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/@SuppressWarnings("serial")public class ReportWordResultRequest extends Query {
	
	private Integer servicePeriodId;//期号Id
	private String siteCode;//网站标识码
	private Integer type;//报告类型（0：抽查，1：正常合同）
	
	private String pdfUrl;//pdf地址
	private Integer periodNum;//周期期数
	private String groupBy;
	private String upperOrgCode;//上级组织单位编码
	private Integer layerType;//网站类别/网站类别
	private String aliasName;//文件名
	private Integer isRead;//是否已读
	private Integer checkReportResult;//反馈状态
	
	private List<String> list;
	private Integer level;
	private String code;
	private String orgSiteCode;
	
	
	public String getOrgSiteCode() {
		return orgSiteCode;
	}
	public void setOrgSiteCode(String orgSiteCode) {
		this.orgSiteCode = orgSiteCode;
	}
	public List<String> getList() {
		return list;
	}
	public void setList(List<String> list) {
		this.list = list;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getPdfUrl() {
		return pdfUrl;
	}
	public void setPdfUrl(String pdfUrl) {
		this.pdfUrl = pdfUrl;
	}
	public Integer getPeriodNum() {
		return periodNum;
	}
	public void setPeriodNum(Integer periodNum) {
		this.periodNum = periodNum;
	}
	public Integer getServicePeriodId() {
		return servicePeriodId;
	}
	public void setServicePeriodId(Integer servicePeriodId) {
		this.servicePeriodId = servicePeriodId;
	}
	public String getSiteCode() {
		return siteCode;
	}
	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getGroupBy() {
		return groupBy;
	}
	public void setGroupBy(String groupBy) {
		this.groupBy = groupBy;
	}
	public String getUpperOrgCode() {
		return upperOrgCode;
	}
	public void setUpperOrgCode(String upperOrgCode) {
		this.upperOrgCode = upperOrgCode;
	}
	public Integer getLayerType() {
		return layerType;
	}
	public void setLayerType(Integer layerType) {
		this.layerType = layerType;
	}
	public String getAliasName() {
		return aliasName;
	}
	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}
	public Integer getIsRead() {
		return isRead;
	}
	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}
	public Integer getCheckReportResult() {
		return checkReportResult;
	}
	public void setCheckReportResult(Integer checkReportResult) {
		this.checkReportResult = checkReportResult;
	}
}

