package com.ucap.cloud_web.entity;


import java.util.Date;


/*** <br>* <b>作者：</b>zhaoDY<br>* <b>日期：</b> 2017-02-14 19:09:38 <br>* <b>版权所有：<b>版权所有(C) 2017<br>*/public class BigOrgTrend {


	//主键	private int id;

	//站点名称
	private String name;
		//站点标识码	private String siteCode;
	//监测时间	private String scanDate;
	//当日健康指数	private String healthindex;
	//当日健康指数趋势	private String healthindexTrend;
	//当日连不通网站占比	private String linkerrsiteprop;
	//当日连不通网站占比趋势	private String linkerrsitepropTrend;
	//当日更新量	private String updatenum;
	//当日更新量趋势	private String updatenumTrend;
	//创建时间	private Date createTime;
	//修改时间	private Date modifyTime;

	
		public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/** set  */	public void setId(int id){
		this.id=id;
	}
	/** get  */	public int getId(){
		return id;
	}
	/** set 站点标识码 */	public void setSiteCode(String siteCode){
		this.siteCode=siteCode;
	}
	/** get 站点标识码 */	public String getSiteCode(){
		return siteCode;
	}
	/** set 监测时间 */	public void setScanDate(String scanDate){
		this.scanDate=scanDate;
	}
	/** get 监测时间 */	public String getScanDate(){
		return scanDate;
	}
	/** set 当日健康指数 */	public void setHealthindex(String healthindex){
		this.healthindex=healthindex;
	}
	/** get 当日健康指数 */	public String getHealthindex(){
		return healthindex;
	}
	/** set 当日健康指数趋势 */	public void setHealthindexTrend(String healthindexTrend){
		this.healthindexTrend=healthindexTrend;
	}
	/** get 当日健康指数趋势 */	public String getHealthindexTrend(){
		return healthindexTrend;
	}
	/** set 当日连不通网站占比 */	public void setLinkerrsiteprop(String linkerrsiteprop){
		this.linkerrsiteprop=linkerrsiteprop;
	}
	/** get 当日连不通网站占比 */	public String getLinkerrsiteprop(){
		return linkerrsiteprop;
	}
	/** set 当日连不通网站占比趋势 */	public void setLinkerrsitepropTrend(String linkerrsitepropTrend){
		this.linkerrsitepropTrend=linkerrsitepropTrend;
	}
	/** get 当日连不通网站占比趋势 */	public String getLinkerrsitepropTrend(){
		return linkerrsitepropTrend;
	}
	/** set 当日更新量 */	public void setUpdatenum(String updatenum){
		this.updatenum=updatenum;
	}
	/** get 当日更新量 */	public String getUpdatenum(){
		return updatenum;
	}
	/** set 当日更新量趋势 */	public void setUpdatenumTrend(String updatenumTrend){
		this.updatenumTrend=updatenumTrend;
	}
	/** get 当日更新量趋势 */	public String getUpdatenumTrend(){
		return updatenumTrend;
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

