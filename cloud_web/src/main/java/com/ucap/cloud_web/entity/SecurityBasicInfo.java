package com.ucap.cloud_web.entity;


import java.util.Date;

import com.publics.util.utils.StringUtils;
import com.ucap.cloud_web.util.CommonUtils;


/*** <br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2016-04-12 10:12:55 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public class SecurityBasicInfo {


	//	private int id;
	//网站标识码	private String siteCode;
	//栏目URL	private String channelUrl;
	//栏目名称	private String channelName;
	//问题类型id	private int problemTypId;
	//截图	private String imgUrl;
	//扫描时间	private String scanDate;
	//操作人id	private int userId;
	//创建时间	private Date createTime;
	//修改时间	private Date modifyTime;
	//问题描述	private String problemDesc;
	//期号	private int servicePeriodId;
	//浏览器版本
	private String webVersion;	/** set  */	public void setId(int id){
		this.id=id;
	}
	public String getWebVersion() {
		return webVersion;
	}

	public void setWebVersion(String webVersion) {
		this.webVersion = webVersion;
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
	/** set 栏目URL */	public void setChannelUrl(String channelUrl){
		this.channelUrl=channelUrl;
	}
	/** get 栏目URL */	public String getChannelUrl(){
		return channelUrl;
	}
	/** set 栏目名称 */	public void setChannelName(String channelName){
		this.channelName=channelName;
	}
	/** get 栏目名称 */	public String getChannelName(){
		return channelName;
	}
	/** set 问题类型id */	public void setProblemTypId(int problemTypId){
		this.problemTypId=problemTypId;
	}
	/** get 问题类型id */	public int getProblemTypId(){
		return problemTypId;
	}
	/** set 截图 */	public void setImgUrl(String imgUrl){
		this.imgUrl=imgUrl;
	}
	/** get 截图 */	public String getImgUrl(){
		return imgUrl;
	}
	/** set 扫描时间 */	public void setScanDate(String scanDate){
		this.scanDate=scanDate;
	}
	/** get 扫描时间 */	public String getScanDate(){
		return scanDate;
	}
	/** set 操作人id */	public void setUserId(int userId){
		this.userId=userId;
	}
	/** get 操作人id */	public int getUserId(){
		return userId;
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
	/** set 问题描述 */	public void setProblemDesc(String problemDesc){
		this.problemDesc=problemDesc;
	}
	/** get 问题描述 */	public String getProblemDesc(){
		return problemDesc;
	}
	/** set 期号 */	public void setServicePeriodId(int servicePeriodId){
		this.servicePeriodId=servicePeriodId;
	}
	/** get 期号 */	public int getServicePeriodId(){
		return servicePeriodId;
	}
}

