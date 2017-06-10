package com.ucap.cloud_web.dto;


import com.publics.util.page.Query;


/*** 前台页面传递基础数据<br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-08-26 09:34:34 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@SuppressWarnings("serial")public class PaAdviceRequest extends Query {
	private Integer id;
	private String siteCode;
	private Integer noReadNum;//前台未读数量
	private Integer backNoReadNum;//后台未读数量
	private String title;
	private String content;
	private String senderName;
	private String senderPhone;
	private String senderEmail;
	private String createTime;
	
	
	
	
	
	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNoReadNum() {
		return noReadNum;
	}

	public void setNoReadNum(Integer noReadNum) {
		this.noReadNum = noReadNum;
	}

	public Integer getBackNoReadNum() {
		return backNoReadNum;
	}

	public void setBackNoReadNum(Integer backNoReadNum) {
		this.backNoReadNum = backNoReadNum;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getSenderPhone() {
		return senderPhone;
	}

	public void setSenderPhone(String senderPhone) {
		this.senderPhone = senderPhone;
	}

	public String getSenderEmail() {
		return senderEmail;
	}

	public void setSenderEmail(String senderEmail) {
		this.senderEmail = senderEmail;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}


}

