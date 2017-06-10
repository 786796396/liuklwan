package com.ucap.cloud_web.entity;


import java.util.Date;


/*** <br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-08-23 10:59:22 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public class PaAppraisal {


	//	private int id;
	//项目表id（pa_project）	private int projectId;
	//名称	private String name;
	//网站总数	private int siteCount;
	//新建网站总数	private int newSiteCount;
	//添加人id	private int userId;
	//修改人id	private int modifyUserId;
	//创建时间	private Date createTime;
	//修改时间	private Date modifyTime;
	/** set  */	public void setId(int id){
		this.id=id;
	}
	/** get  */	public int getId(){
		return id;
	}
	/** set 项目表id（pa_project） */	public void setProjectId(int projectId){
		this.projectId=projectId;
	}
	/** get 项目表id（pa_project） */	public int getProjectId(){
		return projectId;
	}
	/** set 名称 */	public void setName(String name){
		this.name=name;
	}
	/** get 名称 */	public String getName(){
		return name;
	}
	/** set 网站总数 */	public void setSiteCount(int siteCount){
		this.siteCount=siteCount;
	}
	/** get 网站总数 */	public int getSiteCount(){
		return siteCount;
	}
	/** set 新建网站总数 */	public void setNewSiteCount(int newSiteCount){
		this.newSiteCount=newSiteCount;
	}
	/** get 新建网站总数 */	public int getNewSiteCount(){
		return newSiteCount;
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

