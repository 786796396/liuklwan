package com.ucap.cloud_web.dto;

import java.util.List;

import com.publics.util.page.Query;
import com.ucap.cloud_web.entity.DatabaseLink;
import com.ucap.cloud_web.entity.DatabaseTreeInfo;
import com.ucap.cloud_web.entity.RelationsPeriod;

/**
 * 前台页面传递基础数据<br>
 * <b>作者：</b>kefan<br>
 * <b>日期：</b> 2015-11-16 10:54:39 <br>
 * <b>版权所有：<b>版权所有(C) 2015<br>
 */
@SuppressWarnings("serial")
public class DatabaseInfoRequest extends Query {

	private Integer id;
	private String level;

	private String siteCodeLike;

	private String parentIdLike;

	private String parentId;

	private Integer isorganizational;

	private Integer notLevel;

	private String vcode;
	
	private String code;

	// 排序
	private String groupBy;

	// 过滤地税
	private String notLikeSiteCode;

	// 地市
	private Object[] siteCodeLists;

	// 网站标识码
	private String siteCode;

	// 状态（0不连通，1连通）
	private Integer linkStatus;
	
	
	//1正常，2例外，3关停
	private Integer isexp;
	
	private Integer isScan;
	
	//省、市、县组织单位编码
	private String orgCode;
	
	//省、市、县组织单位的站点个数
	private Integer spotCount;
	
	// 省
	private String province;
	
	
	//联系人是否接收邮箱（0：否，1：是
	private Integer emailReceive2;
	
	private List<RelationsPeriod> relationsPeriodList;
	
	private String keyWord;//全面监测-关键字查询--查询网站名称
	
	private String keyWordOrisexp;//全面监测-关键字查询--查询网站名称
	
	private List<DatabaseLink> databaseLinkList;//关联表数据
	
	/*站点数据概览 start*/
	private String name;//站点名称
	private Integer normalNum;//正常站点
	private Integer shutDownNum;//关停站点数
	private Integer excepNum;//例外站点数
	private Integer isScanNum;//监测站点数
	private Integer noScanNum;//异常站点数
	private Integer noEqIsScan;//非扫描中站点
	/*站点数据概览 end*/
	private String siteCodeBM;
	private String siteCodeNoBM;
	//通讯录模糊查询：主办单位，联系人或负责人姓名模糊查询
	private String contractsQuery;
	
	//类型 1本机门户 2本机部门 3下属单位...
	private Integer type;
	
	//组织单位siteCode
    private String orgSiteCode;
    
	private Integer isLink;
	
	private Integer isBigdata;
	// 搜索条件
	private String queryInput;
	// 类型
	private String typeVal;

	private Integer paging;

    private List<DatabaseTreeInfo> databaseTreeList;
    
	public List<DatabaseTreeInfo> getDatabaseTreeList() {
		return databaseTreeList;
	}
	public void setDatabaseTreeList(List<DatabaseTreeInfo> databaseTreeList) {
		this.databaseTreeList = databaseTreeList;
	}
	public String getOrgSiteCode() {
		return orgSiteCode;
	}
	public void setOrgSiteCode(String orgSiteCode) {
		this.orgSiteCode = orgSiteCode;
	}
	public String getSiteCodeBM() {
		return siteCodeBM;
	}
	public void setSiteCodeBM(String siteCodeBM) {
		this.siteCodeBM = siteCodeBM;
	}
	public String getSiteCodeNoBM() {
		return siteCodeNoBM;
	}
	public void setSiteCodeNoBM(String siteCodeNoBM) {
		this.siteCodeNoBM = siteCodeNoBM;
	}
	public String getContractsQuery() {
		return contractsQuery;
	}
	public void setContractsQuery(String contractsQuery) {
		this.contractsQuery = contractsQuery;
	}
	public Integer getShutDownNum() {
		return shutDownNum;
	}
	public List<DatabaseLink> getDatabaseLinkList() {
		return databaseLinkList;
	}


	public void setDatabaseLinkList(List<DatabaseLink> databaseLinkList) {
		this.databaseLinkList = databaseLinkList;
	}


	public String getKeyWordOrisexp() {
		return keyWordOrisexp;
	}

