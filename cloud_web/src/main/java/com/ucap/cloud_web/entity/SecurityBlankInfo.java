package com.ucap.cloud_web.entity;


import java.util.Date;


/*** <br>* <b>作者：</b>SunJiang<br>* <b>日期：</b> 2015-10-30 09:51:21 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/public class SecurityBlankInfo {


	//内容保障问题-空白栏目趋势表id	private int id;
	//网站标识码	private String siteCode;
	//期号	private int servicePeriodId;
	//空白栏目个数	private int blankNum;
	//创建时间	private Date createTime;
	/** set 内容保障问题-空白栏目趋势表id */	public void setId(int id){
		this.id=id;
	}
	/** get 内容保障问题-空白栏目趋势表id */	public int getId(){
		return id;
	}
	/** set 网站标识码 */	public void setSiteCode(String siteCode){
		this.siteCode=siteCode;
	}
	/** get 网站标识码 */	public String getSiteCode(){
		return siteCode;
	}
	/** set 期号 */	public void setServicePeriodId(int servicePeriodId){
		this.servicePeriodId=servicePeriodId;
	}
	/** get 期号 */	public int getServicePeriodId(){
		return servicePeriodId;
	}
	/** set 空白栏目个数 */	public void setBlankNum(int blankNum){
		this.blankNum=blankNum;
	}
	/** get 空白栏目个数 */	public int getBlankNum(){
		return blankNum;
	}
	/** set 创建时间 */	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}
	/** get 创建时间 */	public Date getCreateTime(){
		return createTime;
	}
}

