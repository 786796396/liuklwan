package com.ucap.cloud_web.entity;


import java.util.Date;


/*** <br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-11-04 11:24:50 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public class MonitorSilentOrgResult {


	//	private int id;
	//网站标识码	private String siteCode;
	//风险值	private int riskNum;
	//问题总数	private int silentSum;
	//问题网站总数	private int siteSum;
	//脆弱性问题数	private int fragilitySum;
	//挂马问题数	private int trojanSum;
	//变更/篡改问题数	private int tamperSum;
	//网站暗链数	private int darkChainSum;
	//内容泄漏数	private int leakedSum;
	//脆弱性问题网站数	private int fragilitySiteSum;
	//挂马问题网站数	private int trojanSiteSum;
	//变更/篡改问题网站数	private int tamperSiteSum;
	//网站暗链网站数	private int darkChainSiteSum;
	//内容泄漏网站数	private int leakedSiteSum;
	//获取时间	private String scanDate;
	//创建时间	private Date createTime;
	//修改时间	private Date modifyTime;
	
	//类型（0,全部【除了关停与另外】，2本级部门，3下属单位，6其他）
	private int type;
	/** set  */	public void setId(int id){
		this.id=id;
	}
	/** get  */	public int getId(){
		return id;
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
	/** set 问题总数 */	public void setSilentSum(int silentSum){
		this.silentSum=silentSum;
	}
	/** get 问题总数 */	public int getSilentSum(){
		return silentSum;
	}
	/** set 问题网站总数 */	public void setSiteSum(int siteSum){
		this.siteSum=siteSum;
	}
	/** get 问题网站总数 */	public int getSiteSum(){
		return siteSum;
	}
	/** set 脆弱性问题数 */	public void setFragilitySum(int fragilitySum){
		this.fragilitySum=fragilitySum;
	}
	/** get 脆弱性问题数 */	public int getFragilitySum(){
		return fragilitySum;
	}
	/** set 挂马问题数 */	public void setTrojanSum(int trojanSum){
		this.trojanSum=trojanSum;
	}
	/** get 挂马问题数 */	public int getTrojanSum(){
		return trojanSum;
	}
	/** set 变更/篡改问题数 */	public void setTamperSum(int tamperSum){
		this.tamperSum=tamperSum;
	}
	/** get 变更/篡改问题数 */	public int getTamperSum(){
		return tamperSum;
	}
	/** set 网站暗链数 */	public void setDarkChainSum(int darkChainSum){
		this.darkChainSum=darkChainSum;
	}
	/** get 网站暗链数 */	public int getDarkChainSum(){
		return darkChainSum;
	}
	/** set 内容泄漏数 */	public void setLeakedSum(int leakedSum){
		this.leakedSum=leakedSum;
	}
	/** get 内容泄漏数 */	public int getLeakedSum(){
		return leakedSum;
	}
	/** set 脆弱性问题网站数 */	public void setFragilitySiteSum(int fragilitySiteSum){
		this.fragilitySiteSum=fragilitySiteSum;
	}
	/** get 脆弱性问题网站数 */	public int getFragilitySiteSum(){
		return fragilitySiteSum;
	}
	/** set 挂马问题网站数 */	public void setTrojanSiteSum(int trojanSiteSum){
		this.trojanSiteSum=trojanSiteSum;
	}
	/** get 挂马问题网站数 */	public int getTrojanSiteSum(){
		return trojanSiteSum;
	}
	/** set 变更/篡改问题网站数 */	public void setTamperSiteSum(int tamperSiteSum){
		this.tamperSiteSum=tamperSiteSum;
	}
	/** get 变更/篡改问题网站数 */	public int getTamperSiteSum(){
		return tamperSiteSum;
	}
	/** set 网站暗链网站数 */	public void setDarkChainSiteSum(int darkChainSiteSum){
		this.darkChainSiteSum=darkChainSiteSum;
	}
	/** get 网站暗链网站数 */	public int getDarkChainSiteSum(){
		return darkChainSiteSum;
	}
	/** set 内容泄漏网站数 */	public void setLeakedSiteSum(int leakedSiteSum){
		this.leakedSiteSum=leakedSiteSum;
	}
	/** get 内容泄漏网站数 */	public int getLeakedSiteSum(){
		return leakedSiteSum;
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}

