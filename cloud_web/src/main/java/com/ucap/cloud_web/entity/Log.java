package com.ucap.cloud_web.entity;


import java.util.Date;


/*** <br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2015-10-29 10:49:16 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/public class Log {


	//日志自动生成ID	private int logId;
	//操作用户ID	private int userId;
	//操作用户名称	private String name;
	//日志生成时间	private Date logDate;
	//日志类型: 1：安全日志 2：表示操作日志	private int type;
	//物理地址	private String mac;
	//IP地址	private String ip;
	//对象类型	private int objectType;
	//操作事件写入权限编码，系统事件写入系统编码	private String objectId;
	//事件名称	private String eventName;
	//详细描述	private String eventRecord;
	/** set 日志自动生成ID */	public void setLogId(int logId){
		this.logId=logId;
	}
	/** get 日志自动生成ID */	public int getLogId(){
		return logId;
	}
	/** set 操作用户ID */	public void setUserId(int userId){
		this.userId=userId;
	}
	/** get 操作用户ID */	public int getUserId(){
		return userId;
	}
	/** set 操作用户名称 */	public void setName(String name){
		this.name=name;
	}
	/** get 操作用户名称 */	public String getName(){
		return name;
	}
	/** set 日志生成时间 */	public void setLogDate(Date logDate){
		this.logDate=logDate;
	}
	/** get 日志生成时间 */	public Date getLogDate(){
		return logDate;
	}
	/** set 日志类型: 1：安全日志 2：表示操作日志 */	public void setType(int type){
		this.type=type;
	}
	/** get 日志类型: 1：安全日志 2：表示操作日志 */	public int getType(){
		return type;
	}
	/** set 物理地址 */	public void setMac(String mac){
		this.mac=mac;
	}
	/** get 物理地址 */	public String getMac(){
		return mac;
	}
	/** set IP地址 */	public void setIp(String ip){
		this.ip=ip;
	}
	/** get IP地址 */	public String getIp(){
		return ip;
	}
	/** set 对象类型 */	public void setObjectType(int objectType){
		this.objectType=objectType;
	}
	/** get 对象类型 */	public int getObjectType(){
		return objectType;
	}
	/** set 操作事件写入权限编码，系统事件写入系统编码 */	public void setObjectId(String objectId){
		this.objectId=objectId;
	}
	/** get 操作事件写入权限编码，系统事件写入系统编码 */	public String getObjectId(){
		return objectId;
	}
	/** set 事件名称 */	public void setEventName(String eventName){
		this.eventName=eventName;
	}
	/** get 事件名称 */	public String getEventName(){
		return eventName;
	}
	/** set 详细描述 */	public void setEventRecord(String eventRecord){
		this.eventRecord=eventRecord;
	}
	/** get 详细描述 */	public String getEventRecord(){
		return eventRecord;
	}
}

