package com.ucap.cloud_web.entity;


import java.util.Date;


/*** <br>* <b>作者：</b>qingjy<br>* <b>日期：</b> 2016-11-24 21:30:34 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public class ReportCollectResult {


	//主键	private int id;
	//站点标识码	private String siteCode;
	//服务周期ID	private int servicePeriodId;
	//文件类型:1.excel, 2.word	private short type;
	//文件路径	private String path;
	//文件别名	private String aliasName;
	//创建时间	private Date createTime;
	//修改时间	private Date modifyTime;
	/** set 主键 */	public void setId(int id){
		this.id=id;
	}
	/** get 主键 */	public int getId(){
		return id;
	}
	/** set 站点标识码 */	public void setSiteCode(String siteCode){
		this.siteCode=siteCode;
	}
	/** get 站点标识码 */	public String getSiteCode(){
		return siteCode;
	}
	/** set 服务周期ID */	public void setServicePeriodId(int servicePeriodId){
		this.servicePeriodId=servicePeriodId;
	}
	/** get 服务周期ID */	public int getServicePeriodId(){
		return servicePeriodId;
	}
	/** set 文件类型:1.excel, 2.word */	public void setType(short type){
		this.type=type;
	}
	/** get 文件类型:1.excel, 2.word */	public short getType(){
		return type;
	}
	/** set 文件路径 */	public void setPath(String path){
		this.path=path;
	}
	/** get 文件路径 */	public String getPath(){
		return path;
	}
	/** set 文件别名 */	public void setAliasName(String aliasName){
		this.aliasName=aliasName;
	}
	/** get 文件别名 */	public String getAliasName(){
		return aliasName;
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

