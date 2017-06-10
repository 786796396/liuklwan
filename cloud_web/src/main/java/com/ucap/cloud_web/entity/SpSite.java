package com.ucap.cloud_web.entity;


import java.util.Date;


/*** <br>* <b>作者：</b>liujc<br>* <b>日期：</b> 2016-11-22 10:30:41 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public class SpSite {


	//主键	private Integer id;
	//站点名称	private String siteName;
	//标识码	private String siteCode;
	//域名	private String domain;
	//链接地址	private String url;
	//站点显示名称	private String siteShowName;
	//站点关键词	private String siteKeyword;
	//站点描述	private String siteDescript;
	//logo路径	private String logo;
	//服务热线	private String serviceHottel;
	//底部文字	private String bottomText;
	//删除标识（0：正常，1：删除）	private Integer delFlag;
	//状态（0：未开通，1：开通，2：停用）	private Integer status;
	//添加人	private Integer creater;
	//添加时间	private Date createTime;
	//修改人	private Integer modifyer;
	//修改时间	private Date modifyTime;
	
	//模板类型（1:模板A,2:模板B）
	private Integer templateType;
	
	//显示模块（1:日常检测,2:网站概况,3:栏目,4:大数据,5:政府网站基础信息数据库,6:政府网站网民找错数据）
	private String displayModule;
	
	//登陆配置（1:允许全国范围内网站登陆,2:允许自身挂接关系范围内网站登陆）
	private Integer loginConfig;
	
	//生效状态（1:生效,2:无效）
	private Integer effectiveState;
	
	//添加来源（1:前台,2:后台）
	private Integer addFrom;
	
	//有效开始日期
	private String effectiveBeginDate;
	
	//有效结束日期
	private String effectiveEndDate;

	
	
	
	
	
	
	
	
	
		public Integer getTemplateType() {
		return templateType;
	}

	public void setTemplateType(Integer templateType) {
		this.templateType = templateType;
	}

	public String getDisplayModule() {
		return displayModule;
	}

	public void setDisplayModule(String displayModule) {
		this.displayModule = displayModule;
	}

	public Integer getLoginConfig() {
		return loginConfig;
	}

	public void setLoginConfig(Integer loginConfig) {
		this.loginConfig = loginConfig;
	}

	public Integer getEffectiveState() {
		return effectiveState;
	}

	public void setEffectiveState(Integer effectiveState) {
		this.effectiveState = effectiveState;
	}

	public Integer getAddFrom() {
		return addFrom;
	}

	public void setAddFrom(Integer addFrom) {
		this.addFrom = addFrom;
	}

	public String getEffectiveBeginDate() {
		return effectiveBeginDate;
	}

	public void setEffectiveBeginDate(String effectiveBeginDate) {
		this.effectiveBeginDate = effectiveBeginDate;
	}

	public String getEffectiveEndDate() {
		return effectiveEndDate;
	}

	public void setEffectiveEndDate(String effectiveEndDate) {
		this.effectiveEndDate = effectiveEndDate;
	}

	/** set 主键 */	public void setId(Integer id){
		this.id=id;
	}
	/** get 主键 */	public Integer getId(){
		return id;
	}
	/** set 站点名称 */	public void setSiteName(String siteName){
		this.siteName=siteName;
	}
	/** get 站点名称 */	public String getSiteName(){
		return siteName;
	}
	/** set 标识码 */	public void setSiteCode(String siteCode){
		this.siteCode=siteCode;
	}
	/** get 标识码 */	public String getSiteCode(){
		return siteCode;
	}
	/** set 域名 */	public void setDomain(String domain){
		this.domain=domain;
	}
	/** get 域名 */	public String getDomain(){
		return domain;
	}
	/** set 链接地址 */	public void setUrl(String url){
		this.url=url;
	}
	/** get 链接地址 */	public String getUrl(){
		return url;
	}
	/** set 站点显示名称 */	public void setSiteShowName(String siteShowName){
		this.siteShowName=siteShowName;
	}
	/** get 站点显示名称 */	public String getSiteShowName(){
		return siteShowName;
	}
	/** set 站点关键词 */	public void setSiteKeyword(String siteKeyword){
		this.siteKeyword=siteKeyword;
	}
	/** get 站点关键词 */	public String getSiteKeyword(){
		return siteKeyword;
	}
	/** set 站点描述 */	public void setSiteDescript(String siteDescript){
		this.siteDescript=siteDescript;
	}
	/** get 站点描述 */	public String getSiteDescript(){
		return siteDescript;
	}
	/** set logo路径 */	public void setLogo(String logo){
		this.logo=logo;
	}
	/** get logo路径 */	public String getLogo(){
		return logo;
	}
	/** set 服务热线 */	public void setServiceHottel(String serviceHottel){
		this.serviceHottel=serviceHottel;
	}
	/** get 服务热线 */	public String getServiceHottel(){
		return serviceHottel;
	}
	/** set 底部文字 */	public void setBottomText(String bottomText){
		this.bottomText=bottomText;
	}
	/** get 底部文字 */	public String getBottomText(){
		return bottomText;
	}
	/** set 删除标识（0：正常，1：删除） */	public void setDelFlag(Integer delFlag){
		this.delFlag=delFlag;
	}
	/** get 删除标识（0：正常，1：删除） */	public Integer getDelFlag(){
		return delFlag;
	}
	/** set 状态（0：未开通，1：开通，2：停用） */	public void setStatus(Integer status){
		this.status=status;
	}
	/** get 状态（0：未开通，1：开通，2：停用） */	public Integer getStatus(){
		return status;
	}
	/** set 添加人 */	public void setCreater(Integer creater){
		this.creater=creater;
	}
	/** get 添加人 */	public Integer getCreater(){
		return creater;
	}
	/** set 添加时间 */	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}
	/** get 添加时间 */	public Date getCreateTime(){
		return createTime;
	}
	/** set 修改人 */	public void setModifyer(Integer modifyer){
		this.modifyer=modifyer;
	}
	/** get 修改人 */	public Integer getModifyer(){
		return modifyer;
	}
	/** set 修改时间 */	public void setModifyTime(Date modifyTime){
		this.modifyTime=modifyTime;
	}
	/** get 修改时间 */	public Date getModifyTime(){
		return modifyTime;
	}
}

