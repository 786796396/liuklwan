package com.ucap.cloud_web.entity;


import java.util.Date;


/*** <br>* <b>作者：</b>liujc<br>* <b>日期：</b> 2016-11-22 10:32:48 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public class SpArticlePermission {


	//主键	private int id;
	//文章表主键	private int articleId;
	//组织/站点标识码	private String siteCode;
	//添加人	private int creater;
	//添加时间	private Date createTime;
	/** set 主键 */	public void setId(int id){
		this.id=id;
	}
	/** get 主键 */	public int getId(){
		return id;
	}
	/** set 文章表主键 */	public void setArticleId(int articleId){
		this.articleId=articleId;
	}
	/** get 文章表主键 */	public int getArticleId(){
		return articleId;
	}
	/** set 组织/站点标识码 */	public void setSiteCode(String siteCode){
		this.siteCode=siteCode;
	}
	/** get 组织/站点标识码 */	public String getSiteCode(){
		return siteCode;
	}
	/** set 添加人 */	public void setCreater(int creater){
		this.creater=creater;
	}
	/** get 添加人 */	public int getCreater(){
		return creater;
	}
	/** set 添加时间 */	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}
	/** get 添加时间 */	public Date getCreateTime(){
		return createTime;
	}
}

