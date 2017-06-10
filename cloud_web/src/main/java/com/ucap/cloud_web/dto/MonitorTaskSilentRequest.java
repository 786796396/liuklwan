package com.ucap.cloud_web.dto;


import java.util.List;

import com.publics.util.page.Query;


/**

	//网站标识码
	private String siteCode;

	//合同Id
	private int contractId;
	
	//当前时间
	private String nowTime;
	
	//结束时间
	private String endTime;

	//合同 id 集合
	private List<Integer> contractIds;
	
	// 结束时间  小于当前时间
	private String endNowTime;
	
	
	
	
	public String getNowTime() {
		return nowTime;
	}

	public void setNowTime(String nowTime) {
		this.nowTime = nowTime;
	}


	public String getEndNowTime() {
		return endNowTime;
	}

	public void setEndNowTime(String endNowTime) {
		this.endNowTime = endNowTime;
	}

	public List<Integer> getContractIds() {
		return contractIds;
	}

	public void setContractIds(List<Integer> contractIds) {
		this.contractIds = contractIds;
	}


	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	public int getContractId() {
		return contractId;
	}

	public void setContractId(int contractId) {
		this.contractId = contractId;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

}
