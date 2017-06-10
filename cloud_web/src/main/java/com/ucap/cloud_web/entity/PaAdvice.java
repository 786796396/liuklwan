package com.ucap.cloud_web.entity;


import java.util.Date;


/*** <br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-08-26 09:34:34 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public class PaAdvice {


	//	private int id;
	//站点标识码	private String siteCode;
	//留言标题	private String title;
	//内容	private String content;
	//留言人未读数量	private int noReadNum;
	//后台未读数量	private int backNoReadNum;
	//留言人姓名	private String senderName;
	//留言人电话	private String senderPhone;
	//留言人邮箱	private String senderEmail;
	//创建时间	private Date createTime;
	/** set  */	public void setId(int id){
		this.id=id;
	}
	/** get  */	public int getId(){
		return id;
	}
	/** set 站点标识码 */	public void setSiteCode(String siteCode){
		this.siteCode=siteCode;
	}
	/** get 站点标识码 */	public String getSiteCode(){
		return siteCode;
	}
	/** set 留言标题 */	public void setTitle(String title){
		this.title=title;
	}
	/** get 留言标题 */	public String getTitle(){
		return title;
	}
	/** set 内容 */	public void setContent(String content){
		this.content=content;
	}
	/** get 内容 */	public String getContent(){
		return content;
	}
	/** set 留言人未读数量 */	public void setNoReadNum(int noReadNum){
		this.noReadNum=noReadNum;
	}
	/** get 留言人未读数量 */	public int getNoReadNum(){
		return noReadNum;
	}
	/** set 后台未读数量 */	public void setBackNoReadNum(int backNoReadNum){
		this.backNoReadNum=backNoReadNum;
	}
	/** get 后台未读数量 */	public int getBackNoReadNum(){
		return backNoReadNum;
	}
	/** set 留言人姓名 */	public void setSenderName(String senderName){
		this.senderName=senderName;
	}
	/** get 留言人姓名 */	public String getSenderName(){
		return senderName;
	}
	/** set 留言人电话 */	public void setSenderPhone(String senderPhone){
		this.senderPhone=senderPhone;
	}
	/** get 留言人电话 */	public String getSenderPhone(){
		return senderPhone;
	}
	/** set 留言人邮箱 */	public void setSenderEmail(String senderEmail){
		this.senderEmail=senderEmail;
	}
	/** get 留言人邮箱 */	public String getSenderEmail(){
		return senderEmail;
	}
	/** set 创建时间 */	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}
	/** get 创建时间 */	public Date getCreateTime(){
		return createTime;
	}
}

