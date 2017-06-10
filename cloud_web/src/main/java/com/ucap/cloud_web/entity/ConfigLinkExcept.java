package com.ucap.cloud_web.entity;


import java.util.Date;


/*** <br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-08-16 15:13:23 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public class ConfigLinkExcept {


	//	private int id;
	//网站标识码	private String siteCode;
	//不包含url	private String url;
	//0:正常，-1：删除	private int status;
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
	/** set 不包含url */	public void setUrl(String url){
		this.url=url;
	}
	/** get 不包含url */	public String getUrl(){
		return url;
	}
	/** set 0:正常，-1：删除 */	public void setStatus(int status){
		this.status=status;
	}
	/** get 0:正常，-1：删除 */	public int getStatus(){
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

