package com.ucap.cloud_web.entity;


import java.util.Date;

import com.publics.util.utils.StringUtils;
import com.ucap.cloud_web.util.CommonUtils;


/*** <br>* <b>作者：</b>Nora<br>* <b>日期：</b> 2015-10-29 11:19:33 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/public class ConnectionAll {


	//首页连通性表id	private int id;
	//网站标识码	private String siteCode;
	//扫描时间（yyyy-dd-mm）	private String scanDate;
	//总次数	private int connectionSum;
	//成功次数	private int successNum;
	//成功占比	private String successProportion;
	//超时次数	private int errorNum;
	//超时占比	private String errorProportion;
	//名称（业务系统名称，关键栏目名称，空（首页））	private String name;
	//类型（1首页面连通性、2、业务系统连通性、3关键栏目连通性）	private int type;
	//连通性的url	private String url;
	// 1： 1天1次  96:1天96次   
	private String connectionSumType;
	// 1天1次 和1天96次  分别有多少条
	private Integer sumTypeCount;	//创建时间	private Date createTime;
	/** set 首页连通性表id */	public void setId(int id){
		this.id=id;
	}
	/** get 首页连通性表id */	public int getId(){
		return id;
	}
	/** set 网站标识码 */	public void setSiteCode(String siteCode){
		this.siteCode=siteCode;
	}
	/** get 网站标识码 */	public String getSiteCode(){
		return siteCode;
	}
	/** set 扫描时间（yyyy-dd-mm） */	public void setScanDate(String scanDate){
		this.scanDate=scanDate;
	}
	/** get 扫描时间（yyyy-dd-mm） */	public String getScanDate(){
		return scanDate;
	}
	/** set 总次数 */	public void setConnectionSum(int connectionSum){
		this.connectionSum=connectionSum;
	}
	/** get 总次数 */	public int getConnectionSum(){
		return connectionSum;
	}
	/** set 成功次数 */	public void setSuccessNum(int successNum){
		this.successNum=successNum;
	}
	/** get 成功次数 */	public int getSuccessNum(){
		return successNum;
	}
	/** set 成功占比 */	public void setSuccessProportion(String successProportion){
		this.successProportion=successProportion;
	}
	/** get 成功占比 */	public String getSuccessProportion(){
		return successProportion;
	}
	/** set 超时次数 */	public void setErrorNum(int errorNum){
		this.errorNum=errorNum;
	}
	/** get 超时次数 */	public int getErrorNum(){
		return errorNum;
	}
	/** set 超时占比 */	public void setErrorProportion(String errorProportion){
		this.errorProportion=errorProportion;
	}
	/** get 超时占比 */	public String getErrorProportion(){
		return errorProportion;
	}
	/** set 名称（业务系统名称，关键栏目名称，空（首页）） */	public void setName(String name){
		this.name=name;
	}
	/** get 名称（业务系统名称，关键栏目名称，空（首页）） */	public String getName(){
		return name;
	}
	/** set 类型（1首页面连通性、2、业务系统连通性、3关键栏目连通性） */	public void setType(int type){
		this.type=type;
	}
	/** get 类型（1首页面连通性、2、业务系统连通性、3关键栏目连通性） */	public int getType(){
		return type;
	}
	/** set 连通性的url */	public void setUrl(String url){
		this.url=url;
	}
	/** get 连通性的url */	public String getUrl(){
		return url;
	}
	/** set 创建时间 */	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}
	/** get 创建时间 */	public Date getCreateTime(){
		return createTime;
	}

	@Override
	public String toString() {
		return "ConnectionAll [id=" + id + ", siteCode=" + siteCode
				+ ", scanDate=" + scanDate + ", connectionSum=" + connectionSum
				+ ", successNum=" + successNum + ", successProportion="
				+ successProportion + ", errorNum=" + errorNum
				+ ", errorProportion=" + errorProportion + ", name=" + name
				+ ", type=" + type + ", url=" + url + ", createTime="
				+ createTime + "]";
	}

	public String getConnectionSumType() {
		return connectionSumType;
	}

	public void setConnectionSumType(String connectionSumType) {
		this.connectionSumType = connectionSumType;
	}

	public Integer getSumTypeCount() {
		return sumTypeCount;
	}

	public void setSumTypeCount(Integer sumTypeCount) {
		this.sumTypeCount = sumTypeCount;
	}


	}

