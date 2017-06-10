package com.ucap.cloud_web.dto;


import com.publics.util.page.Query;


/*** 前台页面传递基础数据<br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2017-03-14 14:39:10 <br>* <b>版权所有：<b>版权所有(C) 2017<br>*/@SuppressWarnings("serial")public class EarlyDetailTempRequest extends Query {
	
	private String siteCode;//网站标识码
	private Integer type;//预警类型  1-首页不连通
	private Integer contractInfoId;//合同id
	//是否发送预警 1-是；2否
	private Integer sendStatus;

	//组织单位发送预警类型（1：本组织，2：下级填报单位），0：填报单位本身
	private Integer orgTbStatus;
	
	private String siteName;//网站名称
	private String lastScanTime;//最后一次连不通扫描时间
	
	
	private String scanDate;//日期
	private String notErrorCode;//非错误编码
	
	
	
	public String getNotErrorCode() {
		return notErrorCode;
	}
	public void setNotErrorCode(String notErrorCode) {
		this.notErrorCode = notErrorCode;
	}
	public String getScanDate() {
		return scanDate;
	}
	public void setScanDate(String scanDate) {
		this.scanDate = scanDate;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public String getLastScanTime() {
		return lastScanTime;
	}
	public void setLastScanTime(String lastScanTime) {
		this.lastScanTime = lastScanTime;
	}
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
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getOrgTbStatus() {
		return orgTbStatus;
	}
	public void setOrgTbStatus(Integer orgTbStatus) {
		this.orgTbStatus = orgTbStatus;
	}
	public Integer getSendStatus() {
		return sendStatus;
	}
	public void setSendStatus(Integer sendStatus) {
		this.sendStatus = sendStatus;
	}
	

}

