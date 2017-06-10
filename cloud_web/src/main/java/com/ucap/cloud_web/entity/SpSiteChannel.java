package com.ucap.cloud_web.entity;


import java.util.Date;


/*** <br>* <b>作者：</b>liujc<br>* <b>日期：</b> 2016-11-22 10:31:39 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public class SpSiteChannel {


	//	private int id;
	//栏目id	private int channelId;
	//组织/站点编码	private String siteCode;
	//排序	private int sort;
	//添加人	private int creater;
	//添加时间	private Date createTime;
	/** set  */	public void setId(int id){
		this.id=id;
	}
	/** get  */	public int getId(){
		return id;
	}
	/** set 栏目id */	public void setChannelId(int channelId){
		this.channelId=channelId;
	}
	/** get 栏目id */	public int getChannelId(){
		return channelId;
	}
	/** set 组织/站点编码 */	public void setSiteCode(String siteCode){
		this.siteCode=siteCode;
	}
	/** get 组织/站点编码 */	public String getSiteCode(){
		return siteCode;
	}
	/** set 排序 */	public void setSort(int sort){
		this.sort=sort;
	}
	/** get 排序 */	public int getSort(){
		return sort;
	}
	/** set 添加人 */	public void setCreater(int creater){
		this.creater=creater;
	}
	/** get 添加人 */	public int getCreater(){
		return creater;
	}
	/** set 添加时间 */	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}
	/** get 添加时间 */	public Date getCreateTime(){
		return createTime;
	}
}

