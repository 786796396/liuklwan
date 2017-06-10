package com.ucap.cloud_web.entity;


import java.util.Date;


/*** <br>* <b>作者：</b>liujc<br>* <b>日期：</b> 2016-09-02 09:18:15 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public class SecurityQuestion {


	//内容保障问题--安全问题	private int id;
	//网站标识码	private String siteCode;
	
	//指标码表id（三级指标外键id）
	private Integer dicIndicatorId;
		//上传附件路径	private String path;
	//截图	private String imgUrl;
	//发现时间	private String scanDate;
	//问题描述	private String problemDesc;
	//浏览器版本	private String webVersion;
	//是否删除（0：正常，1：删除；默认0）	private int delFlag;
	//用户id	private int userId;
	//创建时间	private Date createTime;
	//修改时间	private Date modifyTime;
	/** set 内容保障问题--安全问题 */	public void setId(int id){
		this.id=id;
	}
	/** get 内容保障问题--安全问题 */	public int getId(){
		return id;
	}
	/** set 网站标识码 */	public void setSiteCode(String siteCode){
		this.siteCode=siteCode;
	}
	/** get 网站标识码 */	public String getSiteCode(){
		return siteCode;
	}
	/** set 上传附件路径 */	public void setPath(String path){
		this.path=path;
	}
	/** get 上传附件路径 */	public String getPath(){
		return path;
	}
	/** set 截图 */	public void setImgUrl(String imgUrl){
		this.imgUrl=imgUrl;
	}
	/** get 截图 */	public String getImgUrl(){
		return imgUrl;
	}
	/** set 发现时间 */	public void setScanDate(String scanDate){
		this.scanDate=scanDate;
	}
	/** get 发现时间 */	public String getScanDate(){
		return scanDate;
	}
	/** set 问题描述 */	public void setProblemDesc(String problemDesc){
		this.problemDesc=problemDesc;
	}
	/** get 问题描述 */	public String getProblemDesc(){
		return problemDesc;
	}
	/** set 浏览器版本 */	public void setWebVersion(String webVersion){
		this.webVersion=webVersion;
	}
	/** get 浏览器版本 */	public String getWebVersion(){
		return webVersion;
	}
	/** set 是否删除（0：正常，1：删除；默认0） */	public void setDelFlag(int delFlag){
		this.delFlag=delFlag;
	}
	/** get 是否删除（0：正常，1：删除；默认0） */	public int getDelFlag(){
		return delFlag;
	}
	/** set 用户id */	public void setUserId(int userId){
		this.userId=userId;
	}
	/** get 用户id */	public int getUserId(){
		return userId;
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

	public Integer getDicIndicatorId() {
		return dicIndicatorId;
	}

	public void setDicIndicatorId(Integer dicIndicatorId) {
		this.dicIndicatorId = dicIndicatorId;
	}
}

