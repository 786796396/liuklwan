package com.ucap.cloud_web.entity;


import java.util.Date;
/**
 * <br>
 * <b>作者：</b>Na.Y<br>
 * <b>日期：</b> 2016-09-13 14:51:28 <br>
 * <b>版权所有：<b>版权所有(C) 2016<br>
 */
public class DetectionPeroidCount {

	//
	private int id;

	// 网站标识码
	private String siteCode;

	// 服务周期Id
	private int servicePeroidId;

	// 实际开始日期（yyyy-mm-dd）
	private String startDate;

	// 实际结束日期（yyyy-mm-dd）
	private String endDate;

	// 网站访问失败比例
	private String connErrorProportion;

	// 首页死链数
	private int linkHome;

	// 全站死链数
	private int linkAll;

	// 首页信息未更新天数是否超过两周（1：是 0：否 -1：未知）
	private int securityHome;

	// 栏目不更新
	private int securityChannel;

	// 空白栏目
	private int securityBlank;

	// 互动回应情况数量
	private int securityResponse;

	// 获取服务不实用数量：办事指南
	private int serviceGuide;

	// 服务不实用数量：附件下载
	private int serviceDownload;

	// 服务不实用:在线系统连通性数量
	private int serviceConn;

	// 严重错误：严重错别字
	private int seriousCorrect;

	// 严重错误：虚假伪造内容数量
	private int seriousUnreal;

	// 严重错误：反动暴力或者色情数量
	private int seriousViolence;

	// 严重错误：其他
	private int seriousOthers;

	// 其他
	private int others;

	// 创建时间
	private Date createTime;

	// 修改时间
	private Date modifyTime;

	// 针对全面监测-站点报告是否可下载
	private int isDown;
	// 针对全面监测-站点报告状态
	private int checkReportResult;
	// 网站标识码name
	private String siteName;
	// 网站标识码url
	private String url;
	// 控制该数据是否显示 0显示 1隐藏
	private String isHide;
	// 检测形式（0:抽查 1:全面检测）
	private Integer type;
	// 0:未读未反馈 1：已读未反馈
	private Integer isRead;
	// 网站检查结果 1：合格，2：不合格
	private Integer siteCheckResult;
	// 网站检查结果 1：合格，2：不合格
	private String siteCheckResultName;

	/** set */
	public void setId(int id) {
		this.id = id;
	}

	/** get */
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

	/** set 服务周期Id */
	public void setServicePeroidId(int servicePeroidId) {
		this.servicePeroidId = servicePeroidId;
	}

	/** get 服务周期Id */
	public int getServicePeroidId() {
		return servicePeroidId;
	}

	/** set 开始日期（yyyy-mm-dd） */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/** get 开始日期（yyyy-mm-dd） */
	public String getStartDate() {
		return startDate;
	}

	/** set 结束日期（yyyy-mm-dd） */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/** get 结束日期（yyyy-mm-dd） */
	public String getEndDate() {
		return endDate;
	}

	/** set 网站访问失败比例 */
	public void setConnErrorProportion(String connErrorProportion) {
		this.connErrorProportion = connErrorProportion;
	}

	/** get 网站访问失败比例 */
	public String getConnErrorProportion() {
		return connErrorProportion;
	}

	/** set 首页死链数 */
	public void setLinkHome(int linkHome) {
		this.linkHome = linkHome;
	}

	/** get 首页死链数 */
	public int getLinkHome() {
		return linkHome;
	}

	/** set 全站死链数 */
	public void setLinkAll(int linkAll) {
		this.linkAll = linkAll;
	}

	/** get 全站死链数 */
	public int getLinkAll() {
		return linkAll;
	}

	/** set 首页信息未更新天数是否超过两周（1：是 0：否 -1：未知） */
	public void setSecurityHome(int securityHome) {
		this.securityHome = securityHome;
	}

	/** get 首页信息未更新天数是否超过两周（1：是 0：否 -1：未知） */
	public int getSecurityHome() {
		return securityHome;
	}

	/** set 栏目不更新 */
	public void setSecurityChannel(int securityChannel) {
		this.securityChannel = securityChannel;
	}

	/** get 栏目不更新 */
	public int getSecurityChannel() {
		return securityChannel;
	}

	/** set 空白栏目 */
	public void setSecurityBlank(int securityBlank) {
		this.securityBlank = securityBlank;
	}

	/** get 空白栏目 */
	public int getSecurityBlank() {
		return securityBlank;
	}

	/** set 互动回应情况数量 */
	public void setSecurityResponse(int securityResponse) {
		this.securityResponse = securityResponse;
	}

	/** get 互动回应情况数量 */
	public int getSecurityResponse() {
		return securityResponse;
	}

	/** set 获取服务不实用数量：办事指南 */
	public void setServiceGuide(int serviceGuide) {
		this.serviceGuide = serviceGuide;
	}

	/** get 获取服务不实用数量：办事指南 */
	public int getServiceGuide() {
		return serviceGuide;
	}

	/** set 服务不实用数量：附件下载 */
	public void setServiceDownload(int serviceDownload) {
		this.serviceDownload = serviceDownload;
	}

	/** get 服务不实用数量：附件下载 */
	public int getServiceDownload() {
		return serviceDownload;
	}

	/** set 服务不实用:在线系统连通性数量 */
	public void setServiceConn(int serviceConn) {
		this.serviceConn = serviceConn;
	}

	/** get 服务不实用:在线系统连通性数量 */
	public int getServiceConn() {
		return serviceConn;
	}

	/** set 严重错误：严重错别字 */
	public void setSeriousCorrect(int seriousCorrect) {
		this.seriousCorrect = seriousCorrect;
	}

	/** get 严重错误：严重错别字 */
	public int getSeriousCorrect() {
		return seriousCorrect;
	}

	/** set 严重错误：虚假伪造内容数量 */
	public void setSeriousUnreal(int seriousUnreal) {
		this.seriousUnreal = seriousUnreal;
	}

	/** get 严重错误：虚假伪造内容数量 */
	public int getSeriousUnreal() {
		return seriousUnreal;
	}

	/** set 严重错误：反动暴力或者色情数量 */
	public void setSeriousViolence(int seriousViolence) {
		this.seriousViolence = seriousViolence;
	}

	/** get 严重错误：反动暴力或者色情数量 */
	public int getSeriousViolence() {
		return seriousViolence;
	}

	/** set 严重错误：其他 */
	public void setSeriousOthers(int seriousOthers) {
		this.seriousOthers = seriousOthers;
	}

	/** get 严重错误：其他 */
	public int getSeriousOthers() {
		return seriousOthers;
	}

	/** set 其他 */
	public void setOthers(int others) {
		this.others = others;
	}

	/** get 其他 */
	public int getOthers() {
		return others;
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

	public int getIsDown() {
		return isDown;
	}

	public void setIsDown(int isDown) {
		this.isDown = isDown;
	}

	public int getCheckReportResult() {
		return checkReportResult;
	}

	public void setCheckReportResult(int checkReportResult) {
		this.checkReportResult = checkReportResult;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIsHide() {
		return isHide;
	}

	public void setIsHide(String isHide) {
		this.isHide = isHide;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getIsRead() {
		return isRead;
	}

	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}

	public Integer getSiteCheckResult() {
		return siteCheckResult;
	}

	public void setSiteCheckResult(Integer siteCheckResult) {
		this.siteCheckResult = siteCheckResult;
	}

	public String getSiteCheckResultName() {
		return siteCheckResultName;
	}

	public void setSiteCheckResultName(String siteCheckResultName) {
		this.siteCheckResultName = siteCheckResultName;
	}

}
