package com.ucap.cloud_web.entity;


import java.util.Date;


/**


	//大数据访问权限表id



	//开始时间
	private String startDate;
	//结束时间
	private String endDate;
	//tab标签名称1
	private String tabNameOne;
	//tab标签名称2
	private String tabNameTwo;
	//tab标签名称3
	private String tabNameThree;
	
	//焦点推荐显示指标：（1、健康指数 2、连通率 3、首页死链个 4、首页不更新网站数  5、更新量条）
	private Integer focusType;
	//焦点推荐展示条数
	private Integer showNum;
	//焦点推荐详情页访问地址1
	private String focusUrlOne;
	//焦点推荐详情页访问地址2
	private String focusUrlTwo;
	//焦点推荐详情页访问地址3
	private String focusUrlThree;
	
	

	//外部引用大数据访问的url
	private String url;
	//外部引用焦点推荐url 访问指标的url
	private String indexUrl;
	
		this.id=id;
	}

		return id;
	}

		this.orgSiteCode=orgSiteCode;
	}

		return orgSiteCode;
	}

		this.desCode=desCode;
	}

		return desCode;
	}

		this.status=status;
	}

		return status;
	}

		this.createTime=createTime;
	}

		return createTime;
	}

		this.modifyTime=modifyTime;
	}

		return modifyTime;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getTabNameOne() {
		return tabNameOne;
	}

	public void setTabNameOne(String tabNameOne) {
		this.tabNameOne = tabNameOne;
	}

	public String getTabNameTwo() {
		return tabNameTwo;
	}

	public void setTabNameTwo(String tabNameTwo) {
		this.tabNameTwo = tabNameTwo;
	}

	public String getTabNameThree() {
		return tabNameThree;
	}

	public void setTabNameThree(String tabNameThree) {
		this.tabNameThree = tabNameThree;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIndexUrl() {
		return indexUrl;
	}

	public void setIndexUrl(String indexUrl) {
		this.indexUrl = indexUrl;
	}

	public Integer getFocusType() {
		return focusType;
	}

	public void setFocusType(Integer focusType) {
		this.focusType = focusType;
	}

	public Integer getShowNum() {
		return showNum;
	}

	public void setShowNum(Integer showNum) {
		this.showNum = showNum;
	}

	public String getFocusUrlOne() {
		return focusUrlOne;
	}

	public void setFocusUrlOne(String focusUrlOne) {
		this.focusUrlOne = focusUrlOne;
	}

	public String getFocusUrlTwo() {
		return focusUrlTwo;
	}

	public void setFocusUrlTwo(String focusUrlTwo) {
		this.focusUrlTwo = focusUrlTwo;
	}

	public String getFocusUrlThree() {
		return focusUrlThree;
	}

	public void setFocusUrlThree(String focusUrlThree) {
		this.focusUrlThree = focusUrlThree;
	}

