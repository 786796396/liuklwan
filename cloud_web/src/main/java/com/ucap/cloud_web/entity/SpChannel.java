package com.ucap.cloud_web.entity;


import java.util.Date;


/*** <br>* <b>作者：</b>liujc<br>* <b>日期：</b> 2016-11-22 10:32:16 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public class SpChannel {


	//	private int id;
	//栏目名称	private String clannelName;
	//删除标识（0：正常，1：删除）	private Integer delFlag;
	//添加人	private int creater;
	//添加时间	private Date createTime;
	//修改人	private int modiyer;
	//修改时间	private Date modifyTime;
	/** set  */	public void setId(int id){
		this.id=id;
	}
	/** get  */	public int getId(){
		return id;
	}
	/** set 栏目名称 */	public void setClannelName(String clannelName){
		this.clannelName=clannelName;
	}
	/** get 栏目名称 */	public String getClannelName(){
		return clannelName;
	}
	/** set 删除标识（0：正常，1：删除） */	public void setDelFlag(Integer delFlag){
		this.delFlag=delFlag;
	}
	/** get 删除标识（0：正常，1：删除） */	public Integer getDelFlag(){
		return delFlag;
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
	/** set 修改人 */	public void setModiyer(int modiyer){
		this.modiyer=modiyer;
	}
	/** get 修改人 */	public int getModiyer(){
		return modiyer;
	}
	/** set 修改时间 */	public void setModifyTime(Date modifyTime){
		this.modifyTime=modifyTime;
	}
	/** get 修改时间 */	public Date getModifyTime(){
		return modifyTime;
	}
}

