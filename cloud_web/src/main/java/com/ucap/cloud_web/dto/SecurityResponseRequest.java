package com.ucap.cloud_web.dto;


import com.publics.util.page.Query;


/*** 前台页面传递基础数据<br>* <b>作者：</b>SunJiang<br>* <b>日期：</b> 2015-10-30 09:51:21 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/@SuppressWarnings("serial")public class SecurityResponseRequest extends Query {

	/**
	 * 网站标识码
	 */
	private String siteCode;
	
	// 栏目url
	private String channelUrl;
	
	// 问题类型id
	private int problemTypeId;
	/**
	 * 栏目名称
	 */
	private String dicChannelId;
	/**
	 * 期号id
	 */
	private int servicePeriodId;
	
	/**
	 * 统计栏目个数
	 */
	private Integer channelSum;
	/**
	 * 互动回应差栏目id数组
	 */
	private int[] dicChannelIdArray;
	
	private String startTime;//开始时间（主要是和创建时间做对比）
	
	private String endTime;//结束时间（主要是和创建时间做对比）
	
	private String returnTime;//返回时间
	private Integer problemNum;
	private String siteName;
	private String homePageUrl;
	private String jumpPageUrl;
	private Integer isorganizational;//是否门户	
	private String director;//主办单位
	
	private String channelName;//栏目名称
	
	
	private String beginScanDate;//服务开始日期
	
	private String endScanDate;//服务结束日期
	
	private String scanDate;//用于周期弱化后，发现时间
	
	private String problemDesc;//问题描述
	
	
	
	public String getProblemDesc() {
		return problemDesc;
	}
	public void setProblemDesc(String problemDesc) {
		this.problemDesc = problemDesc;
	}
	public String getChannelUrl() {
		return channelUrl;
	}
	public void setChannelUrl(String channelUrl) {
		this.channelUrl = channelUrl;
	}
	public int getProblemTypeId() {
		return problemTypeId;
	}
	public void setProblemTypeId(int problemTypeId) {
		this.problemTypeId = problemTypeId;
	}
	public String getBeginScanDate() {
		return beginScanDate;
	}
	public void setBeginScanDate(String beginScanDate) {
		this.beginScanDate = beginScanDate;
	}
	public String getEndScanDate() {
		return endScanDate;
	}
	public void setEndScanDate(String endScanDate) {
		this.endScanDate = endScanDate;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
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
	private String createTime;
	
	
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
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getSiteCode() {
		return siteCode;
	}
	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}
	public String getDicChannelId() {
		return dicChannelId;
	}
	public void setDicChannelId(String dicChannelId) {
		this.dicChannelId = dicChannelId;
	}
	public int getServicePeriodId() {
		return servicePeriodId;
	}
	public void setServicePeriodId(int servicePeriodId) {
		this.servicePeriodId = servicePeriodId;
	}
	public Integer getChannelSum() {
		return channelSum;
	}
	public void setChannelSum(Integer channelSum) {
		this.channelSum = channelSum;
	}
	public String getReturnTime() {
		return returnTime;
	}
	public void setReturnTime(String returnTime) {
		this.returnTime = returnTime;
	}
	public int[] getDicChannelIdArray() {
		return dicChannelIdArray;
	}
	public void setDicChannelIdArray(int[] dicChannelIdArray) {
		this.dicChannelIdArray = dicChannelIdArray;
	}
	public Integer getProblemNum() {
		return problemNum;
	}
	public void setProblemNum(Integer problemNum) {
		this.problemNum = problemNum;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
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
	public String getScanDate() {
		return scanDate;
	}
	public void setScanDate(String scanDate) {
		this.scanDate = scanDate;
	}

}

