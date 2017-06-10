package com.ucap.cloud_web.dto;


import java.util.List;

import com.publics.util.page.Query;
import com.ucap.cloud_web.entity.DatabaseInfo;
import com.ucap.cloud_web.entity.DatabaseLink;


/**
* 前台页面传递基础数据<br>
* <b>作者：</b>cuichx<br>
* <b>日期：</b> 2015-10-29 11:05:04 <br>
* <b>版权所有：<b>版权所有(C) 2015<br>
*/
@SuppressWarnings("serial")
public class EarlyDetailRequest extends Query {
	
	//网站标识码
	private String siteCode;
	
	//预警类型
	private Integer type;
	
	//1.首页连不通比例超过80%，2关键栏目连不通比例超过80%，3网站首页更新量为0，4人工检查时发现，5插码挂码
	private Integer updateGradeType;
	
	//查询多个type
	private String types;
	//抽查进度表id
	private Integer spotCheckSchedule;
	
	//问题描述
	private String queDescribe;
	
	//发现时间
	private String scanTime;
	
	//发现日期
	private String scanDate;
	
	//站点名称
	private String siteName;
	
	//站点首页url
	private String homePageUrl;
	
	//站点跳转url
	private String jumpPageUrl;
	
	//预警url
	private String url;
	
	
	private String status;
	
	//是否发送微信预警通知（0-未发送 1-已发送）
	private Integer sendWxStatus;
	//短信发送状态
	private Integer sendSmsStatus;
	//微信发送多个状态
	private String sendWxStatusList;
	//邮件发送状态
	private Integer sendEmailState;
	//邮件发送状态  not
	private Integer sendEmailStateNot;
	//短信发送多个状态
	private String  sendSmsStatusList;	
	//错误词汇
	private String wrongTerm;
	//推荐词汇
	private String expectTerms;
	//检查类型（0：抽查，1：正常合同）
	private Integer checkType;
	//开始时间
	private String beginTime;
	//结束时间
	private String endTime;
	
	private String groupBy;
	
	//人工审核状态（0未审核，1未通过，2通过）
	private Integer checkState;
	
	private List<DatabaseLink>  linkList;
	
	//站点信息集合
	private List<DatabaseInfo> websiteList;
	
	//siteCode 长度
	private Integer siteCodeLength;
	//1站群  2 单站
	private Integer earlyType;
	//1发日报  2不发送
	private Integer isDailyReceive;
	//是否通知下级填报单位  1通知 2不通知
	private Integer isNextAllSite;
	
	private String yesDay;
	private String toDay;
	private String tomDay;
	
	private String orgSiteCode;
	
	private Integer sendOrgFlag;//组织单位有没有发送过预警
	
	private Integer sendTBFlag;//填报单位有没有发送过预警
	
	private Integer servicePeriodId;//服务周期id
	
	private String code ;//对应的tree表中的code
	
	private Integer orgSendStatus;//
	
	private Integer tbSendStatus;
	
	
	
	
	public Integer getTbSendStatus() {
		return tbSendStatus;
	}

	public void setTbSendStatus(Integer tbSendStatus) {
		this.tbSendStatus = tbSendStatus;
	}

	public Integer getOrgSendStatus() {
		return orgSendStatus;
	}

