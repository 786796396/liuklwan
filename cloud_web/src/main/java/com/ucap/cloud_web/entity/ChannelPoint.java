package com.ucap.cloud_web.entity;

import java.util.Date;

/**
 * <br>
 * <b>作者：</b>Nora<br>
 * <b>日期：</b> 2015-10-29 11:19:32 <br>
 * <b>版权所有：<b>版权所有(C) 2015<br>
 */
public class ChannelPoint {

	// 重点栏目检测表id
	private int id;

	// 网站标识码
	private String siteCode;

	// 站点信息id
	private int websiteInfoId;

	// 类别id
	private int dicChannelId;

	// 子类id
	private int dicChannelSonId;

	// 栏目名称(手动输入)
	private String channelName;

	// 栏目url
	private String channelUrl;

	// 跳转url
	private String jumpPageUrl;

	// md5加密后url(先jumpPageUrl加密，没有加密channelUrl)
	private String encodeUrl;

	// 状态（监测中：1，未监测：0），取消监测将状态置为0 add by Na.Y 20151216
	private int status;
	
	// 连通状态连通状态（0：不连通，1：连通）
	private Integer linkStatus;

	// 区分为  绩效考核添加的数据（1，绩效考核 0，其他）
	private Integer paAdd;
	
	//创建人
	private String creator;
	
	//栏目创建时间
	private String created;
	
	//修改人
	private String modifier;
	
	//栏目修改时间
	private String modified;

	//手动改变监测状态标记 0表示 取消改动(由未监测改为监测) 1 表示前台将监测手动改为未监测
	private int lable;
	public int getLable() {
		return lable;
	}

	public void setLable(int lable) {
		this.lable = lable;
	}

	public Integer getPaAdd() {
		return paAdd;
	}

	public void setPaAdd(Integer paAdd) {
		this.paAdd = paAdd;
	}

	/**
	 * @return the linkStatus
	 */
	public Integer getLinkStatus() {
		return linkStatus;
	}

	/**
	 * @param linkStatus the linkStatus to set
	 */
	public void setLinkStatus(Integer linkStatus) {
		this.linkStatus = linkStatus;
	}

	// 创建时间
	private Date createTime;

	// 修改时间
	private Date modifyTime;

	/** set 重点栏目检测表id */
	public void setId(int id) {
		this.id = id;
	}

	/** get 重点栏目检测表id */
	public int getId() {
		return id;
	}

	/** set 网站标识码 */
	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	/** get 网站标识码 */
	public String getSiteCode() {
		return siteCode;
	}

	/** set 站点信息id */
	public void setWebsiteInfoId(int websiteInfoId) {
		this.websiteInfoId = websiteInfoId;
	}

	/** get 站点信息id */
	public int getWebsiteInfoId() {
		return websiteInfoId;
	}

	/** set 类别id */
	public void setDicChannelId(int dicChannelId) {
		this.dicChannelId = dicChannelId;
	}

	/** get 类别id */
	public int getDicChannelId() {
		return dicChannelId;
	}

	/** set 子类id */
	public void setDicChannelSonId(int dicChannelSonId) {
		this.dicChannelSonId = dicChannelSonId;
	}

	/** get 子类id */
	public int getDicChannelSonId() {
		return dicChannelSonId;
	}

	/** set 栏目名称(手动输入) */
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	/** get 栏目名称(手动输入) */
	public String getChannelName() {
		return channelName;
	}

	/** set 栏目url */
	public void setChannelUrl(String channelUrl) {
		this.channelUrl = channelUrl;
	}



	/** get 栏目url */
	public String getChannelUrl() {
		return channelUrl;
	}

	/** set 跳转url */
	public void setJumpPageUrl(String jumpPageUrl) {
		this.jumpPageUrl = jumpPageUrl;
	}


	/** get 跳转url */
	public String getJumpPageUrl() {
		if (null == jumpPageUrl) {
			return "";
		}
		return jumpPageUrl;
	}

	/** set 创建时间 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/** get 创建时间 */
	public Date getCreateTime() {
		return createTime;
	}

	/** set 修改时间 */
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	/** get 修改时间 */
	public Date getModifyTime() {
		return modifyTime;
	}

	public String getEncodeUrl() {
		return encodeUrl;
	}

	public void setEncodeUrl(String encodeUrl) {
		this.encodeUrl = encodeUrl;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ChannelPoint [id=" + id + ", siteCode=" + siteCode
				+ ", websiteInfoId=" + websiteInfoId + ", dicChannelId="
				+ dicChannelId + ", dicChannelSonId=" + dicChannelSonId
				+ ", channelName=" + channelName + ", channelUrl=" + channelUrl
				+ ", jumpPageUrl=" + jumpPageUrl + ", createTime=" + createTime
				+ ", modifyTime=" + modifyTime + ",creator=" + creator
				+",created="+created+",modifier="+modifier+",modified="+modified+"]";
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public String getModified() {
		return modified;
	}

	public void setModified(String modified) {
		this.modified = modified;
	}

}
