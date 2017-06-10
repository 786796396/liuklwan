package com.ucap.cloud_web.entity;


import java.util.Date;


/*** <br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2015-10-29 10:45:27 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/public class DicIndicator {


	//指标码表id	private int id;
	//父类id	private int parentId;
	//名称	private String indicatorName;
	//监测周期（0：天，1：月）	private int period;
	//监测说明	private String remark;
	//创建时间	private Date createTime;
	//修改时间	private Date modifyTime;
	/** set 指标码表id */	public void setId(int id){
		this.id=id;
	}
	/** get 指标码表id */	public int getId(){
		return id;
	}
	/** set 父类id */	public void setParentId(int parentId){
		this.parentId=parentId;
	}
	/** get 父类id */	public int getParentId(){
		return parentId;
	}
	/** set 名称 */	public void setIndicatorName(String indicatorName){
		this.indicatorName=indicatorName;
	}
	/** get 名称 */	public String getIndicatorName(){
		return indicatorName;
	}
	/** set 监测周期（0：天，1：月） */	public void setPeriod(int period){
		this.period=period;
	}
	/** get 监测周期（0：天，1：月） */	public int getPeriod(){
		return period;
	}
	/** set 监测说明 */	public void setRemark(String remark){
		this.remark=remark;
	}
	/** get 监测说明 */	public String getRemark(){
		return remark;
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

