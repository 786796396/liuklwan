package com.ucap.cloud_web.entity;


import java.util.Date;


/**


	//预警详情表id


	//网站标识码name
	private String siteCodeName;
	






	//组织单位发送预警类型（1：本组织，2：下级填报单位），0：填报单位本身
	private int orgTbStatus;
	



	
	//最后一次连不通扫描时间
	private String lastScanTime;
	
	private String principalEmail;//组织单位   负责人邮箱地址
	private String linkmanEmail;//组织单位        联系人邮箱地址
	private String linkmanEmailTwo;//组织单位        联系人邮箱地址
	private String linkmanEmailThree;//组织单位        联系人邮箱地址
	
/*	private Integer isWxReceive;//是否发送微信 1发送 2不发送
	private Integer isEmailReceive;//是否发送邮件  1发送 2不发送
	private Integer isTelReceive;//是否发送短信 1发送 2不发送
*/	
	private String principalPhone;//组织单位   负责人电话
	private String linkmanPhone;//组织单位        联系人电话
	private String linkmanPhoneTwo;//组织单位        联系人电话
	private String linkmanPhoneThree;//组织单位        联系人电话
	
	private String jumpUrl;//跳转url
	private String homeUrl;//首页url
	
	private Integer contractInfoId;//合同id
	

		return lastScanTime;
	}

	public void setLastScanTime(String lastScanTime) {
		this.lastScanTime = lastScanTime;
	}

	public Integer getContractInfoId() {
		return contractInfoId;
	}

	public void setContractInfoId(Integer contractInfoId) {
		this.contractInfoId = contractInfoId;
	}

	public String getJumpUrl() {
		return jumpUrl;
	}

	public void setJumpUrl(String jumpUrl) {
		this.jumpUrl = jumpUrl;
	}

	public String getHomeUrl() {
		return homeUrl;
	}

	public void setHomeUrl(String homeUrl) {
		this.homeUrl = homeUrl;
	}

	public String getPrincipalEmail() {
		return principalEmail;
	}

	public void setPrincipalEmail(String principalEmail) {
		this.principalEmail = principalEmail;
	}

	public String getLinkmanEmail() {
		return linkmanEmail;
	}

	public void setLinkmanEmail(String linkmanEmail) {
		this.linkmanEmail = linkmanEmail;
	}

	public String getLinkmanEmailTwo() {
		return linkmanEmailTwo;
	}

	public void setLinkmanEmailTwo(String linkmanEmailTwo) {
		this.linkmanEmailTwo = linkmanEmailTwo;
	}

	public String getLinkmanEmailThree() {
		return linkmanEmailThree;
	}

	public void setLinkmanEmailThree(String linkmanEmailThree) {
		this.linkmanEmailThree = linkmanEmailThree;
	}

	public String getPrincipalPhone() {
		return principalPhone;
	}

	public void setPrincipalPhone(String principalPhone) {
		this.principalPhone = principalPhone;
	}

	public String getLinkmanPhone() {
		return linkmanPhone;
	}

	public void setLinkmanPhone(String linkmanPhone) {
		this.linkmanPhone = linkmanPhone;
	}

	public String getLinkmanPhoneTwo() {
		return linkmanPhoneTwo;
	}

	public void setLinkmanPhoneTwo(String linkmanPhoneTwo) {
		this.linkmanPhoneTwo = linkmanPhoneTwo;
	}

	public String getLinkmanPhoneThree() {
		return linkmanPhoneThree;
	}

	public void setLinkmanPhoneThree(String linkmanPhoneThree) {
		this.linkmanPhoneThree = linkmanPhoneThree;
	}

	/** set 预警详情表id */
		this.id=id;
	}

		return id;
	}

		this.siteCode=siteCode;
	}

		return siteCode;
	}

		this.type=type;
	}

		return type;
	}

		this.scanTime=scanTime;
	}

		return scanTime;
	}

		this.errorCode=errorCode;
	}

		return errorCode;
	}

		this.errorDescribe=errorDescribe;
	}

		return errorDescribe;
	}

		this.url=url;
	}

		return url;
	}

		this.queue=queue;
	}

		return queue;
	}

		this.createTime=createTime;
	}

		return createTime;
	}

		this.continuedTime=continuedTime;
	}

		return continuedTime;
	}

		this.modifyTime=modifyTime;
	}

		return modifyTime;
	}

	public String getSiteCodeName() {
		return siteCodeName;
	}

	public void setSiteCodeName(String siteCodeName) {
		this.siteCodeName = siteCodeName;
	}

	public int getSendStatus() {
		return sendStatus;
	}

	public void setSendStatus(int sendStatus) {
		this.sendStatus = sendStatus;
	}

	public int getOrgTbStatus() {
		return orgTbStatus;
	}

	public void setOrgTbStatus(int orgTbStatus) {
		this.orgTbStatus = orgTbStatus;
	}

}
