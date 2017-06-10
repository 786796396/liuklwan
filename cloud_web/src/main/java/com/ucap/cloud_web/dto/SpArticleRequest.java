package com.ucap.cloud_web.dto;


import com.publics.util.page.Query;


/**

	private int sort;	//排序
	
	private int channelId;  // 栏目id
	
	private int isTop;  // 是否置顶（0：否，1：是）
	
	private int delFlag;//删除标识（0：正常，1：删除）
	
	private String siteCode;

	public int getChannelId() {
		return channelId;
	}

	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}

	public int getIsTop() {
		return isTop;
	}

	public void setIsTop(int isTop) {
		this.isTop = isTop;
	}

	public int getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(int delFlag) {
		this.delFlag = delFlag;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

}
