package com.ucap.cloud_web.dto;


import com.publics.util.page.Query;


/*** 前台页面传递基础数据<br>* <b>作者：</b>liukl<br>* <b>日期：</b> 2017-03-21 18:49:16 <br>* <b>版权所有：<b>版权所有(C) 2017<br>*/@SuppressWarnings("serial")public class SpotSecurityReportRequest extends Query {
	//抽查进度表id
	private Integer scheduleId;

	public Integer getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(Integer scheduleId) {
		this.scheduleId = scheduleId;
	}

}

