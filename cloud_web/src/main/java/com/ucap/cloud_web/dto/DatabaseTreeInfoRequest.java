package com.ucap.cloud_web.dto;

import com.publics.util.page.Query;

/**
 * 前台页面传递基础数据<br>
 * <b>作者：</b>liujc<br>
 * <b>日期：</b> 2016-12-06 14:26:52 <br>
 * <b>版权所有：<b>版权所有(C) 2016<br>
 */
@SuppressWarnings("serial")
public class DatabaseTreeInfoRequest extends Query {

	private Integer id;
	// 父类id
	private Integer parentId;
	// 是否组织单位编码
	private String isOrg;
	// 标识码
	private String siteCode;
	//siteCode 长度
	private Integer siteCodeLength;
	//code like查询
	private String codeLike;
	private Integer level;
	
	private String typeIn;
	// 
	private Integer isScan;
	
	private String code;
	
	//关键字  筛选
	private String keyWord;
	private Integer type;//1 省市县  2部委

	/******** 新表字段 ***********/
	private Integer isBm; // 是否部委 1:部委 0：非部委
	private Integer isBigdata; // 是否大数据使用：1.是 2.否
	private Integer isLink; // 是否link使用：1.是 2.否
	private Integer isExclusive; // 是否专属页面使用：1.是 2.否
	private String exclusiveCode; // 专属code(空：无专属页面)
	private String orgSiteCode; // 组织单位编码
	private Integer dependType; // 主从挂接关系；1.主； 2.从
	private Integer layerType; // 层级类型；（-1默认无用,1本级门户，2本级部门，3下属单位，6其他）
	private Integer isexp; // 1正常，2例外，3关停
	private String name; // 名称
	private Integer isorganizational;
	
	
	
	//层级类型数组
	private String[] layerTypeArr;
	
	public String[] getLayerTypeArr() {
		return layerTypeArr;
	}

	public void setLayerTypeArr(String[] layerTypeArr) {
		this.layerTypeArr = layerTypeArr;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getIsOrg() {
		return isOrg;
	}

	public void setIsOrg(String isOrg) {
		this.isOrg = isOrg;
	}

	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}
	public Integer getSiteCodeLength() {
		return siteCodeLength;
	}

	public void setSiteCodeLength(Integer siteCodeLength) {
		this.siteCodeLength = siteCodeLength;
	}

	public Integer getIsexp() {
		return isexp;
	}

	public void setIsexp(Integer isexp) {
		this.isexp = isexp;
	}

	public Integer getIsorganizational() {
		return isorganizational;
	}

	public void setIsorganizational(Integer isorganizational) {
		this.isorganizational = isorganizational;
	}

	public String getCodeLike() {
		return codeLike;
	}

	public void setCodeLike(String codeLike) {
		this.codeLike = codeLike;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getIsBm() {
		return isBm;
	}

	public void setIsBm(Integer isBm) {
		this.isBm = isBm;
	}

	public Integer getIsScan() {
		return isScan;
	}

	public void setIsScan(Integer isScan) {
		this.isScan = isScan;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public String getOrgSiteCode() {
		return orgSiteCode;
	}

	public void setOrgSiteCode(String orgSiteCode) {
		this.orgSiteCode = orgSiteCode;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getIsLink() {
		return isLink;
	}

	public void setIsLink(Integer isLink) {
		this.isLink = isLink;
	}

	public Integer getDependType() {
		return dependType;
	}

	public void setDependType(Integer dependType) {
		this.dependType = dependType;
	}

	public Integer getIsBigdata() {
		return isBigdata;
	}

	public void setIsBigdata(Integer isBigdata) {
		this.isBigdata = isBigdata;
	}

	public String getExclusiveCode() {
		return exclusiveCode;
	}

	public void setExclusiveCode(String exclusiveCode) {
		this.exclusiveCode = exclusiveCode;
	}

	public Integer getLayerType() {
		return layerType;
	}

	public void setLayerType(Integer layerType) {
		this.layerType = layerType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getIsExclusive() {
		return isExclusive;
	}

	public void setIsExclusive(Integer isExclusive) {
		this.isExclusive = isExclusive;
	}

	public String getTypeIn() {
		return typeIn;
	}

	public void setTypeIn(String typeIn) {
		this.typeIn = typeIn;
	}
	
	
}
