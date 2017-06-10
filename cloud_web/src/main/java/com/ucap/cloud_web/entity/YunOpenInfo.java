package com.ucap.cloud_web.entity;


import java.util.Date;


/*** <br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2017-05-10 19:06:11 <br>* <b>版权所有：<b>版权所有(C) 2017<br>*/public class YunOpenInfo {


	//云分析任务表id	private int id;
	//组织/填报标识码	private String siteCode;
	//组织/填报名称	private String name;
	//试用日期	private String useDate;
	//服务截止日期	private String serviceEndDate;
	//嵌码情况	private String inCodeCondition;
	//嵌码日期	private String inCodeDate;
	//到期任务	private String endTask;
	//是否组织单位（0-填报 1-组织）	private int isOrg;
	//是否签订	private int isCost;
	//创建时间	private Date createTime;
	//修改时间	private Date modifyTime;
	/** set 云分析任务表id */	public void setId(int id){
		this.id=id;
	}
	/** get 云分析任务表id */	public int getId(){
		return id;
	}
	/** set 组织/填报标识码 */	public void setSiteCode(String siteCode){
		this.siteCode=siteCode;
	}
	/** get 组织/填报标识码 */	public String getSiteCode(){
		return siteCode;
	}
	/** set 组织/填报名称 */	public void setName(String name){
		this.name=name;
	}
	/** get 组织/填报名称 */	public String getName(){
		return name;
	}
	/** set 试用日期 */	public void setUseDate(String useDate){
		this.useDate=useDate;
	}
	/** get 试用日期 */	public String getUseDate(){
		return useDate;
	}
	/** set 服务截止日期 */	public void setServiceEndDate(String serviceEndDate){
		this.serviceEndDate=serviceEndDate;
	}
	/** get 服务截止日期 */	public String getServiceEndDate(){
		return serviceEndDate;
	}
	/** set 嵌码情况 */	public void setInCodeCondition(String inCodeCondition){
		this.inCodeCondition=inCodeCondition;
	}
	/** get 嵌码情况 */	public String getInCodeCondition(){
		return inCodeCondition;
	}
	/** set 嵌码日期 */	public void setInCodeDate(String inCodeDate){
		this.inCodeDate=inCodeDate;
	}
	/** get 嵌码日期 */	public String getInCodeDate(){
		return inCodeDate;
	}
	/** set 到期任务 */	public void setEndTask(String endTask){
		this.endTask=endTask;
	}
	/** get 到期任务 */	public String getEndTask(){
		return endTask;
	}
	/** set 是否组织单位（0-填报 1-组织） */	public void setIsOrg(int isOrg){
		this.isOrg=isOrg;
	}
	/** get 是否组织单位（0-填报 1-组织） */	public int getIsOrg(){
		return isOrg;
	}
	/** set 是否签订 */	public void setIsCost(int isCost){
		this.isCost=isCost;
	}
	/** get 是否签订 */	public int getIsCost(){
		return isCost;
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

