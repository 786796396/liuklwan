package com.ucap.cloud_web.entity;


import java.util.Date;


/*** <br>* <b>作者：</b>ccx<br>* <b>日期：</b> 2016-03-05 17:42:12 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public class UserLog {


	//主键	private int id;
	//网站标识码(组织和填报)	private String siteCode;
	//网站名称	private String name;
	//省级	private String province;
	//市级	private String city;
	//县级	private String county;
	//是否门户（0非门户，1门户）	private int isorganizational;
	
	//ip地址	private String ip;
	//创建时间	private Date createTime;
	
	// (是否收费  1是2否)
	private Integer isCost;

	
		public Integer getIsCost() {
		return isCost;
	}

	public void setIsCost(Integer isCost) {
		this.isCost = isCost;
	}

	/** set 主键 */	public void setId(int id){
		this.id=id;
	}
	/** get 主键 */	public int getId(){
		return id;
	}
	/** set 网站标识码(组织和填报) */	public void setSiteCode(String siteCode){
		this.siteCode=siteCode;
	}
	/** get 网站标识码(组织和填报) */	public String getSiteCode(){
		return siteCode;
	}
	/** set 网站名称 */	public void setName(String name){
		this.name=name;
	}
	/** get 网站名称 */	public String getName(){
		return name;
	}
	/** set 省级 */	public void setProvince(String province){
		this.province=province;
	}
	/** get 省级 */	public String getProvince(){
		return province;
	}
	/** set 市级 */	public void setCity(String city){
		this.city=city;
	}
	/** get 市级 */	public String getCity(){
		return city;
	}
	/** set 县级 */	public void setCounty(String county){
		this.county=county;
	}
	/** get 县级 */	public String getCounty(){
		return county;
	}
	/** set 是否门户（0非门户，1门户） */	public void setIsorganizational(int isorganizational){
		this.isorganizational=isorganizational;
	}
	/** get 是否门户（0非门户，1门户） */	public int getIsorganizational(){
		return isorganizational;
	}
	
	
	/** get ip地址 */	public String getIp() {
		return ip;
	}
	/** set ip地址 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/** set 创建时间 */	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}
	/** get 创建时间 */	public Date getCreateTime(){
		return createTime;
	}
}

