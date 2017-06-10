package com.ucap.cloud_web.dto;


import com.publics.util.page.Query;


/*** 前台页面传递基础数据<br>* <b>作者：</b>SunJiang<br>* <b>日期：</b> 2015-10-30 09:51:22 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/@SuppressWarnings("serial")public class TaskAllRequest extends Query {
	
	private String siteCode;
	//周期Id
	private Integer servicePeriodId;
	//服务周期关联表id
	private Integer relations_period;
	public String getSiteCode() {
		return siteCode;
	}
	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}
	public Integer getServicePeriodId() {
		return servicePeriodId;
	}
	public void setServicePeriodId(Integer servicePeriodId) {
		this.servicePeriodId = servicePeriodId;
	}
	public Integer getRelations_period() {
		return relations_period;
	}
	public void setRelations_period(Integer relations_period) {
		this.relations_period = relations_period;
	}	
	
}

