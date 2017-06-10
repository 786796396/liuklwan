package com.ucap.cloud_web.entity;


import java.util.Date;


/*** <br>* <b>作者：</b>liukl<br>* <b>日期：</b> 2017-03-08 14:46:28 <br>* <b>版权所有：<b>版权所有(C) 2017<br>*/public class BigSiteOverview {


	//	private int id;
	//父类id	private int parentId;
	//站点标识码	private String siteCode;
	//网站名称	private String name;
	//关停站点	private int sumClose;
	//例外站点	private int sumException;
	//暂不监测站点	private int sumNo;
	//全面监测站点	private int sumNormal;
	//上报站点	private int sumReport;
	//扫描时间	private String scanDate;
	//创建时间	private Date createTime;
	//修改时间	private Date modifyTime;
	/** set  */	public void setId(int id){
		this.id=id;
	}
	/** get  */	public int getId(){
		return id;
	}
	/** set 父类id */	public void setParentId(int parentId){
		this.parentId=parentId;
	}
	/** get 父类id */	public int getParentId(){
		return parentId;
	}
	/** set 站点标识码 */	public void setSiteCode(String siteCode){
		this.siteCode=siteCode;
	}
	/** get 站点标识码 */	public String getSiteCode(){
		return siteCode;
	}
	/** set 网站名称 */	public void setName(String name){
		this.name=name;
	}
	/** get 网站名称 */	public String getName(){
		return name;
	}
	/** set 关停站点 */	public void setSumClose(int sumClose){
		this.sumClose=sumClose;
	}
	/** get 关停站点 */	public int getSumClose(){
		return sumClose;
	}
	/** set 例外站点 */	public void setSumException(int sumException){
		this.sumException=sumException;
	}
	/** get 例外站点 */	public int getSumException(){
		return sumException;
	}
	/** set 暂不监测站点 */	public void setSumNo(int sumNo){
		this.sumNo=sumNo;
	}
	/** get 暂不监测站点 */	public int getSumNo(){
		return sumNo;
	}
	/** set 全面监测站点 */	public void setSumNormal(int sumNormal){
		this.sumNormal=sumNormal;
	}
	/** get 全面监测站点 */	public int getSumNormal(){
		return sumNormal;
	}
	/** set 上报站点 */	public void setSumReport(int sumReport){
		this.sumReport=sumReport;
	}
	/** get 上报站点 */	public int getSumReport(){
		return sumReport;
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

	public String getScanDate() {
		return scanDate;
	}

	public void setScanDate(String scanDate) {
		this.scanDate = scanDate;
	}
}

