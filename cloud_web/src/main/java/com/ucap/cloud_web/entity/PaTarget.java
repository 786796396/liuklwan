package com.ucap.cloud_web.entity;


import java.util.Date;


/*** <br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-08-24 13:38:42 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public class PaTarget {


	//	private int id;
	//项目评估对象表id（pa_appraisal）	private int appraisalId;
	//站点标识码	private String siteCode;
	//网站名称	private String siteName;
	//首页地址	private String indexUrl;
	//是否是新建（1：已存在，2：新建）	private short isExist;
	//创建时间	private Date createTime;
	//修改时间	private Date modifyTime;
	
	//自评状态
	private Integer ratingState;
	//考评状态
	private Integer appraisalState;

	// 任务对象关联表 id
	private Integer paTargetTaskId;
	
	//是否开放（1：开放，2：关闭）
	private Integer isOpen;
	

	public Integer getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(Integer isOpen) {
		this.isOpen = isOpen;
	}

	public Integer getPaTargetTaskId() {
		return paTargetTaskId;
	}

	public void setPaTargetTaskId(Integer paTargetTaskId) {
		this.paTargetTaskId = paTargetTaskId;
	}

	public Integer getRatingState() {
		return ratingState;
	}

	public void setRatingState(Integer ratingState) {
		this.ratingState = ratingState;
	}

	public Integer getAppraisalState() {
		return appraisalState;
	}

	public void setAppraisalState(Integer appraisalState) {
		this.appraisalState = appraisalState;
	}

	/** set  */	public void setId(int id){
		this.id=id;
	}
	/** get  */	public int getId(){
		return id;
	}
	/** set 项目评估对象表id（pa_appraisal） */	public void setAppraisalId(int appraisalId){
		this.appraisalId=appraisalId;
	}
	/** get 项目评估对象表id（pa_appraisal） */	public int getAppraisalId(){
		return appraisalId;
	}
	/** set 站点标识码 */	public void setSiteCode(String siteCode){
		this.siteCode=siteCode;
	}
	/** get 站点标识码 */	public String getSiteCode(){
		return siteCode;
	}
	/** set 网站名称 */	public void setSiteName(String siteName){
		this.siteName=siteName;
	}
	/** get 网站名称 */	public String getSiteName(){
		return siteName;
	}
	/** set 首页地址 */	public void setIndexUrl(String indexUrl){
		this.indexUrl=indexUrl;
	}
	/** get 首页地址 */	public String getIndexUrl(){
		return indexUrl;
	}
	/** set 是否是新建（1：已存在，2：新建） */	public void setIsExist(short isExist){
		this.isExist=isExist;
	}
	/** get 是否是新建（1：已存在，2：新建） */	public short getIsExist(){
		return isExist;
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

