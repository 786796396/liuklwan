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
public class CorrectContentDetail {

	// 内容正确性Id
	private int id;

	// 内容正确性趋势表Id
	private int correctContentInfo;

	// 网站标识码
	private String siteCode;

	// 扫描时间（yyyy-dd-mm）
	private String scanDate;

	// 内容正确性类型（确定，疑似，可能）
	private int correctType;

	// 问题描述
	private String problemDesc;

	// 网站url(内容正确性监测)
	private String url;

	// 截图
	private String imgUrl;

	// 错误词汇
	private String wrongTerm;

	// 推荐词(多个推荐词之间用|分隔)
	private String expectTerms;

	// 创建时间
	private Date createTime;

	// 操作人 add by Nora 20160220
	private int operator;

	// 是否删除（0：正常，1：删除；默认0）
	private int delFlag;

	public int getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(int delFlag) {
		this.delFlag = delFlag;
	}

	public int getOperator() {
		return operator;
	}

	public void setOperator(int operator) {
		this.operator = operator;
	}

	/** set 内容正确性Id */
	public void setId(int id) {
		this.id = id;
	}

	/** get 内容正确性Id */
	public int getId() {
		return id;
	}

	/** set 内容正确性趋势表Id */
	public void setCorrectContentInfo(int correctContentInfo) {
		this.correctContentInfo = correctContentInfo;
	}

	/** get 内容正确性趋势表Id */
	public int getCorrectContentInfo() {
		return correctContentInfo;
	}

	/** set 网站标识码 */
	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	/** get 网站标识码 */
	public String getSiteCode() {
		return siteCode;
	}

	/** set 内容正确性类型（确定，疑似，可能） */
	public void setCorrectType(int correctType) {
		this.correctType = correctType;
	}

	/** get 内容正确性类型（确定，疑似，可能） */
	public int getCorrectType() {
		return correctType;
	}

	/** set 问题描述 */
	public void setProblemDesc(String problemDesc) {
		this.problemDesc = problemDesc;
	}

	/** get 问题描述 */
	public String getProblemDesc() {
		return problemDesc;
	}

	/** set 网站url(内容正确性监测) */
	public void setUrl(String url) {
		this.url =url;
	}

	/** get 网站url(内容正确性监测) */
	public String getUrl() {
		return url;
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

	public String getScanDate() {
		return scanDate;
	}

	public void setScanDate(String scanDate) {
		this.scanDate = scanDate;
	}

	public String getWrongTerm() {
		return wrongTerm;
	}

	public void setWrongTerm(String wrongTerm) {
		this.wrongTerm = wrongTerm;
	}

	public String getExpectTerms() {
		return expectTerms;
	}

	public void setExpectTerms(String expectTerms) {
		this.expectTerms = expectTerms;
	}

}
