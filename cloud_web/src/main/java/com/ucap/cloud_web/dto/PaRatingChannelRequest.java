package com.ucap.cloud_web.dto;


import com.publics.util.page.Query;


/*** 前台页面传递基础数据<br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-08-23 10:59:23 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@SuppressWarnings("serial")public class PaRatingChannelRequest extends Query {

	//
	private Integer ratingDetailId;
	
	private Integer id;
	
	private Integer targetTaskId;
	
	private Integer taskId;
	//添加类别（1：填报人添加，2：评分人添加）
	private Integer addType;
	
	public Integer getTargetTaskId() {
		return targetTaskId;
	}

	public void setTargetTaskId(Integer targetTaskId) {
		this.targetTaskId = targetTaskId;
	}

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRatingDetailId() {
		return ratingDetailId;
	}

	public void setRatingDetailId(Integer ratingDetailId) {
		this.ratingDetailId = ratingDetailId;
	}

	public Integer getAddType() {
		return addType;
	}

	public void setAddType(Integer addType) {
		this.addType = addType;
	}

}

