package com.ucap.cloud_web.dto;


import java.util.List;

import com.publics.util.page.Query;
import com.ucap.cloud_web.entity.DatabaseInfo;


/**
* 前台页面传递基础数据<br>
* <b>作者：</b>cuichx<br>
* <b>日期：</b> 2015-10-29 11:05:30 <br>
* <b>版权所有：<b>版权所有(C) 2015<br>
*/
@SuppressWarnings("serial")
public class EarlyInfoRequest extends Query {
	//网站标识码
	private String siteCode;
	//网站标识码精确查询
	private String siteCode1;
	//组织机构编码
	private String orgId;
	//新预警数
	private Integer newEarlyNum;
	
	private List<DatabaseInfo> databaseInfoList;
	//预警类型：1首页连通性，2首页不更新，3栏目不更新，4空白栏目，5互动回应，6内容正确性，7升级改版或者临时关停
	private Integer type;
	//升级改版或者临时关停类型 1.首页连不通比例超过80%，2关键栏目连不通比例超过80%，3网站首页更新量为0，4人工检查时发现，5插码挂码
	private Integer updateGradeType;
	//创建日期
	private String newDate;
	
	//检查类型（0：抽查，1：正常合同）
	private Integer checkType;
	//邮件是否发送
	private Integer emailState;
	
	private String typeIn;
	//网站名称
	private String siteName;
	//预警类型
	private String earlyType;
	//预警个数
	private int earlyNum;
	
	private String url;
	//网站主办单位
	private String director;
	
	private int servicePeriodId;//服务周期id
	
	
	public int getServicePeriodId() {
		return servicePeriodId;
	}

	public void setServicePeriodId(int servicePeriodId) {
		this.servicePeriodId = servicePeriodId;
	}

	public String getSiteCode1() {
		return siteCode1;
	}

	public void setSiteCode1(String siteCode1) {
		this.siteCode1 = siteCode1;
	}

	public Integer getUpdateGradeType() {
		return updateGradeType;
	}

	public void setUpdateGradeType(Integer updateGradeType) {
		this.updateGradeType = updateGradeType;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getEarlyNum() {
		return earlyNum;
	}

	public void setEarlyNum(int earlyNum) {
		this.earlyNum = earlyNum;
	}

	public String getEarlyType() {
		return earlyType;
	}

	public void setEarlyType(String earlyType) {
		this.earlyType = earlyType;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getTypeIn() {
		return typeIn;
	}

	public void setTypeIn(String typeIn) {
		this.typeIn = typeIn;
	}
	
	public Integer getEmailState() {
		return emailState;
	}

	public void setEmailState(Integer emailState) {
		this.emailState = emailState;
	}

	public Integer getCheckType() {
		return checkType;
	}

	public void setCheckType(Integer checkType) {
		this.checkType = checkType;
	}

	public List<DatabaseInfo> getDatabaseInfoList() {
		return databaseInfoList;
	}

	public void setDatabaseInfoList(List<DatabaseInfo> databaseInfoList) {
		this.databaseInfoList = databaseInfoList;
	}

	public String getNewDate() {
		return newDate;
	}

	public void setNewDate(String newDate) {
		this.newDate = newDate;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	public Integer getNewEarlyNum() {
		return newEarlyNum;
	}

	public void setNewEarlyNum(Integer newEarlyNum) {
		this.newEarlyNum = newEarlyNum;
	}

}

