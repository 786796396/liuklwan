package com.ucap.cloud_web.entity;


import java.util.Date;


/*** <br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-09-13 11:30:35 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public class TempReport {


	//	private Integer id;
	//	private String siteCode;

	private String orgSiteCode;	//	private String reportName;
	//	private int reportReason;
	//	private String reportDetail;
	//	private String reportStartDate;
	//	private String reportEndDate;
	//	private String startTime;
	//	private String endTime;
	//周期 1 ； 持续  2；	private int isCycle;
	//1 待审核，2 通过，3 驳回	private int status;
	//	private String createTime;
	//	private String modifyTime;
	
	//报备日期；
	private String reportDate;
	
	//网站名称
	private String webName;

	public String getOrgSiteCode() {
		return orgSiteCode;
	}

	public void setOrgSiteCode(String orgSiteCode) {
		this.orgSiteCode = orgSiteCode;
	}

	public String getReportDate() {
		return reportDate;
	}

	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}

	public String getWebName() {
		return webName;
	}

	public void setWebName(String webName) {
		this.webName = webName;
	}

	public Integer getId() {
		return id;
	}
	/** set  */
	public void setId(Integer id) {
		this.id = id;
	}
	/** set  */	public void setSiteCode(String siteCode){
		this.siteCode=siteCode;
	}
	/** get  */	public String getSiteCode(){
		return siteCode;
	}
	/** set  */	public void setReportName(String reportName){
		this.reportName=reportName;
	}
	/** get  */	public String getReportName(){
		return reportName;
	}
	/** set  */	public void setReportReason(int reportReason){
		this.reportReason=reportReason;
	}
	/** get  */	public int getReportReason(){
		return reportReason;
	}
	/** set  */	public void setReportDetail(String reportDetail){
		this.reportDetail=reportDetail;
	}
	/** get  */	public String getReportDetail(){
		return reportDetail;
	}
	/** set  */	public void setReportStartDate(String reportStartDate){
		this.reportStartDate=reportStartDate;
	}
	/** get  */	public String getReportStartDate(){
		return reportStartDate;
	}
	/** set  */	public void setReportEndDate(String reportEndDate){
		this.reportEndDate=reportEndDate;
	}
	/** get  */	public String getReportEndDate(){
		return reportEndDate;
	}
	/** set  */	public void setStartTime(String startTime){
		this.startTime=startTime;
	}
	/** get  */	public String getStartTime(){
		return startTime;
	}
	/** set  */	public void setEndTime(String endTime){
		this.endTime=endTime;
	}
	/** get  */	public String getEndTime(){
		return endTime;
	}
	/** set 周期 1 ； 持续  2； */	public void setIsCycle(int isCycle){
		this.isCycle=isCycle;
	}
	/** get 周期 1 ； 持续  2； */	public int getIsCycle(){
		return isCycle;
	}
	/** set 1 待审核，2 通过，3 驳回 */	public void setStatus(int status){
		this.status=status;
	}
	/** get 1 待审核，2 通过，3 驳回 */	public int getStatus(){
		return status;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}
}

