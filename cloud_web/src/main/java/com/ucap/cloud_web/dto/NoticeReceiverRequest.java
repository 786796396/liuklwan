package com.ucap.cloud_web.dto;

import com.publics.util.page.Query;

/**
 * 前台页面传递基础数据<br>
 * <b>作者：</b>Na.Y<br>
 * <b>日期：</b> 2016-09-21 13:42:55 <br>
 * <b>版权所有：<b>版权所有(C) 2016<br>
 */
@SuppressWarnings("serial")
public class NoticeReceiverRequest extends Query {

	// 通知表发送Id（组织单位发送通知Id）
	private Integer noticeSenderId;

	// 反馈状态（0：无需反馈，1：未提交，2：已提交，3：退回）
	private Integer status;

	private String siteCode;

	private String siteName;

	// 网站标识码或网站名称模糊查询
	private String siteSearch;

	// 反馈开始日期（用于查询）
	private String feedDateStart;

	// 反馈结束日期（用于查询）
	private String feedDateEnd;

	// 通知：话题
	private String topic;

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getFeedDateStart() {
		return feedDateStart;
	}

	public void setFeedDateStart(String feedDateStart) {
		this.feedDateStart = feedDateStart;
	}

	public String getFeedDateEnd() {
		return feedDateEnd;
	}

	public void setFeedDateEnd(String feedDateEnd) {
		this.feedDateEnd = feedDateEnd;
	}

	public String getSiteSearch() {
		return siteSearch;
	}

	public void setSiteSearch(String siteSearch) {
		this.siteSearch = siteSearch;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getNoticeSenderId() {
		return noticeSenderId;
	}

	public void setNoticeSenderId(Integer noticeSenderId) {
		this.noticeSenderId = noticeSenderId;
	}

}
