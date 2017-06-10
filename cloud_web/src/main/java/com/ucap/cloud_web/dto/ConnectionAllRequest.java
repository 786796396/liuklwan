package com.ucap.cloud_web.dto;

import java.util.List;

import com.publics.util.page.Query;
import com.ucap.cloud_web.entity.DatabaseInfo;
import com.ucap.cloud_web.entity.DatabaseLink;


/**
 * 前台页面传递基础数据<br>
 * <b>作者：</b>Nora<br>
 * <b>日期：</b> 2015-10-29 11:19:33 <br>
 * <b>版权所有：<b>版权所有(C) 2015<br>
 */
@SuppressWarnings("serial")
public class ConnectionAllRequest extends Query {

	// 网站标识码
	private String siteCode;

	// 类型（1首页面连通性、2、业务系统连通性、3关键栏目连通性）
	private Integer type;
	//过滤条件
	private String term;
	
	// 扫描时间（yyyy-dd-mm）
	private String scanDate;
	//开始日期  （yyyy-dd-mm）
	private String startDate;
	//结束日期  （yyyy-dd-mm）
	private String endDate;
	
	private String systemName;
	private String url;
	
	private String errorProportion;
	
	/**
	 * 封装数据库中查询出来的数据
	 */
	private Integer errorNum;
	private Integer connectionSum;
	private Integer successNum;
	private String siteName;
	private String homePageUrl;
	private String jumpPageUrl;
	private Integer countSum;
	private Integer total;
	
	// 昨天时间
	private String yesterDayStr;
	// 昨天的前7天
	private String beforeSeven;
	
	
	/**
	 * list数组
	 */
	private List<Object> ids;
	
	private List<DatabaseInfo>  databaseList;
	
	private List<DatabaseLink> dataLinkList;
	
	//网站主办单位
	private String director;
	
	private Integer isorganizational;//是否门户	
	
	private boolean groupBy;//分组
	
	private boolean notConnFlag;//100%连不通标志
	
	//siteCode等号查询非like查询
	private String siteCodeEqual;	

	private Integer queue; // 监测频率
	
	private Integer channelSum;//栏目个数
	
	

	public Integer getChannelSum() {
		return channelSum;
	}
	public void setChannelSum(Integer channelSum) {
		this.channelSum = channelSum;
	}
	public String getSiteCodeEqual() {
		return siteCodeEqual;
	}
	public void setSiteCodeEqual(String siteCodeEqual) {
		this.siteCodeEqual = siteCodeEqual;
	}
	public boolean isNotConnFlag() {
		return notConnFlag;
	}
	public void setNotConnFlag(boolean notConnFlag) {
		this.notConnFlag = notConnFlag;
	}
	public String getYesterDayStr() {
		return yesterDayStr;
	}
	public void setYesterDayStr(String yesterDayStr) {
		this.yesterDayStr = yesterDayStr;
	}
	public String getBeforeSeven() {
		return beforeSeven;
	}
	public void setBeforeSeven(String beforeSeven) {
		this.beforeSeven = beforeSeven;
	}

	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public String getErrorProportion() {
		return errorProportion;
	}
	public void setErrorProportion(String errorProportion) {
		this.errorProportion = errorProportion;
	}
	public List<DatabaseLink> getDataLinkList() {
		return dataLinkList;
	}
	public void setDataLinkList(List<DatabaseLink> dataLinkList) {
		this.dataLinkList = dataLinkList;
	}
	public String getSystemName() {
		return systemName;
	}
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getSuccessNum() {
		return successNum;
	}
	public void setSuccessNum(Integer successNum) {
		this.successNum = successNum;
	}
	public List<DatabaseInfo> getDatabaseList() {
		return databaseList;
	}
	public void setDatabaseList(List<DatabaseInfo> databaseList) {
		this.databaseList = databaseList;
	}
	public boolean isGroupBy() {
		return groupBy;
	}
	public void setGroupBy(boolean groupBy) {
		this.groupBy = groupBy;
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
	public List<Object> getIds() {
		return ids;
	}
	public void setIds(List<Object> ids) {
		this.ids = ids;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}

	/**
	 * 首页网站个数
	 */
	private int homeNum;
	/**
	 * 栏目网站个数
	 */
	private int channelNum;
	/**
	 * 业务网站个数
	 */
	private int buseNum;
	
	public int getHomeNum() {
		return homeNum;
	}
	public void setHomeNum(int homeNum) {
		this.homeNum = homeNum;
	}
	public int getChannelNum() {
		return channelNum;
	}
	public void setChannelNum(int channelNum) {
		this.channelNum = channelNum;
	}
	public int getBuseNum() {
		return buseNum;
	}
	public void setBuseNum(int buseNum) {
		this.buseNum = buseNum;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getSiteCode() {
		return siteCode;
	}
	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	public String getScanDate() {
		return scanDate;
	}

	public void setScanDate(String scanDate) {
		this.scanDate = scanDate;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getErrorNum() {
		return errorNum;
	}
	public void setErrorNum(Integer errorNum) {
		this.errorNum = errorNum;
	}
	public Integer getConnectionSum() {
		return connectionSum;
	}
	public void setConnectionSum(Integer connectionSum) {
		this.connectionSum = connectionSum;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public String getHomePageUrl() {
		return homePageUrl;
	}
	public void setHomePageUrl(String homePageUrl) {
		this.homePageUrl = homePageUrl;
	}
	public String getJumpPageUrl() {
		return jumpPageUrl;
	}
	public void setJumpPageUrl(String jumpPageUrl) {
		this.jumpPageUrl = jumpPageUrl;
	}
	public Integer getCountSum() {
		return countSum;
	}
	public void setCountSum(Integer countSum) {
		this.countSum = countSum;
	}

	public Integer getQueue() {
		return queue;
	}

	public void setQueue(Integer queue) {
		this.queue = queue;
	}

}