	public void setOrgSendStatus(Integer orgSendStatus) {
		this.orgSendStatus = orgSendStatus;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getServicePeriodId() {
		return servicePeriodId;
	}

	public void setServicePeriodId(Integer servicePeriodId) {
		this.servicePeriodId = servicePeriodId;
	}

	public Integer getSendTBFlag() {
		return sendTBFlag;
	}

	public void setSendTBFlag(Integer sendTBFlag) {
		this.sendTBFlag = sendTBFlag;
	}

	public Integer getSendOrgFlag() {
		return sendOrgFlag;
	}

	public void setSendOrgFlag(Integer sendOrgFlag) {
		this.sendOrgFlag = sendOrgFlag;
	}

	public String getYesDay() {
		return yesDay;
	}

	public void setYesDay(String yesDay) {
		this.yesDay = yesDay;
	}


	public String getToDay() {
		return toDay;
	}

	public void setToDay(String toDay) {
		this.toDay = toDay;
	}

	public String getTomDay() {
		return tomDay;
	}

	public void setTomDay(String tomDay) {
		this.tomDay = tomDay;
	}

	public Integer getCheckState() {
		return checkState;
	}

	public void setCheckState(Integer checkState) {
		this.checkState = checkState;
	}

	public List<DatabaseLink> getLinkList() {
		return linkList;
	}

	public void setLinkList(List<DatabaseLink> linkList) {
		this.linkList = linkList;
	}

	public Integer getUpdateGradeType() {
		return updateGradeType;
	}

	public void setUpdateGradeType(Integer updateGradeType) {
		this.updateGradeType = updateGradeType;
	}

	public String getScanDate() {
		return scanDate;
	}

	public void setScanDate(String scanDate) {
		this.scanDate = scanDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<DatabaseInfo> getWebsiteList() {
		return websiteList;
	}

	public void setWebsiteList(List<DatabaseInfo> websiteList) {
		this.websiteList = websiteList;
	}

	public Integer getSendEmailState() {
		return sendEmailState;
	}

	public void setSendEmailState(Integer sendEmailState) {
		this.sendEmailState = sendEmailState;
	}

	public String getGroupBy() {
		return groupBy;
	}

	public void setGroupBy(String groupBy) {
		this.groupBy = groupBy;
	}

	public String getWrongTerm() {
		return wrongTerm;
	}

	public void setWrongTerm(String wrongTerm) {
		this.wrongTerm = wrongTerm;
	}

	public String getExpectTerms() {
		return expectTerms;
	}

	public void setExpectTerms(String expectTerms) {
		this.expectTerms = expectTerms;
	}

	public String getSendWxStatusList() {
		return sendWxStatusList;
	}

	public void setSendWxStatusList(String sendWxStatusList) {
		this.sendWxStatusList = sendWxStatusList;
	}

	public String getSendSmsStatusList() {
		return sendSmsStatusList;
	}

	public void setSendSmsStatusList(String sendSmsStatusList) {
		this.sendSmsStatusList = sendSmsStatusList;
	}

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

	public Integer getSendSmsStatus() {
		return sendSmsStatus;
	}

	public void setSendSmsStatus(Integer sendSmsStatus) {
		this.sendSmsStatus = sendSmsStatus;
	}

	public Integer getCheckType() {
		return checkType;
	}

	public void setCheckType(Integer checkType) {
		this.checkType = checkType;
	}

	public Integer getSpotCheckSchedule() {
		return spotCheckSchedule;
	}

	public void setSpotCheckSchedule(Integer spotCheckSchedule) {
		this.spotCheckSchedule = spotCheckSchedule;
	}

	public Integer getSendWxStatus() {
		return sendWxStatus;
	}

	public void setSendWxStatus(Integer sendWxStatus) {
		this.sendWxStatus = sendWxStatus;
	}

	public String getHomePageUrl() {
		return homePageUrl;
	}

	public void setHomePageUrl(String homePageUrl) {
		this.homePageUrl = homePageUrl;
	}

	public String getJumpPageUrl() {
		return jumpPageUrl;
	}

	public void setJumpPageUrl(String jumpPageUrl) {
		this.jumpPageUrl = jumpPageUrl;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getQueDescribe() {
		return queDescribe;
	}

	public void setQueDescribe(String queDescribe) {
		this.queDescribe = queDescribe;
	}

	public String getScanTime() {
		return scanTime;
	}

	public void setScanTime(String scanTime) {
		this.scanTime = scanTime;
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

	public Integer getSiteCodeLength() {
		return siteCodeLength;
	}

	public void setSiteCodeLength(Integer siteCodeLength) {
		this.siteCodeLength = siteCodeLength;
	}

	public Integer getEarlyType() {
		return earlyType;
	}

	public void setEarlyType(Integer earlyType) {
		this.earlyType = earlyType;
	}

	public Integer getIsDailyReceive() {
		return isDailyReceive;
	}

	public void setIsDailyReceive(Integer isDailyReceive) {
		this.isDailyReceive = isDailyReceive;
	}

	public Integer getIsNextAllSite() {
		return isNextAllSite;
	}

	public void setIsNextAllSite(Integer isNextAllSite) {
		this.isNextAllSite = isNextAllSite;
	}

	public Integer getSendEmailStateNot() {
		return sendEmailStateNot;
	}

	public void setSendEmailStateNot(Integer sendEmailStateNot) {
		this.sendEmailStateNot = sendEmailStateNot;
	}

	public String getOrgSiteCode() {
		return orgSiteCode;
	}

	public void setOrgSiteCode(String orgSiteCode) {
		this.orgSiteCode = orgSiteCode;
	}


	
}

