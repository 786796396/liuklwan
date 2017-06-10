package com.ucap.cloud_web.dto;


import java.util.List;

import com.publics.util.page.Query;


/*** 前台页面传递基础数据<br>* <b>作者：</b>liujc<br>* <b>日期：</b> 2016-10-17 15:16:51 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@SuppressWarnings("serial")public class MTaskoverviewRequest extends Query {

	private String taskid;
	private String countday;//查询日期
	private Double linkerrsiteprop;  // 当日连不通网站占比
	private Double indexdeadprop;  // 当日死链占比均值   
	private Integer updatenum;   // 当日更新网站条数

	private String startCountday;
	private String endCountday;

	
	public String getStartCountday() {
		return startCountday;
	}
	public void setStartCountday(String startCountday) {
		this.startCountday = startCountday;
	}
	public String getEndCountday() {
		return endCountday;
	}
	public void setEndCountday(String endCountday) {
		this.endCountday = endCountday;
	}
	public String getTaskid() {
		return taskid;
	}
	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}
	public String getCountday() {
		return countday;
	}
	public void setCountday(String countday) {
		this.countday = countday;
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
	
	
	
}

