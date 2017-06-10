package com.ucap.cloud_web.entity;

import java.util.Date;

import com.publics.util.utils.StringUtils;
import com.ucap.cloud_web.util.CommonUtils;

/**
 * <br>
 * <b>作者：</b>SunJiang<br>
 * <b>日期：</b> 2015-12-03 15:22:56 <br>
 * <b>版权所有：<b>版权所有(C) 2015<br>
 */
public class SecurityHomeChannel {

	//
	private int id;

	// 网站标识码
	private String siteCode;

	// 栏目名称
	private String name;
	// 网址
	private String url;

	// 最后更新时间
	private String modifyDate;

	// 未更新天数
	private Integer notUpdateNum;

	// 快照
	private String imageUrl;

	// 缩略图
	private String litImg;

	// 类型（1首页，2栏目）
	private int type;
	// 栏目类型
	private int channelType;
	// 扫描时间
	private String scanDate;
	// 操作人ID
	private Integer userId;
	// 问题类型ID
	private Integer problemTypId;
	// 问题描述
	private String problemDesc;
	// 浏览器版本
	private String webVersion;
	// 期号
	private Integer servicePeriodId;
	// 逾期未更新状态 0：是>updateDay 1：否 <=updateDay
	private Integer flagType;

	// 最后正常监测时间 add by Na.Y 20160817
	private String lastAccessDate;
	
	//相应栏目对应的逾期信息
	private String notUpdateStr;
	
	

	public String getNotUpdateStr() {
		return notUpdateStr;
	}

	public void setNotUpdateStr(String notUpdateStr) {
		this.notUpdateStr = notUpdateStr;
	}

	public String getLastAccessDate() {
		return lastAccessDate;
	}

	public void setLastAccessDate(String lastAccessDate) {
		this.lastAccessDate = lastAccessDate;
	}

	public String getLitImg() {
		return litImg;
	}

	public void setLitImg(String litImg) {
		this.litImg = litImg;
	}

	// 创建时间
	private Date createTime;

	public int getChannelType() {
		return channelType;
	}

	public void setChannelType(int channelType) {
		this.channelType = channelType;
	}

	/** set */
	public void setId(int id) {
		this.id = id;
	}

	/** get */
	public int getId() {
		return id;
	}

	/** set 网站标识码 */
	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	/** get 网站标识码 */
	public String getSiteCode() {
		return siteCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/** set 网址 */
	public void setUrl(String url) {
		this.url = url;
	}

	/** get 网址 */
	public String getUrl() {
		return url;
	}

	/** set 最后更新时间 */
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	/** get 最后更新时间 */
	public String getModifyDate() {
		return modifyDate;
	}

	/** set 未更新天数 */
	public void setNotUpdateNum(Integer notUpdateNum) {
		this.notUpdateNum = notUpdateNum;
	}

	/** get 未更新天数 */
	public Integer getNotUpdateNum() {
		return notUpdateNum;
	}

	/** set 快照 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	/** get 快照 */
	public String getImageUrl() {
		return imageUrl;
	}

	/** set 类型（1首页，2栏目） */
	public void setType(int type) {
		this.type = type;
	}

	/** get 类型（1首页，2栏目） */
	public int getType() {
		return type;
	}

	/** set 扫描时间 */
	public void setScanDate(String scanDate) {
		this.scanDate = scanDate;
	}

	/** get 扫描时间 */
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

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getProblemTypId() {
		return problemTypId;
	}

	public void setProblemTypId(Integer problemTypId) {
		this.problemTypId = problemTypId;
	}

	public String getProblemDesc() {
		return problemDesc;
	}

	public void setProblemDesc(String problemDesc) {
		this.problemDesc = problemDesc;
	}

	public String getWebVersion() {
		return webVersion;
	}

	public void setWebVersion(String webVersion) {
		this.webVersion = webVersion;
	}

	public Integer getServicePeriodId() {
		return servicePeriodId;
	}

	public void setServicePeriodId(Integer servicePeriodId) {
		this.servicePeriodId = servicePeriodId;
	}

	public Integer getFlagType() {
		return flagType;
	}

	public void setFlagType(Integer flagType) {
		this.flagType = flagType;
	}

}
