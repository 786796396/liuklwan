package com.ucap.cloud_web.dto;


import com.publics.util.page.Query;


/*** 前台页面传递基础数据<br>* <b>作者：</b>liujc<br>* <b>日期：</b> 2017-02-28 18:03:30 <br>* <b>版权所有：<b>版权所有(C) 2017<br>*/@SuppressWarnings("serial")public class BigOrgDailyRequest extends Query {
	
	private String taskid;//组织单位siteCode
	private String orgSiteCode;
	private String countDay;//查询日期
	private Double linkerrsiteprop;  // 当日连不通网站占比
	private Double indexdeadprop;  // 当日死链占比均值   
	private Integer updatenum;   // 当日更新网站条数
	
	private Integer parentId;
	private Integer isBm;//0非部委 1部委
	
	private String siteCodeNot;//组织单位编码不等于
	
	private Integer startIndex;//开始条数
	private Integer pageSize;//一页展示多少条
	private String orderVal;//柱形图的val
	public String getSiteCodeNot() {
		return siteCodeNot;
	}
	public void setSiteCodeNot(String siteCodeNot) {
		this.siteCodeNot = siteCodeNot;
	}
	public String getTaskid() {
		return taskid;
	}
	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}
	public Double getLinkerrsiteprop() {
		return linkerrsiteprop;
	}
	public void setLinkerrsiteprop(Double linkerrsiteprop) {
		this.linkerrsiteprop = linkerrsiteprop;
	}
	public Double getIndexdeadprop() {
		return indexdeadprop;
	}
	public void setIndexdeadprop(Double indexdeadprop) {
		this.indexdeadprop = indexdeadprop;
	}
	public Integer getUpdatenum() {
		return updatenum;
	}
	public void setUpdatenum(Integer updatenum) {
		this.updatenum = updatenum;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public Integer getIsBm() {
		return isBm;
	}
	public void setIsBm(Integer isBm) {
		this.isBm = isBm;
	}
	public String getCountDay() {
		return countDay;
	}
	public void setCountDay(String countDay) {
		this.countDay = countDay;
	}
	public String getOrgSiteCode() {
		return orgSiteCode;
	}
	public void setOrgSiteCode(String orgSiteCode) {
		this.orgSiteCode = orgSiteCode;
	}
	public Integer getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public String getOrderVal() {
		return orderVal;
	}
	public void setOrderVal(String orderVal) {
		this.orderVal = orderVal;
	}

}

