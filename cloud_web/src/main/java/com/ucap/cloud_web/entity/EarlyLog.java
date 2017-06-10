package com.ucap.cloud_web.entity;


import java.util.Date;


/*** <br>* <b>作者：</b>luocheng<br>* <b>日期：</b> 2017-01-03 11:00:12 <br>* <b>版权所有：<b>版权所有(C) 2017<br>*/public class EarlyLog {


	//	private int id;
	//网站标识码	private String siteCode;
	//服务周期id	private int servicePeriodId;
	//预警类型: 0:日报,1首页连通性，2首页不更新，3栏目不更新，4空白栏目，5互动回应，6内容正确性，7升级改版或者临时关停，8首页连不通比例超过3%，9网站疑似被挂码或被篡改，10首页不更新超过10天 ，11空白栏目超过5个，12栏目不更新超过8个，13互动回应差栏目超过3个月未回应	private int earlyType;
	//发送方式:  1:邮件  2：短信 3：微信	private int sendType;
	//联系地址	private String contactAddress;
	//内容	private String content;
	//创建时间	private Date createTime;
	
	//发送至设置方
	private int sendSquare;
	
	public int getSendSquare() {
		return sendSquare;
	}

	public void setSendSquare(int sendSquare) {
		this.sendSquare = sendSquare;
	}

	/** set  */	public void setId(int id){
		this.id=id;
	}
	/** get  */	public int getId(){
		return id;
	}
	/** set 网站标识码 */	public void setSiteCode(String siteCode){
		this.siteCode=siteCode;
	}
	/** get 网站标识码 */	public String getSiteCode(){
		return siteCode;
	}
	/** set 服务周期id */	public void setServicePeriodId(int servicePeriodId){
		this.servicePeriodId=servicePeriodId;
	}
	/** get 服务周期id */	public int getServicePeriodId(){
		return servicePeriodId;
	}
	/** set 预警类型: 0:日报,1首页连通性，2首页不更新，3栏目不更新，4空白栏目，5互动回应，6内容正确性，7升级改版或者临时关停，8首页连不通比例超过3%，9网站疑似被挂码或被篡改，10首页不更新超过10天 ，11空白栏目超过5个，12栏目不更新超过8个，13互动回应差栏目超过3个月未回应 */	public void setEarlyType(int earlyType){
		this.earlyType=earlyType;
	}
	/** get 预警类型: 0:日报,1首页连通性，2首页不更新，3栏目不更新，4空白栏目，5互动回应，6内容正确性，7升级改版或者临时关停，8首页连不通比例超过3%，9网站疑似被挂码或被篡改，10首页不更新超过10天 ，11空白栏目超过5个，12栏目不更新超过8个，13互动回应差栏目超过3个月未回应 */	public int getEarlyType(){
		return earlyType;
	}
	/** set 发送方式:  1:邮件  2：短信 3：微信 */	public void setSendType(int sendType){
		this.sendType=sendType;
	}
	/** get 发送方式:  1:邮件  2：短信 3：微信 */	public int getSendType(){
		return sendType;
	}
	/** set 联系地址 */	public void setContactAddress(String contactAddress){
		this.contactAddress=contactAddress;
	}
	/** get 联系地址 */	public String getContactAddress(){
		return contactAddress;
	}
	/** set 内容 */	public void setContent(String content){
		this.content=content;
	}
	/** get 内容 */	public String getContent(){
		return content;
	}
	/** set 创建时间 */	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}
	/** get 创建时间 */	public Date getCreateTime(){
		return createTime;
	}
}

