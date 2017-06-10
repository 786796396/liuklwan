package com.ucap.cloud_web.entity;


import java.util.Date;


/*** <br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-08-23 10:59:24 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public class PaTask {


	//	private int id;
	//项目表id（pa_project）	private int projectId;
	//任务名称	private String taskName;
	//自评开始日期	private String startDate;
	//自评结束日期	private String endDate;
	//考评状态 1：未启动，2：进行中，3：已完成	private short stauts;
	//自评进度	private double ratingProgress;
	//打分进度	private double markProgress;
	//创建时间	private Date createTime;
	//修改时间	private Date modifyTime;
	
	//项目名称
	private String projectName;
	
	//  自评状态（1：已提交，2：未自评，3：填报中）
	private Integer ratingState;
	
	//任务对象关联表  id
	private Integer targetTaskId;

	//项目评估对象id
	private Integer appraisalId;

	//是否开放（1：开放，2：关闭）
	private Integer isOpen;
	
	public Integer getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(Integer isOpen) {
		this.isOpen = isOpen;
	}

	public Integer getAppraisalId() {
		return appraisalId;
	}

	public void setAppraisalId(Integer appraisalId) {
		this.appraisalId = appraisalId;
	}

	public Integer getTargetTaskId() {
		return targetTaskId;
	}

	public void setTargetTaskId(Integer targetTaskId) {
		this.targetTaskId = targetTaskId;
	}

	public Integer getRatingState() {
		return ratingState;
	}

	public void setRatingState(Integer ratingState) {
		this.ratingState = ratingState;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	/** set  */	public void setId(int id){
		this.id=id;
	}
	/** get  */	public int getId(){
		return id;
	}
	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/** set 项目表id（pa_project） */	public void setProjectId(int projectId){
		this.projectId=projectId;
	}
	/** get 项目表id（pa_project） */	public int getProjectId(){
		return projectId;
	}
	/** set 任务名称 */	public void setTaskName(String taskName){
		this.taskName=taskName;
	}
	/** get 任务名称 */	public String getTaskName(){
		return taskName;
	}

	/** set 考评状态 1：未启动，2：进行中，3：已完成 */	public void setStauts(short stauts){
		this.stauts=stauts;
	}
	/** get 考评状态 1：未启动，2：进行中，3：已完成 */	public short getStauts(){
		return stauts;
	}
	/** set 自评进度 */	public void setRatingProgress(double ratingProgress){
		this.ratingProgress=ratingProgress;
	}
	/** get 自评进度 */	public double getRatingProgress(){
		return ratingProgress;
	}
	/** set 打分进度 */	public void setMarkProgress(double markProgress){
		this.markProgress=markProgress;
	}
	/** get 打分进度 */	public double getMarkProgress(){
		return markProgress;
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

