package com.ucap.cloud_web.entity;


import java.util.Date;


/*** <br>* <b>作者：</b>liukl<br>* <b>日期：</b> 2017-03-21 18:49:15 <br>* <b>版权所有：<b>版权所有(C) 2017<br>*/public class SpotSecurityReport {


	//抽查汇报上传任务表id	private int id;
	//抽查进度表id	private int spotCheckScheduleId;
	//组织单位site_code	private String siteCode;
	//任务名称	private String taskName;
	//监测开始时间（yyyy-mm-dd）	private String startDate;
	//监测结束时间(yyyy-mm-dd)	private String endDate;
	//1 有任务2：无任务	private int type;
	//状态（1：检查中，2：检查完成）	private int status;
	//抽查网站数量	private int spotWebsiteNum;
	//附件url	private String affixUrl;
	//附件名（原文件名）	private String affixName;
	//上报上级组织单位	private String reportOrgCode;
	//创建时间	private Date createTime;
	//修改时间	private Date modifyTime;
	/** set 抽查汇报上传任务表id */	public void setId(int id){
		this.id=id;
	}
	/** get 抽查汇报上传任务表id */	public int getId(){
		return id;
	}
	/** set 抽查进度表id */	public void setSpotCheckScheduleId(int spotCheckScheduleId){
		this.spotCheckScheduleId=spotCheckScheduleId;
	}
	/** get 抽查进度表id */	public int getSpotCheckScheduleId(){
		return spotCheckScheduleId;
	}
	/** set 组织单位site_code */	public void setSiteCode(String siteCode){
		this.siteCode=siteCode;
	}
	/** get 组织单位site_code */	public String getSiteCode(){
		return siteCode;
	}
	/** set 任务名称 */	public void setTaskName(String taskName){
		this.taskName=taskName;
	}
	/** get 任务名称 */	public String getTaskName(){
		return taskName;
	}
	/** set 监测开始时间（yyyy-mm-dd） */	public void setStartDate(String startDate){
		this.startDate=startDate;
	}
	/** get 监测开始时间（yyyy-mm-dd） */	public String getStartDate(){
		return startDate;
	}
	/** set 监测结束时间(yyyy-mm-dd) */	public void setEndDate(String endDate){
		this.endDate=endDate;
	}
	/** get 监测结束时间(yyyy-mm-dd) */	public String getEndDate(){
		return endDate;
	}
	/** set 1 有任务2：无任务 */	public void setType(int type){
		this.type=type;
	}
	/** get 1 有任务2：无任务 */	public int getType(){
		return type;
	}
	/** set 状态（1：检查中，2：检查完成） */	public void setStatus(int status){
		this.status=status;
	}
	/** get 状态（1：检查中，2：检查完成） */	public int getStatus(){
		return status;
	}
	/** set 抽查网站数量 */	public void setSpotWebsiteNum(int spotWebsiteNum){
		this.spotWebsiteNum=spotWebsiteNum;
	}
	/** get 抽查网站数量 */	public int getSpotWebsiteNum(){
		return spotWebsiteNum;
	}
	/** set 附件url */	public void setAffixUrl(String affixUrl){
		this.affixUrl=affixUrl;
	}
	/** get 附件url */	public String getAffixUrl(){
		return affixUrl;
	}
	/** set 附件名（原文件名） */	public void setAffixName(String affixName){
		this.affixName=affixName;
	}
	/** get 附件名（原文件名） */	public String getAffixName(){
		return affixName;
	}
	/** set 上报上级组织单位 */	public void setReportOrgCode(String reportOrgCode){
		this.reportOrgCode=reportOrgCode;
	}
	/** get 上报上级组织单位 */	public String getReportOrgCode(){
		return reportOrgCode;
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

