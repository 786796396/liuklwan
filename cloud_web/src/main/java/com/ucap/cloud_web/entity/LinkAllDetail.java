package com.ucap.cloud_web.entity;

import java.util.Date;

import com.publics.util.utils.StringUtils;
import com.ucap.cloud_web.util.CommonUtils;

/**
 * <br>
 * <b>作者：</b>cuichx<br>
 * <b>日期：</b> 2015-10-29 10:48:14 <br>
 * <b>版权所有：<b>版权所有(C) 2015<br>
 */
public class LinkAllDetail {

	// 全站连接可用性检测结果详情表id
	private int id;

	// 全站连接可用性趋势表id
	private int linkAllInfoId;

	// 期号id
	private int servicePeriodId;

	// 网站标识码
	private String siteCode;

	// 不可用连接url
	private String url;
	
	//父页面url
	private String parentUrl;
	
	// 检测层次
	private int scanLevel;

	// 连接标题
	private String linkTitle;

	// 父页面标题
	private String parentTitle;

	// 死链类别（站内死链=0,域内死链=1,域外死链=2,域内空白标题死链=3,域外空白标题死链=4,域内附件死链=5,域外附件死链=6,域内图片死链=7,域外图片死链=8,非法URL不符合URL规则,没有经过httpclient验证的死链
	// = 9）
	private String urlType;

	// 资源类型
	private int resourceType;

	// 范围(站内、域外)
	private int scope;

	// 错误描述
	private String errorDescribe;

	// 错误代码
	private String errorCode;

	// 快照
	private String imgUrl;

	// 扫描时间
	private String scanTime;
	//监测时间
	private String scanDate;
	//连接不可用天数
	private int linkNotavailableDay;
	//周期表id
	private Integer relationsPeriod;
	
	// 创建时间
	private Date createTime;

	// 操作人 add by Nora 20160220
	private int operator;
	
	//站内站外类型> 1:站内，2:站外
	private Integer webType;
	
	public int getOperator() {
		return operator;
	}

	public void setOperator(int operator) {
		this.operator = operator;
	}

	/** set 全站连接可用性检测结果详情表id */
	public void setId(int id) {
		this.id = id;
	}

	/** get 全站连接可用性检测结果详情表id */
	public int getId() {
		return id;
	}

	/** set 全站连接可用性趋势表id */
	public void setLinkAllInfoId(int linkAllInfoId) {
		this.linkAllInfoId = linkAllInfoId;
	}

	/** get 全站连接可用性趋势表id */
	public int getLinkAllInfoId() {
		return linkAllInfoId;
	}

	/** set 期号id */
	public void setServicePeriodId(int servicePeriodId) {
		this.servicePeriodId = servicePeriodId;
	}

	/** get 期号id */
	public int getServicePeriodId() {
		return servicePeriodId;
	}

	/** set 网站标识码 */
	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	/** get 网站标识码 */
	public String getSiteCode() {
		return siteCode;
	}

	/** set 不可用连接url */
	public void setUrl(String url) {
		this.url =url;
	}

	/** get 不可用连接url */
	public String getUrl() {
		return url;
	}

	/** set 检测层次 */
	public void setScanLevel(int scanLevel) {
		this.scanLevel = scanLevel;
	}

	/** get 检测层次 */
	public int getScanLevel() {
		return scanLevel;
	}

	/** set 连接标题 */
	public void setLinkTitle(String linkTitle) {
		this.linkTitle = linkTitle;
	}

	/** get 连接标题 */
	public String getLinkTitle() {
		return linkTitle;
	}

	/** set 父页面标题 */
	public void setParentTitle(String parentTitle) {
		this.parentTitle = parentTitle;
	}

	/** get 父页面标题 */
	public String getParentTitle() {
		return parentTitle;
	}

	/**
	 * set 死链类别（站内死链=0,域内死链=1,域外死链=2,域内空白标题死链=3,域外空白标题死链=4,域内附件死链=5,域外附件死链=6,
	 * 域内图片死链=7,域外图片死链=8,非法URL不符合URL规则,没有经过httpclient验证的死链 = 9）
	 */
	public void setUrlType(String urlType) {
		this.urlType = urlType;
	}

	/**
	 * get 死链类别（站内死链=0,域内死链=1,域外死链=2,域内空白标题死链=3,域外空白标题死链=4,域内附件死链=5,域外附件死链=6,
	 * 域内图片死链=7,域外图片死链=8,非法URL不符合URL规则,没有经过httpclient验证的死链 = 9）
	 */
	public String getUrlType() {
		return urlType;
	}

	/** set 资源类型 */
	public void setResourceType(int resourceType) {
		this.resourceType = resourceType;
	}

	/** get 资源类型 */
	public int getResourceType() {
		return resourceType;
	}

	/** set 范围(站内、域外) */
	public void setScope(int scope) {
		this.scope = scope;
	}

	/** get 范围(站内、域外) */
	public int getScope() {
		return scope;
	}

	/** set 错误描述 */
	public void setErrorDescribe(String errorDescribe) {
		this.errorDescribe = errorDescribe;
	}

	/** get 错误描述 */
	public String getErrorDescribe() {
		return errorDescribe;
	}

	/** set 错误代码 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/** get 错误代码 */
	public String getErrorCode() {
		return errorCode;
	}

	/** set 快照 */
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	/** get 快照 */
	public String getImgUrl() {
		return imgUrl;
	}

	/** set 扫描时间 */
	public void setScanTime(String scanTime) {
		this.scanTime = scanTime;
	}

	/** get 扫描时间 */
	public String getScanTime() {
		return scanTime;
	}

	/** set 创建时间 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/** get 创建时间 */
	public Date getCreateTime() {
		return createTime;
	}

	public String getScanDate() {
		return scanDate;
	}

	public void setScanDate(String scanDate) {
		this.scanDate = scanDate;
	}

	public int getLinkNotavailableDay() {
		return linkNotavailableDay;
	}

	public void setLinkNotavailableDay(int linkNotavailableDay) {
		this.linkNotavailableDay = linkNotavailableDay;
	}

	public Integer getRelationsPeriod() {
		return relationsPeriod;
	}

	public void setRelationsPeriod(Integer relationsPeriod) {
		this.relationsPeriod = relationsPeriod;
	}

	public String getParentUrl() {
		return parentUrl;
	}

	public void setParentUrl(String parentUrl) {
		this.parentUrl = parentUrl;
	}

	public Integer getWebType() {
		return webType;
	}

	public void setWebType(Integer webType) {
		this.webType = webType;
	}


}
