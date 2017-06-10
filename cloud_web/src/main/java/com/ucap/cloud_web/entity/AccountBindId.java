package com.ucap.cloud_web.entity;


import java.util.Date;


/*** <br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2017-05-03 10:19:53 <br>* <b>版权所有：<b>版权所有(C) 2017<br>*/public class AccountBindId {


	//	private int id;
	//	private String openid;
	//	private Date createTime;
	//	private Date modifyTime;
	/** set  */	public void setId(int id){
		this.id=id;
	}
	/** get  */	public int getId(){
		return id;
	}
	/** set  */	public void setOpenid(String openid){
		this.openid=openid;
	}
	/** get  */	public String getOpenid(){
		return openid;
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
}

