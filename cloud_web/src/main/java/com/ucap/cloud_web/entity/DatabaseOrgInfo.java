package com.ucap.cloud_web.entity;


import java.util.Date;


/**


	//主键




	
	//负责人姓名
	private String principalName;
	
	//负责人邮箱
	private String principalEmail;
	
	//负责人电话
	private String principalPhone;
	
	//联系人one姓名
	private String linkmanName;
	
	//联系人one邮箱
	private String linkmanEmail;
	
	//联系人one电话
	private String linkmanPhone;
	
	//联系人two姓名
	private String linkmanNametwo;
	
	//联系人two邮箱
	private String linkmanEmailtwo;
	
	//联系人two电话
	private String linkmanPhonetwo;
	
	//联系人three姓名
	private String linkmanNamethree;
	
	//联系人three邮箱
	private String linkmanEmailthree;
	
	//联系人three电话
	private String linkmanPhonethree;
	


	//状态：1可用，2不可用
	private int type;

	
	//修改时间
	private Date modifyTime;
	
	private Integer guideState;
	
		return principalName;
	}

	public void setPrincipalName(String principalName) {
		this.principalName = principalName;
	}

	public String getPrincipalEmail() {
		return principalEmail;
	}

	public void setPrincipalEmail(String principalEmail) {
		this.principalEmail = principalEmail;
	}

	public String getPrincipalPhone() {
		return principalPhone;
	}

	public void setPrincipalPhone(String principalPhone) {
		this.principalPhone = principalPhone;
	}

	public String getLinkmanName() {
		return linkmanName;
	}

	public void setLinkmanName(String linkmanName) {
		this.linkmanName = linkmanName;
	}

	public String getLinkmanPhone() {
		return linkmanPhone;
	}

	public void setLinkmanPhone(String linkmanPhone) {
		this.linkmanPhone = linkmanPhone;
	}
	
	public String getLinkmanNametwo() {
		return linkmanNametwo;
	}

	public void setLinkmanNametwo(String linkmanNametwo) {
		this.linkmanNametwo = linkmanNametwo;
	}

	public String getLinkmanEmailtwo() {
		return linkmanEmailtwo;
	}

	public void setLinkmanEmailtwo(String linkmanEmailtwo) {
		this.linkmanEmailtwo = linkmanEmailtwo;
	}

	public String getLinkmanPhonetwo() {
		return linkmanPhonetwo;
	}

	public void setLinkmanPhonetwo(String linkmanPhonetwo) {
		this.linkmanPhonetwo = linkmanPhonetwo;
	}

	public String getLinkmanNamethree() {
		return linkmanNamethree;
	}

	public void setLinkmanNamethree(String linkmanNamethree) {
		this.linkmanNamethree = linkmanNamethree;
	}

	public String getLinkmanEmailthree() {
		return linkmanEmailthree;
	}

	public void setLinkmanEmailthree(String linkmanEmailthree) {
		this.linkmanEmailthree = linkmanEmailthree;
	}

	public String getLinkmanPhonethree() {
		return linkmanPhonethree;
	}

	public void setLinkmanPhonethree(String linkmanPhonethree) {
		this.linkmanPhonethree = linkmanPhonethree;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getLinkmanEmail() {
		return linkmanEmail;
	}

	public void setLinkmanEmail(String linkmanEmail) {
		this.linkmanEmail = linkmanEmail;
	}

	/** set 主键 */
		this.id=id;
	}

		return id;
	}

		this.name=name;
	}

		return name;
	}

		this.siteCode=siteCode;
	}

		return siteCode;
	}

		this.vcode=vcode;
	}

		return vcode;
	}

		this.vcode2=vcode2;
	}

		return vcode2;
	}

		this.createTime=createTime;
	}

		return createTime;
	}

	public Integer getGuideState() {
		return guideState;
	}

	public void setGuideState(Integer guideState) {
		this.guideState = guideState;
	}

