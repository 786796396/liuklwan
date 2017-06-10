package com.ucap.cloud_web.entity;


import java.util.Date;


/*** <br>* <b>作者：</b>SunJiang<br>* <b>日期：</b> 2015-10-30 09:51:22 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/public class TaskAll {


	//	private int id;
	//订单详情表id	private int websiteInfoId;
	//周期Id	private int servicePeriodId;
	//监测状态（1：未启动 2：启动成功 3：启动失败，4.获取成功，5.获取失败）	private int status;
	//启动失败次数(最多5次)	private int startTimes;
	//获取监测结果次数（最多5次）	private int fetchTimes;
	//启动日期（YYYY-mm-dd 00:00:00格式，以周期结束时间往前推）	private Date startDate;
	//taskId(启动任务后返回的)	private String returnTaskId;
	//启动轮询时间（yyyy-mm-dd hh:mm:ss）	private Date startTime;
	//创建时间	private Date createTime;
	//获取数据轮询时间	private Date fetchTime;
	/** set  */	public void setId(int id){
		this.id=id;
	}
	/** get  */	public int getId(){
		return id;
	}
	/** set 订单详情表id */	public void setWebsiteInfoId(int websiteInfoId){
		this.websiteInfoId=websiteInfoId;
	}
	/** get 订单详情表id */	public int getWebsiteInfoId(){
		return websiteInfoId;
	}
	/** set 周期Id */	public void setServicePeriodId(int servicePeriodId){
		this.servicePeriodId=servicePeriodId;
	}
	/** get 周期Id */	public int getServicePeriodId(){
		return servicePeriodId;
	}
	/** set 监测状态（1：未启动 2：启动成功 3：启动失败，4.获取成功，5.获取失败） */	public void setStatus(int status){
		this.status=status;
	}
	/** get 监测状态（1：未启动 2：启动成功 3：启动失败，4.获取成功，5.获取失败） */	public int getStatus(){
		return status;
	}
	/** set 启动失败次数(最多5次) */	public void setStartTimes(int startTimes){
		this.startTimes=startTimes;
	}
	/** get 启动失败次数(最多5次) */	public int getStartTimes(){
		return startTimes;
	}
	/** set 获取监测结果次数（最多5次） */	public void setFetchTimes(int fetchTimes){
		this.fetchTimes=fetchTimes;
	}
	/** get 获取监测结果次数（最多5次） */	public int getFetchTimes(){
		return fetchTimes;
	}
	/** set 启动日期（YYYY-mm-dd 00:00:00格式，以周期结束时间往前推） */	public void setStartDate(Date startDate){
		this.startDate=startDate;
	}
	/** get 启动日期（YYYY-mm-dd 00:00:00格式，以周期结束时间往前推） */	public Date getStartDate(){
		return startDate;
	}
	/** set taskId(启动任务后返回的) */	public void setReturnTaskId(String returnTaskId){
		this.returnTaskId=returnTaskId;
	}
	/** get taskId(启动任务后返回的) */	public String getReturnTaskId(){
		return returnTaskId;
	}
	/** set 启动轮询时间（yyyy-mm-dd hh:mm:ss） */	public void setStartTime(Date startTime){
		this.startTime=startTime;
	}
	/** get 启动轮询时间（yyyy-mm-dd hh:mm:ss） */	public Date getStartTime(){
		return startTime;
	}
	/** set 创建时间 */	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}
	/** get 创建时间 */	public Date getCreateTime(){
		return createTime;
	}
	/** set 获取数据轮询时间 */	public void setFetchTime(Date fetchTime){
		this.fetchTime=fetchTime;
	}
	/** get 获取数据轮询时间 */	public Date getFetchTime(){
		return fetchTime;
	}
}

