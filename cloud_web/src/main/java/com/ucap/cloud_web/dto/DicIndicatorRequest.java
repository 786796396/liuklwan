package com.ucap.cloud_web.dto;


import com.publics.util.page.Query;


/**
	//父类id
	private int parentId;
	//名称
	private String indicatorName;

	//监测周期（0：天，1：月）
	private int period;

	//监测说明
	private String remark;

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getIndicatorName() {
		return indicatorName;
	}

	public void setIndicatorName(String indicatorName) {
		this.indicatorName = indicatorName;
	}

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	

}
