package com.ucap.cloud_web.dto;

import java.util.Date;
import java.util.List;
import com.publics.util.page.Query;
import com.ucap.cloud_web.entity.DatabaseInfo;

/**
 * 前台页面传递基础数据<br>
 * <b>作者：</b>cuichx<br>
 * <b>日期：</b> 2015-10-29 11:11:37 <br>
 * <b>版权所有：<b>版权所有(C) 2015<br>
 */
@SuppressWarnings("serial")
public class ReportManageLogRequest extends Query {

	private Integer servicePeriodId;// 期号id
	private Object[] siteCodeList;// 网站标识码数据
	private String siteCode;// 网站标识码
	private String siteName;// 网站名称
	private String sendTime;// 报告上次发送时间
	private Integer sendState;// 报告上次发送的状态（0否、1是）
	private Integer dicSiteId;// 站点类型id
	private Integer dicComboId;// 套餐类型id
	private String pdfUrl;// 预览pdf的url
	private Date serviceBeginTime;//服务开始时间
	
	private Integer successNum;//每期报告发送成功次数
	private Integer errorNum;//每期报告发送失败次数
	private Integer siteCodeNum;//每期的发送报告的网站个数
	
	private String createTime;//YYYY-MM-DD(用于创建时间的模糊查询)
	//是否已经发送微信通知（0-否 1-是）
	private Integer sendWxState;
	
	private  List<DatabaseInfo> currentNextSiteCode;
	//每个周期的期数
	private Integer periodNum;
	//每个周期的开始时间
	private Date startTime;
	
	

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Integer getPeriodNum() {
		return periodNum;
	}

	public void setPeriodNum(Integer periodNum) {
		this.periodNum = periodNum;
	}

	public List<DatabaseInfo> getCurrentNextSiteCode() {
		return currentNextSiteCode;
	}

	public void setCurrentNextSiteCode(List<DatabaseInfo> currentNextSiteCode) {
		this.currentNextSiteCode = currentNextSiteCode;
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

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public Integer getSendState() {
		return sendState;
	}

	public void setSendState(Integer sendState) {
		this.sendState = sendState;
	}

	public Integer getDicSiteId() {
		return dicSiteId;
	}

	public void setDicSiteId(Integer dicSiteId) {
		this.dicSiteId = dicSiteId;
	}

	public Integer getDicComboId() {
		return dicComboId;
	}

	public void setDicComboId(Integer dicComboId) {
		this.dicComboId = dicComboId;
	}

	public Object[] getSiteCodeList() {
		return siteCodeList;
	}

	public void setSiteCodeList(Object[] siteCodeList) {
		this.siteCodeList = siteCodeList;
	}

	public String getPdfUrl() {
		return pdfUrl;
	}

	public void setPdfUrl(String pdfUrl) {
		this.pdfUrl = pdfUrl;
	}

	public Integer getSuccessNum() {
		return successNum;
	}

	public void setSuccessNum(Integer successNum) {
		this.successNum = successNum;
	}

	public Integer getErrorNum() {
		return errorNum;
	}

	public void setErrorNum(Integer errorNum) {
		this.errorNum = errorNum;
	}

	public Integer getSiteCodeNum() {
		return siteCodeNum;
	}

	public void setSiteCodeNum(Integer siteCodeNum) {
		this.siteCodeNum = siteCodeNum;
	}

	public Date getServiceBeginTime() {
		return serviceBeginTime;
	}

	public void setServiceBeginTime(Date serviceBeginTime) {
		this.serviceBeginTime = serviceBeginTime;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Integer getSendWxState() {
		return sendWxState;
	}

	public void setSendWxState(Integer sendWxState) {
		this.sendWxState = sendWxState;
	}
	
}
