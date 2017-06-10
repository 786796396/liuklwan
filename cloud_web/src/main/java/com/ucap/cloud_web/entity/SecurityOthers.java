package com.ucap.cloud_web.entity;


import java.util.Date;

import com.publics.util.utils.StringUtils;
import com.ucap.cloud_web.util.CommonUtils;


/*** <br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2016-06-23 15:27:08 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public class SecurityOthers {


	//id主键	private int id;
	//网站标识码	private String siteCode;
	//期号	private int servicePeriodId;
	//问题名称
	private String name;	//扫描日期	private String scanDate;
	//网页url	private String url;
	//截图	private String imgUrl;
	//问题描述	private String problemDesc;
	//用户id	private int userId;
	//浏览器版本	private String webVersion;
	//创建时间	private Date createTime;
	//修改时间	private Date modifyTime;
	/** set id主键 */	public void setId(int id){
		this.id=id;
	}
	/** get id主键 */	public int getId(){
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
	/** set 扫描日期 */	public void setScanDate(String scanDate){
		this.scanDate=scanDate;
	}
	/** get 扫描日期 */	public String getScanDate(){
		return scanDate;
	}
	/** set 网页url */	public void setUrl(String url){
		this.url=url;
	}
	/** get 网页url */	public String getUrl(){
		return url;
	}
	/** set 截图 */	public void setImgUrl(String imgUrl){
		this.imgUrl=imgUrl;
	}
	/** get 截图 */	public String getImgUrl(){
		return imgUrl;
	}
	/** set 问题描述 */	public void setProblemDesc(String problemDesc){
		this.problemDesc=problemDesc;
	}
	/** get 问题描述 */	public String getProblemDesc(){
		return problemDesc;
	}
	/** set 用户id */	public void setUserId(int userId){
		this.userId=userId;
	}
	/** get 用户id */	public int getUserId(){
		return userId;
	}
	/** set 浏览器版本 */	public void setWebVersion(String webVersion){
		this.webVersion=webVersion;
	}
	/** get 浏览器版本 */	public String getWebVersion(){
		return webVersion;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

