package com.ucap.cloud_web.entity;


import java.util.Date;


/*** <br>* <b>作者：</b>liujc<br>* <b>日期：</b> 2016-09-02 16:14:53 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public class SecurityStatus {


	//主键ID	private Integer id;
	//网站标示码	private String siteCode;
	//扫描类别（1：安全扫描   2：全站死链扫描）	private Integer type;
	//状态:(1:未监测  2:监测中  3:已完成)	private Integer status;
	//是否已读（1:未读  2：已读，  默认1）	private Integer isReaded;
	//是否删除（0：正常，1：删除；默认0）	private Integer delFlag;
	//创建时间	private Date createTime;
	//修改时间	private Date modifyTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getIsReaded() {
		return isReaded;
	}

	public void setIsReaded(Integer isReaded) {
		this.isReaded = isReaded;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
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
	
}

