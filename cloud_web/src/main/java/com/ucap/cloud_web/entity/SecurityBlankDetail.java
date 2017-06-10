package com.ucap.cloud_web.entity;


import java.util.Date;

import com.publics.util.utils.StringUtils;
import com.ucap.cloud_web.util.CommonUtils;


/*** <br>* <b>作者：</b>SunJiang<br>* <b>日期：</b> 2015-10-30 09:51:21 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/public class SecurityBlankDetail {


	//内容保障问题--空白栏目趋势详情表id	private int id;
	//空白栏目趋势id	private int securityBlankInfo;
	//期号	private int servicePeriodId;
	//网站标识码	private String siteCode;
	//栏目名称	private String channelName;
	//栏目url	private String url;
	//路径	private String path;
	//快照	private String imgUrl;
	
	//扫描时间
	private String scanDate;
	//创建时间	private Date createTime;
	//问题描述
	private String problemDesc;
	//浏览器版本
	private String webVersion;
	public String getWebVersion() {
		return webVersion;
	}

	public void setWebVersion(String webVersion) {
		this.webVersion = webVersion;
	}

	public String getScanDate() {
		return scanDate;
	}

	public void setScanDate(String scanDate) {
		this.scanDate = scanDate;
	}

	/** set 内容保障问题--空白栏目趋势详情表id */	public void setId(int id){
		this.id=id;
	}
	/** get 内容保障问题--空白栏目趋势详情表id */	public int getId(){
		return id;
	}
	/** set 空白栏目趋势id */	public void setSecurityBlankInfo(int securityBlankInfo){
		this.securityBlankInfo=securityBlankInfo;
	}
	/** get 空白栏目趋势id */	public int getSecurityBlankInfo(){
		return securityBlankInfo;
	}
	/** set 期号 */	public void setServicePeriodId(int servicePeriodId){
		this.servicePeriodId=servicePeriodId;
	}
	/** get 期号 */	public int getServicePeriodId(){
		return servicePeriodId;
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
	/** set 栏目url */	public void setUrl(String url){
		this.url=url;
	}
	/** get 栏目url */	public String getUrl(){
		return url;
	}
	/** set 路径 */	public void setPath(String path){
		this.path=path;
	}
	/** get 路径 */	public String getPath(){
		return path;
	}
	/** set 快照 */	public void setImgUrl(String imgUrl){
		this.imgUrl=imgUrl;
	}
	/** get 快照 */	public String getImgUrl(){
		return imgUrl;
	}
	/** set 创建时间 */	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}
	/** get 创建时间 */	public Date getCreateTime(){
		return createTime;
	}

	public String getProblemDesc() {
		return problemDesc;
	}

	public void setProblemDesc(String problemDesc) {
		this.problemDesc = problemDesc;
	}

}

