package com.ucap.cloud_web.dto;


import java.util.List;

import com.publics.util.page.Query;
import com.ucap.cloud_web.entity.DatabaseInfo;

/*** 前台页面传递基础数据<br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2016-01-05 13:11:41 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@SuppressWarnings("serial")public class IndexCountRequest extends Query {
	//网站标识码
	private String siteCode;
	//网站名称
	private String siteName;
	//健康指数统计总数
	private Double totalSum;
	//健康折算分数
	private Double convertScores;
	//查询健康指数的网站个数
	private Integer indexLength;
	//集合参数
	private List<DatabaseInfo> list;
	//扫描日期
	private String scanDate;
	//套餐Id
	private Integer comboId;
	//套餐编码
	private String comboCode;
	//类型;1付费，2免费，3抽查
	private Integer type;
	//套餐类型数据
	private String[] comboArray;
	//网站标识码集合
	private List<String> siteCodeList;

	
	public List<String> getSiteCodeList() {
		return siteCodeList;
	}
	public void setSiteCodeList(List<String> siteCodeList) {
		this.siteCodeList = siteCodeList;
	}
	public Integer getIndexLength() {
		return indexLength;
	}
	public void setIndexLength(Integer indexLength) {
		this.indexLength = indexLength;
	}
	public String getComboCode() {
		return comboCode;
	}
	public void setComboCode(String comboCode) {
		this.comboCode = comboCode;
	}

	public String[] getComboArray() {
		return comboArray;
	}
	public void setComboArray(String[] comboArray) {
		this.comboArray = comboArray;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getComboId() {
		return comboId;
	}
	public void setComboId(Integer comboId) {
		this.comboId = comboId;
	}
	public Double getConvertScores() {
		return convertScores;
	}
	public void setConvertScores(Double convertScores) {
		this.convertScores = convertScores;
	}
	public String getSiteCode() {
		return siteCode;
	}
	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public List<DatabaseInfo> getList() {
		return list;
	}
	public void setList(List<DatabaseInfo> list) {
		this.list = list;
	}
	public String getScanDate() {
		return scanDate;
	}
	public void setScanDate(String scanDate) {
		this.scanDate = scanDate;
	}
	public Double getTotalSum() {
		return totalSum;
	}
	public void setTotalSum(Double totalSum) {
		this.totalSum = totalSum;
	}
}

