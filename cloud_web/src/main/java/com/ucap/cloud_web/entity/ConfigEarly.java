package com.ucap.cloud_web.entity;


import java.util.Date;


/**


	//预警配置详情表id








	private Integer isLinkmanTwo;
	private Integer isLinkmanThree;
	//组织单位负责人是否接收预警 1接收 2不接收
	private Integer isOrgPrincipal;
	//组织单位联系人是否接收预警 1接收 2不接收
	private Integer isOrgLinkman;
	private Integer orgLinkmanTwo;
	private Integer orgLinkmanThree;
	
	













	
	private String principalEmail;//组织单位   负责人邮箱地址
	private String linkmanEmail;//组织单位        联系人邮箱地址
	private String principalPhone;//组织单位   负责人电话
	private String linkmanPhone;//组织单位        联系人电话
	
	//短信模板id
	private String tplId;
	//短信参数
	private String tplValue;
	
	//databaseLink表 type
	private int dataBaseLinkType;
	
	//填报单位负责人
	private String principalName;
	//填报单位联系人
	private String linkmanName;
	private String linkmanNameTwo;
	private String linkmanNameThree;
	//填报单位负责人手机
	private String telephone;
	//填报单位联系人手机
	private String telephone2;
	private String linkmanPhoneTwo;
	private String linkmanPhoneThree;
	//填报单位负责人邮箱
	private String email;
	//填报单位联系人邮箱
	private String email2;
	private String linkmanEmailTwo;
	private String linkmanEmailThree;
	//站点名称
	private String name;
	
	//跳转url
	private String jumpUrl;
	
	//首页url
	private String siteUrl;
	
	// 开始 几时
	private String startHour;
	// 结束 几时
	private String endHour;

	

	public String getStartHour() {
		return startHour;
	}

	public void setStartHour(String startHour) {
		this.startHour = startHour;
	}

	public String getEndHour() {
		return endHour;
	}

	public void setEndHour(String endHour) {
		this.endHour = endHour;
	}

	public Integer getOrgLinkmanTwo() {
		return orgLinkmanTwo;
	}

	public void setOrgLinkmanTwo(Integer orgLinkmanTwo) {
		this.orgLinkmanTwo = orgLinkmanTwo;
	}

	public Integer getOrgLinkmanThree() {
		return orgLinkmanThree;
	}

	public void setOrgLinkmanThree(Integer orgLinkmanThree) {
		this.orgLinkmanThree = orgLinkmanThree;
	}

	public Integer getIsLinkmanTwo() {
		return isLinkmanTwo;
	}

	public void setIsLinkmanTwo(Integer isLinkmanTwo) {
		this.isLinkmanTwo = isLinkmanTwo;
	}

	public Integer getIsLinkmanThree() {
		return isLinkmanThree;
	}

	public void setIsLinkmanThree(Integer isLinkmanThree) {
		this.isLinkmanThree = isLinkmanThree;
	}

	public String getLinkmanNameTwo() {
		return linkmanNameTwo;
	}

	public void setLinkmanNameTwo(String linkmanNameTwo) {
		this.linkmanNameTwo = linkmanNameTwo;
	}

	public String getLinkmanNameThree() {
		return linkmanNameThree;
	}

	public void setLinkmanNameThree(String linkmanNameThree) {
		this.linkmanNameThree = linkmanNameThree;
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

	public String getJumpUrl() {
		return jumpUrl;
	}

	public void setJumpUrl(String jumpUrl) {
		this.jumpUrl = jumpUrl;
	}

	public String getSiteUrl() {
		return siteUrl;
	}

	public void setSiteUrl(String siteUrl) {
		this.siteUrl = siteUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrincipalName() {
		return principalName;
	}

	public void setPrincipalName(String principalName) {
		this.principalName = principalName;
	}

	public String getLinkmanName() {
		return linkmanName;
	}

	public void setLinkmanName(String linkmanName) {
		this.linkmanName = linkmanName;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getTelephone2() {
		return telephone2;
	}

	public void setTelephone2(String telephone2) {
		this.telephone2 = telephone2;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail2() {
		return email2;
	}

	public void setEmail2(String email2) {
		this.email2 = email2;
	}

	/** set 预警配置详情表id */
		this.id=id;
	}

		return id;
	}

		this.siteCode=siteCode;
	}

		return siteCode;
	}

		this.earlyType=earlyType;
	}

		return earlyType;
	}

		this.isWxReceive=isWxReceive;
	}

		return isWxReceive;
	}

		this.isEmailReceive=isEmailReceive;
	}

		return isEmailReceive;
	}

		this.isTelReceive=isTelReceive;
	}

		return isTelReceive;
	}

		this.isDailyReceive=isDailyReceive;
	}

		return isDailyReceive;
	}

		this.isPrincipalReceive=isPrincipalReceive;
	}

		return isPrincipalReceive;
	}

		this.isLinkmanReceive=isLinkmanReceive;
	}

		return isLinkmanReceive;
	}

		this.homeConnection=homeConnection;
	}

		return homeConnection;
	}

		this.homeConnectionPer=homeConnectionPer;
	}

		return homeConnectionPer;
	}

		this.correctContent=correctContent;
	}

		return correctContent;
	}

		this.modifySite=modifySite;
	}

		return modifySite;
	}

		this.blankChannel=blankChannel;
	}

		return blankChannel;
	}

		this.notUpdateChannel=notUpdateChannel;
	}

		return notUpdateChannel;
	}

		this.notUpdateHome=notUpdateHome;
	}

		return notUpdateHome;
	}

		this.securityResponse=securityResponse;
	}

		return securityResponse;
	}

		this.isSiteReceive=isSiteReceive;
	}

		return isSiteReceive;
	}


		this.isNextAllSite=isNextAllSite;
	}

		return isNextAllSite;
	}

		this.userId=userId;
	}

		return userId;
	}

		this.createTime=createTime;
	}

		return createTime;
	}

		this.modifyTime=modifyTime;
	}

		return modifyTime;
	}

	public Integer getIsOrgPrincipal() {
		return isOrgPrincipal;
	}

	public void setIsOrgPrincipal(Integer isOrgPrincipal) {
		this.isOrgPrincipal = isOrgPrincipal;
	}

	public Integer getIsOrgLinkman() {
		return isOrgLinkman;
	}

	public void setIsOrgLinkman(Integer isOrgLinkman) {
		this.isOrgLinkman = isOrgLinkman;
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

	public int getDataBaseLinkType() {
		return dataBaseLinkType;
	}

	public void setDataBaseLinkType(int dataBaseLinkType) {
		this.dataBaseLinkType = dataBaseLinkType;
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

	public String getTplId() {
		return tplId;
	}

	public void setTplId(String tplId) {
		this.tplId = tplId;
	}

	public String getTplValue() {
		return tplValue;
	}

	public void setTplValue(String tplValue) {
		this.tplValue = tplValue;
	}

