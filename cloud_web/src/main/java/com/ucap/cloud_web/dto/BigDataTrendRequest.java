package com.ucap.cloud_web.dto;


import com.publics.util.page.Query;


/**

	//父类id
	private Integer id;
	//用户唯一标识
	private String siteCode;
	// 扫描日期
	private String scanDate;
	//sql排序字段
	private String sqlName;
	
	
	public String getScanDate() {
		return scanDate;
	}
	public void setScanDate(String scanDate) {
		this.scanDate = scanDate;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSiteCode() {
		return siteCode;
	}
	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}
	public String getSqlName() {
		return sqlName;
	}
	public void setSqlName(String sqlName) {
		this.sqlName = sqlName;
	}
	
	
	
}
