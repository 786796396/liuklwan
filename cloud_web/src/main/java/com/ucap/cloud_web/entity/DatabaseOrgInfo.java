package com.ucap.cloud_web.entity;


import java.util.Date;


/*** <br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2016-03-17 18:11:30 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public class DatabaseOrgInfo {


	//主键	private int id;
	//网站名称	private String name;
	//网站标识码	private String siteCode;
	//网站校验码	private String vcode;
	//校验码2	private String vcode2;
	
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
	//创建时间	private Date createTime;
	
	//修改时间
	private Date modifyTime;
	
	private Integer guideState;
		public String getPrincipalName() {
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

	/** set 主键 */	public void setId(int id){
		this.id=id;
	}
	/** get 主键 */	public int getId(){
		return id;
	}
	/** set 网站名称 */	public void setName(String name){
		this.name=name;
	}
	/** get 网站名称 */	public String getName(){
		return name;
	}
	/** set 网站标识码 */	public void setSiteCode(String siteCode){
		this.siteCode=siteCode;
	}
	/** get 网站标识码 */	public String getSiteCode(){
		return siteCode;
	}
	/** set 网站校验码 */	public void setVcode(String vcode){
		this.vcode=vcode;
	}
	/** get 网站校验码 */	public String getVcode(){
		return vcode;
	}
	/** set 校验码2 */	public void setVcode2(String vcode2){
		this.vcode2=vcode2;
	}
	/** get 校验码2 */	public String getVcode2(){
		return vcode2;
	}
	/** set 创建时间 */	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}
	/** get 创建时间 */	public Date getCreateTime(){
		return createTime;
	}

	public Integer getGuideState() {
		return guideState;
	}

	public void setGuideState(Integer guideState) {
		this.guideState = guideState;
	}
}

