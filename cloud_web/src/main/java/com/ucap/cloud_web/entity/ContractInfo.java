package com.ucap.cloud_web.entity;


import java.util.Date;


/*** <br>* <b>作者：</b>ccx<br>* <b>日期：</b> 2016-03-04 20:20:38 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public class ContractInfo {


	//合同信息表id	private int id;
	
    private String siteCode;
	
	//客户信息表id	private int customerInfoId;
	
	//客户名称
	private String customerName;
	//合同号编码	private String contractCode;
	//临时合同号	private String contractCodeTemp;
	
	//抽查合同号(C+yyyyMMdd_抽查进度表id_批次_组次)
	private String contractCodeSpot;
	//合同名称	private String contractName;
	//检查总次数	private int checkCount;
	//已检查次数	private int completeCount;

	//合同生效时间	private Date contractBeginTime;
	//合同结束时间	private Date contractEndTime;
	//销售姓名	private String sellName;
	//创建时间	private Date createTime;
	//修改时间	private Date modifyTime;
	
	private int websiteCount;//站点总数
	
	private int advancePeroidNum;//高级版期数
	
	private int commonPeriodNum;//普通版期数
	
	private int spotCheckCount;//抽查站次
	
	private int executeStatus;//执行状态  -1，初始化：0，服务中：1，服务结束：2

	private int isCorrect;//是否校对错别字（0：否，1：是）
	
	private int isSearchTb;//填报单位是否能够查看详细信息（1-可以查看  2-不能查看）
	
	
	public int getIsSearchTb() {
		return isSearchTb;
	}

	public void setIsSearchTb(int isSearchTb) {
		this.isSearchTb = isSearchTb;
	}

	public int getExecuteStatus() {
		return executeStatus;
	}

	public void setExecuteStatus(int executeStatus) {
		this.executeStatus = executeStatus;
	}

	public String getContractCodeSpot() {
		return contractCodeSpot;
	}

	public void setContractCodeSpot(String contractCodeSpot) {
		this.contractCodeSpot = contractCodeSpot;
	}

	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	public int getWebsiteCount() {
		return websiteCount;
	}

	public void setWebsiteCount(int websiteCount) {
		this.websiteCount = websiteCount;
	}

	public int getAdvancePeroidNum() {
		return advancePeroidNum;
	}

	public void setAdvancePeroidNum(int advancePeroidNum) {
		this.advancePeroidNum = advancePeroidNum;
	}

	public int getCommonPeriodNum() {
		return commonPeriodNum;
	}

	public void setCommonPeriodNum(int commonPeriodNum) {
		this.commonPeriodNum = commonPeriodNum;
	}

	public int getSpotCheckCount() {
		return spotCheckCount;
	}

	public void setSpotCheckCount(int spotCheckCount) {
		this.spotCheckCount = spotCheckCount;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/** set 合同信息表id */	public void setId(int id){
		this.id=id;
	}
	/** get 合同信息表id */	public int getId(){
		return id;
	}
	/** set 客户信息表id */	public void setCustomerInfoId(int customerInfoId){
		this.customerInfoId=customerInfoId;
	}
	/** get 客户信息表id */	public int getCustomerInfoId(){
		return customerInfoId;
	}
	/** set 合同号编码 */	public void setContractCode(String contractCode){
		this.contractCode=contractCode;
	}
	/** get 合同号编码 */	public String getContractCode(){
		return contractCode;
	}
	/** set 临时合同号 */	public void setContractCodeTemp(String contractCodeTemp){
		this.contractCodeTemp=contractCodeTemp;
	}
	/** get 临时合同号 */	public String getContractCodeTemp(){
		return contractCodeTemp;
	}
	/** set 合同名称 */	public void setContractName(String contractName){
		this.contractName=contractName;
	}
	/** get 合同名称 */	public String getContractName(){
		return contractName;
	}
	/** set 检查总次数 */	public void setCheckCount(int checkCount){
		this.checkCount=checkCount;
	}
	/** get 检查总次数 */	public int getCheckCount(){
		return checkCount;
	}
	/** set 已检查次数 */	public void setCompleteCount(int completeCount){
		this.completeCount=completeCount;
	}
	/** get 已检查次数 */	public int getCompleteCount(){
		return completeCount;
	}

	/** set 合同生效时间 */	public void setContractBeginTime(Date contractBeginTime){
		this.contractBeginTime=contractBeginTime;
	}
	/** get 合同生效时间 */	public Date getContractBeginTime(){
		return contractBeginTime;
	}
	/** set 合同结束时间 */	public void setContractEndTime(Date contractEndTime){
		this.contractEndTime=contractEndTime;
	}
	/** get 合同结束时间 */	public Date getContractEndTime(){
		return contractEndTime;
	}
	/** set 销售姓名 */	public void setSellName(String sellName){
		this.sellName=sellName;
	}
	/** get 销售姓名 */	public String getSellName(){
		return sellName;
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

	public int getIsCorrect() {
		return isCorrect;
	}

	public void setIsCorrect(int isCorrect) {
		this.isCorrect = isCorrect;
	}
}