	public void setKeyWordOrisexp(String keyWordOrisexp) {
		this.keyWordOrisexp = keyWordOrisexp;
	}

	public Integer getNoEqIsScan() {
		return noEqIsScan;
	}

	public void setNoEqIsScan(Integer noEqIsScan) {
		this.noEqIsScan = noEqIsScan;
	}

	public Integer getNormalNum() {
		return normalNum;
	}

	public void setNormalNum(Integer normalNum) {
		this.normalNum = normalNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setShutDownNum(Integer shutDownNum) {
		this.shutDownNum = shutDownNum;
	}

	public Integer getExcepNum() {
		return excepNum;
	}

	public void setExcepNum(Integer excepNum) {
		this.excepNum = excepNum;
	}

	public Integer getIsScanNum() {
		return isScanNum;
	}

	public void setIsScanNum(Integer isScanNum) {
		this.isScanNum = isScanNum;
	}

	public Integer getNoScanNum() {
		return noScanNum;
	}

	public void setNoScanNum(Integer noScanNum) {
		this.noScanNum = noScanNum;
	}

	public Integer getEmailReceive2() {
		return emailReceive2;
	}

	public void setEmailReceive2(Integer emailReceive2) {
		this.emailReceive2 = emailReceive2;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public Integer getSpotCount() {
		return spotCount;
	}

	public void setSpotCount(Integer spotCount) {
		this.spotCount = spotCount;
	}

	public Integer getIsScan() {
		return isScan;
	}

	public void setIsScan(Integer isScan) {
		this.isScan = isScan;
	}

	public String getVcode() {
		return vcode;
	}

	public void setVcode(String vcode) {
		this.vcode = vcode;
	}

	public Integer getNotLevel() {
		return notLevel;
	}

	public void setNotLevel(Integer notLevel) {
		this.notLevel = notLevel;
	}

	public String getParentIdLike() {
		return parentIdLike;
	}

	public void setParentIdLike(String parentIdLike) {
		this.parentIdLike = parentIdLike;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getSiteCodeLike() {
		return siteCodeLike;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setSiteCodeLike(String siteCodeLike) {
		this.siteCodeLike = siteCodeLike;
	}

	public Integer getIsorganizational() {
		return isorganizational;
	}

	public void setIsorganizational(Integer isorganizational) {
		this.isorganizational = isorganizational;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getGroupBy() {
		return groupBy;
	}

	public void setGroupBy(String groupBy) {
		this.groupBy = groupBy;
	}

	public String getNotLikeSiteCode() {
		return notLikeSiteCode;
	}

	public void setNotLikeSiteCode(String notLikeSiteCode) {
		this.notLikeSiteCode = notLikeSiteCode;
	}

	public Object[] getSiteCodeLists() {
		return siteCodeLists;
	}

	public void setSiteCodeLists(Object[] siteCodeLists) {
		this.siteCodeLists = siteCodeLists;
	}

	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	public Integer getLinkStatus() {
		return linkStatus;
	}

	public void setLinkStatus(Integer linkStatus) {
		this.linkStatus = linkStatus;
	}

	public Integer getIsexp() {
		return isexp;
	}

	public void setIsexp(Integer isexp) {
		this.isexp = isexp;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public List<RelationsPeriod> getRelationsPeriodList() {
		return relationsPeriodList;
	}

	public void setRelationsPeriodList(List<RelationsPeriod> relationsPeriodList) {
		this.relationsPeriodList = relationsPeriodList;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
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

	public String getQueryInput() {
		return queryInput;
	}

	public void setQueryInput(String queryInput) {
		this.queryInput = queryInput;
	}

	public String getTypeVal() {
		return typeVal;
	}

	public void setTypeVal(String typeVal) {
		this.typeVal = typeVal;
	}

	public Integer getPaging() {
		return paging;
	}

	public void setPaging(Integer paging) {
		this.paging = paging;
	}
	public String getCode()
	{
		return code;
	}
	public void setCode(String code)
	{
		this.code = code;
	}
	public Integer getIsBigdata()
	{
		return isBigdata;
	}
	public void setIsBigdata(Integer isBigdata)
	{
		this.isBigdata = isBigdata;
	}
	
}
