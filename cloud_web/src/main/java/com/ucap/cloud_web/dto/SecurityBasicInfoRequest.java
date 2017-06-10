package com.ucap.cloud_web.dto;


import com.publics.util.page.Query;


/*** 前台页面传递基础数据<br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2016-04-12 10:13:05 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@SuppressWarnings("serial")public class SecurityBasicInfoRequest extends Query {
	
	/**
	 * 网站标识码
	 */
	private String siteCode;
	/**
	 * 栏目名称
	 */
	private String channelName ;
	/**
	 * 期号id
	 */
	private int servicePeriodId;
	
	
	/**
	 * 封装数据查询的数据
	 */
	private Integer serviceSum;//每类问题的个数
	private Integer problemTypeId;//问题类型id
	private String returnTime;//返回时间
	private Integer problemNum;
	private String homePageUrl;
	private String jumpPageUrl;
	private String siteName;
	
	private Integer isorganizational;//是否门户	
	private String director;//主办单位
	
	//创建时间
	private String createTime;
	
	//办事事项
	private String serviceItem;
	
	
	//办公地址
	private String address;
	
	//联系人
	private String linkmanName;
	
	//负责人
	private String principalName;
	
	//负责人办公电话
	private String mobile;
	
	//联系人办公电话
	private String mobile2;
	
	//负责人手机
	private String telephone;
	
	//联系人手机
	private String telephone2;
	
	//登录验证邮箱--负责人
	private String email;
	
	//邮箱--联系人
	private String email2;
	
	//发现时间
	private String scanDate;
	
	//基本信息个数统计
	private Integer basicNum;
	
	
	public Integer getBasicNum() {
		return basicNum;
	}
	public void setBasicNum(Integer basicNum) {
		this.basicNum = basicNum;
	}
	public Integer getServiceSum() {
		return serviceSum;
	}
	public void setServiceSum(Integer serviceSum) {
		this.serviceSum = serviceSum;
	}
	public Integer getProblemTypeId() {
		return problemTypeId;
	}
	public void setProblemTypeId(Integer problemTypeId) {
		this.problemTypeId = problemTypeId;
	}
	public String getReturnTime() {
		return returnTime;
	}
	public void setReturnTime(String returnTime) {
		this.returnTime = returnTime;
	}
	public Integer getProblemNum() {
		return problemNum;
	}
	public void setProblemNum(Integer problemNum) {
		this.problemNum = problemNum;
	}
	public String getHomePageUrl() {
		return homePageUrl;
	}
	public void setHomePageUrl(String homePageUrl) {
		this.homePageUrl = homePageUrl;
	}
	public String getJumpPageUrl() {
		return jumpPageUrl;
	}
	public void setJumpPageUrl(String jumpPageUrl) {
		this.jumpPageUrl = jumpPageUrl;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public Integer getIsorganizational() {
		return isorganizational;
	}
	public void setIsorganizational(Integer isorganizational) {
		this.isorganizational = isorganizational;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getServiceItem() {
		return serviceItem;
	}
	public void setServiceItem(String serviceItem) {
		this.serviceItem = serviceItem;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLinkmanName() {
		return linkmanName;
	}
	public void setLinkmanName(String linkmanName) {
		this.linkmanName = linkmanName;
	}
	public String getPrincipalName() {
		return principalName;
	}
	public void setPrincipalName(String principalName) {
		this.principalName = principalName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getMobile2() {
		return mobile2;
	}
	public void setMobile2(String mobile2) {
		this.mobile2 = mobile2;
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
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public String getSiteCode() {
		return siteCode;
	}
	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	public int getServicePeriodId() {
		return servicePeriodId;
	}
	public void setServicePeriodId(int servicePeriodId) {
		this.servicePeriodId = servicePeriodId;
	}
	public String getScanDate() {
		return scanDate;
	}
	public void setScanDate(String scanDate) {
		this.scanDate = scanDate;
	}

}

