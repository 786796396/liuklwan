package com.ucap.cloud_web.dto;


import com.publics.util.page.Query;


/*** 前台页面传递基础数据<br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-08-23 10:59:24 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@SuppressWarnings("serial")public class PaTargetTaskRequest extends Query {
	
	private String siteCode ;
	
	private String ratingState;
	
	private Integer taskId;
	
	//2 不参与考评 1 参与考评
	private Integer stauts;

	public Integer getStauts() {
		return stauts;
	}

	public void setStauts(Integer stauts) {
		this.stauts = stauts;
	}

	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	public String getRatingState() {
		return ratingState;
	}

	public void setRatingState(String ratingState) {
		this.ratingState = ratingState;
	}

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}


}

