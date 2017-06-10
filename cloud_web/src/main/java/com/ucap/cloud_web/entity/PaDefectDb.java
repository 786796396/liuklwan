package com.ucap.cloud_web.entity;


import java.util.Date;


/*** <br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-08-23 10:59:22 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public class PaDefectDb {


	//	private int id;
	//站点标识码	private String siteCode;
	//缺陷库名称	private String name;
	//删除标识（1：删除，2：未删除）	private short delFlag;
	//是否启用（1：启用，2：禁用）	private short enabled;
	//创建时间	private Date createTime;
	//修改时间	private Date modifyTime;
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
	/** set 缺陷库名称 */	public void setName(String name){
		this.name=name;
	}
	/** get 缺陷库名称 */	public String getName(){
		return name;
	}
	/** set 删除标识（1：删除，2：未删除） */	public void setDelFlag(short delFlag){
		this.delFlag=delFlag;
	}
	/** get 删除标识（1：删除，2：未删除） */	public short getDelFlag(){
		return delFlag;
	}
	/** set 是否启用（1：启用，2：禁用） */	public void setEnabled(short enabled){
		this.enabled=enabled;
	}
	/** get 是否启用（1：启用，2：禁用） */	public short getEnabled(){
		return enabled;
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

