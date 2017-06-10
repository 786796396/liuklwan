package com.ucap.cloud_web.dto;

import java.util.List;

import com.publics.util.page.Query;
import com.ucap.cloud_web.entity.DatabaseInfo;
import com.ucap.cloud_web.entity.DatabaseLink;

/**
 * 前台页面传递基础数据<br>
 * <b>作者：</b>SunJiang<br>
 * <b>日期：</b> 2015-12-03 15:22:56 <br>
 * <b>版权所有：<b>版权所有(C) 2015<br>
 */
@SuppressWarnings("serial")
public class SecurityHomeChannelRequest extends Query {
	/**
	 * 网站标识码
	 */
	private String siteCode;
	/**
	 * 首页不更新个数
	 */
	private Integer homeNum;
	/**
	 * 栏目信息不更新个数
	 */
	private Integer channelNum;
	/**
	 * 空白栏目的个数
	 */
	private Integer blankChannel;
	/**
	 * 互动回应差的个数
	 */
	private Integer securityResponse;
	/**
	 * 服务不实用的个数
	 */
	private Integer securityService;
	/**
	 * 扫描时间
	 */
	private String scanDate;

	/**
	 * 小于扫描时间的所有数据
	 */
	private String scanDateLow;

	/**
	 * sitecode数组
	 */
	private List<Object> ids;
	/**
	 * 栏目类型数组
	 */
	private String[] channelTypes;
	/**
	 * 关键字查询
	 */
	private String terms;
	/**
	 * 开始时间
	 */
	private String beginScanDate;
	/**
	 * 结束时间
	 */
	private String endScanDate;
	/**
	 * 更新时间
	 */
	private String updateTime;
	/**
	 * 首页未更新天数
	 */
	private Integer notUpdateDays;
	/**
	 * 网站名称
	 */
	private String siteName;
	/**
	 * 栏目未更新数量
	 */
	private Integer unUpdateSum;
	/**
	 * 跳转URL
	 */
	private String jumpPageUrl;
	/**
	 * 首页URL
	 */
	private String homePageUrl;

	/**
	 * 类型（1首页，2栏目）
	 */
	private Integer type;
	/**
	 * 首页不跟新个数统计（查询数据库返回）
	 */
	private Integer homeNotUpdateNum;
	/**
	 * 栏目不更新数据统计（查询数据库返回）
	 */
	private Integer channelNorUpdateNum;

	private String director;// 主办单位

	private Integer isorganizational;// 是否主办单位

	// 全选控制字段
	private boolean flag;

	// 分组
	private String groupBy;

	private String selectType;

	private List<DatabaseInfo> databaseList;

	private List<DatabaseLink> databaseLinkList;

	// 非机器输入
	private String noAutoInput;

	// 机器输入
	private String autoInput;

	// 最后正常监测时间 add by Na.Y 20160817
	private String lastAccessDate;

	private Integer servicePeriodId;
	//基本信息个数
	private int basicNum;
	
	private String url;
	
	private Integer notUpdateNum;//首页未更新天数
	
	private String modifyDate;//最后更新时间
	
	private String imageUrl;//快照
	
	private String notUpSiteNum;//栏目更新不及时总数
	
	private String wxFlag;//老版---微信内容保障问题
	
	
	
	public String getWxFlag() {
		return wxFlag;
	}

	public void setWxFlag(String wxFlag) {
		this.wxFlag = wxFlag;
	}

	public String getNotUpSiteNum() {
		return notUpSiteNum;
	}

