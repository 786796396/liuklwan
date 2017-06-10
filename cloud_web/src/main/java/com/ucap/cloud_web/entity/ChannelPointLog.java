package com.ucap.cloud_web.entity;


import java.util.Date;


/*** <br>* <b>作者：</b>qingjy<br>* <b>日期：</b> 2016-11-29 17:19:36 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public class ChannelPointLog {


	//栏目删除表id	private int id;
	//网站标识码	private String siteCode;
	//栏目名称	private String channelName;
	//栏目url	private String channelUrl;
	//操作类型（0：同步删除、1：本地删除、 2：后台删除）	private int type;
	//操作人	private String userId;
	//创建时间	private Date createTime;
	/** set 栏目删除表id */	public void setId(int id){
		this.id=id;
	}
	/** get 栏目删除表id */	public int getId(){
		return id;
	}
	/** set 网站标识码 */	public void setSiteCode(String siteCode){
		this.siteCode=siteCode;
	}
	/** get 网站标识码 */	public String getSiteCode(){
		return siteCode;
	}
	/** set 栏目名称 */	public void setChannelName(String channelName){
		this.channelName=channelName;
	}
	/** get 栏目名称 */	public String getChannelName(){
		return channelName;
	}
	/** set 栏目url */	public void setChannelUrl(String channelUrl){
		this.channelUrl=channelUrl;
	}
	/** get 栏目url */	public String getChannelUrl(){
		return channelUrl;
	}
	/** set 操作类型（0：同步删除、1：本地删除、 2：后台删除） */	public void setType(int type){
		this.type=type;
	}
	/** get 操作类型（0：同步删除、1：本地删除、 2：后台删除） */	public int getType(){
		return type;
	}
	/** set 操作人 */	public void setUserId(String userId){
		this.userId=userId;
	}
	/** get 操作人 */	public String getUserId(){
		return userId;
	}
	/** set 创建时间 */	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}
	/** get 创建时间 */	public Date getCreateTime(){
		return createTime;
	}
}

