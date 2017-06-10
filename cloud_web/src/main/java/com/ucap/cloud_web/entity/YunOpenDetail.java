package com.ucap.cloud_web.entity;


import java.util.Date;


/*** <br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2017-05-10 19:03:10 <br>* <b>版权所有：<b>版权所有(C) 2017<br>*/public class YunOpenDetail {


	//云分析详情表id	private int id;
	//云分析任务表id	private int yunOpenInfoId;
	//网站标识码	private String siteCode;
	//网站名称	private String name;
	//js下载地址	private String loadJsUrl;
	//是否嵌码（0-否 1-是）	private int isInCode;
	//嵌码日期	private String inCodeDate;
	//结束日期	private String endDate;
	//状态	private int state;
	//创建日期	private Date createTime;
	//修改日期	private Date modifyTime;
	/** set 云分析详情表id */	public void setId(int id){
		this.id=id;
	}
	/** get 云分析详情表id */	public int getId(){
		return id;
	}
	/** set 云分析任务表id */	public void setYunOpenInfoId(int yunOpenInfoId){
		this.yunOpenInfoId=yunOpenInfoId;
	}
	/** get 云分析任务表id */	public int getYunOpenInfoId(){
		return yunOpenInfoId;
	}
	/** set 网站标识码 */	public void setSiteCode(String siteCode){
		this.siteCode=siteCode;
	}
	/** get 网站标识码 */	public String getSiteCode(){
		return siteCode;
	}
	/** set 网站名称 */	public void setName(String name){
		this.name=name;
	}
	/** get 网站名称 */	public String getName(){
		return name;
	}
	/** set js下载地址 */	public void setLoadJsUrl(String loadJsUrl){
		this.loadJsUrl=loadJsUrl;
	}
	/** get js下载地址 */	public String getLoadJsUrl(){
		return loadJsUrl;
	}
	/** set 是否嵌码（0-否 1-是） */	public void setIsInCode(int isInCode){
		this.isInCode=isInCode;
	}
	/** get 是否嵌码（0-否 1-是） */	public int getIsInCode(){
		return isInCode;
	}
	/** set 嵌码日期 */	public void setInCodeDate(String inCodeDate){
		this.inCodeDate=inCodeDate;
	}
	/** get 嵌码日期 */	public String getInCodeDate(){
		return inCodeDate;
	}
	/** set 结束日期 */	public void setEndDate(String endDate){
		this.endDate=endDate;
	}
	/** get 结束日期 */	public String getEndDate(){
		return endDate;
	}
	/** set 状态 */	public void setState(int state){
		this.state=state;
	}
	/** get 状态 */	public int getState(){
		return state;
	}
	/** set 创建日期 */	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}
	/** get 创建日期 */	public Date getCreateTime(){
		return createTime;
	}
	/** set 修改日期 */	public void setModifyTime(Date modifyTime){
		this.modifyTime=modifyTime;
	}
	/** get 修改日期 */	public Date getModifyTime(){
		return modifyTime;
	}
}

