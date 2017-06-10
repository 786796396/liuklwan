package com.ucap.cloud_web.dto;


import java.util.Date;
import java.util.List;

import com.publics.util.page.Query;


/**
* 前台页面传递基础数据<br>
* <b>作者：</b>cuichx<br>
* <b>日期：</b> 2015-12-18 09:26:33 <br>
* <b>版权所有：<b>版权所有(C) 2015<br>
*/
@SuppressWarnings("serial")
public class AccountBindInfoRequest extends Query {
	
	private Integer id;
	//用户微信号的唯一标识
	private String openId;
	
	//是否收费（0-免费  1-收费）
	private Integer isCustomer;
	//客户名称
	private String companyName;
	//站点名称
	private String siteName;
	//客户编号
	private String siteCode;
	
	//0-解绑 1-绑定
	private Integer status;
	
	//站群报告通知（0-不发送 1-发送）
	private Integer siteListReportStatus;
	
	//站群预警通知（0-不发送 1-发送）
	private Integer siteListEarlyStatus;
	
	//本级门户预警通知（0-不发送 1-发送）
	private Integer localhostEarlyStatus;
	
	//本级门户报告通知（0-不发送 1-发送）
	private Integer localhostReportStatus;
	
	//本级门户网站网站标识码
	private String localMHSiteCode;
	
	//本级门户网站网站名称
	private String localMHSiteName;
	
	//套餐名称
	private String comboName;
	
	//服务开始时间
	private Date serviceBeginTime;
	
	//服务结束时间
	private Date serviceEndTime;
	
	//站点信息表id
	private Integer websiteId;
	
	//套餐表id
	private Integer dicComboId;
	
	//新预警总数
	private Integer newEarlyNum;
	
	private List<Object> paramList;
	//网站数
	private Integer siteNum;
	//报告数
	private Integer reportNum;
	//发送失败数
	private Integer errorNum;
	//发送成功数
	private Integer successNum;
	
	private String notOpenId;
	
	
	
	public Integer getIsCustomer() {
		return isCustomer;
	}
	public void setIsCustomer(Integer isCustomer) {
		this.isCustomer = isCustomer;
	}
	public String getNotOpenId() {
		return notOpenId;
	}
	public void setNotOpenId(String notOpenId) {
		this.notOpenId = notOpenId;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public String getSiteCode() {
		return siteCode;
	}
	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getSiteListReportStatus() {
		return siteListReportStatus;
	}
	public void setSiteListReportStatus(Integer siteListReportStatus) {
		this.siteListReportStatus = siteListReportStatus;
	}
	public Integer getSiteListEarlyStatus() {
		return siteListEarlyStatus;
	}
	public void setSiteListEarlyStatus(Integer siteListEarlyStatus) {
		this.siteListEarlyStatus = siteListEarlyStatus;
	}
	public Integer getLocalhostEarlyStatus() {
		return localhostEarlyStatus;
	}
	public void setLocalhostEarlyStatus(Integer localhostEarlyStatus) {
		this.localhostEarlyStatus = localhostEarlyStatus;
	}
	public Integer getLocalhostReportStatus() {
		return localhostReportStatus;
	}
	public void setLocalhostReportStatus(Integer localhostReportStatus) {
		this.localhostReportStatus = localhostReportStatus;
	}
	public String getLocalMHSiteCode() {
		return localMHSiteCode;
	}
	public void setLocalMHSiteCode(String localMHSiteCode) {
		this.localMHSiteCode = localMHSiteCode;
	}
	public String getLocalMHSiteName() {
		return localMHSiteName;
	}
	public void setLocalMHSiteName(String localMHSiteName) {
		this.localMHSiteName = localMHSiteName;
	}
	public String getComboName() {
		return comboName;
	}
	public void setComboName(String comboName) {
		this.comboName = comboName;
	}
	public Date getServiceBeginTime() {
		return serviceBeginTime;
	}
	public void setServiceBeginTime(Date serviceBeginTime) {
		this.serviceBeginTime = serviceBeginTime;
	}
	public Date getServiceEndTime() {
		return serviceEndTime;
	}
	public void setServiceEndTime(Date serviceEndTime) {
		this.serviceEndTime = serviceEndTime;
	}
	public Integer getWebsiteId() {
		return websiteId;
	}
	public void setWebsiteId(Integer websiteId) {
		this.websiteId = websiteId;
	}
	public Integer getDicComboId() {
		return dicComboId;
	}
	public void setDicComboId(Integer dicComboId) {
		this.dicComboId = dicComboId;
	}
	public Integer getNewEarlyNum() {
		return newEarlyNum;
	}
	public void setNewEarlyNum(Integer newEarlyNum) {
		this.newEarlyNum = newEarlyNum;
	}
	public List<Object> getParamList() {
		return paramList;
	}
	public void setParamList(List<Object> paramList) {
		this.paramList = paramList;
	}
	public Integer getSiteNum() {
		return siteNum;
	}
	public void setSiteNum(Integer siteNum) {
		this.siteNum = siteNum;
	}
	public Integer getReportNum() {
		return reportNum;
	}
	public void setReportNum(Integer reportNum) {
		this.reportNum = reportNum;
	}
	public Integer getErrorNum() {
		return errorNum;
	}
	public void setErrorNum(Integer errorNum) {
		this.errorNum = errorNum;
	}
	public Integer getSuccessNum() {
		return successNum;
	}
	public void setSuccessNum(Integer successNum) {
		this.successNum = successNum;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
}

