package com.ucap.cloud_web.dto;


import com.publics.util.page.Query;


/*** 前台页面传递基础数据<br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-08-24 13:38:42 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@SuppressWarnings("serial")public class PaTargetRequest extends Query {
	
	//项目 id
	private Integer appraisalId;
	//任务id
	private Integer taskId;
	
	//考评状态
	private Integer appraisalState;
	// 站点标识码  或者  名称

	private String siteOrName;
	
	
	public Integer getAppraisalState() {
		return appraisalState;
	}
	public void setAppraisalState(Integer appraisalState) {
		this.appraisalState = appraisalState;
	}
	public String getSiteOrName() {
		return siteOrName;
	}
	public void setSiteOrName(String siteOrName) {
		this.siteOrName = siteOrName;
	}
	public Integer getTaskId() {
		return taskId;
	}
	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}
	public Integer getAppraisalId() {
		return appraisalId;
	}
	public void setAppraisalId(Integer appraisalId) {
		this.appraisalId = appraisalId;
	}
	


}

