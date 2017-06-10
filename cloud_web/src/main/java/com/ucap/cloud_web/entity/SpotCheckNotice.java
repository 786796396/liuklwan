package com.ucap.cloud_web.entity;


import java.util.Date;


/*** <br>* <b>作者：</b>yangshuai<br>* <b>日期：</b> 2016-06-01 18:43:28 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public class SpotCheckNotice {


	//整改通知id	private int id;
	
	//组织单位siteCode(发送通知)
	private String orgSiteCode;
	//主办单位	private String director;
	//网站信息id	private int databaseInfoId;
	//网站标识码	private String siteCode;
	//服务周期id	private int servicePeriodId;
	//通知时间	private Date requireTime;
	//整改期限时间	private Date endTime;
	//整改要求	private String noticeRequirement;
	//整改反馈	private String noticeResponse;
	//整改问题数	private int questionNum;
	//整改报告名称	private String noticeReportName;
	//整改报告url	private String noticeReportUrl;
	//创建时间	private Date createTime;
	//修改时间	private Date modifyTime;
	
	//联系人邮箱
	private String email2;
	
	//负责人邮箱
	private String email;
	
	private Integer type;//0抽查 1全面监测
	private Integer isRead;//0:未读未反馈  1：已读未反馈
	//报告状态
	private int checkReportResult;
	
	//是否允许上级查看（用于省对县这种跨级通知反馈）
	private int isAllowUpper;
	
	//上级组织单位编码
	private String upperOrgCode;
	
	//联系人邮箱
	private String email2Upper;
		
	//负责人邮箱
	private String emailUpper;
	
	
	public String getEmail2Upper() {
		return email2Upper;
	}

	public void setEmail2Upper(String email2Upper) {
		this.email2Upper = email2Upper;
	}

	public String getEmailUpper() {
		return emailUpper;
	}

	public void setEmailUpper(String emailUpper) {
		this.emailUpper = emailUpper;
	}

	public String getUpperOrgCode() {
		return upperOrgCode;
	}

	public void setUpperOrgCode(String upperOrgCode) {
		this.upperOrgCode = upperOrgCode;
	}

	public String getOrgSiteCode() {
		return orgSiteCode;
	}

	public void setOrgSiteCode(String orgSiteCode) {
		this.orgSiteCode = orgSiteCode;
	}


	public int getIsAllowUpper() {
		return isAllowUpper;
	}

	public void setIsAllowUpper(int isAllowUpper) {
		this.isAllowUpper = isAllowUpper;
	}

	public int getCheckReportResult() {
		return checkReportResult;
	}

	public void setCheckReportResult(int checkReportResult) {
		this.checkReportResult = checkReportResult;
	}
	/** set 整改通知id */	public void setId(int id){
		this.id=id;
	}
	/** get 整改通知id */	public int getId(){
		return id;
	}
	/** set 主办单位 */	public void setDirector(String director){
		this.director=director;
	}
	/** get 主办单位 */	public String getDirector(){
		return director;
	}
	/** set 网站信息id */	public void setDatabaseInfoId(int databaseInfoId){
		this.databaseInfoId=databaseInfoId;
	}
	/** get 网站信息id */	public int getDatabaseInfoId(){
		return databaseInfoId;
	}
	/** set 网站标识码 */	public void setSiteCode(String siteCode){
		this.siteCode=siteCode;
	}
	/** get 网站标识码 */	public String getSiteCode(){
		return siteCode;
	}
	/** set 服务周期id */	public void setServicePeriodId(int servicePeriodId){
		this.servicePeriodId=servicePeriodId;
	}
	/** get 服务周期id */	public int getServicePeriodId(){
		return servicePeriodId;
	}
	/** set 通知时间 */	public void setRequireTime(Date requireTime){
		this.requireTime=requireTime;
	}
	/** get 通知时间 */	public Date getRequireTime(){
		return requireTime;
	}
	/** set 整改期限时间 */	public void setEndTime(Date endTime){
		this.endTime=endTime;
	}
	/** get 整改期限时间 */	public Date getEndTime(){
		return endTime;
	}
	/** set 整改要求 */	public void setNoticeRequirement(String noticeRequirement){
		this.noticeRequirement=noticeRequirement;
	}
	/** get 整改要求 */	public String getNoticeRequirement(){
		return noticeRequirement;
	}
	/** set 整改反馈 */	public void setNoticeResponse(String noticeResponse){
		this.noticeResponse=noticeResponse;
	}
	/** get 整改反馈 */	public String getNoticeResponse(){
		return noticeResponse;
	}
	/** set 整改问题数 */	public void setQuestionNum(int questionNum){
		this.questionNum=questionNum;
	}
	/** get 整改问题数 */	public int getQuestionNum(){
		return questionNum;
	}
	/** set 整改报告名称 */	public void setNoticeReportName(String noticeReportName){
		this.noticeReportName=noticeReportName;
	}
	/** get 整改报告名称 */	public String getNoticeReportName(){
		return noticeReportName;
	}
	/** set 整改报告url */	public void setNoticeReportUrl(String noticeReportUrl){
		this.noticeReportUrl=noticeReportUrl;
	}
	/** get 整改报告url */	public String getNoticeReportUrl(){
		return noticeReportUrl;
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

	public String getEmail2() {
		return email2;
	}

	public void setEmail2(String email2) {
		this.email2 = email2;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getIsRead() {
		return isRead;
	}

	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}
}

