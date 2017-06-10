package com.ucap.cloud_web.entity;


import java.util.Date;


/*** <br>* <b>作者：</b>SunJiang<br>* <b>日期：</b> 2016-03-21 16:16:55 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public class DatabaseLink {


	//	private int id;
	//基本信息表id	private int databaseInfoId;
	//网站标识码
	private String orgSiteCode;
	
	//网站标识码
	private String siteCode;
	//是否门户	private int isorganizational;
	//类型（1本级门户，2本级部门，3下属单位，4例外，5关停，6其他）	private int type;
	//创建时间	private Date createTime;
	/** set  */	public void setId(int id){
		this.id=id;
	}
	/** get  */	public int getId(){
		return id;
	}
		public String getOrgSiteCode() {
		return orgSiteCode;
	}

	public void setOrgSiteCode(String orgSiteCode) {
		this.orgSiteCode = orgSiteCode;
	}

	/** set 基本信息表id */	public void setDatabaseInfoId(int databaseInfoId){
		this.databaseInfoId=databaseInfoId;
	}
	/** get 基本信息表id */	public int getDatabaseInfoId(){
		return databaseInfoId;
	}

	/** set 网站标识码 */	public void setSiteCode(String siteCode){
		this.siteCode=siteCode;
	}
	/** get 网站标识码 */	public String getSiteCode(){
		return siteCode;
	}
	/** set 是否门户 */	public void setIsorganizational(int isorganizational){
		this.isorganizational=isorganizational;
	}
	/** get 是否门户 */	public int getIsorganizational(){
		return isorganizational;
	}
	/** set 类型（1本级门户，2本级部门，3下属单位，4例外，5关停，6其他） */	public void setType(int type){
		this.type=type;
	}
	/** get 类型（1本级门户，2本级部门，3下属单位，4例外，5关停，6其他） */	public int getType(){
		return type;
	}
	/** set 创建时间 */	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}
	/** get 创建时间 */	public Date getCreateTime(){
		return createTime;
	}
}

