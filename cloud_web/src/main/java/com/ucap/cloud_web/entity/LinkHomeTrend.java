package com.ucap.cloud_web.entity;


import java.util.Date;


/*** <br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2015-10-29 10:49:03 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/public class LinkHomeTrend {


	//首页连通趋势表id	private int id;
	//网站标识码	private String siteCode;
	//扫描时间（yyyy-dd-mm）	private String scanDate;
	//总数（首页扫描的总数）	private int websiteSum;
	//创建时间	private Date createTime;
	/** set 首页连通趋势表id */	public void setId(int id){
		this.id=id;
	}
	/** get 首页连通趋势表id */	public int getId(){
		return id;
	}
	/** set 网站标识码 */	public void setSiteCode(String siteCode){
		this.siteCode=siteCode;
	}
	/** get 网站标识码 */	public String getSiteCode(){
		return siteCode;
	}
	/** set 扫描时间（yyyy-dd-mm） */	public void setScanDate(String scanDate){
		this.scanDate=scanDate;
	}
	/** get 扫描时间（yyyy-dd-mm） */	public String getScanDate(){
		return scanDate;
	}
	/** set 总数（首页扫描的总数） */	public void setWebsiteSum(int websiteSum){
		this.websiteSum=websiteSum;
	}
	/** get 总数（首页扫描的总数） */	public int getWebsiteSum(){
		return websiteSum;
	}
	/** set 创建时间 */	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}
	/** get 创建时间 */	public Date getCreateTime(){
		return createTime;
	}
}

