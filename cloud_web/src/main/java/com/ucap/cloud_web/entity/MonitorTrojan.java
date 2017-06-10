package com.ucap.cloud_web.entity;


import java.util.Date;


/*** <br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-11-04 11:24:51 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public class MonitorTrojan {


	//	private int id;
	//基本信息id	private int databaseInfoId;
	//任务id	private int monitorTaskId;
	//网站标识码	private String siteCode;
	//风险值	private int riskNum;
	//网站url	private String url;
	//md5加密url(先jump_url后url)	private String encodeUrl;
	//问题url	private String purl;
	//级别（1：低级2：高级）	private int level;
	//具体类型（1.配置风险，2.信息泄露）	private int loopholeType;
	//方法（Get或者Post）	private String method;
	//参数	private String param;
	//扫描时间	private String stime;
	//获取时间	private String scanDate;
	//用户id，默认0机器	private int userId;
	//是否删除（0：否，1：删除）	private int delFlag;
	//创建时间	private Date createTime;
	//修改时间	private Date modifyTime;
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
	/** set 任务id */	public void setMonitorTaskId(int monitorTaskId){
		this.monitorTaskId=monitorTaskId;
	}
	/** get 任务id */	public int getMonitorTaskId(){
		return monitorTaskId;
	}
	/** set 网站标识码 */	public void setSiteCode(String siteCode){
		this.siteCode=siteCode;
	}
	/** get 网站标识码 */	public String getSiteCode(){
		return siteCode;
	}
	/** set 风险值 */	public void setRiskNum(int riskNum){
		this.riskNum=riskNum;
	}
	/** get 风险值 */	public int getRiskNum(){
		return riskNum;
	}
	/** set 网站url */	public void setUrl(String url){
		this.url=url;
	}
	/** get 网站url */	public String getUrl(){
		return url;
	}
	/** set md5加密url(先jump_url后url) */	public void setEncodeUrl(String encodeUrl){
		this.encodeUrl=encodeUrl;
	}
	/** get md5加密url(先jump_url后url) */	public String getEncodeUrl(){
		return encodeUrl;
	}
	/** set 问题url */	public void setPurl(String purl){
		this.purl=purl;
	}
	/** get 问题url */	public String getPurl(){
		return purl;
	}
	/** set 级别（1：低级2：高级） */	public void setLevel(int level){
		this.level=level;
	}
	/** get 级别（1：低级2：高级） */	public int getLevel(){
		return level;
	}
	/** set 具体类型（1.配置风险，2.信息泄露） */	public void setLoopholeType(int loopholeType){
		this.loopholeType=loopholeType;
	}
	/** get 具体类型（1.配置风险，2.信息泄露） */	public int getLoopholeType(){
		return loopholeType;
	}
	/** set 方法（Get或者Post） */	public void setMethod(String method){
		this.method=method;
	}
	/** get 方法（Get或者Post） */	public String getMethod(){
		return method;
	}
	/** set 参数 */	public void setParam(String param){
		this.param=param;
	}
	/** get 参数 */	public String getParam(){
		return param;
	}

	public String getStime() {
		return stime;
	}

	public void setStime(String stime) {
		this.stime = stime;
	}

	/** set 获取时间 */	public void setScanDate(String scanDate){
		this.scanDate=scanDate;
	}
	/** get 获取时间 */	public String getScanDate(){
		return scanDate;
	}
	/** set 用户id，默认0机器 */	public void setUserId(int userId){
		this.userId=userId;
	}
	/** get 用户id，默认0机器 */	public int getUserId(){
		return userId;
	}
	/** set 是否删除（0：否，1：删除） */	public void setDelFlag(int delFlag){
		this.delFlag=delFlag;
	}
	/** get 是否删除（0：否，1：删除） */	public int getDelFlag(){
		return delFlag;
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

