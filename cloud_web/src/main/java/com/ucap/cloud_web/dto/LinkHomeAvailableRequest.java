package com.ucap.cloud_web.dto;


import java.util.List;

import com.publics.util.page.Query;
import com.ucap.cloud_web.entity.DatabaseInfo;


/*** 前台页面传递基础数据<br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2015-10-29 11:06:19 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/@SuppressWarnings("serial")public class LinkHomeAvailableRequest extends Query {
	/**
	 * 根据网站表示码和扫描时间查询首页连接可用性表
	 */
	private String siteCode;//网站标识码
	private String scanDate;//扫描时间（yyyy-dd-mm）
	private String term;//查询条件
	
	private String startDate;//开始时间
	private String endDate;//最后时间
	private String beginDate;//开始时间
	private String lastDate;//最后时间
	private int LinkNotavailableDay;//长期不可用天数
	private String groupBy;//分组
	private List<DatabaseInfo> databaseList;
	private Integer questionCode;//问题代码  确认疑似首页不可用链接
	private String[] questionCodeArr;//错误代码 确认疑似全站死链
	private String group;//分组
	private List<String> linkList;
	
	private Integer countNum;//统计数
	
	




	public Integer getCountNum() {
		return countNum;
	}

	public void setCountNum(Integer countNum) {
		this.countNum = countNum;
	}

	public int getLinkNotavailableDay() {
		return LinkNotavailableDay;
	}
	
	public List<String> getLinkList() {
		return linkList;
	}

	public void setLinkList(List<String> linkList) {
		this.linkList = linkList;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public void setLinkNotavailableDay(int linkNotavailableDay) {
		LinkNotavailableDay = linkNotavailableDay;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public List<DatabaseInfo> getDatabaseList() {
		return databaseList;
	}

	public void setDatabaseList(List<DatabaseInfo> databaseList) {
		this.databaseList = databaseList;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	public String getScanDate() {
		return scanDate;
	}

	public void setScanDate(String scanDate) {
		this.scanDate = scanDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getGroupBy() {
		return groupBy;
	}

	public void setGroupBy(String groupBy) {
		this.groupBy = groupBy;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getLastDate() {
		return lastDate;
	}

	public void setLastDate(String lastDate) {
		this.lastDate = lastDate;
	}

	public Integer getQuestionCode() {
		return questionCode;
	}

	public void setQuestionCode(Integer questionCode) {
		this.questionCode = questionCode;
	}

	public String[] getQuestionCodeArr() {
		return questionCodeArr;
	}

	public void setQuestionCodeArr(String[] questionCodeArr) {
		this.questionCodeArr = questionCodeArr;
	}


}

