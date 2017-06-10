package com.ucap.cloud_web.entity;


import java.util.Date;


/*** <br>* <b>作者：</b>qingjy<br>* <b>日期：</b> 2016-11-29 17:18:44 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public class ChannelPointTemp {


	//栏目临时表id	private int id;
	//网站标识码	private String siteCode;
	//类别id	private int dicChannelId;
	//子类id	private int dicChannelSonId;
	//栏目名称	private String channelName;
	//栏目url	private String channelUrl;
	//同步状态（0：未同步、1：已同步）	private int status;
	//创建时间	private Date createTime;
	//修改时间	private Date modifyTime;
	/** set 栏目临时表id */	public void setId(int id){
		this.id=id;
	}
	/** get 栏目临时表id */	public int getId(){
		return id;
	}
	/** set 网站标识码 */	public void setSiteCode(String siteCode){
		this.siteCode=siteCode;
	}
	/** get 网站标识码 */	public String getSiteCode(){
		return siteCode;
	}
	/** set 类别id */	public void setDicChannelId(int dicChannelId){
		this.dicChannelId=dicChannelId;
	}
	/** get 类别id */	public int getDicChannelId(){
		return dicChannelId;
	}
	/** set 子类id */	public void setDicChannelSonId(int dicChannelSonId){
		this.dicChannelSonId=dicChannelSonId;
	}
	/** get 子类id */	public int getDicChannelSonId(){
		return dicChannelSonId;
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
	/** set 同步状态（0：未同步、1：已同步） */	public void setStatus(int status){
		this.status=status;
	}
	/** get 同步状态（0：未同步、1：已同步） */	public int getStatus(){
		return status;
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

