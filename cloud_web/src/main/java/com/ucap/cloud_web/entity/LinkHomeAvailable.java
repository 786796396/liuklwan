package com.ucap.cloud_web.entity;

import java.util.Date;

import com.publics.util.utils.StringUtils;
import com.ucap.cloud_web.util.CommonUtils;

/**
 * <br>
 * <b>作者：</b>cuichx<br>
 * <b>日期：</b> 2015-10-29 10:48:52 <br>
 * <b>版权所有：<b>版权所有(C) 2015<br>
 */
public class LinkHomeAvailable {

	// 首页连接可用性表id
	private int id;

	// 首页连通趋势表id
	private int linkHomeTrendId;

	// 网站标识码
	private String siteCode;

	// 扫描时间（yyyy-dd-mm）
	private String scanDate;

	// url(首页连接可用性)
	private String url;
	
	//父页面url
	private String parentUrl;
	
	// 链接标题
	private String linkTitle;

	// 死链类别（站内死链=0,域内死链=1,域外死链=2,域内空白标题死链=3,域外空白标题死链=4,域内附件死链=5,域外附件死链=6,域内图片死链=7,域外图片死链=8,非法URL不符合URL规则,没有经过httpclient验证的死链
	// = 9）
	private int urlType;

	// 资源类型（图片、网页、附件、其他等）
	private int resourceType;

	// 范围(站内、域外)
	private int scope;

	// 连接不可用天数,第一天默认1，第二天与第一天比较，如有，新增一条天数加1的数据
	private int linkNotavailableDay;

	// 快照
	private String imgUrl;

	// 问题描述
	private String questionDescribe;

	// 问题代码
	private String questionCode;

	// 创建时间
	private Date createTime;

	// 操作人 add by Nora 20160220
	private int operator;
	
	private String scanTime;

	
	
	public String getScanTime() {
		return scanTime;
	}

	public void setScanTime(String scanTime) {
		this.scanTime = scanTime;
	}

	public int getOperator() {
		return operator;
	}

	public void setOperator(int operator) {
		this.operator = operator;
	}

	/** set 首页连接可用性表id */
	public void setId(int id) {
		this.id = id;
	}

	/** get 首页连接可用性表id */
	public int getId() {
		return id;
	}

	/** set 首页连通趋势表id */
	public void setLinkHomeTrendId(int linkHomeTrendId) {
		this.linkHomeTrendId = linkHomeTrendId;
	}

	/** get 首页连通趋势表id */
	public int getLinkHomeTrendId() {
		return linkHomeTrendId;
	}

	/** set 网站标识码 */
	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	/** get 网站标识码 */
	public String getSiteCode() {
		return siteCode;
	}

	/** set 扫描时间（yyyy-dd-mm） */
	public void setScanDate(String scanDate) {
		this.scanDate = scanDate;
	}

	/** get 扫描时间（yyyy-dd-mm） */
	public String getScanDate() {
		return scanDate;
	}

	/** set url(首页连接可用性) */
	public void setUrl(String url) {
		this.url = url;
	}

	/** get url(首页连接可用性) */
	public String getUrl() {
		return url;
	}

	/** set 链接标题 */
	public void setLinkTitle(String linkTitle) {
		this.linkTitle = linkTitle;
	}

	/** get 链接标题 */
	public String getLinkTitle() {
		return linkTitle;
	}

	/**
	 * set 死链类别（站内死链=0,域内死链=1,域外死链=2,域内空白标题死链=3,域外空白标题死链=4,域内附件死链=5,域外附件死链=6,
	 * 域内图片死链=7,域外图片死链=8,非法URL不符合URL规则,没有经过httpclient验证的死链 = 9）
	 */
	public void setUrlType(int urlType) {
		this.urlType = urlType;
	}

	/**
	 * get 死链类别（站内死链=0,域内死链=1,域外死链=2,域内空白标题死链=3,域外空白标题死链=4,域内附件死链=5,域外附件死链=6,
	 * 域内图片死链=7,域外图片死链=8,非法URL不符合URL规则,没有经过httpclient验证的死链 = 9）
	 */
	public int getUrlType() {
		return urlType;
	}

	/** set 资源类型（图片、网页、附件、其他等） */
	public void setResourceType(int resourceType) {
		this.resourceType = resourceType;
	}

	/** get 资源类型（图片、网页、附件、其他等） */
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

	/** set 连接不可用天数,第一天默认1，第二天与第一天比较，如有，新增一条天数加1的数据 */
	public void setLinkNotavailableDay(int linkNotavailableDay) {
		this.linkNotavailableDay = linkNotavailableDay;
	}

	/** get 连接不可用天数,第一天默认1，第二天与第一天比较，如有，新增一条天数加1的数据 */
	public int getLinkNotavailableDay() {
		return linkNotavailableDay;
	}

	/** set 快照 */
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	/** get 快照 */
	public String getImgUrl() {
		return imgUrl;
	}

	/** set 问题描述 */
	public void setQuestionDescribe(String questionDescribe) {
		this.questionDescribe = questionDescribe;
	}

	/** get 问题描述 */
	public String getQuestionDescribe() {
		return questionDescribe;
	}

	/** set 问题代码 */
	public void setQuestionCode(String questionCode) {
		this.questionCode = questionCode;
	}

	/** get 问题代码 */
	public String getQuestionCode() {
		return questionCode;
	}

	/** set 创建时间 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/** get 创建时间 */
	public Date getCreateTime() {
		return createTime;
	}

	public String getParentUrl() {
		return parentUrl;
	}

	public void setParentUrl(String parentUrl) {
		this.parentUrl = parentUrl;
	}


}
