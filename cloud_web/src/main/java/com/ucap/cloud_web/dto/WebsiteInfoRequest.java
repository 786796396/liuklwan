package com.ucap.cloud_web.dto;

import com.publics.util.page.Query;

/**
 * 前台页面传递基础数据<br>
 * <b>作者：</b>SunJiang<br>
 * <b>日期：</b> 2015-10-30 09:51:22 <br>
 * <b>版权所有：<b>版权所有(C) 2015<br>
 */
@SuppressWarnings("serial")
public class WebsiteInfoRequest extends Query {
	private String siteCode;// 网站标识码
	private String siteName;//网站名称
	private String jumpPageUrl;//跳转URL
	private String homePageUrl;//首页url
	private String dicComboId;// 套餐id
	private Object[] idLists;// siteCode id集合
	/**
	 * 订单id
	 */
	private Integer orderInfoId;//订单id

	/**
	 * 用于判断当前
	 */
	private String nowTime;

	/**
	 * 站点信息表id
	 */
	private Integer websiteInfoId;

	/**
	 * 站点id数组
	 */
	private Object[] websiteIdLists;

	
	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getJumpPageUrl() {
		return jumpPageUrl;
	}

	public void setJumpPageUrl(String jumpPageUrl) {
		this.jumpPageUrl = jumpPageUrl;
	}

	public String getHomePageUrl() {
		return homePageUrl;
	}

	public void setHomePageUrl(String homePageUrl) {
		this.homePageUrl = homePageUrl;
	}

	public Object[] getWebsiteIdLists() {
		return websiteIdLists;
	}

	public void setWebsiteIdLists(Object[] websiteIdLists) {
		this.websiteIdLists = websiteIdLists;
	}

	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	public String getDicComboId() {
		return dicComboId;
	}

	public void setDicComboId(String dicComboId) {
		this.dicComboId = dicComboId;
	}

	public Object[] getIdLists() {
		return idLists;
	}

	public void setIdLists(Object[] idLists) {
		this.idLists = idLists;
	}

	public Integer getOrderInfoId() {
		return orderInfoId;
	}

	public void setOrderInfoId(Integer orderInfoId) {
		this.orderInfoId = orderInfoId;
	}

	public String getNowTime() {
		return nowTime;
	}

	public void setNowTime(String nowTime) {
		this.nowTime = nowTime;
	}

	public Integer getWebsiteInfoId() {
		return websiteInfoId;
	}

	public void setWebsiteInfoId(Integer websiteInfoId) {
		this.websiteInfoId = websiteInfoId;
	}

}
