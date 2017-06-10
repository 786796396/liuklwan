package com.ucap.cloud_web.entity;


import java.util.Date;


/*** <br>* <b>作者：</b>liujc<br>* <b>日期：</b> 2016-12-06 14:26:52 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public class DatabaseTreeInfo {

	//网站名称	private String name;
	//0非门户，1门户	private Integer isorganizational;
	//1正常，2例外，3关停	private Integer isexp;
	
	//关停总数
	private Integer sumClose;
	
	//例外总数
	private Integer sumException;
	
	//暂不检测
	private Integer sumNo;
	
	//正常检测
	private Integer sumNormal;
	//上报总数
	private Integer sumAll;
	
	/** 新表字段 */
	// 主键
	private int id;

	// 父类id
	private int parentId;

	// 组织单位站点标识码
	private String orgSiteCode;

	// 填报单位编码
	private String siteCode;

	// 层级
	private int level;

	// 编码
	private String code;

	// 是否部委
	private int isBm;

	// 主从挂接关系；1.主； 2.从
	private Integer dependType;

	// 层级类型；（-1默认无用,1本级门户，2本级部门，3下属单位，6其他）
	private Integer layerType;

	// 是否大数据使用：1.是 2.否
	private Integer isBigdata;

	// 是否link使用：1.是 2.否
	private Integer isLink;

	// 是否专属页面使用：1.是 2.否
	private Integer isExclusive;

	// 专属code(空：无专属页面)
	private String exclusiveCode;

	// 创建时间
	private Date createTime;

	// 修改时间
	private Date modifyTime;
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getIsorganizational() {
		return isorganizational;
	}

	public void setIsorganizational(Integer isorganizational) {
		this.isorganizational = isorganizational;
	}

	public Integer getIsexp() {
		return isexp;
	}

	public void setIsexp(Integer isexp) {
		this.isexp = isexp;
	}

	public Integer getSumClose() {
		return sumClose;
	}

	public void setSumClose(Integer sumClose) {
		this.sumClose = sumClose;
	}

	public Integer getSumException() {
		return sumException;
	}

	public void setSumException(Integer sumException) {
		this.sumException = sumException;
	}

	public Integer getSumNo() {
		return sumNo;
	}

	public void setSumNo(Integer sumNo) {
		this.sumNo = sumNo;
	}

	public Integer getSumNormal() {
		return sumNormal;
	}

	public void setSumNormal(Integer sumNormal) {
		this.sumNormal = sumNormal;
	}

	public Integer getSumAll() {
		return sumAll;
	}

	public void setSumAll(Integer sumAll) {
		this.sumAll = sumAll;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getOrgSiteCode() {
		return orgSiteCode;
	}

	public void setOrgSiteCode(String orgSiteCode) {
		this.orgSiteCode = orgSiteCode;
	}

	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getIsBm() {
		return isBm;
	}

	public void setIsBm(int isBm) {
		this.isBm = isBm;
	}

	public Integer getDependType() {
		return dependType;
	}

	public void setDependType(Integer dependType) {
		this.dependType = dependType;
	}

	public Integer getLayerType() {
		return layerType;
	}

	public void setLayerType(Integer layerType) {
		this.layerType = layerType;
	}

	public Integer getIsBigdata() {
		return isBigdata;
	}

	public void setIsBigdata(Integer isBigdata) {
		this.isBigdata = isBigdata;
	}

	public Integer getIsLink() {
		return isLink;
	}

	public void setIsLink(Integer isLink) {
		this.isLink = isLink;
	}

	public Integer getIsExclusive() {
		return isExclusive;
	}

	public void setIsExclusive(Integer isExclusive) {
		this.isExclusive = isExclusive;
	}

	public String getExclusiveCode() {
		return exclusiveCode;
	}

	public void setExclusiveCode(String exclusiveCode) {
		this.exclusiveCode = exclusiveCode;
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

