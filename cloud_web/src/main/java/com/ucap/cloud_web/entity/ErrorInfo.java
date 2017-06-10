package com.ucap.cloud_web.entity;


import java.util.Date;


/*** <br>* <b>作者：</b>qingjy<br>* <b>日期：</b> 2017-04-28 16:08:42 <br>* <b>版权所有：<b>版权所有(C) 2017<br>*/public class ErrorInfo {


	//主键	private int id;
	//留言id	private int errorInfoId;
	//网站标识码	private String siteCode;
	//网站名称	private String name;
	//纠错信息id	private int problemId;
	//问题url地址	private String errorUrl;
	//问题描述	private String description;
	//办理状态：-1未处理;0受理;1已办结;2不予受理(无效);3不予受理;4线下受理;5已处理待审核;6初审驳回;7终审待审核;8退回重新处理;9退回重新审核;10不予受理（业务问题）;11不予受理(不属实);12不予受理（不在普查范围）;13不再受理(重复);14已转办（未曝光但已经受理）;15未曝光已办结; 16已转办（运维组是已转办，省部级组织单位是待转办）	private int status;
	//曝光时间	private Date reviewTime;
	//曝光内容：即前台展现的内容	private String displayContent;
	//受理单位	private String transactUnit;
	//受理时间	private Date transactTime;
	//不受理原因	private String rejectReason;
	//受理结果	private String transactResult;
	//创建时间	private Date createTime;
	//修改时间	private Date modifyTime;
	/** set 主键 */	public void setId(int id){
		this.id=id;
	}
	/** get 主键 */	public int getId(){
		return id;
	}
	/** set 留言id */	public void setErrorInfoId(int errorInfoId){
		this.errorInfoId=errorInfoId;
	}
	/** get 留言id */	public int getErrorInfoId(){
		return errorInfoId;
	}
	/** set 网站标识码 */	public void setSiteCode(String siteCode){
		this.siteCode=siteCode;
	}
	/** get 网站标识码 */	public String getSiteCode(){
		return siteCode;
	}
	/** set 网站名称 */	public void setName(String name){
		this.name=name;
	}
	/** get 网站名称 */	public String getName(){
		return name;
	}
	/** set 纠错信息id */	public void setProblemId(int problemId){
		this.problemId=problemId;
	}
	/** get 纠错信息id */	public int getProblemId(){
		return problemId;
	}
	/** set 问题url地址 */	public void setErrorUrl(String errorUrl){
		this.errorUrl=errorUrl;
	}
	/** get 问题url地址 */	public String getErrorUrl(){
		return errorUrl;
	}
	/** set 问题描述 */	public void setDescription(String description){
		this.description=description;
	}
	/** get 问题描述 */	public String getDescription(){
		return description;
	}
	/** set 办理状态：-1未处理;0受理;1已办结;2不予受理(无效);3不予受理;4线下受理;5已处理待审核;6初审驳回;7终审待审核;8退回重新处理;9退回重新审核;10不予受理（业务问题）;11不予受理(不属实);12不予受理（不在普查范围）;13不再受理(重复);14已转办（未曝光但已经受理）;15未曝光已办结; 16已转办（运维组是已转办，省部级组织单位是待转办） */	public void setStatus(int status){
		this.status=status;
	}
	/** get 办理状态：-1未处理;0受理;1已办结;2不予受理(无效);3不予受理;4线下受理;5已处理待审核;6初审驳回;7终审待审核;8退回重新处理;9退回重新审核;10不予受理（业务问题）;11不予受理(不属实);12不予受理（不在普查范围）;13不再受理(重复);14已转办（未曝光但已经受理）;15未曝光已办结; 16已转办（运维组是已转办，省部级组织单位是待转办） */	public int getStatus(){
		return status;
	}
	/** set 曝光时间 */	public void setReviewTime(Date reviewTime){
		this.reviewTime=reviewTime;
	}
	/** get 曝光时间 */	public Date getReviewTime(){
		return reviewTime;
	}
	/** set 曝光内容：即前台展现的内容 */	public void setDisplayContent(String displayContent){
		this.displayContent=displayContent;
	}
	/** get 曝光内容：即前台展现的内容 */	public String getDisplayContent(){
		return displayContent;
	}
	/** set 受理单位 */	public void setTransactUnit(String transactUnit){
		this.transactUnit=transactUnit;
	}
	/** get 受理单位 */	public String getTransactUnit(){
		return transactUnit;
	}
	/** set 受理时间 */	public void setTransactTime(Date transactTime){
		this.transactTime=transactTime;
	}
	/** get 受理时间 */	public Date getTransactTime(){
		return transactTime;
	}
	/** set 不受理原因 */	public void setRejectReason(String rejectReason){
		this.rejectReason=rejectReason;
	}
	/** get 不受理原因 */	public String getRejectReason(){
		return rejectReason;
	}
	/** set 受理结果 */	public void setTransactResult(String transactResult){
		this.transactResult=transactResult;
	}
	/** get 受理结果 */	public String getTransactResult(){
		return transactResult;
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

