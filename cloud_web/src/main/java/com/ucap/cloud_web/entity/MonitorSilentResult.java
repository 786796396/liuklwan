package com.ucap.cloud_web.entity;


import java.util.Date;


/*** <br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-11-04 11:24:50 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public class MonitorSilentResult {


	//	private int id;
	//基本信息id	private int databaseInfoId;
	//网站标识码	private String siteCode;
	//风险值	private int riskNum;
	//问题总数	private int silentNum;
	//脆弱性问题数	private int fragilityNum;
	//挂马问题数	private int trojanNum;
	//变更/篡改问题数	private int tamperNum;
	//网站暗链数	private int darkChainNum;
	//内容泄漏数	private int leakedNum;
	//获取时间	private String scanDate;
	//创建时间	private Date createTime;
	//修改时间	private Date modifyTime;
	
	//类型（默认0昨天，1一周，2两周，3一个月）
	private int resType;
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
	/** set 风险值 */	public void setRiskNum(int riskNum){
		this.riskNum=riskNum;
	}
	/** get 风险值 */	public int getRiskNum(){
		return riskNum;
	}
	/** set 问题总数 */	public void setSilentNum(int silentNum){
		this.silentNum=silentNum;
	}
	/** get 问题总数 */	public int getSilentNum(){
		return silentNum;
	}
	/** set 脆弱性问题数 */	public void setFragilityNum(int fragilityNum){
		this.fragilityNum=fragilityNum;
	}
	/** get 脆弱性问题数 */	public int getFragilityNum(){
		return fragilityNum;
	}
	/** set 挂马问题数 */	public void setTrojanNum(int trojanNum){
		this.trojanNum=trojanNum;
	}
	/** get 挂马问题数 */	public int getTrojanNum(){
		return trojanNum;
	}
	/** set 变更/篡改问题数 */	public void setTamperNum(int tamperNum){
		this.tamperNum=tamperNum;
	}
	/** get 变更/篡改问题数 */	public int getTamperNum(){
		return tamperNum;
	}
	/** set 网站暗链数 */	public void setDarkChainNum(int darkChainNum){
		this.darkChainNum=darkChainNum;
	}
	/** get 网站暗链数 */	public int getDarkChainNum(){
		return darkChainNum;
	}
	/** set 内容泄漏数 */	public void setLeakedNum(int leakedNum){
		this.leakedNum=leakedNum;
	}
	/** get 内容泄漏数 */	public int getLeakedNum(){
		return leakedNum;
	}
	/** set 获取时间 */	public void setScanDate(String scanDate){
		this.scanDate=scanDate;
	}
	/** get 获取时间 */	public String getScanDate(){
		return scanDate;
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

	public int getResType() {
		return resType;
	}

	public void setResType(int resType) {
		this.resType = resType;
	}
}

