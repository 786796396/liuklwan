package com.ucap.cloud_web.dto;


import com.publics.util.page.Query;


/*** 前台页面传递基础数据<br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-10-09 09:53:32 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@SuppressWarnings("serial")public class JcStatisticsRequest extends Query {
	
	//昨天
	private String yDay;
	//之前的第七天
	private String sDay;
	public String getyDay() {
		return yDay;
	}
	public void setyDay(String yDay) {
		this.yDay = yDay;
	}
	public String getsDay() {
		return sDay;
	}
	public void setsDay(String sDay) {
		this.sDay = sDay;
	}
	
	

}

