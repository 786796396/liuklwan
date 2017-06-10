package com.ucap.cloud_web.entity;


import java.util.Date;


/*** <br>* <b>作者：</b>liujc<br>* <b>日期：</b> 2016-07-20 14:57:52 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public class ConfigEarly {


	//预警配置详情表id	private Integer id;
	//网站标识码	private String siteCode;
	//预警设置类型(仅组织单位有)：1站群日报设置，2单位预警设置	private Integer earlyType;
	//是否接收微信预警 1接收 2不接收	private Integer isWxReceive;
	//是否接收邮件预警 1接收 2不接收	private Integer isEmailReceive;
	//是否接收短信预警 1接收 2不接收	private Integer isTelReceive;
	//是否接收日报（仅组织单位有）  1接收 2不接收	private Integer isDailyReceive;
	//填报单位负责人是否接收预警 1接收 2不接收	private Integer isPrincipalReceive;
	//填报单位联系人是否接收预警 1接收 2不接收	private Integer isLinkmanReceive;
	private Integer isLinkmanTwo;
	private Integer isLinkmanThree;
	//组织单位负责人是否接收预警 1接收 2不接收
	private Integer isOrgPrincipal;
	//组织单位联系人是否接收预警 1接收 2不接收
	private Integer isOrgLinkman;
	private Integer orgLinkmanTwo;
	private Integer orgLinkmanThree;
	
		//是否接收首页连不通实时预警 1接收 2不接收	private Integer homeConnection;
	//是否接收首页连不通超过3%预警 1接收 2不接收	private Integer homeConnectionPer;
	//是否接收严重错别字预警 1接收 2不接收	private Integer correctContent;
	//是否接收网站疑似被挂码或被篡改预警 1接收 2不接收	private Integer modifySite;
	//是否接收空白栏目超过5个预警 1接收 2不接收	private Integer blankChannel;
	//是否接收超过10个栏目不更新预警 1接收 2不接收	private Integer notUpdateChannel;
	//是否接收首页超过10天不更新预警 1接收 2不接收	private Integer notUpdateHome;
	//是否接收互动回应差栏目超过三个月未回应预警 1接收 2不接收	private Integer securityResponse;
	//网站是否接收预警  1接收 2不接收 3直接收本级门户预警	private Integer isSiteReceive;
	//下属所有网站预警时是否通知组织单位 1通知 2不通知	private Integer isNextAllSite;
	//用户id	private Integer userId;
	//创建时间	private Date createTime;
	//修改时间	private Date modifyTime;

	
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

	/** set 预警配置详情表id */	public void setId(Integer id){
		this.id=id;
	}
	/** get 预警配置详情表id */	public Integer getId(){
		return id;
	}
	/** set 网站标识码 */	public void setSiteCode(String siteCode){
		this.siteCode=siteCode;
	}
	/** get 网站标识码 */	public String getSiteCode(){
		return siteCode;
	}
	/** set 预警设置类型(仅组织单位有)：1站群日报设置，2单位预警设置 */	public void setEarlyType(Integer earlyType){
		this.earlyType=earlyType;
	}
	/** get 预警设置类型(仅组织单位有)：1站群日报设置，2单位预警设置 */	public Integer getEarlyType(){
		return earlyType;
	}
	/** set 是否接收微信预警 1接收 2不接收 */	public void setIsWxReceive(Integer isWxReceive){
		this.isWxReceive=isWxReceive;
	}
	/** get 是否接收微信预警 1接收 2不接收 */	public Integer getIsWxReceive(){
		return isWxReceive;
	}
	/** set 是否接收邮件预警 1接收 2不接收 */	public void setIsEmailReceive(Integer isEmailReceive){
		this.isEmailReceive=isEmailReceive;
	}
	/** get 是否接收邮件预警 1接收 2不接收 */	public Integer getIsEmailReceive(){
		return isEmailReceive;
	}
	/** set 是否接收短信预警 1接收 2不接收 */	public void setIsTelReceive(Integer isTelReceive){
		this.isTelReceive=isTelReceive;
	}
	/** get 是否接收短信预警 1接收 2不接收 */	public Integer getIsTelReceive(){
		return isTelReceive;
	}
	/** set 是否接收日报（仅组织单位有）  1接收 2不接收 */	public void setIsDailyReceive(Integer isDailyReceive){
		this.isDailyReceive=isDailyReceive;
	}
	/** get 是否接收日报（仅组织单位有）  1接收 2不接收 */	public Integer getIsDailyReceive(){
		return isDailyReceive;
	}
	/** set 负责人是否接收预警 1接收 2不接收 */	public void setIsPrincipalReceive(Integer isPrincipalReceive){
		this.isPrincipalReceive=isPrincipalReceive;
	}
	/** get 负责人是否接收预警 1接收 2不接收 */	public Integer getIsPrincipalReceive(){
		return isPrincipalReceive;
	}
	/** set 联系人是否接收预警 1接收 2不接收 */	public void setIsLinkmanReceive(Integer isLinkmanReceive){
		this.isLinkmanReceive=isLinkmanReceive;
	}
	/** get 联系人是否接收预警 1接收 2不接收 */	public Integer getIsLinkmanReceive(){
		return isLinkmanReceive;
	}
	/** set 是否接收首页连不通实时预警 1接收 2不接收 */	public void setHomeConnection(Integer homeConnection){
		this.homeConnection=homeConnection;
	}
	/** get 是否接收首页连不通实时预警 1接收 2不接收 */	public Integer getHomeConnection(){
		return homeConnection;
	}
	/** set 是否接收首页连不通超过3%预警 1接收 2不接收 */	public void setHomeConnectionPer(Integer homeConnectionPer){
		this.homeConnectionPer=homeConnectionPer;
	}
	/** get 是否接收首页连不通超过3%预警 1接收 2不接收 */	public Integer getHomeConnectionPer(){
		return homeConnectionPer;
	}
	/** set 是否接收严重错别字预警 1接收 2不接收 */	public void setCorrectContent(Integer correctContent){
		this.correctContent=correctContent;
	}
	/** get 是否接收严重错别字预警 1接收 2不接收 */	public Integer getCorrectContent(){
		return correctContent;
	}
	/** set 是否接收网站疑似被挂码或被篡改预警 1接收 2不接收 */	public void setModifySite(Integer modifySite){
		this.modifySite=modifySite;
	}
	/** get 是否接收网站疑似被挂码或被篡改预警 1接收 2不接收 */	public Integer getModifySite(){
		return modifySite;
	}
	/** set 是否接收空白栏目超过5个预警 1接收 2不接收 */	public void setBlankChannel(Integer blankChannel){
		this.blankChannel=blankChannel;
	}
	/** get 是否接收空白栏目超过5个预警 1接收 2不接收 */	public Integer getBlankChannel(){
		return blankChannel;
	}
	/** set 是否接收超过10个栏目不更新预警 1接收 2不接收 */	public void setNotUpdateChannel(Integer notUpdateChannel){
		this.notUpdateChannel=notUpdateChannel;
	}
	/** get 是否接收超过10个栏目不更新预警 1接收 2不接收 */	public Integer getNotUpdateChannel(){
		return notUpdateChannel;
	}
	/** set 是否接收首页超过10天不更新预警 1接收 2不接收 */	public void setNotUpdateHome(Integer notUpdateHome){
		this.notUpdateHome=notUpdateHome;
	}
	/** get 是否接收首页超过10天不更新预警 1接收 2不接收 */	public Integer getNotUpdateHome(){
		return notUpdateHome;
	}
	/** set 是否接收互动回应差栏目超过三个月未回应预警 1接收 2不接收 */	public void setSecurityResponse(Integer securityResponse){
		this.securityResponse=securityResponse;
	}
	/** get 是否接收互动回应差栏目超过三个月未回应预警 1接收 2不接收 */	public Integer getSecurityResponse(){
		return securityResponse;
	}
	/** set 网站是否接收预警  1接收 2不接收 */	public void setIsSiteReceive(Integer isSiteReceive){
		this.isSiteReceive=isSiteReceive;
	}
	/** get 网站是否接收预警  1接收 2不接收 */	public Integer getIsSiteReceive(){
		return isSiteReceive;
	}

	/** set 下属所有网站预警时是否通知组织单位 1通知 2不通知 */	public void setIsNextAllSite(Integer isNextAllSite){
		this.isNextAllSite=isNextAllSite;
	}
	/** get 下属所有网站预警时是否通知组织单位 1通知 2不通知 */	public Integer getIsNextAllSite(){
		return isNextAllSite;
	}
	/** set 用户id */	public void setUserId(Integer userId){
		this.userId=userId;
	}
	/** get 用户id */	public Integer getUserId(){
		return userId;
	}
	/** set 创建时间 */	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}
	/** get 创建时间 */	public Date getCreateTime(){
		return createTime;
	}
	/** set 修改时间 */	public void setModifyTime(Date modifyTime){
		this.modifyTime=modifyTime;
	}
	/** get 修改时间 */	public Date getModifyTime(){
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
}

