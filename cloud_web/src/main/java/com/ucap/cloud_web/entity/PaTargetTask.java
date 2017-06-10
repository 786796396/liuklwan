package com.ucap.cloud_web.entity;


import java.util.Date;


/*** <br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-08-23 10:59:23 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public class PaTargetTask {


	//	private int id;
	//任务表id（pa_task）	private int taskId;
	//对象表id（pa_targer）	private int targetId;
	//站点标识码	private String siteCode;
	//自评状态（1：已提交，2：未自评，3：填报中）	private short ratingState;
	//考评状态（1：考评完成，2：考评中，3：未考评）	private short appraisalState;
	//状态 2：不进行考评，1：参与考评	private short stauts;
	//监管系统是否存在 2：不存在，1：存在	private short isExist;
	//是否开放（1：开放，2：关闭）	private short isOpen;
	//自动生成报告路径	private String createReportPath;
	//自动自评报告路径
	private String ratingReportPath;	//上传报告路径	private String uploadReportPath;
	//自评进度	private double ratingProgress;
	//打分进度	private double markProgress;
	//创建时间	private Date createTime;
	//修改时间	private Date modifyTime;
	public String getRatingReportPath() {
		return ratingReportPath;
	}

	public void setRatingReportPath(String ratingReportPath) {
		this.ratingReportPath = ratingReportPath;
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
	/** set 对象表id（pa_targer） */	public void setTargetId(int targetId){
		this.targetId=targetId;
	}
	/** get 对象表id（pa_targer） */	public int getTargetId(){
		return targetId;
	}
	/** set 站点标识码 */	public void setSiteCode(String siteCode){
		this.siteCode=siteCode;
	}
	/** get 站点标识码 */	public String getSiteCode(){
		return siteCode;
	}
	/** set 自评状态（1：已提交，2：未自评，3：填报中） */	public void setRatingState(short ratingState){
		this.ratingState=ratingState;
	}
	/** get 自评状态（1：已提交，2：未自评，3：填报中） */	public short getRatingState(){
		return ratingState;
	}
	/** set 考评状态（1：考评完成，2：考评中，3：未考评） */	public void setAppraisalState(short appraisalState){
		this.appraisalState=appraisalState;
	}
	/** get 考评状态（1：考评完成，2：考评中，3：未考评） */	public short getAppraisalState(){
		return appraisalState;
	}
	/** set 状态 2：不进行考评，1：参与考评 */	public void setStauts(short stauts){
		this.stauts=stauts;
	}
	/** get 状态 2：不进行考评，1：参与考评 */	public short getStauts(){
		return stauts;
	}
	/** set 监管系统是否存在 2：不存在，1：存在 */	public void setIsExist(short isExist){
		this.isExist=isExist;
	}
	/** get 监管系统是否存在 2：不存在，1：存在 */	public short getIsExist(){
		return isExist;
	}
	/** set 是否开放（1：开放，2：关闭） */	public void setIsOpen(short isOpen){
		this.isOpen=isOpen;
	}
	/** get 是否开放（1：开放，2：关闭） */	public short getIsOpen(){
		return isOpen;
	}
	/** set 自动生成报告路径 */	public void setCreateReportPath(String createReportPath){
		this.createReportPath=createReportPath;
	}
	/** get 自动生成报告路径 */	public String getCreateReportPath(){
		return createReportPath;
	}
	/** set 上传报告路径 */	public void setUploadReportPath(String uploadReportPath){
		this.uploadReportPath=uploadReportPath;
	}
	/** get 上传报告路径 */	public String getUploadReportPath(){
		return uploadReportPath;
	}

	public double getRatingProgress() {
		return ratingProgress;
	}

	public void setRatingProgress(double ratingProgress) {
		this.ratingProgress = ratingProgress;
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

