package com.ucap.cloud_web.entity;

import java.util.Date;


/**
* <br>
* <b>作者：</b>Sunjiang<br>
* <b>日期：</b> 2016-03-05 16:17:04 <br>
* <b>版权所有：<b>版权所有(C) 2016<br>
*/
public class ServicePeriod {


	//服务周期表id
	private int id;

	//服务开始时间
	private Date startDate;

	//服务结束时间
	private Date endDate;

	//创建时间
	private Date createTime;

	//修改时间
	private Date modifyTime;

	//套餐ID
	private int comboId;

	//合同ID
	private int contractInfoId;

	//扫描站点数
	private int webCount;
	//报告周期服务状态
	private int status;
	
	//抽查进度表id
	private int spotCheckScheduleId;
	
	//周期任务号
	private String servicePeriodNum;
	
	//同步合同上带过来的site_code
	private String siteCode;
	
	
	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	public String getServicePeriodNum() {
		return servicePeriodNum;
	}

	public void setServicePeriodNum(String servicePeriodNum) {
		this.servicePeriodNum = servicePeriodNum;
	}

	public int getSpotCheckScheduleId() {
		return spotCheckScheduleId;
	}

	public void setSpotCheckScheduleId(int spotCheckScheduleId) {
		this.spotCheckScheduleId = spotCheckScheduleId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public int getComboId() {
		return comboId;
	}

	public void setComboId(int comboId) {
		this.comboId = comboId;
	}

	public int getContractInfoId() {
		return contractInfoId;
	}

	public void setContractInfoId(int contractInfoId) {
		this.contractInfoId = contractInfoId;
	}

	public int getWebCount() {
		return webCount;
	}

	public void setWebCount(int webCount) {
		this.webCount = webCount;
	}

	
}


