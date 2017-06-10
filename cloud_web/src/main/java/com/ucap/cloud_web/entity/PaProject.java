package com.ucap.cloud_web.entity;


import java.util.Date;


/*** <br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-08-23 10:59:23 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public class PaProject {


	//	private int id;
	//缺陷库表id（pa_defect_db）	private int defectId;
	//项目名称	private String projectName;
	//项目年份	private String projectYear;
	//主管机构	private String chargeDepartment;
	//负责人	private String director;
	//负责人电话	private String directorPhone;
	//添加人id	private int userId;
	//修改人id	private int modifyUserId;
	//创建时间	private Date createTime;
	//修改时间	private Date modifyTime;
	
	private String siteCode;
	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	/** set  */	public void setId(int id){
		this.id=id;
	}
	/** get  */	public int getId(){
		return id;
	}
	/** set 缺陷库表id（pa_defect_db） */	public void setDefectId(int defectId){
		this.defectId=defectId;
	}
	/** get 缺陷库表id（pa_defect_db） */	public int getDefectId(){
		return defectId;
	}
	/** set 项目名称 */	public void setProjectName(String projectName){
		this.projectName=projectName;
	}
	/** get 项目名称 */	public String getProjectName(){
		return projectName;
	}
	/** set 项目年份 */	public void setProjectYear(String projectYear){
		this.projectYear=projectYear;
	}
	/** get 项目年份 */	public String getProjectYear(){
		return projectYear;
	}
	/** set 主管机构 */	public void setChargeDepartment(String chargeDepartment){
		this.chargeDepartment=chargeDepartment;
	}
	/** get 主管机构 */	public String getChargeDepartment(){
		return chargeDepartment;
	}
	/** set 负责人 */	public void setDirector(String director){
		this.director=director;
	}
	/** get 负责人 */	public String getDirector(){
		return director;
	}
	/** set 负责人电话 */	public void setDirectorPhone(String directorPhone){
		this.directorPhone=directorPhone;
	}
	/** get 负责人电话 */	public String getDirectorPhone(){
		return directorPhone;
	}
	/** set 添加人id */	public void setUserId(int userId){
		this.userId=userId;
	}
	/** get 添加人id */	public int getUserId(){
		return userId;
	}
	/** set 修改人id */	public void setModifyUserId(int modifyUserId){
		this.modifyUserId=modifyUserId;
	}
	/** get 修改人id */	public int getModifyUserId(){
		return modifyUserId;
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

