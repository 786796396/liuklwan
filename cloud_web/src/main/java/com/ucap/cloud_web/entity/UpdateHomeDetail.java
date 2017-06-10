package com.ucap.cloud_web.entity;

import java.util.Date;

import com.publics.util.utils.StringUtils;
import com.ucap.cloud_web.util.CommonUtils;

/**
 * <br>
 * <b>作者：</b>SunJiang<br>
 * <b>日期：</b> 2015-10-30 09:51:22 <br>
 * <b>版权所有：<b>版权所有(C) 2015<br>
 */
public class UpdateHomeDetail {

	// 首页面更新明细表id
	private int id;

	// 内容更新统计Id
	private int updateContentCount;

	// 网站标识码
	private String siteCode;
	
	//首页url
	private String homeUrl;

	// 更新时间
	private String updateTime;

	// 标题
	private String title;

	// 更新url
	private String url;
	
	// 扫描时间（yyyy-dd-mm）
	private String scanDate;

	// 创建时间
	private Date createTime;

	// 快照 add by Nora 20151113
	private String imgUrl;

	// 缩略图
	private String litImg;
	public String getLitImg() {
		return litImg;
	}

	public void setLitImg(String litImg) {
		this.litImg = litImg;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	/** set 首页面更新明细表id */
	public void setId(int id) {
		this.id = id;
	}

	/** get 首页面更新明细表id */
	public int getId() {
		return id;
	}

	/** set 内容更新统计Id */
	public void setUpdateContentCount(int updateContentCount) {
		this.updateContentCount = updateContentCount;
	}

	/** get 内容更新统计Id */
	public int getUpdateContentCount() {
		return updateContentCount;
	}

	/** set 网站标识码 */
	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	/** get 网站标识码 */
	public String getSiteCode() {
		return siteCode;
	}

	/** set 更新时间 */
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	/** get 更新时间 */
	public String getUpdateTime() {
		return updateTime;
	}

	/** set 标题 */
	public void setTitle(String title) {
		this.title = title;
	}

	/** get 标题 */
	public String getTitle() {
		return title;
	}

	/** set 首页面网址url */
	public void setUrl(String url) {
		this.url =url;
	}

	/** get 首页面网址url */
	public String getUrl() {
		return url;
	}

	/** set 扫描时间（yyyy-dd-mm） */
	public void setScanDate(String scanDate) {
		this.scanDate = scanDate;
	}

	/** get 扫描时间（yyyy-dd-mm） */
	public String getScanDate() {
		return scanDate;
	}

	/** set 创建时间 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/** get 创建时间 */
	public Date getCreateTime() {
		return createTime;
	}

	public String getHomeUrl() {
		return homeUrl;
	}

	public void setHomeUrl(String homeUrl) {
		this.homeUrl = homeUrl;
	}

	
	

}
