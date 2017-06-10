package com.ucap.cloud_web.entity;


import java.util.Date;


/*** <br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2017-03-24 10:17:35 <br>* <b>版权所有：<b>版权所有(C) 2017<br>*/public class DetectionPeriodOrg {


	//	private int id;
	//网站标识码	private String siteCode;
	//服务周期Id	private int servicePeroidId;
	//类型（0,全部【除了关停与例外】，2本级部门，3下属单位，6其他）	private int type;
	//实际开始日期（yyyy-mm-dd）	private String startDate;
	//实际结束日期（yyyy-mm-dd）	private String endDate;
	//全站死链数量	private int linkAll;
	//全站有死链站点数量	private int linkAllSite;
	//空白栏目数量	private int securityBlank;
	//有空白栏目站点数量	private int securityBlankSite;
	//互动回应情况数量	private int securityResponse;
	//有互动回应情况站点数量	private int securityResponseSite;
	//服务不实用：办事指南数量	private int serviceGuide;
	//服务不实用：办事指南站点数量	private int serviceGuideSite;
	//服务不实用：附件下载数量	private int serviceDownload;
	//服务不实用：附件下载站点数量	private int serviceDownloadSite;
	//服务不实用：其他数量	private int serviceOther;
	//服务不实用：其他站点数量	private int serviceOtherSite;
	//服务不实用：总和	private int securityService;
	//有服务不实用站点数量	private int securityServiceSite;
	//严重错误：虚假伪造内容数量	private int seriousUnreal;
	//严重错误：虚假伪造内容数量	private int seriousUnrealSite;
	//严重错误：反动暴力或者色情数量	private int seriousViolence;
	//严重错误：反动暴力或者色情站点数量	private int seriousViolenceSite;
	//严重错误：其他	private int seriousOthers;
	//严重错误：其他站点数量	private int seriousOthersSite;
	//严重错误：总和	private int securityFatalError;
	//严重错误：站点数量总和	private int securityFatalSite;
	//创建时间	private Date createTime;
	//修好时间	private Date modifyTime;
	/** set  */	public void setId(int id){
		this.id=id;
	}
	/** get  */	public int getId(){
		return id;
	}
	/** set 网站标识码 */	public void setSiteCode(String siteCode){
		this.siteCode=siteCode;
	}
	/** get 网站标识码 */	public String getSiteCode(){
		return siteCode;
	}
	/** set 服务周期Id */	public void setServicePeroidId(int servicePeroidId){
		this.servicePeroidId=servicePeroidId;
	}
	/** get 服务周期Id */	public int getServicePeroidId(){
		return servicePeroidId;
	}
	/** set 类型（0,全部【除了关停与例外】，2本级部门，3下属单位，6其他） */	public void setType(int type){
		this.type=type;
	}
	/** get 类型（0,全部【除了关停与例外】，2本级部门，3下属单位，6其他） */	public int getType(){
		return type;
	}
	/** set 实际开始日期（yyyy-mm-dd） */	public void setStartDate(String startDate){
		this.startDate=startDate;
	}
	/** get 实际开始日期（yyyy-mm-dd） */	public String getStartDate(){
		return startDate;
	}
	/** set 实际结束日期（yyyy-mm-dd） */	public void setEndDate(String endDate){
		this.endDate=endDate;
	}
	/** get 实际结束日期（yyyy-mm-dd） */	public String getEndDate(){
		return endDate;
	}
	/** set 全站死链数量 */	public void setLinkAll(int linkAll){
		this.linkAll=linkAll;
	}
	/** get 全站死链数量 */	public int getLinkAll(){
		return linkAll;
	}
	/** set 全站有死链站点数量 */	public void setLinkAllSite(int linkAllSite){
		this.linkAllSite=linkAllSite;
	}
	/** get 全站有死链站点数量 */	public int getLinkAllSite(){
		return linkAllSite;
	}
	/** set 空白栏目数量 */	public void setSecurityBlank(int securityBlank){
		this.securityBlank=securityBlank;
	}
	/** get 空白栏目数量 */	public int getSecurityBlank(){
		return securityBlank;
	}
	/** set 有空白栏目站点数量 */	public void setSecurityBlankSite(int securityBlankSite){
		this.securityBlankSite=securityBlankSite;
	}
	/** get 有空白栏目站点数量 */	public int getSecurityBlankSite(){
		return securityBlankSite;
	}
	/** set 互动回应情况数量 */	public void setSecurityResponse(int securityResponse){
		this.securityResponse=securityResponse;
	}
	/** get 互动回应情况数量 */	public int getSecurityResponse(){
		return securityResponse;
	}
	/** set 有互动回应情况站点数量 */	public void setSecurityResponseSite(int securityResponseSite){
		this.securityResponseSite=securityResponseSite;
	}
	/** get 有互动回应情况站点数量 */	public int getSecurityResponseSite(){
		return securityResponseSite;
	}
	/** set 服务不实用：办事指南数量 */	public void setServiceGuide(int serviceGuide){
		this.serviceGuide=serviceGuide;
	}
	/** get 服务不实用：办事指南数量 */	public int getServiceGuide(){
		return serviceGuide;
	}
	/** set 服务不实用：办事指南站点数量 */	public void setServiceGuideSite(int serviceGuideSite){
		this.serviceGuideSite=serviceGuideSite;
	}
	/** get 服务不实用：办事指南站点数量 */	public int getServiceGuideSite(){
		return serviceGuideSite;
	}
	/** set 服务不实用：附件下载数量 */	public void setServiceDownload(int serviceDownload){
		this.serviceDownload=serviceDownload;
	}
	/** get 服务不实用：附件下载数量 */	public int getServiceDownload(){
		return serviceDownload;
	}
	/** set 服务不实用：附件下载站点数量 */	public void setServiceDownloadSite(int serviceDownloadSite){
		this.serviceDownloadSite=serviceDownloadSite;
	}
	/** get 服务不实用：附件下载站点数量 */	public int getServiceDownloadSite(){
		return serviceDownloadSite;
	}
	/** set 服务不实用：其他数量 */	public void setServiceOther(int serviceOther){
		this.serviceOther=serviceOther;
	}
	/** get 服务不实用：其他数量 */	public int getServiceOther(){
		return serviceOther;
	}
	/** set 服务不实用：其他站点数量 */	public void setServiceOtherSite(int serviceOtherSite){
		this.serviceOtherSite=serviceOtherSite;
	}
	/** get 服务不实用：其他站点数量 */	public int getServiceOtherSite(){
		return serviceOtherSite;
	}
	/** set 服务不实用：总和 */	public void setSecurityService(int securityService){
		this.securityService=securityService;
	}
	/** get 服务不实用：总和 */	public int getSecurityService(){
		return securityService;
	}
	/** set 有服务不实用站点数量 */	public void setSecurityServiceSite(int securityServiceSite){
		this.securityServiceSite=securityServiceSite;
	}
	/** get 有服务不实用站点数量 */	public int getSecurityServiceSite(){
		return securityServiceSite;
	}
	/** set 严重错误：虚假伪造内容数量 */	public void setSeriousUnreal(int seriousUnreal){
		this.seriousUnreal=seriousUnreal;
	}
	/** get 严重错误：虚假伪造内容数量 */	public int getSeriousUnreal(){
		return seriousUnreal;
	}
	/** set 严重错误：虚假伪造内容数量 */	public void setSeriousUnrealSite(int seriousUnrealSite){
		this.seriousUnrealSite=seriousUnrealSite;
	}
	/** get 严重错误：虚假伪造内容数量 */	public int getSeriousUnrealSite(){
		return seriousUnrealSite;
	}
	/** set 严重错误：反动暴力或者色情数量 */	public void setSeriousViolence(int seriousViolence){
		this.seriousViolence=seriousViolence;
	}
	/** get 严重错误：反动暴力或者色情数量 */	public int getSeriousViolence(){
		return seriousViolence;
	}
	/** set 严重错误：反动暴力或者色情站点数量 */	public void setSeriousViolenceSite(int seriousViolenceSite){
		this.seriousViolenceSite=seriousViolenceSite;
	}
	/** get 严重错误：反动暴力或者色情站点数量 */	public int getSeriousViolenceSite(){
		return seriousViolenceSite;
	}
	/** set 严重错误：其他 */	public void setSeriousOthers(int seriousOthers){
		this.seriousOthers=seriousOthers;
	}
	/** get 严重错误：其他 */	public int getSeriousOthers(){
		return seriousOthers;
	}
	/** set 严重错误：其他站点数量 */	public void setSeriousOthersSite(int seriousOthersSite){
		this.seriousOthersSite=seriousOthersSite;
	}
	/** get 严重错误：其他站点数量 */	public int getSeriousOthersSite(){
		return seriousOthersSite;
	}
	/** set 严重错误：总和 */	public void setSecurityFatalError(int securityFatalError){
		this.securityFatalError=securityFatalError;
	}
	/** get 严重错误：总和 */	public int getSecurityFatalError(){
		return securityFatalError;
	}
	/** set 严重错误：站点数量总和 */	public void setSecurityFatalSite(int securityFatalSite){
		this.securityFatalSite=securityFatalSite;
	}
	/** get 严重错误：站点数量总和 */	public int getSecurityFatalSite(){
		return securityFatalSite;
	}
	/** set 创建时间 */	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}
	/** get 创建时间 */	public Date getCreateTime(){
		return createTime;
	}
	/** set 修好时间 */	public void setModifyTime(Date modifyTime){
		this.modifyTime=modifyTime;
	}
	/** get 修好时间 */	public Date getModifyTime(){
		return modifyTime;
	}
}

