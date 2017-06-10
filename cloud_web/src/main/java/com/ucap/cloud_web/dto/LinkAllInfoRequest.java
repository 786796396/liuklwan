package com.ucap.cloud_web.dto;


import java.util.List;

import com.publics.util.page.Query;
import com.ucap.cloud_web.entity.ServicePeriod;


/**
* 前台页面传递基础数据<br>
* <b>作者：</b>cuichx<br>
* <b>日期：</b> 2015-10-29 11:06:08 <br>
* <b>版权所有：<b>版权所有(C) 2015<br>
*/
@SuppressWarnings("serial")
public class LinkAllInfoRequest extends Query {
	/**
	 * 根据期号(或扫描时间)和sitecode查询全站连接可用性进度和趋势表
	 */
	private Integer servicePeriodId;//期号id
	private String siteCode;//网站标识码
	
	private String searchStartTime;//开始时间
	private String searchEndTime;//结束时间
	
	private String createTime;//创建时间
	private String homePageUrl;
	private String jumpPageUrl;
	private String siteName;
	private String director;
	/**
	 * 用于封装从数据库中查出的数据
	 */
	private String returnTime;//创建时间的前六位字符串
	private Integer websiteSum;//总数(全站扫描的死链总数)
	
	private List<Object> list;
	
	private List<ServicePeriod> serviceList;
	
	
	
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public List<ServicePeriod> getServiceList() {
		return serviceList;
	}
	public void setServiceList(List<ServicePeriod> serviceList) {
		this.serviceList = serviceList;
	}
	public List<Object> getList() {
		return list;
	}
	public void setList(List<Object> list) {
		this.list = list;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public Integer getServicePeriodId() {
		return servicePeriodId;
	}
	public void setServicePeriodId(Integer servicePeriodId) {
		this.servicePeriodId = servicePeriodId;
	}
	public String getSiteCode() {
		return siteCode;
	}
	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}
	public String getSearchStartTime() {
		return searchStartTime;
	}
	public void setSearchStartTime(String searchStartTime) {
		this.searchStartTime = searchStartTime;
	}
	public String getSearchEndTime() {
		return searchEndTime;
	}
	public void setSearchEndTime(String searchEndTime) {
		this.searchEndTime = searchEndTime;
	}
	public String getReturnTime() {
		return returnTime;
	}
	public void setReturnTime(String returnTime) {
		this.returnTime = returnTime;
	}
	public Integer getWebsiteSum() {
		return websiteSum;
	}
	public void setWebsiteSum(Integer websiteSum) {
		this.websiteSum = websiteSum;
	}
	public String getHomePageUrl() {
		return homePageUrl;
	}
	public void setHomePageUrl(String homePageUrl) {
		this.homePageUrl = homePageUrl;
	}
	public String getJumpPageUrl() {
		return jumpPageUrl;
	}
	public void setJumpPageUrl(String jumpPageUrl) {
		this.jumpPageUrl = jumpPageUrl;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	

}

