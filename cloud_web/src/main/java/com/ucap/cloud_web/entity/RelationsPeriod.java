package com.ucap.cloud_web.entity;




/*** <br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2016-03-09 10:08:29 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public class RelationsPeriod {


	//	private int Id;
	//网站标识码	private String siteCode;
	//周期表ID	private int servicePeriodId;
	//周期开始时间	private String startDate;
	//周期结束时间	private String endDate;
	//套餐类型	private int comboId;
	//站点任务号
	private String websiteTaskNum;
	//用于判断，表中不存在
	private int flagResult;
	//url,抽查报告路径,表中不存在
	private String url;
	
	//全站死链是否扫描(1:是，2否)
	private Integer isScan;	/** set  */	public void setId(int Id){
		this.Id=Id;
	}
	/** get  */	public int getId(){
		return Id;
	}
	/** set 网站标识码 */	public void setSiteCode(String siteCode){
		this.siteCode=siteCode;
	}
	/** get 网站标识码 */	public String getSiteCode(){
		return siteCode;
	}
	/** set 周期表ID */	public void setServicePeriodId(int servicePeriodId){
		this.servicePeriodId=servicePeriodId;
	}
	/** get 周期表ID */	public int getServicePeriodId(){
		return servicePeriodId;
	}
	/** set 周期开始时间 */	public void setStartDate(String startDate){
		this.startDate=startDate;
	}
	/** get 周期开始时间 */	public String getStartDate(){
		return startDate;
	}
	/** set 周期结束时间 */	public void setEndDate(String endDate){
		this.endDate=endDate;
	}
	/** get 周期结束时间 */	public String getEndDate(){
		return endDate;
	}
	/** set 套餐类型 */	public void setComboId(int comboId){
		this.comboId=comboId;
	}
	/** get 套餐类型 */	public int getComboId(){
		return comboId;
	}

	public String getWebsiteTaskNum() {
		return websiteTaskNum;
	}

	public void setWebsiteTaskNum(String websiteTaskNum) {
		this.websiteTaskNum = websiteTaskNum;
	}

	public int getFlagResult() {
		return flagResult;
	}

	public void setFlagResult(int flagResult) {
		this.flagResult = flagResult;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getIsScan() {
		return isScan;
	}

	public void setIsScan(Integer isScan) {
		this.isScan = isScan;
	}
}

