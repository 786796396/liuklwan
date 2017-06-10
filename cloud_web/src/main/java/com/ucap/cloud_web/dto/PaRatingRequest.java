package com.ucap.cloud_web.dto;


import com.publics.util.page.Query;


/*** 前台页面传递基础数据<br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-08-23 10:59:23 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@SuppressWarnings("serial")public class PaRatingRequest extends Query {

	//任务 id
	private Integer taskId;
	//任务与对象关联表 id
	private Integer paTargetTaskId ;
	
	//前后台添加标识 
	private Integer addType ;
	
	//自评详情 id
	private Integer paRatingDetailId;
	
	
	
	
	public Integer getPaRatingDetailId() {
		return paRatingDetailId;
	}
	public void setPaRatingDetailId(Integer paRatingDetailId) {
		this.paRatingDetailId = paRatingDetailId;
	}
	public Integer getAddType() {
		return addType;
	}
	public void setAddType(Integer addType) {
		this.addType = addType;
	}
	public Integer getTaskId() {
		return taskId;
	}
	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}
	public Integer getPaTargetTaskId() {
		return paTargetTaskId;
	}
	public void setPaTargetTaskId(Integer paTargetTaskId) {
		this.paTargetTaskId = paTargetTaskId;
	}
	
}

