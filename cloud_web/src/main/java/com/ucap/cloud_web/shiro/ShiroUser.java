package com.ucap.cloud_web.shiro;

import java.io.Serializable;

public class ShiroUser implements Serializable {
	private static final long serialVersionUID = -1748602382963711884L;

	/** 用户id */
	private Integer userId;

	/** 当前登录网站标识码siteCode */
	private String siteCode;

	/** 当前用户名 */
	private String userName;

	/** 当前url */
	private String url;

	/** 当前更新时间 */
	private String updateDate;

	/** 当前级别 */
	private int userType;

	/** 类型：1组织单位登录 ,2填报单位登录 */
	private int type;

	/** 填报单位名称 */
	private String siteName;

	/** 本级名称 */
	private String currentName;

	/** 本级总数 */
	private int currentCount;
	
	/** 下级单位 */
	private int nextCount;

	/** 本级sitecode */
	private String currentSiteCode;

	/** 部委总数 */
	private int bmCount;
	
//	/** 省份总数 */
//	private int shengCount;
//
//	/** 市级总数 */
//	private int shiCount;
//
//	/** 县级总数 */
//	private int xianCount;

	/** 下级站点标识码 */
	private String childSiteCode;

	/** 抽查总数 */
	private String sportCount;

	/** pdf的url */
	private String pdfUrl;
	//填报单位是否收费用户
	private int iscost;
	
	//填报单位是否自己买单
	private int iscostOwn;
	
	//组织单位是否收费
	private int isOrgCost;

	private int count;
	
	//例外个数
	private int exceptionCount;
	//关停个数
	private int closeCount;
	//其他个数
	private int otherCount;
	//标识是否可以查看生成的word报告
	private int canSeeWord;
	//标识是否可以看到全面监测栏目
	private int canSeeMonitor;
	//判断是否显示网站抽查栏目
	private int canSeeSpot;
	//扫描异常
	private int isScan;
	//站点状态
	private int isexp;
	//
	private String orgToInfo;
	//判断为组织单位跳转过去 填报单位的 isScan 放入session中
	private Integer orgToIsScan;
	
	//判断为组织单位跳转过去
	private Integer orgToIsexp;
	
	 
	// 判断是否  组织单位  填报单位的  绩效任务是否存在；
	private Integer paTargetCount;
	
	private Integer isSafetyService ;//  iscost = 1 代表检测中 ；  2 已经过期  0 未购买过
	private Integer isOrgSafetyService ;//  isOrgCost = 1 代表检测中 ；  2 已经过期  0 未购买过
	//新菜单页面引导状态
	private int guideState;
	
	//是否具有地图
	private Integer isHasMap;
	
	private Integer isYunOpen;//判断云分析点击页面跳转控制   0-跳转到申请试用页面   1-直接跳转到详情页面
	
	
	
	public Integer getIsYunOpen() {
		return isYunOpen;
	}

	public void setIsYunOpen(Integer isYunOpen) {
		this.isYunOpen = isYunOpen;
	}

	public Integer getIsHasMap() {
		return isHasMap;
	}

	public void setIsHasMap(Integer isHasMap) {
		this.isHasMap = isHasMap;
	}

	public int getIscostOwn() {
		return iscostOwn;
	}

	public void setIscostOwn(int iscostOwn) {
		this.iscostOwn = iscostOwn;
	}

	public Integer getIsSafetyService() {
		return isSafetyService;
	}

	public void setIsSafetyService(Integer isSafetyService) {
		this.isSafetyService = isSafetyService;
	}

	public Integer getIsOrgSafetyService() {
		return isOrgSafetyService;
	}

	public void setIsOrgSafetyService(Integer isOrgSafetyService) {
		this.isOrgSafetyService = isOrgSafetyService;
	}

	public Integer getPaTargetCount() {
		return paTargetCount;
	}

	public void setPaTargetCount(Integer paTargetCount) {
		this.paTargetCount = paTargetCount;
	}

	public int getIsOrgCost() {
		return isOrgCost;
	}

	public void setIsOrgCost(int isOrgCost) {
		this.isOrgCost = isOrgCost;
	}

	public Integer getOrgToIsScan() {
		return orgToIsScan;
	}

	public void setOrgToIsScan(Integer orgToIsScan) {
		this.orgToIsScan = orgToIsScan;
	}

	public Integer getOrgToIsexp() {
		return orgToIsexp;
	}

	public void setOrgToIsexp(Integer orgToIsexp) {
		this.orgToIsexp = orgToIsexp;
	}

	public ShiroUser() {
	};

