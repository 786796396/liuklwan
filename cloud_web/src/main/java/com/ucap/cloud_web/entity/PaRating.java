package com.ucap.cloud_web.entity;


import java.util.Date;


/*** <br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-08-23 10:59:23 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public class PaRating {


	//	private int id;
	//任务表id（pa_task）	private int taskId;
	//任务对象表id（ppa_target_task）	private int targetTaskId;
	//站点标识码	private String siteCode;
	//填报人	private String ratingName;
	//填报人电话	private String ratingPhone;
	//单位名称	private String companyName;
	//创建时间	private Date createTime;
	//修改时间	private Date modifyTime;
	
	//总体情况
	private String overallSituation;
	// 问题及建议
	private String problemsSuggestions;
	//其他说明
	private String other;
	//计划安排
	private String plan;
	
	
	
	public String getOverallSituation() {
		return overallSituation;
	}

	public void setOverallSituation(String overallSituation) {
		this.overallSituation = overallSituation;
	}

	public String getProblemsSuggestions() {
		return problemsSuggestions;
	}

	public void setProblemsSuggestions(String problemsSuggestions) {
		this.problemsSuggestions = problemsSuggestions;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getPlan() {
		return plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}

	/** set  */	public void setId(int id){
		this.id=id;
	}
	/** get  */	public int getId(){
		return id;
	}
	/** set 任务表id（pa_task） */	public void setTaskId(int taskId){
		this.taskId=taskId;
	}
	/** get 任务表id（pa_task） */	public int getTaskId(){
		return taskId;
	}
	/** set 任务对象表id（ppa_target_task） */	public void setTargetTaskId(int targetTaskId){
		this.targetTaskId=targetTaskId;
	}
	/** get 任务对象表id（ppa_target_task） */	public int getTargetTaskId(){
		return targetTaskId;
	}
	/** set 站点标识码 */	public void setSiteCode(String siteCode){
		this.siteCode=siteCode;
	}
	/** get 站点标识码 */	public String getSiteCode(){
		return siteCode;
	}
	/** set 填报人 */	public void setRatingName(String ratingName){
		this.ratingName=ratingName;
	}
	/** get 填报人 */	public String getRatingName(){
		return ratingName;
	}
	/** set 填报人电话 */	public void setRatingPhone(String ratingPhone){
		this.ratingPhone=ratingPhone;
	}
	/** get 填报人电话 */	public String getRatingPhone(){
		return ratingPhone;
	}
	/** set 单位名称 */	public void setCompanyName(String companyName){
		this.companyName=companyName;
	}
	/** get 单位名称 */	public String getCompanyName(){
		return companyName;
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

