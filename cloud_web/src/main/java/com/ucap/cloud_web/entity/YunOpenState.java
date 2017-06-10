package com.ucap.cloud_web.entity;


import java.util.Date;


/*** <br>* <b>作者：</b>liukl<br>* <b>日期：</b> 2017-03-28 17:40:56 <br>* <b>版权所有：<b>版权所有(C) 2017<br>*/public class YunOpenState {


	//	private int id;
	//网站标识码	private String siteCode;
	//1云搜索2 云分析	private int yunType;
	//1申请试用2申请中3登录使用	private int openState;
	//创建时间	private Date createTime;
	//修改时间	private Date modifyTime;
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
	/** set 1云搜索2 云分析 */	public void setYunType(int yunType){
		this.yunType=yunType;
	}
	/** get 1云搜索2 云分析 */	public int getYunType(){
		return yunType;
	}
	/** set 1申请试用2申请中3登录使用 */	public void setOpenState(int openState){
		this.openState=openState;
	}
	/** get 1申请试用2申请中3登录使用 */	public int getOpenState(){
		return openState;
	}
	/** set 创建时间 */	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}
	/** get 创建时间 */	public Date getCreateTime(){
		return createTime;
	}
	/** set 修改时间 */	public void setModifyTime(Date modifyTime){
		this.modifyTime=modifyTime;
	}
	/** get 修改时间 */	public Date getModifyTime(){
		return modifyTime;
	}
}

