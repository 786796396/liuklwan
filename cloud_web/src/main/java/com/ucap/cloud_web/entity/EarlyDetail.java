package com.ucap.cloud_web.entity;


import java.util.Date;



/**
* <br>
* <b>作者：</b>cuichx<br>
* <b>日期：</b> 2015-10-29 10:47:27 <br>
* <b>版权所有：<b>版权所有(C) 2015<br>
*/
public class EarlyDetail {
	//预警详情表id
	private int id;

	//网站标识码
	private String siteCode;
	//网站标识码name
	private String siteCodeName;
	//问题类型
	private int dicChannelId;

	//问题描述
	private String queDescribe;

	// 预警类型：1首页连通性，2首页不更新，3栏目不更新，4空白栏目，5互动回应，6内容正确性
	private int type;
	
	//升级改版或者临时关停类型 1.首页连不通比例超过80%，2关键栏目连不通比例超过80%，3网站首页更新量为0，4人工检查时发现，5插码挂码
	private int updateGradeType;
	
	//检查类型（0：抽查，1：正常合同）
	private int checkType;

	//抽查进度表Id
	private int spotCheckSchedule;
	
	//发现时间（0000-00-00 00:00:00）
	private String scanTime;
	//发现时间（0000-00-00）
	private String scanDate;
	
	//错误返回码
	private String errorCode;
	
	//是否发送微信预警通知（0-未发送 1-已发送）
	private int sendWxStatus;
	
	//短信发送状态
	private int sendSmsStatus;
	
	//邮箱发送状态
	private int sendEmailState;
	
	//微信发送次数
	private int sendWxTimes;
	//短信发送次数
	private int sendSmsTimes;
	
	//短信发送时间
	private Date sendSmsTime;
	//微信发送时间
	private Date sendWxTime;
	//邮件发送时间
	private Date sendEmailTime;

	//创建时间
	private Date createTime;
	//url
	private String url;
	//错误词汇
	private String wrongTerm;
	//推荐词汇
	private String expectTerms;
	
	private String questionName;
	
	//人工审核状态（0未审核，1未通过，2通过）
	private Integer checkState;
	
	//首页连不通占比
	private String errorProportion;
	
	//空白栏目个数
	private Integer blankNum;
	
	//基本信息个数
	private Integer basicNum;
	
	private Integer scanNum;//有效监测站点个数
	private Integer notConnectionNum;//首页连不通（站点数）
	private Integer notConnectionSum;//首页连不通（次数）
	private String notConnectionPer7;//首页7日连不通率超3%站点数
	private String notConnectionPer;//7日总连不通率
	
	private String linkHomeNum;//首页链接不可用（站点数）
	private Integer linkHomeSum;//首页链接不可用链接（条数）
	private Integer notUpdateHome;//首页更新不及时站点数（日报使用）
	private Integer updateNum;//内容更新量（站点数）
	private String updateAvgNum;//内容更新量（条数）
	private Integer noUpdateChannelNum;//栏目更新不及时（站点数）
	private Integer noUpdateChannelSum;//栏目更新不及时（栏目数）
	
	private Integer noUpdateDay;//首页未更新天数
	private Integer isOrgPrincipal;//组织单位 负责人是否接受 1接受 2不接受
	private Integer isOrgLinkman;//组织单位   联系人是否接受 1接受 2不接受
	private Integer orgLinkmanTwo;//组织单位   联系人2是否接受 1接受 2不接受
	private Integer orgLinkmanThree;//组织单位   联系人3是否接受 1接受 2不接受
	
	
	private String principalEmail;//组织单位   负责人邮箱地址
	private String linkmanEmail;//组织单位        联系人邮箱地址
	private String linkmanEmailTwo;//组织单位        联系人邮箱地址
	private String linkmanEmailThree;//组织单位        联系人邮箱地址
	
	private Integer isWxReceive;//是否发送微信 1发送 2不发送
	private Integer isEmailReceive;//是否发送邮件  1发送 2不发送
	private Integer isTelReceive;//是否发送短信 1发送 2不发送
	
	private String principalPhone;//组织单位   负责人电话
	private String linkmanPhone;//组织单位        联系人电话
	private String linkmanPhoneTwo;//组织单位        联系人电话
	private String linkmanPhoneThree;//组织单位        联系人电话
	private String remark;//播报数据 问题展示
	
	private String lastUpdateDate;//首页最后更新日期
	
	private String startHour;
	
	private String endHour;
	
	//短信模板id
	private String tplId;
	//短信参数
	private String tplValue;
	
	//是否为组织单位发送预警 1-是；2否
	private int orgSendStatus;
	
	//是否为组织单位配置的填报单位发送预警 1-是；2否
	private int orgTbSendStatus;
	
	//是否为填报单位配置发送的预警1-是；2-否
	private int tbSendStatus;
	
	private Integer servicePeriodId;//服务周期id
	
	private String jumpUrl;//跳转url
	private String homeUrl;//首页url
	

	//内容保障：空白栏目
	private int securityBlank;

	//内容保障：服务不实用
	private int securityService;

	//内容保障：互动回应差
	private int securityResponse;

