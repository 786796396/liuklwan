package com.ucap.cloud_web.entity;


import java.util.Date;


/*** <br>* <b>作者：</b>yuangw@ucap.com.cn<br>* <b>日期：</b> 2016-04-19 11:16:39 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public class SpotCheckSchedule {


	//抽查进度表id	private int id;
	//组织单位site_code	private String siteCode;
	//合同表Id	private String contractInfoId;
	//批次	private int batchNum;
	//组次	private int groupNum;
	//任务名称	private String taskName;
	//监测开始时间（yyyy年mm月dd日）	private String startDate;
	//监测结束时间(yyyy年mm月dd日)	private String endDate;
	//状态（0：未启动，1：检查中，2：检查完成）	private int status;
	//抽查网站数量	private int spotWebsiteNum;
	//抽查站点级别（1：省，2：市，3：县）	private int spotLevel;
	//是否门户（0：否，1：是 2:门户/非门户）	private int isorganizational;
	//创建时间	private Date createTime;
	//修改时间	private Date modifyTime;
	
	//上报上级组织单位
	private String reportOrgCode;
	//type  区分有无抽查进度
	private Integer type;
//	附件url
	private String affixUrl;
//	附件名
	private String affixName;
	//抽查任务附件表id
	private Integer  upId;
		/** set 抽查进度表id */	public void setId(int id){
		this.id=id;
	}
	/** get 抽查进度表id */	public int getId(){
		return id;
	}
	/** set 组织单位site_code */	public void setSiteCode(String siteCode){
		this.siteCode=siteCode;
	}
	/** get 组织单位site_code */	public String getSiteCode(){
		return siteCode;
	}
	/** set 合同表Id */	public void setContractInfoId(String contractInfoId){
		this.contractInfoId=contractInfoId;
	}
	/** get 合同表Id */	public String getContractInfoId(){
		return contractInfoId;
	}
	/** set 批次 */	public void setBatchNum(int batchNum){
		this.batchNum=batchNum;
	}
	/** get 批次 */	public int getBatchNum(){
		return batchNum;
	}
	/** set 组次 */	public void setGroupNum(int groupNum){
		this.groupNum=groupNum;
	}
	/** get 组次 */	public int getGroupNum(){
		return groupNum;
	}
	/** set 任务名称 */	public void setTaskName(String taskName){
		this.taskName=taskName;
	}
	/** get 任务名称 */	public String getTaskName(){
		return taskName;
	}
	/** set 监测开始时间（yyyy年mm月dd日） */	public void setStartDate(String startDate){
		this.startDate=startDate;
	}
	/** get 监测开始时间（yyyy年mm月dd日） */	public String getStartDate(){
		return startDate;
	}
	/** set 监测结束时间(yyyy年mm月dd日) */	public void setEndDate(String endDate){
		this.endDate=endDate;
	}
	/** get 监测结束时间(yyyy年mm月dd日) */	public String getEndDate(){
		return endDate;
	}
	/** set 状态（0：未启动，1：检查中，2：检查完成） */	public void setStatus(int status){
		this.status=status;
	}
	/** get 状态（0：未启动，1：检查中，2：检查完成） */	public int getStatus(){
		return status;
	}
	/** set 抽查网站数量 */	public void setSpotWebsiteNum(int spotWebsiteNum){
		this.spotWebsiteNum=spotWebsiteNum;
	}
	/** get 抽查网站数量 */	public int getSpotWebsiteNum(){
		return spotWebsiteNum;
	}
	/** set 抽查站点级别（1：省，2：市，3：县） */	public void setSpotLevel(int spotLevel){
		this.spotLevel=spotLevel;
	}
	/** get 抽查站点级别（1：省，2：市，3：县） */	public int getSpotLevel(){
		return spotLevel;
	}
	/** set 是否门户（0：否，1：是 2:门户/非门户） */	public void setIsorganizational(int isorganizational){
		this.isorganizational=isorganizational;
	}
	/** get 是否门户（0：否，1：是 2:门户/非门户） */	public int getIsorganizational(){
		return isorganizational;
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

	public String getReportOrgCode() {
		return reportOrgCode;
	}

	public void setReportOrgCode(String reportOrgCode) {
		this.reportOrgCode = reportOrgCode;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getAffixUrl() {
		return affixUrl;
	}

	public void setAffixUrl(String affixUrl) {
		this.affixUrl = affixUrl;
	}

	public String getAffixName() {
		return affixName;
	}

	public void setAffixName(String affixName) {
		this.affixName = affixName;
	}

	public Integer getUpId() {
		return upId;
	}

	public void setUpId(Integer upId) {
		this.upId = upId;
	}

}

