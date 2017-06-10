package com.ucap.cloud_web.entity;


import java.util.Date;


/*** <br>* <b>作者：</b>Na.Y<br>* <b>日期：</b> 2016-09-14 09:49:59 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public class ConfigTimer {


	//	private int id;
	//设置trigger名称	private String triggerName;
	//设置Job名称	private String jobDetailName;
	//设置表达式(定时时间)	private String cronExpression;
	//任务类名(is_springbean=0:包名+类 或 is_springbean=1注解名称)	private String targetObject;
	//类名对应的方法名	private String methodName;
	//设置是否并发启动任务 0是false 非0是true	private String concurrent;
	//任务状态（1：可用，0：不可用）	private String state;
	//是否是springBean 1是 0 否	private String isSpringbean;
	//任务类型（1：前台任务，2：后台任务，3：接口任务）	private int type;
	//备注	private String remark;
	//创建时间	private Date createTime;
	//修改时间	private Date modifyTime;
	/** set  */	public void setId(int id){
		this.id=id;
	}
	/** get  */	public int getId(){
		return id;
	}
	/** set 设置trigger名称 */	public void setTriggerName(String triggerName){
		this.triggerName=triggerName;
	}
	/** get 设置trigger名称 */	public String getTriggerName(){
		return triggerName;
	}
	/** set 设置Job名称 */	public void setJobDetailName(String jobDetailName){
		this.jobDetailName=jobDetailName;
	}
	/** get 设置Job名称 */	public String getJobDetailName(){
		return jobDetailName;
	}
	/** set 设置表达式(定时时间) */	public void setCronExpression(String cronExpression){
		this.cronExpression=cronExpression;
	}
	/** get 设置表达式(定时时间) */	public String getCronExpression(){
		return cronExpression;
	}
	/** set 任务类名(is_springbean=0:包名+类 或 is_springbean=1注解名称) */	public void setTargetObject(String targetObject){
		this.targetObject=targetObject;
	}
	/** get 任务类名(is_springbean=0:包名+类 或 is_springbean=1注解名称) */	public String getTargetObject(){
		return targetObject;
	}
	/** set 类名对应的方法名 */	public void setMethodName(String methodName){
		this.methodName=methodName;
	}
	/** get 类名对应的方法名 */	public String getMethodName(){
		return methodName;
	}
	/** set 设置是否并发启动任务 0是false 非0是true */	public void setConcurrent(String concurrent){
		this.concurrent=concurrent;
	}
	/** get 设置是否并发启动任务 0是false 非0是true */	public String getConcurrent(){
		return concurrent;
	}
	/** set 任务状态（1：可用，0：不可用） */	public void setState(String state){
		this.state=state;
	}
	/** get 任务状态（1：可用，0：不可用） */	public String getState(){
		return state;
	}
	/** set 是否是springBean 1是 0 否 */	public void setIsSpringbean(String isSpringbean){
		this.isSpringbean=isSpringbean;
	}
	/** get 是否是springBean 1是 0 否 */	public String getIsSpringbean(){
		return isSpringbean;
	}
	/** set 任务类型（1：前台任务，2：后台任务，3：接口任务） */	public void setType(int type){
		this.type=type;
	}
	/** get 任务类型（1：前台任务，2：后台任务，3：接口任务） */	public int getType(){
		return type;
	}
	/** set 备注 */	public void setRemark(String remark){
		this.remark=remark;
	}
	/** get 备注 */	public String getRemark(){
		return remark;
	}
	/** set 创建时间 */	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}
	/** get 创建时间 */	public Date getCreateTime(){
		return createTime;
	}
	/** set 修改时间 */	public void setModifyTime(Date modifyTime){
		this.modifyTime=modifyTime;
	}
	/** get 修改时间 */	public Date getModifyTime(){
		return modifyTime;
	}
}

