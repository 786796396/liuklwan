package com.ucap.cloud_web.entity;

import java.util.Date;

import com.publics.util.utils.StringUtils;
import com.ucap.cloud_web.util.CommonUtils;

/**
 * <br>
 * <b>作者：</b>Nora<br>
 * <b>日期：</b> 2015-10-29 11:19:33 <br>
 * <b>版权所有：<b>版权所有(C) 2015<br>
 */
public class ConnectionBusinessDetail {

	// 业务连通性详情表id
	private int id;

	// 连通性id
	private int connectionAllId;

	// 网站标识码
	private String siteCode;

	// 扫描日期（yyyy-dd-mm）
	private String scanDate;

	// 业务系统名称
	private String systemName;

	// url（业务连通性）
	private String url;

	// 扫描时间（接口返回）
	private String scanTime;

	// 连通状态
	private int state;

	// 问题描述
	private String questionDescribe;

	// 问题代码
	private String questionCode;

	// 创建时间
	private Date createTime;
	/** set 业务连通性详情表id */
	public void setId(int id) {
		this.id = id;
	}

	/** get 业务连通性详情表id */
	public int getId() {
		return id;
	}

	/** set 连通性id */
	public void setConnectionAllId(int connectionAllId) {
		this.connectionAllId = connectionAllId;
	}

	/** get 连通性id */
	public int getConnectionAllId() {
		return connectionAllId;
	}

	/** set 网站标识码 */
	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	/** get 网站标识码 */
	public String getSiteCode() {
		return siteCode;
	}

	/** set 扫描日期（yyyy-dd-mm） */
	public void setScanDate(String scanDate) {
		this.scanDate = scanDate;
	}

	/** get 扫描日期（yyyy-dd-mm） */
	public String getScanDate() {
		return scanDate;
	}

	/** set 业务系统名称 */
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	/** get 业务系统名称 */
	public String getSystemName() {
		return systemName;
	}

	/** set url（业务连通性） */
	public void setUrl(String url) {
		this.url = url;
	}

	/** get url（业务连通性） */
	public String getUrl() {
		return url;
	}

	/** set 扫描时间（接口返回） */
	public void setScanTime(String scanTime) {
		this.scanTime = scanTime;
	}

	/** get 扫描时间（接口返回） */
	public String getScanTime() {
		return scanTime;
	}

	/** set 连通状态 */
	public void setState(int state) {
		this.state = state;
	}

	/** get 连通状态 */
	public int getState() {
		return state;
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

	@Override
	public String toString() {
		return "ConnectionBusinessDetail [id=" + id + ", connectionAllId="
				+ connectionAllId + ", siteCode=" + siteCode + ", scanDate="
				+ scanDate + ", systemName=" + systemName + ", url=" + url
				+ ", scanTime=" + scanTime + ", state=" + state
				+ ", questionDescribe=" + questionDescribe + ", questionCode="
				+ questionCode + ", createTime=" + createTime + "]";
	}


}
