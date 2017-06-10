package com.ucap.cloud_web.entity;


import java.util.Date;

import com.publics.util.utils.StringUtils;
import com.ucap.cloud_web.util.CommonUtils;


/*** <br>* <b>作者：</b>sunjq<br>* <b>日期：</b> 2016-05-09 11:52:59 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public class SecurityFatalError {


	//	private int id;
	//网站标识码	private String siteCode;
	//期号	private int servicePeriodId;
	//问题类型
	private int type;
	//问题名称	private String name;
	//页面url	private String url;
	//问题描述	private String problemDesc;
	//截图地址	private String imgUrl;
	//用户id	private int userId;
	//发现时间	private String scanDate;
	//修改时间	private Date modifyTime;
	//浏览器版本
	private String webVersion;
	
		public String getWebVersion() {
		return webVersion;
	}

	public void setWebVersion(String webVersion) {
		this.webVersion = webVersion;
	}

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
	public int getServicePeriodId() {
		return servicePeriodId;
	}

	public void setServicePeriodId(int servicePeriodId) {
		this.servicePeriodId = servicePeriodId;
	}

	/** set 问题名称 */	public void setName(String name){
		this.name=name;
	}
	/** get 问题名称 */	public String getName(){
		return name;
	}
	/** set 页面url */	public void setUrl(String url){
		this.url=url;
	}
	/** get 页面url */	public String getUrl(){
		return url;
	}
	/** set 问题描述 */	public void setProblemDesc(String problemDesc){
		this.problemDesc=problemDesc;
	}
	/** get 问题描述 */	public String getProblemDesc(){
		return problemDesc;
	}
	/** set 截图地址 */	public void setImgUrl(String imgUrl){
		this.imgUrl=imgUrl;
	}
	/** get 截图地址 */	public String getImgUrl(){
		return imgUrl;
	}
	/** set 用户id */	public void setUserId(int userId){
		this.userId=userId;
	}
	/** get 用户id */	public int getUserId(){
		return userId;
	}
	/** set 发现时间 */	public void setScanDate(String scanDate){
		this.scanDate=scanDate;
	}
	/** get 发现时间 */	public String getScanDate(){
		return scanDate;
	}
	/** set 修改时间 */	public void setModifyTime(Date modifyTime){
		this.modifyTime=modifyTime;
	}
	/** get 修改时间 */	public Date getModifyTime(){
		return modifyTime;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}

