package com.ucap.cloud_web.entity;


import java.util.Date;


/*** <br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2015-10-29 10:48:42 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/public class LinkAllInfo {


	//全站连接可用性趋势表id	private int id;
	//期号id	private int servicePeriodId;
	//网站标识码	private String siteCode;


		//总数(全站扫描的死链总数)	private int websiteSum;
	//创建时间	private Date createTime;
	/** set 全站连接可用性趋势表id */	public void setId(int id){
		this.id=id;
	}
	/** get 全站连接可用性趋势表id */	public int getId(){
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
	/** set 总数(全站扫描的死链总数) */	public void setWebsiteSum(int websiteSum){
		this.websiteSum=websiteSum;
	}
	/** get 总数(全站扫描的死链总数) */	public int getWebsiteSum(){
		return websiteSum;
	}
	/** set 创建时间 */	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}
	/** get 创建时间 */	public Date getCreateTime(){
		return createTime;
	}}

