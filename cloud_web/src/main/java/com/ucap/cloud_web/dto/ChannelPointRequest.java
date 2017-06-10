package com.ucap.cloud_web.dto;

import com.publics.util.page.Query;

/**
 * 前台页面传递基础数据<br>
 * <b>作者：</b>Nora<br>
 * <b>日期：</b> 2015-10-29 11:19:32 <br>
 * <b>版权所有：<b>版权所有(C) 2015<br>
 */
@SuppressWarnings("serial")
public class ChannelPointRequest extends Query {
	// 网站标识码
	private String siteCode;

	// 站点信息id
	private Integer websiteInfoId;
	
	//监测状态    监测中：1，未监测：0 标记删除-1
	private Integer status;
	
	private Integer dicChannelId;
	private Integer dicChannelCount;
	
	private String statusIn;
	//子类集合
	private String dicChannelSonIdList;
	
	//用于标记查询的是正常数据还是删除数据  该字段为不为空，只查询监测中和未检测数据
	private String statusFlag;

	// md5加密后url(先jumpPageUrl加密，没有加密channelUrl)
	private String encodeUrl;

	// 栏目url
	private String channelUrl;

	/**
	 * 过滤条件
	 */
	private String term;

	/**
	 * 集合封装参数，sql通过in查询
	 */
	private String[] types;
	/**
	 * 对数据库中查询出的数据封装
	 */
	private Integer pointChannelSum;
	private Integer unUpdateSum;
	private String returnTime;// YYYY-MM
	private String siteName;
	private String jumpPageUrl;
	private String homePageUrl;

	/**
	 * 栏目id数组
	 */
	private Object[] channelPointIdArray;
	/**
	 * 0业务系统连通性  1关键栏目连通性
	 */
	private Integer channelType;
	
	public String getDicChannelSonIdList() {
		return dicChannelSonIdList;
	}

	public void setDicChannelSonIdList(String dicChannelSonIdList) {
		this.dicChannelSonIdList = dicChannelSonIdList;
	}

	public Integer getDicChannelId() {
		return dicChannelId;
	}

	public void setDicChannelId(Integer dicChannelId) {
		this.dicChannelId = dicChannelId;
	}

	public Integer getDicChannelCount() {
		return dicChannelCount;
	}

	public void setDicChannelCount(Integer dicChannelCount) {
		this.dicChannelCount = dicChannelCount;
	}

	public String getStatusIn() {
		return statusIn;
	}

	public void setStatusIn(String statusIn) {
		this.statusIn = statusIn;
	}

	public String getStatusFlag() {
		return statusFlag;
	}

	public void setStatusFlag(String statusFlag) {
		this.statusFlag = statusFlag;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Object[] getChannelPointIdArray() {
		return channelPointIdArray;
	}

	public void setChannelPointIdArray(Object[] channelPointIdArray) {
		this.channelPointIdArray = channelPointIdArray;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	public Integer getPointChannelSum() {
		return pointChannelSum;
	}

	public void setPointChannelSum(Integer pointChannelSum) {
		this.pointChannelSum = pointChannelSum;
	}

	public String getReturnTime() {
		return returnTime;
	}

	public void setReturnTime(String returnTime) {
		this.returnTime = returnTime;
	}

	public Integer getWebsiteInfoId() {
		return websiteInfoId;
	}

	public void setWebsiteInfoId(Integer websiteInfoId) {
		this.websiteInfoId = websiteInfoId;
	}

	public String getEncodeUrl() {
		return encodeUrl;
	}

	public void setEncodeUrl(String encodeUrl) {
		this.encodeUrl = encodeUrl;
	}

	public Integer getUnUpdateSum() {
		return unUpdateSum;
	}

	public void setUnUpdateSum(Integer unUpdateSum) {
		this.unUpdateSum = unUpdateSum;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
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

	public String[] getTypes() {
		return types;
	}

	public void setTypes(String[] types) {
		this.types = types;
	}

	public String getChannelUrl() {
		return channelUrl;
	}

	public void setChannelUrl(String channelUrl) {
		this.channelUrl = channelUrl;
	}

	public Integer getChannelType() {
		return channelType;
	}

	public void setChannelType(Integer channelType) {
		this.channelType = channelType;
	}

}
