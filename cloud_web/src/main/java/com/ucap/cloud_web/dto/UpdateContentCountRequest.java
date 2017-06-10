package com.ucap.cloud_web.dto;


import java.util.List;

import com.publics.util.page.Query;
import com.ucap.cloud_web.entity.DatabaseInfo;


/*** 前台页面传递基础数据<br>* <b>作者：</b>SunJiang<br>* <b>日期：</b> 2015-10-30 09:51:22 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/ /**<p>Description: </p>
 * <p>@Package：com.ucap.cloud_web.dto </p>
 * <p>Title: UpdateContentCountRequest </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：sunjiang </p>
 * <p>@date：2015-12-8上午11:13:28 </p>
 */
@SuppressWarnings("serial")public class UpdateContentCountRequest extends Query {
	
	private String siteCode;//网站标识码
	private Integer type;//类型（首页：0，栏目：1）
	private String scanDate;//扫描时间（yyyy-dd-mm）

	private String beginScanDate;//开始扫描时间
	private String endScanDate;//结束扫描时间
	private int homeNum;//首页个数
	private int channelNum;//栏目个数
	private int shiDepartment;//市部门
	private int shengDepartment;//省部门
	private int shiPortals;//市门户
	private int shengPortals;//省门户
	private int shiGovernment;//市政府
	private int xianDepartment;//县部门
	private int xian;//区县政府
	private String channelName;//栏目名称
	private String lastUpdateUrl;//最后更新url
	// 封装数据库中查询出来的数据
	private String siteName;//网站名称
	private Integer updateNum;//更行个数
	private String homePageUrl;//首页url
	private String jumpPageUrl;//跳转url
	private Integer isorganizational;//是否门户	
	private String director;//主办单位
	
	private List<String>  linkList;//网站标识码集合
	private String groupBy;//分组
	
	
	
	
	public String getGroupBy() {
		return groupBy;
	}

	public void setGroupBy(String groupBy) {
		this.groupBy = groupBy;
	}

	public List<String> getLinkList() {
		return linkList;
	}

	public void setLinkList(List<String> linkList) {
		this.linkList = linkList;
	}

	public Integer getIsorganizational() {
		return isorganizational;
	}

	public void setIsorganizational(Integer isorganizational) {
		this.isorganizational = isorganizational;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public Integer getUpdateNum() {
		return updateNum;
	}

	public void setUpdateNum(Integer updateNum) {
		this.updateNum = updateNum;
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

	/**
	 * list数组
	 */
	private List<DatabaseInfo> ids;
	
	
	public int getXianDepartment() {
		return xianDepartment;
	}

	public void setXianDepartment(int xianDepartment) {
		this.xianDepartment = xianDepartment;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public int getShiDepartment() {
		return shiDepartment;
	}

	public void setShiDepartment(int shiDepartment) {
		this.shiDepartment = shiDepartment;
	}

	public int getShengDepartment() {
		return shengDepartment;
	}

	public void setShengDepartment(int shengDepartment) {
		this.shengDepartment = shengDepartment;
	}

	public int getShiPortals() {
		return shiPortals;
	}

	public void setShiPortals(int shiPortals) {
		this.shiPortals = shiPortals;
	}

	public int getShengPortals() {
		return shengPortals;
	}

	public void setShengPortals(int shengPortals) {
		this.shengPortals = shengPortals;
	}

	public int getShiGovernment() {
		return shiGovernment;
	}

	public void setShiGovernment(int shiGovernment) {
		this.shiGovernment = shiGovernment;
	}

	public int getXian() {
		return xian;
	}

	public void setXian(int xian) {
		this.xian = xian;
	}

	public List<DatabaseInfo> getIds() {
		return ids;
	}

	public void setIds(List<DatabaseInfo> ids) {
		this.ids = ids;
	}

	public int getHomeNum() {
		return homeNum;
	}

	public void setHomeNum(int homeNum) {
		this.homeNum = homeNum;
	}

	public int getChannelNum() {
		return channelNum;
	}

	public void setChannelNum(int channelNum) {
		this.channelNum = channelNum;
	}

	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getScanDate() {
		return scanDate;
	}

	public void setScanDate(String scanDate) {
		this.scanDate = scanDate;
	}

	public String getBeginScanDate() {
		return beginScanDate;
	}

	public void setBeginScanDate(String beginScanDate) {
		this.beginScanDate = beginScanDate;
	}

	public String getEndScanDate() {
		return endScanDate;
	}

	public void setEndScanDate(String endScanDate) {
		this.endScanDate = endScanDate;
	}

	public String getLastUpdateUrl() {
		return lastUpdateUrl;
	}

	public void setLastUpdateUrl(String lastUpdateUrl) {
		this.lastUpdateUrl = lastUpdateUrl;
	}
	
	
	
	

}

