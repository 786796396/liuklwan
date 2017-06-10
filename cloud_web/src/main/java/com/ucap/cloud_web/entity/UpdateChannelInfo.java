package com.ucap.cloud_web.entity;


import java.util.Date;


/*** <br>* <b>作者：</b>SunJiang<br>* <b>日期：</b> 2015-10-30 09:51:22 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/public class UpdateChannelInfo {


	//栏目更新id	private int id;
	//栏目类型	private int dicChannelId;
	//网站标识码	private String siteCode;
	//更新栏目总数	private int updateNum;
	//扫描时间(YYYY-MM-dd)	private String scanDate;
	//创建时间	private Date createTime;
	/** set 栏目更新id */	public void setId(int id){
		this.id=id;
	}
	/** get 栏目更新id */	public int getId(){
		return id;
	}
	/** set 栏目类型 */	public void setDicChannelId(int dicChannelId){
		this.dicChannelId=dicChannelId;
	}
	/** get 栏目类型 */	public int getDicChannelId(){
		return dicChannelId;
	}
	/** set 网站标识码 */	public void setSiteCode(String siteCode){
		this.siteCode=siteCode;
	}
	/** get 网站标识码 */	public String getSiteCode(){
		return siteCode;
	}
	/** set 更新栏目总数 */	public void setUpdateNum(int updateNum){
		this.updateNum=updateNum;
	}
	/** get 更新栏目总数 */	public int getUpdateNum(){
		return updateNum;
	}
	/** set 扫描时间(YYYY-MM-dd) */	public void setScanDate(String scanDate){
		this.scanDate=scanDate;
	}
	/** get 扫描时间(YYYY-MM-dd) */	public String getScanDate(){
		return scanDate;
	}
	/** set 创建时间 */	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}
	/** get 创建时间 */	public Date getCreateTime(){
		return createTime;
	}
}

