package com.ucap.cloud_web.entity;


import java.util.Date;


/*** <br>* <b>作者：</b>qingjy<br>* <b>日期：</b> 2016-11-07 16:41:57 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public class MonitorTaskSilent {


	//	private int id;
	//基本信息id	private int databaseInfoId;
	//网站标识码	private String siteCode;
	//解决首页url改变无法找到对应站点	private String encodeUrl;
	//合同Id	private int contractId;
	//类型（0:全部 1.网站脆弱性 2.网站挂马，3.变更/篡改 4.网站暗链 5.内容泄漏）	private int type;
	//任务开始时间	private Date startDate;
	//任务结束时间	private Date endDate;
	//状态（1：启动，2：停止，3：启动失败,4：启动成功,5：关闭,6：关闭失败）	private int isStart;
	//任务启动时间	private Date startTime;
	//扫描频率（单位：分钟）	private int rateTime;
	//创建时间	private Date createTime;
	//修改时间	private Date modifyTime;

	//开始日期  新添加的
	private String startD;

	//结束日期  新添加的
	private String endD;
	
		public String getStartD() {
		return startD;
	}

	public void setStartD(String startD) {
		this.startD = startD;
	}

	public String getEndD() {
		return endD;
	}

	public void setEndD(String endD) {
		this.endD = endD;
	}

	/** set  */	public void setId(int id){
		this.id=id;
	}
	/** get  */	public int getId(){
		return id;
	}
	/** set 基本信息id */	public void setDatabaseInfoId(int databaseInfoId){
		this.databaseInfoId=databaseInfoId;
	}
	/** get 基本信息id */	public int getDatabaseInfoId(){
		return databaseInfoId;
	}
	/** set 网站标识码 */	public void setSiteCode(String siteCode){
		this.siteCode=siteCode;
	}
	/** get 网站标识码 */	public String getSiteCode(){
		return siteCode;
	}
	/** set 解决首页url改变无法找到对应站点 */	public void setEncodeUrl(String encodeUrl){
		this.encodeUrl=encodeUrl;
	}
	/** get 解决首页url改变无法找到对应站点 */	public String getEncodeUrl(){
		return encodeUrl;
	}
	/** set 合同Id */	public void setContractId(int contractId){
		this.contractId=contractId;
	}
	/** get 合同Id */	public int getContractId(){
		return contractId;
	}
	/** set 类型（0:全部 1.网站脆弱性 2.网站挂马，3.变更/篡改 4.网站暗链 5.内容泄漏） */	public void setType(int type){
		this.type=type;
	}
	/** get 类型（0:全部 1.网站脆弱性 2.网站挂马，3.变更/篡改 4.网站暗链 5.内容泄漏） */	public int getType(){
		return type;
	}
	/** set 任务开始时间 */	public void setStartDate(Date startDate){
		this.startDate=startDate;
	}
	/** get 任务开始时间 */	public Date getStartDate(){
		return startDate;
	}
	/** set 任务结束时间 */	public void setEndDate(Date endDate){
		this.endDate=endDate;
	}
	/** get 任务结束时间 */	public Date getEndDate(){
		return endDate;
	}
	/** set 状态（1：启动，2：停止，3：启动失败,4：启动成功,5：关闭,6：关闭失败） */	public void setIsStart(int isStart){
		this.isStart=isStart;
	}
	/** get 状态（1：启动，2：停止，3：启动失败,4：启动成功,5：关闭,6：关闭失败） */	public int getIsStart(){
		return isStart;
	}
	/** set 任务启动时间 */	public void setStartTime(Date startTime){
		this.startTime=startTime;
	}
	/** get 任务启动时间 */	public Date getStartTime(){
		return startTime;
	}
	/** set 扫描频率（单位：分钟） */	public void setRateTime(int rateTime){
		this.rateTime=rateTime;
	}
	/** get 扫描频率（单位：分钟） */	public int getRateTime(){
		return rateTime;
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

