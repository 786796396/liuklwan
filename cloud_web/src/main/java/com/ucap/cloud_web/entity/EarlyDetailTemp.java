package com.ucap.cloud_web.entity;


import java.util.Date;


/*** <br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2017-03-14 14:39:10 <br>* <b>版权所有：<b>版权所有(C) 2017<br>*/public class EarlyDetailTemp {


	//预警详情表id	private int id;
	//网站标识码	private String siteCode;

	//网站标识码name
	private String siteCodeName;
		//预警类型：1首页连通性	private int type;
	//发现时间（0000-00-00 00:00:00）	private String scanTime;
	//错误返回码	private String errorCode;
	//问题描述	private String errorDescribe;
	//url	private String url;
	//是否发送预警 1-是；2否	private int sendStatus;

	//组织单位发送预警类型（1：本组织，2：下级填报单位），0：填报单位本身
	private int orgTbStatus;
		//任务队列（1：5分钟、2：10分钟、3：15分钟、4：30分钟、5：60分钟、6：1天）	private int queue;
	//创建时间	private Date createTime;
	//持续时间（保存的是上次的修改时间）	private Date continuedTime;
	//修改时间	private Date modifyTime;
	
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
	
	public String getLastScanTime() {
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

	/** set 预警详情表id */	public void setId(int id){
		this.id=id;
	}
	/** get 预警详情表id */	public int getId(){
		return id;
	}
	/** set 网站标识码 */	public void setSiteCode(String siteCode){
		this.siteCode=siteCode;
	}
	/** get 网站标识码 */	public String getSiteCode(){
		return siteCode;
	}
	/** set 预警类型：1首页连通性 */	public void setType(int type){
		this.type=type;
	}
	/** get 预警类型：1首页连通性 */	public int getType(){
		return type;
	}
	/** set 发现时间（0000-00-00 00:00:00） */	public void setScanTime(String scanTime){
		this.scanTime=scanTime;
	}
	/** get 发现时间（0000-00-00 00:00:00） */	public String getScanTime(){
		return scanTime;
	}
	/** set 错误返回码 */	public void setErrorCode(String errorCode){
		this.errorCode=errorCode;
	}
	/** get 错误返回码 */	public String getErrorCode(){
		return errorCode;
	}
	/** set 问题描述 */	public void setErrorDescribe(String errorDescribe){
		this.errorDescribe=errorDescribe;
	}
	/** get 问题描述 */	public String getErrorDescribe(){
		return errorDescribe;
	}
	/** set url */	public void setUrl(String url){
		this.url=url;
	}
	/** get url */	public String getUrl(){
		return url;
	}
	/** set 任务队列（1：5分钟、2：10分钟、3：15分钟、4：30分钟、5：60分钟、6：1天） */	public void setQueue(int queue){
		this.queue=queue;
	}
	/** get 任务队列（1：5分钟、2：10分钟、3：15分钟、4：30分钟、5：60分钟、6：1天） */	public int getQueue(){
		return queue;
	}
	/** set 创建时间 */	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}
	/** get 创建时间 */	public Date getCreateTime(){
		return createTime;
	}
	/** set 持续时间（保存的是上次的修改时间） */	public void setContinuedTime(Date continuedTime){
		this.continuedTime=continuedTime;
	}
	/** get 持续时间（保存的是上次的修改时间） */	public Date getContinuedTime(){
		return continuedTime;
	}
	/** set 修改时间 */	public void setModifyTime(Date modifyTime){
		this.modifyTime=modifyTime;
	}
	/** get 修改时间 */	public Date getModifyTime(){
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

