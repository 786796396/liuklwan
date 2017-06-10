package com.ucap.cloud_web.dto;


import java.util.List;

import com.publics.util.page.Query;


/*** 前台页面传递基础数据<br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2015-10-29 11:06:29 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/@SuppressWarnings("serial")public class LinkHomeTrendRequest extends Query {
	/**
	 * 通过网站表示码和扫描时间查询首页连通趋势表
	 */
	private String siteCode;//网站标识码
	private String scanDate;//扫描时间（yyyy-dd-mm）
	private String siteName;//网站名称
	private Integer websiteSum;//不可用链接个数
	private String homePageUrl;//首页URL
	private String jumpPageUrl;//跳转url
	private String director;//主办单位
	private Integer indexlinknum;// 链接总数
	/**
	 * 查询某个时间段内的首页连通趋势表数据
	 */
	private String begeinScanDate;//开始时间(yyyy-MM-dd)
	private String endScanDate;//结束时间(yyyy-MM-dd)
	
	private List<Object> ids;
	
	private Integer countNum;//总记录数

	public Integer getCountNum() {
		return countNum;
	}
	public void setCountNum(Integer countNum) {
		this.countNum = countNum;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getJumpPageUrl() {
		return jumpPageUrl;
	}
	public void setJumpPageUrl(String jumpPageUrl) {
		this.jumpPageUrl = jumpPageUrl;
	}
	public List<Object> getIds() {
		return ids;
	}
	public void setIds(List<Object> ids) {
		this.ids = ids;
	}
	public String getBegeinScanDate() {
		return begeinScanDate;
	}
	public void setBegeinScanDate(String begeinScanDate) {
		this.begeinScanDate = begeinScanDate;
	}
	public String getEndScanDate() {
		return endScanDate;
	}
	public void setEndScanDate(String endScanDate) {
		this.endScanDate = endScanDate;
	}
	public String getSiteCode() {
		return siteCode;
	}
	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}
	public String getScanDate() {
		return scanDate;
	}
	public void setScanDate(String scanDate) {
		this.scanDate = scanDate;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
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

	public Integer getIndexlinknum() {
		return indexlinknum;
	}

	public void setIndexlinknum(Integer indexlinknum) {
		this.indexlinknum = indexlinknum;
	}

}

