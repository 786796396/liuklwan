package com.ucap.cloud_web.entity;

import java.util.Date;

/**
 * <br>
 * <b>作者：</b>Na.Y<br>
 * <b>日期：</b> 2016-09-21 13:42:55 <br>
 * <b>版权所有：<b>版权所有(C) 2016<br>
 */
public class NoticeReceiver {

	//
	private int id;

	// 填报单位编码
	private String siteCode;

	// 网站名称
	private String siteName;

	// 通知表发送Id（组织单位发送通知Id）
	private int noticeSenderId;

	// 反馈内容
	private String feedContents;

	// 反馈附件url
	private String feedAffixUrl;

	// 反馈附件名（原文件名）
	private String feedAffixName;

	// 反馈状态（0：无需反馈，1：未提交，2：已提交，3：退回）
	private int status;

	// 反馈日期（yyyy-mm-dd）
	private String feedDate;

	// 是否已读（1：是：0：否）
	private int isRead;

	// 创建时间
	private Date createTime;

	// 修改时间
	private Date modifyTime;

	// 退回时间
	private Date returnTime;

	// 通知相关信息，用于查询时返回封装对象 start
	// 主题
	private String topic;

	// 内容
	private String contents;

	// 附件url
	private String affixUrl;

	// 附件名（原文件名）
	private String affixName;

	//反馈截止日期（yyyy-mm-dd）
	private String feedDeadlineDate;

	// 通知相关信息，用于查询时返回封装对象 end

	public String getFeedDeadlineDate() {
		return feedDeadlineDate;
	}

	public void setFeedDeadlineDate(String feedDeadlineDate) {
		this.feedDeadlineDate = feedDeadlineDate;
	}

	public Date getReturnTime() {
		return returnTime;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getAffixUrl() {
		return affixUrl;
	}

	public void setAffixUrl(String affixUrl) {
		this.affixUrl = affixUrl;
	}

	public String getAffixName() {
		return affixName;
	}

	public void setAffixName(String affixName) {
		this.affixName = affixName;
	}

	public void setReturnTime(Date returnTime) {
		this.returnTime = returnTime;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	/** set */
	public void setId(int id) {
		this.id = id;
	}

	/** get */
	public int getId() {
		return id;
	}

	/** set 填报单位编码 */
	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	/** get 填报单位编码 */
	public String getSiteCode() {
		return siteCode;
	}

	/** set 通知表发送Id（组织单位发送通知Id） */
	public void setNoticeSenderId(int noticeSenderId) {
		this.noticeSenderId = noticeSenderId;
	}

	/** get 通知表发送Id（组织单位发送通知Id） */
	public int getNoticeSenderId() {
		return noticeSenderId;
	}

	/** set 反馈内容 */
	public void setFeedContents(String feedContents) {
		this.feedContents = feedContents;
	}

	/** get 反馈内容 */
	public String getFeedContents() {
		return feedContents;
	}

	/** set 反馈附件url */
	public void setFeedAffixUrl(String feedAffixUrl) {
		this.feedAffixUrl = feedAffixUrl;
	}

	/** get 反馈附件url */
	public String getFeedAffixUrl() {
		return feedAffixUrl;
	}

	/** set 反馈附件名（原文件名） */
	public void setFeedAffixName(String feedAffixName) {
		this.feedAffixName = feedAffixName;
	}

	/** get 反馈附件名（原文件名） */
	public String getFeedAffixName() {
		return feedAffixName;
	}

	/** set 反馈状态（0：无需反馈，1：未提交，2：已提交，3：退回） */
	public void setStatus(int status) {
		this.status = status;
	}

	/** get 反馈状态（0：无需反馈，1：未提交，2：已提交，3：退回） */
	public int getStatus() {
		return status;
	}

	/** set 反馈日期（yyyy-mm-dd） */
	public void setFeedDate(String feedDate) {
		this.feedDate = feedDate;
	}

	/** get 反馈日期（yyyy-mm-dd） */
	public String getFeedDate() {
		return feedDate;
	}

	/** set 是否已读（1：是：0：否） */
	public void setIsRead(int isRead) {
		this.isRead = isRead;
	}

	/** get 是否已读（1：是：0：否） */
	public int getIsRead() {
		return isRead;
	}

	/** set 创建时间 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/** get 创建时间 */
	public Date getCreateTime() {
		return createTime;
	}

	/** set 修改时间 */
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	/** get 修改时间 */
	public Date getModifyTime() {
		return modifyTime;
	}

}
