package com.ucap.cloud_web.dto;


import com.publics.util.page.Query;


/**

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
