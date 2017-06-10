package com.ucap.cloud_web.entity;

import java.util.Date;

/**
 * <br>
 * <b>作者：</b>Nora<br>
 * <b>日期：</b> 2015-11-13 14:58:41 <br>
 * <b>版权所有：<b>版权所有(C) 2015<br>
 */
public class TaskDaily {

	// id
	private int id;

	// 订单详情表id
	private int websiteInfoId;

	// 网站标识码
	private String siteCode;

	// 任务开始日期（对应订单服务开始时间）
	private Date startDate;

	// 任务结束日期（对应订单服务结束时间）
	private Date endDate;

	// 启动状态（0：未启动 1：启动成功 2：启动失败）
	private int startStatus;

	// 获取监测状态（3:未获取 4.获取成功，5.获取失败）
	private int fetchStatus;

	// 是否启动（0：不启动 1：启动 ）
	private int isStart;

	// 启动时间
	private Date startTime;

	// 启动失败次数（最多5次）
	private int startTimes;

	// 获取数据时间
	private Date fetchTime;

	// 获取监测结果失败次数(最多监测5次)
	private int fetchTimes;

	// 当前锁定数据Id
	private String currentLockId;

	// 创建时间
	private Date createTime;
	
	//修改时间
	private Date modifyTime;
	//是否扫描（1：是，0：否）
	private int isScan;
	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public int getIsStart() {
		return isStart;
	}

	public void setIsStart(int isStart) {
		this.isStart = isStart;
	}

	/** set id */
	public void setId(int id) {
		this.id = id;
	}

	/** get id */
	public int getId() {
		return id;
	}

	/** set 订单详情表id */
	public void setWebsiteInfoId(int websiteInfoId) {
		this.websiteInfoId = websiteInfoId;
	}

	/** get 订单详情表id */
	public int getWebsiteInfoId() {
		return websiteInfoId;
	}

	/** set 网站标识码 */
	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	/** get 网站标识码 */
	public String getSiteCode() {
		return siteCode;
	}

	/** set 任务开始日期（对应订单服务开始时间） */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/** get 任务开始日期（对应订单服务开始时间） */
	public Date getStartDate() {
		return startDate;
	}

	/** set 任务结束日期（对应订单服务结束时间） */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/** get 任务结束日期（对应订单服务结束时间） */
	public Date getEndDate() {
		return endDate;
	}

	public int getStartStatus() {
		return startStatus;
	}

	public void setStartStatus(int startStatus) {
		this.startStatus = startStatus;
	}

	public int getFetchStatus() {
		return fetchStatus;
	}

	public void setFetchStatus(int fetchStatus) {
		this.fetchStatus = fetchStatus;
	}

	/** set 启动时间 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/** get 启动时间 */
	public Date getStartTime() {
		return startTime;
	}

	/** set 启动失败次数（最多5次） */
	public void setStartTimes(int startTimes) {
		this.startTimes = startTimes;
	}

	/** get 启动失败次数（最多5次） */
	public int getStartTimes() {
		return startTimes;
	}

	/** set 获取数据时间 */
	public void setFetchTime(Date fetchTime) {
		this.fetchTime = fetchTime;
	}

	/** get 获取数据时间 */
	public Date getFetchTime() {
		return fetchTime;
	}

	/** set 获取监测结果次数(最多监测5次) */
	public void setFetchTimes(int fetchTimes) {
		this.fetchTimes = fetchTimes;
	}

	/** get 获取监测结果次数(最多监测5次) */
	public int getFetchTimes() {
		return fetchTimes;
	}

	/** set 创建时间 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/** get 创建时间 */
	public Date getCreateTime() {
		return createTime;
	}

	public String getCurrentLockId() {
		return currentLockId;
	}

	public void setCurrentLockId(String currentLockId) {
		this.currentLockId = currentLockId;
	}

	@Override
	public String toString() {
		return "TaskDaily [id=" + id + ", websiteInfoId=" + websiteInfoId
				+ ", siteCode=" + siteCode + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", startStatus=" + startStatus
				+ ", fetchStatus=" + fetchStatus + ", startTime=" + startTime
				+ ", startTimes=" + startTimes + ", fetchTime=" + fetchTime
				+ ", fetchTimes=" + fetchTimes + ", currentLockId="
				+ currentLockId + ", createTime=" + createTime + "]";
	}

	public int getIsScan() {
		return isScan;
	}

	public void setIsScan(int isScan) {
		this.isScan = isScan;
	}

}
