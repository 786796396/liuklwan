package com.ucap.cloud_web.entity;


import java.util.Date;


/*** <br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2015-10-29 11:23:36 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/public class ReportWordResult {


	//	private int id;
	
	//期号Id	private int servicePeriodId;
	//网站标识码	private String siteCode;
	//报告类型（0：抽查，1：正常合同）	private int type;
	//word报告存储地址	private String wordUrl;
	
	//pdf存储地址
	private String pdfUrl;
	
	//excel存储地址
	private String excelUrl;
	
	//文件别名
	private String aliasName;
	//创建时间	private Date createTime;
	
	/** 下载数据报告start **/
	//周期开始时间
	private Date startDate;
	
	//周期结束时间
	private Date endDate;
	
	//报告状态
	private Integer checkReportResult;
	
	
	//通知整改表id
	private Integer scnId;
	//检测形式（0:抽查 1:全面检测）
	private Integer scnType;
	//主办单位
	private String director;
	//已读状态 0:未读未反馈 1：已读未反馈
	private Integer isRead;
	
	
	
	public Integer getScnId() {
		return scnId;
	}

	public void setScnId(Integer scnId) {
		this.scnId = scnId;
	}

	public Integer getScnType() {
		return scnType;
	}

	public void setScnType(Integer scnType) {
		this.scnType = scnType;
	}

	public Integer getIsRead() {
		return isRead;
	}

	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	/** 下载数据报告end **/
		public String getAliasName() {
		return aliasName;
	}

	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}

	/** set  */	public void setId(int id){
		this.id=id;
	}
	/** get  */	public int getId(){
		return id;
	}
	/** set 期号Id */	public void setServicePeriodId(int servicePeriodId){
		this.servicePeriodId=servicePeriodId;
	}
	/** get 期号Id */	public int getServicePeriodId(){
		return servicePeriodId;
	}
	/** set 网站标识码 */	public void setSiteCode(String siteCode){
		this.siteCode=siteCode;
	}
	/** get 网站标识码 */	public String getSiteCode(){
		return siteCode;
	}
	/** set 报告类型（0：抽查，1：正常合同） */	public void setType(int type){
		this.type=type;
	}
	/** get 报告类型（0：抽查，1：正常合同） */	public int getType(){
		return type;
	}
	/** set 创建时间 */	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}
	/** get 创建时间 */	public Date getCreateTime(){
		return createTime;
	}

	public String getWordUrl() {
		return wordUrl;
	}

	public void setWordUrl(String wordUrl) {
		this.wordUrl = wordUrl;
	}

	public String getPdfUrl() {
		return pdfUrl;
	}

	public void setPdfUrl(String pdfUrl) {
		this.pdfUrl = pdfUrl;
	}

	public String getExcelUrl() {
		return excelUrl;
	}

	public void setExcelUrl(String excelUrl) {
		this.excelUrl = excelUrl;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getCheckReportResult() {
		return checkReportResult;
	}

	public void setCheckReportResult(Integer checkReportResult) {
		this.checkReportResult = checkReportResult;
	}
}

