package com.ucap.cloud_web.entity;


import java.util.Date;


/**


	//合同信息表id
	
    private String siteCode;
	

	
	//客户名称
	private String customerName;


	
	//抽查合同号(C+yyyyMMdd_抽查进度表id_批次_组次)
	private String contractCodeSpot;









	
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

	/** set 合同信息表id */
		this.id=id;
	}

		return id;
	}

		this.customerInfoId=customerInfoId;
	}

		return customerInfoId;
	}

		this.contractCode=contractCode;
	}

		return contractCode;
	}

		this.contractCodeTemp=contractCodeTemp;
	}

		return contractCodeTemp;
	}

		this.contractName=contractName;
	}

		return contractName;
	}

		this.checkCount=checkCount;
	}

		return checkCount;
	}

		this.completeCount=completeCount;
	}

		return completeCount;
	}


		this.contractBeginTime=contractBeginTime;
	}

		return contractBeginTime;
	}

		this.contractEndTime=contractEndTime;
	}

		return contractEndTime;
	}

		this.sellName=sellName;
	}

		return sellName;
	}

		this.createTime=createTime;
	}

		return createTime;
	}

		this.modifyTime=modifyTime;
	}

		return modifyTime;
	}

	public int getIsCorrect() {
		return isCorrect;
	}

	public void setIsCorrect(int isCorrect) {
		this.isCorrect = isCorrect;
	}

