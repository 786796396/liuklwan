package com.ucap.cloud_web.entity;

import java.util.Date;

import com.publics.util.utils.StringUtils;
import com.ucap.cloud_web.util.CommonUtils;

/**
 * <br>
 * <b>作者：</b>SunJiang<br>
 * <b>日期：</b> 2015-10-30 09:51:21 <br>
 * <b>版权所有：<b>版权所有(C) 2015<br>
 */
public class SecurityResponse {

	// 内容保障问题--互动回应详情表id
	private int id;

	// 栏目id
	private int dicChannelId;

	// 网站标识码
	private String siteCode;

	// 期号
	private int servicePeriodId;

	// 栏目名称
	private String channelName;

	// 栏目url
	private String channelUrl;

	// 问题类型id
	private int problemTypeId;

	// 问题描述
	private String problemDesc;

	// 截图
	private String imgUrl;
	
	//扫描时间
	private String scanDate;

	// 创建时间
	private Date createTime;

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

	/** set 内容保障问题--互动回应详情表id */
	public void setId(int id) {
		this.id = id;
	}

	/** get 内容保障问题--互动回应详情表id */
	public int getId() {
		return id;
	}

	/** set 栏目id */
	public void setDicChannelId(int dicChannelId) {
		this.dicChannelId = dicChannelId;
	}

	/** get 栏目id */
	public int getDicChannelId() {
		return dicChannelId;
	}

	/** set 网站标识码 */
	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	/** get 网站标识码 */
	public String getSiteCode() {
		return siteCode;
	}

	/** set 期号 */
	public void setServicePeriodId(int servicePeriodId) {
		this.servicePeriodId = servicePeriodId;
	}

	/** get 期号 */
	public int getServicePeriodId() {
		return servicePeriodId;
	}

	/** set 栏目名称 */
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	/** get 栏目名称 */
	public String getChannelName() {
		return channelName;
	}

	/** set 栏目url */
	public String getChannelUrl() {
		return channelUrl;
	}

	/** get 栏目url */
	public void setChannelUrl(String channelUrl) {
		this.channelUrl = channelUrl;
	}
	/** set 问题类型id */
	public void setProblemTypeId(int problemTypeId) {
		this.problemTypeId = problemTypeId;
	}

	/** get 问题类型id */
	public int getProblemTypeId() {
		return problemTypeId;
	}

	/** set 问题描述 */
	public void setProblemDesc(String problemDesc) {
		this.problemDesc = problemDesc;
	}

	/** get 问题描述 */
	public String getProblemDesc() {
		return problemDesc;
	}

	/** set 截图 */
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	/** get 截图 */
	public String getImgUrl() {
		return imgUrl;
	}

	/** set 创建时间 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/** get 创建时间 */
	public Date getCreateTime() {
		return createTime;
	}


}
