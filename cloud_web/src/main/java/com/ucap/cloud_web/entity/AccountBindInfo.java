package com.ucap.cloud_web.entity;


import java.util.Date;


/*** <br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2016-01-04 14:48:02 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public class AccountBindInfo {


	//id	private int id;
	//微信用户唯一标识	private String openId;
	//客户编码	private String siteCode;
	//客户名称	private String siteName;
	
	//微信用户昵称
	private String nickname;
	//绑定状态	private int status;
	
	//绑定状态
	private int isCustomer;
	//站群报告发送状态	private int siteListReportStatus;
	//站群预警通知发送状态	private int siteListEarlyStatus;
	//本级门户发送预警通知状态	private int localhostEarlyStatus;
	//本级门户发送报告通知状态	private int localhostReportStatus;
	//创建时间	private Date createTime;
	//修改时间	private Date modifyTime;

	
		public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/** set  */	public void setId(int id){
		this.id=id;
	}
	/** get  */	public int getId(){
		return id;
	}
	/** set  */	public void setOpenId(String openId){
		this.openId=openId;
	}
	/** get  */	public String getOpenId(){
		return openId;
	}
	/** set  */	public void setSiteCode(String siteCode){
		this.siteCode=siteCode;
	}
	/** get  */	public String getSiteCode(){
		return siteCode;
	}
	/** set  */	public void setSiteName(String siteName){
		this.siteName=siteName;
	}
	/** get  */	public String getSiteName(){
		return siteName;
	}
	/** set  */	public void setStatus(int status){
		this.status=status;
	}
	/** get  */	public int getStatus(){
		return status;
	}
	/** set  */	public void setSiteListReportStatus(int siteListReportStatus){
		this.siteListReportStatus=siteListReportStatus;
	}
	/** get  */	public int getSiteListReportStatus(){
		return siteListReportStatus;
	}
	/** set  */	public void setSiteListEarlyStatus(int siteListEarlyStatus){
		this.siteListEarlyStatus=siteListEarlyStatus;
	}
	/** get  */	public int getSiteListEarlyStatus(){
		return siteListEarlyStatus;
	}
	/** set  */	public void setLocalhostEarlyStatus(int localhostEarlyStatus){
		this.localhostEarlyStatus=localhostEarlyStatus;
	}
	/** get  */	public int getLocalhostEarlyStatus(){
		return localhostEarlyStatus;
	}
	/** set  */	public void setLocalhostReportStatus(int localhostReportStatus){
		this.localhostReportStatus=localhostReportStatus;
	}
	/** get  */	public int getLocalhostReportStatus(){
		return localhostReportStatus;
	}
	/** set  */	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}
	/** get  */	public Date getCreateTime(){
		return createTime;
	}
	/** set  */	public void setModifyTime(Date modifyTime){
		this.modifyTime=modifyTime;
	}
	/** get  */	public Date getModifyTime(){
		return modifyTime;
	}

	public int getIsCustomer() {
		return isCustomer;
	}

	public void setIsCustomer(int isCustomer) {
		this.isCustomer = isCustomer;
	}
}

