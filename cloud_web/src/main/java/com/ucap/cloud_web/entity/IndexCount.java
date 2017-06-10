package com.ucap.cloud_web.entity;


import java.util.Date;


/*** <br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2016-01-05 13:08:55 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public class IndexCount {


	//
	private int id;

	// 网站标识码
	private String siteCode;

	// 扫描时间
	private String scanDate;

	// 套餐id
	private int comboId;

	// 类型;1付费，2免费，3抽查
	private int type;

	// 健康总分数
	private double totalScores;

	// 折算分数
	private double convertScores;

	// 首页连通性扣分
	private double connectionHome;

	// 栏目连通性扣分
	private double connectionChannel;

	// 关键栏目连通性扣分
	private double connectionBusiness;

	// 首页链接可用性扣分
	private double linkHome;

	// 全站链接可用性扣分
	private double linkAll;

	// 栏目信息不更新
	private double securityChannel;

	// 空白栏目
	private double securityBlank;

	// 互动回应差
	private double securityResponse;

	// 服务不实用
	private double securityServcie;

	// 内容正确性
	private double correctContent;

	// 网站安全
	private double websiteSafe;

	// 首页更新
	private double updateHome;

	// 栏目更新
	private double updateChannel;

	// 内容分析
	private double updateAnalyse;

	// 批次（用于免费版）
	private int times;

	// 创建时间
	private Date createTime;

	// 修改时间
	private Date modifyTime;

	public double getConvertScores() {
		return convertScores;
	}

	public void setConvertScores(double convertScores) {
		this.convertScores = convertScores;
	}

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

	/** set 扫描时间 */
	public void setScanDate(String scanDate) {
		this.scanDate = scanDate;
	}

	/** get 扫描时间 */
	public String getScanDate() {
		return scanDate;
	}

	/** set 套餐id */
	public void setComboId(int comboId) {
		this.comboId = comboId;
	}

	/** get 套餐id */
	public int getComboId() {
		return comboId;
	}

	/** set 类型;1付费，2免费，3抽查 */
	public void setType(int type) {
		this.type = type;
	}

	/** get 类型;1付费，2免费，3抽查 */
	public int getType() {
		return type;
	}

	/** set 健康总分数 */
	public void setTotalScores(double totalScores) {
		this.totalScores = totalScores;
	}

	/** get 健康总分数 */
	public double getTotalScores() {
		return totalScores;
	}

	/** set 首页连通性扣分 */
	public void setConnectionHome(double connectionHome) {
		this.connectionHome = connectionHome;
	}

	/** get 首页连通性扣分 */
	public double getConnectionHome() {
		return connectionHome;
	}

	/** set 栏目连通性扣分 */
	public void setConnectionChannel(double connectionChannel) {
		this.connectionChannel = connectionChannel;
	}

	/** get 栏目连通性扣分 */
	public double getConnectionChannel() {
		return connectionChannel;
	}

	/** set 关键栏目连通性扣分 */
	public void setConnectionBusiness(double connectionBusiness) {
		this.connectionBusiness = connectionBusiness;
	}

	/** get 关键栏目连通性扣分 */
	public double getConnectionBusiness() {
		return connectionBusiness;
	}

	/** set 首页链接可用性扣分 */
	public void setLinkHome(double linkHome) {
		this.linkHome = linkHome;
	}

	/** get 首页链接可用性扣分 */
	public double getLinkHome() {
		return linkHome;
	}

	/** set 全站链接可用性扣分 */
	public void setLinkAll(double linkAll) {
		this.linkAll = linkAll;
	}

	/** get 全站链接可用性扣分 */
	public double getLinkAll() {
		return linkAll;
	}

	/** set 栏目信息不更新 */
	public void setSecurityChannel(double securityChannel) {
		this.securityChannel = securityChannel;
	}

	/** get 栏目信息不更新 */
	public double getSecurityChannel() {
		return securityChannel;
	}

	/** set 空白栏目 */
	public void setSecurityBlank(double securityBlank) {
		this.securityBlank = securityBlank;
	}

	/** get 空白栏目 */
	public double getSecurityBlank() {
		return securityBlank;
	}

	/** set 互动回应差 */
	public void setSecurityResponse(double securityResponse) {
		this.securityResponse = securityResponse;
	}

	/** get 互动回应差 */
	public double getSecurityResponse() {
		return securityResponse;
	}

	/** set 服务不实用 */
	public void setSecurityServcie(double securityServcie) {
		this.securityServcie = securityServcie;
	}

	/** get 服务不实用 */
	public double getSecurityServcie() {
		return securityServcie;
	}

	/** set 内容正确性 */
	public void setCorrectContent(double correctContent) {
		this.correctContent = correctContent;
	}

	/** get 内容正确性 */
	public double getCorrectContent() {
		return correctContent;
	}

	/** set 网站安全 */
	public void setWebsiteSafe(double websiteSafe) {
		this.websiteSafe = websiteSafe;
	}

	/** get 网站安全 */
	public double getWebsiteSafe() {
		return websiteSafe;
	}

	/** set 首页更新 */
	public void setUpdateHome(double updateHome) {
		this.updateHome = updateHome;
	}

	/** get 首页更新 */
	public double getUpdateHome() {
		return updateHome;
	}

	/** set 栏目更新 */
	public void setUpdateChannel(double updateChannel) {
		this.updateChannel = updateChannel;
	}

	/** get 栏目更新 */
	public double getUpdateChannel() {
		return updateChannel;
	}

	/** set 内容分析 */
	public void setUpdateAnalyse(double updateAnalyse) {
		this.updateAnalyse = updateAnalyse;
	}

	/** get 内容分析 */
	public double getUpdateAnalyse() {
		return updateAnalyse;
	}

	/** set 批次（用于免费版） */
	public void setTimes(int times) {
		this.times = times;
	}

	/** get 批次（用于免费版） */
	public int getTimes() {
		return times;
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

}

