package com.ucap.cloud_web.entity;


import java.util.Date;


/*** <br>* <b>作者：</b>Na.Y<br>* <b>日期：</b> 2016-09-21 13:43:19 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public class NoticeSender {


	//	private int id;
	//组织单位编码	private String orgSiteCode;

	//组织单位名称
	private String orgSiteName;
		//主题	private String topic;
	//内容	private String contents;
	//附件url	private String affixUrl;
	//附件名（原文件名）	private String affixName;
	//是否下级反馈（1：是，0：否）	private int isFeedback;
	//反馈截止日期（yyyy-mm-dd）	private String feedDeadlineDate;
	//状态（0：提交，1：发布）	private int status;
	//发布日期（yyyy-mm-dd）	private String publishDate;
	//创建时间	private Date createDate;
	//修改时间	private Date modifyTime;

		public String getOrgSiteName() {
		return orgSiteName;
	}

	public void setOrgSiteName(String orgSiteName) {
		this.orgSiteName = orgSiteName;
	}

	/** set  */	public void setId(int id){
		this.id=id;
	}
	/** get  */	public int getId(){
		return id;
	}
	/** set 组织单位编码 */	public void setOrgSiteCode(String orgSiteCode){
		this.orgSiteCode=orgSiteCode;
	}
	/** get 组织单位编码 */	public String getOrgSiteCode(){
		return orgSiteCode;
	}
	/** set 主题 */	public void setTopic(String topic){
		this.topic=topic;
	}
	/** get 主题 */	public String getTopic(){
		return topic;
	}
	/** set 内容 */	public void setContents(String contents){
		this.contents=contents;
	}
	/** get 内容 */	public String getContents(){
		return contents;
	}
	/** set 附件url */	public void setAffixUrl(String affixUrl){
		this.affixUrl=affixUrl;
	}
	/** get 附件url */	public String getAffixUrl(){
		return affixUrl;
	}
	/** set 附件名（原文件名） */	public void setAffixName(String affixName){
		this.affixName=affixName;
	}
	/** get 附件名（原文件名） */	public String getAffixName(){
		return affixName;
	}
	/** set 是否下级反馈（1：是，0：否） */	public void setIsFeedback(int isFeedback){
		this.isFeedback=isFeedback;
	}
	/** get 是否下级反馈（1：是，0：否） */	public int getIsFeedback(){
		return isFeedback;
	}
	/** set 反馈截止日期（yyyy-mm-dd） */	public void setFeedDeadlineDate(String feedDeadlineDate){
		this.feedDeadlineDate=feedDeadlineDate;
	}
	/** get 反馈截止日期（yyyy-mm-dd） */	public String getFeedDeadlineDate(){
		return feedDeadlineDate;
	}
	/** set 状态（0：提交，1：发布） */	public void setStatus(int status){
		this.status=status;
	}
	/** get 状态（0：提交，1：发布） */	public int getStatus(){
		return status;
	}
	/** set 发布日期（yyyy-mm-dd） */	public void setPublishDate(String publishDate){
		this.publishDate=publishDate;
	}
	/** get 发布日期（yyyy-mm-dd） */	public String getPublishDate(){
		return publishDate;
	}
	/** set 创建时间 */	public void setCreateDate(Date createDate){
		this.createDate=createDate;
	}
	/** get 创建时间 */	public Date getCreateDate(){
		return createDate;
	}
	/** set 修改时间 */	public void setModifyTime(Date modifyTime){
		this.modifyTime=modifyTime;
	}
	/** get 修改时间 */	public Date getModifyTime(){
		return modifyTime;
	}

	@Override
	public String toString() {
		return "NoticeSender [id=" + id + ", orgSiteCode=" + orgSiteCode
				+ ", orgSiteName=" + orgSiteName + ", topic=" + topic
				+ ", contents=" + contents + ", affixUrl=" + affixUrl
				+ ", affixName=" + affixName + ", isFeedback=" + isFeedback
				+ ", feedDeadlineDate=" + feedDeadlineDate + ", status="
				+ status + ", publishDate=" + publishDate + ", createDate="
				+ createDate + ", modifyTime=" + modifyTime + "]";
	}
	
	
}

