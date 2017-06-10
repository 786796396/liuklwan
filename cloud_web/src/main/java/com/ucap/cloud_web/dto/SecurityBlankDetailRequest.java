package com.ucap.cloud_web.dto;


import com.publics.util.page.Query;


/*** 前台页面传递基础数据<br>* <b>作者：</b>SunJiang<br>* <b>日期：</b> 2015-10-30 09:51:21 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/@SuppressWarnings("serial")public class SecurityBlankDetailRequest extends Query {

	/**
	 * 网站标识码
	 */
	private String siteCode;
	/**
	 * 栏目名称
	 */
	private String channelName;
	/**
	 * 空白栏目趋势id
	 */
	private Integer securityBlankInfo;
	/**
	 * 期号id
	 */
	private Integer servicePeriodId;
	
	private String beginTime;
	private String endTime;
	
	private String beginScanDate;
	
	private String endScanDate;
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
	
	//创建时间
	private String createTime;
	
	//发现时间
	private String scanDate;

	/**
	 * 用于封装从数据库中查出的数据
	 */
	private Integer blankNum;//空白栏目个数
	private String siteName;
	private String homePageUrl;
	private String jumpPageUrl;
	private Integer isorganizational;//是否门户	
	private String director;//主办单位
	
	private Integer blankChannelSum;//每个月统计个数
	private String returnTime;//返回时间YYYY-MM
	
	
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
	public Integer getBlankNum() {
		return blankNum;
	}
	public void setBlankNum(Integer blankNum) {
		this.blankNum = blankNum;
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
	public String getSiteCode() {
		return siteCode;
	}
	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	
	public Integer getSecurityBlankInfo() {
		return securityBlankInfo;
	}
	public void setSecurityBlankInfo(Integer securityBlankInfo) {
		this.securityBlankInfo = securityBlankInfo;
	}
	public Integer getServicePeriodId() {
		return servicePeriodId;
	}
	public void setServicePeriodId(Integer servicePeriodId) {
		this.servicePeriodId = servicePeriodId;
	}
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Integer getBlankChannelSum() {
		return blankChannelSum;
	}
	public void setBlankChannelSum(Integer blankChannelSum) {
		this.blankChannelSum = blankChannelSum;
	}
	public String getReturnTime() {
		return returnTime;
	}
	public void setReturnTime(String returnTime) {
		this.returnTime = returnTime;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getScanDate() {
		return scanDate;
	}
	public void setScanDate(String scanDate) {
		this.scanDate = scanDate;
	}
	
	
}

