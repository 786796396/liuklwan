package com.ucap.cloud_web.entity;


import java.util.Date;


/*** <br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-08-23 10:59:23 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public class PaRatingChannel {


	//	private int id;
	//自评详情表id（pa_rating_detail）	private int ratingDetailId;
	//栏目表id（channel_point）	private int channelId;

	
	//前台添加为 1  后台为 2 
	private Integer addType;
		//截图保存地址（多个中间用 | 隔开）	private String imgUrl;
	//添加人id	private int userId;
	//修改人id	private int modifyUserId;
	//创建时间	private Date createTime;
	//修改时间	private Date modifyTime;
	
	//附件路径 
	private String path;
	// 附件别名
	private String aliasName;
	
	//栏目名称
	private String channelName;
	
	//栏目url
	private String channelUrl;

	
	
		public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getChannelUrl() {
		return channelUrl;
	}

	public void setChannelUrl(String channelUrl) {
		this.channelUrl = channelUrl;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getAliasName() {
		return aliasName;
	}

	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}

	public Integer getAddType() {
		return addType;
	}

	public void setAddType(Integer addType) {
		this.addType = addType;
	}

	/** set  */	public void setId(int id){
		this.id=id;
	}
	/** get  */	public int getId(){
		return id;
	}
	/** set 自评详情表id（pa_rating_detail） */	public void setRatingDetailId(int ratingDetailId){
		this.ratingDetailId=ratingDetailId;
	}
	/** get 自评详情表id（pa_rating_detail） */	public int getRatingDetailId(){
		return ratingDetailId;
	}
	/** set 栏目表id（channel_point） */	public void setChannelId(int channelId){
		this.channelId=channelId;
	}
	/** get 栏目表id（channel_point） */	public int getChannelId(){
		return channelId;
	}
	/** set 截图保存地址（多个中间用 | 隔开） */	public void setImgUrl(String imgUrl){
		this.imgUrl=imgUrl;
	}
	/** get 截图保存地址（多个中间用 | 隔开） */	public String getImgUrl(){
		return imgUrl;
	}
	/** set 添加人id */	public void setUserId(int userId){
		this.userId=userId;
	}
	/** get 添加人id */	public int getUserId(){
		return userId;
	}
	/** set 修改人id */	public void setModifyUserId(int modifyUserId){
		this.modifyUserId=modifyUserId;
	}
	/** get 修改人id */	public int getModifyUserId(){
		return modifyUserId;
	}
	/** set 创建时间 */	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}
	/** get 创建时间 */	public Date getCreateTime(){
		return createTime;
	}
	/** set 修改时间 */	public void setModifyTime(Date modifyTime){
		this.modifyTime=modifyTime;
	}
	/** get 修改时间 */	public Date getModifyTime(){
		return modifyTime;
	}
}

