package com.ucap.cloud_web.entity;

import java.util.Date;

/**
 * <br>
 * <b>作者：</b>Nora<br>
 * <b>日期：</b> 2015-10-29 11:19:33 <br>
 * <b>版权所有：<b>版权所有(C) 2015<br>
 */
public class ConnectionHomeDetail {

	// 首页连通性详情表id
	private int id;

	// 连通性id
	private int connectionAllId;

	// 网站标识码
	private String siteCode;

	// 扫描时间（yyyy-dd-mm）
	private String scanDate;

	// 连通状态
	private int state;

	// 问题描述
	private String questionDescribe;

	// 问题代码
	private String questionCode;

	// 连通时间（接口返回时间）
	private String scanTime;

	// 创建时间
	private Date createTime;

	/** set 首页连通性详情表id */
	public void setId(int id) {
		this.id = id;
	}

	/** get 首页连通性详情表id */
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

	/** set 扫描时间（yyyy-dd-mm） */
	public void setScanDate(String scanDate) {
		this.scanDate = scanDate;
	}

	/** get 扫描时间（yyyy-dd-mm） */
	public String getScanDate() {
		return scanDate;
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

	/** set 连通时间（接口返回时间） */
	public void setScanTime(String scanTime) {
		this.scanTime = scanTime;
	}

	/** get 连通时间（接口返回时间） */
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

	@Override
	public String toString() {
		return "ConnectionHomeDetail [id=" + id + ", connectionAllId="
				+ connectionAllId + ", siteCode=" + siteCode + ", scanDate="
				+ scanDate + ", state=" + state + ", questionDescribe="
				+ questionDescribe + ", questionCode=" + questionCode
				+ ", scanTime=" + scanTime + ", createTime=" + createTime + "]";
	}

}
