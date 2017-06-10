package com.ucap.cloud_web.entity;


import java.util.Date;


/*** <br>* <b>作者：</b>liujc<br>* <b>日期：</b> 2016-11-22 10:33:04 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public class SpArticle {


	//主键
	private int id;

	//标题
	private String title;

	//副标题
	private String subTitle;

	//栏目id
	private int channelId;

	//页面标题
	private String pageTitle;

	//关键词
	private String keyword;

	//是否公共（0：否，1：是）
	private Integer isPub; 

	//链接地址
	private String link;

	//来源网址
	private String sourceUrl;

	//来源
	private String sourceName;

	//概要
	private String summary;

	//内容
	private String content;

	//排序
	private int sort;

	//浏览量
	private int browerCount;

	//删除标识（0:正常，1：删除）
	private Integer delFlag;

	//是否显示（1：显示，0：不显示）
	private Integer isShow;

	//是否置顶（0：否，1：是）
	private Integer isTop;

	//发布日期
	private Date pubDate;

	//添加人
	private int creater;

	//添加时间
	private Date createTime;

	//修改人
	private int modifyer;

	//修改时间
	private Date modifyTime;

	/** set 主键 */
	public void setId(int id){
		this.id=id;
	}

	/** get 主键 */
	public int getId(){
		return id;
	}

	/** set 标题 */
	public void setTitle(String title){
		this.title=title;
	}

	/** get 标题 */
	public String getTitle(){
		return title;
	}

	/** set 副标题 */
	public void setSubTitle(String subTitle){
		this.subTitle=subTitle;
	}

	/** get 副标题 */
	public String getSubTitle(){
		return subTitle;
	}

	/** set 栏目id */
	public void setChannelId(int channelId){
		this.channelId=channelId;
	}

	/** get 栏目id */
	public int getChannelId(){
		return channelId;
	}

	/** set 页面标题 */
	public void setPageTitle(String pageTitle){
		this.pageTitle=pageTitle;
	}

	/** get 页面标题 */
	public String getPageTitle(){
		return pageTitle;
	}

	/** set 关键词 */
	public void setKeyword(String keyword){
		this.keyword=keyword;
	}

	/** get 关键词 */
	public String getKeyword(){
		return keyword;
	}

	/** set 是否公共（0：否，1：是） */
	public void setIsPub(Integer isPub){
		this.isPub=isPub;
	}

	/** get 是否公共（0：否，1：是） */
	public Integer getIsPub(){
		return isPub;
	}

	/** set 链接地址 */
	public void setLink(String link){
		this.link=link;
	}

	/** get 链接地址 */
	public String getLink(){
		return link;
	}

	/** set 来源网址 */
	public void setSourceUrl(String sourceUrl){
		this.sourceUrl=sourceUrl;
	}

	/** get 来源网址 */
	public String getSourceUrl(){
		return sourceUrl;
	}

	/** set 来源 */
	public void setSourceName(String sourceName){
		this.sourceName=sourceName;
	}

	/** get 来源 */
	public String getSourceName(){
		return sourceName;
	}

	/** set 概要 */
	public void setSummary(String summary){
		this.summary=summary;
	}

	/** get 概要 */
	public String getSummary(){
		return summary;
	}

	/** set 内容 */
	public void setContent(String content){
		this.content=content;
	}

	/** get 内容 */
	public String getContent(){
		return content;
	}

	/** set 排序 */
	public void setSort(int sort){
		this.sort=sort;
	}

	/** get 排序 */
	public int getSort(){
		return sort;
	}

	/** set 浏览量 */
	public void setBrowerCount(int browerCount){
		this.browerCount=browerCount;
	}

	/** get 浏览量 */
	public int getBrowerCount(){
		return browerCount;
	}

	/** set 删除标识（0:正常，1：删除） */
	public void setDelFlag(Integer delFlag){
		this.delFlag=delFlag;
	}

	/** get 删除标识（0:正常，1：删除） */
	public Integer getDelFlag(){
		return delFlag;
	}

	/** set 是否显示（1：显示，0：不显示） */
	public void setIsShow(Integer isShow){
		this.isShow=isShow;
	}

	/** get 是否显示（1：显示，0：不显示） */
	public Integer getIsShow(){
		return isShow;
	}

	/** set 是否置顶（0：否，1：是） */
	public void setIsTop(Integer isTop){
		this.isTop=isTop;
	}

	/** get 是否置顶（0：否，1：是） */
	public Integer getIsTop(){
		return isTop;
	}

	/** set 发布日期 */
	public void setPubDate(Date pubDate){
		this.pubDate=pubDate;
	}

	/** get 发布日期 */
	public Date getPubDate(){
		return pubDate;
	}

	/** set 添加人 */
	public void setCreater(int creater){
		this.creater=creater;
	}

	/** get 添加人 */
	public int getCreater(){
		return creater;
	}

	/** set 添加时间 */
	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}

	/** get 添加时间 */
	public Date getCreateTime(){
		return createTime;
	}

	/** set 修改人 */
	public void setModifyer(int modifyer){
		this.modifyer=modifyer;
	}

	/** get 修改人 */
	public int getModifyer(){
		return modifyer;
	}

	/** set 修改时间 */
	public void setModifyTime(Date modifyTime){
		this.modifyTime=modifyTime;
	}

	/** get 修改时间 */
	public Date getModifyTime(){
		return modifyTime;
	}
}

