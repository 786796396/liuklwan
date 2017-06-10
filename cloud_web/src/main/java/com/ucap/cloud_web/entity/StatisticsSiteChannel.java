package com.ucap.cloud_web.entity;


import java.util.Date;


/*** <br>* <b>作者：</b>qingjy<br>* <b>日期：</b> 2017-05-03 19:08:59 <br>* <b>版权所有：<b>版权所有(C) 2017<br>*/public class StatisticsSiteChannel {


	//主键	private int id;
	//组织单位标识码	private String siteCode;
	//组织单位名称	private String orgName;
	//报送网站数量,下属挂接的网站数量	private int siteNum;
	//收费网站数量，database_info中 iscost = 1 (0免费，1收费)	private int costSiteNum;
	//关停网站数量, database_info中 isexp = 3 (1正常，2例外，3关停)	private int shutSiteNum;
	//例外网站数量, database_info中 isexp = 2 (1正常，2例外，3关停)	private int exceptSiteNum;
	//监测异常网站数, database_info中 is_scan ！=1 & isexp = 1	private int monitorAbnormal;
	//正常监测网站数, database_info中 is_scan = 1 & isexp = 1	private int monitorNormal;
	//上报栏目数量, channel_point 中 status != -1 （-1：标记删除，0：未监测, 1：监测中）	private int channelNum;
	//监测栏目数量,  channel_point 中 status = 1（-1：标记删除，0：未监测, 1：监测中）	private int monitorChannelNum;
	//统计日期 yyyy-MM-dd	private String tallyDate;
	//创建时间	private Date createTime;
	/** set 主键 */	public void setId(int id){
		this.id=id;
	}
	/** get 主键 */	public int getId(){
		return id;
	}
	/** set 组织单位标识码 */	public void setSiteCode(String siteCode){
		this.siteCode=siteCode;
	}
	/** get 组织单位标识码 */	public String getSiteCode(){
		return siteCode;
	}
	/** set 组织单位名称 */	public void setOrgName(String orgName){
		this.orgName=orgName;
	}
	/** get 组织单位名称 */	public String getOrgName(){
		return orgName;
	}
	/** set 报送网站数量,下属挂接的网站数量 */	public void setSiteNum(int siteNum){
		this.siteNum=siteNum;
	}
	/** get 报送网站数量,下属挂接的网站数量 */	public int getSiteNum(){
		return siteNum;
	}
	/** set 收费网站数量，database_info中 iscost = 1 (0免费，1收费) */	public void setCostSiteNum(int costSiteNum){
		this.costSiteNum=costSiteNum;
	}
	/** get 收费网站数量，database_info中 iscost = 1 (0免费，1收费) */	public int getCostSiteNum(){
		return costSiteNum;
	}
	/** set 关停网站数量, database_info中 isexp = 3 (1正常，2例外，3关停) */	public void setShutSiteNum(int shutSiteNum){
		this.shutSiteNum=shutSiteNum;
	}
	/** get 关停网站数量, database_info中 isexp = 3 (1正常，2例外，3关停) */	public int getShutSiteNum(){
		return shutSiteNum;
	}
	/** set 例外网站数量, database_info中 isexp = 2 (1正常，2例外，3关停) */	public void setExceptSiteNum(int exceptSiteNum){
		this.exceptSiteNum=exceptSiteNum;
	}
	/** get 例外网站数量, database_info中 isexp = 2 (1正常，2例外，3关停) */	public int getExceptSiteNum(){
		return exceptSiteNum;
	}
	/** set 监测异常网站数, database_info中 is_scan ！=1 & isexp = 1 */	public void setMonitorAbnormal(int monitorAbnormal){
		this.monitorAbnormal=monitorAbnormal;
	}
	/** get 监测异常网站数, database_info中 is_scan ！=1 & isexp = 1 */	public int getMonitorAbnormal(){
		return monitorAbnormal;
	}
	/** set 正常监测网站数, database_info中 is_scan = 1 & isexp = 1 */	public void setMonitorNormal(int monitorNormal){
		this.monitorNormal=monitorNormal;
	}
	/** get 正常监测网站数, database_info中 is_scan = 1 & isexp = 1 */	public int getMonitorNormal(){
		return monitorNormal;
	}
	/** set 上报栏目数量, channel_point 中 status != -1 （-1：标记删除，0：未监测, 1：监测中） */	public void setChannelNum(int channelNum){
		this.channelNum=channelNum;
	}
	/** get 上报栏目数量, channel_point 中 status != -1 （-1：标记删除，0：未监测, 1：监测中） */	public int getChannelNum(){
		return channelNum;
	}
	/** set 监测栏目数量,  channel_point 中 status = 1（-1：标记删除，0：未监测, 1：监测中） */	public void setMonitorChannelNum(int monitorChannelNum){
		this.monitorChannelNum=monitorChannelNum;
	}
	/** get 监测栏目数量,  channel_point 中 status = 1（-1：标记删除，0：未监测, 1：监测中） */	public int getMonitorChannelNum(){
		return monitorChannelNum;
	}
	/** set 统计日期 yyyy-MM-dd */	public void setTallyDate(String tallyDate){
		this.tallyDate=tallyDate;
	}
	/** get 统计日期 yyyy-MM-dd */	public String getTallyDate(){
		return tallyDate;
	}
	/** set 创建时间 */	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}
	/** get 创建时间 */	public Date getCreateTime(){
		return createTime;
	}
}

