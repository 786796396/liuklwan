package com.ucap.cloud_web;

 /**<p>Description:配置首页不更新快照跳转地址 </p>
 * <p>@Package：com.ucap.cloud_web </p>
 * <p>Title: UrlAdapterVar </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：sunjq </p>
 * <p>@date：2015-11-19下午06:54:04 </p>
 */
public class UrlAdapterVar {
	
	/**
	 * 系统对外访问路径 用于邮件模板等获取系统资源
	 */
	private String webPath;
	
	/**
	 * 云监测接口前面部分：如：http://218.244.131.213:8080/
	 */
	private String urlPre;
	
	/**
	 * 內容校対快照地址
	 */
	private String wronglyImg;
	
	
	/**
	 * 首页快照缩略图
	 */
	private String litImgUrl;
	
	/**
	 * 死链快照地址
	 */
	private String xenuLink;

	/**
	 * 普通快照地址
	 */
	private String ordinaryLInk;
	
	/**
	 * 本地截图路径
	 */
	private String linkUrl;
	
	/**验证组织单位是否可以登录*/
	private String loginHttpPostUrl;

	/**
	 * 登录页面  监测网页个数，发现问题个数
	 */
	private String loginMonitor;
	/**
	 * 项目部署路径
	 */
	private String webPathTomcat;
	/**
	 * word报告的配置文件夹路径
	 */
	private String wordfilepath;
	/**
	 * 大数据分析汇总结果接口路径
	 */
	private String taskResultPath;
	/**
	 * 大数据分析站点结果接口
	 */
	@SuppressWarnings("unused")
	private String siteResultPath;
	/**
	 * 项目部署的绝对路径
	 */
	private String webPaths;
	
	/**
	 * 前台上传的  截图 
	 */
	private String cloudWebLinkUrl;
	/**
	 * 绩效获取为word路径
	 */
	private String jiXiaoWord;
	/**
	 * 调用后台在线报告url
	 */
	private String onlineReportUrl;
	/**
	 * 试用云搜索url
	 */
	private String cloudSearchUrl;
	/**
	 * cloub_web  项目部署的绝对路径
	 * @return
	 */
	private String cloubWebPaths;
	/**
	 * 连通性url
	 */
	private String connectivityUrl;
	
	/**
	 * 个性化Url
	 */
	private String spUrl;
	
	/**
	 * logo保存spLogoUrl
	 */
	private String spLogoUrl;
	
	//大数据首页json生成路径
	public String getBigHomeJsonUrl() {
		return getCloubWebPaths() + "/json/bigHome";
	}

	
	public String getSpLogoUrl() {
		return spLogoUrl;
	}

	public void setSpLogoUrl(String spLogoUrl) {
		this.spLogoUrl = spLogoUrl;
	}

	public String getCloubWebPaths() {
		return cloubWebPaths;
	}

	public void setCloubWebPaths(String cloubWebPaths) {
		this.cloubWebPaths = cloubWebPaths;
	}

	public String getJiXiaoWord() {
		return jiXiaoWord;
	}

	public void setJiXiaoWord(String jiXiaoWord) {
		this.jiXiaoWord = jiXiaoWord;
	}

	public String getCloudWebLinkUrl() {
		return cloudWebLinkUrl;
	}

	public void setCloudWebLinkUrl(String cloudWebLinkUrl) {
		this.cloudWebLinkUrl = cloudWebLinkUrl;
	}

	/**
	 * 10.URL连通校验接口
	 * 
	 * @return
	 */
	public String getVerifyUrlLink() {
		return getUrlPre() + "/Monitor/verifyUrlLink.ejf";
	}

	/** @Description: 获取普通快照地址
	 * @author sunjiaqi --- 2016-3-6下午10:19:43     
	 * @return           
	*/
	public String getImgUrl() {
		return getOrdinaryLInk()+"?cmd=common&src=";
	}
	
	/** @Description: 获取死链快照地址
	 * @author sunjiaqi --- 2016-3-6下午10:21:25     
	 * @return           
	*/
	public String getXenuLinkUrl(){
		return getXenuLink() + "?src=";
	}
	
	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public String getLoginHttpPostUrl() {
		return loginHttpPostUrl;
	}

	public void setLoginHttpPostUrl(String loginHttpPostUrl) {
		this.loginHttpPostUrl = loginHttpPostUrl;
	}
	
	public String getUrlPre() {
		return urlPre;
	}

	public void setUrlPre(String urlPre) {
		this.urlPre = urlPre;
	}

	public String getLoginMonitor() {
		return loginMonitor;
	}

	public void setLoginMonitor(String loginMonitor) {
		this.loginMonitor = loginMonitor;
	}

	public String getXenuLink() {
		return xenuLink;
	}

	public void setXenuLink(String xenuLink) {
		this.xenuLink = xenuLink;
	}

	public String getOrdinaryLInk() {
		return ordinaryLInk;
	}

	public void setOrdinaryLInk(String ordinaryLInk) {
		this.ordinaryLInk = ordinaryLInk;
	}

	public void setLitImgUrl(String litImgUrl) {
		this.litImgUrl = litImgUrl;
	}
	public String getLitImgUrl() {
		return litImgUrl;
	}

	public String getWronglyImg() {
		return wronglyImg+"pSnapshoot.ejf?";
	}

	public void setWronglyImg(String wronglyImg) {
		this.wronglyImg = wronglyImg;
	}

	public String getWebPath() {
		return webPath;
	}

	public void setWebPath(String webPath) {
		this.webPath = webPath;
	}

	public String getWebPathTomcat() {
		return webPathTomcat;
	}

	public void setWebPathTomcat(String webPathTomcat) {
		this.webPathTomcat = webPathTomcat;
	}

	public String getWordfilepath() {
		return wordfilepath;
	}

	public void setWordfilepath(String wordfilepath) {
		this.wordfilepath = wordfilepath;
	}

	public String getTaskResultPath() {
		return taskResultPath+"taskresult.ejf";
	}

	public void setTaskResultPath(String taskResultPath) {
		this.taskResultPath = taskResultPath;
	}

	public String getSiteResultPath() {
		return taskResultPath.substring(0, taskResultPath.lastIndexOf("/")+1)+"siteresult.ejf";
	}

	public void setSiteResultPath(String siteResultPath) {
		this.siteResultPath = siteResultPath;
	}

	public String getWebPaths() {
		return webPaths;
	}

	public void setWebPaths(String webPaths) {
		this.webPaths = webPaths;
	}

	public String getOnlineReportUrl() {
		return onlineReportUrl;
	}

	public void setOnlineReportUrl(String onlineReportUrl) {
		this.onlineReportUrl = onlineReportUrl;
	}

	public String getCloudSearchUrl() {
		return cloudSearchUrl;
	}

	public void setCloudSearchUrl(String cloudSearchUrl) {
		this.cloudSearchUrl = cloudSearchUrl;
	}

	public String getConnectivityUrl() {
		return connectivityUrl;
	}

	public void setConnectivityUrl(String connectivityUrl) {
		this.connectivityUrl = connectivityUrl;
	}

	public String getSpUrl() {
		return spUrl;
	}

	public void setSpUrl(String spUrl) {
		this.spUrl = spUrl;
	}
	
}
