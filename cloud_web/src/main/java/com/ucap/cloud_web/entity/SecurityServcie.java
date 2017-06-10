package com.ucap.cloud_web.entity;


import java.util.Date;

import com.publics.util.utils.StringUtils;
import com.ucap.cloud_web.util.CommonUtils;


/*** <br>* <b>作者：</b>SunJiang<br>* <b>日期：</b> 2015-10-30 09:51:21 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/public class SecurityServcie {


	//内容保障问题--服务不实详情表id	private int id;
	//期号	private int servicePeriodId;
	//网站标识码	private String siteCode;
	//办事事项	private String serviceItem;
	//问题类型id	private int problemTypeId;
	//问题描述	private String problemDesc;
	//网页url	private String url;
	//截图	private String imgUrl;
	
	//扫描日期
	private String scanDate;
	//创建时间	private Date createTime;

	//浏览器版本
	private String webVersion;
	
	//父页面url
	private String parentUrl;
	
		public String getParentUrl() {
		return parentUrl;
	}

	public void setParentUrl(String parentUrl) {
		this.parentUrl = parentUrl;
	}

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

	/** set 内容保障问题--服务不实详情表id */	public void setId(int id){
		this.id=id;
	}
	/** get 内容保障问题--服务不实详情表id */	public int getId(){
		return id;
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
	/** set 办事事项 */	public void setServiceItem(String serviceItem){
		this.serviceItem=serviceItem;
	}
	/** get 办事事项 */	public String getServiceItem(){
		return serviceItem;
	}
	/** set 问题类型id */	public void setProblemTypeId(int problemTypeId){
		this.problemTypeId=problemTypeId;
	}
	/** get 问题类型id */	public int getProblemTypeId(){
		return problemTypeId;
	}
	/** set 问题描述 */	public void setProblemDesc(String problemDesc){
		this.problemDesc=problemDesc;
	}
	/** get 问题描述 */	public String getProblemDesc(){
		return problemDesc;
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
	/** set 创建时间 */	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}
	/** get 创建时间 */	public Date getCreateTime(){
		return createTime;
	}

}

