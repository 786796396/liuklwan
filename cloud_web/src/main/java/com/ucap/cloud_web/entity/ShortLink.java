package com.ucap.cloud_web.entity;


import java.util.Date;


/*** <br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2017-05-16 11:23:42 <br>* <b>版权所有：<b>版权所有(C) 2017<br>*/public class ShortLink {


	//	private int id;
	//原url	private String url;
	//短连接加密code	private String shortUrl;
	//创建时间	private Date createTime;
	//修改时间	private Date modifyTime;
	/** set  */	public void setId(int id){
		this.id=id;
	}
	/** get  */	public int getId(){
		return id;
	}
	/** set 原url */	public void setUrl(String url){
		this.url=url;
	}
	/** get 原url */	public String getUrl(){
		return url;
	}
	/** set 短连接加密code */	public void setShortUrl(String shortUrl){
		this.shortUrl=shortUrl;
	}
	/** get 短连接加密code */	public String getShortUrl(){
		return shortUrl;
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

