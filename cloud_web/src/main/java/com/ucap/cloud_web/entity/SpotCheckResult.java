package com.ucap.cloud_web.entity;


import java.util.Date;

import com.publics.util.utils.StringUtils;
import com.ucap.cloud_web.util.CommonUtils;


/*** <br>* <b>作者：</b>yuangw@ucap.com.cn<br>* <b>日期：</b> 2016-04-19 11:16:19 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public class SpotCheckResult {


	//抽查任务结果表id	private int id;
	//抽查进度表Id	private int spotCheckSchedule;
	//组织单位site_code	private String orgSiteCode;
	//网站标识码	private String siteCode;
	//网站名称	private String siteName;
	//url	private String url;
	//省	private String province;
	//市	private String city;
	//县	private String county;
	//是否门户（0：否，1：是）	private int isorganizational;
	//主办单位	private String director;
	//抽查状态（-1:未提交，0：未启动，1:检查中，2：扫描完成 ，3：报告完成）	private int state;
	//检查结果（1：合格，0：单项否决，2检查中）	private int checkResult;
	//创建时间	private Date createTime;
	//修改时间	private Date modifyTime;

	//报告审核结果（0：待审核，1：通过，2：不通过）
	private int checkReportResult;
	/** set 抽查任务结果表id */	public void setId(int id){
		this.id=id;
	}
	/** get 抽查任务结果表id */	public int getId(){
		return id;
	}
	/** set 抽查进度表Id */	public void setSpotCheckSchedule(int spotCheckSchedule){
		this.spotCheckSchedule=spotCheckSchedule;
	}
	/** get 抽查进度表Id */	public int getSpotCheckSchedule(){
		return spotCheckSchedule;
	}
	/** set 组织单位site_code */	public void setOrgSiteCode(String orgSiteCode){
		this.orgSiteCode=orgSiteCode;
	}
	/** get 组织单位site_code */	public String getOrgSiteCode(){
		return orgSiteCode;
	}
	/** set 网站标识码 */	public void setSiteCode(String siteCode){
		this.siteCode=siteCode;
	}
	/** get 网站标识码 */	public String getSiteCode(){
		return siteCode;
	}
	/** set 网站名称 */	public void setSiteName(String siteName){
		this.siteName=siteName;
	}
	/** get 网站名称 */	public String getSiteName(){
		return siteName;
	}
	/** set url */	public void setUrl(String url){
		this.url=url;
	}
	/** get url */	public String getUrl(){
		return url;
	}
	/** set 省 */	public void setProvince(String province){
		this.province=province;
	}
	/** get 省 */	public String getProvince(){
		return province;
	}
	/** set 市 */	public void setCity(String city){
		this.city=city;
	}
	/** get 市 */	public String getCity(){
		return city;
	}
	/** set 县 */	public void setCounty(String county){
		this.county=county;
	}
	/** get 县 */	public String getCounty(){
		return county;
	}
	/** set 是否门户（0：否，1：是） */	public void setIsorganizational(int isorganizational){
		this.isorganizational=isorganizational;
	}
	/** get 是否门户（0：否，1：是） */	public int getIsorganizational(){
		return isorganizational;
	}
	/** set 主办单位 */	public void setDirector(String director){
		this.director=director;
	}
	/** get 主办单位 */	public String getDirector(){
		return director;
	}
	/** set 抽查状态（-1:未提交，0：未启动，1:检查中，2：扫描完成 ，3：报告完成） */	public void setState(int state){
		this.state=state;
	}
	/** get 抽查状态（-1:未提交，0：未启动，1:检查中，2：扫描完成 ，3：报告完成） */	public int getState(){
		return state;
	}
	/** set 检查结果（1：合格，0：单项否决） */	public void setCheckResult(int checkResult){
		this.checkResult=checkResult;
	}
	/** get 检查结果（1：合格，0：单项否决） */	public int getCheckResult(){
		return checkResult;
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

	/**get报告审核结果*/
	public int getCheckReportResult() {
		return checkReportResult;
	}

	/**set报告审核结果*/
	public void setCheckReportResult(int checkReportResult) {
		this.checkReportResult = checkReportResult;
	}
}

