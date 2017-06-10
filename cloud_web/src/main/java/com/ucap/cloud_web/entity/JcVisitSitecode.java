package com.ucap.cloud_web.entity;


import java.util.Date;


/*** <br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-12-13 20:29:02 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public class JcVisitSitecode {


	//主键	private int id;
	//sitecode网站标识码	private String siteCode;
	//访问次数	private int count;
	//当天此sitecode url统计数	private int urlCnt;
	//当天此sitecode ip统计数	private int ipCnt;
	//首页url计数	private int homeUrlCnt;
	//访问日期（日志日期）	private String visitDate;
	//创建时间	private Date createTime;
	//修改日期	private Date modifyTime;
	
	//网站名
	private String name;

	//url
	private String url;
	
	//跳转url
	private String jumpUrl;
	
		public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getJumpUrl() {
		return jumpUrl;
	}

	public void setJumpUrl(String jumpUrl) {
		this.jumpUrl = jumpUrl;
	}

	/** set 主键 */	public void setId(int id){
		this.id=id;
	}
	/** get 主键 */	public int getId(){
		return id;
	}
	/** set sitecode网站标识码 */	public void setSiteCode(String siteCode){
		this.siteCode=siteCode;
	}
	/** get sitecode网站标识码 */	public String getSiteCode(){
		return siteCode;
	}
	/** set 访问次数 */	public void setCount(int count){
		this.count=count;
	}
	/** get 访问次数 */	public int getCount(){
		return count;
	}
	/** set 当天此sitecode url统计数 */	public void setUrlCnt(int urlCnt){
		this.urlCnt=urlCnt;
	}
	/** get 当天此sitecode url统计数 */	public int getUrlCnt(){
		return urlCnt;
	}
	/** set 当天此sitecode ip统计数 */	public void setIpCnt(int ipCnt){
		this.ipCnt=ipCnt;
	}
	/** get 当天此sitecode ip统计数 */	public int getIpCnt(){
		return ipCnt;
	}
	/** set 首页url计数 */	public void setHomeUrlCnt(int homeUrlCnt){
		this.homeUrlCnt=homeUrlCnt;
	}
	/** get 首页url计数 */	public int getHomeUrlCnt(){
		return homeUrlCnt;
	}
	/** set 访问日期（日志日期） */	public void setVisitDate(String visitDate){
		this.visitDate=visitDate;
	}
	/** get 访问日期（日志日期） */	public String getVisitDate(){
		return visitDate;
	}
	/** set 创建时间 */	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}
	/** get 创建时间 */	public Date getCreateTime(){
		return createTime;
	}
	/** set 修改日期 */	public void setModifyTime(Date modifyTime){
		this.modifyTime=modifyTime;
	}
	/** get 修改日期 */	public Date getModifyTime(){
		return modifyTime;
	}
}

