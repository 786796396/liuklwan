package com.ucap.cloud_web.entity;

import java.util.Date;

/**
 * <br>
 * <b>作者：</b>SunJiang<br>
 * <b>日期：</b> 2015-10-30 09:51:22 <br>
 * <b>版权所有：<b>版权所有(C) 2015<br>
 */
public class TimedTaskLog {

	// 定时任务操纵记录表id
	private int id;

	// 网站标识码
	private String siteCode;

	// 开始时间
	private Date beginTime;

	// 结束时间
	private Date endTime;

	// 错误信息
	private String errMesg;

	// 完成状态(0未完成，1完成)
	private int status;

	// 类型（1:首页更新，2：栏目更新，3.首页死链，4.内容正确性
	// ，5,获取更新，首页死链，错别字数据任务，6.连通性任务，7.汇总数据任务，8.健康指数任务）
	private int type;

	// 日志描述
	private String remark;

	// 扫描日期
	private String scanDate;

	// 创建时间
	private Date createTime;

	// 消耗时间（分钟）
	private String cousumedTime;

	// 数据来源
	private String dataTo;

	public String getCousumedTime() {
		return cousumedTime;
	}

	public void setCousumedTime(String cousumedTime) {
		this.cousumedTime = cousumedTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getScanDate() {
		return scanDate;
	}

	public void setScanDate(String scanDate) {
		this.scanDate = scanDate;
	}

	/** set 定时任务操纵记录表id */
	public void setId(int id) {
		this.id = id;
	}

	/** get 定时任务操纵记录表id */
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

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getErrMesg() {
		return errMesg;
	}

	public void setErrMesg(String errMesg) {
		this.errMesg = errMesg;
	}

	/** set 完成状态(0未完成，1完成) */
	public void setStatus(int status) {
		this.status = status;
	}

	/** get 完成状态(0未完成，1完成) */
	public int getStatus() {
		return status;
	}

	/** set 类型 */
	public void setType(int type) {
		this.type = type;
	}

	/** get 类型 */
	public int getType() {
		return type;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getDataTo() {
		return dataTo;
	}

	public void setDataTo(String dataTo) {
		this.dataTo = dataTo;
	}

}
