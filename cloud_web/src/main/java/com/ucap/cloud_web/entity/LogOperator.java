package com.ucap.cloud_web.entity;

import java.util.Date;


/*** <br>* <b>作者：</b>Nora<br>* <b>日期：</b> 2016-09-29 15:30:25 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public class LogOperator {


	//	private int id;
	//操作站点	private String siteCode;
	//操作前的内容	private String contentsBefore;
	//操作后的内容	private String contentsAfter;
	//日志类型	private int type;
	//操作对象（如表名）	private String targetObject;
	//备注	private String remark;
	//操作人	private String operator;
	//创建时间	private Date createTime;
	/** set  */	public void setId(int id){
		this.id=id;
	}
	/** get  */	public int getId(){
		return id;
	}
	/** set 操作站点 */	public void setSiteCode(String siteCode){
		this.siteCode=siteCode;
	}
	/** get 操作站点 */	public String getSiteCode(){
		return siteCode;
	}
	/** set 操作前的内容 */	public void setContentsBefore(String contentsBefore){
		this.contentsBefore=contentsBefore;
	}
	/** get 操作前的内容 */	public String getContentsBefore(){
		return contentsBefore;
	}
	/** set 操作后的内容 */	public void setContentsAfter(String contentsAfter){
		this.contentsAfter=contentsAfter;
	}
	/** get 操作后的内容 */	public String getContentsAfter(){
		return contentsAfter;
	}
	/** set 日志类型 */	public void setType(int type){
		this.type=type;
	}
	/** get 日志类型 */	public int getType(){
		return type;
	}
	/** set 操作对象（如表名） */	public void setTargetObject(String targetObject){
		this.targetObject=targetObject;
	}
	/** get 操作对象（如表名） */	public String getTargetObject(){
		return targetObject;
	}
	/** set 备注 */	public void setRemark(String remark){
		this.remark=remark;
	}
	/** get 备注 */	public String getRemark(){
		return remark;
	}
	/** set 操作人 */	public void setOperator(String operator){
		this.operator=operator;
	}
	/** get 操作人 */	public String getOperator(){
		return operator;
	}
	/** set 创建时间 */	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}
	/** get 创建时间 */	public Date getCreateTime(){
		return createTime;
	}
}