	public ShiroUser(Integer userId, String siteCode, String userName, String url, String updateDate, int userType,
			String siteName, String childSiteCode, String currentName, String currentSiteCode, int bmCount, 
			int type, String sportCount, String pdfUrl,int iscost,int iscostOwn, int isOrgCost,int currentCount,int nextCount,
			int exceptionCount,int closeCount,int otherCount,int canSeeWord,int canSeeMonitor,int canSeeSpot,
			int isScan,int isexp,String orgToInfo,Integer orgToIsScan, Integer orgToIsexp,Integer paTargetCount, Integer isSafetyService,Integer isOrgSafetyService,int guideState,Integer isHasMap,Integer isYunOpen) {
		super();
		this.userId = userId;
		this.siteCode = siteCode;
		this.userName = userName;
		this.url = url;
		this.updateDate = updateDate;
		this.userType = userType;
		this.currentName = currentName;
		this.currentSiteCode = currentSiteCode;
		this.siteName = siteName;
		this.childSiteCode = childSiteCode;
		this.bmCount = bmCount;
//		this.shengCount = shengCount;
//		this.shiCount = shiCount;
//		this.xianCount = xianCount;
		this.type = type;
		this.sportCount = sportCount;
		this.pdfUrl = pdfUrl;
		this.iscost = iscost;
		this.iscostOwn=iscostOwn;
		this.currentCount = currentCount;
		this.nextCount = nextCount;
		this.exceptionCount = exceptionCount;
		this.closeCount = closeCount;
		this.otherCount = otherCount;
		this.canSeeWord = canSeeWord;
		this.canSeeMonitor = canSeeMonitor;
		this.canSeeSpot = canSeeSpot;
		this.isScan = isScan;
		this.isexp = isexp;
		this.orgToInfo = orgToInfo;
		this.orgToIsScan = orgToIsScan;
		this.orgToIsexp = orgToIsexp;
		this.isOrgCost=isOrgCost;
		this.paTargetCount=paTargetCount;
		this.isSafetyService=isSafetyService;
		this.isOrgSafetyService=isOrgSafetyService;
		this.guideState=guideState;
		this.isHasMap=isHasMap;
		this.isYunOpen=isYunOpen;
	}

	/**
	 * 本函数输出将作为默认的<shiro:principal/>输出.
	 */
	public Integer getUserId() {
		return userId;
	}

	public int getExceptionCount() {
		return exceptionCount;
	}

	public String getOrgToInfo() {
		return orgToInfo;
	}

	public void setOrgToInfo(String orgToInfo) {
		this.orgToInfo = orgToInfo;
	}

	public void setExceptionCount(int exceptionCount) {
		this.exceptionCount = exceptionCount;
	}

	public int getCloseCount() {
		return closeCount;
	}

	public void setCloseCount(int closeCount) {
		this.closeCount = closeCount;
	}

	public int getOtherCount() {
		return otherCount;
	}

	public void setOtherCount(int otherCount) {
		this.otherCount = otherCount;
	}

	public int getIscost() {
		return iscost;
	}

	public void setIscost(int iscost) {
		this.iscost = iscost;
	}

	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

//	public int getShengCount() {
//		return shengCount;
//	}

	public String getCurrentName() {
		return currentName;
	}

	public void setCurrentName(String currentName) {
		this.currentName = currentName;
	}

//	public void setShengCount(int shengCount) {
//		this.shengCount = shengCount;
//	}
//
//	public int getShiCount() {
//		return shiCount;
//	}
//
//	public void setShiCount(int shiCount) {
//		this.shiCount = shiCount;
//	}
//
//	public int getXianCount() {
//		return xianCount;
//	}
//
//	public void setXianCount(int xianCount) {
//		this.xianCount = xianCount;
//	}

	public String getChildSiteCode() {
		return childSiteCode;
	}

	public void setChildSiteCode(String childSiteCode) {
		this.childSiteCode = childSiteCode;
	}

	public String getSiteName() {
		return siteName;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getCurrentSiteCode() {
		return currentSiteCode;
	}

	public void setCurrentSiteCode(String currentSiteCode) {
		this.currentSiteCode = currentSiteCode;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getSportCount() {
		return sportCount;
	}

	public void setSportCount(String sportCount) {
		this.sportCount = sportCount;
	}

	public String getPdfUrl() {
		return pdfUrl;
	}

	public void setPdfUrl(String pdfUrl) {
		this.pdfUrl = pdfUrl;
	}

	public int getBmCount() {
		return bmCount;
	}

	public void setBmCount(int bmCount) {
		this.bmCount = bmCount;
	}

	public int getCurrentCount() {
		return currentCount;
	}

	public void setCurrentCount(int currentCount) {
		this.currentCount = currentCount;
	}

	public int getNextCount() {
		return nextCount;
	}

	public void setNextCount(int nextCount) {
		this.nextCount = nextCount;
	}

	public int getCanSeeWord() {
		return canSeeWord;
	}

	public void setCanSeeWord(int canSeeWord) {
		this.canSeeWord = canSeeWord;
	}

	public int getCanSeeMonitor() {
		return canSeeMonitor;
	}

	public void setCanSeeMonitor(int canSeeMonitor) {
		this.canSeeMonitor = canSeeMonitor;
	}

	public int getCanSeeSpot() {
		return canSeeSpot;
	}

	public void setCanSeeSpot(int canSeeSpot) {
		this.canSeeSpot = canSeeSpot;
	}

	public int getIsScan() {
		return isScan;
	}

	public void setIsScan(int isScan) {
		this.isScan = isScan;
	}

	public int getIsexp() {
		return isexp;
	}

	public void setIsexp(int isexp) {
		this.isexp = isexp;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getGuideState() {
		return guideState;
	}

	public void setGuideState(int guideState) {
		this.guideState = guideState;
	}


	
	
}