	//内容保障：基本信息
	private int securityBasic;
	
	//内容保障：严重错误
	private int securityFatalError;
	
	//内容保障：安全问题
	private int securityQuestion;
	
	//短连接
	private String shortUrl;
	
	public String getShortUrl() {
		return shortUrl;
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}

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

	public Integer getNotConnectionSum() {
		return notConnectionSum;
	}

	public void setNotConnectionSum(Integer notConnectionSum) {
		this.notConnectionSum = notConnectionSum;
	}

	public String getNotConnectionPer7() {
		return notConnectionPer7;
	}

	public void setNotConnectionPer7(String notConnectionPer7) {
		this.notConnectionPer7 = notConnectionPer7;
	}

	public Integer getLinkHomeSum() {
		return linkHomeSum;
	}

	public void setLinkHomeSum(Integer linkHomeSum) {
		this.linkHomeSum = linkHomeSum;
	}

	public Integer getNoUpdateChannelSum() {
		return noUpdateChannelSum;
	}

	public void setNoUpdateChannelSum(Integer noUpdateChannelSum) {
		this.noUpdateChannelSum = noUpdateChannelSum;
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

	public int getSecurityBlank() {
		return securityBlank;
	}

	public void setSecurityBlank(int securityBlank) {
		this.securityBlank = securityBlank;
	}

	public int getSecurityService() {
		return securityService;
	}

	public void setSecurityService(int securityService) {
		this.securityService = securityService;
	}

	public int getSecurityResponse() {
		return securityResponse;
	}

	public void setSecurityResponse(int securityResponse) {
		this.securityResponse = securityResponse;
	}

	public int getSecurityBasic() {
		return securityBasic;
	}

	public void setSecurityBasic(int securityBasic) {
		this.securityBasic = securityBasic;
	}

	public int getSecurityFatalError() {
		return securityFatalError;
	}

	public void setSecurityFatalError(int securityFatalError) {
		this.securityFatalError = securityFatalError;
	}

	public int getSecurityQuestion() {
		return securityQuestion;
	}

	public void setSecurityQuestion(int securityQuestion) {
		this.securityQuestion = securityQuestion;
	}

	public Integer getServicePeriodId() {
		return servicePeriodId;
	}

	public void setServicePeriodId(Integer servicePeriodId) {
		this.servicePeriodId = servicePeriodId;
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

	public int getOrgSendStatus() {
		return orgSendStatus;
	}

	public void setOrgSendStatus(int orgSendStatus) {
		this.orgSendStatus = orgSendStatus;
	}

	public int getOrgTbSendStatus() {
		return orgTbSendStatus;
	}

	public void setOrgTbSendStatus(int orgTbSendStatus) {
		this.orgTbSendStatus = orgTbSendStatus;
	}

	public int getTbSendStatus() {
		return tbSendStatus;
	}

	public void setTbSendStatus(int tbSendStatus) {
		this.tbSendStatus = tbSendStatus;
	}

	public String getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(String lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getLinkHomeNum() {
		return linkHomeNum;
	}

	public void setLinkHomeNum(String linkHomeNum) {
		this.linkHomeNum = linkHomeNum;
	}

	public String getUpdateAvgNum() {
		return updateAvgNum;
	}

	public void setUpdateAvgNum(String updateAvgNum) {
		this.updateAvgNum = updateAvgNum;
	}

	public Integer getBasicNum() {
		return basicNum;
	}

	public void setBasicNum(Integer basicNum) {
		this.basicNum = basicNum;
	}

	public Integer getBlankNum() {
		return blankNum;
	}

	public void setBlankNum(Integer blankNum) {
		this.blankNum = blankNum;
	}

	public String getErrorProportion() {
		return errorProportion;
	}

	public void setErrorProportion(String errorProportion) {
		this.errorProportion = errorProportion;
	}

	public Integer getCheckState() {
		return checkState;
	}

	public void setCheckState(Integer checkState) {
		this.checkState = checkState;
	}

	public int getUpdateGradeType() {
		return updateGradeType;
	}

	public void setUpdateGradeType(int updateGradeType) {
		this.updateGradeType = updateGradeType;
	}

	public int getCheckType() {
		return checkType;
	}

	public void setCheckType(int checkType) {
		this.checkType = checkType;
	}

	public int getSpotCheckSchedule() {
		return spotCheckSchedule;
	}

	public void setSpotCheckSchedule(int spotCheckSchedule) {
		this.spotCheckSchedule = spotCheckSchedule;
	}

	public String getQuestionName() {
		return questionName;
	}

	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public Date getSendEmailTime() {
		return sendEmailTime;
	}

	public void setSendEmailTime(Date sendEmailTime) {
		this.sendEmailTime = sendEmailTime;
	}

	public int getSendEmailState() {
		return sendEmailState;
	}

	public void setSendEmailState(int sendEmailState) {
		this.sendEmailState = sendEmailState;
	}

	public Date getSendSmsTime() {
		return sendSmsTime;
	}

	public void setSendSmsTime(Date sendSmsTime) {
		this.sendSmsTime = sendSmsTime;
	}

	public Date getSendWxTime() {
		return sendWxTime;
	}

	public void setSendWxTime(Date sendWxTime) {
		this.sendWxTime = sendWxTime;
	}

	public String getWrongTerm() {
		return wrongTerm;
	}

	public void setWrongTerm(String wrongTerm) {
		this.wrongTerm = wrongTerm;
	}

	public String getExpectTerms() {
		return expectTerms;
	}

	public void setExpectTerms(String expectTerms) {
		this.expectTerms = expectTerms;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getSendWxTimes() {
		return sendWxTimes;
	}

	public void setSendWxTimes(int sendWxTimes) {
		this.sendWxTimes = sendWxTimes;
	}

	public int getSendSmsTimes() {
		return sendSmsTimes;
	}

	public void setSendSmsTimes(int sendSmsTimes) {
		this.sendSmsTimes = sendSmsTimes;
	}

	public int getSendSmsStatus() {
		return sendSmsStatus;
	}

	public void setSendSmsStatus(int sendSmsStatus) {
		this.sendSmsStatus = sendSmsStatus;
	}


	public int getSendWxStatus() {
		return sendWxStatus;
	}

	public void setSendWxStatus(int sendWxStatus) {
		this.sendWxStatus = sendWxStatus;
	}

	/** set 预警详情表id */
	public void setId(int id){
		this.id=id;
	}

	/** get 预警详情表id */
	public int getId(){
		return id;
	}

	/** set 网站标识码 */
	public void setSiteCode(String siteCode){
		this.siteCode=siteCode;
	}

	/** get 网站标识码 */
	public String getSiteCode(){
		return siteCode;
	}

	/** set 问题类型 */
	public void setDicChannelId(int dicChannelId){
		this.dicChannelId=dicChannelId;
	}

	/** get 问题类型 */
	public int getDicChannelId(){
		return dicChannelId;
	}

	/** set 问题描述 */
	public void setQueDescribe(String queDescribe){
		this.queDescribe=queDescribe;
	}

	/** get 问题描述 */
	public String getQueDescribe(){
		return queDescribe;
	}
	
	/** get 预警类型：1首页连通性，2首页不更新，3栏目不更新，4空白栏目，5互动回应，6内容正确性 */
	public int getType() {
		return type;
	}

	/** set 预警类型：1首页连通性，2首页不更新，3栏目不更新，4空白栏目，5互动回应，6内容正确性 */
	public void setType(int type) {
		this.type = type;
	}

	/** set 发现时间（0000-00-00 00:00:00） */
	public void setScanTime(String scanTime){
		this.scanTime=scanTime;
	}

	/** get 发现时间（0000-00-00 00:00:00） */
	public String getScanTime(){
		return scanTime;
	}

	/** set 创建时间 */
	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}

	/** get 创建时间 */
	public Date getCreateTime(){
		return createTime;
	}

	public Integer getScanNum() {
		return scanNum;
	}

	public void setScanNum(Integer scanNum) {
		this.scanNum = scanNum;
	}

	public Integer getNotConnectionNum() {
		return notConnectionNum;
	}

	public void setNotConnectionNum(Integer notConnectionNum) {
		this.notConnectionNum = notConnectionNum;
	}

	public String getNotConnectionPer() {
		return notConnectionPer;
	}

	public void setNotConnectionPer(String notConnectionPer) {
		this.notConnectionPer = notConnectionPer;
	}


	public Integer getNotUpdateHome() {
		return notUpdateHome;
	}

	public void setNotUpdateHome(Integer notUpdateHome) {
		this.notUpdateHome = notUpdateHome;
	}

	public Integer getUpdateNum() {
		return updateNum;
	}

	public void setUpdateNum(Integer updateNum) {
		this.updateNum = updateNum;
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

	public String getSiteCodeName() {
		return siteCodeName;
	}

	public void setSiteCodeName(String siteCodeName) {
		this.siteCodeName = siteCodeName;
	}

	public Integer getIsEmailReceive() {
		return isEmailReceive;
	}

	public void setIsEmailReceive(Integer isEmailReceive) {
		this.isEmailReceive = isEmailReceive;
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

	public Integer getIsWxReceive() {
		return isWxReceive;
	}

	public void setIsWxReceive(Integer isWxReceive) {
		this.isWxReceive = isWxReceive;
	}

	public Integer getIsTelReceive() {
		return isTelReceive;
	}

	public void setIsTelReceive(Integer isTelReceive) {
		this.isTelReceive = isTelReceive;
	}

	public Integer getNoUpdateDay() {
		return noUpdateDay;
	}

	public void setNoUpdateDay(Integer noUpdateDay) {
		this.noUpdateDay = noUpdateDay;
	}

	public Integer getNoUpdateChannelNum() {
		return noUpdateChannelNum;
	}

	public void setNoUpdateChannelNum(Integer noUpdateChannelNum) {
		this.noUpdateChannelNum = noUpdateChannelNum;
	}

	public String getScanDate() {
		return scanDate;
	}

	public void setScanDate(String scanDate) {
		this.scanDate = scanDate;
	}

}

