package com.ucap.cloud_web.dto;



import com.publics.util.page.Query;


/**
* 前台页面传递基础数据<br>
* <b>作者：</b>SunJiang<br>
* <b>日期：</b> 2015-10-30 09:51:22 <br>
* <b>版权所有：<b>版权所有(C) 2015<br>
*/
@SuppressWarnings("serial")
public class UpdateChannelDetailRequest extends Query {

	private String siteCode;//网站标识码
	private String scanDate;//扫描时间
	private String url;//栏目url
	private String beginScanDate;//扫描开始时间
	private String endScanDate;//扫描结束时间
	private String title;//标题，用来关键字查询
	private Integer dicChannelId;//栏目类型Id
	private String term;//查询条件
	private String[] types;//栏目分类
	private String[] urlArray;//url数组
	
	
	public String[] getTypes() {
		return types;
	}
	public void setTypes(String[] types) {
		this.types = types;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public Integer getDicChannelId() {
		return dicChannelId;
	}
	public void setDicChannelId(Integer dicChannelId) {
		this.dicChannelId = dicChannelId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBeginScanDate() {
		return beginScanDate;
	}
	public void setBeginScanDate(String beginScanDate) {
		this.beginScanDate = beginScanDate;
	}
	public String getEndScanDate() {
		return endScanDate;
	}
	public void setEndScanDate(String endScanDate) {
		this.endScanDate = endScanDate;
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String[] getUrlArray() {
		return urlArray;
	}
	public void setUrlArray(String[] urlArray) {
		this.urlArray = urlArray;
	}

		
	
}

