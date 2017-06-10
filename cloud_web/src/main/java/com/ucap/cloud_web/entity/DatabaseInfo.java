package com.ucap.cloud_web.entity;


import java.io.Serializable;
import java.util.Date;

import com.publics.util.utils.StringUtils;
import com.ucap.cloud_web.util.CommonUtils;


/*** <br>* <b>作者：</b>kefan<br>* <b>日期：</b> 2015-11-16 11:05:33 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/public class DatabaseInfo implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//	private int id;
	//父节点id，初始值为0	private String parentId;
	//网站标识码	private String siteCode;

	//网站名称
	private String name;
		//网站校验码	private String vcode;
	//省	private String province;
	//市	private String city;
	//县	private String county;
	
	//级别，1省份，2市，3县
	private String level;

	//0非门户，1门户	private int isorganizational;
	
	//url地址
	private String url;
	
	//跳转url
	private String jumpUrl;
	
	//md5加密url(先jump_url后url)
	private String encodeUrl;
	//网站主管单位	private String director;
	//办公地址
	private String address;
	
	//联系人
	private String linkmanName;
	
	//新增联系人姓名
	private String  linkmanNameTwo;
	private String  linkmanNameThree;
	
	
	//负责人
	private String principalName;
	
	//负责人职位   add by Na.Y 20160908
	private String principalPost;
	
	//负责人办公电话
	private String mobile;
	
	//联系人办公电话
	private String mobile2;
	
	//负责人手机
	private String telephone;
	
	//联系人手机
	private String telephone2;
	
	//新增联系人手机
	private String linkmanPhoneTwo;
	private String linkmanPhoneThree;
	
	//登录验证邮箱--负责人
	private String email;
	
	//邮箱--联系人
	private String email2;
	
	//新增联系人邮箱
	private String linkmanEmailTwo;
	private String linkmanEmailThree;
	
	//1正常，2例外，3关停
	private int isexp;
	
	//是否收费（0免费，1收费）
	private int iscost;
	
	//负责人是否接收邮箱（0：否，1：是）
	private int emailReceive;
	
	//联系人是否接收邮箱（0：否，1：是）
	private int emailReceive2 ;
	
	//负责人是否接收手机短信（0：否，1：是）
	private int telReceive ;
	
	//联系人是否接收手机短信（0：否，1：是）
	private int telReceive2;
	
	//状态（0不连通，1连通）
	private int linkStatus ;
	
	//是否可用（1：可用，0：不可用）
	private int state;
	
	//创建时间	private Date createTime;
	//修改时间
	private Date modifyTime;
	
	//是否扫描
	private int isScan;
	
	//备注（用于填写不扫描原因）
	private String remark;
	
	//针对全面监测-站点报告是否可下载
	private int isDown;
	
	//url地址 站点信息管理 用到的字段
	private String manageInfoUrl;
		
	//跳转url 站点信息管理 用到的字段
	private String manageInfoJumpUrl;
	
	//任务队列（1：5分钟、2：10分钟、3：15分钟、4：30分钟、5：60分钟、6：1天）
	private int queue;
	
	//判断是否完成 
	private Integer guideState;
	
	public int getIsScan() {
		return isScan;
	}
	//针对全面监测-站点报告状态
	private int checkReportResult;
	//栏目数量 -- 数据库中不存在
	private int channelNum;
	
	
	private Integer type;
	
	//网站类型 1门户 2本级  3 下属
	private Integer layerType;
	
	public Integer getLayerType() {
		return layerType;
	}
	public void setLayerType(Integer layerType) {
		this.layerType = layerType;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getPrincipalPost() {
		return principalPost;
	}

	public void setPrincipalPost(String principalPost) {
		this.principalPost = principalPost;
	}

	/**
	 * @return the channelNum
	 */
	public int getChannelNum() {
		return channelNum;
	}

	/**
	 * @param channelNum the channelNum to set
	 */
	public void setChannelNum(int channelNum) {
		this.channelNum = channelNum;
	}

	/**
	 * @return the serialversionuid
	 */
	
	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public int getCheckReportResult() {
		return checkReportResult;
	}

	public void setCheckReportResult(int checkReportResult) {
		this.checkReportResult = checkReportResult;
	}

	public void setIsScan(int isScan) {
		this.isScan = isScan;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVcode() {
		return vcode;
	}

	public void setVcode(String vcode) {
		this.vcode = vcode;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public int getIsorganizational() {
		return isorganizational;
	}

	public void setIsorganizational(int isorganizational) {
		this.isorganizational = isorganizational;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getJumpUrl() {
		return jumpUrl;
	}

	public void setJumpUrl(String jumpUrl) {
		this.jumpUrl = jumpUrl;
	}

	public String getEncodeUrl() {
		return encodeUrl;
	}

	public void setEncodeUrl(String encodeUrl) {
		this.encodeUrl = encodeUrl;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
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

	public int getIsexp() {
		return isexp;
	}

	public void setIsexp(int isexp) {
		this.isexp = isexp;
	}

	public int getIscost() {
		return iscost;
	}

	public void setIscost(int iscost) {
		this.iscost = iscost;
	}

	public int getEmailReceive() {
		return emailReceive;
	}

	public void setEmailReceive(int emailReceive) {
		this.emailReceive = emailReceive;
	}

	public int getEmailReceive2() {
		return emailReceive2;
	}

	public void setEmailReceive2(int emailReceive2) {
		this.emailReceive2 = emailReceive2;
	}

	public int getTelReceive() {
		return telReceive;
	}

	public void setTelReceive(int telReceive) {
		this.telReceive = telReceive;
	}

	public int getTelReceive2() {
		return telReceive2;
	}

	public void setTelReceive2(int telReceive2) {
		this.telReceive2 = telReceive2;
	}

	public int getLinkStatus() {
		return linkStatus;
	}

	public void setLinkStatus(int linkStatus) {
		this.linkStatus = linkStatus;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public int getIsDown() {
		return isDown;
	}

	public void setIsDown(int isDown) {
		this.isDown = isDown;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	
	
/*	public String toString() {
		return "DatabaseInfo [id=" + id + ", parentId=" + parentId
				+ ", siteCode=" + siteCode + ", name=" + name + ", vcode="
				+ vcode + ", province=" + province + ", city=" + city
				+ ", county=" + county + ", level=" + level
				+ ", isorganizational=" + isorganizational + ", url=" + url
				+ ", jumpUrl=" + jumpUrl + ", encodeUrl=" + encodeUrl
				+ ", director=" + director + ", address=" + address
				+ ", linkmanName=" + linkmanName + ", principalName="
				+ principalName + ", mobile=" + mobile + ", mobile2=" + mobile2
				+ ", telephone=" + telephone + ", telephone2=" + telephone2
				+ ", email=" + email + ", email2=" + email2 + ", isexp="
				+ isexp + ", iscost=" + iscost + ", emailReceive="
				+ emailReceive + ", emailReceive2=" + emailReceive2
				+ ", telReceive=" + telReceive + ", telReceive2=" + telReceive2
				+ ", linkStatus=" + linkStatus + ", createTime=" + createTime
				+ ", modifyTime=" + modifyTime +",guideState="+guideState+ "]";
	}*/
	

	

	
	public String getManageInfoUrl() {
		if(StringUtils.isNotEmpty(this.url)){
			this.manageInfoUrl = url;
			return manageInfoUrl;
		}else{
			return manageInfoUrl;
		}
		
	}

	

	@Override
	public String toString() {
		return "DatabaseInfo [id=" + id + ", parentId=" + parentId
				+ ", siteCode=" + siteCode + ", name=" + name + ", vcode="
				+ vcode + ", province=" + province + ", city=" + city
				+ ", county=" + county + ", level=" + level
				+ ", isorganizational=" + isorganizational + ", url=" + url
				+ ", jumpUrl=" + jumpUrl + ", encodeUrl=" + encodeUrl
				+ ", director=" + director + ", address=" + address
				+ ", linkmanName=" + linkmanName + ", linkmanNameTwo="
				+ linkmanNameTwo + ", linkmanNameThree=" + linkmanNameThree
				+ ", principalName=" + principalName + ", principalPost="
				+ principalPost + ", mobile=" + mobile + ", mobile2=" + mobile2
				+ ", telephone=" + telephone + ", telephone2=" + telephone2
				+ ", linkmanPhoneTwo=" + linkmanPhoneTwo
				+ ", linkmanPhoneThree=" + linkmanPhoneThree + ", email="
				+ email + ", email2=" + email2 + ", linkmanEmailTwo="
				+ linkmanEmailTwo + ", linkmanEmailThree=" + linkmanEmailThree
				+ ", isexp=" + isexp + ", iscost=" + iscost + ", emailReceive="
				+ emailReceive + ", emailReceive2=" + emailReceive2
				+ ", telReceive=" + telReceive + ", telReceive2=" + telReceive2
				+ ", linkStatus=" + linkStatus + ", state=" + state
				+ ", createTime=" + createTime + ", modifyTime=" + modifyTime
				+ ", isScan=" + isScan + ", remark=" + remark + ", isDown="
				+ isDown + ", manageInfoUrl=" + manageInfoUrl
				+ ", manageInfoJumpUrl=" + manageInfoJumpUrl + ", queue="
				+ queue + ", guideState=" + guideState + ", checkReportResult="
				+ checkReportResult + ", channelNum=" + channelNum + "]";
	}

	public String getManageInfoJumpUrl() {
		if(StringUtils.isNotEmpty(jumpUrl)){
			this.manageInfoJumpUrl=jumpUrl;
			return manageInfoJumpUrl;
		}else{
			return manageInfoJumpUrl;
		}
		
	}

	public void setManageInfoUrl(String manageInfoUrl) {
		this.manageInfoUrl = manageInfoUrl;
	}

	public void setManageInfoJumpUrl(String manageInfoJumpUrl) {
		this.manageInfoJumpUrl = manageInfoJumpUrl;
	}

	public int getQueue() {
		return queue;
	}

	public void setQueue(int queue) {
		this.queue = queue;
	}

	public Integer getGuideState() {
		return guideState;
	}

	public void setGuideState(Integer guideState) {
		this.guideState = guideState;
	}
	}