	public void setNotUpSiteNum(String notUpSiteNum) {
		this.notUpSiteNum = notUpSiteNum;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getNotUpdateNum() {
		return notUpdateNum;
	}

	public void setNotUpdateNum(Integer notUpdateNum) {
		this.notUpdateNum = notUpdateNum;
	}

	public int getBasicNum() {
		return basicNum;
	}

	public void setBasicNum(int basicNum) {
		this.basicNum = basicNum;
	}

	public String getLastAccessDate() {
		return lastAccessDate;
	}

	public void setLastAccessDate(String lastAccessDate) {
		this.lastAccessDate = lastAccessDate;
	}

	public String getAutoInput() {
		return autoInput;
	}

	public void setAutoInput(String autoInput) {
		this.autoInput = autoInput;
	}

	public String getNoAutoInput() {
		return noAutoInput;
	}

	public void setNoAutoInput(String noAutoInput) {
		this.noAutoInput = noAutoInput;
	}

	public String getScanDateLow() {
		return scanDateLow;
	}

	public void setScanDateLow(String scanDateLow) {
		this.scanDateLow = scanDateLow;
	}

	public List<DatabaseLink> getDatabaseLinkList() {
		return databaseLinkList;
	}

	public void setDatabaseLinkList(List<DatabaseLink> databaseLinkList) {
		this.databaseLinkList = databaseLinkList;
	}

	public List<DatabaseInfo> getDatabaseList() {
		return databaseList;
	}

	public void setDatabaseList(List<DatabaseInfo> databaseList) {
		this.databaseList = databaseList;
	}

	public String getSelectType() {
		return selectType;
	}

	public void setSelectType(String selectType) {
		this.selectType = selectType;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public Integer getIsorganizational() {
		return isorganizational;
	}

	public void setIsorganizational(Integer isorganizational) {
		this.isorganizational = isorganizational;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public Integer getHomeNotUpdateNum() {
		return homeNotUpdateNum;
	}

	public void setHomeNotUpdateNum(Integer homeNotUpdateNum) {
		this.homeNotUpdateNum = homeNotUpdateNum;
	}

	public Integer getChannelNorUpdateNum() {
		return channelNorUpdateNum;
	}

	public void setChannelNorUpdateNum(Integer channelNorUpdateNum) {
		this.channelNorUpdateNum = channelNorUpdateNum;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getBeginScanDate() {
		return beginScanDate;
	}

	public void setBeginScanDate(String beginScanDate) {
		this.beginScanDate = beginScanDate;
	}

	public String getEndScanDate() {
		return endScanDate;
	}

	public void setEndScanDate(String endScanDate) {
		this.endScanDate = endScanDate;
	}

	public List<Object> getIds() {
		return ids;
	}

	public void setIds(List<Object> ids) {
		this.ids = ids;
	}

	public String getScanDate() {
		return scanDate;
	}

	public void setScanDate(String scanDate) {
		this.scanDate = scanDate;
	}

	public Integer getHomeNum() {
		return homeNum;
	}

	public void setHomeNum(Integer homeNum) {
		this.homeNum = homeNum;
	}

	public Integer getChannelNum() {
		return channelNum;
	}

	public void setChannelNum(Integer channelNum) {
		this.channelNum = channelNum;
	}

	public Integer getBlankChannel() {
		return blankChannel;
	}

	public void setBlankChannel(Integer blankChannel) {
		this.blankChannel = blankChannel;
	}

	public Integer getSecurityResponse() {
		return securityResponse;
	}

	public void setSecurityResponse(Integer securityResponse) {
		this.securityResponse = securityResponse;
	}

	public Integer getSecurityService() {
		return securityService;
	}

	public void setSecurityService(Integer securityService) {
		this.securityService = securityService;
	}

	public String[] getChannelTypes() {
		return channelTypes;
	}

	public void setChannelTypes(String[] channelTypes) {
		this.channelTypes = channelTypes;
	}

	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	public String getTerms() {
		return terms;
	}

	public void setTerms(String terms) {
		this.terms = terms;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getNotUpdateDays() {
		return notUpdateDays;
	}

	public void setNotUpdateDays(Integer notUpdateDays) {
		this.notUpdateDays = notUpdateDays;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public Integer getUnUpdateSum() {
		return unUpdateSum;
	}

	public void setUnUpdateSum(Integer unUpdateSum) {
		this.unUpdateSum = unUpdateSum;
	}

	public String getJumpPageUrl() {
		return jumpPageUrl;
	}

	public void setJumpPageUrl(String jumpPageUrl) {
		this.jumpPageUrl = jumpPageUrl;
	}

	public String getHomePageUrl() {
		return homePageUrl;
	}

	public void setHomePageUrl(String homePageUrl) {
		this.homePageUrl = homePageUrl;
	}

	public String getGroupBy() {
		return groupBy;
	}

	public void setGroupBy(String groupBy) {
		this.groupBy = groupBy;
	}

	public Integer getServicePeriodId() {
		return servicePeriodId;
	}

	public void setServicePeriodId(Integer servicePeriodId) {
		this.servicePeriodId = servicePeriodId;
	}

}
