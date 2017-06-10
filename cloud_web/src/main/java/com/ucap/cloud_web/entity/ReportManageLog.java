package com.ucap.cloud_web.entity;


import java.util.Date;


/*** <br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2015-10-29 11:23:36 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/public class ReportManageLog {

	//报告管理id	private int id;
	//期号id	private int servicePeriodId;
	//网站标识码	private String siteCode;
	//网站名称	private String siteName;
	//报告上次发送时间	private Date sendTime;
	//报告上次发送的状态（0否、1是）	private int sendState;
	
	//是否已经发送微信通知（0-否 1-是）
	private int sendWxState;
	//创建时间	private Date createTime;
	/** set 报告管理id */	public void setId(int id){
		this.id=id;
	}
	/** get 报告管理id */	public int getId(){
		return id;
	}
	/** set 期号id */	public void setServicePeriodId(int servicePeriodId){
		this.servicePeriodId=servicePeriodId;
	}
	/** get 期号id */	public int getServicePeriodId(){
		return servicePeriodId;
	}
	/** set 网站标识码 */	public void setSiteCode(String siteCode){
		this.siteCode=siteCode;
	}
	/** get 网站标识码 */	public String getSiteCode(){
		return siteCode;
	}
	/** set 网站名称 */	public void setSiteName(String siteName){
		this.siteName=siteName;
	}
	/** get 网站名称 */	public String getSiteName(){
		return siteName;
	}
	/** set 报告上次发送时间 */	public void setSendTime(Date sendTime){
		this.sendTime=sendTime;
	}
	/** get 报告上次发送时间 */	public Date getSendTime(){
		return sendTime;
	}
	/** set 报告上次发送的状态（0否、1是） */	public void setSendState(int sendState){
		this.sendState=sendState;
	}
	/** get 报告上次发送的状态（0否、1是） */	public int getSendState(){
		return sendState;
	}
	/** set 创建时间 */	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}
	/** get 创建时间 */	public Date getCreateTime(){
		return createTime;
	}

	public int getSendWxState() {
		return sendWxState;
	}

	public void setSendWxState(int sendWxState) {
		this.sendWxState = sendWxState;
	}
}

