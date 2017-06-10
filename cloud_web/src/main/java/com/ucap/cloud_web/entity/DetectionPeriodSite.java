package com.ucap.cloud_web.entity;


import java.util.Date;


/*** <br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2017-03-24 10:15:11 <br>* <b>版权所有：<b>版权所有(C) 2017<br>*/public class DetectionPeriodSite {


	//	private int id;
	//网站标识码	private String siteCode;
	//服务周期Id	private int servicePeroidId;
	//实际开始日期（yyyy-mm-dd）	private String startDate;
	//实际结束日期（yyyy-mm-dd）	private String endDate;
	//全站死链数量	private int linkAll;
	//空白栏目数量	private int securityBlank;
	//互动回应情况数量	private int securityResponse;
	//服务不实用：办事指南数量	private int serviceGuide;
	//服务不实用：附件下载数量	private int serviceDownload;
	//服务不实用：其他	private int serviceOther;
	//服务不实用：总和	private int securityService;
	//严重错误：虚假伪造内容数量	private int seriousUnreal;
	//严重错误：反动暴力或者色情数量	private int seriousViolence;
	//严重错误：其他	private int seriousOthers;
	//严重错误：总和	private int securityFatalError;
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
	/** set 空白栏目数量 */	public void setSecurityBlank(int securityBlank){
		this.securityBlank=securityBlank;
	}
	/** get 空白栏目数量 */	public int getSecurityBlank(){
		return securityBlank;
	}
	/** set 互动回应情况数量 */	public void setSecurityResponse(int securityResponse){
		this.securityResponse=securityResponse;
	}
	/** get 互动回应情况数量 */	public int getSecurityResponse(){
		return securityResponse;
	}
	/** set 服务不实用：办事指南数量 */	public void setServiceGuide(int serviceGuide){
		this.serviceGuide=serviceGuide;
	}
	/** get 服务不实用：办事指南数量 */	public int getServiceGuide(){
		return serviceGuide;
	}
	/** set 服务不实用：附件下载数量 */	public void setServiceDownload(int serviceDownload){
		this.serviceDownload=serviceDownload;
	}
	/** get 服务不实用：附件下载数量 */	public int getServiceDownload(){
		return serviceDownload;
	}
	/** set 服务不实用：其他 */	public void setServiceOther(int serviceOther){
		this.serviceOther=serviceOther;
	}
	/** get 服务不实用：其他 */	public int getServiceOther(){
		return serviceOther;
	}
	/** set 服务不实用：总和 */	public void setSecurityService(int securityService){
		this.securityService=securityService;
	}
	/** get 服务不实用：总和 */	public int getSecurityService(){
		return securityService;
	}
	/** set 严重错误：虚假伪造内容数量 */	public void setSeriousUnreal(int seriousUnreal){
		this.seriousUnreal=seriousUnreal;
	}
	/** get 严重错误：虚假伪造内容数量 */	public int getSeriousUnreal(){
		return seriousUnreal;
	}
	/** set 严重错误：反动暴力或者色情数量 */	public void setSeriousViolence(int seriousViolence){
		this.seriousViolence=seriousViolence;
	}
	/** get 严重错误：反动暴力或者色情数量 */	public int getSeriousViolence(){
		return seriousViolence;
	}
	/** set 严重错误：其他 */	public void setSeriousOthers(int seriousOthers){
		this.seriousOthers=seriousOthers;
	}
	/** get 严重错误：其他 */	public int getSeriousOthers(){
		return seriousOthers;
	}
	/** set 严重错误：总和 */	public void setSecurityFatalError(int securityFatalError){
		this.securityFatalError=securityFatalError;
	}
	/** get 严重错误：总和 */	public int getSecurityFatalError(){
		return securityFatalError;
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

